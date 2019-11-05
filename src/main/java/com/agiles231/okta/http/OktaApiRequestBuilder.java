package com.agiles231.okta.http;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;

import com.agiles231.okta.ApiVersion;
import com.agiles231.okta.http.uri.InitializedUriBuilder;

public class OktaApiRequestBuilder {

    private final InitializedUriBuilder uriBuilder;
    private final String uriBasePath;

    public OktaApiRequestBuilder(InitializedUriBuilder uriBuilder, ApiVersion apiVersion) {
        this.uriBuilder = uriBuilder;
        this.uriBasePath = "/api/" + apiVersion.toString();
    }
    
	public HttpGet getHttpGet(URI uri) throws URISyntaxException {
		return new HttpGet(uri);
	}
	
	public HttpPut getHttpPut(URI uri) throws URISyntaxException {
		return new HttpPut(uri);
	}
	
	public HttpPost getHttpPost(URI uri) throws URISyntaxException {
		return new HttpPost(uri);
	}
	
	public HttpDelete getHttpDelete(URI uri) throws URISyntaxException {
		return new HttpDelete(uri);
	}

	public HttpGet getHttpGet(String path) throws URISyntaxException {
		URI uri = uriBuilder.setPath(uriBasePath + path).build();
		return new HttpGet(uri);
	}

	public HttpGet getHttpGet(String path, List<NameValuePair> params) throws URISyntaxException {
	    uriBuilder.addParameters(params);
		URI uri = uriBuilder.setPath(uriBasePath + path).build();
		uriBuilder.clearParameters();
		return new HttpGet(uri);
	}
	
	public HttpPut getHttpPut(String path) throws URISyntaxException {
		URI uri = uriBuilder.setPath(uriBasePath + path).build();
		uriBuilder.clearParameters();
		return new HttpPut(uri);
	}

	public HttpPut getHttpPut(String path, List<NameValuePair> params) throws URISyntaxException {
	    uriBuilder.addParameters(params);
		URI uri = uriBuilder.setPath(uriBasePath + path).build();
		uriBuilder.clearParameters();
		return new HttpPut(uri);
	}
	
	public HttpPost getHttpPost(String path) throws URISyntaxException {
		URI uri = uriBuilder.setPath(uriBasePath + path).build();
		uriBuilder.clearParameters();
		return new HttpPost(uri);
	}

	public HttpPost getHttpPost(String path, List<NameValuePair> params) throws URISyntaxException {
	    uriBuilder.addParameters(params);
		URI uri = uriBuilder.setPath(uriBasePath + path).build();
		uriBuilder.clearParameters();
		return new HttpPost(uri);
	}
	
	public HttpDelete getHttpDelete(String path) throws URISyntaxException {
		URI uri = uriBuilder.setPath(uriBasePath + path).build();
		uriBuilder.clearParameters();
		return new HttpDelete(uri);
	}

	public HttpDelete getHttpDelete(String path, List<NameValuePair> params) throws URISyntaxException {
	    uriBuilder.addParameters(params);
		URI uri = uriBuilder.setPath(uriBasePath + path).build();
		uriBuilder.clearParameters();
		return new HttpDelete(uri);
	}
	
}
