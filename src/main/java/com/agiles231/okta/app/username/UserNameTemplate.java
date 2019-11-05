package com.agiles231.okta.app.username;

public class UserNameTemplate {

    private final String template;
    private final String type;
    private final String userSuffix;
    
    public UserNameTemplate(String template, String type, String userSuffix) {
        super();
        this.template = template;
        this.type = type;
        this.userSuffix = userSuffix;
    }

    public String getTemplate() {
        return template;
    }

    public String getType() {
        return type;
    }

    public String getUserSuffix() {
        return userSuffix;
    }
    
}
