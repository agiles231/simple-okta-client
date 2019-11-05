package com.agiles231.okta.auth;

import java.util.Map;

public interface AuthApi {

    public Map basicUsernamePasswordAuthn(String username, String password, String relayState, AuthOptions options)
            throws AuthenticationOperationFailedException;
}
