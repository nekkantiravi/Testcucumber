Feature: Beauty Box Manage Subscription feature file




  @beauty
  Scenario Outline: As a customer am able to subscribe for beautybox
    Given I visit the web site as a guest user
    When I click on SignIn button on Home page
    When I enter an existing email id
    When I delete the existing subscribed record in the database
    Then I Sign out of the website
    And I launch beautybox about page url
    #When I visit postgresql database to insert or delete "<records>" as a precondition
    Then I see "<status>" on about page
    And I click on subscribe on about page
    When I enter an existing email id
    #Then I should be able to verify BB Subscription panel agreement details
    Then I should verify order "<sub_total>" ,"<order_total>" and click on "<subscribe>" button

    Examples:
      |status|sub_total|order_total|
      |SUBSCRIBE NOW|$15|$15.75|


  @beauty
  Scenario Outline: As a customer am able to verify Subscribe status on  manageSubscription Page
    Given I visit the web site as a guest user
    When I click on SignIn button on Home page
    When I enter an existing email id
    When I update the existing subscribe status record in the database
    Then I Sign out of the website
    And I launch beautybox about page url
    Then I see "<status>" on about page
    And I click on subscribe on about page
    When I enter an existing email id
    Then I should be able to verify manageSubscription "<current_status>"

    Examples:
  |status|current_status|
  |SUBSCRIBE NOW|SUBSCRIBED|



  @beauty
  Scenario Outline: As a customer am able to CANCEL on manageSubscription Page
    Given I visit the web site as a guest user
    And I launch beautybox about page url
    Then I see "<status>" on about page
    And I click on subscribe on about page
    When I enter an existing email id
    Then I should be able to verify manageSubscription "<current_status>"
    When I click on cancel subscription button
    Then I should be able to verify manageSubscription "<new_status>"

    Examples:
      |status|current_status|new_status|
      |SUBSCRIBE NOW|SUBSCRIBED|CANCELLED|



  @wip
  Scenario Outline: As a customer am able to verify differnt status on  manageSubscription
    Given I visit the web site as a guest user
    And I launch beautybox about page url
    When I visit postgresql database to insert or delete "<records>" as a precondition


    Examples:
      |status|current_status|records|
      |SUBSCRIBE NOW|SUBSCRIBED|BeautyBox_SubPending.sql|


  @beauty
  Scenario Outline: As a signedIn user I should be able to subscribe now
    Given I visit the web site as a guest user
    When I click on SignIn button on Home page
    When I enter an existing email id
    When I delete the existing subscribed record in the database
    And I launch beautybox about page url
    Then I see "<status>" on about page
    #When I visit postgresql database to insert or delete "<records>" as a precondition
    And I click on subscribe on about page
    Then I should verify order "<sub_total>" ,"<order_total>" and click on "<subscribe>" button

    Examples:
      |status|sub_total|order_total|
      |SUBSCRIBE NOW|$15|$15.75|