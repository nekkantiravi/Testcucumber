Feature: As a product owner, I would like to have Last Act under Price Type 8 appear as badge text for products on PDP,Wishlist pages

  @Mew_UFT @release_17H @domain_selection @project_UFT
  Scenario: verify that the Last Act is displayed on PDP in Domestic Mode
    Given I visit the mobile web site as a guest user in domestic mode
    And I search using mobile website for "All Men's shoes"
    And I click filter of search and browse page
    And I click on special offers button
    And I select the option Last Act
    And I click on the apply button on Sort and Filter Page
    And I navigate PDP of the first product
    Then I should see "LAST ACT" message on PDP

  @Mew_UFT @release_17H @domain_selection @project_UFT
  Scenario: verify that the Last Act is displayed on WishList Page in Domestic Mode
    Given I visit the mobile web site as a guest user in domestic mode
    And I search using mobile website for "All Men's shoes"
    And I click filter of search and browse page
    And I click on special offers button
    And I select the option Last Act
    And I click on the apply button on Sort and Filter Page
    And I navigate PDP of the first product
    And I select product related attributes from PDP using mobile website
    And I click Add to Wish List button on PDP using mobile website
    And I click on view list in ATW overlay from PDP using mobile website
    Then I should see "LAST ACT" message on WishList Page

  @Mew_UFT @release_17H @domain_selection @project_UFT
  Scenario: verify that the Last Act is displayed on PDP in iShip Mode
    Given I visit the mobile web site as a guest user in iship mode
    And I search using mobile website for "All Men's shoes"
    And I click filter of search and browse page
    And I click on special offers button
    And I select the option Last Act
    And I click on the apply button on Sort and Filter Page
    And I navigate PDP of the first product
    Then I should see "LAST ACT" message on PDP

  @Mew_UFT @release_17H @domain_selection @project_UFT
  Scenario: verify that the Last Act is displayed on PDP in Registry Mode
    Given I visit the mobile web site as a guest user in registry mode
    And I search using mobile website for "Kitchenware"
    And I click filter of search and browse page
    And I click on sales & offers button
    And I select the option Last Act
    And I click on the apply button on Sort and Filter Page
    And I navigate PDP of the first product
    Then I should see "LAST ACT" message on PDP