package com.ntrphanikumar.emanager.eservice;

import com.ntrphanikumar.emanager.dtos.Employee;
import com.ntrphanikumar.emanager.dtos.EmployeeCollection;
import com.ntrphanikumar.emanager.restclient.RestClient;

public class EmployeeService {

    private static final String EMPLOYEES_URL = "/employees/";
    private final RestClient restClient;

    public EmployeeService(RestClient restClient) {
        this.restClient = restClient;
    }

    public EmployeeCollection getEmployees() {
        return restClient.doGet(EMPLOYEES_URL);
    }

    public Employee getEmployee(int employeeId) {
        return restClient.doGet(EMPLOYEES_URL + employeeId);
    }

    public Employee createEmployee(Employee employee) {
        return restClient.doPost(EMPLOYEES_URL, employee);
    }

    public Employee updateEmployee(Employee employee) {
        return restClient.doPut(EMPLOYEES_URL + employee.getId(), employee);
    }

    public void deleteEmployee(int employeeId) {
        restClient.doDelete(EMPLOYEES_URL + employeeId);
    }
}
