package fr.lirmm.fairness.assessment;

import fr.lirmm.fairness.assessment.models.Configuration;
import fr.lirmm.fairness.assessment.models.PortalInstance;
import fr.lirmm.fairness.assessment.utils.ResultCache;

import java.io.IOException;
import java.util.logging.Logger;

public class CacheSaverCMD {

    private static final Logger LOGGER = Logger.getLogger(CacheSaverCMD.class.getName());

    public static void main(String[] args) throws IOException {
        ResultCache resultCache = new ResultCache();
        if(args.length == 0){
            args = Configuration.getInstance().getConfiguredPortalAvailable();
        }

        for (String portal : args) {
            LOGGER.info("Cache saver for : " + portal);
            resultCache.flush(portal);
            resultCache.save(PortalInstance.getFromConfiguration(Configuration.getInstance() , portal, true));
        }
    }
}
