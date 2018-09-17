Feature: Verify MCOM 620 password reset email content

  @regression
  Scenario: Verify Email content for template mcom_com_620_1A_password_reset
    Given i trigger "mcom_com_620_1A_password_reset" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    And i should see Macys logo