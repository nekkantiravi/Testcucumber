 Feature: Verify BCOM 264 fedfil sdd partial cancel email content
 
   @regression
   @blcom_eod_264_14U_sdd_partial_cancel
   @blcom_eod_264
   Scenario: Verify email content for template blcom_eod_264_14U_sdd_partial_cancel
    Given i trigger "blcom_eod_264_14U_sdd_partial_cancel" input through csp testemail
 	And i have an enhanced payload sent to email provider 
 	When i navigate to view the email page
 Then i should see static contents:
  """
  Unfortunately, one or more items in your order are no longer available for Same Day Delivery, and we had to cancel it. We apologize for any inconvenience. You have not been charged. The item(s) may still be available from <link>bloomingdales.com<link> for standard delivery-please click on the item(s) below to check. The rest of your order will not be affected.
  """
 	And i should see preheader:
	"""
	Your Order Has Changed
	"""
	And i should see header:
	"""
	YOUR ORDER HAS CHANGED
	"""
	And i should see firstname
 	And i should see bloomingdales logo
	And i should see freeshipping image	
 	And i should see "ORDER DETAILS" text
 	Then i should see order number
 	And i should see order capture date
 	And i should see billing address
 	And i should verify shipment address for fedfil sdd partial cancel
	And i should see "ITEM DETAILS" text
	And i should see product details
	And i should see order details
	And i should see "PAYMENT METHOD" text
	And i should see payment card info
	And i should see "UNAVAILABLE ITEM(S)" text