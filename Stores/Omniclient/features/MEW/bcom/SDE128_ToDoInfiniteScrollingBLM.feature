# Author: Traci Morris
# Story: SDE-128 - OmniClient :: To Do Infinite Scrolling
# Date Created: 06/12/2017
# Date Signed Off:

Feature: As an associate using the mobile clientele application, I want List/Event detail information
  to be displayed in segments so that I don't have to wait for all information to display before taking action.

  @manual @domain_stores @omniclient @story_SDE-128 @bcom @MEW
  Scenario: SAs should see their list of To Dos load in segments (infinite scrolling)
    Given I launch the bloomingdales's omniclient page on mobile
    When I sign into OmniClient BLM mobile application as Associate
    And I select the hamburger icon
    And I should see the list of options on the menu
    Then I click on My Lists tab
    And I should see the list of TO DOS on the mobile page
    Then I select a List Title
    And I should see the list details
    And I should be able to scroll through the list details

  @manual @domain_stores @omniclient @story_SDE-128 @bcom @MEW
  Scenario: SMs should see their list of To Dos load in segments (infinite scrolling)
    Given I launch the bloomingdales's omniclient page on mobile
    When I sign into OmniClient BLM mobile application as Selling Manager
    Then I select the hamburger icon
    And I should see the list of options on the menu
    Then I click on My Lists tab
    And I should see the list of TO DOS on the mobile page
    Then I select a List Title
    And I should see the list details
    And I should be able to scroll through the list details


