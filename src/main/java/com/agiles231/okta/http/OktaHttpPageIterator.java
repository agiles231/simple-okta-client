package com.agiles231.okta.http;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.agiles231.okta.json.JsonCollectionExtractor;

public class OktaHttpPageIterator {
    
    private static final Logger logger = LogManager.getLogger();

    public static <T> Collection<T> iteratePages(OktaHttpClient client, HttpUriRequest request, JsonCollectionExtractor extractor, Class<T> clazz) throws URISyntaxException, IOException {
        Collection<T> coll = new LinkedList<>();
        URI uri = request.getURI();
        logger.info("Iterating pages");
        logger.debug("uri: " + uri);
        while(uri != null) {
            HttpResponse response = client.execute(request);
            String contents = HttpResponseContentExtractor.getContentFromResponse(response);
            logContents(contents);
            coll.addAll(extractor.extractCollection(contents, clazz));
            uri = OktaHttpLinkExtractor.getNextLink(response);
            response = client.executeHttpGet(uri);
        }
        return coll;
    }

    public static <T> Collection<T> iteratePages(OktaHttpClient client, URI origin, JsonCollectionExtractor extractor, Class<T> clazz) throws URISyntaxException, IOException {
        Collection<T> coll = new LinkedList<>();
        URI uri = origin;
        logger.info("Iterating pages");
        logger.debug("uri: " + uri);
        while(uri != null) {
            HttpResponse response = client.executeHttpGet(uri);
            String contents = HttpResponseContentExtractor.getContentFromResponse(response);
            logContents(contents);
            coll.addAll(extractor.extractCollection(contents, clazz));
            uri = OktaHttpLinkExtractor.getNextLink(response);
        }
        return coll;
    }

    public static <T> Collection<T> iteratePages(OktaHttpClient client, String origin, JsonCollectionExtractor extractor, Class<T> clazz) throws URISyntaxException, IOException {
        Collection<T> coll = new LinkedList<>();
        String url = origin;
        logger.info("Iterating pages");
        logger.debug("url: " + url);
        HttpResponse response = client.executeHttpGet(url);
        String contents = HttpResponseContentExtractor.getContentFromResponse(response);
        logContents(contents);
        coll.addAll(extractor.extractCollection(contents, clazz));
        URI uri = OktaHttpLinkExtractor.getNextLink(response);
        if (uri != null) {
            coll.addAll(iteratePages(client, uri, extractor, clazz));
        }
        return coll;
    }

    public static <T> Collection<T> iteratePages(OktaHttpClient client, String origin, JsonCollectionExtractor extractor, Class<T> clazz, List<NameValuePair> params) throws URISyntaxException, IOException {
        Collection<T> coll = new LinkedList<>();
        String url = origin;
        logger.info("Iterating pages");
        logger.debug("uri: " + url);
        HttpResponse response = client.executeHttpGet(url, params);
        String contents = HttpResponseContentExtractor.getContentFromResponse(response);
        logContents(contents);
        coll.addAll(extractor.extractCollection(contents, clazz));
        URI uri = OktaHttpLinkExtractor.getNextLink(response);
        if (uri != null) {
            coll.addAll(iteratePages(client, uri, extractor, clazz));
        }
        return coll;
    }
    
    private static void logContents(String contents) {
        if (contents.length() > 80) {
            logger.debug("contents: " + contents.substring(0, 40) + " ... " + contents.substring(contents.length() - 40, contents.length()));
        } else {
            logger.debug("contents: " + contents);
        }
    }
    
}
