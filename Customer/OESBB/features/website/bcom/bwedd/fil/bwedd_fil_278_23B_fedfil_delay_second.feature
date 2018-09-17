Feature: Verify BWEDD 278 fedfil delay second email content

  @regression @bwedd_fil_278_23B_fedfil_delay_second
  Scenario: Verify email content for template bwedd_fil_278_23B_fedfil_delay_second
    Given i trigger "bwedd_fil_278_23B_fedfil_delay_second" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    And i should see preheader:
      """
      SECOND DELAY NOTICE: Important information about your Registry order.
      """
    And i should see bloomingdales registry logo
    And i should see freeshipping image
    Then i should see header:
      """
      WE NEED YOUR PERMISSION TO PROCEED WITH THIS REGISTRY ORDER
      """
    # First name is not displaying in the email. Oracle Responsys need to fix the issue.
    #And i should see firstname
    Then i should see static contents:
      """
      An item from your order is currently out of stock and its delivery date has been delayed. A new estimated shipping date is listed below. You will not be charged until the item ships.ACTION REQUIREDIf you still want this item, please call Customer Service at 1-800-777-0000 and follow the automated prompts. If we do not hear from you, this item will be canceled and removed from your order. You may also use this phone number if you wish to cancel your order.
      """
    And i should see the mentioned text in the email
      | You will not be charged until the item(s) ships.                                                                              |
      | You have the continuing right to cancel your order at any time before it is shipped. To do so, please call at 1-800-777-0000. |
    And i should see "ORDER DETAILS" text
    And i should see order number
    And i should see order capture date
    And i should see estimated ship date
    And i should see "ITEM DETAILS" text
    And i should see product details
