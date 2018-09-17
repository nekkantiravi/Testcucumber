#####################################################################################
# Author : Credit Systems Conversion Carryover :: QE Team
#B-90757 :SDT:UI::MEW::BCOM::Comp-Credit Gateway Redesign:: Left navigation
#####################################################################################

Feature: As an online credit customer, I would like to verify left navigation links on gateway and non gateway pages


  @domain_customer @artifact_shopapp @project_responsive_gateway @release_17O
  Scenario: Verify display of links on credit gateway page as sign in user
    Given I sign to mobile website with user having "single_line_amex_user" added in profile
    When I navigate to credit service gateway page on MEW
    Then I should see below elements in credit service gateway page:
      | promotions     |
      | stores         |
      | fob_my_account |
      | credit_card    |
      |fob_my_wallet   |
      | fob_my_loyalist|
      | fob_registry   |
      | fob_wish_list  |
      | fob_recent_activity|

  @domain_customer @artifact_shopapp @project_responsive_gateway @release_17O
  Scenario: Verify the navigation of credit gateway page from non gateway page as sign in user
    Given I sign to mobile website with user having "single_card_ss" added in profile
    When I navigate the global navigation menu as follows:
      | MY ACCOUNT |
    And I should see View details link as part of the scheduled payment message
    When I navigate the global navigation menu as follows:
      | Macy's Credit Card |
    Then I should reach on the "/my-credit/gateway" page

  @domain_customer @artifact_shopapp @project_responsive_gateway @release_17O
  Scenario: Verify display of links on credit gateway page as guest user
    Given I visit the mobile web site as a guest user without add CC
    When I navigate to credit service gateway page on MEW
    Then I should see below elements in credit service gateway page:
      | promotions     |
      | stores         |
      | fob_my_account |
      | credit_card    |
      |fob_my_wallet   |
      | fob_my_loyalist|
      | fob_registry   |
      | fob_wish_list  |
      | fob_recent_activity|

  @domain_customer @artifact_shopapp @project_responsive_gateway @release_17O
  Scenario: Verify the navigation of credit gateway page from non gateway page as guest user
    Given I visit the web site as a guest user
    When I navigate the global navigation menu as follows:
      | Macy's Credit Card |
    Then I should reach on the "/my-credit/gateway" page



