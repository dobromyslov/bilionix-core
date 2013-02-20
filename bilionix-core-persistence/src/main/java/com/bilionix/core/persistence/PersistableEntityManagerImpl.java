package com.bilionix.core.persistence;

import com.bilionix.core.interfaces.persistence.PersistableEntity;
import com.bilionix.core.interfaces.persistence.PersistableEntityManager;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Реализация основного менеджера доменных объектов.
 */
public class PersistableEntityManagerImpl<T extends PersistableEntity> implements PersistableEntityManager<T> {

    /**
     * JPA-менеджер объектов.
     */
    @PersistenceContext
    protected EntityManager em;

    /**
     * Построитель JPA-запросов.
     */
    private CriteriaBuilder builder;

    /**
     * Класс доменного объекта, с которым ведется работа.
     */
    protected Class<T> clazz;

    /**
     * Конструктор.
     */
    @SuppressWarnings("unchecked")
    public PersistableEntityManagerImpl() {
        getClazz();
    }

    /**
     * Определяет класс доменного объекта, с которым ведется работа.
     * @return класс доменного объекта
     */
    @SuppressWarnings("unchecked")
    protected Class<T> getClazz() {
        if (clazz == null) {
            Type genericSuperclass = getClass().getGenericSuperclass();
            // Возможность создавать объект вне зависимости от параметризации класса
            if (genericSuperclass instanceof ParameterizedType) {
                clazz = (Class<T>) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
            }
        }
        return clazz;
    }

    /**
     * Возвращает текущего построитель JPA-запросов.
     * @return построитель JPA-запросов
     */
    protected CriteriaBuilder getBuilder() {
        if (builder == null) {
            builder = em.getCriteriaBuilder();
        }
        return builder;
    }

    /**
     * Вызывается перед сохранением нового объекта.
     * @param entity новый доменный объект
     */
    protected void beforePersist(T entity) {

    }

    /**
     * Вызывается после сохранения нового объекта.
     * @param entity сохраненный доменный объект
     */
    protected void afterPersist(T entity) {

    }

    /**
     * Вызывается перед обновлением существующего объекта.
     * @param entity необновленный доменный объект
     */
    protected void beforeUpdate(T entity) {

    }

    /**
     * Вызывается после обновления существующего объекта.
     * @param entity обновленный доменный объект
     */
    protected void beforeCreate(T entity) {

    }

    @Override
    public Long persist(T entity) {
        beforePersist(entity);
        if (entity.getId() != null) {
            beforeUpdate(entity);
            entity = em.merge(entity);
        }
        else {
            beforeCreate(entity);
            em.persist(entity);
        }
        afterPersist(entity);
        return entity.getId();
    }

    @Override
    public Map<Long, T> persistAll(Iterable<T> entities) {
        Map<Long, T> result = new HashMap<>();
        for (T e : entities) {
            result.put(persist(e), e);
        }
        return result;
    }

    @Override
    public void remove(T entity) {
        entity = get(entity.getId());
        if (entity != null) {
            em.remove(entity);
        }
    }

    @Override
    public void removeAll(Iterable<T> entities) {
        for (T e : entities) {
            remove(e);
        }
    }

    @Override
    public void remove(Long id) {
        T entity = get(id);
        if (entity != null) {
            em.remove(entity);
        }
    }

    @Override
    public void removeByIds(Iterable<Long> ids) {
        for (Long id : ids) {
            remove(id);
        }
    }

    @Override
    public T get(Long id) {
        T result = null;
        if (id != null && id > 0) {
            result = em.find(clazz, id);
        }
        return result;
    }

    @Override
    public Long getCount() {
        CriteriaQuery<Long> criteria = getBuilder().createQuery(Long.class);
        criteria.select(getBuilder().count(criteria.from(clazz)));

        return em.createQuery(criteria).getSingleResult();
    }

    @Override
    public List<T> listAll(int start, int count) {
        CriteriaQuery<T> criteria = getBuilder().createQuery(clazz);
        criteria.orderBy(getBuilder().asc(criteria.from(clazz).get("id")));
        TypedQuery<T> query = em.createQuery(criteria);
        query.setFirstResult(start);
        if (count > 0) {
            query.setMaxResults(count);
        }
        return query.getResultList();
    }

    @Override
    public List<T> listAll() {
        return listAll(0, 0);
    }

    @Override
    public T getByProperty(String property, Object value) {
        T result;
        try {
            CriteriaQuery<T> criteria = getBuilder().createQuery(clazz);
            criteria.where(getBuilder().equal(criteria.from(clazz).get(property), value));
            result = em.createQuery(criteria).setMaxResults(1).getSingleResult();
        }
        catch (NoResultException e) {
            result = null;
        }
        return result;
    }

    @Override
    public List<T> listByProperty(String property, Object value) {
        CriteriaQuery<T> criteria = getBuilder().createQuery(clazz);
        criteria.where(getBuilder().equal(criteria.from(clazz).get(property), value));
        return em.createQuery(criteria).getResultList();
    }
}