Feature: Verify MCOM 609 reset confirm email content

  @regression
  @mcom_com_609_1B_reset_confirm
  @mcom_com_609
  Scenario: Verify Email content for template mcom_com_609_1B_reset_confirm
    Given i trigger "mcom_com_609_1B_reset_confirm" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      Your password has been changed. My Account
      """
    Then i should see static contents:
      """
      Your password was reset	
      You're all set! Check out what's new at Macy's. 
      """
    And i should see Macys logo
    And i should see button as "shop macy's"
