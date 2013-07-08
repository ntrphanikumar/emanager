package com.akrantha.emanager.registration.services;

import java.util.List;

import com.akrantha.emanager.registration.dtos.Employee;

public class EmployeeService {

    private final InMemoryTable<Employee> employeeTable;

    public EmployeeService(InMemoryTable<Employee> employeeTable) {
        this.employeeTable = employeeTable;
    }

    public int createEmployee(Employee employee) {
        return employeeTable.insert(employee);
    }

    public void updateEmployee(Employee employee) {
        employeeTable.update(employee);
    }

    public Employee deleteEmployee(int id) {
        return employeeTable.delete(id);
    }

    public List<Employee> getEmployees() {
        return employeeTable.getAll();
    }

    public Employee getEmployeeById(int id) {
        return employeeTable.getById(id);
    }

}
