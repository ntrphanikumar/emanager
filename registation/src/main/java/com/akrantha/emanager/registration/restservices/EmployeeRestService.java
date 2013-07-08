package com.akrantha.emanager.registration.restservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.akrantha.emanager.registration.dtos.Employee;
import com.akrantha.emanager.registration.services.EmployeeService;

@Path("/employees")
public class EmployeeRestService {

    private final EmployeeService employeeService;

    public EmployeeRestService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @POST
    @Path("/create")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Employee createEmployee(Employee employee) {
        int empId = employeeService.createEmployee(employee);
        employee.setId(empId);
        return employee;
    }
}
