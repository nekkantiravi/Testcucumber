Feature: Verify BCOM 278 fedfil delay second email content

  @regression @bwedd_fil_251_9_fedfil_delay_second
  Scenario: Verify email content for template blcom_fil_251_9_fedfil_delay_second
    Given i trigger "bwedd_fil_251_9_fedfil_delay_second" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    And i should see preheader:
      """
      SECOND DELAY NOTICE: Please note the new shipping date.
      """
    And i should see bloomingdales registry logo
    And i should see freeshipping image
    Then i should see header:
      """
      A GIFT PURCHASED FROM YOUR REGISTRY IS ON BACKORDER
      """
    And i should see registrant firstname
    Then i should see body text for fedfil delay second:
      """
      The following gift purchased from your registry by <billingAddr.firstName> <billingAddr.lastName> is on backorder and we are awaiting new stock. An updated estimated shipping date is below. You will not be charged until the item ships.
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
