#Author: Mirna Silva
#Date Created: 04/20/2017
#Version One: https://www14.v1host.com/Macyscom/story.mvc/Summary?oidToken=Story%3A3255311


Feature: As a User I want to Update product, Add another product, Move product to Registry or Move product to my Wish List Page

  @MEWleanlab @BagEditPDP
  Scenario Outline: As a customer I want to "Update Bag" by editing the quantity value of a product on the Shopping bag Edit PDP page
    Given I visit the mobile web site as a guest user in <site_mode> mode
    When I directly add an available and orderable product "3048" to my bag in mobile site
    Then I should be in mobile shopping bag
    When I click on product image on "shopping_bag" page
    Then I should navigate to bag edit PDP page
    When I add "3" quantity to my bag from mobile standard PDP Page
    And I click on "Update Bag"
    Then I should be in mobile shopping bag
    And product quantity should be updated to "3"
    Examples:
      | site_mode |
      | domestic  |
      | iship     |

  @MEWleanlab @BagEditPDP
  Scenario Outline: As a customer I want to add another product to bag by editing the same product's color or size
    Given I visit the mobile web site as a guest user in <site_mode> mode
    When I directly add an available and orderable product "2452500" to my bag in mobile site
    Then I should be in mobile shopping bag
    When I click on product image on "shopping_bag" page
    Then I should navigate to bag edit PDP page
    When I select a different color on the bag edit PDP page
    And I click on "Add Another Item"
    Then I should be in mobile shopping bag
    And I should see a new product added to bag
  Examples:
  | site_mode |
  | domestic  |
  | iship     |

  @MEWleanlab @BagEditPDP
  Scenario: As a customer I want to move product from shopping bag to my Wish List
    Given I visit the mobile web site as a guest user in domestic mode
    When I directly add an available and orderable product "3048" to my bag in mobile site
    Then I should be in mobile shopping bag
    When I click on product image on "shopping_bag" page
    Then I should navigate to bag edit PDP page
    When I click on "Add to Wish List"
    Then I should verify that Wish List confirmation overlay displays
    When I navigate to shopping bag page using mobile website
    Then I should verify that product is not present on the shopping bag page

  @MEWleanlab @BagEditPDP
  Scenario Outline: As a customer I want to move product from shopping bag to my Registry
    Given I visit the mobile web site as a guest user in <site_mode> mode
    When I directly add an available and orderable product "3048" to my bag in mobile site
    Then I should be in mobile shopping bag
    When I click on product image on "shopping_bag" page
    Then I should navigate to bag edit PDP page
    When I click on "Add to Registry"
    Then I should verify that I am navigated to the Registry sign in page
    When I navigate to shopping bag page using mobile website
    Then I should verify that product is present on the shopping bag page
    Examples:
      | site_mode |
      | domestic  |
      | registry  |

  @MEWleanlab @BagEditPDP
  Scenario Outline: As a customer I want to "Update Bag" by clicking on button "Edit" on Shopping Bag page
    Given I visit the mobile web site as a guest user in <site_mode> mode
    When I directly add an available and orderable product "3048" to my bag in mobile site
    Then I should be in mobile shopping bag
    When I click on button Edit on shopping bag page
    Then I should navigate to bag edit PDP page
    When I add "3" quantity to my bag from mobile standard PDP Page
    And I click on "Update Bag"
    Then I should be in mobile shopping bag
    And product quantity should be updated to "3"
    Examples:
      | site_mode |
      | domestic  |
      | iship     |
