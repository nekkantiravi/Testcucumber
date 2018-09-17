Feature: Cobrand message display and removal

# Kill switch :: creditCoBrandMigrationEnabled should be TRUE

############ B-74830 ::: TRA3/TRB3 message display and removal ################
  @domain_customer @artifact_shopapp @project_cobrand_migration @release_17G
  Scenario: Verify TRA3 message display on wallet page
    Given I sign in with user having "tra3_msg_eligible" added in wallet
    When I navigate to My Wallet page from My Account page
    Then I should see "tra3_header_message" on wallet page
    And I should see "tra3_body_message" on wallet page

  @domain_customer @artifact_shopapp @project_cobrand_migration @release_17G
  Scenario: Verify TRB3 message display on wallet page
    Given I sign in with user having "trb3_msg_eligible" added in wallet
    When I navigate to My Wallet page from My Account page
    Then I should see "trb3_header_message" on wallet page
    And I should see "trb3_body_message" on wallet page

  @domain_customer @artifact_shopapp @project_cobrand_migration @release_17G
  Scenario Outline:Verify Learn More functionality when user shows with TRA message eligibility on Gateway page
    Given I sign in with user having "<card_type>" added in wallet
    When I navigate to My Wallet page from My Account page
    And I click on Find Out More link on "oc_my_wallet" page
    Then I should reach on the "<FAQ_page>" page
  Examples:
    | card_type         | FAQ_page                                                    |
    | tra3_msg_eligible | https://macys--tst.custhelp.com/app/answers/detail/a_id/380 |
    | trb3_msg_eligible | https://macys--tst.custhelp.com/app/answers/detail/a_id/609 |

  @domain_customer @artifact_shopapp @project_cobrand_migration @release_17G
  Scenario: Verify TRA3 Message dismissal functionality on wallet page
    Given I sign in with user having "tra3_msg_eligible" added in wallet
    When I navigate to My Wallet page from My Account page
    And I click on suppress link on "oc_my_wallet" page
    Then I should not see "tra3_header_message" on wallet page

  @domain_customer @artifact_shopapp @project_cobrand_migration @release_17G
  Scenario: Verify TRB3 Message dismissal functionality on wallet page
    Given I sign in with user having "trb3_msg_eligible" added in wallet
    When I navigate to My Wallet page from My Account page
    And I click on suppress link on "oc_my_wallet" page
    Then I should not see "trb3_header_message" on wallet page

  # As per story we need to test in MEW after cobrand message suppession on desktop wallet
  @use_manual @domain_customer @project_cobrand_migration @release_17G
  Scenario: Verify Post TRA3 Message dismissal on MEW
    Given I login in MEW as the same user with already dismissed tra3_message
    And I navigate to Wallet page
    Then I should not see tra3_message on page

  # As per story we need to test in MEW after cobrand message suppession on desktop wallet
  @use_manual @domain_customer @project_cobrand_migration @release_17G
  Scenario: Verify Post TRB3 Message dismissal on MEW
    Given I login in MEW as the same user with already dismissed TRB3 message
    And I navigate to Wallet page
    Then I should not see TRA3 message on page


