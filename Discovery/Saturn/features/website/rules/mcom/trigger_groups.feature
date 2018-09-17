@trigger_group
Feature: Trigger Groups Functional tests common to MCOM and BCOM
  As a valid Saturn user
  I want to Create/Edit/Find Trigger Groups, save and confirm that the dB contains Trigger Groups data

#######-----------  CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-11462  ------########
@csi @Saturn_Regression
Scenario: As a Merchandising Manager, I would like the default match type for Keyword Pattern trigger to be set to Has Exact Match in Trigger Group
  Given I login to Saturn as an "admin" user
  When I navigate to "Create Trigger Group" under Rules
  And I select "Keyword Pattern" trigger on Trigger Group page
  Then I see "Has EXACT Match to" as default value for Keyword Pattern Trigger on Trigger Group page

#########-------- CSI Defect: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=D-04092 ------########
@csi @Saturn_Regression
Scenario: Error message should be generated when I create and save the trigger group without saving the trigger
  Given I login to Saturn as an "admin" user
  When I navigate to "Create Trigger Group" under Rules
  And I enter Trigger Group name as "TG - Error Message"
  And I select "Keyword Pattern" trigger on Trigger Group page
  And I populate keyword pattern trigger on Trigger Group page
  And I save Trigger Group
  Then I should see error message "Save at least one trigger before saving the trigger group"

########## ------ CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-27485  ------- ##########
@csi @Saturn_Regression
Scenario: Confirm that the Result Set trigger default operator is set to '>' on Trigger Group
  Given I login to Saturn as an "admin" user
  When I navigate to "Create Trigger Group" under Rules
  And I enter Trigger Group name as "TG - Result Set"
  And I select "Result Set" trigger on Trigger Group page
  Then I see operator "  >" selected by default with "75" percent on Trigger Group page

######### ------- CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-27618  -------  #########
@csi @Saturn_Regression
Scenario: Confirm that the default operator for multiple triggers should be 'or' on Trigger Group
  Given I login to Saturn as an "admin" user
  When I navigate to "Create Trigger Group" under Rules
  And I enter Trigger Group name as "TG - Multiple Triggers"
  And I select "Keyword Pattern" trigger on Trigger Group page
  When I populate keyword pattern trigger on Trigger Group page
  And I save Trigger on Trigger Group page
#  Then I should not see multiple trigger operator box on Trigger Group page
  And I click on Add Trigger on Trigger Group page
  Then I see multiple trigger operator box between first Trigger and second trigger with operator OR selected on Trigger Group page

#######-----------  CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-28572  #######-----------
@csi @Saturn_Regression
Scenario: Confirm that Trigger and Action drop down values are listed in alphanumeric order on Trigger Group page
  Given I login to Saturn as an "admin" user
  When I navigate to "Create Trigger Group" under Rules
  And I click on Add Trigger on Trigger Group page
  Then I see "Trigger Types" in alphanumeric order on Trigger Group page
    |All Search      |
    |Category Ids    |
    |Facet Refinement|
    |Keyword Pattern |
    |Result Count    |
    |Result Set      |

  @Saturn_dry_run
  Scenario:Create a trigger group with all triggers
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Trigger Group" under Rules
    And I enter trigger group Name as below "Trigger Group with all triggers"
    When I click on Add trigger
    When I select "Category Ids" trigger on rules page
    And I create Category Ids trigger with category Id
    And I save trigger
    When I select "Facet Refinement" trigger on rules page
    And I create facet refinement trigger
    And I save trigger
    When I select "Keyword Pattern" trigger on rules page
    When I create keyword pattern trigger with the given criteria and value
    And I save trigger
    When I select "Result Count" trigger on rules page
    When I create result count trigger with "Greater Than" attribute
    When I save trigger
    When I select "Result Set" trigger on rules page
    When I create Result Set trigger
    And I save trigger
    And I save trigger group
    When I click Edit trigger group
    And I should see all the below triggers added on the rules page
      |Category Ids|
      |Facet Refinement|
      |Keyword Pattern|
      |Result Count|
      |Result Set|


  @Saturn_dry_run
  Scenario:Create a rule with trigger group which has all triggers
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Trigger Group" under Rules
    And I enter trigger group Name as below "Trigger Group with all triggers"
    When I select "Category Ids" trigger on rules page
    And I create Category Ids trigger with category Id
    And I save trigger
    When I select "Keyword Pattern" trigger on rules page
    When I create keyword pattern trigger with the given criteria and value
    And I save trigger
    When I select "Facet Refinement" trigger on rules page
    And I create facet refinement trigger
    And I save trigger
    When I select "Result Count" trigger on rules page
    And I create result count trigger with "Greater Than" attribute
    And I save trigger
    When I select "Result Set" trigger on rules page
    When I create Result Set trigger
    And I save trigger
    And I save trigger group
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "trigger group with all triggers"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I click on Add trigger
    When I select trigger group
    And I enter trigger group name under trigger
    When I save trigger
    And I save rule
    Then I should see rule details as entered Rule Name, Rule Priority, Effective and Expiration dates,fob
    When I click on  Edit rule
    And I click on  Edit trigger
    Then I should see the trigger group name as entered

  @Saturn_Regression
  Scenario:Create Trigger Group Error Validation.(user should see error message on saving triggers with out providing input data)
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Trigger Group" under Rules
    Then I should be on the Create Trigger Group page
    And I enter trigger group Name as below "Trigger Group with all triggers"
    And I click on Add trigger
    And I select "All Search" trigger on rules page
    And I save trigger
    And I click "ok" button on alert popup
    And I should see the trigger saved as "All Search"
    And I select "Category Ids" trigger on rules page
    And I try to save trigger
    Then I should see the "Category Error Message" for the "Category Id" trigger
    When I click "Ok" button on alert popup
    And I select "Facet Refinement" trigger on rules page
    And I try to save trigger
    Then I should see the "Facet Error Message" for the "Facet Refinement" trigger
    When I click "Ok" button on alert popup
    And I select "Keyword Pattern" trigger on rules page
    And I try to save trigger
    Then I should see the "Keyword Error Message" for the "Keyword Pattern" trigger
    Then I should see the "Keyword Group Error Message" for the "Keyword Pattern" trigger
    When I click "Ok" button on alert popup
    And I select "Result Count" trigger on rules page
    And I try to save trigger
    Then I should see the "Result Count Error Message" for the "Result Count" trigger
    When I click "Ok" button on alert popup
    And I select "Result Set" trigger on rules page
    And I try to save trigger
    Then I should see the "Result Set Error Message" for the "Result Set" trigger

  @Saturn_Regression
  Scenario:Add Trigger Group to a Rule and verify error validation on adding same trigger group multiple times
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Trigger Group" under Rules
    Then I should be on the Create Trigger Group page
    And I enter trigger group Name as below "All Search Trigger Group"
    And I select "All Search" trigger on rules page
    And I save trigger on trigger group page
    And I click "Ok" button on alert popup
    And I save trigger group
    Then I should see the trigger group saved
    And I should see the trigger saved as "All Search"
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Trigger Group Rule"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    When I click on Add trigger
    And I select trigger group
    And I enter the value of Trigger Group Created
    And I save trigger
    And I save rule
    Then I should see the rule saved
    When I click on  Edit rule
    And I click on  Edit trigger
    And I click on Add trigger
    And I select trigger group
    And I enter the value of Trigger Group Created
    When  I save trigger
    Then I should see the "Duplicate Error Message" trigger group error message

  @Saturn_Regression
  Scenario:Create triggers in create trigger group page and do error validation by selecting same facets from drop down.
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Trigger Group" under Rules
    When I click on Add trigger
    And I select "Keyword Pattern" trigger on rules page
    When I create keyword pattern trigger with all criteria
    When I click on "+" sign to add more criteria
    When I select the criteria as "Brand"
    Then I should see the error message for "Brand"
    When I click "Ok" button on alert popup
    When I select the criteria as "Customer Service"
    Then I should see the error message for "Customer Service"
    When I click "Ok" button on alert popup
    When I select the criteria as "Material"
    Then I should see the error message for "Material"
    When I click "Ok" button on alert popup
    When I select the criteria as "Occasion"
    Then I should see the error message for "Occasion"
    When I click "Ok" button on alert popup
    When I select the criteria as "Product Noun"
    Then I should see the error message for "Product Noun"
    When I click "Ok" button on alert popup
    When I select the criteria as "Style"
    Then I should see the error message for "Style"

  @Saturn_Regression
  Scenario:Create triggers in create trigger group page and do error validation by selecting same attributes for Result Set drop down.
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Trigger Group" under Rules
    When I click on Add trigger
    When I select "Result Set" trigger on rules page
    When I create Result Set trigger
    When I click on "+" sign to add more criteria
    When I select the attribute as "Brand" from Result Set dropdown
    Then I should see the error message for "Brand"
    When I click "Ok" button on alert popup
    When I sel?.vd hould see the error message for "Department"
    When I click "Ok" button on alert popup
    When I select the attribute as "Normal Color" from Result Set dropdown
    Then I should see the error message for "Normal Color"
    When I click "Ok" button on alert popup
    When I select the attribute as "Price Type" from Result Set dropdown
    Then I should see the error message for "Price Type"
    When I click "Ok" button on alert popup
    When I select the attribute as "Product Type" from Result Set dropdown
    Then I should see the error message for "Product Type"
    When I click "Ok" button on alert popup
    When I select the attribute as "Promo Id" from Result Set dropdown
    Then I should see the error message for "Promo Id"


    @Saturn_Regression
Scenario:Verify error validation for providing invalid value for Category Id on rules page.
  Given I login to Saturn as an "admin" user
  When I navigate to "Create Trigger Group" under Rules
#  When I click on Add trigger
  And I select "Category Ids" trigger on rules page
  And I create "Category Ids" trigger with "Invalid Category Id"
  And I save trigger
  Then I should see the "Invalid Category Id" Error Message

  @Saturn_Regression
  Scenario:Verify error validation for providing invalid value for Facet Refinement on rules page.
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Trigger Group" under Rules
#  When I click on Add trigger
    And I select "Facet Refinement" trigger on rules page
    And I create Facet Refinement trigger with "Invalid facet refinement value"
    And I save trigger
    Then I should see the "Invalid facet refinement value" Error Message

    @Saturn_Regression
  Scenario:Verify error validation for providing invalid value for Result Set on rules page.
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Trigger Group" under Rules
#  When I click on Add trigger
    And I select "Result Set" trigger on rules page
    And I create Result Set trigger with "Invalid Result Set value"
    And I save trigger
    Then I should see the "Invalid Result Set value" Error Message

  @Saturn_Regression
Scenario:Verify error validation for providing invalid value for Result Count on rules page.
  Given I login to Saturn as an "admin" user
  When I navigate to "Create Trigger Group" under Rules
#  When I click on Add trigger
  And I select "Result Count" trigger on rules page
  When I select "Greater Than" Result Count attribute
  And I create Result Count trigger with "Invalid Result Count Value"
  And I try to save trigger
  Then I should see the "Invalid Result Count" Error Message
  When I select "Greater Than" Result Count attribute
  And I create Result Count trigger with "Invalid Result Count Value"
  And I try to save trigger
  Then I should see the "Invalid Result Count" Error Message

@Saturn_Regression
Scenario:Verify Duplicate trigger group should not get created
  Given I login to Saturn as an "admin" user
  When I navigate to "Create Trigger Group" under Rules
  And I enter trigger group Name as below "Trigger Group with all triggers"
  When I click on Add trigger
  And I select "All Search" trigger on rules page
  And I save trigger on trigger group page
  And I click "Ok" button on alert popup
  And I save trigger group
  When I navigate to "Create Trigger Group" under Rules
  And I enter the same trigger Group Name
  When I click on Add trigger
  And I select "All Search" trigger on rules page
  And I save trigger on trigger group page
  And I click "Ok" button on alert popup
  And I save trigger group
  Then I should see the "Duplicate Trigger Group Error Message" error on trigger group page

  @Saturn_Regression
  Scenario:Validate error message on creating a rule with All search trigger+one all search trigger from trigger group page
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Trigger Group" under Rules
    Then I should be on the Create Trigger Group page
    And I enter trigger group Name as below "All Search Trigger Group"
    And I click on Add trigger
    And I select "All Search" trigger on rules page
    And I save trigger on trigger group page
    And I click "Ok" button on alert popup
    And I save trigger group
    Then I should see the trigger group saved
    And I should see the trigger saved as "All Search"
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Trigger Group Rule"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    When I click on Add trigger
    And I select trigger group
    And I enter the value of Trigger Group Created
    And I select "All Search" trigger on rules page
    And I save trigger
    And I click "ok" button on alert popup
    And I should see the trigger saved as "All Search"
    And I save rule
    Then I should see "Multiple Trigger" error

  @Saturn_Regression
  Scenario:verify cancel,delete and edit functionality of triggers on trigger group page
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Trigger Group" under Rules
    And I enter trigger group Name as below "Keyword pattern trigger"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I click on Add trigger
    And I select "Keyword Pattern" trigger on rules page
    When I create keyword pattern trigger with the given criteria and value
    When I save trigger
    Then I should see the trigger saved as "Keyword Pattern"
    When I click on  Edit trigger
    And I edit the keyword pattern trigger with other criteria and value
    When I click on the 'Cancel' button
    Then I should see the keyword pattern trigger with given details
    When I click on  Edit trigger
    And I edit the keyword pattern trigger with other criteria and value
    And I save trigger
    And I save rule
    Then I should see the rule saved
    Then I should see the keyword pattern trigger with given details
    When I click on  Edit rule
    And I click on  Edit trigger
    When I click on Delete button
    Then the trigger should be deleted

  @Saturn_Regression
  Scenario:Verify error message on Trigger group page for saving trigger without input data
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Trigger Group" under Rules
    And I enter trigger group Name as below "Keyword pattern trigger"
    And I click on Add trigger
    And I select "Keyword Pattern" trigger on rules page
    And I save trigger
    Then I should see the error message input data Alert
    When I create keyword pattern trigger with the given criteria and value
    And I save trigger
    And I save trigger group
    Then I should see the trigger group saved
    And I should see the keyword pattern trigger with given details

#######-----------  CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-34783  #######-----------
  @csi @Saturn_Regression
  Scenario: Confirm that Result Set Trigger drop down values are listed in alphanumeric order on Trigger Group page
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Trigger Group" under Rules
    And I select "Result Set" trigger on Trigger Group page
    Then I see "Result Set Operator Types" in alphanumeric order on Trigger Group page
      |Brand       |
      |Department  |
      |Normal Color|
      |Price Type  |
      |Product Type|
      |Promo ID    |