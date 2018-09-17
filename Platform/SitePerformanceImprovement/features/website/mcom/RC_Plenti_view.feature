Feature: Validation of Plenti section view on Responsive page

  @domain_Site_performance_Optimization @Signed_in_checkout @Rc_Plenti_section
  Scenario: Plenti - Redeem Plenti User
    Given I visit the web site as a Plenti user with a "fully_enrolled" status
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    Then I validate Plenti redeem functionality through checkout as a "signed in" user

  @domain_Site_performance_Optimization @Signed_in_checkout @Rc_Plenti_section
  Scenario: Plenti as payment for for tux only item on RC page
    Given I visit the web site as a registered user
    When I add an "available" product to my bag
    And I navigate to shopping bag page
    And I checkout until I reach the shipping & payment page as a "signed in" user
    And I expand plenti panel on "responsive_checkout_signed_in" page
    And I lookup plenti id using valid usl phone number on payment page
    Then I pay whole transaction amount with usl on payment page and place order
    Then I should see USL information on order confirmation page

  @domain_Site_performance_Optimization @Signed_in_checkout @Rc_Plenti_section
  Scenario: Validation of intial Plenti view for registered user
    Given I visit the web site as a registered user
    And I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    When I checkout until I reach the shipping & payment page as a "signed in" user
    Then I validate Plenti functionality in payment page
    And I expand plenti panel on "responsive_checkout_signed_in" page
    Then I click on Cancel button to collapse the Plenti view

  @domain_Site_performance_Optimization @Signed_in_checkout @Rc_Plenti_section
  Scenario: Validation of second Plenti view for registered user
    Given I visit the web site as a registered user
    And I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    When I checkout until I reach the shipping & payment page as a "signed in" user
    And I expand plenti panel on "responsive_checkout_signed_in" page
    Then I perform all valid Plenti lookups on the "payment" page

  @domain_Site_performance_Optimization @Signed_in_checkout @Rc_Plenti_section @1
  Scenario: Validation of Plenti number validation in  Plenti view for registered user
    Given I visit the web site as a registered user
    And I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    When I checkout until I reach the shipping & payment page as a "signed in" user
    And I expand plenti panel on "responsive_checkout_signed_in" page
    When I selected "Plenti" radio button
    And I click on Search button without entering any digits in the "plenti" input field
    Then I should see error message as "Please enter your Plenti account number." for empty "plenti number"
    When I enter "10" digits in the "plenti" input field
    Then I should see error message as "Please enter a valid Plenti account number" for invalid "plenti number"

  @domain_Site_performance_Optimization @Signed_in_checkout @Rc_Plenti_section @1
  Scenario: Validation of Phone number validation in  Plenti view for registered user
    Given I visit the web site as a registered user
    And I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    When I checkout until I reach the shipping & payment page as a "signed in" user
    And I expand plenti panel on "responsive_checkout_signed_in" page
    When I selected "Phone" radio button
    And I click on Search button without entering any digits in the "phone" input field
    Then I should see error message as "Please enter a phone number." for empty "phone number"
    And I enter "8" digits in the "phone" input field
    Then I should see error message as "Please enter a valid 10-digit phone number." for invalid "phone number"

  @domain_Site_performance_Optimization @Signed_in_checkout @Rc_Plenti_section @1
  Scenario: Validation of Macys's card number in  Plenti view for registered user
    Given I visit the web site as a registered user
    And I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    When I checkout until I reach the shipping & payment page as a "signed in" user
    And I expand plenti panel on "responsive_checkout_signed_in" page
    When I selected "Macys card number" radio button
    And I click on Search button without entering any digits in the "Macys card number" input field
    Then I should see error message as "Please enter your credit card." for empty "Macys card number"
    And I enter "15" digits in the "Macys card number" input field
    Then I should see error message as "Please enter a valid card number." for invalid "Macys card number"