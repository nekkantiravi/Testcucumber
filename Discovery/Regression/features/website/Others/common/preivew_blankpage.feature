#=================================================
# Author: SWAT TEAM
# Date Created: Sept 4th 2017
#=================================================

Feature: Search for blank pages and errors on Splash and Sub Splash Pages
  #
  #          :title => ['Not Found', 'Site Unavailable', 'Access Denied', 'Not Available', 'Registry Error', 'Registry Test', 'Network Error'],
  #          :text  => ['java.io', 'Oops!', 'Back in a few', 'inventory system is currently unavailable', 'access denied', 'access is denied', 'please try again']


  @production  @blank_page @preview
  Scenario: Verify that WOMEN Sub categories don't show blank pages or errors
    Given I visit the web site as a guest user
    Then I verify no pages under "WOMEN" are blank

  @production @blank_page @preview
  Scenario: Verify that MEN Sub categories don't show blank pages or errors
    Given I visit the web site as a guest user
    Then I verify no pages under "MEN" are blank

  @production @blank_page @preview
  Scenario: Verify that HOME Sub categories don't show blank pages or errors
    Given I visit the web site as a guest user
    Then I verify no pages under "HOME" are blank

  @production @blank_page @preview
  Scenario: Verify that SHOES Sub categories don't show blank pages or errors
    Given I visit the web site as a guest user
    Then I verify no pages under "SHOES" are blank

  @production @blank_page @preview
  Scenario: Verify that HANDBAGS Sub categories don't show blank pages or errors
    Given I visit the web site as a guest user
    Then I verify no pages under "HANDBAGS" are blank

  @production @blank_page @preview
  Scenario: Verify that BEAUTY Sub categories don't show blank pages or errors
    Given I visit the web site as a guest user
    Then I verify no pages under "BEAUTY" are blank

  @production @blank_page @preview
  Scenario: Verify that KIDS Sub categories don't show blank pages or errors
    Given I visit the web site as a guest user
    Then I verify no pages under "KIDS" are blank
