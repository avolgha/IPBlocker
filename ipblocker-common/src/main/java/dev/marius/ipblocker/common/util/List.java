package dev.marius.ipblocker.common.util;

import java.util.ArrayList;
import java.util.Arrays;

public class List {

    private final java.util.List<Object> objects = new ArrayList<>();

    public List(Object... objectArray) {
        Arrays.stream(objectArray).iterator().forEachRemaining(objects::add);
    }

    public java.util.List<Object> getObjects() {
        return objects;
    }

    public <T> T get(int index, T to) {
        return (T) objects.get(index).getClass().cast(to);
    }

}
