Feature: Test for verify-promotions

  @preview_mew
  Scenario: Verify shop now links on deals and promotions page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Promotions  |
    And I should see following elements on "deals_and_promotions" page:
      | sales_and_promotions |
      | select_category      |
    And I click on "sales_and_promotions" on "deals_and_promotions" page
    Then I should see following elements on "offer_details" panel:
      | header              |
      | offer_details_modal |
      | shop_now            |
      | details_disclaimer  |