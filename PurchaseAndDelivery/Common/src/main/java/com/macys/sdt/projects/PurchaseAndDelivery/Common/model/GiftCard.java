package com.macys.sdt.projects.PurchaseAndDelivery.Common.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.json.JSONObject;

import java.util.UUID;

/**
 * Created by atepliashin on 12/5/16.
 */
@Data
@Accessors(chain = true)
public class GiftCard {

    private String number;
    private String numberEncoded;
    private String numberMasked;
    private String cid;
    private Double totalAmount;
    private String cardStatus;
    private String cardType;
    private String effectiveDate;
    private String expirationDate;
    private Integer siteId;
    private Long userId;
    private String cardGuid;
    private boolean expired;

    public GiftCard() {
        cardGuid = UUID.randomUUID().toString();
    }

    public static GiftCard buildFromJson(JSONObject json) {
        return new GiftCard().
                setNumber(json.getString("number")).
                setCid(json.getString("cid")).
                setNumberEncoded(json.getString("number_encoded")).
                setNumberMasked(json.getString("number_masked")).
                setTotalAmount(json.getDouble("total_amount")).
                setCardStatus(json.getString("card_status")).
                setCardType(json.getString("card_type")).
                setEffectiveDate(json.getString("effective_date")).
                setExpirationDate(json.getString("expiration_date")).
                setSiteId(json.getInt("site_id")).
                setExpired(json.getBoolean("expired"));
    }
}
