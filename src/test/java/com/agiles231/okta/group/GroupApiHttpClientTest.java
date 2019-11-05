package com.agiles231.okta.group;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import com.agiles231.okta.group.exception.GroupOperationFailedException;
import com.agiles231.okta.http.HttpGsonObjectExtractor;
import com.agiles231.okta.http.HttpStringEntityCreator;
import com.agiles231.okta.http.OktaHttpClient;
import com.agiles231.okta.json.GsonListExtractor;
import com.agiles231.testing.config.OktaHttpClientCreator;
import com.google.gson.Gson;

public class GroupApiHttpClientTest {

    @Test
    public void testGroupApiHttpClient() throws FileNotFoundException, URISyntaxException, IOException, GroupOperationFailedException {
        OktaHttpClient client = OktaHttpClientCreator.getClient("src/test/resources/apiHttp.properties", "baseUri"
                , "apiKey", "proxy.host", "proxy.port");
        Gson gson = new Gson();
        GroupApi groupApi = new GroupApiHttpClient(client, new HttpStringEntityCreator(gson), new HttpGsonObjectExtractor(gson)
                , new GsonListExtractor(gson));
        Collection<Group> groups = groupApi.getAllGroups(null);
        Assert.assertNotNull(groups);
    }
}
