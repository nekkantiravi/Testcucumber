Feature: Verify BCOM profile reset confirmation Email content

  @regression
  @blcom_com_601_1B_profileresetconfirmation
  Scenario: Verify email content for template blcom_com_601_1B_profileresetconfirmation
    Given i trigger "blcom_com_601_1B_profileresetconfirmation" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see bloomingdales logo
    And i should see freeshipping image
    And i should see preheader:
      """
      Important information about your account.
      """
      Then i should see static contents:
      """
      YOU SUCCESSFULLY UPDATED YOUR SECURITY QUESTION
	  You're all set! Thanks for changing your security question and helping us make your account more secure. If you did not recently change it, or believe that an unauthorized person has accessed your account, please <link>contact us<link> immediately. 
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