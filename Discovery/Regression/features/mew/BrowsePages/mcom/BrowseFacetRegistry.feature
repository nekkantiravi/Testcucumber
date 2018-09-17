Feature: Tests for browse facets in registry mode

  @domain_mew_discovery @use_mew_regression
  Scenario: BrowsePage - User selects facet value within Special Offers facet on browse page for registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Kitchen Gadgets |
    When I click on filter link
    When I select facet name "Special Offers"
    And I select facet value "Clearance/Closeout"
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in browse page with selected facet values in registry mode
    When I refresh the page
    When I click on filter link
    Then I should see the facet values selected
    When I click on back button
    Then I should see only products in browse page with selected facet values in registry mode
    When I remove the facets from the breadcrumb
    Then I should see the products without any facet values in browse page in registry mode

  @domain_mew_discovery @use_mew_regression
  Scenario: BrowsePage - User selects multiple facet values within Special Offers facet on browse page for registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Kitchen Gadgets |
    When I click on filter link
    When I select facet name "Special Offers"
    And I select multiple facets
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in browse page with selected multiple facet values in registry mode
    When I refresh the page
    When I click on filter link
    Then I should see the facet values selected
    When I click on back button
    Then I should see only products in browse page with selected multiple facet values in registry mode
    When I remove the facets from the breadcrumb
    Then I should see the products without any facet values in browse page in registry mode

  @domain_mew_discovery @use_mew_regression
  Scenario: BrowsePage - User selects multiple facet values within multiple facets on browse page for registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Kitchen Gadgets |
    When I click on filter link
    When I select facet name "Special Offers"
    And I select multiple facets
    Then I should see the facet values selected
    When I select facet name "Kitchen Linen Type"
    And I select multiple facets
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in browse page with selected multiple facet values in registry mode
    When I refresh the page
    When I click on filter link
    Then I should see the facet values selected
    When I click on back button
    Then I should see only products in browse page with selected multiple facet values in registry mode
    When I remove the facets from the breadcrumb
    Then I should see the products without any facet values in browse page in registry mode

  @domain_mew_discovery @use_mew_regression
  Scenario: BrowsePage - User selects facet value within Kitchen Linen Type facet on browse page for registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Kitchen Gadgets |
    When I click on filter link
    When I select facet name "Kitchen Linen Type"
    And I select facet value "Kitchen Linens"
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in browse page with selected facet values in registry mode
    When I refresh the page
    When I click on filter link
    Then I should see the facet values selected
    When I click on back button
    Then I should see only products in browse page with selected facet values in registry mode
    When I remove the facets from the breadcrumb
    Then I should see the products without any facet values in browse page in registry mode

  @domain_mew_discovery @use_mew_regression
  Scenario: BrowsePage - User selects multiple facet values within Kitchen Linen Type facet on browse page for registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Kitchen Gadgets |
    When I click on filter link
    When I select facet name "Kitchen Linen Type"
    And I select multiple facets
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in browse page with selected multiple facet values in registry mode
    When I refresh the page
    When I click on filter link
    Then I should see the facet values selected
    When I click on back button
    Then I should see only products in browse page with selected multiple facet values in registry mode
    When I remove the facets from the breadcrumb
    Then I should see the products without any facet values in browse page in registry mode

  @domain_mew_discovery @use_mew_regression
  Scenario: BrowsePage - User selects facet value within Brand facet on browse page for registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Kitchen Gadgets |
    When I click on filter link
    When I select facet name "Brand"
    And I select facet value "WellnessMats"
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in browse page with selected facet values in registry mode
    When I refresh the page
    When I click on filter link
    Then I should see the facet values selected
    When I click on back button
    Then I should see only products in browse page with selected facet values in registry mode
    When I remove the facets from the breadcrumb
    Then I should see the products without any facet values in browse page in registry mode

  @domain_mew_discovery @use_mew_regression
  Scenario: BrowsePage - User selects multiple facet values within Brand facet on browse page for registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Kitchen Gadgets |
    When I click on filter link
    When I select facet name "Brand"
    And I select multiple facets
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in browse page with selected multiple facet values in registry mode
    When I refresh the page
    When I click on filter link
    Then I should see the facet values selected
    When I click on back button
    Then I should see only products in browse page with selected multiple facet values in registry mode
    When I remove the facets from the breadcrumb
    Then I should see the products without any facet values in browse page in registry mode

  @domain_mew_discovery @use_mew_regression
  Scenario: BrowsePage - User selects facet value within Customers Top Rated facet on browse page for registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Kitchen Gadgets |
    When I click on filter link
    When I select facet name "Customers Top Rated"
    And I select facet value "[5.0 TO *]"
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in browse page with selected facet values in registry mode
    When I refresh the page
    When I click on filter link
    Then I should see the facet values selected
    When I click on back button
    Then I should see only products in browse page with selected facet values in registry mode
    When I remove the facets from the breadcrumb
    Then I should see the products without any facet values in browse page in registry mode

  @domain_mew_discovery @use_mew_regression
  Scenario: BrowsePage - User selects multiple facet values within Customers Top Rated facet on browse page for registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Kitchen Gadgets |
    When I click on filter link
    When I select facet name "Customers Top Rated"
    And I select multiple facets
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in browse page with selected multiple facet values in registry mode
    When I refresh the page
    When I click on filter link
    Then I should see the facet values selected
    When I click on back button
    Then I should see only products in browse page with selected multiple facet values in registry mode
    When I remove the facets from the breadcrumb
    Then I should see the products without any facet values in browse page in registry mode

  @domain_mew_discovery @use_mew_regression
  Scenario: BrowsePage - User selects facet value within Price facet on browse page for registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Kitchen Gadgets |
    When I click on filter link
    When I select facet name "Price"
    And I select facet value "$50 - $100"
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in browse page with selected facet values in registry mode
    When I refresh the page
    When I click on filter link
    Then I should see the facet values selected
    When I click on back button
    Then I should see only products in browse page with selected facet values in registry mode
    When I remove the facets from the breadcrumb
    Then I should see the products without any facet values in browse page in registry mode

  @domain_mew_discovery @use_mew_regression
  Scenario: BrowsePage - User selects multiple facet values within Price facet on browse page for registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Kitchen Gadgets |
    When I click on filter link
    When I select facet name "Price"
    And I select multiple facets
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in browse page with selected multiple facet values in registry mode
    When I refresh the page
    When I click on filter link
    Then I should see the facet values selected
    When I click on back button
    Then I should see only products in browse page with selected multiple facet values in registry mode
    When I remove the facets from the breadcrumb
    Then I should see the products without any facet values in browse page in registry mode

  @domain_mew_discovery @use_mew_regression
  Scenario: BrowsePage - User selects facet value within Special Offers facet and Verify product persistence when navigating back from PDP in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Kitchen Gadgets |
    When I click on filter link
    When I select facet name "Special Offers"
    And I select facet value "Clearance/Closeout"
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in browse page with selected facet values in registry mode
    And I navigate to a random PDP page on browse page
    When I click on back button on PDP page and navigate to browse page
    Then I should see only products in browse page with selected facet values in registry mode

  @domain_mew_discovery @use_mew_regression
  Scenario: BrowsePage - User selects multiple facet values within Special Offers facet Verify product persistence when navigating back from PDP in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Kitchen Gadgets |
    When I click on filter link
    When I select facet name "Special Offers"
    And I select multiple facets
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in browse page with selected multiple facet values in registry mode
    And I navigate to a random PDP page on browse page
    When I click on back button on PDP page and navigate to browse page
    Then I should see only products in browse page with selected multiple facet values in registry mode

  @domain_mew_discovery @use_mew_regression
  Scenario: BrowsePage - User selects facet value within Price facet and Verify product persistence when navigating back from PDP in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Kitchen Gadgets |
    When I click on filter link
    When I select facet name "Price"
    And I select facet value "$50 - $100"
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in browse page with selected facet values in registry mode
    And I navigate to a random PDP page on browse page
    When I click on back button on PDP page and navigate to browse page
    Then I should see only products in browse page with selected facet values in registry mode

  @domain_mew_discovery @use_mew_regression
  Scenario: BrowsePage - User selects multiple facet values within Price facet Verify product persistence when navigating back from PDP in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Kitchen Gadgets |
    When I click on filter link
    When I select facet name "Price"
    And I select multiple facets
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in browse page with selected multiple facet values in registry mode
    And I navigate to a random PDP page on browse page
    When I click on back button on PDP page and navigate to browse page
    Then I should see only products in browse page with selected multiple facet values in registry mode

  @domain_mew_discovery @use_mew_regression
  Scenario: BrowsePage - User selects multiple facet values within Price facet on browse page and verifies clear all functionality in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Kitchen Gadgets |
    When I click on filter link
    When I select facet name "Price"
    And I select multiple facets
    Then I should see the facet values selected
    When I click on back button
    Then I should see the apply selection overlay with apply and cancel buttons
    When I click the cancel button
    Then I should see the products without any facet values in browse page in registry mode
    When I click on filter link
    When I select facet name "Price"
    And I select multiple facets
    Then I should see the facet values selected
    When I click on back button
    Then I should see the apply selection overlay with apply and cancel buttons
    Then I click on Apply button on apply selection overlay
    Then I should see only products in browse page with selected multiple facet values in registry mode

  @domain_mew_discovery @use_mew_regression
  Scenario: BrowsePage - User selects facet value within Price facet on browse page and verifies pagination in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Kitchen Gadgets |
    When I click on filter link
    When I select facet name "Price"
    And I select facet value "$50 - $100"
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in browse page with selected facet values in registry mode
    And I navigate to a random page other than first page
    Then I should see only products in browse page with selected facet values in registry mode
    When I remove the facet from the breadcrumb
    And current page number should be equal 1
    Then I should see the products without any facet values in browse page in registry mode

  @domain_mew_discovery @use_mew_regression
  Scenario: BrowsePage - User selects multiple facet values within Price facet on browse page and verifies pagination in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Kitchen Gadgets |
    When I click on filter link
    When I select facet name "Price"
    And I select multiple facets
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in browse page with selected multiple facet values in registry mode
    And I navigate to a random page other than first page
    Then I should see only products in browse page with selected multiple facet values in registry mode
    When I remove the facet from the breadcrumb
    And current page number should be equal 1
    Then I should see only products in browse page with selected multiple facet values in registry mode

  @domain_mew_discovery @use_mew_regression
  Scenario: BrowsePage - Verify clear all functionality when user selects single facet value within random facet on registry browse page.
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Kitchen Gadgets |
    When I click on filter link
    When I select random facet on facet accordion model
    And I select random facet value on facet accordion model
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in browse page with selected facet values in registry mode
    When I click on filter link
    Then I should see the facet values selected
    When I click the clear all button
    When I click on Apply button
    Then I should see the products without any facet values in browse page in registry mode

  @domain_mew_discovery @use_mew_regression
  Scenario: BrowsePage - Verify clear all functionality when user selects multiple facet values within random facet on registry browse page.
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Kitchen Gadgets |
    When I click on filter link
    When I select random facet on facet accordion model
    And I select multiple facets
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in browse page with selected multiple facet values in registry mode
    When I click on filter link
    Then I should see the facet values selected
    When I click the clear all button
    When I click on Apply button
    Then I should see the products without any facet values in browse page in registry mode

  @domain_mew_discovery @use_mew_regression
  Scenario: BrowsePage - User selects facet value within Special Offers facet on browse page and all facet values should update accordingly in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Kitchen Gadgets |
    When I click on filter link
    When I select facet name "Special Offers"
    And I select facet value "Clearance/Closeout"
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in browse page with selected facet values in registry mode
    When I click on filter link
    Then I should see facet headers and values updated based on previous selection in browse page for registry mode

  @domain_mew_discovery @use_mew_regression
  Scenario: BrowsePage - Verify user can able to select facet value from each available facets in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Kitchen Gadgets |
    Then I should be on the registry browse page
    And I click on filter link
    And I select single facet value from each available facets
    And I click on Apply button
    Then I should be on the registry browse page
    And I should see only products in browse page with selected multiple facet values in registry mode
    And I refresh the page
    Then I should see only products in browse page with selected multiple facet values in registry mode
    When I remove the facets from the breadcrumb
    Then I should see the products without any facet values in browse page in registry mode