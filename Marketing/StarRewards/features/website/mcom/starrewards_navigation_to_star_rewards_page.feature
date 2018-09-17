# Author: Star Rewards Project QE Team
# Date Created: 11/24/2017
# Version One: MCOM Story

Feature: Navigation to star rewards page

  @wip @domain_marketing @artifact_xapi @project_star_rewards
  Scenario: Verify the Star Rewards link in My Account header drop down as a signed in user from different pages
    Given I visit the web site as a registered user
    Then I click on Star Rewards link in my account dropdown on below pages:
      | page_name | link                  | page                 |
      | home      | goto_guest_my_account | my_account           |
      | home      | stores_header         | stores               |
      | home      | deals_header          | deals_and_promotions |
      | home      | lists_header          | wish_list            |
      | home      | weddingregistry_fob   | registry_home        |

  @wip @domain_marketing @artifact_xapi @project_star_rewards
  Scenario: Verify Star Rewards link in My Account header drop down as signedin user by clicking on links in Footer section
    Given I visit the web site as a registered user
    Then I click on Star Rewards link in my account dropdown on below pages:
      | page_name | link                            | page                            |
      | footer    | goto_order_status               | order_status                    |
      | footer    | goto_us_site                    | international_shipping          |
      | footer    | goto_credit_services            | credit_service_gateway_signedin |
      | footer    | goto_credit_pay_bill_online     | credit_service_gateway_signedin |
      | footer    | goto_credit_cardholder_benefits | credit_benefits                 |
      | footer    | goto_credit_apply_now           | credit_benefits                 |
      | footer    | goto_our_stores                 | our_stores                      |
      | footer    | goto_store_locations_and_hours  | store_locations                 |

  @wip @domain_marketing @artifact_xapi @project_star_rewards
  Scenario: Verify the Star Rewards link in My Account header drop down as a guest user from different pages
    Given I visit the web site as a guest user
    Then I click on Star Rewards link in my account dropdown on below pages:
      | page_name | link                  | page                 |
      | home      | goto_sign_in_link     | sign_in              |
      | home      | goto_guest_my_account | my_account           |
      | home      | stores_header         | stores               |
      | home      | deals_header          | deals_and_promotions |
      | home      | lists_header          | wish_list            |
      | home      | weddingregistry_fob   | registry_home        |

  Scenario: Verify Star Rewards link in My Account header drop down as guest user by clicking on links in Footer section
    Given I visit the web site as a guest user
    Then I click on Star Rewards link in my account dropdown on below pages:
      | page_name | link                            | page                         |
      | footer    | goto_order_status               | order_status                 |
      | footer    | goto_us_site                    | international_shipping       |
      | footer    | goto_credit_services            | credit_service_gateway_guest |
      | footer    | goto_credit_pay_bill_online     | credit_service_gateway_guest |
      | footer    | goto_credit_cardholder_benefits | credit_benefits              |
      | footer    | goto_credit_apply_now           | credit_benefits              |
      | footer    | goto_our_stores                 | our_stores                   |
      | footer    | goto_store_locations_and_hours  | store_locations              |