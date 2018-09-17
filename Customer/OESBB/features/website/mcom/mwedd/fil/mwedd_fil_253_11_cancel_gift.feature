Feature: Verify MWEDD 253_cancel_gift email content

  @mwedd_fil_253_11_cancel_gift
   @regression
   Scenario: Verify email content for tempalte mwedd_fil_253_11_cancel_gift
    Given i trigger "mwedd_fil_253_11_cancel_gift" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    
    And i should see pre header:
    """
	An order made from your registry was canceled. View Registry 
	"""
	And i should see Macys logo
	And i should see default categories:
    """
    FOR THE HOME,MEN,WOMEN,SHOES
    """
    And i should see firstname
	And i should see static contents:
	"""
	Due to unexpected vendor delays, the following order won’t arrive on the expected date and was canceled by your guest. The items will remain on your registry since they may become available in the future.
	
	If you’d like to make any changes, please visit your registry.
	"""
	And i should see "Order #:" text
    And i should see order number
    And i should see "Order date:" text
    And i should see order capture date
    And i should see "Order cancelation on:" text
    And i should see order cancelation date for 253 11 mwedd
    And i should see "Order canceled by:" text
    And i should see orderer canceled by
    """
    Order canceled by: <billingAddr.firstName> <billingAddr.lastName>
    """
    And i should see button as "view my registry"
    Then i should see product name
    And i should see product quantity 
	And i should see product color
	And i should see product size
	And i should see product type
	And i should see product price
  And i should see product name url valid
 #And i should see refund amount
    #And i should see product name
    #And i should see product quantity
    #And i should see product color
    #And i should see product type
    #And i should see product name url valid
    
    @optional
   Scenario: Verify optional fields empty scenario for template mwedd_fil_253_11_cancel_gift
    Given i trigger "mwedd_fil_253_11_cancel_gift_optional" input through csp testemail
    And i wait for enhanced payload to be sent to email provider
    When i navigate to view the email page
    Then i should see default categories:
      """
      FOR THE HOME FOB,MEN FOB,WOMEN FOB,SHOES FOB
      """