Feature: Verify BLTY LTY vrc email content

  @blty_lty_500_1B_lty_vrc @regression
  Scenario: Verify Email content for template blty_lty_500_1B_lty_vrc
    Given i trigger "blty_lty_500_1B_lty_vrc" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    And i should see preheader:
      """
      Redeem it today online or in store.
      """
    And i should see bloomingdales logo
    And i should see loyallist free shipping logo
    And i should see loyalty reward card amount
    And i should see reward card number:
      """
      Reward Card Number: <loyaltyrewardcardnumber>
      """
    And i should see expire date:
      """
      Expires: <expiredate>
      """
    And i should see button as "sign in & shop"
