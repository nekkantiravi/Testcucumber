# Author: Ovidiu Rucoi
# Story: SDE-150 - OmniClient :: MyShop Selling Manager Dashboard
# Date Created: 05/09/2017
# Date Signed Off:

Feature: As a MyShop Selling Manager, I want my dashboard to display information as it becomes available so that I don't have to wait for all information to load and display before taking action

  @manual @domain_stores @omniclient @story_SDE-150 @mcom @website
  Scenario: Verifying individual loading indicators on Dashboard
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as Selling Manager
    Then I should see Selling Manager HomePage
    And I should NOT see main loading icon
    And I should see individual loading indicators for the following:
      | Selected Selling Manager's Selling Areas List |
      | Macys To Dos                                  |

  @manual @domain_stores @omniclient @story_SDE-150 @mcom @website
  Scenario: Verifying individual loading indicators on expanding a Selling Manager
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as Selling Manager
    Then I should see Selling Manager HomePage
    When I expand a Selling Manager from "My Store" section
    Then I should see individual loading indicators for the following under the Selling Manager:
      | List of Selling Areas                |
    When I expand a Selling Area under a Selling Manager
    Then I should see individual loading indicators for the following under the Selling Area:
      | List of Selling Associates                |
    And I should see individual loading indicators for the following under Yesterday's summary:
      | Client sales                              |
      | Client shopped                            |
      | Potentials to Clients                     |
      | New to Book                               |
      | List of Previous Shopper/Transaction Info |

  @manual @domain_stores @omniclient @story_SDE-150 @mcom @website
  Scenario: Verifying clicking any feature regardless of loading indicators
    When I sign into Omniclient application as Selling Manager
    Then I should see Selling Manager HomePage
    And I should see individual loading indicators for the following:
      | Selected Selling Manager's Selling Areas List |
      | Macys To Dos                                  |
    When I click on "My Clients" Tab
    Then I should be able to navigate to My clients page regardless of loading indicators in Dashboard page

  @manual @domain_stores @omniclient @story_SDE-150 @mcom @website
  Scenario: Verifying clicking any feature regardless of individual loading indicators on expanding a Selling Area
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as Selling Manager
    Then I should see Selling Manager HomePage
    When I expand a Selling Manager from "My Store" section
    Then I should see individual loading indicators for the following under the Selling Manager:
      | List of Selling Areas                |
    When I expand a Selling Area under a Selling Manager
    Then I should see individual loading indicators for the following under the Selling Area:
      | List of Selling Associates                |
    And I should see individual loading indicators for the following under Yesterday's summary:
      | Client sales                              |
      | Client shopped                            |
      | Potentials to Clients                     |
      | New to Book                               |
      | List of Previous Shopper/Transaction Info |
    When I click on "My Clients" Tab
    Then I should be able to navigate to My clients page regardless of loading indicators in My Store section