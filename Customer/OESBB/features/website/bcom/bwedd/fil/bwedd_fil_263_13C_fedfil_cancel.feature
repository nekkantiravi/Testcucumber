Feature: Verify BCOM fedfil cancel email content

  @bwedd_fil_263_13C_fedfil_cancel
  @regression
  Scenario: Verify email content for template bwedd_fil_263_13C_fedfil_cancel
    Given i trigger "bwedd_fil_263_13C_fedfil_cancel" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    And i should see preheader:
      """
      You have not been charged.
      """
    And i should see bloomingdales registry logo
    And i should see freeshipping image
    Then i should see header:
      """
      WE'RE SORRY, THESE ITEMS HAVE BEEN CANCELED
      """
    And i should see firstname
    Then i should see static contents:
      """
      Because we did not receive your consent to proceed with the delayed items listed below, we were legally required to cancel them. You have not been charged.If you have any questions or would like assistance in placing a new order, please contact Customer Service at 1-800-777-0000.
      """
    And i should see "ORDER DETAILS" text
    And i should see order number
    And i should see order capture date
    And i should see "ITEM DETAILS" text
    And i should see shop now button
    And i should see product details
    And i should see product cancelled quantity
    Then i should see static contents for com template:
      """
      Thank you,
      Bloomingdale's Customer Service
      Call us at any time:1-800-777-0000
      We're available 24hours, 7 days a week.
      customerservice@bloomingdales.com
      """
