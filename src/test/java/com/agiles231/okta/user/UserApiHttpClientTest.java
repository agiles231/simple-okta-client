package com.agiles231.okta.user;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import com.agiles231.okta.http.HttpGsonObjectExtractor;
import com.agiles231.okta.http.HttpStringEntityCreator;
import com.agiles231.okta.http.OktaHttpClient;
import com.agiles231.okta.json.GsonListExtractor;
import com.agiles231.testing.config.OktaHttpClientCreator;
import com.google.gson.Gson;


public class UserApiHttpClientTest {

    private static final Logger logger = LogManager.getLogger(UserApiHttpClientTest.class);

    @Test
    public void testUserApiHttpClient() throws Exception {
        OktaHttpClient client = OktaHttpClientCreator.getClient("src/test/resources/apiHttp.properties", "baseUri"
                , "apiKey", "proxy.host", "proxy.port");
        Gson gson = new Gson();
        UserApiHttpClient userApi = new UserApiHttpClient(client, new HttpStringEntityCreator(gson), new HttpGsonObjectExtractor(gson)
                , new GsonListExtractor(gson));
        User self = userApi.getClientSelf();
        logger.debug(self);
        Assert.assertNotNull("getClientSelf returned null", self);
        
        Collection<User> users = userApi.getAllUsers(null);
        Assert.assertNotNull(users);
        
        Integer i = new Random().nextInt();
        String newSecondEmail = "biggestBoy" + i + "@domain.com";
        Map<String, Object> profile = new HashMap<>();
        profile.put("secondEmail", newSecondEmail);
        User user1 = userApi.partialUpdateUser(self.getId(), null, profile);
        profile = user1.getProfile();
        Assert.assertEquals("secondEmail was not properly updated", newSecondEmail, profile.get("secondEmail"));
        
        User user2 = userApi.getUser(self.getId());
        Assert.assertEquals("partialUpdateUser returned user with different Id", self.getId(), user1.getId());
        Assert.assertEquals("getUser returned user with different Id", self.getId(), user2.getId());
        
        Collection<UserGroup> selfGroups = userApi.getUserGroups(self.getId());
        Assert.assertNotNull(selfGroups);
        
//        UserCredentials creds = userApi.changePassword(self.getId(), null, new CredentialsPassword("Big_boy$12345", null), new CredentialsPassword("", null));
//        Assert.assertNotNull(creds);
        
    }
}
