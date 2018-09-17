Feature: Beauty Box creditcard validation Feature File

  @creditcardvalidation
  Scenario Outline: Creditcard validation on Checkout page with less and no number of cards
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


  @creditcardvalidation
  Scenario Outline: Creditcard validation with expired cards data on subscription Checkout page
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

  @creditcardvalidation
  Scenario Outline: Creditcard Type field validation on subscription checkout page
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

  @creditcardvalidation
  Scenario Outline: Reset back the original data after executing automation
    When I visit postgresql database to insert or delete "<records>" as a precondition
    And I visit the database to insert "<availabilty>" max availabilty of next "<months>" months

    Examples:
      |records|availabilty|months|
      |postexecution_insertdata.sql|500|6|