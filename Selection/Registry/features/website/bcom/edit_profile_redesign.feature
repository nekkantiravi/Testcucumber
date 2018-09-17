#BCOM Registry Lean Lab
#Author: Masha Malygina
#V-1 Story: B-89990

Feature: Verify functionality of new Edit Registry Profile redesign

  @B-89990 @registry @use_regression @domain_selection
  Scenario Outline: Verify error messages for all required fields on Edit Registry Page
    Given I visit the web site as a registry user without checkout
    When I navigate to Edit Registry Page
    And I delete all entries in the <section> section
    Then validate the <section> section for required validation messages

    Examples:
    | section       |
    | my account    |
    | event details |
    | address       |
    | shipping      |

  @B-89990 @registry @use_regression @domain_selection
  Scenario Outline: Verify error messages on invalid input for all required fields on Edit Registry Page
    Given I visit the web site as a registry user without checkout
    When I navigate to Edit Registry Page
    And I enter invalid values in the <section> section
    Then I validate the <section> section for error messages on invalid input

    Examples:
      | section       |
      | my account    |
      | event details |
      | address       |
      | shipping      |

  @B-89990 @registry @use_regression @domain_selection
  Scenario: Verify that user can successfully update My Account section information on the Edit Registry Profile page
    Given I visit the web site as a registry user without checkout
    And I navigate to Edit Registry Page
    When I change update values in "My Account" section
    Then I verify the changes have been saved in "My Account" section by navigating back to Edit Registry Profile page

  @B-89990 @registry @use_regression @domain_selection
  Scenario: Verify that user can successfully update Event Details section information on the Edit Registry Profile page
    Given I visit the web site as a registry user without checkout
    And I navigate to Edit Registry Page
    When I change update values in "Event Details" section
    Then I verify the changes have been saved in "Event Details" section by navigating back to Edit Registry Profile page

  @B-89990 @registry @use_regression @domain_selection
  Scenario: Verify that Event Type and Preferred Store are disabled on the Edit Registry Profile page
    Given I visit the web site as a registry user without checkout
    When I navigate to Edit Registry Page
    Then Verify that Event Type and Preferred Store are disabled on the Edit Registry Profile page

  @B-89990 @registry @use_regression @domain_selection
  Scenario: Verify that user can successfully update Address section information on the Edit Registry Profile page
    Given I visit the web site as a registry user without checkout
    And I navigate to Edit Registry Page
    When I change update values in "Address" section
    Then I verify the changes have been saved in "Address" section by navigating back to Edit Registry Profile page

  @B-89990 @registry @use_regression @domain_selection
  Scenario: Verify that user can successfully update Address section to have Partner's address be same as Mailing address on the Edit Registry Profile page
    Given I visit the web site as a registry user without checkout
    And I navigate to Edit Registry Page
    When I check "Same as Mailing Address" checkbox in the "Address" section of the Edit Registry Profile
    And I click on "Save Changes" on the Edit Registry Profile
    And I navigate to Edit Registry Page
    Then I should see that "Same as Mailing Address" checkbox is selected

  @B-89990 @registry @use_regression @domain_selection
  Scenario: Verify that user can successfully update Shipping section information on the Edit Registry Profile page
    Given I visit the web site as a registry user without checkout
    And I navigate to Edit Registry Page
    When I change update values in "Shipping" section
    Then I verify the changes have been saved in "Shipping" section by navigating back to Edit Registry Profile page

  @B-89990 @registry @use_regression @domain_selection
  Scenario: Verify that user can successfully update registry email preferences on the Edit Registry Profile
    Given I visit the web site as a registry user without checkout with registry email preferences selected
    When I navigate to Edit Registry Page
    Then I should see that "registry email preferences" checkbox is selected
    And I check "Sign up to receive registry emails" checkbox in the "Event Details" section of the Edit Registry Profile
    And I click on "Save Changes" on the Edit Registry Profile
    And I navigate to Edit Registry Page
    And I should see that "registry email preferences" checkbox is not selected





