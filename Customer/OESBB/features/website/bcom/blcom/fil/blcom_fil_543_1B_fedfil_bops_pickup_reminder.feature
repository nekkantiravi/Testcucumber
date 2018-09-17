Feature: Verify fedfil bops pickup reminder email content

  @regression @blcom_fil_543_1B_fedfil_bops_pickup_reminder
  Scenario: Verify email content for template blcom_fil_543_1B_fedfil_bops_pickup_reminder
    Given i trigger "blcom_fil_543_1B_fedfil_bops_pickup_reminder" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    And i should see preheader:
      """
      Important information about your order.
      """
    And i should see body text for bops pickup reminder:
     """
      <billingAddr.firstName> designated you to pick up this order and it is ready - although part of it could not be completed. please see below for details.
     """
    And i should see bloomingdales logo
    And i should see freeshipping image
    And i should see shipment firstname
    And i should see order number
    And i should see order capture date
    And i should see store name
    And i should see store address
    And i should see store today's hours
    And i should see store tomorrow's hours
    And i should see phone number for general questions
    And i should see in-store pickup questions for bops pickup reminder:
      """
      Questions about instore pickup? Call
      """
    And i should see note section below store hours for bops pickup reminder:
    """
    We have also notified <billingAddr.firstName> <billingAddr.lastName> that this order is now ready. NOTE: This order will be available for pickup through <pickupCancelDate>. After that date, the order will no longer be held and will be canceled.
    """
    And i should see pickup barcode
    And i should see store pickup instructions
    And i should see "IN-STORE PICKUP INFORMATION" text
    And i should see contact information
    And i should see "THIS ORDER IS A GIFT" text
    And i should see gift message
    And i should see gift receipt option
    And i should see "ITEM DETAILS" text
    And i should see product details
    And i should see "UNAVAILABLE ITEM(S)" text
    And i should see text for unavailable item section for bops pickup reminder:
      """
      One or more items in this order were unavailable and have been removed from the order. <billingAddr.firstName> <billingAddr.lastName> will not be charged for these items.
      """
    
    
    @optional
    Scenario: Verify optional fields empty scenario for template blcom_fil_543_1B_fedfil_bops_pickup_reminder_optional
    Given i trigger "blcom_fil_543_1B_fedfil_bops_pickup_reminder_optional" input through csp testemail
    When i navigate to view the email page
    And i should see bloomingdales logo