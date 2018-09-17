Feature: Verify MWEDD 544 bops item picked up email content

  @mwedd_fil_544_1B_bops_item_picked_up 
  @regression 
  @bat
  Scenario: Verify Email content for for template mwedd_fil_544_1B_bops_item_picked_up
    Given i trigger "mwedd_fil_544_1B_bops_item_picked_up" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
       Thanks for using Buy Online, Pick Up In Store. Shop Macy's
      """
    And i should see body text for bops item picked up:
      """
      We just wanted to let you know that your order for pick up in store was picked up by <shipmentname>. Thanks for shopping with us!
      """
    Then i should see order number
    Then i should see billing firstname
    Then i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
    And i should see Macys logo
    And i should see button as "VISIT MY ACCOUNT"