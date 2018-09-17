Feature: Verify BCOM maf virtual Exchange reminder email content

  @regression
  Scenario: Verify email content for template blcom_maf_245_5K_virtual_exchange_reminder
    Given i trigger "blcom_maf_245_5K_maf_virtual_exchange_reminder" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see bloomingdales logo
    And i should see freeshipping image
    And i should see preheader:
      """
      REMINDER: PLEASE RETURN YOUR ITEMS
      """
    And i should see firstname
    And i should see static contents for virtual exchange reminder:
      """
      On <returnSubmittedDate>, we ordered a replacement for the item(s) you received. At this time, we have no record of the original item(s) you were meant to return. If we don't receive it by <returnExpectedBackDate>, we reserve the right to charge your card for the estimated amount of the item(s) including tax and shipping fees: <sumOfPlaceholders13And15>
      """
    And i should see order number
    And i should see order capture date
    And i should see replacement date
    And i should see original payment
    And i should see reason for return
    And i should see payment card and type:
      """
      (Original form of tender: <cardNoAndCardType>)
      """
    And i should see "ORIGINALLY ORDERED DETAILS" text
    And i should see "ITEM DETAILS" text
    And i should see "REPLACEMENT ORDER DETAILS" text
    And i should see product details

  @optional
  Scenario: Verify optional fields empty scenario for template blcom_maf_245_5K_maf_virtual_exchange_reminder
    Given i trigger "blcom_maf_245_5K_maf_virtual_exchange_reminder_optional" input through csp testemail
    And i wait for enhanced payload to be sent to email provider
    When i navigate to view the email page
    Then i should see bloomingdales logo