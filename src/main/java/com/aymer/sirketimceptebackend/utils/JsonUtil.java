package com.aymer.sirketimceptebackend.utils;

import com.aymer.sirketimceptebackend.common.exception.ServiceException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {

    private JsonUtil() {
    }

    public static ObjectNode createObjectNode() {
        return new ObjectMapper().createObjectNode();
    }

    public static JsonNode getJsonNode(Object obj) {
        try {
            SimpleModule module = new SimpleModule();
            module.addSerializer(BigDecimalTr.class, new ToStringSerializer());
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(module);
            JsonNode json = mapper.valueToTree(obj);
            return json;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public static <T> List<T> getObjectList(JsonNode jsonNode, Class<T> tClass) {
        SimpleModule module = new SimpleModule();
        module.addSerializer(BigDecimalTr.class, new ToStringSerializer());
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);
        try {
            CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, tClass);
            return mapper.readValue(jsonNode.toString(), listType);
        } catch (JsonProcessingException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public static JsonNode getJsonObject(String jsonStr) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(jsonStr);
            return json;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public static ObjectNode getValueJson(JsonNode json, String s) {
        try {
            JsonNode jsonObj = (json.has(s) && !(json.get(s) instanceof NullNode)) ? json.get(s) : null;
            if (jsonObj == null) {
                return null;
            }
            return (ObjectNode) jsonObj;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public static String getValueString(JsonNode json, String s) {
        try {
            return (json.has(s) && !(json.get(s) instanceof NullNode)) ? json.get(s).asText() : null;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public static Long getValueLong(JsonNode json, String s) {
        try {
            return (json.has(s) && !(json.get(s) instanceof NullNode)) ? json.get(s).asLong() : null;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public static Integer getValueInt(JsonNode json, String s) {
        try {
            return (json.has(s) && !(json.get(s) instanceof NullNode)) ? json.get(s).asInt() : null;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public static BigDecimal getValueBigDecimal(JsonNode json, String s) {
        try {
            return (json.has(s) && !(json.get(s) instanceof NullNode)) ? BigDecimal.valueOf(json.get(s).asDouble()) : null;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public static LocalDate getValueLocalDate(JsonNode json, String s) {
        try {
            String str = (json.has(s) && !(json.get(s) instanceof NullNode)) ? json.get(s).asText() : null;
            if (str == null) {
                return null;
            }
            return DateUtils.asLocalDateFromString(str);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

}
