package fr.lirmm.fairness.assessment;

import fr.lirmm.fairness.assessment.utils.ResultCache;

public class CacheSaverCMD {
    public static void main(String[] args) {
        ResultCache resultCache = new ResultCache();
        resultCache.flush("stageportal");
        resultCache.save("stageportal");
    }
}
