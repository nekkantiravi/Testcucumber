#Author: Discovery QE
#Date Created: 06/12/2015

Feature: Verify SubCategory Pages - Left Nav Red Links

  @dsv_category_26 @use_regression @artifact_navapp @domain_discovery @priority_high @snbc_comp
  Scenario: BrowsePage - Verify Red Links - HOME - Kitchen
    Given I am on CategoryBrowsePage for "7497" category id in Domestic mode
    #Given I am on Browse Page for "Kitchen" under "HOME"
    Then I verify red link present in LNA
    # Notes:
    # Do full validation -
    # Red links should be present on top or bottom of LNA & should navigate correctly

  @dsv_category_26 @use_regression @artifact_navapp @domain_discovery @priority_high @snbc_comp
  Scenario: BrowsePage - Verify Red Links - HOME - Dining & Entertaining
    Given I am on CategoryBrowsePage for "7498" category id in Domestic mode
    #Given I am on Browse Page for "Dining & Entertaining" under "HOME"
    Then I verify red link present in LNA
    # Notes:
    # Do full validation -
    # Red links should be present on top or bottom of LNA & should navigate correctly

  @dsv_category_26 @use_regression @artifact_navapp @domain_discovery @priority_high @snbc_comp
  Scenario: BrowsePage - Verify Red Links - HOME - Furniture
    Given I am on CategoryBrowsePage for "29391" category id in Domestic mode
    #Given I am on Browse Page for "Furniture" under "HOME"
    Then I verify red link present in LNA
    # Notes:
    # Do full validation -
    # Red links should be present on top or bottom of LNA & should navigate correctly

  @dsv_category_7 @use_regression @artifact_navapp @domain_discovery @priority_high @snbc_comp
  Scenario: BrowsePage - Verify Red Links - HOME - Mattresses
    Given I am on CategoryBrowsePage for "25931" category id in Domestic mode
    #Given I am on Browse Page for "Mattresses" under "HOME"
    Then I verify red link present in LNA
    # Notes:
    # Do full validation -
    # Red links should be present on top or bottom of LNA & should navigate correctly

  @use_regression @artifact_navapp @domain_discovery @priority_high @snbc_comp
  Scenario: BrowsePage - Verify Red Links - HOME - Outdoor Furniture
    Given I am on CategoryBrowsePage for "7495" category id in Domestic mode
    #Given I am on Browse Page for "Bed & Bath" under "HOME"
    Then I verify red link present in LNA
    # Notes:
    # Do full validation -
    # Red links should be present on top or bottom of LNA & should navigate correctly

  @dsv_category_7 @use_regression @artifact_navapp @domain_discovery @priority_high @snbc_comp
  Scenario: BrowsePage - Verify Red Links - HOME - Luggage
    Given I am on CategoryBrowsePage for "16908" category id in Domestic mode
    #Given I am on Browse Page for "luggage & backpacks" under "HOME"
    Then I verify red link present in LNA
    # Notes:
    # Do full validation -
    # Red links should be present on top or bottom of LNA & should navigate correctly

  @dsv_category_7 @use_regression @artifact_navapp @domain_discovery @priority_high @snbc_comp
  Scenario: BrowsePage - Verify Red Links - WOMEN - Plus Sizes
    Given I am on CategoryBrowsePage for "32147" category id in Domestic mode
    #Given I am on Browse Page for "All Plus Sizes" under "WOMEN"
    Then I verify red link present in LNA
    # Notes:
    # Do full validation -
    # Red links should be present on top or bottom of LNA & should navigate correctly

  @dsv_category_7 @use_regression @artifact_navapp @domain_discovery @priority_high @snbc_comp
  Scenario:BrowsePage - Verify Red Links - WOMEN - Petite
    Given I am on CategoryBrowsePage for "18579" category id in Domestic mode
    #Given I am on Browse Page for "All Petites" under "WOMEN"
    Then I verify red link present in LNA
    # Notes:
    # Do full validation -
    # Red links should be present on top or bottom of LNA & should navigate correctly

  @dsv_category_7 @use_regression @artifact_navapp @domain_discovery @priority_high @snbc_comp
  Scenario: BrowsePage - Verify Red Links - WOMEN - Lingerie
    Given I am on CategoryBrowsePage for "225" category id in Domestic mode
    #Given I am on Browse Page for "Bras, Panties & Shapewear" under "WOMEN"
    Then I verify red link present in LNA
    # Notes:
    # Do full validation -
    # Red links should be present on top or bottom of LNA & should navigate correctly