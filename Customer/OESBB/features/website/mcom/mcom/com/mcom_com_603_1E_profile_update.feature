Feature: Verify MCOM 603 profile update email content

  @regression
  @mcom_com_603_1E_profile_update
  @mcom_com_603
  Scenario: Verify Email content for template mcom_com_603_1E_profile_update
    Given i trigger "mcom_com_603_1E_profile_update" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      You're all set! Shop Macy's
      """
    Then i should see static contents:
      """
      THANKS 
      FOR UPDATING YOUR PROFILE!
      You're all set for more great shopping. 
      """
    And i should see Macys logo
    And i should see button as "shop now"
