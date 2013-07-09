package com.akrantha.emanager.eservice.steps;

import java.util.Date;

import com.akrantha.emanager.dtos.Employee;
import com.akrantha.emanager.eservice.EmployeeService;

public abstract class AbstractEServiceSteps {

    protected static final String NAME = "James Bond";
    protected static final String EMAIL = "jbond@akrantha.com";
    protected static final String EXTN = "12324";
    protected static final Date DOB = new Date();

    private final EmployeeService employeeService;

    private final EmployeeSharedData employeeSharedData;

    public AbstractEServiceSteps(EmployeeService employeeService,
            EmployeeSharedData employeeSharedData) {
        this.employeeService = employeeService;
        this.employeeSharedData = employeeSharedData;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public EmployeeSharedData getEmployeeSharedData() {
        return employeeSharedData;
    }

    protected Employee buildRequestEmployee(String name, String email, Date dob, String extn) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setEmail(email);
        employee.setExtn(extn);
        employee.setDob(dob);
        employeeSharedData.setRequestEmployee(employee);
        return employee;
    }
}
