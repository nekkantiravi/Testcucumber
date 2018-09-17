Feature: Smart Sampling BagApp grid Desktop
  @tag
  Scenario: As a guest user, I should be able to see smart sampling grid on Bag page
    Given I visit the web site as a guest user
    When I search for "eyes"
    Then I should be in Search Landing page
    And I select a random member product
    Then I should be redirected to PDP page
    When I add product to my bag from standard PDP Page
    Then I should be redirected to ATB page
    Then I navigate to shopping bag page from add to bag page
    When I click on show samples button on bag page
    Then I should see the sample grid is displayed

  @tag
  Scenario: As a guest user, I should be able to see smart sample grid on Bag page by clicking on my bag link
    Given I visit the web site as a guest user
    When I search for "eyes"
    Then I should be in Search Landing page
    And I select a random member product
    Then I should be redirected to PDP page
    When I add product to my bag from standard PDP Page
    Then I should be redirected to ATB page
    When I navigate to shopping bag page
    And I click on show samples button on bag page
    Then I should see the sample grid is displayed

  @tag
  Scenario: As a signed in user, I should be able to see smart sampling grid on Bag page
    Given I visit the web site as a registered user
    When I search for "eyes"
    Then I should be in Search Landing page
    And I select a random member product
    Then I should be redirected to PDP page
    When I add product to my bag from standard PDP Page
    And I should be redirected to ATB page
    Then I navigate to shopping bag page from add to bag page
    When I click on show samples button on bag page
    Then I should see the sample grid is displayed

  @tag
  Scenario: As a signed in user, I should be able to see smart sample grid on Bag page by clicking on my bag link
    Given I visit the web site as a registered user
    When I search for "eyes"
    Then I should be in Search Landing page
    And I select a random member product
    Then I should be redirected to PDP page
    When I add product to my bag from standard PDP Page
    Then I should be redirected to ATB page
    When I navigate to shopping bag page
    And I click on show samples button on bag page
    Then I should see the sample grid is displayed