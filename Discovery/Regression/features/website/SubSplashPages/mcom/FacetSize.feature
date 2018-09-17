# Author: Discovery Regression QE Team
# Created Date: 10/10/2017

Feature: Verifying Size Facets in SubSplashPage

  @use_regression @domain_discovery @priority_medium @mode_domestic @feature_subsplash_page
  Scenario: SubSplashPage - Domestic - Verify the "FORWARDPAGE_KEY" page cookie on selecting facet refinements
    Given I visit the web site as a guest user in "domestic" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "Activewear" sub splash page under "WOMEN"
    When I select 1 facet value(s) from 'any' facet sections
    Then I verify that selected facet value in FORWARDPAGE_KEY cookie
    When I select Sign In link from header and sign in from the current page
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that selected facet value in FORWARDPAGE_KEY cookie

  @use_regression @domain_discovery @priority_medium @mode_iship @feature_subsplash_page @discovery_daily_run
  Scenario: SubSplashPage - Iship - Verify the "FORWARDPAGE_KEY" page cookie on selecting facet refinements
    Given I visit the web site as a guest user in "iship" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "Activewear" sub splash page under "MEN"
    When I select 1 facet value(s) from 'any' facet sections
    Then I verify that selected facet value in FORWARDPAGE_KEY cookie
    When I select 'customer service' link from header
    And I select browse 'back' button
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that selected facet value in FORWARDPAGE_KEY cookie

  @use_regression @domain_discovery @priority_medium @mode_domestic @feature_subsplash_page @discovery_daily_run
  Scenario: SubSplashPage - Domestic - Verify the "FORWARDPAGE_KEY" page cookie on deselecting facet refinements
    Given I visit the web site as a guest user in "domestic" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "Activewear" sub splash page under "WOMEN"
    Then I select 4 facet value(s) from 'any' facet sections
    And I deselect 2 facet value(s)
    Then I verify that selected facet value in FORWARDPAGE_KEY cookie
    When I select Sign In link from header and sign in from the current page
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that selected facet value in FORWARDPAGE_KEY cookie

  @use_regression @domain_discovery @priority_medium @mode_iship @feature_subsplash_page
  Scenario: SubSplashPage - Iship - Verify the "FORWARDPAGE_KEY" page cookie on deselecting facet refinements
    Given I visit the web site as a guest user in "iship" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "Activewear" sub splash page under "MEN"
    Then I select 4 facet value(s) from 'any' facet sections
    And I deselect 2 facet value(s)
    Then I verify that selected facet value in FORWARDPAGE_KEY cookie
    When I select 'customer service' link from header
    And I select browse 'back' button
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that selected facet value in FORWARDPAGE_KEY cookie

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @feature_subsplash_page
  Scenario Outline: SubSplashPage - Domestic|Iship - Verify the size facet header in DOMESTIC and ISHIP mode
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    Then I verify that "Size" facet is listed on left nav
    When I click on "Size" facet header on left nav
    Then I verify that new size facet family is displayed
    When I select a random facet sub header
    Then I verify that facet sub header gets "Expanded"
    And I verify that the facet values are displayed
    Examples:
      | mode     | subcategory | category |
      | domestic | Activewear  | WOMEN    |
      | iship    | Activewear  | MEN      |

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @feature_subsplash_page
  Scenario Outline: SubSplashPage - Domestic|Iship - Verify the size facet functionality in DOMESTIC and ISHIP mode
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    Then I verify that "Size" facet is listed on left nav
    When I click on "Size" facet header on left nav
    Then I verify that new size facet family is displayed
    When I select a random facet sub header
    Then I verify that facet sub header gets "Expanded"
    And I verify that the facet values are displayed
    When I select already selected facet sub header
    Then I verify that facet sub header gets "Collapsed"
    And I verify that the facet values are not displayed
    When I select "single" facet value from "Size" facet section
    Then I verify that product count from JSON response against UI are same
    #And I should see the selected facets gets highlighted
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that clear all button is displayed
    When I deselect 1 facet value(s)
    Then I verify that selected facet gets deselected
    When I select multiple facet value from "Size" facet section
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that clear all button is displayed
    When I deselect 1 facet value(s)
    Then I verify that selected facet gets deselected
    Examples:
      | mode     | subcategory | category |
      | domestic | Activewear  | WOMEN    |
      | iship    | Activewear  | MEN      |

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @feature_subsplash_page
  Scenario Outline: SubSplashPage - Domestic|Iship - Verify the multi facets in subsplash page
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    Then I verify that "Size" facet is listed on left nav
    When I click on "Size" facet header on left nav
    Then I verify that new size facet family is displayed
    When I select a random facet sub header
    Then I verify that facet sub header gets "Expanded"
    And I verify that the facet values are displayed
    Examples:
      | mode     | subcategory | category |
      | domestic | Activewear  | WOMEN    |
      | iship    | Activewear  | MEN      |

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @feature_subsplash_page
  Scenario Outline: SubSplashPage - Domestic|Iship - Verify the node level of facet size group in page against Json response
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    Then I verify that "Size" facet is listed on left nav
    When I click on "Size" facet header on left nav
    Then I verify that new size facet family is displayed
    And I should see below keys in the JSON response at top-level groups
      | name           |
      | display Name   |
      | facet Type     |
      | robot Readable |
      | collapsed      |
    And I should see value "MULTISELECTBUTTON" for the key "facetType" in the JSON response at top-level groups
    And I should see below keys in the JSON response at first-level groups
      | name           |
      | display Name   |
      | facet Type     |
      | robot Readable |
      | collapsed      |
    And I should see value "MULTISELECTBUTTON" for the key "facetType" in the JSON response at first-level groups
    And I should see below keys in the JSON response at second-level groups
      | name           |
      | display Name   |
      | facet Type     |
      | robot Readable |
    And I should see value "MULTISELECTBUTTON" for the key "facetType" in the JSON response at second-level groups
    #And I should verify the header values in UI
    Examples:
      | mode     | subcategory | category |
      | domestic | Activewear  | WOMEN    |
      | iship    | Activewear  | MEN      |

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @feature_subsplash_page
  Scenario Outline: SubSplashPage - Domestic|Iship - Verify quick peek for facet size selection
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    Then I verify that "Size" facet is listed on left nav
    When I click on "Size" facet header on left nav
    Then I verify that new size facet family is displayed
    When I select "first" size value from Size facet section
    And I select the quick peek of random product
    And I verify that landed on "quick_view" Page
    Then I verify that the "quick peek" is showing the selected size
    Examples:
      | mode     | subcategory | category |
      | domestic | Activewear  | WOMEN    |
      | iship    | Activewear  | MEN      |

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @feature_subsplash_page
  Scenario Outline: SubSplashPage - Domestic|Iship - Verify PDP for facet size selection
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    Then I verify that "Size" facet is listed on left nav
    When I click on "Size" facet header on left nav
    Then I verify that new size facet family is displayed
    When I select "first" size value from Size facet section
    And I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    Then I verify that the "PDP" is showing the selected size
    Examples:
      | mode     | subcategory | category |
      | domestic | Activewear  | WOMEN    |
      | iship    | Activewear  | MEN      |

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @feature_subsplash_page
  Scenario Outline: SubSplashPage - Domestic|Iship - Verify deselection of facet size from breadcrumb
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    Then I verify that "Size" facet is listed on left nav
    When I click on "Size" facet header on left nav
    Then I verify that new size facet family is displayed
    Then I verify that the product count is displayed
    When I select "first" size value from Size facet section
    Then I verify that products are filtered as per selected facet value
    When I remove the selected facet from the breadcrumb
    Then I verify that all of the products are displayed
    Examples:
      | mode     | subcategory | category |
      | domestic | Activewear  | WOMEN    |
      | iship    | Activewear  | MEN      |

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @feature_subsplash_page
  Scenario Outline: SubSplashPage - Domestic|Iship - Verify facet size choice is retained when paginated
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    Then I verify that "Size" facet is listed on left nav
    When I click on "Size" facet header on left nav
    Then I verify that new size facet family is displayed
    Then I verify that the product count is displayed
    When I select "first" size value from Size facet section
    Then I verify that products are filtered as per selected facet value
    And I navigate to the last page
    Then I verify that products are filtered as per selected facet value
    Examples:
      | mode     | subcategory | category |
      | domestic | Activewear  | WOMEN    |
      | iship    | Activewear  | MEN      |

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @feature_subsplash_page
  Scenario Outline: SubSplashPage - Domestic|Iship - Verify that product counts in overlay and results match
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    Then I verify that the product count is displayed
    When I select "first" size value from Size facet section
    Then I verify that product count for the selected Size in the overlay and results match
    #Note : The product count can be off by 2
    Examples:
      | mode     | subcategory | category |
      | domestic | Activewear  | WOMEN    |
      | iship    | Activewear  | MEN      |

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @feature_subsplash_page
  Scenario Outline: SubSplashPage - Domestic|Iship - Verify facet de-selection with check box under Size facet section
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    Then I verify that the product count is displayed
    When I select "multiple" size value from Size facet section
    #Note : Select the first one from each of the multi select option under the size facet
    Then I verify that the product count is updated
    When I deselect the Size from the overlay
    Then I verify that the product count returns to original
    Examples:
      | mode     | subcategory | category |
      | domestic | Activewear  | WOMEN    |
      | iship    | Activewear  | MEN      |

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @feature_subsplash_page
  Scenario Outline: SubSplashPage -Domestic|Iship - Verify clear all button in Domestic and Iship modes
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    Then I verify that the product count is displayed
    When I select "multiple" size value from Size facet section
    #Note : Select the first one from each of the multi select option under the size facet
    And I click on clear all button
    Then I verify that the product count returns to original
    Examples:
      | mode     | subcategory | category |
      | domestic | Activewear  | WOMEN    |
      | iship    | Activewear  | MEN      |

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @feature_subsplash_page
  Scenario Outline: SubSplashPage - Domestic|Iship - Verify clear all button and product count
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    Then I verify that the product count is displayed
    When I select "multiple" size value from Size facet section
    #Note : Select the first one from each of the multi select option under the size facet
    Then I verify that the product count is updated
    When I click on clear all button
    Then I verify that the product count returns to original
    Examples:
      | mode     | subcategory | category |
      | domestic | Activewear  | WOMEN    |
      | iship    | Activewear  | MEN      |

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @feature_subsplash_page
  Scenario Outline: SubSplashPage - Domestic|Iship - Verify that deselecting the size one by one from breadcrumbs displays products accordingly
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    Then I verify that the product count is displayed
    When I select "multiple" size value from Size facet section
   #Note : Select the first one from each of the multi select option under the size facet
    Then I verify that products are filtered as per selected facet value
    When I remove first size facet from the breadcrumb
    Then I verify that the product count is updated
    When I remove second size facet from the breadcrumb
    Then I verify that the product count is updated
    When I remove last size facet from the breadcrumb
    Then I verify that the product count is updated
   #Note : Verify that the product assortment is getting changed after the deselection of each size
    Examples:
      | mode     | subcategory | category |
      | domestic | Activewear  | WOMEN    |
      | iship    | Activewear  | MEN      |

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @feature_subsplash_page
  Scenario Outline: SubSplashPage - Domestic|Iship - Verify that clear all button is updating the product assortment back to original
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    Then I verify that the product count is displayed
    When I select "multiple" size value from Size facet section
    #Note : Select the first one from each of the multi select option under the size facet
    Then I verify that products are filtered as per selected facet value
    And I verify that the product count is updated
    When I click on clear all button
    Then I verify that the product count returns to original
    Then I verify that all of the products are displayed
    Examples:
      | mode     | subcategory | category |
      | domestic | Activewear  | WOMEN    |
      | iship    | Activewear  | MEN      |

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @feature_subsplash_page
  Scenario Outline: SubSplashPage - Domestic|Iship - Verify that the selected sizes appears on the top in the size facet overlay
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    When I select "multiple" size value from Size facet section
    And I verify that the selected Size facets are highlighted
    Examples:
      | mode     | subcategory | category |
      | domestic | Activewear  | WOMEN    |
      | iship    | Activewear  | MEN      |

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @feature_subsplash_page
  Scenario Outline: SubSplashPage - Domestic|Iship - Verify expanding/collapsing of the size facet retains the selection and keeps the multi select option expanded
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    Then I verify that "Size" facet is listed on left nav
    When I click on "Size" facet header on left nav
    Then I verify that new size facet family is displayed
    When I select "multiple" size value from Size facet section
    And I "collapse" the "Size" facet on left nav
    And I "expand" the "Size" facet on left nav
    Then I verify that the selected Size facets are highlighted
    Examples:
      | mode     | subcategory | category |
      | domestic | Activewear  | WOMEN    |
      | iship    | Activewear  | MEN      |













