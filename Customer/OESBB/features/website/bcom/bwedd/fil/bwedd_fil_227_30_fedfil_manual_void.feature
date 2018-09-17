Feature: Verify BCOM 227 fedfil manual void email content

  @regression
  @bwedd_fil_227_30_fedfil_manual_void
  @bcom_fil_227
  Scenario: Verify Email content for template bwedd_fil_227_30_fedfil_manual_void
    Given i trigger "bwedd_fil_227_30_fedfil_manual_void" input through csp testemail
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
      YOUR ORDER HAS BEEN CANCELED
      """
    And i should see firstname
    Then i should see static contents:
      """
      We have canceled this order. You have not been charged.
      """
    And i should see "ORDER DETAILS" text
    And i should see order number
    And i should see order capture date    
    And i should see "ITEM DETAILS" text
    And i should see product details
    Then i should see static contents for com template:
      """
      Thank you,
      	Bloomingdale's Customer Service
      	Call us at any time:1-800-777-0000
      
      	We're available 24hours, 7 days a week.
      	customerservice@bloomingdales.com
      """
    And i should see "CUSTOMER SERVICE" link
