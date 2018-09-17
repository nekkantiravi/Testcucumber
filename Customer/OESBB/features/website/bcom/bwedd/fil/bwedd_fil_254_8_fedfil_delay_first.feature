Feature: Verify BWEDD 254 fedfil delay first email content

  @regression
  @bwedd_fil_254_8_fedfil_delay_first
  Scenario: Verify Email content for template bwedd_fil_254_8_fedfil_delay_first
    Given i trigger "bwedd_fil_254_8_fedfil_delay_first" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    And i should see preheader:
      """
      Important information about your order.
      """
    And i should see bloomingdales registry logo
    And i should see freeshipping image
    Then i should see header:
      """
      AN ITEM FROM YOUR REGISTRY ORDER IS DELAYED
      """
    And i should see registrant firstname
    Then i should see static contents for 254_8 fedfil delay first:
      """
      An item from your Registry purchased by <billingAddr.firstName> <billingAddr.lastName> is currently out of stock and its delivery date has been delayed. A new estimated shipping date is listed below.
      """
    And i should see "ORDER DETAILS" text
    And i should see order number
    And i should see order capture date
    And i should see estimated ship date
    And i should see "ITEM DETAILS" text
    And i should see product details
    And i should see shop now button
    Then i should see static contents for com template:
      """
      Thank you,
      Bloomingdale's Customer Service
      Call us at any time:1-800-777-0000
      We're available 24hours, 7 days a week.
      customerservice@bloomingdales.com
      """
    And i should see "CUSTOMER SERVICE" link