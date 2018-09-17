@Saturn_Rules
Feature: Rules Functional tests to validate error messages common to MCOM and BCOM
  As a valid Saturn user
  I want to validate rules required fields and error messages

@Saturn_Regression   @priority_two
Scenario: Verify error message on creating rule without rule name
  Given I login to Saturn as an "admin" user
  When I navigate to "Create Rule" under Rules
  And I enter Rule Priority, Effective and Expiration dates, FOB
  And I select "Keyword Pattern" trigger on rules page
  When I create keyword pattern trigger with the given criteria and value
  When I save trigger
  And I save rule
  Then I should see the rule alert as "Rule name is required"

@Saturn_Regression  @priority_two
Scenario: Verify error message on creating rule without start date
  Given I login to Saturn as an "admin" user
  When I navigate to "Create Rule" under Rules
  And I enter Rule Name as "Skip Effective date"
  And I enter Rule Priority, Effective and Expiration dates, FOB
  And I edit the "Effective Date" as ""
  And I select "Keyword Pattern" trigger on rules page
  When I create keyword pattern trigger with the given criteria and value
  When I save trigger
  And I save rule
  Then I should see the rule alert as "Effective date is required"

@Saturn_Regression   @priority_two
Scenario: Verify error message on creating rule without end date
  Given I login to Saturn as an "admin" user
  When I navigate to "Create Rule" under Rules
  And I enter Rule Name as "Skip Effective date"
  And I enter Rule Priority, Effective and Expiration dates, FOB
  And I edit the "Expiry Date" as ""
  And I select "Keyword Pattern" trigger on rules page
  When I create keyword pattern trigger with the given criteria and value
  When I save trigger
  And I save rule
  Then I should see the rule alert as "Expiration date is required"

@Saturn_Regression @priority_two
Scenario: Validate error message on giving end date prior to start date
  Given I login to Saturn as an "admin" user
  When I navigate to "Create Rule" under Rules
  And I enter Rule Name as "Skip Effective date"
  And I enter Rule Priority, Effective and Expiration dates, FOB
  And I edit the "Expiry Date" as "Prior Date"
  And I select "Keyword Pattern" trigger on rules page
  When I create keyword pattern trigger with the given criteria and value
  When I save trigger
  And I save rule
  Then I should see the rule alert as "Expiration date cannot be prior to effective date"


@Saturn_Regression @priority_two
Scenario Outline: Verify message on entering incorrect date format
  Given I login to Saturn as an "admin" user
  When I navigate to "Create Rule" under Rules
  And I enter Rule Name as "Date validation"
  And I enter Rule Priority, Effective and Expiration dates, FOB
  And I edit the "<date_type>" as "Invalid Format Date"
  And I select "Keyword Pattern" trigger on rules page
  And I create keyword pattern trigger with the given criteria and value
  And I save trigger
  And I save rule
  Then I should see the rule alert as "<error_message>"

  Examples:
    |date_type      |error_message                                    |
    |Effective Date |Please enter effective date in mm/dd/yyyy format |
    |Expiry Date    |Please enter expiration date in mm/dd/yyyy format|

@Saturn_Regression   @priority_two
Scenario Outline:Verify Rdpp error message on creating rdpp action without effective date, expiration date and Rdpp value.
  Given I login to Saturn as an "admin" user
  When I navigate to "Create Rule" under Rules
  And I enter Rule Name as "Rdpp Action"
  And I enter Rule Priority, Effective and Expiration dates, FOB
  And I select "Keyword Pattern" trigger on rules page
  When I create keyword pattern trigger with the given criteria and value
  When I save trigger
  And I select "RDPP Algorithm" action on rules page
  And I create rdpp with random algorithm action value
  And I save action on rules page
  Then I should get the "<param>" Alert

  Examples:
    |param|
    |Effective Date|
    |Expiration Date|


  @Saturn_Regression   @priority_two
  Scenario:Verify Rdpp error message on creating rdpp action without Rdpp value.
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Rdpp Action"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Keyword Pattern" trigger on rules page
    When I create keyword pattern trigger with the given criteria and value
    When I save trigger
    And I select "RDPP Algorithm" action on rules page
    And I enter rdpp algorithm Effective and Expiration dates
    And I save action on rules page
    Then I should get the "RDPP Algorithm" Alert

@Saturn_Regression   @priority_two
Scenario: Validate error message on Result Set trigger rule without percentage
  Given I login to Saturn as an "admin" user
  When I navigate to "Create Rule" under Rules
  And I enter Rule Name as "Result set trigger"
  And I enter Rule Priority, Effective and Expiration dates, FOB
  And I select "Result Set" trigger on rules page
  Then I should see criteria in the Result set dropdown
  When I create Result Set trigger with single criteria
  And I clear the percentage value and save trigger
  Then I should see the "Percentage" for the "Result Set" trigger

  @Saturn_Regression  @priority_two
  Scenario:Validate error message on entering duplicate saved set ids and saved query ids
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Modify Result Set Multiple Boosts"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Keyword Pattern" trigger on rules page
    When I create keyword pattern trigger with the given criteria and value
    When I save trigger
    Then I should see the trigger saved as "Keyword Pattern"
    When I again select "Modify Result Set" action on rules page
    And I select the modify result set with operator as "Boost"
    And I create Modify Result Set with required data
    When I select context action as "browse"
    And I save action and click continue
    And I save rule
    Then I should see the rule saved
    When I click on  Edit rule
    And I click on Edit Action
    And I again enter the same "Saved Set Id Saved Query Id" for Modify Result
    And I save action
    And I click continue
    Then I should see the "Duplicate Saved Set Id Saved Query Id" error on rules page


  @Saturn_Regression  @priority_two
Scenario:Validate error message on entering invalid numeric saved set ids and saved query ids
  Given I login to Saturn as an "admin" user
  When I navigate to "Create Rule" under Rules
  And I enter Rule Name as "Modify Result Set Multiple Boosts"
  And I enter Rule Priority, Effective and Expiration dates, FOB
  And I select "Keyword Pattern" trigger on rules page
  When I create keyword pattern trigger with the given criteria and value
  When I save trigger
  Then I should see the trigger saved as "Keyword Pattern"
  When I again select "Modify Result Set" action on rules page
  And I select the modify result set with operator as "Boost"
  And I create Modify Result Set with required data

@Saturn_Regression  @priority_two
Scenario:Validate for message for invalid product id
  Given I login to Saturn as an "admin" user
  When I navigate to "Create Rule" under Rules
  And I enter Rule Name as "invalid product id"
  And I enter Rule Priority, Effective and Expiration dates, FOB
  And I select "Keyword Pattern" trigger on rules page
  When I create keyword pattern trigger with all criteria
  When I save trigger
  When I select "PDP Redirect" action on rules page
  And I enter "Invalid" PPID
  When I select context action as "browse"
  Then I save action and click continue
  Then I should see the Rule error as "Invalid product id"

@Saturn_Regression @priority_two
Scenario:Validate for error for incorrect url redirect
  Given I login to Saturn as an "admin" user
  When I navigate to "Create Rule" under Rules
  And I enter Rule Name as "Url redirect"
  And I enter Rule Priority, Effective and Expiration dates, FOB
  And I select "Keyword Pattern" trigger on rules page
  When I create keyword pattern trigger with the given criteria and value
  When I save trigger
  When I select "URL Redirect" action on rules page
  And I enter "invalid url" for "URL Redirect"
  When I select context action as "browse"
  Then I save action and click continue
  Then I should see the Rule error as "Url validation failed"

@Saturn_Regression @priority_two
Scenario: verify error message on entering duplicate trigger values
  Given I login to Saturn as an "admin" user
  When I navigate to "Create Rule" under Rules
  And I enter Rule Name as "Duplicate trigger criteria"
  And I enter Rule Priority, Effective and Expiration dates, FOB
  And I select "Result Set" trigger on rules page
  And I select "Brand" with facet value as "Guess"
  And I save trigger
  And I click on  Edit trigger
  And I click on add more criteria
  And I select "Brand" with facet value as "Guess"
  Then I should see popup alert as "Duplicate Brand Error"

@Saturn_Regression @priority_two
Scenario:Change state of rule from 'expired to effective' and 'effective to expired'
  Given I login to Saturn as an "admin" user
  When I navigate to "Create Rule" under Rules
  And I enter Rule Name as "Date validation"
  And I enter Rule Priority, FOB
  And I edit the "Effective Date" as "Expired Date"
  And I edit the "Expiry Date" as "Prior Date"
  And I select "Keyword Pattern" trigger on rules page
  Then I should see criteria in the Keyword pattern dropdown
  When I create keyword pattern trigger with all criteria
  When I save trigger
  And I save rule
  Then I should see the rule saved
  And I should see the rule status as "Expired"
  When I click on  Edit rule
  And I edit the "Expiry Date" as "Future Date"
  And I save rule details page
  Then I should see the rule saved
  And I should see the rule status as "Effective"
  When I click on  Edit rule
  And I edit the "Expiry Date" as "Prior Date"
  And I save rule details page
  And I should see the rule status as "Expired"

  @Saturn_Regression
  Scenario:Verify Duplicate rule should not get created
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Duplicate Rule Name"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "All Search" trigger on rules page
    And I save trigger
    And I click "Ok" button on alert popup
    And I save rule
    And I navigate to "Create Rule" under Rules
    And I enter the same rule name
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select the same "All Search" trigger on rules page
    And I save trigger
    And I click "Ok" button on alert popup
    And I save rule
    Then I should see the "Duplicate Rule Message" error on rules page

  @Saturn_Regression
  Scenario:Verify validation for multiple All search trigger
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "All Search"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "All Search" trigger on rules page
    When I save trigger
    And I click "ok" button on alert popup
    Then I should see the trigger saved as "All Search"
    And I select "All Search" trigger on rules page
    When I save trigger
    And I click "ok" button on alert popup
    Then I should see the trigger saved as "All Search"
    When I save rule
    Then I should see "Multiple Trigger" error

############ CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-99990 ###########
  @csi
  Scenario Outline: Confirm the alert when Check All Display Header checkbox is unselected on Manage Facet action value sequence popup on Create Rule page
    Given I login to Saturn as an "non_admin" user
    When I navigate to "Create Rule" under Rules
    And I select "Manage Facets" action on rules page
    And I select the below values from Manage Facets list
      |Age Group|
    Then I should see Value Management column in select facet display
    And I should see the Value Management column value as "Alphanumeric"
    When I click on Edit on Value Management column
    Then I should be on the window titled "Manage Facet Values"
    And I select the "<Value Sequence>"
    When I uncheck the Check All checkbox on Manage Facet value management popup
    Then I confirm all Display checkboxes associated to the facets are unselected
    When I click Save on Manage Facet value management popup
    Then I should see the rule popup alert as "* To save, at least one facet value must be selected for Display"

    Examples:
      |Value Sequence |
      |Alphanumeric   |
      |Manual         |
      |Product Count  |
      |Sitewide-Stella|

  @csi
  Scenario: Confirm the alert when Check All Display Header checkbox is unselected on Manage Facet action value sequence popup on Edit Rule page
    Given I login to Saturn as an "non_admin" user
    When I query Saturn DB for Manage Facets rule
    When I select "Manage Facets" action on rules page
    And I select the below values from Manage Facets list
      |Age Group|