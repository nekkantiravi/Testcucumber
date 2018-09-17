#Author: SST Automation Regression
#Date Created:  07/17/2013
#Date Signed Off:

Feature: Store Events

  @use_regression @wip @please_automate @domain_marketing @akamai_waf
  Scenario Outline: Store Events - Verify basic attributes and footer links
    Given I visit the web site as a guest user
    When I navigate to Store Events page
    Then I verify the basic attributes of the Store Events page
    When I navigate to the "<STORE_EVENTS_LHN_LINKS>" link in Store Events page
    Then I verify that the  footer link - "<STORE_EVENTS_LHN_LINKS>" page is rendered
  Examples:
    | STORE_EVENTS_LHN_LINKS      |
    | Ways To Shop                |
    | Stores                      |
    | Online & Mobile             |
    | Outlets                     |
    | b.Cause                     |
    | Careers                     |
#    | Shopping Services           |
#    | New Stores                  |
#    | Our History                 |
#    | Press Releases              |
#    | Diversity                   |


#  # The below test cases are obsolete becuse these links are no more part of Store Events page
#  @use_regression @wip @please_automate @domain_marketing
#  Scenario Outline: Store Events - Rendering Shopping Services - Size Charts
#    Given I visit the web site as a guest user
#    When I navigate to the "<SIZE_CHARTS_LHN_LINKS>" link in Store Events page
#    Then I verify that the "<SIZE_CHARTS_LHN_LINKS>" page is rendered
#  Examples:
#    | SIZE_CHARTS_LHN_LINKS  |
#    | Women                  |
#    | Men                    |
#    | Kids                   |
#    | Shoes                  |
#
#
#  @use_regression @wip @please_automate @domain_marketing
#  Scenario Outline: Store Events - Rendering Shopping Services
#    Given I visit the web site as a guest user
#    When I navigate to the "<SHOPPING_SERVICE_LHN_LINKS>" link in Store Events page
#    Then I verify that the "<SHOPPING_SERVICE_LHN_LINKS>" page is rendered
#  Examples:
#    | SHOPPING_SERVICE_LHN_LINKS   |
#    | Corporate Services           |
#    | Personal Shoppers            |
#    | Studio Services              |
#    | Interior Designers           |
#    | Find a Wish List             |
#    | Visitor Services             |
#    | Travel Professional Services |
#    | Safety Recall Notice         |
#
#
#  @use_regression @wip @please_automate @domain_marketing
#  Scenario Outline: Store Events - Rendering b.Cause
#    Given I visit the web site as a guest user
#    When I navigate to the "<BCAUSE_LHN_LINKS>" link in Store Events page
#    Then I verify that the "<BCAUSE_LHN_LINKS>" page is rendered
#  Examples:
#    | BCAUSE_LHN_LINKS     |
#    | amfAR                |
#    | BCRF                 |
#    | Child Mind Institute |
#    | Help USA             |
#    | JDRF                 |


  #Test Case ID: BLCOM-56492
  #Commenting the tags below because BCOM have a new Store Events interface so this scenario is not applicable anymore
  @use_regression @use_launch_call @domain_marketing @akamai_waf @Marketing_CBT
  Scenario: Verify Stores & Events locator
    Given I visit the web site as a guest user
    When I select "stores" link in home page
    Then I verify stores are listed in distance order of search location
    When I navigate to a Store Details page
    Then I verify the store details page
    When I navigate to Store Events page from Store Details
    Then I verify the store events page
    # Notes
    # Select a store from the drop down and click on go
    # Verify in store details page: Store hours, store details and store events are displayed for the selected store
    # Select a store event to navigate to store events page
    # Verify user can select event by type , by date on store events page

  @use_regression @use_dsv
  Scenario: As a customer, I want to verify map, weekdays, date and hours on store details page in domestic mode
    Given I visit the web site as a guest user
    When I navigate to Store Events page
    Then I verify stores are listed in distance order of search location
    And I navigate to a Store Details page
    Then I should see store information on store details page