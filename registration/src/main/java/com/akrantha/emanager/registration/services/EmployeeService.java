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

    public void updateEmployee(int id, Employee employee) {
        validateEmployeeId(id);
        validateEmployee(employee);
        employee.setId(id);
        employeeTable.update(employee);
    }

    public Employee deleteEmployee(int id) {
        validateEmployeeId(id);
        return employeeTable.delete(id);
    }

    public List<Employee> getEmployees() {
        return employeeTable.getAll();
    }

    public Employee getEmployeeById(int id) {
        validateEmployeeId(id);
        return employeeTable.getById(id);
    }

    private void validateEmployeeId(int id) {
        checkArgument(id > 0, "Id should be positive number");
        checkNotNull(employeeTable.getById(id), "No employee exists with id: " + id);
    }

    private void validateEmployee(Employee employee) {
        checkArgument(isNotBlank(employee.getName()), "Employee name cannot be empty");
        checkArgument(isNotBlank(employee.getEmail()), "Employee email cannot be empty");
        checkNotNull(employee.getDob(), "Date of birth cannot be null");
    }

}
