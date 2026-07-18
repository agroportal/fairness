package fr.lirmm.fairness.assessment.utils;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import fr.lirmm.fairness.assessment.principles.criterion.question.tests.ResolvableURLTest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

public class HttpTimeoutTest {

    private static HttpServer server;
    private static String baseUrl;

    @BeforeClass
    public static void startServer() throws Exception {
        server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        server.createContext("/fast", exchange -> respond(exchange, 200, "ok"));
        server.createContext("/error", exchange -> respond(exchange, 503, "no"));
        server.createContext("/slow", exchange -> {
            try {
                Thread.sleep(500);
                respond(exchange, 200, "late");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (IOException ignored) {
                exchange.close();
            }
        });
        server.start();
        baseUrl = "http://127.0.0.1:" + server.getAddress().getPort();
    }

    @AfterClass
    public static void stopServer() {
        server.stop(0);
    }

    @After
    public void clearProperties() {
        System.clearProperty("fairness.http.connectTimeoutMillis");
        System.clearProperty("fairness.http.readTimeoutMillis");
        System.clearProperty("fairness.urlCheck.connectTimeoutMillis");
        System.clearProperty("fairness.urlCheck.readTimeoutMillis");
    }

    @Test
    public void fastApiEndpointSucceedsAndRepeatedRequestsDoNotExhaustConnections() throws Exception {
        assertEquals("ok", OntologyRestApi.get(baseUrl + "/fast", "secret", "text/plain"));
        for (int i = 0; i < 30; i++) {
            assertEquals("ok", OntologyRestApi.get(baseUrl + "/fast", "secret", "text/plain"));
            try {
                OntologyRestApi.get(baseUrl + "/error", "secret", "text/plain");
                fail("non-200 response should fail");
            } catch (Exception expected) {
                assertNotNull(expected.getMessage());
            }
        }
    }

    @Test
    public void delayedApiReadIsBounded() {
        System.setProperty("fairness.http.readTimeoutMillis", "100");
        long start = System.nanoTime();
        try {
            OntologyRestApi.get(baseUrl + "/slow", "secret", "text/plain");
            fail("delayed API should time out");
        } catch (Exception expected) {
            assertTrue(elapsedMillis(start) < 2000);
        }
    }

    @Test
    public void delayedResolvableUrlCheckIsBounded() {
        System.setProperty("fairness.urlCheck.readTimeoutMillis", "100");
        long start = System.nanoTime();
        assertFalse(ResolvableURLTest.isValid(baseUrl + "/slow", "text/plain"));
        assertTrue(elapsedMillis(start) < 2000);
    }

    private static long elapsedMillis(long start) {
        return (System.nanoTime() - start) / 1_000_000;
    }

    private static void respond(HttpExchange exchange, int status, String body) throws IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "text/plain");
        if ("HEAD".equals(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(status, -1);
        } else {
            exchange.sendResponseHeaders(status, bytes.length);
            exchange.getResponseBody().write(bytes);
        }
        exchange.close();
    }
}
