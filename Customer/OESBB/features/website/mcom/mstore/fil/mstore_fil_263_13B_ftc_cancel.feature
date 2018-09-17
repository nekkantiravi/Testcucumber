Feature: Verify MSTORE 263_ftc_cancel email content

  @mstore_fil_263_13B_ftc_cancel @regression
  Scenario: Verify email content for tempalte mstore_fil_263_13B_ftc_cancel
    Given i trigger "mstore_fil_263_13B_ftc_cancel" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      We had to cancel your order. Shop Macy's
      """
    And i should see Macys logo
    Then i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
    And i should see billing first name
    And i should see body text for ftc cancel
      """
      Unfortunately, we had to cancel your order since we didn't hear back from you by the confirmation date of <userCancelDate>. If you were charged, you'll receive a refund on the payment method listed below.Your items may still be available on macys.com or at a Macy's store. Please click on the product links below to place a new order. We apologize for any inconvenience.
      """
    And i should see order number
    And i should see order capture date
    And i should see "Payment method:" text
    And i should see the "SHOP MACY'S" button
    And i should see "Item(s)" text
    Then i should see product name
    And i should see product requested quantity
    And i should see product color
    And i should see product size
    And i should see product type
    And i should see product image
    Then i should see product image url valid
    And i should see product name url valid
