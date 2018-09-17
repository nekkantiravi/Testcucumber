#Author: Discovery QE
#Date Created: 25/11/2016


Feature: Manual Scenarios in DOMESTIC, ISHIP and REGISTRY mode

  # Test Case ID: RT-06271
  # Prerequisite: JavaScript should be disabled on the browser
  @artifact_navapp @domain_discovery @mode_domestic @use_manual @wip @priority_medium
  Scenario: BrowsePage - Domestic - Verify JavaScript Compatibility Error Message
    Given I visit the web site as a guest user
    Then I should see javascript compatibility error message
    When I navigate to category splash page
    Then I should see javascript compatibility error message
    When I navigate to any browse page
    Then I should see javascript compatibility error message
    When I navigate to any DLP page
    Then I should see javascript compatibility error message
    When I navigate to any legacy page
    Then I should see javascript compatibility error message
    When I navigate to any SLP page
    Then I should see javascript compatibility error message
    When I search for "jeans"
    Then I should see javascript compatibility error message
    When I navigate to any compaign page
    Then I should see javascript compatibility error message
  # Follow below steps to disable javascript in FF & IE browsers
  #  In new versions of FF, use following steps to disable/ enable Java Script.
  #
  #  1. In the address bar, type "about:config" (with no quotes), and press Enter.
  #  2. Click "I'll be careful, I promise"
  #  3. In the search bar, search for "javascript.enabled" (with no quotes).
  #  4. Right click the result named "javascript.enabled" and click "Toggle". JavaScript is now disabled.
  #
  #  Steps to deselect in IE
  #  1. Click on the 'Internet Options' inside 'Tools' menu
  #  2. Select Security tab and click on Custom level.
  #  3. Select disable Active scripting option under scripting section and click Ok


  # Test Case ID: RT-06398
  @artifact_navapp @domain_discovery @mode_domestic @mode_registry @mode_iship @use_manual @wip @priority_medium
  Scenario Outline: BrowsePage - Domestic|Iship|Registry - Verify the selected color carry over from Browse page, quick peek to PDP
    Given I visit the web site as a guest user
    When I navigate in "<mode>" to "<page_type>" page
    Then I should be landed on "<page_type>" page
    When I select any color swatch for random product
    Then I should see colorized image is displayed
    When I select 'quick peek' button for same product
    Then I should see select color swatch and colorized images are appeared on QP overlay
    When I select 'See Full Product Details' link on QP overlay
    Then I should be landed on respective pdp page
    And I should see select color swatch and colorized images are appeared on PDP page
  Examples:
    | page_type | mode          |
    | Browse    | normal        |
    | Browse    | registry      |
    | Browse    | international |

  # Test Case ID: RT-06398
  @artifact_navapp @domain_discovery @mode_domestic @mode_registry @mode_iship @use_manual @wip @priority_medium
  Scenario Outline: BrowsePage - Domestic|Iship|Registry - Verify the color retention for color facets from browse to PDP
    Given I visit the web site as a guest user
    When I navigate in "<mode>" to "<page_type>" page
    Then I should be landed on "<page_type>" page
    When I select any color facet
    Then I verify that products are filtered with selected color facet values
    And I verify product thumbnail image with selected color or the relevant jumbo color swatch
    When I select random product from thumbnail grid
    Then I should see selected color or the relevant jumbo color swatch in PDP
  Examples:
    | page_type | mode          |
    | Browse    | normal        |
    | Browse    | registry      |
    | Browse    | international |

  # Test Case ID: RT-06507
  @artifact_navapp @domain_discovery @mode_domestic @mode_registry @mode_iship @use_manual @wip @priority_medium
  Scenario Outline: BrowsePage - Domestic|Iship|Registry - Verify Badge text in browse page
    Given I visit the web site as a guest user
    When I navigate in "<mode>" to "<page_type>" page
    Then I should be landed on "<page_type>" page
    And I should see browse page badge text under product thumbnail
  #Ex: Bonus offer badge
  Examples:
    | page_type | mode          |
    | Browse    | normal        |
    | Browse    | registry      |
    | Browse    | international |

  # Test Case ID: RT-06506
  @artifact_navapp @domain_discovery @mode_domestic @mode_registry @mode_iship @use_manual @wip @priority_medium
  Scenario Outline: BrowsePage - Verify new registry URL structure
    Given I visit the web site as a guest user
    When I navigate in "<mode>" to "<page_type>" page
    Then I should be landed on "<page_type>" page
    When I navigate to sign in page from the header
    And I sign in with valid credentials
    Then I should be navigated to respective browse page
  Examples:
    | page_type | mode          |
    | Browse    | normal        |
    | Browse    | registry      |
    | Browse    | international |

  # Test Case ID: RT-06347
  @artifact_navapp @domain_discovery @mode_domestic @mode_registry @mode_iship @use_manual @wip @priority_medium
  Scenario Outline: BrowsePage - Domestic|Iship|Registry - Verify user is successfully able to add product to shopping bag using Brand facets
    Given I am on BrowsePage in <shopping_mode> mode
    When I select 'multiple' facet value from 'brand' facet section
    Then I verify that products are filtered with selected facet values
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select "AddToBag" button on PDP page
    Then I should see "ADD TO BAG" overlay is opened
    And I select "CHECKOUT" button in overlay
    Then I should be navigated to Shopping Bag page
  Examples:
    | shopping_mode |
    | Domestic      |
    | Iship         |
    | Registry      |
  # Notes:
  # Select any special characters facets from BRAND facet Ex: Estee Lauder or Lancome