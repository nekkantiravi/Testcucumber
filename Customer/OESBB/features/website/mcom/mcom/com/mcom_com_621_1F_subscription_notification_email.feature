Feature: Verify MCOM 621 subscription notification email content

  @regression
  Scenario: Verify Email content for template mcom_com_621_1F_subscriptionnotificationemail
    Given i trigger "mcom_com_621_1F_subscription_notification_email" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see Macys logo
    And i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
    And i should see pre header:
      """     
     Please update your account now. Update Now
      """
    And i should see body text:
      """
      Hi Billfirst, Heads up: there's an issue with your Macy's Beauty Box billing info. Make sure to update your account information by the 10th of the month. Otherwise, your Macy's Beauty Box subscription will be canceled.
     """
    And i should see one "UPDATE NOW" button