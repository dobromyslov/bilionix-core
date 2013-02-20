package com.bilionix.core.interfaces.persistence;

/**
 * Интерфейс менеджера именованных доменных объектов.
 */
public interface NamedEntityManager<T extends PersistableEntity> extends PersistableEntityManager<T> {
    /**
     * Ищет объект по имени/названию.
     * @param  name имя/название объекта
     * @return искомый объект
     */
    T getByName(String name);
}
