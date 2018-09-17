package com.macys.sdt.projects.Selection.PROS.pagefactory.page;

import com.macys.sdt.projects.Selection.PROS.exception.PageVerificatonException;
import com.macys.sdt.projects.Selection.PROS.exception.UnknownPageException;
import com.macys.sdt.shared.steps.website.PageNavigation;

import java.net.MalformedURLException;

public interface Page {

    void navigate(PageNavigation.NavigateMode _navigateMode) throws MalformedURLException;

    void verify() throws PageVerificatonException;

    void validate() throws UnknownPageException;
}
