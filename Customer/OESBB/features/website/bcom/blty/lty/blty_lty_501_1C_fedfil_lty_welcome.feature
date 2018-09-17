Feature: Verify BLTY Welcom email content

  @regression
  @blty_lty_501_1C_lty_welcome
  @blty_lty_501
  Scenario: Verify Email content for template blty_lty_501_1C_lty_welcome
    Given i trigger "blty_lty_501_1C_lty_welcome" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    And i should see preheader:
      """
      Now you'll get points every time you shop.
      """
    And i should see bloomingdales logo
    And i should see loyallist free shipping logo
    And i should see loyaltyId