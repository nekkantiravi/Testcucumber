Feature: Verify MCOM 650 offer share COM 650 1A MCOM  email content

  @mcom_com_650_1A_offer_share @regression
  Scenario: Verify Email content for for template mcom_com_650_1A_offer_share
    Given i trigger "mcom_com_650_1A_offer_share" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      Check out more Macy’s deals! Shop now
      """
    And i should see body text for offer share:
      """
      <REPLY_TO_DISPLAY_NAME> found a great deal at Macy's and thought you'd like to check it out. <promoPersonalMessage>
      """
    Then i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
    And i should see Macys logo
    And i should see promo headings for offer share
    And i should see promo description and promo sub description for offer share
    And i should see promo code and promo expiry date for offer share
    And i should see the "SHOP MACY'S" button
    And i should see button as "GET SAVINGS PASS"
    And i should see static text below shop macys button for offer share:
      """
      See all the Latest Deals now!
      """
    And i should see promo legal disclaimer for offer share

  @optional
  Scenario: Verify optional fields empty scenario for template mcom_com_650_1A_offer_share
    Given i trigger "mcom_com_650_1A_offer_share_optional" input through csp testemail
    When i navigate to view the email page
    Then i should see Macys logo
