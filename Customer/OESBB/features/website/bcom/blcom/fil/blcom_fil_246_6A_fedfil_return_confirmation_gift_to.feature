Feature: Verify BLCOM 246 fedfil return confirmation gift to email content

  @regression @blcom_fil_246_6A_fedfil_return_confirmation_gift_to
  Scenario: Verify Email content for template blcom_fil_246_6_fedfil_return_confirmation_gift_to
    Given i trigger "blcom_fil_246_6A_fedfil_return_confirmation_gift_to" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see body text for return confirmation gift to:
      """
      We received your request for a refund for the damaged items listed below. A refund will be issued to you in the amount of <chargeAmt>. If you used a Gift Card or Reward Card, or received the items as a gift, a new Gift Card will be issued to you shortly.
      """
    And i should see preheader:
      """
      You will be credited soon.
      """
    And i should see firstname
    And i should see bloomingdales logo
    And i should see freeshipping image
    And i should see "ORDER DETAILS" text
    Then i should see order number
    And i should see order capture date
    And i should see "Reduction due to insufficient points" text
    And i should see "Reason for reduction" text
    And i should see reason for reduction message:
      """
      If you returned an item used to accumulate Loyallist Points, your points balance will be reduced accordingly. If you do not have enough points to cover the adjustment, your Loyallist Reward Card balance will be reduced. If your Loyallist Reward Card balance is insufficient, your refund will be reduced.
      """
    And i should see estimated refund
    And i should see refunded to card info
    And i should see "ITEM DETAILS" text
    And i should see product details

  @optional
  Scenario: Verify optional fields empty scenario for template blcom_fil_245_5C_fedfil_return_confirmation_bill_to
    Given i trigger "blcom_fil_246_6A_fedfil_return_confirmation_gift_to_optional" input through csp testemail
    When i navigate to view the email page
    Then i should see bloomingdales logo
