package com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by atepliashin on 10/6/16.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface FindBys {
    FindBy[] value();
}
