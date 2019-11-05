package com.agiles231.okta.app.auth;

public class SigningCredential {

    private final String kid;

    public SigningCredential(String kid) {
        super();
        this.kid = kid;
    }

    public String getKid() {
        return kid;
    }
}
