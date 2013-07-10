package com.akrantha.emanager.eservice.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.Date;

import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;

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

    @Given("I am a resource manager")
    @Alias("I have access to emanager application")
    public void givenIAmAResourceManagerWithAccessToEmanagerApplication() {
    }

    @Then("I should get error response with error description as '%message'")
    public void thenIShouldGetError(String message) {
        assertThat(getEmployeeSharedData().getError().getDescription(), is(message));
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
