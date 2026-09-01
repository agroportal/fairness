package fr.lirmm.fairness.assessment.controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.HttpServer;
import fr.lirmm.fairness.assessment.FairServlet;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class RequestResponseSecurityTest {

    @Test
    public void statusDoesNotReflectSecretsOrQueriesAndSyncStillDisablesCache() throws Exception {
        String apiSecret = "api-secret-7f3";
        String querySecret = "request-secret-91a";
        String endpointSecret = "endpoint-secret-55c";
        String userInfoSecret = "endpoint-user-info-42d";
        String fragmentSecret = "endpoint-fragment-18b";
        Map<String, String> parameters = new HashMap<>();
        parameters.put("url", "http://portal-user:" + userInfoSecret + "@example.org/api?token=" + endpointSecret + "#" + fragmentSecret);
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
        assertFalse(json.contains(userInfoSecret));
        assertFalse(json.contains(fragmentSecret));
        JsonObject status = new JsonParser().parse(json).getAsJsonObject().getAsJsonObject("status");
        assertFalse(status.has("apikey"));
        assertFalse(status.get("request").getAsString().contains("?"));
        assertEquals("http://example.org/api", status.get("endpoint").getAsString());
        assertTrue(status.get("useCache").isJsonPrimitive());
        assertFalse(status.get("useCache").getAsBoolean());
    }

    @Test
    public void servletLogsAndResponseDoNotContainRequestCredentials() throws Exception {
        String secret = "logged-secret-2ce";
        String userInfoSecret = "logged-user-info-7a1";
        String fragmentSecret = "logged-fragment-4b8";
        HttpServer server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        server.createContext("/", exchange -> {
            byte[] body = "[]".getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(200, body.length);
            exchange.getResponseBody().write(body);
            exchange.close();
        });
        server.start();

        StringBuilder logs = new StringBuilder();
        Handler handler = new Handler() {
            @Override
            public void publish(LogRecord record) {
                logs.append(record.getMessage());
                if (record.getThrown() != null) logs.append(record.getThrown());
            }
            @Override public void flush() {}
            @Override public void close() {}
        };
        Logger logger = Logger.getLogger(FairServlet.class.getName());
        logger.addHandler(handler);
        StringWriter responseBody = new StringWriter();
        try {
            Map<String, String> parameters = new HashMap<>();
            parameters.put("url", "http://portal-user:" + userInfoSecret + "@127.0.0.1:" + server.getAddress().getPort() + "/api?token=" + secret + "#" + fragmentSecret);
            parameters.put("apikey", secret);
            parameters.put("ontologies", "all");
            parameters.put("sync", "");
            new FairServlet().service(request(parameters, secret), response(responseBody));
        } finally {
            logger.removeHandler(handler);
            server.stop(0);
        }

        assertFalse(logs.toString().contains(secret));
        assertFalse(logs.toString().contains(userInfoSecret));
        assertFalse(logs.toString().contains(fragmentSecret));
        assertFalse(responseBody.toString().contains(secret));
        assertFalse(responseBody.toString().contains(userInfoSecret));
        assertFalse(responseBody.toString().contains(fragmentSecret));
    }

    private HttpServletRequest request(Map<String, String> parameters, String querySecret) {
        return (HttpServletRequest) Proxy.newProxyInstance(getClass().getClassLoader(),
                new Class[]{HttpServletRequest.class}, (proxy, method, args) -> {
                    switch (method.getName()) {
                        case "getParameter": return parameters.get(args[0]);
                        case "getMethod": return "GET";
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
