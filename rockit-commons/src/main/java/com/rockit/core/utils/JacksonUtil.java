package com.rockit.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class JacksonUtil {
    private static final Logger logger = LoggerFactory.getLogger(JacksonUtil.class);
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

    public static String toJson(Object obj) {
        if (obj == null) {
            return "";
        }
        try {
            String json = objectMapper.writeValueAsString(obj);
            return json;
        } catch (JsonProcessingException e) {
            logger.error("to json error", e);
        }
        return "";
    }

    public static <T> T getNodeToObject(JsonNode json, String nodeName, Class<T> clazz) {
        try {
            JsonNode node = json.findPath(nodeName);
            T t = objectMapper.readValue(node.toString(), clazz);
            return t;
        } catch (Exception e) {
            logger.error("json parse error,json=" + json, e);
        }
        return null;
    }

    public static JsonNode toJsonNode(Object obj) {
        try {
            String json = objectMapper.writeValueAsString(obj);
            JsonNode node = objectMapper.readTree(json);
            return node;
        } catch (Exception e) {
            logger.error("to json error", e);
        }
        return null;
    }

    public static JsonNode toJsonNode(String json) {
        if (json == null) {
            return null;
        }
        try {
            JsonNode node = objectMapper.readTree(json);
            return node;
        } catch (Exception e) {
            logger.error("to json error", e);
        }
        return null;
    }

    public static <T> T toObject(String json, Class<T> clazz) {
        try {
            T t = objectMapper.readValue(json, clazz);
            return t;
        } catch (Exception e) {
            logger.error("json parse error,json=" + json, e);
        }
        return null;
    }

    public static <T> T toObject(JsonNode json, Class<T> clazz) {
        try {
            T t = objectMapper.readValue(json.toString(), clazz);
            return t;
        } catch (Exception e) {
            logger.error("json parse error,json=" + json, e);
        }
        return null;
    }

    public static <T> T getNodeToObject(String json, String nodeName, Class<T> clazz) {
        try {
            String nodeJson = getNodeJson(json, nodeName);
            return objectMapper.readValue(nodeJson, clazz);
        } catch (Exception e) {
            logger.error("json parse error,json=" + json, e);
        }
        return null;
    }

    public static <T> List<T> getNodeToList(JsonNode json, String nodeName, Class<T> clazz) {
        try {
            JsonNode jsonNode = json.findPath(nodeName);
            Iterator<JsonNode> ite = jsonNode.elements();
            if (ite == null) {
                return Collections.emptyList();
            }

            List<T> list = new ArrayList<T>();
            while (ite.hasNext()) {
                JsonNode nextNode = ite.next();
                T t = toObject(nextNode, clazz);
                list.add(t);
            }

            return list;
        } catch (Exception e) {
            logger.error("json parse error,json=" + json, e);
        }
        return Collections.emptyList();
    }

    public static <T> List<T> getNodeToList(String json, String nodeName, Class<T> clazz) {
        try {
            return getNodeToList(toJsonNode(json), nodeName, clazz);
        } catch (Exception e) {
            logger.error("json parse error,json=" + json, e);
        }
        return Collections.emptyList();
    }

    public static String getNodeJson(JsonNode json, String nodeName) {
        try {
            String jsonNode = json.findPath(nodeName).toString();
            return jsonNode;
        } catch (Exception e) {
            logger.error("json parse error,json=" + json, e);
        }
        return "";
    }

    public static String getNodeJson(String json, String nodeName) {
        try {
            return getNodeJson(toJsonNode(json), nodeName);
        } catch (Exception e) {
            logger.error("json parse error,json=" + json, e);
        }
        return "";
    }

    public static <T> List<T> toList(String json, Class<T> clazz) {
        try {
            JsonNode node = toJsonNode(json);
            return toList(node, clazz);
        } catch (Exception e) {
            logger.error("json parse error,json=" + json, e);
        }
        return Collections.emptyList();
    }

    public static <T> List<T> toList(JsonNode json, Class<T> clazz) {
        try {
            Iterator<JsonNode> ite = json.elements();
            if (ite == null) {
                return Collections.emptyList();
            }

            List<T> list = new ArrayList<T>();
            while (ite.hasNext()) {
                JsonNode nextNode = ite.next();
                T t = toObject(nextNode, clazz);
                list.add(t);
            }

            return list;
        } catch (Exception e) {
            logger.error("json parse error,json=" + json, e);
        }
        return Collections.emptyList();
    }

    public static class MappedPropertyNamingStrategy extends PropertyNamingStrategy {
        private static final long serialVersionUID = -951554416356637587L;
        private Map<String, String> nameMap;

        public MappedPropertyNamingStrategy(Map<String, String> nameMap) {
            this.nameMap = nameMap;
        }

        @Override
        public String nameForField(MapperConfig<?> config, AnnotatedField field, String defaultName) {
            return mapName(defaultName);
        }

        @Override
        public String nameForGetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
            return mapName(defaultName);
        }

        @Override
        public String nameForSetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
            return mapName(defaultName);
        }

        private String mapName(String defaultName) {
            String name = nameMap.get(defaultName);
            if (name != null) {
                return name;
            } else {
                return defaultName;
            }
        }
    }
}
