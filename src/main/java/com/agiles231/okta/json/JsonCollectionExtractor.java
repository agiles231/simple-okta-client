package com.agiles231.okta.json;

import java.util.Collection;

public interface JsonCollectionExtractor {
    
    public <T> Collection<T> extractCollection(String jsonContent, Class<T> clazz);
}
