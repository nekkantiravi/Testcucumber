# Author: Claudiu Chirila
# Story: Add Clients on New Create To Do Screen
# Date Created: 12/13/2017
# Date Signed Off:

Feature: As an associate, I want a singular way to create To Dos from my dashboard, so that I can efficiently perform my work.


  Background:
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate

  @bcom @domain_stores @omniclient @website
  Scenario: The associate successfully types, selects and adds a customer to the To Customers list

    And I click on the Create To Do button on HOMEPAGE
    And I input client name "TEST2 TEST" in the input filed from Create ToDo page
    And I select the full name "TEST2 TEST ACCOUNT" from client dropdown Create ToDo page
    And user clicks on the ADD button form Create TO Dos page
    Then the selected user "TEST2 TEST ACCOUNT" is displayed in the customers list

  @bcom @domain_stores @omniclient @website
  Scenario: Show All link are displayed when more than 4 rows of customers are displayed

    And I select all Clients from the Clients section on Homepage
    And I click on the Create To Do button on HOMEPAGE
    Then the Show All link is displayed at the bottom of Customers list Create To Dos page

  @bcom @domain_stores @omniclient @website
  Scenario: Scroll bar is displayed when user clicks on Show All link

    And I select all Clients from the Clients section on Homepage
    And I click on the Create To Do button on HOMEPAGE
    And user checks the number of customers displayed in Create List view
    And user clicks on Show All link from Create To Dos page
    Then the scroll bar is displayed in the left side of the customers list
    And the number of customers displayed in list is increased Create To Dos page


  @bcom @domain_stores @omniclient @website
  Scenario: Customer search is successfully done by both FIRST and LAST name / Add button is enabled only after a customer is selected

    And I click on the Create To Do button on HOMEPAGE
    Then ADD button is displayed as disabled Create List page
    And I input client name "2 TEST ACCOUNT" in the input filed from Create ToDo page
    And I select the full name "TEST2 TEST ACCOUNT" from client dropdown Create ToDo page
    And user clicks on the ADD button form Create TO Dos page
    Then the selected user "TEST2 TEST ACCOUNT" is displayed in the customers list


#  @bcom @domain_stores @omniclient @website
#  Scenario: User add customers to the list until the scrollbar is displayed
#    And I click on the Create To Do button on HOMEPAGE
#    And the user adds clients to Customer list until the scrollbar is displayed

