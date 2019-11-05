package com.agiles231.okta.http;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

public class HttpQueryExtractor {


    public static List<NameValuePair> extract(URI uri) {
        return URLEncodedUtils.parse(uri, Charset.defaultCharset());
    }
}
