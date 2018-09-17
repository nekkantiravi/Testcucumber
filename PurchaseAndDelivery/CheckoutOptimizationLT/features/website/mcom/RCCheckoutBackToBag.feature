Feature: Checkout Optimization LT Checkout Guest Back to bag scenarios

  @project_responsive_checkout @domain_purchase_and_delivery @mcom_rc_guest @mcom_rc_signedIn @coo-ll
  Scenario Outline: Verify the back to bag icon on the checkout page leads back to bag page
    Given I visit the web site as a <user_type> user
    When I add a "<product_type>" product to my bag
    And  I checkout until I reach the <page> page as a "<user_type>" user
    And I click on the back to bag icon
    Then validate that I am on the bag page

    Examples:
      |product_type|page    |user_type  |
      |available   |shipping|guest      |
      |available   |shipping|registered |