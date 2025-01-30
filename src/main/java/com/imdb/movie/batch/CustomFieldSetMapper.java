package com.imdb.movie.batch;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class CustomFieldSetMapper<T> implements FieldSetMapper<T> {

    private final Class<T> targetType;

    public CustomFieldSetMapper(Class<T> targetType) {
        this.targetType = targetType;
    }

    @Override
    public T mapFieldSet(FieldSet fieldSet) {
        T instance = createInstance();
        for (String fieldName : fieldSet.getNames()) {
            Field field = getField(fieldName);
            field.setAccessible(true);
            String value = fieldSet.readString(fieldName);
            setFieldValue(instance, field, value);
        }
        return instance;
    }

    private T createInstance() {
        try {
            return targetType.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException("Error creating instance of " + targetType.getName(), e);
        }
    }

    private Field getField(String fieldName) {
        try {
            return targetType.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("Field not found: " + fieldName, e);
        }
    }

    private void setFieldValue(T instance, Field field, String value) {
        try {
            if ("\\N".equals(value)) {
                field.set(instance, null);
            } else {
                setValueBasedOnType(instance, field, value);
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Error setting field value for " + field.getName(), e);
        }
    }

    private void setValueBasedOnType(T instance, Field field, String value) throws IllegalAccessException {
        if (field.getType() == Integer.class) {
            field.set(instance, Integer.valueOf(value));
        } else if (field.getType() == String.class) {
            field.set(instance, value);
        } else if (field.getType() == Double.class) {
            field.set(instance, Double.valueOf(value));
        }
    }
}

