# Author: Discovery Regression QE Team
# Created Date: 11/15/2016

Feature: MCOM :: Browse Page scenarios missing from current scope


  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship
  Scenario Outline: BrowsePage - Domestic|Iship|Registry - Verify price on quick viw overlay for member/standard product
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I select "quick view" button for "member" product on page
    Then I verify that quick peek is displayed
    Then I verify price on quick view overlay is same as on thumbnail grid
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 25122       |
      | Registry      | 31760       |
      | Iship         | 25122       |

  # Navigate to any browse page where we have member/standard products in each mode
  # Get price content section data from thumbnail grid and select quick view button for that member/standard product
  # Verify same price values are displayed on QV overlay or not

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship
  Scenario Outline: BrowsePage - Domestic|Iship|Registry - Verify price on quick viw overlay for master product
    Given I am on CategoryBrowsePage for "7502" category id in <shopping_mode> mode
    And I select "quick view" button for "master" product on page
    Then I verify that quick peek is displayed
    Then I verify price on quick view overlay is same as on thumbnail grid
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |
  # Navigate to any browse page where we have master products in each mode
  # Get price content section data from thumbnail grid and select quick view button for that master product
  # Verify same price values are displayed on QV overlay or not

  @domain_discovery @priority_low @mode_domestic @mode_registry @mode_iship @use_regression @discovery_daily_run
  Scenario Outline: BrowsePage - Domestic|Iship|Registry - Verify bookmarked faceted URL loading without any error
    Given I visit the web site as a guest user in "<mode>" mode
    When I navigate to the bookmarked url:
      | MCOM | <url> |
    Then I should be navigated to "category browse" page
    Examples:
      | mode     | url                                                                                                                                                         |
      | domestic | /shop/makeup-and-perfume/lancome/Pageindex,Sortby/2,TOP_RATED?id=28688                                                                                      |
      | domestic | /shop/makeup-and-perfume/makeup/Brand,Price,Productsperpage/Estée%20Lauder%7CLancôme,10%257C150,120?id=30077                                                |
      | registry | /shop/wedding-registry/cleaning-organizing/all-cleaning-organizing/Brand,Productsperpage/OXO,40?id=66456&cm_sp=reg_hdr-_-cleaning-%26-organizing-_-oxo_COL2 |
      | registry | /shop/wedding-registry/bed-bath/bedding-collections/Color_normal,Custratings/Yellow,4%20stars%20%26%20up?id=7502                                            |
      | iship    | /shop/bed-bath/bedding-collections/Color_normal,Custratings/Yellow,4%20stars%20%26%20up?id=7502&fromPage=contextPage                                        |
      | iship    | /shop/jewelry-watches/mens-watches/Case_size,Price,Sortby/Men%27s%20Large%20%2842mm-49mm%29,1%257C100,PRICE_HIGH_TO_LOW?id=57386                            |
