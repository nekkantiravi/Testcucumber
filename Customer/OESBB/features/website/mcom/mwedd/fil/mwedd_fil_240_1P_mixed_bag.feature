Feature: Verify MWEDD 240 bops mixed bag order confirmation email content
  @mwedd_fil_240_1P_bops_mixed_bag
  @regression
  Scenario: Verify Email content for for template mwedd_fil_240_1P_bops_mixed_bag
    Given i trigger "mwedd_fil_240_1P_bops_mixed_bag" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      Stay tuned for details. My Account
      """
    And i should see body text:
      """
      Thanks for shopping at Macy's! Once your registry order is processed, we'll email details on the item(s) being shipped and item(s) being picked up in store. Don't worry, you won't be charged until then.
      """
    Then i should see order number
    And i should see order capture date
    Then i should see firstname
    Then i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
    #And i should see Macys logo
    And i should see button as "VIEW MY ACCOUNT"
    And i should see contact information:
      """
      Contact information: <billingfirstname> <billinglastname> <recipientemailaddress>
      """
    And i should see billing address
    And i should see contact information pickup:
      """
      Contact Information (Pick-Up): <shipmentsfirstname> <shipmentslastname> <shipmentsemail> <shipmentsphone>
      """
    And i should see shipping method:
      """
      Shipping method: Free
      """
    And i should see gift receipt
    Then i should see product name
    And i should see product quantity
    And i should see product price
    And i should see product image
    Then i should see product image url valid    
    And i should see "Order total" in the email
	And i should see "Shipping" in the email
	And i should see "Additional shipment fee amount" in the email
	And i should see "Shipment upgrade fee amount" in the email
	And i should see "TOTAL" in the email
	
	@production
  	Scenario: Production : Verify email template for 240_bops_mixed_bag 240_1P_MWEDD
    Given i trigger "mwedd_fil_240_1P_bops_mixed_bag" input through csp testemail    
    When i navigate to view the email page
    Then i should see Macys logo