Feature: About page display subscription month box Feature

  @aboutDisplaySubscriptionMonthBox
  Scenario Outline: Guest user should see which month box user about to subscribe when program status is SUBSCRIBE NOW
    Given I visit the web site as a guest user
    When I visit postgresql database to insert or delete "<dmls>" as a precondition
    And I change the subscribedBy "<date>" as a precondition
    And I visit the database to insert "<availabilty>" max availabilty of next "<months>" months
    And I launch beautybox about page url
    Then I see "<status>" on about page
    And I should see subscription month box "<date>" on about page

    Examples:
      |dmls       |date|months|availabilty|status       |
      |cleanup.sql|15  |6     |3          |SUBSCRIBE NOW|

  @aboutDisplaySubscriptionMonthBox
  Scenario Outline: Guest user should not see which month box user about to subscribe when program status is WAITLIST
    Given I visit the web site as a guest user
    When I visit postgresql database to insert or delete "<dmls>" as a precondition
    And I visit the database to insert "<availabilty>" max availabilty of next "<months>" months
    And I visit the database to insert "<availabilty>" user subscriptions
    And I launch beautybox about page url
    Then I see "<status>" on about page
    And I should not see subscription month box on about page

    Examples:
      |dmls       |status           |months|availabilty|
      |cleanup.sql|JOIN THE WAITLIST|6     |3          |

  @aboutDisplaySubscriptionMonthBox
  Scenario Outline: Guest user should not see which month box user about to subscribe when program status is CLOSED
    Given I visit the web site as a guest user
    When I visit postgresql database to insert or delete "<dmls>" as a precondition
    And I visit the database to insert "<availabilty>" max availabilty of next "<months>" months
    And I visit database to change the program status as CLOSED
    And I launch beautybox about page url
    Then I see "<status>" on about page
    And I should not see subscription month box on about page

    Examples:
      |dmls       |status        |months|availabilty|
      |cleanup.sql|currently full|6     |3          |

  @aboutDisplaySubscriptionMonthBox
  Scenario Outline: program status with SUBSCRIBE NOW and non subscribed BeautyBox user should see which month box user about to subscribe
    Given I visit the web site as a guest user
    When I visit postgresql database to insert or delete "<dmls>" as a precondition
    And I change the subscribedBy "<date>" as a precondition
    And I visit the database to insert "<availabilty>" max availabilty of next "<months>" months
    And I create a new profile
    When I launch beautybox about page url
    Then I see "<status>" on about page
    And I should see subscription month box "<date>" on about page

    Examples:
      |dmls       |date|months|availabilty|status       |
      |cleanup.sql|15  |6     |3          |SUBSCRIBE NOW|

  @aboutDisplaySubscriptionMonthBox
  Scenario Outline: program status with WAITLIST and non subscribed BeautyBox user should not see which month box user about to subscribe
    Given I visit the web site as a guest user
    When I visit postgresql database to insert or delete "<dmls>" as a precondition
    And I visit the database to insert "<availabilty>" max availabilty of next "<months>" months
    And I visit the database to insert "<availabilty>" user subscriptions
    And I create a new profile
    And I launch beautybox about page url
    Then I see "<status>" on about page
    And I should not see subscription month box on about page

    Examples:
      |dmls       |status           |months|availabilty|
      |cleanup.sql|JOIN THE WAITLIST|6     |3          |

  @aboutDisplaySubscriptionMonthBox
  Scenario Outline: program status with CLOSED and non subscribed BeautyBox user should not see which month box user about to subscribe
    Given I visit the web site as a guest user
    When I visit postgresql database to insert or delete "<dmls>" as a precondition
    And I visit the database to insert "<availabilty>" max availabilty of next "<months>" months
    And I visit database to change the program status as CLOSED
    And I create a new profile
    And I launch beautybox about page url
    Then I see "<status>" on about page
    And I should not see subscription month box on about page

    Examples:
      |dmls       |status        |months|availabilty|
      |cleanup.sql|currently full|6     |3          |

  @aboutDisplaySubscriptionMonthBox
  Scenario Outline: program status with SUBSCRIBE NOW and SUBSCRIBED user should see which month box user about to subscribe
    Given I visit the web site as a guest user
    When I visit postgresql database to insert or delete "<dmls>" as a precondition
    And I change the subscribedBy "<date>" as a precondition
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
    Then I should see subscription month box "<date>" on about page

    Examples:
      |dmls       |date|status       |cardtype|shippingaddress|months|availabilty|
      |cleanup.sql|15  |SUBSCRIBE NOW|visa    |address1       |6     |3          |


  @aboutDisplaySubscriptionMonthBox
  Scenario Outline: program status with SUBSCRIBE NOW and CANCELLED user should see which monthly box user about to subscribe
    Given I visit the web site as a guest user
    When I visit postgresql database to insert or delete "<dmls>" as a precondition
    And I change the subscribedBy "<date>" as a precondition
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
    And I launch beautybox about page url
    And I should see subscription month box "<date>" on about page

    Examples:
      |dmls       |date|status       |cardtype|shippingaddress|current_status |months|availabilty|new_status|
      |cleanup.sql|15  |SUBSCRIBE NOW|visa    |address1       |SUBSCRIBED     |6     |3          |CANCELLED |

  @aboutDisplaySubscriptionMonthBox
  Scenario Outline: program status with SUBSCRIBE NOW and SUSPENDED user should see which monthly box user about to subscribe
    Given I visit the web site as a guest user
    When I visit postgresql database to insert or delete "<dmls>" as a precondition
    And I change the subscribedBy "<date>" as a precondition
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
    When I launch beautybox about page url
    Then I should see subscription month box "<date>" on about page

    Examples:
      |dmls       |date|months|availabilty|status       |cardtype|shippingaddress|
      |cleanup.sql|15  |6     |3          |SUBSCRIBE NOW|visa    |address1       |

  @aboutDisplaySubscriptionMonthBox
  Scenario Outline: program status with WAITLIST and user status with WAITLIST should not see which monthly box user about to subscribe
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
    Then I see "<subscriptionstatus>" on about page
    Then I should not see subscription month box on about page

    Examples:
      |dmls       |months|availabilty|status           |subscriptionstatus|
      |cleanup.sql|6     |3          |JOIN THE WAITLIST|WAITLIST          |

  @aboutDisplaySubscriptionMonthBox
  Scenario Outline: program status with WAITLIST and user status with SUSPENDED should not see which monthly box user about to subscribe
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
    And I update the database to see the suspended status
    Then I should not see subscription month box on about page

    Examples:
      |dmls       |months|availabilty|status            |
      |cleanup.sql|6     |3          |JOIN THE WAITLIST |

  @aboutDisplaySubscriptionMonthBox
  Scenario Outline: program status with WAITLIST and user status with SUBSCRIBED should not see which monthly box user about to subscribe
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
    And I launch beautybox about page url
    Then I see "<new_status>" on about page
    Then I should not see subscription month box on about page

    Examples:
      |dmls       |months|availabilty|status       |cardtype|shippingaddress|new_status         |
      |cleanup.sql|6     |3          |SUBSCRIBE NOW|visa    |address1       |JOIN THE WAITLIST  |

  @aboutDisplaySubscriptionMonthBox
  Scenario Outline: program status with WAITLIST and user status with CANCELLED should not see which monthly box user about to subscribe
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
    When I visit database to change the program status as WAITLIST
    And I launch beautybox about page url
    Then I see "<pgm_status>" on about page
    Then I should not see subscription month box on about page

    Examples:
      |dmls       |months|availabilty|status       |cardtype|shippingaddress|current_status |new_status|pgm_status       |
      |cleanup.sql|6     |3          |SUBSCRIBE NOW|visa    |address1       |SUBSCRIBED     |CANCELLED |JOIN THE WAITLIST|


  @aboutDisplaySubscriptionMonthBox
  Scenario Outline: program status with CLOSED and user with SUBSCRIBED status should not see which monthly box user about to subscribe
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
    And I launch beautybox about page url
    Then I see "<new_status>" on about page
    And I should not see subscription month box on about page

    Examples:
      |dmls       |months|availabilty|status       |cardtype|shippingaddress|new_status    |
      |cleanup.sql|6     |3          |SUBSCRIBE NOW|visa    |address1       |currently full|

  @aboutDisplaySubscriptionMonthBox
  Scenario Outline: program status with CLOSED and user with WAITLIST status should not see which monthly box user about to subscribe
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
    And I launch beautybox about page url
    Then I should not see subscription month box on about page

    Examples:
      |dmls       |months|availabilty|status           |
      |cleanup.sql|6     |3          |JOIN THE WAITLIST|

  @aboutDisplaySubscriptionMonthBox
  Scenario Outline: Reset back the original data after executing automation
    When I visit postgresql database to insert or delete "<records>" as a precondition
    And I visit the database to insert "<availabilty>" max availabilty of next "<months>" months

    Examples:
      |records|availabilty|months|
      |postexecution_insertdata.sql|500|6|