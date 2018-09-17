Feature: Verify selected quantity of the items on PDP

  @dsv_mew_sev1 @domain_selection
  Scenario: Verify the ability to select the quantity of product items on PDP page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Men          |
      | All Clothing |
      | Jeans        |
    And I navigate PDP of the first product
    And I should be on PDP page
    Then I should be able to change product quantity on PDP page
