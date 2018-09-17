package com.macys.sdt.projects.PurchaseAndDelivery.Common.utils;

import com.macys.sdt.projects.PurchaseAndDelivery.Common.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by atepliashin on 1/27/17.
 */
public class Orders {
    private static List<Order> orders = new ArrayList<>();
    private static Logger logger = Logger.getLogger(Thread.currentThread().getClass().getName());

    public static void add(Order order) {
        orders.add(order);
    }

    public static void add(String number, String email) {
        if (number == null || email == null || number.isEmpty() || email.isEmpty()) {
            throw new IllegalArgumentException("order number or email not present");
        }
        logger.info(String.format("Attempting to create an order object with email \"%s\" and order number \"%s\"", email, number));
        orders.add(new Order().setNumber(number).setEmail(email));
    }

    public static Order last() {
        return orders.get(orders.size() - 1);
    }
}
