Feature: Digital Product XAPi scenarios with wiremock

  @wiremock_service
  Scenario: Verify pdp-xapi service structure for registry, store availability, color only upc,
  two tier price with on sale and rebates promo
    Given I call wiremock pdp-xapi service for "1310" product
    And I verify the registry flag
    And I verify the store product availability
    Then I verify the color upc details
    And I verify the price details
    And I verify the on sale details
    And I verify the special badges
    And I verify the bullet links
    And stop the wiremock

  @wiremock_service
  Scenario Outline: Verify pdp-xapi service structure for member products
    Given I call wiremock pdp-xapi service for "<PID>" product
    Then I verify pdp-xapi service structure
    And stop the wiremock
    Examples:
      |PID       |
      |1310      |
      |4866709   |

  @wiremock_service
  Scenario Outline: Verify pdp-xapi service structure for standard products
    Given I call wiremock pdp-xapi service for "<PID>" product
    Then I verify pdp-xapi service structure
    And stop the wiremock
    Examples:
      |PID       |
      #|4945678   |
      |4811007   |
      |2900353   |
      |1518404   |
      |2897595   |
      |2970441   |
      |4483359   |
      |2351352   |
      #|4369286   |
      |1103208   |

  @wiremock_service
  Scenario Outline: Verify pdp-xapi service structure for color, size, two tier price standard products
    Given I call wiremock pdp-xapi service for "<PID>" product
    Then I verify the color size interaction and size chart details
    And I verify the price details
    And stop the wiremock
    Examples:
      |PID       |
      |4811007   |

  @wiremock_service
  Scenario Outline: Verify pdp-xapi service structure for registry single special badge, regular price standard products
    Given I call wiremock pdp-xapi service for "<PID>" product
    And I verify the registry flag
    And I verify the price details
    And I verify the special badges
    And stop the wiremock
    Examples:
      |PID       |
      |2900353   |

  @wiremock_service1 @wip
  Scenario Outline: Verify pdp-xapi service structure for three tier price, price type text, bullet links, color and type upc
    Given I call wiremock pdp-xapi service for "<PID>" product
    Then I verify pdp-xapi service structure
    And I verify the price details
    And I verify the on sale details
    And I verify the price type text
    And stop the wiremock
    Examples:
      |PID       |
      |1101386   |

  @wiremock_service
  Scenario Outline: Verify pdp-xapi service structure for egift cards
    Given I call wiremock pdp-xapi service for "<PID>" product
    Then I verify the gift card product
    Then I verify pdp-xapi service structure
    And I verify the price details
    And stop the wiremock
    Examples:
      |PID       |
      |4391437   |

  @wiremock_service
  Scenario Outline: Verify pdp-xapi service structure for video ids
    Given I call wiremock pdp-xapi service for "<PID>" product
    Then I verify the video id
    Then I verify pdp-xapi service structure
    And stop the wiremock
    Examples:
      |PID       |
      |77589     |

  @wiremock_service
  Scenario Outline: Verify pdp-xapi service structure for low availability
    Given I call wiremock pdp-xapi service for "<PID>" product
    Then I verify the low availability
    And stop the wiremock
    Examples:
      |PID       |
      |4811007   |

  @wiremock_service1 @wip
  Scenario Outline: Verify pdp-xapi service structure for chanel
    Given I call wiremock pdp-xapi service for "<PID>" product
    Then I verify the chanel product
    And I verify the price details
    And I verify the member flag
    And stop the wiremock
    Examples:
      |PID       |
      |4758667   |

  @wiremock_service11
  Scenario: Verify pdp-xapi service structure for bazaarvoice reviews
    Given I call wiremock pdp-xapi reviews service for "1103208" product
    Then I verify pdp-xapi reviews structure
    And stop the wiremock

  @wiremock_service11
  Scenario: Verify pdp-xapi service status for bazaarvoice reviews submitted
    Given I call wiremock pdp-xapi reviews feedback POST service for product "1103208" with a review content id of "299560"
    Then I verify pdp-xapi reviews feedback response structure
    And stop the wiremock

  @wiremock_service
  Scenario Outline: Verify pdp-xapi service product data being sent to Tealium for a single Item product, no big ticket, no pricing state,
  no gwp, no pwp, no true fit size, with video
    Given I call wiremock pdp-xapi service for "<PID>" product
    Then I verify is single item product
    And I verify is not big ticket
    And I verify product id is not empty
    And I verify product_original_price is not empty
    And I verify product_price is not empty
    And I verify product_pricing_state is empty string
    And I verify product_rating is not empty
    And I verify product name is not empty
    And I verify product_reviews is not empty
    And I verify there is video
    And I verify product_gwp_available is empty
    And I verify product_pwp_available is empty
    And I verify there is not true_fit_size
    And stop the wiremock
    Examples:
      |PID      |
      |77589    |




  @wiremock_service
  Scenario Outline: Verify pdp-xapi service product data being sent to Tealium for a single Item product, no big ticket, no gwp, no pwp,
  with pricing state, video and true fit size
    Given I call wiremock pdp-xapi service for "<PID>" product
    Then I verify is single item product
    And I verify is not big ticket
    And I verify product id is not empty
    And I verify product_original_price is not empty
    And I verify product_price is not empty
    And I verify product_pricing_state is not empty
    And I verify product_rating is not empty
    And I verify product name is not empty
    And I verify product_reviews is not empty
    And I verify there is video
    And I verify product_gwp_available is empty
    And I verify product_pwp_available is empty
    And I verify there is true_fit_size
    And stop the wiremock
    Examples:
      |PID      |
      |1103208  |
      |1518404  |


    @wiremock_service
    Scenario Outline: Verify pdp-xapi service product data being sent to Tealium for single Item, no pricing state, no gwp, no pwp,
    with video and true fit size
      Given I call wiremock pdp-xapi service for "<PID>" product
      Then I verify is single item product
      And I verify is not big ticket
      And I verify product id is not empty
      And I verify there is video
      And I verify product_original_price is not empty
      And I verify product_price is not empty
      And I verify product_pricing_state is empty string
      And I verify product_rating is not empty
      And I verify product name is not empty
      And I verify product_reviews is not empty
      And I verify product_gwp_available is empty
      And I verify product_pwp_available is empty
      And I verify there is true_fit_size
      And stop the wiremock
      Examples:
        |PID      |
        |2351352  |


    @wiremock_service
    Scenario Outline: Verify pdp-xapi service product data being sent to Tealium for single item no video, no gwp, no pwp,
    no true fit size, no pricing state
      Given I call wiremock pdp-xapi service for "<PID>" product
      Then I verify is single item product
      And I verify is not big ticket
      And I verify product id is not empty
      And I verify product_original_price is not empty
      And I verify product_price is not empty
      And I verify product_pricing_state is empty string
      And I verify there is no video
      And I verify product_rating is not empty
      And I verify product name is not empty
      And I verify product_reviews is not empty
      And I verify product_gwp_available is empty
      And I verify product_pwp_available is empty
      And I verify there is not true_fit_size
      And stop the wiremock
      Examples:
        |PID      |
        |1310     |
        |4391437  |


    @wiremock_service
    Scenario Outline: Verify pdp-xapi service product data being sent to Tealium for single item no pricing state, no video,
      no gwp, no pwp, with true fit size
      Given I call wiremock pdp-xapi service for "<PID>" product
      Then I verify is single item product
      And I verify is not big ticket
      And I verify product id is not empty
      And I verify product_original_price is not empty
      And I verify product_price is not empty
      And I verify product_pricing_state is empty string
      And I verify there is no video
      And I verify product_rating is not empty
      And I verify product name is not empty
      And I verify product_reviews is not empty
      And I verify product_gwp_available is empty
      And I verify product_pwp_available is empty
      And I verify there is true_fit_size
      And stop the wiremock
      Examples:
        |PID      |
        |2897595  |
        |2970441  |
        |4483359  |
        |4811007  |

    @wiremock_service
    Scenario Outline: Verify pdp-xapi service product data being sent to Tealium for single item no pricing state, no gwp,
    no pwp, no true fit size, with video
      Given I call wiremock pdp-xapi service for "<PID>" product
      Then I verify is single item product
      And I verify is not big ticket
      And I verify product id is not empty
      And I verify product_original_price is not empty
      And I verify product_price is not empty
      And I verify product_pricing_state is empty string
      And I verify there is video
      And I verify product_rating is not empty
      And I verify product name is not empty
      And I verify product_reviews is not empty
      And I verify product_gwp_available is empty
      And I verify product_pwp_available is empty
      And I verify there is not true_fit_size
      And stop the wiremock
      Examples:
        |PID      |
        |2900353  |
        |4866709  |

  @wiremock_service11
  Scenario: Verify pdp-xapi service status for a Master product
    Given I call wiremock pdp-xapi service for Master "5711669" product with member "5611944,5611945" products
    Then I verify pdp-xapi Master product response structure
    And stop the wiremock





