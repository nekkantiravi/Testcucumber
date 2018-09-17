Feature: Verify BCOM 245 virtual return confirmation email content

  @regression @blcom_fil_245_5F_fedfil_virtual_return_confirmation
  Scenario: Verify Email content for template blcom_fil_245_5F_fedfil_virtual_return_confirmation
    Given i trigger "blcom_fil_245_5F_fedfil_virtual_return_confirmation" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see body text for virtual return confirmation:
      """
      we have issued a credit for the item(s) you received. please allow 24-72 hours for the credit to appear on your account. we apologize for any inconvenience. item(s) that need to be returned are listed below with "please return this item" in the details. please return the item(s) by mail or return in store by <returnExpectedDate>. if we don't receive your return by <returnExpectedDate>, we reserve the right to charge your card for the item(s) and any associated charges incurred at the time of purchase, estimated at $<orderTotal>.

      """
     And i should see ups  content in body text
     """
     Returning by UPS? Print your free UPS label Returning to a store? Find you nearest Bloomingdale's
     """
     And i should see header:
      """
      YOUR CREDIT HAS BEEN PROCESSED
      """
    And i should see firstname for return confirmation
    And i should see bloomingdales logo
    And i should see freeshipping image
    And i should see "UPDATED ORDERED DETAILS" text
    Then i should see order number
    And i should see order capture date
    And i should see estimated refund for virtual return confirmation template
    And i should see reason for return
    And i should see refunded to card info
    And i should see "ITEM DETAILS" text
    And i should see product details

    @optional
  Scenario: Verify optional fields empty scenario for template blcom_fil_245_5F_fedfil_virtual_return_confirmation
    Given i trigger "blcom_fil_245_5F_fedfil_virtual_return_confirmation_optional" input through csp testemail
    When i navigate to view the email page
    Then i should see bloomingdales logo