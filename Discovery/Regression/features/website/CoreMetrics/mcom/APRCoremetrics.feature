Feature: Verify Coremetrics tagging for autocomplete product recommendations functionality in DOMESTIC mode

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario: HeaderAndFooter - HomePage - Verify link click tag is fired when a user enters 2 characters in search box
    Given I visit the web site as a guest user in "domestic" mode
    And I set the "2132" cookie
    When I enter "je" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    And  I should see APR panel with "Best Sellers" header and "4" APR at max

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario: HeaderAndFooter - PDP - Verify Coremetrics tagging of a product view tag 5 is fired when a user navigate to PDP page in domestic mode
    Given I visit the web site as a guest user in "domestic" mode
    And I set the "2132" cookie
    When I enter "je" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    And I should see APR panel with "Best Sellers" header and "4" APR at max
    And I select random APR and navigate to PDP

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario: HeaderAndFooter - AddToBagPage - Verify Coremetrics tagging of a shop 5 tag fired when a user clicks on add to bag button in domestic mode
    Given I visit the web site as a guest user in "domestic" mode
    And I set the "2132" cookie
    When I enter "sh" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    And I should see APR panel with "Best Sellers" header and "4" APR at max
    And I select random APR and navigate to PDP
    And I add product to my bag from standard PDP