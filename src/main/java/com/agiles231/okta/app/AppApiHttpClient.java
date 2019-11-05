package com.agiles231.okta.app;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.agiles231.okta.app.exception.AppOperationFailedException;
import com.agiles231.okta.http.HttpEntityCreator;
import com.agiles231.okta.http.HttpJsonObjectExtractor;
import com.agiles231.okta.http.HttpResponseContentExtractor;
import com.agiles231.okta.http.OktaHttpClient;
import com.agiles231.okta.http.OktaHttpPageIterator;
import com.agiles231.okta.http.exception.HttpEntityCreationFailedException;
import com.agiles231.okta.json.JsonCollectionExtractor;

public class AppApiHttpClient implements AppApi {
    
    private static final Logger logger = LogManager.getLogger(AppApiHttpClient.class);
    
    private static final String ADD_APP_PATH = "/apps";
    private static final String GET_APP_PATH = "/apps/{id}";
    private static final String GET_APPS_PATH = "/apps";
    private static final String UPDATE_APP_PATH = "/apps/{id}";
    private static final String ACTIVATE_APP_PATH = "/apps/{id}/lifecycle/activate";
    private static final String DEACTIVATE_APP_PATH = "/apps/{id}/lifecycle/deactivate";
    private static final String ASSIGN_USER_TO_APP_PATH = "/apps/{id}/users";
    private static final String GET_APP_USER_PATH = "/apps/{appId}/users/{userId}";
    private static final String GET_APP_USERS_PATH = "/apps/{id}/users";
    private static final String UPDATE_APP_USER_PATH = "/apps/{appId}/users/{userId}";
    private static final String ASSIGN_GROUP_TO_APP_PATH = "/apps/{appId}/groups/{groupId}";
    private static final String GET_APP_GROUP_PATH = "/apps/{appId}/groups/{groupId}";
    private static final String GET_APP_GROUPS_PATH = "/apps/{id}/groups";

    private final OktaHttpClient client;
    private final HttpEntityCreator entityCreator;
    private final HttpJsonObjectExtractor jsonExtractor;
    private final JsonCollectionExtractor collectionExtractor;

    public AppApiHttpClient(OktaHttpClient client, HttpEntityCreator entityCreator,
            HttpJsonObjectExtractor jsonExtractor, JsonCollectionExtractor collectionExtractor) {
        this.client = client;
        this.entityCreator = entityCreator;
        this.jsonExtractor = jsonExtractor;
        this.collectionExtractor = collectionExtractor;
    }

    @Override
    public App addApp(Boolean activate, String name, String signOnMode, Map<String, Object> settings)
            throws AppOperationFailedException {
        List<NameValuePair> params = new LinkedList<>();
        if (activate != null) {
            params.add(new BasicNameValuePair("activate", activate.toString()));
        }
        try {
            HttpResponse response = client.executeHttpPost(ADD_APP_PATH, params);
            return jsonExtractor.getObjectFromResponse(response, App.class);
        } catch (URISyntaxException | IOException e) {
            throw new AppOperationFailedException(e);
        }
    }

    @Override
    public App getApp(String id) throws AppOperationFailedException {
        try {
            HttpResponse response = client.executeHttpGet(GET_APP_PATH.replace("{id}", id));
            return jsonExtractor.getObjectFromResponse(response, App.class);
        } catch (IOException | URISyntaxException e) {
            throw new AppOperationFailedException(e);
        }
    }

    @Override
    public Collection<App> getApps(Integer limit, String expand, String after) throws AppOperationFailedException {
        try {
            List<NameValuePair> params = new LinkedList<>();
            if(limit != null) {
                params.add(new BasicNameValuePair("limit", limit.toString()));
            }
            if(after != null) {
                params.add(new BasicNameValuePair("after", after));
            }
            if(expand != null) {
                params.add(new BasicNameValuePair("expand", expand));
            }
            return collectionExtractor.extractCollection(HttpResponseContentExtractor.getContentFromResponse(client.executeHttpGet(GET_APPS_PATH)), App.class);
        } catch (IOException | URISyntaxException e) {
            throw new AppOperationFailedException(e);
        }
    }

    @Override
    public Collection<App> simpleSearchApps(String q, Integer limit, String expand) throws AppOperationFailedException {
        try {
            List<NameValuePair> params = new LinkedList<>();
            params.add(new BasicNameValuePair("q", q));
            if(limit != null) {
                params.add(new BasicNameValuePair("limit", limit.toString()));
            }
            if(expand != null) {
                params.add(new BasicNameValuePair("expand", expand));
            }
            return OktaHttpPageIterator.iteratePages(client, GET_APPS_PATH, collectionExtractor, App.class, params);
        } catch (IOException | URISyntaxException e) {
            throw new AppOperationFailedException(e);
        }
    }

    @Override
    public Collection<App> simpleSearchApps(String q, Integer limit, String expand, String after)
            throws AppOperationFailedException {
        try {
            List<NameValuePair> params = new LinkedList<>();
            params.add(new BasicNameValuePair("q", q));
            if(limit != null) {
                params.add(new BasicNameValuePair("limit", limit.toString()));
            }
            if(expand != null) {
                params.add(new BasicNameValuePair("expand", expand));
            }
            return collectionExtractor.extractCollection(HttpResponseContentExtractor.getContentFromResponse(client.executeHttpGet(GET_APPS_PATH)), App.class);
        } catch (IOException | URISyntaxException e) {
            throw new AppOperationFailedException(e);
        }
    }

    @Override
    public Collection<App> filterApps(Integer limit, String filter, String expand)
            throws AppOperationFailedException {
        try {
            List<NameValuePair> params = new LinkedList<>();
            if(limit != null) {
                params.add(new BasicNameValuePair("limit", limit.toString()));
            }
            params.add(new BasicNameValuePair("filter", filter));
            if(expand != null) {
                params.add(new BasicNameValuePair("expand", expand));
            }
            return OktaHttpPageIterator.iteratePages(client, GET_APPS_PATH, collectionExtractor, App.class, params);
        } catch (IOException | URISyntaxException e) {
            throw new AppOperationFailedException(e);
        }
    }

    @Override
    public Collection<App> filterApps(Integer limit, String filter, String expand, String after)
            throws AppOperationFailedException {
        try {
            List<NameValuePair> params = new LinkedList<>();
            if(limit != null) {
                params.add(new BasicNameValuePair("limit", limit.toString()));
            }
            params.add(new BasicNameValuePair("filter", filter));
            if(after != null) {
                params.add(new BasicNameValuePair("after", after));
            }
            if(expand != null) {
                params.add(new BasicNameValuePair("expand", expand));
            }
            return collectionExtractor.extractCollection(HttpResponseContentExtractor.getContentFromResponse(client.executeHttpGet(GET_APPS_PATH)), App.class);
        } catch (IOException | URISyntaxException e) {
            throw new AppOperationFailedException(e);
        }
    }

    @Override
    public Collection<App> getAllApps(Integer limit, String expand) throws AppOperationFailedException {
        try {
            List<NameValuePair> params = new LinkedList<>();
            if(limit != null) {
                params.add(new BasicNameValuePair("limit", limit.toString()));
            }
            if(expand != null) {
                params.add(new BasicNameValuePair("expand", expand));
            }
            return OktaHttpPageIterator.iteratePages(client, GET_APPS_PATH, collectionExtractor, App.class, params);
        } catch (IOException | URISyntaxException e) {
            throw new AppOperationFailedException(e);
        }
    }

    @Override
    public App updateApp(String id, App app) throws AppOperationFailedException {
        try {
            HttpResponse response = client.executeHttpPut(UPDATE_APP_PATH.replace("{id}", id), entityCreator.createEntity(app));
            return jsonExtractor.getObjectFromResponse(response, App.class);
        } catch (IOException | URISyntaxException | HttpEntityCreationFailedException e) {
            throw new AppOperationFailedException(e);
        }
    }

    @Override
    public void deleteApp(String id) throws AppOperationFailedException {
        throw new UnsupportedOperationException("This method is dangerous to implement");
    }

    @Override
    public void activateApp(String id) throws AppOperationFailedException {
        try {
            client.executeHttpPost(ACTIVATE_APP_PATH.replace("{id}", id));
        } catch (URISyntaxException | IOException e) {
            throw new AppOperationFailedException(e);
        }
    }

    @Override
    public void deactivateApp(String id) throws AppOperationFailedException {
        try {
            client.executeHttpPost(DEACTIVATE_APP_PATH.replace("{id}", id));
        } catch (URISyntaxException | IOException e) {
            throw new AppOperationFailedException(e);
        }
    }

    @Override
    public AppUser assignUserToApp(String appId, String userId, String scope, AppUserCredentials credentials,
            Map<String, Object> profile) throws AppOperationFailedException {
        Map<String, Object> body = new HashMap<>();
        if (appId == null) {
            throw new IllegalArgumentException("appId cannot be null");
        }
        if (userId != null) {
            body.put("id", userId);
        } else {
            throw new IllegalArgumentException("userId cannot be null");
        }
        if (scope != null) {
            body.put("scope", scope);
        }
        if (credentials != null) {
            body.put("credentials", credentials);
        }
        if (profile != null) {
            body.put("profile", profile);
        }
        try {
            HttpEntity entity = entityCreator.createEntity(body);
            HttpResponse response = client.executeHttpPost(ASSIGN_USER_TO_APP_PATH.replace("{id}", appId), entity);
            return jsonExtractor.getObjectFromResponse(response, AppUser.class);
        } catch (IOException | HttpEntityCreationFailedException | URISyntaxException e) {
            throw new AppOperationFailedException(e);
        }
        
    }

    @Override
    public AppUser getAppUser(String appId, String userId) throws AppOperationFailedException {
        try {
            return jsonExtractor.getObjectFromResponse(client.executeHttpGet(GET_APP_USER_PATH.replace("{appId}", appId).replace("{userId}", userId)), AppUser.class);
        } catch (IOException | URISyntaxException e) {
            throw new AppOperationFailedException(e);
        }
    }

    @Override
    public Collection<AppUser> getAllAppUsers(String appId, Integer limit)
            throws AppOperationFailedException {
        try {
            List<NameValuePair> params = new LinkedList<>();
            if (limit != null) {
                params.add(new BasicNameValuePair("limit", limit.toString()));
            }
            return OktaHttpPageIterator.iteratePages(client, GET_APP_USERS_PATH.replace("{id}", appId), collectionExtractor, AppUser.class, params);
        } catch (IOException | URISyntaxException e) {
            throw new AppOperationFailedException(e);
        }
    }

    @Override
    public Collection<AppUser> getAppUsers(String appId, Integer limit) throws AppOperationFailedException {
        try {
            List<NameValuePair> params = new LinkedList<>();
            if (limit != null) {
                params.add(new BasicNameValuePair("limit", limit.toString()));
            }
            return collectionExtractor.extractCollection(
                    HttpResponseContentExtractor.getContentFromResponse(client.executeHttpGet(GET_APP_USERS_PATH.replace("{id}", appId), params)),
                    AppUser.class);
        } catch (IOException | URISyntaxException e) {
            throw new AppOperationFailedException(e);
        }
    }

    @Override
    public Collection<AppUser> getAppUsers(String appId, Integer limit, String after)
            throws AppOperationFailedException {
        try {
            List<NameValuePair> params = new LinkedList<>();
            if (limit != null) {
                params.add(new BasicNameValuePair("limit", limit.toString()));
            }
            if (after != null) {
                params.add(new BasicNameValuePair("after", after.toString()));
            }
            return collectionExtractor.extractCollection(
                    HttpResponseContentExtractor.getContentFromResponse(client.executeHttpGet(GET_APP_USERS_PATH.replace("{id}", appId), params)),
                    AppUser.class);
        } catch (IOException | URISyntaxException e) {
            throw new AppOperationFailedException(e);
        }
    }

    @Override
    public Collection<AppUser> getAppUsers(String appId, String q, Integer limit) throws AppOperationFailedException {
        try {
            List<NameValuePair> params = new LinkedList<>();
            if (limit != null) {
                params.add(new BasicNameValuePair("limit", limit.toString()));
            }
            if (q != null) {
                params.add(new BasicNameValuePair("q", q));
            }
            return OktaHttpPageIterator.iteratePages(client, GET_APP_USERS_PATH.replace("{id}", appId), collectionExtractor, AppUser.class, params);
        } catch (IOException | URISyntaxException e) {
            throw new AppOperationFailedException(e);
        }
    }

    @Override
    public Collection<AppUser> getAppUsers(String appId, String q, Integer limit, String after)
            throws AppOperationFailedException {
        try {
            List<NameValuePair> params = new LinkedList<>();
            if (limit != null) {
                params.add(new BasicNameValuePair("limit", limit.toString()));
            }
            if (q != null) {
                params.add(new BasicNameValuePair("q", q));
            }
            if (after != null) {
                params.add(new BasicNameValuePair("after", after.toString()));
            }
            return collectionExtractor.extractCollection(
                    HttpResponseContentExtractor.getContentFromResponse(client.executeHttpGet(GET_APP_USERS_PATH.replace("{id}", appId), params)),
                    AppUser.class);
        } catch (IOException | URISyntaxException e) {
            throw new AppOperationFailedException(e);
        }
    }

    @Override
    public AppUser updateAppUser(String appId, String userId, AppUserCredentials credentials,
            Map<String, Object> profile) throws AppOperationFailedException {
        Map<String, Object> body = new HashMap<>();
        if (credentials != null) {
            body.put("credentials", credentials);
        }
        if (profile != null) {
            body.put("profile", profile);
        }
        try {
            HttpEntity entity = entityCreator.createEntity(body);
            HttpResponse response = client.executeHttpPost(UPDATE_APP_USER_PATH.replace("{appId}", appId).replace("{userId}", userId), entity);
            return jsonExtractor.getObjectFromResponse(response, AppUser.class);
        } catch (IOException | HttpEntityCreationFailedException | URISyntaxException e) {
            throw new AppOperationFailedException(e);
        }
    }

    @Override
    public AppUser removeAppUser(String appId, String userId) throws AppOperationFailedException {
        throw new UnsupportedOperationException("This method is dangerous to implement");
    }

    @Override
    public AppGroup assignGroupToApp(String appId, String groupId, AppGroup appGroup) throws AppOperationFailedException {
        Map<String, Object> body = new HashMap<>();
        if (appGroup != null) {
            body.put("appGroup", appGroup);
        }
        try {
            HttpEntity entity = entityCreator.createEntity(body);
            return jsonExtractor.getObjectFromResponse(client.executeHttpPut(ASSIGN_GROUP_TO_APP_PATH.replace("{appId}", appId).replace("{groupId}", groupId), entity), AppGroup.class);
        } catch (IOException | URISyntaxException | HttpEntityCreationFailedException e) {
            throw new AppOperationFailedException(e);
        }
    }

    @Override
    public AppGroup getAppGroup(String appId, String groupId) throws AppOperationFailedException {
        try {
            return jsonExtractor.getObjectFromResponse(client.executeHttpGet(GET_APP_GROUP_PATH.replace("{appId}", appId).replace("{groupId}", groupId)), AppGroup.class);
        } catch (IOException | URISyntaxException e) {
            throw new AppOperationFailedException(e);
        }
    }

    @Override
    public Collection<AppGroup> getAppGroups(String appId, Integer limit, String after)
            throws AppOperationFailedException {
        try {
            List<NameValuePair> params = new LinkedList<>();
            if (limit != null) {
                params.add(new BasicNameValuePair("limit", limit.toString()));
            }
            if (after != null) {
                params.add(new BasicNameValuePair("after", after));
            }
            return collectionExtractor.extractCollection(
                    HttpResponseContentExtractor.getContentFromResponse(client.executeHttpGet(GET_APP_GROUPS_PATH.replace("{id}", appId), params))
                    , AppGroup.class);
        } catch (URISyntaxException | IOException e) {
            throw new AppOperationFailedException(e);
        }
    }

    @Override
    public Collection<AppGroup> getAllAppGroups(String appId, Integer limit)
            throws AppOperationFailedException {
        try {
            return OktaHttpPageIterator.iteratePages(client, GET_APP_GROUPS_PATH.replace("{id}", appId), collectionExtractor, AppGroup.class);
        } catch (URISyntaxException | IOException e) {
            throw new AppOperationFailedException(e);
        }
    }

    @Override
    public void removeGroupFromApp(String appId, String groupId) throws AppOperationFailedException {
        throw new UnsupportedOperationException("This method is dangerous to implement");
    }



}
