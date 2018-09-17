Feature: Regression scenarios for Apply Gift Card in Responsive Checkout page for Guest User


# ******************************** GCE Scenarios - Navigating directly to the page ********************************************
  @GCE @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario: verify First view of Gift Cards section elements in Responsive Checkout page as guest user
    Given I visit the web site as a guest user
    Then I verify Apply Gift Cards header is displaying in Gift Cards section for "guest" user
    And I verify "You can apply up to 5." text displaying for "guest" user
    And I verify "Apply Card" button is enabled

  @GCE @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario: verify second view of Gift cards section fields in Responsive Checkout page as a guest user
    Given I visit the web site as a guest user
    Then I verify Apply Gift Cards header is displaying in Gift Cards section for "guest" user
    When I click on Apply card in gift card section
    Then I verify Apply Gift Cards header is displaying in Gift Cards section for "guest" user
    And I should see Gift Card disclaimer text in Gift Card section for guest user as "Note: PayPal can't be used with Gift Cards, Reward Cards and Credit Cards. Plenti points can be earned but not used with PayPal."
    Then I verify the dispaly of card number field
    And I verify the dispaly of cid field
    And I verify the dispaly of captcha image field
    And I verify "captcha refresh" button is enabled
    And I verify the dispaly of captcha input field
    And I verify "Apply" button is enabled
    And I click Cancel and verify Gift card section is collapsed for guest user

# ********************************Scenarios with normal flow ********************************************

  @GCE @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario: verify first view of Gift cards section fields in Responsive Order review page as guest user
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the payment page as a "guest" user
    Then I verify Apply Gift Cards header is displaying in Gift Cards section for "guest" user
    And I verify "You can apply up to 5." text displaying for "guest" user
    And I verify "Apply Card" button is enabled

  @GCE @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario: verify second view of Gift cards section fields in Responsive Order review page as guest user
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the payment page as a "guest" user
    Then I verify Apply Gift Cards header is displaying in Gift Cards section for "guest" user
    When I click on Apply card in gift card section
    Then I verify Apply Gift Cards header is displaying in Gift Cards section for "guest" user
    And I should see Gift Card disclaimer text in Gift Card section for guest user as "Note: PayPal can't be used with Gift Cards, Reward Cards and Credit Cards. Plenti points can be earned but not used with PayPal."
    Then I verify the dispaly of card number field
    And I verify the dispaly of cid field
    And I verify the dispaly of captcha image field
    And I verify "captcha refresh" button is enabled
    And I verify the dispaly of captcha input field
    And I verify "Apply" button is enabled
    And I click Cancel and verify Gift card section is collapsed for guest user

  @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario: Place an order with gift card as payment type
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the payment page as a "guest" user
    And I add valid EGC gift card as payment option on payment page
    Then I checkout until I reach the order confirmation page as an "guest" user

  @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario: verify reward card section, when Bag contains only a gift card for guest user
    Given I visit the website as a guest user using rest services
    When I add an "electronic_gift_card and orderable" product to my bag using rest service
    And I checkout until I reach the shipping page as a "guest" user
    Then I should not see Gift card section in responsive checkout page

  @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario: Verify that gift card payment option should not be displayed in payment section on clicking cancel button.
    Given I visit the website as a guest user using rest services
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    And I click Cancel and verify Gift card section is collapsed for guest user

  @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario: Verify the credit card and gift card button when paypal is selected on guest payment page
    Given I visit the website as a guest user using rest services
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    When I select paypal as tender type
    Then I should see the paypal icon
    And I verify "Apply Card" button is disabled

  @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario: Verify the note in apply gift card overlay as a guest user
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the payment page as a "guest" user
    Then I verify Apply Gift Cards header is displaying in Gift Cards section for "guest" user
    When I click on Apply card in gift card section
    And I should see Gift Card disclaimer text in Gift Card section for guest user as "Note: PayPal can't be used with Gift Cards, Reward Cards and Credit Cards. Plenti points can be earned but not used with PayPal."

  #***************************Manual validation due to Ceptcha field input **************************************************

  @manual @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario Outline: Verify that billing address section is visible and gift card cell is visible when guest user applies to 50% of payment with Plenti points, and 50% with gift card
    Given I am on the Home Page as a <user> user
    Then I add one item to my bag of type "orderable_product"
    And I checkout as a <user> user and fill the shipping address and plenti number
    And I use plenti points to cover 50% of payment
    Then I add a gift card and use it to cover 50% of payment
    And I enter the captcha code manually on ApplyGiftCardPage
    Then I verify user able to enter billing address and place an order
    Examples:
      |user     |
      |guest    |
      |signed in|

  @manual @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario: Verify plenti field is hidden when user applies for gift card which covers 100% of the total amount on CheckoutPage
    Given I am on HomePage as signed in user with plenti already associated with the account
    And I add one item to my bag of type "orderable_product"
    Then I navigate to CheckoutPage and enter gift card details on ApplyGiftCardPage
    And I enter the captcha code manually on ApplyGiftCardPage
    And I click on the ApplyGiftCardPage "save_button"
    And I fill out billing and contact details on checkout page
    Then I verify plenti field is hidden and click on place order on CheckoutPage

  @manual @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario: As a guest user, I should be able to apply gift cards or reward cards which satisfies entire order total
    Given I visit the web site as a "responsive guest" user in mMoney burn period
    When I add a "available and orderable" product to my bag
    And I checkout until I reach the "payment" page as a "guest" user
    Then I should see Gift card or Rewards Card text in payment section
    When I apply a gift cards and reward cards which satisfies entire order total
    And I should see apply link for reward card in payment section
    Then I should see Gift/Rewards cards applied in mini bag

  @manual @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario: As a guest user, I should be able to apply gift cards or reward cards which satisfies entire order total and a message should be displayed in mini bag
    Given I visit the web site as a guest user in mMoney burn period
    When I add a "available and orderable" product to my bag
    And I checkout until I reach the "payment" page as a "guest" user
    Then I should see Gift card or Rewards Card section in payment page
    When I apply a gift cards or reward cards which satisfies entire total
    Then I should see Gift/Rewards cards applied in mini bag
    And I should see message "Gift/Rewards Cards applied -$X.XX" in the mini bag

  @manual @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario Outline:As a guest user in redeem period, when Bag contains a gift card and normal item, value deducted from the mMoney reward card only for the amount of non gift card merchandise
    Given I visit the web site as a guest user
    When I add a vgc product to my bag
    And I add a "available and orderable" product to my bag
    And I checkout until I reach the <payment_page> page as a "guest" user
    Then I add a mMoney reward card
    And I verify gift card and personalization fees is excluded
    And I verify value deducted from the mMoney reward card only for the amount of non gift card merchandise
    When I add another mMoney reward card
    Then I should see following error message in the captcha overlay
      | Macy's Money reward cards cannot be used to purchase gift cards. Please choose a different payment method. |
    Examples:
      | payment_page       |
      | responsive_payment |
      | legacy_payment     |

 @manual @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario:Verify the MB money redeem for Normal item
    Given I visit the web site as a guest user in mMoney burn period
    And I create a new profile
    And current user has icw mmoney card in their wallet
    When I add a random product to bag
    And I navigate to shopping bag page
    And I checkout until I reach the shipping & payment page as a "signed in" user
    And I apply 1st gift card from the list
    Then there's 1 mmoney card applied
    And I should see mmoney card is successfully applied in order summary section

  @manual @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario: :As a guest user in redeem period, when Bag contains a gift card and normal item, verify reward card section on removing eligible product
    Given I visit the web site as a guest user
    When I add a vgc product to my bag
    And I add a "available and orderable" product to my bag
    And I checkout until I reach the <payment_page> page as a "guest" user
    Then I add a mMoney reward card
    Then I verify value deducted from the mMoney reward card only for the amount of non gift card merchandise
    When I remove eligible product from bag
    Then I verify added mMoney reward card is removed from reward card section
    And I verify apply card button is disabled

  @manual @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario: Verify the warning message for 3rd invalid gift card apply attempt in responsive signed in checkout flow
    Given I visit the website as a guest user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the shipping & payment page as a "guest" user
    And I expand gift cards section
    And I make 4 invalid atempts on Gift Card overlay
    Then I should see Gift card warning message in checkout pages:
      | warning_message                                             |
      | Don't forget: You only have 1 chance left to enter this info. You'll be temporarily locked out after your next unsuccessful try.|
