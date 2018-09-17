Feature: Cobrand message display and removal

# Kill switch :: creditCoBrandMigrationEnabled should be TRUE

############ B-77458 & B-77460 ::: TRA3/TRB3 message display and removal ################
  @domain_customer @project_cobrand_migration @release_17G
  Scenario: Verify TRA3 message display on wallet page
    Given I sign in with user having "tra3_msg_eligible" added in wallet
    When I navigate to My Wallet page from My Account page
    Then I should see "tra3_header_message" on wallet page
    And I should see "tra3_body_message" on wallet page

  @domain_customer @project_cobrand_migration @release_17G
  Scenario: Verify TRB3 message display on wallet page
    Given I sign in with user having "trb3_msg_eligible" added in wallet
    When I navigate to My Wallet page from My Account page
    Then I should see "trb3_header_message" on wallet page
    And I should see "trb3_body_message" on wallet page

  @domain_customer @project_cobrand_migration @release_17G
  Scenario Outline:Verify Learn More functionality when user shows with TRA message eligibility on Gateway page
    Given I sign in with user having "<card_type>" added in wallet
    When I navigate to My Wallet page from My Account page
    And I click on Learn More link on "my_bwallet" page
    Then I should reach on the "<FAQ_page>" page
    Examples:
      | card_type         | FAQ_page                                                            |
      | tra3_msg_eligible | https://bloomingdales--tst.custhelp.com/app/answers/detail/a_id/577 |
      | trb3_msg_eligible | https://bloomingdales--tst.custhelp.com/app/answers/detail/a_id/575 |

  @domain_customer @project_cobrand_migration @release_17G @one
  Scenario: Verify TRA3 Message dismissal functionality on wallet page
    Given I sign in with user having "tra3_msg_eligible" added in wallet
    When I navigate to My Wallet page from My Account page
    And I click on X icon on "my_bwallet" page
    Then I should not see "tra3_header_message" on wallet page

  @domain_customer @project_cobrand_migration @release_17G
  Scenario: Verify TRB3 Message dismissal functionality on wallet page
    Given I sign in with user having "trb3_msg_eligible" added in wallet
    When I navigate to My Wallet page from My Account page
    And I click on X icon on "my_bwallet" page
    Then I should not see "trb3_header_message" on wallet page

  # As per story we need to test in MEW after cobrand message suppession on desktop wallet
  @use_manual @domain_customer @project_cobrand_migration @release_17G @release_17G
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


