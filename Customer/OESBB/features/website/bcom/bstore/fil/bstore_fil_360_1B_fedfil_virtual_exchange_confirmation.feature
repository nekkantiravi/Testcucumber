Feature: Verify fedfil virtual exchange confirmation templates

 
  @regression
  @bstore_fil_360_1B_fedfil_virtual_exchange_confirmation
  Scenario: Verify email content for template bstore_fil_360_1B_fedfil_virtual_exchange_confirmation
    Given i trigger "bstore_fil_360_1B_fedfil_virtual_exchange_confirmation" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see static contents:
      """
      We have processed a replacement for the item(s) listed below. The new item(s) should ship within 2-3 business days, at which time you will receive an email with tracking information. We apologize for any inconvenience.
   Next Steps:
    Item(s) that need to be returned are listed below with "Please return this item" in the details. Please return by mail or to a store. If we don't receive your return, we reserve the right to charge your card for the item(s) and any associated charges incurred at the time of purchase.
      """
    And i should see preheader:
      """
     YOUR REPLACEMENT ITEM IS BEING PROCESSED
      """
    And i should see firstname
    And i should see bloomingdales logo
    And i should see freeshipping image
    Then i should see "ORIGINALLY ORDERED DETAILS" text
    And i should see order number
    And i should see order capture date
     And i should see "Reduction due to insufficient points" text
    And i should see "Reason for reduction" text
    And i should see reason for reduction message:
      """
      If you returned an item used to accumulate Loyallist Points, your points balance will be reduced accordingly. If you do not have enough points to cover the adjustment, your Loyallist Reward Card balance will be reduced. If your Loyallist Reward Card balance is insufficient, your refund will be reduced.
      """
    And i should see "CHECK ORDER STATUS" button
    And i should see "ITEM DETAILS" text
    And i should see product details
    Then i should see "REPLACEMENT ORDER DETAILS" text
    And i should see order number
     And i should see replacement date
