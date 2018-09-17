# Author: I Can Wait Project QE Team
# Date Created: 10/04/2016

Feature: ICW core functionality scenarios

  @project_icw @domain_purchase_and_delivery @scenario1
  Scenario Outline: I a guest user, verify user does not gets NHS for 1332 for which ICE_ENABLED is false
    Given I visit the web site as a guest user
    And I pass "<experimentCookie>" in segment cookie
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    Then I should see below shipping options:
      | standard_shipping | true  |
      | premium_shipping  | true  |
      | express_shipping  | true  |

    Examples:
      |experimentCookie|
      |1332            |

  @project_icw @domain_purchase_and_delivery @scenario2
  Scenario Outline: I a guest user, verify user gets NHS for 2334 for which ICE_ENABLED is true
    Given I visit the web site as a guest user
    And I pass "<experimentCookie>" in segment cookie
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    Then I should see below shipping options:
      | standard_shipping | true  |
      | premium_shipping  | true  |
      | express_shipping  | true  |
      | nohurry_shipping  | false |

    Examples:
      |experimentCookie|
      |2334            |
