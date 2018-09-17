#Author: UFT team
#Date Created: 5/22/2017
#Date Signed Off:
#Version One: B-80157

Feature: As a product owner, I would like to remove "my" text from account header drop down value

  @mode_domestic @priority_medium @domain_discovery @use_regression
  Scenario Outline: HeaderAndFooter - Domestic - Verify that the my text is removed from account header drop down value
    Given I visit the web site as a <user_type> user
    Then I verify that new dropdown link names are displayed
      | Macy's Credit Card |
      | Order History      |
      | Profile            |
      | Wallet             |
      | Plenti             |
      | Lists              |
      | STAR REWARDS       |
  Examples:
    | user_type  |
    | guest      |
    | registered |