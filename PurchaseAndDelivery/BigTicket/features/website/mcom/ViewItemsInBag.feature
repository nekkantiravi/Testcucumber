# Author: Big Ticket Project QE Team
# Date Created: 02/22/2017
# Date Updated: 02/28/2017
# Version One: B-66709

Feature: As a customer when I view My Bag I want to view the items I added so I can confirm they are correct before I tender payment.

  @project_BT @domain_purchase_and_delivery @scenario1 @manual
  Scenario Outline: Verify the display of shopping bag page when user have only available BT items
    Given I visit the web site as a <user_type> user
    When I add a big ticket "BT_furniture and ONHAND" product to bag
    And I navigate to shopping bag page
    Then I verify the product attributes are displayed properly
  #Product attributes - thumbnail, product name, color, web ID, quantity and size/dimensions (if available).
    And I should see the big ticket delivery message in shopping bag page:
    |White Glove Delivery to <zip code> Delivery scheduled at checkout |
    And I should not see the "Estimated Shipping"
    And I verify the order attributes are displayed properly
  #Order attributes - Order subtotal, Estimated Delivery Cost, Estimated Sales Tax, Order Total, You Saved (if applicable)
    Examples:
      | user_type  |
      | guest      |
      | registered |

  @project_BT @domain_purchase_and_delivery @scenario2 @manual
  Scenario Outline: Verify the display of shopping bag page when user have only on-order BT items
    Given I visit the web site as a <user_type> user
    When I add a big ticket "BT_furniture and ONHAND" product to bag
    And I navigate to shopping bag page
    Then I verify the product attributes are displayed properly
  #Product attributes - thumbnail, product name, color, web ID, quantity and size/dimensions (if available).
    And I should see the big ticket delivery message in shopping bag page:
      |On-Order White Glove Delivery to <zip code> Delivery scheduled at checkout |
    And I should not see the "Estimated Shipping"
    And I verify the order attributes are displayed properly
  #Order attributes - Order subtotal, Estimated Delivery Cost, Estimated Sales Tax, Order Total, You Saved (if applicable)

    Examples:
      | user_type  |
      | guest      |
      | registered |
    
  @project_BT @domain_purchase_and_delivery @scenario3 @manual
  Scenario Outline: Verify the display of shopping bag page when user have both BT and ST items
    Given I visit the web site as a <user_type> user
    When I add a big ticket "BT_furniture and ONHAND" product to bag
    And I add an "available and orderable" product to my bag
    And I navigate to shopping bag page
    Then I verify the product attributes are displayed properly
  #Product attributes - thumbnail, product name, color, web ID, quantity and size/dimensions (if available).
    And I should see the big ticket delivery message in shopping bag page:
      |White Glove Delivery to <zip code> Delivery scheduled at checkout |
    And I verify the order attributes are displayed properly
  #Order attributes - Order subtotal, Estimated Shipping, Pick-up in Store (if applicable), Estimated delivery cost, Estimated Sales Tax, Order total and You Saved (if applicable)

    Examples:
      | user_type  |
      | guest      |
      | registered |

  @project_BT @domain_purchase_and_delivery @scenario4 @manual
  Scenario Outline: Verify user is able to move the added BT items to list from shopping bag page
    Given I visit the web site as a <user_type> user
    When I add a big ticket "BT_furniture and ONHAND" product to bag
    And I add an "available and orderable" product to my bag
    And I navigate to shopping bag page
    Then I verify the product attributes are displayed properly
  #Product attributes - thumbnail, product name, color, web ID, quantity and size/dimensions (if available).
    When I click on move to list link for the added BT product
    Then I should see the BT item is moved to the list
    And I verify the shopping bag contains the remaining ST items
    And I verify the order attributes are changed accordingly
  #Order attributes - Order subtotal, Estimated Delivery Cost, Estimated Sales Tax, Order Total, You Saved (if applicable)

    Examples:
      | user_type  |
      | guest      |
      | registered |

  @project_BT @domain_purchase_and_delivery @scenario5 @manual
  Scenario Outline: Verify user is able to remove the added BT items from shopping bag page
    Given I visit the web site as a <user_type> user
    When I add a big ticket "BT_furniture and ONHAND" product to bag
    And  I add an "available and orderable" product to my bag
    And I navigate to shopping bag page
    Then I verify the product attributes are displayed properly
  #Product attributes - thumbnail, product name, color, web ID, quantity and size/dimensions (if available).
    When I click on remove link for the added BT product
    Then I should see the BT item is removed from bag
    And I verify the shopping bag contains the remaining ST items
    And I verify the order attributes are changed accordingly
  #Order attributes - Order subtotal, Estimated Delivery Cost, Estimated Sales Tax, Order Total, You Saved (if applicable)
    Examples:
      | user_type  |
      | guest      |
      | registered |

  @project_BT @domain_purchase_and_delivery @scenario6 @manual
  Scenario Outline: Verify user is able to remove the added ST items from shopping bag page when user have ST and BT items
    Given I visit the web site as a <user_type> user
    When I add a big ticket "BT_furniture and ONHAND" product to bag
    And  I add an "available and orderable" product to my bag
    And I navigate to shopping bag page
    Then I verify the product attributes are displayed properly
#Product attributes - thumbnail, product name, color, web ID, quantity and size/dimensions (if available).
    When I click on remove link for the added ST product
    Then I should see the ST item is removed from bag
    And I verify the shopping bag contains the remaining BT items
    And I verify the order attributes are changed accordingly
#Order attributes - Order subtotal, Estimated Delivery Cost, Estimated Sales Tax, Order Total, You Saved (if applicable)
    Examples:
      | user_type  |
      | guest      |
      | registered |

     @project_BT @domain_purchase_and_delivery @scenario7 @manual
  Scenario Outline: Verify user is able to move the added ST items to list from shopping bag page when user have both ST and BT items
    Given I visit the web site as a <user_type> user
       When I add a big ticket "BT_furniture and ONHAND" product to bag
    And I add an "available and orderable" product to my bag
    And I navigate to shopping bag page
    Then I verify the product attributes are displayed properly
  #Product attributes - thumbnail, product name, color, web ID, quantity and size/dimensions (if available).
    When I click on move to list link for the added ST product
    Then I should see the ST item is moved to the list
    And I verify the shopping bag contains the remaining BT items
    And I verify the order attributes are changed accordingly
  #Order attributes - Order subtotal, Estimated Delivery Cost, Estimated Sales Tax, Order Total, You Saved (if applicable)

    Examples:
      | user_type  |
      | guest      |
      | registered |

  @project_BT @domain_purchase_and_delivery @scenario8 @manual
  Scenario Outline: Verify user is able to update the quantity for the added BT items from shopping bag page
    Given I visit the web site as a <user_type> user
    When I add a big ticket "BT_furniture and ONHAND" product to bag
    And I navigate to shopping bag page
    Then I verify the product attributes are displayed properly
  #Product attributes - thumbnail, product name, color, web ID, quantity and size/dimensions (if available).
    When I update the quantity of the BT product
    Then I should see the BT item quantity is updated
    And I verify the order attributes are changed accordingly
  #Order attributes - Order subtotal, Estimated Delivery Cost, Estimated Sales Tax, Order Total, You Saved (if applicable)
    Examples:
      | user_type  |
      | guest      |
      | registered |

  @project_BT @domain_purchase_and_delivery @scenario9 @manual
  Scenario: Verify user is displayed with the wallet offers in My wallet section bag, when user is having only BT items
    Given I visit the website as a registered user having saved offers in wallet
    When I add a big ticket "BT_furniture and ONHAND" product to bag
    And I navigate to shopping bag page
    Then I should see the My wallet section displayed on shopping bag page
    And I should see the offers in wallet section of shopping bag page

  @project_BT @domain_purchase_and_delivery @scenario10 @manual
  Scenario: Verify user is displayed with the wallet offers in My wallet section bag, when user is having ST and BT items
    Given I visit the website as a registered user having saved offers in wallet
    When I add a big ticket "BT_furniture and ONHAND" product to bag
    And I add an "available and orderable" product to my bag
    And I navigate to shopping bag page
    Then I should see the My wallet section displayed on shopping bag page
    And I should see the offers in wallet section of shopping bag page
    When I apply the wallet offer
    Then I verify that the offer is applied only to ST items

  @project_BT @domain_purchase_and_delivery @scenario11 @manual
  Scenario Outline: Verify the "Excludes furniture and mattresses" text in Pick up in store section when user have ST and BT items in shopping bag page
    Given I visit the web site as a <user_type> user
    When I add a big ticket "BT_furniture and ONHAND" product to bag
    And I add an "available and orderable" product to my bag
    When I navigate to shopping bag page
    Then I should see the "Excludes furniture and mattresses" text in Pick up in store section
    Examples:
      | user_type  |
      | guest      |
      | registered |

  @project_BT @domain_purchase_and_delivery @scenario12 @manual
  Scenario Outline: Verify the "Excludes furniture and mattresses" text in Pick up in store section when user have only ST items in shopping bag page
    Given I visit the web site as a <user_type> user
    And I add a "available and orderable" product to my bag
    When I navigate to shopping bag page
    Then I should see the "Excludes furniture and mattresses" text in Pick up in store section
    Examples:
      | user_type  |
      | guest      |
      | registered |

  @project_BT @domain_purchase_and_delivery @scenario13 @manual
  Scenario Outline: Verify the pick-up in store section is not displayed, when user is having ST and BT items in bag and user removes or moves the ST item from bag or list
    Given I visit the web site as a <user_type> user
    When I add a big ticket "BT_furniture and ONHAND" product to bag
    And I add an "available and orderable" product to my bag
    And I navigate to shopping bag page
    When I click on <link_type> link for <product_type> item
    Then I <display> see the Pick-up in store section
    Examples:
      | user_type  | link_type    | product_type | display    |
      | guest      | remove       | normal       | should not |
      | registered | move to list | normal       | should not |
      | guest      | move to list | bigTicket    | should     |
      | registered | remove       | bigTicket    | should     |

  @project_BT @domain_purchase_and_delivery @scenario14 @manual
  Scenario Outline: Verify the pick-up in store section is displayed, when bag contains only BT items, and user adds or moves the ST item to bag or from list
    Given I visit the web site as a <user_type> user
    When I add a big ticket "BT_furniture and ONHAND" product to bag
    And I navigate to shopping bag page
    Then I should not see the Pick Up in store section
    When I <link_type> ST item to bag
    Then I should see the Pick-up in store section
    Examples:
      | user_type  | link_type      |
      | guest      | add            |
      | registered | move from list |
      | guest      | add            |
      | registered | move from list |
