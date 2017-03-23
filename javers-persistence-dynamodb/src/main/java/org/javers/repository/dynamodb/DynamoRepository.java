package org.javers.repository.dynamodb;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import org.javers.core.commit.Commit;
import org.javers.core.commit.CommitId;
import org.javers.core.json.JsonConverter;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.core.metamodel.object.GlobalId;
import org.javers.core.metamodel.type.EntityType;
import org.javers.core.metamodel.type.ManagedType;
import org.javers.repository.api.JaversRepository;
import org.javers.repository.api.QueryParams;
import org.javers.repository.api.SnapshotIdentifier;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * TODO implement me
 */
public class DynamoRepository implements JaversRepository {
    private JsonConverter jsonConverter;
    private final DynamoDB dynamoDB;

    public DynamoRepository(DynamoDB dynamoDB) {
        this.dynamoDB = dynamoDB;
    }

    @Override
    public List<CdoSnapshot> getStateHistory(GlobalId globalId, QueryParams queryParams) {
        return null;
    }

    @Override
    public List<CdoSnapshot> getValueObjectStateHistory(EntityType ownerEntity, String path, QueryParams queryParams) {
        return null;
    }

    @Override
    public List<CdoSnapshot> getStateHistory(Set<ManagedType> givenClasses, QueryParams queryParams) {
        return null;
    }

    @Override
    public Optional<CdoSnapshot> getLatest(GlobalId globalId) {
        return null;
    }

    @Override
    public List<CdoSnapshot> getSnapshots(QueryParams queryParams) {
        return null;
    }

    @Override
    public List<CdoSnapshot> getSnapshots(Collection<SnapshotIdentifier> snapshotIdentifiers) {
        return null;
    }

    @Override
    public void persist(Commit commit) {
    }

    @Override
    public CommitId getHeadId() {
        return null;
    }

    @Override
    public void setJsonConverter(JsonConverter jsonConverter) {
        this.jsonConverter = jsonConverter;
    }

    @Override
    public void ensureSchema() {
    }

    void clean() {
    }
}
