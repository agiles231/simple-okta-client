package com.agiles231.okta.app;

import com.agiles231.okta.user.CredentialsPassword;

public class AppUserCredentials {

    private final String userName;
    private final CredentialsPassword password;

    public AppUserCredentials(String userName, CredentialsPassword password) {
        super();
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public CredentialsPassword getPassword() {
        return password;
    }

}
