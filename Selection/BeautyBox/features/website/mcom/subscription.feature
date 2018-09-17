Feature: Beauty Box subscription feature File


    @subscription
    Scenario Outline: As a logged in user I want to subscribe for beautybox using different credit cards
      Given I visit the web site as a guest user
     # And I visit the web site as a registered user
      And I create a new profile
      # And I navigate to my address book page
      # And I add 1 shipping address to the address book page
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

      Examples:
        |status       |cardtype|shippingaddress|
        |SUBSCRIBE NOW|visa    |address1   |
        |SUBSCRIBE NOW|master  |address1   |
        |SUBSCRIBE NOW|amex    |address1   |
        |SUBSCRIBE NOW|discover|address1   |



  @subscription @zeus
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
      |SUBSCRIBE NOW|master  |address1       |
      |SUBSCRIBE NOW|amex    |address1       |
      |SUBSCRIBE NOW|discover|address1       |


  @subscription
  Scenario Outline: As a logged in user I want to cancel the subscription for beautybox
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
    When I click on cancel subscription to cancel the beautybox subscription
    And I click on yes for cancel subscription confirmation
    Then I capture subscription status after canceling the subscription
    Then I visit database to update configuration as a precondition
   # Then I Sign out of the website - this step is not required. After checking with other folks, we can wipe off this.

    Examples:
      |status       |cardtype|shippingaddress|
      |SUBSCRIBE NOW|visa    |address1   |


  @subscriptionsuspend
  Scenario Outline: As a logged in user I want to suspend the subscription for beautybox
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
    And I see user successfully subscribed for beautybox
    Then I update the database to see the suspended status
    And I see suspended status on managesubscription
 #   And I see no cancel button for suspended user
    And I click on change button to add new shipping address
    And I click on change button to add new payment card


    Examples:
      |status       |cardtype|shippingaddress|
      |SUBSCRIBE NOW|visa    |address1   |


    @subscription @regression
    Scenario Outline: As a logged in user I want to add multiple shipping and payment card information and subscribe by making second shipping and credit card as default for subscription
        Given I visit the web site as a guest user
        And I create a new profile
        And I launch beautybox about page url
        Then I see "<status>" on about page
        And I click on subscribe on about page
        When I click on add new to add shipping address on checkout
        Then I enter address "<shippingAddress>" Information
        Then I click on save to save the shipping address
        When I click on add new to add creditcard on checkout
        Then I Enter "<cardtype>" Information
        Then I enter address "<shippingAddress>" Information
        Then I click on save to save the credit card address
        And I click on change button to add new shipping address
        When I click on add new to add shipping address on checkout
        And I enter add new "<shippingAddress2>" Information
        Then I click on save to save the shipping address
        And I click on change button to add new shipping address
        Then I select address2 as my default address for subscription
        And I click on save to save the updated shipping address on cs
        And I click on change button to add new payment card
        Then I validate the updated shipping address information
        When I click on add new to add creditcard on checkout
        And I enter new payment "<cardtype2>" and billing information
        And I enter add new "<shippingAddress2>" Information
        Then I click on save to save the credit card address
        And I click on change button to add new payment card
        Then I select payment card2 as my default payment for subscription
        Then I click on save to save the credit card address on cs
        Then I validate the updated card information
        And I accept terms and conditions on subscription checkout
        And I click on continue button on create subscription
        Then I click on subscribe button to create subscription


    Examples:
      |status       |cardtype|shippingAddress|shippingAddress2|cardtype2|
      |SUBSCRIBE NOW|visa    |address1       |address2        |master   |

    @subscription @regression
    Scenario Outline: As a subscribed user I want to add second shipping address and credit card information on manage subscription
      Given I visit the web site as a guest user
      And I create a new profile
      And I launch beautybox about page url
      Then I see "<status>" on about page
      And I click on subscribe on about page
   #   Then I check the checkbox status on create subscription
      When I click on add new to add shipping address on checkout
      Then I enter address "<shippingaddress>" Information
      Then I click on save to save the shipping address
      Then I check the checkbox status on create subscription
      When I click on add new to add creditcard on checkout
      Then I Enter "<cardtype>" Information
      Then I enter address "<shippingaddress>" Information
      Then I click on save to save the credit card address
      And I accept terms and conditions on subscription checkout
      And I click on continue button on create subscription
      Then I click on subscribe button to create subscription
      And I click on change button to add new shipping address
      When I click on add new to add shipping address on checkout
      And I enter add new "<shippingAddress2>" Information
      Then I click on save to save the shipping address
      And I click on change button to add new shipping address
      Then I select address2 as my default address for subscription
      And I click on save to save the updated shipping address
      And I click on change button to add new payment card
      Then I validate the updated shipping address information
      When I click on add new to add creditcard on checkout
      And I enter new payment "<cardtype2>" and billing information
      And I enter add new "<shippingAddress2>" Information
      Then I click on save to save the credit card address
      And I click on change button to add new payment card
      Then I select payment card2 as my default payment for subscription
      And I click on save to save the updated payment card
      Then I validate the updated card information


      Examples:
        |status       |cardtype|shippingaddress|shippingAddress2|cardtype2|
        |SUBSCRIBE NOW|visa    |address1       |address2        |master   |


  @subscription
  Scenario Outline: As a new user I want to create subscription without redirecting to myaccount and validate the payment card months
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
    Then I navigate to manage subscription
    Then I click on edit payment card on beauty ms

    Examples:
      |status       |cardtype|shippingaddress|
      |SUBSCRIBE NOW|visa    |address1       |


  @subscription
  Scenario Outline: As a logged in user I want to cancel the subscription and resubscribe for beautybox
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
    When I click on cancel subscription to cancel the beautybox subscription
    And I click on yes for cancel subscription confirmation
  #  Then I capture subscription status after canceling the subscription
    Then I Sign out of the website
    When I sign in to my existing profile
    When I launch beautybox about page url
    Then I see "<status>" on about page
    And I click on subscribe on about page
    And I accept terms and conditions on subscription checkout
    And I click on continue button on create subscription
    Then I click on subscribe button to create subscription
    And I see user successfully subscribed for beautybox

    Examples:
      |status       |cardtype|shippingaddress|
      |SUBSCRIBE NOW|visa    |address1   |


  @subscription
  Scenario Outline: As a logged in user I want to subscribe by selecting top CTA button for beautybox using different credit cards
    Given I visit the web site as a guest user
    And I create a new profile
    And I launch beautybox about page url
    Then I see cta status "<status>" on about page
    And I click on top subscribe on about page
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
      |SUBSCRIBE NOW|visa    |address1   |
      |SUBSCRIBE NOW|master  |address1   |


  @subscription
  Scenario Outline: Reset back the original data after executing automation
    When I visit postgresql database to insert or delete "<records>" as a precondition
    And I visit the database to insert "<availabilty>" max availabilty of next "<months>" months

    Examples:
      |records|availabilty|months|
      |postexecution_insertdata.sql|500|6|

