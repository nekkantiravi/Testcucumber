# Author: Discovery QE

Feature: Verify that row2 media is removed from all browse & search pages for all modes

  ### Removal of row2 media scenarios in search/browse page
  ### Row2 --> Media Content if media content is their then remove or else nothing should be removed
  #### Row2 --> Category Media (Ex: Banner,slideshow, widgets)

  @sample_tag
  Scenario Outline: SearchResultsPage - Domestic - Verify that row2 media is removed in search page
    Given I visit the web site as a guest user
    When I search for "<search_keyword>"
    And If I see row2 media in search page
    And I update segment value in EFCKEY={"EXPERIMENT":["<segment_value>"]}
    Then I should see row2 media content is removed in search page
  Examples:
    |search_keyword|segment_value|
    |all-clad      |2368         |
    |Fiesta        |2368         |
    |Waterford     |2368         |
    |Aerosoles     |2368         |

  @sample_tag
  Scenario Outline: SearchResultsPage - ISHIP - Verify that row2 media is removed in search page
    Given I visit the web site as a guest user
    And I change country to "India"
    When I search for "<search_keyword>"
    And If I see row2 media in search page
    And I update segment value in EFCKEY={"EXPERIMENT":["<segment_value>"]}
    Then I should see row2 media content is removed in search page
    Examples:
      |search_keyword|segment_value|
      |all-clad      |2368         |
      |Fiesta        |2368         |
      |Waterford     |2368         |
      |Aerosoles     |2368         |

  @sample_tag
  Scenario Outline: SearchResultsPage - Registry - Verify that row2 media is removed in search page
    Given I visit the web site as a guest user
    And I navigate to registry home page
    When I search for "<search_keyword>"
    And If I see row2 media in search page
    And I update segment value in EFCKEY={"EXPERIMENT":["<segment_value>"]}
    Then I should see row2 media content is removed in search page
    Examples:
      |search_keyword|segment_value|
      |all-clad      |2368         |
      |Fiesta        |2368         |
      |Waterford     |2368         |
      |Aerosoles     |2368         |

  @sample_tag
  Scenario Outline: BrowsePage - Domestic - Verify that row2 media is removed in browse page
    Given I visit the web site as a guest user
    When I navigate to random browse page
    And If I see row2 media in browse page
    And I update segment value in EFCKEY={"EXPERIMENT":["<segment_value>"]}
    Then I should see row2 media content is removed in browse page
  Examples:
  |segment_value|
  |2368         |

  @sample_tag
  Scenario Outline: BrowsePage - ISHIP - Verify that row2 media is removed in browse page
    Given I visit the web site as a guest user
    And I change country to "India"
    When I navigate to random browse page
    And If I see row2 media in browse page
    And I update segment value in EFCKEY={"EXPERIMENT":["<segment_value>"]}
    Then I should see row2 media content is removed in browse page
  Examples:
  |segment_value|
  |2368         |

  @sample_tag
  Scenario Outline: BrowsePage - Registry - Verify that row2 media is removed in browse page
    Given I visit the web site as a guest user
    And I navigate to registry home page
    When I navigate to random browse page
    And If I see row2 media in browse page
    And I update segment value in EFCKEY={"EXPERIMENT":["<segment_value>"]}
    Then I should see row2 media content is removed in browse page
  Examples:
  |segment_value|
  |2368         |

    #### Renavigating back to search/browse page removal of row2 verification scenarios

  @sample_tag
  Scenario Outline: SearchResultsPage - Domestic - Verify that row2 media is removed in search page even when user renavigates from PDP to search page
    #Validate this scenario for ISHIP and registry
    Given I visit the web site as a guest user
    When I search for "<search_keyword>"
    And If I see row2 media in search page
    And I update segment value in EFCKEY={"EXPERIMENT":["<segment_value>"]}
    Then I should see row2 media content is removed in search page
    When I navigate to a random product
    And I renavigate back to search page
    Then I should see row2 media content is removed in search page

    Examples:
      |search_keyword|segment_value|
      |all-clad      |2368         |
      |Fiesta        |2368         |
      |Waterford     |2368         |
      |Aerosoles     |2368         |

  @sample_tag
  Scenario Outline: BrowsePage - Domestic - Verify that row2 media is removed in browse page even user renavigates from pdp to browse page
     #Validate this scenario for ISHIP and registry
    Given I visit the web site as a guest user
    When I navigate to random browse page
    And If I see row2 media in browse page
    And I update segment value in EFCKEY={"EXPERIMENT":["<segment_value>"]}
    Then I should see row2 media content is removed in browse page
    When I navigate to a random product
    And I renavigate back to browse page
    Then I should see row2 media content is removed in browse page
  Examples:
    |segment_value|
    |2368         |

    ### Validation with facet selection

  @sample_tag
  Scenario Outline: SearchResultsPage - Domestic - Verify that row2 media is removed in search page when user selects facets, sortby, pagination and product view in search page
    ### Repeate this scenario for Iship and registry modes
    Given I visit the web site as a guest user
    When I search for "<search_keyword>"
    And If I see row2 media in search page
    And I update segment value in EFCKEY={"EXPERIMENT":["<segment_value>"]}
    Then I should see row2 media content is removed in search page
    When I select random facet/sortby/pagination/featured category/product view values
    Then I should see row2 media content is removed in search page
    Examples:
      |search_keyword|segment_value|
      |all-clad      |2368         |
      |Fiesta        |2368         |
      |Waterford     |2368         |
      |Aerosoles     |2368         |

  @sample_tag
  Scenario Outline: BrowsePage - Domestic - Verify that row2 media is removed in browse page when user selects facets, sortby, pagination, featured category and product view in browse page
    ### Repeate this scenario for Iship and registry modes
    Given I visit the web site as a guest user
    When I navigate to random browse page
    And If I see row2 media in browse page
    And I update segment value in EFCKEY={"EXPERIMENT":["<segment_value>"]}
    Then I should see row2 media content is removed in browse page
    When I select random facet/sortby/pagination/featured category/product view values
    Then I should see row2 media content is removed in browse page
    Examples:
      |segment_value|
      |2368         |

    ### Validation reloads scenarios

  @sample_tag
  Scenario Outline: SearchResultsPage - Domestic - Verify that row2 media is removed in search page when user reloads search page
    ### Repeate this scenario for Iship and registry modes
    Given I visit the web site as a guest user
    When I search for "<search_keyword>"
    And If I see row2 media in search page
    And I update segment value in EFCKEY={"EXPERIMENT":["<segment_value>"]}
    Then I should see row2 media content is removed in search page
    When I reload/refresh page
    Then I should see row2 media content is removed in search page
    Examples:
      |search_keyword|segment_value|
      |all-clad      |2368         |
      |Fiesta        |2368         |
      |Waterford     |2368         |
      |Aerosoles     |2368         |

  @sample_tag
  Scenario Outline: BrowsePage - Domestic - Verify that row2 media is removed in browse page when user reloads browse page
    ### Repeate this scenario for Iship and registry modes
    Given I visit the web site as a guest user
    When I navigate to random browse page
    And If I see row2 media in browse page
    And I update segment value in EFCKEY={"EXPERIMENT":["<segment_value>"]}
    Then I should see row2 media content is removed in browse page
    When I reload/refresh page
    Then I should see row2 media content is removed in browse page
    Examples:
      |segment_value|
      |2368         |

  ### Default control and holdout behavior

  @sample_tag
  Scenario Outline: BrowsePage - Domestic - Verify that row2 media is in default bahavior with non treatment experience
     #Repeat this scenarios for iship and registry users
    Given I visit the web site as a guest user
    When I navigate to random browse page
    And If I see row2 media in browse page
    And I update segment value in EFCKEY={"EXPERIMENT":["<segment_value>"]}
    Then I should see row2 media content in browse page
    When I select random facet/sortby/pagination/featured category/product view values
    Then I should see row2 media content in browse page
    When I reload page
    Then I should see row2 media content in browse page

    Examples:
      |segment_value|
      |2366         |
      |2365         |
      |None         |

  @sample_tag
  Scenario Outline: Search Page - Domestic - Verify that row2 media is in default bahavior with non treatment experience
     #Repeat this scenarios for iship and registry users
    Given I visit the web site as a guest user
    When I search for "<search_keyword>"
    And If I see row2 media in search page
    And I update segment value in EFCKEY={"EXPERIMENT":["<segment_value>"]}
    Then I should see row2 media content in browse page
    When I select random facet/sortby/pagination/featured category/product view values
    Then I should see row2 media content in browse page
    When I reload page
    Then I should see row2 media content in browse page
    Examples:
      |search_keyword|segment_value|
      |all-clad      |2366         |
      |Fiesta        |2365         |
      |Waterford     |None         |

  ####    Coremetrics scenarios
  @sample_tag
  Scenario Outline: SearchResultsPage - Domestic - Verify coremetrics tags for treatment2 experience in search page
    #Repeat this scenario for iship and registry users
    Given I visit the web site as a guest user
    When I search for "<search_keyword>"
    And If I see row2 media in search page
    And I update segment value in EFCKEY={"EXPERIMENT":["<segment_value>"]}
    Then I should see row2 media content is removed in search page
    And I should see Page ID and Category ID as per the existing functionality
    And I should see "CFB_1.0:Trt2" in attr3 of page view tag
    Examples:
      |search_keyword|segment_value|
      |all-clad      |2368         |
      |Fiesta        |2368         |
      |Waterford     |2368         |
      |Aerosoles     |2368         |

  @sample_tag
  Scenario Outline: SearchResultsPage - Domestic - Verify coremetrics tags for holdout and control experience in search page
    #Repeat this scenarios for iship and registry users
    Given I visit the web site as a guest user
    When I search for "<search_keyword>"
    And If I see row2 media in search page
    And I update segment value in EFCKEY={"EXPERIMENT":["<segment_value>"]}
    Then I should see row2 media content is removed in search page
    And I should see Page ID and Category ID as per the existing functionality
    And I should see "<tag_value>" in attr3 of page view tag
    Examples:
      |search_keyword|segment_value|tag_value|
      |all-clad      |2366         |CFB_1.0:Hold|
      |Fiesta        |2366         |CFB_1.0:Hold|
      |Waterford     |2365         |CFB_1.0:Ctrl|
      |Aerosoles     |2365         |CFB_1.0:Ctrl|

  @sample_tag
  Scenario Outline: BrowsePage - Domestic - Verify coremetrics tags for treatment2 experience in browse page
    #Repeat this scenario for iship and registry users
    Given I visit the web site as a guest user
    When I navigate to random browse page
    And If I see row2 media in browse page
    And I update segment value in EFCKEY={"EXPERIMENT":["<segment_value>"]}
    Then I should see row2 media content is removed in browse page
    And I should see Page ID and Category ID as per the existing functionality
    And I should see "CFB_1.0:Trt2" in attr3 of page view tag
    Examples:
      |segment_value|
      |2368         |

  @sample_tag
  Scenario Outline: BrowsePage - Domestic - Verify coremetrics tags for holdout and control experience in browse page
  #Repeat this scenarios for iship and registry users
    Given I visit the web site as a guest user
    When I navigate to random browse page
    And If I see row2 media in browse page
    And I update segment value in EFCKEY={"EXPERIMENT":["<segment_value>"]}
    Then I should see row2 media content is removed in browse page
    And I should see Page ID and Category ID as per the existing functionality
    And I should see "<tag_value>" in attr3 of page view tag
    Examples:
      |segment_value|tag_value|
      |2366         |CFB_1.0:Hold|
      |2366         |CFB_1.0:Hold|
      |2365         |CFB_1.0:Ctrl|
      |2365         |CFB_1.0:Ctrl|

