@Saturn_linguistics
Feature: Linguistics Functional tests common to MCOM and BCOM

  @Saturn_dry_run
  Scenario:Create a dictionary
    Given I login to Saturn as an "admin" user
    When I navigate to "Linguistics" under Administration
    And I click on Linguistics "Dictionaries"
    Then I should be on the "Dictionaries" page
    When I click on Add
    Then I should see a window titled "Add Dictionary"
    When I enter the Name as "Dictionary"
    And I select Dictionary Type on Add Dictionary popup
    And I save the dictionary
    Then I should see the dictionary created

# CSI Story:  https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-87952
  @CSI @Saturn_Regression
  Scenario: Enhanced version of finding the Dictionaries for adding Relationships
    Given I login to Saturn as an "admin" user
    When I navigate to "Linguistics" under Administration
    And I click on Linguistics "Terms"
    And I click on Add
    Then I see "Add Relationship/Designation" popup
    And I see Dictionary Type dropdown with the below values in alphanumeric order
      |Brand           |
      |Color           |
      |Customer Service|
      |Gender          |
      |Material        |
      |Miscellaneous   |
      |Occasion        |
      |Product Line    |
      |Product Noun    |
      |Size            |
      |Special Size    |
      |Style           |
    When I select "Dictionary Type" on Add Relationship popup
    Then I see the dictionaries related to the selected Dictionary Type in alphanumeric order
    When I select Dictionary Name on Add Relationship popup
    And I populate "Term 1", "Term 2" and "Relationship" on Add Relationship Designation popup
    And I click Save on Add Relationship Designation popup
    Then I confirm that Term relationship added/edited

  @Saturn_Regression @priority_one
  Scenario Outline:Create a dictionaries with different type & Add/edit/delete terms with all options in dictionary detail page
    Given I login to Saturn as an "admin" user
    When I navigate to "Linguistics" under Administration
    And I click on Linguistics "Dictionaries"
    Then I should be on the "Dictionaries" page
    When I click on Add
    Then I should see a window titled "Add Dictionary"
    When I enter the Name as "Dictionary"
    And I select Dictionary Type on Add Dictionary/Terms popup
    And I save the dictionary
    Then I should see the dictionary created
    When I click on Add
    Then I see "Add Relationship/Designation" popup
    When I select the relation as "<relationship>"
    And I select "Term 1" and "Term 2" on relationship popup
    And I click Save on Add Relationship Designation popup
    Then I verify that the Relationship data is the same as entered
    When I click on Edit Relationship
    And I edit "EditTerm1" and "EditTerm2" on relationship popup
    And I click Save on Add Relationship Designation popup
    Then I verify that the Relationship data is the same as entered
    When I click on Delete RelationShip
    And I click cancel on Relationship Designation popup
    Then I verify that the Relationship data is the same as entered
    When I click on Delete RelationShip
    And I click ok on relationship popup
    Then the relationship should be deleted

    Examples:
      |relationship|
      |Do Not Associate|


  @Saturn_Regression @priority_one
  Scenario Outline:Create a dictionaries with different type & Add/edit/delete single  terms with all options in dictionary detail page
    Given I login to Saturn as an "admin" user
    When I navigate to "Linguistics" under Administration
    And I click on Linguistics "Dictionaries"
    Then I should be on the "Dictionaries" page
    When I click on Add
    Then I should see a window titled "Add Dictionary"
    When I enter the Name as "Dictionary"
    And I select Dictionary Type on Add Dictionary/Terms popup
    And I save the dictionary
    Then I should see the dictionary created
    When I click on Add
    Then I see "Add Relationship/Designation" popup
    When I select the relation as "<relationship>"
    And I select "Term 1" on relationship popup
    And I click Save on Add Relationship Designation popup
    Then I verify that the single term Relationship data is the same as entered
    When I click on Edit Relationship
    When I select the relation as "<relationship>"
    And I edit "EditTerm1" on relationship popup
    And I click Save on Add Relationship Designation popup
    Then I verify that the single term Relationship data is the same as entered
    When I click on Delete RelationShip
    And I click cancel on Relationship Designation popup
    Then I verify that the single term Relationship data is the same as entered
    When I click on Delete RelationShip
    And I click ok on relationship popup
    Then the relationship should be deleted

    Examples:
      |relationship|
      |Keep Original|


  @Saturn_Regression @priority_one
  Scenario:Verify duplicate dictionaries should not get created
    Given I login to Saturn as an "admin" user
    When I navigate to "Linguistics" under Administration
    And I click on Linguistics "Dictionaries"
    Then I should be on the "Dictionaries" page
    When I click on Add
    Then I should see a window titled "Add Dictionary"
    When I enter the Name as "Dictionary"
    And I select Dictionary Type on Add Dictionary/Terms popup
    And I save the dictionary
    Then I should see the dictionary created
    When I navigate to "Linguistics" under Administration
    And I click on Linguistics "Dictionaries"
    Then I should be on the "Dictionaries" page
    When I click on Add
    And I enter the same name of dictionary
    And I select Dictionary Type on Add Dictionary popup
    And I save the dictionary
    Then I should see the "Duplicate Dictionary" error for same name

  @Saturn_Regression @priority_one
  Scenario Outline:Verify Search for created single terms in dictionary detail page by name or filter by or both
    Given I login to Saturn as an "admin" user
    When I navigate to "Linguistics" under Administration
    And I click on Linguistics "Dictionaries"
    Then I should be on the "Dictionaries" page
    When I click on Add
    Then I should see a window titled "Add Dictionary"
    When I enter the Name as "Dictionary"
    And I select Dictionary Type on Add Dictionary/Terms popup
    And I save the dictionary
    Then I should see the dictionary created
    When I click on Add
    Then I see "Add Relationship/Designation" popup
    When I select the relation as "<relationship>"
    And I select "Term 1" on relationship popup
    And I click Save on Add Relationship Designation popup
    Then I verify that the single term Relationship data is the same as entered
    Then I should see the filter dropdown value as "All Relationships"
    And I should see the search term box with no value
    When I select the relation as "<relationship>" from Filter by
    And I enter "Term 1" in the search term box
    And I click Go on Terms page
    Then I verify that the single term Relationship data is the same as entered

    Examples:
      |relationship|
      |Keep Original|

  @Saturn_Regression @priority_one
  Scenario:Verify Search for created double terms in dictionary detail page by name or filter by or both
    Given I login to Saturn as an "admin" user
    When I navigate to "Linguistics" under Administration
    And I click on Linguistics "Dictionaries"
    Then I should be on the "Dictionaries" page
    When I click on Add
    Then I should see a window titled "Add Dictionary"
    When I enter the Name as "Dictionary"
    And I select Dictionary Type on Add Dictionary/Terms popup
    And I save the dictionary
    Then I should see the dictionary created
    When I click on Add
    Then I see "Add Relationship/Designation" popup
    When I select the relation as "One way Synonym"
    And I select "Term 1" and "Term 2" on relationship popup
    And I click Save on Add Relationship Designation popup
    Then I verify that the Relationship data is the same as entered
    And I click on Linguistics "Terms"
    Then I should be on the page with title "Terms"
    And I enter "Term 1" in the search term box
    And I click Go on Terms page
    Then I verify that the Relationship data is the same as entered

  @Saturn_Regression @priority_one
  Scenario Outline:Verify Search for created single terms on Terms page by name or filter by or both
    Given I login to Saturn as an "admin" user
    When I navigate to "Linguistics" under Administration
    And I click on Linguistics "Dictionaries"
    Then I should be on the "Dictionaries" page
    When I click on Add
    Then I should see a window titled "Add Dictionary"
    When I enter the Name as "Dictionary"
    And I select Dictionary Type on Add Dictionary/Terms popup
    And I save the dictionary
    Then I should see the dictionary created
    When I navigate to "Linguistics" under Administration
    And I click on Linguistics "Terms"
    Then I should be on the "Terms" page
    And I click on Add
    Then I see "Add Relationship/Designation" popup
    And I select the created Dictionary Type and Dictionary Name
    When I select the relation as "<relationship>"
    And I select "Term 1" and "Term 2" on relationship popup
    And I click Save on Add Relationship Designation popup
    When I select the relation as "<relationship>" from Filter by
    And I enter "Term 1" in the search term box
    And I click Go on Terms page
    Then I verify that the Term data is the same as entered

    Examples:
      |relationship|
      |Do Not Associate|


  @Saturn_Regression @priority_one
  Scenario Outline:Verify Add/edit/delete a new terms from terms page
    Given I login to Saturn as an "admin" user
    When I navigate to "Linguistics" under Administration
    And I click on Linguistics "Dictionaries"
    Then I should be on the "Dictionaries" page
    When I click on Add
    Then I should see a window titled "Add Dictionary"
    When I enter the Name as "Dictionary"
    And I select Dictionary Type on Add Dictionary/Terms popup
    And I save the dictionary
    Then I should see the dictionary created
    When I navigate to "Linguistics" under Administration
    And I click on Linguistics "Terms"
    Then I should be on the "Terms" page
    And I click on Add
    Then I see "Add Relationship/Designation" popup
    And I select the created Dictionary Type and Dictionary Name
    When I select the relation as "<relationship>"
    And I select "Term 1" and "Term 2" on relationship popup
    And I click Save on Add Relationship Designation popup
    When I select the relation as "<relationship>" from Filter by
    And I enter "Term 1" in the search term box
    And I click Go on Terms page
    Then I verify that the Term data is the same as entered
    When I click on Edit Relationship
    And I edit "EditTerm1" and "EditTerm2" on relationship popup
    And I click Save on Add Relationship Designation popup
    When I select the relation as "<relationship>" from Filter by
    And I enter "EditTerm1" in the search term box
    And I click Go on Terms page
    When I click on Delete RelationShip
    And I click cancel on Relationship Designation popup
    When I select the relation as "<relationship>" from Filter by
    And I enter "EditTerm1" in the search term box
    And I click Go on Terms page
    Then I verify that the Term data is the same as entered
    When I click on Delete RelationShip
    And I click ok on relationship popup
    Then the term data should be deleted

    Examples:
      |relationship|
      |Do Not Associate|


  @Saturn_Regression
  Scenario: As a user I am able to create hypernym relationship with various argument
    Given I login to Saturn as an "admin" user
    When I navigate to "Linguistics" under Administration
    And I click on Linguistics "Dictionaries"
    Then I should be on the "Dictionaries" page
    When I click on Add
    Then I should see a window titled "Add Dictionary"
    When I enter the Name as "Dictionary"
    And I select Dictionary Type on Add Dictionary popup
    And I save the dictionary
    Then I should see the dictionary created
    When I click on Add
    Then I see "Add Relationship/Designation" popup
    When I select the relation as "Hypernym"
    And I select "Term 1" and "Term 2" on relationship popup
    And I click Save on Add Relationship Designation popup
    Then I verify that the Relationship data is the same as entered
    And I verify that the same data is updated in DB

############ CSI Story:  https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-91624  ################
  @CSI @Saturn_Regression
  Scenario: Edit Terms Relationship/Designation by selecting different Dictionary type and dictionary
    Given I login to Saturn as an "admin" user
    When I navigate to "Linguistics" under Administration
    And I click on Linguistics "Terms"
    Then I should be on the "Terms" page
    When I click Edit next to term on Terms page
    Then I see "Edit Relationship/Designation" popup
    When I change Dictionary Type on Edit Relationship popup
    And I see Dictionary Name is not selected in Dictionary Name dropdown
    And I select Dictionary Name on Edit Relationship popup
    And I click Save on Edit Relationship Designation popup
    Then I confirm that Term relationship added/edited

  @CSI @Saturn_Regression
  Scenario: Edit Terms Relationship/Designation by selecting different Dictionary type, dictionary and term details
    Given I login to Saturn as an "admin" user
    When I navigate to "Linguistics" under Administration
    And I click on Linguistics "Terms"
    Then I should be on the "Terms" page
    When I click Edit next to term on Terms page
    Then I see "Edit Relationship/Designation" popup
    When I change Dictionary Type on Edit Relationship popup
    And I see Dictionary Name is not selected in Dictionary Name dropdown
    And I select Dictionary Name on Edit Relationship popup
    And I edit Terms and Relationship on Edit Relationship Designation popup
    And I click Save on Edit Relationship Designation popup
    Then I confirm that Term relationship added/edited

  @CSI @Saturn_Regression
  Scenario: Edit Terms Relationship/Designation by selecting new Dictionary Name for same Dictionary type
    Given I login to Saturn as an "admin" user
    When I navigate to "Linguistics" under Administration
    And I click on Linguistics "Terms"
    Then I should be on the "Terms" page
    When I click Edit next to term on Terms page
    Then I see "Edit Relationship/Designation" popup
    When I change Dictionary Name on Edit Relationship popup
    And I click Save on Edit Relationship Designation popup
    Then I confirm that Term relationship added/edited

####### Story:  https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-94567 ########################

  @Saturn_Core_Search @CSI @Saturn_Regression
  Scenario: Confirm that the new relationship Misspelling is available on Linguistics Terms Add Relationship/Designation pop-up
    Given I login to Saturn as an "admin" user
    When I navigate to "Linguistics" under Administration
    And I click on Linguistics "Terms"
    When I click on Add
    Then I see "Add Relationship/Designation" popup
    When I select Dictionary Type on Add Relationship popup
    When I select Dictionary Name on Add Relationship popup
    And I enter term value in "Term 1" field on Add Relationship popup
    And I select the relation as "Misspelling"
    And I enter term value in "Term 2" field on Add Relationship popup
    And I click Save on term Add Relationship/Designation popup
    Then I confirm that Term relationship added/edited
    And I confirm relationship is saved in the DB with Relationship type “MIS”

  @Saturn_Core_Search @CSI @Saturn_Regression
  Scenario: Confirm the same term validation alert on Linguistics Terms Add Relationship/Designation pop-up
    Given I login to Saturn as an "admin" user
    When I navigate to "Linguistics" under Administration
    And I click on Linguistics "Terms"
    When I click on Add
    Then I see "Add Relationship/Designation" popup
    When I select Dictionary Type on Add Relationship popup
    When I select Dictionary Name on Add Relationship popup
    And I enter term value in "Term 1" field on Add Relationship popup
    And I select the relation as "Misspelling"
    And I enter Term one value in Term two field on Relationship popup
    And I see the alert message on alert popup "Term1 and Term2 are not unique. Please update at least one term before saving"
    And I click Ok on Relationship popup
    And I click Save on term Add Relationship/Designation popup
    Then I confirm that Term relationship added/edited
    And I confirm relationship is saved in the DB with Relationship type “MIS”

  @Saturn_Core_Search @CSI @Saturn_Regression
  Scenario: Filtered Search - Confirm that the new relationship Misspelling is available on Linguistics Terms Edit Relationship/Designation pop-up
    Given I login to Saturn as an "admin" user
    When I navigate to "Linguistics" under Administration
    And I click on Linguistics "Terms"
    When I select the relation as "Equals" from Filter by
    And I search for "Dictionary Terms" on Terms Page
    And I click Edit next to term on Terms page
    Then I see "Edit Relationship/Designation" popup
    And I select the relation as "Misspelling"
    And I click Save on Edit Relationship Designation popup
    Then I confirm that Term relationship added/edited
    And I confirm relationship is saved in the DB with Relationship type “MIS”

  @Saturn_Core_Search @CSI @Saturn_Regression
  Scenario: Filtered Search - Confirm the same term validation alert on Linguistics Terms Edit Relationship/Designation pop-up
    Given I login to Saturn as an "admin" user
    When I navigate to "Linguistics" under Administration
    And I click on Linguistics "Terms"
    And I search for "Dictionary Terms" on Terms Page
    And I click Edit next to term on Terms page
    Then I see "Edit Relationship/Designation" popup
    And I select the relation as "Misspelling"
    And I enter Term one value in Term two field on Relationship popup
    And I see the alert message on alert popup "Term1 and Term2 are not unique. Please update at least one term before saving"
    And I click Ok on Relationship popup
    And I click Save on Edit Relationship Designation popup
    Then I confirm that Term relationship added/edited
    And I confirm relationship is saved in the DB with Relationship type “MIS”

  @Saturn_Core_Search @CSI @Saturn_Regression
  Scenario: Simple Search - Confirm that the new relationship Misspelling is available on Linguistics Terms Edit Relationship/Designation pop-up
    Given I login to Saturn as an "admin" user
    When I select the simple search option as "Dictionary Terms"
    When I enter the "Dictionary Terms" as a simple search value
    And I click Go for Simple Search
    Then I should see the search results related to "Dictionary Terms"
    When I click Edit next to term on Terms page
    Then I see "Edit Relationship/Designation" popup
    And I select the relation as "Misspelling"
    And I click Save on Edit Relationship Designation popup
    Then I confirm that Term relationship added/edited
    And I confirm relationship is saved in the DB with Relationship type “MIS”

  @Saturn_Core_Search @CSI @Saturn_Regression
  Scenario: Simple Search - Confirm the same term validation alert on Linguistics Terms Edit Relationship/Designation pop-up
    Given I login to Saturn as an "admin" user
    When I select the simple search option as "Dictionary Terms"
    When I enter the "Dictionary Terms" as a simple search value
    And I click Go for Simple Search
    Then I should see the search results related to "Dictionary Terms"
    When I click Edit next to term on Terms page
    Then I see "Edit Relationship/Designation" popup
    And I select the relation as "Misspelling"
    And I enter Term one value in Term two field on Relationship popup
    And I see the alert message on alert popup "Term1 and Term2 are not unique. Please update at least one term before saving"
    And I click Ok on Relationship popup
    And I click Save on Edit Relationship Designation popup
    Then I confirm that Term relationship added/edited
    And I confirm relationship is saved in the DB with Relationship type “MIS”

############# Story:  https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-95257

#Add Relationship/Designation from Dictionary path
  @Saturn_Core_Search @CSI @Saturn_Regression
  Scenario: Confirm that the new relationship Misspelling is available on Linguistics Dictionaries Add Relationship/Designation term pop-up
    Given I login to Saturn as an "admin" user
    When I navigate to "Linguistics" under Administration
    And I click on Linguistics "Dictionaries"
    Then I should be on the "Dictionaries" page
    When I click View Details next to any Dictionary
    Then I see "Dictionary Detail" page
    When I click on Add button on Dictionary Detail page
    Then I see "Add Relationship/Designation" popup
    And I enter term value in "Term 1" field on Add Relationship popup
    And I select the relation as "Misspelling"
    And I enter term value in "Term 2" field on Add Relationship popup
    And I click Save on term Add Relationship/Designation popup
    Then I confirm that Term relationship edited/added on Dictionary Detail page
    And I confirm relationship is saved in the DB with Relationship type “MIS”

#Add Term alert from Dictionary path
  @Saturn_Core_Search @CSI @Saturn_Regression
  Scenario: Confirm the same term validation alert on Linguistics Dictionaries Add Relationship/Designation pop-up
    Given I login to Saturn as an "admin" user
    When I navigate to "Linguistics" under Administration
    And I click on Linguistics "Dictionaries"
    Then I should be on the "Dictionaries" page
    When I click View Details next to any Dictionary
    Then I see "Dictionary Detail" page
    When I click on Add
    Then I see "Add Relationship/Designation" popup
    And I enter term value in "Term 1" field on Add Relationship popup
    And I select the relation as "Misspelling"
    And I enter Term one value in Term two field on Relationship popup
    And I see the alert message on alert popup "Term1 and Term2 are not unique. Please update at least one term before saving"
    And I click Ok on Relationship popup
    And I click Save on term Add Relationship/Designation popup
    Then I confirm that Term relationship edited/added on Dictionary Detail page
    And I confirm relationship is saved in the DB with Relationship type “MIS”

#Edit Relationship/Designation from Dictionary path
  @Saturn_Core_Search @CSI @Saturn_Regression
  Scenario: Confirm that the new relationship Misspelling is available on Linguistics Dictionaries Edit Relationship/Designation term pop-up
    Given I login to Saturn as an "admin" user
    When I navigate to "Linguistics" under Administration
    And I click on Linguistics "Dictionaries"
    Then I should be on the "Dictionaries" page
    When I click View Details next to any Dictionary
    Then I see "Dictionary Detail" page
    When I click Edit next to term on Dictionary Detail page
    Then I see "Edit Relationship/Designation" popup
    And I select the relation as "Misspelling"
    And I click Save on Edit Relationship Designation popup
    Then I confirm that Term relationship edited/added on Dictionary Detail page
    And I confirm relationship is saved in the DB with Relationship type “MIS”

#Edit Term alert from Dictionary path
  @Saturn_Core_Search @CSI @Saturn_Regression
  Scenario: Confirm the same term validation alert on Linguistics Dictionaries Edit Relationship/Designation pop-up
    Given I login to Saturn as an "admin" user
    When I navigate to "Linguistics" under Administration
    And I click on Linguistics "Dictionaries"
    Then I should be on the "Dictionaries" page
    When I click View Details next to any Dictionary
    Then I see "Dictionary Detail" page
    When I click Edit next to term on Dictionary Detail page
    Then I see "Edit Relationship/Designation" popup
    And I select the relation as "Misspelling"
    And I enter Term one value in Term two field on Relationship popup
    And I see the alert message on alert popup "Term1 and Term2 are not unique. Please update at least one term before saving"
    And I click Ok on Relationship popup
    And I click Save on Edit Relationship Designation popup
    Then I confirm that Term relationship edited/added on Dictionary Detail page
    And I confirm relationship is saved in the DB with Relationship type “MIS”
