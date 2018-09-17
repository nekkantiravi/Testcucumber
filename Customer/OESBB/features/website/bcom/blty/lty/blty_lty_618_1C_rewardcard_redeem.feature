Feature: Verify BLCOM 618 bmoney rewardcard redeem email content

  @blty_lty_618_1C_bmoney_rewardcard_redeem @regression
  Scenario: Verify Email content
    Given i trigger "blty_lty_618_1C_lty_rewardcard_redeem" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see preheader:
      """
      Go ahead, splurge a little.
      """
    And i should see bloomingdales logo
    #And i should see bmoney logo
    And i should see static contents in bmoney rewardcard:
      """
      Print your Promotional Gift Card and present it to a sales associate at checkout.
      """
    And i should see static contents in bmoney rewardcard:
      """
     You can key in your Promotional Gift Card number in checkout under the gift/reward card section.
      """
    And i should see "USE IT IN-STORE" label in bmoney rewardcard
    And i should see "USE IT ONLINE" label in bmoney rewardcard
    And i shoould see "YOU'VE EARNED" text in bMoney email
    And i shoould see "REDEEM IT FROM" text in bMoney email
    And i shoould see "$<amount>" text in bMoney email
    And i shoould see "<rewardCardEffectiveDate>-<rewardCardExpirationDate>" text in bMoney email
    And i shoould see "EXPIRES <rewardCardExpirationDate>" text in bMoney email
    And i shoould see "CARD #: <rewardCardNumber>" text in bMoney email
    And i shoould see "CID #: <cid>" text in bMoney email
    And i should see bMoney reward card bar code image in email
    And i should see below bMoney disclimer text in email:
      """
     This Promotional Card may be used to purchase merchandise and in-store services at any Bloomingdale's store or online at bloomingdales.com (US only). It cannot be redeemed for cash, used at Bloomingdale's Outlet Stores, used to purchase Bloomingdale's gift cards, or applied as payment or credit to your credit card account. This promotional card is not replaceable if lost or stolen. If Promotional Gift Card is not included with a return, the Promotional Gift Card value or amount used will be deducted from the amount refunded. This Promotional Gift Card is required for all sales and inquires.
      """
