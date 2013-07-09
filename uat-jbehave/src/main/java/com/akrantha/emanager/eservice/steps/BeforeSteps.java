package com.akrantha.emanager.eservice.steps;

import org.jbehave.core.annotations.BeforeScenario;

import com.akrantha.emanager.eservice.EmployeeService;

public class BeforeSteps extends AbstractEServiceSteps {

    public BeforeSteps(EmployeeService employeeService, EmployeeSharedData employeeSharedData) {
        super(employeeService, employeeSharedData);
    }

    @BeforeScenario
    public void beforeScenario() {
        getEmployeeSharedData().setEmployeeCollection(null);
        getEmployeeSharedData().setRequestEmployee(null);
        getEmployeeSharedData().setResponseEmployee(null);
    }
}
