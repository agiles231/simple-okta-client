package com.agiles231.okta.app;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import com.agiles231.okta.app.exception.AppOperationFailedException;
import com.agiles231.okta.http.HttpGsonObjectExtractor;
import com.agiles231.okta.http.HttpStringEntityCreator;
import com.agiles231.okta.http.OktaHttpClient;
import com.agiles231.okta.json.GsonListExtractor;
import com.agiles231.testing.config.OktaHttpClientCreator;
import com.google.gson.Gson;


public class AppApiHttpClientTest {

    @Test
    public void testAppApiHttpCilent() throws FileNotFoundException, URISyntaxException, IOException, AppOperationFailedException {
        OktaHttpClient client = OktaHttpClientCreator.getClient("src/test/resources/apiHttp.properties", "baseUri"
                , "apiKey", "proxy.host", "proxy.port");
        Gson gson = new Gson();
        AppApi appApi = new AppApiHttpClient(client, new HttpStringEntityCreator(gson), new HttpGsonObjectExtractor(gson)
                , new GsonListExtractor(gson));
        Collection<App> apps = appApi.getAllApps(null, null);
        Assert.assertNotNull(apps);
        for (App app : apps) {
            Assert.assertNotNull(app.getId());
        }
        
        for (App app : apps) {
            Collection<AppUser> appUsers = appApi.getAllAppUsers(app.getId(), null);
            for (AppUser appUser : appUsers) {
                Assert.assertNotNull(appUser.getId());
            }
        }
    }
}
