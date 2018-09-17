#=================================================
# Author: SWAT Team
# Date Created: 2017/10/11
#=================================================

Feature: Search for blank pages and errors on Splash and Sub Splash Pages
  #
  #          :title => ['Not Found', 'Site Unavailable', 'Access Denied', 'Not Available', 'Registry Error', 'Registry Test', 'Network Error'],
  #          :text  => ['java.io', 'Oops!', 'Back in a few', 'inventory system is currently unavailable', 'access denied', 'access is denied', 'please try again']


  @production @blank_page @preview
  Scenario: Verify that BED & BATH sub categories don't show blank pages or errors
    Given I visit the web site as a guest user
    Then I verify no pages under "BED & BATH" are blank

  @production @blank_page @preview
  Scenario: Verify that JUNIORS sub categories don't show blank pages or errors
    Given I visit the web site as a guest user
    Then I verify no pages under "JUNIORS" are blank

  @production @blank_page @preview
  Scenario: Verify that JEWELRY sub categories don't show blank pages or errors
    Given I visit the web site as a guest user
    Then I verify no pages under "JEWELRY" are blank

  @production @blank_page @preview
  Scenario: Verify that WATCHES sub categories don't show blank pages or errors
    Given I visit the web site as a guest user
    Then I verify no pages under "WATCHES" are blank

  @production @blank_page @preview
  Scenario: Verify that GIFTS sub categories don't show blank pages or errors
    Given I visit the web site as a guest user
    Then I verify no pages under "GIFTS" are blank

  @production @blank_page @preview
  Scenario: Verify that BRANDS sub categories don't show blank pages or errors
    Given I visit the web site as a guest user
    Then I verify no pages under "BRANDS" are blank
