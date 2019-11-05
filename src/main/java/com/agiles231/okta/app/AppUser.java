package com.agiles231.okta.app;

import java.util.HashMap;
import java.util.Map;

public class AppUser {

    private final String id;
    private final String externalId;
    private final String created;
    private final String lastUpdated;
    private final String scope;
    private final String status;
    private final String statusChanged;
    private final String passwordChanged;
    private final String syncState;
    private final String lastSync;
    private final AppUserCredentials credentials;
    private final Map<String, Object> profile;

    public AppUser(String id, String externalId, String created, String lastUpdated, String scope, String status,
            String statusChanged, String passwordChanged, String syncState, String lastSync,
            AppUserCredentials credentials, Map<String, Object> profile) {
        super();
        this.id = id;
        this.externalId = externalId;
        this.created = created;
        this.lastUpdated = lastUpdated;
        this.scope = scope;
        this.status = status;
        this.statusChanged = statusChanged;
        this.passwordChanged = passwordChanged;
        this.syncState = syncState;
        this.lastSync = lastSync;
        this.credentials = credentials;
        this.profile = profile;
    }

    public String getId() {
        return id;
    }

    public String getExternalId() {
        return externalId;
    }

    public String getCreated() {
        return created;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public String getScope() {
        return scope;
    }

    public String getStatus() {
        return status;
    }

    public String getStatusChanged() {
        return statusChanged;
    }

    public String getPasswordChanged() {
        return passwordChanged;
    }

    public String getSyncState() {
        return syncState;
    }

    public String getLastSync() {
        return lastSync;
    }

    public AppUserCredentials getCredentials() {
        return credentials;
    }

    public Map<String, Object> getProfile() {
        return new HashMap<>(profile);
    }

}
