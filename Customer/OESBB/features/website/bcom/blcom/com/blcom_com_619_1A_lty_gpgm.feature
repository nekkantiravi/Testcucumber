Feature: Verify BCOM loyallty campaign email content

  @regression @blcom_com_619_1A_lty_gpgm
  Scenario: Verify Email content for template blcom_com_619_1A_lty_gpgm
    Given i trigger "blcom_com_619_1A_lty_gpgm" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see bloomingdales logo    
    And i should see freeshipping image
    
    And i should see preheader:
      """
      Get ready to give back & get rewarded! shop now
      """
    And i should see signatue contents for gpgm template:
      """
      Thank you, Bloomingdale's Customer Service Call us at any time: 1-800-777-0000 We're available 24hours, 7 days a week. customerservice@bloomingdales.com
      """
