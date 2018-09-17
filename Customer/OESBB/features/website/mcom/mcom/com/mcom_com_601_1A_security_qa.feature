Feature: Verify MCOM 601 security qa email content

  @regression
  @mcom_com_601_1A_security_qa
  @mcom_com_601
  Scenario: Verify Email content for template mcom_com_601_1A_security_qa
    Given i trigger "mcom_com_601_1A_security_qa" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      We've changed your security question. My Account
      """
    Then i should see static contents:
      """
      Your password was reset	
      You're all set! Thanks for changing your security question and helping us keep your account secure. 
      """
    And i should see Macys logo
    And i should see button as "shop macy's"
