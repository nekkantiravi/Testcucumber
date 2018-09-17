Feature: DSV checkout scenarios for Purchase and Delivery

  @domain_purchase_and_delivery @dsv_desktop_sev1 @dsv_desktop_sev2 @ifs @akamai_waf
  Scenario Outline: Verify user is able to Checkout until order review page
    Given I visit the web site as a <user_type> user
    When I add an "available and orderable" product to my bag
    Then I checkout until I reach the order review page as a "<user>" user
    Then I verify basic attributes of the Order Review Page
    Examples:
      | user_type  | user      |
      | guest      | guest     |
      | registered | signed in |

  @domain_purchase_and_delivery @dsv_desktop_sev1 @ifs
  Scenario: Verify BOPs Checkout until Order Review
    Given I visit the web site as a guest user
    When I add an "available_bops and orderable" product to my bag and select checkout
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the shipping page as a "bops" user
    Then I should see bops shipping in order summary section
    When I checkout until I reach the payment page as a "bops" user
    Then I should see bops shipping in order summary section
    When I checkout until I reach the order review page as a "bops" user
    Then I should see bops shipping in order summary section

  @domain_purchase_and_delivery @dsv_desktop_sev1 @ifs @akamai_waf
  Scenario: Verify Checkout for registry products with Sign in
    Given I visit the web site as a registry user
    When I add registry product to my bag from BVR page
    Then I should see registry item in shopping bag page
    And I checkout until I reach the shipping & payment page as an "signed in" user

  @domain_purchase_and_delivery @dsv_desktop_sev1 @ifs
  Scenario: As a customer, when I add a electronic gift card to my bag I should see that surcharge appears
    Given I visit the web site as a guest user
    When I add an "electronic_gift_card and orderable" product to my bag and select checkout
    Then I should see shipping surcharge fee on shopping bag page

  @domain_purchase_and_delivery @dsv_desktop_sev1 @ifs
  Scenario: Verify Iship checkout for dsv
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "India"
    And I close the welcome mat if it's visible
    And I mouse over "WOMEN" category from top navigation
    And I select "Dresses" subcategory from flyout menu
    And I select a random member product
    Then I should be redirected to PDP page
    Then I verify the iship specific attributes of PDP Page
    When I add product to my bag from standard PDP Page
    And I navigate to shopping bag page from add to bag page
    And I remove all items from the shopping bag
    And I add a "available and orderable and iship_eligible" product to my bag and continue checkout
    And I checkout until I reach the shipping & payment page as a "iship" user from "India"
    Then I verify the basic attributes in Border Free Page

  @dsv_sev2_dryrun
  Scenario: Verify whether Place Order button is enabled when both Shipping & Payment section are showing in the Summary state
    Given I visit the web site as a guest user
    And I add a "available and orderable" product to my bag
    And I checkout until I reach the order review page as a "guest" user
    And I should see place order button in enabled state