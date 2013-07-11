package com.ntrphanikumar.emanager.dtos;

import java.util.List;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Employees")
public class EmployeeCollection {

    private List<Employee> employees;

    @XmlElementWrapper(name = "Employees")
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

}
