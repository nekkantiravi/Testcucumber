Feature: Verify BCOM maf virtual return receipt email content

  @blcom_maf_245_5L_virtual_return_receipt @regression @blcom_maf_245
  Scenario: Verify email content for template blcom_maf_245_5L_virtual_return_receipt
    Given i trigger "blcom_maf_245_5L_virtual_return_receipt" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see preheader:
      """
      WE RECEIVED YOUR RETURN
      """
    And i should see static contents:
      """
      We received the item that we credited you for on 06/02/2016 and your return is complete.
      """
    And i should see firstname
    And i should see bloomingdales logo
    And i should see freeshipping image
    And i should see "UPDATED ORDERED DETAILS" text
    And i should see order number
    And i should see order capture date
    And i should see estimated refund
    And i should see reason for return for virtual return templates
    And i should see refunded to card info
    And i should see "ITEM DETAILS" text
    And i should see product details

  @optional
  Scenario: Verify optional fields empty scenario for template blcom_maf_245_5L_virtual_return_receipt
    Given i trigger "blcom_maf_245_5L_virtual_return_receipt_optional" input through csp testemail
    And i wait for enhanced payload to be sent to email provider
    When i navigate to view the email page
    Then i should see bloomingdales logo