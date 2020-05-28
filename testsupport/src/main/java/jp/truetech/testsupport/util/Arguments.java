package jp.truetech.testsupport.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Arguments {

    private Map<String, String> arguments = new HashMap<>();

    public Arguments() {

    }

    public Arguments(String... args) {
        Arrays.stream(args)
        .filter(e -> e.contains("="))
        .forEach(e -> {
            String[] nameAndValue = e.split("=", 2);
            arguments.put(nameAndValue[0], nameAndValue[1]);
        });
        System.out.println("arguments: " + arguments);
    }

    public boolean contains(String name) {
        return arguments.containsKey(name);
    }

    public void put(String name, String value) {
        arguments.put(name, value);
    }

    public String get(String name) {
        return arguments.get(name);
    }

}
