package com.ntrphanikumar.emanager.registration.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.ntrphanikumar.emanager.dtos.Employee;
import com.ntrphanikumar.emanager.registration.services.InMemoryTable;

@RunWith(MockitoJUnitRunner.class)
public class InMemoryTableTest {

    private static final int ID = 21;

    @Mock
    private Employee inMemoryPersistableObject;

    @Mock
    private Map<Integer, Employee> objById;

    @Mock
    private AtomicInteger id;

    private InMemoryTable<Employee> inMemoryTable;

    @Before
    public void setup() {
        inMemoryTable = new InMemoryTable<Employee>(objById, id);
        when(id.intValue()).thenReturn(ID);
    }

    @Test
    public void insertShouldSetIdOnObject() {
        inMemoryTable.insert(inMemoryPersistableObject);
        verify(inMemoryPersistableObject).setId(ID);
    }

    @Test
    public void insertShouldSavePersistedObjectInMapById() {
        inMemoryTable.insert(inMemoryPersistableObject);
        verify(objById).put(inMemoryPersistableObject.getId(), inMemoryPersistableObject);
    }

    @Test
    public void insertShouldReturnTheIdOfPersistedObject() {
        assertThat(inMemoryTable.insert(inMemoryPersistableObject), is(ID));
    }

    @Test
    public void deleteShouldRemoveFromObjectById() {
        inMemoryTable.delete(ID);
        verify(objById).remove(ID);
    }

    @Test
    public void getByIdShouldReturnObjectFromMap() {
        when(objById.get(ID)).thenReturn(inMemoryPersistableObject);
        assertThat(inMemoryTable.getById(ID), is(inMemoryPersistableObject));
    }

    @Test
    public void updateShouldUpdatePersistableObjectInMapById() {
        when(inMemoryPersistableObject.getId()).thenReturn(ID);
        inMemoryTable.update(inMemoryPersistableObject);
        verify(objById).put(ID, inMemoryPersistableObject);
    }

    @Test
    public void getAllShouldReturnAllPersistedObjectsFromObjectByIdMap() {
        assertThat(inMemoryTable.getAll(), is(objById.values()));
    }

}
