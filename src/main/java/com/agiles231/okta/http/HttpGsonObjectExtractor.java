package com.agiles231.okta.http;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

public class HttpGsonObjectExtractor implements HttpJsonObjectExtractor {

    private static final Logger logger = LogManager.getLogger(HttpGsonObjectExtractor.class);

    private final Gson gson;

    public HttpGsonObjectExtractor(Gson gson) {
        this.gson = gson;
    }
    @Override
    public <T> T getObjectFromResponse(HttpResponse response, Class<T> clazz) throws IOException {
        String contents = HttpResponseContentExtractor.getContentFromResponse(response);
        logger.debug(contents);
        return gson.fromJson(contents, clazz);
    }

}
