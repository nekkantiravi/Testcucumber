Feature: As a mobile user i want to search products in easy views with limited products on each page

  ### Domestic and Iship modes ###

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: DLP - As a mobile user I should see not more than 48 products in DLP view per page
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate to "Adidas" dynamic landing page in <mode> using mobile website
    Then I should see all products displayed in dlp page
    Then I should see not more than 48 products in the DLP page
    And I should see pagination is displayed
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: DLP - As a mobile user I should see no pagination with less than 48 products in DLP page
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate to "3 Sprouts" dynamic landing page in <mode> using mobile website
    Then I should see all products displayed in dlp page
    And Pagination should not be displayed
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: DLP - As a mobile user when I tap on next button next page should be displayed with appropriate products on DLP page
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate to "Adidas" dynamic landing page in <mode> using mobile website
    Then pagination should be displayed and defaulted to first page
    When I navigate to next page on dlp page
    Then I should see all products displayed in dlp page
    And current page number should be equal 2
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: DLP - As a mobile user when I tap on prev button previous page should be displayed with appropriate products on DLP page
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate to "Adidas" dynamic landing page in <mode> using mobile website
    When I select 3 page on DLP page
    And I navigate to prev page on dlp
    Then I should see all products displayed in dlp page
    And current page number should be decreased by 1
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: DLP - As a mobile user I should see correct number of pages in pagination on DLP page
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate to "Adidas" dynamic landing page in <mode> using mobile website
    Then I should see pagination is displayed
    And I should see correct number of pages in pagination
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: DLP - As a mobile user I should be able to navigate different pages in pagination on DLP page
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate to "Adidas" dynamic landing page in <mode> using mobile website
    Then I should see pagination is displayed
    When I select 3 page on DLP page
    Then selected page should be displayed in pagination
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: DLP - Verify Next and Prev buttons on DLP pagination
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate to "Adidas" dynamic landing page in <mode> using mobile website
    Then I should see pagination is displayed
    And I should see next button on pagination
    And I should not see prev button on pagination
    When I navigate to last page
    Then I should not see next button on pagination
    And I should see prev button on pagination
    Examples:
      | mode     |
      | domestic |
      | iship    |