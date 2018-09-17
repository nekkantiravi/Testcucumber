  #Author: SST Automation Regression
  #Date Created:  07/17/2013
  #Date Signed Off:

  Feature: Customer Service

  @wip @please_automate @domain_marketing
  Scenario Outline: Customer Service - Related Tools
    Given I visit the web site as a registered user
    When I navigate to the Customer Service Page
    And I navigate to the "<RELATED_TOOLS_LINKS>" link in Customer Service page
    Then I verify that the "<RELATED_TOOLS_LINKS>" page is rendered
  Examples:
    | RELATED_TOOLS_LINKS     |
    | My Profile              |
    | Macy's Credit Card      |
    | Order Status            |
    | Store Locator           |
    | Gift Card BalanceChange |
    | My Password             |
    | Password Assistance     |

      #MCOM-57260
    @wip @please_automate @domain_marketing
    Scenario: Customer Service - Our Stores
      Given I am on the Our Stores Events Page as a guest user
      Then I verify the search functionality in Our Stores page
    # Notes
    # Search for a Store  Location & hours by ZIP or City & State
    #  Verify Store locations and hours are displayed based upon the the ZIP or City/State entered.
    # Search catalogs by ZIP
    #  Verify Catalogs are displayed
    # Search this week in Stores by ZIP
    #  Verify Store detail based upon ZIP is displayed

  #MCOM-57261
    @use_regression @myaccount_7 @domain_marketing
    Scenario: Customer Service - Contact Us
      Given I visit the web site as a guest user
      When I select the "contact us" link in the footer
      And I verify contact us page links
      # Notes
      # Navigate to few links in Macy's Credit Card, Stores, Order Questions, Furniture & Mattresses, Registry and verify
      # they navigate to the appropriate pages
