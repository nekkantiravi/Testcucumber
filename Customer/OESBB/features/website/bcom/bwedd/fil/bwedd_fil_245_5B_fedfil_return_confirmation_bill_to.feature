Feature: Verify BWEDD 245 fedfil return confirmation bill to email content

  @regression @bwedd_fil_245_5B_fedfil_return_confirmation_bill_to
  Scenario: Verify Email content for template bwedd_fil_245_5B_fedfil_return_confirmation_bill_to
    Given i trigger "bwedd_fil_245_5B_fedfil_return_confirmation_bill_to" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see bloomingdales registry logo
    And i should see freeshipping image
    And i should see billing first name
    And i should see body text for return confirmation bill to:
      """
      The merchandise listed below has been returned to us as undeliverable by the carrier. We have processed a return for this merchandise. A refund will be issued to you in the amount of $<orderTotals.orderTotal>. If you used a Gift Card or REWARD CARD, a new card will be issued to you shortly. If you would like to re-order this item(s), please call Customer Service at 1-800-777-0000, referencing your order number.
      """
    And i should see header:
      """
      YOUR SHIPMENT WAS RETURNED AS UNDELIVERABLE
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
