Feature: As a mobile user i want to browse products in easy views with limited products on each page

  @dsv_mew_sev1 @domain_discovery
  Scenario: As a mobile user I should see not more than 90 products in category browse view per page
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate the global navigation menu as follows:
      |Women|
      |Coats|
    Then I should see the "category_browse" Page
    Then I verify total product available on browse page is more then 90
    Then I should see not more than 90 products in the browse page
    And pagination should be displayed on browse page

  @dsv_mew_sev1 @domain_discovery
  Scenario: As a mobile user I should see no pagination with less than 90 products in category browse page
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate the global navigation menu as follows:
      |Jewelry & Accessories|
      |Gloves|
    Then I should see the "category_browse" Page
    Then I verify total product available on browse page is less then 90
    And no pagination should be displayed on browse page

  @dsv_mew_sev1 @domain_discovery
  Scenario: As a mobile user when I tap on next button next page should be displayed with appropriate products
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate the global navigation menu as follows:
      |Women|
      |Coats|
    Then I should see the "category_browse" Page
    Then I verify total product available on browse page is more then 90
    Then browse pagination should be displayed and defaulted to first page
    When I navigate to "next" page on browse page
    Then I should see not more than 90 products in the browse page
    And current browse page number should be equal to 2

  @dsv_mew_sev1 @domain_discovery
  Scenario: As a mobile user when I tap on prev button previous page should be displayed with appropriate products
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate the global navigation menu as follows:
      |Women|
      |Coats|
    Then I should see the "category_browse" Page
    Then I verify total product available on browse page is more then 90
    Then browse pagination should be displayed and defaulted to first page
    When I navigate to "next" page on browse page
    Then I should see not more than 90 products in the browse page
    And current browse page number should be equal to 2
    When I navigate to "prev" page on browse page
    Then I should see not more than 90 products in the browse page
    And current browse page number should be equal to 1

  @dsv_mew_sev1 @domain_discovery
  Scenario:  As a mobile user I should see first page when I tap on next button from last page
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate the global navigation menu as follows:
      |Women|
      |Coats|
    Then I should see the "category_browse" Page
    Then I verify total product available on browse page is more then 90
    Then browse pagination should be displayed and defaulted to first page
    When I navigate to "last" page on browse page
    Then I navigate to "next" page on browse page
    And current browse page number should be equal to 1

  @dsv_mew_sev1 @domain_discovery
  Scenario: As a mobile user I should see last page when I tap on prev button from first page
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate the global navigation menu as follows:
      |Women|
      |Coats|
    Then I should see the "category_browse" Page
    Then I verify total product available on browse page is more then 90
    Then browse pagination should be displayed and defaulted to first page
    When I navigate to "prev" page on browse page
    And current page number should be set to last page

  @dsv_mew_sev1 @domain_discovery
  Scenario:  As a mobile user I should be able to navigate different pages in pagination
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate the global navigation menu as follows:
      |Women|
      |Coats|
    Then I should see the "category_browse" Page
    Then browse pagination should be displayed and defaulted to first page
    When I select first page from drop-down
    And current browse page number should be equal to 1

  @dsv_mew_sev1 @domain_discovery
  Scenario: As a mobile user I should see correct number of pages in pagination
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate the global navigation menu as follows:
      |Women|
      |Coats|
    Then I should see the "category_browse" Page
    Then browse pagination should be displayed and defaulted to first page
    And current browse page number should be equal to 1