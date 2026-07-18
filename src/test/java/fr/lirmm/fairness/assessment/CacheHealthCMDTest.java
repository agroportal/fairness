package fr.lirmm.fairness.assessment;

import fr.lirmm.fairness.assessment.utils.ResultCache;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CacheHealthCMDTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void validatesCacheStructure() throws Exception {
        Path cache = temporaryFolder.getRoot().toPath().resolve("cache.json");
        ResultCache resultCache = new ResultCache();

        assertFalse(valid(resultCache, cache));
        assertFalse(writeAndValidate(resultCache, cache, "bad json"));
        assertFalse(writeAndValidate(resultCache, cache, "[]"));
        assertFalse(writeAndValidate(resultCache, cache, "{\"other\":{}}"));
        assertFalse(writeAndValidate(resultCache, cache, "{\"ontologies\":{}}"));
        assertTrue(writeAndValidate(resultCache, cache, "{\"ontologies\":{\"A\":{}}}"));
    }

    @Test
    public void runReturnsExactHealthExitCodes() {
        String[] configured = {"portal"};
        assertEquals(1, CacheHealthCMD.run(new String[0], cache(true), configured));
        assertEquals(1, CacheHealthCMD.run(new String[]{"portal"}, cache(true), null));
        assertEquals(1, CacheHealthCMD.run(new String[]{"portal", "extra"}, cache(true), configured));
        assertEquals(1, CacheHealthCMD.run(new String[]{"unknown"}, cache(true), configured));
        assertEquals(1, CacheHealthCMD.run(new String[]{"portal"}, cache(false), configured));
        assertEquals(0, CacheHealthCMD.run(new String[]{"portal"}, cache(true), configured));
    }

    private ResultCache cache(boolean healthy) {
        return new ResultCache() {
            @Override
            public boolean isValid(String portal) {
                return healthy;
            }
        };
    }

    private boolean writeAndValidate(ResultCache resultCache, Path path, String json) throws Exception {
        Files.write(path, json.getBytes(StandardCharsets.UTF_8));
        return valid(resultCache, path);
    }

    private boolean valid(ResultCache resultCache, Path path) throws Exception {
        java.lang.reflect.Method method = ResultCache.class.getDeclaredMethod("isValid", Path.class);
        method.setAccessible(true);
        return (boolean) method.invoke(resultCache, path);
    }
}
