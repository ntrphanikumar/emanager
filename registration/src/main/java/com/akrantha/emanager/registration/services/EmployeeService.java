package com.akrantha.emanager.registration.services;

import java.util.Date;
import java.util.List;

import com.akrantha.emanager.dtos.Employee;

public class EmployeeService {

    private final InMemoryTable<Employee> employeeTable;

    public EmployeeService(InMemoryTable<Employee> employeeTable) {
        this.employeeTable = employeeTable;

        Employee e = new Employee();
        e.setDob(new Date());
        e.setName("Phani Kumar");
        e.setEmail("phani@gmail.com");
        e.setExtn("33243");
        createEmployee(e);
    }

    public int createEmployee(Employee employee) {
        return employeeTable.insert(employee);
    }

    public void updateEmployee(int id, Employee employee) {
        employee.setId(id);
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
