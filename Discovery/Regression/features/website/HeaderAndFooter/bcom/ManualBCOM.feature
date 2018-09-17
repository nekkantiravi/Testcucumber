#Author: Discovery QE
#Date Created: 25/11/2016


Feature: Manual Scenarios in DOMESTIC, ISHIP and REGISTRY mode

  # Test Case ID: RT-06260
  @artifact_navapp @domain_discovery @mode_iship @use_manual @wip
  Scenario: HomePage - Iship - Verify the display welcome message in English on welcome mat overlay
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "a random country"
    And I close the welcome mat if it's visible
    Then I verify the language in welcome mat
    When I select English link in welcome mat
    Then I verify display welcome message in English

  # Test Case ID: RT-06265
  # Pre-requisite: Modify header add-on should be installed in FF browser
  @artifact_navapp @domain_discovery @mode_iship @use_manual @wip
  Scenario: HomePage - Verify modify header for given country ISHIP home page
    Given I visit the web site as a guest user
    And I remove browser cache
    When I open modify header add-on in FF browser
    And I enable the option for LONDON or CANADA
    And I navigate to Macys home page
    Then I should be navigated to respective country ISHIP home page

  # Test Case ID: RT-06272
  @domain_discovery @mode_iship @use_regression
  Scenario: Header - Verify registry navigation in Iship mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "a random country"
    And I close the welcome mat if it's visible
    When I navigate registry home page in iship mode
    Then I should be navigated to customer service page


  # Test Case ID: RT-06260
  # Pre-requisite: Remove browser cache
  @artifact_navapp @domain_discovery @mode_domestic @use_manual @wip
  Scenario: Online Cookie - Verify online cookie value and expiration date
    Given I visit the web site as a guest user
    When I select any https link
    #Ex: sign in link
    Then I verify bloomingdales_online cookie value and expiration date
    When I select any http link
    #Ex: Men category
    Then I verify bloomingdales_online cookie value and expiration date
    And I should see same cookie value for bloomingdales_online and expiration date