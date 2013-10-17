package com.ntrphanikumar.emanager.restclient;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHeader;

import com.ntrphanikumar.emanager.dtos.ErrorDTO;

public class RestClient {

	private static final String XML = "application/xml";
	private static final Header ACCEPT_HEADER = new BasicHeader("Accept", XML);
	private static final Header CONTENT_TYPE_HEADER = new BasicHeader(
			"Content-Type", XML);

	private final HttpClient httpClient;
	private final String apiBaseUrl;
	private final Marshaller marshaller;
	private final Unmarshaller unmarshaller;

	public RestClient(HttpClient httpClient, String apiBaseUrl,
			JAXBContext jaxbContext) throws JAXBException {
		this.httpClient = httpClient;
		this.apiBaseUrl = apiBaseUrl;
		this.marshaller = jaxbContext.createMarshaller();
		this.unmarshaller = jaxbContext.createUnmarshaller();
	}

	public <T> T doGet(String url) {
		try {
			HttpResponse response = httpClient.execute(xmlHeaders(new HttpGet(
					apiBaseUrl + url)));
			return buildFromResponse(response);
		} catch (IOException e) {
			throw new RestClientException(e.getMessage());
		}
	}

	private HttpUriRequest xmlHeaders(HttpUriRequest request) {
		request.setHeader(ACCEPT_HEADER);
		request.setHeader(CONTENT_TYPE_HEADER);
		return request;
	}

	public <T> T doPost(String url, Object object) {
		try {
			HttpPost httpPost = new HttpPost(apiBaseUrl + url);
			xmlHeaders(httpPost);
			StringWriter writer = new StringWriter();
			marshaller.marshal(object, writer);
			httpPost.setEntity(new ByteArrayEntity(writer.getBuffer()
					.toString().getBytes(), ContentType.APPLICATION_XML));
			return buildFromResponse(httpClient.execute(httpPost));
		} catch (JAXBException | IOException e) {
			throw new RestClientException(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	private <T> T buildFromResponse(HttpResponse response) {
		Object obj = null;
		try {
			if (response.getEntity() == null) {
				return null;
			}
			obj = unmarshaller.unmarshal(response.getEntity().getContent());
		} catch (JAXBException | IOException e) {
			throw new RestClientException(e.getMessage());
		}
		if (obj instanceof ErrorDTO) {
			throw new RestClientException((ErrorDTO) obj);
		}
		return (T) obj;

	}

	public <T> T doPut(String url, Object object) {
		try {
			HttpPut httpPut = new HttpPut(apiBaseUrl + url);
			xmlHeaders(httpPut);
			StringWriter writer = new StringWriter();
			marshaller.marshal(object, writer);
			httpPut.setEntity(new ByteArrayEntity(writer.getBuffer().toString()
					.getBytes(), ContentType.APPLICATION_XML));
			return buildFromResponse(httpClient.execute(httpPut));
		} catch (JAXBException | IOException e) {
			throw new RestClientException(e.getMessage());
		}
	}

	public void doDelete(String url) {
		try {
			buildFromResponse(httpClient.execute(xmlHeaders(new HttpDelete(
					apiBaseUrl + url))));
		} catch (IOException e) {
			throw new RestClientException(e.getMessage());
		}
	}
}
