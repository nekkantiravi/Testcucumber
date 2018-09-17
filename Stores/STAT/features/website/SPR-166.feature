# Author: STAT Team
# Story: SDC-166 - GVB - UI - Registry validation error message
# Date Created: 10/24/2017
# Date Signed Off:

@STAT @vendor_bonus @SPR-166
Feature: As a Gift Registry Advisor I want the registry number entered into the Registry field on the
  ' Vendor Bonus Request' page to be validated as a wedding or commitment registry so I can
  submit Vendor Bonus Requests according to established business rules

  Scenario Outline: "<ID>" Registries are <text>
    Given I am on Gift
    And I log in as an Advisor
    When I click the hamburger/main menu
    And I click the Vendor Bonus Program link
    Then I should see "Vendor Bonus Program" as title
    When I click the Create Request link
    And I type "<CODE>" in Registry ID field
    And I click search
    Then I should see "<MESSAGE>" as the message

    Examples:
    |ID | CODE  |MESSAGE| text|
    |01| 2357102 || allowed for Wedding|
    |02| 2357103  |Occasion type must be wedding or commitment in order to qualify for the vendor bonus program|are not allowed for Anniversary|
    |03| 2357104 || allowed for Commitment                                                                                                     |
    |04| 2357105 |Occasion type must be wedding or commitment in order to qualify for the vendor bonus program|are not allowed for Housewarming|
    |05| 012       |Invalid registry Id 12 and/or divison 71|not allowed with insufficient ID characters                                       |
    |06|12345678901|Invalid registry Id 1234567890 and/or divison 71|not accepting over 10 characters                                          |


  Scenario: Registry alpha characters are not allowed (cannot be entered)
    Given I am on Gift
    And I log in as an Advisor
    When I click the hamburger/main menu
    And I click the Vendor Bonus Program link
    And I click the Create Request link
    And I type "abcdefghijklmnoprstuvxz" in Registry ID field
    Then I should see Search button is "inactive"
