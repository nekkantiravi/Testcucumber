Feature: Verify BWEDD 233 1A  email content

  @bwedd_fil_233_1A_payment_auth_failure @regression @bwedd_fil_233
  Scenario: Verify Email content for for template blcom_fil_233_1A_payment_auth_failure
    Given i trigger "bwedd_fil_233_1A_payment_auth_failure" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    And i should see preheader:
      """
      Please update your payment information
      """
    #And i should see body text with order cancel time:
      #"""      
#				Please update your payment information through the link below. We'll hold your order until <orderCanceldate> <orderCanceltime> ET. If we don't hear from you by that time, we'll have to cancel the order.  
#				
#				For assistance, please call our Customer Service team at 1-800-777-0000 We’re available 24 hours a day, 7 days a week.
      #"""
    Then i should see order number
    And i should see order capture date for 233 payment_auth_failure template
    Then i should see firstname
    And i should see firstname
    And i should see bloomingdales registry logo
    #Free shipping logo not displaying in email.it's a defect.so commented below step
    #And i should see freeshipping image
    And i should see "ORDER DETAILS" text
    Then i should see order number
    And i should see "ITEM DETAILS" text
    And i should see product details
    And i should see "PAYMENT METHOD" text
    And i should see payment card info for 233 payment_auth_failure template
