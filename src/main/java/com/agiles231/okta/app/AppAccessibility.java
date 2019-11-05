package com.agiles231.okta.app;

public class AppAccessibility {
    private final Boolean selfService;
    private final String errorRedirectUrl;
    private final String loginRedirectUrl;

    public AppAccessibility(Boolean selfService, String errorRedirectUrl, String loginRedirectUrl) {
        super();
        this.selfService = selfService;
        this.errorRedirectUrl = errorRedirectUrl;
        this.loginRedirectUrl = loginRedirectUrl;
    }

    public Boolean getSelfService() {
        return selfService;
    }

    public String getErrorRedirectUrl() {
        return errorRedirectUrl;
    }

    public String getLoginRedirectUrl() {
        return loginRedirectUrl;
    }

}
