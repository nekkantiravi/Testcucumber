Feature: Verify MCOM 621 subscription notification email content

 @mcom_com_621_1D_subscription_notification_email @regression
  Scenario: Verify Email content for template mcom_com_621_1D_subscriptionnotificationemail
    Given i trigger "mcom_com_621_1D_subscription_notification_email" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see Macys logo
    And i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
    And i should see pre header:
      """
     We're sorry to see you go! Re-Subscribe
      """
#    And i should see body text:
#      """
#     hi billfirst, your macy's beauty box subscription has been canceled. we're sorry you are no longer part of the program! didn't mean for your subscription to be canceled? no worries! you can re-subscribe at any time. keep in mind-spots fill up quickly! you may have to join the waitlist until more spots open up. not interested? no problem! you can still get inspired: check out all the beauty finds at macy's.
		
#     """
# getting the special symbol in bodText
    And i should see one "SHOP BEAUTY" button