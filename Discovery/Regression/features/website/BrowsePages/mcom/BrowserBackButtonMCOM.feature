Feature: Verify browser back button verification on Browse Page

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify customer view persistence with '60' item count option in 3 column grid view in All modes
    Given I am on CategoryBrowsePage for "<Category_Name>" under "<FOB>" in <shopping_mode> mode
    Then I should be in category browse page
    And I verify that item count buttons in page
    When I filter the result set to show "60" items per page
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on CategoryBrowsePage on same product grid point
    Examples:
      | shopping_mode | Category_Name            | FOB     |
      | Domestic      | Dress Shirts             | Men     |
      | Registry      | Cookware & Cookware Sets | Kitchen |
      | Iship         | Dress Shirts             | Men     |

  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify customer view persistence with '120' item count option in 3 column grid view in DOMESTIC mode
    Given I am on CategoryBrowsePage for "<Category_Name>" under "<FOB>" in <shopping_mode> mode
    Then I should be in category browse page
    And I verify that item count buttons in page
    When I filter the result set to show "120" items per page
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on CategoryBrowsePage on same product grid point
    Examples:
      | shopping_mode | Category_Name            | FOB     |
      | Domestic      | Dress Shirts             | Men     |
      | Registry      | Cookware & Cookware Sets | Kitchen |
      | Iship         | Dress Shirts             | Men     |

  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify customer view persistence with '60' item count option and pagination in 3 column grid view in DOMESTIC mode
    Given I am on CategoryBrowsePage for "<Category_Name>" under "<FOB>" in <shopping_mode> mode
    Then I should be in category browse page
    And I verify that item count buttons in page
    When I filter the result set to show "60" items per page
    And I navigate to next page of thumbnail grid
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on CategoryBrowsePage on same product grid point
    Examples:
      | shopping_mode | Category_Name            | FOB     |
      | Domestic      | Dress Shirts             | Men     |
      | Registry      | Cookware & Cookware Sets | Kitchen |
      | Iship         | Dress Shirts             | Men     |

  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page @xbrowser_search
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify customer view persistence with '120' item count option and pagination in 3 column grid view in DOMESTIC mode
    Given I am on CategoryBrowsePage for "<Category_Name>" under "<FOB>" in <shopping_mode> mode
    Then I should be in category browse page
    And I verify that item count buttons in page
    When I filter the result set to show "120" items per page
    And I navigate to next page of thumbnail grid
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on CategoryBrowsePage on same product grid point
    Examples:
      | shopping_mode | Category_Name            | FOB     |
      | Domestic      | Dress Shirts             | Men     |
      | Registry      | Cookware & Cookware Sets | Kitchen |
      | Iship         | Dress Shirts             | Men     |

  @domain_discovery @priority_medium @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario: CategoryBrowsePage - Ensure product thumbnails displayed as existing on Search Landing page when navigated through browser back button after browse grid implementation in ISHIP mode
    Given I am on CategoryBrowsePage for "jeans" under "Men" in Iship mode
    Then I should be in category browse page
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I should be in category browse page
    And I verify that landed on CategoryBrowsePage on same product grid point