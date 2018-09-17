Feature: Verify MCOM 621 subscription notification email content

  @regression
  Scenario: Verify Email content for template mcom_com_621_1H_subscriptionnotificationemail
    Given i trigger "mcom_com_621_1H_subscription_notification_email" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see Macys logo
    And i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
    And i should see pre header:
      """
     Don't worry, you haven't been charged. Re-Subscribe
      """
    And i should see body text:
    """
    Hi Billfirst, We reached out earlier to let you know about an issue with your account information. Because it wasn't updated within the 10-day time frame we specified, your Macy's Beauty Box subscription has been canceled. We're sorry to see you go!
    """
    And i should see one "SHOP BEAUTY" button