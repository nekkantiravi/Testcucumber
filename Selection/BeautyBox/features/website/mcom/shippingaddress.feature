Feature: Beauty Box subscription Feature File

  @shippingaddressvalidation
  Scenario Outline: As a registered user I want to add invalid shipping address to check the shipping address validation
    Given I visit the web site as a guest user
    And I create a new profile
    And I launch beautybox about page url
    Then I see "<status>" on about page
    And I click on subscribe on about page
    When I click on add new to add shipping address on checkout
    Then I enter address "<shippingaddress>" Information
    Then I click on save to save the shipping address
    And I see error message to the user

    Examples:
    |status       |shippingaddress  |
    |SUBSCRIBE NOW|invalidAddress   |
    |SUBSCRIBE NOW|invalidAddress_2 |
    |SUBSCRIBE NOW|invalidAddress_3 |
    |SUBSCRIBE NOW|invalidAddress_4 |
    |SUBSCRIBE NOW|invalidAddress_5 |
    |SUBSCRIBE NOW|invalidAddress_6 |
    |SUBSCRIBE NOW|invalidAddress_7 |
    |SUBSCRIBE NOW|invalidAddress_8 |
    |SUBSCRIBE NOW|invalidAddress_9 |
    |SUBSCRIBE NOW|invalidAddress_10 |
    |SUBSCRIBE NOW|invalidAddress_11 |
    |SUBSCRIBE NOW|invalidAddress_12 |
    |SUBSCRIBE NOW|invalidAddress_13 |


  @shippingaddressvalidation
  Scenario Outline: As a registered user I should see static message of shipping on Checkout page
    Given I visit the web site as a guest user
    And I create a new profile
    And I launch beautybox about page url
    Then I see "<status>" on about page
    And I click on subscribe on about page
    When I click on add new to add shipping address on checkout
    And I enter address "<shippingaddress>" Information
    And I click on save to save the shipping address
    When I click on add new to add creditcard on checkout
    Then I Enter "<cardtype>" Information
    Then I enter address "<shippingaddress>" Information
    Then I click on save to save the credit card address


    Examples:
      |status       |cardtype|shippingaddress|
      |SUBSCRIBE NOW|visa    |address1       |


  @shippingaddressvalidation
  Scenario Outline: Reset back the original data after executing automation
    When I visit postgresql database to insert or delete "<records>" as a precondition
    And I visit the database to insert "<availabilty>" max availabilty of next "<months>" months

    Examples:
      |records|availabilty|months|
      |postexecution_insertdata.sql|500|6|