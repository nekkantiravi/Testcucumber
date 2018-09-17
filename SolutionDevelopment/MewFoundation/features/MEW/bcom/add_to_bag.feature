Feature: Add to bag scenarios

  @mew_foundation @ifs
  Scenario:Verify add to bag flow from the global navigation with guest user for member product
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Men                  |
      | All Clothing         |
      | Active & Workout     |
    And I select a random member product using mobile website
    Then I should be redirected to PDP page using mobile website
    When I add product to my bag from standard PDP page
    Then I should be redirected to ATB page using mobile website
    When I navigate to shopping bag page from add to bag page using mobile website
    Then I should be in mobile shopping bag
    And I should see following elements on "shopping_bag" page:
      | line_items  |
      | item_title  |
      | item_image  |
      | remove_item |

  @mew_foundation @ifs
  Scenario: As a guest user,Verify add to bag flow from global navigation for master product
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Home                |
      | Bedding             |
      | Bedding Collections |
    And I select a random master product using mobile website
    Then I should be redirected to master PDP page in mobile website
    And I should see member products listed in mobile website
    When I add member product from PDP and select "checkout" using mobile website
    Then I should be in mobile shopping bag
