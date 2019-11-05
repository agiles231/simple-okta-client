package com.agiles231.okta;

import com.agiles231.okta.app.AppApi;
import com.agiles231.okta.app.AppApiHttpClient;
import com.agiles231.okta.auth.AuthApi;
import com.agiles231.okta.auth.AuthApiHttpClient;
import com.agiles231.okta.group.GroupApi;
import com.agiles231.okta.group.GroupApiHttpClient;
import com.agiles231.okta.http.HttpEntityCreator;
import com.agiles231.okta.http.HttpJsonObjectExtractor;
import com.agiles231.okta.http.OktaHttpClient;
import com.agiles231.okta.json.JsonCollectionExtractor;
import com.agiles231.okta.user.UserApi;
import com.agiles231.okta.user.UserApiHttpClient;

public class OktaApiHttpClient implements OktaApi {

    UserApi userApi;
    AppApi appApi;
    GroupApi groupApi;
    AuthApi authApi;

    public OktaApiHttpClient(OktaHttpClient client, HttpEntityCreator entityCreator,
            HttpJsonObjectExtractor jsonExtractor, JsonCollectionExtractor collectionExtractor) {
        this.userApi = new UserApiHttpClient(client, entityCreator, jsonExtractor, collectionExtractor);
        this.appApi = new AppApiHttpClient(client, entityCreator, jsonExtractor, collectionExtractor);
        this.groupApi = new GroupApiHttpClient(client, entityCreator, jsonExtractor, collectionExtractor);
        this.authApi = new AuthApiHttpClient(client, entityCreator, jsonExtractor, collectionExtractor);
    }
    @Override
    public UserApi getUserApi() {
        return userApi;
    }

    @Override
    public AppApi getAppApi() {
        return appApi;
    }

    @Override
    public GroupApi getGroupApi() {
        return groupApi;
    }

    @Override
    public AuthApi getAuthApi() {
        return authApi;
    }

}
