Feature: Home Page validations

  @use_regression @domain_marketing @launch_call
  Scenario: HomePage - Validate all links
  Given I visit the web site as a guest user
  And I verify all links in homepage return valid response

  @use_regression @domain_marketing @launch_call
  Scenario: Home Page - Verify TOPNAV elements are displayed in DOMESTIC mode
    Given I visit the web site as a guest user
    Then I verify TOPNAV elements are displayed and return a 200 OK
      | TOPNAV           |
      | sign in          |
      | my account       |
      | stores           |
      | deals            |
      | lists            |
      | gifts            |
      | wedding registry |

  @use_regression @domain_marketing @launch_call
  Scenario: Home Page - Verify TOPNAV elements are displayed in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    When I change country to "India"
    And I close the welcome mat if it's visible
    Then I verify TOPNAV elements are displayed and return a 200 OK
      | TOPNAV           |
      | stores           |
      | wedding registry |
      | customer service |
      | shipping to      |
      | IN flag          |

  @use_regression @domain_marketing @launch_call
  Scenario: Home Page - Verify FOBs in DOMESTIC mode
    Given I visit the web site as a guest user
    And I verify FOBS are displayed and return a 200 OK
      | FOBS                   |
      | HOME                   |
      | BED & BATH             |
      | WOMEN                  |
      | MEN                    |
      | JUNIORS                |
      | KIDS                   |
      | BEAUTY                 |
      | SHOES                  |
      | HANDBAGS               |
      | JEWELRY                |
      | WATCHES                |
      | BRANDS                 |
      | ACTIVE                 |

  @use_regression @domain_marketing @launch_call
  Scenario: Home Page - Verify FOBs in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    When I change country to "Australia"
    And I close the welcome mat if it's visible
    And I verify FOBS are displayed and return a 200 OK
      | FOBS                   |
      | HOME                   |
      | BED & BATH             |
      | WOMEN                  |
      | MEN                    |
      | JUNIORS                |
      | KIDS                   |
      | SHOES                  |
      | HANDBAGS & ACCESSORIES |
      | JEWELRY                |
      | WATCHES                |
      | BRANDS                 |