# Author: I Can Wait Project QE Team
# Date Created: 05/01/2017

Feature: ICW mMoney enabled functionality scenarios

  @project_icw @domain_purchase_and_delivery @scenario1
  Scenario Outline: As a guest user, Verify the estimate ICW mMoney amount on RC checkout page after selecting nohurry option when experiment id supports icw mmoney
    Given I visit the web site as a guest user
    And I pass "<experimentCookie>" in segment cookie
    When I add a "mbmoney and orderable and mbmoney_eligible" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    When I select nohurry in shipping methods
    Then I should see icw mMoney message on shipping method section based on experiment id:
      | Plus get $5 Macy's Money! |
    When I checkout until I reach the payment page as a "guest" user
    Then I should see estimated mMoney amount is displayed on RC order summary section
  Examples:
  |experimentCookie|
  |2357            |

  @project_icw @domain_purchase_and_delivery @scenario2
  Scenario Outline: As a guest user, Verify the estimate ICW mMoney amount on RC checkout page after selecting nohurry option when experiment id does not supports icw mmoney
    Given I visit the web site as a guest user
    And I pass "<experimentCookie>" in segment cookie
    When I add a "mbmoney and orderable and mbmoney_eligible" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    When I select nohurry in shipping methods
    Then I should not see icw mMoney message on shipping method section based on experiment id:
      | Plus get $5 Macy's Money! |
  Examples:
  |experimentCookie|
  |2340            |