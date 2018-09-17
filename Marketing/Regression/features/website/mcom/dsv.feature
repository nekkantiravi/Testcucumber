Feature: DSV Scenarios

  @dsv_desktop_sev1 @launch_call @regression @feature_promotions @Marketing_CBT
  Scenario: Verify SUPC Promo code
    Given I visit the web site as a guest user
    When I create a new profile
    When I navigate to My Wallet page from My Account page
    And I retrieve the sign-up SUPC
    And I add a "available and supc_eligible" product to my bag
    When I navigate to shopping bag page from add to bag page
    Then I verify the sign-up SUPC on the checkout page
    # Notes:
    # Open the email client create profile successful email should contain SUPC promo code
    # Description: Verify SUPC Promo code
    # Steps:
    # 1. Navigate to macys.com.
    # 2. Create an macys account with valid email address.
    # 3. Verify the SUPC Promo code.
    #
    # Expected Results:
    # 1. Macys homepage should display.
    # 2. macys account should create and the confirmation email should be sent to the email address mentioned.
    # 3. SUPC Promo code should be displayed in the email.

  @dsv_desktop_sev1 @artifact_navapp @domain_marketing @use_regression @mode_domestic @Marketing_CBT
  Scenario: Verify all Home Page links lead to valid destinations
    Given I visit the web site as a guest user
    Then I verify that every link on the home page leads to a valid destination

  @domain_marketing @Marketing_CBT
  Scenario: Verify registered user is able to add usl id manually on payment page
    Given I visit the web site as a registered user
    And I add an "available" product to my bag
    When I checkout until I reach the shipping & payment page as a "signed in" user
    Then I should be able to add below USL ID on payment page:
      | usl_id_type         |
      | prod_fully_enrolled |


  Scenario: Verify guest user is able to add usl id manually on payment page
    Given I visit the web site as a guest user
    And I add an "available" product to my bag
    When I checkout until I reach the "responsive_payment_guest_section" page as a "guest" user
    Then I should be able to add below USL ID on payment page:
      | usl_id_type         |
      | prod_fully_enrolled |


  Scenario: As a signed in user on prod, I should see USL information on checkout
   # Given I visit the web site as a guest user
   # And I sign-in to my existing profile if in prod else I create a new profile
    Given I visit the web site as a Plenti user with a "prod_fully_enrolled" status
    When I add an "orderable" product to my bag
    And I checkout until I reach the "shipping & payment" page as a "signed in" user
    Then I should see USL ID linked on payment page