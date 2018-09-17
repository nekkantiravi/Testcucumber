# Author: DISCOVERY QE
# Date Created: 04/14/2017

Feature: Verification of search rail (macys logo , Search box and bag) in Domestic , Registry and Iship modes

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign @discovery_daily_run
  Scenario Outline: Header - Verify New Search rail should be displayed in DOMESTIC, ISHP and Registry modes
    Given I visit the web site as a guest user in "<mode_name>" mode
    Then I verify new search rail is displayed
    Examples:
      | mode_name |
      | domestic  |
      | registry  |
      | iship     |

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario Outline: Navapp - Verify Macys Logo ,search box and bag on search rail in Domestic mode , IShip and Registry
    Given I visit the web site as a guest user in "<mode_name>" mode
    Then I Should see Macys Logo on top left corner of the search rail on header
    And I Should see bag icon on top right corner of the search rail on header
    And I Should see Search box in between the macys logo and bag
    Examples:
      | mode_name |
      | domestic  |
      | registry  |
      | iship     |

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario Outline: Shopapp - Verify Macys Logo and bag on search rail in Domestic mode and Registry modes
    Given I visit the web site as a registered user in "<mode_name>" mode
    When I navigate to my account page
    Then I Should see Macys Logo on top left corner of the search rail on header
    And I Should see bag icon on top right corner of the search rail on header
    And I Should see Search box in between the macys logo and bag
    Examples:
      | mode_name |
      | domestic  |
      | registry  |

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario Outline: Bagapp - Verify Macys Logo and bag on search rail in Domestic mode , IShip and Registry
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to shopping bag page
    Then I Should see Macys Logo on top left corner of the search rail on header
    And I Should see bag icon on top right corner of the search rail on header
    And I Should see Search box in between the macys logo and bag
    Examples:
      | mode_name |
      | domestic  |
      | registry  |
      | iship     |

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario Outline: Navapp - Verify Macys Logo naviagating to home page in Domestic mode , IShip and Registry
    Given I visit the web site as a guest user in "<mode_name>" mode
    Then I Should see Macys Logo on top left corner of the search rail on header
    When I verify that Macys logo takes to Home Page
    Then I should be landed on Home page
    Examples:
      | mode_name |
      | domestic  |
      | registry  |
      | iship     |

  # Above Scenario should be covered in all the nav app pages (catsplash page , browse page, subsplash page, search results page,PDP page,potentially CE,
  # MP, Customer Service, NarVar, sign-up for emails)

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario Outline: Shopapp - Verify Macys Logo naviagating to home page in Domestic mode and Registry mode
    Given I visit the web site as a registered user in "<mode_name>" mode
    When I navigate to my account page
    Then I Should see Macys Logo on top left corner of the search rail on header
    When I verify that Macys logo takes to Home Page
    Then I should be landed on Home page
    Examples:
      | mode_name |
      | domestic  |
      | registry  |
   #Above Scenario should be covered in all the shop app pages ( My account page , order history page ...)

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario Outline: Bagapp- Verify Macys Logo naviagating to home page in Domestic mode and Registry mode
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to shopping bag page
    Then I Should see Macys Logo on top left corner of the search rail on header
    When I verify that Macys logo takes to Home Page
    Then I should be landed on Home page
    Examples:
      | mode_name |
      | domestic  |
      | registry  |

  #Above scenario should be covered in add to bag page,Checkout, as well

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario Outline: Navapp - Verify Search box has default text on Domestic mode , IShip and Registry
    Given I visit the web site as a guest user in "<mode_name>" mode
    Then I Should see Macys Logo on top left corner of the search rail on header
    And I verify that "Search or enter web ID" default message is displayed in new search box
    Examples:
      | mode_name |
      | domestic  |
      | registry  |
      | iship     |

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: Minimal Header - Verify Macys logo and Minimal bag is displayed in Minimal header in wgl registry signin page
    Given I visit the web site as a guest user
    When I navigate to registry home page
    And I select "Create Your Registry"
    Then I verify macys logo and minimal bag in minimal header

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: Bag icon - Verify Bag icon changes after adding items to bag
    Given I visit the web site as a guest user
    Then I verify red star appears in a white bag
    When I add a random product to bag
    Then I verify red star disappears and red bag appears

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: Header - Verify when clicking on bag taking me to shopping bag page with new Header and Footer redesign
    Given I visit the web site as a guest user
    When I click on the new bag icon
    Then I verify new search rail is displayed

  #As Clean will be scaled to 100% as of 08/23/2017, ignoring Control and Holdout scenarios

#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario: Header - Verify search rail is not displayed when user navigate to Hnf Holdout view type
#    Given I visit the web site as a guest user
#    And Navigate to "HnF Holdout" viewtype in new header footer experience
#    Then I verify search rail is not displayed

#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario: Header - Verify search rail is not displayed when user navigate to Hnf Control view type
#    Given I visit the web site as a guest user
#    And Navigate to "HnF Control" viewtype in new header footer experience
#    Then I verify search rail is not displayed
