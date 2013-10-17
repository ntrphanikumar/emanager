package com.ntrphanikumar.emanager.eservice.steps;

import org.jbehave.core.annotations.When;

import com.ntrphanikumar.emanager.dtos.Employee;
import com.ntrphanikumar.emanager.eservice.EmployeeService;
import com.ntrphanikumar.emanager.restclient.RestClientException;

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

	private void createEmployee(Employee employee) {
		try {
			getEmployeeSharedData().setRequestEmployee(employee);
			getEmployeeSharedData().setResponseEmployee(
					getEmployeeService().createEmployee(employee));
		} catch (RestClientException e) {
			getEmployeeSharedData().setError(e.getErrorDTO());
		}
	}

}
