package com.bilionix.core.interfaces.persistence;

/**
 * Интерфейс менеджера объектов справочника.
 */
public interface ReferenceEntityManager<T extends PersistableEntity> extends NamedEntityManager<T> {
    /**
     * Ищет объект по коду справочника.
     * @param  code код справочника
     * @return искомый объект
     */
    T getByCode(String code);
}
