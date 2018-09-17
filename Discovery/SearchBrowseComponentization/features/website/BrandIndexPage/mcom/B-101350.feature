# Author: QE Team
# Date Created: 01/28/2018
# Story B-101350

Feature: Verifying canvas data is displayed on BrandIndex page and Subcategory pages on different modes

  @SNBC_Phase4
  Scenario Outline: Verify canvas data is displayed on All Brands page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to Brands index page
    And I verify canvas data is present in service on BrandIndex page
    Then I verify canvas data is displayed in row 101 on BrandIndex page
    And I verify canvas data supports only Image or Imagemap media on BrandIndex page
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |
      | registry      |

  @SNBC_Phase4
  Scenario Outline: Verify canvas data supports Image or Imagemap on Subcategory pages on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to Brands index page
    And I select "<brand>" category from left navigation
    And I verify canvas data is present in service on BrandIndex page
    Then I verify canvas data is displayed in row 101 on BrandIndex page
    And I verify canvas data supports only Image or Imagemap media on BrandIndex page
    Examples:
      | shopping_mode | brand  |
      | domestic      | Beauty |
      | iship         | Dining |
      | registry      | Dining |

  @SNBC_Phase4 @manual
  Scenario Outline: Verify canvas data is displayed above A-Z index on All Brands page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to Brands index page
    Then I verify canvas row is displaying above A-Z index
    And I verify canvas row is displayed as per sequence
    Examples:
      | shopping_mode | brand  |
      | domestic      | Beauty |
      | iship         | Dining |
      | registry      | Dining |

  @SNBC_Phase4 @manual
  Scenario Outline: Verify canvas data is displayed above A-Z index on Subcategory pages on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to Brands index page
    And I select "<brand>" category from left navigation
    Then I verify canvas row is displaying above A-Z index
    And I verify canvas row is displayed as per sequence
    Examples:
      | shopping_mode | brand  |
      | domestic      | Beauty |
      | iship         | Dining |
      | registry      | Dining |