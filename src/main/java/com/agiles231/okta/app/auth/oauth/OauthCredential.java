package com.agiles231.okta.app.auth.oauth;

public class OauthCredential {

    private final String client_id;
    private final String client_secret;
    private final String token_endpoint_auth_method;
    private final Boolean autoKeyRotation;

    public OauthCredential(String client_id, String client_secret, String token_endpoint_auth_method,
            Boolean autoKeyRotation) {
        super();
        this.client_id = client_id;
        this.client_secret = client_secret;
        this.token_endpoint_auth_method = token_endpoint_auth_method;
        this.autoKeyRotation = autoKeyRotation;
    }

    public String getClient_id() {
        return client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public String getToken_endpoint_auth_method() {
        return token_endpoint_auth_method;
    }

    public Boolean getAutoKeyRotation() {
        return autoKeyRotation;
    }

}
