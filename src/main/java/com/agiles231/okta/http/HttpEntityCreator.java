package com.agiles231.okta.http;

import org.apache.http.HttpEntity;

import com.agiles231.okta.http.exception.HttpEntityCreationFailedException;

public interface HttpEntityCreator {

    public HttpEntity createEntity(Object obj) throws HttpEntityCreationFailedException;
}
