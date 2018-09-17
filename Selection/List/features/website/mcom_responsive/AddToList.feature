#---------------------------------------------------
# Brand         : MCOM Responsive
# Author        : Ejay Landicho
# Date Created	: Oct.18,2017
#---------------------------------------------------

Feature: Add to list functionality on responsive page

  @responsive_list @domain_selection @project_mcom
  Scenario: As a guest user, I should be able to add an upc to my list
    Given I visit the web site as a guest user
    And I set cookie for SSC to see responsive experience
    When I select a "member" product and navigate to PDP in "site" mode
    Then I click on A2L button on PDP page
    And I land on List landing page
    And I click on default list
    And I verify the basic components on the page for "Guest" user
    And I should see "1" products in the responsive list page

  @responsive_list @domain_selection @project_mcom
  Scenario: As a registered user, I should be able to add an unavailable upc to my list through the service
    Given I visit the web site as a registered user
    And I set cookie for SSC to see responsive experience
    And I add a "unavailable" upc to my list through the service
    And I land on List landing page
    And I click on default list
    And I verify the basic components on the page for "SignedIn" user
    And I verify the unavailable message for the product on the list page
    And I should see "1" products in the responsive list page

  @responsive_list @domain_selection @project_mcom
  Scenario: As a registered user, I should be able to add an unavailable product to my list through the service
    Given I visit the web site as a registered user
    And I set cookie for SSC to see responsive experience
    And I add a "unavailable" product to my list through the service
    And I land on List landing page
    And I click on default list
    And I verify the basic components on the page for "SignedIn" user
    And I verify the unavailable message for the product on the list page
    And I should see "1" products in the responsive list page

  @responsive_list @domain_selection @project_mcom
  Scenario: As a registered user, I should be able to add a member product to my list through the service
    Given I visit the web site as a registered user
    And I set cookie for SSC to see responsive experience
    And I add a "member" product to my list through the service
    And I land on List landing page
    And I click on default list
    And I verify the basic components on the page for "SignedIn" user
    And I should see "1" products in the responsive list page

  #@responsive_list @domain_selection @project_mcom @t
  #Scenario Outline: I should be able to add a member product to my list through database insert
    #Given I visit the web site as a guest user
    #And I set cookie for SSC to see responsive experience
    #When I visit postgresql database to insert or delete "<dmls>" as a precondition
    #And I navigate directly to responsive list page for database user
    #And I verify the unavailable message for the product on the list page
    #Examples:
      #|dmls|
      #|lists_product_inserts.sql|

  @responsive_list @domain_selection @project_mcom
  Scenario Outline: As a user, I should be able to add a registry product to my list
    Given I visit the web site as a <user_type> user
    And I set cookie for SSC to see responsive experience
    When I select a "registry" product and navigate to PDP in "site" mode
    Then I click on A2L button on PDP page
    Then I verify the list overlay popup on PDP page for "<user_page>" user
    And I land on List landing page
    And I click on default list
    And I verify the basic components on the page for "<user_page>" user
    And I should see "1" products in the responsive list page
    Examples:
      |user_type  | user_page |
      |guest      | Guest     |
      |registered | SignedIn  |

  @responsive_list @domain_selection @project_mcom
  Scenario Outline: As a registered user, I should be able to add a product from master product page
    Given I visit the web site as a <user_type> user
    And I set cookie for SSC to see responsive experience
    When I select a "master" product and navigate to PDP in "site" mode
    Then I click on A2L button on PDP page
    Then I verify the list overlay popup on PDP page for "<user_page>" user
    And I land on List landing page
    And I click on default list
    And I verify the basic components on the page for "<user_page>" user
    And I should see "1" products in the responsive list page
    Examples:
      |user_type  | user_page |
      |guest      | Guest     |
      |registered | SignedIn  |


  @responsive_list @domain_selection @project_mcom @t
  Scenario Outline: As a registered user, I should be able to add a product big ticket product
    Given I visit the web site as a <user_type> user
    And I set cookie for SSC to see responsive experience
    When I select a "BigTicket" product and navigate to PDP in "site" mode
    Then I click on ALBigTicket button on PDP page
    Then I verify the list overlay popup on PDP page for "<user_page>" user
    And I land on List landing page
    And I click on default list
    And I verify the basic components on the page for "<user_page>" user
    And I should see "1" products in the responsive list page
    Examples:
      |user_type  | user_page |
      |guest      | Guest     |
      |registered | SignedIn  |
