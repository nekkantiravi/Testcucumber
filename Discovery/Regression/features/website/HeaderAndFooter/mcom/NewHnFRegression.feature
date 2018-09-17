#Author: Discovery QE
#Date Created: 30/05/2017

#Treatement A

Feature: Verify new HnF Redesign functionality in DOMESTIC, ISHIP and REGISTRY modes

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: Verify new HnF Redesign functionality for Loyality page in DOMESTIC mode
    Given I visit the web site as a registered user
    When I navigate to loyality enroll page
    Then I verify New Header Redesign implementation for clean in "domestic" mode

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: Verify new HnF Redesign functionality for campaign page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to mobile app campaign page
    Then I verify New Header Redesign implementation for clean in "domestic" mode

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: Verify new HnF Redesign functionality in ATB page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I add a random product to bag
    Then I verify New Header Redesign implementation for clean in "domestic" mode

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: Verify new HnF Redesign functionality in Shopping bag page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I add a random product to bag
    And I navigate to shopping bag page from add to bag page
    Then I verify New Header Redesign implementation for clean in "domestic" mode

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario Outline: Verify new HnF Redesign functionality for My Account section in Domestic mode
    Given I visit the web site as a "registered" user in "site" mode
    When I hover on My Account element on link rail
    And I click on My Account "<pages>" in "Domestic" Mode
    Then I verify New Header Redesign implementation for clean in "domestic" mode
    Examples:
      | pages                 |
      | MY ORDER HISTORY      |
      | MY PROFILE            |
      | MY WALLET             |
      | MY PLENTI             |
      | MY LISTS              |

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario Outline: Verify new HnF Redesign functionality for My Account section in Registry mode
    Given I visit the web site as a "Registered" user in "registry" mode
    When I hover on My Account element on link rail
    And I click on My Account "<pages>" in "Registry" Mode
    Then I verify New Header Redesign implementation for clean in "registry" mode
    Examples:
      | pages                 |
      | MY ORDER HISTORY      |
      | MY PROFILE            |
      | MY WALLET             |
      | MY PLENTI             |
      | MY LISTS              |

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign @xbrowser_hfr
  Scenario: Verify new HnF Redesign functionality in international context page
    Given I visit the web site as a guest user in "iship" mode
    When I navigate to international context page
    Then I verify New Header Redesign implementation for clean in "iship" mode

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign @xbrowser_hfr
  Scenario: Verify new HnF Redesign functionality in Find registry page
    Given I visit the web site as a guest user
    When I navigate to registry home page
    And I navigate to find registry page from top nav
    Then I verify New Header Redesign implementation for clean in "registry" mode

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: Home page - Verify new HnF changes are seen in deals and promotions page
    Given I visit the web site as a guest user in "domestic" mode
    When I visit the deals and promotions page
    Then I verify New Header Redesign implementation for clean in "domestic" mode

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: Home page - Verify new HnF changes are seen in stores page
    Given I visit the web site as a guest user in "domestic" mode
    When I visit the stores page
    Then I verify New Header Redesign implementation for clean in "domestic" mode

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: Home page - Verify new HnF changes are seen in wish lists page
    Given I visit the web site as a guest user in "domestic" mode
    When I visit the wish list page
    Then I verify New Header Redesign implementation for clean in "domestic" mode

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: Home page - Verify new HnF changes are seen in Order history page
    Given I visit the web site as a registered user
    When I navigate to the order history page
    Then I verify New Header Redesign implementation for clean in "domestic" mode

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: Home page - Verify new HnF changes are seen in account page
    Given I visit the web site as a registered user
    When I navigate to my account page
    Then I verify New Header Redesign implementation for clean in "domestic" mode

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: Home page - Verify new HnF changes are seen in sign in page
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to signin page of "SITE" mode
    Then I verify New Header Redesign implementation for clean in "domestic" mode

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario Outline: Home page - Verify new HnF changes are seen in registry page
    Given I visit the web site as a registry user
    When I hover on manage link rail element
    And I click on "<registry_items>" link from subNav
    Then I verify New Header Redesign implementation for clean in "registry" mode
    Examples:
      | registry_items   |
      | REGISTRY MANAGER |
      | VIEW REGISTRY    |
      | BENEFITS         |

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: Home page - Verify new HnF changes are seen in wedding registry page
    Given I visit the web site as a guest user in "registry" mode
    Then I verify New Header Redesign implementation for clean in "registry" mode

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: Verify new HnF Redesign functionality for Beauty box subscriptions page in DOMESTIC mode
    Given I visit the web site as a registered user
    When I navigate to beauty box subscriptions page
    And Navigate to "HnF Clean" viewtype in new header footer experience
    Then I verify New Header Redesign implementation for clean in "domestic" mode

  #Treatment - B

  #As Clean will be scaled to 100% as of 08/23/2017, ignoring Radical scenarios

#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario: Verify new HnF Redesign functionality for Loyality page in DOMESTIC mode for treatment B
#    Given I visit the web site as a registered user
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    When I navigate to loyality enroll page
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    Then I verify New Header Redesign implementation for radical in current page

#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario: Verify new HnF Redesign functionality for campaign page in DOMESTIC mode for treatment B
#    Given I visit the web site as a registered user
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    When I navigate to mobile app campaign page
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    Then I verify New Header Redesign implementation for radical in current page

#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario: Verify new HnF Redesign functionality in ATB page in DOMESTIC mode for treatment B
#    Given I visit the web site as a guest user
#    When I add a random product to bag
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    Then I verify New Header Redesign implementation for radical in current page

#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario: Verify new HnF Redesign functionality in Shopping bag page in DOMESTIC mode for treatment B
#    Given I visit the web site as a guest user
#    When I add a random product to bag
#    And I navigate to shopping bag page from add to bag page
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    Then I verify New Header Redesign implementation for radical in current page

#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario Outline: Verify new HnF Redesign functionality for My Account section in Domestic mode for treatment B
#    Given I visit the web site as a registered user
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    When I hover on My Account element on link rail
#    And I click on My Account "<pages>" in "Domestic" Mode
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    Then I verify New Header Redesign implementation for radical in current page
#    Examples:
#      | pages                 |
#      | MY ORDER HISTORY      |
#      | MY PROFILE            |
#      | MY WALLET             |
#      | MY PLENTI             |
#      | MY LISTS              |

#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario Outline: Verify new HnF Redesign functionality for My Account section in Registry mode for treatment B
#    Given I visit the web site as a "registered" user in "registry" mode
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    When I hover on My Account element on link rail
#    And I click on My Account "<pages>" in "Registry" Mode
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    Then I verify New Header Redesign implementation for radical in current page
#    Examples:
#      | pages                 |
#      | MY ORDER HISTORY      |
#      | MY PROFILE            |
#      | MY WALLET             |
#      | MY PLENTI             |
#      | MY LISTS              |

#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario: Verify new HnF Redesign functionality in international context page for treatment B
#    Given I visit the web site as a guest user
#    When I navigate to international context page
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    Then I verify New Header Redesign implementation for radical in current page

#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario: Verify new HnF Redesign functionality in Find registry page for treatment B
#    Given I visit the web site as a guest user
#    When I navigate to registry home page
#    When I navigate to find registry page from top nav
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    Then I verify New Header Redesign implementation for radical in current page

#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario: Home page - Verify new HnF changes are seen in deals and promotions page for treatment B
#    Given I visit the web site as a guest user in "domestic" mode
#    When I visit the deals and promotions page
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    Then I verify New Header Redesign implementation for radical in current page

#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario: Home page - Verify new HnF changes are seen in stores page for treatment B
#    Given I visit the web site as a guest user in "domestic" mode
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    When I visit the stores page
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    Then I verify New Header Redesign implementation for radical in current page

#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario: Home page - Verify new HnF changes are seen in wish lists page for treatment B
#    Given I visit the web site as a guest user in "domestic" mode
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    When I visit the wish list page
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    Then I verify New Header Redesign implementation for radical in current page

#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario: Home page - Verify new HnF changes are seen in Order history page for treatment B
#    Given I visit the web site as a registered user
#    When I navigate to the order history page
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    Then I verify New Header Redesign implementation for radical in current page
#
#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario: Home page - Verify new HnF changes are seen in account page for treatment B
#    Given I visit the web site as a registered user
#    When I navigate to my account page
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    Then I verify New Header Redesign implementation for radical in current page

#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario: Home page - Verify new HnF changes are seen in sign in page for treatment B
#    Given I visit the web site as a guest user in "domestic" mode
#    When I navigate to signin page of "SITE" mode
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    Then I verify New Header Redesign implementation for radical in current page

#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario Outline: Home page - Verify new HnF changes are seen in registry page for treatment B
#    Given I visit the web site as a registry user
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    And I hover on manage link rail element
#    When I click on registry pages "<registry_items>"
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    Then I verify New Header Redesign implementation for radical in current page
#    Examples:
#      | registry_items   |
#      | REGISTRY MANAGER |
#      | VIEW REGISTRY    |
#      | REGISTRY GUIDE   |
#      | BENEFITS         |

#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario: Home page - Verify new HnF changes are seen in wedding registry page for treatment B
#    Given I visit the web site as a guest user in "registry" mode
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    Then I verify New Header Redesign implementation for radical in current page

#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario: Verify new HnF Redesign functionality for Beauty box subscriptions page in DOMESTIC mode for treatment B
#    Given I visit the web site as a registered user
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    When I navigate to beauty box subscriptions page
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    Then I verify New Header Redesign implementation for radical in current page