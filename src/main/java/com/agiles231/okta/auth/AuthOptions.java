package com.agiles231.okta.auth;

public class AuthOptions {
    
    private final Boolean multiOptionalFactorEnroll;
    private final Boolean warnBeforePasswordExpired;
 
    public AuthOptions(Boolean multiOptionalFactorEnroll, Boolean warnBeforePasswordExpired) {
        super();
        this.multiOptionalFactorEnroll = multiOptionalFactorEnroll;
        this.warnBeforePasswordExpired = warnBeforePasswordExpired;
    }

    public Boolean getMultiOptionalFactorEnroll() {
        return multiOptionalFactorEnroll;
    }

    public Boolean getWarnBeforePasswordExpired() {
        return warnBeforePasswordExpired;
    }

    

}
