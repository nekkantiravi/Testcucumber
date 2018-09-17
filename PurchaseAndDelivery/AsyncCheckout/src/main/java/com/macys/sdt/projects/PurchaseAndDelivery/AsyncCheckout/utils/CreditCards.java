package com.macys.sdt.projects.PurchaseAndDelivery.AsyncCheckout.utils;

import com.macys.sdt.framework.exceptions.DataException;
import com.macys.sdt.framework.model.CreditCard;

import java.util.List;

/**
 * Created by atepliashin on 1/19/17.
 */
public class CreditCards {

    public static CreditCard getValidCard(String type) throws DataException {
        return getValidCard(type, false);
    }

    public static CreditCard getValidCard(String type, boolean secure3d) throws DataException {
        return com.macys.sdt.framework.utils.CreditCards.getValidCards().stream().
                filter(card -> card.getCardType().name.equalsIgnoreCase(type)
                        && secure3d == card.has3DSecure()).findFirst().
                orElseThrow(() -> new DataException(String.format("no valid cards of type %s found", type)));
    }

    public static CreditCard getInvalidCard() throws DataException {
        return getInvalidCards().stream().findFirst().
                orElseThrow(() -> new DataException("no invalid cards found"));
    }

    public static List<CreditCard> getInvalidCards() throws DataException {
        return com.macys.sdt.framework.utils.CreditCards.getCards("invalid_cards.json");
    }
}
