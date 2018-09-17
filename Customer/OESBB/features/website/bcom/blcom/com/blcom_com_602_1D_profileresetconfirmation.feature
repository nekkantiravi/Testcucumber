Feature: Verify BCOM profile reset confirmation Email content

  @regression
  @blcom_com_602_1D_profileresetconfirmation
  Scenario: Verify email content for template blcom_com_602_1D_profileresetconfirmation
    Given i trigger "blcom_com_602_1D_profileresetconfirmation" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see bloomingdales logo
    And i should see freeshipping image
    And i should see preheader:
      """
      If you did not request a change, please <link>contact us<link>.
      """
      Then i should see static contents:
      """
      YOUR PASSWORD HAS BEEN RESET
      Your bloomingdales.com password has been reset. If you did not recently change it, or believe that an unauthorized person has accessed your account, please <link>contact us<link> immediately.
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