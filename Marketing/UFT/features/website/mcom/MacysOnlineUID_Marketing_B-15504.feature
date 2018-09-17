#Author: UFT team
#Date Created: 03/29/2016
#Date Signed Off:
#Version One: B-15504

Feature:  As a producer I want to ensure that Coremetrics attribute macys_online_uid is collected in pageview Attribute 31.

  #Coremetrics Scenario
  @artifact_navapp @mode_domestic @release_17J @priority_medium @domain_marketing @project_UFT_COREMETRICS
  Scenario: Verify explore attribute 31 fired when user signed from browse pages
    Given I visit the web site as a registered user
    When I sign out from my current profile
    And I navigate to browse page in "domestic" mode
    And I signin with existing profile from "browse" page

  #Coremetrics Scenario
  @artifact_navapp @mode_domestic @release_17J @priority_medium @domain_marketing @project_UFT_COREMETRICS
  Scenario: Verify explore attribute 31 fired when user signed from splash pages
    Given I visit the web site as a registered user
    When I sign out from my current profile
    And I navigate to random category splash page
    And I signin with existing profile from "splash" page

  #Coremetrics Scenario
  @artifact_navapp @mode_domestic @release_17J @priority_medium @domain_marketing @project_UFT_COREMETRICS
  Scenario: Verify explore attribute 31 fired when user signed from search results page
    Given I visit the web site as a registered user
    When I sign out from my current profile
    And I search for "jeans"
    And I signin with existing profile from "search results" page

  #Coremetrics Scenario
  @artifact_navapp @mode_domestic @release_17J @priority_medium @domain_marketing @project_UFT_COREMETRICS
  Scenario: Verify explore attribute 31 fired when user signed in from Stores Page
    Given I visit the web site as a registered user
    When I sign out from my current profile
    And I navigate to the "stores" page from footer
    And I signin with existing profile from "stores" page