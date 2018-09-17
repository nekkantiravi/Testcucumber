Feature: Regression scenarios for Apply Gift Card in Responsive Checkout page for Registered User

# ******************************** GCE Scenarios - Navigating directly to the page *******************************************

  @GCE @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario: verify first view of Gift Cards section elements in Responsive Checkout page as registerd user
    Given I visit the web site as a registered user
    Then I should see Gift card section in responsive checkout page
    Then I verify Apply Gift Cards header is displaying in Gift Cards section for "signed in" user
    And I expand gift cards section

  @GCE @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario: verify the second view of Gift cards section fields in Responsive Checkout page as a registered user
    Given I visit the web site as a registered user
    Then I verify the dispaly of card number field
    And I verify the dispaly of cid field
    And I verify the dispaly of captcha image field
    And I verify "captcha refresh" button is enabled
    And I verify the dispaly of captcha input field
    And I verify "Apply Gift Card" button is enabled
    And I click Cancel and verify Gift card section is collapsed for registered user

  @GCE @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario: verify 3rd view of Gift cards section fields in checkout as Registered user
    Given I visit the web site as a registered user
    And I verify masked card number and value on checkout page
    And I verify "Remove" button is enabled
    And I verify "you can apply up to 5." text displaying for "registered" user
    And I verify "APPLY ANOTHER CARD" button is disabled

  @GCE @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario: verify Gift cards section fields in Responsive checkout page as registered user
    Given I visit the web site as a registered user
    And I add valid EGC gift card as payment option on payment page
    And I should see Gift card section in responsive checkout page
    And I should see Gift Card disclaimer text in Gift Card section as "Gift Cards can’t be used with PayPal or on orders covered by Plenti points."

  @GCE @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario: verify Gift cards section fields in Responsive Confirmation page as registered user
    Given I visit the web site as a registered user
    Then I validate the applied Gift card amount in Confirmation page

# ********************************Scenarios with normal flow ********************************************

  @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario: Verify the display of first view of Gift card sections for a signed in user
    Given I visit the website as a registered user using rest services
    And I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    When I checkout until I reach the shipping & payment page as a "signed in" user
    Then I should see Gift card section in responsive checkout page
    Then I verify Apply Gift Cards header is displaying in Gift Cards section for "signed in" user
    And I expand gift cards section

  @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario: Verify the display of second view of Gift card sections for a signed in user
    Given I visit the website as a registered user using rest services
    And I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    When I checkout until I reach the shipping & payment page as a "signed in" user
    Then I should see Gift card section in responsive checkout page
    Then I verify Apply Gift Cards header is displaying in Gift Cards section for "signed in" user
    And I expand gift cards section
    Then I verify the dispaly of card number field
    And I verify the dispaly of cid field
    And I verify the dispaly of captcha image field
    And I verify "captcha refresh" button is enabled
    And I verify the dispaly of captcha input field
    And I verify "Apply Gift Card" button is enabled
    And I click Cancel and verify Gift card section is collapsed for registered user

  @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario: Verify the credit card and gift card sections when paypal is selected as a signed in user
    Given I visit the website as a registered user using rest services
    When I add a "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as a "signed in" user
    When I select paypal as tender type
    Then I verify "expand" button is disabled
    And I should see Gift Card disclaimer text in Gift Card section as "Gift Cards can’t be used with PayPal or on orders covered by Plenti points."

  @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario: Verify payment and order summary sections when applied plenti points satisfies the order total as a signed in user
    Given I visit the web site as a registered user
    When I navigate to my account page
    And I add fully_enrolled_usl id on my account page
    When I navigate to My Wallet page from My Account page
    And I click ADD a NEW CARD button
    And I add a credit card to My Wallet as default card on My Wallet page
    And I directly add an available and orderable product "1310" to my bag
    And I checkout until I reach the shipping & payment page as a "responsive_signed in" user
    And I remove USL ID from shipping and payment page
    When I lookup plenti id using valid usl phone number on payment page
    And I add usl as payment on payment page
    Then I verify "expand" button is disabled
    And I should see Gift Card disclaimer text in Gift Card section as "Gift Cards can’t be used with PayPal or on orders covered by Plenti points."

  @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
  Scenario: verify Gift card section As a signed in user, when Bag only contains a gift card,,
    Given I visit the website as a registered user using rest services
    When I add an "virtual_gift_card and orderable" product to my bag using rest service
    And I checkout until I reach the shipping & payment page as a "signed in" user
    Then I should not see Gift card section in responsive checkout page

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
  Scenario:Verify the Gift card money redeem for Normal item
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
  Scenario: Verify the warning message for 3rd invalid gift card apply attempt in responsive signed in checkout flow
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the shipping & payment page as a "signed in" user
    And I expand gift cards section
    And I make 4 invalid atempts on Gift Card overlay
    Then I should see Gift card warning message in checkout pages:
      | warning_message                                             |
      | Don't forget: You only have 1 chance left to enter this info. You'll be temporarily locked out after your next unsuccessful try.|

  @manual @domain_Site_performance_Optimization @guest_checkout @RC_GiftCards
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
    And I cover the order total with gift card card as tender type
    Then I should see billing address section in active state
    And I should see the profile email address with update link in billing address section
    And I should see the text "Order confirmation will be sent to this address:" over the email address
    When I submit the billing address section with valid address
