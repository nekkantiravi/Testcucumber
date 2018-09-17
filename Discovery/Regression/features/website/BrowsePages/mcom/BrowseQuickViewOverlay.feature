#=========================
# Author: Discovery SNBC QE
# Date Created: 08/03/2017
#=========================
Feature: Verify Quick View Overlay functionality in Category Browse Page contents in DOMESTIC, ISHIP and REGISTRY mode

  # *********************** ADD TO BAG  AS Guest User for Domestic|Iship|Registry Mode *******************************************
  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page @discovery_daily_run @xbrowser_browse*
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify ADD TO BAG button on quick view overlay as guest user
    Given I am on CategoryBrowsePage for "<Category_Name>" under "<FOB>" in <shopping_mode> mode
    When I select "quick view" button for "member" product on page
    Then I verify that landed on "quick_view" Page
    When I click add to bag button on QuickView page
    And I click checkout button on QuickView page
    Then I verify that respective product is in 'Shopping Bag' page
   Examples:
     | shopping_mode |Category_Name |FOB     |
     | Domestic      |Shorts         |Men     |
     | Registry      |Luggage Sets   |Luggage |
     | Iship         |Boots          |Shoes   |

  #***********************ADD TO BAG  AS Registered User for Domestic|Iship|Registry Mode *******************************************
  @domain_discovery @mode_domestic @snbc_comp @use_regression @feature_browse_page
  Scenario: CategoryBrowsePage - Domestic - Verify ADD TO BAG button on quick view overlay as singed-in user
    Given I visit the web site as a registered user in "domestic" mode
    When I navigate to the "jeans" browse page under "Men"
    And I select "quick view" button for "member" product on page
    Then I verify that landed on "quick_view" Page
    When I click add to bag button on QuickView page
    And I click checkout button on QuickView page
    Then I verify that respective product is in 'Shopping Bag' page

  @domain_discovery @mode_registry @snbc_comp @use_regression @feature_browse_page
  Scenario: CategoryBrowsePage - Registry  - Verify ADD TO BAG button on quick view overlay as singed-in user
    Given I visit the web site as a registered user in "registry" mode
    When I navigate to the "Cookware & Cookware Sets" browse page under "Kitchen"
    And I select "quick view" button for "member" product on page
    Then I verify that landed on "quick_view" Page
    When I click add to bag button on QuickView page
    And I click checkout button on QuickView page
    Then I verify that respective product is in 'Shopping Bag' page

  @domain_discovery @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario: CategoryBrowsePage -  Iship  - Verify ADD TO BAG button on quick view overlay as singed-in user
    Given I visit the web site as a registered user in "iship" mode
    When I navigate to the "jeans" browse page under "Men"
    And I select "quick view" button for "member" product on page
    Then I verify that landed on "quick_view" Page
    When I click add to bag button on QuickView page
    And I click checkout button on QuickView page
    Then I verify that respective product is in 'Shopping Bag' page

 # ***********************ADD TO REGISTRY  As Guest User For Domestic Mode *******************************************
  @domain_discovery @mode_domestic @snbc_comp @use_regression @feature_browse_page
  Scenario: CategoryBrowsePage - Domestic - Verify ADD TO REGISTRY button on quick view overlay as guest user
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to the "Kitchen" catsplash page under "HOME"
    And I navigate to "Cookware & Cookware Sets" browse page from cat splash page
    And I select "quick view" button for "member" product on page
    Then I verify that landed on "quick_view" Page
    When I click "ADD TO REGISTRY" button on QuickView page
    Then I should be navigated to the registry sign in page

#*********************** ADD TO REGISTRY  As Register User for Domestic Mode *******************************************
  @domain_discovery @mode_registry @snbc_comp @use_regression @feature_browse_page
  Scenario: CategoryBrowsePage - Domestic - Verify ADD TO REGISTRY button on quick view overlay as singed-in user
    Given I visit the web site as a registered user in "domestic" mode
    When I navigate to the "Kitchen" catsplash page under "HOME"
    And I navigate to "Cookware & Cookware Sets" browse page from cat splash page
    And I select "quick view" button for "member" product on page
    Then I verify that landed on "quick_view" Page
    When I click "ADD TO REGISTRY" button on QuickView page
    Then I verified error message displayed as "There is no registry associated to this profile. Please create a registry or claim your in-store created registry from the Registry Homepage."

   # ***********************ADD TO REGISTRY  As Registry  User *******************************************
  @domain_discovery @mode_registry @snbc_comp @use_regression @feature_browse_page
  Scenario: CategoryBrowsePage - Registry - Verify ADD TO REGISTRY button on quick view overlay as registry user
    Given I visit the web site as a registry user
    When I navigate to the "Cookware & Cookware Sets" browse page under "Kitchen"
    And I select "quick view" button for "member" product on page
    Then I verify that landed on "quick_view" Page
    When I click "ADD TO REGISTRY" button on QuickView page
    Then I should see success message in quick view overlay

  #*********************** ADD TO LIST as Guest User for Domestic and Registry Mode *******************************************
  @domain_discovery @mode_domestic @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic - Verify ADD TO LIST button on quick view overlay as guest user
    Given I am on CategoryBrowsePage for "<Category_Name>" under "<FOB>" in <shopping_mode> mode
    When I select "quick view" button for "member" product on page
    And I click "ADD TO LIST" button on QuickView page
    Then I should see success message as "Added to your guest List.Sign In to save it to your account."
    Examples:
     | shopping_mode |Category_Name |FOB     |
     | Domestic      |Shorts        |Men     |

  #*********************** ADD TO LIST as Registered User for Domestic and Registry Mode*******************************************
  @domain_discovery @mode_domestic @snbc_comp @use_regression @feature_browse_page
  Scenario: CategoryBrowsePage - Domestic - Verify ADD TO LIST button on quick view overlay as registered user
    Given I visit the web site as a registered user in "domestic" mode
    When I navigate to the "jeans" browse page under "Men"
    And I select "quick view" button for "member" product on page
    Then I verify that landed on "quick_view" Page
    When I click "ADD TO LIST" button on QuickView page
    Then I should see success message as "Added to your List."