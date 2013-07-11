package com.ntrphanikumar.emanager.eservice.steps;

import com.ntrphanikumar.emanager.dtos.Employee;
import com.ntrphanikumar.emanager.dtos.EmployeeCollection;
import com.ntrphanikumar.emanager.dtos.ErrorDTO;

public class EmployeeSharedData {
    private Employee requestEmployee, responseEmployee, employee;

    private EmployeeCollection employeeCollection;

    private ErrorDTO error;

    public Employee getRequestEmployee() {
        return requestEmployee;
    }

    public void setRequestEmployee(Employee requestEmployee) {
        this.requestEmployee = requestEmployee;
    }

    public Employee getResponseEmployee() {
        return responseEmployee;
    }

    public void setResponseEmployee(Employee responseEmployee) {
        this.responseEmployee = responseEmployee;
    }

    public EmployeeCollection getEmployeeCollection() {
        return employeeCollection;
    }

    public void setEmployeeCollection(EmployeeCollection employeeCollection) {
        this.employeeCollection = employeeCollection;
    }

    public ErrorDTO getError() {
        return error;
    }

    public void setError(ErrorDTO error) {
        this.error = error;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
