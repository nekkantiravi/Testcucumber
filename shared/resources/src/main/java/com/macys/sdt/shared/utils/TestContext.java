package com.macys.sdt.shared.utils;

import com.macys.sdt.shared.steps.website.PageNavigation;

public interface TestContext {
    boolean isIshipMode();
    boolean isRegistryMode();
    boolean isSiteModeMode();
    PageNavigation.NavigateMode getCurrentMode();
}
