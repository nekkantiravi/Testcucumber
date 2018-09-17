#=================================================
# Author: SWAT Team
# Date Created: 2017/10/11
#=================================================

Feature: Search for blank pages and errors on Splash and Sub Splash Pages
  #
  #          :title => ['Not Found', 'Site Unavailable', 'Access Denied', 'Not Available', 'Registry Error', 'Registry Test', 'Network Error'],
  #          :text  => ['java.io', 'Oops!', 'Back in a few', 'inventory system is currently unavailable', 'access denied', 'access is denied', 'please try again']


  @production @blank_page @preview
  Scenario: Verify that JEWELRY & ACCESSORIES Sub categories don't show blank pages or errors
    Given I visit the web site as a guest user
    Then I verify no pages under "JEWELRY & ACCESSORIES" are blank

  @production @blank_page @preview
  Scenario: Verify that DESIGNERS Sub categories don't show blank pages or errors
    Given I visit the web site as a guest user
    Then I verify no pages under "DESIGNERS" are blank

  @production @blank_page @preview
  Scenario: Verify that SALE Sub categories don't show blank pages or errors
    Given I visit the web site as a guest user
    Then I verify no pages under "SALE" are blank

  @production @blank_page @preview
  Scenario: Verify that WHAT'S NEW Sub categories don't show blank pages or errors
    Given I visit the web site as a guest user
    Then I verify no pages under "WHAT'S NEW" are blank

  @production @blank_page @preview
  Scenario: Verify that GIFTS Sub categories don't show blank pages or errors
    Given I visit the web site as a guest user
    Then I verify no pages under "GIFTS" are blank
