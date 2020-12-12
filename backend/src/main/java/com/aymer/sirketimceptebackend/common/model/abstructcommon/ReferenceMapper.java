package com.aymer.sirketimceptebackend.common.model.abstructcommon;

import org.mapstruct.TargetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class ReferenceMapper {

    private final EntityManager entityManager;

    @Autowired
    public ReferenceMapper(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public <T extends Idendifier> T resolve(Long id, @TargetType Class<T> entityClass) {
        return id != null ? entityManager.find( entityClass, id ) : null;
    }

    public Long toReference(Idendifier entity) {
        return entity != null && entity.getId() != null ? entity.getId() : null;
    }

    public <T extends Idendifier> T resolve(SelectItem selectItem, @TargetType Class<T> entityClass) {
        return selectItem != null && selectItem.getValue() != null ? entityManager.find( entityClass, selectItem.getValue() ) : null;
    }

    public SelectItem toComboItem(Idendifier entity) {
        return entity != null && entity.getId() != null ? new SelectItem(entity.getId(), entity.getAciklama()) : null;
    }
}
