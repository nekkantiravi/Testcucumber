 Feature: Verify BWEDD 544 fedfil bops order thankyou email content
 
   @regression
   @bwedd_fil_544_1B_fedfil_bops_order_thankyou
   @bwedd_fil_544
   Scenario: Verify Email content for template bwedd_fil_544_1B_fedfil_bops_order_thankyou
    Given i trigger "bwedd_fil_544_1B_fedfil_bops_order_thankyou" input through csp testemail
 	And i have an enhanced payload sent to email provider 
 	When i navigate to view the email page
  Then i should see body text for fedfil bops order thankyou:
    """
    Your order has been picked up by <shipment.firstName> <shipment.lastName> at <storeName>. Thank you for shopping at The Registry at Bloomingdale's.
    """
 	And i should see preheader:
	"""
	Thank you for shopping with us!
	"""
 	And i should see bloomingdales registry logo
	And i should see freeshipping image