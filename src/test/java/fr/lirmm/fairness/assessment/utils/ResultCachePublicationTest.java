package fr.lirmm.fairness.assessment.utils;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.GroupPrincipal;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
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
    public void relativeSymlinkPublishesTargetWithoutReplacingLink() throws Exception {
        Path root = temporaryFolder.getRoot().toPath();
        Path targetDirectory = root.resolve("targets");
        Files.createDirectories(targetDirectory);
        Path target = targetDirectory.resolve("cache.json");
        Files.write(target, "{\"ontologies\":{\"old\":{}}}".getBytes(StandardCharsets.UTF_8));
        Path link = root.resolve("cache.json");
        Path relativeTarget = link.getParent().relativize(target);
        try {
            Files.createSymbolicLink(link, relativeTarget);
        } catch (UnsupportedOperationException | SecurityException e) {
            org.junit.Assume.assumeNoException(e);
        }

        String candidate = "{\"ontologies\":{\"new\":{}}}";
        new ResultCache().store(candidate, link, 1);

        assertTrue(Files.isSymbolicLink(link));
        assertEquals(relativeTarget, Files.readSymbolicLink(link));
        assertEquals(candidate, new String(Files.readAllBytes(target), StandardCharsets.UTF_8));

        try {
            new ResultCache().store("{\"ontologies\":{}}", link, 1);
            fail("invalid candidate was published through symlink");
        } catch (IOException expected) {
            assertTrue(Files.isSymbolicLink(link));
            assertEquals(relativeTarget, Files.readSymbolicLink(link));
            assertEquals(candidate, new String(Files.readAllBytes(target), StandardCharsets.UTF_8));
        }
    }

    @Test
    public void atomicReplacementPreservesGroupReadableOwnershipAndPermissions() throws Exception {
        Path cache = cacheWith("{\"ontologies\":{\"old\":{}}}");
        org.junit.Assume.assumeTrue(supportsPosix(cache));
        GroupPrincipal secondaryGroup = secondaryGroup(cache);
        org.junit.Assume.assumeNotNull(secondaryGroup);
        Set<PosixFilePermission> permissions = PosixFilePermissions.fromString("rw-r-----");
        PosixFileAttributeView view = Files.getFileAttributeView(cache, PosixFileAttributeView.class);
        try {
            view.setGroup(secondaryGroup);
            view.setPermissions(permissions);
        } catch (IOException | UnsupportedOperationException | SecurityException e) {
            org.junit.Assume.assumeNoException(e);
        }
        PosixFileAttributes expected = view.readAttributes();

        new ResultCache().store("{\"ontologies\":{\"new\":{}}}", cache, 1);

        PosixFileAttributes actual = Files.readAttributes(cache, PosixFileAttributes.class);
        assertEquals(expected.owner(), actual.owner());
        assertEquals(secondaryGroup, actual.group());
        assertEquals(permissions, actual.permissions());
    }

    @Test
    public void deniedPublicationPreservesOldBytes() throws Exception {
        Path cache = cacheWith("{\"ontologies\":{\"old\":{}}}");
        org.junit.Assume.assumeTrue(supportsPosix(cache));
        Path directory = cache.getParent();
        byte[] old = Files.readAllBytes(cache);
        Set<PosixFilePermission> directoryPermissions = Files.getPosixFilePermissions(directory);
        try {
            Files.setPosixFilePermissions(directory, PosixFilePermissions.fromString("r-x------"));
            org.junit.Assume.assumeFalse(Files.isWritable(directory));
            try {
                new ResultCache().store("{\"ontologies\":{\"new\":{}}}", cache, 1);
                fail("publication without directory write access succeeded");
            } catch (IOException expected) {
                assertArrayEquals(old, Files.readAllBytes(cache));
                assertNoTemporaryFiles(cache);
            }
        } finally {
            Files.setPosixFilePermissions(directory, directoryPermissions);
        }
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
        Set<PosixFilePermission> permissions = supportsPosix(cache) ? Files.getPosixFilePermissions(cache) : null;
        try {
            new ResultCache().store(candidate, cache, sourceCount);
            fail("invalid candidate was published");
        } catch (IOException expectedFailure) {
            assertArrayEquals(expected, Files.readAllBytes(cache));
            if (permissions != null) assertEquals(permissions, Files.getPosixFilePermissions(cache));
            assertNoTemporaryFiles(cache);
        }
    }

    private boolean supportsPosix(Path path) throws IOException {
        return Files.getFileStore(path).supportsFileAttributeView(PosixFileAttributeView.class);
    }

    private GroupPrincipal secondaryGroup(Path path) {
        try {
            String primary = Files.readAttributes(path, PosixFileAttributes.class).group().getName();
            Process process = new ProcessBuilder("id", "-Gn").start();
            String groups = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            if (process.waitFor() == 0) {
                for (String group : groups.trim().split("\\s+")) {
                    if (!group.equals(primary)) {
                        return path.getFileSystem().getUserPrincipalLookupService().lookupPrincipalByGroupName(group);
                    }
                }
            }
        } catch (IOException | UnsupportedOperationException | SecurityException e) {
            return null;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return null;
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
