@Saturn_Rules
Feature: Rules Functional tests common to MCOM
  As a valid Saturn user
  I want to create/Edit/Copy/Find Rule, save and confirm that the dB contains Rule data

@Saturn_Regression
Scenario:Verify All Search trigger compatibility with show Master or Member Products
  Given I login to Saturn as an "admin" user
  When I navigate to "Create Rule" under Rules
  And I enter Rule Name as "All Search"
  And I enter Rule Priority, Effective and Expiration dates, FOB
  And I select "All Search" trigger on rules page
  When I click on Add action
  Then I should see the below option is disabled for All Search
    |Show Master or Member Products|
  And I save trigger
  And I click "Ok" button on alert popup
  Then I should see the below option is disabled for All Search
    |Show Master or Member Products|
  When I click on  Edit trigger
  Then I should see the below option is disabled for All Search
    |Show Master or Member Products|

@Saturn_Regression
Scenario:Create a rule with Result set Trigger + Modify result set action Boost Attribute
  Given I login to Saturn as an "admin" user
  When I navigate to "Create Rule" under Rules
  And I enter Rule Name as "Result set trigger"
  And I enter Rule Priority, Effective and Expiration dates, FOB
  And I select "Result Set" trigger on rules page
  Then I should see criteria in the Result set dropdown
  When I create Result Set trigger
  When I save trigger
  Then I should see the trigger saved as "Result Set"
  When I select "Modify Result Set" action on rules page
  And I select the modify result set with operator as "Boost Attribute Values"
  And I create "Modify Result Set" with Boost Attribute
  When I select context action as "search"
  And I save action and click continue
  Then I should see the action saved as "Modify Result Set"
  And I save rule
  Then I should see the rule saved
  Then I should see rule details as entered Rule Name, Rule Priority, Effective and Expiration dates,fob
  Then I should see modify result set operator with operator as "Boost Attribute Values"
  And I should see the Boost Attribute value as selected

@Saturn_Must_Pass @Mcom_Specific
Scenario: create a rule with show media banners option with media type as Banners
  Given I login to Saturn as an "admin" user
  When I navigate to "Create Rule" under Rules
  And I enter Rule Name as "Show Media"
  And I enter Rule Priority, Effective and Expiration dates, FOB
  When I select "Facet Refinement" trigger on rules page
  And I create facet refinement trigger
  And I save trigger
  Then I should see the trigger saved as "Facet Refinement"
  When I select "Show Media" action on rules page
  And I select "Banners" as media type
  And I create show media with Media Group Id
  When I select the show media operator with location as
    |KWS_BOTTOM_BANNER|
  When I select context action as "search"
  And I save action and click continue
  And I save rule
  Then I should see the rule saved
  Then I should see rule details as entered Rule Name, Rule Priority, Effective and Expiration dates,fob
  And I should see media type as "Banners"
  Then I should see Show Media with Banners Id as entered

########### CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-26704  #######-----------
@csi @Saturn_Regression
Scenario: As a Site Merch Manager, when I create a new rule in Saturn, I should no longer see a default value selected in rule priority field
  Given I login to Saturn as an "admin" user
  When I navigate to "Create Rule" under Rules
  Then I should see "no value" selected in Create RuleRule Priority field

############## CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-51465   #######-----------
############## CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-86459   #######-----------
############## CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-86831   #######-----------
@csi @Saturn_Regression
Scenario: Confirm Modify Result Set Operator enabled and disabled values with Category Id Trigger in Create Rule page
  Given I login to Saturn as an "admin" user
  When I navigate to "Create Rule" under Rules
  And I enter Rule Name as "Modify Result set Operator Disabled"
  And I enter Rule Priority, Effective and Expiration dates, FOB
  And I select "Category Ids" trigger on rules page
  And I create Category Ids trigger with category Id
  And I save trigger
  And I click on Add action
  And I select "Modify Result Set" action on Create Rule page
  Then I confirm below Modify Result Set Operator types are disabled
    |Add     |
    |Remove  |
    |Replace |
  Then I confirm below Modify Result Set Operator types are enabled
    |Boost                  |
    |Boost Attribute Values |

#######-----------  CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-26767  #######-----------
@csi @Saturn_Regression
Scenario: Confirm that All Search trigger can be combined with Category ID trigger in a rule
  Given I login to Saturn as an "admin" user
  When I navigate to "Create Rule" under Rules
  And I enter Rule Name as "All Search with Category Ids"
  And I enter Rule Priority, Effective and Expiration dates, FOB
  And I select "All Search" trigger on rules page
  And I save trigger
  And I click "ok" button on alert popup
  And I select "Category Ids" trigger on rules page
  And I create Category Ids trigger with category Id
  And I save trigger
  When I select "RDPP Algorithm" action on rules page
  And I create rdpp with random algorithm action value
  And I enter rdpp algorithm Effective and Expiration dates
  When I select context action as "browse"
  And I save action and click continue
  And I save rule
  Then I should see the rule saved

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
    |Literal Match   |
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
    |Product Type|
    |Promo ID    |

##################################################################################

@Saturn_Regression
Scenario:Verify Category Ids compatibility with actions
  Given I login to Saturn as an "admin" user
  When I navigate to "Create Rule" under Rules
  And I enter Rule Name as "Category Ids  Compatibility"
  And I enter Rule Priority, Effective and Expiration dates, FOB
  And I select "Category Ids" trigger on rules page
  When I click on Add action
  Then I should see the below options enabled under actions dropdown for "Category Ids"
    |Manage Facets|
    |Manage Featured Facet|
    |Modify Result Set|
    |RDPP Algorithm|
    |Show Media|


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
    |Index/Follow                   |
    |Manage Facets                  |
    |Manage Featured Facet          |
    |Modify Result Set              |
    |PDP Redirect                   |
    |RDPP Algorithm                 |
    |Show Master or Member Products |
    |Show Media                     |
    |URL Redirect                   |


  @Saturn_Regression
  Scenario:Verify All Facet Refinement compatibility with actions
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Facet Refinement Compatibility"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Facet Refinement" trigger on rules page
    When I click on Add action
    Then I should see the below options enabled under actions dropdown for "Facet Refinement"
      |Display Message|
      |Manage Facets|
      |RDPP Algorithm|
      |Show Media|

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
      |Show Media|

  @Saturn_Regression
  Scenario:Create a rule with multiple show media action with banner option with same location with same context and validate error
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Show Media"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Keyword Pattern" trigger on rules page
    When I create keyword pattern trigger with the given criteria and value
    And I save trigger
    When I again select "Show Media" action on rules page
    And I select "Banners" as media type
    And I create show media with Media Group Id
    When I select the show media operator with location as
      |KWS_BOTTOM_BANNER|
    When I select context action as "search"
    And I save action and click continue
    Then I should see the action saved as "Show Media"
    When I again select "Show Media" action on rules page
    And I select "Banners" as media type
    And I create show media with Media Group Id
    When I select the show media operator with location as
      |KWS_BOTTOM_BANNER|
    And I click on context
    And I click save action
    Then I should see "Context Alert" with "Same Context"
    And I click "OK" button on same context alert popup
    And I de select all the context options selected by default
    And I select "TABLET" as context
    When I de select the navigation context "search"
    When I select context action as "browse"
    And I uncheck the apply non canvas option
    And I save action on rules page
    Then I should see the action saved as "Show Media"
    When I save rule
    Then I should see the rule saved
    Then I should see rule details as entered Rule Name, Rule Priority, Effective and Expiration dates,fob
    And I should see media type as "Banners"
    Then I should see Show Media with Banners Id as entered

  @Saturn_Must_Pass @Show_media_Canvas
  Scenario: create a rule with show media with canvas
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Show Media"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Result Set" trigger on rules page
    Then I should see criteria in the Result set dropdown
    When I create Result Set trigger
    When I save trigger
    Then I should see the trigger saved as "Result Set"
    When I select "Show Media" action on rules page
    And I select "Canvas" as media type
    And I create show media with "Canvas Id"
    And I save action on rules page
    And I save rule
    Then I should see the rule saved
    Then I should see rule details as entered Rule Name, Rule Priority, Effective and Expiration dates,fob
    And I should see the action saved as "Show Media"
    When I click on  Edit rule
    And I click on Edit Action
    Then I should see Show Media with "Canvas Id" as entered

  @Saturn_Must_Pass @pdp_redirect_test
  Scenario: create a rule with  pdp redirect action
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "PDP Redirect"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Keyword Pattern" trigger on rules page
    When I create keyword pattern trigger with the given criteria and value
    And I save trigger
    Then I should see the trigger saved as "Keyword Pattern"
    When I select "PDP Redirect" action on rules page
    And I create PDP Redirect action with first PPID value
    When I select context action as "browse"
    And I save action
    And I click continue
    And I should see the action saved as "PDP Redirect"
    And I save rule
    When I click on  Edit rule
    And I click on Edit Action
    And I verify the value of PPID
    And I create PDP Redirect action with second PPID value
    And I click on 'Cancel' action
    And I click on Edit Action
    And I verify the value of PPID
    And I save action and click continue
    When I save rule details page
    Then I should see the rule saved
    Then I should see rule details as entered Rule Name, Rule Priority, Effective and Expiration dates,fob

  @Saturn_Regression
  Scenario:Create a rule with any redirect action+any other action with same context and then different context
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Category Redirect"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Keyword Pattern" trigger on rules page
    When I create keyword pattern trigger with the given criteria and value
    And I save trigger
    Then I should see the trigger saved as "Keyword Pattern"
    When I again select "Category Redirect" action on rules page
    When I create "Category Redirect" action with "Category Id"
    When I de select all the context options selected by default
    And I select "DESKTOP" as context
    When I select context action as "search"
    And I save action and click continue
    Then I should see the action saved as "Category Redirect"
    When I again select "RDPP Algorithm" action on rules page
    And I create rdpp with random algorithm action value
    And I enter rdpp algorithm Effective and Expiration dates
    And I click on context
    When I de select the context option "DESKTOP"
    And I select "DESKTOP" as context
    When I select context action as "browse"
    And I click save action
    Then I should see "Context Alert" with "Same Context"
    And I click "OK" button on same context alert popup
    When I de select the context option "DESKTOP"
    And I select "TABLET" as context
    And I uncheck the apply non canvas option
    And I again save action
    Then I should see the action saved as "RDPP Algorithm" on Rules Page
    Then I should see the rule saved
    Then I should see rule details as entered Rule Name, Rule Priority, Effective and Expiration dates,fob
    Then I should see that "Category Redirect" has "Category Id"  same as entered
    Then I should see rdpp algorithm as entered


  @Saturn_Regression
  Scenario:Create a rule with multiple show media action with Zero Results Media option with same location with same context and validate error
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Show Media"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Keyword Pattern" trigger on rules page
    When I create keyword pattern trigger with the given criteria and value
    And I save trigger
    When I again select "Show Media" action on rules page
    And I select "Zero Results Media" as media type
    And I create show media with pool id
    When I select context action as "search"
    And I save action and click continue
    Then I should see the action saved as "Show Media"
    When I again select "Show Media" action on rules page
    And I select "Zero Results Media" as media type
    And I create show media with pool id
    When I select context action as "search"
    And I save action on rules page
    Then I should see "Context Alert" with "Same Context"
    When I de select the context option "search"
    When I select context action as "browse"
    And I save action and click continue
    Then I should see the action saved as "Show Media"
    When I save rule
    Then I should see the rule saved
    Then I should see rule details as entered Rule Name, Rule Priority, Effective and Expiration dates,fob
    And I should see media type as "Zero Results Media"
    Then I should see Show Media with pool id as entered
