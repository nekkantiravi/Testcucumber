Feature: Verify BCOM 542 fedfil bops late pickup cancel email content

  @regression
  @blcom_fil_542_30D_fedfil_bops_late_pickup_cancel 

  Scenario: Verify Email content for template blcom_fil_542_30D_fedfil_bops_late_pickup_cancel
    Given i trigger "blcom_fil_542_30D_fedfil_bops_late_pickup_cancel" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see body text for 542_30D bops late pickup cancel:
    """
    Order <orderNumber> was available for pickup through <pickupCancelDate>. Since it wasn't picked up or it was canceled over the phone, the order has now been canceled and a credit or refund will be issued to <billingAddrfirstName> <billingAddrlastName>.
    """
    And i should see header:
      """
      THIS ORDER HAS BEEN CANCELED
      """
    And i should see preheader:
      """
      A credit or refund will be issued.
      """
    And i should see shipping first name
    And i should see bloomingdales logo
    And i should see freeshipping image
    And i should see "ORDER DETAILS" text
    Then i should see order number
    And i should see order capture date
    And i should see "ITEM DETAILS" text
    And i should see product details