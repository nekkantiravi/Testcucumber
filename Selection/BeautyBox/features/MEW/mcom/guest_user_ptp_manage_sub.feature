Feature: PTP Page Guest User Manage subscription Feature

  @guestUserPTPManageSubscription
  Scenario Outline: Program status with SUBSCRIBE NOW and BB user with no status should see error message when user visits BB page as a guest user
    Given I visit the web site as a guest user
    When I visit postgresql database to insert or delete "<dmls>" as a precondition
    And I visit the database to insert "<availabilty>" max availabilty of next "<months>" months
    And I launch ptp page url
    Then I see "<status>" on ptp page
    And I should see manage subscription link on ptp page
    And I click manage subscription link on ptp page
    And I create a profile
    Then I should see "<error_message>" on ptp page

    Examples:
    |dmls       |status       |months|availabilty|error_message                            |
    |cleanup.sql|SUBSCRIBE NOW|6     |3          |Sorry, you're currently not a subscriber.|

  @guestUserPTPManageSubscription
  Scenario Outline: Program status with WAITLIST and BB user with no status should see error message when user visits BB page as a guest user
    Given I visit the web site as a guest user
    When I visit postgresql database to insert or delete "<dmls>" as a precondition
    And I visit the database to insert "<availabilty>" max availabilty of next "<months>" months
    And I visit the database to insert "<availabilty>" user subscriptions
    And I launch ptp page url
    Then I see "<status>" on ptp page
    And I should see manage subscription link on ptp page
    And I click manage subscription link on ptp page
    And I create a profile
    Then I should see "<error_message>" on ptp page

    Examples:
      |dmls       |status           |months|availabilty|error_message                            |
      |cleanup.sql|JOIN THE WAITLIST|6     |3          |Sorry, you're currently not a subscriber.|

  @guestUserPTPManageSubscription
  Scenario Outline: Program status with CLOSED and BB user with no status should see error message when user visits BB page as a guest user
    Given I visit the web site as a guest user
    When I visit postgresql database to insert or delete "<dmls>" as a precondition
    And I visit the database to insert "<availabilty>" max availabilty of next "<months>" months
    And I visit database to change the program status as CLOSED
    And I launch ptp page url
    Then I see "<status>" on ptp page
    And I should see manage subscription link on ptp page
    And I click manage subscription link on ptp page
    And I create a profile
    Then I should see "<error_message>" on ptp page

    Examples:
      |dmls       |status        |months|availabilty|error_message                            |
      |cleanup.sql|currently full|6     |3          |Sorry, you're currently not a subscriber.|


  @guestUserPTPManageSubscription
  Scenario Outline: program status with SUBSCRIBE NOW and Guest user with CANCELLED status click on manage subscription to see MS page and status
    Given I visit the web site as a guest user
    When I visit postgresql database to insert or delete "<dmls>" as a precondition
    And I visit the database to insert "<availabilty>" max availabilty of next "<months>" months
    And I create a new profile
    And I launch beautybox about page url
    Then I see "<status>" on about page
    And I click on subscribe on about page
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
    And I click manage subscription link on about page
    Then I should be able to verify manageSubscription "<current_status>"
    When I click on cancel subscription button
    Then I should be able to verify manageSubscription "<new_status>"
    And I sign out of the website
    When I launch ptp page url
    And I click manage subscription link on ptp page
    And I sign in to my profile
    Then I should be able to verify manageSubscription "<new_status>"

    Examples:
      |dmls       |status       |cardtype|shippingaddress|current_status |months|availabilty|new_status|
      |cleanup.sql|SUBSCRIBE NOW|visa    |address1       |SUBSCRIBED     |6     |3          |CANCELLED |


  @guestUserPTPManageSubscription
  Scenario Outline: program status with SUBSCRIBE NOW and Guest user with SUSPENDED status click on manage subscription to see MS page and status
    Given I visit the web site as a guest user
    When I visit postgresql database to insert or delete "<dmls>" as a precondition
    And I visit the database to insert "<availabilty>" max availabilty of next "<months>" months
    And I create a new profile
    And I launch beautybox about page url
    Then I see "<status>" on about page
    And I click on subscribe on about page
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
    Then I update the database to see the suspended status
    And I see suspended status on managesubscription
    And I sign out of the website
    When I launch ptp page url
    And I click manage subscription link on ptp page
    And I sign in to my profile
    Then I should be able to verify manageSubscription "<new_status>"

    Examples:
      |dmls       |months|availabilty|status       |cardtype|shippingaddress|new_status|
      |cleanup.sql|6     |3          |SUBSCRIBE NOW|visa    |address1       |SUSPENDED |


  @guestUserPTPManageSubscription
  Scenario Outline: program status with WAITLIST and Guest user with WAITLIST status click on manage subscription to see MS page and status
    Given I visit the web site as a guest user
    When I visit postgresql database to insert or delete "<dmls>" as a precondition
    And I visit the database to insert "<availabilty>" max availabilty of next "<months>" months
    And I visit the database to insert "<availabilty>" user subscriptions
    And I launch beautybox about page url
    Then I see "<status>" on about page
    When I click on subscribe on about page
    And I create a new profile
    Then I should navigate to welcome waitlist page
    When I launch beautybox about page url
    And I click manage subscription link on ptp page
    And I sign out of the website
    When I launch ptp page url
    And I click manage subscription link on ptp page
    And I sign in to my profile
    Then I should be able to verify manageSubscription "<subscriptionstatus>"

    Examples:
      |dmls       |months|availabilty|status           |subscriptionstatus|
      |cleanup.sql|6     |3          |JOIN THE WAITLIST|WAITLIST          |


  @guestUserPTPManageSubscription
  Scenario Outline: program status with WAITLIST and Guest user with SUSPENDED status click on manage subscription to see MS page and status
    Given I visit the web site as a guest user
    When I visit postgresql database to insert or delete "<dmls>" as a precondition
    And I visit the database to insert "<availabilty>" max availabilty of next "<months>" months
    And I visit the database to insert "<availabilty>" user subscriptions
    And I create a new profile
    And I launch beautybox about page url
    Then I see "<status>" on about page
    When I click on subscribe on about page
    Then I should navigate to welcome waitlist page
    When I launch beautybox about page url
    Then I see "<status>" on about page
    When I update the database to see the suspended status
    When I click manage subscription link on about page
    And I see suspended status on managesubscription
    And I sign out of the website
    When I launch ptp page url
    And I click manage subscription link on ptp page
    And I sign in to my profile
    Then I should be able to verify manageSubscription "<subscriptionstatus>"

    Examples:
      |dmls       |months|availabilty|status            |subscriptionstatus|
      |cleanup.sql|6     |3          |JOIN THE WAITLIST |SUSPENDED         |


  @guestUserPTPManageSubscription
  Scenario Outline: program status with WAITLIST and Guest user with SUBSCRIBED status click on manage subscription to see MS page and status
    Given I visit the web site as a guest user
    When I visit postgresql database to insert or delete "<dmls>" as a precondition
    And I visit the database to insert "<availabilty>" max availabilty of next "<months>" months
    And I create a new profile
    And I launch beautybox about page url
    Then I see "<status>" on about page
    And I click on subscribe on about page
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
    When I visit database to change the program status as WAITLIST
    And I launch ptp page url
    And I click manage subscription link on ptp page
    And I sign out of the website
    And I launch ptp page url
    Then I see "<new_status>" on ptp page
    And I click manage subscription link on ptp page
    When I sign in to my profile
    Then I should be able to verify manageSubscription "<current_status>"

    Examples:
      |dmls       |months|availabilty|status       |cardtype|shippingaddress|new_status         |current_status|
      |cleanup.sql|6     |3          |SUBSCRIBE NOW|visa    |address1       |JOIN THE WAITLIST  |SUBSCRIBED    |


  @guestUserPTPManageSubscription
  Scenario Outline: program status with WAITLIST and Guest user with CANCELLED status click on manage subscription to see MS page and status
    Given I visit the web site as a guest user
    When I visit postgresql database to insert or delete "<dmls>" as a precondition
    And I visit the database to insert "<availabilty>" max availabilty of next "<months>" months
    And I create a new profile
    And I launch beautybox about page url
    Then I see "<status>" on about page
    And I click on subscribe on about page
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
    When I launch beautybox about page url
    And I click manage subscription link on about page
    Then I should be able to verify manageSubscription "<current_status>"
    When I click on cancel subscription button
    Then I should be able to verify manageSubscription "<new_status>"
    And I sign out of the website
    When I visit database to change the program status as WAITLIST
    And I launch ptp page url
    Then I see "<pgm_status>" on ptp page
    And I click manage subscription link on ptp page
    When I sign in to my profile
    Then I should be able to verify manageSubscription "<new_status>"

    Examples:
      |dmls       |months|availabilty|status       |cardtype|shippingaddress|current_status |new_status|pgm_status       |
      |cleanup.sql|6     |3          |SUBSCRIBE NOW|visa    |address1       |SUBSCRIBED     |CANCELLED |JOIN THE WAITLIST|

  @guestUserPTPManageSubscription0
  Scenario Outline: program status with CLOSED and Guest user with SUBSCRIBED status click on manage subscription to see MS page and status
    Given I visit the web site as a guest user
    When I visit postgresql database to insert or delete "<dmls>" as a precondition
    And I visit the database to insert "<availabilty>" max availabilty of next "<months>" months
    And I create a new profile
    And I launch beautybox about page url
    Then I see "<status>" on about page
    And I click on subscribe on about page
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
    When I visit database to change the program status as CLOSED
    And I launch ptp page url
    And I click manage subscription link on ptp page
    And I sign out of the website
    And I launch ptp page url
    Then I see "<new_status>" on ptp page
    And I click manage subscription link on ptp page
    When I sign in to my profile
    Then I should be able to verify manageSubscription "<current_status>"

    Examples:
      |dmls       |months|availabilty|status       |cardtype|shippingaddress|new_status    |current_status|
      |cleanup.sql|6     |3          |SUBSCRIBE NOW|visa    |address1       |currently full|SUBSCRIBED    |

  @guestUserPTPManageSubscription1
  Scenario Outline: program status with CLOSED and Guest user with WAITLIST status click on manage subscription to see MS page and status
    Given I visit the web site as a guest user
    When I visit postgresql database to insert or delete "<dmls>" as a precondition
    And I visit the database to insert "<availabilty>" max availabilty of next "<months>" months
    And I visit the database to insert "<availabilty>" user subscriptions
    And I launch beautybox about page url
    Then I see "<status>" on about page
    When I click on subscribe on about page
    And I create a new profile
    Then I should navigate to welcome waitlist page
    When I visit database to change the program status as CLOSED
    And I launch ptp page url
    And I click manage subscription link on ptp page
    And I sign out of the website
    And I launch ptp page url
    And I click manage subscription link on ptp page
    When I sign in to my profile
    Then I should be able to verify manageSubscription "<subscriptionstatus>"

    Examples:
      |dmls       |months|availabilty|status           |subscriptionstatus|
      |cleanup.sql|6     |3          |JOIN THE WAITLIST|WAITLIST          |


  @guestUserPTPManageSubscription2
  Scenario Outline: Program status with SUBSCRIBE NOW and Guest user with SUBSCRIBED status click on manage subscription to see MS page and status
    Given I visit the web site as a guest user
    When I visit postgresql database to insert or delete "<dmls>" as a precondition
    And I visit the database to insert "<availabilty>" max availabilty of next "<months>" months
    And I create a new profile
    And I launch beautybox about page url
    Then I see "<status>" on about page
    And I click on subscribe on about page
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
    And I launch ptp page url
    When I click manage subscription link on ptp page
    And I sign out of the website
    And I launch ptp page url
    When I click manage subscription link on ptp page
    And I sign in to my profile
    Then I should be able to verify manageSubscription "<current_status>"

    Examples:
      |dmls       |status       |cardtype|shippingaddress|months|availabilty|current_status|
      |cleanup.sql|SUBSCRIBE NOW|visa    |address1       |6     |3          |SUBSCRIBED    |