Feature: Verify browser back button verification on Search Landing Page

  @use_regression @feature_bgv @artifact_navapp @domain_discovery @bgv_2 @release_15H @mode_domestic @priority_medium @project_snb
  Scenario Outline: SearchResultPage - Domestic|Iship|Registry - Verify view location persistence with '40' item count option in 4 column grid view in DOMESTIC mode
    Given I am on SearchResultsPage for "cookware" in <shopping_mode> mode
    Then I should be in Search Landing page
    And I verify that item count buttons in page
    When I select "4" Column Grid icon
    And  I filter the result set to show "40" items
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SearchResultsPage on same product grid point
  Examples:
  | shopping_mode |
  | Domestic |
  | Iship |
  | Registry |

  @use_regression @feature_bgv @artifact_navapp @domain_discovery @bgv_2 @release_15H @mode_domestic @priority_medium @project_snb
  Scenario Outline: SearchResultPage - Domestic|Iship|Registry - Verify customer view persistence with '100' item count option in 4 column grid view in DOMESTIC mode
    Given I am on SearchResultsPage for "cookware" in <shopping_mode> mode
    Then I should be in Search Landing page
    And I verify that item count buttons in page
    When I select "4" Column Grid icon
    And  I filter the result set to show "100" items
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SearchResultsPage on same product grid point
  Examples:
  | shopping_mode |
  | Domestic |
  | Iship |
  | Registry |

  @use_regression @feature_bgv @artifact_navapp @domain_discovery @bgv_2 @release_15H @mode_domestic @priority_medium @project_snb
  Scenario Outline: SearchResultPage - Domestic|Iship|Registry - Verify customer view persistence with '60' item count option in 3 column grid view in DOMESTIC mode
    Given I am on SearchResultsPage for "cookware" in <shopping_mode> mode
    Then I should be in Search Landing page
    And I verify that item count buttons in page
    When I select "3" Column Grid icon
    And  I filter the result set to show "60" items
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SearchResultsPage on same product grid point
  Examples:
  | shopping_mode |
  | Domestic |
  | Iship |
  | Registry |

  @use_regression @feature_bgv @artifact_navapp @domain_discovery @bgv_2 @release_15H @mode_domestic @priority_medium @project_snb
  Scenario Outline: SearchResultPage - Domestic|Iship|Registry - Verify customer view persistence with '120' item count option in 3 column grid view in DOMESTIC mode
    Given I am on SearchResultsPage for "cookware" in <shopping_mode> mode
    Then I should be in Search Landing page
    And I verify that item count buttons in page
    When I select "3" Column Grid icon
    And  I filter the result set to show "120" items
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SearchResultsPage on same product grid point
  Examples:
  | shopping_mode |
  | Domestic |
  | Iship |
  | Registry |


  @use_regression @feature_bgv @artifact_navapp @domain_discovery @bgv_2 @release_15H @mode_domestic @priority_medium @project_snb
  Scenario Outline: SearchResultPage - Domestic|Iship|Registry - Verify customer view persistence with '40' item count option and pagination in 4 column grid view
    Given I am on SearchResultsPage for "cookware" in <shopping_mode> mode
    Then I should be in Search Landing page
    And I verify that item count buttons in page
    When I select "4" Column Grid icon
    And  I filter the result set to show "40" items
    And I navigate to next page of thumbnail grid
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SearchResultsPage on same product grid point
  Examples:
  | shopping_mode |
  | Domestic |
  | Iship |
  | Registry |

  @use_regression @feature_bgv @artifact_navapp @domain_discovery @bgv_2 @release_15H @mode_domestic @priority_medium @project_snb
  Scenario Outline: SearchResultPage - Domestic|Iship|Registry - Verify customer view persistence with '100' item count option and pagination in 4 column grid view in DOMESTIC mode
    Given I am on SearchResultsPage for "cookware" in <shopping_mode> mode
    Then I should be in Search Landing page
    And I verify that item count buttons in page
    When I select "4" Column Grid icon
    And  I filter the result set to show "100" items
    And I navigate to next page of thumbnail grid
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SearchResultsPage on same product grid point
  Examples:
  | shopping_mode |
  | Domestic |
  | Iship |
  | Registry |

  @use_regression @feature_bgv @artifact_navapp @domain_discovery @bgv_2 @release_15H @mode_domestic @priority_medium @project_snb
  Scenario Outline: SearchResultPage - Domestic|Iship|Registry - Verify customer view persistence with '60' item count option and pagination in 3 column grid view in DOMESTIC mode
    Given I am on SearchResultsPage for "cookware" in <shopping_mode> mode
    Then I should be in Search Landing page
    And I verify that item count buttons in page
    When I select "3" Column Grid icon
    And  I filter the result set to show "60" items
    And I navigate to next page of thumbnail grid
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SearchResultsPage on same product grid point
  Examples:
  | shopping_mode |
  | Domestic |
  | Iship |
  | Registry |

  @use_regression @feature_bgv @artifact_navapp @domain_discovery @bgv_2 @release_15H @mode_domestic @priority_medium @project_snb
  Scenario Outline: SearchResultPage - Domestic|Iship|Registry - Verify customer view persistence with '120' item count option and pagination in 3 column grid view in DOMESTIC mode
    Given I am on SearchResultsPage for "cookware" in <shopping_mode> mode
    Then I should be in Search Landing page
    And I verify that item count buttons in page
    When I select "3" Column Grid icon
    And  I filter the result set to show "120" items
    And I navigate to next page of thumbnail grid
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SearchResultsPage on same product grid point
  Examples:
  | shopping_mode |
  | Domestic |
  | Iship |
  | Registry |

  @spo_5062 @release_14G @use_regression @s4a_stable @priority_high @use_regression_1 @mode_iship @domain_discovery @artifact_navapp @project_snb
  Scenario: SearchResultsPage - Ensure product thumbnails displayed as existing on Search Landing page when navigated through browser back button after browse grid implementation in ISHIP mode
    Given I am on SearchResultsPage for "jeans" in ISHIP mode
    Then I should be in Search Landing page
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I should be in Search Landing page
    And I verify that landed on SearchResultsPage on same product grid point