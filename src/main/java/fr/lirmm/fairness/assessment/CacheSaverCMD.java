package fr.lirmm.fairness.assessment;

import fr.lirmm.fairness.assessment.utils.ResultCache;

public class CacheSaverCMD {
    public static void main(String[] args) {
        ResultCache resultCache = new ResultCache();
        String[] portals = {"stageportal" , "agroportal" , "bioportal"};

        for (String portal : portals) {
            System.out.println("Cache saver for : " + portal);
            System.out.println();
            resultCache.flush(portal);
            resultCache.save(portal);
        }
    }
}
