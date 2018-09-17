
Feature: Manage Featured Facet action  type added in action in  Saturn rules

  @domain_Saturn @feature_Core_search_improvements_2017 @release_7.11
  Scenario: Verify user able to create a rule with  "Manage Featured Facet" action  and "Disable" sub action
    Given I navigate to the Saturn login page
    When I enter login "credentials"
    And I navigate to Rules menu
    And I select Create rule
    And I enter Rule Name, Rule Priority, Effective and Expiration dates,fob
    And I select "Keyword Pattern" trigger
    And I click on "Add Action"
    And I select "Manage Featured Facet " action
    And I select "Disable" sub-action
    And I select search option in  context container
    And I de select apply non canvas
    And I save action
    And I save rule
    Then I should see rule saved

  @domain_Saturn @feature_Core_search_improvements_2017 @release_7.11
  Scenario: Verify user able to create a rule with  "Manage Featured Facet" action  and "Category Ids" trigger
    Given I navigate to the Saturn login page
    When I enter login "credentials"
    And I navigate to Rules menu
    And I select Create rule
    And I enter Rule Name, Rule Priority, Effective and Expiration dates,fob
    And I select "Category Ids" trigger on rules page
    And I create Category Ids trigger with category Id
    And I save trigger
    And I click on "Add Action"
    And I select "Manage Featured Facet " action
    And I select "Disable" sub-action
    When I select context action as "browse"
    And I de select apply non canvas
    And I save action
    And I save rule
    Then I should see rule saved

  @domain_Saturn @feature_Core_search_improvements_2017 @release_7.11
  Scenario: Verify warning message displaying when user selects "Disable" sub-action
    Given I navigate to the Saturn login page
    When I enter login "credentials"
    And I navigate to Rules menu
    And I select Create rule
    And I click on "Add Action"
    And I select "Manage Featured Facet " action
    And I select "Disable" sub-action
    Then I should see Warning message "Warning! This option will prevent Featured Facet Values for all searches matching the Trigger conditions."

   #FFV/B-76397
  @domain_Saturn @feature_Core_search_improvements_2017 @release_7.11
  Scenario: Verify  user able to save rule with  "Managed Featured Facet" action  with  "Disable" sub action  in  Edit  rule page
    Given I navigate to the Saturn login page
    When I enter login "credentials"
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "keyword pattern Compatibility"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Keyword Pattern" trigger on rules page
    And I create keyword pattern trigger with the given criteria and value
    And I save trigger
    And I should see the trigger saved as "Keyword Pattern"
    When I again select "Manage Featured Facet" action on rules page
    And I enter Manage Featured Facets value
    And I de select the context option "All Mobile"
    And I select "TABLET" as context
    And I select context action as "search"
    And I save action and click continue
    Then I should see the action saved as "Manage Featured Facet" on Rules Page
    When I save rule
    Then I should see the rule saved
    When I click on  Edit rule
    And I click on Edit Action
    And I select "Disable" sub-action
    Then I should see Warning message "Warning! This option will prevent Featured Facet Values for all searches matching the Trigger conditions."
    And I save action
    And I save rule details
    Then I should see rule saved

    #B-77085
  @domain_Saturn @feature_Core_search_improvements_2017 @release_7.11
  Scenario: Verify  user able to save "Managed Featured Facet" action  with  "Automate" sub action  in  create  rule page
    Given I navigate to the Saturn login page
    When I enter login "credentials"
    And I navigate to Rules menu
    And I select Create rule
    And I enter Rule Name, Rule Priority, Effective and Expiration dates,fob
    And I select "Keyword Pattern" trigger
    And I click on "Add Action"
    And I select "Manage Featured Facet " action
    And I select Automate sub-action
    Then I should see automate sub action warning message "Warning! This option is for the automated behavior only."
    When  I enter "30" in threshold filed
    And I select navigation
    And  I de select apply non canvas
    And I save action
    And I save rule
    Then I should see rule saved


    #B-77087
  @domain_Saturn @feature_Core_search_improvements_2017 @release_7.11
  Scenario: Verify "Managed Featured Facet" action  and "Automate" sub action displaying in  Action section  in  Edit  rule page
    Given I login to Saturn as an "admin" user
    When I navigate to "Create Rule" under Rules
    And I enter Rule Name as "Display Message"
    And I enter Rule Priority, Effective and Expiration dates, FOB
    And I select "Keyword Pattern" trigger on rules page
    When I create keyword pattern trigger with the given criteria and value
    And I save trigger
    Then I should see the trigger saved as "Keyword Pattern"
    When I select "Display Message" action on rules page
    And  I create "Display Message" action  with Display "message"
    And  I select context action as "browse"
    And I save action and click continue
    Then I should see the action saved as "Display Message"
    And I save rule
    Then I should see rule details as entered Rule Name, Rule Priority, Effective and Expiration dates,fob
    Then I should see display message as entered
    And I get the "Rule Name" to be used as a search value
    And I navigate to Rules menu
    And I click Find Rules
    When I select the filter as "Rule Name"
    And I enter rule detail search value as "Rule Name"
    And I select Rule
    And I click on  Edit rule
    And  I click on  Edit  in   Add Action section
    And I select "Manage Featured Facet " action
    And I select Automate sub-action
    Then I should see automate sub action warning message "Warning! This option is for the automated behavior only."
    And I should see threshold field  displaying  with text  "If the search result count is greater than"
    When I change threshhold value to "120"
    When I select context action as "browse" for the same action
    And I save action
    And I save rule details
    Then I should see rule saved

    #B-76394
  @domain_Saturn @feature_Core_search_improvements_2017 @release_7.11
  Scenario: Confirm that "Manage Featured Facet" Action is enabled for rule with "Keyword Pattern" triggers in Edit Rule Page
    Given I navigate to the Saturn login page
    When I enter login "credentials"
    And I navigate to Rules menu
    And I click Find Rules
    And I select Rule
    And I click on  Edit rule
    And  I click on  Edit  in   Add Trigger section
    And I click "Keyword Pattern" edit trigger
    And  I click on Edit Action
    Then I confirm "Manage Featured Facet" is enabled
    When I select "Manage Featured Facet" action
    And I select Automate sub-action
    Then I should see automate sub action warning message "Warning! This option is for the automated behavior only."
    When  I enter "30" in threshold filed
    And  I de select apply non canvas
    Then I save action
    And I save rule details
    Then I should see rule saved


  @domain_Saturn @feature_Core_search_improvements_2017 @release_7.11
  Scenario: Confirm that "Manage Featured Facet" Action is enabled for rule with "Result Set" triggers Edit Rule page
    Given I navigate to the Saturn login page
    When I enter login "credentials"
    And I navigate to Rules menu
    And I click Find Rules
    And I select Rule
    And I click on  Edit rule
    And  I click on  Edit  in   Add Trigger section
    And I populate "Result Set" trigger in Edit Rule page
    And  I click on  Edit  in   Add Action section
    Then I confirm "Manage Featured Facet" is enabled
    When I select "Manage Featured Facet" action
    And I select Automate sub-action
    Then I should see automate sub action warning message "Warning! This option is for the automated behavior only."
    When  I enter "30" in threshold filed
    And  I de select apply non canvas
    Then I save action
    And I save rule details
    Then I should see rule saved


  @domain_Saturn @feature_Core_search_improvements_2017 @release_7.11
  Scenario: Confirm that "Manage Featured Facet" Action is enabled for rule with "All Search" trigger in Edit rule page
    Given I navigate to the Saturn login page
    When I enter login "credentials"
    And I navigate to Rules menu
    And I click Find Rules
    And I select Rule
    And I click on  Edit rule
    And  I click on  Edit  in   Add Trigger section
    And I Select "All Search" trigger in Edit Rule Page
    And  I click on  Edit  in   Add Action section
    Then I confirm "Manage Featured Facet" is enabled
    When I select "Manage Featured Facet" action
    And I select Automate sub-action
    Then I should see automate sub action warning message "Warning! This option is for the automated behavior only."
    When  I enter "30" in threshold filed
    Then I save action
    And I save rule details
    Then I should see rule saved


    #B-76393
  @domain_Saturn @feature_Core_search_improvements_2017 @release_7.11
  Scenario: Confirm that "Manage Featured Facet" Action is enabled for rule with "Keyword Pattern" triggers
    Given I navigate to the Saturn login page
    When I enter login "credentials"
    And I navigate to Rules menu
    And I select Create rule
    And I enter Rule Name, Rule Priority, Effective and Expiration dates,fob
    And I select "Keyword Pattern" trigger
    And I click on "Add Action"
    Then I confirm "Manage Featured Facet" is enabled
    When I select "Manage Featured Facet" action
    And I select Automate sub-action
    Then I should see automate sub action warning message "Warning! This option is for the automated behavior only."
    When  I enter "30" in threshold filed
    And I select navigation
    And  I de select apply non canvas
    Then I save action
    And I save rule
    Then I should see rule saved


  @domain_Saturn @feature_Core_search_improvements_2017 @release_7.11
  Scenario: Confirm that "Manage Featured Facet" Action is enabled for rule with "Result Set" triggers
    Given I navigate to the Saturn login page
    When I enter login "credentials"
    And I navigate to Rules menu
    And I select Create rule
    And I enter Rule Name, Rule Priority, Effective and Expiration dates,fob
    And I populate "Result Set" trigger in Create Rule page
    And I click on "Add Action"
    Then I confirm "Manage Featured Facet" is enabled
    When I select "Manage Featured Facet" action
    And I select Automate sub-action
    Then I should see automate sub action warning message "Warning! This option is for the automated behavior only."
    When  I enter "30" in threshold filed
    And I select navigation
    And  I de select apply non canvas
    Then I save action
    And I save rule
    Then I should see rule saved

    
  @domain_Saturn @feature_Core_search_improvements_2017 @release_7.11
  Scenario: Confirm that "Manage Featured Facet" Action is enabled for rule with "All Search" trigger
    Given I navigate to the Saturn login page
    When I enter login "credentials"
    And I navigate to Rules menu
    And I select Create rule
    And I enter Rule Name, Rule Priority, Effective and Expiration dates,fob
    And I Select "All Search" trigger in Create Rule Page
    And I click on "Add Action"
    Then I confirm "Manage Featured Facet" is enabled
    When I select "Manage Featured Facet" action
    And I select Automate sub-action
    Then I should see automate sub action warning message "Warning! This option is for the automated behavior only."
    When  I enter "30" in threshold filed
    And I select navigation
    And  I de select apply non canvas
    Then I save action
    And I save rule
    Then I should see rule saved








