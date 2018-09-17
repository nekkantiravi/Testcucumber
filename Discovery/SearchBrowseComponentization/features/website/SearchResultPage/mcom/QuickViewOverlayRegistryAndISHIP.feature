# Author:  Discovery QE Team
# Story: B-88546
# Date Created: 10/08/2017
# Date Signed Off:

Feature: As an international customer, I want the ability to "QuicKView" recommendations and add to my bag without disrupting my shopping experience.

#--------------------------------------------------------------------------------------------------
#-----------------------Scenarios for ZSR_ZONE_B Panel---------------------------------------------
#--------------------------------------------------------------------------------------------------

  @15B @artifact_navapp @priority_medium @use_regression @pros @mcom @domain_selection @snbc_comp
  Scenario: Verify QuickView button is present for member products in ZSR_ZONE_B recommendation panel in Domestic mode
    Given I am on ZeroSearchResultPage for "jnjnsecjnc" in Domestic mode
    Then I verify that recommendation panel is displayed on ZSR page
    When I quick view any "member" recommended product in the panel on zsr page
    Then I verify that quick peek is displayed
    And I should see the Quick view of the product clicked

  @15B @artifact_navapp @priority_medium @use_regression @pros @mcom @domain_selection @snbc_comp
  Scenario: Verify QuickView button is present for member products in ZSR_ZONE_B recommendation panel in Registry mode
    Given I am on ZeroSearchResultPage for "jnjnsecjnc" in Registry mode
    Then I verify that recommendation panel is displayed on ZSR page
    When I quick view any "member" recommended product in the panel on zsr page
    Then I verify that quick peek is displayed
    And I should see the Quick view of the product clicked

  @15B @artifact_navapp @priority_medium @use_regression @pros @mcom @domain_selection @snbc_comp
  Scenario: Verify QuickView button is present for member products in ZSR_ZONE_B recommendation panel in Iship mode
    Given I am on ZeroSearchResultPage for "jnjnsecjnc" in Iship mode
    Then I verify that recommendation panel is displayed on ZSR page
    When I quick view any "member" recommended product in the panel on zsr page
    Then I verify that quick peek is displayed
    And I should see the Quick view of the product clicked



