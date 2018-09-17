Feature: Verify BCOM Footer UI

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify SIGN UP FOR EMAILS OR TEXTS button in the footer in domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    When I scroll the page down
    Then I should see SIGN UP FOR EMAILS OR TEXTS button in footer
    When I navigate to "emails or texts" footer links using mobile website
    Then I verify SIGN UP FOR EMAILS OR TEXTS page

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify SIGN UP FOR EMAILS OR TEXTS button in the footer in domestic mode with signed in user
    Given I visit the mobile web site as a registered user without add CC
    When I scroll the page down
    Then I should see SIGN UP FOR EMAILS OR TEXTS button in footer
    When I navigate to "emails or texts" footer links using mobile website
    Then I verify SIGN UP FOR EMAILS OR TEXTS page

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify SIGN UP FOR EMAILS OR TEXTS button in the footer in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I scroll the page down
    Then I should see SIGN UP FOR EMAILS OR TEXTS button in footer
    When I navigate to "emails or texts" footer links using mobile website
    Then I verify SIGN UP FOR EMAILS OR TEXTS page

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify SIGN UP FOR EMAILS OR TEXTS button in the footer in iship mode
    Given I visit the mobile web site as a guest user in iship mode
    When I scroll the page down
    Then I should see SIGN UP FOR EMAILS OR TEXTS button in footer
    When I navigate to "emails or texts" footer links using mobile website
    Then I should see signup for emails page opened in new tab

  @domain_mew_discovery @use_mew_regression
  Scenario Outline:  Footer: Verify all the links navigation under footer in Domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    When I scroll the page down
    When I Navigate to "<links>" page in footer
    Then I verify the page navigation for "<links>" page
    Examples:
      | links                     |
      | Sign in                   |
      | Change Country            |
      | Customer Service          |
      | Privacy Practices         |
      | Internet based Ads        |
      | Customers' Bill of Rights |
      | Full Site                 |
      | Way to Shop               |
      | Careers                   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline:  Footer: Verify all the links navigation under footer in Registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I scroll the page down
    When I Navigate to "<links>" page in footer
    Then I verify the page navigation for "<links>" page
    Examples:
      | links                     |
      | Sign in                   |
      | Change Country            |
      | Customer Service          |
      | Privacy Practices         |
      | Internet based Ads        |
      | Customers' Bill of Rights |
      | Full Site                 |
      | Way to Shop               |
      | Careers                   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline:  Footer: Verify all the links navigation under footer in iship mode
    Given I visit the mobile web site as a guest user in iship mode
    When I scroll the page down
    When I Navigate to "<links>" page in footer
    Then I verify the page navigation for "<links>" page
    Examples:
      | links                     |
      | Change Country            |
      | Customer Service          |
      | Privacy Practices         |
      | Internet based Ads        |
      | Customers' Bill of Rights |
      | Full Site                 |
      | Way to Shop               |
      | Careers                   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Footer: Verify all the social icon links under footer in domestic mode
    Given I visit the mobile web site as a guest user
    When I scroll the page down
    When I Navigate to "<Social_Link>" social link in footer
    Then I verify the navigation for "<Social_Link>" page
    Examples:
      | Social_Link |
      | Pinterest   |
      | Facebook    |
      | Twitter     |
      | Instagram   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Footer: Verify all the social icon links under footer in iship home page
    Given I visit the mobile web site as a guest user in iship mode
    When I scroll the page down
    When I Navigate to "<Social_Link>" social link in footer
    Then I verify the navigation for "<Social_Link>" page
    Examples:
      | Social_Link |
      | Pinterest   |
      | Facebook    |
      | Twitter     |
      | Instagram   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Search Landing Page: Verify all the footer links navigation under footer in Domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    When I search using mobile website for "jeans"
    Then I should be in Search Landing page using mobile website
    When I scroll the page down
    When I Navigate to "<links>" page in footer
    Then I verify the page navigation for "<links>" page
    Examples:
      | links                     |
      | Sign in                   |
      | Change Country            |
      | Customer Service          |
      | Privacy Practices         |
      | Internet based Ads        |
      | Customers' Bill of Rights |
      | Full Site                 |
      | Way to Shop               |
      | Careers                   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Member PDP Page: Verify all the footer links navigation under footer in Domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    And I navigate the global navigation menu as follows:
      | MEN   |
      | Jeans |
    And I select a random member product using mobile website
    Then I should be redirected to PDP page using mobile website
    When I scroll the page down
    When I Navigate to "<links>" page in footer
    Then I verify the page navigation for "<links>" page
    Examples:
      | links                     |
      | Sign in                   |
      | Change Country            |
      | Customer Service          |
      | Privacy Practices         |
      | Internet based Ads        |
      | Customers' Bill of Rights |
      | Full Site                 |
      | Way to Shop               |
      | Careers                   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: My Account Page: Verify all the footer links navigation under footer in Domestic mode
    Given I visit the mobile web site as a registered user
    When I navigate the global navigation menu as follows:
      | My Account |
    When I scroll the page down
    And I Navigate to "<links>" page in footer
    Then I verify the page navigation for "<links>" page
    Examples:
      | links                     |
      | Change Country            |
      | Customer Service          |
      | Privacy Practices         |
      | Internet based Ads        |
      | Customers' Bill of Rights |
      | Full Site                 |
      | Way to Shop               |
      | Careers                   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Browse Page: Verify all the links under footer in domestic mode
    Given I visit the mobile web site as a guest user
    And I navigate the global navigation menu as follows:
      | MEN   |
      | Jeans |
    Then I should be on mobile browse page
    When I scroll the page down
    When I Navigate to "<links>" page in footer
    Then I verify the page navigation for "<links>" page
    Examples:
      | links                     |
      | Sign in                   |
      | Change Country            |
      | Customer Service          |
      | Privacy Practices         |
      | Internet based Ads        |
      | Customers' Bill of Rights |
      | Full Site                 |
      | Way to Shop               |
      | Careers                   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify country logo visibility in footer
    Given I visit the mobile web site as a guest user in <mode> mode
    And I should see country logo in footer section
    Examples:
      | mode     |
      | domestic |
      | iship    |
      | registry |

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify currency in Iship mode search page
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to change country page using mobile website
    And I change country to "Australia" using mobile website
    And I close the welcome mat if it's visible using mobile website
    When I type "Jeans" in mew search and click enter
    Then I should be on the search results page
    And I should see price displayed in "Australia" currency

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify currency in Iship mode browse page
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to change country page using mobile website
    And I change country to "Australia" using mobile website
    And I close the welcome mat if it's visible using mobile website
    When I navigate the global navigation menu as follows:
      | MEN   |
      | Jeans |
    Then I should be on the browse page
    And I should see price displayed in "Australia" currency

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify currency in Iship mode search page after switching from domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "Jeans" in mew search and click enter
    Then I should be on the search results page
    When I navigate to change country page using mobile website
    And I change country to "Australia" using mobile website
    And I close the welcome mat if it's visible using mobile website
    Then I should be on the search results page
    And I should see price displayed in "Australia" currency

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify currency in Iship mode browse page after switching from domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate the global navigation menu as follows:
      | MEN   |
      | Jeans |
    Then I should be on the browse page
    When I navigate to change country page using mobile website
    And I change country to "Australia" using mobile website
    And I close the welcome mat if it's visible using mobile website
    Then I should be on the browse page
    And I should see price displayed in "Australia" currency

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify footer links on unified login page in domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    When I verify following "<category>" from global navigation
    When I scroll the page down
    Then I verify data in footer section of the unified login page
      | Terms of Use       |
      | Privacy Practices  |
      | Interest Based Ads |
      | Product Recall     |
    And I verify the copy right section on footer shows "© 2018 Bloomingdale's. 1000 Third Avenue New York, NY 10022."
    Examples:
      | category   |
      | My Account |
      | My bWallet |

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify footer links on create registry page in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Create Registry |
    When I scroll the page down
    Then I verify data in footer section of the unified login page
      | Terms of Use       |
      | Privacy Practices  |
      | Interest Based Ads |
      | Product Recall     |
    And I verify the copy right section on footer shows "© 2018 Bloomingdale's. 1000 Third Avenue New York, NY 10022."

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify footer links on manage registry page in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Manage Registry |
      | View Registry   |
    When I scroll the page down
    Then I verify data in footer section of the unified login page
      | Terms of Use       |
      | Privacy Practices  |
      | Interest Based Ads |
      | Product Recall     |
    And I verify the copy right section on footer shows "© 2018 Bloomingdale's. 1000 Third Avenue New York, NY 10022."
