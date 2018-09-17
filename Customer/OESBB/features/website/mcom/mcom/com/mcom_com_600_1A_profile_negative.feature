Feature: Verify MCOM 600 profile negative  email content

  @mcom_com_600_1A_profile_negative @regression
  Scenario: Verify Email content
    Given i trigger "mcom_com_600_1A_profile_negative" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      This offer is already in your wallet. Check It Out
      """
    #Then i should see static contents:
     # """
      #Thanks for creating a Macy's account. Here's an extra-special offer, just for you! It's already saved in your wallet!
      #"""
    #Then i should see profile negative static content:
      #"""
      #MY WALLET Now that you have a Macy's account, all your savings passes & special offers-including this one-will be automatically saved in your wallet. Access them online or in store for faster checkout & more savings! CHECK IT OUT
      #"""
    And i should see Macys logo
    #And i should see button as "GET MY OFFER"
    #And i should see button as "CHECK IT OUT"
    #And i should see button as "GO NOW"    
    Then i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """

  @optional
  Scenario: Verify optional fields empty scenario for template mcom_com_600_1A_profile_negative
    Given i trigger "mcom_com_600_1A_profile_negative_optional" input through csp testemail
    When i navigate to view the email page
    Then i should see Macys logo