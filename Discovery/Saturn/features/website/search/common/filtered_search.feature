Feature: Filtered Search functionality tests

  As a valid Saturn user
  I want to filtered search functionality for Rules and Trigger Groups

@Saturn_dry_run @Saturn_Regression
Scenario:Verify search filter and navigation dropdown values on find rules page
  Given I login to Saturn as an "admin" user
  When I navigate to "Find Rules" under Rules
  Then I should be on the find rules page
  Then I should see the below options in the filter type dropdown
    |Select Filter|
    |Creator|
    |Created Date|
    |Effective Date - Expiration Date|
    |FOB|
    |Modifier|
    |Modified Date|
    |Rule Name|
    | Published|
    | Trigger Group Name|
  And I should see the below options in the view dropdown
    |All|
    |Search|
    |Browse|

@Saturn_dry_run @Saturn_Regression
Scenario:Verify search results for find rules filter value Creator
  Given I login to Saturn as an "admin" user
  When I navigate to "Find Rules" under Rules
  Then I should be on the find rules page
  When I select the filter as "Creator"
  And I enter the "Creator" search value
  And I select the nav type as "Search"
  And I click Go button for filtered search
  Then I should see the search results with same "Creator"
  And I verify pagination on find rules page

@Saturn_dry_run @Saturn_Regression
Scenario:Verify search results for find rules filter value Created Date
  Given I login to Saturn as an "admin" user
  When I navigate to "Find Rules" under Rules
  Then I should be on the find rules page
  When I select the filter as "Created Date"
  And I enter the "Created" date
  And I select the nav type as "Search"
  And I click Go button for filtered search
  Then I should see the search results with same "Created Date"

@Saturn_dry_run @Saturn_Regression
Scenario:Verify search results for find rules filter value Effective Date - Expiration Date
  Given I login to Saturn as an "admin" user
  When I navigate to "Find Rules" under Rules
  Then I should be on the find rules page
  When I select the filter as "Effective Date - Expiration Date"
  And I enter the effective and expiry dates
  And I select the nav type as "Browse"
  And I click Go button for filtered search
  And I should see the search results with same "Effective Date"
  Then I should see the search results with same "Expiry Date"

@Saturn_dry_run @Saturn_Regression
Scenario:Verify search results for find rules filter value FOB
  Given I login to Saturn as an "admin" user
  When I navigate to "Find Rules" under Rules
  Then I should be on the find rules page
  When I select the filter as "FOB"
  And I select FOB value
  And I select the nav type as "Browse"
  And I click Go button for filtered search
  Then I should see the search results with same "FOB"

@Saturn_dry_run @Saturn_Regression
Scenario:Verify search results for find rules filter value Modifier
  Given I login to Saturn as an "admin" user
  When I navigate to "Find Rules" under Rules
  Then I should be on the find rules page
  When I select the filter as "Modifier"
  And I enter the "Modifier" search value
  And I select the nav type as "Browse"
  And I click Go button for filtered search
  Then I should see the search results with same "Modifier"

@Saturn_dry_run @Saturn_Regression
Scenario:Verify search results for find rules filter value Modified Date
  Given I login to Saturn as an "admin" user
  When I navigate to "Find Rules" under Rules
  Then I should be on the find rules page
  When I select the filter as "Modified Date"
  And I enter the "Modified" date
  And I select the nav type as "Search"
  And I click Go button for filtered search
  Then I should see the search results with same "Modified Date"

@Saturn_dry_run @Saturn_Regression
Scenario:Verify search results for find rules filter value Rule Name
  Given I login to Saturn as an "admin" user
  When I navigate to "Find Rules" under Rules
  Then I should be on the find rules page
  When I select the filter as "Rule Name"
  And I enter the "Rule Name" search value
  And I select the nav type as "Search"
  And I click Go button for filtered search
  Then I should see the search results with same "Rule Name"

@Saturn_dry_run @Saturn_Regression
Scenario:Verify search results for find rules filter value Published
  Given I login to Saturn as an "admin" user
  When I navigate to "Find Rules" under Rules
  Then I should be on the find rules page
  When I select the filter as "Published"
  And I click on "Publish Filter" on find rules page
  And I select the nav type as "Browse"
  Then I click Go button for filtered search
  Then I should see the search results with same "Publish Filter"

@Saturn_dry_run @Saturn_Regression
Scenario:Verify search results for find rules filter value Trigger Group Name
  Given I login to Saturn as an "admin" user
  When I navigate to "Find Rules" under Rules
  Then I should be on the find rules page
  When I select the filter as "Trigger Group Name"
  And I enter the "Trigger Group Name" search value
  And I select the nav type as "Browse"
  And I click Go button for filtered search
  Then I should see the search results with same "Trigger Group Name"

@Saturn_dry_run @Saturn_Regression
Scenario:Verify search filter dropdown values on find trigger groups page
  Given I login to Saturn as an "admin" user
  When I navigate to "Find Trigger Groups" under Rules
  Then I should be on the find trigger groups page
  Then I should see the below options in the filter type dropdown
    |Trigger Group Name|
    |Description|

@Saturn_dry_run @Saturn_Regression
Scenario:Verify search functionality for find trigger groups filter value Trigger Group Name
  Given I login to Saturn as an "admin" user
  When I navigate to "Find Trigger Groups" under Rules
  Then I should be on the find trigger groups page
  When I select the filter as "Trigger Group Name"
  And I enter the "Trigger Group Name" search value
  And I click Go
  Then I should see the search results with same "Trigger Group Name For Group"

@Saturn_dry_run @Saturn_Regression
Scenario:Verify search functionality for find trigger groups filter value Description
  Given I login to Saturn as an "admin" user
  When I navigate to "Find Trigger Groups" under Rules
  Then I should be on the find trigger groups page
  When I select the filter as "Description"
  And I enter the "Description" search value
  And I click Go
  Then I should see the search results with same "Description"

@Saturn_Regression
Scenario:Verify sort trigger group name column on find trigger group page
  Given I login to Saturn as an "admin" user
  When I navigate to "Find Trigger Groups" under Rules
  Then I should be on the find trigger groups page
  Then I should see the trigger group names in "ascending order"
  When I click on the sort icon for sorting the trigger group name column
  Then I should see the trigger group names in "descending order"



