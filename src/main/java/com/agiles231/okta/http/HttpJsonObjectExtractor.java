package com.agiles231.okta.http;

import java.io.IOException;

import org.apache.http.HttpResponse;

public interface HttpJsonObjectExtractor {

    public <T> T getObjectFromResponse(HttpResponse response, Class<T> clazz) throws IOException;
}
