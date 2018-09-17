# Author: Stores Domain Checkout Team
# Story: SDU-66 - Select Shipping Method
# Date Created: 04/13/2017
# Date Signed Off:

@domain_stores @project_checkout @release_1708 @story_SDU-66  @Macy's
Feature: As an associate, I want to set the shipping method for a customer purchase.

  Background: Asociate arrives on the Shipping Method overlay
    Given I am on "Macy's Sales Trans"
    And I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    Then I click on the bag icon
    When I press the checkout button
    Then I see the Shipping Method Overlay

  @Macy's
  Scenario: Macy's - An associate verifies that the default shipping method is Standard
    Then I can see the Standard option selected
    When I close the overlay
    And I call Cancel
    Then I can see the sales trans landing page

  @Macy's
  Scenario: Macy's - An associate changes the shipping method to Premium
    When I select Premium option
    Then I can see the Premium option selected
    When I close the overlay
    And I call Cancel
    Then I can see the sales trans landing page

  @Macy's
  Scenario: Macy's - An associate changes the shipping method to Express
    When I select Express option
    Then I can see the Express option selected
    When I close the overlay
    And I call Cancel
    Then I can see the sales trans landing page

  @Macy's
  Scenario: Macy's - An associate proceeds to the next phase from the Shipping Method overlay
    When I press next steps
    Then I see the Shipping information Overlay
    When I input the Shipping Information
    And I press next steps
    Then I can see the Input Billing Information screen
    When I press next steps
    Then I see the Order Review Overlay
    And I can see the Shipping Method is set to "Standard" in the Review overlay
    When I close the overlay
    And I call Cancel
    Then I can see the sales trans landing page

  @Macy's
  Scenario: Macy's - An associate proceeds to the next phase from the Shipping Method overlay while selecting Premium
    When I select Premium option
    And I press next steps
    Then I see the Shipping information Overlay
    When I input the Shipping Information
    And I press next steps
    Then I can see the Input Billing Information screen
    When I press next steps
    Then I see the Order Review Overlay
    And I can see the Shipping Method is set to "Premium" in the Review overlay
    When I close the overlay
    And I call Cancel
    Then I can see the sales trans landing page

  @Macy's
  Scenario: Macy's - An associate proceeds to the next phase from the Shipping Method overlay while selecting Express
    When I select Express option
    And I press next steps
    Then I see the Shipping information Overlay
    When I input the Shipping Information
    And I press next steps
    Then I can see the Input Billing Information screen
    When I press next steps
    Then I see the Order Review Overlay
    And I can see the Shipping Method is set to "Express" in the Review overlay
    When I close the overlay
    And I call Cancel
    Then I can see the sales trans landing page

