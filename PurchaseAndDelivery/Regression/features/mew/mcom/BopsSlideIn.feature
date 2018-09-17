Feature: Verify Bops Slide In functionlaity in bag

  @artifact_mew
  Scenario: Verify that store should be selected from the store Finder
    Given I visit the mobile web site as a guest user
    And I directly add an available and orderable product "1494" to my bag in mobile site
    And I click on the check availability in stores link
    And I should see a store finder

  @artifact_mew
  Scenario: Able to view the store results in store Finder and select a store
    Given I visit the mobile web site as a guest user
    And I directly add an available and orderable product "1494" to my bag in mobile site
    And I click on the check availability in stores link
    And I should see a store finder
    And I see results in store finder
    And I select a random store

  @artifact_mew
  Scenario: Able to view the store results in store Finder and select a store
    Given I visit the mobile web site as a guest user
    And I directly add an available and orderable product "1494" to my bag in mobile site
    And I click on the check availability in stores link
    And I should see a store finder

  @artifact_mew
  Scenario: Verify if the pick it up option is selected after the store is selected
    Given I visit the mobile web site as a guest user
    And I directly add an available and orderable product "1494" to my bag in mobile site
    And I click on the check availability in stores link
    And I should see a store finder
    And I see results in store finder
    And I select a random store
    And I click on Pick it up option for the product

  @artifact_mew
  Scenario: Verify if the pick it up option is selected after the store is selected for a signed In user
    Given I visit the mobile web site as a registered user
    And I directly add an available and orderable product "1494" to my bag in mobile site
    And I click on the check availability in stores link
    And I should see a store finder
    And I see results in store finder
    And I select a random store
    And I click on Pick it up option for the product


  Scenario: Verify if store is changed using the change store link
    Given I visit the mobile web site as a guest user
    When I select an "available_bops and available and orderable" product to my bag
    Then I verify Buy Online Pickup
    And I add product to my bag from PDP Page
    And I click on the check availability in stores link
    And I should see a store finder
    And I see results in store finder
    And I select a random store

  Scenario: Verify if the pick it up option is selected after the store is selected
    Given I visit the mobile web site as a guest user
    And I directly add an available and orderable product "1494" to my bag in mobile site
    And I click on the check availability in stores link
    And I should see a store finder
    And I select a nearest store using "SanFrancisco CA" search