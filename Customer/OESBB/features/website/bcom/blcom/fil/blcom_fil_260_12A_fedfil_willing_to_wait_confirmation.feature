Feature: Verify BCOM 260 willing to wait confirmation email content

  @regression @blcom_fil_260_12A_fedfil_willing_to_wait_confirmation
  Scenario: Verify Email content for template blcom_fil_260_12A_fedfil_willing_to_wait_confirmation
    Given i trigger "blcom_fil_260_12A_fedfil_willing_to_wait_confirmation" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see static contents:
      """
      We received your confirmation to proceed with the delayed items listed below. An estimated shipping date is provided below. You will not be charged until the item ships.
      """
    And i should see preheader:
      """
      We will proceed with your delayed item.
      """
    And i should see firstname
    And i should see bloomingdales logo
    And i should see freeshipping image
    And i should see "ORDER DETAILS" text
    Then i should see order number
    And i should see order capture date
    And i should see expected date as estimated ship date
    And i should see customer care static text
      """
      To cancel your order before it ships, contact Customer Service at 1-800-777-0000
      """
    And i should see "ITEM DETAILS" text
    And i should see product details

  @optional
  Scenario: Verify optional fields empty scenario for template blcom_fil_260_12A_fedfil_willing_to_wait_confirmation
    Given i trigger "blcom_fil_260_12A_fedfil_willing_to_wait_confirmation_optional" input through csp testemail
    When i navigate to view the email page
    And i should see bloomingdales logo
