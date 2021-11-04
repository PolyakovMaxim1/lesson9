package com.netcracker.framework;

import com.netcracker.annotations.Inject;
import com.netcracker.factory.Factory;

import java.util.Arrays;

public class StudentServiceInjector implements AfterInitialization {

    @Override
    public void initialize(Object o) {
        Arrays.stream(
                o.getClass().getDeclaredFields()
        )
                .filter(field -> field.isAnnotationPresent(Inject.class))
                .forEach(field -> {
                    field.setAccessible(true);
                    try {
                        field.set(o, Factory.getObjects().get(field.getAnnotation(Inject.class).className()));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
    }
}
