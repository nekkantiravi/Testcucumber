# Author: Stores Domain Checkout Team
# Story: SDU-431 - Create mock tendering screen
# Date Created: 10/01/2017
# Date Signed Off:

@domain_stores @project_checkout @release_1717 @story_SDU-431
Feature: As an associate I want to get to tendering screen after the checkout info are complete

  @Macy's @Send
  Scenario: Macy's - An associate navigates to tender page after shipping info flow is done
    Given I am on "Macy's Sales Trans"
    When I checkout an item for a send sale
    Then I can see the mock tendering screen
    And I can see the Tendering Module label
    And No extra tendering text is displayed on the bottom page of Tendering screen
    And I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I can see the sales trans landing page



  @Macy's @Take
  Scenario: Macys - In a Take when an associate navigates to tender page tender page is properly displyed
    Given I am on "Macy's Sales Trans"
    When I checkout an item for a take sale
    Then I can see the mock tendering screen
    And I can verify the authorize button was removed
    And No extra tendering text is displayed on the bottom page of Tendering screen
    And I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I can see the sales trans landing page


  @Bloomingdale's @Send
  Scenario: Bloomingdale's - An associate navigates to tender page after shipping info flow is done
    Given I am on "Bloomingdale's Sales Trans"
    When I add "20714001940" item to the Checkout bag
    Then I can see the added to bag toast message
    And I click on the bag icon
    Then I can see the checkout button
    When I press the checkout button
    And I select Free Shipping
    And I press next steps
    And I input the Shipping Information
    And I press next steps
    And I press next steps
    Then I see the Order Review Overlay
    When I press next steps
    Then I can see the mock tendering screen
    And No extra tendering text is displayed on the bottom page of Tendering screen
    And I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I can see the sales trans landing page


  @Bloomingdale's @Take
  Scenario: Bloomingdale's - In a Take when an associate navigates to tender page tender page is properly displayed
    Given I am on "Bloomingdale's Sales Trans"
    When I select Take option
    And I add "20714001940" item to the Checkout bag
    When I close the CRL Overlay
    And I click on the bag icon
    Then I should be able to see the bag view
    When I press the checkout button
    Then I can see the bag fee overlay
    When I add "0" bags
    Then I can see the mock tendering screen
    And I can verify the authorize button was removed
    And No extra tendering text is displayed on the bottom page of Tendering screen
    And I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I can see the sales trans landing page
