package com.agiles231.okta.user;

public class UserCredentials {
    
    private final CredentialsPassword password;
    private final RecoveryQuestion recoveryQuestion;
    private final CredentialsProvider provider;
    
    public UserCredentials(CredentialsPassword password, RecoveryQuestion recoveryQuestion,
            CredentialsProvider provider) {
        super();
        this.password = password;
        this.recoveryQuestion = recoveryQuestion;
        this.provider = provider;
    }

    public CredentialsPassword getPassword() {
        return password;
    }

    public RecoveryQuestion getRecoveryQuestion() {
        return recoveryQuestion;
    }

    public CredentialsProvider getProvider() {
        return provider;
    }
}
