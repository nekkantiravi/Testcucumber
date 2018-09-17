Feature: Verify home page links

  @scenario1 @xbrowser_mew @domain_marketing
  Scenario: Verify user should be able navigate to loyallist enrollment page from footer
    Given I visit the mobile web site as a guest user
    When I click on "Become a loyallist" button on footer using mobile website
    Then I navigate to loyallist enrollment page from become a loyallist page

  @scenario2 @xbrowser_mew @domain_marketing
  Scenario: Verify "Promotions" button is clickable on home page
    Given I visit the mobile web site as a guest user
    When I click on "Promotions" button on footer using mobile website
    Then I should be redirected to "deals_and_promotions" page

  @scenario3 @xbrowser_mew @domain_discovery
  Scenario: Verify FOB links on Home page are clickable and navigate to the correct pages
    Given I visit the mobile web site as a guest user
    Then I verify FOB links on home page are clickable and navigate to the correct pages

  @scenario4 @xbrowser_mew @domain_discovery
  Scenario: Verify header and footer appear on the Home page
    Given I visit the mobile web site as a guest user
    Then I verify header and footer appear on the Home page

  @scenario5 @xbrowser_mew @domain_marketing
  Scenario: Verify Sales and Promotions page
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

  @scenario6 @xbrowser_mew @domain_purchase_and_delivery
  Scenario: Verify user can navigate to My Bag page from Home page and items are viewable in bag
    Given I visit the mobile web site as a guest user
    When I add an "available and member_product" product to my bag using mobile website
    And I navigate back to "home" page using mobile website
    And I click on "shopping_bag_icon" on "home" page
    Then I should be redirected to "shopping_bag" page
    And I should see following elements on "shopping_bag" page:
      | line_items  |
      | item_title  |
      | item_image  |
      | remove_item |

  @scenario7 @xbrowser_mew @domain_marketing
  Scenario: Verify ads are clickable on home page
    Given I visit the mobile web site as a guest user
    Then I verify ads are clickable on home page
