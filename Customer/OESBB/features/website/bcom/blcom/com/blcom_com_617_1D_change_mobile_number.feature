Feature: Verify BCOM mobile number change email content

  @regression @blcom_com_617_1D_change_mobile_number
  Scenario: Verify Email content for template blcom_com_617_1A_email_change_notification
    Given i trigger "blcom_com_617_1D_change_mobile_number" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
   
    And i should see preheader:
      """
      Contact us if you didn't recently change your cell phone number. 
      """
      Then i should see header:
      """
      YOUR CELL PHONE NUMBER WAS UPDATED
      """
      And i should see firstname
      And i should see bloomingdales logo
      Then i should see static content for mobile number change notification as:
      """
      The cell phone number associated with your bloomingdales.com account was recently changed to <profilePhoneNumber>. if you didn't make this change, please call Customer Service at 1-800-777-0000. We're available 24 hours a day, 7 days a week.
	 	   """
      Then i should see signatue contents for gpgm template:
      """      
			Thank you, Bloomingdale's Customer Service Call us at any time: 1-800-777-0000 We're available 24hours, 7 days a week. customerservice@bloomingdales.com 
       
      """
  
    
