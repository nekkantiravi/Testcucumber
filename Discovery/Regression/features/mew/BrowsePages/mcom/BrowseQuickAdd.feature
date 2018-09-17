Feature: As a user I should be able to use quick add feature in browse pages to add a product to the bag

  ### Domestic and Iship ###

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BrowsePage - Go to Category browse page and verify quick add buttons
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate the global navigation menu as follows:
      | Shop    |
      | Women   |
      | Dresses |
    Then I should be on the browse page
    Then I should see quick add button for every member product on browse page
    When I tap on the quick add button on a random product
    Then I should see the quick add overlay on the browse page
    And I should see the quick add overlay with all elements as per the response on <mode> mode
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BrowsePage - Go to browse page and verify quick add overlay to close after adding item to bag
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate the global navigation menu as follows:
      | Shop    |
      | Women   |
      | Dresses |
    Then I should be on the browse page
    Then I should see quick add button for every member product on browse page
    When I tap on the quick add button on a random product
    Then I should see the quick add overlay on the browse page
    And I should see the quick add overlay with all elements as per the response on <mode> mode
    When I tap on add to bag button in quick add overlay
    Then I should not see the quick add overlay on the browse page
    And I should see product is added to bag
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BrowsePage - Verify  page is navigating to PDP when user selects see product details link on quick add
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate the global navigation menu as follows:
      | Shop    |
      | Women   |
      | Dresses |
    Then I should be on the browse page
    Then I should see quick add button for every member product on browse page
    When I tap on the quick add button on a random product
    Then I should see the quick add overlay on the browse page
    When I tap on see product details link
    Then I should be on PDP page
    Examples:
      | mode     |
      | domestic |
      | iship    |

    ### Registry ###

  @domain_mew_discovery @use_mew_regression
  Scenario: BrowsePage - Go to Category browse page and verify quick add buttons on registry browse
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Bakeware        |
    Then I should be on the browse page
    Then I should see quick add button for every member product on browse page
    When I tap on the quick add button on a random product
    Then I should see the quick add overlay on the browse page
    And I should see the quick add overlay with all elements as per the response on registry mode

  @domain_mew_discovery @use_mew_regression
  Scenario: BrowsePage - Go to browse page and verify quick add overlay to close after adding item to bag on registry browse
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Bakeware        |
    Then I should be on the browse page
    Then I should see quick add button for every member product on browse page
    When I tap on the quick add button on a random product
    Then I should see the quick add overlay on the browse page
    And I should see the quick add overlay with all elements as per the response on registry mode
    When I tap on add to bag button in quick add overlay
    Then I should not see the quick add overlay on the browse page
    And I should see product is added to bag

  @domain_mew_discovery @use_mew_regression
  Scenario: BrowsePage - Verify  page is navigating to PDP when user selects see product details link on quick add on registry browse
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Bakeware        |
    Then I should be on the browse page
    Then I should see quick add button for every member product on browse page
    When I tap on the quick add button on a random product
    Then I should see the quick add overlay on the browse page
    When I tap on see product details link
    Then I should be on registry PDP page