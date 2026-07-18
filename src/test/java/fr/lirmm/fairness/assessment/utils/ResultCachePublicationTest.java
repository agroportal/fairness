package fr.lirmm.fairness.assessment.utils;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class ResultCachePublicationTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void invalidCandidatesPreserveOldBytesAndLeaveNoTemporaryFiles() throws Exception {
        Path cache = cacheWith("{\"ontologies\":{\"old\":{}}}");
        byte[] old = Files.readAllBytes(cache);

        assertRejected(cache, "not json", 1, old);
        assertRejected(cache, "[]", 1, old);
        assertRejected(cache, "{\"other\":{}}", 1, old);
        assertRejected(cache, "{\"ontologies\":{}}", 1, old);
        assertRejected(cache, "{\"ontologies\":{\"only\":{}}}", 10, old);
    }

    @Test
    public void completeCandidateReplacesOldCache() throws Exception {
        Path cache = cacheWith("{\"ontologies\":{\"old\":{}}}");
        String candidate = "{\"ontologies\":{\"one\":{},\"two\":{}}}";

        new ResultCache().store(candidate, cache, 2);

        assertEquals(candidate, new String(Files.readAllBytes(cache), StandardCharsets.UTF_8));
        assertNoTemporaryFiles(cache);
    }

    @Test
    public void legitimateCatalogueShrinkPublishesWhenCandidateMatchesSource() throws Exception {
        Path cache = cacheWith(ontologies(10));
        String candidate = ontologies(4);

        new ResultCache().store(candidate, cache, 4);

        assertEquals(candidate, new String(Files.readAllBytes(cache), StandardCharsets.UTF_8));
        assertNoTemporaryFiles(cache);
    }

    private Path cacheWith(String content) throws IOException {
        Path cache = temporaryFolder.getRoot().toPath().resolve("cache.json");
        Files.write(cache, content.getBytes(StandardCharsets.UTF_8));
        return cache;
    }

    private void assertRejected(Path cache, String candidate, int sourceCount, byte[] expected) throws Exception {
        try {
            new ResultCache().store(candidate, cache, sourceCount);
            fail("invalid candidate was published");
        } catch (IOException expectedFailure) {
            assertArrayEquals(expected, Files.readAllBytes(cache));
            assertNoTemporaryFiles(cache);
        }
    }

    private void assertNoTemporaryFiles(Path cache) throws IOException {
        try (Stream<Path> files = Files.list(cache.getParent())) {
            assertEquals(1, files.count());
        }
    }

    private String ontologies(int count) {
        StringBuilder json = new StringBuilder("{\"ontologies\":{");
        for (int i = 0; i < count; i++) {
            if (i > 0) json.append(',');
            json.append('\"').append(i).append("\":{}");
        }
        return json.append("}}").toString();
    }
}
