Feature: Testing appium with Java

  @appium_experiment @scenario1
  Scenario: Global navigation tier-1 and tier-2 items
    Given I am on Home screen
    When I go to menu
    Then Menu screen is opened
    And The following elements are displayed
      | Shop      |
      | Offers    |
      | Account   |
      | Lists     |
      | Registry  |
      | Stores    |
      | More      |

  @appium_experiment @scenario2
  Scenario: Verify Stores search and see its details
    Given I am on Home screen
    Given I navigate to the first StoreDetailsPage for the search term "San Francisco"
    Then I verify I can search for a store and see its details

  @appium_experiment @scenario3 @t
  Scenario: Browse to product
    Given I am on Home screen
    And I navigate the app global navigation menu as follows:
      | Women    |
      | Clothing |
      | Dresses  |
    And I select a random product
