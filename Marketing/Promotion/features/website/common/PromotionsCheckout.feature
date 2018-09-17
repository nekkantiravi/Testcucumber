Feature: Verify promotions

  @domain_marketing
  Scenario Outline: Verify promotions applied in mini bag matches with shopping bag
    Given I visit the web site as a guest user
    When I navigate to the PDP page of an available product with the promotion id: <promo_id>
    And I verify the promotion on the pdp page
    And I activate the promotion by adding the correct quantity of the product to the bag and checkout
    Then The promotion is active on the shopping bag page
    When I checkout until I reach the "shipping" page as a "guest" user
    Then I should see promotions applied in mini bag matches with shopping bag
    When I continue checking out until I reach the "payment" page
    Then I should see promotions applied in mini bag matches with shopping bag
    When I continue checking out until I reach the "order review" page
    Then I should see promotions applied in mini bag matches with shopping bag
    And I continue checking out until I reach the "order confirmation" page

  Examples:
    | promo_id |

  @wip @domain_marketing
  Scenario Outline: Verify a specific promotion is applied on the shipping page
    Given I visit the web site as a guest user
    When I navigate to the PDP page of an available product with the promotion id: <promo_id>
    And I activate the promotion by adding the correct quantity of the product to the bag and checkout
    When I checkout until I reach the "shipping" page as a "guest" user
    Then The promotion is active on the shipping page

  Examples:
    | promo_id |

  @wip @domain_marketing
  Scenario Outline: Verify a specific promotion is not applied on the shipping page when the threshold triggers are not met
    Given I visit the web site as a guest user
    When I navigate to the PDP page of an available product with the promotion id: <promo_id>
    And I fail to activate the promotion by adding less than the correct quantity of the product to the bag and checkout
    When I checkout until I reach the "shipping" page as a "guest" user
    Then The promotion is not active on the shipping page

  Examples:
    | promo_id |

  @wip @domain_marketing
  Scenario Outline: Verify a specific promotion is not applied for non-qualifying products on the shipping page
    Given I visit the web site as a guest user
    When I navigate to the PDP page of an non-qualifying product with the promotion id: <promo_id>
    And I activate the promotion by adding the correct quantity of the product to the bag and checkout
    When I checkout until I reach the "shipping" page as a "guest" user
    Then The promotion is not active on the shipping page

  Examples:
    | promo_id |

  @wip @domain_marketing
  Scenario Outline: Verify a specific promotion is not applied for excluded products on the shipping page
    Given I visit the web site as a guest user
    When I navigate to the PDP page of an excluded product with the promotion id: <promo_id>
    And I activate the promotion by adding the correct quantity of the product to the bag and checkout
    When I checkout until I reach the "shipping" page as a "guest" user
    Then The promotion is not active on the shipping page

  Examples:
    | promo_id |

  @wip @domain_marketing
  Scenario Outline: Verify a random promotion is applied on the shipping page
    Given I visit the web site as a guest user
    When I navigate to the PDP page of an available product with a promotion type "<promo_type>" with working gift operation "true"
    And I activate the promotion by adding the correct quantity of the product to the bag and checkout
    When I checkout until I reach the "shipping" page as a "guest" user
    Then The promotion is active on the shipping page

  Examples:
    | promo_type |

  @wip @domain_marketing
  Scenario Outline: Verify a random promotion is not applied on the shipping page for non-qualifying products
    Given I visit the web site as a guest user
    When I navigate to the PDP page of an non-qualifying product with a promotion type "<promo_type>" with working gift operation "true"
    And I activate the promotion by adding the correct quantity of the product to the bag and checkout
    When I checkout until I reach the "shipping" page as a "guest" user
    Then The promotion is not active on the shipping page

  Examples:
    | promo_type |
