Feature: Verify MCOM 264 sdd partial cancel email content


  @mcom_eod_264_14U_sdd_partial_cancel
  @regression
  Scenario: Verify Email content for template mcom_eod_264_14U_sdd_partial_cancel
    Given i trigger "mcom_eod_264_14U_sdd_partial_cancel" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      Your order has changed. My Account
      """
    # There is an issue with double quote. Responsys need to fix the issue.
    #And i should see body text:
    #  """
    #  we're sorry, your order for same-day delivery has changed. we were unable to fulfill part of the order. the rest of your order will still arrive at your scheduled time.don't worry, you weren't charged for the item(s) we couldn't fulfill.the item(s) may still be available on macys.com or at a macy's store. please click on the product links below to place a new order.
    #  """
    Then i should see order number
    And i should see order capture date
    Then i should see credit card type as mentioned:
      """
      Payment method: <cardtype> <cardnumber>
      """
    And i should see billing firstname
    And i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
    And i should see Macys logo
    And i should see billing address
    And i should see shipping address
    And i should see shipping method
    And i should see contact information for sdd partial cancel:
      """
      Contact information: <billingfirstname> <billinglastname> <shipmentsemail> <shipmentsphone> 
      """
    Then i should see product name
    And i should see product quantity
    And i should see product color
    And i should see product size
    And i should see product type
    And i should see product price for sdd cancel
    And i should see product image with different class name
    And i should see product image url valid with different class name
    And i should see product name url valid
    And i should see "Order total" in the email
    And i should see "Additional shipment fee amount" in the email
    And i should see "Same day delivery" in the email
    And i should see "Gift wrap fee amount" in the email
    And i should see sales tax in the email
    And i should see "TOTAL" in the email
    