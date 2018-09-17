Feature: Verify MCOM 240 bops order confirmation email content

  @mwedd_fil_240_1N_bops_order_confirmation @regression
  Scenario: Verify Email content for for template mwedd_fil_240_1N_bops_order_confirmation
    Given i trigger "mwedd_fil_240_1N_bops_order_confirmation" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      Stay tuned for details. My Account
      """
    And i should see body text:
      """
      Thanks for shopping at macys.com. Once your registry order is processed, we'll send additional details. Don't worry, you won't be charged until then.
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
    And i should see contact information:
      """
      Contact information: <billingfirstname> <billinglastname> <recipientemailaddress>
      """
    And i should see billing address
    And i should see contact information pickup:
      """
      Contact Information (Pickup): <shipmentsfirstname> <shipmentslastname> <shipmentsemail> <shipmentsphone> Text pick-up notice: Yes
      """
    And i should see pick up location:
      """
      Pick-up location: <storename> <shipmentsstoreaddrline1> <shipmentsstorecity>, <shipmentsstorestate> <shipmentsstorezipcode> <shipmentsstorephone>
      """
    And i should see shipping method:
      """
      Shipping method: Free
      """
    And i should see gift receipt
    Then i should see product name
    And i should see product quantity
    And i should see product color
    And i should see product size
    And i should see product type
    And i should see pick-up at
    And i should see order ready notice to
    And i should see product price
    And i should see product image
    Then i should see product image url valid
    And i should see product name url valid
    And i should see "Order total" in the email
    And i should see "Pick up in-store" in the email
    And i should see "Sales tax" in the email
    And i should see "TOTAL" in the email
