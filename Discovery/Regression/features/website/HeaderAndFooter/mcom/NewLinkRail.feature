#Author: Discovery QE
#Date Created: 20/04/2017

#Notes:
# Below scenarios should be verified in all Home,Browse,Splash,Sub-Splash,Catsplash,PDP,Bag,DLP,Search Results,SLP,Brand pages
# All Shopapp pages Examples:My Preferences,My Address Book,Furniture & Mattress Status,My Macy's Credit Card,Cardholder
# Benifits,Apply & Learn,More,Gift Card Balance,Signout

# All Navapp pages

Feature: Treatment A - Verify Link Rail functionality on the header in all modes
#Treatment - A

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign @discovery_daily_run
  Scenario Outline: Header - Verify New Link rail should be displayed in DOMESTIC, ISHP and Registry modes
    Given I visit the web site as a guest user in "<mode_name>" mode
    Then I verify new link rail is displayed
    Examples:
      | mode_name |
      | domestic  |
      | registry  |
      | iship     |

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario Outline: Verify link rail has 5 clickable elements on left in Domestic
    Given I visit the web site as a guest user
    Then I should see "<elements>" to the left of the link rail
    And I verify the "<elements>" links pages are rendered properly in "Domestic" mode
    Examples:
      | elements         |
      | STORES           |
      | DEALS            |
      | LISTS            |
      | WEDDING REGISTRY |

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario Outline: Verify link rail has 5 clickable elements on left in Registry modes
    Given I visit the web site as a registry user
    Then I should see "<link rail elements>" to the left of the link rail for registry
    And I verify the "<link rail elements>" links pages are rendered properly in "Registry" mode
    Examples:
      | link rail elements |
      | FIND REGISTRY      |
      | CREATE REGISTRY    |
      | HELP               |
      | STORES             |

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: Verify Manage link rail element has 4 clickable elements when hovered on it in registry mode for guest user
    Given I visit the web site as a guest user
    When I navigate to registry home page
    And I hover on manage link rail element
    Then I should see 4 clickable elements from the dropdown
    And I should see all 4 clickable links are navigating to respective page for guest user

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: Verify Manage link rail element has 4 clickable elements when hovered on it in registry mode for registered user
    Given I visit the web site as a "registered" user in "registry" mode
    And I hover on manage link rail element
    Then I should see 4 clickable elements from the dropdown
    And I should see all 4 clickable links are navigating to respective page for registered user

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign @wip
  Scenario: Verify gifts dropdown with two clickable links that will appear when hovered on gifts link in Domestic mode
    Given I visit the web site as a guest user
    When I hover on Gifts link
    Then I Should see dropdown with two clickable elements for Gifts

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign @wip
  Scenario: Verify gifts dropdown with two ASTRA-based clickable links that will appear when hovered on it in Registry modes
    Given I visit the web site as a guest user
    When I navigate to registry home page
    Then I Should see dropdown with two clickable elements for Gifts

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario Outline: Verify 3 clickable elements to the left of the Link Rail in IShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Australia"
    Then I should see "<link rail elements>" to the left of link rail
    And I verify the "<link rail elements>" links pages are rendered properly in "IShip" mode
    Examples:
      | link rail elements |
      | STORES             |
      | WEDDING REGISTRY   |
      | SHIPPING TO        |

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: Verify 'need help' pop out is displayed when user clicks on Help link in Registry Home page from Link rail
    Given I visit the web site as a guest user
    When I navigate to registry home page
    And I Should see Help link on the link rail and click on it
    Then I Should see need help popout window
    And I close the help overlay

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: Verify link rail has 2 clickable elements (Sign in ,My Account) on right in Domestic mode
    Given I visit the web site as a guest user
    Then I should see 2 clickable to the right of the link rail

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: Verify link rail has 2 clickable elements on right in Registry modes
    Given I visit the web site as a guest user
    When I navigate to registry home page
    Then I should see 2 clickable to the right of the link rail

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario Outline: Verify My Account element on the home page in registry mode
    Given I visit the web site as a registry user
    When I hover on My Account element on link rail
    Then I verify the My Account Pages "<items>" are rendered properly in "Registry" Mode
    Examples:
      | items                 |
      | MY MACY'S CREDIT CARD |
      | MY ORDER HISTORY      |
      | MY PROFILE            |
      | MY WALLET             |
      | MY PLENTI             |
      | MY LISTS              |

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario Outline: Verify My Account element on the home page in domestic mode
    Given I visit the web site as a registered user
    When I hover on My Account element on link rail
    Then I verify the My Account Pages "<items>" are rendered properly in "Domestic" Mode
    Examples:
      | items                 |
      | MY MACY'S CREDIT CARD |
      | MY ORDER HISTORY      |
      | MY PROFILE            |
      | MY WALLET             |
      | MY PLENTI             |
      | MY LISTS              |

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: Verify there are no links on right of the link rail in IShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Australia"
    And I navigate to "shipping to" footer links
    Then I should not see any clickable elements on the right of the link rail

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: Verify carat symbols for dropdowns (Manage, My Account) in CE page (Manage - Benefits)
    Given I visit the web site as a guest user
    And I navigate to registry home page
    When I hover on manage link rail element
    And I click on registry pages "BENEFITS"
    Then I should see carat symbols beside MANAGE and MY ACCOUNT Dropdowns

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: Verify carat symbols for dropdowns in CE page (Learn More and Apply)
    Given I visit the web site as a guest user
    When I navigate to the "Apply & Learn More" page from footer
    And I should be redirected to "credit_service_marketing" page
    Then I should see carat symbols beside MANAGE and MY ACCOUNT Dropdowns

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: Verify a user successfully sign in
    Given I visit the web site as a registered user
    And I click on "profile" link from subNav
    Then I Should see welcome message on the right corner of the page on link rail
    And I should see sign out link on the new header and footer
    And I verify link rail appears below the media rail

  #Treatment -B

#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario:Verify link rail appears below the media rail in domestic mode for treatment B
#    Given I visit the web site as a guest user
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    Then I verify link rail appears below the media rail
#
#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario:Verify link rail appears below the media rail in Iship mode for treatment B
#    Given I visit the web site as a guest user
#    When I navigate to international context page
#    And I change country to "Australia"
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    Then I verify link rail appears below the media rail
#
#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario:Verify link rail appears below the media rail in Registry mode for treatment B
#    Given I visit the web site as a guest user
#    When I navigate to registry home page
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    Then I verify link rail appears below the media rail
#
#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario Outline: Verify link rail has 5 clickable elements in Domestic mode for treatment B
#    Given I visit the web site as a guest user
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    Then I should see "<elements>" to the left of the link rail
#    And I verify the "<elements>" links pages are rendered properly in "Domestic" mode
#    Examples:
#      | elements         |
#      | STORES           |
#      | DEALS            |
#      | LISTS            |
#      | WEDDING REGISTRY |
#
#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario Outline: Verify link rail has 5 clickable elements to the left of the Links Rail in Registry modes for treatment B
#    Given I visit the web site as a registry user
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    Then I should see "<link rail elements>" to the left of the link rail for registry
#    And I verify the "<link rail elements>" links pages are rendered properly in "Registry" mode
#    Examples:
#      | link rail elements |
#      | FIND REGISTRY      |
#      | CREATE REGISTRY    |
#      | HELP               |
#      | STORES             |
#
#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario: Verify Manage link rail element has 4 clickable elements when hovered on it in registry mode for treatment B
#    Given I visit the web site as a guest user
#    When I navigate to registry home page
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    And I hover on manage link rail element
#    And I should see 4 clickable elements from the dropdown
#
#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign @wip
#  Scenario: Verify gifts dropdown with two clickable links that will appear when hovered on gifts link in Domestic mode for treatment B
#    Given I visit the web site as a guest user
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    When I hover on Gifts link
#    Then I Should see dropdown with two clickable elements for Gifts
#
#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign @wip
#  Scenario: Verify gifts dropdown with two ASTRA-based clickable links that will appear when hovered on it in Registry modes for treatment B
#    Given I visit the web site as a guest user
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    When I navigate to registry home page
#    Then I Should see dropdown with two clickable elements for Gifts
#
#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario Outline: Verify 3 clickable elements to the left of the Link Rail in IShip mode for treatment B
#    Given I visit the web site as a guest user
#    When I navigate to international context page
#    When I change country to "Australia"
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    Then I should see "<link rail elements>" to the left of link rail
#    And I verify the "<link rail elements>" links pages are rendered properly in "IShip" mode
#    Examples:
#      | link rail elements |
#      | STORES             |
#      | WEDDING REGISTRY   |
#      | SHIPPING TO        |
#
#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario: Verify "need help?" pop out is displayed when user clicks on Help link in Registry Home page from Link rail for treatment B
#    Given I visit the web site as a guest user
#    When I navigate to registry home page
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    And I Should see Help link on the link rail and click on it
#    Then I Should see need help popout window
#    And I close the help overlay
#
#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario: Verify link rail has 2 clickable elements on right in Domestic mode for treatment B
#    Given I visit the web site as a guest user
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    Then I should see 2 clickable to the right of the link rail
#
#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario: Verify link rail has 2 clickable elements on right in Registry modes for treatment B
#    Given I visit the web site as a guest user
#    When I navigate to registry home page
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    Then I should see 2 clickable to the right of the link rail
#
#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario Outline: Verify My Account element on the home page in registry mode for treatment B
#    Given I visit the web site as a registry user
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    When I hover on My Account element on link rail
#    Then I verify the My Account Pages "<items>" are rendered properly in "Registry" Mode
#    Examples:
#      | items                 |
#      | MY MACY'S CREDIT CARD |
#      | MY ORDER HISTORY      |
#      | MY PROFILE            |
#      | MY WALLET             |
#      | MY PLENTI             |
#      | MY LISTS              |
#
#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario Outline: Verify My Account element on the home page in domestic mode for treatment B
#    Given I visit the web site as a registered user
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    When I hover on My Account element on link rail
#    Then I verify the My Account Pages "<items>" are rendered properly in "Domestic" Mode
#    Examples:
#      | items                 |
#      | MY MACY'S CREDIT CARD |
#      | MY ORDER HISTORY      |
#      | MY PROFILE            |
#      | MY WALLET             |
#      | MY PLENTI             |
#      | MY LISTS              |
#
#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario: Verify there are no links on right of the link rail in IShip mode for treatment B
#    Given I visit the web site as a guest user
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    When I navigate to international context page
#    Then I should not see any clickable elements on the right of the link rail
#
#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario:  Verify carat symbols for dropdowns in CE page (Manage - Benefits) for treatment B
#    Given I visit the web site as a guest user
#    And I navigate to registry home page
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    And I hover on manage link rail element
#    When I click on registry pages "BENEFITS"
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    Then I should see carat symbols beside MANAGE and MY ACCOUNT Dropdowns
#
#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario: Verify carat symbols for dropdowns in CE page (Learn More and Apply) for treatment B
#    Given I visit the web site as a guest user
#    When I navigate to the "Apply & Learn More" page from footer
#    And I should be redirected to "credit_service_marketing" page
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    Then I should see carat symbols beside MANAGE and MY ACCOUNT Dropdowns
#
#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario: Verify a user successfully signin for treatment B
#    Given I visit the web site as a registered user
#    And I navigate to my profile page
#    When Navigate to "HnF Radical" viewtype in new header footer experience
#    Then I Should see welcome message on the right corner of the page on link rail
#    And I should see sign out link on the new header and footer
#    Then I verify link rail appears below the media rail

  #As Clean will be scaled to 100% as of 08/23/2017, ignoring Control and Holdout scenarios

#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario: Header - Verify link rail is not displayed when user navigate to Hnf Holdout view type for treatment B
#    Given I visit the web site as a guest user
#    And Navigate to "HnF Holdout" viewtype in new header footer experience
#    Then I verify link rail is not displayed

#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario: Header - Verify link rail is not displayed when user navigate to Hnf Control view type for treatment B
#    Given I visit the web site as a guest user
#    And Navigate to "HnF Control" viewtype in new header footer experience
#    Then I verify link rail is not displayed

