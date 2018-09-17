Feature: Verify BWEDD 276 fedfil delay first email content

  @regression
  @bwedd_fil_276_21E_fedfil_delay_first
  Scenario: Verify email content for template bwedd_fil_276_21E_fedfil_delay_first
    Given i trigger "bwedd_fil_276_21E_fedfil_delay_first" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    And i should see preheader:
      """
      Important information about your registry order.
      """
    And i should see bloomingdales registry logo
    And i should see freeshipping image
    Then i should see header:
      """
      WE NEED YOUR PERMISSION TO PROCEED WITH THIS REGISTRY ORDER
      """
    And i should see firstname
    Then i should see static contents for fedfil delay first:
      """
      an item from your registry order is currently out of stock and its delivery date has been delayed. a new estimated shipping date is listed below. you will not be charged until the item ships.action requiredif you still want this item, please call customer service at 1-800-777-0000 and follow the automated prompts. if we do not hear from you by <systemCancelDate>, this item will be canceled and removed from your order. you may also use this phone number if you wish to cancel your order. view instructions
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