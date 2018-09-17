# Author: Traci Morris
# Story: SDE-60 - OmniClient :: Credit Card Search
# Date Created: 07/07/2017
# Date Signed Off:

Feature: As an associate, I want to search for customers using their Credit Card so that I can
  locate customer profiles and add customers to my book.

  @manual @domain_stores @omniclient @story_SDE-60 @website @mcom
  Scenario: Search for a Client that is in CC2 but NOT in our DB via an Individualized Card swipe.
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    Then I should be logged in "associate" and see the omniclient landing page
    When I click the Credit Card field
    And I swipe an individualized Credit Card
    Then I should see Search Results Screen and see the following from CC2:
      | Name           |
      | Address        |
      | Phone Number   |

  @manual @domain_stores @omniclient @story_SDE-60 @website @mcom
  Scenario: Search for a Client that is in our DB who I have a relationship with via an Individualized Card swipe.
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    Then I should be logged in "associate" and see the omniclient landing page
    When I click the Credit Card field
    And I swipe an individualized Credit Card
    Then I should see Search Results Screen and see the following:
      | Name                   |
      | Address                |
      | Phone Number           |
      | My Relationship icon   |

  @manual @domain_stores @omniclient @story_SDE-60 @website @mcom
  Scenario: Search for a Client that is in our DB who I DO NOT have a relationship with via an Individualized Card swipe.
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    Then I should be logged in "associate" and see the omniclient landing page
    When I click the Credit Card field
    And I swipe an individualized Credit Card
    Then I should see Search Results Screen and see the following:
      | Name                                  |
      | Address                               |
      | Phone Number                          |
      | Relationship w/other Associate icon   |
      | Add to Book icon                      |

  @manual @domain_stores @omniclient @story_SDE-60 @website @mcom
  Scenario: Search for a Client that is NOT in CC2 and is NOT in our DB via an Operational Card swipe.
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    Then I should be logged in "associate" and see the omniclient landing page
    When I click the Credit Card field
    And I swipe an operational Credit Card
    Then I should see Search Results Screen and see the following:
      | Name           |


  @manual @domain_stores @omniclient @story_SDE-60 @website @mcom
  Scenario: Search for a Client that is in our DB who I have a relationship with via an Operational Card swipe.
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    Then I should be logged in "associate" and see the omniclient landing page
    When I click the Credit Card field
    And I swipe an operational Credit Card
    Then I should see Search Results Screen and see the following:
      | Name                   |
      | Address                |
      | Phone Number           |
      | My Relationship icon   |


  @manual @domain_stores @omniclient @story_SDE-60 @website @mcom
  Scenario: Search for a Client that is in our DB who I DO NOT have a relationship with via an Operational Card swipe.
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    Then I should be logged in "associate" and see the omniclient landing page
    When I click the Credit Card field
    And I swipe an operational Credit Card
    Then I should see Search Results Screen and see the following:
      | Name                                  |
      | Address                               |
      | Phone Number                          |
      | Relationship w/other Associate icon   |
      | Add to Book icon                      |

