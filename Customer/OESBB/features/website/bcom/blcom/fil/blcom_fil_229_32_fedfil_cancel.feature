Feature: Verify BCOM fedfil cancel email content

  @blcom_fil_229_32_fedfil_cancel @regression
  Scenario: Verify email content for template blcom_fil_229_32_fedfil_cancel
    Given i trigger "blcom_fil_229_32_fedfil_cancel" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    And i should see preheader:
      """
      A credit or refund will be issued.
      """
    And i should see bloomingdales logo
    And i should see freeshipping image
    Then i should see header:
      """
      THIS ITEM IS NO LONGER AVAILABLE
      """
    And i should see firstname
    Then i should see static contents:
      """
      We received your exchange request for the item below; however, unfortunately, it's no longer available. A credit or refund will be issued to the card you used. If you paid with a Gift Card or Loyallist Reward Card, a new Gift Card will be issued to you shortly.
      """
    And i should see "ORDER DETAILS" text
    And i should see order number
    And i should see order capture date
    And i should see refund amount
    And i should see refunded to card info
    And i should see "ITEM DETAILS" text
    And i should see shop now button
    And i should see product details
    And i should see product cancelled quantity
    Then i should see static contents for com template:
      """
      Thank you,
      Bloomingdale's Customer Service
      Call us at any time:1-800-777-0000
      We're available 24hours, 7 days a week.
      customerservice@bloomingdales.com
      """

  @optional
  Scenario: Verify optional fields empty scenario for template blcom_fil_229_32_fedfil_cancel
    Given i trigger "blcom_fil_229_32_fedfil_cancel_optional" input through csp testemail
    When i navigate to view the email page
    And i should see bloomingdales logo
