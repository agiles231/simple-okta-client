package com.agiles231.okta.app;

import java.util.List;
import java.util.Map;

public class App {

    private final String id;
    private final String name;
    private final String label;
    private final String status;
    private final String lastUpdated;
    private final String created;
    private final AppAccessibility accessibility;
    private final AppVisibility visibility;
    private final List<String> features;
    private final String signOnMode;
    private final AppCredentials credentials;
    private final Map<String, Object> settings;
    private final Map<String, Object> _embedded;
    
    public App(String id, String name, String label, String status, String lastUpdated, String created,
            AppAccessibility accessibility, AppVisibility visibility, List<String> features, String signOnMode,
            AppCredentials credentials, Map<String, Object> settings, Map<String, Object> _embedded) {
        super();
        this.id = id;
        this.name = name;
        this.label = label;
        this.status = status;
        this.lastUpdated = lastUpdated;
        this.created = created;
        this.accessibility = accessibility;
        this.visibility = visibility;
        this.features = features;
        this.signOnMode = signOnMode;
        this.credentials = credentials;
        this.settings = settings;
        this._embedded = _embedded;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }

    public String getStatus() {
        return status;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public String getCreated() {
        return created;
    }

    public AppAccessibility getAccessibility() {
        return accessibility;
    }

    public AppVisibility getVisibility() {
        return visibility;
    }

    public List<String> getFeatures() {
        return features;
    }

    public String getSignOnMode() {
        return signOnMode;
    }

    public AppCredentials getCredentials() {
        return credentials;
    }

    public Map<String, Object> getSettings() {
        return settings;
    }

    public Map<String, Object> get_embedded() {
        return _embedded;
    }
    
    
    
    
}
