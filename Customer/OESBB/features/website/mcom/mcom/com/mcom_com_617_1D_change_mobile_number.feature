Feature: Verify MCOM 617 Mobile number change notification email content

  @regression @mcom_com_617_1D_change_mobile_number
  Scenario: Verify Email content for template mcom_com_617_1D_change_mobile_number notification
    Given i trigger "mcom_com_617_1D_change_mobile_number" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
     	Didnt change your mobile phone number? Contact Us
      """
    Then i should see static content for mobile number change notification as:
      """
     Your mobile phone number was updated We're confirming you changed your Macy's mobile phone number to: <profilePhoneNumber> If you didn't make this change, please call Customer Service at 1-800-289-6229. We're available 24 hours a day, 7 days a week. Have a question? <link>Contact Us<link>
      """
    And i should see Macys logo
    And i should see button as "shop macy's"
   
  