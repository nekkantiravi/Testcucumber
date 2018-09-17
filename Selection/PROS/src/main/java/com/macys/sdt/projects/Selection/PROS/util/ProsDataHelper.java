package com.macys.sdt.projects.Selection.PROS.util;

import com.macys.sdt.shared.steps.website.PageNavigation;
import com.macys.sdt.shared.utils.FileUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class ProsDataHelper {

    private static ProsDataHelper ourInstance = new ProsDataHelper();
    private static final String DATA_FILE_NAME = "file:Selection/PROS/src/main/resources/pros_data.json";
    private Map<String, String> dataMap = null;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProsDataHelper.class);
    private FileUtil fileUtilInst = FileUtil.getInstance();

    public enum DataKey {

        Mcom("mcom"),
        PdpUrl("pdpUrl"),
        RegistryPdpUrl("registryPdpUrl");

        String val = null;

        DataKey(String val_) {
            this.val = val_;
        }

        @Override
        public String toString() {
            return val;
        }
    }

    private ProsDataHelper() {

        try {
            dataMap = fileUtilInst.getJsonKeyValue(DataKey.Mcom.toString(), DATA_FILE_NAME);
        } catch (Exception ex) {
            LOGGER.warn("Unable to load data file " + DATA_FILE_NAME + ": " + ExceptionUtils.getStackTrace(ex));
        }
    }

    public static ProsDataHelper getInstance() {
        return ourInstance;
    }

    public String getProdUrl(int _pid, PageNavigation.NavigateMode _navMode) {

        String url_ = null;

        if (PageNavigation.NavigateMode.Registry == _navMode) {
            url_ = dataMap.get(DataKey.RegistryPdpUrl.toString());
        } else {
            url_ = dataMap.get(DataKey.PdpUrl.toString());
        }

        url_ = String.format(url_, _pid);
        return url_;
    }
}
