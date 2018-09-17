Feature: HAD Functional tests common to MCOM and BCOM

####--------------- CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-42673   -------------####
####--------------- CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-44420    ------------####
@csi @Saturn_Regression @Must_Pass
Scenario: Confirm alphanumeric pagination search for attribute value in HAD panel
  Given I login to Saturn as an "admin" user
  When I navigate to "HAD" under Administration
  And I search for HAD data by "Attribute_Name" with "Has exact match to"
  And I select HAD attribute
  Then I see search results in HAD panel
  And I should see pagination options
  When I click any enabled value in alphanumeric pagination
  Then I should see page with search results for selected value

@csi @Saturn_Regression
Scenario: Confirm that pagination options are not visible with HAD search for invalid attribute value
  Given I login to Saturn as an "admin" user
  When I navigate to "HAD" under Administration
  And I search for invalid attribute value
  Then I should see 0 Matching Items
  And I should not see pagination options for "invalid attribute value"

@csi @Saturn_Regression
Scenario: Confirm that pagination options are not visible with HAD search for attribute name with no values
  Given I login to Saturn as an "admin" user
  When I navigate to "HAD" under Administration
  And I search HAD data for "attribute with 15 or less values"
  And I select HAD attribute
  Then I should not see pagination options for "attribute with 15 or less values"

####--------------- CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-38934    ------------####
@csi @Saturn_Regression @Must_Pass
Scenario: Confirm numeric entry pagination search for attribute value in HAD panel
  Given I login to Saturn as an "admin" user
  When I navigate to "HAD" under Administration
  And I search HAD data for "attribute with more than 15 values"
  And I select HAD attribute
  And I should see pagination options
  And I confirm numeric entry pagination

####--------------- CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-40151    ------------####
####--------------- CSI Defect: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=D-30366    ------------####
@csi @Saturn_Regression @Must_Pass
Scenario Outline: Confirm Holistic attribute data search by certain Find free text entry filter options using 'Has Exact Match To' value
  Given I login to Saturn as an "admin" user
  When I navigate to "HAD" under Administration
  And I search for HAD data by "<Type>" with "Has exact match to"
  And I select HAD attribute
  Then I see HAD results for "<Type>" match data listed for "Has exact match to"


  Examples:
    |Type 				|
    |Dest_Attribute_Name|
    |Attribute_Name		|
    |Attr_Value     	|
    |Index Value As 	|
    |Composite			|
    |Autocomplete Term	|

@csi @Saturn_Regression
Scenario Outline: Confirm Holistic attribute data search by certain Find free text entry filter options using 'Contains'
  Given I login to Saturn as an "admin" user
  When I navigate to "HAD" under Administration
  And I search for HAD data by "<Type>" with "Contains"
  And I select HAD attribute
  Then I see HAD results for "<Type>" match data listed for "Contains"

  Examples:
    |Type 				|
    |Dest_Attribute_Name|
    |Attribute_Name		|
    |Attr_Value     	|
    |Index Value As 	|
    |Composite			|
    |Autocomplete Term	|

@Saturn_Regression
Scenario:Fetch HAD using attribute name, save changes and verify the database
  Given I login to Saturn as an "admin" user
  When I navigate to "HAD" under Administration
  Then I should be on "Holistic Attribute Data" page
  And I search for HAD data by "Attribute_Name" with "Has exact match to"
  And I select HAD attribute
  Then I see search results in HAD panel
  When I modify "Index value" option in holistic attribute page
  And I modify "Composite type" dropdown list in holistic attribute page
  And I click on "Save Changes" button in holistic attribute page
  Then I should verify Index value option as in database
  And I should see composite value as entered in database

@Saturn_Regression
Scenario: Fetch Attribute using Facet with sub filter as true
  Given I login to Saturn as an "admin" user
  When I navigate to "HAD" under Administration
  Then I should be on "Holistic Attribute Data" page
  And I search for HAD data by "Facet" with "True"
  And I click Go button on had page
  And I should see search results in holistic attribute page
  When I select HAD attribute
  Then I should see facet as "Y" in data base

@Saturn_Regression
Scenario: Fetch Attribute using Facet with sub filter as true
  Given I login to Saturn as an "admin" user
  When I navigate to "HAD" under Administration
  Then I should be on "Holistic Attribute Data" page
  And I search for HAD data by "Facet" with "False"
  And I click Go button on had page
  And I should see search results in holistic attribute page
  When I select HAD attribute
  Then I should see facet as "N" in data base

@Saturn_Regression
Scenario: Fetch Attribute using Index Value with sub filter as False
  Given I login to Saturn as an "admin" user
  When I navigate to "HAD" under Administration
  Then I should be on "Holistic Attribute Data" page
  And I search for HAD data by "Index Value" with "False"
  And I should see search results in holistic attribute page
  When I click on the first search result
  Then I should see Index Value as unchecked in holistic attribute page
  And I should see Index Value as "N" in database
  When I check Index Value header check box  in holistic attribute page
  Then I Click save changes button
  Then I should see facet as "Y" in data base

  @Saturn_Regression
  Scenario: Fetch HAD using attribute name with 'contains' option, save changes and verify the database
    Given I login to Saturn as an "admin" user
    When I navigate to "HAD" under Administration
    Then I should be on "Holistic Attribute Data" page
    And I select the filter "Attribute Name" on HAD page
    Then I enter for "color" as "Contains" in search field in holistic attribute page
    And I click Go button on had page
    When I select any attribute form "New attribute" section in holistic attribute page
    Then I should see attribute name consists of term "color"

######## CSI Story:  https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-89902 ###########
@csi @Saturn_Regression
Scenario: Confirm that the results under New & Changed Values section do not have a second line under the Attribute values with the text 'example'
  Given I login to Saturn as an "admin" user
  When I navigate to "HAD" under Administration
  Then I confirm that the second line under the Attribute values under New & Changed Values section displays Attribute Name
