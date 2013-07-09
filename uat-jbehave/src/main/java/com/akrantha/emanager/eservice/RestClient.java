package com.akrantha.emanager.eservice;

import java.io.IOException;

import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicHeader;

public class RestClient {

    private static final String XML = "application/xml";
    private static final Header ACCEPT_HEADER = new BasicHeader("Accept", XML);
    private static final Header CONTENT_TYPE_HEADER = new BasicHeader("Content-Type", XML);

    private final HttpClient httpClient;
    private final String apiBaseUrl;
    private final Marshaller marshaller;
    private final Unmarshaller unmarshaller;

    public RestClient(HttpClient httpClient, String apiBaseUrl, Marshaller marshaller,
            Unmarshaller unmarshaller) {
        this.httpClient = httpClient;
        this.apiBaseUrl = apiBaseUrl;
        this.marshaller = marshaller;
        this.unmarshaller = unmarshaller;
    }

    public HttpResponse doGet(String url) throws ClientProtocolException, IOException {
        return httpClient.execute(xmlHeaders(new HttpGet(apiBaseUrl + url)));
    }

    private HttpUriRequest xmlHeaders(HttpUriRequest request) {
        request.setHeader(ACCEPT_HEADER);
        request.setHeader(CONTENT_TYPE_HEADER);
        return request;
    }
}
