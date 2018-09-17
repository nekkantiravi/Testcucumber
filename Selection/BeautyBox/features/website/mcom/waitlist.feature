Feature: Beauty Box waitlist Feature File

  @jointhewaitlist
  Scenario Outline: User should see waitlist status once the subscription list is full

    Given I visit the web site as a guest user
    Then I visit database to insert "<dmls>" as a precondition
    And I launch beautybox about page url
    And I create a new profile
    And I launch beautybox about page url
    Then I see "<status>" on about page
    And I click on subscribe now on about page
    When I click on add new to add shipping address on checkout
    Then I enter address "<shippingaddress>" Information
    Then I click on save to save the shipping address
    When I click on add new to add creditcard on checkout
    Then I Enter "<cardtype>" Information
    And I enter address "<shippingaddress>" Information
    Then I click on save to save the credit card address
    And I accept terms and conditions on subscription checkout
    And I click on continue button on create subscription
    Then I click on subscribe button to create subscription
    And I see user successfully subscribed for beautybox
    And I launch beautybox about page url
    Then I see "<updatedstatus>" on about page

    Examples:
      |dmls|status|updatedstatus|cardtype|shippingaddress|
      |regressionwaitlist_one.sql|SUBSCRIBE NOW|JOIN THE WAITLIST|visa|address1|

  @jointhewaitlist
  Scenario Outline:  New User should see waitlist status when more subscription list is full from backend

    Given I visit the web site as a guest user
    Then I visit database to insert "<dmls>" as a precondition
    And I launch beautybox about page url
    Then I see "<status>" on about page
    And I click on subscribe on about page
    And I create a new profile
    Then I should navigate to welcome waitlist page

    Examples:
      |dmls|status|
      |regressionwaitlist_two.sql|JOIN THE WAITLIST|


  @jointhewaitlist
  Scenario Outline: Signedin User joining to the WL
    Given I visit the web site as a registered user
    Then I visit database to insert "<dmls>" as a precondition
    And I launch beautybox about page url
    Then I see "<status>" on about page
    When I click on subscribe on about page
    Then I should navigate to welcome waitlist page

    Examples:
      |dmls|status|
      |qtyegrtthanmaxqty_wlperis-1.sql|JOIN THE WAITLIST|


  @jointhewaitlist
  Scenario Outline:Creating New User to join the WL from beautybox page
    Given I visit the web site as a guest user
    When I visit database to insert "<dmls>" as a precondition
    And I launch beautybox about page url
    Then I see "<status>" on about page
    When I click on subscribe on about page
    And I create a new profile
    Then I should navigate to welcome waitlist page


    Examples:
      |dmls|status|
      |qtyegrtthanmaxqty_wlperis-1.sql|JOIN THE WAITLIST|


  @jointhewaitlist
  Scenario Outline: Newly created profile user join the WL
    Given I visit the web site as a guest user
    When I click on SignIn button on Home page
    And I create a new profile
    And I visit database to insert "<dmls>" as a precondition
    And I launch beautybox about page url
    Then I see "<status>" on about page
    When I click on subscribe on about page
    Then I should navigate to welcome waitlist page

    Examples:
      |dmls|status|
      |qtyegrtthanmaxqty_wlperis-1.sql|JOIN THE WAITLIST|


  @jointhewaitlist
  Scenario Outline: navigate the user to manage subscription page tries to join WL again
    Given I visit the web site as a guest user
    And I create a new profile
    Then I visit database to insert "<dmls>" as a precondition
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
      |qtyequaltomaxqty_wlperis-1.sql|JOIN THE WAITLIST|

  @jointhewaitlist
  Scenario Outline: verifying the manage subscription page for newly joined WL user
    Given I visit the web site as a guest user
    And I create a new profile
    Then I visit database to insert "<dmls>" as a precondition
    And I launch beautybox about page url
    Then I see "<status>" on about page
    When I click on subscribe on about page
    Then I should navigate to welcome waitlist page
    When I launch beautybox ManageSubscriptions page url
    Then I should not see shipping section
    And I should not see creditcard section
    And I should not see cancel button

    Examples:
      |dmls|status|
      |qtyegrtthanmaxqty_wlperis-1.sql|JOIN THE WAITLIST|



  @jointhewaitlist
  Scenario Outline: Subscribed user tries to join the waitlist should navigates manage subscriptions page
    Given I visit the web site as a guest user
    Then I visit database to insert "<dmls_1>" as a precondition
    And I launch beautybox about page url
    Then I see "<status>" on about page
    And I click on subscribe on about page
    And I create a new profile
    When I click on add new to add shipping address on checkout
    Then I enter address "<shippingaddress>" Information
    Then I click on save to save the shipping address
    When I click on add new to add creditcard on checkout
    Then I Enter "<cardtype>" Information
    And I enter address "<shippingaddress>" Information
    Then I click on save to save the credit card address
    And I accept terms and conditions on subscription checkout
    And I click on continue button on create subscription
    Then I click on subscribe button to create subscription
    And I see user successfully subscribed for beautybox
    Then I visit database to insert "<dmls>" as a precondition
    When I launch beautybox about page url
    Then I see "<status>" on about page
    When I click on subscribe on about page
    Then I launch beautybox ManageSubscriptions page url

    Examples:
      |status       |cardtype|shippingaddress|dmls|status|dmls_1|
      |SUBSCRIBE NOW|visa    |address1       |qtyegrtthanmaxqty_wlperis-1.sql|JOIN THEWAITLIST|postexecution_insertdata.sql|


  @jointhewaitlist
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


  @jointhewaitlist
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


  @jointhewaitlist
  Scenario Outline: Reset back the original data after executing automation
    When I visit postgresql database to insert or delete "<records>" as a precondition
    And I visit the database to insert "<availabilty>" max availabilty of next "<months>" months

    Examples:
      |records|availabilty|months|
      |postexecution_insertdata.sql|500|6|

