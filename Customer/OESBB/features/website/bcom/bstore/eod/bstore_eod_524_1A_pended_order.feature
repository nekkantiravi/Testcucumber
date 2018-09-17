Feature: Verify BSTORE 524 Pended order notification email content

@regression @bstore_eod_524_1A_pended_order
  Scenario: Verify Email content for template bstore_eod_524_1A_pended_order notification
    Given i trigger "bstore_eod_524_1A_pended_order" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see preheader:
      """
     	Your order can't be processed.
      """
      And i should see header:
      """
      WE NEED TO SPEAK WITH YOU
      """
      And i should see firstname
      And i should see bloomingdales logo
      Then i should see static contents:
      """		
      We've been trying to contact you. Your recent order can't be processed and we need to speak with you. To ensure that your order is not canceled, please listen to the message we left at the phone number ending in 2312 and then call our Consumer Protection department at 1-800-537-0197 (Monday-Saturday 9AM-10PM, Sunday 9AM-8PM ET).	Note: We need to hear from you by 11:28AM on 08/10/2017. When calling, please have the reservation number (left in the voicemail) available.	Do not respond to this message.	
      """ 
     Then i should see signatue contents for gpgm template:
      """      
			Thank you, Bloomingdale's Customer Service Call us at any time: 1-800-777-0000 We're available 24hours, 7 days a week. customerservice@bloomingdales.com 
       
      """