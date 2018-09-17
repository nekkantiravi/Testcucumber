Feature: Pagination verification on Category browse page

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario: CategoryBrowsePage - Verify pagination in DOMESTIC mode
    Given I am on CategoryBrowsePage for "Flats" under "Shoes" in Domestic mode
    Then I verify that pagination works

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario: CategoryBrowsePage - Without Pagination in DOMESTIC mode
    Given I am on CategoryBrowsePage for "Fitbit" under "WATCHES" in Domestic mode
    Then I should be in category browse page
    And I verify that pagination is not displayed

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page @discovery_daily_run
  Scenario Outline: CategoryBrowsePage - Verify pagination using Next/Previous button
    Given I am on CategoryBrowsePage for "<Category_Name>" under "<FOB>" in <shopping_mode> mode
    And I click 2 pagination number
    Then I verify that navigated to page 2 on browse page
    And I click on next pagination button
    Then I verify that navigated to page 3 on browse page
    When I click on previous pagination button
    Then I verify that navigated to page 2 on browse page
    Examples:
     | shopping_mode |Category_Name |FOB     |
     | Domestic      |Shorts        |Men     |
     | Registry      |Bakeware      |Kitchen |
     | Iship         |Boots         |Shoes   |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Verify pagination using Next/Previous button
    Given I am on CategoryBrowsePage for "<Category_Name>" under "<FOB>" in <shopping_mode> mode
    Then I should be in Category Browse page
    And I click 2 pagination number
    Then I verify that navigated to page 2 on browse page
    And I verify that all the product thumbnails displayed properly on the Category Browse page
    When I click on next pagination button
    Then I verify that navigated to page 3 on browse page
    And I verify that all the product thumbnails displayed properly on the Category Browse page
    When I click on previous pagination button
    Then I verify that navigated to page 2 on browse page
    And I verify that all the product thumbnails displayed properly on the Category Browse page
    Examples:
      | shopping_mode | Category_Name | FOB     |
      | Domestic      | Shorts        | Men     |
      | Registry      | Bakeware      | Kitchen |
      | Iship         | Boots         | Shoes   |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Verify pagination in ALL modes
    Given I am on CategoryBrowsePage for "<Category_Name>" under "<FOB>" in <shopping_mode> mode
    Then I verify that pagination works
    Examples:
      | shopping_mode | Category_Name | FOB     |
      | Domestic      | Shorts        | Men     |
      | Registry      | All Luggage   | Luggage |
      | Iship         | Boots         | Shoes   |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Verify previous pagination button in ALL modes
    Given I am on CategoryBrowsePage for "<Category_Name>" under "<FOB>" in <shopping_mode> mode
    When I click 3 pagination number
    Then I verify that navigated to page 3 on browse page
    And I verify that all the product thumbnails displayed properly on the Category Browse page
    When I click on previous pagination button
    Then I verify that navigated to page 2 on browse page
    And I verify that all the product thumbnails displayed properly on the Category Browse page
    Examples:
      | shopping_mode | Category_Name | FOB     |
      | Domestic      | Shorts        | Men     |
      | Registry      | All Luggage   | Luggage |
      | Iship         | Boots         | Shoes   |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Verify Next pagination button in ALL modes
    Given I am on CategoryBrowsePage for "<Category_Name>" under "<FOB>" in <shopping_mode> mode
    When I click 2 pagination number
    Then I verify that navigated to page 2 on browse page
    And I verify that all the product thumbnails displayed properly on the Category Browse page
    When I click on next pagination button
    Then I verify that navigated to page 3 on browse page
    And I verify that all the product thumbnails displayed properly on the Category Browse page
    Examples:
      | shopping_mode | Category_Name | FOB     |
      | Domestic      | Shorts        | Men     |
      | Registry      | All Luggage   | Luggage |
      | Iship         | Boots         | Shoes   |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Verify browser back button takes to previously navigated page before navigating to PDP in ALL modes
    Given I am on CategoryBrowsePage for "<Category_Name>" under "<FOB>" in <shopping_mode> mode
    When I click 2 pagination number
    Then I verify that navigated to page 2 on browse page
    And I verify that all the product thumbnails displayed properly on the Category Browse page
    When I click on next pagination button
    Then I verify that navigated to page 3 on browse page
    And I verify that all the product thumbnails displayed properly on the Category Browse page
    When I click on next pagination button
    Then I verify that navigated to page 4 on browse page
    And I verify that all the product thumbnails displayed properly on the Category Browse page
    And I navigate to PDP of the first product
    And I select browse 'back' button
    Then I verify that navigated to page 4 on browse page
    Examples:
     | shopping_mode |Category_Name   |FOB     |
     | Domestic      |Shorts          |Men     |
     | Registry      |All Luggage     |Luggage |
     | Iship         |Boots           |Shoes   |

  #Note:  paginate,navigate to product and press browser back button, check url
  # paginate,choose quick view and press browser back button, check url
  # paginate , press forward backward button in between

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario: CategoryBrowsePage - Domestic - Verify Sort By functionality with pagination with 60 items per page
    Given I am on CategoryBrowsePage for "Jeans" under "Men" in DOMESTIC mode
    When I filter the result set to show "60" items per page
    And I click 3 pagination number
    Then I verify that navigated to page 3 on browse page
    And I verify that all the product thumbnails displayed properly on the Category Browse page
    When I click on previous pagination button
    Then I verify that navigated to page 2 on browse page
    And I verify that all the product thumbnails displayed properly on the Category Browse page
    When I select "Best Sellers" in sort by drop down
    Then I verify that navigated to page 1 on browse page

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario: SearchResultsPage - Domestic - Verify Sort By functionality with pagination with 120 items per page
     Given I am on CategoryBrowsePage for "Jeans" under "Men" in DOMESTIC mode
    When I filter the result set to show "120" items per page
    And I click 3 pagination number
    Then I verify that navigated to page 3 on browse page
    And I verify that all the product thumbnails displayed properly on the Category Browse page
    When I click on previous pagination button
    Then I verify that navigated to page 2 on browse page
    And I verify that all the product thumbnails displayed properly on the Category Browse page
    When I select "Best Sellers" in sort by drop down
    Then I verify that navigated to page 1 on browse page

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario: SearchResultsPage - Domestic - Verify "60" item per page functionality with pagination
    Given I am on CategoryBrowsePage for "Jeans" under "Men" in DOMESTIC mode
    And I click 3 pagination number
    Then I verify that navigated to page 3 on browse page
    And I verify that all the product thumbnails displayed properly on the Category Browse page
    When I click on previous pagination button
    Then I verify that navigated to page 2 on browse page
    And I verify that all the product thumbnails displayed properly on the Category Browse page
    When I filter the result set to show "60" items per page
    Then I verify that navigated to page 1 on browse page

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page @xbrowser_browse
  Scenario: SearchResultsPage - Domestic - Verify "120" item per page functionality with pagination
    Given I am on CategoryBrowsePage for "Jeans" under "Men" in DOMESTIC mode
    And I click 3 pagination number
    Then I verify that navigated to page 3 on browse page
    And I verify that all the product thumbnails displayed properly on the Category Browse page
    When I click on previous pagination button
    Then I verify that navigated to page 2 on browse page
    And I verify that all the product thumbnails displayed properly on the Category Browse page
    When I filter the result set to show "120" items per page
    Then I verify that navigated to page 1 on browse page