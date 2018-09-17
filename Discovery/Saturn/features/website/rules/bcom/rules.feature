@Saturn_Rules
Feature: Rules Functional tests common to BCOM
  As a valid Saturn user
  I want to create/Edit/Copy/Find Rule, save and confirm that the dB contains Rule data

@Saturn_Regression
Scenario:Verify All Search trigger compatibility with show Master or Member Products
  Given I login to Saturn as an "admin" user
  When I navigate to "Create Rule" under Rules
  And I enter Rule Name as "All Search"
  And I enter Rule Priority, Effective and Expiration dates, FOB
  And I select "All Search" trigger on rules page
  When I save trigger
  And I click "ok" button on alert popup
  When I select "Show Master or Member Products" action on rules page
  And I select the display as "Show only the Master Products"
  When I select context action as "search"
  And I save action and click continue
  Then I should see the action saved as "Show Master or Member Products"
  When I save rule
  Then I should see the rule saved
  Then I should see rule details as entered Rule Name, Rule Priority, Effective and Expiration dates,fob


  @Saturn_Regression

########### CSI Defect: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=D-04286  #######-----------
@csi @Saturn_Regression
Scenario: Confirm an error message when a Category ID Trigger rule with category redirect action without Category ID is created and saved
  Given I login to Saturn as an "admin" user
  When I navigate to "Create Rule" under Rules
  And I enter Rule Name as "Category Redirect"
  And I enter Rule Priority, Effective and Expiration dates, FOB
  And I select "Category Ids" trigger on rules page
  And I create Category Ids trigger with category Id
  And I save trigger
  And I select "Category Redirect" action on rules page
  And I save action
  Then I confirm an error message "Category ID is missing."

#######-----------  CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-26040  #######-----------
@csi @Saturn_Regression
Scenario: Confirm that Category Redirect Action is enabled for rule with Keyword Pattern and Facet refinement Triggers
  Given I login to Saturn as an "admin" user
  When I navigate to "Create Rule" under Rules
  And I enter Rule Name as "Category Redirect Enabled"
  And I enter Rule Priority, Effective and Expiration dates, FOB
  And I select "Keyword Pattern" trigger on rules page
  When I create keyword pattern trigger with the given criteria and value
  And I save trigger
  And I select "Facet Refinement" trigger on rules page
  And I create facet refinement trigger
  And I save trigger
  When I click on Add action
  Then I confirm "Category Redirect" is enabled on rules page
  And I select "Category Redirect" action on Create Rule page
  And I create "Category Redirect" action with "Category Id"
  And I select context action as "search"
  And I save action and click continue
  And I save rule
  Then I should see the rule saved

########### CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-26704  #######-----------
Scenario: As a Site Merch Manager, when I create a new rule in Saturn, I should see a default value "90" selected in rule priority field
  Given I login to Saturn as an "admin" user
  When I navigate to "Create Rule" under Rules
  Then I should see "90" selected in Create RuleRule Priority field

############## CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-86459   #######-----------
############## CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-90280   #######-----------
@csi @Saturn_Regression
Scenario: Confirm Modify Result Set Operator enabled and disabled values with Category Id Trigger in Create Rule page
  Given I login to Saturn as an "admin" user
  When I navigate to "Create Rule" under Rules
  And I enter Rule Name as "Modify Result set Operator Disabled"
  And I enter Rule Priority, Effective and Expiration dates, FOB
  And I select "Category Ids" trigger on rules page
  And I create Category Ids trigger with category Id
  And I save trigger
  And I select "Modify Result Set" action on rules page
  Then I confirm below Modify Result Set Operator types are disabled
    |Add|
  Then I confirm below Modify Result Set Operator types are enabled
    |Remove                 |
    |Replace                |
    |Boost                  |
    |Boost Attribute Values |

#######-----------  CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-28572  #######-----------
@csi @Saturn_Regression
Scenario: Confirm that Trigger drop down values are listed in alphanumeric order in Create Rule page
  Given I login to Saturn as an "admin" user
  When I navigate to "Create Rule" under Rules
  And I click on Add trigger
  Then I see "Trigger Types" in alphanumeric order on Rule page
    |All Search      |
    |Category Ids    |
    |Facet Refinement|
    |Keyword Pattern |
    |Result Count    |
    |Result Set      |

#######-----------  CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-34783  #######-----------
@csi @Saturn_Regression
Scenario: Confirm that Result Set Trigger drop down values are listed in alphanumeric order in Create Rule page
  Given I login to Saturn as an "admin" user
  When I navigate to "Create Rule" under Rules
  And I select "Result Set" trigger on rules page
  Then I see "Result Set Operator Types" in alphanumeric order on Rule page
    |Brand       |
    |Department  |
    |Normal Color|
    |Price Type  |
    |Product Noun|
    |Product Type|
    |Promo ID    |

##########################################################################################

@Saturn_Regression
Scenario:Verify Category Ids compatibility with actions
  Given I login to Saturn as an "admin" user
  When I navigate to "Create Rule" under Rules
  And I enter Rule Name as "Category Ids  Compatibility"
  And I enter Rule Priority, Effective and Expiration dates, FOB
  And I select "Category Ids" trigger on rules page
  When I click on Add action
  Then I should see the below options enabled under actions dropdown for "Category Ids"
  |Category Redirect|
  |Manage Facets|
  |Manage Featured Facet|
  |Modify Result Set|
  |RDPP Algorithm|
  |Show Master or Member Products|
  |Show Media|
  |URL Redirect|

  #######-----------  CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-28610  #######-----------
@csi @Saturn_Regression
Scenario: Confirm that Action drop down values are listed in alphanumeric order in Create Rule page
  Given I login to Saturn as an "admin" user
  When I navigate to "Create Rule" under Rules
  When I click on Add action
  Then I see "Action Types" in alphanumeric order on Rule page
    |Category Redirect              |
    |Display Message                |
    |Execute New Search             |
    |Manage Facets                  |
    |Manage Featured Facet          |
    |Modify Result Set              |
    |PDP Redirect                   |
    |RDPP Algorithm                 |
    |Show Master or Member Products |
    |Show Media                     |
    |URL Redirect                   |
##########################################################################################

  @Saturn_Regression
  Scenario:Verify All Facet Refinement compatibility with actions
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Facet Refinement Compatibility"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Facet Refinement" trigger on rules page
    When I click on Add action
    Then I should see the below options enabled under actions dropdown for "Facet Refinement"
     |Category Redirect|
      |Display Message|
      |Manage Facets|
     |Modify Result Set|
      |RDPP Algorithm|
     |Show Master or Member Products |
     |Show Media                     |

  @Saturn_Regression @Saturn_Top_priority
  Scenario:Verify Result Set compatibility with actions
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Result Set Compatibility"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Result Set" trigger on rules page
    When I click on Add action
    Then I should see the below options enabled under actions dropdown for "Result Set"
      |Display Message|
      |Manage Facets|
      |Manage Featured Facet|
      |Modify Result Set|
      |RDPP Algorithm|
      |Show Master or Member Products |
      |Show Media|