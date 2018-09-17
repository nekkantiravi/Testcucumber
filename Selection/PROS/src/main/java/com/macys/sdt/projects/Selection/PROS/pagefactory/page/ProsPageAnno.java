package com.macys.sdt.projects.Selection.PROS.pagefactory.page;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ProsPageAnno {

    String[] hPanelSelectors();

    String[] vPanelSelectors();

    String jsonPath();
}
