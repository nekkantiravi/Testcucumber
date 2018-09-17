Feature: Loyalty Account Management
  #For tests specifically interacting with a loyalty account summary page and editing or removing.

  @use_regression @artifact_shopapp @artifact_shopapp_2 @priority_high @s4a_stable @domain_marketing @loyalty_manage
  Scenario: Verify Account Management Remove Loyallist Number
    Given I visit the web site as a loyallist
    When I navigate to the loyalty account summary page
    Then I can remove my loyallist number

  @use_regression @use_launch_call @s4a_stable @domain_marketing @loyalty_manage
  Scenario: Verify Account Summary Edit is Successful
    Given I visit the web site as a loyallist
    When I navigate to the loyalty account summary page
    Then I can edit my loyallist account and verify the changes

  #BLCOM-80289
  @artifact_shopapp @priority_high @artifact_shopapp_2 @health_check @domain_marketing @loyalty_manage
  Scenario: Verify Loyalty Account Summary Page Registered User
    Given I visit the web site as a registered user without loyalty
    And I navigate to the loyalty account summary page
    Then I verify the account summary page for the loyallists:
      | pending_points                |
      | less_than_2500_points         |
      | more_than_2500_points         |
      | negative_points               |
      | toptier_pending_points        |
      | toptier_less_than_2500_points |
      | toptier_more_than_2500_points |
      | toptier_negative_points       |
