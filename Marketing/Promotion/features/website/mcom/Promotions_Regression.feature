Feature: Test Promotions Regression Cases

  @domain_marketing @use_regression
  Scenario: Verify a registered user can place a Site-Wide PWP in the shopping bag and checkout
    Given I visit the web site as a registered user
    And I add "PWP" product to my bag
    And I verify the promotion on the PDP page
    And I select options to trigger the promotion and add to bag
    And I verify the offer description on the add to bag overlay
    And I navigate to shopping bag page from add to bag overlay
    Then I verify the promotion on the shopping bag page
    And I checkout until I reach the shipping & payment page as a "responsive_signed in" user
    Then I verify the promotion on the order details panel
   # Then I verify the price on the checkout page

  @domain_marketing @use_regression
  Scenario: Verify a guest user can place a Site-Wide PWP in the shopping bag and checkout
    Given I visit the web site as a guest user
    And I add "PWP" product to my bag
    And I verify the promotion on the PDP page
    And I select options to trigger the promotion and add to bag
    And I verify the offer description on the add to bag overlay
    And I navigate to shopping bag page from add to bag overlay
    Then I verify the promotion on the shopping bag page
    And I checkout until I reach the shipping & payment page as a "responsive_guest" user
    Then I verify the promotion on the order details panel
   # Then I verify the price on the checkout page

  Scenario: Verify a registered user can place a Regular PWP in the shopping bag and checkout
    Given I visit the web site as a registered user
    When I navigate to the PDP of a product with a PWP promotion
    And I verify the promotion on the PDP
    And I select options to trigger the promotion and add to bag
    And I verify the offer description on the add to bag overlay
    And I checkout to the shopping bag page
    Then I verify the promotion on the shopping bag page
    When I checkout until I reach the "shipping and payment" page as a "signed in" user
    Then I verify the promotion on the order details panel
    And I continue checking out until I reach the "order review" page
    Then I verify the promotion on the order details panel
    And I continue checking out until I reach the "order confirmation" page
    Then I verify the price on the order confirmation page

  @wip @domain_marketing @use_regression
  Scenario: Verify a registered user can place a Dollar Off Order promotion item in the shopping bag and checkout
    Given I visit the web site as a registered user
    And I add "dollar_off" product to my bag
    And I select options to trigger the promotion and add to bag
    And I navigate to shopping bag page from add to bag overlay
    Then I enter the promo code in promotion field and click on apply
    Then I verify the promotion on the shopping bag page
    And I checkout until I reach the shipping & payment page as a "responsive_signed in" user
    Then I verify the promotion on the order details panel
   # Then I verify the price on the checkout page


  @wip @domain_marketing @use_regression
  Scenario: Verify a guest user can place a Dollar Off Order promotion item in the shopping bag and checkout
    Given I visit the web site as a guest user
    And I add "dollar_off" product to my bag
    And I select options to trigger the promotion and add to bag
    And I navigate to shopping bag page from add to bag overlay
    Then I enter the promo code in promotion field and click on apply
    Then I verify the promotion on the shopping bag page
    And I checkout until I reach the shipping & payment page as a "responsive_guest" user
    Then I verify the promotion on the order details panel
   # Then I verify the price on the checkout page

  @domain_marketing @use_regression
  Scenario: Verify a registered user can place a Promotional Pricing promotion item in the shopping bag and checkout
    Given I visit the web site as a registered user
    And I add "promotional_pricing" product to my bag
    And I verify the promotion on the PDP page
    And I select options to trigger the promotion and add to bag
    And I verify the offer description on the add to bag overlay
    And I navigate to shopping bag page from add to bag overlay
    Then I verify the promotion on the shopping bag page
    And I checkout until I reach the shipping & payment page as a "responsive_signed in" user
    Then I verify the promotion on the order details panel
   # Then I verify the price on the checkout page

  @domain_marketing @use_regression
  Scenario: Verify a guest user can place a Promotional Pricing promotion item in the shopping bag and checkout
    Given I visit the web site as a guest user
    And I add "promotional_pricing" product to my bag
    And I verify the promotion on the PDP page
    And I select options to trigger the promotion and add to bag
    And I verify the offer description on the add to bag overlay
    And I navigate to shopping bag page from add to bag overlay
    Then I verify the promotion on the shopping bag page
    And I checkout until I reach the shipping & payment page as a "responsive_guest" user
    Then I verify the promotion on the order details panel
   # Then I verify the price on the checkout page

  @domain_marketing @use_regression
  Scenario: Verify a registered user can place a Percent Off Order promotion item in the shopping bag and checkout
    Given I visit the web site as a registered user
    And I add "percent_off" product to my bag
    And I verify the promotion on the PDP page
    And I select options to trigger the promotion and add to bag
    And I verify the offer description on the add to bag overlay
    And I navigate to shopping bag page from add to bag page
    And I adjust qty to make the promotion available
    Then I enter the promo code in promotion field and click on apply
    Then I verify the promotion on the shopping bag page
    And I checkout until I reach the shipping & payment page as a "responsive_signed in" user
    Then I verify the promotion on the order details panel
   # Then I verify the price on the checkout page

  @domain_marketing @use_regression
  Scenario: Verify a guest user can place a Percent Off Order promotion item in the shopping bag and checkout
    Given I visit the web site as a guest user
    And I add "percent_off" product to my bag
    And I verify the promotion on the PDP page
    And I select options to trigger the promotion and add to bag
    And I verify the offer description on the add to bag overlay
    And I navigate to shopping bag page from add to bag page
    And I adjust qty to make the promotion available
    Then I enter the promo code in promotion field and click on apply
    Then I verify the promotion on the shopping bag page
    And I checkout until I reach the shipping & payment page as a "responsive_signed in" user
    Then I verify the promotion on the order details panel
   # Then I verify the price on the checkout page

  Scenario: Verify a guest user can place a Percent Off Order promotion item with promo code in the shopping bag and checkout
    Given I visit the web site as a guest user
    When I navigate to the PDP of a product with a Percent_Off_Order promotion with a promo code
    And I verify the promotion on the PDP
    And I select options to trigger the promotion and add to bag
    And I verify the offer description on the add to bag overlay
    And I checkout to the shopping bag page
    Then I verify the promotion on the shopping bag page
    When I checkout until I reach the "shipping" page as a "guest" user
    Then I verify the promotion on the order details panel
    When I checkout until I reach the "order review" page as a "guest" user
    Then I verify the promotion on the order details panel
    When I checkout until I reach the "order confirmation" page as a "guest" user
    Then I verify the price on the order confirmation page

  Scenario: Verify a registered user can place a Bundled GWP promotion item in the shopping bag and checkout
    Given I visit the web site as a registered user
    When I navigate to the PDP of a product with a Bundled_GWP promotion
    And I verify the promotion on the PDP
    And I select options to trigger the promotion and add to bag
    And I verify the offer description on the add to bag overlay
    And I checkout to the shopping bag page
    Then I verify the promotion on the shopping bag page
    When I checkout until I reach the "shipping and payment" page as a "signed in" user
    Then I verify the promotion on the order details panel
    And I continue checking out until I reach the "order review" page
    Then I verify the promotion on the order details panel
    And I continue checking out until I reach the "order confirmation" page
    Then I verify the price on the order confirmation page

  @domain_marketing @use_regression
  Scenario: Verify a registered user can place a Threshold GWP promotion item in the shopping bag and checkout
    Given I visit the web site as a registered user
    And I add "GWP" product to my bag
    And I verify the promotion on the PDP page
    And I select options to trigger the promotion and add to bag
    And I verify the offer description on the add to bag overlay
    And I navigate to shopping bag page from add to bag overlay
    Then I verify the promotion on the shopping bag page
    And I checkout until I reach the shipping & payment page as a "responsive_signed in" user
    Then I verify the promotion on the order details panel
   # Then I verify the price on the checkout page

  @domain_marketing @use_regression
  Scenario: Verify a guest user can place a Threshold GWP promotion item in the shopping bag and checkout
    Given I visit the web site as a guest user
    And I add "GWP" product to my bag
    And I verify the promotion on the PDP page
    And I select options to trigger the promotion and add to bag
    And I verify the offer description on the add to bag overlay
    And I navigate to shopping bag page from add to bag overlay
    Then I verify the promotion on the shopping bag page
    And I checkout until I reach the shipping & payment page as a "responsive_guest" user
    Then I verify the promotion on the order details panel
   # Then I verify the price on the checkout page

  Scenario: Verify a registered user can place a Threshold GWP promotion item with color/size/type options in the shopping bag and checkout
    Given I visit the web site as a registered user
    When I navigate to the PDP of a product with a Threshold_GWP promotion with color size and type options
    And I verify the promotion on the PDP page
    And I select options to trigger the promotion and add to bag
    And I verify the offer description on the add to bag overlay
    And I checkout to the shopping bag page
    Then I verify the promotion on the shopping bag page
    When I checkout until I reach the "shipping and payment" page as a "signed in" user
    Then I verify the promotion on the order details panel
    And I continue checking out until I reach the "order review" page
    Then I verify the promotion on the order details panel
    And I continue checking out until I reach the "order confirmation" page
    Then I verify the price on the order confirmation page

  @domain_marketing @use_regression @Marketing_CBT
  Scenario: Verify specific promotion in shopping bag and checkout page
    Given I visit the web site as a registered user
    And I verify specific promotion in shopping bag
      | Promotion                      |
      | Dollar Off without promocode   |
      | Qualified To Quantity          |
      | Lowest Price                   |
      | Flat Fee                       |
      | Free Shipping                  |
