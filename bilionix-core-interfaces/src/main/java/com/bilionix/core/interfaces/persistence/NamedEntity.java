package com.bilionix.core.interfaces.persistence;

import com.bilionix.core.interfaces.HasName;

/**
 * Интерфейс объекта с именем/названием, сохраняемого в Persistence.
 */
public interface NamedEntity extends PersistableEntity, HasName {
}
