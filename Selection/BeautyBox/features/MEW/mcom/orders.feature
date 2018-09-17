Feature: Beauty Box order feature


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





