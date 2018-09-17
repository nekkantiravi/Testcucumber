Feature: Beauty Box order feature file

  @bborder
  Scenario: Validating Write a Review button for beauty box order which is in Delivered status as a guest user
    Given I visit order history page as a guest user
    When I select "delivered_beautybox" order as a "guest" user
    And I lookup with order number and emailaddress in OH page
    Then I verify write a review for beauty box order in the order "details" page


  @bborder
  Scenario: Validating Write a Review button for beauty box order which is in Delivered status as a signed in user
    Given I visit the web site as a guest user
    When I select "delivered_beautybox" order as a "signed" user
    And I navigate to order history page
    And I lookup with order number and emailaddress in OH page
    Then I verify write a review for beauty box order in the order "history" page

  @bborder
  Scenario Outline: Reset back the original data after executing automation
    When I visit postgresql database to insert or delete "<records>" as a precondition
    And I visit the database to insert "<availabilty>" max availabilty of next "<months>" months

    Examples:
      |records|availabilty|months|
      |postexecution_insertdata.sql|500|6|




