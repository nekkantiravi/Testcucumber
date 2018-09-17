Feature: Verify the Quick View zoomer functionality accross all Navapp pages


  @domain_Other @artifact_navapp @project_siteperformance @feature_Zoomer
  Scenario Outline: Verify the Zoomer functionality in QuickView panel in Browse page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to browse page in "<mode_name>" mode
    And I select a random quick view
    Then I should see the quickview overlay
    When I click on "zoom_in" Button in QuickView panel
    Then I should see the image is "zoomed_in"
    When I click on "zoom_out" Button in QuickView panel
    Then I should see the image is "zoomed_out"
    When I click on "zoom_in" Button in QuickView panel
    And I click on "zoom_in" Button in QuickView panel
    When I click on "zoom_reset" Button in QuickView panel
    Then I should see the image is "zoomed_out"
    Examples:
      |mode_name|
      |domestic|
      |registry|
      |iship   |

  @domain_Other @artifact_navapp @project_siteperformance @feature_Zoomer
  Scenario Outline: Verify the Zoomer functionality in Shopping bag page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I search for "nike women"
    Then I should be in Search Landing page
    And I select a random member product
    Then I should be redirected to PDP page
    When I add product to my bag from standard PDP Page
    Then I should be redirected to ATB page
    When I navigate to shopping bag page from add to bag page
    And I select a random quick view in shopping bag page
    Then I should see the quickview overlay
    When I click on "zoom_in" Button in QuickView panel
    Then I should see the image is "zoomed_in"
    When I click on "zoom_out" Button in QuickView panel
    Then I should see the image is "zoomed_out"
    When I click on "zoom_in" Button in QuickView panel
    And I click on "zoom_in" Button in QuickView panel
    When I click on "zoom_reset" Button in QuickView panel
    Then I should see the image is "zoomed_out"
    Examples:
      |mode_name|
      |domestic|
      |registry|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Zoomer @1
  Scenario Outline: Verify the Zoomer functionality in QuickView panel in SubSplash page
    Given I visit the web site as a guest user in "<mode_name>" mode
    And I navigate to "<mode_name>" subsplash pages
    And I select a random quick view
    Then I should see the quickview overlay
    When I click on "zoom_in" Button in QuickView panel
    Then I should see the image is "zoomed_in"
    When I click on "zoom_out" Button in QuickView panel
    Then I should see the image is "zoomed_out"
    When I click on "zoom_in" Button in QuickView panel
    And I click on "zoom_in" Button in QuickView panel
    When I click on "zoom_reset" Button in QuickView panel
    Then I should see the image is "zoomed_out"
    Examples:
      |mode_name|
      |domestic|
      |iship   |