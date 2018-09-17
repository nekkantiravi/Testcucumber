# Author: DISCOVERY QE
# Date Created: 06/10/2015
# Date Updated: 24/11/2015

Feature: Verification of BCOM Footer functionality in DOMESTIC, ISHIP and REGISTRY modes

 ######################################### Customer Service Links in Footer ######################################

  @use_regression @artifact_navapp @project_HNFComp @release_17H @feature_hnf_comp @priority_high
  Scenario Outline: Footer - Verify Customer Service links on HOME page in DOMESTIC mode
    Given I visit the web site as a guest user
    And I verify all links for "Customer Service" in the Footer
    When I Navigate to "<CUSTOMER_SERVICE>" footer links
    Then I verify the basic attributes "<CUSTOMER_SERVICE>" page
    Examples:
      | CUSTOMER_SERVICE    |
      | CUSTOMER SERVICE    |
      | Shipping Policy     |
      | Returns & Exchanges |
      | International       |

  @use_regression @artifact_navapp @project_HNFComp @release_17H @feature_hnf_comp @priority_high
  Scenario Outline: Footer - Verify Customer Service links on Browse Page in REGISTRY mode
    Given I visit the web site as a guest user
    And I navigate to registry home page
    When I navigate to category browse page
    And I verify all links for "Customer Service" in the Footer
    When I Navigate to "<CUSTOMER_SERVICE>" footer links
    Then I verify the basic attributes "<CUSTOMER_SERVICE>" page
    Examples:
      | CUSTOMER_SERVICE    |
      | CUSTOMER SERVICE    |
      | Shipping Policy     |
      | Returns & Exchanges |
      | International       |

  ######################################### Credit Services links in Footer ######################################

  @use_regression @artifact_navapp @project_HNFComp @release_17H @feature_hnf_comp @priority_high
  Scenario Outline: Footer - Verify Credit Services links on CatSplash Page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate directly to category splash page
    Then I verify all links for "Credit Services" in the Footer
    When I Navigate to "<CREDIT_SERVICES>" footer links
    Then I verify the basic attributes "<CREDIT_SERVICES>" page
    Examples:
      | CREDIT_SERVICES     |
      | MY CREDIT CARD      |
      | Pay Bill            |
      | Cardholder Benefits |
      | Learn More & Apply  |


  @use_regression @artifact_navapp @project_HNFComp @release_17H @feature_hnf_comp @priority_high
  Scenario Outline: Footer - Verify Credit Services links on Home Page in REGISTRY mode
    Given I visit the web site as a guest user
    And I navigate to registry home page
    Then I verify all links for "Credit Services" in the Footer
    When I Navigate to "<CREDIT_SERVICES>" footer links
    Then I verify the basic attributes "<CREDIT_SERVICES>" page
    Examples:
      | CREDIT_SERVICES     |
      | MY CREDIT CARD      |
      | Pay Bill            |
      | Cardholder Benefits |
      | Learn More & Apply  |

  ######################################### My Account links in Footer ######################################

  @use_regression @artifact_navapp @project_HNFComp @release_17H @feature_hnf_comp @priority_high
  Scenario Outline: Footer - Verify My Account links on Category Browse Page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to category browse page
    Then I verify all links for "My Account" in the Footer
    When I navigate to "<MY_ACCOUNT>" footer links
    Then I verify the basic attributes "<MY_ACCOUNT>" page
    Examples:
      | MY_ACCOUNT   |
      | MY ACCOUNT   |
      | Order Status |
      | My Loyallist |
      | My Profile   |

  @use_regression @artifact_navapp @project_HNFComp @release_17H @feature_hnf_comp @priority_high
  Scenario Outline: Footer - Verify My Account links on Category Browse Page in REGISTRY mode
    Given I visit the web site as a guest user
    And I navigate to registry home page
    When I navigate to category browse page
    Then I verify all links for "My Account" in the Footer
    When I navigate to "<MY_ACCOUNT>" footer links
    Then I verify the basic attributes "<MY_ACCOUNT>" page
    Examples:
      | MY_ACCOUNT   |
      | MY ACCOUNT   |
      | Order Status |
      | My Loyallist |
      | My Profile   |

  ######################################### COMPANY links in Footer ######################################

  @use_regression @artifact_navapp @project_HNFComp @release_17H @feature_hnf_comp @priority_high
  Scenario Outline: Footer - Verify Company links on Category Splash Page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate directly to category splash page
    Then I verify all links for "Company" in the Footer
    When I Navigate to "<COMPANY_LINKS>" footer links
    Then I verify the basic attributes "<COMPANY_LINKS>" page
    Examples:
      | COMPANY_LINKS |
      | COMPANY       |
      | About Us      |
      | b.cause       |
      | Careers       |

  @use_regression @artifact_navapp @project_HNFComp @release_17H @feature_hnf_comp @priority_high
  Scenario Outline: Footer - Verify Company links on Category Browse Page in REGISTRY mode
    Given I visit the web site as a guest user
    And I navigate to registry home page
    When I navigate to category browse page
    Then I verify all links for "Company" in the Footer
    When I Navigate to "<COMPANY_LINKS>" footer links
    Then I verify the basic attributes "<COMPANY_LINKS>" page
    Examples:
      | COMPANY_LINKS |
      | COMPANY       |
      | About Us      |
      | b.cause       |
      | Careers       |

  ######################################### Ways to Shop links in Footer ######################################

  @use_regression @artifact_navapp @project_HNFComp @release_17H @feature_hnf_comp @priority_high
  Scenario Outline: Footer - Verify Ways to Shop links on Category Browse Page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to category browse page
    Then I verify all links for "Ways to Shop" in the Footer
    When I Navigate to "<WAYS_TO_SHOP>" footer links
    Then I verify the basic attributes "<WAYS_TO_SHOP>" page
    Examples:
      | WAYS_TO_SHOP     |
      | WAYS TO SHOP     |
      | Online & Mobile  |
      | Stores           |
      | Pick Up In Store |

  @use_regression @artifact_navapp @project_HNFComp @release_17H @feature_hnf_comp @priority_high
  Scenario Outline: Footer - Verify Ways to Shop links on Category Browse Page in REGISTRY mode
    Given I visit the web site as a guest user
    And I navigate to registry home page
    When I navigate to category browse page
    Then I verify all links for "Ways to Shop" in the Footer
    When I Navigate to "<WAYS_TO_SHOP>" footer links
    Then I verify the basic attributes "<WAYS_TO_SHOP>" page
    Examples:
      | WAYS_TO_SHOP     |
      | WAYS TO SHOP     |
      | Online & Mobile  |
      | Stores           |
      | Pick Up In Store |

  ######################################### Social Icons links in Footer ######################################

  @use_regression @artifact_navapp @project_HNFComp @release_17H @feature_hnf_comp @priority_high
  Scenario Outline: Footer - Verify Social Icon links in Home page in DOMESTIC mode
    Given I visit the web site as a guest user
    Then I verify all links for "Social Media" in the Footer
    When I Navigate to "<Social_Media>" media footer link
    And I verify the basic attributes "<Social_Media>" page
    Examples:
      | Social_Media |
      | Mobile       |
      | Instagram    |
      | Snapchat     |
      | Pinterest    |
      | Facebook     |
      | Twitter      |
      | Tumblr       |

  @use_regression @artifact_navapp @project_HNFComp @release_17H @feature_hnf_comp @priority_high
  Scenario Outline: Footer - Verify Social Icon links in browse page in REGISTRY mode
    Given I visit the web site as a guest user
    And I navigate to registry home page
    When I navigate to category browse page
    Then I verify all links for "Social Media" in the Footer
    And I Navigate to "<Social_Media>" media footer link
    And I verify the basic attributes "<Social_Media>" page
    Examples:
      | Social_Media |
      | Mobile       |
      | Instagram    |
      | Snapchat     |
      | Pinterest    |
      | Facebook     |
      | Twitter      |
      | Tumblr       |

  @use_regression @artifact_navapp @project_HNFComp @release_17H @feature_hnf_comp @priority_high
  Scenario Outline: Footer - Verify Social Icon links in ISHIP page
    Given I visit the web site as a guest user in "iship" mode
    And I close the welcome mat if it's visible
    Then I verify all links for "Social Media" in the Footer
    And I Navigate to "<Social_Media>" media footer link
    And I verify the basic attributes "<Social_Media>" page
    Examples:
      | Social_Media |
      | Mobile       |
      | Instagram    |
      | Snapchat     |
      | Pinterest    |
      | Facebook     |
      | Twitter      |
      | Tumblr       |

  #################################################### Legal notice links in Footer ##########################################

  @use_regression @artifact_navapp @project_HNFComp @release_17H @feature_hnf_comp @priority_high
  Scenario: Footer - Verify Legal notice links in Home page in DOMESTIC mode
    Given I visit the web site as a guest user
    Then I verify all legal notice links in the Footer
    And I verify the legal notice pages are rendered properly in "site" mode
      | Terms of use                     |
      | Privacy                          |
      | CA Privacy Rights                |
      | Customers Bill of Rights         |
      | CA Transparency Supply Chain Act |

  @use_regression @artifact_navapp @project_HNFComp @release_17H @feature_hnf_comp @priority_high
  Scenario: Footer - Verify Legal notice links in Home page in REGISTRY mode
    Given I visit the web site as a guest user
    And I navigate to registry home page
    Then I verify all legal notice links in the Footer
    And I verify the legal notice pages are rendered properly in "registry" mode
      | Terms of use                     |
      | Privacy                          |
      | CA Privacy Rights                |
      | Customers Bill of Rights         |
      | CA Transparency Supply Chain Act |

  @use_regression @artifact_navapp @project_HNFComp @release_17H @feature_hnf_comp @priority_high
  Scenario: Footer - Verify Legal notice links in Home page in ISHIP mode
    Given I visit the web site as a guest user in "iship" mode
    And I close the welcome mat if it's visible
    Then I verify all legal notice links in the Footer
    And I verify the legal notice pages are rendered properly in "iship" mode
      | Terms of use                     |
      | Privacy                          |
      | CA Privacy Rights                |
      | Customers Bill of Rights         |
      | CA Transparency Supply Chain Act |

  #################################################### Easy Web Browser link in Footer ##########################################

  @use_regression @artifact_navapp @project_HNFComp @release_17H @feature_hnf_comp @priority_high
  Scenario: Footer - Verify the Easy web Browser link in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to category browse page
    Then I verify the Easy Web browser link is rendered properly

  @use_regression @artifact_navapp @project_HNFComp @release_17H @feature_hnf_comp @priority_high
  Scenario: Footer - Verify the Easy web Browser link in REGISTRY mode
    Given I visit the web site as a guest user
    And I navigate to registry home page
    When I navigate to category browse page
    Then I verify the Easy Web browser link is rendered properly

  @use_regression @artifact_navapp @project_HNFComp @release_17H @feature_hnf_comp @priority_high
  Scenario: Footer - Verify the Easy web Browser link in ISHIP mode
    Given I visit the web site as a guest user in "iship" mode
    And I close the welcome mat if it's visible
    When I navigate to category browse page
    Then I verify the Easy Web browser link is rendered properly

  #################################################### Signup for Emails in Footer ###############################################


  @use_regression @artifact_navapp @project_HNFComp @release_17H @feature_hnf_comp @priority_high
  Scenario: Footer - Verify the Signup for emails
    Given I visit the web site as a guest user
    Then I verify signup for emails functionality and navigate back to homepage

  @use_regression @artifact_navapp @project_HNFComp @release_17H @feature_hnf_comp @priority_high
  Scenario: Footer - Verify the Signup for emails in REGISTRY mode
    Given I visit the web site as a guest user
    And I navigate to registry home page
    Then I verify signup for emails functionality and navigate back to homepage

  @use_regression @artifact_navapp @project_HNFComp @release_17H @feature_hnf_comp @priority_high
  Scenario: Footer - Verify the Signup for emails in ISHIP mode
    Given I visit the web site as a guest user in "iship" mode
    And I close the welcome mat if it's visible
    Then I verify signup for emails functionality and navigate back to homepage

  @artifact_navapp @domain_discovery @mode_domestic  @please_automate @wip @priority_medium @test
  Scenario: Footer - Verify 'Like No Other Store in the World' image appearance in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate directly to category splash page
    Then I verify "Like No Other Store in the World" image displayed below footer

  @use_regression @artifact_navapp @project_HNFComp @release_17H @feature_hnf_comp @priority_high
  Scenario: Footer - Verify 'Like No Other Store in the World' image appearance in REGISTRY mode
    Given I visit the web site as a guest user
    And I navigate to registry home page
    When I search for "plates"
    Then I verify "Like No Other Store in the World" image displayed below footer

  @use_regression @artifact_navapp @project_HNFComp @release_17H @feature_hnf_comp @priority_high
  Scenario: Footer - Verify 'Like No Other Store in the World' image appearance in ISHIP mode
    Given I visit the web site as a guest user in "iship" mode
    And I close the welcome mat if it's visible
    When I navigate directly to category splash page
    Then I verify "Like No Other Store in the World" image displayed below footer

  @use_regression @artifact_navapp @project_HNFComp @release_17H @feature_hnf_comp @priority_high
  Scenario: Footer - Verify 'Customer Service 1-800-777-0000' image appearance in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate directly to category splash page
    Then I verify "Customer Service 1-800-777-0000" image displayed below footer

  @use_regression @artifact_navapp @project_HNFComp @release_17H @feature_hnf_comp @priority_high
  Scenario: Footer - Verify 'Customer Service 1-800-777-0000' image appearance in REGISTRY mode
    Given I visit the web site as a guest user
    When I navigate directly to category splash page
    Then I verify "Customer Service 1-800-777-0000" image displayed below footer

  @use_regression @artifact_navapp @project_HNFComp @release_17H @feature_hnf_comp @priority_high
  Scenario: Footer - Verify 'Customer Service 1-800-777-0000' image appearance in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate directly to category splash page
    Then I verify "Customer Service 1-800-777-0000" image displayed below footer