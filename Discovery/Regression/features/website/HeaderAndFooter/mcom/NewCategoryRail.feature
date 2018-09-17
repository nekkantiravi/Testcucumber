#Author: Discovery QE
#Date Created: 14/04/2017

Feature: Verify Category Rail on header in DOMESTIC, ISHIP and REGISTRY modes

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign @discovery_daily_run
  Scenario Outline: Header - Verify New Category rail should be displayed in DOMESTIC, ISHP and Registry modes
    Given I visit the web site as a guest user in "<mode_name>" mode
    Then I verify new category rail is displayed
    Examples:
      | mode_name |
      | domestic  |
      | registry  |
      | iship     |

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: Home page - Verify TOPNAV on Home Page in DOMESTIC mode
    Given I visit the web site as a guest user
    Then I verify dynamic top navigation in "domestic" mode

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: Home page - Verify TOPNAV on Home Page in Registry mode
    Given I visit the web site as a guest user
    When I navigate to registry home page
    Then I verify dynamic top navigation in "registry" mode

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: Home page - Verify TOPNAV on Home Page in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "India"
    And I close the welcome mat if it's visible
    Then I verify dynamic top navigation in "iship" mode

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: Category Splash page- Verify TOPNAV on Category Splash Page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to random category splash page
    Then I verify dynamic top navigation in "domestic" mode

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: CategorySplashPage - Verify TOPNAV on Navapp Home Page and Category Splash Page in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    Then I change country to "India"
    And I close the welcome mat if it's visible
    Then I verify dynamic top navigation in "iship" mode

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: Shopping Bag Page - Verify TOPNAV on shopping bag Page in Registry mode
    Given I visit the web site as a guest user
#    When I navigate to registry home page
#    And I add a random product to my bag and checkout
    Then I verify dynamic top navigation in "domestic" mode

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: Find Registry Page - Verify TOPNAV on Wedding Registry Legacy Capture Email Page in REGISTRY mode
    Given I visit the web site as a guest user
    When I navigate to registry home page
    And I navigate to find registry on registry home page
    Then I verify dynamic top navigation in "registry" mode

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario Outline: Header - Verify TOPNAV - FLYOUTS should be displayed for all FOB in REGISTRY mode
    Given I visit the web site as a guest user in "registry" mode
    And Navigate to "HnF Clean" viewtype in new header footer experience
    When I hover over on the below "<fob>" fob's
    Then I verify that flyout menu is displayed
    Examples:
      | fob                    |
      | STARTER IDEAS          |
      | DINING                 |
      | KITCHEN                |
      | BED & BATH             |
      | HOME DECOR             |
      | LUGGAGE                |
      | BRANDS                 |
      | WEDDING SHOP           |

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario Outline: Header - Verify TOPNAV - FLYOUTS should be displayed in Category Browse Page in DOMESTIC, ISHIP and REGISTRY modes
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to random browse page
    And Navigate to "HnF Clean" viewtype in new header footer experience
    And I mouse over "BED & BATH" category from top navigation
    Then I verify that flyout menu is displayed
    Examples:
      | mode_name |
      | domestic  |
      | registry  |
      | iship     |

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario Outline: Header - Verify TOPNAV - FLYOUTS should be displayed in Shopap My Wallet Page in DOMESTIC mode
    Given I visit the web site as a registered user
    And Navigate to "HnF Clean" viewtype in new header footer experience
    When I hover on My Account element on link rail
    And I click on My Account "<pages>" in "Domestic" Mode
    And Navigate to "HnF Clean" viewtype in new header footer experience
    And I mouse over "BED & BATH" category from top navigation
    Then I verify that flyout menu is displayed
    Examples:
      | pages     |
      | MY WALLET |

  #As Clean will be scaled to 100% as of 08/23/2017, ignoring Control and Holdout scenarios

#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario: Header - Verify category rail is not displayed when user navigate to Hnf Holdout view type
#    Given I visit the web site as a guest user
#    And Navigate to "HnF Holdout" viewtype in new header footer experience
#    Then I verify category rail is not displayed
#
#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario: Header - Verify category rail is not displayed when user navigate to Hnf Control view type
#    Given I visit the web site as a guest user
#    And Navigate to "HnF Control" viewtype in new header footer experience
#    Then I verify category rail is not displayed