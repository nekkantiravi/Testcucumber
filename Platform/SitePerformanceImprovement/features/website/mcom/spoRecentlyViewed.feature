Feature: Verify the Recently Viewed Items Panel accross Navapp pages

  @domain_Other @artifact_navapp @project_siteperformance @feature_rvi
  Scenario Outline: Verify the Recently viewed items in search pages
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I search for "<Search_cat>"
    And I visit "8" random items
    Then I verify 6 Recent products are displayed at one time in RVI panel
    And I should validate the navigation arrow buttons
    When I click on "right" arrow key inside RVI
    Then I validate that next set of products are displayed
    When I click on "left" arrow key inside RVI
    Then I validate that previous set of products are displayed
    When I click "edit" button in rvi panel
    And I remove an item and verify the item is removed from RVI panel
    Examples:
      |mode_name|Search_cat|
      |domestic |Dresses|
      |iship    |Hobos     |

  @domain_Other @artifact_navapp @project_siteperformance @feature_rvi
  Scenario Outline: Verify the Recently viewed items in Subsplash pages
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I search for "<Search_cat>"
    And I visit "8" random items
    And I navigate to "<mode_name>" subsplash pages
    Then I verify 6 Recent products are displayed at one time in RVI panel
    And I should validate the navigation arrow buttons
    When I click on "right" arrow key inside RVI
    Then I validate that next set of products are displayed
    When I click on "left" arrow key inside RVI
    Then I validate that previous set of products are displayed
    When I click "edit" button in rvi panel
    And I remove and item and verify the item is removed from RVI panel
    Examples:
      |mode_name|Search_cat|
      |domestic |Dresses|
      |iship    |Hobos  |

  @domain_Other @artifact_navapp @project_siteperformance @feature_rvi
  Scenario Outline: Verify the Recently viewed items in browse pages
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to browse page in "<mode_name>" mode
    And I visit "8" random items
    And I verify 6 Recent products are displayed at one time in RVI panel
    And I should validate the navigation arrow buttons
    When I click on "right" arrow key inside RVI
    Then I validate that next set of products are displayed
    When I click on "left" arrow key inside RVI
    Then I validate that previous set of products are displayed
    When I click "edit" button in rvi panel
    And I remove an item and verify the item is removed from RVI panel
    Examples:
      |mode_name|
      |domestic|
      |iship|


  @domain_Other @artifact_navapp @project_siteperformance @feature_rvi
  Scenario Outline: Verify the Recently viewed items in Category splash pages
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I search for "<Search_cat>"
    And I visit "8" random items
    And I navigate to random category splash page
    Then I verify 6 Recent products are displayed at one time in RVI panel
    And I should validate the navigation arrow buttons
    When I click on "right" arrow key inside RVI
    Then I validate that next set of products are displayed
    When I click on "left" arrow key inside RVI
    Then I validate that previous set of products are displayed
    When I click "edit" button in rvi panel
    And I remove an item and verify the item is removed from RVI panel
    Examples:
      |mode_name|Search_cat|
      |domestic |Dresses|
      |iship    |Hobos  |


  @domain_Other @artifact_navapp @project_siteperformance @feature_rvi
  Scenario Outline: Verify the Recently viewed items in Brand index page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I search for "<Search_cat>"
    And I visit "8" random items
    And I navigate to brand index page in "<mode_name>" mode
    Then I verify 6 Recent products are displayed at one time in RVI panel
    And I should validate the navigation arrow buttons
    When I click on "right" arrow key inside RVI
    Then I validate that next set of products are displayed
    When I click on "left" arrow key inside RVI
    Then I validate that previous set of products are displayed
    When I click "edit" button in rvi panel
    And I remove an item and verify the item is removed from RVI panel
    Examples:
      |mode_name|Search_cat|
      |domestic |Dresses|
      |iship    |Hobos  |

  @domain_Other @artifact_navapp @project_siteperformance @feature_rvi
  Scenario: Verify the Recently viewed items in Deals and promotion page
    Given I visit the web site as a guest user in "domestic" mode
    When I search for "Dresses"
    And I visit "8" random items
    And I visit the deals and promotions page
    Then I verify 6 Recent products are displayed at one time in RVI panel
    And I should validate the navigation arrow buttons
    When I click on "right" arrow key inside RVI
    Then I validate that next set of products are displayed
    When I click on "left" arrow key inside RVI
    Then I validate that previous set of products are displayed
    When I click "edit" button in rvi panel
    And I remove an item and verify the item is removed from RVI panel


  @domain_Other @artifact_navapp @project_siteperformance @feature_rvi
  Scenario Outline: Verify the Recently viewed items in Shopping bag page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I search for "<Search_cat>"
    When I visit "8" random items
    When I navigate to shopping bag page
    Then I verify 6 Recent products are displayed at one time in RVI panel
    And I should validate the navigation arrow buttons
    When I click on "right" arrow key inside RVI
    Then I validate that next set of products are displayed
    When I click on "left" arrow key inside RVI
    Then I validate that previous set of products are displayed
    When I click "edit" button in rvi panel
    And I remove an item and verify the item is removed from RVI panel
    Examples:
      |mode_name|Search_cat|
      |domestic |Jeans     |
      |iship    |Hobos     |

  @domain_Other @artifact_navapp @project_siteperformance @feature_rvi
  Scenario Outline: Verify the Recently viewed items in zero results search landing pages
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I search for "<Search_cat>"
    And I visit "2" random items
    And I search for "xyza"
    Then I should be in zero result page
    And I should see rvi panel
    Examples:
      |mode_name|Search_cat|
      |domestic |Jeans     |
      |iship    |Hobos     |


  @domain_Other @artifact_navapp @project_siteperformance @feature_rvi
  Scenario: Verify the Recently viewed items in Add to bag page for domestic mode
    Given I visit the web site as a guest user in "domestic" mode
    When I search for "sandals"
    And I visit "2" random items
    And I add a random product to bag
    Then I should be redirected to ATB page
    And I should see rvi panel

  @domain_Other @artifact_navapp @project_siteperformance @feature_rvi
  Scenario: Verify Review for product thumbnails in RRVI panel
    Given I visit the web site as a guest user in "domestic" mode
    When I search for "jeans"
    And I select a random member product with customer ratings
    And I search for "jeans"
    And I should see rvi panel
    And I should view the review link in rvi panel


  @domain_Other @artifact_navapp @project_siteperformance @feature_rvi
  Scenario Outline: Verify there is no Recently viewed items panel when there is no items visited for Browse pages
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to browse page in "<mode_name>" mode
    Then I should not see Rvi panel
    Examples:
      |mode_name|
      |domestic|
      |iship|

  @domain_Other @artifact_navapp @project_siteperformance @feature_rvi
  Scenario Outline: Verify there is no Recently viewed items panel when there is no items visited for Search pages
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to search results page in "<mode_name>" mode
    Then I should not see Rvi panel
    Examples:
      |mode_name|
      |domestic|
      |iship|

  @domain_Other @artifact_navapp @project_siteperformance @feature_rvi
  Scenario Outline: Verify there is no Recently viewed items panel when there is no items visited for Brand index page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to brand index page in "<mode_name>" mode
    Then I should not see Rvi panel
    Examples:
      |mode_name|
      |domestic|
      |iship|

  @domain_Other @artifact_navapp @project_siteperformance @feature_rvi
  Scenario Outline: Verify there is no Recently viewed items panel when there is no items visited for Shopping bag page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to shopping bag page
    Then I should not see Rvi panel
    Examples:
      |mode_name|
      |domestic|
      |iship|


  @domain_Other @artifact_navapp @project_siteperformance @feature_rvi
  Scenario Outline: Verify there is no Recently viewed items panel when there is no items visited for Cat Splash page
    Given I visit the web site as a guest user in "<mode_name>" mode
    And I navigate to random category splash page
    Then I should not see Rvi panel
    Examples:
      |mode_name|
      |domestic|
      |iship|

  @domain_Other @artifact_navapp @project_siteperformance @feature_rvi
  Scenario Outline: Verify there is no Recently viewed items panel when there is no items visited for sub Splash page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to "<mode_name>" subsplash pages
    Then I should not see Rvi panel
    Examples:
      |mode_name|
      |domestic|
      |iship|


  @domain_Other @artifact_navapp @project_siteperformance @feature_rvi
  Scenario Outline: Verify there is no navigation arrows in Recently viewed items panel when the items visted is less than seven in Search Landing Page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I search for "<Search_cat>"
    And I visit "6" random items
    When I navigate to search results page in "<mode_name>" mode
    Then I should not see the navigation arrow buttons
    Examples:
      |mode_name|Search_cat|
      |domestic |Dresses|
      |iship    |Hobos     |

  @domain_Other @artifact_navapp @project_siteperformance @feature_rvi
  Scenario Outline: Verify there is no navigation arrows in Recently viewed items panel when the items visted is less than seven in browse page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I search for "<Search_cat>"
    And I visit "6" random items
    When I navigate to browse page in "<mode_name>" mode
    Then I should not see the navigation arrow buttons
    Examples:
      |mode_name|Search_cat|
      |domestic |Dresses|
      |iship    |Hobos     |

  @domain_Other @artifact_navapp @project_siteperformance @feature_rvi
  Scenario Outline: Verify there is no navigation arrows in Recently viewed items panel when the items visted is less than seven in Brand index page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I search for "<Search_cat>"
    And I visit "6" random items
    When I navigate to brand index page in "<mode_name>" mode
    Then I should not see the navigation arrow buttons
    Examples:
      |mode_name|Search_cat|
      |domestic |Dresses|
      |iship    |Hobos     |

  @domain_Other @artifact_navapp @project_siteperformance @feature_rvi
  Scenario Outline: Verify there is no navigation arrows in Recently viewed items panel when the items visted is less than seven in Shopping Bag page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I search for "<Search_cat>"
    And I visit "6" random items
    When I navigate to shopping bag page
    Then I should not see the navigation arrow buttons
    Examples:
      |mode_name|Search_cat|
      |domestic |Dresses|
      |iship    |Hobos     |


  @domain_Other @artifact_navapp @project_siteperformance @feature_rvi
  Scenario Outline: Verify there is no navigation arrows in Recently viewed items panel when the items visted is less than seven for Cat Splash page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I search for "<Search_cat>"
    And I visit "6" random items
    And I navigate to random category splash page
    Then I should not see the navigation arrow buttons
    Examples:
      |mode_name|Search_cat|
      |domestic |Dresses|
      |iship    |Hobos  |



  @domain_Other @artifact_navapp @project_siteperformance @feature_rvi
  Scenario Outline: Verify there is no Recently viewed items panel when there is no items visited for Sub Splash page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I search for "<Search_cat>"
    And I visit "6" random items
    When I navigate to "<mode_name>" subsplash pages
    Then I should not see the navigation arrow buttons
    Examples:
      |mode_name|Search_cat|
      |domestic |Dresses|
      |iship    |Hobos  |

  @domain_Other @artifact_navapp @project_siteperformance @feature_rvi
  Scenario: Verify the Recently viewed items in Catspash pages for Registry mode
    Given I visit the web site as a guest user in "registry" mode
    When I search for "Electrics"
    And I visit "10" random items
    And I navigate to registry catsplash page
    And I verify 6 Recent products displayed at one time in RVI panel for registry mode
    And I should validate the navigation arrow buttons
    When I click on "right" arrow key inside RVI
    Then I validate that next set of products are displayed
    When I click on "left" arrow key inside RVI
    Then I validate that previous set of products are displayed
    When I click "edit" button in rvi panel
    And I remove and verify the item is removed from old RVI panel

  @domain_Other @artifact_navapp @project_siteperformance @feature_rvi
  Scenario: Verify the Recently viewed items in search pages for Registry mode
    Given I visit the web site as a guest user in "registry" mode
    When I search for "Electrics"
    And I visit "10" random items
    Then I verify 6 Recent products displayed at one time in RVI panel for registry mode
    And I should validate the navigation arrow buttons
    When I click on "right" arrow key inside RVI
    Then I validate that next set of products are displayed
    When I click on "left" arrow key inside RVI
    Then I validate that previous set of products are displayed
    When I click "edit" button in rvi panel
    And I remove and verify the item is removed from old RVI panel


  @domain_Other @artifact_navapp @project_siteperformance @feature_rvi
  Scenario: Verify the Recently viewed items in Subsplash pages for Registry mode
    Given I visit the web site as a guest user in "registry" mode
    When I search for "Electrics"
    And I visit "10" random items
    And I navigate to "registry" subsplash pages
    Then I verify 6 Recent products displayed at one time in RVI panel for registry mode
    And I should validate the navigation arrow buttons
    When I click on "right" arrow key inside RVI
    Then I validate that next set of products are displayed
    When I click on "left" arrow key inside RVI
    Then I validate that previous set of products are displayed
    When I click "edit" button in rvi panel
    And I remove and verify the item is removed from old RVI panel


  @domain_Other @artifact_navapp @project_siteperformance @feature_rvi
  Scenario: Verify the Recently viewed items in Brand index page registry mode
    Given I visit the web site as a guest user in "registry" mode
    When I search for "Electrics"
    And I visit "10" random items
    And I navigate to registry Brand index page
    Then I verify 6 Recent products displayed at one time in RVI panel for registry mode
    And I should validate the navigation arrow buttons
    When I click on "right" arrow key inside RVI
    Then I validate that next set of products are displayed
    When I click on "left" arrow key inside RVI
    Then I validate that previous set of products are displayed
    When I click "edit" button in rvi panel
    And I remove and verify the item is removed from old RVI panel

  @domain_Other @artifact_navapp @project_siteperformance @feature_rvi
  Scenario: Verify the Recently viewed items in zero results search landing pages for Registry mode
    Given I visit the web site as a guest user in "registry" mode
    When I search for "Electrics"
    And I visit "10" random items
    And I search for "xyza"
    Then I should be in the Registry zero result page
    Then I verify 6 Recent products displayed at one time in RVI panel for registry mode
    And I should validate the navigation arrow buttons
    When I click on "right" arrow key inside RVI
    Then I validate that next set of products are displayed
    When I click on "left" arrow key inside RVI
    Then I validate that previous set of products are displayed
    When I click "edit" button in rvi panel
    And I remove and verify the item is removed from old RVI panel

  @test @domain_Other @artifact_navapp @project_siteperformance @feature_rvi
  Scenario: Verify the alternate images fade out on hover in RVI panel for Master PDP in Registry mode
    Given I visit the web site as a guest user in "registry" mode
    When I search for "dinning"
    And I visit "5" random items having alternate image thumbnail
    And I select a random master product
    Then I should see the alternate images in master pdp rvi panel

  @test @domain_Other @artifact_navapp @project_siteperformance @feature_rvi
  Scenario: Verify the alternate images fade out on hover in RVI panel for ZSLP in Registry mode
    Given I visit the web site as a guest user in "registry" mode
    When I search for "dinning"
    And I visit "5" random items having alternate image thumbnail
    And I search for "xyza"
    Then I should be in the Registry zero result page
    And I should see the alternate images in master pdp rvi panel

