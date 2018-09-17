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
  Then I should not see multiple trigger operator box on Trigger Group page
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
