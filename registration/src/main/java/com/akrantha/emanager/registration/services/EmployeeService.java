package com.akrantha.emanager.registration.services;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.util.List;

import com.akrantha.emanager.dtos.Employee;

public class EmployeeService {

    private final InMemoryTable<Employee> employeeTable;

    public EmployeeService(InMemoryTable<Employee> employeeTable) {
        this.employeeTable = employeeTable;
    }

    public int createEmployee(Employee employee) {
        validateEmployee(employee);
        return employeeTable.insert(employee);
    }

    private void validateEmployee(Employee employee) {
        checkArgument(isNotBlank(employee.getName()), "Employee name cannot be empty");
        checkArgument(isNotBlank(employee.getEmail()), "Employee email cannot be empty");
        checkNotNull(employee.getDob(), "Date of birth cannot be null");
    }

    public void updateEmployee(int id, Employee employee) {
        checkArgument(id > 0, "Id should be positive number");
        validateEmployee(employee);
        employee.setId(id);
        employeeTable.update(employee);
    }

    public Employee deleteEmployee(int id) {
        checkArgument(id > 0, "Id should be positive number");
        return employeeTable.delete(id);
    }

    public List<Employee> getEmployees() {
        return employeeTable.getAll();
    }

    public Employee getEmployeeById(int id) {
        checkArgument(id > 0, "Id should be positive number");
        return employeeTable.getById(id);
    }

}
