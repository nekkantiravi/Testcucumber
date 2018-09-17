# Author: Ingrid- Stores Domain Checkout Team
# Story: SDU-707 - Enhance configuration screen to support multiple levels (ECSTSML)
# Date Created: 06/07/2017
# Date Signed Off:


@domain_stores @project_checkout @release_1712 @story_SDU-707
  Feature: As an associate I want to know more information about the device, scanner and other configurations of the application

    @Macy's
    Scenario: Macy's - As an associate I want to know more information about the device
      Given I am on "Macy's Sales Trans"
      And I click on Hamburger icon
      When I click the About Icon
      Then I see the Device row
      When I click on Device row
      Then I can see Device information
      When I click on Hamburger icon
      Then I See the About icon

    @Macy's
    Scenario: Macy's - As an associate I want to know more information about the scanner
        Given I am on "Macy's Sales Trans"
        And I click on Hamburger icon
        When I click the About Icon
        Then I see the Scanner row
        When I click on the Scanner row
        Then I can see Scanner information
      When I click on Hamburger icon
      Then I See the About icon

    @Macy's
    Scenario: Macy's - As an associate I want to know more information about the Other configurations
      Given I am on "Macy's Sales Trans"
      And I click on Hamburger icon
      When I click the About Icon
      Then I see the Other row
      When I click on the Other row
      Then I can see Other configurations
      When I click on Hamburger icon
      Then I See the About icon

    @Bloomingdale's
    Scenario: Bloomingdale's - As an associate I want to know more information about the device
      Given I am on "Bloomingdale's Sales Trans"
      And I click on Hamburger icon
      When I click the About Icon
      Then I see the Device row
      When I click on Device row
      Then I can see Device information
      When I click on Hamburger icon
      Then I See the About icon

    @Bloomingdale's
    Scenario: Bloomingdale's - As an associate I want to know more information about the scanner
      Given I am on "Bloomingdale's Sales Trans"
      And I click on Hamburger icon
      When I click the About Icon
      Then I see the Scanner row
      When I click on the Scanner row
      Then I can see Scanner information
      When I click on Hamburger icon
      Then I See the About icon

    @Bloomingdale's
    Scenario: As an associate I want to know more information about the Other configurations
      Given I am on "Bloomingdale's Sales Trans"
      And I click on Hamburger icon
      When I click the About Icon
      Then I see the Other row
      When I click on the Other row
      Then I can see Other configurations
      When I click on Hamburger icon
      Then I See the About icon



