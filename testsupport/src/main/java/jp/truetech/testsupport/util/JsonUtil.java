package jp.truetech.testsupport.util;

import java.util.Iterator;
import java.util.Map.Entry;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    private static final ObjectMapper OM = new ObjectMapper();
    private static final String EOL = System.getProperty("line.separator");

    public static String format(String json) {
        return format(json, 2);
    }

    public static String format(String json, int indent) {
        try {
            StringBuilder sb = new StringBuilder();
            JsonNode root = OM.readTree(json);
            format(root, indent, 0, sb);
            return sb.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    static StringBuilder format(JsonNode node, int indent, int level, StringBuilder sb) {
        if (node.isContainerNode()) {
            if (node.isArray()) {
                return formatArray(node, indent, level, sb);
            }
            else {
                return formatContainer(node, indent, level, sb);
            }
        }
        else {
            return formatValue(node, sb);
        }
    }

    static StringBuilder formatArray(JsonNode node, int indent, int level, StringBuilder sb) {
        sb.append("[");
        if (!node.isEmpty()) {
            sb.append(EOL);
        }
        for (Iterator<JsonNode> it = node.elements(); it.hasNext();) {
            format(it.next(), indent, level + 1, sb);
            if (it.hasNext()) {
                sb.append(",").append(EOL);
            }
        }
        sb.append(EOL);
        indent(indent * level, sb).append("]");
        return sb;
    }

    static StringBuilder formatContainer(JsonNode node, int indent, int level, StringBuilder sb) {
        indent(indent * level, sb);
        sb.append("{").append(EOL);
        for (Iterator<Entry<String, JsonNode>> it = node.fields(); it.hasNext();) {
            Entry<String, JsonNode> field = it.next();
            indent(indent * (level + 1), sb);
            quote(sb, field.getKey()).append(":");
            format(field.getValue(), indent, level + 1, sb);
            if (it.hasNext()) {
                sb.append(",");
            }
            sb.append(EOL);
        }
        indent(indent * level, sb);
        sb.append("}");
        return sb;
    }

    static StringBuilder formatValue(JsonNode node, StringBuilder sb) {
        if (node.isTextual()) {
            return quote(sb, node.asText());
        }
        else {
            return sb.append(node.asText());
        }
    }

    static StringBuilder indent(int indent, StringBuilder sb) {
        for (int i = 0; i < indent; i++) {
            sb.append(" ");
        }
        return sb;
    }

    static StringBuilder quote(StringBuilder sb, String value) {
        return sb.append("\"").append(value).append("\"");
    }
}
