package com.agiles231.okta.app;

public class AppGroup {
    private final String id;
    private final String lastUpdated;
    private final Integer priority;

    public AppGroup(String id, String lastUpdated, Integer priority) {
        super();
        this.id = id;
        this.lastUpdated = lastUpdated;
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public Integer getPriority() {
        return priority;
    }

}
