package com.agiles231.okta.auth;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.agiles231.okta.http.HttpEntityCreator;
import com.agiles231.okta.http.HttpJsonObjectExtractor;
import com.agiles231.okta.http.OktaHttpClient;
import com.agiles231.okta.json.JsonCollectionExtractor;

public class AuthApiHttpClient implements AuthApi {

    private static final Logger logger = LogManager.getLogger(AuthApiHttpClient.class);

    private static final String AUTHN_PATH = "/authn";

    private final OktaHttpClient client;
    private final HttpEntityCreator entityCreator;
    private final JsonCollectionExtractor collectionExtractor;
    private final HttpJsonObjectExtractor jsonExtractor;

    public AuthApiHttpClient(OktaHttpClient client, HttpEntityCreator entityCreator,
            HttpJsonObjectExtractor jsonExtractor, JsonCollectionExtractor collectionExtractor) {
        this.client = client;
        this.entityCreator = entityCreator;
        this.jsonExtractor = jsonExtractor;
        this.collectionExtractor = collectionExtractor;
    }

    @Override
    public Map basicUsernamePasswordAuthn(String username, String password, String relayState, AuthOptions options)
            throws AuthenticationOperationFailedException {
        try {
            HttpPost post = client.getRequestBuilder().getHttpPost(AUTHN_PATH);
            HashMap<String, Object> body = new HashMap<String, Object>();
            body.put("username", username);
            body.put("password", password);
            body.put("options", options);

            post.setEntity(entityCreator.createEntity(body));
            HttpResponse response = client.execute(post);
            return jsonExtractor.getObjectFromResponse(response, Map.class);
        } catch (Exception e) {
            throw new AuthenticationOperationFailedException(e);
        }
    }

}
