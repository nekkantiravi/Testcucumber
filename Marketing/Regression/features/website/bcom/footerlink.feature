Feature: Home Footer links

  @dsv_desktop_sev2
  Scenario Outline: Verify the Customer Service footer links navigation in page
    Given I visit the web site as a guest user
    When I Navigate to "<links>" footer links
    Then I verify navigated to the "<links>" page
    Examples:
      |links              |
      |CUSTOMER SERVICE   |
      |Shipping Policy    |
      |Returns & Exchanges|
      |International      |

  @dsv_desktop_sev2
  Scenario Outline: Verify the My Account footer links navigation in page
    Given I visit the web site as a guest user
    When I Navigate to "<links>" footer links
    Then I verify navigated to the "<links>" page
    Examples:
      |links         |
      |MY ACCOUNT    |
      |Order Status  |
      |My Loyallist  |
      |My Profile    |

  @dsv_desktop_sev2
  Scenario Outline: Verify the My credit card footer links navigation in page
    Given I visit the web site as a guest user
    When I Navigate to "<links>" footer links
    Then I verify navigated to the "<links>" page
    Examples:
      |links               |
      |MY CREDIT CARD      |
      |Pay Bill            |
      |Cardholder Benefits |
      |Learn More & Apply  |

  @dsv_desktop_sev2
  Scenario Outline: Verify the Company footer links navigation in page
    Given I visit the web site as a guest user
    When I Navigate to "<links>" footer links
    Then I verify navigated to the "<links>" page
    Examples:
      |links    |
      |COMPANY  |
      |About Us |
      |b.cause  |
      |Careers  |

  @dsv_desktop_sev2
  Scenario Outline: Verify the Ways to Shop footer links navigation in page
    Given I visit the web site as a guest user
    When I Navigate to "<links>" footer links
    Then I verify navigated to the "<links>" page
    Examples:
      |links                    |
      |WAYS TO SHOP             |
      |Online & Mobile          |
      |Stores                   |
      |Pick Up In Store         |

  @dsv_desktop_sev2
  Scenario Outline: Verify that social icons click navigate to the correct page
    Given I visit the web site as a guest user
    When I verify the footer "<category>" is present and has the correct url
    Examples:
      |category  |
      |facebook  |
      |twitter   |
      |pinterest |
      |instagram |
      |mobile    |
      |tumblr    |
      |snapchat  |

  @dsv_desktop_sev2
  Scenario: Verify info & exclusion footer links navigate to the correct page
    Given I visit the web site as a guest user
    When I click on INFO and EXCLUSIONS link
    Then I select and verify footer Ad banner