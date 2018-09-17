Feature: Verify MCOM 607 profile positive email content

  @regression
  @mcom_com_607_1B_profile_positive
  @mcom_com_607
  Scenario: Verify Email content for template mcom_com_607_1B_profile_positive
    Given i trigger "mcom_com_607_1B_profile_positive" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      This offer is already in your wallet. Check It Out
      """
    #Then i should see static contents:
     #"""
    #Thanks for creating a Macy's account
    #"""
    And i should see Macys logo
    #And i should see button as "CHECK IT OUT"
    Then i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
     @optional
   Scenario: Verify optional fields empty scenario for template mcom_com_607_1B_profile_positive
    Given i trigger "mcom_com_607_1B_profile_positive_optional" input through csp testemail
    When i navigate to view the email page
    Then i should see Macys logo
    
   @production
   Scenario: Production : Verify email template for mcom_com_607_1B_profile_positive
    Given i trigger "mcom_com_607_1B_profile_positive" input through csp testemail
    When i navigate to view the email page
    Then i should see Macys logo