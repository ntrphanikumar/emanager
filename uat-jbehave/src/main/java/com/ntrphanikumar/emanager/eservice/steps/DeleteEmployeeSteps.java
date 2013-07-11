package com.ntrphanikumar.emanager.eservice.steps;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import com.ntrphanikumar.emanager.eservice.EmployeeService;
import com.ntrphanikumar.emanager.restclient.RestClientException;

public class DeleteEmployeeSteps extends AbstractEServiceSteps {

    public DeleteEmployeeSteps(EmployeeService employeeService,
            EmployeeSharedData employeeSharedData) {
        super(employeeService, employeeSharedData);
    }

    @When("I request do delete a existing employee")
    public void whenIRequestDoDeleteAExistingEmployee() {
        getEmployeeService().deleteEmployee(getEmployeeSharedData().getEmployee().getId());
    }

    @Then("Employee should be removed successfully from system")
    public void thenEmployeeShouldBeRemovedSuccessfullyFromSystem() {
        MatcherAssert.assertThat(
                getEmployeeService().getEmployees().getEmployees()
                        .contains(getEmployeeSharedData().getEmployee()), Matchers.is(false));
    }

    @When("I request do delete a employee with invalid employee id")
    public void whenIRequestDoDeleteAEmployeeWithInvalidEmployeeId() {
        try {
            getEmployeeService().deleteEmployee(-10);
        } catch (RestClientException e) {
            getEmployeeSharedData().setError(e.getErrorDTO());
        }
    }
}
