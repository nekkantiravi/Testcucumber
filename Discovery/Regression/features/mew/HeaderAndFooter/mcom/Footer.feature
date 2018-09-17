#Author: Discovery QE
#Date Created: 06/21/2017

Feature: Verify MCOM Componentization :: MW Footer UI

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario Outline: Verify legal notice with required links and tapping on the links should direct the user to the corresponding pages in domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    When I scroll the page down
    Then I verify all legal notice links in the mew Footer
    And I verify the legal notice "<pages>" for mew are rendered properly
    Examples:
      | pages                                         |
      | Request our corporate name & address by email |
      | CA Privacy Rights                             |
      | CA Transparency in Supply Chain               |
      | Customer Bill of rights                       |

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario Outline: Verify legal notice with required links and tapping on the links should direct the user to the corresponding pages in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I scroll the page down
    Then I verify all legal notice links in the mew Footer
    And I verify the legal notice "<pages>" for mew are rendered properly
    Examples:
      | pages                                         |
      | Request our corporate name & address by email |
      | CA Privacy Rights                             |
      | CA Transparency in Supply Chain               |
      | Customer Bill of rights                       |

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario Outline: Verify legal notice with required links and tapping on the links should direct the user to the corresponding pages in Iship mode
    Given I visit the mobile web site as a guest user in iship mode
    When I scroll the page down
    Then I verify all legal notice links in the mew Footer
    And I verify the legal notice "<pages>" for mew are rendered properly
    Examples:
      | pages                                         |
      | Request our corporate name & address by email |
      | CA Privacy Rights                             |
      | CA Transparency in Supply Chain               |
      | Customer Bill of rights                       |

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify macy's address beneath legal notices in domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    When I scroll the page down
    Then I should see text "Macys.com, Inc., 680 Folsom St. San Francisco, CA 94107" at the bottom of the page

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify macy's address beneath legal notices in registry
    Given I visit the mobile web site as a guest user in registry mode
    When I scroll the page down
    Then I should see text "Macys.com, Inc., 680 Folsom St. San Francisco, CA 94107" at the bottom of the page

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify macy's address beneath legal notices in iship mode
    Given I visit the mobile web site as a guest user in iship mode
    When I scroll the page down
    Then I should see text "Macys.com, Inc., 680 Folsom St., San Francisco, CA 94107" at the bottom of the page

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify SIGN UP FOR EMAILS OR TEXTS button in the footer in domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    When I scroll the page down
    Then I should see SIGN UP FOR EMAILS OR TEXTS button in footer
    When I navigate to "emails or texts" footer links using mobile website
    Then I verify SIGN UP FOR EMAILS OR TEXTS navigates to Sing in Page

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify SIGN UP FOR EMAILS OR TEXTS button in the footer in domestic mode with signed in user
    Given I visit the mobile web site as a registered user without add CC
    When I scroll the page down
    Then I should see SIGN UP FOR EMAILS OR TEXTS button in footer
    When I navigate to "emails or texts" footer links using mobile website
    Then I verify SIGN UP FOR EMAILS OR TEXTS navigates to preferences page

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify SIGN UP FOR EMAILS OR TEXTS button in the footer in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I scroll the page down
    Then I should see SIGN UP FOR EMAILS OR TEXTS button in footer
    When I navigate to "emails or texts" footer links using mobile website
    Then I verify SIGN UP FOR EMAILS OR TEXTS navigates to Sing in Page

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify SIGN UP FOR EMAILS OR TEXTS button in the footer in iship mode
    Given I visit the mobile web site as a guest user in iship mode
    When I scroll the page down
    Then I should see SIGN UP FOR EMAILS OR TEXTS button in footer
    When I navigate to "emails or texts" footer links using mobile website
    Then I should see signup for emails page opened in new tab

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario Outline:  Footer: Verify all the links navigation under footer in Domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    When I scroll the page down
    When I Navigate to "<links>" page in footer
    Then I verify the page navigation for "<links>" page
    Examples:
      | links              |
      | Sign in            |
      | Change Country     |
      | Find A Store       |
      | Customer Service   |
      | Privacy Practices  |
      | Internet based Ads |
      | Legal Notice       |
      | Desktop Version    |

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario Outline:  Footer: Verify all the links navigation under footer in Registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I scroll the page down
    When I Navigate to "<links>" page in footer
    Then I verify the page navigation for "<links>" page
    Examples:
      | links              |
      | Sign in            |
      | Change Country     |
      | Find A Store       |
      | Customer Service   |
      | Privacy Practices  |
      | Internet based Ads |
      | Legal Notice       |

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario Outline:  Footer: Verify all the links navigation under footer in iship mode
    Given I visit the mobile web site as a guest user in iship mode
    When I scroll the page down
    When I Navigate to "<links>" page in footer
    Then I verify the page navigation for "<links>" page
    Examples:
      | links              |
      | Change Country     |
      | Find A Store       |
      | Customer Service   |
      | Privacy Practices  |
      | Internet based Ads |
      | Legal Notice       |

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
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
      | Youtube     |

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario Outline: Footer: Verify all the social icon links under footer in registry home page
    Given I visit the mobile web site as a guest user in registry mode
    When I scroll the page down
    When I Navigate to "<Social_Link>" social link in footer
    Then I verify the navigation for "<Social_Link>" page
    Examples:
      | Social_Link |
      | Pinterest   |
      | Facebook    |
      | Twitter     |
      | Instagram   |
      | Youtube     |

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario Outline: Search Landing Page: Verify all the footer links navigation under footer in Domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    When I search using mobile website for "Lenox Eternal Serving Set"
    Then I should be in Search Landing page using mobile website
    When I scroll the page down
    When I Navigate to "<links>" page in footer
    Then I verify the page navigation for "<links>" page
    Examples:
      | links              |
      | Sign in            |
      | Change Country     |
      | Find A Store       |
      | Customer Service   |
      | Privacy Practices  |
      | Internet based Ads |
      | Legal Notice       |

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario Outline: Master PDP Page: Verify all the footer links navigation under footer in Domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate the global navigation menu as follows:
      | Shop                |
      | For The Home        |
      | Bed & Bath          |
      | Bedding             |
      | Bedding Collections |
    And I select a random master product using mobile website
    Then I should be redirected to PDP page using mobile website
    When I scroll the page down
    When I Navigate to "<links>" page in footer
    Then I verify the page navigation for "<links>" page
    Examples:
      | links              |
      | Sign in            |
      | Change Country     |
      | Find A Store       |
      | Customer Service   |
      | Privacy Practices  |
      | Internet based Ads |
      | Legal Notice       |

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario Outline: Member PDP Page: Verify all the footer links navigation under footer in Domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate the global navigation menu as follows:
      | Shop       |
      | Men        |
      | Activewear |
    And I select a random member product using mobile website
    Then I should be redirected to PDP page using mobile website
    When I scroll the page down
    When I Navigate to "<links>" page in footer
    Then I verify the page navigation for "<links>" page
    Examples:
      | links              |
      | Sign in            |
      | Change Country     |
      | Find A Store       |
      | Customer Service   |
      | Privacy Practices  |
      | Internet based Ads |
      | Legal Notice       |

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario Outline: My Account Page: Verify all the footer links navigation under footer in Domestic mode
    Given I visit the mobile web site as a registered user
    When I navigate the global navigation menu as follows:
      | My Account |
    When I scroll the page down
    And I Navigate to "<links>" page in footer
    Then I verify the page navigation for "<links>" page
    Examples:
      | links              |
      | Change Country     |
      | Find A Store       |
      | Customer Service   |
      | Privacy Practices  |
      | Internet based Ads |
      | Legal Notice       |

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario Outline: Browse Page: Verify all the social icon links under footer in domestic mode
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop              |
      | For The Home      |
      | More For The Home |
      | Home Decor        |
    Then I should be on mobile browse page
    When I scroll the page down
    When I Navigate to "<Social_Link>" social link in footer
    Then I verify the navigation for "<Social_Link>" page
    Examples:
      | Social_Link |
      | Pinterest   |
      | Facebook    |
      | Twitter     |
      | Instagram   |
      | Youtube     |

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario Outline: Browse Page: Verify all the links under footer in domestic mode
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop              |
      | For The Home      |
      | More For The Home |
      | Home Decor        |
    Then I should be on mobile browse page
    When I scroll the page down
    When I Navigate to "<links>" page in footer
    Then I verify the page navigation for "<links>" page
    Examples:
      | links              |
      | Sign in            |
      | Change Country     |
      | Find A Store       |
      | Customer Service   |
      | Privacy Practices  |
      | Internet based Ads |
      | Legal Notice       |

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify the text "Be the first to know about our sales & events!" on the top of the footer in domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    When I scroll the page down
    Then I should see the footer text "Be the first to know about our sales & events!" displayed on the footer

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify the text "Be the first to know about our sales & events!" on the top of the footer in domestic mode
    Given I visit the mobile web site as a guest user in iship mode
    When I scroll the page down
    Then I should see the footer text "Be the first to know about our sales & events!" displayed on the footer

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify the text "Be the first to know about our sales & events!" on the top of the footer in domestic mode
    Given I visit the mobile web site as a guest user in registry mode
    When I scroll the page down
    Then I should see the footer text "Be the first to know about our sales & events!" displayed on the footer

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
      | Shop   |
      | Women  |
      | Jeans  |
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
      | Shop   |
      | Women  |
      | Jeans  |
    Then I should be on the browse page
    When I navigate to change country page using mobile website
    And I change country to "Australia" using mobile website
    And I close the welcome mat if it's visible using mobile website
    Then I should be on the browse page
    And I should see price displayed in "Australia" currency