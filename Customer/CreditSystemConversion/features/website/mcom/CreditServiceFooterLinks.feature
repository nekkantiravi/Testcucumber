#####################################################################################
# Story             :  B-03143 : BUS::UI::MCOM::Footer Links as Guest
#                   : B-01226 : BUS::UI::MCOM::Footer Links as Logged In User
#                   : B-43027 : BUS::UI::MCOM::Page Footer:: Redirect "pay bill" link to Citi/Fusion
#                   : B-79885:Desktop::M/BCOM::SDT Migration::Credit Service Footer Links
# Author            : Credit Systems Conversion Carryover :: QE Team
#####################################################################################

Feature: As a guest or logged in user, I want have a landing page that provides an
  overview of the credit services footer link's.

  @use_regression @domain_customer @project_csr @release_15K @use_browser @wip
  Scenario Outline: Verify the credit servicing footer link navigation for signed-in user from My account page
    Given I visit the web site as a registered user
    When I navigate to my account page
    Then I should see below credit card links in footer section:
      | link_text           | credit_link                     |
      | MACY'S CREDIT CARD  | goto_credit_services            |
      | pay bill            | goto_credit_pay_bill_online     |
      | cardholder benefits | goto_credit_cardholder_benefits |
      | learn more & apply  | goto_credit_apply_now           |
    When I navigate to the "<Credit_link>" page from footer
    Then I should reach on the "<landing_page>" page
    Examples:
      | Credit_link         | landing_page                      |
      | credit services     | /creditservice/gateway            |
      | pay bill            | /creditservice/gateway            |
      | cardholder benefits | /creditservice/marketing/benefits |
      | apply & learn more  | /creditservice/marketing/main     |


  @use_regression @domain_customer @project_csr @release_15K @use_browser @wip
  Scenario Outline: Verify the credit servicing footer link navigation for guest user from My account page
    Given I visit the web site as a guest user
    Then I should see below credit card links in footer section:
      | link_text           | credit_link                     |
      | MACY'S CREDIT CARD  | goto_credit_services            |
      | pay bill            | goto_credit_pay_bill_online     |
      | cardholder benefits | goto_credit_cardholder_benefits |
      | learn more & apply  | goto_credit_apply_now           |
    When I navigate to the "<Credit_link>" page from footer
    Then I should reach on the "<landing_page>" page
    Examples:
      | Credit_link         | landing_page                      |
      | credit services     | /creditservice/gateway            |
      | pay bill            | /account/signin                   |
      | cardholder benefits | /creditservice/marketing/benefits |
      | apply & learn more  | /creditservice/marketing/main     |

  @use_regression @domain_customer @project_csr @release_15K @use_browser @use_domain_qual
  Scenario: Verify user should land on credit gateway page when guest user signin after clicking pay bill link
    Given I visit the web site as a registered user
    And I sign out from my current profile
    When I navigate to the "pay bill" page from footer
    Then SignIn page should get loaded
    When I sign in with "profile_nocard" user
    Then I should get a pop-up to add a card to profile

   ##Pre-condition: credit_5_2016_Enabled kill switch should be true for below three scenarios.

  @domain_customer @project_credit_service @artifact_shopapp @release_16G
  Scenario: verify functionality of Pay My Bill from MCOM footer for a signed in user with card.
    Given I sign in with user having "new_prop_user" added in profile
    When I navigate to the "pay bill" page from footer
    Then I should redirect to "citi_fusion" page

  @domain_customer @project_credit_service @artifact_shopapp @release_16G
  Scenario: verify functionality of Pay My Bill from MCOM footer for a signed in user without card.
    Given I visit the web site as a registered user
    When I navigate to the "Pay bill" page from footer
    Then I should get a pop-up to add a card to profile
    And I clicked on Add Card button on credit gate way page
    Then I should redirect to "citi_fusion" page

  @domain_customer @project_credit_service @artifact_shopapp @release_16G
  Scenario: verify the functionality of Pay My Bill link from MCOM footer for an unauthenticated user.
  Given I visit the web site as a guest user
   When I navigate to the "pay bill" page from footer
   Then I should reach on the "/account/signin" page
