package com.virjar.superappium.util;


import lombok.extern.slf4j.Slf4j;

/**
 * Created by virjar on 17/6/13.
 */
@Slf4j
public class ObjectFactory {

    @SuppressWarnings("unchecked")
    public static <T> T newInstance(String className) {
        try {
            Class<?> aClass = Class.forName(className);
            return (T) aClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("can not create instance for class " + className);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T newInstance(Class<T> tClass) {
        try {
            return tClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("can not create instance for class " + tClass);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> Class<T> classForName(String className) {
        try {
            return (Class<T>) Class.forName(className);
        } catch (Exception e) {
            throw new RuntimeException("can not load class: " + className);
        }
    }
}
