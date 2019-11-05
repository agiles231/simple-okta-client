package com.agiles231.okta.http.uri;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;

public class InitializedUriBuilder {

    private final URIBuilder uriBuilder;
    private final String basePath;

    public InitializedUriBuilder(URIBuilder uriBuilder, String basePath) {
        super();
        this.uriBuilder = uriBuilder;
        this.basePath = basePath;
    }

    public URIBuilder addParameter(String param, String value) {
        return uriBuilder.addParameter(param, value);
    }

    public URIBuilder addParameters(List<NameValuePair> nvps) {
        return uriBuilder.addParameters(nvps);
    }

    public URI build() throws URISyntaxException {
        return uriBuilder.build();
    }

    public URIBuilder clearParameters() {
        return uriBuilder.clearParameters();
    }

    public URIBuilder removeQuery() {
        return uriBuilder.removeQuery();
    }

    public URIBuilder setParameter(String arg0, String arg1) {
        return uriBuilder.setParameter(arg0, arg1);
    }

    public URIBuilder setParameters(List<NameValuePair> nvps) {
        return uriBuilder.setParameters(nvps);
    }

    public URIBuilder setParameters(NameValuePair... arg0) {
        return uriBuilder.setParameters(arg0);
    }

    public URIBuilder setPath(String path) {
        return uriBuilder.setPath(basePath + path);
    }

}
