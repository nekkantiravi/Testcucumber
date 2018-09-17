# Author: Traci Morris
# Story: SDE-32 - OmniClient :: Selling Manager Dashboard
# Date Created: 05/02/2017
# Date Signed Off:

  Feature: As a Selling Manager, I want my dashboard to display information as it becomes available so that I don't have to wait for all information to load and display before taking action

    @manual @domain_stores @omniclient @story_SDE-32 @bcom @website
    Scenario: Verifying individual loading indicators on Dashboard
      Given I launch the bloomie's omniclient page
      When I sign into Omniclient application as Selling Manager
      Then I should see Selling Manager Dashboard
      And I should NOT see main loading icon
      And I should see individual loading indicators for the following:
          | My Sales Professionals |
          | Clients                |
          | Lists                  |
          | Client Sales           |

    @manual @domain_stores @omniclient @story_SDE-32 @bcom @website
    Scenario: Verifying individual loading indicators on expanding My Scheduled Areas
      Given I launch the bloomie's omniclient page
      When I sign into Omniclient application as Selling Manager
      Then I should see Selling Manager Dashboard
      When I expand a selling area from "My Sales Professionals" section
      Then I should see individual loading indicators for the following under My SPs:
          | List of Sales Professionals                |
      And I should see individual loading indicators for the following under Yesterday's summary:
          | Client Sales                              |
          | Customer shopped                          |
          | Prospects to Clients                      |
          | New to Book                               |
          | List of Previous Shopper/Transaction Info |

    @manual @domain_stores @omniclient @story_SDE-32 @bcom @website
    Scenario: Verifying clicking any feature regardless of loading indicators
      Given I launch the bloomie's omniclient page
      When I sign into Omniclient application as Selling Manager
      Then I should see Selling Manager Dashboard
      And I should see individual loading indicators for the following:
        | My Sales Professionals |
        | Clients                |
        | Lists                  |
        | Client Sales           |
      When I click on "My Customers" Tab
      And I should be able to navigate to My customers page regardless of loading indicators in Dashboard page


    @manual @domain_stores @omniclient @story_SDE-32 @bcom @website
    Scenario: Verifying clicking any feature after expanding Scheduled Areas
      Given I launch the bloomie's omniclient page
      When I sign into Omniclient application as Selling Manager
      Then I should see Selling Manager Dashboard
      When I expand a selling area from "My Sales Professionals" section
      Then I should see individual loading indicators for the following under My SPs:
        | List of Sales Professionals                |
      And I should see individual loading indicators for the following under Yesterday's summary:
        | Client Sales                              |
        | Customer shopped                          |
        | Prospects to Clients                      |
        | New to Book                               |
        | List of Previous Shopper/Transaction Info |
      When I click on "My Customers" Tab
      And I should be able to navigate to My customers page regardless of loading indicators in SPs section