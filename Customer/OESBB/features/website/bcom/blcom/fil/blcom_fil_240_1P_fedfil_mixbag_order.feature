Feature: Verify fedfil mixbag order email content

  @blcom_fil_240_1P_fedfil_mixbag_order @regression
  Scenario: Verify email content for blcom 240 1P mixbag order template
    Given i trigger "blcom_fil_240_1P_fedfil_mixbag_order" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see bloomingdales logo
    And i should see freeshipping image
    And i should see preheader:
      """
      A separate email will confirm when your order is ready for pickup.
      """
    And i should see header:
      """
      LOOK FOR AN EMAIL CONFIRMING YOUR PICKUP IS READY
      """
    And i should see static text:
      """
      Please wait to come to the store until you've received a confirmation email that your order is ready to be picked up.
      """
    And i should see static contents:
      """
      As soon as your Buy Online, Pick Up In Store order is available, we will send you an email with details on when and where you can pick it up. You can view your order status and history at any time by visiting <link>My Account<link>.
      """
    And i should see customer care static text
      """
      Call Customer Service at 1-800-777-0000 with questions about your order.
      """
    And i should see estimated pick up date
    And i should see order number
    And i should see order capture date
    And i should see billing address
    And i should see "IN-STORE PICKUP INFORMATION" text
    And i should see sms opted message text
    And i should see pickup instore items
    And i should see shipment phone number
    And i should see "ITEM DETAILS" text
    And i should see product details
    And i should see order details
    And i should see "In-Store Pickup" text
    And i should see "PAYMENT METHOD" text
    And i should see payment card info
    And i should see pick up location:
      """
      Pickup location: <storename> <shipmentsstoreaddrline1> <shipmentsstoreaddrline2> <shipmentsstorecity> <shipmentsstorestate> <shipmentsstorezipcode> <shipmentsstorephone>
      """
    And i should see pickup person contact information:
      """
      Person Picking Up Order: <shipmentsfirstname> <shipmentslastname> <shipmentsemail> <shipmentsphone>
      """
    And i should see gift receipt option in fedfil mixbag order:
      """
      Gift receipt: Yes
      """
    And i should see gift box option in fedfil mixbag order:
      """
      Gift Box: Yes
      """
    And i should see gift message
    And i should verify shipment details
    And i should see "ITEM DETAILS" text
    And i should see shipping method
    And i should see loyalty section in the email
    And i should see the mentioned text in the email
      | NOTE: Your point balance will be updated once your order ships. Purchase amounts paid with a Gift Card or REWARD CARD do not receive Loyallist points. |
      
      
      
    @optional
    Scenario: Verify optional fields empty scenario for template blcom_fil_240_1P_fedfil_mixbag_order_optional
    Given i trigger "blcom_fil_240_1P_fedfil_mixbag_order_optional" input through csp testemail
    When i navigate to view the email page
    And i should see bloomingdales logo
      
