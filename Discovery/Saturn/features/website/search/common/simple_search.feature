Feature: Simple Search functionality tests

  As a valid Saturn user
  I want to Simple Search functionality for Rules and Dictionaries

########    CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-35566       #######
########    CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-26996       #######
########    CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-51287       #######

@Saturn_dry_run @Saturn_Regression
Scenario:Verify global search list and global search input available on Home page
  Given I login to Saturn as an "non_admin" user
  Then I should see global search tab on the home page
  Then I see the Simple Search values in alphanumeric order
    |Category ID     |
    |Dictionary Names|
    |Dictionary Terms|
    |FOB             |
    |Rule Description|
    |Rule ID         |
    |Rule Names      |
  And I should see the default option selected as "Rule ID" in the global search list

########    CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-54902       #######
@Saturn_dry_run @Saturn_Regression
Scenario:Verify simple search functionality for Rule Id
  Given I login to Saturn as an "non_admin" user
  Then I should see the default option selected as "Rule ID" in the global search list
  And I select the simple search option as "Rule ID"
  When I enter the "Single Rule ID" as "Rule ID" simple search value
  And I click Go for Simple Search
  Then I should be navigated to rules page
  And I should see that the "Rule Id" on the rules page is the same as entered

########    CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-28526       #######
########    CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-36683      #######
@Saturn_dry_run @Saturn_Regression
Scenario:Verify simple search functionality for Single Category Id
  Given I login to Saturn as an "non_admin" user
  And I select the simple search option as "Category ID"
  When I enter the "Single Category ID" as "Category ID" simple search value
  And I click Go for Simple Search
  Then I see Rules results with "Single Category ID" search
  And I click on first rule Id link on the search results page
  Then I should be navigated to rules page
  When I click on  Edit rule
  And I click on  Edit trigger
  Then I should see that the "category Id" on the rules page is the same as entered

@Saturn_dry_run @Saturn_Regression
Scenario:Verify simple search functionality for Dictionary Name
  Given I login to Saturn as an "admin" user
  And I select the simple search option as "Dictionary Names"
  When I enter the "Dictionary Names" as a simple search value
  And I click Go for Simple Search
  Then I should see the search results related to "Dictionary Names"

@Saturn_dry_run @Saturn_Regression
Scenario:Verify simple search functionality for Rule Name
  Given I login to Saturn as an "non_admin" user
  And I select the simple search option as "Rule Names"
  When I enter the "Rule Name" as a simple search value
  And I click Go for Simple Search
  Then I should see the search results related to "Rule Name"

@Saturn_dry_run @Saturn_Regression
Scenario:Verify simple search functionality for Dictionary Terms
  Given I login to Saturn as an "admin" user
  And I select the simple search option as "Dictionary Terms"
  When I enter the "Dictionary Terms" as a simple search value
  And I click Go for Simple Search
  Then I should see the search results related to "Dictionary Terms"

@Saturn_dry_run @Saturn_Regression
Scenario:Verify simple search functionality for FOB
  Given I login to Saturn as an "non_admin" user
  And I select the simple search option as "FOB"
  When I select FOB value for simple search
  And I click Go for Simple Search
  Then I should see the search results related to "FOB"

############ CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-86551 ##############
@Saturn_dry_run @Saturn_Regression
Scenario:Verify simple search functionality for Rule Description
  Given I login to Saturn as an "non_admin" user
  And I select the simple search option as "Rule Description"
  When I enter the "Rule Description" as a simple search value
  And I click Go for Simple Search
  And I click on first rule Id link on the search results page
  Then I should be navigated to rules page
  When I click on  Edit rule
  Then I should see that the "Rule Description" on the rules page is the same as entered
########################################################

@Saturn_Regression
Scenario:verify message on search rule with invalid name
  Given I login to Saturn as an "non_admin" user
  And I select the simple search option as "Rule Names"
  When I enter the "Invalid Rule Name" as a simple search value
  And I should see "Invalid Rule Name" message

############ CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-92815 ##################
@csi @Saturn_Regression
Scenario Outline: Simple Search for rules using multiple Category IDs
  Given I login to Saturn as an "non_admin" user
  When I select the simple search option as "Category ID"
  And I enter the "<input_type>" as "Category ID" simple search value
  And I click Go for Simple Search
  Then I see Rules results with "<input_type>" search
  And I click on first rule Id link on the search results page
  Then I should be navigated to rules page
  When I click on  Edit rule
  And I click on  Edit trigger
  Then I should see that the "category Id" on the rules page is the same as entered

  Examples:
    |input_type					             |
    |Multiple Category IDs separated by Comma|
    |Multiple Category IDs separated by Space|

@csi @Saturn_Regression
Scenario Outline: Confirm error validation for non-numeric value in Category ID search field
  Given I login to Saturn as an "non_admin" user
  And I select the simple search option as "Category ID"
  And I enter the "<input_type>" as "Category ID" simple search value
  And I click Go for Simple Search
  Then I see Simple Search alert "Please provide a numeric value for Category ID search"

  Examples:
    |input_type		               |
    |Letters                       |
    |Special Characters            |
    |Letters and Special Characters|

######## CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-92814 #################
@csi @Saturn_Regression
Scenario Outline: Simple Search for rules using multiple Rule IDs
  Given I login to Saturn as an "non_admin" user
  When I select the simple search option as "Rule ID"
  And I enter the "<input_type>" as "Rule ID" simple search value
  And I click Go for Simple Search
  Then search results in Rules List view should display the Rules
  And I click on first rule Id link on the search results page
  Then I should be navigated to rules page
  And I should see that the "Rule Id" on the rules page is the same as entered

  Examples:
    |input_type						   |
    |Multiple Rule IDs separated by Comma|
    |Multiple Rule IDs separated by Space|

@csi @Saturn_Regression
Scenario Outline: Confirm error validation for non-numeric value in Rule ID search field
  Given I login to Saturn as an "non_admin" user
  And I select the simple search option as "Rule ID"
  And I enter the "<input_type>" as "Rule ID" simple search value
  And I click Go for Simple Search
  Then I see Simple Search alert "Please provide a numeric value for Rule ID search"

  Examples:
    |input_type		               |
    |Letters                       |
    |Special Characters            |
    |Letters and Special Characters|
