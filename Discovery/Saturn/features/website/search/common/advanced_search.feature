@Saturn_advanced_search
Feature: Advanced Search  Functional tests common to MCOM and BCOM.
  As a valid Saturn non_admin user
  I want to Search Rules data using Advanced Search

######### --------------- Advanced Search without details --------------- #############
@csi @Saturn_Regression
Scenario Outline: Advanced Search by Trigger types in Advanced Search page without details
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  And I select "<Trigger_type>" trigger on Advanced Search Page
  And I press "Submit" in Advanced Search Page
  Then I see all matching rules displayed on UI
  And I confirm trigger search results without sub criteria from Advanced Search page match with database data

  Examples:
    |Trigger_type	 |
    |Facet Refinement|
    |Keyword Pattern |
    |Result Count    |
    |Result Set	     |

@csi @Saturn_Regression
Scenario Outline: Advanced Search by Action types in Advanced Search page without details
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  And I select "<Action_type>" action on Advanced Search Page
  And I press "Submit" in Advanced Search Page
  Then I see all matching rules displayed on UI
  And I confirm action search results without sub criteria from Advanced Search page match with database data

  Examples:
    |Action_type	               |
    |Category Redirect             |
    |Display Message               |
    |Execute New Search            |
    |Index/Follow                  |
    |Manage Facets                 |
    |Manage Featured Facet         |
    |Modify Result Set             |
    |PDP Redirect                  |
    |RDPP Algorithm                |
    |Show Master or Member Products|
    |Show Media                    |
    |URL Redirect                  |

################### -------------------------- Category Ids Trigger --------------------- ###################
 ############ CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-52669 #############
@csi @Saturn_Regression
Scenario Outline: Advanced Search by Category ID trigger with Included Category Ids
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  And I select "Category Ids" trigger on Advanced Search Page
  And I enter "<category_id_input_type>" in Included Category Ids field for Category ID Trigger on Advanced Search Page
  And I press "Submit" in Advanced Search Page
  Then I see all matching rules displayed on UI
  And I confirm Category ID trigger with "<category_id_input_type>" and Included Category Ids search results from Advanced Search page match DB

  Examples:
    |category_id_input_type                  |
    |Single Category ID                      |
    |Multiple Category IDs separated by Comma|
    |Multiple Category IDs separated by Space|

@csi @Saturn_Regression
Scenario Outline: Advanced Search by Category ID trigger with Excluded Category Ids
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  When I select "Category Ids" trigger on Advanced Search Page
  And I enter "<category_id_input_type>" in Excluded Category Ids field for Category ID Trigger on Advanced Search Page
  And I press "Submit" in Advanced Search Page
  Then I see all matching rules displayed on UI
  And I confirm Category ID trigger with "<category_id_input_type>" and Excluded Category Ids search results from Advanced Search page match DB

  Examples:
    |category_id_input_type                  |
    |Single Category ID                      |
    |Multiple Category IDs separated by Comma|
    |Multiple Category IDs separated by Space|

@csi @Saturn_Regression
Scenario: Advanced Search by Category ID trigger with Included and Excluded Category Ids
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  When I select "Category Ids" trigger on Advanced Search Page
  And I enter "Single Category ID" in Included Category Ids field for Category ID Trigger on Advanced Search Page
  And I enter "Single Category ID" in Excluded Category Ids field for Category ID Trigger on Advanced Search Page
  And I press "Submit" in Advanced Search Page
  Then I see all matching rules displayed on UI
  And I confirm Category ID trigger with Included and Excluded Category Ids search results from Advanced Search page match DB

@csi @Saturn_Regression
Scenario: Advanced Search by Category ID trigger with action and context
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  When I select "Category Ids" trigger on Advanced Search Page
  And I enter "Single Category ID" in Included Category Ids field for Category ID Trigger on Advanced Search Page
  And I select "Modify Result Set" action on Advanced Search Page
  And I select Navigation type "Browse" on Advanced Search Page
  And I press "Submit" in Advanced Search Page
  Then I see all matching rules displayed on UI
#  And I confirm Category ID trigger with Action and Context rules search results match with the DB data

@csi @Saturn_Regression
Scenario Outline: Advanced Search by Category ID trigger with invalid search value
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  When I select "Category Ids" trigger on Advanced Search Page
  And I enter "<invalid_data>" in Included Category Ids field for Category ID Trigger on Advanced Search Page
  And I press "Submit" in Advanced Search Page
  Then I confirm alert message "<alert_message>"

  Examples:
    |invalid_data |alert_message                                                     |
    |invalid_value|Category IDs should be numbers separated by space AND/OR commas.  |
    |empty_value  |Please fill the fields with Included AND/OR Excluded Category IDs.|

################### -------------------------- Facet Refinement Trigger --------------------- ###################
############ CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-58323 #############

################### -------------------------- Keyword Pattern Trigger --------------------- ###################
############ CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-58322 ##############
@csi @Saturn_Regression
Scenario: Advanced Search UI layout of Keyword Pattern trigger
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  When I select "Keyword Pattern" trigger on Advanced Search Page
  Then I see "Has EXACT Match to" displayed for Keyword Pattern Trigger on Advanced Search page
  When I click on "Has EXACT Match to" for Keyword Pattern Trigger on Advanced Search page
  Then I see "Contains" displayed for Keyword Pattern Trigger on Advanced Search page
  Then I see "Keyword Pattern attributes" Types in alphanumeric order on Advanced Search Page
    |All 	         |
    |Brand			 |
    |Color			 |
    |Customer Service|
    |Gender			 |
    |Material		 |
    |Miscellaneous	 |
    |Occasion		 |
    |Product Line	 |
    |Product Noun	 |
    |Size            |
    |Special Size    |
    |Style           |
  And I see Keyword Pattern attribute value text field

@csi @Saturn_Regression
Scenario Outline: Advanced Search by Keyword Pattern trigger with attribute
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  When I select "Keyword Pattern" trigger on Advanced Search Page
  And I select "<Keyword_pattern_attribute>" from Keyword Pattern attributes dropdown
  And I press "Submit" in Advanced Search Page
  Then I see all matching rules displayed on UI
  And I confirm Keyword Pattern trigger with "<Keyword_pattern_attribute>" results match with database data

  Examples:
    |Keyword_pattern_attribute|
    |Brand			 		  |
    |Color			 		  |
    |Customer Service		  |
    |Gender			          |
    |Material		          |
    |Miscellaneous	          |
    |Occasion		          |
    |Product Line	          |
    |Product Noun	          |
    |Size                     |
    |Special Size             |
    |Style                    |

@csi @Saturn_Regression
Scenario Outline: Advanced Search by Keyword Pattern trigger with attribute and attribute value
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  When I select "Keyword Pattern" trigger on Advanced Search Page
  And I select "<Keyword_pattern_attribute>" from Keyword Pattern attributes dropdown
  And I enter "<attribute_value>" in Keyword Pattern attribute value text field
  And I press "Submit" in Advanced Search Page
  Then I see all matching rules displayed on UI
  And I confirm Keyword Pattern trigger with "<Keyword_pattern_attribute>" and "<attribute_value>" results match with database data

  Examples:
    |Keyword_pattern_attribute|attribute_value       |
    |Brand			 		  |Brand value	         |
    |Color			 		  |Color value	         |
    |Customer Service		  |Customer Service value|
    |Gender			          |Gender value          |
    |Material		          |Material Value        |
    |Miscellaneous	          |Miscellaneous value   |
    |Occasion		          |Occasion value		 |
    |Product Line	          |Product Line value	 |
    |Product Noun	          |Product Noun value    |
    |Size                     |Size value			 |
    |Special Size             |Special Size value    |
    |Style                    |Style value			 |

@csi @Saturn_Regression
Scenario Outline: Advanced Search by Keyword Pattern trigger with match type
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  When I select "Keyword Pattern" trigger on Advanced Search Page
  And I select Keyword Pattern trigger match type as "<match_type>"
  And I select "Brand" from Keyword Pattern attributes dropdown
  And I enter "<attribute_value>" in Keyword Pattern attribute value text field
  And I press "Submit" in Advanced Search Page
  Then I see all matching rules displayed on UI
  And I confirm the Keyword Pattern trigger with match type search results match with the DB data

  Examples:
    |match_type		   |
    |Has EXACT Match to|
    |Contains		   |

@csi @Saturn_Regression
Scenario: Advanced Search by Keyword Pattern trigger with action and context
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  When I select "Keyword Pattern" trigger on Advanced Search Page
  And I select "Display Message" action on Advanced Search Page
  And I select Navigation type "Browse" on Advanced Search Page
  And I press "Submit" in Advanced Search Page
  Then I see all matching rules displayed on UI
  And I confirm the "Keyword Pattern" trigger search results match with the DB data

################### -------------------------- Result Count Trigger --------------------- ###################
   ######### CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-58324 ####

@csi @Saturn_Regression
Scenario: Advanced Search UI layout of Result Count trigger
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  And I select "Result Count" trigger on Advanced Search Page
  Then I see Result Count Trigger types with default selection as "Greater Than" on Advanced Search page
    |Greater Than|
    |Less Than   |

@csi @Saturn_Regression
Scenario Outline: Confirm that Result Count trigger displays error message when user enters invalid value in the search parameter
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  And I select "Result Count" trigger on Advanced Search Page
  And I enter "<invalid_data>" for Result Count trigger with "Greater Than" on Advanced Search Page
  And I press "Submit" in Advanced Search Page
  Then I confirm alert message "<alert_message>"

  Examples:
    |invalid_data |alert_message                                                    |
    |non-numeric  |The value inserted is not a number. Value must be a whole number.|
    |decimal      |The value must be a whole number.                                |

@csi @Saturn_Regression
Scenario Outline: Advanced Search by Result Count trigger rules without sub details
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  And I select "Result Count" trigger on Advanced Search Page
  And I enter "empty_value" for Result Count trigger with "<result_count_type>" on Advanced Search Page
  And I press "Submit" in Advanced Search Page
  Then I see all matching rules displayed on UI
  And I confirm Result Count trigger with "<result_count_type>" and "without_value" match with database data

  Examples:
    |result_count_type|
    |Greater Than     |
    |Less Than        |

@csi @Saturn_Regression
Scenario Outline: Advanced Search by Result Count trigger rules with value
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  And I select "Result Count" trigger on Advanced Search Page
  And I enter "valid_results_count_value" for Result Count trigger with "<result_count_type>" on Advanced Search Page
  And I press "Submit" in Advanced Search Page
  Then I see all matching rules displayed on UI
  And I confirm Result Count trigger with "<result_count_type>" and "valid_results_count_value" match with database data

  Examples:
    |result_count_type|
    |Greater Than     |
    |Less Than        |

@csi @Saturn_Regression
Scenario Outline: Advanced Search by Result Count trigger with action and context in Advanced Search page
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  And I select "Result Count" trigger on Advanced Search Page
  And I enter "empty_value" for Result Count trigger with "<result_count_type>" on Advanced Search Page
  And I select "Display Message" action on Advanced Search Page
  And I select Navigation type "Browse" on Advanced Search Page
  And I press "Submit" in Advanced Search Page
  Then I see all matching rules displayed on UI
#  And I confirm the "combination of trigger action and context" rules search results match with the DB data

  Examples:
    |result_count_type|
    |Greater Than     |
    |Less Than        |


################### -------------------------- Result Set Trigger --------------------- ###################
######## CSI Story:  https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-56930 ###########
@csi @Saturn_Regression
Scenario: Advanced Search by Result Set Trigger

################### -------------------------- Category Redirect Action  ------------------- ##################
@csi @Saturn_Regression
Scenario: Advanced Search by Category Redirect action with Category ID
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  When I select "Category Redirect" action on Advanced Search Page
  When I enter Category ID in Category Id entry field
  And I press "Submit" in Advanced Search Page
  Then I see all matching rules displayed on UI
  And I confirm Category Redirect action with sub criteria search results from Advanced Search page match with database data

################### -------------------------- Display Message Action  ------------------- ##################
  ####### CSI Story:  https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-84470 ---##########

  @csi @Saturn_Regression
Scenario: Advanced Search by Display Message action with entire display message
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  When I select "Display Message" action on Advanced Search Page
  When I enter "entire message" in Message Displayed textarea
  And I press "Submit" in Advanced Search Page
  Then I see all matching rules displayed on UI
  And I confirm Display Message action with "entire message" search results from Advanced Search page match with database data

  @csi @Saturn_Regression
Scenario: Advanced Search by Display Message action with single term from message
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  When I select "Display Message" action on Advanced Search Page
  When I enter "single term" in Message Displayed textarea
  And I press "Submit" in Advanced Search Page
  Then I see all matching rules displayed on UI
  And I confirm Display Message action with "single term" search results from Advanced Search page match with database data

@manual
Scenario: Excel export of Display Message rules in Advanced Search page
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  When I select "Display Message" action on Advanced Search Page
  And I enter "single term" in Message Displayed textarea
  And I press "Submit" in Advanced Search Page
  Then I see all matching rules displayed on UI
  When I press "Export" in Advanced Search Page
  Then "advanced_search.csv" file downloaded
  And I confirm "advanced_search.csv" file match with UI data

################### -------------------------- Execute New Search Action  ------------------- ##################
   ########## CSI Story:  https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-86687 --##########
  @csi @Saturn_Regression
Scenario: Advanced Search by Execute New Search action with Replace term
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  And I select "Execute New Search" action on Advanced Search Page
  And I enter Term in Replace with Term entry field
  And I press "Submit" in Advanced Search Page
  Then I see all matching rules displayed on UI
  And I confirm Execute New Search action with sub criteria search results from Advanced Search page match with database data

  @csi @Saturn_Regression
Scenario: Advanced Search by Execute New Search action with the combination of trigger and context
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  And I select trigger from trigger type drop down
  And I enter trigger search parameters
  And I select "Execute New Search" action on Advanced Search Page
  And I enter Term in Replace with Term entry field
  And I select context options
  And I press "Submit" in Advanced Search Page
  Then I see all matching rules displayed on UI
  And I confirm action search results from Advanced Search page match with database data


################### -------------------------- Index/Follow Action   ------------------- ##################

################### -------------------------- Manage Facets Action  ------------------- ##################
  @csi @Saturn_Regression
Scenario: Manage Facets action default selections on Advanced Search page
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  When I select "Manage Facets" action on Advanced Search Page
  Then I see "Manage Facets" operator type All is selected by default
  And I see "Manage Facets" operator type values in alphanumeric order
    |Add           |
    |Remove        |
    |Replace       |

  @csi @Saturn_Regression
Scenario Outline: Advanced Search by Manage Facet Action with operator
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  And I select "Manage Facets" action on Advanced Search Page
  And I select Manage Facets operator "<operator_type>" on Advanced Search Page
  And I press "Submit" in Advanced Search Page
  Then I see all matching rules displayed on UI
  And I confirm Manage Facets action with sub criteria search results from Advanced Search page match with database data

  Examples:
    |operator_type |
    |All           |
    |Add           |
    |Remove        |
    |Replace       |

  @csi @Saturn_Regression
Scenario: Advanced Search by Manage Facet Action with operator and Facets
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  And I select "Manage Facets" action on Advanced Search Page
  And I select Manage Facets operator "Remove" on Advanced Search Page
  And I select Factes for Manage Facets action on Advanced Search Page
  And I press "Submit" in Advanced Search Page
  Then I see all matching rules displayed on UI
  And I confirm action search results from Advanced Search page match with database data


################### -------------------------- Manage Featured Facet Action  ------------------- ##################
  ####### CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-86102 ######
  ####### CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-86114 ######
  @csi @Saturn_Regression
Scenario: Manage Featured Facet action default selections on Advanced Search page
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  When I select "Manage Featured Facet" action on Advanced Search Page
  Then I see "Manage Featured Facet" operator type All is selected by default
  And I see "Manage Featured Facet" operator type values in alphanumeric order
    |All     |
    |Automate|
    |Disable |

  @csi @Saturn_Regression
Scenario Outline: Advanced Search by Manage Featured Facet action with operator type
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  When I select "Manage Featured Facet" action on Advanced Search Page
  And I select "<operator_type>" from Manage Featured Facet operator type drop down
  And I press "Submit" in Advanced Search Page
  Then I see all matching rules displayed on UI
  And I confirm Manage Featured Facet action with sub criteria search results from Advanced Search page match with database data

  Examples:
    |operator_type|
    |All          |
    |Automate     |
    |Disable      |

@csi @Saturn_Regression
Scenario Outline: Advanced Search by Manage Featured Facet action with the combination of trigger and context
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  And I select trigger from trigger type drop down
  And I enter trigger search parameters
  And I select "Manage Featured Facet" action on Advanced Search Page
  And I select "<operator_type>" from Manage Featured Facet operator type drop down
  And I select context options
  And I press "Submit" in Advanced Search Page
  Then I see all matching rules displayed on UI
  And I confirm action search results from Advanced Search page match with database data

  Examples:
    |operator_type|
    |All          |
    |Automate     |
    |Disable      |


################### -------------------------- Modify Result Set Action  ------------------- ##################
    ########### CSI Story:  https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-73869 -####
  @csi @Saturn_Regression
Scenario: Modify Result Set action default selections on Advanced Search page
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  When I select "Modify Result Set" action on Advanced Search Page
  Then I see "Modify Result Set" operator type All is selected by default
  And I see "Modify Result Set" operator type values in alphanumeric order
    |Add                   |
    |Boost                 |
    |Boost Attribute Values|
    |Remove                |
    |Replace               |

  @csi @Saturn_Regression
Scenario Outline: Advanced Search by Modify Result Set Action with operator
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  And I select "Modify Result Set" action on Advanced Search Page
  And I select Modify Result Set operator "<type>" on Advanced Search Page
  And I press "Submit" in Advanced Search Page
  Then I see all matching rules displayed on UI
  And I confirm Modify Result Set action with sub criteria search results from Advanced Search page match with database data

  Examples:
    |type                  |
    |All                   |
    |Add                   |
    |Boost                 |
    |Remove                |
    |Replace               |
    |Boost Attribute Values|

  ########### CSI Story:  https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-73752 -############
@csi @Saturn_Regression
Scenario: UI layout of Modify Result Set Action / Boost Attribute Values in Advanced Search page
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  And I select "Modify Result Set" action on Advanced Search Page
  And I select Modify Result Set operator "Boost Attribute Values" on Advanced Search Page
  Then I see that the UI for the Boost Attribute Values contains a label "Key in Attribute"
  And I see that the UI for the Boost Attribute Values contains a input type text with "Search" as a placeholder
  And I see that the UI for the Boost Attribute Values contains a list with the Facet names
  And I see that the UI for the Boost Attribute Values contains a label "Select Value(s)"
  And I see that the UI for the Boost Attribute Values contains a table header with labels "Attributes" and "Values"

@csi @Saturn_Regression
Scenario: Selection of facets on the Modify Result Set Action / Boost Attribute Values in Advanced Search page
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  And I select "Modify Result Set" action on Advanced Search Page
  And I select Modify Result Set operator "Boost Attribute Values" on Advanced Search Page
  Then I see that the UI for the Boost Attribute Values contains a list with the Facet names
  And I see that the UI for the Boost Attribute Values contains a label "Select Value(s)"
  And I see that the UI for the Boost Attribute Values contains a table header with labels "Attributes" and "Values"
  When I click on one of the items of the list
  Then I see a list containing all values of the selected facet under "Select Value(s)" label
  When I click on one or more facet value from facet values list
  Then I see that the item shows on the table at the bottom of Boost Attribute Values UI
  And I see that the facet value on the table have a "x" icon
  When I click on the "x" icon on a facet value
  Then I see that the facet value is removed from the table

@csi @Saturn_Regression
Scenario: Filtering of Facets on the Modify Result Set Action / Boost Attribute Values in Advanced Search page
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  And I select "Modify Result Set" action on Advanced Search Page
  And I select Modify Result Set operator "Boost Attribute Values" on Advanced Search Page
  Then I see that the UI for the Boost Attribute Values contains a label "Key in Attribute"
  And I see that the UI for the Boost Attribute Values contains a input type text with "Search" as a placeholder
  And I see that the UI for the Boost Attribute Values contains a list with the Facet names
  When I focus the search input and type a string pattern into the field
  Then I see that the Facet Names list was filtered to show only facets whose names contain the string pattern I typed
  When I clear the contents of the Search input
  Then I see that the Facet Names list shows the complete list of facets again

  ###### CSI Story:  https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-79266  --- #####
@csi @Saturn_Regression
Scenario: Advanced Search by Modify Result Set action and Boost Attribute Values without Facet selection
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  When I select "Modify Result Set" action on Advanced Search Page
  And I select Modify Result Set operator "Boost Attribute Values" on Advanced Search Page
  And I press "Submit" in Advanced Search Page
  Then I see all matching rules displayed on UI
  And I confirm Modify Result Set action with sub criteria search results from Advanced Search page match with database data

@csi @Saturn_Regression
Scenario Outline: Advanced Search by Modify Result Set action and Boost Attribute Values with single/multiple Facets
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  And I select "Modify Result Set" action on Advanced Search Page
  And I select Modify Result Set operator "Boost Attribute Values" on Advanced Search Page
  And I select "<facet_count>" Facet from Facets list for Modify Result Set
  And I select values from Select values list
  And I press "Submit" in Advanced Search Page
  Then I see all matching rules displayed on UI
  And I confirm action search results from Advanced Search page match with database data

  Examples:
    |facet_count|
    |single     |
    |multiple   |


################### -------------------------- PDP Redirect Action  ------------------- ##################
      ############ CSI Story:  https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-84471 ---#########
  @csi @Saturn_Regression
Scenario: Advanced Search by PDP Redirect action with Product ID
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  When I select "PDP Redirect" action on Advanced Search Page
  When I enter Product ID in PDP entry field
  And I press "Submit" in Advanced Search Page
  Then I see all matching rules displayed on UI
  And I confirm PDP Redirect action with Product ID search results from Advanced Search page match with database data

@manual
Scenario: Excel export of PDP Redirect rule in Advanced Search page
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  When I select "PDP Redirect" action on Advanced Search Page
  When I enter Product ID in PDP entry field
  And I press "Submit" in Advanced Search Page
  Then I see all matching rules displayed on UI
  When I press "Export" in Advanced Search Page
  Then "advanced_search.csv" file downloaded
  And I confirm "advanced_search.csv" file match with UI data

################### -------------------------- RDPP Algorithm Action  ------------------- ##################
       ############ CSI Story:  https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-61470 ---#########
  @csi @Saturn_Regression
Scenario Outline: Advanced Search by RDPP Algorithm Action with Effective and/or Expiration dates
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  And I select "RDPP Algorithm" action on Advanced Search Page
  And I select "<RDPP_dates>" for RDPP Algorithm action on Advanced Search Page
  And I press "Submit" in Advanced Search Page
  Then I see all matching rules displayed on UI
  And I confirm RDPP Algorithm action with "<RDPP_dates>" search results from Advanced Search page match with database data

  Examples:
    |RDPP_dates	                 |
    |Effective Date              |
    |Expiration Date             |
    |Effective & Expiration Dates|

################### -------------------------- Show Media Action  ------------------- ##################
  @csi @Saturn_Regression
Scenario Outline: Advanced Search by Show Media Action with Media type
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  And I select "Show Media" action on Advanced Search Page
  And I select "<Media_type>" for Show Media action on Advanced Search Page
  And I press "Submit" in Advanced Search Page
  Then I see all matching rules displayed on UI
  And I confirm Show Media action with "<Media_type>" search results from Advanced Search page match with database data

  Examples:
    |Media_type	  	    |
    |Banners            |
    |Canvas	  		    |
    |Copy Block Media   |
    |Zero Results Media |

################### -------------------------- URL Redirect Action  ------------------- #############################
   ######## CSI Story:  https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-84201 ----##################
  @csi @Saturn_Regression
Scenario Outline: Advanced Search by URL Redirect action with URL
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  And I select "URL Redirect" action on Advanced Search Page
  And I enter "<url_type>" in the URL entry field
  And I press "Submit" in Advanced Search Page
  Then I see all matching rules displayed on UI
  And I confirm URL Redirect action with "<url_type>" search results from Advanced Search page match with database data

  Examples:
    |url_type               |
    |Entire URL             |
    |Single term from URL   |
    |Multiple terms from URL|

@manual
Scenario: Excel export of URL Redirect rule in Advanced Search page
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  And I select "URL Redirect" action on Advanced Search Page
  And I enter "URL" in the URL entry field
  And I press "Submit" in Advanced Search Page
  Then I see all matching rules displayed on UI
  And I confirm action search results from Advanced Search page match with database data
  And I press "Export" in Advanced Search Page
  Then "advanced_search.csv" file downloaded
  And I confirm "advanced_search.csv" file match with UI data

#########################################################################################################
      ############ CSI Story:  https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-61932 ---#########
  @csi @Saturn_Regression
Scenario: Clear should clear all the search criteria on Advanced Search page
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  And I select "Category Redirect" action on Advanced Search Page
  And I select Navigation type "Browse" on Advanced Search Page
  And I press "Clear" in Advanced Search Page
  Then I see all search selections cleared

@manual
Scenario: CSV should export all the searched rules in Advanced Search page
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  And I select "Category Redirect" action on Advanced Search Page
  And I press "Submit" in Advanced Search Page
  Then I see all matching rules displayed on UI
  And I confirm action search results from Advanced Search page match with database data
  When I press "Export" in Advanced Search Page
  Then "advanced_search.csv" file downloaded
  And I confirm "advanced_search.csv" file match with UI data

################### CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-50299 #############
################### CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-60140 #############
  @csi @Saturn_Regression
Scenario Outline: Advanced Search by Navigation type
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  And I select Navigation type "<Navigation_Type>" on Advanced Search Page
  And I press "Submit" in Advanced Search Page
  Then I see all matching rules displayed on UI
  And I confirm context Navigation type "<Navigation_Type>" search results from Advanced Search page match with database data

  Examples:
    |Navigation_Type|
    |Browse 	    |
    |Search         |
    |Landing		|

  @csi @Saturn_Regression
Scenario Outline: Advanced Search by Device type
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  And I select Device type "<Device_Type>" on Advanced Search Page
  And I press "Submit" in Advanced Search Page
  Then I see all matching rules displayed on UI
  And I confirm context Device type "<Device_Type>" search results from Advanced Search page match with database data

  Examples:
    |Device_Type           |
    |Desktop               |
    |Tablet                |
    |All Mobile            |
    |Store Search and Send |

  @csi @Saturn_Regression
Scenario Outline: Advanced Search by Shopping Mode
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  And I select Shopping Mode "<Shopping_Mode>" on Advanced Search Page
  And I press "Submit" in Advanced Search Page
  Then I see all matching rules displayed on UI
  And I confirm context Shopping Mode "<Shopping_Mode>" search results from Advanced Search page match with database data

  Examples:
    |Shopping_Mode |
    |Site 	       |
    |Registry      |

  @csi @Saturn_Regression
Scenario Outline: Advanced Search by Locale
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  And I select Locale "<Locale>" on Advanced Search Page
  And I press "Submit" in Advanced Search Page
  Then I see all matching rules displayed on UI
  And I confirm context Locale "<Locale>" search results from Advanced Search page match with database data

  Examples:
    |Locale		        |
    |Domestic - US      |
    |International - ALL|

  @csi @Saturn_Regression
Scenario: Advanced Search with combination of all context types
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  When I select Device type "Desktop" on Advanced Search Page
  And I select Shopping mode "Site" on Advanced Search Page
  And I select Navigation type "Search" on Advanced Search Page
  And I select Locale "US" in Advanced Search Page
  And I press "Submit" in Advanced Search Page
  Then I see all matching rules displayed on UI
  And I confirm "combination of Context types" rules search results match with DB data

  @csi @Saturn_Regression
Scenario: Advanced Search with combination of trigger and context
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  And I select Trigger type from Trigger drop down
  And I enter search parameters for Trigger type
  And I select Device type "Desktop" on Advanced Search Page
  And I press "Submit" in Advanced Search Page
  Then I see all matching rules displayed on UI
  And I confirm "combination of trigger and context" rules search results match with DB data

  @csi @Saturn_Regression
Scenario: Advanced Search by Context with combination of Action
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  When I select "Category Redirect" action on Advanced Search Page
  And I select Device type "Desktop" on Advanced Search Page
  And I press "Submit" in Advanced Search Page
  Then I see all matching rules displayed on UI
  And I confirm "combination of action and context" rules search results match with DB data

@csi @Saturn_Regression
Scenario: Advanced Search by Context with combination of Trigger and Action
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  When I select Trigger type from Trigger drop down
  And I select "Category Redirect" action on Advanced Search Page
  And I select Device type "Desktop" on Advanced Search Page
  And I press "Submit" in Advanced Search Page
  Then I see all matching rules displayed on UI
  And I confirm "combination of trigger action and context" rules search results match with DB data

##################### CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-60784 #############

#################################################
################# CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-61883 #######################
@csi @Saturn_Regression
Scenario: Confirm improved numeric pagination with Advanced search results
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  When I select "Display Message" action on Advanced Search Page
  And I press "Submit" in Advanced Search Page
  Then I see all matching rules displayed on UI
  And I see Total number of results with page navigation options
  And I confirm numeric entry pagination on Advanced Search page

@csi @Saturn_Regression
Scenario: Confirm that an alert message displayed when user enters an invalid page number
  Given I login to Saturn as an "non_admin" user
  When I navigate to "Advanced Search" under Rules
  When I select "Display Message" action on Advanced Search Page
  And I press "Submit" in Advanced Search Page
  Then I see all matching rules displayed on UI
  When I enter invalid page number in Page Number entry field
  Then I see the alert message "Please enter valid page number"


