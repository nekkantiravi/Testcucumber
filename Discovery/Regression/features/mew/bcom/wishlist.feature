 Feature: Verify the wishlist functionality on search results and browse pages

   @domain_mew_discovery @use_mew_regression
   Scenario: Verify the wishlist functionality on search results page
     Given I visit the mobile web site as a guest user in domestic mode
     When I search using mobile website for "shoes"
     Then I should be in Search Landing page using mobile website
     And I should see the wishlist image wrapper for the products displayed

   @domain_mew_discovery @use_mew_regression
   Scenario: Verify the add to wishlist overlay on selection of product on search results page
     Given I visit the mobile web site as a guest user in domestic mode
     When I search using mobile website for "jeans"
     Then I should see the wishlist image wrapper for the products displayed
     When I click on the wishlist image wrapper for a random product
     Then I should see Add to wish list overlay

   @domain_mew_discovery @use_mew_regression
   Scenario: Verify the add to wishlist overlay on selection of product on search results page
     Given I visit the mobile web site as a guest user in domestic mode
     When I search using mobile website for "jeans"
     Then I should see the wishlist image wrapper for the products displayed
     When I click on the wishlist image wrapper for a random product
     Then I should see Add to wish list overlay
     When I select a dropdown value
     And I click on Add to wish list button
     Then I should see the item added to wish list

   @domain_mew_discovery @use_mew_regression
   Scenario: Verify the wishlist functionality on browse page
     Given I visit the mobile web site as a guest user in domestic mode
     When I navigate the global navigation menu as follows:
       | Men       |
       | Pants     |
     Then I should see the "category_browse" Page
     And I should see the wishlist image wrapper for the products displayed

   @domain_mew_discovery @use_mew_regression
   Scenario: Verify the add to wishlist overlay is displayed on selection of product on browse page
     Given I visit the mobile web site as a guest user in domestic mode
     When I navigate the global navigation menu as follows:
       | Men       |
       | Pants     |
     Then I should see the wishlist image wrapper for the products displayed
     When I click on the wishlist image wrapper for a random product
     Then I should see Add to wish list overlay

    @domain_mew_discovery @use_mew_regression
    Scenario: Verify the add to wishlist overlay on selection of product on browse page
      Given I visit the mobile web site as a guest user in domestic mode
      When I navigate the global navigation menu as follows:
        | Men       |
        | Pants     |
      Then I should see the wishlist image wrapper for the products displayed
      When I click on the wishlist image wrapper for a random product
      Then I should see Add to wish list overlay
      When I select a dropdown value
      And I click on Add to wish list button
      Then I should see the item added to wish list