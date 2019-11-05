package com.agiles231.okta.user;

public class CredentialsPassword {

    private final String value;
    private final HashedPassword hash;
    
    public CredentialsPassword(String value, HashedPassword hash) {
        super();
        this.value = value;
        this.hash = hash;
    }

    public String getValue() {
        return value;
    }

    public HashedPassword getHash() {
        return hash;
    }    
}
