package com.agiles231.testing.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import org.apache.http.HttpHost;

import com.agiles231.okta.ApiVersion;
import com.agiles231.okta.http.OktaHttpClient;
import com.google.gson.Gson;

public class OktaHttpClientCreator {

    public static OktaHttpClient getClient(String fileName, String baseUriProperty, String apiKeyProperty
            , String proxyHostProperty, String proxyPortProperty) throws URISyntaxException, FileNotFoundException, IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(fileName));
        String baseUri = properties.getProperty(baseUriProperty);
        String apiKey = properties.getProperty(apiKeyProperty);
        String proxyHost = properties.getProperty(proxyHostProperty);
        String proxyPort = properties.getProperty(proxyPortProperty);
        HttpHost proxy = null;
        if(proxyHost != null && proxyPort != null) {
            if(!proxyPort.matches("\\d+")) {
                throw new IllegalArgumentException("Proxy port must be a number between 1 and 65535");
            }
            proxy = new HttpHost(proxyHost, Integer.parseInt(proxyPort));
        }
        return new OktaHttpClient(new URI(baseUri), ApiVersion.V1, apiKey, proxy);
    }
}
