Feature: Verification of BCOM Footer functionality in ISHIP modes

 ######################################### Customer Service Links in ISHIP Footer ######################################

  @B-47205 @artifact_navapp @domain_discovery @mode_iship @priority_high @use_regression
  Scenario Outline: Footer - Verify Customer Service links in iship mode
    Given I visit the web site as a guest user in "iship" mode
    When I Navigate to "<CUSTOMER_SERVICE>" footer links
    Then I verify the basic attributes "<CUSTOMER_SERVICE>" page
  Examples:
    | CUSTOMER_SERVICE |
    | CUSTOMER SERVICE |
    | FAQs             |
    | Visitor Services |
    | Domestic         |

 ######################################### Your Order Links in ISHIP Footer ######################################
  @B-47205 @artifact_navapp @domain_discovery @mode_iship @priority_high @use_regression
  Scenario Outline: Footer - Verify Your Order links in iship mode
    Given I visit the web site as a guest user in "iship" mode
    When I Navigate to "<YOUR_ORDER>" footer links
    Then I verify the basic attributes "<YOUR_ORDER>" page
    Examples:
      | YOUR_ORDER          |
      | YOUR ORDER          |
      | Order Status        |
      | Shipping Policy     |
      | Returns & Exchanges |

 ######################################### Company Links in ISHIP Footer ######################################

  @B-47205 @artifact_navapp @domain_discovery @mode_iship @priority_high @use_regression
  Scenario Outline: Footer - Verify Company links in iship mode
    Given I visit the web site as a guest user in "iship" mode
    When I Navigate to "<COMPANY>" footer links
    Then I verify the basic attributes "<COMPANY>" page
  Examples:
    | COMPANY  |
    | COMPANY  |
    | About Us |
    | b.cause  |
    | Careers  |

 ######################################### Ways To Shop Links in ISHIP Footer ######################################

  @B-47205 @artifact_navapp @domain_discovery @mode_iship @priority_high @use_regression
  Scenario Outline: Footer - Verify Ways To Shop links in iship mode
    Given I visit the web site as a guest user in "iship" mode
    When I Navigate to "<WAYS_TO_SHOP>" footer links
    Then I verify the basic attributes "<WAYS_TO_SHOP>" page
    Examples:
      | WAYS_TO_SHOP    |
      | WAYS TO SHOP    |
      | Online & Mobile |
      | Stores          |
      | Outlets         |


 ######################################### My Account and My Credit Card inks should not be present in ISHIP footer ######################################

  @B-47205 @artifact_navapp @domain_discovery @mode_iship @priority_high @use_regression
  Scenario: Footer - Verify My Account and My Credit Card links are not present in iship mode
    Given I visit the web site as a guest user in "iship" mode
    Then I should not see below links on international Page
    | My Loyallist        |
    | My Account          |
    | Pay Bill            |
    | Cardholder Benefits |
    | Apply & Lean More   |

  @B-47205 @artifact_navapp @domain_discovery @mode_iship @priority_high @use_regression
  Scenario: Footer - Verify all legal notice links on international site homepage
    Given I visit the web site as a guest user in "iship" mode
    When I verify all legal notice links in the Footer
