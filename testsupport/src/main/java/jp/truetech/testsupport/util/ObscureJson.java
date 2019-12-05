package jp.truetech.testsupport.util;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ObscureJson {

    private static ObjectMapper mapper = new ObjectMapper();

    private final List<String> obscureNames;
    private final List<String> excudeValues;

    private char obscureChar = '*';
    private char obscureNum = '9';

    public ObscureJson(List<String> obscureNames, List<String> excludeValues) {
        this.obscureNames = Collections.unmodifiableList(obscureNames);
        this.excudeValues = Collections.unmodifiableList(excludeValues);
    }

    public JsonNode obscure(String json) {
        try {
            return obscure(mapper.readTree(json));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return MissingNode.getInstance();
    }

    public JsonNode obscure(JsonNode node) {
        if (node.isObject()) {
            for (Iterator<String> it = node.fieldNames(); it.hasNext();) {
                String name = it.next();
                JsonNode field = node.get(name);
                if (field.isValueNode() && obscureNames.contains(name)) {
                    if (!excudeValues.contains(field.asText())) {
                        if (field.isNumber()) {
                            ((ObjectNode) node).put(name, obscureValue(field.asLong()));
                        }
                        else {
                            ((ObjectNode) node).put(name, obscureValue(field.asText()));
                        }
                    }
                }
                else if (field.isObject()) {
                    obscure(field);
                }
                else if (field.isContainerNode()) {
                    for (Iterator<JsonNode> e = field.elements(); e.hasNext();) {
                        obscure(e.next());
                    }
                }
            }
        }
        return node;
    }

    private String obscureValue(String value) {
        return repeat(obscureChar, value.length());
    }

    private long obscureValue(long value) {
        return Long.parseLong(repeat(obscureNum, (int) Math.log10(value) + 1));
    }

    private String repeat(char c, int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(c);
        }
        return sb.toString();
    }
}
