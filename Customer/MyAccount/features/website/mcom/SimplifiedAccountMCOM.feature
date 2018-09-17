#Author: Amol Ray
#Migrated by : Manjeet Ranga
#Date Created: 08/09/2017

Feature: Verify create profile functionality with few fields (MVP6)

  @use_regression @domain_customer @migrated_to_sdt
  Scenario: Verify My Account Page Title
    Given I visit the web site as a registered user
    When I navigate to my account page
    Then I validate the my account page title "My Account"

  @artifact_shopapp @domain_customer @release_16K @project_UFT @mode_domestic @use_regression  @migrated_to_sdt
  Scenario: Verify flyouts from FOBs are expanded in my account page
    Given I visit the web site as a registered user
    When I navigate to my account page
    When I mouse hover on any category
    Then I should see flyout menu

  @use_regression @domain_customer @artifact_shopapp @domain_marketing @priority_high @use_regression_4 @mode_domestic @migrated_to_sdt
  Scenario: Verify new legal notice text in the footer on MyAccount page
    Given I visit the web site as a registered user
    When I navigate to my account page
    Then I verify the new legal notice text in the footer:
      | 2017 macys.com is a registered trademark. All rights reserved. |

  @use_regression @domain_customer @artifact_shopapp @domain_marketing @priority_high @use_regression_4 @mode_domestic @migrated_to_sdt
  Scenario: Verify new legal notice text in the footer on signin page
    Given I visit the web site as a guest user
    When I navigate to sign in page
    Then I verify the new legal notice text in the footer:
      | 2017 macys.com is a registered trademark. All rights reserved. |