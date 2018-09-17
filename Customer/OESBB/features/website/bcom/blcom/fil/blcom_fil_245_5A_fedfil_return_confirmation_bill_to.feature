Feature: Verify BCOM 245 fedfil return confirmation bill to email content

  @regression @blcom_fil_245_5A_fedfil_return_confirmation_bill_to
  Scenario: Verify Email content for template blcom_fil_245_5A_fedfil_return_confirmation_bill_to
    Given i trigger "blcom_fil_245_5A_fedfil_return_confirmation_bill_to" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see bloomingdales logo
    And i should see freeshipping image
    And i should see billing first name
    And i should see body text for return confirmation bill to:
      """
      We received the returned items listed below. A refund will be issued to you in the amount of $<orderTotals.orderTotal>. If you used a Gift Card or Reward Card, or received the items as a gift, a new Gift Card will be issued to you shortly.
      """
    And i should see preheader:
      """
      We received your return.
      """
    And i should see header:
      """
      WE RECEIVED YOUR RETURN
      """
    And i should see order number
    And i should see order capture date
    And i should see "Reduction due to insufficient points" text
    And i should see "Reason for reduction" text
    And i should see reason for reduction message:
      """
      If you returned an item used to accumulate Loyallist Points, your points balance will be reduced accordingly. If you do not have enough points to cover the adjustment, your Loyallist Reward Card balance will be reduced. If your Loyallist Reward Card balance is insufficient, your refund will be reduced.
      """
    And i should see amount returned
    And i should see refunded to card info
    And i should see "ITEM DETAILS" text
    And i should see product details
    
     #Below steps are failing due to test data. So we need to perform manual tesing for below 
     #cases
    #And i should see zone name in the email
    #And i should see pros product images
    #And i should see pros product names
