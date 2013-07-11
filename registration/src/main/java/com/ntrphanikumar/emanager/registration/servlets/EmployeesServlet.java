package com.ntrphanikumar.emanager.registration.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ntrphanikumar.emanager.dtos.Employee;
import com.ntrphanikumar.emanager.registration.services.EmployeeService;

@SuppressWarnings("deprecation")
public class EmployeesServlet extends HttpServlet {

    private static final long serialVersionUID = 1138009549980101444L;

    private EmployeeService employeeService;

    @Override
    public void init() throws ServletException {
        super.init();
        employeeService = WebApplicationContextUtils.getRequiredWebApplicationContext(
                getServletContext()).getBean(EmployeeService.class);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Employee employee = new Employee();
        employee.setName(req.getParameter("name"));
        employee.setEmail(req.getParameter("email"));
        employee.setDob(new Date(req.getParameter("dob")));
        employee.setExtn(req.getParameter("extn"));
        employeeService.createEmployee(employee);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        Employee employee = employeeService
                .getEmployeeById(Integer.parseInt(req.getParameter("id")));
        employee.setName(req.getParameter("name"));
        employee.setEmail(req.getParameter("email"));
        employee.setDob(new Date(req.getParameter("dob")));
        employee.setExtn(req.getParameter("extn"));
        employeeService.updateEmployee(employee.getId(), employee);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        employeeService.deleteEmployee(Integer.parseInt(req.getParameter("id")));
    }

}
