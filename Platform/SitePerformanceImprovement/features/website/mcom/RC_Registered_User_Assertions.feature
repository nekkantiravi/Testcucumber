Feature: Verify the shipping and delivery text fields for registered user

  @domain_Site_performance_Optimization @Registered_checkout @shipping&delivery_field
  Scenario: Verify the change shipping & delivery section for registered user
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable and gift_wrappable and gift_messageable" product to my bag using rest service that is not "registrable and available_bops"
    And I checkout until I reach the shipping page as a "signed in" user
    And I enter details on the shipping section for "registered" and continue from shipping section
    Then I verify the "change" button displays on the shipping & delivery section

  @domain_Site_performance_Optimization @Registered_checkout @shipping&delivery_field
  Scenario: Verify the required buttons available for change the address shipping & delivery section for registered user
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable and gift_wrappable and gift_messageable" product to my bag using rest service that is not "registrable and available_bops"
    And I checkout until I reach the shipping page as a "signed in" user
    And I enter details on the shipping section for "registered" and continue from shipping section
    Then I verify the "change" button displays on the shipping & delivery section
    Then I verify the "edit" button displays on the shipping & delivery section
    And I verify the "Add New" button displays on the shipping & delivery section
    And I verify the "cancel" button displays on the shipping & delivery section

  @testfix @domain_Site_performance_Optimization @Registered_checkout @shipping&delivery_field
  Scenario: Verify the edit functionality by change the existing address in shipping & delivery section for registered user
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable and gift_wrappable and gift_messageable" product to my bag using rest service that is not "registrable and available_bops"
    And I checkout until I reach the shipping page as a "signed in" user
    And I enter details on the shipping section for "registered" and continue from shipping section
    And I click the change button in "shipping & delivery" section
    And I "Edit" in the shipping & delivery section
    Then I verify that "Edited" address is displaying in shipping & delivery section

  @domain_Site_performance_Optimization @Registered_checkout @shipping&delivery_field
  Scenario: Verify the Add new functionality by adding the new address in shipping & delivery section for registered user
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable and gift_wrappable and gift_messageable" product to my bag using rest service that is not "registrable and available_bops"
    And I checkout until I reach the shipping page as a "signed in" user
    And I enter details on the shipping section for "registered" and continue from shipping section
    And I click the change button in "shipping & delivery" section
    And I "add new" in the shipping & delivery section
    Then I verify that "addedNew" address is displaying in shipping & delivery section

  @domain_Site_performance_Optimization @Registered_checkout @shipping&delivery_field
  Scenario: Verify the change option in credit card payment section working as expected for registered user
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable and gift_wrappable and gift_messageable" product to my bag using rest service that is not "registrable and available_bops"
    And I checkout until I reach the shipping page as a "signed in" user
    And I enter details on the shipping section for "registered" and continue from shipping section
    Then I get the existing card details in payment section
    And I "change" the card details in payment section
    Then I verify the see the edited details of the card is updated in payment section

  @domain_Site_performance_Optimization @Registered_checkout @shipping&delivery_field
  Scenario: Verify the addnew option in credit card payment section working as expected for registered user
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable and gift_wrappable and gift_messageable" product to my bag using rest service that is not "registrable and available_bops"
    And I checkout until I reach the shipping page as a "signed in" user
    And I enter details on the shipping section for "registered" and continue from shipping section
    Then I get the existing card details in payment section
    And I "add new" the card details in payment section
    Then I verify that another card is available in card list

  @domain_Site_performance_Optimization @Registered_checkout @shipping&delivery_field
  Scenario: Verify the buttons available when user clicks the change option in credit card payment section
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable and gift_wrappable and gift_messageable" product to my bag using rest service that is not "registrable and available_bops"
    And I checkout until I reach the shipping page as a "signed in" user
    And I enter details on the shipping section for "registered" and continue from shipping section
    Then I verify the buttons available for change the card details

  @domain_Site_performance_Optimization @Registered_checkout @shipping&delivery_field
  Scenario: Verify the alert, security code in the payment section for registered user in shipping and delivery page
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable and gift_wrappable and gift_messageable" product to my bag using rest service that is not "registrable and available_bops"
    And I checkout until I reach the shipping page as a "signed in" user
    And I enter details on the shipping section for "registered" and continue from shipping section
    Then I verify the alert in payment section
    When I re-enter card number in payment section
    Then I verify that thank you message after enter the required details
    And I verify that user able to enter security code in payment section


  @domain_Site_performance_Optimization @Registered_checkout @shipping&delivery_field
  Scenario: Verify the buttons in contact details section in payment section for registered user
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable and gift_wrappable and gift_messageable" product to my bag using rest service that is not "registrable and available_bops"
    And I checkout until I reach the shipping page as a "signed in" user
    And I enter details on the shipping section for "registered" and continue from shipping section
    Then I verify the buttons available for change the contact details


  @domain_Site_performance_Optimization @Registered_checkout @shipping&delivery_field
  Scenario: Verify the updation in contact details section in payment section for registered user
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable and gift_wrappable and gift_messageable" product to my bag using rest service that is not "registrable and available_bops"
    And I checkout until I reach the shipping page as a "signed in" user
    And I enter details on the shipping section for "registered" and continue from shipping section
    And I update the phone number in contact details
    Then I verify the phone number is updated
