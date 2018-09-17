Feature: Checkout Optimization LT Checkout SignedIn Add/Edit Credit Card scenarios in RC.

  @project_responsive_checkout @domain_purchase_and_delivery @bcom_rc_signedin @coo-ll

  Scenario: verify user can add credit card as signedIn user when there is no saved credit card.
    Given I visit the web site as a registered user
    When I add a "available" product to my bag
    And I checkout until I reach the shipping page as a "signed in" user
    And I add a shipping address on shipping page for signed in user
    And I don't have a saved credit card
    Then I should be able to add a new Visa credit card during checkout in signed in

  @project_responsive_checkout @domain_purchase_and_delivery @bcom_rc_signedin @coo-ll
  Scenario: verify user can edit his credit card as signedIn user when there is a credit card.
    Given I visit the web site as a registered user
    When I add a "available" product to my bag
    And I checkout until I reach the shipping page as a "signed in" user
    And I add a shipping address on shipping page for signed in user
    Then I should be able to add a new Visa credit card during checkout in signed in
    And I do have a saved credit card
    And I click on change credit card
    Then I should be able edit saved credit card information

  @project_responsive_checkout @domain_purchase_and_delivery @bcom_rc_signedin @coo-ll
  Scenario: verify user can add credit card as signedIn user when there is a credit card.
    Given I visit the web site as a registered user
    When I add a "available" product to my bag
    And I checkout until I reach the shipping page as a "signed in" user
    And I add a shipping address on shipping page for signed in user
    Then I should be able to add a new Visa credit card during checkout in signed in
    And I do have a saved credit card
    And I click on change credit card
    Then I should be able to add a new Discover credit card during checkout in signed in


