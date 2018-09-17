Feature: PTP Page Manage subscription Feature

  @PTPManageSubscription
  Scenario Outline: program status with SUBSCRIBE NOW and non subscribed BeautyBox user clicks on MS page to see error message
    Given I visit the web site as a guest user
    When I visit postgresql database to insert or delete "<dmls>" as a precondition
    And I visit the database to insert "<availabilty>" max availabilty of next "<months>" months
    And I create a new profile
    When I launch ptp page url
    Then I see "<status>" on ptp page
    When I click manage subscription link on ptp page
    Then I should see "<error_message>" on ptp page

    Examples:
      |dmls       |months|availabilty|status       |error_message                            |
      |cleanup.sql|6     |3          |SUBSCRIBE NOW|Sorry, you're currently not a subscriber.|


  @PTPManageSubscription
  Scenario Outline: program status with SUBSCRIBE NOW and SUBSCRIBED user click on manage subscription to see MS page and status
    Given I visit the web site as a guest user
    When I visit postgresql database to insert or delete "<dmls>" as a precondition
    And I visit the database to insert "<availabilty>" max availabilty of next "<months>" months
    And I create a new profile
    And I navigate to my address book page
    And I add 1 shipping address to the address book page
    And I launch beautybox about page url
    Then I see "<status>" on about page
    And I click on subscribe on about page
    When I click on add new to add creditcard on checkout
    Then I Enter "<cardtype>" Information
    And I enter address "<shippingaddress>" Information
    Then I click on save to save the credit card address
    And I accept terms and conditions on subscription checkout
    And I click on continue button on create subscription
    Then I click on subscribe button to create subscription
    And I see user successfully subscribed for beautybox
    When I launch ptp page url
    And I click manage subscription link on ptp page
    Then I should be able to verify manageSubscription "<subscriptionstatus>"

    Examples:
      |dmls       |status       |cardtype|shippingaddress|months|availabilty|subscriptionstatus|
      |cleanup.sql|SUBSCRIBE NOW|visa    |address1       |6     |3          |SUBSCRIBED        |


  @PTPManageSubscription
  Scenario Outline: program status with SUBSCRIBE NOW and CANCELLED user click on manage subscription to see MS page and status
    Given I visit the web site as a guest user
    When I visit postgresql database to insert or delete "<dmls>" as a precondition
    And I visit the database to insert "<availabilty>" max availabilty of next "<months>" months
    And I create a new profile
    And I navigate to my address book page
    And I add 1 shipping address to the address book page
    And I launch beautybox about page url
    Then I see "<status>" on about page
    And I click on subscribe on about page
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
    When I launch ptp page url
    And I click manage subscription link on ptp page
    Then I should be able to verify manageSubscription "<new_status>"

    Examples:
      |dmls       |status       |cardtype|shippingaddress|current_status |months|availabilty|new_status|
      |cleanup.sql|SUBSCRIBE NOW|visa    |address1       |SUBSCRIBED     |6     |3          |CANCELLED |

  @PTPManageSubscription
  Scenario Outline: program status with SUBSCRIBE NOW and SUSPENDED user click on manage subscription to see MS page and status
    Given I visit the web site as a guest user
    When I visit postgresql database to insert or delete "<dmls>" as a precondition
    And I visit the database to insert "<availabilty>" max availabilty of next "<months>" months
    And I create a new profile
    And I navigate to my address book page
    And I add 1 shipping address to the address book page
    And I launch beautybox about page url
    Then I see "<status>" on about page
    And I click on subscribe on about page
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
    When I launch ptp page url
    And I click manage subscription link on ptp page
    Then I should be able to verify manageSubscription "<new_status>"

    Examples:
      |dmls       |months|availabilty|status       |cardtype|shippingaddress|new_status|
      |cleanup.sql|6     |3          |SUBSCRIBE NOW|visa    |address1       |SUSPENDED |


  @PTPManageSubscription
  Scenario Outline: program status with WAITLIST and user status with WAITLIST click on manage subscription to see MS page and status
    Given I visit the web site as a guest user
    When I visit postgresql database to insert or delete "<dmls>" as a precondition
    And I visit the database to insert "<availabilty>" max availabilty of next "<months>" months
    And I visit the database to insert "<availabilty>" user subscriptions
    And I launch beautybox about page url
    Then I see "<status>" on about page
    When I click on subscribe on about page
    And I create a new profile
    Then I should navigate to welcome waitlist page
    When I launch ptp page url
    And I click manage subscription link on ptp page
    Then I should be able to verify manageSubscription "<subscriptionstatus>"

    Examples:
      |dmls       |months|availabilty|status           |subscriptionstatus|
      |cleanup.sql|6     |3          |JOIN THE WAITLIST|WAITLIST          |

  @PTPManageSubscription
  Scenario Outline: program status with WAITLIST and user status with SUSPENDED click on manage subscription to see MS page and status
    Given I visit the web site as a guest user
    When I visit postgresql database to insert or delete "<dmls>" as a precondition
    And I visit the database to insert "<availabilty>" max availabilty of next "<months>" months
    And I visit the database to insert "<availabilty>" user subscriptions
    And I create a new profile
    And I launch beautybox about page url
    Then I see "<status>" on about page
    When I click on subscribe on about page
    Then I should navigate to welcome waitlist page
    When I launch ptp page url
    Then I see "<status>" on about page
    When I update the database to see the suspended status
    And I click manage subscription link on ptp page
    Then I should be able to verify manageSubscription "<subscriptionstatus>"

    Examples:
      |dmls       |months|availabilty|status            |subscriptionstatus|
      |cleanup.sql|6     |3          |JOIN THE WAITLIST |SUSPENDED         |

  @PTPManageSubscription
  Scenario Outline: program status with WAITLIST and user status with SUBSCRIBED click on manage subscription to see MS page and status
    Given I visit the web site as a guest user
    When I visit postgresql database to insert or delete "<dmls>" as a precondition
    And I visit the database to insert "<availabilty>" max availabilty of next "<months>" months
    And I create a new profile
    And I navigate to my address book page
    And I add 1 shipping address to the address book page
    And I launch beautybox about page url
    Then I see "<status>" on about page
    And I click on subscribe on about page
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
    Then I see "<new_status>" on about page
    When I click manage subscription link on ptp page
    Then I should be able to verify manageSubscription "<current_status>"

    Examples:
      |dmls       |months|availabilty|status       |cardtype|shippingaddress|new_status         |current_status|
      |cleanup.sql|6     |3          |SUBSCRIBE NOW|visa    |address1       |JOIN THE WAITLIST  |SUBSCRIBED    |

  @PTPManageSubscription
  Scenario Outline: program status with WAITLIST and user status with CANCELLED click on manage subscription to see MS page and status
    Given I visit the web site as a guest user
    When I visit postgresql database to insert or delete "<dmls>" as a precondition
    And I visit the database to insert "<availabilty>" max availabilty of next "<months>" months
    And I create a new profile
    And I navigate to my address book page
    And I add 1 shipping address to the address book page
    And I launch beautybox about page url
    Then I see "<status>" on about page
    And I click on subscribe on about page
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
    When I visit database to change the program status as WAITLIST
    And I launch ptp page url
    Then I see "<pgm_status>" on ptp page
    And I click manage subscription link on ptp page
    Then I should be able to verify manageSubscription "<new_status>"

    Examples:
      |dmls       |months|availabilty|status       |cardtype|shippingaddress|current_status |new_status|pgm_status       |
      |cleanup.sql|6     |3          |SUBSCRIBE NOW|visa    |address1       |SUBSCRIBED     |CANCELLED |JOIN THE WAITLIST|

  @PTPManageSubscription
  Scenario Outline: program status with WAITLIST and user with no status click on manage subscription to see error message
    Given I visit the web site as a guest user
    When I visit postgresql database to insert or delete "<dmls>" as a precondition
    And I visit the database to insert "<availabilty>" max availabilty of next "<months>" months
    And I visit the database to insert "<availabilty>" user subscriptions
    When I visit database to change the program status as WAITLIST
    And I create a new profile
    And I launch ptp page url
    Then I see "<status>" on ptp page
    And I click manage subscription link on ptp page
    Then I should see "<error_message>" on ptp page

    Examples:
      |dmls       |months|availabilty|status            |error_message                            |
      |cleanup.sql|6     |3          |JOIN THE WAITLIST |Sorry, you're currently not a subscriber.|


  @PTPManageSubscription
  Scenario Outline: program status with CLOSED and user with SUBSCRIBED status click on manage subscription to see MS page and status
    Given I visit the web site as a guest user
    When I visit postgresql database to insert or delete "<dmls>" as a precondition
    And I visit the database to insert "<availabilty>" max availabilty of next "<months>" months
    And I create a new profile
    And I navigate to my address book page
    And I add 1 shipping address to the address book page
    And I launch beautybox about page url
    Then I see "<status>" on about page
    And I click on subscribe on about page
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
    Then I see "<new_status>" on ptp page
    And I click manage subscription link on ptp page
    Then I should be able to verify manageSubscription "<current_status>"

    Examples:
      |dmls       |months|availabilty|status       |cardtype|shippingaddress|new_status    |current_status|
      |cleanup.sql|6     |3          |SUBSCRIBE NOW|visa    |address1       |currently full|SUBSCRIBED    |

  @PTPManageSubscription
  Scenario Outline: program status with CLOSED and user with WAITLIST status click on manage subscription to see MS page and status
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
    Then I should be able to verify manageSubscription "<subscriptionstatus>"

    Examples:
      |dmls       |months|availabilty|status           |subscriptionstatus|
      |cleanup.sql|6     |3          |JOIN THE WAITLIST|WAITLIST          |
