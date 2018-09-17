#Registry Lean Lab BCOM
#Created: 2/23/2017
#Author: Masha Malygina
#Version-one Story: B-70876

Feature: BCOM::To verify functionality of mobile registry home page in registry lean lab

  @B-70876 @dsv_test @dsv_mew_sev1
  Scenario: Verify user can navigate to the registry mobile home page by selecting MY REGISTRY tab in left nav
    Given I visit the mobile web site as a guest user in domestic mode
    When I select "THE REGISTRY" tab in the left nav
    Then I should land on the registry mobile home page

  @B-70876 @registry_mobile @domain_selection
   Scenario: Verify title tag of the registry mobile home page
     Given I navigate to mobile registry home page
     Then I should verify the title tag is:
     """
     Wedding Registry & Gift Registry at Bloomingdale's
     """

  @B-70876 @dsv_test @dsv_mew_sev1
  Scenario: Verify user can navigate to FIND by using left nav menu on the mobile registry home page
    Given I navigate to mobile registry home page
    And I select "Find Registry" tab in the left nav
    Then I should land on registry search results page

  @B-70876 @dsv_test @dsv_mew_sev1
  Scenario: Verify user can navigate to CREATE by using left nav menu on the mobile registry home page
    Given I navigate to mobile registry home page
    And I select "Create Registry" tab in the left nav
    Then I should land on Let's Get Started page

  @B-70876 @dsv_test @dsv_mew_sev1
  Scenario: Verify user can navigate to MANAGE by using left nav menu on the mobile registry home page
    Given I navigate to mobile registry home page
    When I navigate the global navigation menu as follows:
      | Manage Registry  |
      | View Registry    |
    Then I should land on registry log in page

  @B-70876 @registry_mobile @domain_selection
  Scenario: Verify error message when guest user searches for non-existing registry from registry mobile home page
    Given I navigate to mobile registry home page
    When I search for non-existing couple's registry using mobile site
    Then I should land on registry search results page
    And I should see the error message displayed:
    """
    Sorry, we were unable to find a registry to match your search. Please revise your search below.
    """

    
  @B-70876 @registry_mobile @domain_selection
  Scenario: Verify guest user can create registry from registry mobile home page
    Given I navigate to mobile registry home page
    When I click on create registry
    Then I should verify that I land on create registry page

  @B-70876 @registry_mobile @domain_selection
  Scenario: Verify registry user cannot sign in with invalid registry account using "Manage" section on registry mobile home page
    Given I navigate to mobile registry home page
    When I sign in with invalid credentials in Manage section on mobile registry home page page
    Then I should land on registry log in page
    And I should see the error message displayed:
  """
  That email address/password combination is not in our records. Forgot Your Password?
  """

  @B-70876 @registry_mobile @domain_selection
  Scenario: Verify guest user can navigate to Benefits and Perks page from registry mobile home page
    Given I navigate to mobile registry home page
    And I navigate to "Benefits and Perks" page from registry mobile home page
    And I should land on "Benefits and Perks" page

  @B-70876 @registry_mobile @domain_selection
  Scenario: Verify guest user can navigate to Wedding Checklist from registry mobile home page
    Given I navigate to mobile registry home page
    And I navigate to "Where To Start" page from registry mobile home page
    And I should land on "Wedding Checklist" page

  @B-70876 @registry_mobile @domain_selection
  Scenario: Verify guest user can navigate to Little Registry guide from registry mobile home page
    Given I navigate to mobile registry home page
    And I navigate to "Little Registry Guide" page from registry mobile home page
    And I should land on "Little Registry Guide" page

  @B-70876 @registry_mobile @domain_selection
  Scenario: Verify that user with valid registry account can login into Registry Manager fom mobile registry homepage
    Given I visit the mobile web site as a registered user without add CC
    When I sign in with valid registry credentials in Manage section on mobile registry home page
    Then I should land on Registry Manager page

  @B-70876 @registry_mobile @domain_selection
  Scenario: Verify that guest user can search for existing couple's registry from mobile registry home page
    Given I visit the mobile web site as a guest user
    When I search for the existing couple's registry using mobile registry homepage
    Then I should land on find registry page with search results
    And I should see searched name in the result set
