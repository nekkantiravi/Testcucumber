#####################################################################################
# Story             :  B-03632 : BUS::UI::BCOM::Footer Links as Guest
#                   : B-03633 : BUS::UI::BCOM::Footer Links as Logged In User
#                   : B-79885 : Desktop::M/BCOM::SDT Migration::Credit Service Footer Links
# Author            : Credit Systems Conversion Carryover :: QE Team
#####################################################################################

Feature: As a guest or logged in user, I want have a landing page that provides an
  overview of the credit services footer link's.

  @use_regression @domain_customer @project_csr @release_15K @use_browser @use_domain_qual
  Scenario Outline: Verify the credit servicing footer link navigation for signed-in user from My account page
    Given I visit the web site as a registered user
    When I navigate to my account page
    Then I should see below credit card links in footer section:
      | link_text           | credit_link                     |
      | MY CREDIT CARD      | goto_credit_services            |
      | Pay Bill            | goto_credit_pay_bill_online     |
      | Cardholder Benefits | goto_credit_cardholder_benefits |
      | Learn More & Apply  | goto_credit_apply_now           |
    When I navigate to the "<Credit_link>" page from footer
    Then I should reach on the "<landing_page>" page
    Examples:
      | Credit_link         | landing_page                      |
      | credit services     | /creditservice/gateway            |
      | Pay Bill            | /creditservice/gateway            |
      | Cardholder Benefits | /creditservice/marketing/benefits |
      | Apply & Learn More  | /creditservice/marketing/main     |


  @use_regression @domain_customer @project_csr @release_15K @use_browser @use_domain_qual
  Scenario Outline: Verify the credit servicing footer link navigation for non signed-in user
    Given I visit the web site as a guest user
    Then I should see below credit card links in footer section:
      | link_text           | credit_link                     |
      | MY CREDIT CARD      | goto_credit_services            |
      | Pay Bill            | goto_credit_pay_bill_online     |
      | Cardholder Benefits | goto_credit_cardholder_benefits |
      | Learn More & Apply  | goto_credit_apply_now           |
    When I navigate to the "<Credit_link>" page from footer
    Then I should reach on the "<landing_page>" page
    Examples:
      | Credit_link         | landing_page                      |
      | credit services     | /creditservice/gateway            |
      | Pay Bill            | /account/signin                   |
      | Cardholder Benefits | /creditservice/marketing/benefits |
      | Apply & Learn More  | /creditservice/marketing/main     |

### Note :: Pay bill footer link scenarios are covered as part of B-74305 story
