package com.bilionix.core.persistence;

import com.bilionix.core.interfaces.persistence.PersistableEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Основной объект системы.
 */
@MappedSuperclass
public class PersistableEntityImpl implements PersistableEntity {

    /**
     * Идентификатор объекта.
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * Версия объекта.
     * Необходима для Optimistic Concurrency COntrol и GWT RequestFactory.
     */
    @NotNull
    @Version
    @Column(columnDefinition="int4 default 0")
    private Integer version = 0;

    @Override
    public Long getId() {
        return id;
    }

    /**
     * Возвращает версию объекта.
     * @return версия
     */
    public Integer getVersion() {
        return version;
    }
}
