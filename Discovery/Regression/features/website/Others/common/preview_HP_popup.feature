#=================================================
# Author: SWAT TEAM
# Date Created: Oct 23rd 2017
#=================================================


Feature: To Validate all popups on Home Page

  @preview_desktop @popup_validation
  Scenario: Verify all popups links on Home Page- Domestic mode
    Given I visit the web site as a guest user in "domestic" mode
    And I verify all popup on "Domestic HomePage" page opens without any errors

  @preview_desktop @popup_validation
  Scenario: Verify all popups links on Home Page- Iship mode
    Given I visit the web site as a guest user in "iship" mode
    And I verify all popup on "Iship HomePage" page opens without any errors

  @preview_desktop @popup_validation
  Scenario: Verify all popups links on category page- Registry mode
    Given I visit the web site as a guest user in "registry" mode
    And I verify all popup on "Registry HomePage" page opens without any errors