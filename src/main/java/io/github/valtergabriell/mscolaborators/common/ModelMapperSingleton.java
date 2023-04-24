package io.github.valtergabriell.mscolaborators.common;

import org.modelmapper.ModelMapper;

public class ModelMapperSingleton {
    private static ModelMapper instance;

    private ModelMapperSingleton() {
        instance = new ModelMapper();
    }

    public static ModelMapper getInstance() {
        if (instance == null) {
            instance = new ModelMapper();
        }
        return instance;
    }
}
