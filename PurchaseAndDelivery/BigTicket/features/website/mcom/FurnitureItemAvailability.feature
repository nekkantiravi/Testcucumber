# Author: Big Ticket Project QE Team
# Date Created: 01/25/2017
# Version One: B-66705

Feature: As a customer viewing a PDP for furniture online I want to provide my delivery address zip code so that I can know if the item is available for purchase.

  @project_BT @domain_purchase_and_delivery @scenario1
  Scenario Outline: Verify user is able to enter the zipcode on radical PDP page for Big Ticket items
    Given I visit the web site as a <user_type> user
    When I navigate to "BT_furniture and ONHAND" product PDP page
    Then I should see zipcode text field on PDP page for BT products
    And I verify below button on PDP page:
      | ADD TO LIST | true  |
      | ADD TO BAG  | false |
    Examples:
      | user_type  |
      | guest      |
      | registered |

  @project_BT @domain_purchase_and_delivery @scenario2
  Scenario Outline: Verify error message, when user enter zipcode less than 5 digits
    Given I visit the web site as a <user_type> user
    When I navigate to "BT_furniture and orderable" product PDP page
    And I enter a zipcode with 4 digits
    And I click on "bt_zipcode_submit_btn" on "product_display" page
    Then I should see error message related to zipcode:
      | Please enter a valid zip code. |
    Examples:
      | user_type  |
      | guest      |
      | registered |

  @project_BT @domain_purchase_and_delivery @scenario3
  Scenario Outline: Verify user able to enter zipcode starting with 0's
    Given I visit the web site as a <user_type> user
    When I navigate to "BT_mattress and orderable" product PDP page
    And I enter a valid "<zipcode>" starting with zero's
    And I verify below button on PDP page:
      | ADD TO LIST | true |
      | ADD TO BAG  | true |
    Examples:
      | user_type  | zipcode |
      | guest      | 08609   |
      | registered | 08609   |

  @project_BT @domain_purchase_and_delivery @scenario4
  Scenario Outline: Verify "find in store" functionality on PDP page for BT items
    Given I visit the web site as a <user_type> user
    When I navigate to "BT_furniture and orderable" product PDP page
    And I click on "See It In Store" link
    Then I should see store overlay with no change to current FIS functionality
    Examples:
      | user_type  |
      | guest      |
      | registered |

  @project_BT @domain_purchase_and_delivery @scenario5
  Scenario Outline: Verify "Talk to our experts" functionality on PDP page for BT items
    Given I visit the web site as a <user_type> user
    When I navigate to "BT_furniture and orderable" product PDP page
    Then I should see talk to experts copy message as:
      | For questions or financing options, talk to a Macy’s Furniture and Mattress Expert. Call 1800-BUY-MACY (289-6229) |

    Examples:
      | user_type  |
      | guest      |
      | registered |


  @project_BT @domain_purchase_and_delivery @scenario6
  Scenario Outline: Verify user able to add the Big Ticket items to wishlist
    Given I visit the web site as a <user_type> user
    When I navigate to "BT_furniture and orderable" product PDP page
    And I add the product to wishlist
    Then I should see the message saying item is added to list
    When I select wishlist link on the wishlist overlay in PDP page
    Then I should see wishlist landing page as a <user_type> user
    Examples:
      | user_type  |
      | guest      |
      | registered |


  @project_BT @domain_purchase_and_delivery @scenario7
  Scenario Outline: Verify user able to add BT items to bag, when we get the availability response as "Available" for given zipcode
    Given I visit the web site as a <user_type> user
    When I navigate to "BT_furniture and ONHAND" product PDP page
    And I enter a zipcode on BT PDP page
    And I click on "bt_zipcode_submit_btn" on "product_display" page
    Then I should see "ONHAND" availability message on BT PDP page as:
      | In stock and available for delivery to <zip_code>. |
    And I verify below button on PDP page:
      | ADD TO LIST | true |
      | ADD TO BAG  | true |
    When I add product to my bag from standard PDP Page
    Then I should see that BT product is successfully added to bag
    Examples:
      | user_type  |
      | guest      |
      | registered |

  @project_BT @domain_purchase_and_delivery @scenario8
  Scenario Outline: Verify user able to add BT items to wishlist, when we get the availability response as "Available" for given zipcode
    Given I visit the web site as a <user_type> user
    When I navigate to "BT_furniture and ONHAND" product PDP page
    And I enter a zipcode on BT PDP page
    And I click on "bt_zipcode_submit_btn" on "product_display" page
    Then I should see "ONHAND" availability message on BT PDP page as:
      | In stock and available for delivery to <zip_code>. |
    And I verify below button on PDP page:
      | ADD TO LIST | true |
      | ADD TO BAG  | true |
    When I add the product to wishlist
    Then I should see the message saying item is added to list
    When I select wishlist link on the wishlist overlay in PDP page
    Then I should see wishlist landing page as a <user_type> user
    Examples:
      | user_type  |
      | guest      |
      | registered |


  @project_BT @domain_purchase_and_delivery @scenario9
  Scenario Outline: Verify error message on PDP page for BT items with multiple attributes, when user will not select all the required attributes while adding product to wishlist
    Given I visit the web site as a <user_type> user
    When I navigate to "BT_mattress_type and ONHAND" product PDP page
    And I enter a zipcode on BT PDP page
    And I click on "bt_zipcode_submit_btn" on "product_display" page
    And I add the product to <type>
    Then I should see error message as "Please select a type."
    Examples:
      | user_type  | type     |
      | guest      | wishlist |
      | registered | wishlist |
      | guest      | bag      |
      | registered | bag      |

  @project_BT @domain_purchase_and_delivery @scenario10
  Scenario Outline: Verify PDP page for BT items, when we get the availability response as "Not Available" for given zipcode
    Given I visit the web site as a <user_type> user
    When I navigate to "BT_furniture and NOT_AVAILABLE" product PDP page
    And I enter a zipcode on BT PDP page
    And I click on "bt_zipcode_submit_btn" on "product_display" page
    Then I should see "NOT_AVAILABLE" availability message on BT PDP page as:
      | This item is not available for delivery in your area. |
    And  I verify below button on PDP page:
      | ADD TO LIST | true  |
      | ADD TO BAG  | false |
    When I add the product to wishlist
    Then I should see the message saying item is added to list
    Examples:
      | user_type  |
      | guest      |
      | registered |

  @project_BT @domain_purchase_and_delivery @scenario11
  Scenario Outline: Verify PDP page for BT items, when we get the availability response as "On-Order" for given zipcode
    Given I visit the web site as a <user_type> user
    When I navigate to "BT_furniture and BACKORDER" product PDP page
    And I enter a zipcode on BT PDP page
    And I click on "bt_zipcode_submit_btn" on "product_display" page
    Then I should see "BACKORDER" availability message on BT PDP page as:
      | Order now. This item is estimated to be in stock by |
    And  I verify below button on PDP page:
      | ADD TO LIST | true |
      | ADD TO BAG  | true |
    When I add the product to wishlist
    Then I should see the message saying item is added to list
    When I add product to my bag from standard PDP Page
    Then I should see that BT product is successfully added to bag
    Examples:
      | user_type  |
      | guest      |
      | registered |

  @project_BT @domain_purchase_and_delivery @scenario12 @manual
  Scenario Outline: Verify user is able to enter the zipcode on master PDP page for Big Ticket items
    Given I visit the web site as a <user_type> user
    When I navigate to "BT_collection_master and orderable" product PDP page
    Then I should see zipcode text field on PDP page for BT products
    And I verify below button on PDP page for all member products:
      | ADD TO LIST | true  |
      | ADD TO BAG  | false |
    And I verify the availabilty message for all the member products
    Examples:
      | user_type  |
      | guest      |
      | registered |

  @project_BT @domain_purchase_and_delivery @scenario13 @manual
  Scenario Outline: Verify user able to add BT product to wishlist/bag with multiple attribute, when we get the availability response as "On-Order" for given zipcode
    Given I visit the web site as a <user_type> user
    When I navigate to "BT_furniture and BACKORDER" product PDP page
    And I enter a zipcode on BT PDP page
    And I click on "bt_zipcode_submit_btn" on "product_display" page
    Then I should see "BACKORDER" availability message on BT PDP page as:
      | Order now. This item is estimated to be in stock by |
    And I verify error message when user selects the color which is not available:
      | TBD |
    And I verify below button on PDP page:
      | ADD TO LIST | true |
      | ADD TO BAG  | true |
    When I add the product to bag
    Then I should see error message as "Please select a type."
    When I select random color swatch for the given product
    And I select available size of the product
    And I add the product to <type>
    Then I should see that BT product is successfully added to <type>

    Examples:
      | user_type  | type     |
      | guest      | bag      |
      | registered | bag      |
      | guest      | wishlist |
      | registered | wishlist |

  @project_BT @domain_purchase_and_delivery @scenario14 @manual
  Scenario Outline: Verify user is able to enter the zipcode on Quick View overlay for Big Ticket items
    Given I visit the web site as a <user_type> user
    When I navigate to the "Furniture" browse page under "Home"
    And I quick view a random product on browse page
    Then I should see zipcode text field on QV overlay for BT products
    And I verify below button on QV overlay:
      | ADD TO LIST | true  |
      | ADD TO BAG  | false |
    Examples:
      | user_type  |
      | guest      |
      | registered |

  @project_BT @domain_purchase_and_delivery @scenario15 @manual
  Scenario Outline: Verify user able to see availability information for all product attributes
    Given I visit the web site as a <user_type> user
    When I navigate to "BT_furniture and ONHAND" product PDP page
    And I enter a zipcode on BT PDP page
    And I click on "bt_zipcode_submit_btn" on "product_display" page
    Then I should see "ONHAND" availability message on BT PDP page as:
      | In stock and available for delivery to <zip_code>. |
    And I should see all colors,sizes or both attributes for the available UPC's
    And I verify user can select the colors which are available
    And I verify colors that are not available should be shown with a X mark
    And I verify user cannot select the colors which are not available
    And I verify below button on PDP page for all member products:
      | ADD TO LIST | true |
      | ADD TO BAG  | true |
    And I should see existing BOPS functionality suppressed on the PDP for BT items
    Examples:
      | user_type  |
      | guest      |
      | registered |

  @project_BT @domain_purchase_and_delivery @scenario16 @manual
  Scenario Outline: Verify user able to add BT product to wishlist/bag with multiple attribute, when we get the availability response as "Available" for given zipcode
    Given I visit the web site as a <user_type> user
    When I navigate to "BT_furniture and ONHAND" product PDP page
    And I enter a zipcode on BT PDP page
    And I click on "bt_zipcode_submit_btn" on "product_display" page
    Then I should see "ONHAND" availability message on BT PDP page as:
      | In stock and available for delivery to <zip_code>. |
    And I verify error message when user selects the color which is not available:
      | TBD |
    And I verify below button on PDP page:
      | ADD TO LIST | true |
      | ADD TO BAG  | true |
    When I select random color swatch for the given product
    And I select available size of the product
    And I add the product to <type>
    Then I should see that BT product is successfully added to <type>
    Examples:
      | user_type  | type     |
      | guest      | wishlist |
      | registered | wishlist |
      | guest      | bag      |
      | registered | bag      |