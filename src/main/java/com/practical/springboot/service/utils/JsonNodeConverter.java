package com.practical.springboot.service.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jooq.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JsonNodeConverter implements Converter<Object, JsonNode> {
    private static final long serialVersionUID = 6867331905932216137L;
    private static final Logger LOG = LoggerFactory.getLogger(JsonNodeConverter.class);
    private ObjectMapper objectMapper = new ObjectMapper();

    public JsonNodeConverter() {
    }

    public JsonNode from(Object databaseObject) {
        if (databaseObject == null) {
            return null;
        } else {
            try {
                return (JsonNode)this.objectMapper.readValue(databaseObject.toString(), JsonNode.class);
            } catch (IOException var3) {
                LOG.warn("action=convertToJsonNode, databaseObject={}", databaseObject.toString());
                throw new IllegalArgumentException("Object cannot be converted to JsonNode", var3);
            }
        }
    }

    public Object to(JsonNode userObject) {
        if (userObject == null) {
            return null;
        } else {
            try {
                return this.objectMapper.writeValueAsString(userObject);
            } catch (JsonProcessingException var3) {
                LOG.warn("action=jsonNodeToString, userObject={}", userObject);
                throw new IllegalArgumentException("JsonNode cannot be converted to String", var3);
            }
        }
    }

    public Class<Object> fromType() {
        return Object.class;
    }

    public Class<JsonNode> toType() {
        return JsonNode.class;
    }

    void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}
