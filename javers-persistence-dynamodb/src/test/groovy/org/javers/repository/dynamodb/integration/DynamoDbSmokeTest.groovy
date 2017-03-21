package org.javers.repository.dynamodb.integration

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.document.DynamoDB
import com.amazonaws.services.dynamodbv2.model.*
import spock.lang.Shared
import spock.lang.Specification

class DynamoDbSmokeTest extends Specification {
    @Shared DynamoDB dynamoDB

    void setupSpec() {
        def credentials = new AWSStaticCredentialsProvider(new BasicAWSCredentials("eee", "eee"))
        def client = AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(
                    new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-west-2"))
                .withCredentials(credentials)
                .build()
        dynamoDB = new DynamoDB(client)
    }

    def "should write & read from DynamoDB"(){
      given:
      createTestTable()

      expect:
      true
    }

    void createTestTable() {
        def tableName = "hello_world"
        if (!tableExists(tableName)) {
            dynamoDB.createTable(
                    tableName,
                    [new KeySchemaElement("my_id", KeyType.HASH)], //partition key
                    [new AttributeDefinition("my_id", ScalarAttributeType.S)], //attributes
                    new ProvisionedThroughput(10L, 10L))
            println "table $tableName created"
        }

        def table = dynamoDB.getTable(tableName)
        table.waitForActive()

        println "table status: " + table.describe().tableStatus

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
