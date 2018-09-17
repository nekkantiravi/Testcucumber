#Team: Bcom Optimization and Fulfillment lab
#Author: Ulyana Opolska
#Date Created: 10/26/2017

#Pre-Requisite:: kill switch: responsiveMyAccountEnabled=true

Feature: Responsive My Account Functionality check

  Background:
    Given I visit the web site as a guest user
    And I navigate to create profile page
    And I create a new profile
    And I navigate to my account page

  @b-92057 @optimization_lab @priority_high @use_regression
  Scenario: As a new user, When I navigate to My Account page, I should see Bloomingdale's Credit Card card with default data
    And I should see "Bloomingdale's Credit Card" card displayed with expected default text information
      | header                 | Bloomingdale's Credit Card                                                  |
      | guide_text             | Add your card to easily make payments and manage your credit account online.|
      | add_bcom_card_btn      | ADD YOUR CARD                                                               |
      | pitch_card             | Don't have a Bloomingdale's Credit Card?                                    |
      | learn_and_apply_btn    | APPLY NOW                                                                   |
      | disclaimer             | Subject to credit approval.                                                 |

  @b-92057 @optimization_lab @priority_high @use_regression
  Scenario: As a new user, When I navigate to My Account page, and I click 'Add your card' button, I should be navigated to citi add card page
    When I click "add card" button on Bloomingdale's Credit Card on "my account" page
    Then I should be navigated to citi "add card" page

  @b-92057 @optimization_lab @priority_high @use_regression
  Scenario: As a new user, When I navigate to My Account page, and I click 'Apply Now' button, I should be navigated to citi application page
    When I click "apply now" button on Bloomingdale's Credit Card on "my account" page
    Then I should be navigated to citi "apply now" page

  @b-92057 @optimization_lab @priority_high @use_regression
  Scenario: As a new user, When I navigate to My Account page, I should see Orders card with default data
    And I should see "Orders" card displayed with expected default text information
      | header                          | Orders                              |
      | guide_text                      | No Orders Available                 |
      | order_status_and_history        | ORDER STATUS & HISTORY              |
      | furniture_and_mattress_delivery | FURNITURE & MATTRESS DELIVERY STATUS|

  @b-92057 @optimization_lab @priority_high @use_regression
  Scenario: Validate recent order placed is reflecting on Orders card
    When I navigate to My Wallet page from My Account page
    And I click ADD a NEW CARD button
    And I add a credit card to My Wallet as default card on My Wallet page
    And I add a "available and orderable" product to my bag
    And I checkout until I reach the order review page as a "signed in" user
    Then I checkout until I reach the order confirmation page as an "signed in" user
    Then I should be able to validate order details on responsive MyAccount page

  @b-92057 @optimization_lab @priority_high @use_regression
  Scenario: Validate filled state of Orders card for registered user
    When I navigate to My Wallet page from My Account page
    And I click ADD a NEW CARD button
    And I add a credit card to My Wallet as default card on My Wallet page
    When I place "3" orders successfully
    Then I should see 2 of my most recent orders on the Orders card
    And I should be able to validate rest of Orders card filled state

  @b-92057 @optimization_lab @priority_high @use_regression
    Scenario: As a user when I click Orders Status & History link, I am navigated to Orders Status & History page
     When I click "order status & history" button on Orders card on My Account page
     Then I should be navigated to "order status & history" page

  @b-92057 @optimization_lab @priority_high @use_regression
  Scenario: As a user when I click Furniture & Mattress Delivery Status link, I am navigated to Furniture & Mattress Delivery Status link page
    When I click "furniture & mattress delivery status" button on Orders card on My Account page
    Then I should be navigated to "furniture & mattress delivery status" page

  @b-92057 @optimization_lab @priority_high @use_regression
  Scenario: Verify Loyallist card elements for user without loyallist account
    And I should see "Loyallist" card displayed with expected default text information
      | header           | Loyallist                                         |
      | guide_text       | Not a Loyallist?                                  |
      | guide_text2      | Earn rewards when you shop!                       |
      | guide_text3      | It's easy. It's free!                             |
      | loyallist        | Become a Loyallist                                |
      | loyallist_number | SAVE YOUR LOYALLIST NUMBER TO YOUR ONLINE ACCOUNT |

  @b-92057 @optimization_lab @priority_high @use_regression
  Scenario: As a user when I click Become a Loyallist link, I am navigated to Loyallist enrollment page
    When I click "become a loyallist" link on Loyallist card on My Account page
    Then I should be navigated to "loyallist enrollment" page

  @b-92057 @optimization_lab @priority_high @use_regression
  Scenario: As a user when I click Save your Loyallist Number To Your Online Account link, I am navigated to Furniture & Mattress Delivery Status link page
    When I click "save your loyallist number to your online account" link on Loyallist card on My Account page
    Then I should be navigated to "loyallist account association" page

  @b-92057 @optimization_lab @priority_high @use_regression
  Scenario: Verify Loyallist card elements for user with loyallist account
    And I click "save your loyallist number to your online account" link on Loyallist card on My Account page
    And I can associate my account by loyallist number using "base_tier"
    When I navigate to my account page
    Then I should be able to validate Loyallist card filled state

  @b-92057 @optimization_lab
  Scenario: Verify the default bWallet card UI with links and buttons on responsive my account page
    And I should see Wallet card displayed with expected links and buttons

  @b-92057 @optimization_lab @priority_high @use_regression
  Scenario: As a new user, When I navigate to My Account page, I should see Wallet card with default data
    And I should see default eWallet card UI with expected text information
      | header                 | bWallet                |
      | offer_txt              | Current Offers         |
      | promotion_txt          | View Sales & Promotions|
      | add_payment_method_txt | Add a Credit Card      |
      | footer_wallet_txt      | MY bWALLET             |
      | footer_gift_txt        | MY GIFT CARDS          |

  @b-92057 @optimization_lab @priority_high @use_regression
  Scenario: Verify add credit card functionality in Wallet card from responsive my account page
    When I navigate to My Wallet page from My Account page
    And I click ADD a NEW CARD button
    And I add a credit card to My Wallet as default card on My Wallet page
    And I navigate to my account page
    Then I should see the credit card added on my account page
      | header                  | bWallet               |
      | Change_Default_Card_txt | Default Payment Method|
      | footer_wallet_txt       | MY bWALLET            |
      | footer_gift_txt         | MY GIFT CARDS         |

  @b-92057 @optimization_lab @priority_high @use_regression
  Scenario: Verify footer link functionality on Wallet card from responsive my account page
    When I navigate to My Wallet page from My Account page
    And I click ADD a NEW CARD button
    And I add a credit card to My Wallet as default card on My Wallet page
    And I navigate to my account page
    Then I should be able to validate Wallet footer links

  @b-92057 @optimization_lab @priority_high @use_regression
  Scenario: Verify Edit link functionality on Wallet card from responsive my account page
    When I navigate to My Wallet page from My Account page
    And I click ADD a NEW CARD button
    And I add a credit card to My Wallet as default card on My Wallet page
    And I navigate to my account page
    Then I clicked on Change Default Card link

  @b-92057 @optimization_lab @priority_high @use_regression
  Scenario: As a new user, When I navigate to My Account page, I should see Wish Lists card with default data
    And I should see "Lists" card displayed with expected default text information
      | header          | Wish Lists                                                                                                             |
      | guide_text      | Easy to create, a cinch to share. Create one now, and we'll drop you an email when something on your list goes on sale.|
      | create_wishlist | CREATE AND MANAGE LISTS                                                                                                |

  @b-92057 @optimization_lab @priority_high @use_regression
  Scenario: As a new user, when I create multiple wishlists, i should be able to validate 8 lists on Lists card
    When I select wishlist link in header
    And I create "8" wishlists
    When I search for "3048"
    And I add the product to wishlist
    And I select wishlist link on the wishlist overlay in PDP page
    Then I should be able to validate wishlist data on My Account page

  @b-92057 @optimization_lab @priority_high @use_regression
  Scenario: As a new user with no Registry, When I navigate to My Account page, I should not see Registry card
    And I should not see Registry card

  @b-92057 @optimization_lab @priority_high @use_regression
  Scenario: As a user with registry account , When I navigate to My Account page, I should see Registry card with all expected information
    When I navigate to registry home page
    And I mouse over "GETTING STARTED" category from top navigation
    And I select "Create a Registry" subcategory from flyout menu
    And I create a new registry for user with existing bcom profile
    When I navigate to my account page
    Then I should be able to validate registry card

