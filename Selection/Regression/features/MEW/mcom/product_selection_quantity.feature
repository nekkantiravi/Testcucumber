Feature: I want the ability to select the quantity of the product I am interested in so that I can purchase the desired amount all at once

  @dsv_mew_sev2 @domain_selection
  Scenario: Verify the ability to see maximum available quantity of product items on master collection page
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate the global navigation menu as follows:
      | Shop                |
      | For The Home        |
      | Bed & Bath          |
      | Bedding             |
      | Bedding Collections |
    When I select color swatch product
    And I select first member product of master product
    And I should see maximum available quantity of product on pdp
