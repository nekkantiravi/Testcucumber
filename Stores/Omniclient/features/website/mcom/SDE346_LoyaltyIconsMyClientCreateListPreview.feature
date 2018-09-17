# Author: Claudiu Chirila
# Story: SDE-346 - OmniClient :Macy's Loyalty: Loyalty Tier Icons on the My Client Create List Preview Page
# Date Created: 10/26/2017
# Date Signed Off:

@manual @SKIPPED
Feature: As a MyClient User, I want to know the current Loyalty tier level status of my customer who is a member of the Macy's Star Rewards Loyalty Program,
  so that I can drive sales by leveraging this information.

  @domain_stores @omniclient @story_SDE-346 @website @mcom
  Scenario: Verify the Loyalty Tier Icons are displayed on the My Client Event Info screen from PROFILE tab

    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I navigate on Create To Dos tab
    And I navigate to PROFILE tab
    And I click on I want to include Customer Profile Dates in list radio button
    And I select "January" in From Month dropdown Profile tab
    And I select "01" in From Day dropdown Profile tab
    And I select "December" in To Month dropdown Profile tab
    And I select "31" in To Day dropdown Profile tab
    And I click on PREVIEW button from CREATE TODOS tabs
    Then the CREATE LIST PREVIEW page is displayed
    And the column named Star Rewards Level is displayed LIST PREVIEW
    And I should see the Star Rewards Tier Icons on the Create List preview screen

      | gold        |
      | silver      |
      | platinum    |
      | non-loyalty |

  @domain_stores @omniclient @story_SDE-346 @website @mcom
  Scenario: Verify the Loyalty Tier Icons are displayed on the My Client Event Info screen from TRANSACTIONS tab
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I navigate on Create To Dos tab
    And I navigate to TRANSACTIONS tab
    And I click on filter radio button from TRANSACTIONS tab
    And I select the I want to include transactions filters in my list radio button
    And I click on PREVIEW button from CREATE TODOS tabs
    Then the CREATE LIST PREVIEW page is displayed
    And the column named Star Rewards Level is displayed LIST PREVIEW
    And I should see the Star Rewards Tier Icons on the Create List preview screen

      | gold        |
      | silver      |
      | platinum    |
      | non-loyalty |

