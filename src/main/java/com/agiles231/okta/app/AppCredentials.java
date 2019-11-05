package com.agiles231.okta.app;

import com.agiles231.okta.app.auth.SigningCredential;
import com.agiles231.okta.app.auth.oauth.OauthCredential;
import com.agiles231.okta.app.username.UserNameTemplate;
import com.agiles231.okta.user.CredentialsPassword;

public class AppCredentials {
    String scheme;
    UserNameTemplate userNameTemplate;
    SigningCredential signing;
    String userName;
    CredentialsPassword password;
    OauthCredential oauthClient;

    public AppCredentials(String scheme, UserNameTemplate userNameTemplate, SigningCredential signing,
            String userName, CredentialsPassword password, OauthCredential oauthClient) {
        super();
        this.scheme = scheme;
        this.userNameTemplate = userNameTemplate;
        this.signing = signing;
        this.userName = userName;
        this.password = password;
        this.oauthClient = oauthClient;
    }

    public String getScheme() {
        return scheme;
    }

    public UserNameTemplate getUserNameTemplate() {
        return userNameTemplate;
    }

    public SigningCredential getSigning() {
        return signing;
    }

    public String getUserName() {
        return userName;
    }

    public CredentialsPassword getPassword() {
        return password;
    }

    public OauthCredential getOauthClient() {
        return oauthClient;
    }

}
