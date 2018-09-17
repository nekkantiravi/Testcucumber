Feature: Beauty Box Callto Action Feature File

    @bbprogramstatus
    Scenario Outline: As a logged in user I want to see subscribe now on about page
      Given I visit the web site as a guest user
      When I visit postgresql database to insert or delete "<records>" as a precondition
      Then I visit database to insert "<dmls>" as a precondition
      And I launch beautybox about page url
      Then I see "<status>" on about page

      Examples:
      |records|dmls|status|
      |BeautyBox_Subscription.sql|BB_SubscribeNow.sql|SUBSCRIBE NOW|

    @beauty
    Scenario Outline: As a logged in user I want to see subscribe now on about page
      Given I visit the web site as a guest user
      When I visit postgresql database to insert or delete "<records>" as a precondition
    #  Then I visit database to insert "<dmls>" as a precondition
      And I visit the database to insert "<availabilty>" max availabilty of next "<months>" months
      And I launch beautybox about page url
      Then I see "<status>" on about page


      Examples:
        |records|status|availabilty|months|
        |BeautyBox_Subscription.sql|SUBSCRIBE NOW|5|6|

    @beauty
    Scenario Outline: As a logged in user I want to see user to subscribe now for monthly box
      Given I visit the web site as a guest user
      When I visit postgresql database to insert or delete "<records>" as a precondition
      And I visit the database to insert "<availabilty>" max availabilty of next "<months>" months
      And I visit the database to insert "<suspend_user>" user
      Then I visit database to insert "<dmls>" as a precondition
      And I launch beautybox about page url
      Then I see "<status>" on about page

      Examples:
        |records|availabilty|months|suspend_user|dmls|status|
        |BeautyBox_Subscription.sql|5|6|1       |BB_Subscribe_Suspend.sql|SUBSCRIBE NOW|


    @bbprogramstatus
    Scenario Outline: As a logged in user I want to see user to subscribe now for monthly box
      Given I visit the web site as a guest user
      When I visit postgresql database to insert or delete "<records>" as a precondition
      Then I visit database to insert "<dmls>" as a precondition
      And I launch beautybox about page url
      Then I see "<status>" on about page

      Examples:
      |records|dmls|status|
      |BeautyBox_Subscription.sql|BB_Subscribe_Suspend.sql|SUBSCRIBE NOW|



    @bbprogramstatus
    Scenario Outline: As a logged in user I want to see user as subscribe now button to subscribe for monthly box when the WL quantity is lessthan maxquantity and wl % is -1
      Given I visit the web site as a guest user
      Then I visit database to insert "<dmls>" as a precondition
      And I launch beautybox about page url
      Then I see "<status>" on about page

      Examples:
      |dmls|status|
      |lessthanmaxqty_wlperis-1.sql|SUBSCRIBE NOW|

    @bbprogramstatus
    Scenario Outline: As a logged in user I want user to see Join Waitlist button to subscribe for monthly box when the WL quantity >= maxquantity and wl % is -1
      Given I visit the web site as a guest user
      Then I visit database to insert "<dmls>" as a precondition
      And I launch beautybox about page url
      Then I see "<status>" on about page

      Examples:
      |dmls|status|
      |qtyegrtthanmaxqty_wlperis-1.sql|JOIN THE WAITLIST|
      |qtyequaltomaxqty_wlperis-1.sql|JOIN THE WAITLIST|

    @bbprogramstatus
    Scenario Outline: As a logged in user I want to see user to subscribe for monthly box when the WL quantity is lessthan maxquantity and wl % is 0
      Given I visit the web site as a guest user
      Then I visit database to insert "<dmls>" as a precondition
      And I launch beautybox about page url
      Then I see "<status>" on about page

      Examples:
      |dmls|status|
      |lessthanmaxqty_wlperis0.sql|SUBSCRIBE NOW|

    @bbprogramstatus
    Scenario Outline: As a logged in user I want to see user to subscribe for monthly box when the WL quantity >= maxquantity and wl % is 0
      Given I visit the web site as a guest user
      Then I visit database to insert "<dmls>" as a precondition
      And I launch beautybox about page url
      Then I see "<status>" on about page

      Examples:
      |dmls|status|
      |qtygrtthanmaxqty_wlperis0.sql|currently full|
      |qtyequalmaxqty_wlperis0.sql|currently full|


    @bbprogramstatus
    Scenario Outline: As a logged in user I want to see user to subscribe for monthly box as wait list status
      Given I visit the web site as a guest user
      When I visit postgresql database to insert or delete "<records>" as a precondition
      Then I visit database to insert "<dmls>" as a precondition
      And I launch beautybox about page url
      Then I see "<status>" on about page

      Examples:
      |records|dmls|status|
      |BeautyBox_Subscription.sql|BB_WaitList.sql|JOIN THE WAITLIST|


   @bbprogramstatus
    Scenario Outline: As a logged in user I want to see user to be in waitlist for subscription
      Given I visit the web site as a guest user
      When I visit postgresql database to insert or delete "<records>" as a precondition
      Then I visit database to insert "<dmls>" as a precondition
      And I launch beautybox about page url
      Then I see "<status>" on about page

      Examples:
      |records|dmls|status|
      |BeautyBox_Subscription.sql|BB_WaitList.sql|JOIN THE WAITLIST|

    @bbprogramstatus
    Scenario Outline: As a logged in user I want user to see subscription closed status for monthly box
      Given I visit the web site as a guest user
      When I visit postgresql database to insert or delete "<records>" as a precondition
      Then I visit database to insert "<dmls>" as a precondition
      And I launch beautybox about page url
      Then I see "<status>" on about page

      Examples:
      |records|dmls|status|
      |BeautyBox_Subscription.sql|BB_CurrentlyFull.sql|currently full|

    @bbprogramstatus
    Scenario Outline: As a logged in user - user to see subscription closed status for monthly box
      Given I visit the web site as a guest user
      When I visit postgresql database to insert or delete "<records>" as a precondition
      Then I visit database to insert "<dmls>" as a precondition
      And I launch beautybox about page url
      Then I see "<status>" on about page

      Examples:
      |records|dmls|status|
      |BeautyBox_Subscription.sql|BB_CF_WL_Cancel_SP.sql|currently full|

    @aboutcta
    Scenario: As a guest user validate the CTA on about page
      Given I visit the web site as a guest user
      And I launch beautybox about page url
      Then I see the cta status on about page

    @precondition
    Scenario Outline: Reset back the original data after executing automation
    When I visit postgresql database to insert or delete "<records>" as a precondition
    And I visit the database to insert "<availabilty>" max availabilty of next "<months>" months

    Examples:
    |records|availabilty|months|
    |postexecution_insertdata.sql|500|6|


  @aboutcta
  Scenario Outline: Reset back the original data after executing automation
    When I visit postgresql database to insert or delete "<records>" as a precondition
    And I visit the database to insert "<availabilty>" max availabilty of next "<months>" months

    Examples:
      |records|availabilty|months|
      |postexecution_insertdata.sql|500|6|