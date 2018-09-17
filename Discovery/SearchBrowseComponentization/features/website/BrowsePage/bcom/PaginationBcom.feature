Feature: Pagination verification on Category Browse Page

  @domain_discovery @high @project_snb @mode_all @snbc_comp @use_regression @migrated_to_sdt
  Scenario Outline: CategoryBrowsePage - Verify pagination in ALL modes
    Given I am on CategoryBrowsePage for "<Category_Name>" under "<FOB>" in <shopping_mode> mode
    And I clear existing class variables to avoid data issues
    Then I verify that pagination works
    Examples:
      | shopping_mode |Category_Name |FOB     |
      | Domestic      |Pants         |Women   |
      | Registry      |Cookware      |Kitchen |
      | Iship         |Boots         |Shoes   |

  @use_regression @domain_discovery @project_snb @mode_all @priority_medium @snbc_comp @migrated_to_sdt
  Scenario Outline: CategoryBrowsePage - Verify Without Pagination in ALL mode
    Given I am on CategoryBrowsePage for "<Category_Name>" under "<FOB>" in <shopping_mode> mode
    And I clear existing class variables to avoid data issues
    When I select 'multiple' facet value from 'any' facet sections
    And I verify that pagination is not displayed
    Examples:
      | shopping_mode |Category_Name |FOB     |
      | Domestic      |Pants         |Women   |
      | Registry      |Lighting      |Home Decor |
      | Iship         |Boots         |Shoes   |

  @domain_discovery @priority_medium @mode_all @project_snb @snbc_comp @use_regression @migrated_to_sdt
  Scenario Outline: CategoryBrowsePage - Verify Pagination using Next / Previous button in ALL modes
    Given I am on CategoryBrowsePage for "<Category_Name>" under "<FOB>" in <shopping_mode> mode
    And I clear existing class variables to avoid data issues
    And I click 2 pagination number
    Then I verify that navigated to page 2 on browse page
    And I click on next pagination button
    Then I verify that navigated to page 3 on browse page
    When I click on previous pagination button
    Then I verify that navigated to page 2 on browse page
    Examples:
      | shopping_mode |Category_Name |FOB     |
      | Domestic      |Pants         |Women   |
      | Registry      |Cookware      |Kitchen |
      | Iship         |Boots         |Shoes   |

  @use_regression @registry @priority_high @mode_all @project_snb @snbc_comp @migrated_to_sdt
  Scenario Outline: CategoryBrowsePage - Verify Pagination and products using Next / Previous button in ALL Modes
    Given I am on CategoryBrowsePage for "<Category_Name>" under "<FOB>" in <shopping_mode> mode
    And I clear existing class variables to avoid data issues
    And I click 2 pagination number
    Then I verify that navigated to page 2 on browse page
    And I verify that all the product thumbnails displayed properly on the Search Landing page
    When I click on next pagination button
    Then I verify that navigated to page 3 on browse page
    And I verify that all the product thumbnails displayed properly on the Search Landing page
    When I click on previous pagination button
    Then I verify that navigated to page 2 on browse page
    And I verify that all the product thumbnails displayed properly on the Search Landing page
    Examples:
      | shopping_mode |Category_Name |FOB     |
      | Domestic      |Pants         |Women   |
      | Registry      |Cookware      |Kitchen |
      | Iship         |Boots         |Shoes   |

  @domain_discovery @mode_domestic @mode_all @mode_all @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Verify previous pagination button in ALL modes
    Given I am on CategoryBrowsePage for "<Category_Name>" under "<FOB>" in <shopping_mode> mode
    And I clear existing class variables to avoid data issues
    When I click 3 pagination number
    Then I verify that navigated to page 3 on browse page
    And I verify that all the product thumbnails displayed properly on the Category Browse page
    When I click on previous pagination button
    Then I verify that navigated to page 2 on browse page
    And I verify that all the product thumbnails displayed properly on the Category Browse page
    Examples:
      | shopping_mode |Category_Name |FOB     |
      | Domestic      |Pants         |Women   |
      | Registry      |Cookware      |Kitchen |
      | Iship         |Boots         |Shoes   |

  @domain_discovery @mode_domestic @mode_all @mode_all @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Verify Next pagination button in ALL modes
    Given I am on CategoryBrowsePage for "<Category_Name>" under "<FOB>" in <shopping_mode> mode
    And I clear existing class variables to avoid data issues
    When I click 2 pagination number
    Then I verify that navigated to page 2 on browse page
    And I verify that all the product thumbnails displayed properly on the Category Browse page
    When I click on next pagination button
    Then I verify that navigated to page 3 on browse page
    And I verify that all the product thumbnails displayed properly on the Category Browse page
    Examples:
      | shopping_mode |Category_Name |FOB     |
      | Domestic      |Pants         |Women   |
      | Registry      |Cookware      |Kitchen |
      | Iship         |Boots         |Shoes   |

  @domain_discovery @mode_domestic @mode_registry @mode_all @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Verify browser back button takes to previously navigated page before navigating to PDP in ALL modes
    Given I am on CategoryBrowsePage for "<Category_Name>" under "<FOB>" in <shopping_mode> mode
    And I clear existing class variables to avoid data issues
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
      | shopping_mode |Category_Name |FOB     |
      | Domestic      |Pants         |Women   |
      | Registry      |Cookware      |Kitchen |
      | Iship         |Boots         |Shoes   |

  @domain_discovery @mode_domestic @mode_registry @mode_all @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic - Verify Sort By functionality with pagination with 90 items per page
    Given I am on CategoryBrowsePage for "<Category_Name>" under "<FOB>" in <shopping_mode> mode
    And I clear existing class variables to avoid data issues
    When I filter the result set to show "90" items per page
    And I click 3 pagination number
    Then I verify that navigated to page 3 on browse page
    And I verify that all the product thumbnails displayed properly on the Category Browse page
    When I click on previous pagination button
    Then I verify that navigated to page 2 on browse page
    And I verify that all the product thumbnails displayed properly on the Category Browse page
    When I select "Best Sellers" in sort by drop down
    Then I verify that navigated to page 1 on browse page
    Examples:
      | shopping_mode |Category_Name |FOB     |
      | Domestic      |Pants         |Women   |
      | Registry      |Cookware      |Kitchen |
      | Iship         |Boots         |Shoes   |

  @domain_discovery @mode_domestic @mode_all @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic - Verify Sort By functionality with pagination with 180 items per page
    Given I am on CategoryBrowsePage for "<Category_Name>" under "<FOB>" in <shopping_mode> mode
    And I clear existing class variables to avoid data issues
    When I filter the result set to show "180" items per page
    And I click 3 pagination number
    Then I verify that navigated to page 3 on browse page
    And I verify that all the product thumbnails displayed properly on the Category Browse page
    When I click on previous pagination button
    Then I verify that navigated to page 2 on browse page
    And I verify that all the product thumbnails displayed properly on the Category Browse page
    When I select "Best Sellers" in sort by drop down
    Then I verify that navigated to page 1 on browse page
    Examples:
      | shopping_mode |Category_Name |FOB     |
      | Domestic      |Pants         |Women   |
      | Registry      |Cookware      |Kitchen |
      | Iship         |Boots         |Shoes   |

  @domain_discovery @mode_domestic @mode_registry @mode_all @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic - Verify "90" item per page functionality with pagination
    Given I am on CategoryBrowsePage for "<Category_Name>" under "<FOB>" in <shopping_mode> mode
    And I clear existing class variables to avoid data issues
    And I click 3 pagination number
    Then I verify that navigated to page 3 on browse page
    And I verify that all the product thumbnails displayed properly on the Category Browse page
    When I click on previous pagination button
    Then I verify that navigated to page 2 on browse page
    And I verify that all the product thumbnails displayed properly on the Category Browse page
    When I filter the result set to show "90" items per page
    Then I verify that navigated to page 1 on browse page
  Examples:
    | shopping_mode |Category_Name |FOB     |
    | Domestic      |Pants         |Women   |
    | Registry      |Cookware      |Kitchen |
    | Iship         |Boots         |Shoes   |

  @test @domain_discovery @mode_domestic @mode_registry @mode_all @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage -  Verify "180" item per page functionality with pagination in ALL mode
    Given I am on CategoryBrowsePage for "<Category_Name>" under "<FOB>" in <shopping_mode> mode
    And I click 3 pagination number
    Then I verify that navigated to page 3 on browse page
    And I verify that all the product thumbnails displayed properly on the Category Browse page
    When I click on previous pagination button
    Then I verify that navigated to page 2 on browse page
    And I verify that all the product thumbnails displayed properly on the Category Browse page
    When I filter the result set to show "180" items per page
    Then I verify that navigated to page 1 on browse page
    Examples:
      | shopping_mode |Category_Name |FOB     |
      | Domestic      |Pants         |Women   |
      | Registry      |Cookware      |Kitchen |
      | Iship         |Boots         |Shoes   |