# Author: Stores Domain Checkout Team
# Story: SDC-62 - Checkout :: Create a Bag View
# Date Created: 09/06/2016
# Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDC-62
Feature: As an associate, I want to view a bag screen,
  so that I can add items to it for my customer to purchase.

  Scenario: An associate accesses the bag screen and sees its contents.
    Given I am on Sales Trans
    When I click on the bag icon
    Then I can see Checkout empty bag view
