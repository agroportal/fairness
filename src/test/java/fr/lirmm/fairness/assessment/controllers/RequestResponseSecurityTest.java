package fr.lirmm.fairness.assessment.controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class RequestResponseSecurityTest {

    @Test
    public void statusDoesNotReflectSecretsOrQueriesAndSyncStillDisablesCache() throws Exception {
        String apiSecret = "api-secret-7f3";
        String querySecret = "request-secret-91a";
        String endpointSecret = "endpoint-secret-55c";
        Map<String, String> parameters = new HashMap<>();
        parameters.put("url", "http://example.org/api?token=" + endpointSecret);
        parameters.put("apikey", apiSecret);
        parameters.put("sync", "");
        HttpServletRequest request = request(parameters, querySecret);
        StringWriter body = new StringWriter();

        RequestController requestController = new RequestController(request);
        requestController.getPortalInstance();
        assertTrue(requestController.isCacheDisabled());
        assertEquals("http://service.test:8080/ofaire", requestController.getRequestURI(request));

        new ResponseController(response(body)).respond(true,
                requestController.getRequestURI(request), System.currentTimeMillis(), "", requestController);

        String json = body.toString();
        assertFalse(json.contains(apiSecret));
        assertFalse(json.contains(querySecret));
        assertFalse(json.contains(endpointSecret));
        JsonObject status = new JsonParser().parse(json).getAsJsonObject().getAsJsonObject("status");
        assertFalse(status.has("apikey"));
        assertFalse(status.get("request").getAsString().contains("?"));
        assertFalse(status.get("endpoint").getAsString().contains("?"));
        assertTrue(status.get("useCache").isJsonPrimitive());
        assertFalse(status.get("useCache").getAsBoolean());
    }

    private HttpServletRequest request(Map<String, String> parameters, String querySecret) {
        return (HttpServletRequest) Proxy.newProxyInstance(getClass().getClassLoader(),
                new Class[]{HttpServletRequest.class}, (proxy, method, args) -> {
                    switch (method.getName()) {
                        case "getParameter": return parameters.get(args[0]);
                        case "getScheme": return "http";
                        case "getServerName": return "service.test";
                        case "getServerPort": return 8080;
                        case "getRequestURI": return "/ofaire";
                        case "getQueryString": return "apikey=" + querySecret;
                        default: return defaultValue(method.getReturnType());
                    }
                });
    }

    private HttpServletResponse response(StringWriter body) {
        PrintWriter writer = new PrintWriter(body);
        return (HttpServletResponse) Proxy.newProxyInstance(getClass().getClassLoader(),
                new Class[]{HttpServletResponse.class}, (proxy, method, args) ->
                        method.getName().equals("getWriter") ? writer : defaultValue(method.getReturnType()));
    }

    private Object defaultValue(Class<?> type) {
        if (!type.isPrimitive()) return null;
        if (type == boolean.class) return false;
        if (type == char.class) return '\0';
        if (type == byte.class) return (byte) 0;
        if (type == short.class) return (short) 0;
        if (type == int.class) return 0;
        if (type == long.class) return 0L;
        if (type == float.class) return 0F;
        return 0D;
    }
}
