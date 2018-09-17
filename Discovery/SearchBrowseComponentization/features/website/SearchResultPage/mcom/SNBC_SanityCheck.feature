# Author: Discovery QE Team

Feature: Sanity check for SNBC on all mode

  @daily_run @snbc_reg @snbc_bat
  Scenario Outline: SearchResultPage - Domestic|Iship|Registry - Verify basic sanity check on search page
    Given I am on SearchResultsPage for "black" in <shopping_mode> mode
    Then I verify that item count buttons in page
    When I select the quick peek of random product
    Then I verify that quick peek is displayed
    Then I take screen shot of the page
    When I close the quickview dialog
    Then I verify that quick peek is not displayed
    When I click 3 pagination number
    Then I verify that navigated to page 3 on search result page
    Then I take screen shot of the page
    When I select random option in sort by drop down
    Then I verify that navigated to page 1 on search result page
    Then I take screen shot of the page
    When I click 2 pagination number
    Then I verify that navigated to page 2 on search result page
    Then I take screen shot of the page
    When I select 'single' facet value from 'any' facet section
    Then I verify that navigated to page 1 on search result page
    Then I take screen shot of the page
    Then I verify that products are filtered as per selected facet values
    When I remove the selected facet from the breadcrumb
    Then I verify that the product count returns to original
    Then I take screen shot of the page
    When I click 4 pagination number
    Then I verify that navigated to page 4 on search result page
    Then I take screen shot of the page
    When I filter the result set to show "120" items per page
    Then I verify that navigated to page 1 on search result page
    Then I take screen shot of the page
    Examples:
      | shopping_mode |
      | Domestic      |
      | REGISTRY      |
      | ISHIP         |

  @daily_run @snbc_reg @snbc_bat
  Scenario Outline: SearchResultPage - Domestic|Iship|Registry - Verify basic sanity check on search page for registered user
    Given I visit the web site as a registered user in "<shopping_mode>" mode
    When I search for "black"
    And I modify the url to get in to snbc experiment
    Then I verify that item count buttons in page
    When I select the quick peek of random product
    Then I verify that quick peek is displayed
    Then I take screen shot of the page
    When I close the quickview dialog
    Then I verify that quick peek is not displayed
    When I click 3 pagination number
    Then I verify that navigated to page 3 on search result page
    Then I take screen shot of the page
    When I select random option in sort by drop down
    Then I verify that navigated to page 1 on search result page
    Then I take screen shot of the page
    When I click 2 pagination number
    Then I verify that navigated to page 2 on search result page
    Then I take screen shot of the page
    When I select 'single' facet value from 'any' facet section
    Then I verify that navigated to page 1 on search result page
    Then I take screen shot of the page
    Then I verify that products are filtered as per selected facet values
    When I remove the selected facet from the breadcrumb
    Then I verify that the product count returns to original
    Then I take screen shot of the page
    When I click 4 pagination number
    Then I verify that navigated to page 4 on search result page
    Then I take screen shot of the page
    When I filter the result set to show "120" items per page
    Then I verify that navigated to page 1 on search result page
    Then I take screen shot of the page
    Examples:
      | shopping_mode |
      | domestic      |
      | registry      |

  @daily_run @snbc_reg @snbc_bat
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify ADD TO BAG button on quick view overlay as guest user in All mode
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    Then I select "quick view" button for "member" product on page
    Then I verify that quick peek is displayed
    And I take screen shot of the page
    Then I click add to bag button on QuickView page
    Then I click checkout button on QuickView page
    Then I verify that respective product is in 'Shopping Bag' page
    And I take screen shot of the page
    Examples:
      | shopping_mode | keyword  |
      | Domestic      | hangers  |
      | Registry      | hangers  |
      | Iship         | handbags |

  #***********************ADD TO BAG  AS Registered User for Domestic|Iship|Registry Mode *******************************************
  @daily_run @snbc_reg @snbc_bat
  Scenario: SearchResultsPage - Domestic - Verify ADD TO BAG button on quick view overlay as singed-in user in Domestic mode
    Given I visit the web site as a registered user in "domestic" mode
    Then I search for "knife" in search box
    Then I modify the url to get in to snbc experiment
    Then  I select "quick view" button for "member" product on page
    Then I verify that quick peek is displayed
    And I take screen shot of the page
    Then I click add to bag button on QuickView page
    Then I click checkout button on QuickView page
    Then I verify that respective product is in 'Shopping Bag' page
    And I take screen shot of the page

  @daily_run @snbc_reg @snbc_bat
  Scenario: SearchResultsPage -  Registry  - Verify ADD TO BAG button on quick view overlay as singed-in user in  Registry  mode
    Given I visit the web site as a registered user in "registry" mode
    Then I search for "knife" in search box
    Then I modify the url to get in to snbc experiment
    Then I select "quick view" button for "member" product on page
    Then I verify that quick peek is displayed
    And I take screen shot of the page
    Then I click add to bag button on QuickView page
    Then I click checkout button on QuickView page
    Then I verify that respective product is in 'Shopping Bag' page
    And I take screen shot of the page

 # ***********************ADD TO REGISTRY  As Guest User For Domestic Mode *******************************************
  @daily_run @snbc_reg @snbc_bat
  Scenario: SearchResultsPage - Domestic - Verify ADD TO REGISTRY button on quick view overlay as guest user in Domestic mode
    Given I visit the web site as a guest user in "domestic" mode
    Then I search for "knife" in search box
    Then I modify the url to get in to snbc experiment
    Then I select "quick view" button for "member" product on page
    Then I verify that quick peek is displayed
    And I take screen shot of the page
    Then I click "ADD TO REGISTRY" button on QuickView page
    Then I should be navigated to the registry sign in page
    And I take screen shot of the page

#*********************** ADD TO REGISTRY  As Register User for Domestic Mode *******************************************
  @daily_run @snbc_reg @snbc_bat
  Scenario: SearchResultsPage - Domestic - Verify ADD TO REGISTRY button on quick view overlay as singed-in user in Domestic mode
    Given I visit the web site as a registered user in "domestic" mode
    Then I search for "knife" in search box
    Then I modify the url to get in to snbc experiment
    Then I select "quick view" button for "member" product on page
    Then I verify that quick peek is displayed
    And I take screen shot of the page
    Then I click "ADD TO REGISTRY" button on QuickView page
    Then I verified error message displayed as "There is no registry associated to this profile. Please create a registry or claim your in-store created registry from the Registry Homepage."
    And I take screen shot of the page

   # ***********************ADD TO REGISTRY  As Registry  User *******************************************
  @daily_run @snbc_reg @snbc_bat
  Scenario: SearchResultsPage - Registry - Verify ADD TO REGISTRY button on quick view overlay as registry user in Registry mode
    Given I visit the web site as a registry user
    Then I search for "knife" in search box
    Then I modify the url to get in to snbc experiment
    Then I select "quick view" button for "member" product on page
    Then I verify that quick peek is displayed
    And I take screen shot of the page
    Then I click "ADD TO REGISTRY" button on QuickView page
    Then I should see success message in quick view overlay
    And I take screen shot of the page

  #*********************** ADD TO LIST as Guest User for Domestic and Registry Mode *******************************************
  @daily_run @snbc_reg @snbc_bat
  Scenario Outline: SearchResultsPage - Domestic|Registry - Verify ADD TO LIST button on quick view overlay as guest user in Domestic|Registry mode
    Given I am on SearchResultsPage for "knife" in <shopping_mode> mode
    Then I select "quick view" button for "member" product on page
    Then I verify that quick peek is displayed
    And I take screen shot of the page
    Then I click "ADD TO LIST" button on QuickView page
    Then I should see success message as "Added to your guest List.Sign In to save it to your account."
    And I take screen shot of the page
    Examples:
      | shopping_mode |
      | Domestic      |

  #*********************** ADD TO LIST as Registered User for Domestic and Registry Mode*******************************************
  @daily_run @snbc_reg @snbc_bat
  Scenario: SearchResultsPage - Domestic - Verify ADD TO LIST button on quick view overlay as registered user in Domestic mode
    Given I visit the web site as a registered user in "domestic" mode
    Then I search for "knife" in search box
    Then I modify the url to get in to snbc experiment
    Then I select "quick view" button for "member" product on page
    Then I verify that quick peek is displayed
    And I take screen shot of the page
    Then I click "ADD TO LIST" button on QuickView page
    Then I should see success message as "Added to your List."
    And I take screen shot of the page

    #------- Browse Page Verifications-----------------#

  @daily_run @snbc_reg @snbc_bat
  Scenario Outline: SearchResultPage - Domestic|Iship|Registry - Verify basic sanity check on browse page
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    Then I verify that item count buttons in page
    When I select the quick peek of random product
    Then I verify that quick peek is displayed
    Then I take screen shot of the page
    When I close the quickview dialog
    Then I verify that quick peek is not displayed
    When I click 3 pagination number
    Then I verify that navigated to page 3 on search result page
    Then I take screen shot of the page
    When I select random option in sort by drop down
    Then I verify that navigated to page 1 on search result page
    Then I take screen shot of the page
    When I click 2 pagination number
    Then I verify that navigated to page 2 on search result page
    Then I take screen shot of the page
    When I select 'single' facet value from 'any' facet section
    Then I verify that navigated to page 1 on search result page
    Then I take screen shot of the page
    Then I verify that products are filtered as per selected facet values
    When I remove the selected facet from the breadcrumb
    Then I verify that the product count returns to original
    Then I take screen shot of the page
    When I click 4 pagination number
    Then I verify that navigated to page 4 on search result page
    Then I take screen shot of the page
    When I filter the result set to show "120" items per page
    Then I verify that navigated to page 1 on search result page
    Then I take screen shot of the page
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 44717       |
      | Registry      | 53629       |
      | Iship         | 44717       |

  @daily_run @snbc_reg @snbc_bat
  Scenario Outline: SearchResultPage - Domestic|Iship|Registry - Verify basic sanity check on browse page for registered user
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    When I search for "black"
    And I modify the url to get in to snbc experiment
    Then I verify that item count buttons in page
    When I select the quick peek of random product
    Then I verify that quick peek is displayed
    Then I take screen shot of the page
    When I close the quickview dialog
    Then I verify that quick peek is not displayed
    When I click 3 pagination number
    Then I verify that navigated to page 3 on search result page
    Then I take screen shot of the page
    When I select random option in sort by drop down
    Then I verify that navigated to page 1 on search result page
    Then I take screen shot of the page
    When I click 2 pagination number
    Then I verify that navigated to page 2 on search result page
    Then I take screen shot of the page
    When I select 'single' facet value from 'any' facet section
    Then I verify that navigated to page 1 on search result page
    Then I take screen shot of the page
    Then I verify that products are filtered as per selected facet values
    When I remove the selected facet from the breadcrumb
    Then I verify that the product count returns to original
    Then I take screen shot of the page
    When I click 4 pagination number
    Then I verify that navigated to page 4 on search result page
    Then I take screen shot of the page
    When I filter the result set to show "120" items per page
    Then I verify that navigated to page 1 on search result page
    Then I take screen shot of the page
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 44717       |
      | Registry      | 53629       |

  @daily_run @snbc_reg @snbc_bat
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify ADD TO BAG button on quick view overlay as guest user from browse page in All mode
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    Then I select "quick view" button for "member" product on page
    Then I verify that quick peek is displayed
    And I take screen shot of the page
    Then I click add to bag button on QuickView page
    Then I click checkout button on QuickView page
    Then I verify that respective product is in 'Shopping Bag' page
    And I take screen shot of the page
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 44717       |
      | Registry      | 53629       |
      | Iship         | 44717       |

  #***********************ADD TO BAG  AS Registered User for Domestic|Iship|Registry Mode *******************************************
  @daily_run @snbc_reg @snbc_bat
  Scenario: SearchResultsPage - Domestic - Verify ADD TO BAG button on quick view overlay from browse page as singed-in user in Domestic mode
    Given I visit the web site as a registered user in "domestic" mode
    When I navigate to the "Cleaning & Organization" browse page under "HOME"
    Then I modify the url to get in to snbc experiment
    Then  I select "quick view" button for "member" product on page
    Then I verify that quick peek is displayed
    And I take screen shot of the page
    Then I click add to bag button on QuickView page
    Then I click checkout button on QuickView page
    Then I verify that respective product is in 'Shopping Bag' page
    And I take screen shot of the page

  @daily_run @snbc_reg @snbc_bat
  Scenario: SearchResultsPage -  Registry  - Verify ADD TO BAG button on quick view overlay from browse page as singed-in user in  Registry  mode
    Given I visit the web site as a registered user in "registry" mode
    When I navigate to the "Cutlery & Knives" browse page under "KITCHEN"
    Then I modify the url to get in to snbc experiment
    Then I select "quick view" button for "member" product on page
    Then I verify that quick peek is displayed
    And I take screen shot of the page
    Then I click add to bag button on QuickView page
    Then I click checkout button on QuickView page
    Then I verify that respective product is in 'Shopping Bag' page
    And I take screen shot of the page

 # ***********************ADD TO REGISTRY  As Guest User For Domestic Mode *******************************************
  @daily_run @snbc_reg @snbc_bat
  Scenario: SearchResultsPage - Domestic - Verify ADD TO REGISTRY button on quick view overlay from browse page as guest user in Domestic mode
    Given I visit the web site as a guest user in "domestic" mode
    When I am on Browse Page for "Cleaning & Organization" under "Home"
    Then I modify the url to get in to snbc experiment
    Then I select "quick view" button for "member" product on page
    Then I verify that quick peek is displayed
    And I take screen shot of the page
    Then I click "ADD TO REGISTRY" button on QuickView page
    Then I should be navigated to the registry sign in page
    And I take screen shot of the page

#*********************** ADD TO REGISTRY  As Register User for Domestic Mode *******************************************
  @daily_run @snbc_reg @snbc_bat
  Scenario: SearchResultsPage - Domestic - Verify ADD TO REGISTRY button on quick view overlay from browse page as singed-in user in Domestic mode
    Given I visit the web site as a registered user in "domestic" mode
    When I navigate to the "Cleaning & Organization" browse page under "HOME"
    Then I modify the url to get in to snbc experiment
    Then I select "quick view" button for "member" product on page
    Then I verify that quick peek is displayed
    And I take screen shot of the page
    Then I click "ADD TO REGISTRY" button on QuickView page
    Then I verified error message displayed as "There is no registry associated to this profile. Please create a registry or claim your in-store created registry from the Registry Homepage."
    And I take screen shot of the page

   # ***********************ADD TO REGISTRY  As Registry  User *******************************************
  @daily_run @snbc_reg @snbc_bat
  Scenario: SearchResultsPage - Registry - Verify ADD TO REGISTRY button on quick view overlay from browse page as registry user in Registry mode
    Given I visit the web site as a registry user
    When I am on Browse Page for "Cutlery & Knives" under "KITCHEN"
    Then I modify the url to get in to snbc experiment
    Then I select "quick view" button for "member" product on page
    Then I verify that quick peek is displayed
    And I take screen shot of the page
    Then I click "ADD TO REGISTRY" button on QuickView page
    Then I should see success message in quick view overlay
    And I take screen shot of the page

  #*********************** ADD TO LIST as Guest User for Domestic and Registry Mode *******************************************
  @daily_run @snbc_reg @snbc_bat
  Scenario Outline: SearchResultsPage - Domestic|Registry - Verify ADD TO LIST button on quick view overlay from browse page as guest user in Domestic|Registry mode
    Given I am on CategoryBrowsePage for "31760" category id in <shopping_mode> mode
    Then I select "quick view" button for "member" product on page
    Then I verify that quick peek is displayed
    And I take screen shot of the page
    Then I click "ADD TO LIST" button on QuickView page
    Then I should see success message as "Added to your guest List.Sign In to save it to your account."
    And I take screen shot of the page
    Examples:
      | shopping_mode |
      | Domestic      |

  #*********************** ADD TO LIST as Registered User for Domestic and Registry Mode*******************************************
  @daily_run @snbc_reg @snbc_bat
  Scenario: SearchResultsPage - Domestic - Verify ADD TO LIST button on quick view overlay from browse page as registered user in Domestic mode
    Given I visit the web site as a registered user in "domestic" mode
    When I navigate to the "Cleaning & Organization" browse page under "HOME"
    Then I modify the url to get in to snbc experiment
    Then I select "quick view" button for "member" product on page
    Then I verify that quick peek is displayed
    And I take screen shot of the page
    Then I click "ADD TO LIST" button on QuickView page
    Then I should see success message as "Added to your List."
    And I take screen shot of the page

    #------- Sub-Splash Page Verifications-----------------#

  @daily_run @snbc_reg @snbc_bat
  Scenario Outline: SearchResultPage - Domestic|Iship|Registry - Verify basic sanity check on sub-splash page
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    Then I verify that item count buttons in page
    When I select the quick peek of random product
    Then I verify that quick peek is displayed
    Then I take screen shot of the page
    When I close the quickview dialog
    Then I verify that quick peek is not displayed
    When I click 3 pagination number
    Then I verify that navigated to page 3 on search result page
    Then I take screen shot of the page
    When I select random option in sort by drop down
    Then I verify that navigated to page 1 on search result page
    Then I take screen shot of the page
    When I click 2 pagination number
    Then I verify that navigated to page 2 on search result page
    Then I take screen shot of the page
    When I select 'single' facet value from 'any' facet section
    Then I verify that navigated to page 1 on search result page
    Then I take screen shot of the page
    Then I verify that products are filtered as per selected facet values
    When I remove the selected facet from the breadcrumb
    Then I verify that the product count returns to original
    Then I take screen shot of the page
    When I click 4 pagination number
    Then I verify that navigated to page 4 on search result page
    Then I take screen shot of the page
    When I filter the result set to show "120" items per page
    Then I verify that navigated to page 1 on search result page
    Then I take screen shot of the page
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 29891       |
      | Registry      | 28928       |
      | Iship         | 29891       |

  @daily_run @snbc_reg @snbc_bat
  Scenario Outline: SearchResultPage - Domestic|Iship|Registry - Verify basic sanity check on sub-splash page for registered user
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    When I search for "black"
    And I modify the url to get in to snbc experiment
    Then I verify that item count buttons in page
    When I select the quick peek of random product
    Then I verify that quick peek is displayed
    Then I take screen shot of the page
    When I close the quickview dialog
    Then I verify that quick peek is not displayed
    When I click 3 pagination number
    Then I verify that navigated to page 3 on search result page
    Then I take screen shot of the page
    When I select random option in sort by drop down
    Then I verify that navigated to page 1 on search result page
    Then I take screen shot of the page
    When I click 2 pagination number
    Then I verify that navigated to page 2 on search result page
    Then I take screen shot of the page
    When I select 'single' facet value from 'any' facet section
    Then I verify that navigated to page 1 on search result page
    Then I take screen shot of the page
    Then I verify that products are filtered as per selected facet values
    When I remove the selected facet from the breadcrumb
    Then I verify that the product count returns to original
    Then I take screen shot of the page
    When I click 4 pagination number
    Then I verify that navigated to page 4 on search result page
    Then I take screen shot of the page
    When I filter the result set to show "120" items per page
    Then I verify that navigated to page 1 on search result page
    Then I take screen shot of the page
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 29891       |
      | Registry      | 28928       |

  @daily_run @snbc_reg @snbc_bat
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify ADD TO BAG button on quick view overlay as guest user from sub-splash page in All mode
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    Then I select "quick view" button for "member" product on page
    Then I verify that quick peek is displayed
    And I take screen shot of the page
    Then I click add to bag button on QuickView page
    Then I click checkout button on QuickView page
    Then I verify that respective product is in 'Shopping Bag' page
    And I take screen shot of the page
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 29891       |
      | Registry      | 28928       |
      | Iship         | 29891       |

  #***********************ADD TO BAG  AS Registered User for Domestic|Iship|Registry Mode *******************************************
  @daily_run @snbc_reg @snbc_bat
  Scenario: SearchResultsPage - Domestic - Verify ADD TO BAG button on quick view overlay from sub-splash page as singed-in user in Domestic mode
    Given I visit the web site as a registered user in "domestic" mode
    When I am on Browse Page for "Activewear" under "WOMEN"
    Then I modify the url to get in to snbc experiment
    Then  I select "quick view" button for "member" product on page
    Then I verify that quick peek is displayed
    And I take screen shot of the page
    Then I click add to bag button on QuickView page
    Then I click checkout button on QuickView page
    Then I verify that respective product is in 'Shopping Bag' page
    And I take screen shot of the page

  @daily_run @snbc_reg @snbc_bat
  Scenario: SearchResultsPage -  Registry  - Verify ADD TO BAG button on quick view overlay from sub-splash page as singed-in user in  Registry  mode
    Given I visit the web site as a registered user in "registry" mode
    When I navigate to the "Mikasa" browse page under "DINING"
    Then I modify the url to get in to snbc experiment
    Then I select "quick view" button for "member" product on page
    Then I verify that quick peek is displayed
    And I take screen shot of the page
    Then I click add to bag button on QuickView page
    Then I click checkout button on QuickView page
    Then I verify that respective product is in 'Shopping Bag' page
    And I take screen shot of the page

  #*********************** ADD TO LIST as Guest User for Domestic and Registry Mode *******************************************
  @daily_run @snbc_reg @snbc_bat
  Scenario Outline: SearchResultsPage - Domestic|Registry - Verify ADD TO LIST button on quick view overlay from sub-splash page as guest user in Domestic|Registry mode
    Given I am on CategoryBrowsePage for "20626" category id in <shopping_mode> mode
    Then I select "quick view" button for "member" product on page
    Then I verify that quick peek is displayed
    And I take screen shot of the page
    Then I click "ADD TO LIST" button on QuickView page
    Then I should see success message as "Added to your guest List.Sign In to save it to your account."
    And I take screen shot of the page
    Examples:
      | shopping_mode |
      | Domestic      |

  #*********************** ADD TO LIST as Registered User for Domestic and Registry Mode*******************************************
  @daily_run @snbc_reg @snbc_bat
  Scenario: SearchResultsPage - Domestic - Verify ADD TO LIST button on quick view overlay from sub-splash page as registered user in Domestic mode
    Given I visit the web site as a registered user in "domestic" mode
    When I navigate to the "Shirts" browse page under "MEN"
    Then I modify the url to get in to snbc experiment
    Then I select "quick view" button for "member" product on page
    Then I verify that quick peek is displayed
    And I take screen shot of the page
    Then I click "ADD TO LIST" button on QuickView page
    Then I should see success message as "Added to your List."
    And I take screen shot of the page

    ############ 17R  Failed scenario ###################

  @daily_run @snbc_reg @snbc_bat
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify two keyword search not resulting on zero search results page
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    Then I take screen shot of the page
    Then I verify that Zero Search Result page is not displayed
    Then I take screen shot of the page
    When I select 'single' facet value from 'any' facet section
    Then I take screen shot of the page
    Then I verify that products are filtered as per selected facet values
    Examples:
      | shopping_mode | keyword      |
      | Domestic      | blue jeans   |
      | Registry      | white plates |
      | Iship         | red jeans    |

  @daily_run @snbc_reg @snbc_bat
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify brand keyword search not resulting on zero search results page
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I search for "<keyword>"
    Then I take screen shot of the page
    Then I verify that Zero Search Result page is not displayed
    When I select 'single' facet value from 'any' facet section
    Then I take screen shot of the page
    Then I verify that products are filtered as per selected facet values
    Examples:
      | shopping_mode | keyword        |
      | domestic      | calvin klein   |
      | registry      | Charter Club   |
      | iship         | martha stewart |

  @daily_run @snbc_reg @snbc_bat
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify brand keyword search with product not resulting on zero search results page
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I search for "<keyword>"
    Then I take screen shot of the page
    Then I verify that Zero Search Result page is not displayed
    When I select 'single' facet value from 'any' facet section
    Then I take screen shot of the page
    Then I verify that products are filtered as per selected facet values
    Examples:
      | shopping_mode | keyword                   |
      | domestic      | calvin klein jeans        |
      | registry      | charter club damask       |
      | iship         | martha stewart collection |

  @daily_run @snbc_reg @snbc_bat
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify product and category search not resulting on zero search results page
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I search for "<keyword>"
    Then I take screen shot of the page
    Then I verify that Zero Search Result page is not displayed
    When I select 'single' facet value from 'any' facet section
    Then I take screen shot of the page
    Then I verify that products are filtered as per selected facet values
    Examples:
      | shopping_mode | keyword       |
      | domestic      | jeans women's |
      | registry      | knife cutlery |
      | iship         | pant men's    |

  @daily_run @snbc_reg @snbc_bat
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify searching with brand, color and product and category not resulting on zero search results page
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I search for "<keyword>"
    Then I take screen shot of the page
    Then I verify that Zero Search Result page is not displayed
    When I select 'single' facet value from 'any' facet section
    Then I take screen shot of the page
    Then I verify that products are filtered as per selected facet values
    Examples:
      | shopping_mode | keyword                         |
      | domestic      | calvin klein blue jeans women's |
      | registry      | charter club damask white sheet |
      | iship         | polo blue t-shirt men's         |

  @daily_run @snbc_reg @snbc_bat
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify searching with brand and category not resulting on zero search results page
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I search for "<keyword>"
    Then I take screen shot of the page
    Then I verify that Zero Search Result page is not displayed
    When I select 'single' facet value from 'any' facet section
    Then I take screen shot of the page
    Then I verify that products are filtered as per selected facet values
    Examples:
      | shopping_mode | keyword                 |
      | domestic      | calvin klein women's    |
      | registry      | charter club bed & bath |
      | iship         | polo men's              |

  @daily_run @snbc_reg @snbc_bat
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify partial match message on search landing page when there are multiple combinations to the search term entered
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I verify that message "We found 'n' results for <partial_match_text>" in search landing page header
    And I verify that search message "There were 0 matches for " is displayed
    When I select any search term link
    And I select browse 'back' button
    Then I verify that message "We found 'n' results for <partial_match_text>" in search landing page header
    When I select 'single' facet value from 'any' facet section
    And I click on clear all button
    Then I verify that message "We found 'n' results for <partial_match_text>" in search landing page header
    When I select random product from thumbnail grid
    And I select browse 'back' button
    Then I verify that message "We found 'n' results for <partial_match_text>" in search landing page header
    Examples:
      | shopping_mode | keyword                     | partial_match_text                   |
      | Domestic      | red gucci belt              | Red Gucci , Red Belt                 |
      | Iship         | yellow glassware silverware | Yellow Glassware , Yellow Silverware |
      | Registry      | yellow glassware silverware | Yellow Glassware , Yellow Silverware |


  @daily_run @snbc_reg @snbc_bat
  Scenario: SearchResultsPage - Domestic|Iship|Registry - Verify search with product id not resulting on zero search results page
    Given I visit the web site as a guest user
    When I search for an available product ID or WebID having "productid only"
    Then I take screen shot of the page
    And I should be redirected to PDP page
    When I search for an available product ID or WebID having "productid with preceding zeros and space"
    Then I take screen shot of the page
    And I should be redirected to PDP page
    When I search for an available product ID or WebID having "product id with other characters"
    Then I take screen shot of the page
    And I should be redirected to PDP page

  @daily_run @snbc_reg @snbc_bat
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify searching with special character keyword not resulting on zero search results page
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I search for "<keyword>"
    Then I take screen shot of the page
    Then I verify that Zero Search Result page is not displayed
    Examples:
      | shopping_mode | keyword      |
      | domestic      | Estée Lauder |
      | domestic      | Lancôme      |
      | registry      | Nambé        |
      | iship         | Nambé        |