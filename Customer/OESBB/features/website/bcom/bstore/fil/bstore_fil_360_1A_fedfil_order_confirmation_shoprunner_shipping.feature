Feature: Verify fedfil order confirmation templates

  @bstore_fil_360_1A_fedfil_order_confirmation_shoprunner_shipping @regression @bstore_fil_360
  Scenario: Verify email content for template bstore_fil_360_1A_fedfil_order_confirmation_shoprunner_shipping
    Given i trigger "bstore_fil_360_1A_fedfil_order_confirmation_shoprunner_shipping" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see static contents:
      """
      Your replacement has been processed and will soon be on its way. You will not be charged for shipping. However, we do not refund shipping charges, unless we made an error.
      """
    And i should see preheader:
      """
      Your replacement has been processed
      """
    And i should see firstname
    And i should see bloomingdales logo
    And i should see freeshipping image
    And i should see "ORDER DETAILS" text
    And i should see order number
    And i should see order capture date
    And i should see billing address
    And i should see "CHECK ORDER STATUS" button
    And i should see note text as:
      """
      Note: Changes to your order must be made by phone. Call Customer Service within 30 minutes of when your order was submitted at <link>1-800-777-0000<link>. <link>Learn more<link>
      """
    And i should see "SHIPMENT DETAILS" text
    And i should see shoprunner shipping delivery method
    And i should verify shipment details
    And i should see "ITEM DETAILS" text
    And i should see product details