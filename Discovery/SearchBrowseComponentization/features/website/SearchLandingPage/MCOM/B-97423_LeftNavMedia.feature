# Author: QE Team
# Date Created: 12/15/2017

Feature: Verifying the media on left hand navigation for SLP pages

  @SNBC_Phase4 @feature_slp
  Scenario: Verify category media is displayed on left navigation for SLP page in domestic mode
    Given I am on SLP for "112746" category id in Domestic mode
    Then I verify left navigation is present on SLP page
    And I verify left navigation media type content in service for "CATEGORY" media type
    And I click on link from left navigation
    And I verify correct url is displayed for associated media type fetched from service

  @SNBC_Phase4 @feature_slp
  Scenario: Verify Static media is displayed on left navigation for SLP page in domestic mode
    Given I am on SLP for "112746" category id in Domestic mode
    Then I verify left navigation is present on SLP page
    And I verify left navigation media type content in service for "STATIC" media type
    And I click on link from left navigation
    And I verify correct url is displayed for associated media type fetched from service

  @SNBC_Phase4 @feature_slp
  Scenario: Verify Clone category media is displayed on left navigation for SLP page in domestic mode
    Given I am on SLP for "112746" category id in Domestic mode
    Then I verify left navigation is present on SLP page
    And I verify left navigation media type content in service for "CLONE_CAT_LHN" media type
    And I click on link from left navigation for clone category
    And I verify correct url is displayed for associated media type fetched from service
    And I verify left navigation links for Cloned category Url is same as SLP page
    #Clone category test data not available