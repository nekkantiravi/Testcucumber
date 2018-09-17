# Author: I Can Wait Project QE Team
# Date Created: 12/01/2016
# Version One: B-58612, B-58613, B-50832, B-50833, B-58982
Feature: ICW Redeem mMoney scenarios

  ## all these tests require REDEEM PERIOD active and presence of MMoney card used on Loyalty/MST side

  @project_icw @domain_purchase_and_delivery @scenario8 @icw_mMoney
  Scenario: registered user with icw MMoney cards should see the appropriate copy in the header
    Given I visit the web site as a registered user
    And current user has icw mmoney card in their wallet
    And I navigate to My Wallet page from My Account page
    And macys's money copy present

  @project_icw @domain_purchase_and_delivery @scenario8 @icw_mMoney
  Scenario Outline: registered user with icw MMoney cards should see them in wallet
    Given I visit the web site as a registered user
    And current user has <card> in their wallet
    And I navigate to My Wallet page from My Account page
    And there's <number> mmoney card in my wallet

    Examples:
      | card                    | number |
      | icw mmoney card         | 1      |
      | expired icw mmoney card | 0      |

  @project_icw @domain_purchase_and_delivery @scenario8 @icw_mMoney
  Scenario: registered user with icw MMoney cards should see mmoney message in bag when ICW Redeem is active
    Given I visit the web site as a registered user
    And current user has icw mmoney card in their wallet
    When I add an "available and orderable" product to my bag
    And I navigate to shopping bag page
    Then I should have Macy's Money to spend

  @project_icw @domain_purchase_and_delivery @scenario8 @icw_mMoney
  Scenario: registered user with icw MMoney cards should see them on checkout when ICW Redeem is active
    Given I visit the web site as a registered user
    And current user has icw mmoney card in their wallet
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as a "registered" user
    And I apply 1st gift card from the list
    Then there's 1 mmoney card applied

  # since it's impossible to enter captcha as of now this test will not pass
  @project_icw @domain_purchase_and_delivery @scenario8 @icw_mMoney @todo
  Scenario: guest user should be able to apply icw reward card on checkout
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the payment page as a "guest" user
    And I manually apply expired icw mmoney card as a guest user
    Then there's 1 mmoney card applied

  ## this test require EARN PERIOD active, and REDEEM present
  @project_icw @domain_purchase_and_delivery @scenario8 @icw_mMoney @todo
  Scenario Outline: learn more content section should contain info about redeem dates
    Given I visit the web site as a <user> user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping page as a "<user>" user
    And I select learn more link in nohurry shipping method
    Then learn more section has redeem period info

    Examples:
      | user       |
      | guest      |
      | registered |

  @project_icw @domain_purchase_and_delivery @scenario8 @icw_mMoney @todo
  Scenario: a registered places an order with and redeems an icw mmoney card during the process
    Given I visit the web site as a registered user
    And current user has icw mmoney card in their wallet
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as a "registered" user
    And I apply 1st gift card from the list
    And I checkout until I reach the order confirmation page as a "registered" user
    And I goto Home page
    And I navigate to My Wallet page from My Account page
    And there's 0 mmoney cards in my wallet