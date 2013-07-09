package com.akrantha.emanager.eservice;

import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.apache.http.HttpResponse;

import com.akrantha.emanager.dtos.EmployeeCollection;

public class EmployeeService {

    private final RestClient restClient;

    public EmployeeService(RestClient restClient) {
        this.restClient = restClient;
    }

    public void getEmployees() throws IOException, IllegalStateException, JAXBException {
        HttpResponse response = restClient.doGet("/employees");
        Object unmarshal = JAXBContext.newInstance(EmployeeCollection.class).createUnmarshaller()
                .unmarshal(response.getEntity().getContent());
        EmployeeCollection employeeCollection = (EmployeeCollection) unmarshal;
    }
}
