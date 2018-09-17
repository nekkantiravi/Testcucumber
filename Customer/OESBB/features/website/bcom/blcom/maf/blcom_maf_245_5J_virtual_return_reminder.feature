Feature: Verify BCOM maf virtual return reminder email content

  @blcom_maf_245_5J_virtual_return_reminder 
  @regression
  @blcom_maf_245
  Scenario: Verify email content for template blcom_maf_245_5J_virtual_return_reminder
    Given i trigger "blcom_maf_245_5J_virtual_return_reminder" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see preheader:
      """
      REMINDER: PLEASE RETURN YOUR ITEMS
      """
    And i should see static contents for virtual return reminder:
      """
      On <returnsubmitteddate>, we issued a credit for the item(s) you received. At this time, we have no record of the original item(s) you were meant to return. If we don't receive it, we reserve the right to charge your card for the estimated amount of the item(s) including tax and shipping fees: <estimatedrefund>. If you've recently returned your item(s) at a store, you can disregard this message.
      """
    And i should see firstname
    And i should see bloomingdales logo
    And i should see freeshipping image
    And i should see "UPDATED ORDERED DETAILS" text
    And i should see order number
    And i should see order capture date
    And i should see estimated refund
    And i should see reason for return for virtual return templates
    And i should see card credited
    And i should see "ITEM DETAILS" text
    And i should see product details

    @optional
  Scenario: Verify optional fields empty scenario for template blcom_maf_245_5J_virtual_return_reminder
    Given i trigger "blcom_maf_245_5J_virtual_return_reminder_optional" input through csp testemail
    And i wait for enhanced payload to be sent to email provider
    When i navigate to view the email page
    Then i should see bloomingdales logo