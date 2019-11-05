package com.agiles231.okta.http;

import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;

import com.agiles231.okta.http.exception.HttpEntityCreationFailedException;
import com.google.gson.Gson;

public class HttpStringEntityCreator implements HttpEntityCreator {

    private final Gson gson;
    public HttpStringEntityCreator(Gson gson) {
        this.gson = gson;
    }

    @Override
    public HttpEntity createEntity(Object obj) throws HttpEntityCreationFailedException {
        try {
            return new StringEntity(this.gson.toJson(obj));
        } catch (UnsupportedEncodingException e) {
            throw new HttpEntityCreationFailedException(e);
        }
    }

}
