# Author: UFT
# Date Created: 04/14/2015
# Date Modified:
# Date Signed Off:

Feature: Verify the Browse Demo UI in on eComPortal(MASS)

  #Version 1 Story ID: B-15021

  @sst
  Scenario: Verify the Browse Demo UI link in left nav under Discovery Preview Services header
    Given I login into mass portal as a valid user
    Then  I should see "Discovery Preview Services" in the left nav panel
    And I should not see "Discovery Data Lookup" in the left nav panel
    And I should see "Browse Demo UI" under "Discovery Preview Services"

  @sst
  Scenario: Verify the attributes that should and should not be displayed in Demo UI page
    Given I login into mass portal as a valid user
    When I navigate to the "Browse Demo UI" page under Discovery Preview Services section
    Then I select "discovery_CellA" from SDP URL
    Then I should see the following attributes displayed in the page
      |  Show results in new tab:       |
      | Use old-style context attributes |
      | Customer Experiment:             |
    And I should not see the following attributes in the page
      | Available for international customers |
      | Images Requested:                     |

  @sst
  Scenario: Verify the Endpoint service attribute in Demo UI page
    Given I login into mass portal as a valid user
    When I navigate to the "Browse Demo UI" page under Discovery Preview Services section
    Then I select "discovery_CellA" from SDP URL
    Then I should see the "Apollo" option selected by default in the "Endpoint service" section
    And I should not see the "catalog" option in the "Endpoint service" section

  @sst
  Scenario: Verify the defaults value for flags in Demo UI page
    Given I login into mass portal as a valid user
    When I navigate to the "Browse Demo UI" page under Discovery Preview Services section
    Then I select "discovery_CellA" from SDP URL
    Then I should see the flags in the specified state
      | Facets Requested                 | checked   |
      | Use old-style context attributes | unchecked |
      | Use context map                  | checked   |

  @sst
  Scenario: Verify the default value for Navigation type field in Demo UI page
    Given I login into mass portal as a valid user
    When I navigate to the "Browse Demo UI" page under Discovery Preview Services section
    Then I select "discovery_CellA" from SDP URL
    Then I should see "BROWSE" is selected in "navigation type" dropdown

  @sst
  Scenario: Verify the With preview date field in Demo UI page is as per the requirement
    Given I login into mass portal as a valid user
    When I navigate to the "Browse Demo UI" page under Discovery Preview Services section
    Then I select "discovery_CellA" from SDP URL
    Then I should see the format is "yyyy-MM-dd" in the With Preview date field
    And I should see the SDP date value is filled in the With Preview Date field by default

  @sst
  Scenario: Verify various attributes in Use old-style context attributes section
    Given I login into mass portal as a valid user
    When I navigate to the "Browse Demo UI" page under Discovery Preview Services section
    Then I select "discovery_CellA" from SDP URL
    Then I should see the following fields in Use old-style context attributes section are disabled
      | Channel      | Country Code |

  @sst
  Scenario: Verify that Context information is displayed in the search results
    Given I login into mass portal as a valid user
    When I navigate to the "Browse Demo UI" page under Discovery Preview Services section
    Then I select "discovery_CellA" from SDP URL
    Then I check "With preview info"
    Then I search with MCOM "269" or BCOM "4114"  values
    Then I click on "preview info"
    Then I should see the context information displayed for MCOM "269" or BCOM "4114"

  @sst
  Scenario: Verify that product details are displayed in the search results
    Given I login into mass portal as a valid user
    When I navigate to the "Browse Demo UI" page under Discovery Preview Services section
    Then I select "discovery_CellA" from SDP URL
    Then I search with MCOM "5449" or BCOM "6602"  values
    Then I should see the products along with details sorted in the correct order

  @sst
  Scenario: Verify that facet filters along with product count are displayed in the search results
    Given I login into mass portal as a valid user
    When I navigate to the "Browse Demo UI" page under Discovery Preview Services section
    Then I select "discovery_CellA" from SDP URL
    Then I search with MCOM "269" or BCOM "4114"  values
    Then I click on "Facets (refinements)"
    Then I should see the facet filters with possible product count

  @sst
  Scenario: Verify that canvas ids for the category are displayed in the search results
    Given I login into mass portal as a valid user
    When I navigate to the "Browse Demo UI" page under Discovery Preview Services section
    Then I select "discovery_CellA" from SDP URL
    Then I search with MCOM "39096" or BCOM "2921"  values
    Then I should see all the canvas ids for the category along with the link to call media service

  @sst
  Scenario: Verify that all the applied refinement details are displayed in the search results
    Given I login into mass portal as a valid user
    When I navigate to the "Browse Demo UI" page under Discovery Preview Services section
    Then I select "discovery_CellA" from SDP URL
    Then I search with MCOM "269" or BCOM "2921"  values
    Then I click on "Facets (refinements)"
    Then I should see all the refinement details are displayed in the search results

  @sst
  Scenario: Verify viewing of Navigation Provider Cache Statistics page
    Given I login into mass portal as a valid user
    When I navigate to the "Browse Demo UI" page under Discovery Preview Services section
    Then I select "discovery_CellA" from SDP URL
    Then I search with MCOM "269" or BCOM "2921"  values
    Then I click on "NavigationProvider Cache Statistics"
    Then I should see the Navigation Provider Cache Statistics page displayed correctly for MCOM "269" or BCOM "2921"

