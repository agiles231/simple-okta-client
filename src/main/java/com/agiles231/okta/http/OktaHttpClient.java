package com.agiles231.okta.http;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.agiles231.okta.ApiVersion;
import com.agiles231.okta.http.uri.InitializedUriBuilder;

public class OktaHttpClient {
    
    private static final Logger logger = LogManager.getLogger(OktaHttpClient.class);

    private final OktaApiRequestBuilder requestBuilder;
    private final URI baseUri;
    private final HttpClient client;
    private final ApiVersion apiVersion;

    public OktaHttpClient(URI baseUri, ApiVersion apiVersion, String apiKey, HttpHost proxy) {
        super();
        if (apiKey == null || apiKey.length() == 0) {
            throw new IllegalStateException("Missing or invalid API key.");
        }
        if (baseUri == null) {
            throw new IllegalStateException("Missing base URL");
        }
        this.baseUri = baseUri;
        List<Header> requestHeaders = new ArrayList<Header>();
        requestHeaders.add(new BasicHeader(HttpHeaders.ACCEPT, "application/json"));
        requestHeaders.add(new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json"));
        requestHeaders.add(new BasicHeader(HttpHeaders.AUTHORIZATION, "SSWS " + apiKey));

        HttpClientBuilder builder = HttpClientBuilder.create();
        if (proxy != null) {
            builder.setProxy(proxy);
        }
        builder.setDefaultHeaders(requestHeaders);
        this.client = builder.build();

        this.apiVersion = apiVersion;

        String scheme = baseUri.getScheme();
        String host = baseUri.getHost();
        int port = baseUri.getPort();
        String basePath = baseUri.getPath();

        URIBuilder uriBuilder = new URIBuilder().setScheme(scheme).setHost(host);
        if (port !=  -1) { // -1 is indicator for not provided
            uriBuilder = uriBuilder.setPort(port);
        }
        requestBuilder = new OktaApiRequestBuilder(new InitializedUriBuilder(uriBuilder, basePath), apiVersion);
    }

    public OktaHttpClient(URI baseUri, ApiVersion apiVersion, String apiKey) {
        this(baseUri, apiVersion, apiKey, null);
    }
    
    public URI getBaseUri() {
        return baseUri;
    }
    
    public ApiVersion getApiVersion() {
        return apiVersion;
    }
    
    public OktaApiRequestBuilder getRequestBuilder() {
        return requestBuilder;
    }

    public HttpResponse execute(HttpUriRequest httpUriRequest) throws ClientProtocolException, IOException {
        logger.info("Request : " + httpUriRequest);
        return OktaRateLimitMonitor.awaitRateLimitAndExecute(this, httpUriRequest);
    }

    private HttpResponse doExecute(HttpUriRequest httpUriRequest) throws ClientProtocolException, IOException {
        return client.execute(httpUriRequest);
    }

    public HttpResponse executeHttpGet(URI uri) throws URISyntaxException, ClientProtocolException, IOException {
		return execute(requestBuilder.getHttpGet(uri));
	}
	
	public HttpResponse executeHttpPut(URI uri) throws URISyntaxException, ClientProtocolException, IOException {
		return execute(requestBuilder.getHttpPut(uri));
	}
	
	public HttpResponse executeHttpPost(URI uri) throws URISyntaxException, ClientProtocolException, IOException {
		return execute(requestBuilder.getHttpPost(uri));
	}
	
	public HttpResponse executeHttpDelete(URI uri) throws URISyntaxException, ClientProtocolException, IOException {
		return execute(requestBuilder.getHttpDelete(uri));
	}

	public HttpResponse executeHttpGet(String path) throws URISyntaxException, ClientProtocolException, IOException {
		return execute(requestBuilder.getHttpGet(path));
	}

	public HttpResponse executeHttpGet(String path, List<NameValuePair> params) throws URISyntaxException, ClientProtocolException, IOException {
		return execute(requestBuilder.getHttpGet(path, params));
	}
	
	public HttpResponse executeHttpPut(String path) throws URISyntaxException, ClientProtocolException, IOException {
		return execute(requestBuilder.getHttpPut(path));
	}

	public HttpResponse executeHttpPut(String path, List<NameValuePair> params) throws URISyntaxException, ClientProtocolException, IOException {
		return execute(requestBuilder.getHttpPut(path, params));
	}
	
	public HttpResponse executeHttpPost(String path) throws URISyntaxException, ClientProtocolException, IOException {
		return execute(requestBuilder.getHttpPost(path));
	}

	public HttpResponse executeHttpPost(String path, List<NameValuePair> params) throws URISyntaxException, ClientProtocolException, IOException {
		return execute(requestBuilder.getHttpPost(path, params));
	}
	
	public HttpResponse executeHttpDelete(String path) throws URISyntaxException, ClientProtocolException, IOException {
		return execute(requestBuilder.getHttpDelete(path));
	}

	public HttpResponse executeHttpDelete(String path, List<NameValuePair> params) throws URISyntaxException, ClientProtocolException, IOException {
		return execute(requestBuilder.getHttpDelete(path, params));
	}
	
	public HttpResponse executeHttpPut(URI uri, HttpEntity entity) throws URISyntaxException, ClientProtocolException, IOException {
	    HttpEntityEnclosingRequestBase put = requestBuilder.getHttpPut(uri);
	    put.setEntity(entity);
		return execute(put);
	}
	
	public HttpResponse executeHttpPost(URI uri, HttpEntity entity) throws URISyntaxException, ClientProtocolException, IOException {
	    HttpPost post = requestBuilder.getHttpPost(uri);
	    post.setEntity(entity);
		return execute(post);
	}
	
	public HttpResponse executeHttpPut(String path, HttpEntity entity) throws URISyntaxException, ClientProtocolException, IOException {
	    HttpPut put = requestBuilder.getHttpPut(path);
	    put.setEntity(entity);
		return execute(put);
	}

	public HttpResponse executeHttpPut(String path, List<NameValuePair> params, HttpEntity entity) throws URISyntaxException, ClientProtocolException, IOException {
		return execute(requestBuilder.getHttpPut(path, params));
	}
	
	public HttpResponse executeHttpPost(String path, HttpEntity entity) throws URISyntaxException, ClientProtocolException, IOException {
	    HttpPost post = requestBuilder.getHttpPost(path);
	    post.setEntity(entity);
		return execute(post);
	}

	public HttpResponse executeHttpPost(String path, List<NameValuePair> params, HttpEntity entity) throws URISyntaxException, ClientProtocolException, IOException {
	    HttpPost post = requestBuilder.getHttpPost(path, params);
	    post.setEntity(entity);
		return execute(post);
	}

	static class OktaRateLimitMonitor {
    
	    private final static Integer THRESHOLD = 10;
        private final static Map<String, Instant> templatePathResets = new HashMap<>();
        private final static Set<String> templatePaths = new HashSet<>();
        private final static Map<String, Integer> templatePathRemainings = new HashMap<>();
        static {
            String[] keys = new String[] {".*/api/v1/authn",".*/api/v1/authn/factors/.+/verify",".*/api/v1/apps",".*/api/v1/apps/.+",".*/api/v1/groups",".*/api/v1/groups/.+",".*/api/v1/users",".*/api/v1/users/.+",".*/api/v1/logs",".*/api/v1/events",".*/api/v1/sessions",".*/api/v1/orgs",".*/oauth2/.+/v1",".*/oauth2/v1",".*/oauth2/v1/clients",".*/oauth2,/api/v1"};
            for (String key : keys) {
                templatePathResets.put(key, Instant.MIN);
                templatePathRemainings.put(key, 0);
            }
            templatePaths.addAll(templatePathResets.keySet());
        }

        /**
         * Awaits rate limiting for provided path.
         * If rate is currently limited, this method will block.
         * If rate is not currently limited, this method will log call
         * for current rate-limiting interval and immediately return.
         * @param path
         * @throws InterruptedException 
         * @throws IOException 
         * @throws ClientProtocolException 
         */
        public static HttpResponse awaitRateLimitAndExecute(OktaHttpClient client, HttpUriRequest request) throws ClientProtocolException, IOException {
            String path = request.getURI().getPath();
            String templatePath = getTemplatePath(path);
            if(isRateLimited(templatePath)) {
                Duration wait = getRateLimitResetWaitTime(templatePath);
                try {
                    logger.info("Rate-limiting in effect for " + wait.toMillis() + "ms");
                    Thread.sleep(wait.toMillis());
                } catch (InterruptedException e) { // print and try the request even though wait was not over
                    e.printStackTrace();
                }
            }
            HttpResponse response = client.doExecute(request);
            updateTemplatePathRateLimitingInfo(templatePath, extractSingleValueHeader(response, "X-Rate-Limit-Reset"), extractSingleValueHeader(response, "X-Rate-Limit-Remaining"));
            return response;
        }
        
        private static String extractSingleValueHeader(HttpResponse response, String header) {
            Header[] headers = response.getHeaders(header);
            if (headers != null && headers.length >= 1) {
                Header h = headers[0];
                return h.getValue();
            }
            return null;
        }
        
        private synchronized static void updateTemplatePathRateLimitingInfo(String templatePath, String resetHeader, String remainingHeader) {
            Instant receivedReset = Instant.ofEpochSecond(Long.parseLong(resetHeader));
            Instant reset = templatePathResets.get(templatePath);
            if (receivedReset.isAfter(reset)) { // new info, overwrite
                templatePathResets.put(templatePath, receivedReset);
                templatePathRemainings.put(templatePath, Integer.parseInt(remainingHeader));
            } else { // take smallest and assume as latest info
                Integer existingRemainings = templatePathRemainings.get(templatePath);
                templatePathRemainings.put(templatePath, Math.min(existingRemainings, Integer.parseInt(remainingHeader)));
            }
        }
        
        private synchronized static String getTemplatePath(String path) {
            for (String templatePath : templatePaths) {
                if (path.matches(templatePath)) {
                    return templatePath;
                }
            }
            return null;
        }
        
        private synchronized static boolean isRateLimited(String templatePath) {
            Instant reset = templatePathResets.get(templatePath);
            Instant now = Instant.now();
            if (now.isBefore(reset)) {
                Integer remaining = templatePathRemainings.get(templatePath);
                if (remaining - THRESHOLD <= 0) {
                    return true;
                }
            }
            return false;
        }

        private static Duration getRateLimitResetWaitTime(String templatePath) {
            Instant reset = getRateLimitReset(templatePath);
            Instant now = Instant.now();
            if (reset != null && reset.isAfter(now)) {
                return Duration.between(now, reset);
            }
            return Duration.ZERO;
        }
        
        private synchronized static Instant getRateLimitReset(String templatePath) {
            return templatePathResets.get(templatePath);
        }
    }
}
