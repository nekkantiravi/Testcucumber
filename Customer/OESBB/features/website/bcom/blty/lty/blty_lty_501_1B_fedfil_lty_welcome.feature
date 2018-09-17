Feature: Verify BLTY Welcom email content

  @regression @blty_lty_501_1B_lty_welcome @blty_lty_501
  Scenario: Verify Email content for template blty_lty_501_1B_lty_welcome
    Given i trigger "blty_lty_501_1B_lty_welcome" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    And i should see preheader:
      """
      Now you'll get points every time you shop.
      """
    And i should see bloomingdales logo
    And i should see loyallist free shipping logo
    And i should see loyaltyId
    And i should see loyalty firstName

  @optional
  Scenario: Verify optional fields empty scenario for template blty_lty_501_1B_lty_welcome
    Given i trigger "blty_lty_501_1B_lty_welcome_optional" input through csp testemail
    And i wait for enhanced payload to be sent to email provider
    When i navigate to view the email page
    Then i should see bloomingdales logo
    
    
  @production
  Scenario: Production : Verify email template for blty_lty_501_1B_lty_welcome
    Given i trigger "blty_lty_501_1B_lty_welcome" input through csp testemail
    And i wait for enhanced payload to be sent to email provider
    When i navigate to view the email page
    Then i should see bloomingdales logo
