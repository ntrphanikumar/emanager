package com.ntrphanikumar.emanager.registration.services;

import java.util.Date;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;
import com.ntrphanikumar.emanager.dtos.Employee;
import com.ntrphanikumar.emanager.registration.services.EmployeeService;
import com.ntrphanikumar.emanager.registration.services.InMemoryTable;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    private static final Date DATE = new Date();

    private static final String VALUE = "value";

    private static final int ID = 2;

    private EmployeeService employeeService;

    @Mock
    private InMemoryTable<Employee> employeeTable;

    @Mock
    private Employee employee;

    @Before
    public void setUp() {
        employeeService = new EmployeeService(employeeTable);
        Mockito.when(employeeTable.insert(employee)).thenReturn(ID);
        Mockito.when(employeeTable.getById(ID)).thenReturn(employee);

        Mockito.when(employee.getName()).thenReturn(VALUE);
        Mockito.when(employee.getEmail()).thenReturn(VALUE);
        Mockito.when(employee.getExtn()).thenReturn(VALUE);
        Mockito.when(employee.getDob()).thenReturn(DATE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createEmployeeShouldThrowIllegalArgumentExceptionIfNameIsNull() {
        Mockito.when(employee.getName()).thenReturn(null);
        employeeService.createEmployee(employee);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createEmployeeShouldThrowIllegalArgumentExceptionIfNameIsBlank() {
        Mockito.when(employee.getName()).thenReturn("  ");
        employeeService.createEmployee(employee);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createEmployeeShouldThrowIllegalArgumentExceptionIfEmailIsNull() {
        Mockito.when(employee.getEmail()).thenReturn(null);
        employeeService.createEmployee(employee);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createEmployeeShouldThrowIllegalArgumentExceptionIfEmailIsBlank() {
        Mockito.when(employee.getEmail()).thenReturn("  ");
        employeeService.createEmployee(employee);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createEmployeeShouldThrowIllegalArgumentExceptionIfDateOfBirthIsNull() {
        Mockito.when(employee.getDob()).thenReturn(null);
        employeeService.createEmployee(employee);
    }

    @Test
    public void createEmployeeShouldReturnIdOfNewEmployeeOnSuccessfulCreation() {
        MatcherAssert.assertThat(employeeService.createEmployee(employee), Matchers.is(ID));
        Mockito.verify(employeeTable).insert(employee);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateEmployeeShouldThrowIllegalArgumentExceptionIfNoEmployeeExistsWithId() {
        employeeService.updateEmployee(ID + 1, employee);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateEmployeeShouldThrowIllegalArgumentExceptionIfNameIsNull() {
        Mockito.when(employee.getName()).thenReturn(null);
        employeeService.updateEmployee(ID, employee);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateEmployeeShouldThrowIllegalArgumentExceptionIfNameIsBlank() {
        Mockito.when(employee.getName()).thenReturn("  ");
        employeeService.updateEmployee(ID, employee);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateEmployeeShouldThrowIllegalArgumentExceptionIfEmailIsNull() {
        Mockito.when(employee.getEmail()).thenReturn(null);
        employeeService.updateEmployee(ID, employee);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateEmployeeShouldThrowIllegalArgumentExceptionIfEmailIsBlank() {
        Mockito.when(employee.getEmail()).thenReturn("  ");
        employeeService.updateEmployee(ID, employee);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateEmployeeShouldThrowIllegalArgumentExceptionIfDateOfBirthIsNull() {
        Mockito.when(employee.getDob()).thenReturn(null);
        employeeService.updateEmployee(ID, employee);
    }

    @Test
    public void updateEmployeeShouldUpdateEmployeeInTable() {
        employeeService.updateEmployee(ID, employee);
        Mockito.verify(employeeTable).update(employee);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteEmployeeShouldThrowIllegalArgumentExceptionForInvalidEmployeeId() {
        employeeService.deleteEmployee(ID + 1);
    }

    @Test
    public void deleteEmployeeShouldDeleteEmployeeFromTable() {
        employeeService.deleteEmployee(ID);
        Mockito.verify(employeeTable).delete(ID);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getEmployeeByIdShouldThrowIllegalArgumentExceptionForInvalidEmployeeId() {
        employeeService.getEmployeeById(ID + 1);
    }

    @Test
    public void getEmployeeByIdShouldReturnEmployee() {
        MatcherAssert.assertThat(employeeService.getEmployeeById(ID), Matchers.is(employee));
    }

    @Test
    public void getEmployeesShouldReturnAllEmployeesInTable() {
        Mockito.when(employeeTable.getAll()).thenReturn(Lists.newArrayList(employee));
        MatcherAssert.assertThat(employeeService.getEmployees(), Matchers.contains(employee));
    }
}
