package com.bilionix.core.interfaces;

/**
 * У объекта есть уникальный код.
 * Например, элемент справочника.
 */
public interface HasCode {
    String getCode();
    void setCode(String code);
}
