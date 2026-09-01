package fr.lirmm.fairness.assessment;

import fr.lirmm.fairness.assessment.models.Configuration;
import fr.lirmm.fairness.assessment.utils.ResultCache;

import java.util.Arrays;

public class CacheHealthCMD {

    public static void main(String[] args) {
        System.exit(run(args, new ResultCache(), Configuration.getInstance().getConfiguredPortalAvailable()));
    }

    static int run(String[] args, ResultCache resultCache, String[] configuredPortals) {
        if (args.length != 1 || configuredPortals == null || !Arrays.asList(configuredPortals).contains(args[0])) {
            return 1;
        }
        return resultCache.isValid(args[0]) ? 0 : 1;
    }
}
