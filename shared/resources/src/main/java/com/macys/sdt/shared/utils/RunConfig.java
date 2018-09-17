package com.macys.sdt.shared.utils;

import org.apache.commons.lang3.StringUtils;

public class RunConfig {

    private static RunConfig runConfig = new RunConfig();

    public enum Params {

        MemberPid("member_pid"),
        ElementTimeout("element_timeout"),
        MasterPid("master_pid");

        String value = null;

        Params(String _val) {
            this.value = _val;
        }

        public String toString() {
            return value;
        }
    }

    private RunConfig() {
    }

    public static RunConfig getInstance() {
        return runConfig;
    }

    public boolean hasPid(Product.Type productType_) {

        boolean has_ = true;

        try {
            switch (productType_) {
                case Member:
                    getMemberPid();
                    break;
                case Master:
                    getMasterPid();
                    break;
                default:
                    has_ = false;
            }
        } catch (Exception e) {
            has_ = false;
        }

        return has_;
    }

    public boolean hasMemberProdId() {

        boolean has_ = true;
        try {
            getMemberPid();
        } catch (Exception e) {
            has_ = false;
        }
        return has_;
    }

    public int getMemberPid() throws Exception {

        String pidSysVal = StringUtils.trimToEmpty(com.macys.sdt.framework.runner.RunConfig.getExParam(Params.MasterPid.toString()));

        Integer pid_ = StringUtils.isNumeric(pidSysVal) ? Integer.valueOf(pidSysVal) : null;

        if (pid_ != null) {
            return pid_.intValue();
        }

        throw new Exception(String.format("Unable to evaluate product id for given environment parameter %s", Params.MemberPid.toString()));
    }

    public int getMasterPid() throws Exception {

        String pidSysVal = StringUtils.trimToEmpty(com.macys.sdt.framework.runner.RunConfig.getExParam(Params.MemberPid.toString()));

        Integer pid_ = StringUtils.isNumeric(pidSysVal) ? Integer.valueOf(pidSysVal) : null;

        if (pid_ != null) {
            return pid_.intValue();
        }

        throw new Exception(String.format("Unable to evaluate product id for given environment parameter %s", Params.MemberPid.toString()));
    }
}
