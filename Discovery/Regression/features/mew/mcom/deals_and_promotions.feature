Feature: Test for verify-promotions

  @preview_mew
  Scenario: Verify Deals and Promotions page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Deals  |
    Then I should see "by category" sort option is selected on deals and promotions page
    And I should see following elements on "deals_and_promotions" page:
      | deals_and_promotions_items      |
      | category                        |
    Then I should see "by category" sort option is selected on deals and promotions page
    And I click on "offer_heading" on "deals_and_promotions" page
    Then I should see following elements on "offer_details" panel:
      | header              |
      | offer_details_modal |
      | add_to_wallet or shop_now |
      | share_offer         |
      | details_exclusions  |