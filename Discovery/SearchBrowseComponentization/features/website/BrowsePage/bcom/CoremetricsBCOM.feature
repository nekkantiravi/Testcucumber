#------------------------------------------------
# Brand         : BCOM
#-----------------------------------------------

Feature: Verify the RDPP rules and shop 5 and shop 9 are being captured through coremetrics tag on any page with a product grid

  @rdpp @domain_discovery @snbc_coremetrics @migrated_to_sdt
  Scenario Outline: Verify the RDPP rules are being captured through coremetrics tag when user navigated to browse page in All mode
    Given I visit the web site as a guest user in "<Site_Mode>" mode
    And I clear existing class variables to avoid data issues
    When I navigate to browse page in "<Site_Mode>" mode
    And I modify the url to get in to snbc experiment
    Then I should be in Search Landing page
    Examples:
      | Site_Mode |
      | domestic  |
      | registry  |
      | iship     |

  @rdpp @domain_discovery @snbc_coremetrics @migrated_to_sdt
  Scenario Outline: Verify the RDPP rules are being captured through coremetrics tag when user navigated to PDP page in browse page in All mode
    Given I am on CategoryBrowsePage for "<category_id>" category id in <Site_Mode> mode
    And I clear existing class variables to avoid data issues
    When I select random product from thumbnail grid
    And I verify that landed on respective product display page
    Examples:
      | Site_Mode | category_id |
      | domestic  | 17647       |
      | registry  | 8149        |
      | iship     | 17647       |

  @rdpp @domain_discovery @snbc_coremetrics @migrated_to_sdt
  Scenario Outline: Verify the RDPP rules are being captured through coremetrics tag when the user navigated to quick peek page in browse page on All mode
    Given I am on CategoryBrowsePage for "<category_id>" category id in <Site_Mode> mode
    And I clear existing class variables to avoid data issues
    When I select the quick peek of random member product
    Then I verify that quick peek is displayed
    Examples:
      | Site_Mode | category_id |
      | domestic  | 17647       |
      | registry  | 8149        |
      | iship     | 17647       |

  @rdpp @domain_discovery @snbc_coremetrics @migrated_to_sdt
  Scenario Outline: Verify the RDPP rules are being captured through coremetrics tag when the user navigated to See full product info page by quick peek in browse page on All mode
    Given I am on CategoryBrowsePage for "<category_id>" category id in <Site_Mode> mode
    And I clear existing class variables to avoid data issues
    When I select the quick peek of random member product
    Then I verify that quick peek is displayed
    When I select 'see full product details' link from the quickview dialog
    Then I should be redirected to PDP page
    Examples:
      | Site_Mode | category_id |
      | domestic  | 17647       |
      | registry  | 8149        |
      | iship     | 17647       |

  @rdpp @domain_discovery @snbc_coremetrics @shop5 @migrated_to_sdt
  Scenario Outline: Verify the RDPP rules and shop 5 tags are being captured through coremetrics tag when the user adds an item to bag from Quick Peek after selecting the product from a product grid in browse page on All mode
    Given I am on CategoryBrowsePage for "<category_id>" category id in <Site_Mode> mode
    And I clear existing class variables to avoid data issues
    When I select the quick peek of random member product
    Then I verify that quick peek is displayed
    When I click add to bag button on QuickView page
    And I click checkout button on QuickView page
    Then I verify that landed on "shopping_bag" Page
    Examples:
      | Site_Mode | category_id |
      | domestic  | 17647       |
      | registry  | 8149        |
      | iship     | 17647       |

  @rdpp @domain_discovery @snbc_coremetrics @shop5 @migrated_to_sdt
  Scenario Outline: Verify the RDPP rules and shop 5 tags are being captured through coremetrics tag when the user navigated to shopping bag page by adding an item to bag from PDP after selecting the product from a product grid in browse page on All mode
    Given I am on CategoryBrowsePage for "<category_id>" category id in <Site_Mode> mode
    And I clear existing class variables to avoid data issues
    When I select random member product from thumbnail grid
    And I verify that landed on respective product display page
    Then I add product to my bag from standard PDP
    Then I click on checkout button on add to bag page
    Then I verify that landed on "shopping_bag" Page
    Examples:
      | Site_Mode | category_id |
      | domestic  | 17647       |
      | registry  | 8149        |
      | iship     | 17647       |

  @rdpp @domain_discovery @snbc_coremetrics @shop9 @migrated_to_sdt
  Scenario Outline: Verify the RDPP rules and shop 9 tags are being captured through coremetrics tag when the user checkout the product till order confirmation page from browse page in Site and Registry MODE
    Given I am on CategoryBrowsePage for "<category_id>" category id in <Site_Mode> mode
    And I clear existing class variables to avoid data issues
    And I select "Villeroy & Boch" item from "Brand" facet on left nav
    And I modify the url to get in to snbc experiment
    And I navigate to pdp of "3048" product
    Then I verify that landed on respective product display page
    When I add product to my bag from standard PDP
    And I checkout until I reach the order confirmation page as an "guest" user
    Then I should see order confirmation section displayed with order details
    Examples:
      | Site_Mode | category_id |
      | domestic  | 1005751     |
      | registry  | 8313        |

  @rdpp @domain_discovery @snbc_coremetrics @shop9 @migrated_to_sdt
  Scenario: Verify the RDPP rules and shop 9 tags are being captured through coremetrics tag when the user checkout the product till order confirmation page from browse page in Iship MODE
    Given I visit the web site as a guest user
    And I clear existing class variables to avoid data issues
    When I navigate to international context page
    And I change country to "India"
    And I close the welcome mat if it's visible
    And I navigate directly to "1005752" category page
    And I select "Villeroy & Boch" item from "Brand" facet on left nav
    And I modify the url to get in to snbc experiment
    And I navigate to pdp of "481786" product
    Then I verify that landed on respective product display page
    When I add product to my bag from standard PDP
    And I checkout until I reach the order confirmation page as a "iship" user from "India"
    Then I should see order number on order receipt page

  @rdpp @domain_discovery @snbc_coremetrics @migrated_to_sdt
  Scenario Outline: Verify the RDPP rules are being captured through coremetrics tag when the user navigate to PDP directly from a product grid after selecting a random page and random facet from browse page on All mode
    Given I am on CategoryBrowsePage for "<category_id>" category id in <Site_Mode> mode
    And I clear existing class variables to avoid data issues
    When I select 'random' page number from pagination
    And I select 'single' facet value from 'any' facet section
    And I select random product from thumbnail grid
    And I verify that landed on respective product display page
    Examples:
      | Site_Mode | category_id |
      | domestic  | 17647       |
      | registry  | 8149        |
      | iship     | 17647       |

  @shop5_registered_regular_search_registry @snbc_coremetrics @migrated_to_sdt
  Scenario Outline: Checkout Page - As a registered user, Shop 5 coremetrics tags are populated correctly navigating to PDP from browse page
    Given I visit the web site as a registered user in "<Site_Mode>" mode
    And I clear existing class variables to avoid data issues
    When I navigate to the "<subcategory>" browse page under "<category>"
    Then I modify the url to get in to snbc experiment
    Then I select 1 th product from thumbnail grid
    Then I add product to my bag from standard PDP
    Then I click on checkout button on add to bag page
    Then I verify that landed on "shopping_bag" Page
  Examples:
  | Site_Mode | subcategory  | category |
  | domestic  | Dress Shirts | MEN      |
  | registry  | Cutlery      | KITCHEN  |

  @shop5_registered_regular_search_QV_registry @snbc_coremetrics @migrated_to_sdt
  Scenario Outline: Checkout Page - As a registered user, Shop 5 coremetrics tags are populated correctly from QV on browse page
    Given I visit the web site as a registered user in "<Site_Mode>" mode
    And I clear existing class variables to avoid data issues
    When I navigate to the "<subcategory>" browse page under "<category>"
    Then I modify the url to get in to snbc experiment
    Then I select the quick peek of 1 th product
    Then I verify that quick peek is displayed
    Then I click add to bag button on QuickView page
    Then I click checkout button on QuickView page
    Then I verify that landed on "shopping_bag" Page
    Examples:
      | Site_Mode | subcategory  | category |
      | domestic  | Dress Shirts | MEN      |
      | registry  | Cutlery      | KITCHEN  |