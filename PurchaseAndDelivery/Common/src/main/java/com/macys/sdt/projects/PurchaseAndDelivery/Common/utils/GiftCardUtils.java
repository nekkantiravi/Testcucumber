package com.macys.sdt.projects.PurchaseAndDelivery.Common.utils;

import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.framework.utils.db.utils.DBUtils;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.model.GiftCard;
import org.json.JSONArray;
import org.json.JSONObject;

import static com.macys.sdt.framework.utils.Utils.getResourceFile;
import static com.macys.sdt.framework.utils.Utils.readTextFile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by atepliashin on 12/5/16.
 */
public class GiftCardUtils {

    public static class DB {

        public static void addUserRewardCard(GiftCard card) {
            Connection connection = DBUtils.setupDBConnection();
            String statement = Utils.getSqlQueries().getJSONObject("mb_money").getString("insert_user_reward_card");
            statement = statement.replaceFirst("\\?", card.getUserId().toString()).
                    replaceFirst("\\?", card.getNumberEncoded()).
                    replaceFirst("\\?", card.getNumberMasked()).
                    replaceFirst("\\?", card.getCardGuid()).
                    replaceFirst("\\?", card.getTotalAmount().toString()).
                    replaceFirst("\\?", card.getEffectiveDate()).
                    replaceFirst("\\?", card.getExpirationDate()).
                    replaceFirst("\\?", card.getCardType()).
                    replaceFirst("\\?", card.getCardStatus()).
                    replaceFirst("\\?", card.getSiteId().toString());
            System.out.println(statement);
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(statement);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static List<GiftCard> getUserRewardCards() {
        List<GiftCard> cards = new ArrayList<>();
        JSONArray jsonCards;
        try {
            jsonCards = new JSONObject(readTextFile(getResourceFile("userRewardCards.json"))).getJSONArray("macys");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < jsonCards.length(); i++) {
            cards.add(GiftCard.buildFromJson(jsonCards.getJSONObject(i)));
        }
        return cards;
    }

    public static List<GiftCard> getUserICWCards() {
        return getUserRewardCards().stream().filter(card -> card.getCardType().equals("ICW")).collect(Collectors.toList());
    }
}
