Feature: Responsive My Account Functionality check

  Background:
    Given I visit the web site as a signed-in user
    When I navigate the global navigation menu as follows:
      | My Account |

  @domain_customer,@project_salab,@priority_high @use_regression
  Scenario: As a new user, When I navigate to My Account page, I should see Macy's Credit Card card with default data
    And I should see "Macy's Credit Card" card displayed with expected default text information
      | header                 | Macy's Credit Card    |
      | guide_text             | To manage your Macy's Credit Card or make a payment, just add your card here.    |
      | add_macys_card_btn     | ADD MACY'S CARD    |
      | pitch_card             | Don't have a Macy's Card? Open an account and save 20% today and tomorrow up to a total of $100.*   |
      | learn_and_apply_btn    | LEARN & APPLY   |
      | disclaimer             | *Subject to credit approval. A total savings of up to $100 on your purchases over the two days.   |
      | exclusion_details_link | Exclusions & Details   |

  @domain_customer,@project_salab,@priority_high,@dsv_sev2_dryrun @use_regression
  Scenario: As a new user, When I navigate to My Account page, I should see Orders card with default data
    And I should see "Orders" card displayed with expected default text information
      | header                          | Orders    |
      | guide_text                      | No online orders available at this time.    |
      | order_status_and_history        | Order Status & History    |
      | furniture_and_mattress_delivery | Furniture & Mattress Delivery   |

  @domain_customer,@project_salab,@priority_high @use_regression
  Scenario: Validate recent order placed is reflecting on Orders card
    When I navigate to My Wallet page from My Account page
    And I click ADD a NEW CARD button
    And I add a credit card to My Wallet as default card on My Wallet page
    And I add a "available and orderable" product to my bag
    And I checkout until I reach the order review page as a "signed in" user
    Then I checkout until I reach the order confirmation page as an "signed in" user
    Then I should be able to validate order details on responsive MyAccount page

  @domain_customer,@project_salab,@priority_high @use_regression
  Scenario: Validate filled state of Orders card for registered user
    When I navigate to My Wallet page from My Account page
    And I click ADD a NEW CARD button
    And I add a credit card to My Wallet as default card on My Wallet page
    When I place "3" orders successfully
    Then I should see 2 of my most recent orders on the Orders card
    And I should be able to validate rest of Orders card filled state

  @domain_customer,@project_salab,@priority_high,@dsv_sev2_dryrun @use_regression
  Scenario: As a new user, When I navigate to My Account page, I should see Lists / Wish Lists card with default data
    And I should see "Lists" card displayed with expected default text information
      | header           | Lists                |
      | guide_text       | Features: Save items to buy later Share with friends and family Get sales alerts and more  |
      | create_wishlist  | View Lists           |

  @domain_customer,@project_salab,@priority_high @use_regression
  Scenario: As a new user, when I create multiple wishlists, i should be able to validate 6 lists on Lists card
    When I select wishlist link in header
    And I create "8" wishlists
    When I search for "jeans"
    And I add the product to wishlist
    And I select wishlist link on the wishlist overlay in PDP page
    Then I should be able to validate wishlist data on My Account page

  @domain_customer,@project_salab,@priority_high @use_regression
  Scenario: As a new user with no Registry, When I navigate to My Account page, I should not see Registry card
    And I should not see Registry card

  @domain_customer,@project_salab,@priority_high @use_regression
  Scenario: As a user with registry account , When I navigate to My Account page, I should see Registry card with all expected information
    And I navigate to registry home page
    And I select "Create Your Registry"
    And I continue creating registry from create registry page
    Then I should be navigated to the registry manager page
    When I navigate back to My Account page from Registry Welcome page
    Then I should be able to validate registry card

  @domain_customer,@project_salab,@priority_high,@dsv_sev2_dryrun @use_regression
  Scenario: Verify the default eWallet card UI with links and buttons on responsive my account page
    And I should see Wallet card displayed with expected links and buttons

  @domain_customer,@project_salab,@priority_high @use_regression
  Scenario: As a new user, When I navigate to My Account page, I should see Wallet card with default data
    And I should see default eWallet card UI with expected text information
      | header                  | Wallet    |
      | offer_txt               | Offers:    |
      | promotion_txt           | You have no offers saved. To view and add offers, visit Deals & Promotions    |
      | add_payment_method_txt  | Add A Payment Method    |
      | footer_wallet_txt       | Wallet  |
      | footer_gift_txt         | Gift Cards  |

  @domain_customer,@project_salab,@priority_high @use_regression
  Scenario: Verify add credit card functionality in Wallet card from responsive my account page
    And I add credit card to wallet from my account popup
      | cardType |  Visa  |
    Then I should see the credit card added on my account page
      | header                  | Wallet    |
      | footer_wallet_txt       | Wallet  |
      | footer_gift_txt         | Gift Cards  |

  @domain_customer,@project_salab,@priority_high @use_regression
  Scenario: Verify footer link functionality on Wallet card from responsive my account page
    When I clicked on add payment method link
    And I add credit card to wallet from my account popup
      | cardType |  Visa  |
    Then I should be able to validate Wallet footer links

  @domain_customer,@project_salab,@priority_high @use_regression
  Scenario: Verify Edit link functionality on Wallet card from responsive my account page
    When I clicked on add payment method link
    And I add credit card to wallet from my account popup
      | cardType |  Visa  |
    Then I clicked on Change Default Card link



