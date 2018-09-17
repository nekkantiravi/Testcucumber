Feature: Checkout Optimization LT Checkout retain security code scenarios

  @project_responsive_checkout @domain_purchase_and_delivery
  Scenario:  Verify credit card security code is retained after saving billing section once
    Given I visit the web site as a guest user
    When I add a "available" product to my bag
    And I checkout until I reach the order review page as a "guest" user
    Then As a guest user I verify the security code is retained

  Scenario Outline: Verify credit card security code is retained after coming back to edit shipping section
    Given I visit the website as a guest user using rest services
    When I add a "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the order review page as a "<user_type>" user
    And As a <user_type> user I edit the shipping address
    Then As a <user_type> I verify that the payment section is in summary state
    And As a <user_type> user I verify the security code is retained

    Examples:
    |user_type |
    |guest     |
    |signed in |

  Scenario Outline: Verify credit card security code is retained after coming back to edit shipping method
    Given I visit the website as a guest user using rest services
    When I add a "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the order review page as a "<user_type>" user
    And I select and submit premium in shipping methods
    Then As a <user_type> I verify that the payment section is in summary state
    And As a <user_type> user I verify the security code is retained

    Examples:
      |user_type |
      |guest     |
      |signed in |

  Scenario Outline: Verify credit card security code is retained after adding a plenti account
    Given I visit the website as a guest user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the order review page as a "<user_type>" user
    And I lookup plenti id using valid usl phone number on payment page
    Then As a <user_type> I verify that the payment section is in summary state
    And As a <user_type> user I verify the security code is retained

    Examples:
      |user_type |
      |guest     |
      |signed in |

    Scenario: Verify credit card security code is cleared after adding a new card in signed in checkout
      Given I visit the website as a guest user using rest services
      When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
      And I checkout until I reach the order review page as a "signed in" user
      And I click on change credit card
      And I should be able to add a new Discover credit card during checkout in signed in
      Then As a signed in I verify that the payment section is in summary state
      And As a signed in user I verify the security code is not retained

    Scenario Outline: Verify credit card security code is cleared after reloading the page
      Given I visit the website as a guest user using rest services
      When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
      And I checkout until I reach the order review page as a "<user_type>" user
      And I refresh current page
      Then As a <user_type> user I verify the security code is not retained

      Examples:
        |user_type |
        |guest     |
        |signed in |