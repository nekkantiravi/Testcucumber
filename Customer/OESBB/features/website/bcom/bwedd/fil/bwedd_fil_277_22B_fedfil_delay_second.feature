Feature: Verify BWEDD 277 fedfil delay second email content

  @regression @bwedd_fil_277_22B_fedfil_delay_second
  Scenario: Verify email content for template bwedd_fil_277_22B_fedfil_delay_second
    Given i trigger "bwedd_fil_277_22B_fedfil_delay_second" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    #Preheader not displaying as expected in email.Oracle need to fix this issue.
    #And i should see preheader:
    #"""
    #SECOND DELAY NOTICE: We've provided an updated shipping date.
    #"""
    And i should see bloomingdales registry logo
    And i should see freeshipping image
    Then i should see header:
      """
      AN ITEM FROM YOUR REGISTRY ORDER IS DELAYED
      """
    # First name is not displaying in the email. Oracle Responsys need to fix the issue.
    #And i should see firstname
    Then i should see static contents:
      """
      An item from your order is currently out of stock and its delivery date has been delayed. A new estimated shipping date is listed below. You will not be charged until the item ships.

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
