#---------------------------------------------------
# Brand         : MCOM_responsive
# Author        : vijaya bharathi
# Date Created	: Aug.11,2017
#---------------------------------------------------

Feature: Add to Bag use case verification

  @responsive_list @domain_selection @project_mcom
  Scenario: As a guest user, I should be able to click on Add to bag and verify the pop-up screen is coming
    Given I visit the web site as a guest user
    And I set cookie for SSC to see responsive experience
    When I select a "member" product and navigate to PDP in "site" mode
    Then I click on A2L button on PDP page
    Then I verify the list overlay popup on PDP page for "Guest" user
    And I land on List landing page
    And I click on default list
    And I verify the basic components on the page for "Guest user" user
    And I click on the "AddToBag" button
    Then I should verify the information on the popup
    #Then I click on the "viewcheckout" button

  @responsive_list @domain_selection @project_mcom
  Scenario: As a guest user, I should be able to click on viewbag and checkout and user should land on shopping bag page
    Given I visit the web site as a guest user
    And I set cookie for SSC to see responsive experience
    When I select a "member" product and navigate to PDP in "site" mode
    Then I click on A2L button on PDP page
    Then I verify the list overlay popup on PDP page for "Guest" user
    And I land on List landing page
    And I click on default list
    And I verify the basic components on the page for "Guest" user
    And I click on the "AddToBag" button
    Then I should verify the information on the popup
    Then I click on the "viewcheckout" button
    Then I verify user landed on shoppingbag page



  @responsive_list @domain_selection @project_mcom
  Scenario: As a guest user, should be able to click on your bag link on atb popup and user should land on shopping bag page
    Given I visit the web site as a guest user
    And I set cookie for SSC to see responsive experience
    When I select a "member" product and navigate to PDP in "site" mode
    Then I click on A2L button on PDP page
    Then I verify the list overlay popup on PDP page for "Guest" user
    And I land on List landing page
    And I click on default list
    And I verify the basic components on the page for "Guest" user
    And I click on the "AddToBag" button
    Then I should verify the information on the popup
    Then I click on the "yourbag" button
    Then I verify user landed on shoppingbag page


  @responsive_list @domain_selection @project_mcom
  Scenario: As a guest user, should be able to back to list button and user should land on list page again
    Given I visit the web site as a guest user
    And I set cookie for SSC to see responsive experience
    When I select a "member" product and navigate to PDP in "site" mode
    Then I click on A2L button on PDP page
    Then I verify the list overlay popup on PDP page for "Guest" user
    And I land on List landing page
    And I click on default list
    And I verify the basic components on the page for "Guest" user
    And I click on the "AddToBag" button
    Then I should verify the information on the popup
    Then I click on the "continue" button
    Then I verify user landed on list page

  @responsive_list @domain_selection @project_mcom
  Scenario: As a guest user, from landing page navigate to default list and click add to bag
    Given I visit the web site as a guest user
    And I set cookie for SSC to see responsive experience
    When I select a "member" product and navigate to PDP in "site" mode
    Then I click on A2L button on PDP page
    Then I verify the list overlay popup on PDP page for "Guest" user
    And I land on List landing page
    And I click on default list
    And I verify the basic components on the page for "Guest" user
    And I click on the "AddToBag" button
    Then I should verify the information on the popup
    Then I click on the "viewcheckout" button
    Then I verify user landed on shoppingbag page


  @responsive_list @domain_selection @project_mcom
  Scenario: As a signedin user, from landing page navigate to default list and click add to bag
    Given I visit the web site as a registered user
    And I set cookie for SSC to see responsive experience
    When I select a "member" product and navigate to PDP in "site" mode
    Then I click on A2L button on PDP page
    Then I verify the list overlay popup on PDP page for "SignedIn" user
    And I land on List landing page
    And I click on default list
    And I verify the basic components on the page for "SignedIn" user
    And I click on the "AddToBag" button
    Then I should verify the information on the popup
    Then I click on the "viewcheckout" button
    Then I verify user landed on shoppingbag page

  @responsive_list @domain_selection @project_mcom
  Scenario: As a signedin user, from landing page navigate to default list and click your bag link on atb popup and user should land on shopping bag page
    Given I visit the web site as a registered user
    And I set cookie for SSC to see responsive experience
    When I select a "member" product and navigate to PDP in "site" mode
    Then I click on A2L button on PDP page
    Then I verify the list overlay popup on PDP page for "SignedIn" user
    And I land on List landing page
    And I click on default list
    And I verify the basic components on the page for "SignedIn" user
    And I click on the "AddToBag" button
    Then I should verify the information on the popup
    Then I click on the "yourbag" button
    Then I verify user landed on shoppingbag page

  @responsive_list @domain_selection @project_mcom
  Scenario: As a signedin user, from landing page navigate to default list and click back to list button on atb popup and user should land on list page again
    Given I visit the web site as a registered user
    And I set cookie for SSC to see responsive experience
    When I select a "member" product and navigate to PDP in "site" mode
    Then I click on A2L button on PDP page
    Then I verify the list overlay popup on PDP page for "SignedIn" user
    And I land on List landing page
    And I click on default list
    And I verify the basic components on the page for "SignedIn" user
    And I click on the "AddToBag" button
    Then I should verify the information on the popup
    Then I click on the "continue" button
    Then I verify user landed on list page

  @responsive_list @domain_selection @project_mcom
  Scenario: As a signedin user, from landing page navigate to default list and click on Add to bag and verify the pop-up screen is coming
    Given I visit the web site as a registered user
    And I set cookie for SSC to see responsive experience
    When I select a "member" product and navigate to PDP in "site" mode
    Then I click on A2L button on PDP page
    Then I verify the list overlay popup on PDP page for "SignedIn" user
    And I land on List landing page
    And I click on default list
    And I verify the basic components on the page for "SignedIn" user
    And I click on the "AddToBag" button
    Then I should verify the information on the popup


