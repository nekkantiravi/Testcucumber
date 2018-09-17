#Author: Discovery QE
#Date Created: 09/24/2015

Feature: Verify each FOB SubSplash Popular Related Searches section

#TestLink-BLCOM-80149 Vone - RT-06558
  @use_regression @artifact_navapp @priority_high @mode_domestic @wip @deprecated
  Scenario: CategorySubSplashPage - Verify Popular Searches - HOME
    Given I visit the web site as a guest user
    When I navigate to "HOME" category page
    Then I should see the Popular Searches at the bottom
    And I click on any keyword in the Popular Searches
    Then I should see the relevant page displayed
    # Notes:
    # Do full validation - Popular searches links should be present and clickable
    # Verify the landing page URL is in following format http://<environment>/buy/<keyword>
    # Links of products or categories should be displayed properly