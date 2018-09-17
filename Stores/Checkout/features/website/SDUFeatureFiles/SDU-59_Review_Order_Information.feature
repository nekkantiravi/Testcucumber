# Author: Stores Domain Checkout Team
# Story: SDU-59 - Checkout :: Review Order Information
# Date Created: 05/03/2017
# Date Signed Off:

@domain_stores @project_checkout @release_1708 @story_SDU-59  @Macy's
Feature: As a customer, I want to review my order information (i.e. Shipping and Billing info.)
  prior to transaction completion, so that I can ensure accuracy.


  Background: Associate arrives on the Order Review Overlay
    Given I am on "Macy's Sales Trans"
    When I checkout an item and arrive on the "Order Review" overlay

  @Macy's
  Scenario: Macy's - Associate sees Order Information on the Overlay for Customer Review
    And I can see the Shipping Method is set to "Standard" in the Review overlay
    And I can see the selected Shipping Address in the Review overlay
    And I can see the selected Billing Address in the Review overlay
    When I call Cancel
    Then I can see the sales trans landing page

  @Macy's
  Scenario: Macy's - Associate sees an error in the Shipping Method section and updates it to Express
    And I tap edit next to Shipping Method
    Then I see the Shipping Method Overlay
    And I select Express option
    And I click on Next Step button
    Then I see the Shipping information Overlay
    When I click on Next Step button
    Then I can see the Input Billing Information screen
    And I click on Next Step button
    Then I see the Order Review Overlay
    And I can see the Shipping Method is set to "Express" in the Review overlay
    And I can see the selected Shipping Address in the Review overlay
    And I can see the selected Billing Address in the Review overlay
    When I call Cancel
    Then I can see the sales trans landing page

  @Macy's
  Scenario: Macy's - Associate sees an error in the Shipping Method section and updates it to Premium
    And I tap edit next to Shipping Method
    Then I see the Shipping Method Overlay
    And I select Premium option
    And I click on Next Step button
    Then I see the Shipping information Overlay
    When I click on Next Step button
    Then I can see the Input Billing Information screen
    And I click on Next Step button
    Then I see the Order Review Overlay
    And I can see the Shipping Method is set to "Premium" in the Review overlay
    And I can see the selected Shipping Address in the Review overlay
    And I can see the selected Billing Address in the Review overlay
    When I call Cancel
    Then I can see the sales trans landing page

  @Macy's
  Scenario: Macy's - Associate sees an error in the Shipping Address section and edits it
    And I tap edit next to Shipping Address
    Then I see the Shipping information Overlay
    When I update the Shipping Address
    And I click on Next Step button
    Then I can see the Input Billing Information screen
    When I click on Next Step button
    Then I see the Order Review Overlay
    And I can see the Shipping Method is set to "Standard" in the Review overlay
    And I can see the updated Shipping Address in the Review overlay
    When I call Cancel
    Then I can see the sales trans landing page

  @Macy's
  Scenario: Macy's - Associate sees an error in the Billing Address section and edits it
    And I tap edit next to Billing Address
    Then I see the same as Shipping prompt
    When I uncheck the same as shipping checkbox
    Then I can see the Input Billing Information screen
    When I input Billing Information
    And I click on Next Step button
    Then I see the Order Review Overlay
    And I can see the Shipping Method is set to "Standard" in the Review overlay
    And I can see the selected Shipping Address in the Review overlay
    And I can see the updated Billing Address in the Review overlay
    When I call Cancel
    Then I can see the sales trans landing page

  @Macy's
  Scenario: Macy's - Customer confirms Order Info is correct, so the associate presses Complete
      and navigates to the tender screen
    And I tap on the Complete button
    Then I can see the mock tendering screen
    When I call Cancel
    Then I can see the sales trans landing page