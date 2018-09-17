package com.macys.sdt.projects.Discovery.Regression.actions.website;

import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.shared.actions.website.mcom.pages.home.Home;
import cucumber.api.DataTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by yh03383 on 6/13/2017.
 */
public class PreviewCategory extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(PreviewCategory.class);

    public static void goToCategory(DataTable dataTable) {
        List<String> category = dataTable.asList(String.class);

        for (String cat : category) {
            logger.info("opening category::" + cat);
            Wait.forPageReady();
            new Home().selectMainCategory(cat);
        }
    }
}
