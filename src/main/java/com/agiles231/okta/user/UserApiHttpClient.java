package com.agiles231.okta.user;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.agiles231.okta.http.HttpEntityCreator;
import com.agiles231.okta.http.HttpJsonObjectExtractor;
import com.agiles231.okta.http.HttpResponseContentExtractor;
import com.agiles231.okta.http.OktaHttpClient;
import com.agiles231.okta.http.OktaHttpPageIterator;
import com.agiles231.okta.http.exception.HttpEntityCreationFailedException;
import com.agiles231.okta.json.JsonCollectionExtractor;
import com.agiles231.okta.user.exception.UserNotFoundException;
import com.agiles231.okta.user.exception.UserOperationFailedException;

public class UserApiHttpClient implements UserApi {

    private static final Logger logger = LogManager.getLogger(UserApiHttpClient.class);

    private final static String CREATE_USER_PATH = "";
    private final static String GET_CLIENT_SELF_PATH = "/users/me";
    private final static String GET_USER_PATH = "/users/{id}";
    private final static String GET_USERS_PATH = "/users";
    private final static String PARTIAL_UPDATE_USER_PATH = "/users/{id}";
    private final static String GET_USER_GROUPS_PATH = "/users/{id}/groups";
    private final static String DELETE_USER_PATH = "/users/{id}";

    private final static String ACTIVATE_USER_PATH = "/users/{id}/lifecycle/activate";
    private final static String REACTIVATE_USER_PATH = "/users/{id}/lifecycle/reactivate";
    private final static String SUSPEND_USER_PATH = "/users/{id}/lifecycle/suspend";
    private final static String UNSUSPEND_USER_PATH = "/users/{id}/lifecycle/unsuspend";
    private final static String UNLOCK_USER_PATH = "/users/{id}/lifecycle/unlock";
    private final static String DEACTIVATE_USER_PATH = "/users/{id}/lifecycle/deactivate";
    private final static String RESET_PASSWORD_PATH = "/users/{id}/lifecycle/reset_password";
    private final static String EXPIRE_PASSWORD_PATH = "/users/{id}/lifecycle/expire_password";
    private final static String RESET_FACTORS_PATH = "/users/{id}/lifecycle/reset_factors";

    private final static String FORGOT_PASSWORD_PATH = "/users/{id}/credentials/forgot_password";
    private final static String CHANGE_PASSWORD_PATH = "/users/{id}/credentials/change_password";
    private final static String CHANGE_RECOVERY_QUESTION_PATH = "/users/{id}/credentials/change_recovery_question";

    private final OktaHttpClient client;
    private final HttpEntityCreator entityCreator;
    private final JsonCollectionExtractor collectionExtractor;
    private final HttpJsonObjectExtractor jsonExtractor;


    public UserApiHttpClient(OktaHttpClient client, HttpEntityCreator entityCreator,
            HttpJsonObjectExtractor jsonExtractor, JsonCollectionExtractor collectionExtractor) {
        this.client = client;
        this.entityCreator = entityCreator;
        this.jsonExtractor = jsonExtractor;
        this.collectionExtractor = collectionExtractor;
    }

    @Override
    public User createUser(Map<String, Object> profile, Boolean activate, Boolean provider, UserCredentials credentials,
            List<String> groupIds, String nextLogin) throws UserOperationFailedException {
        try {
            List<NameValuePair> params = new LinkedList<>();
            if (activate != null) {
                params.add(new BasicNameValuePair("activate", activate.toString()));
            }
            if (provider != null) {
                params.add(new BasicNameValuePair("provider", provider.toString()));
            }
            if (nextLogin != null) {
                params.add(new BasicNameValuePair("nextLogin", nextLogin));
            }
            HttpPost post = client.getRequestBuilder().getHttpPost(CREATE_USER_PATH, params);
            HashMap<String, Object> body = new HashMap<String, Object>();
            body.put("profile", profile);

            if (groupIds != null && groupIds.size() > 0) {
                body.put("groupIds", groupIds);
            }

            post.setEntity(entityCreator.createEntity(body));
            HttpResponse response = client.execute(post);
            return jsonExtractor.getObjectFromResponse(response, User.class);
        } catch (Exception e) {
            throw new UserOperationFailedException(e);
        }
    }

    @Override
    public User getClientSelf() throws UserNotFoundException {
        try {
            HttpGet get = client.getRequestBuilder().getHttpGet(GET_CLIENT_SELF_PATH);
            HttpResponse response = client.execute(get);
            return jsonExtractor.getObjectFromResponse(response, User.class);
        } catch (Exception e) {
            throw new UserNotFoundException(e);
        }
    }

    @Override
    public User getUser(String loginOrId) throws UserNotFoundException {
        try {
            HttpGet get = client.getRequestBuilder().getHttpGet(GET_USER_PATH.replace("{id}", loginOrId));
            HttpResponse response = client.execute(get);
            return jsonExtractor.getObjectFromResponse(response, User.class);
        } catch (Exception e) {
            throw new UserNotFoundException(e);
        }
    }

    @Override
    public Collection<User> getUsers(Integer limit) throws Exception {
        String path = GET_USERS_PATH;
        List<NameValuePair> params = new LinkedList<>();
        if (limit != null) {
            params.add(new BasicNameValuePair("limit", limit.toString()));
        }
        return collectionExtractor.extractCollection(
                HttpResponseContentExtractor.getContentFromResponse(client.executeHttpGet(path)), User.class);
    }

    @Override
    public Collection<User> getUsers(Integer limit, String after) throws Exception {
        String path = GET_USERS_PATH;
        List<NameValuePair> params = new LinkedList<>();
        if (limit != null) {
            params.add(new BasicNameValuePair("limit", limit.toString()));
        }
        if (after != null) {
            params.add(new BasicNameValuePair("after", after));
        }
        return collectionExtractor.extractCollection(
                HttpResponseContentExtractor.getContentFromResponse(client.executeHttpGet(path)), User.class);
    }

    @Override
    public Collection<User> getAllUsers(Integer limit) throws Exception {
        String path = GET_USERS_PATH;
        List<NameValuePair> params = new LinkedList<>();
        if (limit != null) {
            params.add(new BasicNameValuePair("limit", limit.toString()));
        }
        return OktaHttpPageIterator.iteratePages(client, path, collectionExtractor, User.class, params);
    }

    @Override
    public Collection<User> filterUsers(String filter, Integer limit) throws Exception {
        String path = GET_USERS_PATH;
        List<NameValuePair> params = new LinkedList<>();
        if (limit != null) {
            params.add(new BasicNameValuePair("limit", limit.toString()));
        }
        params.add(new BasicNameValuePair("filter", filter));
        return OktaHttpPageIterator.iteratePages(client, path, collectionExtractor, User.class, params);
    }

    @Override
    public Collection<User> filterUsers(String filter, Integer limit, String after) throws Exception {
        String path = GET_USERS_PATH;
        List<NameValuePair> params = new LinkedList<>();
        if (limit != null) {
            params.add(new BasicNameValuePair("limit", limit.toString()));
        }
        params.add(new BasicNameValuePair("filter", filter));
        return collectionExtractor.extractCollection(
                HttpResponseContentExtractor.getContentFromResponse(client.executeHttpGet(path)), User.class);
    }

    @Override
    public Collection<User> searchUsers(String search, Integer limit) throws Exception {
        String path = GET_USERS_PATH;
        List<NameValuePair> params = new LinkedList<>();
        if (limit != null) {
            params.add(new BasicNameValuePair("limit", limit.toString()));
        }
        params.add(new BasicNameValuePair("search", search));
        return OktaHttpPageIterator.iteratePages(client, path, collectionExtractor, User.class, params);
    }

    @Override
    public Collection<User> searchUsers(String search, Integer limit, String after) throws Exception {
        String path = GET_USERS_PATH;
        List<NameValuePair> params = new LinkedList<>();
        if (limit != null) {
            params.add(new BasicNameValuePair("limit", limit.toString()));
        }
        params.add(new BasicNameValuePair("search", search));
        return collectionExtractor.extractCollection(
                HttpResponseContentExtractor.getContentFromResponse(client.executeHttpGet(path)), User.class);
    }

    @Override
    public Collection<User> simpleSearchUsers(String q, Integer limit) throws Exception {
        String path = GET_USERS_PATH;
        List<NameValuePair> params = new LinkedList<>();
        if (limit != null) {
            params.add(new BasicNameValuePair("limit", limit.toString()));
        }
        params.add(new BasicNameValuePair("q", q));
        return OktaHttpPageIterator.iteratePages(client, path, collectionExtractor, User.class, params);
    }

    @Override
    public Collection<User> simpleSearchUsers(String q, Integer limit, String after) throws Exception {
        String path = GET_USERS_PATH;
        List<NameValuePair> params = new LinkedList<>();
        if (limit != null) {
            params.add(new BasicNameValuePair("limit", limit.toString()));
        }
        params.add(new BasicNameValuePair("q", q));
        return collectionExtractor.extractCollection(
                HttpResponseContentExtractor.getContentFromResponse(client.executeHttpGet(path)), User.class);
    }

    @Override
    public User updateUser(String id, UserCredentials credentials, Map<String, Object> profile)
            throws UserNotFoundException, UserOperationFailedException {
        throw new UnsupportedOperationException("Use partialUpdateUser instead. This method is dangerous to implement");
    }

    @Override
    public User partialUpdateUser(String loginOrId, UserCredentials credentials, Map<String, Object> profile)
            throws UserNotFoundException, UserOperationFailedException {

        Map<String, Object> user = new HashMap<String, Object>();
        if (profile != null) {
            user.put("profile", profile);
        }
        if (credentials != null) {
            user.put("credentials", credentials);
        }

        try {
            HttpResponse response = client.executeHttpPost(PARTIAL_UPDATE_USER_PATH.replace("{id}", loginOrId),
                    entityCreator.createEntity(user));
            return jsonExtractor.getObjectFromResponse(response, User.class);
        } catch (IOException | URISyntaxException | HttpEntityCreationFailedException e) {
            throw new UserOperationFailedException(e);
        }
    }

    @Override
    public Collection<UserGroup> getUserGroups(String userId) throws UserNotFoundException {
        try {
            HttpResponse response = client.executeHttpGet(GET_USER_GROUPS_PATH.replace("{id}", userId));
            String contents = HttpResponseContentExtractor.getContentFromResponse(response);
            Collection<UserGroup> groups = new LinkedList<>();
            groups.addAll(collectionExtractor.extractCollection(contents, UserGroup.class));
            return groups;
        } catch (Exception e) {
            throw new UserNotFoundException(e);
        }
    }

    @Override
    public void deleteUser(String id, Boolean sendEmail) throws UserNotFoundException, IllegalStateException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("This method is dangerous to implement");
    }

    @Override
    public void activateUser(String id, Boolean sendEmail) throws UserNotFoundException, IllegalStateException {
        List<NameValuePair> params = new LinkedList<>();
        if (sendEmail != null) {
            params.add(new BasicNameValuePair("sendEmail", sendEmail.toString()));
        }
        try {
            HttpResponse response = client.executeHttpPost(ACTIVATE_USER_PATH.replace("{id}", id), params);
            String contents = HttpResponseContentExtractor.getContentFromResponse(response);
            // TODO turn into activation link object, if sendEmail was false, and return
        } catch (IOException | URISyntaxException e) {
            throw new UserNotFoundException(e);
        }

    }

    public void reactivateUser(String id, Boolean sendEmail) throws UserNotFoundException, IllegalStateException {
        List<NameValuePair> params = new LinkedList<>();
        if (sendEmail != null) {
            params.add(new BasicNameValuePair("sendEmail", sendEmail.toString()));
        }
        try {
            HttpResponse response = client.executeHttpPost(REACTIVATE_USER_PATH.replace("{id}", id), params);
            String contents = HttpResponseContentExtractor.getContentFromResponse(response);
            // TODO turn into activation link object, if sendEmail was false, and return
        } catch (IOException | URISyntaxException e) {
            throw new UserNotFoundException(e);
        }
    }

    @Override
    public void deactivateUser(String id, Boolean sendEmail) throws UserNotFoundException, IllegalStateException {
        List<NameValuePair> params = new LinkedList<>();
        if (sendEmail != null) {
            params.add(new BasicNameValuePair("sendEmail", sendEmail.toString()));
        }
        try {
            client.executeHttpPost(DEACTIVATE_USER_PATH.replace("{id}", id), params);
        } catch (IOException | URISyntaxException e) {
            throw new UserNotFoundException(e);
        }

    }

    @Override
    public void suspendUser(String id) throws UserNotFoundException, IllegalStateException {
        try {
            client.executeHttpPost(SUSPEND_USER_PATH.replace("{id}", id));
        } catch (IOException | URISyntaxException e) {
            throw new UserNotFoundException(e);
        }
    }

    @Override
    public void unsuspendUser(String id) throws UserNotFoundException, IllegalStateException {
        try {
            client.executeHttpPost(UNSUSPEND_USER_PATH.replace("{id}", id));
        } catch (IOException | URISyntaxException e) {
            throw new UserNotFoundException(e);
        }

    }

    @Override
    public void unlockUser(String id) throws UserNotFoundException, IllegalStateException {
        try {
            client.executeHttpPost(UNLOCK_USER_PATH.replace("{id}", id));
        } catch (IOException | URISyntaxException e) {
            throw new UserNotFoundException(e);
        }

    }

    @Override
    public void resetPassword(String id) throws UserNotFoundException, IllegalStateException {
        List<NameValuePair> params = new LinkedList<>();
        try {
            client.executeHttpPost(RESET_PASSWORD_PATH.replace("{id}", id), params);
        } catch (IOException | URISyntaxException e) {
            throw new UserNotFoundException(e);
        }
    }

    @Override
    public URI resetPasswordWithEmail(String id) throws UserNotFoundException, IllegalStateException {
        List<NameValuePair> params = new LinkedList<>();
        params.add(new BasicNameValuePair("sendEmail", "true"));
        try {
            HttpResponse response = client.executeHttpPost(RESET_PASSWORD_PATH.replace("{id}", id), params);
            Map<String, Object> map = jsonExtractor.getObjectFromResponse(response, Map.class);
            return new URI((String)map.get("resetPasswordUrl"));
        } catch (IOException | URISyntaxException e) {
            throw new UserNotFoundException(e);
        }
    }

    @Override
    public User expirePassword(String id) throws UserNotFoundException, IllegalStateException {
        List<NameValuePair> params = new LinkedList<>();
        try {
            HttpResponse response = client.executeHttpPost(EXPIRE_PASSWORD_PATH.replace("{id}", id), params);
            //String contents = HttpResponseContentExtractor.getContentFromResponse(response);
            return jsonExtractor.getObjectFromResponse(response, User.class);
        } catch (IOException | URISyntaxException e) {
            throw new UserNotFoundException(e);
        }
    }

    public String expirePasswordWithTempPassword(String id) throws UserNotFoundException, IllegalStateException {
        List<NameValuePair> params = new LinkedList<>();
        params.add(new BasicNameValuePair("tempPassword", "true"));
        try {
            HttpResponse response = client.executeHttpPost(EXPIRE_PASSWORD_PATH.replace("{id}", id), params);
            Map<String, Object> map = jsonExtractor.getObjectFromResponse(response, Map.class);
            return (String)map.get("tempPassword");
        } catch (IOException | URISyntaxException e) {
            throw new UserNotFoundException(e);
        }
    }

    @Override
    public void resetFactors(String id) throws UserNotFoundException, IllegalStateException {
        try {
            client.executeHttpPost(RESET_FACTORS_PATH.replace("{id}", id));
        } catch (IOException | URISyntaxException e) {
            throw new UserNotFoundException(e);
        }
    }

    @Override
    public void forgotPassword(String id, Boolean sendEmail) throws UserNotFoundException, IllegalStateException {
        List<NameValuePair> params = new LinkedList<>();
        if (sendEmail != null) {
            params.add(new BasicNameValuePair("sendEmail", sendEmail.toString()));
        }
        try {
            HttpResponse response = client.executeHttpPost(FORGOT_PASSWORD_PATH.replace("{id}", id), params);
            String contents = HttpResponseContentExtractor.getContentFromResponse(response);
            // TODO turn into reset password url, if sendEmail was false, and return
        } catch (IOException | URISyntaxException e) {
            throw new UserNotFoundException(e);
        }

    }

    @Override
    public UserCredentials changePassword(String id, String strict, CredentialsPassword oldPassword,
            CredentialsPassword newPassword) throws UserNotFoundException, IllegalStateException {
        List<NameValuePair> params = new LinkedList<>();
        if (strict != null) {
            params.add(new BasicNameValuePair("strict", strict));
        }
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("oldPassword", oldPassword);
        requestBody.put("newPassword", newPassword);
        try {
            HttpEntity entity = entityCreator.createEntity(requestBody);
            HttpResponse response = client.executeHttpPost(CHANGE_PASSWORD_PATH.replace("{id}", id), params, entity);
            return jsonExtractor.getObjectFromResponse(response, UserCredentials.class);
        } catch (HttpEntityCreationFailedException | IOException | URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }

    }

    @Override
    public UserCredentials changeRecoveryQuestion(String id, CredentialsPassword password,
            RecoveryQuestion recoveryQuestion) throws UserNotFoundException, IllegalStateException {
        List<NameValuePair> params = new LinkedList<>();
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("password", password);
        requestBody.put("recovery_question", recoveryQuestion);
        try {
            HttpEntity entity = entityCreator.createEntity(requestBody);
            HttpResponse response = client.executeHttpPost(CHANGE_RECOVERY_QUESTION_PATH.replace("{id}", id), params,
                    entity);
            return jsonExtractor.getObjectFromResponse(response, UserCredentials.class);
        } catch (HttpEntityCreationFailedException | IOException | URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }

    }

}
