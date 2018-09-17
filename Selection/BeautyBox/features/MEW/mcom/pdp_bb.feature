Feature: Beauty Box Feature File



#  @tag
#  Scenario Outline:As a customer I should be able to view the shipping address
#     Given I visit the web site as a guest user
#     And I navigate to given "<URL>" url
#    And I click on the change button and edit button
#    Then I should verify the shipping address as below
#
#      Examples:
#      |URL|
#      |http://172.17.3.24:8081/checkout/appLayout?renderMode=client|

   @wip
  Scenario Outline:As a customer I should be able to view the shipping address
    Given I visit the web site as a guest user
    And I navigate to given "<URL>" url
    And I click on the change button and edit button
    Then I should verify the "<first_name>" "<last_name>" "<address>" "<city>" "<zipcode>" and "<phone>" as below

    Examples:
      |URL|                                                          |first_name| |last_name| |address| |city| |zipcode||phone|
      |http://172.17.3.24:8081/checkout/appLayout?renderMode=client| |Bob|        |Wastaken|  |Main St.||San Francisco||98323||3422333234|

  @wip
  Scenario Outline:As a customer I should be able to view the credit card details
    Given I visit the web site as a guest user
    And I navigate to given "<URL>" url
    And I click on the change


    Examples:
      |URL|
      |http://172.17.3.24:8081/checkout/appLayout?renderMode=client|

  @tag
  Scenario Outline:As a customer I want to view the BB on PDP Page
    Given I visit the web site as a guest user
    And I navigate to given "<URL>" urlz
    When I search for "3015380"
    When I edit killswitches throguh browser
    Then I should not be about to view BeautyBox link
    And I should be able to replace segment_value cookie
    When I search for "3015380"
    Then I should land on About us when I click on BeautyBox link
    Examples:
      |URL|
      |http://m2qa1.qa11codemacys.fds.com/|


  @wip
  Scenario Outline:As a customer I should be able to view the credit card and contact Info details
    Given I navigate to given "<URL>" url
    When I enter an existing email id
    Then I should be able to select a credit card
    Then I should be able to verify credit card details

    Examples:
      |URL|
      |mcom-beautydev.c4d.devops.fds.com/subscription/beautybox/checkout|

  @wip
  Scenario Outline:As a customer I should be able to verify order confirmation ,subtotal and subscribe button
    Given I navigate to given "<URL>" url
    When I enter an existing email id
    And I should be able to add RegistredCheckout cookies
    Then I should be able to verify BB Subscription panel "<sub_text>"info and checkbox
    Then I should verify order "<sub_total>" ,"<order_total>" and click on "<subscribe>" button

    Examples:
      |URL|sub_text|sub_total|order_total|
      |mcom-beautydev.c4d.devops.fds.com/subscription/beautybox/checkout|Beauty Box will ship each month from the XX to the XX.|$11.99|$12.71|


  @wip
  Scenario Outline:As a customer I should be able to select shipping address
    Given I navigate to given "<URL>" url
    When I enter an existing email id
    And I click on the change button and edit button
    Then I should be able to count the address list and select any address
    Then I should verify the shipping address as below


    Examples:
      |URL|
      |mcom-beautydev.c4d.devops.fds.com/subscription/beautybox/checkout|






