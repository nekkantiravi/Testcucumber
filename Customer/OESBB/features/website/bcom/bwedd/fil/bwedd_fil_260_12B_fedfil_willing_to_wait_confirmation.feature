Feature: Verify BWEDD 260 willing to wait confirmation email content

  @regression @bwedd_fil_260_12B_fedfil_willing_to_wait_confirmation
  Scenario: Verify Email content for template bwedd_fil_260_12B_fedfil_willing_to_wait_confirmation
    Given i trigger "bwedd_fil_260_12B_fedfil_willing_to_wait_confirmation" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see static contents:
      """
      We received your confirmation to proceed with the delayed items listed below. We do not yet have an estimated shipping date from the vendor, but you indicated you are willing to wait. You will not be charged until the item ships.
      """
    And i should see preheader:
      """
      We will proceed with your delayed item.
      """
    And i should see firstname
    And i should see bloomingdales registry logo
    And i should see freeshipping image
    And i should see "ORDER DETAILS" text
    Then i should see order number
    And i should see order capture date
    And i should see customer care static text
      """
      To cancel your order before it ships, contact Customer Service at 1-800-777-0000
      """
    And i should see "ITEM DETAILS" text
    And i should see product details
