#Author: Marco Andrade
#Date Created: 5/18/15
#V1: https://www14.v1host.com/Macyscom/story.mvc/Summary?oidToken=Story:370779

  #  The below test cases is obsolete as preferences of Profile page moved to My Preferences page
#@use_regression @bcom_regression @bcom_crel @B-18281 @priority_high @myaccount_2
#Feature: Enrollment in Loyalty auto-opts in a customer to SMS marketing
#
#  Scenario: Text message preference subscribe checkbox should be unchecked after creating loyallist account and navigating to My Profile page
#    Given I visit the web site as a guest user
#    When I enroll into the Loyalty program by creating a new profile
#    And I navigate to My Account page
#    And I navigate to my profile
#    Then I should see text message checkbox unchecked on my profile page