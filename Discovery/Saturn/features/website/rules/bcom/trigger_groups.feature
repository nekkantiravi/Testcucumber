@trigger_group
Feature: Trigger Groups Functional tests on BCOM
  As a valid Saturn user
  I want to Create/Edit/Find Trigger Groups, save and confirm that the dB contains Trigger Groups data

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
  |Product Noun|
  |Product Type|
  |Promo ID    |