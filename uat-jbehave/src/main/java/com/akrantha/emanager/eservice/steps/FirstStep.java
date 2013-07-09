package com.akrantha.emanager.eservice.steps;

import java.io.IOException;
import java.util.Date;

import javax.xml.bind.JAXBException;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import com.akrantha.emanager.dtos.Employee;
import com.akrantha.emanager.eservice.EmployeeService;

public class FirstStep {

    private final EmployeeService employeeService;

    public FirstStep(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Given("step represents a precondition to an event")
    public void givenStepRepresentsAPreconditionToAnEvent() throws IOException,
            IllegalStateException, JAXBException {
        employeeService.getEmployees();
    }

    @When("step represents the occurrence of the event")
    public void whenStepRepresentsTheOccurrenceOfTheEvent() {
        Employee employee = new Employee();
        employee.setDob(new Date());
        employee.setEmail("phanik@imaginea.com");
        employee.setExtn("323434");
        employee.setName("N T R Phani Kumar");
        Employee createEmployee = employeeService.createEmployee(employee);
        System.out.println(createEmployee.getName());
    }

    @Then("step represents the outcome of the event")
    public void thenStepRepresentsTheOutcomeOfTheEvent() {
        // PENDING
    }

}
