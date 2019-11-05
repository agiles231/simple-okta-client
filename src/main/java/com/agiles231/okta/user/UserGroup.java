package com.agiles231.okta.user;

import java.util.Map;

public class UserGroup {

    private final String id;
    Map<String, Object> profile;
    
    public UserGroup(String id, Map<String, Object> profile) {
        super();
        this.id = id;
        this.profile = profile;
    }

    public String getId() {
        return id;
    }

    public Map<String, Object> getProfile() {
        return profile;
    }
}
