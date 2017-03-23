package org.javers.repository.dynamodb.integration

import com.amazonaws.services.dynamodbv2.document.DynamoDB
import com.amazonaws.services.dynamodbv2.document.Item
import com.amazonaws.services.dynamodbv2.document.Table
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec
import com.amazonaws.services.dynamodbv2.model.*
import spock.lang.Shared
import spock.lang.Specification

class DynamoDbSmokeTest extends Specification {
    @Shared DynamoDB dynamoDB

    void setupSpec() {
        dynamoDB = DynamoDbClientFactory.createLocalDbClient()
    }

    def 'should write & read from DynamoDB'(){
      given:
      //crete table
      def table = getOrCreateTestTable()

      when:
      //insert
      table.putItem(new Item()
              .withPrimaryKey('my_id', '1')
              .with('my_text_col', 'world')) //String

      table.putItem(new Item()
              .withPrimaryKey('my_id', '2')
              .with('my_int_col', 5))        //int

      table.putItem(new Item()
              .withPrimaryKey('my_id', '3')
              .withJSON('my_json_col',       //Document type
              '''{
                   "address": {
                     "city": "London",
                     "streetNo": 5
                   }    
                 }'''))

      //select
      def items = [
            table.getItem(new GetItemSpec().withPrimaryKey('my_id', '1')),
            table.getItem(new GetItemSpec().withPrimaryKey('my_id', '2')),
            table.getItem(new GetItemSpec().withPrimaryKey('my_id', '3'))
      ]

      then:
      items[0].asMap() == ['my_id':'1', 'my_text_col':'world']
      items[1].asMap() == ['my_id':'2', 'my_int_col':5]
      items[2].asMap() == ['my_id':'3', 'my_json_col': [address: [city: "London", streetNo: 5]]]
    }

    Table getOrCreateTestTable() {
        def tableName = 'hello_world'
        if (!tableExists(tableName)) {
            dynamoDB.createTable(
                    tableName,
                    [new KeySchemaElement('my_id', KeyType.HASH)], //partition key
                    [new AttributeDefinition('my_id', ScalarAttributeType.S)], //attributes
                    new ProvisionedThroughput(10L, 10L))
            println 'table $tableName created'
        }

        def table = dynamoDB.getTable(tableName)
        table.waitForActive()

        println 'table status: ' + table.describe().tableStatus
        table
    }

    boolean tableExists(String tableName) {
        try {
            dynamoDB.getTable(tableName).describe()
            return true
        } catch (ResourceNotFoundException e) {
            return false
        }
    }
}
