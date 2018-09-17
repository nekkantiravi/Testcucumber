Feature: PDPxAPI

  @done
  Scenario: Verify pdp page against pdp service and fcc service
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop         |
      | Men          |
      | All Clothing |
      | Jeans        |
    And I select a random member product using mobile website
    And I store product information
    Then I call pdp service and verify product info

  @done
  Scenario: Verify pdp page against pdp service and fcc service availability
    Given I visit mobile pdp page with "2809262" product
    And I store product information
    Then I verify pdp service for "2809262" rug product

  @done
  Scenario: Verify the error messages while adding a product to the wish list
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop         |
      | Men          |
      | All Clothing |
      | Jeans        |
    And I select a random member product using mobile website
    And I tap on Add to Wish List button on PDP page without selecting upc combination
    And I should be prompted with the standard error toast message "Please select a size."

  @done
  Scenario: Verify pdp service against fcc service availability
    Given I call pdp service for "2809262" product
    Then I verify pdp service for "2809262" rug product against fcc service

    @done
    Scenario: As a mobile user i should see color name displayed for a product
      Given I visit the mobile web site as a guest user
      When I search for "bedding collection" global Search Input Field
      And I navigate to master PDP page that has multiple color swatches
      Then all the available colors should be displayed for the members

  @done
  Scenario: As a mobile user i should see color name/size displayed for a product
    Given I visit the mobile web site as a guest user
    When I search for "Men Jeans" global Search Input Field
    And I navigate to member PDP page that has multiple color/size combination
    Then I verify available colors and sizes should be displayed
