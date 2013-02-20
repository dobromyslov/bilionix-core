package com.bilionix.core.persistence;

import org.hibernate.validator.constraints.NotBlank;
import com.bilionix.core.interfaces.persistence.ReferenceEntity;

import javax.persistence.MappedSuperclass;

/**
 * Элемент справочника системы.
 * Обладает кодом и значением.
 */
@MappedSuperclass
public class ReferenceEntityImpl extends NamedEntityImpl implements ReferenceEntity {

    /**
     * Код по справочнику.
     */
    @NotBlank
    private String code;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }
}
