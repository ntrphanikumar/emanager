package com.akrantha.emanager.registration.services;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.akrantha.emanager.dtos.InMemoryPersistable;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class InMemoryTable<T extends InMemoryPersistable> {

    private final AtomicInteger id = new AtomicInteger(0);

    private final Map<Integer, T> objById = Maps.newLinkedHashMap();

    public int insert(T obj) {
        obj.setId(id.incrementAndGet());
        objById.put(obj.getId(), obj);
        return obj.getId();
    }

    public T delete(int id) {
        return objById.remove(id);
    }

    public void update(T obj) {
        objById.put(obj.getId(), obj);
    }

    public List<T> getAll() {
        return Lists.newArrayList(objById.values());
    }

    public T getById(int id) {
        return objById.get(id);
    }
}
