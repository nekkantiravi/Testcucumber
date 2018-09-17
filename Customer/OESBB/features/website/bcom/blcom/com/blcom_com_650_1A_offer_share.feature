Feature: Verify BCOM 650 offer share email content

  @blcom_com_650_1A_offer_share @regression
  Scenario: Verify Email content for template blcom_com_650_1A_offer_share
    Given i trigger "blcom_com_650_1A_offer_share" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    And i should see bloomingdales logo
    And i should see freeshipping image
    And i should see preheader:
      """
      Look what I found at bloomingdales.com
      """
    And i should see body text for offer share:
      """
      <REPLY_TO_DISPLAY_NAME> wanted to share this offer with you from Bloomingdale's. <promoPersonalMessage>
      """
    And i should see promo heading for offer share
    And i should see promo description for offer share
    And i should see button as "SHOP NOW"
    And i should see button as "VIEW THIS OFFER"
    
    
    @optional
    Scenario: Verify optional fields empty scenario for template blcom_com_650_1A_offer_share_optional
    Given i trigger "blcom_com_650_1A_offer_share_optional" input through csp testemail
    When i navigate to view the email page
    And i should see freeshipping image
 