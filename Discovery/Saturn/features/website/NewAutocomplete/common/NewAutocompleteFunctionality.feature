
Feature: As a Saturn Search Manager,I want to see the New Autocomplete option in the Administration tab so
  I can navigate to the New Autocomplete options and manage overrides.


  #############################story_B-98992###########################################
  @new_autocomplete
  Scenario: Verify New Autocomplete option display in the administration tab
    Given I login to Saturn as an "admin" user
    When I mouse hover on "administration" option
    Then I should see New Autocomplete option in the dropdown list
    And I should see "New Autocomplete" option is placed below "About" option
    And I should see "Autocomplete" option is placed below "New Autocomplete" option
    When I mouse hover on "New Autocomplete" option
    Then I should see a flyout with below options populated:
      |Autocomplete Overrides|
      |Dictionary Overrides  |
      |Stemming Report       |
      |Stop Words Report     |
      |Unbreakable Phrases Report|


  @new_autocomplete
  Scenario: Verify the behavior when non admin user signs in to saturn
    Given I login to Saturn as an "non_admin" user
    Then I should not see "Administration" tab


  #############################story_B-99050###########################################
  @new_autocomplete
  Scenario: Verify Autocomplete override table header bar options
    Given I login to Saturn as an "admin" user
    When I navigate to "New Autocomplete" under Administration
    And I click on "Autocomplete Overrides" option
    Then I should see autocomplete override table with 10 columns
    And I should see "Autocomplete Override" text in header bar
    And I should see "Add Override" button in header bar
    And I should see "Export" button in header bar

  @new_autocomplete
  Scenario: Verify Autocomplete override table column display
    Given I login to Saturn as an "admin" user
    When I navigate to "New Autocomplete" under Administration
    And I click on "Autocomplete Overrides" option
    Then I should see autocomplete override table with below 10 columns:
      |ID|
      |Override Phrase|
      |Type           |
      |Operator       |
      |Created By     |
      |Creation Date  |
      |Modified By    |
      |Modified Date  |
      |Published      |
      |Action (s)     |

  @new_autocomplete @wip
  Scenario: Verify data in each column of Autocomplete override table
    Given I login to Saturn as an "admin" user
    When I navigate to "New Autocomplete" under Administration
    And I click on "Autocomplete Overrides" option
    Then I should see autocomplete override table with 10 columns
#    And I should see that "ID" column contains "numeric" data
#    And I should see that "ID" is not hyperlinked
#    And I should see that "Override Phrase" column contains "alphaNumeric" data
#    And I should see that "Type" column contains "whitelist or blacklist" data
#    And I should see that "Operator" column contains "Contains or Exact Match" data
    And I should see operator types are populated as in DB table
#    And I should see that "Created By" column contains "User Name" data
#    And I should see that "Creation Date" column contains "dd/MM/yyyy" data
#    And I should see that "Modified By" column contains "User Name" data
#    And I should see that "Modified Date" column contains "dd/MM/yyyy" data
#    And I should see that "Published" column contains "ON or OFF" data
#    And I should see that "Action (s)" column contains "Edit or Delete" data
#    And I should see that "Edit" and "Delete" strings are hyperlinked

  @new_autocomplete @wip
  Scenario: Verify Autocomplete override table footer Navigation bar options
    Given I login to Saturn as an "admin" user
    When I navigate to "New Autocomplete" under Administration
    And I click on "Autocomplete Overrides" option
    Then I should see autocomplete override table with 10 columns
    And I should see page record count in left side of footer navigation bar
    And I should see standard page navigation in right side of footer navigation bar
    And I should see pagination links are working as expected

  @new_autocomplete @wip
  Scenario: Verify sorting behavior of autocomplete override table columns
    Given I login to Saturn as an "admin" user
    When I navigate to "New Autocomplete" under Administration
    And I click on "Autocomplete Overrides" option
    Then I should see autocomplete override table with 10 columns
    And I should see primary sort on the table as descending by creation date
    And I should see all cloumns are sortable except "Action (s)" column
    And I should see default sort behavior for each column as below: ///dependent on service
      |ID             |descending      |
      |Override Phrase|alphabetical A-Z|
      |Type           |alphabetical A-Z|
      |Operator       |alphabetical A-Z|
      |Created By     |alphabetical A-Z|
      |Creation Date  |descending      |
      |Modified By    |alphabetical A-Z|
      |Modified Date  |descending      |
      |Published      |ON              |

    #############################Story #B-98993#######################################

  @new_autocomplete @wip
  Scenario: Verify Add override popup overlay display in Autocomplete override page
    Given I login to Saturn as an "admin" user
    When I navigate to "New Autocomplete" under Administration
    And I click on "Autocomplete Overrides" option
    And I click on "Add Override" button
    And I should see "Display Phrase" text box as required field
    And I should see "Override Type" and "Operator Type" dropdown boxes as required fields
    And I should see "Publish" checkbox
    And I should see "Ok", "Cancel" and "close" buttons

  @new_autocomplete @wip
  Scenario: Verify the behavior by adding override with more than 100 characters in override phrase
    Given I login to Saturn as an "admin" user
    When I navigate to "New Autocomplete" under Administration
    And I navigate to "Add Override" popup in "Autocomplete Overrides" page
    And I enter display phrase with 120 characters in combination of alphanumeric and special characters
    Then I should see that override phrase accepts till 100 characters only

    ################################################Story #B-99262####################################

  @new_autocomplete @wip
  Scenario: Verify filter options in Autocomplete override table
    Given I login to Saturn as an "admin" user
    When I navigate to "New Autocomplete" under Administration
    And I click on "Autocomplete Overrides" option
    Then I should see two levels of filters in the override table
    And I should see primary filter with below options:
      |All      |
      |Blacklist|
      |Whitelist|
    And I should see secondary filter with below options:
      |All|
      |ID |
      |Override Phrase|
      |Operator       |
      |Created By     |
      |Creation Date  |
      |Modified By    |
      |Modified Date  |
      |Published      |
    And I should see "Go" button and "Clear" option

  @new_autocomplete @wip
  Scenario: Verify the behavior when selection from primary filter is applied in Autocomplete override table
    Given I login to Saturn as an "admin" user
    When I navigate to "New Autocomplete" under Administration
    And I click on "Autocomplete Overrides" option
    And I select any random option from "primary" filter
    And I click on "Go" button
    Then I should see appropriate results in autocomplete override table

  @new_autocomplete @wip
  Scenario Outline: Verify the behavior when selection from both the filters are applied in Autocomplete override table
    Given I login to Saturn as an "admin" user
    When I navigate to "New Autocomplete" under Administration
    And I click on "Autocomplete Overrides" option
    And I select any random option from "primary" filter
    And I select "<secondary_filter>" option
    And I enter value in secondary filter textbox field based on selected "<secondary_filter>" option
    And I click on "Go" button
    Then I should see appropriate results in autocomplete override table
    Examples:
      |secondary_filter|
      |All            |
      |ID             |
      |Override Phrase|
      |Operator       |
      |Created By     |
      |Creation Date  |
      |Modified By    |
      |Modified Date  |
      |Published      |

  @new_autocomplete @wip
  Scenario: Verify the behavior when cleared selections after applying both the filters in Autocomplete override table
    Given I login to Saturn as an "admin" user
    When I navigate to "New Autocomplete" under Administration
    And I click on "Autocomplete Overrides" option
    And I select any random option from "primary" filter
    And I select any random option from "secondary" filter
    And I click on "Clear" option
    Then I should see that selected filters are cleared

  @new_autocomplete @wip
  Scenario: Verify the persistence of filters when paginated in Autocomplete override table
    Given I login to Saturn as an "admin" user
    When I navigate to "New Autocomplete" under Administration
    And I click on "Autocomplete Overrides" option
    And I apply both the filters and click on "Go" button
    Then I should see appropriate results in autocomplete override table
    When I paginate
    Then I should see that selected filters are persisted

  @new_autocomplete @wip
  Scenario: Verify the persistence of filters when sorting Autocomplete override table columns
    Given I login to Saturn as an "admin" user
    When I navigate to "New Autocomplete" under Administration
    And I click on "Autocomplete Overrides" option
    And I apply both the filters and click on "Go" button
    Then I should see appropriate results in autocomplete override table
    When I sort any random column in autocomplete override table
    Then I should see that selected filters are persisted

  @new_autocomplete @wip
  Scenario Outline: Verify the persistence of filters after navigating from individual record and override table
    Given I login to Saturn as an "admin" user
    When I navigate to "New Autocomplete" under Administration
    And I click on "Autocomplete Overrides" option
    And I apply both the filters and click on "Go" button
    Then I should see appropriate results in autocomplete override table
    When I click on "<action_type>" value of any random record
    And I click on "cancel" button on overlay
    Then I should see that selected filters are persisted
    Examples:
      |action_type|
      |Edit|
      |Delete|

  @new_autocomplete @wip
  Scenario Outline: Verify the persistence of page after navigating from individual record and override table
    Given I login to Saturn as an "admin" user
    When I navigate to "New Autocomplete" under Administration
    And I click on "Autocomplete Overrides" option
    And I apply both the filters and click on "Go" button
    Then I should see appropriate results in autocomplete override table
    When I paginate
    And I click on "<action_type>" value of any random record
    And I click on "cancel" button on edit override
    Then I should see that user is on the same page
    And I should see that selected filters are persisted
    Examples:
      |action_type|
      |Edit|
      |Delete|












    ###########################Story_B-99311#########################################
  @new_autocomplete @wip
  Scenario: Verify Dictionary override table header bar options
    Given I login to Saturn as an "admin" user
    When I navigate to "New Autocomplete" under Administration
    And I click on "Dictionary Overrides" option
    Then I should see dictionary override table with 7 columns
    And I should see "Dictionary Overrides" text in header bar
    And I should see "Add Override" button in header bar
    And I should see "Export" button in header bar

  @new_autocomplete @wip
  Scenario: Verify Dictionary override table column display
    Given I login to Saturn as an "admin" user
    When I navigate to "New Autocomplete" under Administration
    And I click on "Dictionary Overrides" option
    Then I should see dictionary override table with below 7 columns:
      |ID|
      |Term|
      |Created By     |
      |Creation Date  |
      |Modified By    |
      |Modified Date  |
      |Action(s)      |

  @new_autocomplete @wip
  Scenario: Verify data in each column of Dictionary override table
    Given I login to Saturn as an "admin" user
    When I navigate to "New Autocomplete" under Administration
    And I click on "Dictionary Overrides" option
    Then I should see dictionary override table with 7 columns
    And I should see that "ID" column contains "numeric" data
    And I should see that "ID" is not hyperlinked
    And I should see that "Term" column contains "alphaNumeric" data
    And I should see that "Created By" column contains "User Name" data
    And I should see that "Creation Date" column contains "Date format" data
    And I should see that "Modified By" column contains "User Name" data
    And I should see that "Modified Date" column contains "Date format" data
    And I should see that "Action(s)" column contains "Edit or Delete" data
    And I should see that "Edit" and "Delete" strings are hyperlinked

  @new_autocomplete @wip
  Scenario: Verify Dictionary override table footer Navigation bar options
    Given I login to Saturn as an "admin" user
    When I navigate to "New Autocomplete" under Administration
    And I click on "Dictionary Overrides" option
    Then I should see dictionary override table with 7 columns
    And I should see page record count in left side of footer navigation bar
    And I should see standard page navigation in right side of footer navigation bar
    And I should see pagination links are working as expected

  @new_autocomplete @wip
  Scenario: Verify sorting behavior of Dictionary override table columns
    Given I login to Saturn as an "admin" user
    When I navigate to "New Autocomplete" under Administration
    And I click on "Dictionary Overrides" option
    Then I should see dictionary override table with 7 columns
    And I should see primary sort on the table as descending by creation date
    And I should see all cloumns are sortable except "Action (s)" column
    And I should see default sort behavior for each column as below:
      |ID             |descending      |
      |Term           |alphabetical A-Z|
      |Created By     |alphabetical A-Z|
      |Creation Date  |descending      |
      |Modified By    |alphabetical A-Z|
      |Modified Date  |descending      |
