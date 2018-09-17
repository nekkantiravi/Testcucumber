package com.macys.sdt.projects.Selection.PROS.exception;

public class PageVerificatonException extends Exception {
    protected String idSelector = null;

    public PageVerificatonException(String _idSelector, String _msg) {
        super(_msg);
        idSelector = _idSelector;
    }

    @Override
    public String getMessage() {
        return new StringBuilder("Unable to verify page with selector(s) ").append(idSelector).append(":  ").append(super.getMessage()).toString();
    }
}
