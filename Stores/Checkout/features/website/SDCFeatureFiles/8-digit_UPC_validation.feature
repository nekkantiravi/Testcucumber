# Author: Stores Domain Checkout Team
# Story: SDC-189 - Checkout :: Add 8-digit UPC validation
# Date Created: 12/06/2016
# Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDC-189
Feature: As an associate, I want to check the validation process for adding 8-digit UPC.

  @Macy's @Send
  Scenario: Macy's - An associate verifies error message while adding 8-digit UPC.
    Given I am on "Macy's Sales Trans"
    When I add "43739967" item to the Checkout bag
    Then I should see appropriate error message
    When I call Cancel
    Then I am on "Macy's Sales Trans"

    @Macy's @Send
  Scenario Outline: Macy's - An associate successfully adds a 8-digit UPC with leading zeros.
      Given I am on "Macy's Sales Trans"
    When I add "<UPC>" item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
   	Then I see the item added to the bag
      When I call Cancel
      Then I am on "Macy's Sales Trans"

   	Examples:
   	| UPC            |
   	| 00043739967    |
   	| 000043739967   |
   	| 0000043739967  |
   	| 00000043739967 |
