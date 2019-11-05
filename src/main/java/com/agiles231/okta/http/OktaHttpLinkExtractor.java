package com.agiles231.okta.http;

import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OktaHttpLinkExtractor {

    private static Logger logger = LogManager.getLogger(OktaHttpLinkExtractor.class);
    public static URI getNextLink(HttpResponse response) {

        Header[] headers = response.getHeaders("Link");

        // Loop through the link headers until it finds the one for rel="next"
        for (Header header : headers) {
            if (header.getValue().contains("rel=\"next\"")) {
                String headerValue = header.getValue();
                logger.debug(header.getName() + " " + header.getValue());
                try {
                    String uri = sanitizeLinkValue(headerValue);
                    return new URI(uri);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    
    private static String sanitizeLinkValue(String link) {
        Matcher matcher = Pattern.compile("<(.*)>; rel=\"next\"").matcher(link);
        matcher.find();
        logger.debug(matcher.group(1));
        return matcher.group(1);
    }
}
