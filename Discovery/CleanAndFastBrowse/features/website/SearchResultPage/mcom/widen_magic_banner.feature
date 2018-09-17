# Author: Discovery QE

Feature: Verify magic Banner functionality in all browse & search pages for all modes

  ### Magic banner verification scenarios in search/browse page

  @sample_tag
  Scenario Outline: SearchResultsPage - Domestic - Verify magic banner functionality in search page
    Given I visit the web site as a guest user
    And I update segment value in EFCKEY={"EXPERIMENT":["<segment_value>"]}
    When I search for "<search_keyword>"
    And If I see magic banner media in search page
    Then I verify magic banner is above the search page body content
    And I should see magic banner is aligned with browse page content
  Examples:
    |search_keyword|segment_value|
    |all-clad      |2367         |
    |Fiesta        |2367         |
    |Waterford     |2367         |
    |Aerosoles     |2367         |

  @sample_tag
  Scenario Outline: SearchResultsPage - ISHIP - Verify magic banner functionality in search page
    Given I visit the web site as a guest user
    And I change country to "India"
    And I update segment value in EFCKEY={"EXPERIMENT":["<segment_value>"]}
    When I search for "<search_keyword>"
    And If I see magic banner media in search page
    Then I verify magic banner is above the search page body content
    And I should see magic banner is aligned with browse page content
    Examples:
      |search_keyword|segment_value|
      |all-clad      |2367         |
      |Fiesta        |2367         |
      |Waterford     |2367         |
      |Aerosoles     |2367         |

  @sample_tag
  Scenario Outline: SearchResultsPage - Registry - Verify magic banner functionality in search page
    Given I visit the web site as a guest user
    And I navigate to registry home page
    And I update segment value in EFCKEY={"EXPERIMENT":["<segment_value>"]}
    When I search for "<search_keyword>"
    And If I see magic banner media in search page
    Then I verify magic banner is above the search page body content
    And I should see magic banner is aligned with browse page content
    Examples:
      |search_keyword|segment_value|
      |all-clad      |2367         |
      |Fiesta        |2367         |
      |Waterford     |2367         |
      |Aerosoles     |2367         |

  @sample_tag
  Scenario Outline: BrowsePage - Domestic - Verify magic banner functionality in browse page
    Given I visit the web site as a guest user
    And I update segment value in EFCKEY={"EXPERIMENT":["<segment_value>"]}
    When I navigate to random browse page
    And If I see magic banner media in search page
    Then I verify magic banner is above the search page body content
    And I should see magic banner is aligned with browse page content
  Examples:
  |segment_value|
  |2367         |

  @sample_tag
  Scenario Outline: BrowsePage - ISHIP - Verify magic banner functionality in browse page
    Given I visit the web site as a guest user
    And I change country to "India"
    And I update segment value in EFCKEY={"EXPERIMENT":["<segment_value>"]}
    When I navigate to random browse page
    And If I see magic banner media in search page
    Then I verify magic banner is above the search page body content
    And I should see magic banner is aligned with browse page content
  Examples:
  |segment_value|
  |2367         |

  @sample_tag
  Scenario Outline: BrowsePage - Registry - Verify magic banner functionality in browse page
    Given I visit the web site as a guest user
    And I navigate to registry home page
    And I update segment value in EFCKEY={"EXPERIMENT":["<segment_value>"]}
    When I navigate to random browse page
    And If I see magic banner media in search page
    Then I verify magic banner is above the search page body content
    And I should see magic banner is aligned with browse page content
  Examples:
  |segment_value|
  |2367         |

    #### Renavigating back to search/browse page magic banner verification scenarios

  @sample_tag
  Scenario Outline: SearchResultsPage - Domestic - Verify magic banner functionality when user renavigates from PDP to search page
    #Validate this scenario for ISHIP and registry
    Given I visit the web site as a guest user
    And I update segment value in EFCKEY={"EXPERIMENT":["<segment_value>"]}
    When I search for "<search_keyword>"
    And If I see magic banner media in search page
    Then I verify magic banner is above the search page body content
    And I should see magic banner is center aligned with browse page content
    When I navigate to a random product
    And I renavigate back to search page
    Then I verify magic banner is above the search page body content
    And I should see magic banner is center aligned with browse page content

    Examples:
      |search_keyword|segment_value|
      |all-clad      |2367         |
      |Fiesta        |2367         |
      |Waterford     |2367         |
      |Aerosoles     |2367         |

  @sample_tag
  Scenario Outline: BrowsePage - Domestic - Verify magic banner functionality when user renavigates from pdp to browse page
     #Validate this scenario for ISHIP and registry
    Given I visit the web site as a guest user
    And I update segment value in EFCKEY={"EXPERIMENT":["<segment_value>"]}
    When I navigate to random browse page
    And If I see magic banner media in search page
    Then I verify magic banner is above the search page body content
    And I should see magic banner is aligned with browse page content
    When I navigate to a random product
    And I renavigate back to browse page
    Then I verify magic banner is above the search page body content
    And I should see magic banner is center aligned with browse page content
  Examples:
    |segment_value|
    |2367         |

    ### Validation with facet selection

  @sample_tag
  Scenario Outline: SearchResultsPage - Domestic - Verify magic banner functionality when user selects facets, sortby, pagination and product view in search page
    ### Repeate this scenario for Iship and registry modes
    Given I visit the web site as a guest user
    And I update segment value in EFCKEY={"EXPERIMENT":["<segment_value>"]}
    When I search for "<search_keyword>"
    And If I see magic banner media in search page
    Then I verify magic banner is above the search page body content
    And I should see magic banner is aligned with browse page content
    When I select random facet/sortby/pagination/featured category/product view values
    Then I verify magic banner is above the search page body content
    And I should see magic banner is aligned with browse page content
    Examples:
      |search_keyword|segment_value|
      |all-clad      |2367         |
      |Fiesta        |2367         |
      |Waterford     |2367         |
      |Aerosoles     |2367         |

  @sample_tag
  Scenario Outline: BrowsePage - Domestic - Verify magic banner functionality when user selects facets, sortby, pagination, featured category and product view in browse page
    ### Repeate this scenario for Iship and registry modes
    Given I visit the web site as a guest user
    And I update segment value in EFCKEY={"EXPERIMENT":["<segment_value>"]}
    When I navigate to random browse page
    And If I see magic banner media in search page
    Then I verify magic banner is above the search page body content
    And I should see magic banner is aligned with browse page content
    When I select random facet/sortby/pagination/featured category/product view values
    Then I verify magic banner is above the search page body content
    And I should see magic banner is aligned with browse page content
    Examples:
      |segment_value|
      |2367         |

  ### Validation reload scenarios

  @sample_tag
  Scenario Outline: SearchResultsPage - Domestic - Verify magic banner functionality when user reloads search page
    ### Repeat this scenario for Iship and registry modes
    Given I visit the web site as a guest user
    And I update segment value in EFCKEY={"EXPERIMENT":["<segment_value>"]}
    When I search for "<search_keyword>"
    And If I see magic banner media in search page
    Then I verify magic banner is above the search page body content
    And I should see magic banner is aligned with browse page content
    When I reload/refresh page
    Then I verify magic banner is above the search page body content
    And I should see magic banner is aligned with browse page content
    Examples:
      |search_keyword|segment_value|
      |all-clad      |2367         |
      |Fiesta        |2367         |
      |Waterford     |2367         |
      |Aerosoles     |2367         |

  @sample_tag
  Scenario Outline: BrowsePage - Domestic - Verify magic banner functionality when user reloads browse page
    ### Repeate this scenario for Iship and registry modes
    Given I visit the web site as a guest user
    And I update segment value in EFCKEY={"EXPERIMENT":["<segment_value>"]}
    When I navigate to random browse page
    And If I see magic banner media in search page
    Then I verify magic banner is above the search page body content
    And I should see magic banner is aligned with browse page content
    When I reload/refresh page
    Then I verify magic banner is above the search page body content
    And I should see magic banner is aligned with browse page content
    Examples:
      |segment_value|
      |2367         |

  ### Default control and holdout behavior

  @sample_tag
  Scenario Outline: BrowsePage - Domestic - Verify magic banner functionality in default bahavior with non treatment experience
     #Repeat this scenarios for iship and registry users
    Given I visit the web site as a guest user
    And I update segment value in EFCKEY={"EXPERIMENT":["<segment_value>"]}
    When I navigate to random browse page
    And If I see magic banner media in search page
    Then I should see magic banner is just aligned with media content of browse page beside left nav facet list
    When I select random facet/sortby/pagination/featured category/product view values
    Then I should see magic banner is just aligned with media content of browse page beside left nav facet list

    Examples:
      |segment_value|
      |2366         |
      |2365         |
      |None         |

  @sample_tag
  Scenario Outline: Search Page - Domestic - Verify magic banner functionality in default bahavior with non treatment experience
     #Repeat this scenarios for iship and registry users
    Given I visit the web site as a guest user
    And I update segment value in EFCKEY={"EXPERIMENT":["<segment_value>"]}
    When I search for "<search_keyword>"
    And If I see magic banner media in search page
    Then I should see magic banner is just aligned with media content of search page beside left nav facet list
    When I select random facet/sortby/pagination/featured category/product view values
    Then I should see magic banner is just aligned with media content of browse page beside left nav facet list
    Examples:
      |search_keyword|segment_value|
      |all-clad      |2366         |
      |Fiesta        |2365         |
      |Waterford     |None         |

  ####    Coremetrics scenarios
  @sample_tag
  Scenario Outline: SearchResultsPage - Domestic - Verify coremetrics tags for treatment1 experience in search page
    #Repeat this scenario for iship and registry users
    Given I visit the web site as a guest user
    And I update segment value in EFCKEY={"EXPERIMENT":["<segment_value>"]}
    When I search for "<search_keyword>"
    And If I see magic banner media in search page
    Then I verify magic banner is above the search page body content
    And I should see magic banner is aligned with browse page content
    And I should see Page ID and Category ID as per the existing functionality
    And I should see "CFB_1.0:Trt1" in attr3 of page view tag
    Examples:
      |search_keyword|segment_value|
      |all-clad|2367              |
      |Fiesta|2367                 |
      |Waterford|2367              |
      |Aerosoles|2367              |

  @sample_tag
  Scenario Outline: SearchResultsPage - Domestic - Verify coremetrics tags for non treatment1 experience in search page
    #Repeat this scenarios for iship and registry users
    Given I visit the web site as a guest user
    And I update segment value in EFCKEY={"EXPERIMENT":["<segment_value>"]}
    When I search for "<search_keyword>"
    And If I see magic banner media in search page
    And I should see magic banner is just aligned with media content of search page beside left nav facet list
    And I should see Page ID and Category ID as per the existing functionality
    And I should see "<tag_value>" in attr3 of page view tag
    Examples:
      |search_keyword|segment_value|tag_value|
      |all-clad      |2366         |CFB_1.0:Hold|
      |Fiesta        |2366         |CFB_1.0:Hold|
      |Waterford     |2365         |CFB_1.0:Ctrl|
      |Aerosoles     |2365         |CFB_1.0:Ctrl|

  @sample_tag
  Scenario Outline: BrowsePage - Domestic - Verify coremetrics tags for magic banner treatment1 experience in browse page
    #Repeat this scenario for iship and registry users
    Given I visit the web site as a guest user
    And I update segment value in EFCKEY={"EXPERIMENT":["<segment_value>"]}
    When I navigate to random browse page
    And If I see magic banner media in search page
    Then I verify magic banner is above the search page body content
    And I should see magic banner is aligned with browse page content
    And I should see Page ID and Category ID as per the existing functionality
    And I should see "CFB_1.0:Trt1" in attr3 of page view tag
    Examples:
      |segment_value|
      |2367         |

  @sample_tag
  Scenario Outline: BrowsePage - Domestic - Verify coremetrics tags for non treatment1 experience in browse page
  #Repeat this scenarios for iship and registry users
    Given I visit the web site as a guest user
    And I update segment value in EFCKEY={"EXPERIMENT":["<segment_value>"]}
    When I navigate to random browse page
    And If I see magic banner media in search page
    And I should see magic banner is just aligned with media content of search page beside left nav facet list
    And I should see Page ID and Category ID as per the existing functionality
    And I should see "<tag_value>" in attr3 of page view tag
    Examples:
      |segment_value|tag_value|
      |2366         |CFB_1.0:Hold|
      |2366         |CFB_1.0:Hold|
      |2365         |CFB_1.0:Ctrl|
      |2365         |CFB_1.0:Ctrl|