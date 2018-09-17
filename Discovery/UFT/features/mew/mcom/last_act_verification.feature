Feature: As a product owner, I would like to have Last Act under Price Type 8 appear as badge text for products in search/browse pages

  @Mew_UFT @release_17H @domain_discovery @project_UFT
  Scenario: verify that the Last Act is displayed on SRP in Domestic Mode
    Given I visit the mobile web site as a guest user in domestic mode
    And I search using mobile website for "All Men's shoes"
    And I click filter of search and browse page
    And I click on special offers button
    And I select the option Last Act
    And I click on the apply button on Sort and Filter Page
    Then I should see LAST ACT message on all the products of SRP

  @Mew_UFT @release_17H @domain_discovery @project_UFT
  Scenario: verify that the Last Act is displayed on SRP in iShip Mode
    Given I visit the mobile web site as a guest user in iship mode
    And I search using mobile website for "All Men's shoes"
    And I click filter of search and browse page
    And I click on special offers button
    And I select the option Last Act
    And I click on the apply button on Sort and Filter Page
    Then I should see LAST ACT message on all the products of SRP

  @Mew_UFT @release_17H @domain_discovery @project_UFT
  Scenario: verify that the Last Act is displayed on SRP in Registry Mode
    Given I visit the mobile web site as a guest user in registry mode
    And I search using mobile website for "Kitchenware"
    And I click filter of search and browse page
    And I click on sales & offers button
    And I select the option Last Act
    And I click on the apply button on Sort and Filter Page
    Then I should see LAST ACT message on all the products of SRP
