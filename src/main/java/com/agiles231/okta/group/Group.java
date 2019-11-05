package com.agiles231.okta.group;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Group {

    private final String id;
    private String created;
    private String lastUpdated;
    private String lastMembershipUpdated;
    private List<String> objectClass;
    private String type;
    private Map<String, Object> profile;
    
    public Group(String id, String created, String lastUpdated, String lastMembershipUpdated, List<String> objectClass,
            String type, Map<String, Object> profile) {
        super();
        this.id = id;
        this.created = created;
        this.lastUpdated = lastUpdated;
        this.lastMembershipUpdated = lastMembershipUpdated;
        this.objectClass = objectClass;
        this.type = type;
        this.profile = profile;
    }

    public String getId() {
        return id;
    }

    public String getCreated() {
        return created;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public String getLastMembershipUpdated() {
        return lastMembershipUpdated;
    }

    public List<String> getObjectClass() {
        return objectClass;
    }

    public String getType() {
        return type;
    }

    public Map<String, Object> getProfile() {
        return new HashMap<>(profile);
    }
    
}
