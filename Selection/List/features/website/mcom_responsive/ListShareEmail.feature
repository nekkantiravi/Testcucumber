#---------------------------------------------------
# Brand         : MCOM_responsive
# Author        : Harish Madathil
# Date Created	: Oct.14,2017
#---------------------------------------------------

@testShare @responsive_list @domain_selection @project_mcom
Feature: Share Email use case verification
  Scenario Outline: As a signed user, I should be able to click on share email and verify the pop-up screen is coming
    Given I visit the web site as a registered user
    And I set cookie for SSC to see responsive experience
    When I navigate to PDP with PID "<productId>"
    And I click Add to Wish List button on PDP using desktop website
    And I land on List landing page
    And I click on default list
    And I verify the basic components on the page for "SignedIn" user
    And I click on email share icon and verify email list button on the email list share overlay
    Then I verify the error message "<email_share_error_msg>"
    Then I enter to "<email_address>" and click on email list button
    And I verify the google recaptcha error message

    Examples:
      |productId |  |product_info | |email_share_error_msg|email_address|
      |22805   |   |Lenox Eternal Gold 4-Piece Hostess Set| |Your email address must be entered in this format: jane@company.com. Please separate multiple addresses with commas.|list@abc.com|
