Feature: Verify BCOM 245 fedfil return confirmation bill to email content

  @regression
  @blcom_com_245_5A_fedfil_return_confirmation_bill_to
  Scenario: Verify Email content for template blcom_com_245_5A_fedfil_return_confirmation_bill_to
    Given i trigger "blcom_com_245_5A_fedfil_return_confirmation_bill_to" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    And i should see body text for return confirmation bill to:
      """
      We've received your request to return the merchandise listed below. Please follow the delivery instructions on the return confirmation to correctly package, label and ship your return. Need another shipping label? You can reprint your return confirmation and shipping label online. If you used a Gift Card or Reward Card, or received the item(s) as a gift, a new card may be issued.
      """
    And i should see header:
      """
      YOUR RETURN HAS BEEN SUBMITTED
      """
    And i should see shipment firstname
    And i should see bloomingdales logo
    And i should see freeshipping image
    Then i should see order number
    And i should see return submitted date
    And i should see expected refund amount
    And i should see refunded to card info
    And i should see "ITEM DETAILS" text
    And i should see product details
    And i should see product requested quantity
    
   
  @production
  Scenario: Production : Verify email template for blcom_com_245_5A_fedfil_return_confirmation_bill_to
    Given i trigger "blcom_com_245_5A_fedfil_return_confirmation_bill_to" input through csp testemail
    When i navigate to view the email page
    Then i should see bloomingdales logo
