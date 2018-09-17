Feature: Verify MCOM 621 subscription notification email content

  @regression
  Scenario: Verify Email content for template mcom_com_621_1G_subscriptionnotificationemail
    Given i trigger "mcom_com_621_1G_subscription_notification_email" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see Macys logo
    And i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
    And i should see pre header:
      """
     Update your account now to keep your subscription. Update Now
      """
    And i should see body text:
      """
      Hi Billfirst, Heads up: there's an issue with your Macy's Beauty Box shipping information. Please update your account details as soon as possible to maintain your subscription.
     """
    And i should see one "UPDATE NOW" button