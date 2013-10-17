package com.ntrphanikumar.emanager.eservice.steps;

import org.jbehave.core.annotations.When;

import com.ntrphanikumar.emanager.dtos.Employee;
import com.ntrphanikumar.emanager.eservice.EmployeeService;
import com.ntrphanikumar.emanager.restclient.RestClientException;

public class UpdateEmployeeSteps extends AbstractEServiceSteps {

	public UpdateEmployeeSteps(EmployeeService employeeService,
			EmployeeSharedData employeeSharedData) {
		super(employeeService, employeeSharedData);
	}

	@When("I try to update an employee without name")
	public void whenITryToUpdateAnEmployeeWithoutName() {
		getEmployeeSharedData().getEmployee().setName(null);
		updateEmployee(getEmployeeSharedData().getEmployee());
	}

	@When("I try to update an employee without email")
	public void whenITryToUpdateAnEmployeeWithoutEmail() {
		getEmployeeSharedData().getEmployee().setEmail(null);
		updateEmployee(getEmployeeSharedData().getEmployee());
	}

	@When("I try to update an employee without date of birth")
	public void whenITryToUpdateAnEmployeeWithoutDateOfBirth() {
		getEmployeeSharedData().getEmployee().setDob(null);
		updateEmployee(getEmployeeSharedData().getEmployee());
	}

	@When("I try to update an employee without extension")
	public void whenITryToUpdateAnEmployeeWithoutExtension() {
		getEmployeeSharedData().getEmployee().setExtn(null);
		updateEmployee(getEmployeeSharedData().getEmployee());
	}

	@When("I try to update an employee with all details filled")
	public void whenITryToUpdateAnEmployeeWithAllDetailsFilled() {
		getEmployeeSharedData().getEmployee().setName("New name");
		updateEmployee(getEmployeeSharedData().getEmployee());
	}

	private void updateEmployee(Employee employee) {
		try {
			getEmployeeSharedData().setRequestEmployee(employee);
			getEmployeeSharedData().setResponseEmployee(
					getEmployeeService().updateEmployee(employee.getId(),
							employee));
		} catch (RestClientException e) {
			getEmployeeSharedData().setError(e.getErrorDTO());
		}
	}

}
