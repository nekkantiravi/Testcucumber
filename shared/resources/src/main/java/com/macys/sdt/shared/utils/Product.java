package com.macys.sdt.shared.utils;

public class Product {

    public enum Type {

        Member("member"),
        Master("master"),
        Standard("standard");

        private String val = null;

        Type(String val_) {
            this.val = val_;
        }

        @Override
        public String toString() {
            return this.val;
        }
    }
}
