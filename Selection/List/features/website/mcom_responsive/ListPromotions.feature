#---------------------------------------------------
# Brand         : MCOM_responsive
# Author        : Harish Madathil
# Date Created	: Nov.29,2017
#---------------------------------------------------

@responsive_list @domain_selection @project_mcom @t
Feature: List Promotion use case verification
  Scenario Outline: As a signed user, I should be able to see items with promotions added to list page
    Given I visit the web site as a registered user
    And I set cookie for SSC to see responsive experience
    And I add a "22805" product to my list through the service call
    And I add a "1494" product to my list through the service call
    And I add a "78600" product to my list through the service call
    And I land on List landing page
    And I click on default list
    And I verify the List promotion components on the page for "SignedIn" user
    #And I click on email share icon and verify email list button on the email list share overlay
    #Then I verify the error message "<email_share_error_msg>"
    #Then I enter to "<email_address>" and click on email list button
    #And I verify the google recaptcha error message

    Examples:
      |productId |  |product_info | |email_share_error_msg|email_address|
      |22805   |   |Dinnerware, French Garden Dinner Plate| |Your email address must be entered in this format: jane@company.com. Please separate multiple addresses with commas.|list@abc.com|
