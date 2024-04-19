package com.hasanjahidul.docManagement.utils;

import com.hasanjahidul.docManagement.entity.BaseEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.Collection;

@Component
public class SoftDeleteUtil {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public <T> void softDelete(T entity, JpaRepository<T, Long> repository, String... childNames) {
        if (entity == null) {
            return;
        }

        markEntityAndChildrenAsDeleted(entity, childNames);

        repository.save(entity);
    }

    private void markEntityAndChildrenAsDeleted(Object entity, String... childNames) {
        markEntityAsDeleted(entity);

        if (childNames != null) {
            for (String childName : childNames) {
                markChildEntitiesAsDeleted(entity, childName);
            }
        }
    }

    private void markChildEntitiesAsDeleted(Object entity, String childName) {
        try {
            Field field = entity.getClass().getDeclaredField(childName);
            field.setAccessible(true);
            if (Collection.class.isAssignableFrom(field.getType())) {
                Collection<?> children = (Collection<?>) field.get(entity);
                if (children != null) {
                    for (Object child : children) {
                        markEntityAndChildrenAsDeleted(child);
                    }
                }
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void markEntityAsDeleted(Object entity) {
        if (entity instanceof BaseEntity) {
            BaseEntity baseEntity = (BaseEntity) entity;
            baseEntity.setIsDeleted(true);
        }
    }
}