Feature: Verify BCOM 600 profile welcome Email content

  @regression
  @blcom_com_600_1D_profile_welcome
  Scenario: Verify email content for template blcom_com_600_1D_profile_welcome
    Given i trigger "blcom_com_600_1D_profile_welcome" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see bloomingdales logo
    And i should see freeshipping image
    And i should see preheader:
      """
      Thank you for creating an account.
      """
    Then i should see static contents for com template:
      """
      Thank you, 
	  Bloomingdale's Customer Service 
	   Call us at any time: 1-800-777-0000 

	   We're available 24hours, 7 days a week. 
	   customerservice@bloomingdales.com 
      """
      And i should see "CUSTOMER SERVICE" link