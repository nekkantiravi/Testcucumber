Feature: Verify BCOM fedfil cancel email content

  @regression @blcom_fil_265_15A_fedfil_cancel
  Scenario: Verify email content for template blcom_fil_265_15A_fedfil_cancel
    Given i trigger "blcom_fil_265_15A_fedfil_cancel" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    And i should see preheader:
      """
      We've removed it from your order.
      """
    And i should see bloomingdales logo
    And i should see freeshipping image
    Then i should see header:
      """
      THIS ITEM WAS DISCONTINUED
      """
    And i should see firstname
    Then i should see static contents:
      """
      Unfortunately, the item listed below has been discontinued and removed from your order. You have not been charged. If you have any questions or would like assistance in placing a new order, please contact Customer Service at 1-800-777-0000.
      """
    And i should see "ORDER DETAILS" text
    And i should see order number
    And i should see order capture date
    And i should see "ITEM DETAILS" text
    And i should see shop now button
    And i should see product details
    Then i should see static contents for com template:
      """
      Thank you,
      Bloomingdale's Customer Service
      Call us at any time:1-800-777-0000
      We're available 24hours, 7 days a week.
      customerservice@bloomingdales.com
      """
	##Require test data to excute below commented methods      
  #And i should see zone name in the email
  #And i should see pros product images
  #And i should see pros product names
