Feature: Verify BSTORE maf virtual exchange receipt email content

  @bstore_maf_245_5M_virtual_exchange_receipt
  @regression
  @bstore_maf_245
  Scenario: Verify email content for template bstore_maf_245_5M_virtual_exchange_receipt
    Given i trigger "bstore_maf_245_5M_virtual_exchange_receipt" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see preheader:
      """
      WE RECEIVED YOUR RETURN 
      """
    Then i should see firstname
    And i should see bloomingdales logo
    And i should see freeshipping image
    And i should see static contents:
      """
      We received the item that we replaced on 06/07/2016 and your return is complete.
      """
    Then i should see "REPLACEMENT ORDER DETAILS" text
    And i should see order capture date
    And i should see original payment
    And i should see replacement date
    And i should see return completed date
    And i should see reason for replacement
    And i should see "ITEM DETAILS" text
    And i should see product details
