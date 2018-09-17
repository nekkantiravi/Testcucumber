#Author: Discovery QE
#Date Created: 06/12/2015


Feature: Manual Scenarios in DOMESTIC, ISHIP and REGISTRY mode


  #As per current functionality tag clouds are not visible in registry mode. So, below scenario cant be automated
  #Testlink-BLCOM-84275 Vone-RT-06516
  @use_manual @priority_high @artifact_navapp @domain_discovery @mode_registry @wip @deprecated
  Scenario Outline: CategorySubSplashPage - Verify tag cloud in REGISTRY mode
    Given I visit the web site as a guest user
    When I navigate to registry page
    When I navigate in "registry" to "Sub Splash" page
    Then I should be landed on "Sub Splash" page
    And I should see the Popular Searches at the bottom
    And I verify the page for keyword which has "<options>" from popular searches
  Examples:
    | options				|
    | /shop/featured/ URL |
    | /shop/ URL			|
    | /cms/slp/2/ URL		|
      # Notes
      # Verify Popular Searches in splash page:
      # Navigate to BRANDSEARCH PAGE by clicking on popular search link
      # Verify in BRANDSEARCH
      # appended as "/shop/{searchphrase}"
      # relevant product displayed in the "site" page
      # facets, Pagination, and sort by in the page
      # Verify all three different types of links - /buy, /shop, cms/slp/2/

  #As per current functionality we are not seeing these special shops and boutiques links in any of the splash pages.
  #So, moved this scenario to manual
  #Testlink-BLCOM-58621
#Vone-RT-06294
  @use_manual @artifact_navapp @domain_discovery @mode_domestic @wip @deprecated
  Scenario: Category page - Verify Special Shops and Boutiques links in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to category page with Special Shops and Boutiques
    And I navigate to Special Shops and Boutiques from left nav links
    Then I verify the Special Shops and Boutiques Page
    # Notes:
    # VERIFY:
    # BCOM: Click on one of the Special Shops or Boutiques on top of left navigation
    # Eg:- Fashion Index,The Wedding Shop
    # Verify appropriate content for the selected link is loaded for the special shop
    # MCOM: Eg: SPECIAL_SHOPS, Macy's Heritage Shops, Wedding Shop
    # MCOM Eg: For the Home  | Custom Windows & Carpet, Men | The Wedding Shop