Feature: Verify BCOM 245 fedfil return confirmation bill to email content

  @regression @blcom_com_245_5E_fedfil_return_confirmation_bill_to
  Scenario: Verify Email content for template blcom_com_245_5E_fedfil_return_confirmation_bill_to
    Given i trigger "blcom_com_245_5E_fedfil_return_confirmation_bill_to" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    # Hyphen symbol is not displaying as expected in email. Oracle need to fix it
    #Then i should see body text for return confirmation bill to:
    #"""
    #We've received a notice from the carrier that your returned purchase is on its way to us. You can track your return here. Please allow 7-10 business days for transit, plus an additional 4 business days upon receipt for a refund to be issued to your account.
    #"""
    And i should see header:
      """
      YOUR RETURN IS IN TRANSIT
      """
    And i should see order number
    And i should see shipment firstname
    And i should see bloomingdales logo
    And i should see freeshipping image
    And i should see order capture date
    And i should see expected refund amount
    And i should see refunded to card info
    And i should see "ITEM DETAILS" text
    And i should see product details
    And i should see product requested quantity
