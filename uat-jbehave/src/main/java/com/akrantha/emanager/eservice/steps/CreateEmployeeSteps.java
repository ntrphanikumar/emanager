package com.akrantha.emanager.eservice.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import com.akrantha.emanager.dtos.Employee;
import com.akrantha.emanager.eservice.EmployeeService;
import com.akrantha.emanager.eservice.RestClientException;

public class CreateEmployeeSteps extends AbstractEServiceSteps {

    public CreateEmployeeSteps(EmployeeService employeeService,
            EmployeeSharedData employeeSharedData) {
        super(employeeService, employeeSharedData);
    }

    @When("I try to create an employee without name")
    public void whenITryToCreateAnEmployeeWithoutName() {
        createEmployee(buildRequestEmployee(null, EMAIL, DOB, EXTN));
    }

    @When("I try to create an employee without email")
    public void whenITryToCreateAnEmployeeWithoutEmail() {
        createEmployee(buildRequestEmployee(NAME, null, DOB, EXTN));
    }

    @When("I try to create an employee without date of birth")
    public void whenITryToCreateAnEmployeeWithoutDateOfBirth() {
        createEmployee(buildRequestEmployee(NAME, EMAIL, null, EXTN));
    }

    @When("I try to create an employee without extension")
    public void whenITryToCreateAnEmployeeWithoutExtension() {
        createEmployee(buildRequestEmployee(NAME, EMAIL, DOB, null));
    }

    @When("I try to create an employee with all details filled")
    public void whenITryToCreateAnEmployeeWithAllDetailsFilled() {
        createEmployee(buildRequestEmployee(NAME, EMAIL, DOB, EXTN));
    }

    @Then("I should get created employee in response with all provided details filled")
    public void thenIShouldGetCreatedEmployeeInResponseWithAllProvidedDetailsFilled() {
        assertThat(getEmployeeSharedData().getResponseEmployee().getId(), is(greaterThan(0)));
        assertThat(getEmployeeSharedData().getResponseEmployee(), is(getEmployeeSharedData()
                .getRequestEmployee()));
    }

    private void createEmployee(Employee employee) {
        try {
            employee = getEmployeeService().createEmployee(employee);
            getEmployeeSharedData().setResponseEmployee(employee);
        } catch (RestClientException e) {
            getEmployeeSharedData().setError(e.getErrorDTO());
        }
    }

}
