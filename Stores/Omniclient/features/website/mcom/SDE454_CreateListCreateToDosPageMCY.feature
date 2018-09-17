# Story: Create To Dos Page
# Date Created: 01/09/2018
# Date Signed Off:

Feature: As an associate, I need a page where I can give a title to my Pre-Gen or Wizard List, set a due date write a
  description, so that I can target my outreach efficiently

  Background:
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I navigate on Create To Dos tab
    Then I should see the Create List dashboard

  @mcom @domain_stores @omniclient @story_SDE-454 @website
  Scenario: Create List Screen - View
    When I select the Create To Dos button from Create List dashboard
    Then the CREATE MY LIST Screen is displayed
    And I should see the input boxes to create a list
      | TITLE       |
      | DATE        |
      | DESCRIPTION |
    And the CREATE MY LIST button should be displayed in the Create List view
    And the CANCEL button should be displayed in the Create List view
    And the Required Fields note should be displayed in the Create List view

  @mcom @domain_stores @omniclient @story_SDE-454 @website
  Scenario: Create List Screen - Title Field Validation
#    NO Title
    When I select the Create To Dos button from Create List dashboard
    And I enter a title " " in the input field on CREATE LISTS page
    And I select a valid date in Create List View on CREATE LIST page
    Then the CREATE MY LIST button should be disabled in the Create List view
#  Input exactly the limit (50 chars)
    When I enter a title "rrrrrrrrr tttttttt yyyyyyyyy uuuuuuuuu iiiiiiiiiii" in the input field on CREATE LISTS page
    Then the CREATE MY LIST button should be enabled in the Create List view
#  Input over the limit (over 50 chars)
    When I enter a title "rrrrrrrrr tttttttt yyyyyyyyy uuuuuuuuu iiiiiiiiiii12334" in the input field on CREATE LISTS page
    Then I should see the title "rrrrrrrrr tttttttt yyyyyyyyy uuuuuuuuu iiiiiiiiiii" in the input field on CREATE LISTS page
    And the CREATE MY LIST button should be enabled in the Create List view

  @mcom @domain_stores @omniclient @story_SDE-454 @website
  Scenario: Create List Screen - Date Validation
#    NO Date selected
    When I select the Create To Dos button from Create List dashboard
    And I enter a title "LISTTitle123" in the input field in CREATE LIST page
    Then the CREATE MY LIST button should be disabled in the Create List view

  @mcom @domain_stores @omniclient @story_SDE-454 @website
  Scenario: Create List Screen - Description Validation
#    NO Description
    When I select the Create To Dos button from Create List dashboard
    And I enter a title "LISTTitle123" in the input field in CREATE LIST page
    And I select a valid date in Create List View on CREATE LIST page
    Then the CREATE MY LIST button should be enabled in the Create List view
#    More than 250 characters
    When I enter 251 characters in the Description input field on CREATE LIST page
    Then 250 characters should be displayed in the Description input field on CREATE LIST page

  @mcom @domain_stores @omniclient @Story_SDE-454 @website
  Scenario: Create List Screen - Cancel
    When I select the Create To Dos button from Create List dashboard
    And I enter a title "LISTTitle123" in the input field in CREATE LIST page
    And I select a valid date in Create List View on CREATE LIST page
    And I click on CANCEL button from Create LIST page
    And I click OK on the disregard changes popup message Create To Dos page
    Then I should see the Create List dashboard

  @mcom @domain_stores @omniclient @story_SDE-454 @website
  Scenario: Create List Screen - PCI Validation
    When I select the Create To Dos button from Create List dashboard
#    Credit Card information may not be entered into the Title field. Please try again.
    And I enter a title "123456789" in the input field in CREATE LIST page
    And I select a valid date in Create List View on CREATE LIST page
    And I select the CREATE MY LIST button from Create List view
    Then I should see the Credit Card information in Title field error message
    And I select the OK button on the error message
#    Credit Card information may not be entered into the Description field. Please try again.
    When I enter a title "PCI TEST" in the input field in CREATE LIST page
    And I enter a description "123456789" in the input field on CREATE LIST page
    And I select the CREATE MY LIST button from Create List view
    Then I should see the Credit Card information in Description field error message

  @mcom @domain_stores @omniclient @story_SDE-454 @website
  Scenario: Create List from Pre-Gen Lists
    When I select the Create To Dos button from Create List dashboard
    Then the CREATE MY LIST Screen is displayed
    And I enter a title "QUICKLIST TITLE" in the input field in CREATE LIST page
    And I select a valid date in Create List View on CREATE LIST page
    And I select the CREATE MY LIST button from Create List view
    Then MY MACYS TO DOS section is displayed
    And the List "QUICKLIST TITLE" is displayed on the MY MACYS TO DOS tab
