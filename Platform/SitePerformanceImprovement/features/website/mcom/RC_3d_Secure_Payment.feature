Feature: As a SA i want to verify the existing flow working as expected for 3d secure payment checkout

  @domain_Site_performance_Optimization @guest @RC_checkout
  Scenario: Place a 3d secure order with visa card as a guest user
    Given I visit the website as a guest user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the payment page as an "guest" user
    And I add 3d secure "Visa" as my card type and checkout
    And I enter the 3d secure credentials
    Then Order should be placed successfully

  @domain_Site_performance_Optimization @guest @RC_checkout
  Scenario: Place a 3d secure order with master card as a guest user
    Given I visit the website as a guest user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the payment page as an "guest" user
    And I add 3d secure "MasterCard" as my card type and checkout
    And I enter the 3d secure credentials
    Then Order should be placed successfully

  @domain_Site_performance_Optimization @signedin @RC_checkout
  Scenario: Place a 3d secure order with visa card as a signed in user
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the shipping & payment page as a "signed in" user
    And I add 3d secure "Visa" as my card type and checkout
    And I enter the 3d secure credentials
    Then Order should be placed successfully

  @domain_Site_performance_Optimization @signedin @RC_checkout
  Scenario: Place a 3d secure order with master card as a signed in user
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the shipping & payment page as a "signed in" user
    And I add 3d secure "MasterCard" as my card type and checkout
    And I enter the 3d secure credentials
    Then Order should be placed successfully
