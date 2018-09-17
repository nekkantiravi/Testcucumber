#---------------------------------------------------
# Brand         : MCOM_responsive
# Author        : Usman Chaudhri
# Date Created	: Nov.22,2017
#---------------------------------------------------

@responsive_list @domain_selection @project_mcom
Feature: Share list via twitter.
  Scenario Outline: As a signed user, I should be able to click on twitter share and verify the twitter window open
    Given I visit the web site as a registered user
    And I set cookie for SSC to see responsive experience
    When I navigate to PDP with PID "<productId>"
    And I click Add to Wish List button on PDP using desktop website
    And I land on List landing page
    And I click on default list
    And I verify the basic components on the page for "SignedIn" user
    And I click on the twitter share icon
    Then I verify the twitter window appears
    And I verify the google recaptcha error message

    Examples:
      |productId |
      |22805   |