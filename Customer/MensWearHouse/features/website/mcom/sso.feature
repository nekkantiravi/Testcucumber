Feature: Verify Single Sign On changes related to Tuxedo product.

  @project_menswearhousedigital @feature_sso @use_wip @domain_customer_management @priority_medium
  Scenario Outline: Verify Search strings containing the tuxedo rental text at the end are submitted correctly
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    And I search for "tuxedo rentals"
    Then I should be in Search Landing page
    And I verify that page have keyword as "Tux Styles"
    Examples:
      | shopping_mode    |
      | domestic         |
      | registry         |
      | iship            |

  @project_menswearhousedigital @feature_sso @use_wip @domain_customer_management @priority_medium
  Scenario Outline: Verify tuxedo rental strings from FOB
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    And I navigate to "Men" category page
    Then I navigate to the "Tuxedos & Formalwear" browse page under "Men"
    Then I navigate to the "Tuxedo Rental Shop" category page under "Tuxedos & Formalwear"
    Then I should be in Search Landing page
    And I verify that page have keyword as "Tux Styles"
    Examples:
      | shopping_mode    |
      | domestic         |
      | registry         |
      | iship            |


