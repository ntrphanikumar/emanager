package com.ntrphanikumar.emanager.registration.services;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.collect.Lists;
import com.ntrphanikumar.emanager.dtos.InMemoryPersistable;

public class InMemoryTable<T extends InMemoryPersistable> {

    private final AtomicInteger id;

    private final Map<Integer, T> objById;

    public InMemoryTable(Map<Integer, T> objById, AtomicInteger id) {
        this.objById = objById;
        this.id = id;
    }

    public int insert(T obj) {
        id.incrementAndGet();
        int idValue = id.intValue();
        obj.setId(idValue);
        objById.put(obj.getId(), obj);
        return idValue;
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
