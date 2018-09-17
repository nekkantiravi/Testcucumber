# Author: Traci Morris
# Story: SDE-32 - OmniClient :: Selling Manager Dashboard
# Date Created: 05/02/2017
# Date Signed Off:

  Feature: As a Selling Manager, I want my dashboard to display information as it becomes available so that I don't have to wait for all information to load and display before taking action

    @manual @domain_stores @omniclient @story_SDE-32 @mcom @website
    Scenario: Verifying individual loading indicators on Dashboard
      Given I launch the macy's omniclient page
      When I sign into Omniclient application as Selling Manager
      Then I should see Selling Manager HomePage
      And I should NOT see main loading icon
      And I should see individual loading indicators for the following:
          | My Selling Associates |
          | Macys To Dos          |

    @manual @domain_stores @omniclient @story_SDE-32 @mcom @website
    Scenario: Verifying individual loading indicators on expanding My Scheduled Areas
      Given I launch the macy's omniclient page
      When I sign into Omniclient application as Selling Manager
      Then I should see Selling Manager HomePage
      When I expand a selling area from "My Selling Associates" section
      Then I should see individual loading indicators for the following under My SAs:
          | List of Selling Associates                |
      And I should see individual loading indicators for the following under Yesterday's summary:
          | Client sales                              |
          | Client shopped                            |
          | Potentials to Clients                     |
          | New to Book                               |
          | List of Previous Shopper/Transaction Info |

    @manual @domain_stores @omniclient @story_SDE-32 @mcom @website
    Scenario: Verifying clicking any feature regardless of loading indicators
      When I sign into Omniclient application as Selling Manager
      Then I should see Selling Manager HomePage
      And I should see individual loading indicators for the following:
        | My Selling Associates |
        | Macys To Dos          |
      When I click on "My Clients" Tab
      And I should be able to navigate to My clients page regardless of loading indicators in Dashboard page

    @manual @domain_stores @omniclient @story_SDE-32 @mcom @website
    Scenario: Verifying clicking any feature individual loading indicators on expanding My Scheduled Areas
      Given I launch the macy's omniclient page
      When I sign into Omniclient application as Selling Manager
      Then I should see Selling Manager HomePage
      When I expand a selling area from "My Selling Associates" section
      Then I should see individual loading indicators for the following under My SAs:
        | List of Selling Associates                |
      And I should see individual loading indicators for the following under Yesterday's summary:
        | Client sales                              |
        | Client shopped                            |
        | Potentials to Clients                     |
        | New to Book                               |
        | List of Previous Shopper/Transaction Info |
      When I click on "My Clients" Tab
      And I should be able to navigate to My clients page regardless of loading indicators in SAs section