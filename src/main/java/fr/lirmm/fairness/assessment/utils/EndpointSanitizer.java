package fr.lirmm.fairness.assessment.utils;

import java.net.URI;
import java.net.URISyntaxException;

public final class EndpointSanitizer {

    private EndpointSanitizer() {
    }

    public static String sanitize(String endpoint) {
        if (endpoint == null) {
            return null;
        }
        try {
            URI uri = new URI(endpoint);
            if (uri.getScheme() == null || uri.getHost() == null) {
                return null;
            }
            return new URI(uri.getScheme(), null, uri.getHost(), uri.getPort(), uri.getPath(), null, null).toString();
        } catch (URISyntaxException | IllegalArgumentException e) {
            return null;
        }
    }
}
