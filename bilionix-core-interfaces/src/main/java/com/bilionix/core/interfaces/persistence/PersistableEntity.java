package com.bilionix.core.interfaces.persistence;

import com.bilionix.core.interfaces.HasId;

import java.io.Serializable;

/**
 * Интерфейс основного объекта системы, сохраняемого в Persistence.
 */
public interface PersistableEntity extends Serializable, HasId {
}
