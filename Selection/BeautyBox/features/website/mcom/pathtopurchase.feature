Feature: Beauty Box PTP Feature File


  @PTPFullSizeProducts
  Scenario Outline: As a guest user I want to see PTP full size products when user navigates from about page
    Given I visit the web site as a guest user
    And I launch beautybox about page url
    When I click on "<shopTheProduct>" on about page for default month "<ptpPrice>"

    Examples:
    |shopTheProduct|ptpPrice|
    |SHOP FULL SIZE PRODUCTS|productsDesc|


  @WIP
  Scenario Outline: As a logged in user I want to add a product to bag
    Given I visit the web site as a guest user
    And I launch beautybox about page url
    When I click on "<shopTheProduct>" on about page for default month
     #  Then I navigate to ptp full size products to validate images and description
     # Then I navigate to ptp full size products to validate products and description by passing "year" and "month"

    Examples:
      |shopTheProduct|
      |Shop the products.|
      |shoptheproductimage|

  @WIP
  Scenario Outline: As a logged in user I want to compare PTP product original price and service response original prices
    Given I visit the web site as a guest user
    And I launch beautybox about page url
    When I click on "<shopTheProduct>" on about page for default month "<ptpPrice>"
    Then I navigate to ptp full size products to validate products original price

    Examples:
    |shopTheProduct|ptpPrice|
    |Shop the products.|originalPrice|
    |shoptheproductimage|originalPrice|

  @WIP
  Scenario Outline: As a logged in user I want to compare PTP product sale price and service response sale prices
    Given I visit the web site as a guest user
    And I launch beautybox about page url
    When I click on "<shopTheProduct>" on about page for default month "<ptpPrice>"
    Then I navigate to ptp full size products to validate products retail price

    Examples:
    |shopTheProduct|ptpPrice|
    |Shop the products.|retailPrice|
    |shoptheproductimage|retailPrice|

  @WIP
  Scenario Outline: As a logged in user I want to see PTP full size products
    Given I visit the web site as a guest user
    # And I launch beautybox about page url
    When I select month and year from "<pageName>" page dropdown to validate "<ptpPrice>"

    Examples:
    |pageName|ptpPrice|
    |monthlyBox|productsDesc|
    |monthlyBox|originalPrice|
    |monthlyBox|retailPrice|

