Feature: Verify MWEDD 264 bops ready for pickup email content

  @mwedd_fil_264_14R_bops_ready_for_pickup @regression
  Scenario: Verify Email content for for template mwedd_fil_264_14R_bops_ready_for_pickup
    Given i trigger "mwedd_fil_264_14R_bops_ready_for_pickup" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      The rest of your registry order is ready for pick up. My Account
      """
    And i should see body text for 264_14R:
   """
   we're sorry, we weren't able to fulfill part of your order. your Plenti points used on the item(s) we couldn't fulfill will be credited back to your account. you may still find the item(s) on macys.com or at another macy's store. check the product links below to place a new order.
   """
    Then i should see order number
    And i should see pickup date
    Then i should see billing first name
    Then i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
    And i should see Macys logo
    And i should see button as "VIEW MY ACCOUNT"
    And i should see contact information:
      """
      Contact information: <billingfirstname> <billinglastname> <recipientemailaddress>
      """
   And i should see billing address
    And i should see contact information pickup:
      """
      Pick Up Contact Information: <shipmentsfirstname> <shipmentslastname> <shipmentsemail> <shipmentsphone> Text pick-up notice:Yes
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
    And i should see credit card type
    And i should see gift receipt
    And i should see pickup barcode
    Then i should see product name
    And i should see product quantity
    And i should see product color
    And i should see product size
    And i should see product type
    And i should see product price for bops ready for pickup
    And i should see product image
    Then i should see product image url valid
    And i should see product name url valid
    And i should see button as "CHECK ORDER STATUS"
    And i should see "Order total" in the email
    And i should see pick up in-store for 264_14Q in the email
    And i should see sales tax for 264_14Q in the email
     And i should see "PR VAT" in the email
    And i should see "PR Municipal" in the email
    And i should see "TOTAL" in the email
    
  @mwedd_fil_264_14R_bops_ready_for_pickup @regression
  Scenario: Verify Email content for for template mwedd_fil_264_14R_bops_ready_for_pickup_sts_quoted
    Given i trigger "mwedd_fil_264_14R_bops_ready_for_pickup_sts_quoted" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      The rest of your registry order is ready for pick up. My Account
      """
    And i should see body text for 264_14R:
      """
      we're sorry, we weren't able to fulfill part of your order. your Plenti points used on the item(s) we couldn't fulfill will be credited back to your account. you may still find the item(s) on macys.com or at another macy's store. check the product links below to place a new order.
      """
    And i should see "Shippable items (pick up unavailable)" text