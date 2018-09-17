Feature: Verify MCOM 618 mmoney rewardcard email content

  @mcom_com_618_1A_mmoney_rewardcard @regression
  Scenario: Verify Email content
    Given i trigger "mcom_com_618_1A_mmoney_rewardcard" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see preheader:
      """
      Use your Macy�s Money online and in stores. Shop Now
      """
    Then i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
    And i should see Macys logo
    And i should see mmoney order details section for 16A
    And i should see mmoney body text for 16A:
      """
      Shop with it online & in stores from <rewardCardEffectiveDate>-<rewardCardExpirationDate> on our best brandsincluding cosmetics & fragrances! You can even use it on top of coupons & sale prices!
      """
    And i should see reward card earn end date for mmomney:
      """
      The more you buy, the more you get! Shop now through <rewardCardEarnEndDate> to get even more Macy's Money. exclusions & details
      """
    And i should see one "Check It Out" button
    And i should see one "Check Order Status" button

  @optional
  Scenario: Verify optional fields empty scenario for template mcom_com_618_1A_mmoney_rewardcard
    Given i trigger "mcom_com_618_1A_mmoney_rewardcard_optional" input through csp testemail
    When i navigate to view the email page
    Then i should see Macys logo
