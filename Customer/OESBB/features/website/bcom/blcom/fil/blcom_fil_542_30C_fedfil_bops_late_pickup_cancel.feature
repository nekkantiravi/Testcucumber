Feature: Verify BCOM 542 fedfil bops late pickup cancel email content

  @regression
  @blcom_fil_542_30C_fedfil_bops_late_pickup_cancel
  
  Scenario: Verify Email content for template blcom_fil_542_30C_fedfil_bops_late_pickup_cancel
    Given i trigger "blcom_fil_542_30C_fedfil_bops_late_pickup_cancel" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see body text for 542_30C bops late pickup cancel:
      """
      Your order was available for pickup through <pickupCancelDate>. Since it wasn't picked up or you called to cancel it, a credit or refund will be issued to you in the amount of $<chargeAmt>. If you paid in full using a Gift Card or Reward Card, a new Gift Card will be issued to you for pickup at the store.
      """
    And i should see header:
      """
      YOUR ORDER HAS BEEN CANCELED
      """
    And i should see preheader:
      """
      A credit or refund will be issued.
      """
    And i should see billing first name
    And i should see bloomingdales logo
    And i should see freeshipping image
    And i should see "ORDER DETAILS" text
    Then i should see order number
    And i should see order capture date
    And i should see amount refunded
    And i should see refunded to card info
    And i should see "ITEM DETAILS" text
    And i should see product details
    
    @optional
    Scenario: Verify optional fields empty scenario for template blcom_fil_542_30C_fedfil_bops_late_pickup_cancel_optional
    Given i trigger "blcom_fil_542_30C_fedfil_bops_late_pickup_cancel_optional" input through csp testemail
    When i navigate to view the email page
    And i should see bloomingdales logo