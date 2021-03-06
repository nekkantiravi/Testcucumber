Feature: Verify MCOM 265 product discontinued email content

  @mcom_fil_266_16B_product_discontinued @regression
  Scenario: Verify Email content for for template mcom_fil_266_16B_product_discontinued
    Given i trigger "mcom_fil_266_16B_product_discontinued" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      Discontinued item(s) were removed from your order. Shop Now
      """
    And i should see body text:
      """
      We're sorry, but the item(s) listed below are discontinued and were removed from your order. Don't worry, you weren't charged. Please visit macys.com to find similar items.
      """
    Then i should see order number
    And i should see order capture date with two digits in the year
    Then i should see firstname
    Then i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
    And i should see macys logo using alternative text
    And i should see button as "SHOP MACY'S"
    And i should see credit card type
    Then i should see product name
    And i should see product requested quantity
    And i should see product color
    And i should see product size
    And i should see product type
    And i should see product image
    Then i should see product image url valid
    And i should see product name url valid
