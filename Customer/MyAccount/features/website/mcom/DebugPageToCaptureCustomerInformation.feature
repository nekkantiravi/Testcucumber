#Author: UFT team
#Migrated By: Manjeet Ranga
#Date Created: 04/05/2016
#Date Signed Off:
#Version One: B-37150

Feature:  As a member of the support team, I would like to have a new page developed, "Customer debug page" which allows
  us to capture some basic information needed for troubleshooting issues, that a customer is facing on the
  client side.

  @artifact_navapp @mode_domestic @release_16D @priority_medium @domain_customer
  @project_UFT @feedback_page @use_regression @migrated_to_sdt
  Scenario: Verify captcha should be provided to access customer feedback form page
    Given I visit the web site as a guest user
    When I navigate to the static url:
      | MCOM | /shop/feedback |
    Then I should see feedback page is as expected

  @artifact_navapp @mode_domestic @release_16D @priority_medium @domain_customer @project_UFT
  @feedback_page @use_regression @migrated_to_sdt
  Scenario: Verify submit comment on customer feedback form page
    Given I visit the web site as a guest user
    When I navigate to the static url:
      | MCOM | /shop/feedback |
    Then I should see feedback page is as expected
    And I should see feedback text area
    When I enter the below text in the feedback text area
      | This is a test to verify the customer feedback form |
    When I submit the feedback in the customer feedback page
    Then I should see the following customer thank you message
      | Thank you! Your feedback submitted Successfully..! |
    And I should see comment is cleared

  @artifact_navapp @mode_domestic @release_16D @priority_medium @domain_customer @project_UFT
  @feedback_page @use_regression @migrated_to_sdt
  Scenario: Verify cancel comment on customer feedback form page
    Given I visit the web site as a guest user
    When I navigate to the static url:
      | MCOM | /shop/feedback |
    Then I should see feedback page is as expected
    And I should see feedback text area
    When I enter the below text in the feedback text area
      | This is a test to verify the customer feedback form |
    When I cancel the comment on customer feedback page
    Then I should see comment is cleared