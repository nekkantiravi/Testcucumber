Feature: Verify MCOM 543 bops pickup reminder confirmation email content

  @regression
  Scenario: Verify Email content for for template mcom_fil_543_1A_bops_pickup_reminder
    Given i trigger "mcom_fil_543_1A_bops_pickup_reminder" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      We'll hold it until the date below.
      """
    And i should see Macys logo
    And i should see body text for bops pickup reminder:
      """
      Don't forget to pick up your order waiting at <storeName>.We'll hold it until <pickupCancelDate>.If you assigned a pick-up contact, they'll receive this final reminder, too.

      """
    And i should see order number
    And i should see order capture date
    And i should see firstname for 543 templates
    And i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
    And i should see billing address
    And i should see pickup barcode
    And i should see credit card type for 543 templates
    And i should see gift receipt
    And i should see contact information pickup:
      """
      Pick-up contact: <shipmentsfirstname> <shipmentslastname> <shipmentsemail> <shipmentsphone>
      """
    And i should see pick up location:
      """
      Pick-up location: <storename> <shipmentsstoreaddrline1> <shipmentsstorecity>, <shipmentsstorestate> <shipmentsstorezipcode> <shipmentsstorephone>
      """
    And i should see contact information:
      """
      Contact information: <billingfirstname> <billinglastname> <recipientemailaddress>
      """
    And i should see store name information at lable:
      """
      Item(s) no longer available at <storeName>
      """
    And i should see button as "VIEW MY ACCOUNT"
    And i should see store today's hours
    And i should see store tomorrow's hours
    And i should see "view map" button in store hours section
    And i should see store pickup area
    And i should see "Item(s)" text
    And i should see "Item(s) to be shipped" text
    And i should see "Item(s) previously shipped" text
    And i should see recepient email address
    And i should see product name
    And i should see product quantity
    And i should see product color
    And i should see product size
    And i should see product price
    And i should see product image
    And i should see product image url valid
    And i should see product name url valid
    And i should see "Order total" in the email
    And i should see "Sales tax" in the email
    And i should see "TOTAL" in the email
    And i should see "Pick up in-store" in the email
    
    
    @optional
    Scenario: Verify Email content for for template mcom_fil_543_1A_bops_pickup_reminder
    Given i trigger "mcom_fil_543_1A_bops_pickup_reminder_optional" input through csp testemail
    When i navigate to view the email page
    And i should see Macys logo
