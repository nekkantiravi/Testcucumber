# Author: DISCOVERY QE
# Date Created: 10/5/2015

Feature: Verify CatSplash - Designer Page

    #Testlink-BLCOM-56483
    #Vone-RT-06295
    @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
    Scenario: CategorySplashPage - Verify the Designer page in DOMESITC mode
    Given I visit the web site as a guest user
    When I navigate to brand index page in "domestic" mode
    When I select any brand from Designer page
    And I should see breadcrumb on Search Results page
        # Notes:
        # Navigate to Brand index page from any Left Nav from Cat Splash page
        # BCOM -  All Designers link
        # attributes need to be verified:
        # All designer featured brand banner should display on top
        # A-Z brand Index should display below to the main banner
        # correct brand name should display according to alphabetical order under brands section
        # All the brands in the selected category should be displayed
        # back to top link should be displayed under each brand category
        # All brand links should display in left nav
        # clicking on any brand name should navigate to the serach result page for the specified brand
        # breadcrumb should display as: <brand name clicked> > <fob category name> <#> items found
        # clicking a letter that isn't greyed should anchor down to the brand listing that starts with the clicked letter
        # clicking "Back to top" link should jump back to top anchor

  #Testlink-BLCOM-58628
   #Vone-RT-06296
  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario Outline: CategorySplashPage - Verify Designer Shops in Left navigation in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "<SubCat>" browse page under "<FOB>"
    When I select the first brand in the Brand facet
    Then I verify that browse page contents are displayed
    Then I verify that the product thumbnails are displayed with the selected brand
    # Notes:
    # VERIFY:
    # Click on one of the Designer Shops link from left navigation
    # Verify appropriate content for the selected link is loaded for the Designer shop
  Examples:
    | FOB          |SubCat        |
    | HOME         |Ralph Lauren  |
    | WOMEN        |Burberry      |
    | SHOES        |Gucci         |
    | HANDBAGS     |Burberry      |