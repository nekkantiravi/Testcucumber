Feature: Pagination verification on Search Landing Page

  @use_domain_qual @domain_discovery @artifact_navapp @high @project_snb
  Scenario: SearchResultsPage - Verify pagination in DOMESTIC mode
    Given I am on SearchResultsPage for "shoes" in Domestic mode
    Then I verify that pagination works

  @use_regression @artifact_navapp @domain_discovery @project_snb @mode_domestic @priority_medium
  Scenario: SearchResultsPage - Without Pagination in DOMESTIC mode
    Given I am on SearchResultsPage for "bread maker" in Domestic mode
    Then I should be in Search Landing page
    And I verify that pagination is not displayed

  @use_regression  @artifact_navapp @domain_discovery @priority_low @use_dsv @project_snb
  Scenario: SearchResultsPage - UN - Keyword search for less than 4 products in DOMESTIC mode
    Given I am on SearchResultsPage for "bread maker" in Domestic mode
    Then I verify that the product count is "less" than "4"
    And I verify that pagination is not displayed

  @artifact_navapp @domain_discovery @priority_medium @mode_domestic @project_snb
  Scenario Outline: SearchResultsPage - Pagination using Next / Previous button in DOMESTIC mode
    Given I am on SearchResultsPage for "skirts" in <shopping_mode> mode
    And I click 2 pagination number
    Then I verify that navigated to page 2 on search result page
    And I click on next pagination button
    Then I verify that navigated to page 3 on search result page
    When I click on previous pagination button
    Then I verify that navigated to page 2 on search result page
  Examples:
  | shopping_mode  |
  | Domestic       |
  | Registry       |
  | Iship          |

 @spo_6160 @release_14I @use_regression @registry @priority_high @mode_registry @project_snb
  Scenario Outline: SearchResultsPage - Pagination using Next / Previous button in REGISTRY Mode
    Given I am on SearchResultsPage for "dining" in <shopping_mode> mode
    Then I should be in Search Landing page
    And I should be in Registry mode
    And I click 2 pagination number
   Then I verify that navigated to page 2 on search result page
    And I verify that all the product thumbnails displayed properly on the Search Landing page
    When I click on next pagination button
   Then I verify that navigated to page 3 on search result page
    And I verify that all the product thumbnails displayed properly on the Search Landing page
    When I click on previous pagination button
   Then I verify that navigated to page 2 on search result page
    And I verify that all the product thumbnails displayed properly on the Search Landing page
  Examples:
  | shopping_mode  |
  | Domestic       |
  | Registry       |
  | Iship          |

  @artifact_navapp @domain_discovery @priority_high @mode_domestic @project_snb @add_missing_scope
  Scenario Outline: SearchResultsPage - Verify pagination in ALL modes
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
    Then I verify that pagination works
    Examples:
      | shopping_mode  |
      | Domestic       |
      | Registry       |
      | Iship          |

  @artifact_navapp @domain_discovery @priority_high @mode_domestic @project_snb @add_missing_scope
  Scenario Outline: SearchResultsPage - Verify previous pagination button in ALL modes
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
    When I click 3 pagination number
    Then I verify that navigated to page 3 on search result page
    And I verify that all the product thumbnails displayed properly on the Search Landing page
    When I click on previous pagination button
    Then I verify that navigated to page 2 on search result page
    And I verify that all the product thumbnails displayed properly on the Search Landing page
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |

  @artifact_navapp @domain_discovery @priority_high @mode_domestic @project_snb @add_missing_scope
  Scenario Outline: SearchResultsPage - Verify Next pagination button in ALL modes
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
    When I click 2 pagination number
    Then I verify that navigated to page 2 on search result page
    And I verify that all the product thumbnails displayed properly on the Search Landing page
    When I click on next pagination button
    Then I verify that navigated to page 3 on search result page
    And I verify that all the product thumbnails displayed properly on the Search Landing page
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |

  @artifact_navapp @domain_discovery @priority_high @mode_domestic @project_snb @add_missing_scope
  Scenario Outline: SearchResultsPage - Verify browser back button takes to previously navigated page before navigating to PDP in ALL modes
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
    When I click 2 pagination number
    Then I verify that navigated to page 2 on search result page
    And I verify that all the product thumbnails displayed properly on the Search Landing page
    When I click on next pagination button
    Then I verify that navigated to page 3 on search result page
    And I verify that all the product thumbnails displayed properly on the Search Landing page
    When I click on next pagination button
    Then I verify that navigated to page 4 on search result page
    And I verify that all the product thumbnails displayed properly on the Search Landing page
    And I navigate to PDP of the first product
    And I select browse 'back' button
    Then I verify that navigated to page 4 on search result page
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |

  #Note:  paginate,navigate to product and press browser back button, check url
  # paginate,choose quick view and press browser back button, check url
  # paginate , press forward backward button in between


