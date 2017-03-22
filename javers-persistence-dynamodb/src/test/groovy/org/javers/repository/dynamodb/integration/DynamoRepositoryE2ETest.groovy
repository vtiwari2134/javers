package org.javers.repository.dynamodb.integration

import org.javers.core.JaversRepositoryE2ETest
import org.javers.repository.api.JaversRepository
import org.javers.repository.dynamodb.DynamoRepository

/**
 * @author bartosz.walacik
 */
class DynamoRepositoryE2ETest extends JaversRepositoryE2ETest {
    @Override
    def setup() {
        repository.jsonConverter = javers.jsonConverter
    }

    @Override
    protected JaversRepository prepareJaversRepository() {
        DynamoRepository dynamoRepository = new DynamoRepository()
        dynamoRepository.clean()

        dynamoRepository
    }
}
