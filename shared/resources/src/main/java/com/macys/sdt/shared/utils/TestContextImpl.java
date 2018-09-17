package com.macys.sdt.shared.utils;

import com.macys.sdt.shared.steps.website.PageNavigation;

public class TestContextImpl implements TestContext {

    private PageNavigation.NavigateMode navMode = PageNavigation.NavigateMode.Site;

    @Override
    public boolean isIshipMode() {
        return PageNavigation.NavigateMode.Iship == navMode;
    }

    @Override
    public boolean isRegistryMode() {
        return PageNavigation.NavigateMode.Registry == navMode;
    }

    @Override
    public boolean isSiteModeMode() {
        return PageNavigation.NavigateMode.Site == navMode;
    }

    @Override
    public PageNavigation.NavigateMode getCurrentMode() {
        return navMode;
    }
}
