Feature: OCT MEW ForeSee Regression scenarios

  @signin_co
  Scenario: Mew site browse and sign-in checkout
    Given I visit the mobile web site as a guest user
    When I add an "available and orderable" product to my bag using mobile website
    And I checkout until I reach the shipping page using mobile website as a "guest" user
    And I enter shipping address on guest shipping page using mobile website
    And I select gift options on shipping page
    And I select continue button on guest shipping page using mobile website
    Then I checkout until I reach the order confirmation page using mobile website as a "guest" user

