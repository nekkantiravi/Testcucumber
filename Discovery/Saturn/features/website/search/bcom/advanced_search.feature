@Saturn_advanced_search
Feature: Advanced Search  Functional tests on BCOM
  As a valid Saturn non_admin user
  I want to Search Rules data using Advanced Search

  ############ CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-50298 ##################
  ########### CSI Story: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-86491 ##################

  @csi @Saturn_Regression
  Scenario: Default state of Advanced Search page
    Given I login to Saturn as an "non_admin" user
    When I navigate to "Advanced Search" under Rules
    Then I see "Trigger" Types in alphanumeric order on Advanced Search Page
      |Category Ids	 |
      |Facet Refinement|
      |Keyword Pattern |
      |Result Count    |
      |Result Set	     |
    And I see "Action" Types in alphanumeric order on Advanced Search Page
      |Category Redirect             |
      |Display Message               |
      |Execute New Search            |
      |Manage Facets                 |
      |Manage Featured Facet         |
      |Modify Result Set             |
      |PDP Redirect                  |
      |RDPP Algorithm                |
      |Show Master or Member Products|
      |Show Media                    |
      |URL Redirect                  |
    And I see default selections on Advanced Search page
