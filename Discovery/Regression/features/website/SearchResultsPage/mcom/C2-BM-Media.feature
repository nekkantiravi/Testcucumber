Feature: Verify C2-BM-Media contents in DOMESTIC, ISHIP and REGISTRY mode

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt
  Scenario Outline: SearchResultPage - Domestic|Registry|Iship - Verify header text media is displayed
    Given I am on SearchResultsPage for "shirts" in <shopping_mode> mode
    Then I verify that "header text" media is displayed
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |
      | registry      |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt
  Scenario Outline: SearchResultPage - Domestic|Registry|Iship - Verify slide show media is displayed
    Given I am on SearchResultsPage for "shirts" in <shopping_mode> mode
    Then I verify that "slide show" media is displayed
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |
      | registry      |


  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt
  Scenario Outline: SearchResultPage - Domestic|Registry|Iship - Verify image map media is displayed
    Given I am on SearchResultsPage for "shirts" in <shopping_mode> mode
    Then I verify that "image map" media is displayed
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |
      | registry      |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt
  Scenario Outline: SearchResultPage - Domestic|Registry|Iship - Verify horizontal ruler media is displayed
    Given I am on SearchResultsPage for "shirts" in <shopping_mode> mode
    Then I verify that "horizontal ruler" media is displayed
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |
      | registry      |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt
  Scenario Outline: SearchResultPage - Domestic|Registry|Iship - Verify category icon media is displayed
    Given I am on SearchResultsPage for "shirts" in <shopping_mode> mode
    Then I verify that "category icon" media is displayed
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |
      | registry      |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt
  Scenario Outline: SearchResultPage - Domestic|Registry|Iship - Verify flex pool media is displayed
    Given I am on SearchResultsPage for "shirts" in <shopping_mode> mode
    Then I verify that "flex pool" media is displayed
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |
      | registry      |


  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt
  Scenario Outline: SearchResultPage - Domestic|Registry|Iship - Verify product pool media is displayed
    Given I am on SearchResultsPage for "shirts" in <shopping_mode> mode
    Then I verify that "product pool" media is displayed
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |
      | registry      |

  @domain_discovery @priority_high @use_regression @mode_domestic @mode_registry @mode_iship
  Scenario Outline: SearchResultPage - Domestic|Iship|Registry - Verify media links and popup links are not resulting any error page
    Given I visit the web site as a guest user in "<mode>" mode
    When I search for "<keyword>"
    Then I verify all media links and popups are not resulting error page
    Examples:
      | mode     | keyword      |
      | domestic | levis        |
      | iship    | levis        |
      | registry | bar          |
      | domestic | michael kors |
      | iship    | michael kors |
  # Navigate to any search results page in each mode
  # Identify all links from media content section on page.
  # Then verify all media links are resulting '200' response code with 'Mechanize' agent request.