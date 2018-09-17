Feature: Verify MCOM 621 subscription welcome email content

  @regression
  Scenario: Verify Email content for template mcom_com_621_1C_subscriptionemail
    Given i trigger "mcom_com_621_1C_subscription_welcome_email" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see Macys logo
    And i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
    #And i should see pre header:
     # """
     # Subscribe now-before it's too late! Subscribe Now
      #"""
   # And i should see body text:
      #"""
      #Hi Billfirst, Macy's Beauty Box spots are now available! But hurry-availability won't last long. Thanks for waiting so patiently to join Beauty Box. We can't wait to send you your first box!
     # """
    And i should see one "SUBSCRIBE NOW" button