package com.squidward.utils;

import java.util.HashMap;
import java.util.Map;

public class Parameters {

    private Map<String, String> parameters;
    public Parameters(String body) {

        parameters = new HashMap<>();
        parseBody(body);
    }

    public boolean hasParameter(String parameter) {
        return parameters.containsKey(parameter);
    }

    public String getParameter(String parameter) {
        return parameters.get(parameter);
    }

    private void parseBody(String body) {

        String[] params = body.split("&");
        for (String param : params) {

            String[] keyValuePair = param.split("=");
            if (keyValuePair.length == 2) {
                parameters.put(keyValuePair[0], keyValuePair[1]);
            }
        }
    }
}
