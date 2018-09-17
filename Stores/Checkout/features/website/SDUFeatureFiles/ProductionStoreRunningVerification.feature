#Author: Stores Domain Checkout Team
   #Story: SDU-Showcase Checkout ::Showcase
   #Date Created: 8/10/2017
   #Date Signed Off:

@domain_stores @project_checkout @release_17 @showcase
Feature: As a Developer I want to be sure our Production Environments are up and Running

  @Macy's @Prod
  Scenario Outline: Verify Macy's Stores are up and running
    Given I visit the production stores with a bad pin "<StoreNum>"
    When I can see the landing page
    And I click the bag menu
    Then I can see Sales Trans is up and running

    Examples:
    |StoreNum|
    |ME572|
    |Bl027|
    |ME733|
    |BL031|
    |BL028|
    |ME769|
    |ME732|
    |ME730|
    |ME687|
    |ME665|
    |ME533|
    |ME532|
    |ME408|
    |ME217|
    |ME174|
    |ME095|
    |ME072|
    |ME059|
    |ME053|
    |ME023|
    |ME015|
    |BL001|
    |BL058|
    |BL062|
    |ME075|
    |ME081|
    |ME219|
    |ME301|
    |ME324|
    |ME368|
    |ME511|
    |ME518|
    |ME558|
    |ME584|
    |ME686|
    |ME734|
    |ME757|
    |ME810|
    |ME811|
    |ME828|

############################# Macy's Smoke Tests ##############################
#
#  @Macy's @Prod
#    Scenario Outline: Verify Macy's Stores are up and running
#      Given I verify the Prod Stores "<StoreNum>"
#      When I can see the landing page
#      And I click the bag menu
#      Then I can see Checkout empty bag view
#
#    Examples:
#    |StoreNum|
#    |ME572|
#    |Bl027|
#    |Me733|
#    |BL031|
#    |BL028|
#    |ME769|
#    |ME732|
#    |ME730|
#    |ME687|
#    |ME665|
#    |ME533|
#    |ME532|
#    |ME408|
#    |ME217|
#    |ME174|
#    |ME095|
#    |ME072|
#    |ME059|
#    |ME053|
#    |ME023|
#    |ME015|
#    |BL001|
#    |BL058|
#    |BL062|
#    |ME075|
#    |ME081|
#    |ME219|
#    |ME301|
#    |ME324|
#    |ME368|
#    |ME511|
#    |ME518|
#    |ME558|
#    |ME584|
#    |ME686|
#    |ME734|
#    |ME757|
#    |ME810|
#    |ME811|
#    |ME828|


