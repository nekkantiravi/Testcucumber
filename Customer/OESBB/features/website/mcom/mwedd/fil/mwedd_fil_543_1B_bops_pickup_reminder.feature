Feature: Verify MWEDD 543 bops pickup reminder confirmation email content

  @regression
  Scenario: Verify Email content for for template mwedd_fil_543_1B_bops_pickup_reminder
    Given i trigger "mwedd_fil_543_1B_bops_pickup_reminder" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      We'll hold it until the date below.
      """
    And i should see Macys logo
    And i should see body text for 543_1B bops pickup reminder:
      """
      <billingFirstName>'s order is waiting at <storeName>. This is the final reminder to pick it up.We'll hold it until <pickupCancelDate>.After that, we'll have to cancel it and issue a refund.

      """
    And i should see order number
    And i should see order capture date
    And i should see firstname
    And i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
    And i should see pickup barcode
    And i should see gift receipt
    And i should see contact information pickup:
      """
      Contact Information (Pick-up): <shipmentsfirstname> <shipmentslastname> <shipmentsemail> <shipmentsphone>
      """
    And i should see pick up location:
      """
      Pick-up location: <storename> <shipmentsstoreaddrline1> <shipmentsstorecity>, <shipmentsstorestate> <shipmentsstorezipcode> <shipmentsstorephone>
      """
    And i should see contact information for 543 1B templates:
      """
      Orderer's contact information: <billingfirstname> <billinglastname>
      """
    And i should see store name information at lable:
      """
      Item(s) no longer available at <storeName>
      """
    And i should see store today's hours
    And i should see store tomorrow's hours
    And i should see "view map" button in store hours section
    And i should see store pickup area
    And i should see "Item(s)" text
    And i should see recepient email address
    And i should see product name
    And i should see product quantity
    And i should see product color
    And i should see product size
    And i should see product price
    And i should see product image
    And i should see product image url valid
    And i should see product name url valid
