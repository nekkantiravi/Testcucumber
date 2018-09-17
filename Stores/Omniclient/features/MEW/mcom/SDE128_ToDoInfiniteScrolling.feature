# Author: Traci Morris
# Story: SDE-128 - OmniClient :: To Do Infinite Scrolling
# Date Created: 06/12/2017
# Date Signed Off:

Feature: As an associate using the mobile clientele application, I want List/Event detail information
  to be displayed in segments so that I don't have to wait for all information to display before taking action.

  @manual @domain_stores @omniclient @story_SDE-128 @mcom @MEW
  Scenario: SAs should see their list of To Dos load in segments (infinite scrolling)
    Given I launch the macy's omniclient page on mobile
    When I sign into OmniClient mobile application as Associate
    And I select the hamburger icon
    Then I should see the list of options on the menu
    When I click on My Macys To Dos tab
    Then I should see the list of TO DOS on the mobile page
    When I select a List Title
    Then I should see the list details
    And I should be able to scroll through the list details

  @manual @domain_stores @omniclient @story_SDE-128 @mcom @MEW
  Scenario: SMs should see their list of To Dos load in segments (infinite scrolling)
    Given I launch the macy's omniclient page on mobile
    When I sign into OmniClient mobile application as My Shop Selling Manager
    And I select the hamburger icon
    Then I should see the list of options on the menu
    When I click on My Macys To Dos tab
    Then I should see the list of TO DOS on the mobile page
    When I select a List Title
    Then I should see the list details
    And I should be able to scroll through the list details