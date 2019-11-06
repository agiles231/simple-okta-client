package com.agiles231.okta.json;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

public class GsonListExtractor implements JsonCollectionExtractor {

    private static final Logger logger = LogManager.getLogger(GsonListExtractor.class);
    private final Gson gson;

    public GsonListExtractor(Gson gson) {
        this.gson = gson;
    }

    @Override
    public <T> Collection<T> extractCollection(String jsonContent, Class<T> clazz) {
        logger.debug("jsonContent: " + jsonContent);
        return new ArrayList<>(Arrays.asList(gson.fromJson(jsonContent, (Class<T[]>) Array.newInstance(clazz, 0).getClass()))); // surround with arraylist so that add and remove are supported
    }

}
