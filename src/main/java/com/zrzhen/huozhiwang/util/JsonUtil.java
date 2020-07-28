package com.zrzhen.huozhiwang.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.*;

/**
 * @author chenanlian
 */
@Slf4j
public class JsonUtil {

    static volatile ObjectMapper objectMapper;

    private static final ObjectMapper SORTED_MAPPER = new ObjectMapper();

    static {
        SORTED_MAPPER.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
    }

    /**
     * 获取单例ObjectMapper
     *
     * @return
     */
    public static ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            synchronized (JsonUtil.class) {
                if (objectMapper == null) {
                    objectMapper = new ObjectMapper();
                }
            }
        }
        return objectMapper;
    }

    /**
     * 对象转json字符串
     *
     * @param object
     * @return
     */
    public static String obj2Json(Object object) {
        try {
            return getObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * jsonNode 转字符串并按字母排序
     *
     * @param node
     * @return
     */
    public static String jsonNode2SortStr(final JsonNode node) {
        Object obj;
        String json = null;
        try {
            obj = SORTED_MAPPER.treeToValue(node, Object.class);
            json = SORTED_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 字符串转JsonNode
     *
     * @param str
     * @return
     */
    public static JsonNode str2JsonNode(String str) {
        try {
            return getObjectMapper().readTree(str);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * JsonNode转Map，只取第一层，值转为字符串
     *
     * @param jsonNode
     * @return
     */
    public static Map<String, String> jsonNode2MapStr(JsonNode jsonNode) {
        Map<String, String> map = new HashMap<>();
        if (jsonNode.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> it = jsonNode.fields();
            while (it.hasNext()) {
                Map.Entry<String, JsonNode> entry = it.next();
                map.put(entry.getKey(), entry.getValue().asText(null));
            }
        }
        return map;
    }

    /**
     * JsonNode转Map，只取第一层
     *
     * @param jsonNode
     * @return
     */
    public static Map<String, Object> jsonNode2Map(JsonNode jsonNode) {
        Map<String, Object> map = new HashMap<>();
        if (jsonNode.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> it = jsonNode.fields();
            while (it.hasNext()) {
                Map.Entry<String, JsonNode> entry = it.next();
                map.put(entry.getKey(), entry.getValue());
            }
        }
        return map;
    }

    public static void jsonLeaf(JsonNode node) {
        if (node.isValueNode()) {
            System.out.println(node.toString());
            return;
        }

        if (node.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> it = node.fields();
            while (it.hasNext()) {
                Map.Entry<String, JsonNode> entry = it.next();
                jsonLeaf(entry.getValue());
            }
        }

        if (node.isArray()) {
            Iterator<JsonNode> it = node.iterator();
            while (it.hasNext()) {
                jsonLeaf(it.next());
            }
        }
    }


    public static List<String> getStrList(JsonNode jsonNode) {
        List<String> listll = new ArrayList<>();
        if (jsonNode.isArray()) {
            for (JsonNode objNode : jsonNode) {
                String value = objNode.asText();
                listll.add(value);
            }
        }
        return listll;
    }

    /**
     * 将数据源jsonNode 按模板jsonNode输出sql语句列表
     * @param data 数据来源
     * @param template json 模板
     * @return sql 语句列表
     */
    public static List<String> transform(JsonNode data, JsonNode template) {
        List<String> sqlList = new ArrayList<>();
        Iterator<String> tableIterator = template.fieldNames();
        while (tableIterator.hasNext()) {
            /*表*/
            String tableName = tableIterator.next();
            JsonNode tableNode = template.get(tableName);
            Iterator<String> fieldIterator = tableNode.fieldNames();

            StringBuilder sb = new StringBuilder();
            StringBuilder sbv = new StringBuilder();
            sb.append("INSERT INTO ");
            sb.append(tableName);
            sb.append(" (");
            sbv.append(" VALUES (");

            while (fieldIterator.hasNext()) {
                /*字段*/
                String fieldName = fieldIterator.next();
                String fieldPath = tableNode.get(fieldName).asText();
                JsonNode fileValue = findValueByPath(data, fieldPath);
                /*去除没有值或取值错误的节点*/
                if (null != fileValue && !fileValue.equals("")){
                    sb.append(fieldName);
                    sb.append(",");
                    sbv.append(fileValue);
                    sbv.append(",");
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            sbv.deleteCharAt(sbv.length() - 1);
            sb.append(")");
            sbv.append(")");
            sb.append(sbv);

            sqlList.add(sb.toString());
        }
        return sqlList;
    }


    /**
     * 根据路径获取值
     * @param jsonNode
     * @param valuePath 节点路径
     * @return
     */
    public static JsonNode findValueByPath(JsonNode jsonNode, String valuePath) {
        String[] pathMutil = valuePath.split("\\.");
        JsonNode jsonTem = jsonNode;
        for (int i = 0; i < pathMutil.length; i++) {
            jsonTem = jsonTem.path(pathMutil[i]);
        }
        return jsonTem;
    }


    /**
     * 重组json
     *
     * @param jsonNode
     * @param nodePath
     * @return
     */
    public static JsonNode jsonNodeTransform(JsonNode jsonNode, List<String> nodePath) {
        JsonNodeFactory factory = new JsonNodeFactory(false);
        ObjectNode objectNode = factory.objectNode();
        for (String path : nodePath) {
            String[] pathMutil = path.split("\\.");
            if (pathMutil.length > 1) {
                JsonNode jsonTem = jsonNode;
                for (int i = 0; i < pathMutil.length; i++) {
                    jsonTem = jsonTem.path(pathMutil[i]);
                }
                objectNode.put(path, jsonTem);
            } else {
                JsonNode jsonNodeChild = jsonNode.path(path);
                objectNode.put(path, jsonNodeChild);
            }
        }
        return objectNode;
    }




}