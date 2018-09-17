#Author: Traci Morris
#Story: SDE-130 - MOBILE: Present View with Managers Names
#Date Created: 05/15/2017
#Date Signed Off:

Feature: As a Macy's Selling Manager in the MyShop configuration using a mobile device,
  I want to see my dashboard list of areas in the store, referenced by the name of the
  Selling Manager of that area, so that I can easily understand whose area of the store
  I need to click into in order to drill down into that area or seller.

  @domain_stores @omniclient @story_SDE-130 @mcom @MEW
  Scenario: All Selling Managers in store and unassigned area will be displayed on Selling Managers dashboard when accessing
  application on a mobile device.
    Given I launch the macy's omniclient page on mobile
    When I sign into OmniClient mobile application as My Shop Selling Manager
    Then I should see a list of Selling managers in my store
    And I should see my name at the top
    And I should see a list of staffing zones of the Selling Manager
    And the Unassigned Area is displayed at the bottom of SM list

  @domain_stores @omniclient @story_SDE-130 @mcom @MEW
  Scenario: Selling Managers will be able to click into the plus sign next to any name, and be
  presented with the staffing zones under the supervision of the manager whom they selected.
    Given I launch the macy's omniclient page on mobile
    When I sign into OmniClient mobile application as My Shop Selling Manager
    Then I should see Selling Manager HomePage
    When I expand random Selling Manager name
    Then I should see a list of staffing zones of the Selling Manager
    When I click on the staffing zone
    Then I should see a list of associates who are in that staffing zone
    And I should see List totals change for selected associate


  @domain_stores @omniclient @story_SDE-130 @mcom @MEW
  Scenario: Selling Managers will be able to click the Summary icon (top right) to see Yesterday's Summary
  of the staffing zone they clicked.
    Given I launch the macy's omniclient page on mobile
    When I sign into OmniClient mobile application as My Shop Selling Manager
    Then I should see Selling Manager HomePage
    When I expand random Selling Manager name
    Then I should see a list of staffing zones of the Selling Manager
    When I click the Summary icon on the mobile device
    Then I should see the No Selling Area selected error message
    When I click on the OK button on the No Selling Area selected error message
    Then I should see Selling Manager HomePage

 @manual @domain_stores @omniclient @story_SDE-130 @MEW
  Scenario: Selling Managers will be able to click the Summary icon (top right) to see Yesterday's Summary
  of the staffing zone they clicked.
    Given I launch the macy's omniclient page on mobile
    When I sign into OmniClient mobile application as My Shop Selling Manager
    Then I should see Selling Manager HomePage
    When I expand random Selling Manager name
    Then I should see a list of staffing zones of the Selling Manager
    When I click on the staffing zone
    Then I should see a list of associates who are in that staffing zone
    When I click the Summary icon on the mobile device
    Then I should see Yesterday's Summary page
    And I should see totals for the following under Yesterday's summary:
      | Client Sales                 |
      | Clients Shopped              |
      | Prospects to New Customers   |
      | New to Book                  |
    And I should see a list of clients on the Yesterday's summary page










