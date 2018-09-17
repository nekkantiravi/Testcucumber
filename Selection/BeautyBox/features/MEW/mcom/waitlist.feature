Feature: Beauty Box Roxy Feature File

  @tag1 @Feature:B-76378 @cta @beauty @wait
  Scenario Outline: User is joining in WL from beauty experience
    Given I visit the web site as a guest user
    When I click on SignIn button on Home page
    And I enter an existing email id
    Then I visit database to insert "<dmls>" as a precondition
    And I launch beautybox about page url
    Then I see "<status>" on about page
    When I click on subscribe on about page
    Then I should navigate to welcome waitlist page

    Examples:
      |dmls|status|
      |qtyegrtthanmaxqty_wlperis-1.sql|JOIN THE WAITLIST|


  @tag2 @Feature:B-75035 @wait
  Scenario Outline: As a logged in user, successfully joined in WL
    Given I visit the web site as a guest user
    When I visit database to insert "<dmls>" as a precondition
    And I launch beautybox about page url
    Then I see "<status>" on about page
    When I click on subscribe on about page
    Then I should see subscription popup
    When I clicked on continue button in the popup
    Then SignIn page should get loaded
    When I enter an existing email id
    Then I should navigate to welcome waitlist page


    Examples:
      |dmls|status|
      |qtyegrtthanmaxqty_wlperis-1.sql|JOIN THE WAITLIST|


  @tag3 @Feature:B-76299 @wait
  Scenario Outline: Naviagting to Wiatlist confirmation page once guest user registered to macys after clicked on "JOIN THE WAITLIST" button
    Given I visit the web site as a guest user
    When I click on SignIn button on Home page
    And I create a new profile
    And I visit database to insert "<dmls>" as a precondition
    And I launch beautybox about page url
    Then I see "<status>" on about page
    When I click on subscribe on about page
    Then I should see subscription popup
    When I clicked on continue button in the popup
    Then I should navigate to welcome waitlist page

    Examples:
      |dmls|status|
      |qtyegrtthanmaxqty_wlperis-1.sql|JOIN THE WAITLIST|


  @tag4 @Feature:B-76299 @CTAsection @wait
  Scenario Outline: Waitlist subscribed should be navigated to manage subscriptionce if tries to join the waitlist again
    Given I visit the web site as a guest user
    When I click on SignIn button on Home page
    And I enter an existing email id
    And I have updated user status
    #Then I visit database to insert "<dmls>" as a precondition
    And I launch beautybox about page url
    Then I see "<status>" on about page
    When I click on subscribe on about page
    Then I should navigate to welcome waitlist page
    When I launch beautybox about page url
    Then I see "<status>" on about page
    When I click on subscribe on about page
    Then I launch beautybox ManageSubscriptions page url
    Examples:
      |dmls|status|
      |qtyegrtthanmaxqty_wlperis-1.sql|JOIN THE WAITLIST|



  @tag6 @Feature:B-77498 @wait
  Scenario Outline: Verifying the manage subscription page of signedin user once clicked on the manage link in the waitlist confirmation email(waitlist pending status)
    Given I visit the web site as a guest user
    When I click on SignIn button on Home page
    And I enter an existing email id
    #Then I visit database to insert "<dmls>" as a precondition
    And I launch beautybox about page url
    Then I see "<status>" on about page
    When I click on subscribe on about page
    Then I should navigate to welcome waitlist page
    When I launch beautybox ManageSubscriptions page url
    Then I should not see shipping section
    And I should not see creditcard section

    Examples:
      |dmls|status|
      |qtyegrtthanmaxqty_wlperis-1.sql|JOIN THE WAITLIST|
      |qtyequaltomaxqty_wlperis-1.sql|JOIN THE WAITLIST|


  @tag7 @Feature:B-77498 @CTAsection @wait
  Scenario Outline: Verifying the manage subscription page of regitered user once clicked on the manage link in the waitlist confirmation email(waitlist status)
    Given I visit the web site as a guest user
    When I create a new profile
   # When I visit database to insert "<dmls>" as a precondition
    And I launch beautybox about page url
    Then I see "<status>" on about page
    When I click on subscribe on about page
    Then I should see subscription popup
    When I clicked on continue button in the popup
    Then I should navigate to welcome waitlist page
    When I launch beautybox ManageSubscriptions page url
    Then I should not see shipping section
    And I should not see creditcard section

    Examples:
      |dmls|status|
      |qtyegrtthanmaxqty_wlperis-1.sql|JOIN THE WAITLIST|
      |qtyequaltomaxqty_wlperis-1.sql|JOIN THE WAITLIST|

  @tag8 @Feature:B-77498 @wait
  Scenario Outline: Verifying the manage subscription page of regitered user once clicked on the manage link in the waitlist confirmation email(waitlist_Notified status)
    When I create a new profile
    # When I visit database to insert "<dmls>" as a precondition
    And I launch beautybox about page url
    Then I see "<status>" on about page
    When I click on subscribe on about page
    Then I should see subscription popup
    When I clicked on continue button in the popup
    Then I should navigate to welcome waitlist page
    And I have updated user status
    When I launch beautybox ManageSubscriptions page url
    Then I should not see shipping section
    And I should not see creditcard section

    Examples:
      |dmls|status|
      |qtyegrtthanmaxqty_wlperis-1.sql|JOIN THE WAITLIST|
      |qtyequaltomaxqty_wlperis-1.sql|JOIN THE WAITLIST|


  @wip @Feature:B-74978
  Scenario Outline: Verifying the signedin user flow from waitlist to subscribed
    Given I visit the web site as a guest user
    When I click on SignIn button on Home page
    And I enter an existing email id
   # When I visit database to insert "<dmls>" as a precondition
    And I launch beautybox about page url
    Then I see "<status>" on about page


    Examples:
      |dmls|status|
      |qtyegrtthanmaxqty_wlperis-1.sql|JOIN THE WAITLIST|
      |qtyequaltomaxqty_wlperis-1.sql|JOIN THE WAITLIST|

  @wip @Feature:B-78471
  Scenario Outline: Registered user should get Waitlist confirmation email
    Given I visit the web site as a guest user
    When I create a new profile
    When I visit database to insert "<dmls>" as a precondition
    And I launch beautybox about page url
    Then I see "<status>" on about page
    When I click on subscribe on about page
    Then I should see subscription popup
    When I clicked on continue button in the popup
    Then I should navigate to welcome waitlist page
    And User should get confirmation email to his registered email

    Examples:
      |dmls|status|
      |qtyegrtthanmaxqty_wlperis-1.sql|JOIN THE WAITLIST|
      |qtyequaltomaxqty_wlperis-1.sql|JOIN THE WAITLIST|


  @wip @Feature:B-78471
  Scenario Outline: SignedIn user should get Waitlist confirmation email
    Given I visit the web site as a guest user
    When I click on SignIn button on Home page
    And I enter an existing email id
    Then I visit database to insert "<dmls>" as a precondition
    And I launch beautybox about page url
    Then I see "<status>" on about page
    When I click on subscribe on about page
    Then I should navigate to welcome waitlist page
    And User should get confirmation email to his registered email

    Examples:
      |dmls|status|
      |qtyegrtthanmaxqty_wlperis-1.sql|JOIN THE WAITLIST|
      |qtyequaltomaxqty_wlperis-1.sql|JOIN THE WAITLIST|


  @wip @Feature:B-76299 @wait
  Scenario Outline: Subscribed user tries to join the waitlist should navigates manage subscriptions page
    Given I visit the web site as a guest user
    When I click on SignIn button on Home page
    And I enter an existing email id
   # Then I visit database to insert "<dmls>" as a precondition
    And I launch beautybox about page url
    Then I see "<status>" on about page
    When I click on subscribe on about page
    Then I should navigate to welcome waitlist page
    When I launch beautybox about page url
    Then I see "<status>" on about page
    When I click on subscribe on about page
    Then I launch beautybox ManageSubscriptions page url
    Examples:
      |dmls|status|
      |qtyegrtthanmaxqty_wlperis-1.sql|JOIN THE WAITLIST|
      |qtyequaltomaxqty_wlperis-1.sql|JOIN THE WAITLIST|


  @tag11 @Feature:B-79016 @CTAsection @XAPI
  Scenario Outline: Subscribed user tries to join the waitlist should get error message
    Given I visit the web site as a guest user
    When I click on SignIn button on Home page
    And I enter an existing email id
    And I have updated user status
    Then I visit database to insert "<dmls>" as a precondition
    And I launch beautybox about page url
    Then I see "<status>" on about page
    When I click on subscribe on about page
    Then I should navigate to welcome waitlist page
    When I launch beautybox about page url
    Then I see "<status>" on about page
    When I click on subscribe on about page
    Then I should navigate to welcome waitlist page with Error Message
    Examples:
      |dmls|status|
      |qtyegrtthanmaxqty_wlperis-1.sql|JOIN THE WAITLIST|
      |qtyequaltomaxqty_wlperis-1.sql|JOIN THE WAITLIST|


  @wip @Feature:B-79016 @XAPI
  Scenario Outline: Subscribed user tries to join the waitlist should get error message
    Given I visit the web site as a guest user
    When I click on SignIn button on Home page
    And I enter an existing email id
    And I have updated user status
    Then I visit database to insert "<dmls>" as a precondition
    When I launch beautybox about page url
    Then I see "<status>" on about page
    When I click on subscribe on about page
    Then I should navigate to welcome waitlist page
    When I launch beautybox about page url
    Then I see "<status>" on about page
    When I click on subscribe on about page
    Then I should navigate to welcome waitlist page with Error Message
    Examples:
      |dmls|status|
      |qtyegrtthanmaxqty_wlperis-1.sql|JOIN THE WAITLIST|
      |qtyequaltomaxqty_wlperis-1.sql|JOIN THE WAITLIST|

  @tag12 @Feature:B-79588 @wait
  Scenario Outline: As a registered user I should see static message of shipping on Checkout page
    Given I visit the web site as a guest user
    And I create a new profile
    And I launch beautybox about page url
    Then I see "<status>" on about page
    And I click on subscribe on about page
    When I click on add new to add shipping address on checkout
    Then I should see static message of shipping
    And I enter address "<shippingaddress>" Information
    And I click on save to save the shipping address
    When I click on add new to add creditcard on checkout
    Then I Enter "<cardtype>" Information
    Then I should see static message of shipping
    Then I enter address "<shippingaddress>" Information
    Then I click on save to save the credit card address


    Examples:
      |status       |cardtype|shippingaddress|
      |SUBSCRIBE NOW|visa    |address1       |


  @tag13 @Feature:B-79667
  Scenario Outline: Creditcard field validation of checkout page of subscribe for Create Profile user
    Given I visit the web site as a guest user
    And I create a new profile
    And I launch beautybox about page url
    Then I see "<status>" on about page
    And I click on subscribe on about page
    When I click on add new to add shipping address on checkout
    Then I enter address "<shippingaddress>" Information
    And I click on save to save the shipping address
    When I click on add new to add creditcard on checkout
    Then I Enter "<cardtype>" Information
    And I enter address "<shippingaddress>" Information
    When I click on save to save the credit card address
    Then I should see valid creditcard error message


    Examples:
      |status       |cardtype|shippingaddress|
      |SUBSCRIBE NOW|visacardwithlessnumber|address1|
      |SUBSCRIBE NOW|mastercardwithlessnumber|address1|
      |SUBSCRIBE NOW|amexcardwithlessnumber|address1|
      |SUBSCRIBE NOW|discovercardwithlessnumber|address1|
      |SUBSCRIBE NOW|visacardwithnonumber|address1|
      |SUBSCRIBE NOW|mastercardwithnonumber|address1|
      |SUBSCRIBE NOW|amexcardwithnonumber|address1|
      |SUBSCRIBE NOW|discovercardwithnonumber|address1|

  @tag14 @Feature:B-79667
  Scenario Outline: Creditcard field validation of checkout page of subscribe for Registered User
    Given I visit the web site as a guest user
    When I click on SignIn button on Home page
    And I enter an existing email id
    And I launch beautybox about page url
    Then I see "<status>" on about page
    And I click on subscribe on about page
    When I click on add new to add shipping address on checkout
    Then I enter address "<shippingaddress>" Information
    And I click on save to save the shipping address
    When I click on add new to add creditcard on checkout
    Then I Enter "<cardtype>" Information
    And I should see valid creditcard error message


    Examples:
      |status       |cardtype|shippingaddress|
      |SUBSCRIBE NOW|visacardwithlessnumber|address1|
      |SUBSCRIBE NOW|mastercardwithlessnumber|address1|
      |SUBSCRIBE NOW|amexcardwithlessnumber|address1|
      |SUBSCRIBE NOW|discovercardwithlessnumber|address1|
      |SUBSCRIBE NOW|visacardwithnonumber|address1|
      |SUBSCRIBE NOW|mastercardwithnonumber|address1|
      |SUBSCRIBE NOW|amexcardwithnonumber|address1|
      |SUBSCRIBE NOW|discovercardwithnonumber|address1|


  @tag15 @Feature:B-77697 @createsubscription @beauty
  Scenario Outline: Creditcard expiration field validation of checkout page of subscribe for Registered User
    Given I visit the web site as a guest user
    When I click on SignIn button on Home page
    And I enter an existing email id
    And I launch beautybox about page url
    Then I see "<status>" on about page
    And I click on subscribe on about page
    When I click on add new to add shipping address on checkout
    Then I enter address "<shippingaddress>" Information
    And I click on save to save the shipping address
    When I click on add new to add creditcard on checkout
    Then I Enter "<cardtype>" Information
    When I click on save to save the credit card address
    Then I should see valid creditcardexpritaion error message


    Examples:
      |status       |cardtype|shippingaddress|
      |SUBSCRIBE NOW|expiredvisacreditcard|address1|
      |SUBSCRIBE NOW|expiredmastercreditcard|address1|
      |SUBSCRIBE NOW|expiredAmexcreditcard|address1|
      |SUBSCRIBE NOW|expiredDiscoveredcreditcard|address1|

  @tag16 @Feature:B-77697 @createsubscription @beauty
  Scenario Outline: Creditcard expritaion field validation of checkout page of subscribe for Create Profile user
    Given I visit the web site as a guest user
    And I create a new profile
    And I launch beautybox about page url
    Then I see "<status>" on about page
    And I click on subscribe on about page
    When I click on add new to add shipping address on checkout
    Then I enter address "<shippingaddress>" Information
    And I click on save to save the shipping address
    When I click on add new to add creditcard on checkout
    Then I Enter "<cardtype>" Information
    And I should see valid creditcardexpritaion error message


    Examples:
      |status       |cardtype|shippingaddress|
      |SUBSCRIBE NOW|expiredvisacard|address1|
      |SUBSCRIBE NOW|expiredmastercard|address1|
      |SUBSCRIBE NOW|expiredamexcard|address1|
      |SUBSCRIBE NOW|expireddiscoveredcard|address1|

  @tag17 @Feature:B-79446 @createsubscription @beauty
  Scenario Outline: Creditcard Type field validation of checkout page to subscribe for Create Profile user
    Given I visit the web site as a guest user
    And I create a new profile
    And I launch beautybox about page url
    Then I see "<status>" on about page
    And I click on subscribe on about page
    When I click on add new to add shipping address on checkout
    Then I enter address "<shippingaddress>" Information
    And I click on save to save the shipping address
    When I click on add new to add creditcard on checkout
    Then I Enter "<cardtype>" Information
    And I enter address "<shippingaddress>" Information
    When I click on save to save the credit card address
    Then I should see valid creditcardtype error message


    Examples:
      |status       |cardtype|shippingaddress|
      |SUBSCRIBE NOW|visa_mastercard|address1|
      |SUBSCRIBE NOW|master_visacard|address1|
      |SUBSCRIBE NOW|amex_discovercard|address1|
      |SUBSCRIBE NOW|discover_amexcard|address1|


  @tag18 @Feature:B-79446 @createsubscription @beauty
  Scenario Outline: Creditcard Type field validation of checkout page to subscribe for Registered user
    Given I visit the web site as a guest user
    When I click on SignIn button on Home page
    And I enter an existing email id
    And I launch beautybox about page url
    Then I see "<status>" on about page
    And I click on subscribe on about page
    When I click on add new to add shipping address on checkout
    Then I enter address "<shippingaddress>" Information
    And I click on save to save the shipping address
    When I click on add new to add creditcard on checkout
    Then I Enter "<cardtype>" Information
    And I enter address "<shippingaddress>" Information
    When I click on save to save the credit card address
    Then I should see valid creditcardtype error message


    Examples:
      |status       |cardtype|shippingaddress|
      |SUBSCRIBE NOW|visa_mastercard|address1|
      |SUBSCRIBE NOW|master_visacard|address1|
      |SUBSCRIBE NOW|amex_discovercard|address1|
      |SUBSCRIBE NOW|discover_amexcard|address1|