package com.bilionix.core.interfaces.persistence;

import com.bilionix.core.interfaces.HasCode;

/**
 * Интерфейс объекта справочника, обладающего кодом и названием.
 * Сохраняется в Persistence.
 */
public interface ReferenceEntity extends NamedEntity, HasCode {
}
