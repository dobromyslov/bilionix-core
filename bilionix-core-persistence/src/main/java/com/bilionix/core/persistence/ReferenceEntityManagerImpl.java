package com.bilionix.core.persistence;

import com.bilionix.core.interfaces.persistence.NamedEntity;
import com.bilionix.core.interfaces.persistence.ReferenceEntityManager;

/**
 * Реализация менеджера объектов справочника.
 */
public class ReferenceEntityManagerImpl<T extends NamedEntity> extends NamedEntityManagerImpl<T> implements ReferenceEntityManager<T> {
    @Override
    public T getByCode(String code) {
        return getByProperty("code", code);
    }
}