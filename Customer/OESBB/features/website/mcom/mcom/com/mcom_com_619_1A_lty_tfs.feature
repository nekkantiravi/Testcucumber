Feature: Verify MCOM 619 LTY TFS email content

  @regression @mcom_com_619_1A_lty_tfs
  Scenario: Verify Email content for template mcom_com_619_1A_lty_tfs
    Given i trigger "mcom_com_619_1A_lty_tfs" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      Start shopping to get rewards today. Shop Now
      """
    Then i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
    And i should see Macys logo

  
  
  
  
  @optional
  Scenario: Verify optional fields empty scenario for template mcom_com_619_1A_lty_tfs
    Given i trigger "mcom_com_619_1A_lty_tfs_optional" input through csp testemail
    When i navigate to view the email page
    Then i should see Macys logo