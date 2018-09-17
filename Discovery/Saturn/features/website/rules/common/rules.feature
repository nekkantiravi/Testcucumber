@Saturn_Rules
Feature: Rules Functional tests common to MCOM and BCOM.
  As a valid Saturn user
  I want to create/Edit/Copy/Find Rule, save and confirm that the dB contains Rule data


  @Saturn_Must_Pass @keyword_pattern
  Scenario: Create a rule with keyword pattern trigger
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Keyword pattern trigger"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Keyword Pattern" trigger on rules page
    Then I should see criteria in the Keyword pattern dropdown
    When I create keyword pattern trigger with all criteria
    When I save trigger
    Then I should see the trigger saved as "Keyword Pattern"
    And I save rule
    Then I should see the rule saved
    Then I should see rule details as entered Rule Name, Rule Priority, Effective and Expiration dates,fob
    And I should see the same details entered for the keyword pattern trigger

  @Saturn_Must_Pass @Result_Count
  Scenario: create a rule with Result_Count
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Result count trigger"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Result Count" trigger on rules page
    Then I should see attributes in Result count dropdown
    And I should see "Less Than" as selected by default
    When I create result count trigger with "Greater Than" attribute
    When I save trigger
    Then I should see the trigger saved as "Result Count"
    And I save rule
    Then I should see rule details as entered Rule Name, Rule Priority, Effective and Expiration dates,fob
    And I click on  Edit rule
    And I click on  Edit trigger
    When I edit trigger result count trigger with "Less Than" attribute
    When I save the edited trigger
    Then I should see the trigger saved as "Result Count"
    And I should see the trigger with below details
      |Less Than|2000|
    And I save rule details page
    Then I should see the rule saved

  @Saturn_Must_Pass @Result_set_Trigger
  Scenario: create a rule with Result_set
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Result set trigger"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Result Set" trigger on rules page
    Then I should see criteria in the Result set dropdown
    When I create Result Set trigger
    When I save trigger
    Then I should see the trigger saved as "Result Set"
    And I save rule
    Then I should see rule details as entered Rule Name, Rule Priority, Effective and Expiration dates,fob
    And I should see the same details entered for the Result Set  trigger

  @Saturn_Must_Pass @Facet_refinement
  Scenario: create a rule with facet refinement trigger
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Facet Refinement"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Facet Refinement" trigger on rules page
    Then I should see "Refine by following facets:" at the page
    When I create facet refinement trigger
    When I save trigger
    Then I should see the trigger saved as "Facet Refinement"
    And I save rule
    Then I should see rule details as entered Rule Name, Rule Priority, Effective and Expiration dates,fob
    And I should see the same details entered for the Facet Refinement trigger

  @Saturn_Must_Pass @All_Search
  Scenario: create all search trigger in create rule page
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "All Search"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "All Search" trigger on rules page
    When I save trigger
    Then I should see the "All Search Error Message" for the "All Search" trigger
    And I click "ok" button on alert popup
    And I should see the trigger saved as "All Search"
    And I save rule
    Then I should see rule details as entered Rule Name, Rule Priority, Effective and Expiration dates,fob

  @Saturn_Must_Pass @Rdpp_Algorithm
  Scenario: create rule with rdpp algorithm action
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "RDPP Algorithm"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Category Ids" trigger on rules page
    And I create Category Ids trigger with category Id
    And I save trigger
    And I should see the trigger saved as "Category Ids"
    When I select "RDPP Algorithm" action on rules page
    And I create rdpp with random algorithm action value
    And I enter rdpp algorithm Effective and Expiration dates
    When I select context action as "browse"
    And I save action and click continue
    Then I should see the action saved as "RDPP Algorithm"
    And I save rule
    Then I should see the rule saved
    Then I should see rule details as entered Rule Name, Rule Priority, Effective and Expiration dates,fob
    Then I should see rdpp algorithm as entered

  @Saturn_Must_Pass @category_redirect
  Scenario: create a rule with category redirect action
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Category Redirect"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Keyword Pattern" trigger on rules page
    When I create keyword pattern trigger with the given criteria and value
    And I save trigger
    Then I should see the trigger saved as "Keyword Pattern"
    When I select "Category Redirect" action on rules page
    When I create "Category Redirect" action with "Category Id"
    When I select context action as "search"
    And I save action and click continue
    Then I should see the action saved as "Category Redirect"
    And I save rule
    Then I should see rule details as entered Rule Name, Rule Priority, Effective and Expiration dates,fob
    Then I should see that "Category Redirect" has "Category Id"  same as entered

  @Saturn_Must_Pass @url_redirect
  Scenario: create a rule with url redirect action and verify category ids and keyword pattern enabled
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "URL Redirect"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Keyword Pattern" trigger on rules page
    When I create keyword pattern trigger with the given criteria and value
    And I save trigger
    Then I should see the trigger saved as "Keyword Pattern"
    When I select "URL Redirect" action on rules page
    And I enter "url" for "URL Redirect"
    When I select context action as "search"
    And I save action and click continue
    And I should see the action saved as "URL Redirect"
    And I save rule
    Then I should see rule details as entered Rule Name, Rule Priority, Effective and Expiration dates,fob
    When I click on  Edit rule
    And I click on Edit Action
    Then I should see the value of "url" for "URL Redirect" as entered

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

  @Saturn_Must_Pass @Display_Message
  Scenario: create a rule with  display message action
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Display Message"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    When I select "Facet Refinement" trigger on rules page
    And I create facet refinement trigger
    And I save trigger
    Then I should see the trigger saved as "Facet Refinement"
    When I select "Display Message" action on rules page
    And  I create "Display Message" action  with Display "message"
    And  I select context action as "browse"
    And I save action and click continue
    Then I should see the action saved as "Display Message"
    And I save rule
    Then I should see rule details as entered Rule Name, Rule Priority, Effective and Expiration dates,fob
    Then I should see display message as entered
    And I should see the same details entered for the Facet Refinement trigger

  @Saturn_Must_Pass @modify_result_set_action_Replace
  Scenario:Create Modify Result set action with replace operator and keyword pattern trigger
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Modify Result Set"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Keyword Pattern" trigger on rules page
    When I create keyword pattern trigger with the given criteria and value
    And I save trigger
    When I select "Modify Result Set" action on rules page
    And I select the modify result set with operator as "Replace"
    And I create Modify Result Set with required data
    When I select context action as "browse"
    And I save action and click continue
    And I save rule
    Then I should see the rule saved
    Then I should see rule details as entered Rule Name, Rule Priority, Effective and Expiration dates,fob
    Then I should see modify result set operator with operator as "Replace"
    And I should see Modify Result Set with the data as entered

  @Saturn_Must_Pass @modify_result_set_action_Remove
  Scenario:Create Modify Result set action with remove operator and keyword pattern trigger
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Modify Result set"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Keyword Pattern" trigger on rules page
    When I create keyword pattern trigger with the given criteria and value
    And I save trigger
    When I select "Modify Result Set" action on rules page
    And I select the modify result set with operator as "Remove"
    And I create Modify Result Set with required data
    When I select context action as "search"
    And I save action and click continue
    Then I should see the action saved as "Modify Result Set"
    And I save rule
    Then I should see the rule saved
    Then I should see rule details as entered Rule Name, Rule Priority, Effective and Expiration dates,fob
    When I click on  Edit rule
    When I click on Edit Action
    Then I should see modify result set operator with operator as "Remove"
    And I should see Modify Result Set with the data as entered

  @Saturn_Must_Pass @modify_result_set_action_Boost
  Scenario: Create Modify Result set action with Boost operator and keyword pattern trigger
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Modify Result set"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Keyword Pattern" trigger on rules page
    When I create keyword pattern trigger with the given criteria and value
    And I save trigger
    When I select "Modify Result Set" action on rules page
    And I select the modify result set with operator as "Boost Attribute Values"
    And I create "Modify Result Set" with Boost Attribute
    When I select context action as "search"
    And I save action and click continue
    Then I should see the action saved as "Modify Result Set"
    And I save rule
    Then I should see the rule saved
    Then I should see rule details as entered Rule Name, Rule Priority, Effective and Expiration dates,fob
    When I click on  Edit rule
    And I click on Edit Action
    Then I should see modify result set operator with operator as "Boost Attribute Values"
    And I should see the Boost Attribute value as selected

  @Saturn_Must_Pass @show_master_member_products
  Scenario:create show_master_member_products
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Show Master or Member Products"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Keyword Pattern" trigger on rules page
    When I create keyword pattern trigger with the given criteria and value
    And I save trigger
    When I select "Show Master or Member Products" action on rules page
    And I select the display as "Show only the Master Products"
    When I select context action as "search"
    And I save action
    And I click continue
    And I save rule
    Then I should see the rule saved
    Then I should see rule details as entered Rule Name, Rule Priority, Effective and Expiration dates,fob
    When I click on  Edit rule
    And I click on Edit Action
    When I select the display as "Show only the Member Products"
    And I click on 'Cancel' action
    Then I should see rule details as entered Rule Name, Rule Priority, Effective and Expiration dates,fob
    And I should see the display value as "Show only the Master Products"

  @Saturn_Must_Pass @manage_factes
  Scenario:create a rule with manage facet action
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Manage Facets"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Category Ids" trigger on rules page
    And I create Category Ids trigger with category Id
    And I save trigger
    When I select "Manage Facets" action on rules page
    And I select the below values from Manage Facets list
      |Age Group|
      |Color Family|
    Then I should see Value Management column in select facet display
    When I select context action as "browse"
    And I save action and click continue
    And I save rule
    Then I should see the rule saved
    Then I should see rule details as entered Rule Name, Rule Priority, Effective and Expiration dates,fob
    And I should see default Facet values as
      |Age Group|
      |Color Family|

  @Saturn_Must_Pass @Show_media_Zero_Results_Media
  Scenario: create a rule with show media with media type as "Zero Results Media"
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Show Media"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    When I select "Result Count" trigger on rules page
    When I create result count trigger with "Greater Than" attribute
    When I save trigger
    Then I should see the trigger saved as "Result Count"
    When I select "Show Media" action on rules page
    And I select "Zero Results Media" as media type
    And I create show media with pool id
    When I select context action as "search"
    And I save action and click continue
    And I save rule
    Then I should see the rule saved
    Then I should see rule details as entered Rule Name, Rule Priority, Effective and Expiration dates,fob
    And I should see media type as "Zero Results Media"
    Then I should see Show Media with pool id as entered

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

  @Saturn_Must_Pass @Show_media_Copy_Block_Media
  Scenario: create a rule with show media with media type as Copy Block Media
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Show Media"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Keyword Pattern" trigger on rules page
    When I create keyword pattern trigger with the given criteria and value
    And I save trigger
    When I select "Show Media" action on rules page
    And I select "Copy Block Media" as media type
    And I create show media with Copy Block Id
    When I select context action as "search"
    And I save action and click continue
    And I save rule
    Then I should see the rule saved
    Then I should see rule details as entered Rule Name, Rule Priority, Effective and Expiration dates,fob
    And I should see media type as "Copy Block Media"

  @Saturn_dry_run @Saturn_Regression
  Scenario:Create a rule with keywordpattern trigger+Display message action+ Executive new search action
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Keyword pattern trigger"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Keyword Pattern" trigger on rules page
    When I create keyword pattern trigger with the given criteria and value
    And I save trigger
    When I select "Display Message" action on rules page
    And  I create "Display Message" action  with Display "message"
    And  I select context action as "browse"
    And I save action and click continue
    Then I should see the action saved as "Display Message"
    And I again select "Execute New Search" action on rules page
    And I enter replace term value as "term"
    And I select Search Query as "Replace ALL Customer Search"
    And I save action and click continue
    And I save rule
    Then I should see the rule saved
    Then I should see rule details as entered Rule Name, Rule Priority, Effective and Expiration dates,fob
    Then I should see display message as entered

  @Saturn_dry_run @Saturn_Regression
  Scenario:Create a rule with all search trigger + show media canvas action
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "All Search"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "All Search" trigger on rules page
    And I save trigger
    And I click "Ok" button on alert popup
    When I select "Show Media" action on rules page
    And I select "Canvas" as media type
    And I create show media with "Canvas Id"
    And I save action
    And I save rule
    Then I should see the rule saved
    Then I should see rule details as entered Rule Name, Rule Priority, Effective and Expiration dates,fob
    And I should see the action saved as "Show Media"
    When I click on  Edit rule
    And I click on Edit Action
    Then I should see Show Media with "Canvas Id" as entered

  @Saturn_dry_run @Saturn_Regression
  Scenario:Create a rule with all triggers
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "All Triggers"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    When I click on Add trigger
    Then I should see all the below options in the trigger dropdown
      |All Search|
      |Category Ids|
      |Keyword Pattern|
      |Result Count|
      |Result Set|
      |Literal Match|
      |Facet Refinement|
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
    When I select "RDPP Algorithm" action on rules page
    And I create rdpp with random algorithm action value
    And I enter rdpp algorithm Effective and Expiration dates
    When I select context action as "browse"
    And I save action and click continue
    Then I should see the action saved as "RDPP Algorithm"
    And I save rule
    Then I should see the rule saved
    Then I should see rule details as entered Rule Name, Rule Priority, Effective and Expiration dates,fob
    And I should see all the below triggers added on the rules page
      |Category Ids|
      |Facet Refinement|
      |Keyword Pattern|
      |Result Count|
      |Result Set|

  @Saturn_Regression
  Scenario:As a user I am able to see 'Priority' column added in the Merchandising Rules Screen
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    Then I should be on the rules page
    Then I should see The Rule priority dropdown on create rule page
    And I should see the below values in the Rule Priority dropdown
      |100|
      |95|
      |90|
      |85|
      |80|
      |75|
      |70|
      |65|
      |60|
      |55|
      |50|
      |45|
      |40|
      |35|
      |30|
      |25|
      |20|
      |15|
      |10|
    When I navigate to Rules menu
    And I select Find Rules
    Then I should see the column named "Priority" on the find rules page

  @Saturn_Regression
  Scenario:Verify error validation for multiple Rdpp action with overlapping dates with same context
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "multiple rdpp overlapping dates"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Keyword Pattern" trigger on rules page
    When I create keyword pattern trigger with the given criteria and value
    And I save trigger
    And I should see the trigger saved as "Keyword Pattern"
    When I select "RDPP Algorithm" action on rules page
    And I create rdpp with random algorithm action value
    And I enter rdpp algorithm Effective and Expiration dates
    When I de select all the context options selected by default
    And I select "DESKTOP" as context
    When I select context action as "browse"
    And I save action and click continue
    Then I should see the action saved as "RDPP Algorithm"
    When I again select "RDPP Algorithm" action on rules page
    And I create rdpp with random algorithm action value
    And I enter the "overlapping" rdpp algorithm Effective and Expiration dates
    And I save action on rules page
    Then I should see the "duplication" error

  @Saturn_Regression
  Scenario:Verify error validation for multiple Rdpp action with intersecting dates with same context
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "multiple rdpp intersecting dates"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Keyword Pattern" trigger on rules page
    When I create keyword pattern trigger with the given criteria and value
    And I save trigger
    And I should see the trigger saved as "Keyword Pattern"
    When I select "RDPP Algorithm" action on rules page
    And I create rdpp with random algorithm action value
    And I enter rdpp algorithm Effective and Expiration dates
    When I de select all the context options selected by default
    And I select "DESKTOP" as context
    When I select context action as "browse"
    And I save action and click continue
    Then I should see the action saved as "RDPP Algorithm"
    When I again select "RDPP Algorithm" action on rules page
    And I create rdpp with random algorithm action value
    And I enter the "intersecting" rdpp algorithm Effective and Expiration dates
    And I save action on rules page
    Then I should see the "duplication" error

  @Saturn_Regression
  Scenario:Verify error validation for multiple Rdpp action with in between dates with same context
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "multiple rdpp in between dates"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Keyword Pattern" trigger on rules page
    When I create keyword pattern trigger with the given criteria and value
    And I save trigger
    And I should see the trigger saved as "Keyword Pattern"
    When I select "RDPP Algorithm" action on rules page
    And I create rdpp with random algorithm action value
    And I enter rdpp algorithm Effective and Expiration dates
    When I de select all the context options selected by default
    And I select "DESKTOP" as context
    When I select context action as "browse"
    And I save action and click continue
    Then I should see the action saved as "RDPP Algorithm"
    When I again select "RDPP Algorithm" action on rules page
    And I create rdpp with random algorithm action value
    And I enter the "in between" rdpp algorithm Effective and Expiration dates
    And I save action on rules page
    Then I should see the "duplication" error

  @Saturn_Regression @Saturn_Top_priority
  Scenario:Create a rule by adding all the compatible actions with keyword pattern trigger
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "keyword pattern Compatibility"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Keyword Pattern" trigger on rules page
    And I create keyword pattern trigger with the given criteria and value
    And I save trigger
    And I should see the trigger saved as "Keyword Pattern"
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
    And I select the modify result set with operator as "Replace"
    And I create Modify Result Set with required data
    And I de select all the context options selected by default
    And I select "All Mobile" as context
    When I select context action as "search"
    And I save action on rules page
    Then I should see the action saved as "Modify Result Set" on Rules Page
    When I again select "PDP Redirect" action on rules page
    And I create PDP Redirect action with first PPID value
    And I de select all the context options selected by default
    And I select "Store Search and Send" as context
    When I select context action as "search"
    And I save action on rules page
    And I should see the action saved as "PDP Redirect" on Rules Page
    When I again select "RDPP Algorithm" action on rules page
    And I create rdpp with random algorithm action value
    And I enter rdpp algorithm Effective and Expiration dates
    And I de select all the context options selected by default
    And I select "TABLET" as context
    When I select context action as "browse"
    And I save action on rules page
    Then I should see the action saved as "RDPP Algorithm" on Rules Page
    When I again select "Show Master or Member Products" action on rules page
    And I select the display as "Show only the Master Products"
    And I de select all the context options selected by default
    And I select "All Mobile" as context
    When I select context action as "search"
    And I save action on rules page
    Then I should see the action saved as "Show Master or Member Products" on Rules Page
    When I again select "Show Media" action on rules page
    And I select "Zero Results Media" as media type
    And I create show media with pool id
    And I de select all the context options selected by default
    And I select "DESKTOP" as context
    When I select context action as "browse"
    And I save action on rules page
    Then I should see the action saved as "Show Media" on Rules Page
    When I again select "URL Redirect" action on rules page
    And I enter "url" for "URL Redirect"
    And I de select all the context options selected by default
    And I select "TABLET" as context
    When I select context action as "landing"
    And I save action on rules page
    And I should see the action saved as "URL Redirect" on Rules Page
    When I again select "Category Redirect" action on rules page
    When I create "Category Redirect" action with "Category Id"
    And I de select all the context options selected by default
    And I select "DESKTOP" as context
    When I select context action as "search"
    And I save action on rules page
    Then I should see the action saved as "Category Redirect" on Rules Page
    When I save rule
    Then I should see the rule saved
    Then I should see rule details as entered Rule Name, Rule Priority, Effective and Expiration dates,fob
    Then I should see display message as entered
    And I should see default Facet values as
      |Age Group|
      |Color Family|
    Then I should see modify result set operator with operator as "Replace"
    And I should see Modify Result Set with the data as entered
    And I verify the value of PPID
    Then I should see rdpp algorithm as entered
    And I should see the display value as "Show only the Master Products"
    Then I should see Show Media with pool id as entered
    Then I should see the value of "url" for "URL Redirect" as entered
    Then I should see that "Category Redirect" has "Category Id"  same as entered


  @Saturn_Regression @Saturn_Top_priority
  Scenario:Create a rule by adding all the compatible actions with Category Ids trigger
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Category Ids Compatibility"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Category Ids" trigger on rules page
    And I create Category Ids trigger with category Id
    And I save trigger
    And I should see the trigger saved as "Category Ids"
    When I again select "Manage Facets" action on rules page
    And I select the below values from Manage Facets list
      |Age Group|
      |Color Family|
    Then I should see Value Management column in select facet display
    And I de select all the context options selected by default
    And I select "All Mobile" as context
    And I select context action as "browse"
    And I save action and click continue
    Then I should see the action saved as "Manage Facets" on Rules Page
    When I again select "Manage Featured Facet" action on rules page
    And I enter Manage Featured Facets value
    And I click on context
    And I de select the context option "All Mobile"
    And I select "TABLET" as context
    And I select context action as "search"
    And I save action and click continue
    Then I should see the action saved as "Manage Featured Facet" on Rules Page
    When I again select "Modify Result Set" action on rules page
    And I create Modify Result Set with required data
    And I click on context
    And I de select all the context options selected by default
    And I select "All Mobile" as context
    When I select context action as "search"
    And I save action and click continue
    Then I should see the action saved as "Modify Result Set" on Rules Page
    When I again select "RDPP Algorithm" action on rules page
    And I create rdpp with random algorithm action value
    And I enter rdpp algorithm Effective and Expiration dates
    And I click on context
    And I de select all the context options selected by default
    And I select "TABLET" as context
    And I save action and click continue
    Then I should see the action saved as "RDPP Algorithm" on Rules Page
    When I again select "Show Media" action on rules page
    And I select "Canvas" as media type
    And I create show media with "Canvas Id"
    And I de select all the context options selected by default
    And I select "DESKTOP" as context
    When I select context action as "browse"
    And I save action on rules page
    Then I should see the action saved as "Show Media" on Rules Page
    When I save rule
    Then I should see the rule saved
    Then I should see rule details as entered Rule Name, Rule Priority, Effective and Expiration dates,fob
    Then I should see that "Category Redirect" has "Category Id"  same as entered
    And I should see default Facet values as
      |Age Group|
      |Color Family|
    And I should see the Manage Featured Facets Value as entered
    Then I should see modify result set operator with operator as "Boost"
    And I should see Modify Result Set with the data as entered
    Then I should see rdpp algorithm as entered
    Then I should see Show Media with "Canvas Id" as entered

  @Saturn_Regression @Saturn_Top_priority
  Scenario:Create a rule by adding all the compatible actions with Result Count trigger
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Result Count Compatibility"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Result Count" trigger on rules page
    And I create result count trigger with "Greater Than" attribute
    And I save trigger
    And I should see the trigger saved as "Result Count"
    When I again select "Display Message" action on rules page
    And  I create "Display Message" action  with Display "message"
    And I de select the context option "DESKTOP"
    And I select "TABLET" as context
    And I uncheck the apply non canvas option
    And  I select context action as "browse"
    And I save action on rules page
    Then I should see the action saved as "Display Message" on Rules Page
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
    Then I should see rdpp algorithm as entered

  @Saturn_Regression @Saturn_Top_priority
  Scenario:Create a rule with Modify Result Set multiple Boost and Rdpp Action with different contexts
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
    And I de select all the context options selected by default
    And I select "DESKTOP" as context
    When I select context action as "search"
    And I save action and click continue
    Then I should see the action saved as "Modify Result Set" on Rules Page
    When I again select "Modify Result Set" action on rules page
    And I select the modify result set with operator as "Boost"
    And I create Modify Result Set with required data
    And I click save action
    Then I should see "Context Alert" with "Same Context"
    And I click "OK" button on same context alert popup
    And I click on context
    And I de select the context option "DESKTOP"
    And I select "TABLET" as context
    And I click save action
    Then I should see "Context Alert" with "Same Context"
    When I click "OK" button on alert popup
    And I uncheck the apply non canvas option
    And I save action on rules page
    Then I should see the action saved as "Modify Result Set" on Rules Page
    When I again select "RDPP Algorithm" action on rules page
    And I create rdpp with random algorithm action value
    And I enter rdpp algorithm Effective and Expiration dates
    And I de select all the context options selected by default
    And I select "DESKTOP" as context
    When I select context action as "browse"
    And I save action on rules page
    Then I should see the action saved as "RDPP Algorithm" on Rules Page
    When I save rule
    Then I should see the rule saved
    Then I should see rule details as entered Rule Name, Rule Priority, Effective and Expiration dates,fob
    Then I should see modify result set operator with operator as "Boost"
    And I should see Modify Result Set with the data as entered
    And I should see rdpp algorithm as entered


  @Saturn_Regression @Saturn_Top_priority
  Scenario:Create a rule with Modify Result Set multiple Replace and Rdpp Action with different contexts
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Modify Result Set Multiple Replace"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Keyword Pattern" trigger on rules page
    When I create keyword pattern trigger with the given criteria and value
    When I save trigger
    Then I should see the trigger saved as "Keyword Pattern"
    When I again select "Modify Result Set" action on rules page
    And I select the modify result set with operator as "Replace"
    And I create Modify Result Set with required data
    And I de select all the context options selected by default
    And I select "DESKTOP" as context
    When I select context action as "search"
    And I save action and click continue
    Then I should see the action saved as "Modify Result Set" on Rules Page
    When I again select "Modify Result Set" action on rules page
    And I select the modify result set with operator as "Replace"
    And I create Modify Result Set with required data
    And I click save action
    Then I should see "Context Alert" with "Same Context"
    And I click "OK" button on same context alert popup
    And I click on context
    And I de select the context option "DESKTOP"
    And I select "TABLET" as context
    And I click save action
    Then I should see "Context Alert" with "Same Context"
    When I click "OK" button on alert popup
    And I uncheck the apply non canvas option
    And I save action on rules page
    Then I should see the action saved as "Modify Result Set" on Rules Page
    When I again select "RDPP Algorithm" action on rules page
    And I create rdpp with random algorithm action value
    And I enter rdpp algorithm Effective and Expiration dates
    And I de select all the context options selected by default
    And I select "DESKTOP" as context
    When I select context action as "browse"
    And I save action on rules page
    Then I should see the action saved as "RDPP Algorithm" on Rules Page
    When I save rule
    Then I should see the rule saved
    Then I should see rule details as entered Rule Name, Rule Priority, Effective and Expiration dates,fob
    Then I should see modify result set operator with operator as "Replace"
    And I should see Modify Result Set with the data as entered
    And I should see rdpp algorithm as entered

  @Saturn_Regression
  Scenario:Create a rule with Modify Result Set multiple Add and Rdpp Action with different contexts
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Modify Result Set Multiple Add"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Keyword Pattern" trigger on rules page
    When I create keyword pattern trigger with the given criteria and value
    When I save trigger
    Then I should see the trigger saved as "Keyword Pattern"
    When I again select "Modify Result Set" action on rules page
    And I select the modify result set with operator as "Add"
    And I create Modify Result Set with required data
    And I de select all the context options selected by default
    And I select "DESKTOP" as context
    When I select context action as "search"
    And I save action and click continue
    Then I should see the action saved as "Modify Result Set" on Rules Page
    When I again select "Modify Result Set" action on rules page
    And I select the modify result set with operator as "Add"
    And I create Modify Result Set with required data
    And I click save action
    Then I should see "Context Alert" with "Same Context"
    And I click "OK" button on same context alert popup
    And I click on context
    And I de select the context option "DESKTOP"
    And I select "TABLET" as context
    And I click save action
    Then I should see "Context Alert" with "Same Context"
    When I click "OK" button on alert popup
    And I uncheck the apply non canvas option
    And I save action on rules page
    Then I should see the action saved as "Modify Result Set" on Rules Page
    When I again select "RDPP Algorithm" action on rules page
    And I create rdpp with random algorithm action value
    And I enter rdpp algorithm Effective and Expiration dates
    And I de select all the context options selected by default
    And I select "DESKTOP" as context
    When I select context action as "browse"
    And I save action on rules page
    Then I should see the action saved as "RDPP Algorithm" on Rules Page
    When I save rule
    Then I should see the rule saved
    Then I should see rule details as entered Rule Name, Rule Priority, Effective and Expiration dates,fob
    Then I should see modify result set operator with operator as "Add"
    And I should see Modify Result Set with the data as entered
    And I should see rdpp algorithm as entered

  @Saturn_Regression
  Scenario:Create Managed Facet Action with suppress values and verify the suppress values in DB
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Manage Facets"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Category Ids" trigger on rules page
    And I create Category Ids trigger with category Id
    And I save trigger
    When I again select "Manage Facets" action on rules page
    And I select the below values from Manage Facets list
      |Age|
    Then I should see Value Management column in select facet display
    When I click on Edit on Value Management column
    Then I should be on the window titled "Manage Facet Values"
    When I suppress any value and save the suppression dialog
    When I click on Edit on Value Management column
    Then I should see the same suppressed value as selected
    When I select context action as "browse"
    And I save action and click continue
    And I save rule
    Then I should see the rule saved
    Then I should see the same suppressed value as selected in DB

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

  @Saturn_Regression
  Scenario:verify cancel,delete and edit functionality of triggers on rules page
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Keyword pattern trigger"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Keyword Pattern" trigger on rules page
    When I create keyword pattern trigger with the given criteria and value
    When I save trigger
    Then I should see the trigger saved as "Keyword Pattern"
    When I click on  Edit trigger
    And I edit the keyword pattern trigger with other criteria and value without saving the trigger
    When I click cancel trigger
    Then I should see the keyword pattern trigger with given details
    When I click on  Edit trigger
    And I edit the keyword pattern trigger with other criteria and value
    And I click save trigger
    And I save rule
    Then I should see the rule saved
    Then I should see the keyword pattern trigger with given details
    When I click on  Edit rule
    And I click on  Edit trigger
    When I click on Delete button
    Then the trigger should be deleted

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

  @Saturn_Regression
  Scenario:Create a rule with Multiple Execute new search action+ all other actions(except display message action)
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Keyword pattern trigger"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Keyword Pattern" trigger on rules page
    When I create keyword pattern trigger with the given criteria and value
    And I save trigger
    Then I should see the trigger saved as "Keyword Pattern"
    When I again select "Execute New Search" action on rules page
    And I enter replace term value as "term"
    And I select Search Query as "Replace ALL Customer Search"
    And I de select all the context options selected by default
    And I select "DESKTOP" as context
    And I select context action as "browse"
    And I save action and click continue
    Then I should see the action saved as "Execute New Search"
    When I again select "Execute New Search" action on rules page
    And I enter replace term value as "term"
    And I select Search Query as "Replace ALL Customer Search"
    And I click on context
    When I de select the context option "DESKTOP"
    And I select "TABLET" as context
    And I uncheck the apply non canvas option
    And I save action on rules page
    Then I should see the action saved as "Execute New Search"
    When I again select "Category Redirect" action on rules page
    When I create "Category Redirect" action with "Category Id"
    When I select context action as "search"
    And I de select all the context options selected by default
    And I select "All Mobile" as context
    And I click save action
    Then I should see the "Execute New Search Compatibility Alert"
    When I click "OK" button on alert popup
    When I again select "Manage Facets" action
    And I select the below values from Manage Facets list
      |Age Group|
      |Color Family|
    Then I should see Value Management column in select facet display
    And I de select all the context options selected by default
    And I select "All Mobile" as context
    And I de select the context option "TABLET"
    And I select "DESKTOP" as context
    And I select context action as "browse"
    And I click save action
    Then I should see the "Execute New Search Compatibility Alert"
    When I click "OK" button on alert popup
    When I again select "Manage Featured Facet" action
    And I enter Manage Featured Facets value
    And I de select the context option "All Mobile"
    And I select "TABLET" as context
    And I select "All Mobile" as context
    And I select context action as "search"
    And I click save action
    Then I should see the "Execute New Search Compatibility Alert"
    When I click "OK" button on alert popup
    When I again select "Modify Result Set" action
    And I select the modify result set with operator as "Replace"
    And I create Modify Result Set with required data
    And I select context action as "browse"
    And I click save action
    Then I should see the "Execute New Search Compatibility Alert"
    When I click "OK" button on alert popup
    When I again select "PDP Redirect" action
    And I create PDP Redirect action with first PPID value
    And I select context action as "browse"
    And I click save action
    Then I should see the "Execute New Search Compatibility Alert"
    When I click "OK" button on alert popup
    When I again select "RDPP Algorithm" action
    And I create rdpp with random algorithm action value
    And I enter rdpp algorithm Effective and Expiration dates
    When I select context action as "browse"
    And I click save action
    Then I should see the "Execute New Search Compatibility Alert"
    When I click "OK" button on alert popup
    When I again select "Show Master or Member Products" action
    And I select the display as "Show only the Master Products"
    When I select context action as "search"
    And I click save action
    Then I should see the "Execute New Search Compatibility Alert"
    When I click "OK" button on alert popup
    When I again select "Show Media" action
    And I select "Canvas" as media type
    And I create show media with "Canvas Id"
    When I select context action as "search"
    And I de select the context option "All Mobile"
    And I select "DESKTOP" as context
    And I click save action
    Then I should see the "Execute New Search Compatibility Alert"
    When I click "OK" button on alert popup
    When I again select "URL Redirect" action
    When I select context action as "search"
    And I de select the context option "DESKTOP"
    And I select "TABLET" as context
    And I click save action
    Then I should see the "Execute New Search Compatibility Alert"

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
    And I select "TABLET" as context
    When I select context action as "browse"
    And I click save action
    Then I should see "Context Alert" with "Same Context"
    And I click "OK" button on same context alert popup
    When I de select the context option "TABLET"
    And I select "DESKTOP" as context
    When I de select the navigation context "search"
    And I uncheck the apply non canvas option
    When I save action on rules page
    And I save rule
    Then I should see the action saved as "RDPP Algorithm" on Rules Page
    Then I should see the rule saved
    Then I should see rule details as entered Rule Name, Rule Priority, Effective and Expiration dates,fob
    Then I should see that "Category Redirect" has "Category Id"  same as entered
    Then I should see rdpp algorithm as entered

  @Saturn_Regression
  Scenario:Create a rule with Modify result set with multiple boost.if same context, should get error message
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
    And I de select all the context options selected by default
    And I select "DESKTOP" as context
    When I select context action as "search"
    And I save action and click continue
    Then I should see the action saved as "Modify Result Set" on Rules Page
    When I again select "Modify Result Set" action on rules page
    And I select the modify result set with operator as "Boost"
    And I create Modify Result Set with required data
    And I again save action
    Then I should see "Context Alert" with "Same Context"
    And I click "OK" button on same context alert popup
    And I click on context
    And I de select the context option "DESKTOP"
    And I select "DESKTOP" as context
    And I again save action
    Then I should see "Context Alert" with "Same Context"
    When I click "OK" button on alert popup
    And I uncheck the apply non canvas option
    And I save action on rules page
    Then I should see the action saved as "Modify Result Set" on Rules Page

  @Saturn_Regression
  Scenario:Create a rule with Modify result set for options  with same context(Boost,add,remove,boost attribute value) & do validation in rule detail page
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Modify result set same context"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Keyword Pattern" trigger on rules page
    When I create keyword pattern trigger with the given criteria and value
    And I save trigger
    Then I should see the trigger saved as "Keyword Pattern"
    When I again select "Modify Result Set" action on rules page
    And I select the modify result set with operator as "Boost"
    And I create Modify Result Set with required data
    And I de select all the context options selected by default
    And I select "DESKTOP" as context
    When I select context action as "search"
    And I save action and click continue
    Then I should see the action saved as "Modify Result Set" on Rules Page
    When I again select "Modify Result Set" action on rules page
    And I select the modify result set with operator as "Add"
    And I create Modify Result Set with required data
    And I should see the same context "DESKTOP" for the action
    And I save action and click continue
    Then I should see the action saved as "Modify Result Set" on Rules Page
    And I should see the same context "DESKTOP" for the action
    When I again select "Modify Result Set" action on rules page
    And I select the modify result set with operator as "Remove"
    And I create Modify Result Set with required data
    And I click on context
    And I de select the context option "DESKTOP"
    And I select "TABLET" as context
    And I uncheck the apply non canvas option
    And I click save action
    Then I should see the action saved as "Modify Result Set" on Rules Page
    And I should see the context "DESKTOP" for the "first" action
    And I should see the context "DESKTOP" for the "second" action
    And I should see the context "TABLET" for the "third" action
    When I click on Edit Action
    And I uncheck the apply non canvas option
    And I save action and click continue
    And I should see the context "TABLET" for the "first" action
    And I should see the context "TABLET" for the "second" action
    And I should see the context "TABLET" for the "third" action
    When I again select "Modify Result Set" action on rules page
    And I select the modify result set with operator as "Boost Attribute Values"
    And I create "Modify Result Set" with Boost Attribute
    And I de select the context option "TABLET"
    And I select "ALL MEDIA" as context
    And I uncheck the apply non canvas option
    And I save action and click continue
    Then I should see the action saved as "Modify Result Set"
    And I should see the context "TABLET" for the "first" action
    And I should see the context "TABLET" for the "second" action
    And I should see the context "TABLET" for the "third" action
    And I should see the context "ALL Media" for the "fourth" action
    When I save rule
    Then I should see the rule saved
    When I click on  Edit rule
    And I click on Edit Action
    And I uncheck the apply non canvas option
    And I save action
    Then I should see rule details as entered Rule Name, Rule Priority, Effective and Expiration dates,fob
    Then I should see the "first" Modify Result Set Action with "Boost" operator and data as entered
    And I should see the "Second" Modify Result Set Action with "Add" operator and data as entered
    And I should see the "third" Modify Result Set Action with "Remove" operator and data as entered
    And I should see the "fourth" Modify Result Set Action with "Boost Attribute" operator and data as entered
    And I should see the context "ALL Media" for the "first" action
    And I should see the context "ALL Media" for the "second" action
    And I should see the context "ALL Media" for the "third" action
    And I should see the context "ALL Media" for the "fourth" action

  @Saturn_Regression
  Scenario:Create a rule with Modify result set with following option with different context(Replace,Boost,add,remove,boost attribute value).if same context, should get error message
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Modify result set different context"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Category Ids" trigger on rules page
    And I create Category Ids trigger with category Id
    And I save trigger
    Then I should see the trigger saved as "Category Ids"
    When I again select "Modify Result Set" action on rules page
    And I select the modify result set with operator as "Replace"
    And I create Modify Result Set with required data
    And I de select all the context options selected by default
    And I select "DESKTOP" as context
    When I select context action as "search"
    And I save action and click continue
    Then I should see the action saved as "Modify Result Set" on Rules Page
    When I again select "Modify Result Set" action on rules page
    And I select the modify result set with operator as "Boost"
    And I create Modify Result Set with required data
    And I click save action
    Then I should see "Context Alert" with "Same Context"
    And I click "OK" button on same context alert popup
    And I click on context after clicking save action
    And I de select the context option "DESKTOP"
    And I select "TABLET" as context
    And I uncheck the apply non canvas option
    And I again save action
    Then I should see the action saved as "Modify Result Set" on Rules Page
    And I should see the context "DESKTOP" for the "first" action
    And I should see the context "TABLET" for the "second" action
    When I again select "Modify Result Set" action on rules page
    And I select the modify result set with operator as "Add"
    And I create Modify Result Set with required data
    When I select context action as "search"
    And I click save action
    Then I should see "Context Alert" with "Same Context"
    And I click "OK" button on same context alert popup
    And I de select all the context options selected by default
    And I select "All Mobile" as context
    And I de select the navigation context "search"
    And I select context action as "browse"
    And I again save action
    Then I should see the action saved as "Modify Result Set" on Rules Page
    And I should see the context "DESKTOP" for the "first" action
    And I should see the context "TABLET" for the "second" action
    And I should see the context "ALL Mobile" for the "third" action
    When I again select "Modify Result Set" action on rules page
    And I select the modify result set with operator as "Remove"
    And I create Modify Result Set with required data
    When I select context action as "search"
    And I click save action
    Then I should see "Context Alert" with "Same Context"
    And I click "OK" button on same context alert popup
    And I de select all the context options selected by default
    And I select "Store Search and Send" as context
    And I again save action
    Then I should see the action saved as "Modify Result Set" on Rules Page
    And I should see the context "DESKTOP" for the "first" action
    And I should see the context "TABLET" for the "second" action
    And I should see the context "ALL MEDIA" for the "third" action
    When I again select "Modify Result Set" action on rules page
    And I select the modify result set with operator as "Boost Attribute Values"
    And I create "Modify Result Set" with Boost Attribute
    And I save action on rules page
    Then I should see "Context Alert" with "Same Context"
    And I click "OK" button on same context alert popup
    And I de select the context option "Store Search and Send"
    And I select "DESKTOP" as context
    And I save action and click continue
    Then I should see the action saved as "Modify Result Set"

#######-----------  CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-11462   #######-----------
  @csi @Saturn_Regression
  Scenario: As a Merchandising Manager, When I create a rule I would like the default match type for Keyword Pattern trigger to be set to Has Exact Match
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I select "Keyword Pattern" trigger on rules page
    Then I see "Has EXACT Match to" as default value for Keyword Pattern Trigger in Create Rule page

#######-----------  CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-27618  #######-----------
  @csi @Saturn_Regression
  Scenario: As a Site Merch User, when I create a rule in Saturn I want the default operator for multiple triggers should be 'or'
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I select "Keyword Pattern" trigger on rules page
    Then I should not see multiple trigger operator box on Create Rule page
    When I create keyword pattern trigger with the given criteria and value
    And I save trigger
    And I click on Add trigger
    Then I see multiple trigger operator box between first Trigger and second trigger with operator OR selected on Create Rule page

#######-----------  CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-27485  #######-----------
  @csi @Saturn_Regression
  Scenario: As a Site Merch User, I would like to see the default operator to be set to '>' when I create a Rule with Trigger type Result Set
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Result Set" trigger on rules page
    Then I see operator ">" selected by default with "75" percent on Create Rule page

#######-----------  CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-27105  -----------#######
  @csi @Saturn_Regression
  Scenario Outline: Confirm that the Context Container for the second action to be defaulted to close in Create Rule page when Apply to ALL noncanvas actions is checked
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I select "Keyword Pattern" trigger on rules page
    And I create keyword pattern trigger with the given criteria and value
    And I save trigger
    When I select "<Action Type>" action on rules page
    Then I see Context Container is in "open" state
    When I "check" Apply to ALL non-canvas actions check box
    And I populate required fields in "<Action Type>" action
    And I save action on rules page
    And I select "<compatible>" action on rules page
    Then I see Context Container is in "close" state

    Examples:
      |Action Type                    |
      |Category Redirect              |
      |Display Message                |
      |Manage Facets                   |
      |Modify Result Set              |
      |PDP Redirect                   |
      |RDPP Algorithm                 |
      |Show Master or Member Products |
      |URL Redirect                   |
      |Execute New Search             |

  @csi @Saturn_Regression
  Scenario Outline: Confirm that the Context Container for the second action to be defaulted to open in Create Rule page when Apply to ALL noncanvas actions is unchecked
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I select "Keyword Pattern" trigger on rules page
    And I create keyword pattern trigger with the given criteria and value
    And I save trigger
    When I select "<Action Type>" action on rules page
    Then I see Context Container is in "open" state
    When I "uncheck" Apply to ALL non-canvas actions check box
    And I populate required fields in "<Action Type>" action
    And I save action on rules page
    And I select "<compatible>" action on rules page
    Then I see Context Container is in "open" state

    Examples:
      |Action Type                    |
      |Category Redirect              |
      |Display Message                |
      |Manage Facets                   |
      |Modify Result Set              |
      |PDP Redirect                   |
      |RDPP Algorithm                 |
      |Show Master or Member Products |
      |URL Redirect                   |
      |Execute New Search             |

#######-----------  CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-28615  #######-----------
  @csi @Saturn_Regression
  Scenario: Confirm that Show Media Action drop down values are listed in alphanumeric order in Create Rule page
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I select "Show Media" action on rules page
    Then I see "Show Media Operator Types" in alphanumeric order on Rule page
      |Banners           |
      |Canvas            |
      |Copy Block Media  |
      |Zero Results Media|

#######-----------  CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-28628  #######-----------
  @csi @Saturn_Regression
  Scenario: Confirm that Modify Result Set Action drop down values are listed in alphanumeric order in Create Rule page
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I select "Modify Result Set" action on rules page
    Then I see "Modify Result Set Operator Types" in alphanumeric order on Rule page
      |Add                    |
      |Boost                  |
      |Boost Attribute Values |
      |Remove                 |
      |Replace                |

#######-----------  CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-31230  -----------#######
#######-----------  CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-34302  -----------#######
  @csi @Saturn_Regression
  Scenario Outline: Confirm the default Shopping Mode and Navigation selections in Context Container
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I select "<Action Type>" action on rules page
    Then I see default "Shopping Mode" selection in Context Container
    And I see default "Navigation" selection in Context Container

    Examples:
      |Action Type                    |
      |Category Redirect              |
      |Display Message                |
      |Manage Facets                  |
      |Modify Result Set              |
      |PDP Redirect                   |
      |RDPP Algorithm                 |
      |Show Master or Member Products |
      |URL Redirect                   |

############ CSI Story:  https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-91646 ##############
  @csi @Saturn_Regression
  Scenario: Confirm that the facets can be removed from Manage Facets action at row level without having to deselect in the facet scroll box on Create rule
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Manage Facets"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Category Ids" trigger on rules page
    And I create Category Ids trigger with category Id
    And I save trigger
    When I select "Manage Facets" action on rules page
    And I select the below values from Manage Facets list
      |Age Group   |
      |Activity    |
      |Brand       |
    Then I see Remove option on each facet row to the right of the Expiration Date
      |Age Group   |
      |Activity    |
      |Brand       |
    When I click on Remove option for the below facets
      |Age Group|
    And I see corresponding facet row is removed
    And I confirm that the corresponding facet is unchecked in the facet scroll box

  @csi @Saturn_Regression
  Scenario: Confirm that the facets can be removed from Manage Facets action at row level without having to deselect in the facet scroll box on Edit rule
    Given I login to Saturn as an "admin" user
    When I query Saturn DB for Manage Facets rule
    And I Simple Search for Manage Facets rule
    Then I see Rule detail page
    When I click on Edit for Rule
    Then I see rule in Edit mode
    When I click Edit on Manage Facet Action
    Then I see Manage Facet Action in Edit mode
    And I see Remove option on each facet row to the right of the Expiration Date
    And I click on Remove option
    And I see corresponding facet row is removed
    And I confirm that the corresponding facet is unchecked in the facet scroll box
    When I save action
    And I click on Save for Rule
    Then confirm that Rule is successfully saved
############################################################################

  @Saturn_Regression
  Scenario Outline:Verify rule state based on effective date and verify the publish flag is ON
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Rule State and Publish Flag"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I edit the "Effective Date" as "<date>"
    And I select "Category Ids" trigger on rules page
    And I create Category Ids trigger with category Id
    And I save trigger
    And I should see the trigger saved as "Category Ids"
    When I select "RDPP Algorithm" action on rules page
    And I create rdpp with random algorithm action value
    And I enter rdpp algorithm Effective and Expiration dates
    When I select context action as "browse"
    And I save action and click continue
    Then I should see the action saved as "RDPP Algorithm"
    And I save rule
    Then I should see the rule saved
    And I should see the rule status as "<status>"
    And I should see the publish flag as "ON"

    Examples:
      |date    |status|
      |Current Date|Effective|
      |Expired Date|Expired|
      |Future Date |Future|

  @Saturn_Regression
  Scenario Outline:Create Managed Facet Action with suppress values and verify the suppress values in DB
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Manage Facets"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Category Ids" trigger on rules page
    And I create Category Ids trigger with category Id
    And I save trigger
    When I again select "Manage Facets" action on rules page
    And I select the below values from Manage Facets list
      |Age|
    Then I should see Value Management column in select facet display
    And I should see the Value Management column value as "Alphanumeric"
    When I click on Edit on Value Management column
    Then I should be on the window titled "Manage Facet Values"
    And I select the "<Value Sequence>"
    When I suppress any value and save the suppression dialog
    When I click on Edit on Value Management column
    Then I should see the same suppressed value as selected
    When I select context action as "browse"
    And I save action and click continue
    And I save rule
    Then I should see the rule saved
    Then I should see the same suppressed value as selected in DB
    Examples:
      |Value Sequence|
      |Alphanumeric|
      |Product Count|
      |Sitewide-Stella|

  @Saturn_Regression
  Scenario:Create Managed Facet Action with suppress values and verify the suppress values in DB
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Manage Facets"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Keyword Pattern" trigger on rules page
    When I create keyword pattern trigger with all criteria
    When I save trigger
    When I again select "Manage Facets" action on rules page
    And I select the below values from Manage Facets list
      |Age|
    Then I should see Value Management column in select facet display
    And I should see the Value Management column value as "Alphanumeric"
    When I click on Edit on Value Management column
    Then I should be on the window titled "Manage Facet Values"
    And I select the value sequence as "Manual"
    When I enter the sequence values for random sequences in decreasing order
    When I click on Edit on Value Management column
    Then I should see the sequence values in increasing order
    When I select context action as "browse"
    And I save action and click continue
    Then I should see the action saved as "Manage Facets" on Rules Page
    When I again select "Manage Facets" action on rules page
    And I select the below values from Manage Facets list
      |Age|
    Then I should see Value Management column in select facet display
    And I should see the Value Management column value as "Alphanumeric"
    When I click on Edit on Value Management column
    Then I should be on the window titled "Manage Facet Values"
    And I select the value sequence as "Manual"
    When I enter the sequence values for random sequences in decreasing order
    When I click on Edit on Value Management column
    Then I should see the sequence values in increasing order
    When I select context action as "search"
    And I save action and click continue
    Then I should see the action saved as "Manage Facets" on Rules Page
    And I save rule
    Then I should see the rule saved

  @Saturn_Regression   @priority_two
  Scenario:User should see apply to all non canvas action as unchecked and disabled by default on both create rule page and rule detail page.
    Given I login to Saturn as an "admin" user
    And I enter Rule Name as "Show Media"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "keyword pattern" trigger on rules page
    When I create keyword pattern trigger with the given criteria and value
    When I save trigger
    Then I should see the trigger saved as "Keyword Pattern"
    When I select "Show Media" action on rules page
    And I select "Canvas" as media type
    Then I should see the "Apply to ALL non-canvas actions" as disabled

  @Saturn_Regression @priority_two
  Scenario:User should able to filp between 'Exact match' and 'contains option'
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "test"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Keyword Pattern" trigger on rules page
    Then I should see that search query option is "Has EXACT Match to"
    When I create keyword pattern trigger with all criteria
    When I save trigger
    When I select "RDPP Algorithm" action on rules page
    And I create rdpp with random algorithm action value
    And I enter rdpp algorithm Effective and Expiration dates
    When I select context action as "browse"
    And I save action and click continue
    And I save rule
    Then I should see that search query option is "Has EXACT Match to"
    When I click on  Edit rule
    When I select search Query option as "Contains"
    And I save rule
    Then I should see that search query option is "Has EXACT Match to"

  @Saturn_Regression
  Scenario Outline:Verify rule state based on effective date & expiry date and verify the publish flag is ON
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Rule State and Publish Flag"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I edit the "Effective Date" as "<date>"
    And I edit the "Expiry Date" as "<date>"
    And I select "Category Ids" trigger on rules page
    And I create Category Ids trigger with category Id
    And I save trigger
    And I should see the trigger saved as "Category Ids"
    When I select "RDPP Algorithm" action on rules page
    And I create rdpp with random algorithm action value
    And I enter rdpp algorithm Effective and Expiration dates
    When I select context action as "browse"
    And I save action and click continue
    Then I should see the action saved as "RDPP Algorithm"
    And I save rule
    Then I should see the rule saved
    And I should see the rule status as "<status>"
    And I should see the publish flag as "ON"

    Examples:
      |date    |status|
      |Expired Date|Expired|

  @Saturn_Regression
  Scenario Outline:Verify rule state based on effective date and verify the publish flag is ON
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Rule State and Publish Flag"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I edit the "Effective Date" as "<date>"
    And I select "Category Ids" trigger on rules page
    And I create Category Ids trigger with category Id
    And I save trigger
    And I should see the trigger saved as "Category Ids"
    When I select "RDPP Algorithm" action on rules page
    And I create rdpp with random algorithm action value
    And I enter rdpp algorithm Effective and Expiration dates
    When I select context action as "browse"
    And I save action and click continue
    Then I should see the action saved as "RDPP Algorithm"
    And I save rule
    Then I should see the rule saved
    And I should see the rule status as "<status>"
    And I should see the publish flag as "ON"

    Examples:
      |date    |status|
      |Current Date|Effective|
      |Future Date |Future|

  @Saturn_Regression @priority_two
  Scenario:User should able to flip between 'Exact match' and 'contains option'
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "test"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Keyword Pattern" trigger on rules page
    Then I should see that search query option is "Has EXACT Match to"
    When I create keyword pattern trigger with all criteria
    When I save trigger
    When I select "RDPP Algorithm" action on rules page
    And I create rdpp with random algorithm action value
    And I enter rdpp algorithm Effective and Expiration dates
    When I select context action as "browse"
    And I save action and click continue
    And I save rule
    Then I should see that search query option is "Has Exact Match to"
    When I click on  Edit rule
    And I click on  Edit trigger
    When I select search Query option as "Contains"
    And I save rule details page
    Then I should see that search query option is "Contains"


  @Saturn_Regression
  Scenario:Create a rule with Result set Trigger + Modify result set action Boost Operator
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
    And I select the modify result set with operator as "Boost"
    And I create Modify Result Set with required data
    When I select context action as "search"
    And I save action and click continue
    Then I should see the action saved as "Modify Result Set"
    When I save rule
    Then I should see the rule saved
    Then I should see modify result set operator with operator as "Boost"
    And I should see the same details entered for the Result Set  trigger
    And I should see Modify Result Set with the data as entered

  @Saturn_Regression
  Scenario:Create a rule with multiple show media action with Canvas option with same location with same context and validate error
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Show Media"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Keyword Pattern" trigger on rules page
    When I create keyword pattern trigger with the given criteria and value
    And I save trigger
    When I again select "Show Media" action on rules page
    And I select "Canvas" as media type
    And I create show media with "Canvas Id"
    When I select context action as "search"
    And I save action on rules page
    Then I should see the action saved as "Show Media"
    When I again select "Show Media" action on rules page
    Then I confirm that "Canvas" option is disabled

############# CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-99990 ##############
##### Create rule
  @csi
  Scenario: Confirm Check All Display Header Label on Manage Facet action value sequence popup on Create Rule page
    Given I login to Saturn as an "non_admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "MFA Value Display Check All"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Category Ids" trigger on rules page
    And I create Category Ids trigger with category Id
    And I save trigger
    And I select "Manage Facets" action on rules page
    And I select the below values from Manage Facets list
      |Age Group|
    Then I should see Value Management column in select facet display
    And I should see the Value Management column value as "Alphanumeric"
    When I click on Edit on Value Management column
    Then I should be on the window titled "Manage Facet Values"
    And I select the "<Value Sequence>"
    And I see "Check All" label with associated checkbox placed below Display label
    And I confirm Display checkboxes associated to all the facets are checked by default
    When I uncheck the Check All checkbox on Manage Facet value management popup
    Then I confirm all Display checkboxes associated to the facets are unselected
    And I select Display checkbox/checkboxes on "Manage Facet Values" popup
    And I sequence the selected values on "Manage Facet Values" popup
    And I click Save on "Manage Facet Values" popup
    And I click Save Action
    And I click Save Rule
    Then I confirm the Rule is saved on UI with selected values checked
    And I confirm the Rule is saved in DB with selected values checked
