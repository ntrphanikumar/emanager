package com.akrantha.emanager.registration.services;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.akrantha.emanager.dtos.Employee;

@RunWith(MockitoJUnitRunner.class)
public class InMemoryTableTest {

    @Mock
    private Employee inMemoryPersistableObject;

    private InMemoryTable<Employee> inMemoryTable;

    @Before
    public void setup() {
        inMemoryTable = new InMemoryTable<Employee>();
    }

    @Test
    public void insertShouldSetIdOnObject() {
        inMemoryTable.insert(inMemoryPersistableObject);
        Mockito.verify(inMemoryPersistableObject).setId(Mockito.anyInt());
    }

    @Test
    public void insertShouldReturnTheIdOfPersistedObject() {
        ArgumentCaptor<Integer> idCaptor = ArgumentCaptor.forClass(Integer.class);
        int id = inMemoryTable.insert(inMemoryPersistableObject);
        Mockito.verify(inMemoryPersistableObject).setId(idCaptor.capture());
        MatcherAssert.assertThat(id, Matchers.is(idCaptor.getValue()));
    }

}
