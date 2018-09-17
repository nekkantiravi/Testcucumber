Feature: Verify MCOM 621 subscription welcome email content

  @regression
  Scenario: Verify Email content for template mcom_com_621_1A_subscriptionemail
    Given i trigger "mcom_com_621_1A_subscription_welcome_email" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see Macys logo
    And i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
    And i should see pre header:
      """
      Thanks for subscribing! Get excited for your first box. Find Out More
      """
    #And i should see body text:
      #"""
      #Hi Billfirst, You're in! Thank you for subscribing to Macy's Beauty Box. We can't wait to send out your first box of gorgeous treats! Here's how it works: between the first and 10th of each month, we'll charge $15, plus applicable taxes, to the payment method you provided. Then, we'll send the box to your shipping address. Look out for an email letting you know the box has shipped! Please verify your shipping and payment details are correct:
      #"""
      And i should see beauty box static content:
      """
      Ready to start exploring Macy's Beauty Box? Learn more about your monthly subscription to gorgeous treats!
      """
      
    #And i should see shipping address
    And i should see one "Check It Out" button