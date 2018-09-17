Feature: Verify MCOM 621 subscription welcome email content

  @regression
  Scenario: Verify Email content for template mcom_com_621_1B_subscriptionemail
    Given i trigger "mcom_com_621_1B_subscription_welcome_email" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see Macys logo
    And i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
    #And i should see pre header:
      #"""
      #We'll let you know as soon as spots open up. Explore Beauty
      #"""
   # And i should see body text:
      #"""
      #Hi Billfirst, Great news: you're on the waitlist for Macy's Beauty Box! We'll let you know as soon as new subscription spots open up. In the meantime, get inspired by Macy's Beauty department. Ready, set, glam!
     # """
     
    And i should see one "EXPLORE BEAUTY" button