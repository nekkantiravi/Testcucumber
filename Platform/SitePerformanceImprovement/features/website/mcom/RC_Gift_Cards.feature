Feature: Regression scenarios for Apply Gift Card in Responsive Checkout


# ******************************** GCE Scenarios - Navigating directly to the page ********************************************
  @GCE @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario: verify Gift Cards section elements in Responsive Checkout page as guest user
    Given I visit the web site as a guest user
    Then I verify Apply Gift and Rewards Cards header is displaying
    And I validate text "You can apply up to 5." is displaying
    And I validate Appy Card button

  @GCE @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario: verify Gift Cards section elements in Responsive Checkout page as registerd user
    Given I visit the web site as a registered user
    Then I verify Apply Gift Cards header is displaying in Gift Cards section
    And I expand the gift cards section


  @GCE @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario: verify Gift cards section fields in Responsive Checkout page as a registered user
    Given I visit the web site as a registered user
    Then I validate Gift Card section is displaying
    And I verify Apply Gift Cards header is displaying
    And I validate Card Number text displaying on the Card number field
    When I Enter valid Gift card number
    Then I validate the display of visual icon in Card number field
    And I validate CID number text displaying on the CID number filed
    When hen I enter valid CID number
    Then I validate the display of visual icon in CID number field
    And I validate the captcha image field
    And I validate Refresh button in enabled
    #manual step -- When I enter some text in the captcha input field
    And I click on Apply GIFT CARD button
    Then I see visual image in the captcha input field
    When I click on Cacel button
    Then I verify Gift card section is collapsed

  @GCE @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario: verify Gift cards section fields in Responsive Checkout page as a guest user
    Given I visit the web site as a guest user
    When I click on Apply card in gift card section
    Then I validate Gift Card section is displaying
    And I verify Apply Gift and Rewards Cards header is displaying
    And I see Note on Gift card payment method
    And I validate Card Number text displaying on the Card number field
    When I Enter valid Gift card number
    Then I validate thedisplay of visual icon in Card number field
    And I validate CID number text displaying on the CID number filed
    When hen I enter valid CID number
    Then I validate the display of visual icon in CID number field
    And I validate the captcha image field
    And I validate Refresh button in enabled
 #manual step -- When I enter some text in the captcha input field
    And I click on Apply button
    Then I see visual image in the captcha input field
    When I click on Cacel button
    Then I verify Gift card section is collapsed



  @GCE @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario: verify Gift cards section fields in Responsive Order review page as guest user
    Given I visit the web site as a guest user
    When I click on Apply card in gift card section
    When I Enter valid Gift card number
    When I enter valid CID number
    #manual step -- When I enter some text in the captcha input field
    And I click on Apply button
    And I verify masked card number and value on Order review page
    And I verify Remove button is enabled
    And I verify "you can apply up to 5." text displaying
    And I verify Apply card button is disabled.

  @GCE @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario: verify Gift cards section fields in Responsive Order review page as registered user
    Given I visit the web site as a registered user
    And I verify Apply Gift Cards header is displaying in Gift Cards section
    And I expand the gift cards section
    When I Enter valid Gift card number
    When I enter valid CID number
 #manual step -- When I enter some text in the captcha input field
    And I click on Apply GIFT CARD button
    And I verify Apply Gift Card section in order review page
    And I verify Note in Gift Card section


  @GCE @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario: verify Gift cards section fields in Responsive Order Summary page as guest user
    Given I visit the web site as a guest user
    When I click on Apply card in gift card section
    When I Enter valid Gift card number
  when I enter valid CID number
#manual step -- When I enter some text in the captcha input field
    And I click on Apply button
    When I continue checking out to Order summary page
    Then I verify Gift and Rewards Cards in payment section
    And I verify Card masked number
    And I verify card value


  @GCE @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario: verify Gift cards section fields in Responsive Confirmation page as registered user
    Given I visit the web site as a registered user
    And I verify Apply Gift Cards header is displaying in Gift Cards section
    And I expand the gift cards section
    When I Enter valid Gift card number
    When I enter valid CID number
#manual step -- When I enter some text in the captcha input field
    And I click on Apply GIFT CARD button
    And countinue checking out to Order summary page
    When I place order
    Then I validate the applied amount in Confirmation page


  @GCE @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario: Verify less than 16 digit in the gift card number field
    Given I visit the web site as a guest user


  @GCE @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario:Verify warning message after 4 tries
    Given I visit the web site as a guest user

# ********************************Scenarios with normal flow ********************************************
@domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
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

  @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario: verify reward card section, when Bag contains only a gift card for guest user
    Given I visit the web site as a guest user
    When I add a vgc product to my bag
    And I checkout until I reach the <payment_page> page as a "guest" user
    Then I verify reward card section is disabled
    And I verify apply card button is disabled

  @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario: Verify that gift card payment option should not be displayed in payment section on clicking cancel button.
    Given I visit the web site as a "responsive guest" user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the "responsive payment" page as a "guest" user
    And I opt for gift card payment
    When I opt to cancel gift card payment
    Then I should not see gift card payment section

  @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario: Verify the credit card and gift card sections when paypal is selected as a signed in user
    Given I visit the web site as a "responsive registered" user
    When I add a "available and orderable" product to my bag
    And I checkout until I reach the "responsive shipping and payment" page as a "signed in" user
    And I select "paypal" as tender type
    Then I should see the paypal icon
    And I should see that "credit card" is enabled
    And I should see that "credit card" is unchecked
    And I should see apply gift card button is disabled

  @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario: Verify the credit card and gift card button when paypal is selected on guest payment page
    Given I visit the web site as a "responsive guest" user
    When I add a "available and orderable" radical product to my bag
    And I checkout until I reach the "responsive payment" page as a "guest" user
    And I select "paypal" as tender type
    Then I should see the paypal icon
    And I should see that "credit card" is enabled
    And I should see that "credit card" is unchecked
    And I "should not" see credit card fields are displayed
    And I should see apply gift card button is disabled

  @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario: Verify the note in apply gift card overlay as a guest user
    Given I visit the web site as a "responsive guest" user
    When I add a "available and orderable" radical product to my bag
    And I checkout until I reach the "responsive payment" page as a "guest" user
    Then I should see gift cards section on "payment" page
    And I select apply gift card option
    And I should see following note on apply gift card section:
      | MCOM | PayPal can't be used with Gift Cards, Reward Cards and Credit Cards. Plenti points can be earned but not used with PayPal.  |

  @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario: Verify payment and order summary sections when applied plenti points satisfies the order total as a signed in user
    Given I visit the web site as a "responsive registered" user
    And I add an "available and orderable" product to my bag
    And I checkout until I reach the "responsive shipping and payment" page as a "signed in" user
    And I lookup for "available_points_usl" using valid "usl_phone_number" on payment page
    When I apply USL points equal to order total on responsive payment page
    Then I should see applied USL points and equivalent dollar value in order summary section
    And I should see gift card disable note in apply gift card section
    And I should see billing address section in active state

  @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
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

  @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario: Verify the warning message for 3rd invalid gift card apply attempt in responsive signed in checkout flow
    Given I visit the web site as a "responsive registered" user
    When I add a "available and orderable" radical product to my bag
    And I checkout until I reach the "shipping and payment" page as a "signed in" user
    And I make 4 invalid atempts on Gift Card overlay
    Then I should see Gift card warning message in checkout pages:
      | warning_message                                             |
      | Don't forget: You only have 1 chance left to enter this info. You'll be temporarily locked out after your next unsuccessful try.|

  @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario: As a signed in user in redeem period, when Bag only contains a gift card,, verify reward card section
    Given I visit the website as a registered user who has 1 mMoney reward cards in mMoney burn period
    When I add a VGC and EGC items to bag which met mmoney earn threshold
    And I checkout until I reach the "responsive shipping and payment" page as a "signed in" user
    Then I verify reward card section and gift card buttons are disabled
    #And I should see "Sorry, you can't purchase a Gift Card with a Rewards Card or a Gift Card. Please use a credit or debit card." error message in "responsive payment" page

    # Note: In 15I error message is not displayed but it is good to have.

  @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario: As a signed in user in redeem period, when Bag contains a gift card and normal item, value deducted from the mMoney reward card only for the amount of non gift card merchandise
    # Pre-requisite: We need to test this scenario during the redeem period and user should have mMoney reward card
    Given I visit the website as a registered user who has 5 mMoney reward cards in mMoney burn period
    When I add a VGC and EGC items to bag which met mmoney earn threshold
    And I add an "mbmoney and orderable and mbmoney_eligible and mbmoney_threshold_met" radical product to my bag
    And I checkout until I reach the "responsive shipping and payment" page as a "signed in" user
    When I apply 1 Macy's Money on shipping and payment page
    Then I should see applied reward card amount is updated in order summary section
    When I apply 1 Macy's Money on shipping and payment page
    Then I should see "Macy's Money reward cards cannot be used to purchase gift cards. Please choose a different payment method." error message in shipping and payment page

  @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario: Verify the contact info section email field behavior when order total is covered by gift card
    Given I visit the web site as a "responsive registered" user
    And I add a "available and orderable" radical product to my bag
    And I checkout until I reach the "responsive shipping and payment" page as a "signed in" user
    And I confirm that the "checkoutOptiProfileEmailEnabled" kill switch is set to true
    And I cover the order total with gift card card as tender type
    Then I should see billing address section in active state
    And I should see the profile email address with update link in billing address section
    And I should see the text "Order confirmation will be sent to this address:" over the email address
    When I submit the billing address section with valid address


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







