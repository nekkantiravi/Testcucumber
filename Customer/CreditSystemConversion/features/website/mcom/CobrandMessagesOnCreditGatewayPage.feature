#####################################################################################
# Author : Credit Systems Conversion Carryover :: QE Team
#####################################################################################

Feature: As a customer who meets the criteria to receive message TRA1, TRA2, TRB1, or TRB2, I want to see that message on the MCOM Desktop Credit Gateway page.

# Pre-Requisite:1.Logon profile meets criteria to receive message TRA1, TRA2, TRB1, or TRB2
#				2.Customer with this logon profile has not previously dismissed( clicked on "X" in the message UI of message )
#				3.Feature will be Wrapped under Kill Switch: creditCoBrandMigrationEnabled= true


################ B-71522 : CM4:: BUS::UI:: Desktop:: MCOM::Deliver Message TRA1, TRA2, TRB1, TRB2 on Credit Gateway page (QE only, test in MOCK) ############
  @domain_customer @artifact_shopapp @project_cobrand_migration @release_17G
  Scenario: Verify TRA1 message display on Gateway page
    Given I sign in with user having "tra1_msg_eligible" added in profile
    When I navigate to the "credit services" page from footer
    Then I should see "tra1_header_message" on credit gateway page
    And I should see "tra1_body_message" on credit gateway page

  @domain_customer @artifact_shopapp @project_cobrand_migration @release_17G
  Scenario: Verify TRA2 message display on Gateway page
    Given I sign in with user having "tra2_msg_eligible" added in profile
    When I navigate to the "credit services" page from footer
    Then I should see "tra2_header_message" on credit gateway page
    And I should see "tra2_body_message" on credit gateway page

  @domain_customer @artifact_shopapp @project_cobrand_migration @release_17G
  Scenario: Verify TRB1 message display on Gateway page
    Given I sign in with user having "trb1_msg_eligible" added in profile
    When I navigate to the "credit services" page from footer
    Then I should see "trb1_header_message" on credit gateway page
    And I should see "trb1_body_message" on credit gateway page

  @domain_customer @artifact_shopapp @project_cobrand_migration @release_17G
  Scenario: Verify TRB2 message display on Gateway page
    Given I sign in with user having "trb2_msg_eligible" added in profile
    When I navigate to the "credit services" page from footer
    Then I should see "trb2_header_message" on credit gateway page
    And I should see "trb2_body_message" on credit gateway page

  @domain_customer @artifact_shopapp @project_cobrand_migration @release_17G
  Scenario Outline:Verify Find Out More functionality when user shows with TRA message eligibility on Gateway page
    Given I sign in with user having "<card_type>" added in profile
    When I navigate to the "credit services" page from footer
    And I click on Find Out More link on "credit_service_gateway_signedin" page
    Then I should reach on the "<FAQ_page>" page
    Examples:
      | card_type         | FAQ_page                                                    |
      | tra1_msg_eligible | https://macys--tst.custhelp.com/app/answers/detail/a_id/380 |
      | tra2_msg_eligible | https://macys--tst.custhelp.com/app/answers/detail/a_id/380 |
      | trb1_msg_eligible | https://macys--tst.custhelp.com/app/answers/detail/a_id/609 |
      | trb2_msg_eligible | https://macys--tst.custhelp.com/app/answers/detail/a_id/609 |

############ B-71552 :: CM4:: BUS:: UI::Desktop:: MCOM:: Remove Message TRA1/TRB1 on Credit Gateway page (TEST IN MOCK) ################

  @wip @domain_customer @artifact_shopapp @project_cobrand_message @release_17G
  Scenario: Verify TRA1 Message dismissal functionality on gateway page
    Given I sign in with user having "tra1_msg_eligible" added in profile
    When I navigate to the "credit services" page from footer
    And I click on suppress link on "credit_service_gateway_signedin" page
    Then I should not see "tra1_header_message" on credit gateway page

  @wip @domain_customer @artifact_shopapp @project_cobrand_message @release_17G
  Scenario: Verify TRB1 Message dismissal functionality on gateway page
    Given I sign in with user having "trb1_msg_eligible" added in profile
    When I navigate to the "credit services" page from footer
    And I click on suppress link on "credit_service_gateway_signedin" page
    Then I should not see "trb1_header_message" on credit gateway page

  @use_manual @domain_customer @artifact_shopapp @project_cobrand_migration @release_17G
  Scenario: Verify TRA2 message dispaly when user suppress TRA1
    Given I sign in with user having "tra1_msg_eligible" added in profile
    When I navigate to the "credit services" page from footer
    And I click on X icon on "credit_service_gateway_signedin" page
    Then I should not see "tra1_header_message" on credit gateway page
    When I sign in again after eligible for timeframe2
    Then I should see "tra2_header_message" on credit gateway page
    And I should see "tra2_body_message" on credit gateway page

  @use_manual @domain_customer @artifact_shopapp @project_cobrand_migration @release_17G
  Scenario: Verify TRB2 message dispaly when user suppress TRB1
    Given I sign in with user having "trb1_msg_eligible" added in profile
    When I navigate to the "credit services" page from footer
    And I click on X icon on "credit_service_gateway_signedin" page
    Then I should not see "tra1_header_message" on credit gateway page
    When I sign in again after eligible for timeframe2
    Then I should see "trb2_header_message" on credit gateway page
    And I should see "trb2_body_message" on credit gateway page

############ B-71553 :: CM4:: BUS:: UI::Desktop:: MCOM:: Remove Message TRA2/TRB2 on Credit Gateway page (TEST IN MOCK) ################

  @wip @domain_customer @artifact_shopapp @project_cobrand_migration @release_17G
  Scenario: Verify TRA2 Message dismissal functionality on gateway page
    Given I sign in with user having "tra2_msg_eligible" added in profile
    When I navigate to the "credit services" page from footer
    And I click on suppress link on "credit_service_gateway_signedin" page
    Then I should not see "tra2_header_message" on credit gateway page

  @wip @domain_customer @artifact_shopapp @project_cobrand_migration @release_17G
  Scenario: Verify TRB2 Message dismissal functionality on gateway page
    Given I sign in with user having "trb2_msg_eligible" added in profile
    When I navigate to the "credit services" page from footer
    And I click on suppress link on "credit_service_gateway_signedin" page
    Then I should not see "trb2_header_message" on credit gateway page

  @wip @domain_customer @artifact_shopapp @project_cobrand_message @release_17G
  Scenario: Verify cobrand message display when user has multiple timeframe messages
    Given I sign in with user having "cobrand_multiple" added in profile
    When I navigate to the "credit services" page from footer
    And I click on suppress link on "credit_service_gateway_signedin" page
    Then I should not see "tra1_header_message" on credit gateway page
    And I navigate to the "credit services" page from footer
    Then I should see "trb2_header_message" on credit gateway page

  @wip @domain_customer @artifact_shopapp @project_cobrand_message @release_17G
  Scenario Outline: Verify cobrand message display when timeframe2 message is expired
    Given I sign in with user having "<card_type>" added in profile
    When I navigate to the "credit services" page from footer
    Then I should not see "<notification>" on credit gateway page
    Examples:
      | card_type       | notification |
      | trb2_msg_expire | trb2_body_message |
      | tra2_msg_expire | tra2_body_message |
  # As per story we need to test in MEW after cobrand message suppession on desktop wallet
  @use_manual @domain_customer @artifact_shopapp @project_cobrand_migration @release_17G
  Scenario Outline: Verify post cobrand message display on MEW
    Given I sign to mobile website with user having "<card_type>" added in profile
    When I navigate to my acocunt page
    Then I should not see "<notification>" on my account page
    Examples:
      | card_type           | notification |
      | tra2_msg_suppressed | tra2_message |
      | trb2_msg_suppressed | trb2_message |