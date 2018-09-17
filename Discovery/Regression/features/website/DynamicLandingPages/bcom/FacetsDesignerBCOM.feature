Feature: Verifying Designer Facets in DLP

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Domestic|Iship|Registry - Designer Facet - Verify search box availability under designer facet when no of brands is 15 or less than 15
    Given I am on DynamicLandingPage in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When I navigate to DLP with "DESIGNER" facet and "less" than 15 brands
    And I verify that search box is displayed under Designer facet
    Examples:
      | mode     |
      | normal   |
      | registry |
      | iship    |

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Domestic|Iship|Registry - Designer Facet - Verify search box availability under designer facet when no of brands is more than 15
    Given I am on DynamicLandingPage in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When I navigate to DLP with "DESIGNER" facet and "more" than 15 brands
    Then I verify that search box is displayed under Designer facet
    Examples:
      | mode     |
      | normal   |
      | registry |
      | iship    |

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Domestic|Iship|Registry - Designer Facet - Verify search box functionality under designer facet when no of brands is 15 or less than 15
    Given I am on DynamicLandingPage in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When I navigate to DLP with "DESIGNER" facet and "less" than 15 brands
    And I verify that see default message "Find a Designer" in "Designer" facet
    When I search for 'available brand' keyword in brand facet section
    And I select "available brand" facet value from Brand facet section
    When I remove the selected facet from the breadcrumb
    Then I verify that brand search box is empty
    And I verify that see default message "Find a Designer" in "Designer" facet
    When I search for 'available brand' keyword in brand facet section
    Then I verify that brands are filtered with entered search term or characters
    Examples:
      | mode     |
      | normal   |
      | registry |
      | iship    |

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Domestic|Iship|Registry - Designer Facet - Verify search box functionality under designer facet when no of brands is more than 15
    Given I am on DynamicLandingPage in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When I navigate to DLP with "DESIGNER" facet and "more" than 15 brands
    And I verify that see default message "Find a Designer" in "Designer" facet
    And I search for 'available brand' keyword in brand facet section
    And I select "available brand" facet value from Brand facet section
    And I remove the selected facet from the breadcrumb
    Then I verify that brand search box is empty
    And I verify that see default message "Find a Designer" in "Designer" facet
    When I search for 'available brand' keyword in brand facet section
    Then I verify that brands are filtered with entered search term or characters
    Examples:
      | mode     |
      | normal   |
      | registry |
      | iship    |

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Domestic|Iship|Registry - Designer Facet - Verify error message is displayed when there are no designer facets for the entered term when no of brands is 15 or less than 15
    Given I am on DynamicLandingPage in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When I navigate to DLP with "DESIGNER" facet and "less" than 15 brands
    Then I verify that see default message "Find a Designer" in "Designer" facet
    And I verify that search box is displayed under Brand facet
    When I type a character "xtyz" in brand search box
    Then I verify that error message 'We couldn't find a match.' is displayed below search box
    Examples:
      | mode     |
      | normal   |
      | registry |
      | iship    |

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Domestic|Iship|Registry - Designer Facet - Verify error message is displayed when there are no designer facets for the entered term when no of brands is more than 15
    Given I am on DynamicLandingPage in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When I navigate to DLP with "DESIGNER" facet and "more" than 15 brands
    Then I verify that see default message "Find a Designer" in "Designer" facet
    And I verify that search box is displayed under Brand facet
    When I type a character "xtyz" in brand search box
    Then I verify that error message 'We couldn't find a match.' is displayed below search box
    Examples:
      | mode     |
      | normal   |
      | registry |
      | iship    |

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Domestic|Iship|Registry - Designer Facet - Verify only the designers that match with the entered character combination are displayed when no of brands is 15 or less than 15
    Given I am on DynamicLandingPage in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When I navigate to DLP with "DESIGNER" facet and "less" than 15 brands
    And  I type a character "ma" in brand search box
    Then I should see brand facets displayed according to the text contains "ma" entered in brand search box
    Examples:
      | mode     |
      | normal   |
      | registry |
      | iship    |

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Domestic|Iship|Registry - Designer Facet - Verify only the designers that match with the entered character combination are displayed when no of brands is more than 15
    Given I am on DynamicLandingPage in "<mode>" mode with designer facet
    And I clear existing class variable data to avoid data issues
    When I navigate to DLP with "DESIGNER" facet and "less" than 15 brands
    And  I type a character "ma" in brand search box
    Then I should see brand facets displayed according to the text contains "ma" entered in brand search box
    Examples:
      | mode     |
      | normal   |
      | registry |
      | iship    |

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Domestic|Iship|Registry - Designer Facet - Verify facet breadcrumb for single facet selection
    Given I am on DynamicLandingPage in "<mode>" mode with designer facet
    And I clear existing class variable data to avoid data issues
    When I select "single" facet value from "Designer" facet section
    And I verify that applied facet value is displayed
    Then I verify that products are filtered as per selected facet values
    And I verify that the product count is updated
    Examples:
      | mode     |
      | normal   |
      | registry |
      | iship    |
  # Notes:
  # Select single facet from designer facet section.
  # Verify facet breadcrumb with selected facet and 'CLEAR ALL' button not displayed.
  # Verify products are updated as per selected facet values.
  # Select another facet from designer facet section.
  # Verify facet breadcrumb with two selected facets and 'CLEAR ALL' button displayed.
  # Verify products are updated as per selected facet values.

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Domestic|Iship|Registry - Designer Facet - Verify facet breadcrumb for multiple facet selection
    Given I am on DynamicLandingPage in "<mode>" mode with designer facet
    And I clear existing class variable data to avoid data issues
    When I select "multiple" facet value from "Designer" facet section
    And I verify that applied facet value is displayed
    Then I verify that products are filtered as per selected facet values
    And I verify that the product count is updated
    Examples:
      | mode     |
      | normal   |
      | registry |
      | iship    |

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Domestic|Iship|Registry - Designer Facet - Verify facet persistence for single facet selection
    Given I am on DynamicLandingPage in "<mode>" mode with designer facet
    And I clear existing class variable data to avoid data issues
    When I select "single" facet value from "Designer" facet section
    Then I verify that applied facet value is displayed
    Then I verify that products are filtered as per selected facet values
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on DLP on same product grid point
    When I select another facet value from "Item Type" facet
    Then I verify that products are filtered as per selected facet values
    Examples:
      | mode     |
      | normal   |
      | registry |
      | iship    |

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Domestic|Iship|Registry - Designer Facet - Verify facet persistence for multiple facet selection
    Given I am on DynamicLandingPage in "<mode>" mode with designer facet
    And I clear existing class variable data to avoid data issues
    When I select "multiple" facet value from "Designer" facet section
    Then I verify that applied facet value is displayed
    Then I verify that products are filtered as per selected facet values
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on DLP on same product grid point
    When I navigate to top of page
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that previous sort by selection persist
    And I verify that previous pagination selection persist
    Examples:
      | mode     |
      | normal   |
      | registry |
      | iship    |

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Domestic|Iship|Registry - Designer Facet - Verify that selected facets are separated in facet panel for single facet selection
    Given I am on DynamicLandingPage in "<mode>" mode with designer facet
    And I clear existing class variable data to avoid data issues
    When I select "single" facet value from "Designer" facet section
    Then I verify that applied facet value is displayed
    And I verify that selected facets are separated from inactive facets
    Examples:
      | mode     |
      | normal   |
      | registry |
      | iship    |
  # Notes:
  # Select single designer facet from designer facet section
  # Verify that selected designer facets are displayed separately under facet panel.

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Domestic|Iship|Registry - Designer Facet - Verify selected facets are separated in facet panel for multiple facet selection
    Given I am on DynamicLandingPage in "<mode>" mode with designer facet
    And I clear existing class variable data to avoid data issues
    When I select "multiple" facet value from "Designer" facet section
    Then I verify that applied facet value is displayed
    And I verify that selected facets are separated from inactive facets
    Examples:
      | mode     |
      | normal   |
      | registry |
      | iship    |

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Domestic|Iship|Registry - Designer Facet - Verify facet persistence after sot by and pagination
    Given I am on DynamicLandingPage in "<mode>" mode with designer facet
    And I clear existing class variable data to avoid data issues
    When I select "multiple" facet value from "Designer" facet section
    Then I verify that applied facet value is displayed
    Then I verify that products are filtered as per selected facet values
    When I select "New Arrivals" in sort by drop down
    And I verify that the Sort By "New Arrivals" functionality
    And I verify that products are updated with selected sort option
    Then I verify that previously selected facets persists in breadcrumb
    When I select 'random' page number from pagination
    Then I verify that previously selected facets persists in breadcrumb
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on DLP on same product grid point
    When I navigate to top of page
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that previous sort by selection persist
    And I verify that previous pagination selection persist
    Examples:
      | mode     |
      | normal   |
      | registry |
      | iship    |
  # Notes:
  # Select single/multiple designer facet from facet section
  # Select any sort by option
  # Verify selected designer facets are persisted after sort by also.
  # Select pagination next arrow.
  # Verify selected designer facets are persisted after pagination also.
  # Select any product from thumbnail grid.
  # Select browser back button
  # Verify selected designer, sort by and pagination options are persisted.

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Domestic|Iship|Registry - Designer Facet - Verify clear all button
    Given I am on DynamicLandingPage in "<mode>" mode with designer facet
    And I clear existing class variable data to avoid data issues
    When I select "multiple" facet value from "Designer" facet section
    Then I verify that products are filtered as per selected facet values
    And I verify that applied facet value is displayed
    When I click on clear all button
    Then I verify that the product count returns to original
    Examples:
      | mode     |
      | normal   |
      | registry |
      | iship    |
