package com.agiles231.okta.user;

import java.util.HashMap;
import java.util.Map;

public class User {

    private final String id;
    private String status;
    private String statusChanged;
    private String activated;
    private String created;
    private String lastLogin;
    private String lastUpdated;
    private String passwordChanged;
    
    private UserCredentials credentials;
    private Map<String, Object> profile;
    
    public User(String id, String status, String statusChanged, String activated, String created, String lastLogin,
            String lastUpdated, String passwordChanged, UserCredentials credentials, Map<String, Object> profile) {
        super();
        this.id = id;
        this.status = status;
        this.statusChanged = statusChanged;
        this.activated = activated;
        this.created = created;
        this.lastLogin = lastLogin;
        this.lastUpdated = lastUpdated;
        this.passwordChanged = passwordChanged;
        this.credentials = credentials;
        this.profile = profile;
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getStatusChanged() {
        return statusChanged;
    }

    public String getActivated() {
        return activated;
    }

    public String getCreated() {
        return created;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public String getPasswordChanged() {
        return passwordChanged;
    }

    public UserCredentials getCredentials() {
        return credentials;
    }

    public Map<String, Object> getProfile() {
        return new HashMap<>(profile);
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", status=" + status + ", statusChanged=" + statusChanged + ", activated=" + activated
                + ", created=" + created + ", lastLogin=" + lastLogin + ", lastUpdated=" + lastUpdated
                + ", passwordChanged=" + passwordChanged + ", credentials=" + credentials + ", profile=" + profile
                + "]";
    }
    
}
