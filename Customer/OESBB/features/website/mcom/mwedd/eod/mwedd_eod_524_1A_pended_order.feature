Feature: Verify MWEDD 524 Pended order notification email content

@regression @mwedd_eod_524_1A_pended_order
  Scenario: Verify Email content for template mwedd_eod_524_1A_pended_order notification
    Given i trigger "mwedd_eod_524_1A_pended_order" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    
     And i should see Macys logo
    Then i should see pre header:
      """
     	We can't process Macy's order# 1000000390656. My Account
      """
      Then i should see static contents:
      """	
      We've been trying to contact you. Your recent order can't be processed and we need to speak with you. To ensure that your order is not canceled, please listen to the message we left at the phone number ending in 2312.
Note: We need to hear from you by 11:28AM on 08/10/2017. To reach us, call our Consumer Protection department to resolve this issue at 1-866-282-8977 (Monday-Saturday 9AM-10PM, Sunday 9AM-8PM ET). When calling, please have your reservation number available.
Do not respond to this message.
      """
     And i should see firstname for 543 templates
    
    