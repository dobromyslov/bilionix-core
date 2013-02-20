package com.bilionix.core.persistence;

import com.bilionix.core.interfaces.persistence.NamedEntity;
import com.bilionix.core.interfaces.persistence.NamedEntityManager;

/**
 * Реализация менеджера именных объектов.
 */
public class NamedEntityManagerImpl<T extends NamedEntity> extends PersistableEntityManagerImpl<T> implements NamedEntityManager<T> {
    @Override
    public T getByName(String name) {
        return getByProperty("name", name);
    }
}