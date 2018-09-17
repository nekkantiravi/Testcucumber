Feature: Verify MCOM 264 bops ready for pickup email content

  @mcom_fil_264_14S_bops_ready_for_pickup @regression
  Scenario: Verify Email content for for template mcom_fil_264_14S_bops_ready_for_pickup
    Given i trigger "mcom_fil_264_14S_bops_ready_for_pickup" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header for bops ready for pick up:
      """
      We'll hold it until <pickupCancelDate>.
      """
    And i should see body text for 264_14S and 14T:
      """
      Good news! <billingAddr.firstName>'s order from Macys.com is ready for pick up. You were designated as the person to pick it up.
      """
    Then i should see order number
    And i should see pickup date
    Then i should see shipment firstname
    Then i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
    And i should see Macys logo
    And i should see button as "VIEW MY ACCOUNT"
    And i should see contact information pickup:
      """
      Contact information(Pick-Up): <shipmentsfirstname> <shipmentslastname> <shipmentsemail> <shipmentsphone> 
      """
    And i should see orderer's contact info bops ready for pickup
      """
      Orderer's Contact information: <billingAddr.firstName> <billingAddr.lastName> Text pick-up notice:Yes
      """
    And i should see pick up location:
      """
      Pick-up location: <storename> <shipmentsstoreaddrline1> <shipmentsstorecity>, <shipmentsstorestate> <shipmentsstorezipcode> <shipmentsstorephone>
      """
    And i should see what to bring details
      """
      What to bring:• A government-issued photo ID• This email with order # and barcode or locker code below
      """
    And i should see store today's hours
    And i should see store tomorrow's hours
    And i should see "view map" button in store hours section
    And i should see text below store hours
      """
      We'll hold your order until <pickupCancelDate>. After that, we'll have to cancel it and issue you a refund. If you assigned a pickup-contact, they'll receive an email, too. Note: Some Macy's stores charge a nominal fee for shopping bags. Learn more

      """
    And i should see pickup barcode
    And i should see gift receipt
    Then i should see product name
    And i should see product quantity
    And i should see product color
    And i should see product size
    And i should see product type
    And i should see product image
    Then i should see product image url valid
    And i should see product name url valid
