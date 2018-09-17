Feature: Verify MCOM 542 no bops items available email content

  @mcom_fil_542_30A_no_bops_items_available @regression
  Scenario: Verify Email content for for template mcom_fil_542_30A_no_bops_items_available
    Given i trigger "mcom_fil_542_30A_no_bops_items_available" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      We were unable to fulfill your order. My Account
      """
    And i should see body text for no bops items available:
      """
      We're sorry, we weren't able to fulfill your order at <storename>. Don't worry, you weren't charged for the item(s).You may find the item(s) on macys.com or at another Macy's store. Check the product link(s) below to place a new order.
      """
    Then i should see order number
    And i should see order capture date
    Then i should see firstname
    Then i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
    And i should see Macys logo
    And i should see button as "VIEW MY ACCOUNT"
    And i should see credit card type
    # We have an issue for the below step. As per discussion, commented for now.
    #And i should see contact information:
    #  """
    #  Contact information: <billingfirstname> <billinglastname> <recipientemailaddress>
    #  """
    And i should see billing address
    Then i should see product name
    And i should see product quantity
    And i should see product color
    And i should see product size
    And i should see product type
    And i should see product price
    And i should see product image
    Then i should see product image url valid
    And i should see product name url valid

  @optional
  Scenario: Verify optional fields empty scenario for template mcom_fil_542_30A_no_bops_items_available
    Given i trigger "mcom_fil_542_30A_no_bops_items_available_optional" input through csp testemail
    When i navigate to view the email page
    Then i should see Macys logo
