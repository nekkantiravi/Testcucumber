#Mobile Lean Lab BCOM
#Created: 10/9/2017
#Author: Sankeerth Kakarna
#Version-one Story: B-95028

Feature: BCOM::Persistent Filters on Wish List

  @B-95028 @MEW @bcom @domain_Selection
  Scenario: I verify the color filters persists on Wish List modal
    Given I visit the mobile web site as a guest user
    When I search for "polos"
    When I select random color from filters
    And I apply the filters
    And I click on Wish List modal
    Then I verify that color on modal matches with filters

  @B-95028 @MEW @bcom @domain_Selection
  Scenario: I verify the size filters persists on Wish List modal
    Given I visit the mobile web site as a guest user
    When I search for "polos"
    When I select random size from filters
    And I apply the filters
    And I click on Wish List modal
    Then I verify that size on modal matches with filters

  @B-95028 @MEW @bcom @domain_Selection
  Scenario: I verify the color and size filters persists on Wish List modal
    Given I visit the mobile web site as a guest user
    When I search for "polos"
    When I select random size from filters
    And I apply the filters
    When I select random color from filters
    And I apply the filters
    And I click on Wish List modal
    Then I verify that color on modal matches with filters
    Then I verify that size on modal matches with filters

  @B-95028 @MEW @bcom @domain_Selection
  Scenario: verify that when two sizes are selected in filters, then no size is persisted
    Given I visit the mobile web site as a guest user
    When I search for "polos"
    When I select random size from filters
    And I apply the filters
    When I select random size from filters
    And I apply the filters
    And I click on Wish List modal
    Then I verify that no size is displayed on Wish List

