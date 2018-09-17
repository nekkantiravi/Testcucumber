#=========================
# Author: Discovery SNBC QE
#=========================
Feature: Verify Quick View Overlay functionality in Category Browse Page contents in DOMESTIC, ISHIP and REGISTRY mode

  # *********************** ADD TO BAG  AS Guest User for Domestic|Iship|Registry Mode *******************************************
  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression 
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify ADD TO BAG button on quick view overlay as guest user in All mode
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variables to avoid data issues
    When I select "quick view" button for "member" product on page
    Then I verify that quick peek is displayed
    Then I click add to bag button on QuickView page
    Then I click checkout button on QuickView page
    Then I verify that respective product is in 'Shopping Bag' page
   Examples:
      | shopping_mode | Category_id |
      | Domestic      | 1000238     |
      | Registry      | 8149        |
      | Iship         | 1000238     |

  #***********************ADD TO BAG  AS Registered User for Domestic|Iship|Registry Mode *******************************************
  @domain_discovery @priority_medium @mode_domestic @snbc_comp @use_regression
  Scenario: CategoryBrowsePage - Domestic - Verify ADD TO BAG button on quick view overlay as singed-in user in Domestic mode
    Given I visit the web site as a registered user in "domestic" mode
    And I clear existing class variables to avoid data issues
    When I am on CategoryBrowsePage for "1000238" category id in Domestic mode
    Then  I select "quick view" button for "member" product on page
    Then I verify that quick peek is displayed
    Then I click add to bag button on QuickView page
    Then I click checkout button on QuickView page
    Then I verify that respective product is in 'Shopping Bag' page

  @domain_discovery @priority_medium @mode_registry @snbc_comp @use_regression
  Scenario: CategoryBrowsePage -  Registry  - Verify ADD TO BAG button on quick view overlay as singed-in user in  Registry  mode
    Given I visit the web site as a registered user in "registry" mode
    And I clear existing class variables to avoid data issues
    When I am on CategoryBrowsePage for "8149" category id in Registry mode
    Then I select "quick view" button for "member" product on page
    Then I verify that quick peek is displayed
    Then I click add to bag button on QuickView page
    Then I click checkout button on QuickView page
    Then I verify that respective product is in 'Shopping Bag' page

 # ***********************ADD TO REGISTRY  As Guest User For Domestic Mode *******************************************
  @domain_discovery @priority_medium @mode_domestic @snbc_comp @use_regression
  Scenario: CategoryBrowsePage - Domestic - Verify ADD TO REGISTRY button on quick view overlay as guest user in Domestic mode
    Given I visit the web site as a guest user in "domestic" mode
    And I clear existing class variables to avoid data issues
    When I am on CategoryBrowsePage for "1000238" category id in Domestic mode
    Then I select "quick view" button for "member" product on page
    Then I verify that quick peek is displayed
    Then I click "ADD TO REGISTRY" button on QuickView page
    Then I should be navigated to the registry sign in page

#*********************** ADD TO REGISTRY  As Register User for Domestic Mode *******************************************
  @domain_discovery @priority_medium @mode_domestic @snbc_comp @use_regression
  Scenario: CategoryBrowsePage - Domestic - Verify ADD TO REGISTRY button on quick view overlay as singed-in user in Domestic mode
    Given I visit the web site as a registered user in "domestic" mode
    And I clear existing class variables to avoid data issues
    When I am on CategoryBrowsePage for "1000238" category id in Domestic mode
    Then I select "quick view" button for "member" product on page
    Then I verify that quick peek is displayed
    Then I click "ADD TO REGISTRY" button on QuickView page
    Then I verified error message displayed as "We're unable to find your registry in your online profile."

   # ***********************ADD TO REGISTRY  As Registry  User *******************************************
  @domain_discovery @priority_medium @mode_registry @snbc_comp @use_regression
  Scenario: CategoryBrowsePage - Registry - Verify ADD TO REGISTRY button on quick view overlay as registry user in Registry mode
    Given I visit the web site as a registry user
    And I clear existing class variables to avoid data issues
    When I am on CategoryBrowsePage for "8149" category id in Registry mode
    Then I select "quick view" button for "member" product on page
    Then I verify that quick peek is displayed
    Then I click "ADD TO REGISTRY" button on QuickView page
    Then I should see success message in quick view overlay

  #*********************** ADD TO LIST as Guest User for Domestic and Registry Mode *******************************************
  @domain_discovery @priority_medium @mode_domestic @mode_registry @snbc_comp @use_regression
  Scenario Outline: CategoryBrowsePage - Domestic|Registry - Verify ADD TO LIST button on quick view overlay as guest user in Domestic|Registry mode
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variables to avoid data issues
    Then I select "quick view" button for "member" product on page
    Then I verify that quick peek is displayed
    Then I click "ADD TO LIST" button on QuickView page
    Then I should see success message as "Added to your temporary Wishlist.Sign In to save this list permanently."
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 1000238     |
      | Registry      | 8149        |

  #*********************** ADD TO LIST as Registered User for Domestic and Registry Mode*******************************************
  @domain_discovery @priority_medium @mode_domestic @snbc_comp @use_regression 
  Scenario: CategoryBrowsePage - Domestic - Verify ADD TO LIST button on quick view overlay as registered user in Domestic mode
    Given I visit the web site as a registered user in "domestic" mode
    And I clear existing class variables to avoid data issues
    And I am on CategoryBrowsePage for "1000238" category id in Domestic mode
    Then I select "quick view" button for "member" product on page
    Then I verify that quick peek is displayed
    Then I click "ADD TO LIST" button on QuickView page
    Then I should see success message as "This item has been added to your Wishlist."

  @domain_discovery @priority_medium @mode_registry @snbc_comp @use_regression
  Scenario: CategoryBrowsePage - Registry - Verify ADD TO LIST button on quick view overlay as registered user in Registry mode
    Given I visit the web site as a registered user in "registry" mode
    And I clear existing class variables to avoid data issues
    And I am on CategoryBrowsePage for "8149" category id in Registry mode
    Then I select "quick view" button for "member" product on page
    Then I verify that quick peek is displayed
    Then I click "ADD TO LIST" button on QuickView page
    Then I should see success message as "This item has been added to your Wishlist."
