@Saturn_Rules
Feature: Rules Functional tests to validate trigger and action compatibility common to MCOM and BCOM
  As a valid Saturn user
  I want to validate trigger and action compatibility on rules

  @Saturn_Regression
  Scenario:Verify Keyword Pattern compatibility with actions
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "keyword pattern Compatibility"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Keyword Pattern" trigger on rules page
    When I click on Add action
    Then I should see the below options enabled under actions dropdown for "keyword pattern"
      |Category Redirect|
      |Display Message|
      |Execute New Search|
      |Manage Facets|
      |Manage Featured Facet|
      |Modify Result Set|
      |PDP Redirect|
      |RDPP Algorithm|
      |Show Master or Member Products|
      |Show Media|
      |URL Redirect|

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
  Scenario:Verify Keyword Pattern trigger compatibility with Category Id with conditon OR
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Keyword Pattern Compatibility"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Keyword Pattern" trigger on rules page
    When I create keyword pattern trigger with the given criteria and value
    And I save trigger
    When I select "Category Ids" trigger on rules page
    And I create Category Ids trigger with category Id
    And I save trigger
    When I select "RDPP Algorithm" action on rules page
    And I create rdpp with random algorithm action value
    And I enter rdpp algorithm Effective and Expiration dates
    When I select context action as "browse"
    And I save action and click continue
    Then I should see the action saved as "RDPP Algorithm"
    And I save rule
    Then I should see the rule saved

  @Saturn_Regression @Saturn_Top_priority
  Scenario:Verify All Result Count compatibility with actions
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Result Count Compatibility"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Result Count" trigger on rules page
    When I click on Add action
    Then I should see the below options enabled under actions dropdown for "Result Count"
      |Display Message|
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

  @Saturn_Regression @Saturn_Top_priority
  Scenario:Verify Keyword Pattern trigger compatibility with Category Id
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Keyword Pattern Compatibility"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Keyword Pattern" trigger on rules page
    When I create keyword pattern trigger with the given criteria and value
    And I save trigger
    When I select "Category Ids" trigger on rules page
    And I create Category Ids trigger with category Id
    And I save trigger
    When I select "RDPP Algorithm" action on rules page
    And I create rdpp with random algorithm action value
    And I enter rdpp algorithm Effective and Expiration dates
    When I select context action as "browse"
    And I save action and click continue
    Then I should see the action saved as "RDPP Algorithm"
    And I save rule
    Then I should see the rule saved

  @Saturn_Regression @Saturn_Top_priority
  Scenario:Verify Keyword Pattern trigger compatibility with Result Set with condition AND
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Keyword Pattern Compatibility"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Keyword Pattern" trigger on rules page
    And I create keyword pattern trigger with the given criteria and value
    And I save trigger
    And I select "Result Set" trigger on rules page
    And I select the condition as And
    And I create Result Set trigger
    And I save trigger
    And I save rule
    Then I should see the rule saved

  @Saturn_Regression @Saturn_Top_priority
  Scenario:Verify Category Id trigger compatibility with Keyword Pattern with condition OR
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Keyword Pattern Compatibility"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Keyword Pattern" trigger on rules page
    And I create keyword pattern trigger with the given criteria and value
    And I save trigger
    And I select "Category Ids" trigger on rules page
    And I create Category Ids trigger with category Id
    And I save trigger
    And I select "RDPP Algorithm" action on rules page
    And I create rdpp with random algorithm action value
    And I enter rdpp algorithm Effective and Expiration dates
    And I select context action as "browse"
    And I save action and click continue
    Then I should see the action saved as "RDPP Algorithm"
    And I save rule
    Then I should see the rule saved

  @Saturn_Regression @Saturn_Top_priority
  Scenario:Verify Category Ids trigger compatibility with Keyword Pattern with condition AND
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Keyword Pattern Compatibility"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Category Ids" trigger on rules page
    And I create Category Ids trigger with category Id
    And I save trigger
    And I select "Keyword Pattern" trigger on rules page
    And I select the condition as And
    And I create keyword pattern trigger with the given criteria and value
    And I save trigger
    And I select "RDPP Algorithm" action on rules page
    And I create rdpp with random algorithm action value
    And I enter rdpp algorithm Effective and Expiration dates
    And I select context action as "browse"
    And I save action and click continue
    Then I should see the action saved as "RDPP Algorithm"
    And I save rule
    Then I should see the trigger compatibility error

  @Saturn_Regression @Saturn_Top_priority
  Scenario:Verify Category Ids trigger compatibility with Facet Refinement with condition OR
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as " Category Ids Compatibility"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Category Ids" trigger on rules page
    And I create Category Ids trigger with category Id
    And I save trigger
    And I select "Facet Refinement" trigger on rules page
    And I create facet refinement trigger
    And I save trigger
    When I again select "RDPP Algorithm" action on rules page
    And I create rdpp with random algorithm action value
    And I enter rdpp algorithm Effective and Expiration dates
    And I select context action as "browse"
    And I save action and click continue
    Then I should see the action saved as "RDPP Algorithm" on Rules Page
    And I save rule
    Then I should see the rule saved

  @Saturn_Regression @Saturn_Top_priority
  Scenario:Verify Category Ids trigger compatibility with Facet Refinement with condition AND
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as " Category Ids Compatibility"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Category Ids" trigger on rules page
    And I create Category Ids trigger with category Id
    And I save trigger
    And I select "Facet Refinement" trigger on rules page
    And I select the condition as And
    And I create facet refinement trigger
    And I save trigger
    When I again select "RDPP Algorithm" action on rules page
    And I create rdpp with random algorithm action value
    And I enter rdpp algorithm Effective and Expiration dates
    And I select context action as "browse"
    And I save action and click continue
    Then I should see the action saved as "RDPP Algorithm" on Rules Page
    And I save rule
    Then I should see the rule saved

  @Saturn_Regression @Saturn_Top_priority
  Scenario:Verify Category Ids trigger compatibility with Result Count with condition OR
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Keyword Pattern Compatibility"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    When I select "Category Ids" trigger on rules page
    And I create Category Ids trigger with category Id
    And I save trigger
    When I select "Result Count" trigger on rules page
    And I create result count trigger with "Less Than" attribute
    And I save trigger
    And I select "RDPP Algorithm" action on rules page
    And I create rdpp with random algorithm action value
    And I enter rdpp algorithm Effective and Expiration dates
    And I select context action as "browse"
    And I save action and click continue
    And I save rule
    Then I should see the rule saved

  @Saturn_Regression @Saturn_Top_priority
  Scenario:Verify Keyword Pattern trigger compatibility with Result Count with condition OR
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Keyword Pattern Compatibility"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Keyword Pattern" trigger on rules page
    When I create keyword pattern trigger with the given criteria and value
    And I save trigger
    When I select "Result Count" trigger on rules page
    And I create result count trigger with "Less Than" attribute
    And I save trigger
    And I save rule
    Then I should see the rule saved

  @Saturn_Regression @Saturn_Top_priority
  Scenario:Verify Keyword Pattern trigger compatibility with Result Count with condition AND
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Keyword Pattern Compatibility"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Keyword Pattern" trigger on rules page
    And I create keyword pattern trigger with the given criteria and value
    And I save trigger
    And I select "Result Count" trigger on rules page
    And I select the condition as And
    And I create result count trigger with "Less Than" attribute
    And I save trigger
    And I save rule
    Then I should see the rule saved

  @Saturn_Regression @Saturn_Top_priority
  Scenario:Verify Keyword Pattern trigger compatibility with Result Set with condition OR
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Keyword Pattern Compatibility"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Keyword Pattern" trigger on rules page
    And I create keyword pattern trigger with the given criteria and value
    And I save trigger
    And I select "Result Set" trigger on rules page
    And I create Result Set trigger
    And I save trigger
    And I save rule
    Then I should see the rule saved

  @Saturn_Regression @Saturn_Top_priority
  Scenario:Verify Category Ids trigger compatibility with Result Count with condition AND
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Keyword Pattern Compatibility"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    When I select "Category Ids" trigger on rules page
    And I create Category Ids trigger with category Id
    And I save trigger
    When I select "Result Count" trigger on rules page
    When I select the condition as And
    And I create result count trigger with "Less Than" attribute
    And I save trigger
    And I select "RDPP Algorithm" action on rules page
    And I create rdpp with random algorithm action value
    And I enter rdpp algorithm Effective and Expiration dates
    And I select context action as "browse"
    And I save action and click continue
    And I save rule
    Then I should see the rule saved

  @Saturn_Regression @Saturn_Top_priority
  Scenario:Verify Category Ids trigger compatibility with Result Set with condition OR
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Keyword Pattern Compatibility"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    When I select "Category Ids" trigger on rules page
    And I create Category Ids trigger with category Id
    And I save trigger
    When I select "Result Set" trigger on rules page
    And I create Result Set trigger
    And I save trigger
    And I select "RDPP Algorithm" action on rules page
    And I create rdpp with random algorithm action value
    And I enter rdpp algorithm Effective and Expiration dates
    And I select context action as "browse"
    And I save action and click continue
    And I save rule
    Then I should see the rule saved

  @Saturn_Regression @Saturn_Top_priority
  Scenario:Verify Facet Refinement trigger compatibility with Result Count with condition OR
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Keyword Pattern Compatibility"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    When I select "Facet Refinement" trigger on rules page
    And I create facet refinement trigger
    And I save trigger
    When I select "Result Count" trigger on rules page
    And I create result count trigger with "Less Than" attribute
    And I save trigger
    And I save rule
    Then I should see the rule saved

  @Saturn_Regression @Saturn_Top_priority
  Scenario:Verify Facet Refinement trigger compatibility with Result Count with condition AND
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Keyword Pattern Compatibility"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    When I select "Facet Refinement" trigger on rules page
    And I create facet refinement trigger
    And I save trigger
    When I select "Result Count" trigger on rules page
    When I select the condition as And
    And I create result count trigger with "Less Than" attribute
    And I save trigger
    When I select "RDPP Algorithm" action on rules page
    And I create rdpp with random algorithm action value
    And I enter rdpp algorithm Effective and Expiration dates
    When I select context action as "browse"
    And I save action and click continue
    Then I should see the action saved as "RDPP Algorithm"
    And I save rule
    Then I should see the rule saved

  @Saturn_Regression @Saturn_Top_priority
  Scenario:Verify Facet Refinement trigger compatibility with Result Set with condition OR
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Keyword Pattern Compatibility"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    When I select "Facet Refinement" trigger on rules page
    And I create facet refinement trigger
    And I save trigger
    When I select "Result Set" trigger on rules page
    And I create Result Set trigger
    And I save Result Set Trigger
    When I select "RDPP Algorithm" action on rules page
    And I create rdpp with random algorithm action value
    And I enter rdpp algorithm Effective and Expiration dates
    When I select context action as "browse"
    And I save action and click continue
    Then I should see the action saved as "RDPP Algorithm"
    And I save rule
    Then I should see the rule saved

  @Saturn_Regression @Saturn_Top_priority
  Scenario:Verify Category Ids trigger compatibility with Result Set with condition AND
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Keyword Pattern Compatibility"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    When I select "Facet Refinement" trigger on rules page
    And I create facet refinement trigger
    And I save trigger
    When I select "Result Set" trigger on rules page
    When I select the condition as And
    And I create Result Set trigger
    And I save trigger
    When I select "RDPP Algorithm" action on rules page
    And I create rdpp with random algorithm action value
    And I enter rdpp algorithm Effective and Expiration dates
    When I select context action as "browse"
    And I save action and click continue
    Then I should see the action saved as "RDPP Algorithm"
    And I save rule
    Then I should see the rule saved

  @Saturn_Regression @Saturn_Top_priority
  Scenario:Verify Result Count trigger compatibility with Result Set with condition OR
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Result Count Compatibility"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    When I select "Result Count" trigger on rules page
    And I create result count trigger with "Less Than" attribute
    And I save trigger
    When I select "Result Set" trigger on rules page
    And I create Result Set trigger
    And I save trigger
    And I save rule
    Then I should see the rule saved

  @Saturn_Regression @Saturn_Top_priority
  Scenario:Verify Result Count trigger compatibility with Result Set with condition AND
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Result Count Compatibility"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    When I select "Result Count" trigger on rules page
    And I create result count trigger with "Less Than" attribute
    And I save trigger
    When I select "Result Set" trigger on rules page
    When I select the condition as And
    And I create Result Set trigger
    And I save Result Set Trigger
    When I select "RDPP Algorithm" action on rules page
    And I create rdpp with random algorithm action value
    And I enter rdpp algorithm Effective and Expiration dates
    When I select context action as "browse"
    And I save action and click continue
    Then I should see the action saved as "RDPP Algorithm"
    And I save rule
    Then I should see the rule saved

   @Saturn_Regression @Saturn_Top_priority
  Scenario:Verify Keyword Pattern trigger compatibility with Category Id with condition AND
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Keyword Pattern Compatibility"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Keyword Pattern" trigger on rules page
    When I create keyword pattern trigger with the given criteria and value
    And I save trigger
    When I select "Category Ids" trigger on rules page
    When I select the condition as And
    And I create Category Ids trigger with category Id
    And I save trigger
    When I select "RDPP Algorithm" action on rules page
    And I create rdpp with random algorithm action value
    And I enter rdpp algorithm Effective and Expiration dates
    When I select context action as "browse"
    And I save action and click continue
    Then I should see the action saved as "RDPP Algorithm"
    And I save rule
    Then I should see the trigger compatibility error

  @Saturn_Regression @Saturn_Top_priority
  Scenario:Verify Keyword Pattern trigger compatibility with Facet Refinement with condition OR
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Keyword Pattern Compatibility"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Keyword Pattern" trigger on rules page
    When I create keyword pattern trigger with the given criteria and value
    And I save trigger
    When I select "Facet Refinement" trigger on rules page
    And I create facet refinement trigger
    And I save trigger
    When I select "RDPP Algorithm" action on rules page
    And I create rdpp with random algorithm action value
     And I enter rdpp algorithm Effective and Expiration dates            
    When I select context action as "browse"
    And I save action and click continue
    Then I should see the action saved as "RDPP Algorithm"
    And I save rule
    Then I should see the rule saved
    
  @Saturn_Regression @Saturn_Top_priority
  Scenario:Verify Keyword Pattern trigger compatibility with Facet Refinement with condition AND
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Keyword Pattern Compatibility"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Keyword Pattern" trigger on rules page
    When I create keyword pattern trigger with the given criteria and value
    And I save trigger
    When I select "Facet Refinement" trigger on rules page
    When I select the condition as And
    And I create facet refinement trigger
    And I save trigger
    When I select "RDPP Algorithm" action on rules page
    And I create rdpp with random algorithm action value
    And I enter rdpp algorithm Effective and Expiration dates
    When I select context action as "browse"
    And I save action and click continue
    Then I should see the action saved as "RDPP Algorithm"
    And  I save rule
    Then I should see the rule saved

  @Saturn_Regression
  Scenario:Verify validation for  All search trigger with keyword pattern trigger
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "All Search"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "All Search" trigger on rules page
    When I save trigger
    And I click "ok" button on alert popup
    Then I should see the trigger saved as "All Search"
    And I select "Keyword Pattern" trigger on rules page
    When I create keyword pattern trigger with the given criteria and value
    When I save trigger
    When I save rule
    Then I should see all search compatible error message "All Search trigger would be allowed just with Category Id Trigger."

  @Saturn_Regression
  Scenario:Verify validation for  All search trigger with Result count trigger
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "All Search"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "All Search" trigger on rules page
    When I save trigger
    And I click "ok" button on alert popup
    Then I should see the trigger saved as "All Search"
    And I select "Result Count" trigger on rules page
    Then I should see attributes in Result count dropdown
    And I should see "Less Than" as selected by default
    When I create result count trigger with "Greater Than" attribute
    When I save trigger
    When I save rule
    Then I should see all search compatible error message "All Search trigger would be allowed just with Category Id Trigger."

  @Saturn_Regression
  Scenario:Verify validation for  All search trigger with Result set trigger
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "All Search"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "All Search" trigger on rules page
    When I save trigger
    And I click "ok" button on alert popup
    Then I should see the trigger saved as "All Search"
    And I select "Result Set" trigger on rules page
    When I create Result Set trigger
    When I save Result Set Trigger
    When I save rule
    Then I should see all search compatible error message "All Search trigger would be allowed just with Category Id Trigger."

  @Saturn_Regression
  Scenario:Verify validation for  All search trigger with Facet refinement  trigger
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "All Search"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "All Search" trigger on rules page
    When I save trigger
    And I click "ok" button on alert popup
    Then I should see the trigger saved as "All Search"
    And I select "Facet Refinement" trigger on rules page
    When I create facet refinement trigger
    When I save trigger
    When I save rule
    Then I should see all search compatible error message "All Search trigger would be allowed just with Category Id Trigger."

  @Saturn_Regression
  Scenario:Create a rule with All Search Trigger + Add all compatible action.
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "All Search"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "All Search" trigger on rules page
    When I save trigger
    And I click "ok" button on alert popup
    And I should see the trigger saved as "All Search"
    When I again select "Display Message" action on rules page
    And  I create "Display Message" action  with Display "message"
    And I de select the context option "DESKTOP"
    And I select "TABLET" as context
    And I uncheck the apply non canvas option
    And  I select context action as "browse"
    And I save action on rules page
    Then I should see the action saved as "Display Message" on Rules Page
    When I again select "Manage Facets" action on rules page
    And I select the below values from Manage Facets list
      |Age Group|
      |Color Family|
    Then I should see Value Management column in select facet display
    And I de select all the context options selected by default
    And I select "All Mobile" as context
    And I select context action as "browse"
    And I save action on rules page
    Then I should see the action saved as "Manage Facets" on Rules Page
    When I again select "Manage Featured Facet" action on rules page
    And I enter Manage Featured Facets value
    And I de select the context option "All Mobile"
    And I select "TABLET" as context
    And I select context action as "search"
    And I save action on rules page
    Then I should see the action saved as "Manage Featured Facet" on Rules Page
    When I again select "Modify Result Set" action on rules page
    And I select the modify result set with operator as "Boost Attribute Values"
    And I create "Modify Result Set" with Boost Attribute
    And I de select all the context options selected by default
    And I select "All Mobile" as context
    When I select context action as "search"
    And I save action on rules page
    Then I should see the action saved as "Modify Result Set" on Rules Page
    When I again select "RDPP Algorithm" action on rules page
    And I create rdpp with random algorithm action value
    And I enter rdpp algorithm Effective and Expiration dates
    And I de select all the context options selected by default
    And I select "TABLET" as context
    When I select context action as "browse"
    And I save action on rules page
    Then I should see the action saved as "RDPP Algorithm" on Rules Page
    When I again select "Show Media" action on rules page
    And I select "Zero Results Media" as media type
    And I create show media with pool id
    And I de select all the context options selected by default
    And I select "DESKTOP" as context
    When I select context action as "browse"
    And I save action on rules page
    Then I should see the action saved as "Show Media" on Rules Page
    Then I should see display message as entered
    And I should see default Facet values as
      |Age Group|
      |Color Family|
    Then I should see modify result set operator with operator as "Boost Attribute Values"
    And I should see the Boost Attribute value as selected
    Then I should see rdpp algorithm as entered
    Then I should see Show Media with pool id as entered

  @Saturn_Regression @Saturn_Top_priority
  Scenario:Create a rule by adding all the compatible actions with Result Set trigger
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Result Set Compatibility"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Result Set" trigger on rules page
    Then I should see criteria in the Result set dropdown
    When I create Result Set trigger
    When I save trigger
    Then I should see the trigger saved as "Result Set"
    When I again select "Display Message" action on rules page
    And  I create "Display Message" action  with Display "message"
    And I de select the context option "DESKTOP"
    And I select "TABLET" as context
    And I uncheck the apply non canvas option
    And  I select context action as "browse"
    And I save action on rules page
    Then I should see the action saved as "Display Message" on Rules Page
    When I again select "Manage Facets" action on rules page
    And I select the below values from Manage Facets list
      |Age Group|
      |Color Family|
    Then I should see Value Management column in select facet display
    And I de select all the context options selected by default
    And I select "All Mobile" as context
    And I select context action as "browse"
    And I save action on rules page
    Then I should see the action saved as "Manage Facets" on Rules Page
    When I again select "Manage Featured Facet" action on rules page
    And I enter Manage Featured Facets value
    And I de select the context option "All Mobile"
    And I select "TABLET" as context
    And I select context action as "search"
    And I save action on rules page
    Then I should see the action saved as "Manage Featured Facet" on Rules Page
    When I again select "Modify Result Set" action on rules page
    And I select the modify result set with operator as "Boost"
    And I create Modify Result Set with required data
    And I de select all the context options selected by default
    And I select "All Mobile" as context
    When I select context action as "search"
    And I save action on rules page
    Then I should see the action saved as "Modify Result Set" on Rules Page
    When I again select "RDPP Algorithm" action on rules page
    And I create rdpp with random algorithm action value
    And I enter rdpp algorithm Effective and Expiration dates
    And I de select all the context options selected by default
    And I select "TABLET" as context
    When I select context action as "browse"
    And I save action on rules page
    Then I should see the action saved as "RDPP Algorithm" on Rules Page
    When I again select "Show Media" action on rules page
    And I select "Zero Results Media" as media type
    And I create show media with pool id
    And I de select all the context options selected by default
    And I select "DESKTOP" as context
    When I select context action as "browse"
    And I save action on rules page
    Then I should see the action saved as "Show Media" on Rules Page
    When I save rule
    Then I should see the rule saved
    Then I should see rule details as entered Rule Name, Rule Priority, Effective and Expiration dates,fob
    Then I should see display message as entered
    And I should see default Facet values as
      |Age Group|
      |Color Family|
    And I should see the Manage Featured Facets Value as entered
    Then I should see modify result set operator with operator as "Replace"
    And I should see Modify Result Set with the data as entered
    Then I should see rdpp algorithm as entered
    Then I should see Show Media with pool id as entered

  @Saturn_Regression @Saturn_Top_priority
  Scenario:Create a rule by adding all the compatible actions with Facet Refinement trigger
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Facet Refinement Compatibility"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Facet Refinement" trigger on rules page
    And I create facet refinement trigger
    And I save trigger
    And I should see the trigger saved as "Facet Refinement"
    When I again select "Display Message" action on rules page
    And  I create "Display Message" action  with Display "message"
    And I de select the context option "DESKTOP"
    And I select "TABLET" as context
    And I uncheck the apply non canvas option
    And  I select context action as "browse"
    And I save action on rules page
    Then I should see the action saved as "Display Message" on Rules Page
    When I again select "Manage Facets" action on rules page
    And I select the below values from Manage Facets list
      |Age Group|
      |Color Family|
    Then I should see Value Management column in select facet display
    And I de select all the context options selected by default
    And I select "All Mobile" as context
    And I select context action as "browse"
    And I save action on rules page
    Then I should see the action saved as "Manage Facets" on Rules Page
    When I again select "RDPP Algorithm" action on rules page
    And I create rdpp with random algorithm action value
    And I enter rdpp algorithm Effective and Expiration dates
    And I de select all the context options selected by default
    And I select "TABLET" as context
    When I select context action as "browse"
    And I save action on rules page
    Then I should see the action saved as "RDPP Algorithm" on Rules Page
    When I again select "Show Media" action on rules page
    And I select "Zero Results Media" as media type
    And I create show media with pool id
    And I de select all the context options selected by default
    And I select "DESKTOP" as context
    When I select context action as "browse"
    And I save action on rules page
    Then I should see the action saved as "Show Media" on Rules Page
    When I save rule
    Then I should see the rule saved
    Then I should see rule details as entered Rule Name, Rule Priority, Effective and Expiration dates,fob
    And I should see the same details entered for the Facet Refinement trigger
    Then I should see display message as entered
    And I should see default Facet values as
      |Age Group|
      |Color Family|
    Then I should see rdpp algorithm as entered
    Then I should see Show Media with pool id as entered

############## CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-99767   #######-----------
@csi @Saturn_Regression
Scenario: Confirm Modify Result Set Operator enabled and disabled values with Result Set Trigger in Create Rule page
  Given I login to Saturn as an "admin" user
  When I navigate to "Create Rule" under Rules
  And I enter Rule Name as "Modify Result set Operator Disabled"
  And I enter Rule Priority, Effective and Expiration dates, FOB
  And I select "Result Set" trigger on rules page
  When I create Result Set trigger
  When I save trigger
  And I click on Add action
  And I select "Modify Result Set" action on Create Rule page
  Then I confirm below Modify Result Set Operator types are disabled
    |Add     |
    |Remove  |
    |Replace |
  Then I confirm below Modify Result Set Operator types are enabled
    |Boost                  |
    |Boost Attribute Values |
