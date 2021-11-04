package com.netcracker.factory;

import com.netcracker.framework.AfterInitialization;
import com.netcracker.framework.StudentServiceInjector;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Factory {
    private static Factory factory;
    private static Map<String, Object> classNameToObject= new HashMap<>();
    private static List<AfterInitialization> initializations = new ArrayList() {
        {
            add(new StudentServiceInjector());
        }
    };

    private Factory(Path path) throws IOException, InstantiationException, IllegalAccessException {
        List<? extends Class<?>> classes = Files.list(path)
                .map(pathToClass -> pathToClass.subpath(2, pathToClass.getNameCount()))
                .map(pathToClass -> pathToClass
                        .toString()
                        .replace("\\", ".")
                        .replace(".java", "")
                )
                .map(pathToClass -> {
                    try {
                        return Class.forName(pathToClass);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .collect(Collectors.toList());

        initialize(classes);
        afterInitialization();
    }

    public static Map<String, Object> getObjects() {
        return classNameToObject;
    }

    public static Factory getInstance(Path path) throws IOException, InstantiationException, IllegalAccessException {
        if (factory == null) {
            factory = new Factory(path);
        }
        return factory;
    }

    private static void initialize(List<? extends Class<?>> classes) {
        for (Class<?> clazz : classes) {
            try {
                if (!clazz.isInterface()) {
                    Object o = clazz.newInstance();
                    classNameToObject.put(clazz.getSimpleName(), o);
                }
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private void afterInitialization() {
        for (Object o : classNameToObject.values()) {
            initializations.forEach(init -> init.initialize(o));
        }
    }
}
