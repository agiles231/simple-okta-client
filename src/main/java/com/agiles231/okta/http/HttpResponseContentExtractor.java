package com.agiles231.okta.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

public class HttpResponseContentExtractor {
	
	/**
	 * Gets the content from an HttpResponse object.
	 * 
	 * @param response
	 *            The response from the HttpClient
	 * @return A string with the content from the response (be it HTML, XML, JSON, morse code, etc.)
	 * @throws IOException
	 */
	public static final String getContentFromResponse(HttpResponse response) throws IOException {

		// Create a reader from the content
	    HttpEntity entity = response.getEntity();
		BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));

		// Create a string buffer to store the results
		StringBuffer result = new StringBuffer();
		String line = "";

		// Iterate over the reader's lines while it still has lines and append to the result
		while ((line = reader.readLine()) != null) {
			result.append(line);
		}
		EntityUtils.consume(entity); // required to free the connection

		// Convert the result to a string and return it
		return result.toString();
	}

}
