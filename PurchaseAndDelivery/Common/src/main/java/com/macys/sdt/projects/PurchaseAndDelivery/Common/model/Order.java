package com.macys.sdt.projects.PurchaseAndDelivery.Common.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by atepliashin on 1/28/17.
 */
@Data
@Accessors(chain = true)
public class Order {

    private String number;
    private String email;
    private String btordernumber;
}
