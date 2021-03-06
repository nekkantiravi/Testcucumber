Feature: Verify BCOM fedfil sdd bops cancel email content

  @regression
  @blcom_fil_542
  @blcom_fil_542_30A_fedfil_bops_cancel
  Scenario: Verify Email content for template blcom_fil_542_30A_fedfil_bops_cancel
	Given i trigger "blcom_fil_542_30A_fedfil_bops_cancel" input through csp testemail
	And i have an enhanced payload sent to email provider
	When i navigate to view the email page
	And i should see preheader:
    """
	Your order is no longer available in store.
    """
    And i should see bloomingdales logo
    And i should see freeshipping image
    
    Then i should see static contents:
    """
    Unfortunately, your order is no longer available for in-store pickup and we had to cancel it. You have not been charged.The item(s) may still be available from bloomingdales.com for delivery - please click on the item(s) below to check.
    """
    And i should see firstname    
    And i should see "ORDER DETAILS" text
    And i should see order number
    And i should see order capture date
    And i should see "ITEM DETAILS" text
    And i should see product details
    Then i should see static contents for com template:
      """
      Thank you, 
	  Bloomingdale's Customer Service 
	   Call us at any time: 1-800-777-0000 

	   We're available 24hours, 7 days a week. 
	   customerservice@bloomingdales.com 
      """
      And i should see "CUSTOMER SERVICE" link
      
      
    @optional
    Scenario: Verify optional fields empty scenario for template blcom_fil_542_30A_fedfil_bops_cancel_optional
    Given i trigger "blcom_fil_542_30A_fedfil_bops_cancel_optional" input through csp testemail
    When i navigate to view the email page
    And i should see bloomingdales logo