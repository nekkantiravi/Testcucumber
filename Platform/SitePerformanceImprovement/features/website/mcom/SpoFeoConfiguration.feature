Feature: As a SA I want to verify the existing functionalities on FEO pages


  #------------------------------Optimized Home Page scenarios-----------------------------
  @domain_Other @artifact_navapp @project_sitePerformance @feature_Feo_HomePage
    Scenario: Verify the search field and auto suggestions in Optimized Home page
    Given I visit the web site as a guest user
    Then I verify that page is served from "FEO" tag
    And I verify search box is displayed
    When I enter "jea" keyword in search field
    Then I should see autocomplete suggestions list is populated

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_HomePage
    Scenario: Verify the zero nav Header links for Optimized Home page
    Given I visit the web site as a guest user
    Then I verify that page is served from "FEO" tag
    And I verify the TopNav elements are present in the UI for "guest" user in "Domestic" mode
    And I click on link HF links and browse back to verify page got optimized again
      |SIGN IN|
      |MY ACCOUNT       |
      |STORES           |
      |DEALS            |
      |LISTS            |
      |GIFTS            |
      |WEDDING REGISTRY |

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_HomePage
    Scenario: Verify My Accounts drop down list on Optimized Home page
    Given I visit the web site as a guest user
    Then I verify that page is served from "FEO" tag
    Then I verify that new dropdown link names are displayed
      | Macy's Credit Card |
      | Order History      |
      | Profile            |
      | Wallet             |
      | Star Rewards       |
      | Plenti             |
      | Lists              |

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_HomePage
  Scenario: Verify the Quick bag functionality on Optimized Home page
    Given I visit the web site as a guest user
    Then I verify that page is served from "FEO" tag
    When I hover over the quick bag
    Then I should see empty quickbag message

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_HomePage
    Scenario: Verify the Footer links under customer services on Optimized Home page
    Given I visit the web site as a guest user
    Then I verify that page is served from "FEO" tag
    And I click on link HF links and browse back to verify page got optimized again
      |CUSTOMER SERVICE|
      |order tracking|
      |shipping & delivery|
      |returns            |
      |contact us         |
      |para ayuda         |
      |change country     |

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_HomePage
  Scenario: Verify the Footer links under macy's credit card on Optimized Home page
    Given I visit the web site as a guest user
    Then I verify that page is served from "FEO" tag
    And I click on link HF links and browse back to verify page got optimized again
      |MACY'S CREDIT CARD |
      |pay bill           |
      |cardholder benefits|
      |learn more & apply |

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_HomePage
  Scenario: Verify the Footer links under our stores on Optimized Home page
    Given I visit the web site as a guest user
    Then I verify that page is served from "FEO" tag
    And I click on link HF links and browse back to verify page got optimized again
      |OUR STORES         |
      |locations & hours  |
      |store events       |
      |catalogs           |
      |tell us what you think|
      |my stylist personal shopping|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_HomePage
    Scenario: Verify the Footer links under macy's Inc on Optimized Home page
    Given I visit the web site as a guest user
    Then I verify that page is served from "FEO" tag
    And I click on link HF links and browse back to verify page got optimized again
      |MACY'S INC.                 |
      |macysJOBS                   |
      |press room                  |
      |investors                   |

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_HomePage
  Scenario: Verify the Footer links under stay connected on Optimized Home page
    Given I visit the web site as a guest user
    Then I verify that page is served from "FEO" tag
    And I click on link HF links and browse back to verify page got optimized again
      |STAY CONNECTED              |
      |mobile app                  |
      |sign up for email           |
      |See how we're improving     |
      |WHAT'S HAPPENING AT MACY'S  |

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_HomePage
  Scenario: Verify the Footer social links on Optimized Home Page
    Given I visit the web site as a guest user
    Then I verify that page is served from "FEO" tag
    And I click on footer social media links and browse back to verify page got optimized again
      |facebook|
      |twitter |
      |pinterest|
      |youtube  |
      |macys m-blog|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_HomePage
  Scenario: Verify Footer ad links on Optimized Home page
    Given I visit the web site as a guest user
    Then I verify that page is served from "FEO" tag
    And I click on footer ad link and browse back to verify page got optimized again
      |Macy's Culinary Council|
      |green living|
      |the magic of giving|
      |macys video channel|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_HomePage
  Scenario: Verify customer's first name displayed on Optimized Home page
    Given I visit the web site as a registered user
    Then I verify first name is displayed on the home page

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_HomePage
  Scenario: Verify the FOBs returning 200 on Optimized home page
    Given I visit the web site as a guest user
    Then I verify that page is served from "FEO" tag
    And I verify that flyout menu is displayed for FOBs
      |WOMEN |
      |MEN|
      |HOME|
      |BED & BATH|
      |SHOES|
      |HANDBAGS|
      |BEAUTY|
      |KIDS  |
      |JUNIORS |
      |JEWELRY|
      |WATCHES|
      |ACTIVE|
      |BRANDS|

    #----------------------------------------- Optimized Browse pages scenarios --------------------------------------

  @domain_Other @artifact_navapp @project_sitePerformance @feature_Feo_Browse_FirstPhase
  Scenario Outline: Verify the search field and auto suggestions in FEO Browse pages
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    And I verify search box is displayed
    When I enter "jea" keyword in search field
    Then I should see autocomplete suggestions list is populated
    Examples:
      |subcategory_name||category_name|
      |Dresses||WOMEN|
    #  |MICHAEL Michael Kors||WOMEN|
    #  |Wedges||SHOES|
    #  |Jackets||WOMEN|
    #  |Jeans||WOMEN|
    #  |Pants||WOMEN|
    #  |Maternity||WOMEN|
    #  |25-70% Off Clearance||WOMEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_FirstPhase
  Scenario Outline: Verify the zero nav Header links for Feo Browse pages
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    And I verify the TopNav elements are present in the UI for "guest" user in "Domestic" mode
    And I click on link HF links and browse back to verify page got optimized again
      |SIGN IN|
      |MY ACCOUNT       |
      |STORES           |
      |DEALS            |
      |LISTS            |
      |GIFTS            |
      |WEDDING REGISTRY |
    Examples:
      |subcategory_name||category_name|
    #  |Dresses||WOMEN|
    #  |MICHAEL Michael Kors||WOMEN|
    #  |Wedges||SHOES|
      |Jackets||WOMEN|
    #  |Jeans||WOMEN|
    #  |Pants||WOMEN|
    #  |Maternity||WOMEN|
    #  |25-70% Off Clearance||WOMEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_FirstPhase
  Scenario Outline: Verify My Accounts drop down list on Optimized browse page
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    Then I verify that new dropdown link names are displayed
      | Macy's Credit Card |
      | Order History      |
      | Profile            |
      | Wallet             |
      | Star Rewards       |
      | Plenti             |
      | Lists              |
    Examples:
      |subcategory_name   |category_name     |
     # |Dresses|WOMEN|
      |MICHAEL Michael Kors|WOMEN|
     # |Wedges|SHOES|
     # |Jackets|WOMEN|
     # |Jeans|WOMEN|
     # |Pants|WOMEN|
     # |Maternity|WOMEN|
     # |25-70% Off Clearance|WOMEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_FirstPhase
  Scenario Outline: Verify the Quick bag functionality on FEO Browse pages
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    When I hover over the quick bag
    Then I should see empty quickbag message
    Examples:
      |subcategory_name   |category_name     |
     # |Dresses|WOMEN|
     # |MICHAEL Michael Kors|WOMEN|
      |Wedges|SHOES|
     # |Jackets|WOMEN|
     # |Jeans|WOMEN|
     # |Pants|WOMEN|
     # |Maternity|WOMEN|
     # |25-70% Off Clearance|WOMEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_FirstPhase
  Scenario Outline: Verify the Footer links under customer services in FEO Browse pages for First phase pages
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    And I click on link HF links and browse back to verify page got optimized again
      |CUSTOMER SERVICE|
      |order tracking|
      |shipping & delivery|
      |returns            |
      |contact us         |
      |para ayuda         |
      |change country     |
    Examples:
      |subcategory_name   |category_name     |
     # |Dresses|WOMEN|
      #|MICHAEL Michael Kors|WOMEN|
     # |Wedges|SHOES|
      |Jackets|WOMEN|
     # |Jeans|WOMEN|
     # |Pants|WOMEN|
     # |Maternity|WOMEN|
     # |25-70% Off Clearance|WOMEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_FirstPhase
  Scenario Outline: Verify the Footer links under macy's credit card in FEO Browse pages for First phase pages
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    And I click on link HF links and browse back to verify page got optimized again
      |MACY'S CREDIT CARD |
      |pay bill           |
      |cardholder benefits|
      |learn more & apply |
    Examples:
      |subcategory_name   |category_name     |
     # |Dresses|WOMEN|
     # |MICHAEL Michael Kors|WOMEN|
     # |Wedges|SHOES|
     # |Jackets|WOMEN|
      |Jeans|WOMEN|
     # |Pants|WOMEN|
     # |Maternity|WOMEN|
     # |25-70% Off Clearance|WOMEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_FirstPhase
  Scenario Outline: Verify the Footer links under our stores in FEO Browse pages for First phase pages
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    And I click on link HF links and browse back to verify page got optimized again
      |OUR STORES         |
      |locations & hours  |
      |store events       |
      |catalogs           |
      |tell us what you think|
      |my stylist personal shopping|
    Examples:
      |subcategory_name   |category_name     |
     # |Dresses|WOMEN|
      #|MICHAEL Michael Kors|WOMEN|
     # |Wedges|SHOES|
     # |Jackets|WOMEN|
     # |Jeans|WOMEN|
      |Pants|WOMEN|
     # |Maternity|WOMEN|
     # |25-70% Off Clearance|WOMEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_FirstPhase
  Scenario Outline: Verify the Footer links under macy's Inc in FEO Browse pages for First phase pages
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    And I click on link HF links and browse back to verify page got optimized again
      |MACY'S INC.                 |
      |macysJOBS                   |
      |press room                  |
      |investors                   |
    Examples:
      |subcategory_name   |category_name     |
     # |Dresses|WOMEN|
     # |MICHAEL Michael Kors|WOMEN|
     # |Wedges|SHOES|
     # |Jackets|WOMEN|
     # |Jeans|WOMEN|
     # |Pants|WOMEN|
      |Maternity|WOMEN|
     # |25-70% Off Clearance|WOMEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_FirstPhase
  Scenario Outline: Verify the Footer links under stay connected in FEO Browse pages for First phase pages
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    And I click on link HF links and browse back to verify page got optimized again
      |STAY CONNECTED              |
      |mobile app                  |
      |sign up for email           |
      |See how we're improving     |
      |WHAT'S HAPPENING AT MACY'S  |
    Examples:
      |subcategory_name   |category_name     |
      |Dresses|WOMEN|
     # |MICHAEL Michael Kors|WOMEN|
     # |Wedges|SHOES|
     # |Jackets|WOMEN|
     # |Jeans|WOMEN|
     # |Pants|WOMEN|
     # |Maternity|WOMEN|
     # |25-70% Off Clearance|WOMEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_FirstPhase
  Scenario Outline: Verify the Footer social links for Feo browse pages
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    And I click on footer social media links and browse back to verify page got optimized again
      |facebook|
      |twitter |
      |pinterest|
      |youtube  |
      |macys m-blog|
    Examples:
      |subcategory_name   |category_name     |
     # |Dresses|WOMEN|
     # |MICHAEL Michael Kors|WOMEN|
      |Wedges|SHOES|
     # |Jackets|WOMEN|
     # |Jeans|WOMEN|
     # |Pants|WOMEN|
     # |Maternity|WOMEN|
     # |25-70% Off Clearance|WOMEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_FirstPhase
  Scenario Outline: Verify the page is served from Feo optimized configuration for Footer ad links
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    And I click on footer ad link and browse back to verify page got optimized again
      |Macy's Culinary Council|
      |green living|
      |the magic of giving|
      |macys video channel|
    Examples:
      |subcategory_name||category_name|
     # |Dresses||WOMEN|
     # |MICHAEL Michael Kors||WOMEN|
     # |Wedges||SHOES|
     # |Jackets||WOMEN|
     # |Jeans||WOMEN|
      |Pants||WOMEN|
     # |Maternity||WOMEN|
     # |25-70% Off Clearance||WOMEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_FirstPhase
  Scenario Outline: Verify the page is served from Feo optimization and product thumbnail has title, colors, pricelable and quickview
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    When I select a product having color swatches
    Then I verify that the product title appears
    And I verify that the product image appears
    And I verify that the product price appears
    And I verify that the QuickView label appears on hovering the thumbnail
    Examples:
      |subcategory_name||category_name|
     # |Dresses||WOMEN|
      |MICHAEL Michael Kors||WOMEN|
     # |Wedges||SHOES|
     # |Jackets||WOMEN|
     # |Jeans||WOMEN|
     # |Pants||WOMEN|
     # |Maternity||WOMEN|
     # |25-70% Off Clearance||WOMEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_FirstPhase
  Scenario Outline: Verify the page is served from Feo optimization and product thumbnails are available and clickable
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that page is served from "FEO" tag
    Then I verify that all the product thumbnails displayed properly on the Category Browse page
    Examples:
      |subcategory_name||category_name|
     # |Dresses||WOMEN|
     # |MICHAEL Michael Kors||WOMEN|
     # |Wedges||SHOES|
     # |Jackets||WOMEN|
     # |Jeans||WOMEN|
      |Pants||WOMEN|
     # |Maternity||WOMEN|
     # |25-70% Off Clearance||WOMEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_FirstPhase
  Scenario Outline: Verify the page is served from Feo optimization and getting ensure leftnav links navigating to respective page
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    And I navigate to random sub categories from Left hand nav
    And I select browse 'back' button
    Then I verify that page is served from "FEO" tag
    Examples:
      |subcategory_name||category_name|
     # |Dresses||WOMEN|
     # |MICHAEL Michael Kors||WOMEN|
     # |Wedges||SHOES|
     # |Jackets||WOMEN|
     # |Jeans||WOMEN|
     # |Pants||WOMEN|
      |Maternity||WOMEN|
     # |25-70% Off Clearance||WOMEN|


  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_FirstPhase
  Scenario Outline: Verify the page is served from Feo optimization and getting ensure top icon working as expected
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    And I navigate to bottom of page
    Then I verify that back to top button is displayed on page
    Examples:
      |subcategory_name||category_name|
     # |Dresses||WOMEN|
     # |MICHAEL Michael Kors||WOMEN|
      |Wedges||SHOES|
     # |Jackets||WOMEN|
     # |Jeans||WOMEN|
     # |Pants||WOMEN|
     # |Maternity||WOMEN|
     # |25-70% Off Clearance||WOMEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_FirstPhase
  Scenario Outline: Verify the page is served from Feo optimization and verify filter products when we select any one random facet value
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    When I select 'single' facet value from 'any' facet section
    Then I verify that products are filtered as per selected facet value
    When I click on clear all button
    Then I verify that page is served from "FEO" tag
    Examples:
      |subcategory_name||category_name|
    # |Dresses||WOMEN|
    # |MICHAEL Michael Kors||WOMEN|
    # |Wedges||SHOES|
     |Jackets||WOMEN|
    # |Jeans||WOMEN|
    #  |Pants||WOMEN|
     # |Maternity||WOMEN|
     # |25-70% Off Clearance||WOMEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_FirstPhase
  Scenario Outline: Verify the page is served from Feo optimization and verify pagination using Next / Previous button in DOMESTIC mode
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    And I verify that pagination is displayed
    And I click 2 pagination number
    Then I verify that navigated to page 2 on browse page
    When I click on previous pagination button
    Then I verify that page is served from "FEO" tag
    Examples:
      |subcategory_name||category_name|
     # |Dresses||WOMEN|
     # |MICHAEL Michael Kors||WOMEN|
     # |Wedges||SHOES|
     # |Jackets||WOMEN|
      |Jeans||WOMEN|
     # |Pants||WOMEN|
     # |Maternity||WOMEN|
     # |25-70% Off Clearance||WOMEN||

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_FirstPhase
  Scenario Outline: Verify the page is served from Feo optimization and verify Sort By functionality
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    And I verify that the Sort By displayed with all options
    When I select "Price: High to Low" in sort by drop down
    Then I verify that the Sort By "Price: High to Low" functionality
    Then I select browse 'back' button
    And I verify that page is served from "FEO" tag
    Examples:
      |subcategory_name||category_name|
      |Dresses||WOMEN|
     # |MICHAEL Michael Kors||WOMEN|
     # |Wedges||SHOES|
     # |Jackets||WOMEN|
     # |Jeans||WOMEN|
     # |Pants||WOMEN|
     # |Maternity||WOMEN|
     # |25-70% Off Clearance||WOMEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_FirstPhase
  Scenario Outline: Verify the page is served from Feo optimization and verify top banner
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    Then I verify top banner is displayed on "<Category_Name>" browse page
    Examples:
      |subcategory_name||category_name|
     # |Dresses||WOMEN|
     # |MICHAEL Michael Kors||WOMEN|
     # |Wedges||SHOES|
     # |Jackets||WOMEN|
     # |Jeans||WOMEN|
      |Pants||WOMEN|
     # |Maternity||WOMEN|
     # |25-70% Off Clearance||WOMEN|

    #----------------------------------------------Validation of Optimized Category Splash pages -------------------------------------------------------------------

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_SplashPage_FirstPhase
  Scenario Outline: Verify the search field and auto suggestions in FEO catsplash pages
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to "<Cat_name>" category page
    Then I verify that page is served from "FEO" tag
    And I verify search box is displayed
    When I enter "jea" keyword in search field
    Then I should see autocomplete suggestions list is populated
    Examples:
      |Cat_name|
     # |HOME        |
     # |BED & BATH  |
     # |KIDS        |
      |HANDBAGS    |
     # |BEAUTY      |
     # |SHOES       |
     # |MEN         |
     # |WOMEN       |
     # |Watches     |

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_SplashPage_FirstPhase
  Scenario Outline: Verify the top banner in FEO catsplash pages
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to "<Cat_name>" category page
    Then I verify that page is served from "FEO" tag
    Then I verify top banner is displayed on "<Cat_name>" catsplash page
    Examples:
      |Cat_name|
      |HOME        |
      #|BED & BATH  |
      #|KIDS        |
      #|HANDBAGS    |
      #|BEAUTY      |
      #|SHOES       |
      #|MEN         |
      #|WOMEN       |
      #|Watches     |

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_SplashPage_FirstPhase
  Scenario Outline: Verify the zero nav Header links for Feo catsplash pages
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to "<Cat_name>" category page
    Then I verify that page is served from "FEO" tag
    And I verify the TopNav elements are present in the UI for "guest" user in "Domestic" mode
    And I click on link HF links and browse back to verify page got optimized again
      |SIGN IN|
      |MY ACCOUNT       |
      |STORES           |
      |DEALS            |
      |LISTS            |
      |GIFTS            |
      |WEDDING REGISTRY |
    Examples:
      |Cat_name|
      #|HOME        |
      |BED & BATH  |
      #|KIDS        |
      #|HANDBAGS    |
      #|BEAUTY      |
      #|SHOES       |
      #|MEN         |
      #|WOMEN       |
      #|Watches     |

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_SplashPage_FirstPhase
  Scenario Outline: Verify the Quick bag functionality on FEO catsplsh pages
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to "<Cat_name>" category page
    Then I verify that page is served from "FEO" tag
    When I hover over the quick bag
    Then I should see empty quickbag message
    Examples:
      |Cat_name|
     # |HOME        |
     # |BED & BATH  |
      |KIDS        |
     # |HANDBAGS    |
     # |BEAUTY      |
     # |SHOES       |
     # |MEN         |
     # |WOMEN       |
     # |Watches     |

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_FirstPhase
  Scenario Outline: Verify the Footer links under customer services in FEO Browse pages for First phase pages
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to "<Cat_name>" category page
    Then I verify that page is served from "FEO" tag
    And I click on link HF links and browse back to verify page got optimized again
      |CUSTOMER SERVICE|
      |order tracking|
      |shipping & delivery|
      |returns            |
      |contact us         |
      |para ayuda         |
      |change country     |
    Examples:
      |Cat_name|
    #  |HOME        |
    #  |BED & BATH  |
    #  |KIDS        |
      |HANDBAGS    |
    #  |BEAUTY      |
    #  |SHOES       |
    #  |MEN         |
    #  |WOMEN       |
    #  |Watches     |

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_FirstPhase
  Scenario Outline: Verify the Footer links under macy's credit card in FEO Browse pages for First phase pages
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to "<Cat_name>" category page
    Then I verify that page is served from "FEO" tag
    And I click on link HF links and browse back to verify page got optimized again
      |MACY'S CREDIT CARD |
      |pay bill           |
      |cardholder benefits|
      |learn more & apply |
    Examples:
      |Cat_name|
    #  |HOME        |
    #  |BED & BATH  |
    #  |KIDS        |
    #  |HANDBAGS    |
      |BEAUTY      |
    #  |SHOES       |
    #  |MEN         |
    #  |WOMEN       |
    #  |Watches     |

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_FirstPhase
  Scenario Outline: Verify the Footer links under our stores in FEO Browse pages for First phase pages
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to "<Cat_name>" category page
    Then I verify that page is served from "FEO" tag
    And I click on link HF links and browse back to verify page got optimized again
      |OUR STORES         |
      |locations & hours  |
      |store events       |
      |catalogs           |
      |tell us what you think|
      |my stylist personal shopping|
    Examples:
      |Cat_name|
     # |HOME        |
     # |BED & BATH  |
     # |KIDS        |
     # |HANDBAGS    |
     # |BEAUTY      |
      |SHOES       |
     # |MEN         |
     # |WOMEN       |
     # |Watches     |

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_FirstPhase
  Scenario Outline: Verify the Footer links under macy's Inc in FEO Browse pages for First phase pages
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to "<Cat_name>" category page
    Then I verify that page is served from "FEO" tag
    And I click on link HF links and browse back to verify page got optimized again
      |MACY'S INC.                 |
      |macysJOBS                   |
      |press room                  |
      |investors                   |
    Examples:
      |Cat_name|
    #  |HOME        |
    #  |BED & BATH  |
    #  |KIDS        |
    #  |HANDBAGS    |
    #  |BEAUTY      |
    #  |SHOES       |
      |MEN         |
    #  |WOMEN       |
    #  |Watches     |

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_FirstPhase
  Scenario Outline: Verify the Footer links under stay connected in FEO Browse pages for First phase pages
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to "<Cat_name>" category page
    Then I verify that page is served from "FEO" tag
    And I click on link HF links and browse back to verify page got optimized again
      |STAY CONNECTED              |
      |mobile app                  |
      |sign up for email           |
      |See how we're improving     |
      |WHAT'S HAPPENING AT MACY'S  |
    Examples:
      |Cat_name|
    #  |HOME        |
    #  |BED & BATH  |
    #  |KIDS        |
    #  |HANDBAGS    |
    #  |BEAUTY      |
    #  |SHOES       |
    #  |MEN         |
      |WOMEN       |
    #  |Watches     |

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_SplashPage_FirstPhase
  Scenario Outline: Verify the Footer social links for Feo catsplsh pages
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to "<Cat_name>" category page
    Then I verify that page is served from "FEO" tag
    And I click on footer social media links and browse back to verify page got optimized again
      |facebook|
      |twitter |
      |pinterest|
      |youtube  |
      |macys m-blog|
    Examples:
      |Cat_name|
   #   |HOME        |
   #   |BED & BATH  |
   #   |KIDS        |
   #   |HANDBAGS    |
   #   |BEAUTY      |
   #   |SHOES       |
   #   |MEN         |
   #   |WOMEN       |
      |Watches     |

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_SplashPage_FirstPhase
  Scenario Outline: Verify the Footer ad links for Feo optimized catsplash pages
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to "<Cat_name>" category page
    Then I verify that page is served from "FEO" tag
    And I click on footer ad link and browse back to verify page got optimized again
      |Macy's Culinary Council|
      |green living|
      |the magic of giving|
      |macys video channel|
    Examples:
      |Cat_name|
      #  |HOME        |
    #  |BED & BATH  |
    #  |KIDS        |
    #  |HANDBAGS    |
    #  |BEAUTY      |
    #  |SHOES       |
    #  |MEN         |
      |WOMEN       |
    #  |Watches     |

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_SplashPage_FirstPhase
  Scenario Outline: Verify My Accounts drop down list on Feo optimized catsplash pages
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to "<Cat_name>" category page
    Then I verify that page is served from "FEO" tag
    Then I verify that new dropdown link names are displayed
      | Macy's Credit Card |
      | Order History      |
      | Profile            |
      | Wallet             |
      | Star Rewards       |
      | Plenti             |
      | Lists              |
    Examples:
      |Cat_name|
      #  |HOME        |
    #  |BED & BATH  |
    #  |KIDS        |
    #  |HANDBAGS    |
    #  |BEAUTY      |
    #  |SHOES       |
    #  |MEN         |
      |WOMEN       |
    #  |Watches     |

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_SplashPage_FirstPhase
  Scenario Outline: Verify My Accounts drop down list on Optimized browse page
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    Then I verify that new dropdown link names are displayed
      | Macy's Credit Card |
      | Order History      |
      | Profile            |
      | Wallet             |
      | Star Rewards       |
      | Plenti             |
      | Lists              |
    Examples:
      |subcategory_name   |category_name     |
     # |Dresses|WOMEN|
     # |MICHAEL Michael Kors|WOMEN|
     # |Wedges|SHOES|
      #|Jackets|WOMEN|
     # |Jeans|WOMEN|
      |Pants|WOMEN|
      #|Maternity|WOMEN|
      #|25-70% Off Clearance|WOMEN|

