package com.cleancrud.spark.module;

import com.google.inject.Guice;
import com.google.inject.Injector;
import java.util.concurrent.ConcurrentHashMap;

public class InjectorFactory {

    private static final String INJECTORS_KEY = "INJECTORS";
    private static final ConcurrentHashMap<String, Injector> injectors = new ConcurrentHashMap<>();

    private InjectorFactory() {
    }

    public static Injector create() {
        return Guice.createInjector(
            new EntryPointModule(),
            new UseCaseModule()
        );
    }

    public static void addInjectors() {
        injectors.put(INJECTORS_KEY, create());
    }

    public static Injector getInjector() {
        return injectors.get(INJECTORS_KEY);
    }
}
