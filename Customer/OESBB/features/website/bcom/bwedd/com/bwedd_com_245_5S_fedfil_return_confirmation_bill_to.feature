Feature: Verify BWEDD 245 fedfil return confirmation bill to email content

  @regression @bwedd_com_245_5S_fedfil_return_confirmation_bill_to
  Scenario: Verify Email content for template bwedd_com_245_5S_fedfil_return_confirmation_bill_to
    Given i trigger "bwedd_com_245_5S_fedfil_return_confirmation_bill_to" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see header:
      """
      YOUR RETURN IS IN TRANSIT
      """
    And i should see shipment firstname
    And i should see bloomingdales registry logo
    And i should see freeshipping image
    And i should see order number
    And i should see body text for return confirmation bill to:
      """
      We've been notified by the carrier that the item you want replaced is on its way to us. You can track its progress here. Please allow 5-7 business days for your return to arrive at our facility. We'll confirm receipt once we receive it.
      """
    And i should see order capture date
    And i should see original payment amount
    And i should see reason for replacement label and value
    And i should see refunded to card info
    And i should see "ITEM DETAILS" text
    And i should see product details
    And i should see product requested quantity
