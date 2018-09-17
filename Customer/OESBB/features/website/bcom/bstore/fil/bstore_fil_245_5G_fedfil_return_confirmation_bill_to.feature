Feature: Verify BSTORE 245 fedfil return confirmation bill to email content

  @regression @bstore_fil_245_5G_fedfil_return_confirmation_bill_to
  Scenario: Verify Email content for template bstore_fil_245_5G_fedfil_return_confirmation_bill_to
    Given i trigger "bstore_fil_245_5G_fedfil_return_confirmation_bill_to" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    And i should see freeshipping image
    And i should see billing first name
    And i should see body text for return confirmation bill to:
      """
      We have issued a credit. Please allow 24-72 hours for the credit to appear on your account. We apologize for any inconvenience.
      """
    And i should see header:
      """
      YOUR RETURN HAS BEEN SUBMITTED
      """
    And i should see order number
    And i should see order capture date
    And i should see "Reduction due to insufficient points" text
    And i should see "Reason for reduction" text
    And i should see reason for reduction message:
      """
      If you returned an item used to accumulate Loyallist Points, your points balance will be reduced accordingly. If you do not have enough points to cover the adjustment, your Loyallist Reward Card balance will be reduced. If your Loyallist Reward Card balance is insufficient, your refund will be reduced.
      """
    And i should see reason for return
    And i should see estimated refund amount as order total
    And i should see refunded to card info
    And i should see "Estimated refund" text
    And i should see "Reason for return" text
    And i should see "ITEM DETAILS" text
    And i should see product details
    And i should see product requested quantity
