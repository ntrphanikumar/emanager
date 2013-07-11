package com.ntrphanikumar.emanager.restapi;

import com.ntrphanikumar.emanager.dtos.Employee;
import com.ntrphanikumar.emanager.dtos.EmployeeCollection;

public interface EmployeeRestService {

    Employee createEmployee(Employee employee);

    void deleteEmployee(int employeeId);

    Employee getEmployee(int employeeId);

    EmployeeCollection getEmployees();

    Employee updateEmployee(int employeeId, Employee employee);

}
