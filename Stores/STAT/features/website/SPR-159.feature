# Author: STAT Team
# Story: SPR-159 - GVB - UI - Create Request - Enter registry
# Date Created: 10/23/2017
# Date Signed Off:


@STAT @vendor_bonus @SPR-159
Feature: As a Gift Registry Advisor I want to be able to enter a registry number on the 'Vendor Bonus Request'
  page when I select Create Request on the Vendor Bonus Program page so I can initiate a vendor bonus

  Scenario: Selecting the Create Request navigates to Vendor Bonus Request page and elements are present on the page.
    Given I am on Gift
    And I log in as an Advisor
    When I click the hamburger/main menu
    And I click the Vendor Bonus Program link
    Then I should see "Vendor Bonus Program" as title
    When I click the Create Request link
    Then I should see close button
    And I should see "Vendor Bonus Request" as title
    And I should see "Registry" accordion section
    And I should see Registry ID input field


  Scenario: Search button is disabled until 2 characters of the Registry number is entered and Re
    Given I am on Gift
    And I log in as an Advisor
    When I click the hamburger/main menu
    And I click the Vendor Bonus Program link
    And I click the Create Request link
    Then I should see Search button is "inactive"
    When I type "12" in Registry ID field
    Then I should see Search button is "active"
