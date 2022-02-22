package fr.lirmm.fairness.assessment;

import fr.lirmm.fairness.assessment.models.Configuration;
import fr.lirmm.fairness.assessment.models.PortalInstance;
import fr.lirmm.fairness.assessment.utils.ResultCache;

import java.io.IOException;

public class CacheSaverCMD {
    public static void main(String[] args) throws IOException {
        ResultCache resultCache = new ResultCache();
        if(args.length == 0){
            args = Configuration.getInstance().getConfiguredPortalAvailable();
        }

        for (String portal : args) {
            System.out.println("Cache saver for : " + portal);
            System.out.println();
            resultCache.flush(portal);
            resultCache.save(PortalInstance.getFromConfiguration(Configuration.getInstance() , portal, true));
        }
    }
}
