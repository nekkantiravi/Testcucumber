Feature: Browse page functionality

  @domain_mew_discovery @use_mew_regression
  Scenario: Navigate to random domestic browse page
    Given I visit the mobile web site as a guest user in domestic mode
    Then I randomly navigate to any browse page by clicking on child categories of below "MEN"

  @domain_mew_discovery @use_mew_regression
  Scenario: Navigate to random iship browse page
    Given I visit the mobile web site as a guest user in iship mode
    Then I randomly navigate to any browse page by clicking on child categories of below "KIDS"

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Navigate to random registry browse page
    Given I visit the mobile web site as a guest user in registry mode
    Then I verify the below "<option>" under registry menu from registry home page
    And I verify child categories of below parent "<option>" are visible
    And I randomly navigate to any registry browse page by clicking on child categories of below "<option>"
    Examples:
      | option                |
      | Add Gifts to Registry |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify browse page navigation by url for domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    Then I navigate to "browse" page by url "<url>"
    And I verify browse page data is displayed correctly for category in "domestic" mode
    Examples:
      | url                                                  |
      | /shop/kids/boys-shoes?id=1003143                     |
      | /shop/beauty/estee-lauder-skin-care-products?id=7657 |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify browse page navigation by url for iship mode
    Given I visit the mobile web site as a guest user in iship mode
    Then I navigate to "browse" page by url "<url>"
    And I verify browse page data is displayed correctly for category in "iship" mode
    Examples:
      | url                                                   |
      | /shop/kids/boys-shoes?id=1003143                      |
      | /shop/womens-designer-shoes/mid-calf-boots?id=1005935 |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify browse page navigation by url for registry mode
    Given I visit the mobile web site as a guest user in registry mode
    Then I navigate to "registry" page by url "<url>"
    And I verify browse page data is displayed correctly for category in "registry" mode
    Examples:
      | url                                                      |
      | /shop/wedding-registry/home/dining-entertaining?id=8237  |
      | /shop/wedding-registry/home/skillets-frying-pans?id=8155 |
