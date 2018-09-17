# Author: Ovidiu Rucoi
# Story: SDE-450 MOBILE: Add Phone Type Input to Add customer screen
# Date Created:
# Date Signed Off:

Feature: As a user I need to be able to enter the phone Type into the Add Customer Screen

  @domain_stores @omniclient @Story_SDE-450 @bcom @MEW
  Scenario Outline: Check Phone Type drop down selection
    Given I launch the bloomingdales's omniclient page on mobile
    When I sign into OmniClient BLM mobile application as Associate
    And I click on the Search All Clients button
    Then I should see the Search All Clients page
    When I select Telephone button from the Search All Clients page
    Then I should see the Telephone Number search page
    When I input telephone number "<phoneNumber>" on the Telephone Number search page
    And I click the Search button on the Search All Clients page
    And I tap on Create Client button
    And I write the first name "First Name MCY" in the First Name field
    And I write the last name "Last Name MCY" in the Last Name Field
    And I select the Phone Type "<phoneType>" from Phone Type dropdown
    And I write the required Address "Address 1 pref"
    And I write the city in the City field "New City new"
    And I write the zip code "12345" in the Zip code field
    And I select random state from State dropdown mobile
    And I tap on Save button from Add Client page
    And I write a hint in the Hint field "new hint"
    And I select Preferred Contact method as phone from dropdown
#    And I tap on Continue button from Add to Book page
#    And I tap OK in the add confirmation popup
    And I tap Save button from Add to Book page
    And I tap OK in the Successfully Added client popup
    Then I check that Phone Type is marked as "<phoneType>"

    Examples:
      | phoneType | phoneNumber |
      | Home      | 9876598765  |
      | Mobile    | 4758695847  |
      | Work      | 4198069673  |

  @domain_stores @omniclient @Story_SDE-450 @bcom @MEW
  Scenario Outline: If MOBILE is NOT selected then Text cannot be selectable as Preferred Method of contact
    Given I launch the bloomingdales's omniclient page on mobile
    When I sign into OmniClient BLM mobile application as Associate
    And I click on the Search All Clients button
    Then I should see the Search All Clients page
    When I select Telephone button from the Search All Clients page
    Then I should see the Telephone Number search page
    When I input telephone number "<phoneNumber>" on the Telephone Number search page
    And I click the Search button on the Search All Clients page
    And I tap on Create Client button
    And I write the first name "First Name MCY" in the First Name field
    And I write the last name "Last Name MCY" in the Last Name Field
    And I select the Phone Type "<phoneType>" from Phone Type dropdown
    And I write the required Address "Address 1 pref"
    And I write the city in the City field "New City new"
    And I write the zip code "12345" in the Zip code field
    And I select random state from State dropdown mobile
    And I tap on Save button from Add Client page
    Then I should see the Text dropdown entry as  "<attribute>" "<status>" on Create Client mobile

    Examples:
      | phoneType | phoneNumber | attribute | status |
      | Home      | 9876598765  | disabled  | true   |
      | Work      | 4198069673  | disabled  | true   |

  @domain_stores @omniclient @Story_SDE-450 @bcom @MEW
  Scenario: If no phone type is selected and error message should pop up and display
    Given I launch the bloomingdales's omniclient page on mobile
    When I sign into OmniClient BLM mobile application as Associate
    And I click on the Search All Clients button
    Then I should see the Search All Clients page
    When I select Telephone button from the Search All Clients page
    Then I should see the Telephone Number search page
    When I input telephone number "9876598765" on the Telephone Number search page
    And I click the Search button on the Search All Clients page
    And I tap on Create Client button
    And I write the first name "First Name MCY" in the First Name field
    And I write the last name "Last Name MCY" in the Last Name Field
    And I write the required Address "Address 1 pref"
    And I write the city in the City field "New City new"
    And I write the zip code "12345" in the Zip code field
    And I select random state from State dropdown mobile
    And I tap on Save button from Add Client page
    Then I should see the Phone Type required error popup on the Create Client mobile



