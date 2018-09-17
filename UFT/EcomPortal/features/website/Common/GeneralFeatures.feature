# Author: SST Regression Team
# Date Created: 11/20/2013
# Date Modified: 06/21/2016
# Date Signed Off:

Feature: Verify the general features in mass

#Test Case ID: MCOM-59358 , BLCOM-65380
  @sst @use_bat_next
  Scenario: Verify user can login to mass application
    Given I login into mass portal as a valid user
    Then I should see mass home page
    And I should see welcome message on mass home page
      | MCOM | Welcome to Macys Application Support System.         |
      | BCOM | Welcome to Bloomingdales Application Support System. |
    And I should see left navigation on mass home page
    And I should see logout link on mass home page

#Test Case ID: MCOM-59359 , BLCOM-65381
  @sst
  Scenario: Verify Generic search
    Given I login into mass portal as a valid user
    Then I select "faceted" from SDP URL
    When I navigate to the "Generic search" page under cache lookup section
    Then I should see the "Generic search" page attributes
    When I search with given options in Generic search page
      | Cache dropdown    | Key  | Class dropdown   |
      | catalog-categoryA | 3036 | java.lang.String |
    Then I should see Generic search results
      | Field name | Class | Value |
# Notes:
# verify following on Generic search page
# - verify Cache drop down displayed
# - Verify Key field displayed
# - verify Class drop down displayed
# - verify search button displayed
# Select following sample options to get Generic search results
# 'Cache' => mediaCacheA
# 'Key' => 3036
# 'Class' => java.lang.String
# Click search and verify results display under following columns
# Field name | Class | Value


#Test Case ID: MCOM-59364 , BLCOM-65386
  @sst
  Scenario: Verify Cache Browser
    Given I login into mass portal as a valid user
    Then I select "faceted" from SDP URL
    When I navigate to the "Cache browser" page under cache lookup section
    Then I should see the "Cache browser" page attributes
    When I select given parameters in Cache browser page
      | Cache dropdown                         |
      | cluster-transactionalNamedCacheManager |
    Then I should see Cache browser columns
      | Node | Partitions |
    When I select first link in Partitions column
    Then I should see Cache browser page results
      | Field name | Class | Value |
# Notes:
# verify following on Cache browser page
# - verify Cache drop down displayed
# - verify 'clear cache front map' button displayed
# Select parameter 'cluster-transactionalNamedCacheManager' to generate the results
# verify results display under following columns
# Node | Partitions
#Verify partitions results display under following columns
# Field name | Class | Value

  @sst
  Scenario: Verify Custom date service
    Given I login into mass portal as a valid user
    Then I select "faceted" from SDP URL
    When I navigate to the "Custom date service" page under configuration section
    Then I should see the "Custom date service" page attributes
# Notes:
# verify following on Cache browser page
# - verify Current custom date displayed
# - verify New custom date field displayed
# - verify change button displayed
# Since custom date function affecting to web site we only verify the page attributes

#Test Case ID: MCOM-59369 , BLCOM-65391
  @sst
  Scenario Outline: Verify Configuration service
    Given I login into mass portal as a valid user
    Then I select "faceted" from SDP URL
    When I navigate to the "<config_items>" page under configuration section
    Then I should see the "<config_items>" page attributes
  Examples:
    | config_items              |
    | Cluster nodes info        |
    | Cache                     |
    | Cluster                   |
    | Tangosol properties       |
    | Artifacts Info            |
    | Transactional caches info |

#Test Case ID: MCOM-59370 , BLCOM-65392
  @sst
  Scenario: Verify Logging configuration
    Given I login into mass portal as a valid user
    Then I select "faceted" from SDP URL
    When I navigate to the "Logging configuration" page under configuration section
    Then I should see the "Logging configuration" page attributes
    Then I should see Logging configuration results after selecting show button
      |  | ALL | TRACE | DEBUG | INFO | WARN | ERROR | FATAL | OFF | UNSET | Effective |
# Notes:
# verify following on logging configuration page
# - verify All cluster nodes, Service nodes, Storage nodes links displayed
# - verify show button displayed
# Click on show button to get logging configuration results
# - verify page load without any errors

  @sst
  Scenario: Verify the states for Transaction Manager in Transaction management page
    Given I login into mass portal as a valid user
    When I navigate to the "Transaction management" page under Troubleshooting section
    Then I select "faceted" from SDP URL
    Then I should see "Transaction management" results
      | Transaction manager | State | Simulate actions |
    And I should see every transaction status on page
      | NOT_STARTED | COMMITED | REVERTED | OPEN | REVOKED |
# Notes:
# Verify "Transaction manager" page
# | Transaction manager |State | Simulate actions |
# All the status should be either NOT_STARTED, COMMITED and REVOKED only

#Story @B-08254
  @sst
  Scenario: Verify V2 Search Demo UI page
    Given I login into mass portal as a valid user
    When I navigate to the "Search Demo UI" page under Discovery Preview Services section
    When I select "discovery_CellA" from SDP URL
    Then I should see the "Search Demo UI" page attributes
    When I search with query string "shoes"  against version "V_2"
    Then I should see search demo results for "shoes"

#Story @B-08254
  @sst
  Scenario: Verify V3 Search Demo UI page
    Given I login into mass portal as a valid user
    When I navigate to the "Search Demo UI" page under Discovery Preview Services section
    When I select "discovery_CellA" from SDP URL
    Then I should see the "Search Demo UI" page attributes
    When I search with query string "coat"  against version "V_3"
    Then I should see search demo results for "coat"

