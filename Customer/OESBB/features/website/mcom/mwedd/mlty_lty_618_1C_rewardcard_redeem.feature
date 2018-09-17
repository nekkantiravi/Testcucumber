Feature: Verify MCOM 618 mmoney rewardcard redeem email content

  @mlcomm_lty_618_1C_mmoney_rewardcard_redeem @regression
  Scenario: Verify Email content
    Given i trigger "mlty_lty_618_1C_rewardcard_redeem" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      Use your Macy's Money online and in stores. Shop Now
      """
    Then i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
    And i should see Macys logo
    And i should see mmoney body text for 16C:
      """
      Use it online & in stores from <rewardCardEffectiveDate>-<rewardCardExpirationDate> with promo codes, offers, Plenti points & even on top of sale prices & our best brands-including cosmetics & fragrances!
      """
    And i should see mmoney order details section for 16C
    And i should see one "Check Order Status" button
    And i shoould see "REDEEM IT FROM" text in email
    And i shoould see "<rewardCardEffectiveDate>-<rewardCardExpirationDate>" text in email
    And i shoould see "EXPIRES <rewardCardExpirationDate>" text in email
    And i shoould see "YOUR REWARD CARD NUMBER:" text in email
    And i shoould see "<rewardCardNumber>" text in email
    And i shoould see "CID NUMBER:" text in email
    And i shoould see "<cid>" text in email
    And i should see mMoney reward card bar code image in email
    And i should see below mMoney disclimer text in email:
      """
      The Macy's Money Reward Card may be used to purchase merchandise and in-store services at any Macy's store and online at macys.com (U.S. only). It may not be redeemed for cash, used to purchase Macy's gift cards or applied as payment or credit to your credit card account. When you make a purchase with this card, your receipt will show any remaining balance. Please safeguard this card. Because no proof of purchase receipt is provided with this card, it is not replaceable if lost or stolen. The bearer is responsible for its loss or theft. To check the balance of this card, see a Sales Associate. This card is required for all sales and inquiries. If a purchase used to accumulate Macy's Money is returned, Macy's reserves the right to void this Macy's Money RewardCard, reduce it's value, or offset Macy's Money spent against the return. For more information, go to macys.com/macysmoney. For customer service, call 1-800-511-2752
      """
