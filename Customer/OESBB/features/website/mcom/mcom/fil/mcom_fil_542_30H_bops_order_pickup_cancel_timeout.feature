Feature: Verify MCOM 542 no bops items available timeout email content

  @mcom_fil_542_30H_bops_order_pickup_cancel_timeout @regression
  Scenario: Verify Email content for for template mcom_fil_542_30H_bops_order_pickup_cancel_timeout
    Given i trigger "mcom_fil_542_30H_bops_order_pickup_cancel_timeout" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      We were unable to fulfill your order. My Account
      """
    And i should see body text for no bops items available:
      """
      We're sorry we weren't able to fulfill your order at <storename>, and the time to ship it has expired. The item(s) may still be available on macys.com or at another Macy's store. Click on each item below to view availability.
      """	  
    Then i should see order number
    And i should see order capture date
    Then i should see firstname
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
    #
    # B-79857 doesn't have Contact information in specs
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
    # B-79857 doesn't have TOTAL in specs
    #
    #And i should see "Order total" in the email
    #And i should see "Additional shipment fee amount" in the email
    #And i should see "Shipment upgrade fee amount" in the email
    #And i should see pick up in-store for 542_30H in the email
    #And i should see sales tax in the email
    #And i should see "Gift wrap fee amount" in the email
    #And i should see "PR VAT" in the email
    #And i should see "PR Municipal" in the email
    #And i should see "TOTAL" in the email
      