package org.javers.repository.dynamodb.integration

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.document.DynamoDB

class DynamoDbClientFactory {

    static DynamoDB createLocalDbClient() {
        def credentials = new AWSStaticCredentialsProvider(new BasicAWSCredentials('eee', 'eee'))
        def client = AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(
                new AwsClientBuilder.EndpointConfiguration('http://localhost:8000', 'us-west-2'))
                .withCredentials(credentials)
                .build()

        new DynamoDB(client)
    }
}
