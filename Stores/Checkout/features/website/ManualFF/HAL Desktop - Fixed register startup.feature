# Author: Anamaria Oanea - Stores Domain Checkout Team
# Story: SDU-1160 Fixed register startup
# Date Created: 11/08/2017
# Date Signed Off:


@domain_stores @project_checkout @release_1721 @story_SDU_1160
Feature: As an associate using uPOS on a fixed register, I want to be able to
  A. search for product without needing to authenticate or B. log in as soon as uPOS is launched

  @manual @HALDesktop @Macy's
    Scenario: The associate can log in
    Given I am on landing screen
    And I can see the Sign In form
    When I introduce the credentials
    And I press "Sign In" button
    Then I am on the PD page

  @manual @HALDesktop @Macy's
    Scenario: The associate can search items without logging in
      Given I am on landing screen
      And I can see the "Find Product" button
      When I press "Find Product" button
      Then I am on the PD page
      When I search for "<string>" on product discovery
      Then I see the Search results page

  @manual @HALDesktop @Macy's
    Scenario: The associate needs to authenticate after adding an item to bag
      Given I am on landing screen
      And I can see the "Find Product" button
      When I press "Find Product" button
      Then I am on the PD page
      When I search for "<string>" on product discovery
      Then I see the Search results page
      When I add an item to the Checkout bag
      Then I can see the log in form
      When I introduce the credentials
      And I press "Sign In" button
      Then I am on bag page

  @manual @HALDesktop @Bloomingdale's
  Scenario: The associate can log in
    Given I am on landing screen
    And I can see the Sign In form
    When I introduce the credentials
    And I press "Sign In" button
    Then I am on the PD page

  @manual @HALDesktop @Bloomingdale's
  Scenario: The associate can search items without logging in
    Given I am on landing screen
    And I can see the "Find Product" button
    When I press "Find Product" button
    Then I am on the PD page
    When I search for "<string>" on product discovery
    Then I see the Search results page

  @manual @HALDesktop @Bloomingdale's
  Scenario: The associate needs to authenticate after adding an item to bag
    Given I am on landing screen
    And I can see the "Find Product" button
    When I press "Find Product" button
    Then I am on the PD page
    When I search for "<string>" on product discovery
    Then I see the Search results page
    When I add an item to the Checkout bag
    Then I can see the log in form
    When I introduce the credentials
    And I press "Sign In" button
    Then I am on bag page





