Feature: beauty box sanity scenarios feature file

  @bbsanity @zeus
  Scenario Outline: As a logged in user I want to create subscription without redirecting to myaccount
    Given I visit the web site as a guest user
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

    Examples:
    |status       |cardtype|shippingaddress|
    |SUBSCRIBE NOW|visa    |address1       |


  @bbsanity @zeus
  Scenario Outline: As a logged in user I want to see subscription tile in the MyAccount page
    Given I visit the web site as a guest user
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
    Then I see user successfully subscribed for beautybox
    And I capture subscriptionstatus on Managesubscription page
    When I click on MyAccount link on ManageSubscription page
    Then I see subscription tile in MyAccount page
    And I should see subscriptionstatus in Subscription tile
    When I click on beautybox logo in MyAccount page
    Then I see "<status>" on about page
    When I browser back
    And I see beauty box about page link on subscription tile
    When I click on beauty box about page link
    Then I see "<status>" on about page
    When I browser back
    And I see shop full size product link on subscription tile
    When I click on shop full size product link
    Then I see shop full size product page with full size products
    When I browser back
    And I see Beauty Box FAQ link
    When I click on Beauty Box FAQ link
    Then I see beauty box FAQ page
    Then I see manage subscription link in MyAccount page
    When I click on manage subscription link
    Then I see manage subscription page with subscriptionstatus


    Examples:
    |status       |cardtype|shippingaddress|
    |SUBSCRIBE NOW|visa    |address1       |


  @bbsanity
  Scenario Outline: Reset back the original data after executing automation
    When I visit postgresql database to insert or delete "<records>" as a precondition
    And I visit the database to insert "<availabilty>" max availabilty of next "<months>" months

    Examples:
      |records|availabilty|months|
      |postexecution_insertdata.sql|500|6|