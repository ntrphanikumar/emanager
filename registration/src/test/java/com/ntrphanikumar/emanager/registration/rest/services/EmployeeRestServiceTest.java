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
import com.ntrphanikumar.emanager.registration.rest.services.EmployeeRestService;
import com.ntrphanikumar.emanager.registration.services.EmployeeService;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeRestServiceTest {

    private static final int ID = 3;

    private EmployeeRestService employeeRestService;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private Employee employee;

    @Before
    public void setup() {
        employeeRestService = new EmployeeRestService(employeeService);
        when(employeeService.getEmployeeById(ID)).thenReturn(employee);
    }

    @Test
    public void createEmployeeShouldCallEmployeeServiceToCreateEmployee() {
        Employee createEmployee = employeeRestService.createEmployee(employee);
        verify(employeeService).createEmployee(employee);
        verify(employee).setId(employeeService.createEmployee(employee));
        assertThat(createEmployee, is(employee));
    }

    @Test
    public void deleteEmployeeShouldCallEmployeeServiceToDeleteEmployee() {
        employeeRestService.deleteEmployee(ID);
        verify(employeeService).deleteEmployee(ID);
    }

    @Test
    public void getEmployeeShouldReturnEmployeeFromEmployeeService() {
        assertThat(employeeRestService.getEmployee(ID), is(employee));
    }

    @Test
    public void updateEmployeeShouldUpdateEmployeeWithEmployeeService() {
        assertThat(employeeRestService.updateEmployee(ID, employee), is(employee));
        verify(employeeService).updateEmployee(ID, employee);
    }

    @Test
    public void getEmployeesShouldReturnEmployeeCollectionFromEmployeeService() {
        assertThat(employeeRestService.getEmployees().getEmployees(),
                is(employeeService.getEmployees()));
    }
}
