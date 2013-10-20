package org.javers.model.domain.changeType;

import org.javers.model.domain.Change;
import org.javers.model.domain.Diff;
import org.javers.model.domain.GlobalCdoId;
import org.javers.model.domain.PropertyChange;
import org.javers.model.mapping.Property;

/**
 * reference removed from collection (in *ToMany relation)
 *
 * @author bartosz walacik
 */
public class ReferenceRemoved extends PropertyChange {
    private final GlobalCdoId reference;

    public ReferenceRemoved(GlobalCdoId globalCdoId, Diff parent, Property property, GlobalCdoId reference) {
        super(globalCdoId, parent, property);
        this.reference = reference;
    }

    public GlobalCdoId getRemovedReference() {
        return reference;
    }
}
