package com.akrantha.emanager.eservice.steps;

import java.util.List;

import org.jbehave.core.annotations.BeforeScenario;

import com.akrantha.emanager.dtos.Employee;
import com.akrantha.emanager.eservice.EmployeeService;

public class BeforeSteps extends AbstractEServiceSteps {

    public BeforeSteps(EmployeeService employeeService, EmployeeSharedData employeeSharedData) {
        super(employeeService, employeeSharedData);
    }

    @BeforeScenario
    public void beforeScenario() {
        List<Employee> employees = getEmployeeService().getEmployees().getEmployees();
        if (employees.size() > 0) {
            getEmployeeSharedData().setEmployee(employees.get(0));
        } else {
            getEmployeeSharedData().setEmployee(
                    getEmployeeService().createEmployee(
                            buildRequestEmployee(NAME, EMAIL, DOB, EXTN)));
        }
        getEmployeeSharedData().setEmployeeCollection(null);
        getEmployeeSharedData().setRequestEmployee(null);
        getEmployeeSharedData().setResponseEmployee(null);
    }

}
