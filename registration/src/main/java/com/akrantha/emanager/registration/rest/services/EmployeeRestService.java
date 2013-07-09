package com.akrantha.emanager.registration.rest.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.akrantha.emanager.dtos.Employee;
import com.akrantha.emanager.dtos.EmployeeCollection;
import com.akrantha.emanager.registration.services.EmployeeService;

@Path("/employees")
public class EmployeeRestService {

    private final EmployeeService employeeService;

    public EmployeeRestService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Employee createEmployee(Employee employee) {
        int empId = employeeService.createEmployee(employee);
        employee.setId(empId);
        return employee;
    }

    @DELETE
    @Path("/{employeeId}")
    public void deleteEmployee(@PathParam("employeeId") int employeeId) {
        employeeService.deleteEmployee(employeeId);
    }

    @GET
    @Path("/{employeeId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Employee getEmployee(@PathParam("employeeId") int employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public EmployeeCollection getEmployees() {
        EmployeeCollection employeeCollection = new EmployeeCollection();
        employeeCollection.setEmployees(employeeService.getEmployees());
        return employeeCollection;
    }

    @PUT
    @Path("/{employeeId}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Employee updateEmployee(@PathParam("employeeId") int employeeId, Employee employee) {
        employeeService.updateEmployee(employeeId, employee);
        return employeeService.getEmployeeById(employeeId);
    }
}
