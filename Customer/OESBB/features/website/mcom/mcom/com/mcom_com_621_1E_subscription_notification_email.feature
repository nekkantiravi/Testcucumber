Feature: Verify MCOM 621 subscription notification email content

  @regression
  Scenario: Verify Email content for template mcom_com_621_1E_subscriptionnotificationemail
    Given i trigger "mcom_com_621_1E_subscription_notification_email" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see Macys logo
    And i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
    And i should see pre header:
      """
     We're out of stock & you won't be charged. Find Out More
      """
    #And i should see body text:
      #"""
      #Hi Billfirst, We're sorry! Because of high demand, your Macy's Beauty Box shipment won't be coming this month. Don't worry—your subscription is still active! You won't be charged for this month's box. We apologize for the inconvenience. In the meantime, get inspired by all our best beauty finds!
     #"""
    And i should see one "EXPLORE BEAUTY" button