package org.javers.repository.dynamodb.integration

import com.amazonaws.services.dynamodbv2.document.DynamoDB
import org.javers.core.JaversRepositoryE2ETest
import org.javers.repository.api.JaversRepository
import org.javers.repository.dynamodb.DynamoRepository
import spock.lang.Shared

/**
 * @author bartosz.walacik
 */
class DynamoRepositoryE2ETest extends JaversRepositoryE2ETest {
    @Shared DynamoDB dynamoDB

    void setupSpec() {
        dynamoDB = DynamoDbClientFactory.createLocalDbClient()
    }

    @Override
    def setup() {
        repository.jsonConverter = javers.jsonConverter
    }

    @Override
    protected JaversRepository prepareJaversRepository() {
        DynamoRepository dynamoRepository = new DynamoRepository(dynamoDB)
        dynamoRepository.clean()
        dynamoRepository
    }
}
