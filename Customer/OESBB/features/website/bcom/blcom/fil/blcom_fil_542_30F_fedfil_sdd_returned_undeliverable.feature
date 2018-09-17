Feature: Verify BCOM fedfil sdd returned undeliverable email content

  @regression
  @blcom_fil_542
  @blcom_fil_542_30F_fedfil_sdd_returned_undeliverable
  Scenario: Verify Email content for template blcom_fil_542_30F_fedfil_sdd_returned_undeliverable
	Given i trigger "blcom_fil_542_30F_fedfil_sdd_returned_undeliverable" input through csp testemail
	And i have an enhanced payload sent to email provider
	When i navigate to view the email page
    Then i should see static content for fedfil sdd returned undeliverable as:
    """
    Unfortunately, we're unable to deliver your order today. A refund will be issued to you in the amount of $<payments[].chargeAmt>. If you used a Gift Card or Reward Card, a new one will be sent to you shortly.
    """
    And i should see preheader:
    """
    A credit or refund will be issued
    """
    And i should see firstname    
    And i should see "ORDER DETAILS" text
    And i should see order number
    And i should see bloomindales logo
    #And i should see order capture date
    And i should see "ITEM DETAILS" text
    #And i should see product details
    
    
    @optional
    Scenario: Verify optional fields empty scenario for template blcom_fil_542_30F_fedfil_sdd_returned_undeliverable_optional
    Given i trigger "blcom_fil_542_30F_fedfil_sdd_returned_undeliverable_optional" input through csp testemail
    When i navigate to view the email page
    And i should see freeshipping image