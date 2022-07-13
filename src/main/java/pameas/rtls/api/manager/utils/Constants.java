package pameas.rtls.api.manager.utils;

import pameas.rtls.api.manager.service.EnvUtils;

public class Constants {

    public static final String LOCATION_SERVICE_URL = EnvUtils.getEnvVar("LOCATION_SERVICE_URI", "http://dss.aegean.gr:7011");
    public static final String DBPROXY_URL = EnvUtils.getEnvVar("DBPROXY_URI","http://dss.aegean.gr:8090");
}
