#Author: Discovery QE
#Date Created: 06/12/2015


Feature: Verify CHANEL Pages in DOMESTIC, ISHIP and REGISTRY mode
  #Testlink-BLCOM-77222
  #Vone-RT-06302
  @use_regression @priority_high @artifact_navapp @domain_discovery @mode_domestic @use_domain_qual @bcom @chanel_regression
  Scenario: CategorySubSplashPage - Verify Chanel contents in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "CHANEL" sub splash page under "BEAUTY"
    Then I verify the basic attributes of Chanel Brand Shop Page
    # Notes:
    # Do full validation - should not display any broken image or link

  #Testlink-BLCOM-77222
  #Vone-RT-06302
  @use_regression @priority_high @artifact_navapp @domain_discovery @mode_domestic @chanel_regression
  Scenario: CategorySubSplashPage - Verify Chanel Left Nav functionality in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "CHANEL" sub splash page under "BEAUTY"
    Then I verify the Left Nav of the Chanel Brand Shop Page
    # Notes:
    # Do full validation - should not display any broken image or link
    # Should display the word CHANEL
    # Should VIEW ALL BEAUTY with go back icon

  @use_regression @use_regression_1 @artifact_navapp @domain_discovery @priority_high @mode_domestic @wip @deprecated @chanel_regression
  Scenario: CategorySubSplashPage - Verify Chanel Quick Peek functionality in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "CHANEL" sub splash page under "BEAUTY"
    And I select the quick peek of random product
    Then I verify that quick peek is displayed
    # Notes:
    # Just verify that a popup is displayed with non empty contents

  @use_regression @use_regression_1 @artifact_navapp @domain_discovery @high @disable_env @chanel_regression
  Scenario Outline: CategorySubSplashPage - Verify Chanel Contents functionality in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "CHANEL" sub splash page under "BEAUTY"
    And I navigate to "<leftnav>" from Left Nav links
    Then I verify the basic attributes of chanel browse page
  Examples:
    | leftnav           |
    | WHAT'S NEW         |
    | WOMEN'S FRAGRANCE |
    | MEN'S FRAGRANCE   |
    | MAKEUP            |
    | SKIN CARE         |
    # Notes:
    # Do full validation - should not display any broken image or link
    #  Brand Logo Banner is displayed
    #  SUBCATEGORY text is displayed
    #  Verify slide show animation.
    #  Verify widget animation start and end on first slide.
    #  Verify  Syndicated term should not in the url.
    #  Verify no borders or any grids for product thumbnails.
    #  Verify when mouse hover on more button under product thumbnail -
    #  more color swatches are been displayed on the product description.


  @use_regression @use_regression_1 @artifact_navapp @domain_discovery @priority_high @mode_domestic @chanel_regression
  Scenario Outline: CategorySubSplashPage - Verify Left Nav functionality in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "CHANEL" sub splash page under "BEAUTY"
    And I navigate to "<leftnav>" from Left Nav links
    Then I verify the Left Nav of the Chanel Browse Page
  Examples:
    | leftnav           |
    | WOMEN'S FRAGRANCE |
    | MEN'S FRAGRANCE   |
    | MAKEUP            |
    | SKIN CARE         |
    # Notes:
    # Do full validation - should not display any broken image or link
    # CHANEL left nav is different than regular leftnav
    # Verify NARROW BY, VIEW ALL CHANEL appears

  @use_regression @use_regression_1 @artifact_navapp @domain_discovery @priority_high @mode_domestic @chanel_regression
  Scenario Outline: CategorySubSplashPage - Verify Quick Peek functionality in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "CHANEL" sub splash page under "BEAUTY"
    And I navigate to "<leftnav>" from Left Nav links
    And I select the quick peek of random product
    Then I verify that quick peek is displayed
  Examples:
    | leftnav           |
    | WHAT'S NEW        |
    | WOMEN'S FRAGRANCE |
    | MEN'S FRAGRANCE   |
    | MAKEUP            |
    | SKIN CARE         |
    # Notes:
    # Just verify that a popup is displayed with non empty contents

  @use_regression @use_regression_1 @artifact_navapp @domain_discovery @priority_high @mode_domestic @chanel_regression
  Scenario Outline: CategorySubSplashPage - Verify Color Swatches functionality in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "CHANEL" sub splash page under "BEAUTY"
    And I navigate to "<leftnav>" from Left Nav links
    Then I verify the that color swatches are displayed on chanel browse
  Examples:
    | leftnav           |
    | MAKEUP            |
    # Notes:
    # Just verify that at-least more than 5 products have color swatches

  @use_regression @use_regression_1 @artifact_navapp @domain_discovery @high @mode_domestic @chanel_regression
  Scenario Outline: CategorySubSplashPage - Verify Sort By Price (high-low) functionality in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "CHANEL" sub splash page under "BEAUTY"
    And I navigate to "<leftnav>" from Left Nav links
    And I select "Price (high-low)" in sort by drop down
    Then I verify that the Sort By "Price (high-low)" functionality
  Examples:
    | leftnav           |
    | WHAT'S NEW        |
    | WOMEN'S FRAGRANCE |
    | MEN'S FRAGRANCE   |
    | MAKEUP            |
    | SKIN CARE         |
    # Notes:
    # Verify that products are displayed with non broken images and links, price and quick peek links and that
    # first product price is more than second product and last product

  @use_regression @use_regression_1 @artifact_navapp @domain_discovery @high @mode_domestic @chanel_regression
  Scenario Outline: CategorySubSplashPage - Verify Sort By Price (low-high) functionality in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "CHANEL" sub splash page under "BEAUTY"
    And I navigate to "<leftnav>" from Left Nav links
    And I select "Price (low-high)" in sort by drop down
    Then I verify that the Sort By "Price (low-high)" functionality
  Examples:
    | leftnav           |
    | WHAT'S NEW         |
    | WOMEN'S FRAGRANCE |
    | MEN'S FRAGRANCE   |
    | MAKEUP            |
    | SKIN CARE         |
    # Notes:
    # Verify that products are displayed with non broken images and links, price and quick peek links and that
    # first product price is less than second product and last product

  @use_regression @use_regression_1 @artifact_navapp @domain_discovery @high @mode_domestic @chanel_regression
  Scenario Outline: CategorySubSplashPage - Verify Sort By Customer Top Rated functionality in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "CHANEL" sub splash page under "BEAUTY"
    And I navigate to "<leftnav>" from Left Nav links
    And I select "Customer Top Rated" in sort by drop down
    Then I verify that the Sort By "Customer Top Rated" functionality
  Examples:
    | leftnav           |
    | WHAT'S NEW         |
    | WOMEN'S FRAGRANCE |
    | MEN'S FRAGRANCE   |
    | MAKEUP            |
    | SKIN CARE         |
    # Notes:
    # Verify that products are displayed with non broken images and links, price and quick peek links

  @use_regression @use_regression_1 @artifact_navapp @domain_discovery @high @mode_domestic @chanel_regression
  Scenario Outline: CategorySubSplashPage - Verify Sort By Best Sellers functionality in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "CHANEL" sub splash page under "BEAUTY"
    And I navigate to "<leftnav>" from Left Nav links
    And I select "Best Sellers" in sort by drop down
    Then I verify that the Sort By "Best Sellers" functionality
  Examples:
    | leftnav           |
    | WHAT'S NEW         |
    | WOMEN'S FRAGRANCE |
    | MEN'S FRAGRANCE   |
    | MAKEUP            |
    | SKIN CARE         |
    # Notes:
    # Verify that products are displayed with non broken images and links, price and quick peek links

  @use_regression @use_regression_1 @artifact_navapp @domain_discovery @high @mode_domestic @chanel_regression
  Scenario Outline: CategorySubSplashPage - Verify Sort By New Arrivals functionality in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "CHANEL" sub splash page under "BEAUTY"
    And I navigate to "<leftnav>" from Left Nav links
    And I select "New Arrivals" in sort by drop down
    Then I verify that the Sort By "New Arrivals" functionality
  Examples:
    | leftnav           |
    | WHAT'S NEW         |
    | WOMEN'S FRAGRANCE |
    | MEN'S FRAGRANCE   |
    | MAKEUP            |
    | SKIN CARE         |
    # Notes:
    # Verify that products are displayed with non broken images and links, price and quick peek links

  @use_regression @use_regression_1 @artifact_navapp @domain_discovery @high @mode_domestic @chanel_regression @wip
  Scenario: CategorySubSplashPage - Verify Chanel RVI & Social Icons & Tag Cloud functionality is NOT displayed functionality in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "CHANEL" sub splash page under "BEAUTY"
    Then I should not see recently viewed items section
    And I should not see the Popular Searches section above footer
    # Notes:
    # Recently Viewed is not displayed
    # Social Shopping/Polling Widget is not displayed
    # Tag Cloud is not displayed

  @domain_discovery @mode_domestic @artifact_navapp @priority_high @chanel_regression @use_regression
  Scenario Outline: SubSplashPage -  Verify that Category refinement in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "CHANEL" sub splash page under "BEAUTY"
    And I navigate to "<leftnav>" from Left Nav links
    When I select a random facet item from each of the facets
    Then I should see a filtered list of displayed products
  Examples:
  | leftnav           |
  | WOMEN'S FRAGRANCE |
  | MEN'S FRAGRANCE   |
  | MAKEUP            |
  | SKIN CARE         |

  @domain_discovery @mode_domestic @artifact_navapp @priority_high @chanel_regression @use_regression
  Scenario Outline: SubSplashPage -  Verify pagination on CHANEL page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "CHANEL" sub splash page under "BEAUTY"
    And I navigate to "<leftnav>" from Left Nav links
    And I click on next pagination button
    Then I verify that navigated to page 2 on browse page
    Examples:
      | leftnav           |
      | MAKEUP            |