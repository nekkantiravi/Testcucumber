Feature: Verify BCOM email content for fedfil_vgc_undeliverable template

  @regression @blcom_fil_242_3_fedfil_vgc_undeliverable @blcom_fil_242_3
  Scenario: Verify Email content for template blcom_fil_242_3_fedfil_vgc_undeliverable
    Given i trigger "blcom_fil_242_3_fedfil_vgc_undeliverable" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    And i should see preheader:
      """
      A credit or refund will be issued.
      """
    And i should see bloomingdales logo
    And i should see freeshipping image
    Then i should see header:
      """
      YOUR E-GIFT CARD WAS UNDELIVERABLE
      """
    Then i should see static contents:
      """
      The E-Gift Card you purchased recently could not be delivered to the recipient's email address. A refund will be issued to the card that you used.
      """
    And i should see "ORDER DETAILS" text
    And i should see order number
    And i should see order capture date
    And i should see "ITEM DETAILS" text

  @optional
  Scenario: Verify optional fields empty scenario for template blcom_fil_242_3_fedfil_vgc_undeliverable
    Given i trigger "blcom_fil_242_3_fedfil_vgc_undeliverable_optional" input through csp testemail
    When i navigate to view the email page
    And i should see bloomingdales logo
