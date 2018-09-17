#####################################################################################
# Author : Credit Systems Conversion Carryover :: QE Team
#####################################################################################

Feature: Cobrand message display and removal

# Kill switch :: creditCoBrandMigrationEnabled should be TRUE

############ B-75351 ::CM5:: BUS::UI:: MEW:: MCOM::Deliver Message TRA3, TRB3 on My Wallet page (QE only, test in MOCK) ################
  @domain_customer @project_Profile_Messaging_for_Cobrand_Migration @release_17G
  Scenario: Verify TRA3 message display on wallet page
    Given I sign to mobile website with user having "tra3_msg_eligible" added in wallet
    When I navigate the global navigation menu as follows:
      | Wallet |
    Then I should see "tra3_header_message" on wallet page
    And I should see "tra3_body_message" on wallet page

  @domain_customer @project_Profile_Messaging_for_Cobrand_Migration @release_17G
  Scenario: Verify TRB3 message display on wallet page
    Given I sign to mobile website with user having "trb3_msg_eligible" added in wallet
    When I navigate the global navigation menu as follows:
      | Wallet |
    Then I should see "trb3_header_message" on wallet page
    And I should see "trb3_body_message" on wallet page

  @domain_customer @project_Profile_Messaging_for_Cobrand_Migration @release_17G
  Scenario Outline:Verify Learn More functionality when user shows with TRA message eligibility on Gateway page
    Given I sign to mobile website with user having "<card_type>" added in wallet
    When I navigate the global navigation menu as follows:
      | Wallet |
    And I click on Learn More link on "oc_my_wallet" page
    Then I should reach on the "<FAQ_page>" page
  Examples:
    | card_type         | FAQ_page                                                    |
    | tra3_msg_eligible | https://macys--tst.custhelp.com/app/answers/detail/a_id/380 |
    | trb3_msg_eligible | https://macys--tst.custhelp.com/app/answers/detail/a_id/609 |

####### B-75352 :: CM5:: BUS:: UI::MEW:: MCOM:: Remove Message TRA3/TRB3 on My Wallet page (TEST IN MOCK) ##############
  @wip @domain_customer @project_cobrand_message @release_17G @one
  Scenario: Verify TRA3 Message dismissal functionality on wallet page
    Given I sign to mobile website with user having "tra3_msg_eligible" added in wallet
    When I navigate the global navigation menu as follows:
      | Wallet |
    And I click on X icon on "oc_my_wallet" page
    Then I should not see "tra3_header_message" on wallet page

  @wip @domain_customer @project_cobrand_message @release_17G @one
  Scenario: Verify TRB3 Message dismissal functionality on wallet page
    Given I sign to mobile website with user having "trb3_msg_eligible" added in wallet
    When I navigate the global navigation menu as follows:
      | Wallet |
    And I click on X icon on "oc_my_wallet" page
    Then I should not see "trb3_header_message" on wallet page

  # As per story we need to test in desktop after cobrand message suppession on MEW wallet
  @use_manual @domain_customer @project_cobrand_message @release_17G @release_17G
  Scenario: Verify Post TRA3 Message dismissal on desktop
    Given I login in desktop as the same user with already dismissed tra3_message
    And I navigate to Wallet page
    Then I should not see tra3_message on page

  # As per story we need to test in desktop after cobrand message suppession on MEW wallet
  @use_manual @domain_customer @project_credit_re_platform @credit_edit_remove @release_17G
  Scenario: Verify Post TRB3 Message dismissal on desktop
    Given I login in desktop as the same user with already dismissed TRB3 message
    And I navigate to Wallet page
    Then I should not see TRA3 message on page


