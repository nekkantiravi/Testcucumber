Feature: Shoprunner checkout flow usecases

  @domain_marketing @project_ShopRunner @mode_domestic @priority_high @use_regression
  Scenario: Verify the shoprunner signin functionality on guest checkout page
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    And I should see the SRSignin and SRLearnmore links
    And I should see the shoprunner eligible logo on checkout page
    Then I should see the "offers FREE 2-Day Shipping & Free Returns on eligible items in your bag Learn More | Sign In" Shoprunner eligibility text
    When I click on 'SRSIGNIN' link
    And I Should see the Shoprunner overlay
    Then I signin to Shoprunner account
    And I should see the SRSignout link

  @domain_marketing @project_ShopRunner @mode_domestic @priority_high @use_regression
  Scenario: Verify the shoprunner eligibility on guest checkout page
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    Then I should see the SRSignin and SRLearnmore links
    And I should see the shoprunner eligible logo on checkout page
    And I should see the "offers FREE 2-Day Shipping & Free Returns on eligible items in your bag Learn More | Sign In" Shoprunner eligibility text

  @domain_marketing @project_ShopRunner @mode_domestic @priority_high @use_regression
  Scenario: Verify the Estimated delivery text display on guest checkout flow
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    Then I should see the "Estimated delivery is based on orders placed prior to 5PM ET/2PM PT." text display


  @domain_marketing @project_ShopRunner @mode_domestic @priority_low
  Scenario: Verify the default shipping is selected as standard shipping after Signing in to Shoprunner account
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    And I click on 'SRSIGNIN' link
    And I Should see the Shoprunner overlay
    Then I signin to Shoprunner account
    And I should see the SRSignout link
    And I should see "Standard" method selected by default

  @domain_marketing @project_ShopRunner @mode_domestic @priority_high @wip
  Scenario: Verify the user allowed to select the shoprunner shipping and able to place a shoprunner order
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    And I click on 'SRSIGNIN' link
    And I Should see the Shoprunner overlay
    And I signin to Shoprunner account
    And I should see "Standard" method selected by default
    #And I enter shipping address on guest shipping page
    And I select 'ShopRunner Free 2-Day' in shipping methods
    And I enter shipping address on guest shipping page
    And I select continue button on guest shipping page
    Then I verify the "ShopRunner Free 2-Day" shipping method text display in shipping summary section
    And I fill in payment information on guest payment page
    And I select continue button on guest payment page
    When I place an order
    Then I should see order confirmation section displayed with order details
    And I verify ShopRunner shipping method details on confirmation page

  @domain_marketing @project_ShopRunner @mode_domestic @priority_low
  Scenario: Verify the Shoprunner shipping message display in shipping section on selecting Shoprunner shipping method
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    And I click on 'SRSIGNIN' link
    And I Should see the Shoprunner overlay
    And I signin to Shoprunner account
    And I select 'ShopRunner Free 2-Day' in shipping methods
    And I enter shipping address on guest shipping page
    And I select continue button on guest shipping page
    Then I verify the "ShopRunner Free 2-Day" shipping method text display in shipping summary section
    #Then I verify shipping fee in order summary section

      #=========================Signed in checkout flow============================


  @domain_marketing @project_ShopRunner @mode_domestic @priority_high @use_regression
  Scenario: Verify the shoprunner signin functionality on signedin checkout page
    Given I visit the web site as a registered user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as a "signedIn" user
    Then I should see the SRSignin and SRLearnmore links
    And I should see the shoprunner eligible logo on checkout page
    And I should see the "offers FREE 2-Day Shipping & Free Returns on eligible items in your bag Learn More | Sign In" Shoprunner eligibility text
    When I click on 'SRSIGNIN' link
    And I Should see the Shoprunner overlay
    Then I signin to Shoprunner account
    And I should see the SRSignout link

  @domain_marketing @project_ShopRunner @mode_domestic @priority_high @use_regression
  Scenario: Verify the shoprunner eligibility on signedIn checkout page
    Given I visit the web site as a registered user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as a "signedIn" user
    Then I should see the SRSignin and SRLearnmore links
    And I should see the shoprunner eligible logo on checkout page
    And I should see the "offers FREE 2-Day Shipping & Free Returns on eligible items in your bag Learn More | Sign In" Shoprunner eligibility text


  @domain_marketing @project_ShopRunner @mode_domestic @priority_high @use_regression
  Scenario: Verify the Estimated delivery text display on signedIn checkout flow
    Given I visit the web site as a registered user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as a "signedIn" user
    Then I should see the "Estimated delivery is based on orders placed prior to 5PM ET/2PM PT." text display


  @domain_marketing @project_ShopRunner @mode_domestic @priority_low
  Scenario: Verify the default shipping is selected as standard shipping after Signing in to Shoprunner account on signedin checkout
    Given I visit the web site as a registered user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as a "signedIn" user
    And I click on 'SRSIGNIN' link
    And I Should see the Shoprunner overlay
    Then I signin to Shoprunner account
    And I should see the SRSignout link
    And I should see "Standard" method selected by default


  @domain_marketing @project_ShopRunner @mode_domestic @priority_high @wip
  Scenario: Verify the user allowed to select the shoprunner shipping and able to place an shoprunner order on signedin checkout
    Given I visit the web site as a registered user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as a "signedIn" user
    And I click on 'SRSIGNIN' link
    And I Should see the Shoprunner overlay
    And I signin to Shoprunner account
    And I select shoprunner in shipping methods
    And I verify shipping fee in order summary section
    And I verify the selected shipping method details in shipping summary section
    And I checkout until I reach the order confirmation page as a "signedIn" user
    Then I should see order confirmation section displayed with order details
    And I verify ShopRunner shipping method details on confirmation page

