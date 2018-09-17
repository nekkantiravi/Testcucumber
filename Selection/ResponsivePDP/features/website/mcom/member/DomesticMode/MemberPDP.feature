#---------------------------------------------------
# Brand         : MCOM
# Author        : Bruce Shad
# Date Created	: Oct.13,2017
#---------------------------------------------------

Feature: MemberPDP Validation & Verification Scenarios

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp
  Scenario Outline: Verify A2B functionality & A2B drawer on Member PDP Site mode
    Given I visit the home page on <device> as a guest user
    When I navigate directly to "member" PDP site mode on <device>
    Then I verify the basic elements on member PDP site mode on <device>
    And I verify product prices on member PDP site mode on <device>
    And I select random quantity on member PDP site mode on <device>
    And I click the A2B button on member PDP site mode on <device>
    Then I verify the AddToBag drawer as the "member" product being added in site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp
  Scenario Outline: Verify A2B functionality & A2B drawer on MemberWithColor PDP Site mode
    Given I visit the home page on <device> as a guest user
    When I navigate directly to "memberWithColor" PDP site mode on <device>
    Then I verify the basic elements on member PDP site mode on <device>
    And I verify product prices on member PDP site mode on <device>
    And I randomly select an available color on member PDP site mode on <device>
    And I select random quantity on member PDP site mode on <device>
    And I click the A2B button on member PDP site mode on <device>
    Then I verify the AddToBag drawer as the "memberWithColor" product being added in site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp
  Scenario Outline: Verify A2B functionality & A2B drawer on MemberWithSizeColor PDP Site mode
    Given I visit the home page on <device> as a guest user
    When I navigate directly to "memberWithSizeColor" PDP site mode on <device>
    Then I verify the basic elements on member PDP site mode on <device>
    And I verify product prices on member PDP site mode on <device>
    And I randomly select an available color on member PDP site mode on <device>
    And I randomly select an available size on member PDP site mode on <device>
    And I select random quantity on member PDP site mode on <device>
    And I click the A2B button on member PDP site mode on <device>
    Then I verify the AddToBag drawer as the "memberWithSizeColor" product being added in site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp
  Scenario Outline: Verify A2B functionality & ShoppingBag page on Member PDP Site mode
    Given I visit the home page on <device> as a guest user
    When I navigate directly to "member" PDP site mode on <device>
    Then I verify the basic elements on member PDP site mode on <device>
    And I verify product prices on member PDP site mode on <device>
    And I select random quantity on member PDP site mode on <device>
    And I click the A2B button on member PDP site mode on <device>
    And I click the Checkout button on member PDP site mode on <device>
    Then I verify the ShoppingBag page as the "member" product being added in site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp
  Scenario Outline: Verify A2B functionality & ShoppingBag page on MemberWithColor PDP Site mode
    Given I visit the home page on <device> as a guest user
    When I navigate directly to "memberWithColor" PDP site mode on <device>
    Then I verify the basic elements on member PDP site mode on <device>
    And I verify product prices on member PDP site mode on <device>
    And I randomly select an available color on member PDP site mode on <device>
    And I select random quantity on member PDP site mode on <device>
    And I click the A2B button on member PDP site mode on <device>
    And I click the Checkout button on member PDP site mode on <device>
    Then I verify the ShoppingBag page as the "memberWithColor" product being added in site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp
  Scenario Outline: Verify A2B functionality & ShoppingBag page on MemberWithSizeColor PDP Site mode
    Given I visit the home page on <device> as a guest user
    When I navigate directly to "memberWithSizeColor" PDP site mode on <device>
    Then I verify the basic elements on member PDP site mode on <device>
    And I verify product prices on member PDP site mode on <device>
    And I randomly select an available color on member PDP site mode on <device>
    And I randomly select an available size on member PDP site mode on <device>
    And I select random quantity on member PDP site mode on <device>
    And I click the A2B button on member PDP site mode on <device>
    And I click the Checkout button on member PDP site mode on <device>
    Then I verify the ShoppingBag page as the "memberWithSizeColor" product being added in site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp
  Scenario: Verify A2B functionality & QuickBag overlay on Member PDP Site mode
    Given I visit the home page on desktop as a guest user
    When I navigate directly to "member" PDP site mode on desktop
    Then I verify the basic elements on member PDP site mode on desktop
    And I verify product prices on member PDP site mode on desktop
    And I select random quantity on member PDP site mode on desktop
    And I click the A2B button on member PDP site mode on desktop
    And I click the ContinueShopping button on member PDP site mode on desktop
    Then I verify the elements on QuickBag overlay on desktop
    And I click the Remove button on QuickBag overlay on desktop
    Then I verify the QB count is updating with 0 quantity items in bag on desktop

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp
  Scenario: Verify A2B functionality & QuickBag overlay on MemberWithColor PDP Site mode
    Given I visit the home page on desktop as a guest user
    When I navigate directly to "memberWithColor" PDP site mode on desktop
    Then I verify the basic elements on member PDP site mode on desktop
    And I verify product prices on member PDP site mode on desktop
    And I randomly select an available color on member PDP site mode on desktop
    And I select random quantity on member PDP site mode on desktop
    And I click the A2B button on member PDP site mode on desktop
    And I click the ContinueShopping button on member PDP site mode on desktop
    Then I verify the elements on QuickBag overlay on desktop
    And I click the Remove button on QuickBag overlay on desktop
    Then I verify the QB count is updating with 0 quantity items in bag on desktop

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp
  Scenario: Verify A2B functionality & QuickBag overlay on MemberWithSizeColor PDP Site mode
    Given I visit the home page on desktop as a guest user
    When I navigate directly to "memberWithSizeColor" PDP site mode on desktop
    Then I verify the basic elements on member PDP site mode on desktop
    And I verify product prices on member PDP site mode on desktop
    And I randomly select an available color on member PDP site mode on desktop
    And I randomly select an available size on member PDP site mode on desktop
    And I select random quantity on member PDP site mode on desktop
    And I click the A2B button on member PDP site mode on desktop
    And I click the ContinueShopping button on member PDP site mode on desktop
    Then I verify the elements on QuickBag overlay on desktop
    And I click the Remove button on QuickBag overlay on desktop
    Then I verify the QB count is updating with 0 quantity items in bag on desktop

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp
  Scenario: Verify Product Image link on QuickBag overlay navigates to Member PDP Site mode
    Given I visit the home page on desktop as a guest user
    When I navigate directly to "member" PDP site mode on desktop
    Then I verify the QB count is updating with 0 quantity items in bag on desktop
    And I select maximum quantity on member PDP site mode on desktop
    And I click the A2B button on member PDP site mode on desktop
    And I click the ContinueShopping button on member PDP site mode on desktop
    And I navigate to member PDP site mode with PID "77589" on desktop
    Then I verify the QB count is updating with maximum quantity items in bag on desktop
    And I click the Product Image on QuickBag overlay on desktop
    Then I verify the navigation to member PDP site mode on desktop

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp
  Scenario Outline: Verify QuickBag Icon link on Member PDP Site mode
    Given I visit the home page on <device> as a guest user
    When I navigate directly to "member" PDP site mode on <device>
    Then I verify the basic elements on member PDP site mode on <device>
    And I verify product prices on member PDP site mode on <device>
    And I select random quantity on member PDP site mode on <device>
    And I click the A2B button on member PDP site mode on <device>
    And I click the ContinueShopping button on member PDP site mode on <device>
    And I click the QuickBag Icon on QuickBag overlay on <device>
    Then I verify the ShoppingBag page as the "member" product being added in site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp
  Scenario Outline: Verify that QuickBag count updates on Member PDP Site mode
    Given I visit the home page on <device> as a guest user
    When I navigate directly to "member" PDP site mode on <device>
    And I verify the QB count is updating with 0 quantity items in bag on <device>
    And I select random quantity on member PDP site mode on <device>
    And I click the A2B button on member PDP site mode on <device>
    And I click the ContinueShopping button on member PDP site mode on <device>
    Then I verify the QB count is updating with random quantity items in bag on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp
  Scenario Outline: Verify Product Image link on ShoppingBag page navigates to Member PDP Site mode
    Given I visit the home page on <device> as a guest user
    When I navigate directly to "member" PDP site mode on <device>
    And I click the A2B button on member PDP site mode on <device>
    And I click the Checkout button on member PDP site mode on <device>
    And I click the Product Image on ShoppingBag page site mode on <device>
    Then I verify the navigation to member PDP site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp
  Scenario Outline: Verify empty bag message & BagID removed on ShoppingBag page Site mode
    Given I visit the home page on <device> as a guest user
    When I navigate directly to "member" PDP site mode on <device>
    And I click the A2B button on member PDP site mode on <device>
    And I click the Checkout button on member PDP site mode on <device>
    And I click the Remove button on ShoppingBag page site mode on <device>
    Then I verify the ShoppingBag page in site mode for bag empty message & BagID removed on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp
  Scenario Outline: Verify error message for unavailable Size&Color combination on Member PDP Site mode
    Given I visit the home page on <device> as a guest user
    When I navigate directly to "memberWithUnavailableSizeColorCombo" PDP site mode on <device>
    And I randomly select an available color on member PDP site mode on <device>
    And I randomly select an unavailable size on member PDP site mode on <device>
    Then I verify the basic elements on member PDP site mode on <device>
    Then I verify the error message "This color/size combination is not available." on member PDP site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp
  Scenario Outline: Verify A2B, Add2List & Add2Registry select size error message on member PDP Site mode
    Given I visit the home page on <device> as a guest user
    When I navigate to member PDP site mode with PID "2274957" on <device>
    And I select random quantity on member PDP site mode on <device>
    And I click the A2B button on member PDP site mode on <device>
    Then I verify the error message "Please select a size." on member PDP site mode on <device>
    And I click the A2L button on member PDP site mode on <device>
    Then I verify the error message "Please select a size." on member PDP site mode on <device>
    And I click the A2R button on member PDP site mode on <device>
    Then I verify the error message "Please select a size." on member PDP site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp
  Scenario Outline: Verify A2B limit reached error message on member PDP Site mode
    Given I visit the home page on <device> as a guest user
    When I navigate directly to "member" PDP site mode on <device>
    And I select default quantity on member PDP site mode on <device>
    And I click the A2B button on member PDP site mode on <device>
    And I click the ContinueShopping button on member PDP site mode on <device>
    And I select maximum quantity on member PDP site mode on <device>
    And I click the A2B button on member PDP site mode on <device>
    Then I verify the error message "You've exceeded the quantity limit for this item." on member PDP site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp
  Scenario Outline: Verify A2B limit reached error message for product with size&color on member PDP Site mode
    Given I visit the home page on <device> as a guest user
    When I navigate directly to "memberWithSizeColor" PDP site mode on <device>
    And I select the first available size on member PDP site mode on <device>
    And I select maximum quantity on member PDP site mode on <device>
    And I click the A2B button on member PDP site mode on <device>
    And I click the ContinueShopping button on member PDP site mode on <device>
    And I refresh the browser on <device>
    And I select the first available size on member PDP site mode on <device>
    And I click the A2B button on member PDP site mode on <device>
    Then I verify the error message "You've reached the limit for this item. Please select another size/color." on member PDP site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp
  Scenario Outline: Verify the zoomer & altimages on Member PDP Site mode
    Given I visit the home page on <device> as a guest user
    When I navigate directly to "memberWithAltImages" PDP site mode on <device>
    Then I verify the basic elements on member PDP site mode on <device>
    And I verify the zoomer & altimages on member PDP site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp
  Scenario Outline: Verify product Type feature on member PDP Site mode
    Given I visit the home page on <device> as a guest user
    When I navigate directly to "memberWithTypeColor" PDP site mode on <device>
    Then I verify Type feature & functionality on member PDP site mode on <device>
    And I randomly select an available type on member PDP site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp
  Scenario Outline: Verify Product Not Found & ContinueShopping Back button functionality on Member PDP Site mode
    Given I visit the home page on <device> as a guest user
    When I navigate to member PDP site mode with PID "77589" on <device>
    And I capture the webId on member PDP site mode on <device>
    And I navigate directly to "productNotFound" PDP site mode on <device>
    And I call xAPI service for the product ID
    And I verify "Product Not Found" with xAPI service
    And I click the ContinueShoppingBack button on member PDP site mode on <device>
    Then I verify the navigation to member PDP site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp
  Scenario Outline: Verify Product Details section on Member PDP Site mode
    Given I visit the home page on <device> as a guest user
    When I navigate directly to "member" PDP site mode on <device>
    And I click the Product Details button on member PDP site mode on <device>
    Then I verify elements under Product Details section on member PDP site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp
  Scenario Outline: Verify Pricing Policy in Product Details section on Member PDP Site mode
    Given I visit the home page on <device> as a guest user
    When I navigate directly to "member" PDP site mode on <device>
    And I click the Product Details button on member PDP site mode on <device>
    Then I verify elements under Product Details section on member PDP site mode on <device>
    And I click "Savings based on offering prices, not actual sales" link on member PDP site mode on <device>
    And I verify Pricing Policy overlay on member PDP site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp
  Scenario Outline: Verify Price Details section on Member PDP Site mode
    Given I visit the home page on <device> as a guest user
    When I navigate directly to "member" PDP site mode on <device>
    And I click the Price Details button on member PDP site mode on <device>
    Then  I verify elements under Price Details section on member PDP site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp
  Scenario Outline: Verify navigation of all links under Shipping&Returens section on Member PDP Site mode
    Given I visit the home page on <device> as a guest user
    When I navigate directly to "member" PDP site mode on <device>
    Then I verify the basic elements on member PDP site mode on <device>
    And I click the Shipping & Returns button on member PDP site mode on <device>
    Then I verify the navigation of all links under Shipping&Returns on member PDP site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp
  Scenario Outline: Verify Special Offers section & promotion badges on Member PDP Site mode
    Given I visit the home page on <device> as a guest user
    When I navigate directly to "memberWithPromoBadge" PDP site mode on <device>
    And I call xAPI service for the product ID
    Then I verify pdp-xapi gift with purchase details against promotions service for same product
    And I click the Special Offers button on member PDP site mode on <device>
    Then  I verify special offers should be displayed on member PDP site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp
  Scenario Outline: Verify product with no promotion badges on Member PDP Site mode
    Given I visit the home page on <device> as a guest user
    When I navigate directly to "memberWithoutPromoBadge" PDP site mode on <device>
    Then  I verify special offers should not be displayed on member PDP site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp
  Scenario Outline: Verify Free Shipping message on Member PDP Site mode
    Given I visit the home page on <device> as a guest user
    When I navigate directly to "member" PDP site mode on <device>
    Then I verify the basic elements on member PDP site mode on <device>
    And I click the Shipping & Returns button on member PDP site mode on <device>
    Then I verify the "<message>" message under Shipping&Returns on member PDP site mode on <device>
    Examples:
      |device  |message                                                      |
      |desktop |This item qualifies for Free Shipping with minimum purchase! |
      |mobile  |This item qualifies for Free Shipping with minimum purchase! |
      |tablet  |This item qualifies for Free Shipping with minimum purchase! |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp @wip
  Scenario Outline: Verify Shipping Surcharge Fee message on Member PDP Site mode
    Given I visit the home page on <device> as a guest user
    When I navigate directly to "member" PDP site mode on <device>
    Then I verify the basic elements on member PDP site mode on <device>
    And I click the Shipping & Returns button on member PDP site mode on <device>
    Then I verify the "<message>" message under Shipping&Returns on member PDP site mode on <device>
    Examples:
      |device  |message                |
      |desktop |Shipping Surcharge Fee |
      |mobile  |Shipping Surcharge Fee |
      |tablet  |Shipping Surcharge Fee |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp @wip
  Scenario Outline: Verify availability message & functionality on Member PDP in Site mode
    Given I visit the home page on <device> as a guest user
    When I navigate directly to "member" PDP site mode on <device>
    Then I verify the "Available.Usually ships within x business days." message on member PDP site mode on <device>
    And I verify product prices on member PDP site mode on <device>
    And I click the A2B button on member PDP site mode on <device>
    Then I verify the AddToBag drawer as the "member" product being added in site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp @wip
  Scenario Outline: Verify availability message & functionality for MemberWithSizeColor PDP in Site mode
    Given I visit the home page on <device> as a guest user
    When I navigate directly to "memberWithSizeColor" PDP site mode on <device>
    Then I verify the "Select a color and size above in order to view availability." message on member PDP site mode on <device>
    And I randomly select an available color on member PDP site mode on <device>
    And I randomly select an available size on member PDP site mode on <device>
    And I select random quantity on member PDP site mode on <device>
    Then I verify product prices on member PDP site mode on <device>
    And I verify the "Available.Usually ships within x business days." message on member PDP site mode on <device>
    And I click the A2B button on member PDP site mode on <device>
    Then I verify the AddToBag drawer as the "memberWithSizeColor" product being added in site mode on <device>
    And I click the Checkout button on member PDP site mode on <device>
    Then I verify the ShoppingBag page as the "memberWithSizeColor" product being added in site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp @wip
  Scenario Outline: Verify BOPS functionality on Member PDP in Site mode
    Given I visit the home page on <device> as a guest user
    When I navigate directly to "member" PDP site mode on <device>
    Then I verify BOPS functionality on member PDP site mode on <device>
    And I verify product prices on member PDP site mode on <device>
    And I click the A2B button on member PDP site mode on <device>
    Then I verify the AddToBag drawer as the "member" product being added in site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp @wip
  Scenario Outline: Verify BOPS functionality for memberWithSizeColor PDP in Site mode
    Given I visit the home page on <device> as a guest user
    When I navigate directly to "memberWithSizeColor" PDP site mode on <device>
    Then I verify the "Select a color and size above in order to view availability." message on member PDP site mode on <device>
    And I randomly select an available color on member PDP site mode on <device>
    And I randomly select an available size on member PDP site mode on <device>
    And I select random quantity on member PDP site mode on <device>
    Then I verify BOPS functionality on member PDP site mode on <device>
    And I verify product prices on member PDP site mode on <device>
    And I click the A2B button on member PDP site mode on <device>
    Then I verify the AddToBag drawer as the "memberWithSizeColor" product being added in site mode on <device>
    And I click the Checkout button on member PDP site mode on <device>
    Then I verify the ShoppingBag page as the "memberWithSizeColor" product being added in site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp @wip
  Scenario Outline: Verify the Email social icon on Member PDP Site mode
    Given I visit the home page on <device> as a guest user
    When I navigate directly to "member" PDP site mode on <device>
    Then I verify the Email social icon on member PDP site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp
  Scenario Outline: Verify the Pinterest social icon on Member PDP Site mode
    Given I visit the home page on <device> as a guest user
    When I navigate directly to "member" PDP site mode on <device>
    Then I verify the Pinterest social icon on member PDP site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |
