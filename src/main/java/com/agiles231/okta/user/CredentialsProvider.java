package com.agiles231.okta.user;

public class CredentialsProvider {

    private final String type;
    private final String name;

    public CredentialsProvider() {
        this.type = null;
        this.name = null;
    }
    public CredentialsProvider(String type, String name) {
        super();
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }
    
}
