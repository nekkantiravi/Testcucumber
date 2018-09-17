Feature: Verify MCOM 618 ship address change notification email content

  @regression
  Scenario: Verify Email content for template mcom_com_617_1B_ship_address_change_notification
    Given i trigger "mcom_com_617_1B_ship_address_change_notification" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see Macys logo
    And i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
    And i should see pre header:
      """
      We received your shipping address edits. My Account
      """
    And i should see static content for ship address change notification as:
      """
      Your shipping address was updated We received the changes to your shipping address. You’re all set! If you didn’t request this change, please <link>reset your password<link> immediately or contact us at 1-800-BUY-MACY (289-6229).
      """
