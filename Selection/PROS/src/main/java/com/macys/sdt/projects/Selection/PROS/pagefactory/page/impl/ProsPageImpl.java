package com.macys.sdt.projects.Selection.PROS.pagefactory.page.impl;

import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.projects.Selection.PROS.exception.PageVerificatonException;
import com.macys.sdt.projects.Selection.PROS.pagefactory.page.ProsPageAnno;
import com.macys.sdt.projects.Selection.PROS.pagefactory.page.PageImpl;
import com.macys.sdt.shared.steps.website.PageNavigation;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * interface to comply common Pros features
 * <p>
 * o Obtain panel selectors - horizontal, vertical??
 * o verifying all panels
 * <p>
 * Verification:
 * o Panel(s) existed
 * <p>
 * Panels:
 * o horizontal(s)
 * o vertical(s)
 */
public abstract class ProsPageImpl extends PageImpl {

    @Override
    public void verify() throws PageVerificatonException {

        boolean verified = false;
        String home = pageAlias();
        String[] hPanelSelectors = getClass().getAnnotation(ProsPageAnno.class).hPanelSelectors();

        List<String> elemExprs = buildElementExpr(home, Arrays.asList(hPanelSelectors));


    }

    public void verifyVPanels(List<String> selectors) throws PageVerificatonException {

        verifyPanels(selectors);
    }

    public void verifyHPanels(List<String> selectors) throws PageVerificatonException {

        verifyPanels(selectors);
    }

    private void verifyPanels(List<String> selectors) throws PageVerificatonException {

        boolean failed = true;
        StringBuilder failedSelectorSb = new StringBuilder();

        for (String selector : selectors) {
            boolean displayed = Elements.anyPresent(selector);

            if (!displayed) {
                failedSelectorSb.append(selector).append(", ");
            }
            failed &= displayed;
        }

        if (failed) {
            throw new PageVerificatonException("Failed to verify horizontal panels given selectors:  " + failedSelectorSb.toString(), failedSelectorSb.toString());
        }
    }

    private List<String> buildElementExpr(String pageId, List<String> elementIds) {

        List<String> elemExprs = new ArrayList<>(elementIds != null ? elementIds.size() : 0);

        for (String elemId : elementIds) {

            elemExprs.add(pageId + "." + elemId);
        }

        return elemExprs;
    }

    @Override
    public abstract void navigate(PageNavigation.NavigateMode _navigateMode) throws MalformedURLException;
}
