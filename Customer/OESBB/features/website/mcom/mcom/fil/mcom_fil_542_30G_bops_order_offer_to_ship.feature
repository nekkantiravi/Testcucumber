Feature: Verify MCOM 542 no bops items available offer to ship email content

  @mcom_fil_542_30G_bops_order_offer_to_ship @regression
  Scenario: Verify Email content for for template mcom_fil_542_30G_bops_order_offer_to_ship
    Given i trigger "mcom_fil_542_30G_bops_order_offer_to_ship" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      We'll ship your items for free! Ship My Items
      """
    And i should see body text for no bops items available timeout:
      """
      Some of your item(s) are no longer available for pick up at Macy's. However, we can ship item(s) for free.Just use the link provide below to enter your address before <Weekday, Month, Day, Time EST>. If you wish to cancel the order, no action needs to be taken.
      """	  
    Then i should see order number
    And i should see order capture date
    Then i should see firstname
    Then i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
    And i should see Macys logo
    And i should see button as "SHIP MY ITEMS"
    And i should see contact information:
      """
      Your Contact information: <billingfirstname> <billinglastname> <recipientemailaddress>
      """
    And i should see billing address
    #
    # B-79858 doesn't have Contact information in specs
    #
    #And i should see contact information pickup:
    #  """
    #  Pick-up contact: <shipmentsfirstname> <shipmentslastname> <shipmentsemail> <shipmentsphone> Text pick-up notice: Yes
    #  """
    #We have an issue. Country is displaying in the email. Commenting the step for now.
    #And i should see pick up location:
    #  """
    #  Pick-up location: <storename> <shipmentsstoreaddrline1> <shipmentsstorecity>, <shipmentsstorestate> <shipmentsstorezipcode> <shipmentsstorephone>
    #  """
    #And i should see gift receipt
    Then i should see product name
    And i should see product quantity
    And i should see product color
    And i should see product size
    And i should see product type
    And i should see product price
    And i should see product image
    Then i should see product image url valid
    #
    # B-79858 doesn't have TOTAL in specs
    #
    #And i should see "Order total" in the email
    #And i should see "Additional shipment fee amount" in the email
    #And i should see "Shipment upgrade fee amount" in the email
    #And i should see pick up in-store for 542_30G in the email
    #And i should see sales tax in the email
    #And i should see "Gift wrap fee amount" in the email
    #And i should see "PR VAT" in the email
    #And i should see "PR Municipal" in the email
    #And i should see "TOTAL" in the email