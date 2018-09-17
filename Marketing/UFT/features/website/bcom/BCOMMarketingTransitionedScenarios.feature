# Author: Marketing Regression QE Team
# Created Date: 11/02/2017

Feature: BCOM :: Marketing scenarios transitioned from UFT

####################################### Version One: B-54550  #######################################
  @artifact_shopapp @mode_domestic @release_16I @priority_medium @domain_marketing @project_UFT
  Scenario: Verify global error message should be displayed and Loyalty section as a guest user
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    And I enter shipping address on guest shipping page
    And I select continue button on guest shipping page
    And I look up with invalid loyalty type "top_tier" in guest checkout page
    Then I should see "Unfortunately, we still can't verify your information. To protect security, we only allow five verification attempts. Please call us at 1-800-600-5402 for assistance." error message on guest RC checkout payment page

  @artifact_shopapp @mode_domestic @release_16I @priority_medium @domain_marketing @project_UFT
  Scenario: Verify global error message should be displayed and Loyalty section as a singed user
    Given I visit the web site as a signed in user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping page as a "signed" user
    And I look up with invalid loyalty type "top_tier" in Signed checkout page
    Then I should see "Unfortunately, we still can't verify your information. To protect security, we only allow five verification attempts. Please call us at 1-800-600-5402 for assistance.." error message on Signed RC checkout payment page

