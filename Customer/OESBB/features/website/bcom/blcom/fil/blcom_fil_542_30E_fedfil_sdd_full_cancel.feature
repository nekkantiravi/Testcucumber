Feature: Verify BCOM fedfil sdd full cancel email content

  @regression
  @blcom_fil_542
  @blcom_fil_542_30E_fedfil_sdd_full_cancel
  Scenario: Verify Email content for template blcom_fil_542_30E_fedfil_sdd_full_cancel
	Given i trigger "blcom_fil_542_30E_fedfil_sdd_full_cancel" input through csp testemail
	And i have an enhanced payload sent to email provider
	When i navigate to view the email page
    Then i should see static contents:
    """
    Unfortunately, your order is no longer available for Same Day Delivery and we had to cancel it. We apologize for any inconvenience. You have not been charged. The item(s) may still be available from <link>bloomingdales.com<link> for delivery - please click on the item(s) below to check.
    """
    And i should see preheader:
    """
	Your order could not be completed.
    """
    And i should see firstname    
    And i should see "ORDER DETAILS" text
    And i should see order number
    And i should see bloomingdales logo
    #And i should see order capture date
    And i should see "ITEM DETAILS" text
    #And i should see product details
    
    @optional
   Scenario: Verify optional fields empty scenario for template blcom_fil_542_30E_fedfil_sdd_full_cancel
    Given i trigger "blcom_fil_542_30E_fedfil_sdd_full_cancel_optional" input through csp testemail
    When i navigate to view the email page
    Then i should see bloomingdales logo