package com.macys.sdt.projects.SolutionDevelopment.ExecutionEngine.utils;

import static com.macys.sdt.framework.runner.RunConfig.getEnvOrExParam;

/**
 * This is a EE Utils class
 */
public class EEUtils {

    private static final String sdt = "sdt";
    private static final String qaa = "domain|re|dsv";
    public static String domain = (getEnvOrExParam("domain") != null) ? getEnvOrExParam("domain").toLowerCase() : "sdt";

    /**
     * Check if EE subdomain matches SDT repo
     *
     * @return true if EE subdomain using SDT repo
     */
    public static boolean isSdtRepo() {
        return domain.matches(sdt);
    }

    /**
     * Check if EE subdomain matches QAA repo
     *
     * @return true if EE subdomain using QAA repo
     */
    public static boolean isQaaRepo() {
        return domain.matches(qaa);
    }
}
