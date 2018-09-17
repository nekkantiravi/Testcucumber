Feature: Verify BSTORE 277 fedfil delay second email content

  @regression @bstore_fil_277_22A_fedfil_delay_second
  Scenario: Verify email content for template bstore_fil_277_22A_fedfil_delay_second
    Given i trigger "bstore_fil_277_22A_fedfil_delay_second" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    And i should see preheader:
      """
      SECOND DELAY NOTICE: Important information about your order.
      """
    And i should see bloomingdales logo
    And i should see freeshipping image
    Then i should see header:
      """
      AN ITEM FROM YOUR ORDER IS DELAYED
      """
    And i should see billing first name
    Then i should see static contents:
      """
      An item from your order is currently out of stock and its delivery date has been delayed. We do not yet have an estimated ship date. You will not be charged until the item ships.

      """
    And i should see the mentioned text in the email
      | You will not be charged until the item(s) ships.                                                                              |
      | You have the continuing right to cancel your order at any time before it is shipped. To do so, please call at 1-800-777-0000. |
    And i should see "ORDER DETAILS" text
    And i should see order number
    And i should see order capture date
    And i should see "ITEM DETAILS" text
    And i should see product details
