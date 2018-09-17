Feature:Regression scenarios for Gift Options in Responsive Checkout

  @domain_Site_performance_Optimization @guest_checkout @RC_GiftOptions
  Scenario: Place an order by selecting Gift options (Gift wrap, gift message)as a guest user
    Given I visit the website as a guest user using rest services
    When I add an "available and orderable and gift_wrappable and gift_messageable" product to my bag using rest service that is not "registrable and available_bops"
    And I checkout until I reach the shipping page as a "guest" user
    And I enter shipping address on guest shipping page
    And I select gift options on shipping page
    And I select continue button on guest shipping page
    And I add a credit card during checkout
    And I select continue button on guest payment page
    Then I should see selected gift option indicators in checkout page
    Then I place an order
    And I get order details
    Then I should see selected gift option indicators in order confirmation page

  @domain_Site_performance_Optimization @signed_in_user_checkout @RC_GiftOptions
  Scenario: Place an order by selecting Gift options (Gift wrap, gift message)as a Registered user
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable and gift_wrappable and gift_messageable" product to my bag using rest service that is not "registrable and available_bops"
    And I checkout until I reach the shipping & payment page as a "signed in" user
    And I expand select gift options for signed in user
    Then I should see selected gift option indicators in checkout page
    When I continue checkout until I reach the order confirmation page as a "signed in" user
    Then I should see selected gift option indicators in order confirmation page

  @domain_Site_performance_Optimization @guest_user_checkout @RC_GiftOptions
  Scenario: Verify gift option indicators and gift box fee when gift options are selected as guest user
    Given I visit the website as a guest user using rest services
    When I add an "available and orderable and gift_wrappable and gift_messageable" product to my bag using rest service that is not "registrable and available_bops"
    And I checkout until I reach the shipping page as a "guest" user
    And I enter shipping address on guest shipping page
    And I select gift options on shipping page
    And I select gift box in gift options
    And I select continue button on guest shipping page
    Then I should see gift option indicators in checkout page
    When I continue checkout until I reach the order confirmation page as a "guest" user
    Then I should see gift option indicators in order confirmation page


  @domain_Site_Performance_Optimization @signedin_checkout @RC_GiftOptions
  Scenario: Verify gift option indicators and gift box fee when gift options are selected as a signed in user
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable and gift_wrappable and gift_messageable" product to my bag using rest service
    And I checkout until I reach the shipping & payment page as a "signed in" user
    And I expand select gift options and gift box for signed in user
    Then I should see gift option indicators in checkout page
    When I continue checkout until I reach the order confirmation page as a "signed in" user
    Then I should see gift option indicators in order confirmation page

  @domain_Site_Performance_Optimization @guest_checkout @RC_GiftOptions
  Scenario Outline: Verify that Go green message is displaying in "Shipping Option Page", for registries who have opted the go green option for guest user
    Given I visit the web site as a registry user
    When I add "registrable" product to my bag from BVR page
    And I sign out from my current profile
    And I navigate to shopping bag page
    And I checkout until I reach the shipping page as a "guest" user
    And I select gift options on shipping page
    And I select gift box in gift options
    And I should see Go Green "<message>" on checkout page
    And I select continue button on guest shipping page
    Then I should see gift option indicators in checkout page
    When I continue checkout until I reach the order confirmation page as a "guest" user
    Then I should see gift option indicators in order confirmation page
    Examples:
      |message|
      |This couple has chosen our Go Green option and prefers gifts shipped from Macy's without gift wrap or gift box.|

  @domain_Site_Performance_Optimization @Signed_in_checkout @RC_GiftOptions
  Scenario Outline: Verify that Go green message is displaying in "Shipping Option Page", for registries who have opted the go green option for Signed In user
    Given I visit the web site as a registry user
    When I add "registrable" product to my bag from BVR page
    And I sign out from my current profile
    And I create a new profile
    And I navigate to shopping bag page
    And I checkout until I reach the shipping & payment page as an "responsive signed in" user
    And I expand gift options for signed in user
    And I should see Go Green "<message>" on checkout page
    And I select gift options and gift box for signed in user
    Then I should see gift option indicators in checkout page
    When I continue checkout until I reach the order confirmation page as a "signed in" user
    Then I should see gift option indicators in order confirmation page
    Examples:
      |message|
      |This couple has chosen our Go Green option and prefers gifts shipped from Macy's without gift wrap or gift box.|

  @domain_Site_Performance_Optimization @Signed_in_checkout @RC_GiftOptions
  Scenario: Verify Gift Options with registry bops and normal items as a registered user
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "available_bops"
    And I add an "available_bops" product to my bag using rest service
    Then I should see bops shipping and normal shipping in order summary on shopping bag page
    When I checkout until I reach the shipping page as a "bops" user
    Then I should see bops shipping and normal shipping in order summary section
    When I checkout until I reach the payment page as a "bops" user
    Then I should see bops shipping and normal shipping in order summary section
    When I checkout until I reach the order review page as a "bops" user
    Then I should see bops shipping and normal shipping in order summary section
    And I expand select gift options and gift box for signed in user
    Then I should see gift option indicators in checkout page
    When I continue checkout until I reach the order confirmation page as a "signed in" user
    Then I should see gift option indicators in order confirmation page

  @domain_Site_Performance_Optimization @Guest_checkout @RC_GiftOptions
  Scenario: Verify Gift Options with VGC item as a guest user
    Given I visit the website as a guest user using rest services
    When I add an "virtual_gift_card and orderable" product to my bag using rest service
    And I checkout until I reach the shipping page as a "guest" user
    Then I verify shipping address section not present
    And I verify shipping method section not present
    And I verify gift options section not present
    And I should see vgc shippping copy in checkout page

  @domain_Site_Performance_Optimization @signedin_checkout @RC_GiftOptions
  Scenario: Verify the display of RC shipping sections when user have only VGC item in bag as a signed in user
    Given I visit the website as a registered user using rest services
    When I add an "virtual_gift_card and orderable" product to my bag using rest service
    And I checkout until I reach the shipping & payment page as a "signed in" user
    Then I verify shipping address section for Signed In user should not present
    And I verify shipping method section for Signed In user should not present
    Then I verify gift options section for Signed In user should not present
    And I should see vgc shippping copy in checkout page

  @domain_Site_performance_Optimization @guest_checkout @RC_GiftOptions
  Scenario: Place order with registry bops and normal and vgc items as a guest user
    Given I visit the website as a guest user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I add an "virtual_gift_card and orderable" product to my bag using rest service
    And I add an "available_bops and registrable and orderable" product to my bag using rest service
    And I checkout until I reach the order confirmation page as an "bops" user
    And I select continue button on guest shipping page
    Then I should see gift option indicators in checkout page
    When I continue checkout until I reach the order confirmation page as a "guest" user
    Then I should see gift option indicators in order confirmation page

  @domain_Site_performance_Optimization @signedIn_checkout @RC_GiftOptions
  Scenario Outline: To verify the the warning messages,number of shipments in DB when we have gift wrap eligible and non gift wrapable item.
    Given I visit the web site as a registered user
    When I add an "gift_wrappable and orderable" product to my bag using rest service
    And I add an "available and orderable" product to my bag using rest service that is not "gift_wrappable and available_bops"
    And I checkout until I reach the shipping page as a "registered" user
    And I expand gift options for signed in user
    And I select gift options and gift box for signed in user
    Then I should see gift option indicators in checkout page
    And I should see below global warning "<message>" on the page
    And I enter shipping address on guest shipping page
    When I continue checkout until I reach the order confirmation page as a "signed in" user
    Then I should see gift option indicators in order confirmation page
    And I should see below global warning "<message>" on the page
    Examples:
      |message|
      |Please note the items below cannot be gift wrapped in your order.|


  @domain_Site_performance_Optimization @guest_checkout @RC_GiftOptions
  Scenario: veriy Gift options (Gift wrap, gift message)as a guest user for SDD products
    Given I visit the website as a guest user using rest services
    When I add an "available and sdd_eligible and gift_wrappable and gift_messageable" product to my bag using rest service
    And I checkout until I reach the shipping page as a "guest" user
    And I enter Same Day Delivery shipping address on guest shipping page
    And I select sdd_shipping in shipping methods
    And I verify Gift Box option and Gift message options are disabled for Same day delivery eligible product
    And I select continue button on guest shipping page
    And I add a credit card during checkout
    And I select continue button on guest payment page
    Then I should see selected gift option indicators in checkout page for SDD product
    Then I place an order
    And I get order details
    Then I should see selected gift option indicators in order confirmation page for SDD product

  @domain_Site_performance_Optimization @guest_checkout @RC_GiftOptions
  Scenario: Verify the gift options when user changes the shipping method for the order from SDD to another shipping method
    Given I visit the website as a guest user using rest services
    When I add an "available and sdd_eligible and gift_wrappable and gift_messageable" product to my bag using rest service
    And I checkout until I reach the shipping page as a "guest" user
    And I enter Same Day Delivery shipping address on guest shipping page
    And I select sdd_shipping in shipping methods
    And I verify Gift Box option and Gift message options are disabled for Same day delivery eligible product
    And I select continue button on guest shipping page
    When I click on edit in shipping address section
    And I select "standard" in shipping method
    Then I verify Gift Box option and Gift message options are enabled



   # ********************************************************************GCE scenarios to validate Gift Options section during development *************************************************
  @GCE @domain_Site_performance_Optimization @guest_checkout @RC_GiftOptions @1
  Scenario: Verify Gift Options section in Responsive Checkout page as signed user
    Given I visit the web site as a registered user
    Then I verify gift options section should present
    And I expand gift options for signed in user
    Then I verify Gift Box option and Gift message options are enabled
    And I verify Hide prices option is selected by default in Gift Options section
    Then I validate Cancel and Save buttons are enabled


  @GCE @domain_Site_performance_Optimization @guest_checkout @RC_GiftOptions @1
  Scenario: Verify Gift Options section in Responsive Checkout page as Guest user
    Given I visit the web site as a guest user
    Then I verify gift options section should present
    Then I verify Gift Box option and Gift message options are enabled
    And I verify Hide prices option is selected by default in Gift Options section
    Then I validate Cancel and Save buttons are enabled


  @GCE @domain_Site_performance_Optimization @guest_checkout @RC_GiftOptions
  Scenario: verify Gift Options secion fields in Responsive checkout page
    Given I visit the web site as a guest user
    And I select gift options on shipping page
    And I enter more than 45 characters
    And I enter numbers and special characters
    And I select gift box in gift options
    When I click on Cancel button
    Then I should see gift message on review gift option page


  @GCE @domain_Site_performance_Optimization @guest_checkout @RC_GiftOptions
  Scenario: verify Empty message field
    Given I visit the web site as a guest user
    And I select gift options with no message
    When I click on Cancel button
    Then I validate no gift message is displaying on Review gift options page
