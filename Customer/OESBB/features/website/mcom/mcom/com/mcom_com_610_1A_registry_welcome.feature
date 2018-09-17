Feature: Verify MCOM 610 registry welcome email content

 @mcom_com_610_1A_registry_welcome @regression
  Scenario: Verify Email content for template mcom_com_610_1A_registry_welcome
    Given i trigger "mcom_com_610_1A_registry_welcome" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      We're here to help. Visit Registry
      """
    Then i should see static contents:
      """
     Welcome to the beginning of something wonderful! 
     	
     Creating your registry is exciting and we're here to help every step of the way. Start adding gifts with tips from our useful Registry Guide and get inspiration on bridal looks in our Wedding Shop. We'll check in with great deals and big ideas, so stay tuned!
      """
    And i should see Macys logo
    And i should see button as "view my registry"

    
     @optional
   Scenario: Verify optional fields empty scenario for template mcom_com_610_1A_registry_welcome
    Given i trigger "mcom_com_610_1A_registry_welcome_optional" input through csp testemail
    When i navigate to view the email page
    Then i should see Macys logo