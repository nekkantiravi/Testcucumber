
Feature: As a Saturn Search Manager,I want to verify black list override functionality

  ##################################Story #B-98993 :: Add Override###########################################

  @new_autocomplete @wip
  Scenario Outline: Verify the behavior by creating a new blacklist override with 100 characters display phrase,
  Exact match override type and without selecting publish checkbox
    Given I login to Saturn as an "admin" user
    When I navigate to "New Autocomplete" under Administration
    And I navigate to "Add Override" popup in "Autocomplete Overrides" page
    And I enter display phrase with 100 characters in combination of alphanumeric and special characters
    And I select "Blacklist" as "Override type"
    And I select "<oper_type>" as "Operator Type"
    And I "<publish_value>" publish textbox
    And I click on "OK" button
    Then I should see that new phrase is displayed in autocomplete override table
    And I should see that added phrase is saved in saturn DB
    Examples:
    |oper_type  |publish_value|
    |Exact Match|de-select    |
    |Exact Match|select       |
    |Contains   |de-select    |
    |Contains   |select       |

  @new_autocomplete @wip
  Scenario Outline: Verify the behavior by adding blacklist override and click on cancel button
    Given I login to Saturn as an "admin" user
    When I navigate to "New Autocomplete" under Administration
    And I navigate to "Add Override" popup in "Autocomplete Overrides" page
    And I enter display phrase with 100 characters in combination of alphanumeric and special characters
    And I select "Blacklist" as "Override type"
    And I select "<oper_type>" as "Operator Type"
    And I "<publish_value>" publish textbox
    And I click on "Cancel" button
    Then I should "not" see that new phrase is displayed in autocomplete override table
    And I should "not" see that added phrase is saved in saturn DB
    Examples:
      |oper_type  |publish_value|
      |Exact Match|de-select    |
      |Exact Match|select       |
      |Contains   |de-select    |
      |Contains   |select       |


    #####################################################################