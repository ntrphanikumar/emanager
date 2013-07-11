package com.ntrphanikumar.emanager.registration.rest.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.ntrphanikumar.emanager.dtos.Employee;
import com.ntrphanikumar.emanager.registration.rest.services.EmployeeRestServiceImpl;
import com.ntrphanikumar.emanager.registration.services.EmployeeService;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeRestServiceTest {

    private static final int ID = 3;

    private EmployeeRestServiceImpl employeeRestServiceImpl;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private Employee employee;

    @Before
    public void setup() {
        employeeRestServiceImpl = new EmployeeRestServiceImpl(employeeService);
        when(employeeService.getEmployeeById(ID)).thenReturn(employee);
    }

    @Test
    public void createEmployeeShouldCallEmployeeServiceToCreateEmployee() {
        Employee createEmployee = employeeRestServiceImpl.createEmployee(employee);
        verify(employeeService).createEmployee(employee);
        verify(employee).setId(employeeService.createEmployee(employee));
        assertThat(createEmployee, is(employee));
    }

    @Test
    public void deleteEmployeeShouldCallEmployeeServiceToDeleteEmployee() {
        employeeRestServiceImpl.deleteEmployee(ID);
        verify(employeeService).deleteEmployee(ID);
    }

    @Test
    public void getEmployeeShouldReturnEmployeeFromEmployeeService() {
        assertThat(employeeRestServiceImpl.getEmployee(ID), is(employee));
    }

    @Test
    public void updateEmployeeShouldUpdateEmployeeWithEmployeeService() {
        assertThat(employeeRestServiceImpl.updateEmployee(ID, employee), is(employee));
        verify(employeeService).updateEmployee(ID, employee);
    }

    @Test
    public void getEmployeesShouldReturnEmployeeCollectionFromEmployeeService() {
        assertThat(employeeRestServiceImpl.getEmployees().getEmployees(),
                is(employeeService.getEmployees()));
    }
}
