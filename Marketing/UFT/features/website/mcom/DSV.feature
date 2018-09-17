Feature: DSV scenarios

  @B-85871  @dsv_desktop_sev2
  Scenario: My Account - Rendering my store Section with make this my store
    Given I visit the web site as a guest user
    When I create a new profile
    And I select a random store with zip code in Choose a Preferred Store section
     ####Old myaccount page functionality steps ###
#    Then I should verify that the selected store details are displayed in the my preferred store Section on My Account Page
#    Then I verify the My Account Pages are rendered properly
#      | store locator    |
#      | Store Events     |
#      | personal shopper |
#      | home design      |
#      | visitor discount |
#      | Map/Directions   |

  @B-85871  @dsv_desktop_sev2
  Scenario: Verify that user is able to successfully remove the non star rewards promotions from my wallet for DSV
    Given I visit the web site as a guest user
    When I create a new profile
    And I pick a promo code from deals and promotion page
    And I navigate to My Wallet page from My Account page
    And I add wallet eligible offer manually on wallet page
    Then I should see offer is added to wallet
    When I remove any non star reward offer from the offer list
    Then I should not see the deleted offer in the offer list
    And I should see "Changes saved to your wallet." on top of the wallet page


  @B-85871  @dsv_desktop_sev2 @akamai_waf
  Scenario: Verify store events timing should be in sequential pattern
    Given I am on the Our Stores Events Page as a guest user
    When I enter a zip code and search on our Stores Events Page
    Then I verify date and timing of the event should be displayed in sequential pattern
    # Description: Store events > timing should be in sequential pattern.
    # Steps:
    # 1. Navigate to macys.com
    # 2. Click on footer link "Store events"
    # 3. Enter zip code and search
    #
    # Expected Results:
    # 1. Macys.com home page should display
    # 2. find a store event page should display
    # 3. date and timing of the event should be displayed in sequential pattern
  @B-85871  @dsv_desktop_sev2
  Scenario:Verify Create/Update address with Zip code starting from "0" - My Wallet
    Given I visit the web site as a guest user
    When I create a new profile
    When I navigate to My Wallet page from My Account page
    And I add a credit card to My Wallet with Zip code starting with "0"
    Then I should see added credit card in My Wallet page
    And I "should not" see zip code trimmed

    # Test case steps (MCOM)
    # 1. Navigate to macys.com
    # 2. Sign in with valid credential
    # 3.In My account, Click on My wallet
    # 4. Enter the details under "add/edit credit card information" with (any valid
    # Address with zip code having forward 0)
    # Card type : Macys
    # Card # : 486003962610
    # Address line1 : 10 pascack rd
    # City : township of Washington
    # State : New jersey
    # Zip : 07676
    # And click on save
    # Test case expected result (MCOM)
    # 1. Macys.com home page should display
    # 2. User should be signed in
    # 3. Credit card information should be saved with message "Your Wallet entry was successfully saved" and
    # Forward 0 of zip code should not be trimmed

  @B-85871  @dsv_desktop_sev2
  Scenario: Verify the wallet page redirection functionality from Deals and Promotion page when user is already signed in
    Given I visit the web site as a registered user
    When I visit the deals and promotions page
    Then I should be able to see wallet icon and below wallet description
      | If you haven't already, add your credit card to your wallet to check out faster & get new offers instantly! |
    And I should see "find_out_more" link on "my_offers" page
    When I click on "get_started" link on "my_offers" page
    Then I should be navigated to wallet page

  @B-85871  @dsv_desktop_sev2
  Scenario: Verify that manually added offers from deals and promotions page are displayed in user's Wallet for signed in user
    Given I visit the web site as a registered user
    When I visit the deals and promotions page
    And I click on 'add to wallet' button for any offer in deals and promotions page
    Then I should see the message as "Offer added - use it when you checkout" in tool tip
    And I should see added offer in my wallet page

  @B-85871
  Scenario: Lookup Plenti and validate Plenti details until checkout page
    Given I visit the web site as a registered user
    When I add an "available" product to my bag
    And I navigate to shopping bag page
    Then I validate Plenti functionality through checkout as a "signed in" user
    Then I lookup plenti id using valid usl phone number on shopping bag page
    And I checkout until I reach the "shipping & payment" page as a "signed in" user
    Then I validate Plenti functionality in checkout page
    And I validate Plenti redeem functionality through checkout as a "signed in" user

  @B-85871
  Scenario: Associate existing plenti account and validate Plenti summary page
    Given I visit the web site as a Plenti user
    When I navigate to my plenti page
    Then I should see USL basic attributes on plenti summary page

  @B-85871
  Scenario: Home Page - Verify JSESSIONID cookie in DOMESTIC mode
    Given I visit the web site as a guest user
    Then I verify that "JSESSIONID" cookie is not displayed

