############################################
# Author: Discovery Regression QE Team
# Date: 27th June 2017
# Date Modified: None
############################################

  Feature: Verify brand index page dsv features in DOMESTIC, ISHIP and REGISTRY mode

    #Test case ID: MCOM-83656, MCOM-83654, MCOM-83655
    @use_dsv  @dsv_desktop_sev2 @domain_discovery @use_regression @migrated_to_sdt @mode_domestic @priority_high @discovery_daily_run
    Scenario: Brand Index page - Verify the Shop all Brand Page in DOMESTIC mode
      Given I visit the web site as a guest user
      And I navigate to the "See All Brands" browse page under "WOMEN"
      Then I verify all basic attributes of the Designer page:
        | All Designers Banner                        |
        | A-Z Index                                   |
        | Alphabetical Order under each brand section |
        | Back to Top link under each brand section   |
        | All Brand links in left nav panel           |
      When I select random letter from brand index of the Designer page
      Then I should see designer page is anchor down to selected letter
      When I select 'Back to Top" link at selected letter of the Designer page
      Then I should see Designer page jump back to top anchor
      When I select any brand from Designer page
      Then I should navigate to Search Results page
      And I should see breadcrumb on Search Results page
    # Notes:
    # Navigate to Brand index page from any Left Nav from Cat Splash page
    #      MCOM -  see all brands link
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

    @domain_discovery @use_regression @migrated_to_sdt @mode_domestic  @dsv_desktop_sev2
    Scenario: Brand Index page - Verify Shop and Brand - Iship Mode
      Given I visit the web site as a guest user
      When I navigate to international context page
      And I change country to "Canada"
      And I close the welcome mat if it's visible
      And I navigate to the "See All Brands" browse page under "WOMEN"
      Then I verify left nav links on iship mode brand index page
    # Notes:
    # links should redirect to the correct page.
    # Description: In iShip mode: Verify Shop and Brand
    # Steps:
    # 1. Navigate to macys.com in international mode.
    # 2. Punch the url : http://www.macys.com/shop/all-brands?id=63538
    # 3. Verify the LNA links.
    #
    # Expected Results:
    # 1. International macys homepage should display.
    # 2. Url should navigate to the desired page.
    # 3. links should redirect to the correct page.