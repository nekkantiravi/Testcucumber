Feature: Beauty Box Feature File

  @tag @cloud
  Scenario:As a guest user i should be able to verify header and footer
    Given I visit the web site as a guest user
    And sample test: I click the logo
    And sample test: I navigate to sample page

  @wip
  Scenario Outline:As a customer I want to view the status on My subscription card
    Given I visit the web site as a guest user
    Then I view the Subscription Status "<status>"as below

    Examples:
    |status|
    |SUBSCRIBED|


  @memory
  Scenario Outline:As a user I should be able to validate Memory Profile
    Given I visit the web site as a guest user
    And I launch beautybox about page url
    Then I should be able to iterate "<iterations>" steps

    Examples:
    |iterations|
    |1        |
    |4        |

  @wip  @cloud
  Scenario Outline:As a customer I want to view the
    Given I visit the web site as a guest user
    And I have "<segment_value>" for SEGMENT cookie
    When I connect to DB and insert records to see the subscribe status
    Then I view the Subscription Status "<status>"as below

    Examples:
  | segment_value |status|
  | 22805         |SUBSCRIBED|

  @wip @cloud
  Scenario Outline:As a customer I should be able to view the shipping address pre selected
    Given I visit the web site as a guest user
    And I launch beautybox about page url
    #And I navigate to given "<URL>" url
    Then I should verify the shipping address as below

    Examples:
    |URL|
    |http://172.17.3.24:8081/checkout/appLayout?renderMode=client|


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


  @wip
  Scenario: As a customer am able to ver ify  order history page on GC
    When I click on SignIn button on Home page
    When I enter an existing email id
    Then I should be able to click on My Account Page

  @tag
  Scenario: As a customer am able to verify  order details BB page
    Given I navigate to given "<URL>" url
    When I click on SignIn button on Home page
    When I enter an existing email id
    Then I should be able to click on My Account Page
    Then I should be able to verify BB Product
    And  I should be click on order details Page
    And I should be able to click on product image and it should take me to PDP Page



  @tag
  Scenario Outline: As a customer am able to verify  order details page
    Given I navigate to given "<URL>" url
    When I click on SignIn button on Home page
    When I enter an existing email id
    Then I should be able to click on My Account Page
    Then I should be able to verify order details Page
    And I click on order details page then I should land on that page
    Examples:
      | URL                               |
      | https://www.qa17codemacys.fds.com |


  @tag
  Scenario Outline: As a customer am able to verify details on order details page
    Given I navigate to given "<URL>" url
    When I click on SignIn button on Home page
    When I enter an existing email id
    Then I should be able to click on My Account Page
    And I click on order details page then I should land on that page
    Then I should verify shipping id and cancel order button
    And  I should be able to click on product image and it should take me to Product Page
    Examples:
      | URL                               |
      | https://www.qa17codemacys.fds.com |



  @tag
  Scenario Outline: As a customer am able to verify  negativer scenarios for order  page
    Given I navigate to given "<URL>" url
    When I click on SignIn button on Home page
    When I enter an existing email id
    Then I should be able to click on My Account Page
    Then I should be not able to verify cancel button and return items button
    Examples:
      | URL                               |
      | https://www.qa17codemacys.fds.com |



  @tag
  Scenario: As a customer am verify  order history for BB  MMEW
    Given I navigate to STAGE environment url
    When I click on SignIn button on Home page
    When I enter an existing email id
    Then I navigate to secure-m url
    #Then I should be able to click on My Account on MEW Page
    Then I should be able to verify order tab with an order
    Then I should be verify manage subscription button on order history
    And I should verify the image link taking me to path to purchase page




  @tag7
  Scenario: As a customer am verify  order details for BB  MMEW
    Given I navigate to STAGE environment url
    When I click on SignIn button on Home page
    When I enter an existing email id
    Then I navigate to secure-m url
     #Then I should be able to click on My Account on MEW Page
    Then I should be able to verify order tab with an order
    Then I should be verify see more details button and order details header


  @tag7
  Scenario: As a customer am verify  order details for MM BB functionality
    Given I navigate to STAGE environment url
    When I click on SignIn button on Home page
    When I enter an existing email id
    Then I navigate to secure-m url
    #Then I should be able to click on My Account on MEW Page
    Then I should be able to verify order tab with an order
    Then I should be verify see more details button and order details header
    Then I should be verify manage subscription button on order details
    And I should verify the image link taking me to path to purchase page on order details


