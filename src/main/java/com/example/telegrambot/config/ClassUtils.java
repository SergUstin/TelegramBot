package com.example.telegrambot.config;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Common class for all microservices.
 */
public final class ClassUtils {

    private ClassUtils() {
    }

    /**
     * Возвращает список полей класса type, аналогично Class.getDeclaredFields(), но не только для заданного класса,
     * а и для всей унаследованной им иерархии до toBaseType (включительно, если includeBase = true).
     *
     * @param includeBase filds with base class or not
     * @param toBaseType  type before this class
     * @param type        type of base class
     * @param <T>         type
     * @return list of fields
     */
    public static <T> List<Field> getFields(Class<? extends T> type, Class<T> toBaseType, boolean includeBase) {
        List<Field> fields = new ArrayList<>();
        Class<?> clazz = type;

        while (clazz != toBaseType) {
            Collections.addAll(fields, clazz.getDeclaredFields());
            clazz = clazz.getSuperclass();
        }

        if (includeBase) {
            Collections.addAll(fields, clazz.getDeclaredFields());
        }

        return fields;
    }
}
