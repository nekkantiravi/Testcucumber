#Author: UFT team
#Date Created: 06/01/2017
#Date Signed Off:
#Version One: B-81097

Feature: As a product owner, I would like to ensure that "exceeded quantity limit for item" error message is not displayed on overlay when products have not exceeded the quantity limit.

  Scenario: Verify that exceeded quantity message is not displayed in BVR page when products have not exceeded the quantity limit
    Given I visit the web site as a registry user
    When I navigate to the "Cookware" browse page under "KITCHEN"
    And  I select a random member product
    When I replace product ID with available "399242" product ID
    And I add the product to a registry
    When I navigate to bvr page
    And I updated the need product quantity to 50
    And I add 8 quantity to bag
    And I click on continue shopping button in the overlay
    When I add 6 quantity to bag
    Then I should see exceeded quantity message in the overlay
    When I click on continue shopping button in the overlay
    And I add not exceeded product quantity 2 to bag
    Then I should be able to add product to bag
    And I should not see exceeded quantity message in the overlay


  Scenario: Verify that exceeded quantity message is not displayed in GVR page when products have not exceeded the quantity limit
    Given I visit website as guest user
    And I navigate to guest view registry page
    And I add 8 quantity to bag
    And I click on continue shopping button in the overlay
    When I add 6 quantity to bag
    Then I should see exceeded quantity message in the overlay
    When I click on continue shopping button in the overlay
    And I add not exceeded product quantity 2 to bag
    Then I should be able to add product to bag
    And I should not see exceeded quantity message in the overlay



