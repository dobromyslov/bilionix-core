package com.bilionix.core.persistence;

import org.hibernate.validator.constraints.NotBlank;
import com.bilionix.core.interfaces.persistence.NamedEntity;

import javax.persistence.MappedSuperclass;

/**
 * Объект системы, обладающий именем/названием.
 */
@MappedSuperclass
public class NamedEntityImpl extends PersistableEntityImpl implements NamedEntity {

    /**
     * Имя/название объекта.
     */
    @NotBlank
    private String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
