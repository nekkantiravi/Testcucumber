#Author: UFT team
#Migrated by : Manjeet Ranga
#Date Created: 08/09/2017
#Date Signed Off:
#Version One: B-76617

Feature: Update the text on the pop-up

  @artifact_shopapp @mode_domestic @release_17H @priority_medium @domain_customer @project_UFT @use_regression @migrated_to_sdt
  Scenario: Verify text changes on See all benefits of creating an account pop up
    Given I visit the web site as a guest user
    When I navigate to sign in page
    And I select See All The Benefits Of Creating A Profile on sign in page
    Then I verify below "header" on create profile benefits pop up banner
      | Being a part of the Macy's family is better than ever! |
    And I verify below "description" on create profile benefits pop up banner
      | With a Macy's account, you'll enjoy convenient features designed to make your shopping experience quick, easy and filled with exclusive benefits: |
    And I verify below content on create profile benefits pop up banner
      | Address book - securely store the shipping addresses you use most often.                           |
      | Wallet - save your billing information securely and take advantage of speedy checkout.             |
      | Lists - store your favorite items and let friends and family know exactly what you're wishing for. |
