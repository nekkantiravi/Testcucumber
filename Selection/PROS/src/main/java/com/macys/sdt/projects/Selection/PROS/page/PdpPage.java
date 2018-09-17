package com.macys.sdt.projects.Selection.PROS.page;

import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.projects.Selection.PROS.util.ProsDataHelper;
import com.macys.sdt.shared.steps.website.PageNavigation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PdpPage implements ProsPage {

    private static ProsDataHelper prosDataHelper = ProsDataHelper.getInstance();
    private static final Logger LOGGER = LoggerFactory.getLogger(PdpPage.class);
    private String url = null;
    private int prodId = -1;

    public PdpPage(int _prodId) {
        this.prodId = _prodId;
    }

    private String buildFullUrl(PageNavigation.NavigateMode _navigateMode) {

        if (this.url == null) {

            String partialUrl = prosDataHelper.getProdUrl(this.prodId, _navigateMode);
            this.url = com.macys.sdt.framework.runner.RunConfig.url + partialUrl;
        }

        return this.url;
    }

    @Override
    public void navigate(PageNavigation.NavigateMode _naNavigateMode) {

        Navigate.visit(buildFullUrl(_naNavigateMode));
    }
}
