package com.agiles231.okta.group;

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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicNameValuePair;

import com.agiles231.okta.group.exception.GroupOperationFailedException;
import com.agiles231.okta.http.HttpEntityCreator;
import com.agiles231.okta.http.HttpJsonObjectExtractor;
import com.agiles231.okta.http.HttpResponseContentExtractor;
import com.agiles231.okta.http.OktaHttpClient;
import com.agiles231.okta.http.OktaHttpPageIterator;
import com.agiles231.okta.json.JsonCollectionExtractor;
import com.agiles231.okta.user.User;

public class GroupApiHttpClient implements GroupApi {

    private final static String ADD_GROUP_PATH = "/groups";
    private final static String GET_GROUP_PATH = "/groups/{id}";
    private final static String GET_GROUPS_PATH = "/groups";
    private final static String UPDATE_GROUP_PATH = "/groups/{id}";
    private final static String GET_GROUP_MEMBERS_PATH = "/groups/{id}";
    private final static String ADD_USER_TO_GROUP_PATH = "/groups/{id}";

    private final OktaHttpClient client;
    private final HttpEntityCreator entityCreator;
    private final HttpJsonObjectExtractor jsonExtractor;
    private final JsonCollectionExtractor collectionExtractor;

    public GroupApiHttpClient(OktaHttpClient client, HttpEntityCreator entityCreator,
            HttpJsonObjectExtractor jsonExtractor, JsonCollectionExtractor collectionExtractor) {
        this.client = client;
        this.entityCreator = entityCreator;
        this.jsonExtractor = jsonExtractor;
        this.collectionExtractor = collectionExtractor;
    }

    @Override
    public Group addGroup(Map<String, Object> profile) throws GroupOperationFailedException {
        try {
            Map<String, Object> body = new HashMap<>();
            body.put("profile", profile);
            HttpEntity entity = entityCreator.createEntity(body);
            HttpResponse response = client.executeHttpPost(ADD_GROUP_PATH, entity);
            return jsonExtractor.getObjectFromResponse(response, Group.class);
        } catch (Exception e) {
            throw new GroupOperationFailedException(e);
        }
    }

    @Override
    public Group getGroup(String id) throws GroupOperationFailedException {
        try {
            HttpResponse response = client.executeHttpGet(GET_GROUP_PATH.replace("{id}", id));
            return jsonExtractor.getObjectFromResponse(response, Group.class);
        } catch (Exception e) {
            throw new GroupOperationFailedException(e);
        }
    }

    @Override
    public Collection<Group> getGroups(Integer limit, String after) throws GroupOperationFailedException {
        List<NameValuePair> params = new LinkedList<>();
        if (limit != null) {
            params.add(new BasicNameValuePair("limit", limit.toString()));
        }
        if (after != null) {
            params.add(new BasicNameValuePair("after", after));
        }
        try {
            return collectionExtractor.extractCollection(
                    HttpResponseContentExtractor.getContentFromResponse(client.executeHttpGet(GET_GROUPS_PATH, params)),
                    Group.class);
        } catch (URISyntaxException | IOException e) {
            throw new GroupOperationFailedException(e);
        }
    }

    @Override
    public Collection<Group> simpleSearchGroups(String q, Integer limit) throws GroupOperationFailedException {
        List<NameValuePair> params = new LinkedList<>();
        if (q != null) {
            params.add(new BasicNameValuePair("q", q));
        }
        if (limit != null) {
            params.add(new BasicNameValuePair("limit", limit.toString()));
        }
        try {
            return OktaHttpPageIterator.iteratePages(client, GET_GROUPS_PATH, collectionExtractor, Group.class, params);
        } catch (URISyntaxException | IOException e) {
            throw new GroupOperationFailedException(e);
        }
    }

    @Override
    public Collection<Group> simpleSearchGroups(String q, Integer limit, String after)
            throws GroupOperationFailedException {
        List<NameValuePair> params = new LinkedList<>();
        if (q != null) {
            params.add(new BasicNameValuePair("q", q));
        }
        if (limit != null) {
            params.add(new BasicNameValuePair("limit", limit.toString()));
        }
        if (after != null) {
            params.add(new BasicNameValuePair("after", after));
        }
        try {
            return collectionExtractor.extractCollection(
                    HttpResponseContentExtractor.getContentFromResponse(client.executeHttpGet(GET_GROUPS_PATH, params)),
                    Group.class);
        } catch (URISyntaxException | IOException e) {
            throw new GroupOperationFailedException(e);
        }
    }

    @Override
    public Collection<Group> filterGroups(String filter, Integer limit) throws GroupOperationFailedException {
        List<NameValuePair> params = new LinkedList<>();
        if (filter != null) {
            params.add(new BasicNameValuePair("filter", filter));
        }
        if (limit != null) {
            params.add(new BasicNameValuePair("limit", limit.toString()));
        }
        try {
            return OktaHttpPageIterator.iteratePages(client, GET_GROUPS_PATH, collectionExtractor, Group.class, params);
        } catch (URISyntaxException | IOException e) {
            throw new GroupOperationFailedException(e);
        }
    }

    @Override
    public Collection<Group> filterGroups(String filter, Integer limit, String after)
            throws GroupOperationFailedException {
        List<NameValuePair> params = new LinkedList<>();
        if (filter != null) {
            params.add(new BasicNameValuePair("filter", filter));
        }
        if (limit != null) {
            params.add(new BasicNameValuePair("limit", limit.toString()));
        }
        if (after != null) {
            params.add(new BasicNameValuePair("after", after));
        }
        try {
            return collectionExtractor.extractCollection(
                    HttpResponseContentExtractor.getContentFromResponse(client.executeHttpGet(GET_GROUPS_PATH, params)),
                    Group.class);
        } catch (URISyntaxException | IOException e) {
            throw new GroupOperationFailedException(e);
        }
    }

    @Override
    public Collection<Group> getAllGroups(Integer limit) throws GroupOperationFailedException {
        List<NameValuePair> params = new LinkedList<>();
        if (limit != null) {
            params.add(new BasicNameValuePair("limit", limit.toString()));
        }
        try {
            return OktaHttpPageIterator.iteratePages(client, GET_GROUPS_PATH, collectionExtractor, Group.class, params);
        } catch (URISyntaxException | IOException e) {
            throw new GroupOperationFailedException(e);
        }
    }

    @Override
    public Group updateGroup(String id, Map<String, Object> profile) throws GroupOperationFailedException {
        try {
            Map<String, Object> body = new HashMap<>();
            body.put("profile", profile);
            HttpEntity entity = entityCreator.createEntity(body);
            HttpResponse response = client.executeHttpPut(UPDATE_GROUP_PATH.replace("{id}", id), entity);
            return jsonExtractor.getObjectFromResponse(response, Group.class);
        } catch (Exception e) {
            throw new GroupOperationFailedException(e);
        }
    }

    @Override
    public Group removeGroup(String id) throws GroupOperationFailedException {
        throw new UnsupportedOperationException("This would be dangerous to implement");
    }

    @Override
    public Collection<User> getGroupMembers(String id) throws GroupOperationFailedException {
        try {
            HttpUriRequest get = client.getRequestBuilder().getHttpGet(GET_GROUP_MEMBERS_PATH.replace("{id}", id));
            return OktaHttpPageIterator.iteratePages(client, get, collectionExtractor, User.class);
        } catch (URISyntaxException | IOException e) {
            throw new GroupOperationFailedException(e);
        }
    }

    @Override
    public void addUserToGroup(String groupId, String userId) throws GroupOperationFailedException {
        try {
            client.executeHttpPut(ADD_USER_TO_GROUP_PATH.replace("{groupId}", groupId).replace("userId", userId));
        } catch (IOException | URISyntaxException e) {
            throw new GroupOperationFailedException(e);
        }
    }

    @Override
    public void removeUserFromGroup(String id, String userId) throws GroupOperationFailedException {
        throw new UnsupportedOperationException("This would be dangerous to implement");
    }

    @Override
    public void createGroupRule(GroupRuleCondition condition) throws GroupOperationFailedException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void updateGroupRule(String ruleId, GroupRuleCondition condition) throws GroupOperationFailedException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Collection<GroupRuleCondition> getGroupRules(Integer limit, String after)
            throws GroupOperationFailedException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public GroupRuleCondition getGroupRule(String ruleId) throws GroupOperationFailedException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void deleteGroupRule(String ruleId) throws GroupOperationFailedException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void activateGroupRule(String ruleId) throws GroupOperationFailedException {
        // TODO Auto-generated method stub

    }

    @Override
    public void deactivateGroupRule(String ruleId) throws GroupOperationFailedException {
        // TODO Auto-generated method stub

    }

    

}
