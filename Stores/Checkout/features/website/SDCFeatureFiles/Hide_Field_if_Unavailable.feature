# Author: Stores Domain Checkout Team
# Story: SDC-143 - Testing Only: Hide Field if Unavailable
# Date Created: 11/21/2016
# Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDC-143
Feature: As an associate, I dont want to view empty fields in item details,
  so that I am no confused on the actual details that apply to an item.

  @Macy's @Take
  Scenario: Add UPC that does not have color, size or WebID.
    Given I am on "Macy's Sales Trans"
    When I select Take option
    Then I add an item to the Checkout bag that "does not have a color size WebID"
    And I close the CRL Overlay
    And I can see the added to bag toast message
    When I click on the bag icon
    Then I can see the color size and WebID titles do not display
    When I call Cancel
    Then I am on "Macy's Sales Trans"

    @Macy's @Take
  Scenario: Add UPC that does not have color.
   Given I am on "Macy's Sales Trans"
    When I select Take option
    Then I add an item to the Checkout bag that does not have a color
    And I close the CRL Overlay
    And I can see the added to bag toast message
    When I click on the bag icon
    Then I can see the color title does not display
      When I call Cancel
      Then I am on "Macy's Sales Trans"

      @Macy's @Take
  Scenario: Add UPC that does not have size.
        Given I am on "Macy's Sales Trans"
    When I select Take option
    Then I add an item to the Checkout bag that does not have a size
    And I can see the added to bag toast message
    When I click on the bag icon
    Then I can see the size title does not display
        When I call Cancel
        Then I am on "Macy's Sales Trans"

    @Macy's @Take
  Scenario: Add UPC that does not have WebID.
      Given I am on "Macy's Sales Trans"
    When I select Take option
    Then I add an item to the Checkout bag that does not have a WebID
    And I can see the added to bag toast message
    When I click on the bag icon
    Then I can see the WebID title does not display
      When I call Cancel
      Then I am on "Macy's Sales Trans"


#  //2100973 Works for no size
#
#    // Need to check with PDMs to see relevency


