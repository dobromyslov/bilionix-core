package com.bilionix.core.interfaces.persistence;

import java.util.List;
import java.util.Map;

/**
 * Интерфейс основного менеджера доменных объектов.
 */
public interface PersistableEntityManager<T extends PersistableEntity> {
    /**
     * Сохраняет или обновляет указанный объект.
     * @param  entity оъект, который необходимо сохранить
     * @return присвоенный идентификатор объекта
     */
    Long persist(T entity);

    /**
     * Сохраняет или обновляет список объектов.
     * @param  entities список объектов, которые необходимо сохранить
     * @return список присвоенных идентификаторов и объектов
     */
    Map<Long, T> persistAll(Iterable<T> entities);

    /**
     * Удаляет указанный объект.
     * @param entity объект, который необходимо удалить
     */
    void remove(T entity);

    /**
     * Удаляет список объектов.
     * @param entities список объектов, которые необходимо удалить
     */
    void removeAll(Iterable<T> entities);

    /**
     * Удаляет объект с указанным идентификатором.
     * @param id идентификатор объекта, подлежащего удалению
     */
    void remove(Long id);

    /**
     * Удаляет список объектов с указанными идентификаторами.
     * @param ids список идентификаторов
     */
    void removeByIds(Iterable<Long> ids);

    /**
     * Получает объект по указанному идентификатору.
     * @param id идентификатор объекта
     * @return объект
     */
    T get(Long id);

    /**
     * Считает количество объектов в хранилище.
     * @return количество объектов
     */
    Long getCount();

    /**
     * Получает ограниченный список объектов из хранилища, начиная с заданной позиции start.
     * Размер списка ограничен параметном count.
     * @param  start индекс начального объекта
     * @param  count максимальное количество объектов
     * @return ограниченный список объектов
     */
    List<T> listAll(int start, int count);

    /**
     * Получает все объекты из хранилища.
     * @return полный список объектов
     */
    List<T> listAll();

    /**
     * Ищет один объект по значению атрибута.
     * Если найдено несколько объектов с одинаковым значением атрибута, то возвращает первый по списку.
     * @param property название атрибута
     * @param value значение атрибута
     * @return искомый объект
     */
    T getByProperty(String property, Object value);

    /**
     * Ищет несколько объектов по значению атрибута.
     * @param  property название атрибута
     * @param  value значение атрибута
     * @return список искомых объектов
     */
    List<T> listByProperty(String property, Object value);
}
