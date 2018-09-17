#Author: SPO QE
#Date Created: 11/08/16

Feature: Verify Header/Footer functionality on Home Page in DOMESTIC, ISHIP and RESGITRY mode

  @use_regression @artifact_navapp @domain_continuity @project_SPO @priority_high @feature_headerFooter
  Scenario Outline: Verify the bloomingdales logo displaying in homepage
    Given I visit the web site as a guest user in "<mode_type>" mode
    Then I close popUp on home page
    Then I verify the bloomingdales logo available in "<mode_type>" homepage
    Examples:
      |mode_type|
      |domestic |
      |registry |
      |iship    |

  @use_regression @artifact_navapp @domain_continuity @project_SPO @priority_high @feature_headerFooter
  Scenario Outline: Verify the clicking on bloomingdales logo navigates to homepage in all three modes
    Given I visit the web site as a guest user in "<mode_type>" mode
    When I navigate to browse page in "<mode_type>" mode
    And I click on bloomingdales logo in "<mode_type>" mode
    Then I verify navigated to "<mode_type>" homepage
    Examples:
      |mode_type|
      |domestic |
      |registry |
      |iship    |

  @use_regression @artifact_navapp @domain_continuity @project_SPO @priority_high @feature_headerFooter
  Scenario Outline: Verify the info and exclusion link opens a new window
    Given I visit the web site as a guest user in "<mode_type>" mode
    Then I close popUp on home page
    When I click on INFO and EXCLUSIONS link on top of search field
    Then I verify the new window and close it
    Examples:
      |mode_type|
      |domestic |
      |registry |

  @use_regression @artifact_navapp @domain_continuity @project_SPO @priority_high @feature_headerFooter
  Scenario Outline: Verify the search field and auto suggestion lists in Domestic & iship modes
    Given I visit the web site as a guest user in "<mode_type>" mode
    Then I close popUp on home page
    Then I verify search box is displayed
    When I entered "<Search_char>" keyword in search field
    Then I should see autocomplete suggestions list is populated
    Examples:
      |mode_type|Search_char|
      |domestic |jea        |
      |iship    |caps       |

  @use_regression @artifact_navapp @domain_continuity @project_SPO @priority_high @feature_headerFooter
  Scenario Outline: Verify the search field and auto suggestion lists in Registry mode
    Given I visit the web site as a guest user in "<mode_type>" mode
    Then I close popUp on home page
    Then I verify search box is displayed
    When I entered "<Search_char>" keyword in search field
    Then I should not see autocomplete suggestions
    Examples:
      |mode_type|Search_char|
      |registry |pla        |

  @use_regression @artifact_navapp @domain_continuity @project_SPO @priority_high @feature_headerFooter
  Scenario: Verify the FOB's displaying in Domestic mode
    Given I visit the web site as a guest user in "domestic" mode
    Then I close popUp on home page
    Then I verify the Fob's displayed
      |Designers            |
      |100%                 |
      |What's new           |
      |Women                |
      |Shoes                |
      |Handbags             |
      |Jewelry & Accessories|
      |Men                  |
      |Kids                 |
      |Home                 |
      |The Registry         |
      |Sale                 |

  @use_regression @artifact_navapp @domain_continuity @project_SPO @priority_high @feature_headerFooter
  Scenario: Verify the FOB's displaying in Registry mode
    Given I visit the web site as a guest user in "registry" mode
    Then I close popUp on home page
    Then I verify the Fob's displayed
      |Getting started      |
      |Brands               |
      |Dining & Entertaining|
      |Kitchen              |
      |Home Decor           |
      |Bed & Bath           |
      |Luggage              |
      |Home Care & Tech     |

  @use_regression @artifact_navapp @domain_continuity @project_SPO @priority_high @feature_headerFooter
  Scenario: Verify the FOB's displaying in Iship mode
    Given I visit the web site as a guest user in "iship" mode
    Then I close popUp on home page
    Then I verify the Fob's displayed
      |Designers            |
      |100%                 |
      |What's new           |
      |Women                |
      |Shoes                |
      |Handbags             |
      |Jewelry & Accessories|
      |Men                  |
      |Kids                 |
      |Home                 |
      |The Registry         |
      |Sale                 |

  @use_regression @artifact_navapp @domain_continuity @project_SPO @priority_high @feature_headerFooter
  Scenario Outline: Verify the Flyouts displaying in Domestic mode
    Given I visit the web site as a guest user in "domestic" mode
    Then I close popUp on home page
    When I hover over "<categories>" fobs
    Then I verify that flyout menu is displayed
    Examples:
      |categories|
      |designers |
      |100% |
      |what's new|
      |Women|
      |Shoes|
      |Handbags|
      |Jewelry & Accessories|
      |Beauty|
      |Men   |
      |Kids  |
      |Home  |
      |Gifts |
      |The Registry|
      |Sale|
      |Outlet|

  @use_regression @artifact_navapp @domain_continuity @project_SPO @priority_high @feature_headerFooter
  Scenario Outline: Verify the Flyouts displaying in Registry mode
    Given I visit the web site as a guest user in "registry" mode
    Then I close popUp on home page
    When I hover over "<categories>" fobs
    Then I verify that flyout menu is displayed
    Examples:
      |categories|
      |Getting Started |
      |Brands |
      |Dining & Entertaining|
      |Kitchen|
      |Home Decor|
      |Bed & Bath|
      |Luggage|
      |Home Care & Tech|

  @use_regression @artifact_navapp @domain_continuity @project_SPO @priority_high @feature_headerFooter
  Scenario Outline: Verify the Flyouts displaying in Iship mode
    Given I visit the web site as a guest user in "iship" mode
    Then I close popUp on home page
    When I hover over "<categories>" fobs
    Then I verify that flyout menu is displayed
    Examples:
      |categories|
      |designers |
      |100% |
      |what's new|
      |Women|
      |Shoes|
      |Handbags|
      |Jewelry & Accessories|
      |Men   |
      |Kids  |
      |Home  |
      |Sale|

  @use_regression @artifact_navapp @domain_continuity @project_SPO @priority_high @feature_headerFooter
  Scenario Outline: Verify the display of flyouts
    Given I visit the web site as a guest user in "<mode_type>" mode
    Then I close popUp on home page
    When I mouse over random category from top navigation
    Then I verify that flyout menu is displayed
    Examples:
      |mode_type|
      |domestic |
      |registry |
      |iship    |

  @use_regression @artifact_navapp @domain_continuity @project_SPO @priority_high @feature_headerFooter
  Scenario Outline: Verify the display of Seasonal Ad
    Given I visit the web site as a guest user in "<mode_type>" mode
    Then I close popUp on home page
    When I click on the seasonal action wrapper
    Then I verify the seasonal ad in topnav section
    Examples:
      |mode_type|
      |domestic |
      |registry    |

  @use_regression @artifact_navapp @domain_continuity @project_SPO @priority_high @feature_headerFooter
  Scenario Outline: Verify the topnav section in Domestic mode
    Given I visit the web site as a guest user in "domestic" mode
    Then I close popUp on home page
    Then I should see "<topnav's>" in topnav section
    Examples:
      |topnav's      |
      |Stores & Events|
      |Country change |
      |My account     |
      |Wishlist       |
      |Brown bag      |

  @use_regression @artifact_navapp @domain_continuity @project_SPO @priority_high @feature_headerFooter
  Scenario Outline: Verify the topnav section in Registry mode
    Given I visit the web site as a guest user in "registry" mode
    Then I close popUp on home page
    Then I should see "<topnav's>" in topnav section
    Examples:
      |topnav's       |
      |Stores & Events|
      |My account     |
      |Brown bag      |

  @use_regression @artifact_navapp @domain_continuity @project_SPO @priority_high @feature_headerFooter
  Scenario Outline: Verify the topnav section in Iship mode
    Given I visit the web site as a guest user in "iship" mode
    Then I close popUp on home page
    Then I should see "<topnav's>" in topnav section
    Examples:
      |topnav's       |
      |Country change |
      |Brown bag      |

  @use_regression @artifact_navapp @domain_continuity @project_SPO @priority_high @feature_headerFooter
  Scenario Outline: Verify the topnav link navigation in Domestic mode
    Given I visit the web site as a guest user in "domestic" mode
    Then I close popUp on home page
    When I navigate to "<topnav's>" page from topnav section
    Then I should see the page navigates to "<topnav's>" page
    Examples:
      |topnav's       |
      |Stores & Events|
      |Country change |
      |My account     |
      |Wishlist       |
      |Brown bag      |

  @use_regression @artifact_navapp @domain_continuity @project_SPO @priority_high @feature_headerFooter
  Scenario Outline: Verify the topnav link navigation in Registry mode
    Given I visit the web site as a guest user in "registry" mode
    Then I close popUp on home page
    When I navigate to "<topnav's>" page from topnav section
    Then I should see the page navigates to "<topnav's>" page
    Examples:
      |topnav's       |
      |My account     |
      |Brown bag      |

  @use_regression @artifact_navapp @domain_continuity @project_SPO @priority_high @feature_headerFooter
  Scenario Outline: Verify the topnav link navigation in Iship mode
    Given I visit the web site as a guest user in "iship" mode
    Then I close popUp on home page
    When I navigate to "<topnav's>" page from topnav section
    Then I should see the page navigates to "<topnav's>" page
    Examples:
      |topnav's       |
      |Country change |
      |Brown bag      |


#----------------------------------Footer Section----------------------------------

  @use_regression @artifact_navapp @domain_continuity @project_SPO @priority_high @feature_headerFooter
  Scenario Outline: Verify the customer service footer links navigation in Domestic mode
    Given I visit the web site as a guest user in "domestic" mode
    Then I close popUp on home page
    When I Navigate to "<links>" footer links
    Then I verify navigated to "<links>" page
    Examples:
      |links              |
      |CUSTOMER SERVICE|
      |Shipping Policy    |
      |Returns & Exchanges|
      |International      |

  @use_regression @artifact_navapp @domain_continuity @project_SPO @priority_high @feature_headerFooter
  Scenario Outline: Verify the customer service footer links navigation in registry mode
    Given I visit the web site as a guest user in "registry" mode
    Then I close popUp on home page
    When I Navigate to "<links>" footer links
    Then I verify navigated to "<links>" page
    Examples:
      |links              |
      |CUSTOMER SERVICE|
      |Shipping Policy    |
      |Returns & Exchanges|
      |International      |

  @use_regression @artifact_navapp @domain_continuity @project_SPO @priority_high @feature_headerFooter
  Scenario Outline: Verify the customer service footer links navigation in iship mode
    Given I visit the web site as a guest user in "iship" mode
    Then I close popUp on home page
    When I Navigate to "<links>" footer links
    Then I verify navigated to iship "<links>" page
    Examples:
      |links              |
      |CUSTOMER SERVICE|
      |FAQs    |
      |Visitor Services|
      |Domestic      |


  @use_regression @artifact_navapp @domain_continuity @project_SPO @priority_high @feature_headerFooter
  Scenario Outline: Verify the My Account footer links navigation in Domestic mode
    Given I visit the web site as a guest user in "domestic" mode
    Then I close popUp on home page
    When I Navigate to "<links>" footer links
    Then I verify navigated to "<links>" page
    Examples:
      |links         |
      |MY ACCOUNT|
      |Order Status  |
      |My Loyallist  |
      |My Profile    |

  @use_regression @artifact_navapp @domain_continuity @project_SPO @priority_high @feature_headerFooter
  Scenario Outline: Verify the My Account footer links navigation in registry mode
    Given I visit the web site as a guest user in "registry" mode
    Then I close popUp on home page
    When I Navigate to "<links>" footer links
    Then I verify navigated to "<links>" page
    Examples:
      |links         |
      |MY ACCOUNT|
      |Order Status  |
      |My Loyallist  |
      |My Profile    |

  @use_regression @artifact_navapp @domain_continuity @project_SPO @priority_high @feature_headerFooter
  Scenario Outline: Verify the Your Order footer links navigation in iship mode
    Given I visit the web site as a guest user in "iship" mode
    Then I close popUp on home page
    When I Navigate to "<links>" footer links
    Then I verify navigated to iship "<links>" page
    Examples:
      |links               |
      |YOUR ORDER      |
      |Order Status           |
      |Shipping Policy |
      |Returns & Exchanges|


  @use_regression @artifact_navapp @domain_continuity @project_SPO @priority_high @feature_headerFooter
  Scenario Outline: Verify the My credit card footer links navigation in Domestic mode
    Given I visit the web site as a guest user in "domestic" mode
    Then I close popUp on home page
    When I Navigate to "<links>" footer links
    Then I verify navigated to "<links>" page
    Examples:
      |links               |
      |MY CREDIT CARD      |
      |Pay Bill            |
      |Cardholder Benefits |
      |Apply & Learn More|

  @use_regression @artifact_navapp @domain_continuity @project_SPO @priority_high @feature_headerFooter
  Scenario Outline: Verify the My credit card footer links navigation in registry mode
    Given I visit the web site as a guest user in "registry" mode
    Then I close popUp on home page
    When I Navigate to "<links>" footer links
    Then I verify navigated to "<links>" page
    Examples:
      |links               |
      |MY CREDIT CARD      |
      |Pay Bill            |
      |Cardholder Benefits |
      |Apply & Learn More|

  @use_regression @artifact_navapp @domain_continuity @project_SPO @priority_high @feature_headerFooter
  Scenario Outline: Verify the Company footer links navigation in Domestic mode
    Given I visit the web site as a guest user in "domestic" mode
    Then I close popUp on home page
    When I Navigate to "<links>" footer links
    Then I verify navigated to "<links>" page
    Examples:
      |links    |
      |COMPANY  |
      |About Us |
      |b.cause  |
      |Careers  |

  @use_regression @artifact_navapp @domain_continuity @project_SPO @priority_high @feature_headerFooter
  Scenario Outline: Verify the Company footer links navigation in Registry mode
    Given I visit the web site as a guest user in "registry" mode
    Then I close popUp on home page
    When I Navigate to "<links>" footer links
    Then I verify navigated to "<links>" page
    Examples:
      |links    |
      |COMPANY  |
      |About Us |
      |b.cause  |
      |Careers  |


  @use_regression @artifact_navapp @domain_continuity @project_SPO @priority_high @feature_headerFooter
  Scenario Outline: Verify the Company footer links navigation in Iship mode
    Given I visit the web site as a guest user in "iship" mode
    Then I close popUp on home page
    When I Navigate to "<links>" footer links
    Then I verify navigated to "<links>" page
    Examples:
      |links    |
      |COMPANY  |
      |About Us |
      |b.cause  |
      |Careers  |

  @use_regression @artifact_navapp @domain_continuity @project_SPO @priority_high @feature_headerFooter
  Scenario Outline: Verify the WAY TO SHOP footer links navigation in Domestic mode
    Given I visit the web site as a guest user in "domestic" mode
    Then I close popUp on home page
    When I Navigate to "<links>" footer links
    Then I verify navigated to "<links>" page
    Examples:
      |links           |
      |WAYS TO SHOP    |
      |Online & Mobile |
      |Stores          |
      |Outlets         |

  @use_regression @artifact_navapp @domain_continuity @project_SPO @priority_high @feature_headerFooter
  Scenario Outline: Verify the WAY TO SHOP footer links navigation in Registry mode
    Given I visit the web site as a guest user in "registry" mode
    Then I close popUp on home page
    When I Navigate to "<links>" footer links
    Then I verify navigated to "<links>" page
    Examples:
      |links           |
      |WAYS TO SHOP    |
      |Online & Mobile |
      |Stores          |
      |Outlets         |

  @use_regression @artifact_navapp @domain_continuity @project_SPO @priority_high @feature_headerFooter
  Scenario Outline: Verify the WAY TO SHOP footer links navigation in iship mode
    Given I visit the web site as a guest user in "iship" mode
    Then I close popUp on home page
    When I Navigate to "<links>" footer links
    Then I verify navigated to "<links>" page
    Examples:
      |links           |
      |WAYS TO SHOP    |
      |Online & Mobile |
      |Stores          |
      |Outlets         |

  @use_regression @artifact_navapp @domain_continuity @project_SPO @priority_high @feature_headerFooter
  Scenario Outline: Verify the Ad banner in footer section in Domestic mode
    Given I visit the web site as a guest user in "<mode_type>" mode
    Then I close popUp on home page
    When I select footer Ad banner
    Examples:
      |mode_type|
      |domestic |

  @use_regression @artifact_navapp @domain_continuity @project_SPO @priority_high @feature_headerFooter
  Scenario Outline: Verify the social footer links navigation in Domestic mode
    Given I visit the web site as a guest user in "domestic" mode
    Then I close popUp on home page
    When I navigate and verify "<links>" social footer links & page
    Examples:
      |links    |
      |facebook |
      |twitter  |
      |pinterest|
      |instagram|
      |mobile   |
      |sign-up  |


  @use_regression @artifact_navapp @domain_continuity @project_SPO @priority_high @feature_headerFooter
  Scenario Outline: Verify the social footer links navigation in Registry mode
    Given I visit the web site as a guest user in "registry" mode
    Then I close popUp on home page
    When I navigate and verify "<links>" social footer links & page
    Examples:
      |links    |
      |facebook |
      |twitter  |
      |pinterest|
      |instagram|
      |mobile   |
      |sign-up  |

  @use_regression @artifact_navapp @domain_continuity @project_SPO @priority_high @feature_headerFooter
  Scenario Outline: Verify the social footer links navigation in Iship mode
    Given I visit the web site as a guest user in "iship" mode
    Then I close popUp on home page
    When I navigate and verify "<links>" social footer links & page
    Examples:
      |links    |
      |facebook |
      |twitter  |
      |pinterest|
      |instagram|
      |mobile   |

  @use_regression @artifact_navapp @domain_continuity @project_SPO @priority_high @feature_headerFooter
  Scenario Outline: Verify the bottom footer links navigation in Domestic mode
    Given I visit the web site as a guest user in "domestic" mode
    Then I close popUp on home page
    When I Navigate to "<links>" footer links
    Then I verify navigated to "<links>" page
    Examples:
      |links|
      |Terms of Use|
      |Privacy|
      |CA Privacy Rights|
      |CA Transparency in Supply Chains Act|
      |Customers' Bill of Rights           |
      |Product Recall|
      |Visually Impaired Customers|
      |Essential Accessibility|

  @use_regression @artifact_navapp @domain_continuity @project_SPO @priority_high @feature_headerFooter
  Scenario Outline: Verify the bottom footer links navigation in Registry mode
    Given I visit the web site as a guest user in "registry" mode
    Then I close popUp on home page
    When I Navigate to "<links>" footer links
    Then I verify navigated to "<links>" page
    Examples:
      |links|
      |Terms of Use|
      |Privacy|
      |CA Privacy Rights|
      |CA Transparency in Supply Chains Act|
      |Customers' Bill of Rights           |
      |Product Recall|
      |Visually Impaired Customers|
      |Essential Accessibility|


  @use_regression @artifact_navapp @domain_continuity @project_SPO @priority_high @feature_headerFooter
  Scenario Outline: Verify the bottom footer links navigation in Iship mode
    Given I visit the web site as a guest user in "iship" mode
    Then I close popUp on home page
    When I Navigate to "<links>" footer links
    Then I verify navigated to "<links>" page
    Examples:
      |links|
      |Terms of Use|
      |Privacy|
      |CA Privacy Rights|
      |CA Transparency in Supply Chains Act|
      |Customers' Bill of Rights           |
      |Product Recall|
      |Visually Impaired Customers|
      |Essential Accessibility|



