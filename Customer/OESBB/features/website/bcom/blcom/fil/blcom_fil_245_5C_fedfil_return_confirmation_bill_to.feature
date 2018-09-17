Feature: Verify BCOM 245 fedfil return confirmation bill to email content

  @regression @blcom_fil_245_5C_fedfil_return_confirmation_bill_to
  Scenario: Verify Email content for template blcom_fil_245_5C_fedfil_return_confirmation_bill_to
    Given i trigger "blcom_fil_245_5C_fedfil_return_confirmation_bill_to" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see bloomingdales logo
    And i should see freeshipping image
    And i should see billing first name
    And i should see body text for return confirmation bill to:
      """
      We received your request for a refund for the damaged merchandise listed below. A refund will be issued to you in the amount of $<orderTotals.orderTotal>. If you used a Gift Card or REWARD CARD or received the item(s) as a gift, a new card will be issued to you shortly.
      """
    And i should see preheader:
      """
      We apologize for any inconvenience. Contact us
      """
    And i should see header:
      """
      YOUR REFUND HAS BEEN PROCESSED
      """
    And i should see order number
    And i should see order capture date
    And i should see "Reduction due to insufficient points" text
    And i should see "Reason for reduction" text
    And i should see reason for reduction message:
      """
      If you returned an item used to accumulate Loyallist Points, your points balance will be reduced accordingly. If you do not have enough points to cover the adjustment, your Loyallist Reward Card balance will be reduced. If your Loyallist Reward Card balance is insufficient, your refund will be reduced.
      """
    And i should see refund total
    And i should see refunded to card info
    And i should see "ITEM DETAILS" text
    And i should see product details

  @optional
  Scenario: Verify optional fields empty scenario for template blcom_fil_245_5C_fedfil_return_confirmation_bill_to
    Given i trigger "blcom_fil_245_5C_fedfil_return_confirmation_bill_to_optional" input through csp testemail
    When i navigate to view the email page
    Then i should see bloomingdales logo
