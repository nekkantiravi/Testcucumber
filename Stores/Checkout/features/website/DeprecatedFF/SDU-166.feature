#Author: Stores Domain Checkout Team
   #Story: SDU-166- Checkout :: Base Configuration Screen
   #Date Created: 6/1/2017
   #Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDU-166
Feature: As a Field Service Technician, I want insights into the configuration of uPOS and selling device so that I can
  use this information during research of an issue if needed.

  @Macy's @Send
  Scenario: Field Service Technician is able to view details of the configuration of uPOS to assist in research.
    Given I am on "Macy's Sales Trans"
    And I click on Hamburger icon
    Then I See the About icon
    When I click the About Icon
    Then I am directed to the Base Configuration Screen
#    And I should be able to view the configuration values of uPOS
    When I click on Hamburger icon
    Then I See the About icon
    When I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Bloomingdale's @Send
  Scenario: Field Service Technician is able to view details of the configuration of uPOS to assist in research.
    Given I am on "Bloomingdale's Sales Trans"
    And I click on Hamburger icon
    Then I See the About icon
    When I click the About Icon
    Then I am directed to the Base Configuration Screen
#    And I should be able to view the configuration values of uPOS
    When I click on Hamburger icon
    Then I See the About icon
    When I click on Cancel Transaction button
    Then I am on "Add Product" page

#   Refer to SDU-707 for full functionality









