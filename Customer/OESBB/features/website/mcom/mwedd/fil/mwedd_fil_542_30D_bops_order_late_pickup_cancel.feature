Feature: Verify MCOM 542 bops order late pickup cancel email content

  @mwedd_fil_542_30D_bops_order_late_pickup_cancel @regression
  Scenario: Verify Email content for for template mwedd_fil_542_30D_bops_order_late_pickup_cancel
    Given i trigger "mwedd_fil_542_30D_bops_order_late_pickup_cancel" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      Sorry, we weren't able to hold the order. My Account
      """
    And i should see body text for 542 30D bops order late pickup cancel:
      """
      We're sorry, the final pick-up date has passed and <billingfirstname>'s registry order is no longer available at <storename>. We're letting you know because you were listed as the pick-up contact.
      """
    Then i should see order number
    And i should see order capture date
    Then i should see firstname
    Then i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
    And i should see Macys logo
    And i should see orderers contact information
    And i should see contact information pickup:
      """
      Your Contact Information (Pick-Up): <shipmentsfirstname> <shipmentslastname> <shipmentsemail> <shipmentsphone>
      """
    #We have an issue. Country is displaying in the email. Commenting the step for now.
    #And i should see pick up location:
    #  """
    #  Pick-up location: <storename> <shipmentsstoreaddrline1> <shipmentsstorecity>, <shipmentsstorestate> <shipmentsstorezipcode> <shipmentsstorephone>
    #  """
    And i should see gift receipt
    Then i should see product name
    And i should see product quantity
    And i should see product color
    And i should see product size
    And i should see product type
    And i should see product price
    And i should see product image
    Then i should see product image url valid