Feature: Message make BLM default card in Checkout

  @b-100356 @optimization_lab
  Scenario: Verify that user is prompt to make his Blm card the default card
    Given I visit the web site as a registered user
    When I add a "available" product to my bag
    And I checkout until I reach the shipping & payment page as a "signed in" user
    And I should be able to add a new Visa credit card during checkout in signed in
    Then I see 'Your default card has been successfully updated' message appears
    And I click "change" in payment summary section
    And I should be able to add a new Bloomingdale's credit card during checkout in signed in
    Then I see 'Make your Blm card default' message appears
