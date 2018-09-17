#BCOM Mobile Lean Lab
#Created: 10/18/17
#Author: Maria Ortega
#Version-one Story: B-95607

Feature: BCOM::BOPS modal verification

  @bcom_mew @B-95607 @domain_selection @product_details
  Scenario: As a user, I verify Product description on Bops and PDP match
    Given I visit the mobile web site as a guest user
    When I search for a random product
      | 67858   |
      | 495980  |
      |1435698  |
      |1117721  |
    When I select a size on PDP
    When I verify brand name and product description on PDP
    When I tap pick up in store button
    Then I verify Product description on Bops and PDP match

  @bcom_mew @B-95607 @domain_selection @product_details
  Scenario: As a user, I verify that bopsable store results display
    Given I visit the mobile web site as a guest user
    When I search for a random product
      | 67858   |
      | 495980  |
      |1435698  |
    When I select a size on PDP
    When I tap pick up in store button
    When I enter zip code "10022" and tap on search
    Then I should verify the store results display
    Then I verify store phone number is present
    And I tap on store address link
    Then I verify bops map is present

  @bcom_mew @B-95607 @domain_selection @product_details
  Scenario: As a user, I verify that the save button does not display and alert message is displayed when there is no store available
    Given I visit the mobile web site as a guest user
    When I search for a random product
      | 67858   |
      | 495980  |
      |1435698  |
    When I select a size on PDP
    When I tap pick up in store button
    When I enter zip code "0000" and tap on search
    Then I should see alert message display
    Then I verify save button does not display

  @bcom_mew @B-95607 @domain_selection @product_details
  Scenario: As a user, I verify store's location displays under bops radio button on PDP
    Given I visit the mobile web site as a guest user
    When I search for a random product
      | 67858   |
      | 495980  |
      |1435698  |
    When I select a size on PDP
    When I tap pick up in store button
    When I enter zip code "10022" and tap on search
    When I save the store's pick up location from bops modal
    When I tap on save button
    Then PDP BOPS radio button should display the saved store

  @mew2 @B-95607 @domain_selection @product_details
  Scenario: As a user, I verify distance is rendering correctly
    Given I visit the mobile web site as a guest user
    When I search for "67858"
    When I tap pick up in store button
    When I enter zip code "10001" and tap on search
    Then I should see the miles rendered
    When I enter zip code "10022" and tap on search
    Then I should see the miles rendered
    Then I should see the distance renders as expected

  @bcom_mew @B-95607 @domain_selection @product_details
  Scenario: As a user, I verify that BOPS selected store on PDP generates the store cookie
    Given I visit the mobile web site as a guest user
    When I search for a random product
      | 67858   |
      | 495980  |
      |1435698  |
    When I select a size on PDP
    When I tap pick up in store button
    When I enter zip code "10022" and tap on search
    When I get the store name
    When I tap on save button
    Then I verify cookie value renders the store number

