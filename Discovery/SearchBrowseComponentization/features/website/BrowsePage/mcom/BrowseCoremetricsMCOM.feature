#------------------------------------------------
# Brand         : MCOM
#-----------------------------------------------

Feature: Verify the RDPP rules and shop 5 and shop 9 are being captured through coremetrics tag on any page with a product grid

  @rdpp @domain_discovery @snbc_coremetrics @migrated_to_sdt
  Scenario Outline: Verify the RDPP rules are being captured through coremetrics tag when user navigated to browse page in All mode
    Given I visit the web site as a guest user in "<Site_Mode>" mode
    When I navigate to browse page in "<Site_Mode>" mode
    And I modify the url to get in to snbc experiment
    Then I should be in category browse page
    Examples:
      | Site_Mode |
      | domestic  |
      | registry  |
      | iship     |

  @rdpp @domain_discovery @snbc_coremetrics @migrated_to_sdt
  Scenario Outline: Verify the RDPP rules are being captured through coremetrics tag when user navigated to PDP page in browse page in All mode
    Given I am on CategoryBrowsePage for "<category_id>" category id in <Site_Mode> mode
    When I select random product from thumbnail grid
    And I verify that landed on respective product display page
    Examples:
      | Site_Mode | category_id |
      | domestic  | 26481       |
      | registry  | 31760       |
      | iship     | 28754       |

  @rdpp @domain_discovery @snbc_coremetrics @migrated_to_sdt
  Scenario Outline: Verify the RDPP rules are being captured through coremetrics tag when the user navigated to quick peek page in browse page on All mode
    Given I am on CategoryBrowsePage for "<category_id>" category id in <Site_Mode> mode
    When I select the quick peek of random member product
    Then I verify that quick peek is displayed
    Examples:
      | Site_Mode | category_id |
      | domestic  | 26481       |
      | registry  | 31760       |
      | iship     | 28754       |

  @rdpp @domain_discovery @snbc_coremetrics @migrated_to_sdt
  Scenario Outline: Verify the RDPP rules are being captured through coremetrics tag when the user navigated to See full product info page by quick peek in browse page on All mode
    Given I am on CategoryBrowsePage for "<category_id>" category id in <Site_Mode> mode
    When I select the quick peek of random member product
    Then I verify that quick peek is displayed
    When I select 'see full product details' link from the quickview dialog
    Then I should be redirected to PDP page
    Examples:
      | Site_Mode | category_id |
      | domestic  | 26481       |
      | registry  | 31760       |
      | iship     | 28754       |

  @rdpp @domain_discovery @snbc_coremetrics @shop5 @migrated_to_sdt
  Scenario Outline: Verify the RDPP rules and shop 5 tags are being captured through coremetrics tag when the user adds an item to bag from Quick Peek after selecting the product from a product grid in browse page on All mode
    Given I am on CategoryBrowsePage for "<category_id>" category id in <Site_Mode> mode
    When I select the quick peek of random member product
    Then I verify that quick peek is displayed
    When I click add to bag button on QuickView page
    And I click checkout button on QuickView page
    Then I verify that landed on "shopping_bag" Page
    Examples:
      | Site_Mode | category_id |
      | domestic  | 26481       |
      | registry  | 31760       |
      | iship     | 28754       |

  @rdpp @domain_discovery @snbc_coremetrics @shop5 @migrated_to_sdt
  Scenario Outline: Verify the RDPP rules and shop 5 tags are being captured through coremetrics tag when the user navigated to shopping bag page by adding an item to bag from PDP after selecting the product from a product grid in browse page on All mode
    Given I am on CategoryBrowsePage for "<category_id>" category id in <Site_Mode> mode
    When I select random member product from thumbnail grid
    And I verify that landed on respective product display page
    Then I add product to my bag from standard PDP
    Then I click on checkout button on add to bag page
    Then I verify that landed on "shopping_bag" Page
    Examples:
      | Site_Mode | category_id |
      | domestic  | 26481       |
      | registry  | 31760       |
      | iship     | 28754       |

  @rdpp @domain_discovery @snbc_coremetrics @shop9 @migrated_to_sdt
  Scenario Outline: Verify the RDPP rules and shop 9 tags are being captured through coremetrics tag when the user checkout the product till order confirmation page from browse page in Site and Registry MODE
    Given I am on CategoryBrowsePage for "7919" category id in <Site_Mode> mode
    And I select "Lenox" item from "Brand" facet on left nav
    And I modify the url to get in to snbc experiment
    And I navigate to pdp of "22804" product
    Then I verify that landed on respective product display page
    When I add product to my bag from standard PDP
    And I checkout until I reach the order confirmation page as an "guest" user
    Then I should see order confirmation section displayed with order details
    Examples:
      | Site_Mode |
      | domestic  |
      | registry  |

  @rdpp @domain_discovery @snbc_coremetrics @shop9 @migrated_to_sdt
  Scenario: Verify the RDPP rules and shop 9 tags are being captured through coremetrics tag when the user checkout the product till order confirmation page from browse page in Iship MODE
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "India"
    And I close the welcome mat if it's visible
    And I navigate directly to "7919" category page
    And I select "Lenox" item from "Brand" facet on left nav
    And I modify the url to get in to snbc experiment
    And I navigate to pdp of "1310" product
    Then I verify that landed on respective product display page
    When I add product to my bag from standard PDP
    And I checkout until I reach the order confirmation page as a "iship" user from "India"
    Then I should see order number on order receipt page

  @rdpp @domain_discovery @snbc_coremetrics @migrated_to_sdt
  Scenario Outline: Verify the RDPP rules are being captured through coremetrics tag when the user navigate to PDP directly from a product grid after selecting a random page and random facet from browse page on All mode
    Given I am on CategoryBrowsePage for "<category_id>" category id in <Site_Mode> mode
    When I select 'random' page number from pagination
    And I modify the url to get in to snbc experiment
    And I select 'single' facet value from 'any' facet section
    And I select random product from thumbnail grid
    And I verify that landed on respective product display page
    Examples:
      | Site_Mode | category_id |
      | domestic  | 26481       |
      | registry  | 31760       |
      | iship     | 28754       |

  @shop5_registered_regular_search_registry @snbc_coremetrics @migrated_to_sdt
  Scenario Outline: Checkout Page - As a registered user, Shop 5 coremetrics tags are populated correctly navigating to PDP from browse page
    Given I visit the web site as a registered user in "<Site_Mode>" mode
    When I navigate to the "<subcategory>" browse page under "<category>"
    Then I modify the url to get in to snbc experiment
    Then I select 1 th product from thumbnail grid
    Then I add product to my bag from standard PDP
    Then I click on checkout button on add to bag page
    Then I verify that landed on "shopping_bag" Page
    Examples:
      | Site_Mode | subcategory     | category |
      | domestic  | Pants           | MEN      |
      | registry  | Cutlery & Knives| KITCHEN  |

  @shop5_registered_regular_search_QV_registry @snbc_coremetrics @migrated_to_sdt
  Scenario Outline: Checkout Page - As a registered user, Shop 5 coremetrics tags are populated correctly from QV on browse page
    Given I visit the web site as a registered user in "<Site_Mode>" mode
    When I navigate to the "<subcategory>" browse page under "<category>"
    Then I modify the url to get in to snbc experiment
    Then I select the quick peek of 1 th product
    Then I verify that quick peek is displayed
    Then I click add to bag button on QuickView page
    Then I click checkout button on QuickView page
    Then I verify that landed on "shopping_bag" Page
    Examples:
      | Site_Mode | subcategory     | category |
      | domestic  | Pants           | MEN      |
      | registry  | Cutlery & Knives| KITCHEN  |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetric @snbc_coremetrics
  Scenario Outline: BrowsePage - Domestic - Verify link click tags for c2 media in category browse page
    Given I am on CategoryBrowsePage for "<category_id>" category id in <string> mode
    Then I should see "<media>" in "<row_type>" on browse page
    And I verify "<media>" is clickable and navigating to respective media page
    Examples:
      | category_id  | row_type | media                                                                                    |
      | 66718        | 101      | IMAGE_MAP                                                                                |
      | 29891        | 106      | CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics @snbc_coremetrics
  Scenario Outline: BrowsePage - Registry - Verify link click tags for c2 media in category browse page
    Given I am on CategoryBrowsePage for "12291" category id in Registry mode
    Then I should see "<media>" in "<row_type>" on browse page
    And I verify "<media>" is clickable and navigating to respective media page
    Examples:
      | row_type | media         |
      | 106      | CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics @snbc_coremetrics
  Scenario Outline: BrowsePage - Iship - Verify link click tags for c2 media in category browse page
    Given I am on CategoryBrowsePage for "269" category id in Iship mode
    Then I should see "<media>" in "<row_type>" on browse page
    And I verify "<media>" is clickable and navigating to respective media page
    Examples:
      | row_type | media                                                                                    |
      | 106      | CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON |