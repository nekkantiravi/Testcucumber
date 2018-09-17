Feature: Verify fedfil  shipment email content

  @bwedd_fil_264_14H_fedfil_shipment_confirmation_shoprunner_shipping @regression @bwedd_fil_264
  Scenario: Verify email content for template bwedd_fil_264_14H_fedfil_shipment_confirmation_shoprunner_shipping
    Given i trigger "bwedd_fil_264_14H_fedfil_shipment_confirmation_shoprunner_shipping" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see static contents:
      """
      Good news: Your order has shipped! You can view your order status and history at any time by visiting <link>My Account<link>.
      """
    And i should see preheader:
      """
      View tracking information and order details.
      """
    And i should see firstname
    And i should see bloomingdales registry logo
    And i should see freeshipping image
    And i should see "ORDER DETAILS" text
    And i should see order number
    And i should see order capture date
    And i should see billing address
    And i should see "CHECK ORDER STATUS" button
    And i should see note text as:
      """
      NOTE: Changes to your order must be made by phone (<link>1-800-777-0000<link>), within 30 minutes of when the order was submitted. <link>Learn more<link>
      """
    And i should see "SHIPMENT DETAILS" text
    And i should see delivery method
    And i should see shipping address as:
      """
      This order will be shipped to the address we have on file for <shipments[].registryRegistrant1>  and <shipments[].registryRegistrant2>
      """
    And i should verify shipment details
    And i should see "ITEM DETAILS" text
    And i should see product details
    And i should see product name
    And i should see order details
    And i should see "PAYMENT METHOD" text
    And i should see payment card info
    And i should see "THIS ORDER IS A GIFT" text
    And i should see gift message

