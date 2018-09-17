#Author: Discovery QE
#Date Created: 06/12/2015

Feature: Product Thumbnails on Category Browse Page

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: Browse Page & PDP page - Verify thumbnails when navigated through browser back button in DOMESTIC mode
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that all the product thumbnails displayed properly on the Category Browse page
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 25122       |
      | Registry      | 31760       |
      | Iship         | 25122       |

  #Testlink-MCOM-65646
  @use_regression @priority_high @domain_discovery @mode_domestic @snbc_comp @wip @invalid
  Scenario: Category Browse page - Verify 'more' button and Overlay for color swatches in DOMESTIC mode
    Given I am on CategoryBrowsePage for "25122" category id in Domestic mode
    Then I verify the color swatches and more button
    # this feature is not applicable for MCOM
    #Notes: User should able to see 'more' button along side 6 color swatches for products having mroe than 8 colours.
    # An overlay with a full assortment of available colors should display as below.
    #- If 8 colors -  you will see 8 swatches
    #- If more than 8 but less than 32 (you will see 6 + more in the overlay or 7 in the overlay)

  #Testlink-MCOM-85250 Vone - RT-07305
  @use_regression @priority_high @domain_discovery @mode_domestic @snbc_comp
  Scenario: BrowsePage - Verify product thumbnail color swatches display for product in DOMESTIC mode
    Given I am on CategoryBrowsePage for "25122" category id in Domestic mode
    When I select a product having color swatches
    When I "hover on" any color in color swatches section
    Then I should see product displays "color swatch" image
    When I "hover off" any color in color swatches section
    Then I should see product displays "default" image
    When I "select" any color in color swatches section
    Then I should see selected color is highlighted in the color swatch for that product

    # Below functionality is not exists hence commented the steps
#    When I navigate to next page of thumbnail grid
#    And I navigate to previous page from existing page
#    Then I should see selected color is highlighted in the color swatch for that product

    #Notes: Color swatches should appear under the product thumbnail.
    # Upon hover over on a color swatch product thumbnail should change to the hovered category and color swatch should be highlighted
    # Upon Hover off on a color swatch product thumbnail should restore to the default image
    # Click on a color swatch product thumbnail should change to the hovered category and color swatch should be highlighted

  #Testlink-MCOM-96552 Vone - RT-07306
  @use_regression @priority_high @domain_discovery @mode_domestic @snbc_comp @xbrowser_browse
  Scenario: BrowsePage - Verify color swatch display when product thumbnail has no colorised images  in DOMESTIC mode
    Given I am on CategoryBrowsePage for "60363" category id in Domestic mode
    When I select a product having jumbo swatches
    And I "hover on" any color in color swatches section
    Then I should see product displays "jumbo swatch" image
    And I "hover off" any color in color swatches section
    And I should see product displays "default" image
    And I "select" any color in color swatches section
    And I should see selected color is highlighted in the color swatch for that product

    #Notes: Color swatches should appear under the product thumbnail.
    # Upon hover over on a color swatch product thumbnail image should not change and color swatch should be highlighted
    # Upon hover off on a color swatch product thumbnail image should restore to the default image
    # Click on a color swatch product thumbnail image should not change, Jumbo swatch should display at the corner of product thumbnail image
    # and color swatch should get selected

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page @priority_high @snbc_comp @discovery_daily_run
  Scenario Outline: BrowsePage - Verify product thumbnails display in in All mode
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    When I select a product having color swatches
    Then I verify that the product title appears
    And I verify that the product image appears
    And I verify that the product price appears
    And I verify that the QuickView label appears on hovering the thumbnail
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 25122       |
      | Registry      | 31760       |
      | Iship         | 25122       |

    #Notes: Verify that product thumbnail images should be displayed for every product.
  #Verify that customer ratings, special savings, bonus offers should be displayed under the thumbnail (if applicable).
#  Verify that product name should be displayed for each thumbnail.
#  Verify that pricing should display under the product name.

    #TestLink - MCOM-96557 Vone - RT-07466
  @use_regression @priority_medium @domain_discovery @mode_domestic @snbc_comp
  Scenario: BrowsePage - Verify price display for standard product in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to category browse page
    And I verify that products are displayed with price


