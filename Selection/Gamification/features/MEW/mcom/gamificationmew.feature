Feature: MeW MyReviews, Swipe & Shop

  @scenario1
  Scenario: Validating myreviews from PDP as a logged in user
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | My Account |
    And I navigate to the create profile page
    And I create a new profile in mobile site
    And I navigate back to "Home" page using mobile website
    When I search using mobile website for "jeans"
    When I select a random member product using mobile website
    Then I see write a review button on PDP page and click on it
    When I see my reviews page and fill the form
    And I tab on submit button to submit the review
    Then I see successfull confirmation message
    When I click on back button
    Then I see add to bag and write a review button

  @scenario2
  Scenario: Validating myreviews from PDP as a logged in user by selecting recommended as no
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | My Account |
    And I navigate to the create profile page
    And I create a new profile in mobile site
    And I navigate back to "Home" page using mobile website
    When I search using mobile website for "jeans"
    When I select a random member product using mobile website
    Then I see write a review button on PDP page and click on it
    When I see my reviews page and fill the form
    And I tab on submit button to submit the review
    Then I see successfull confirmation message
    When I click on back button
    Then I see add to bag and write a review button

  @scenario3
  Scenario: Validating mew myreviews back button on sign in overlay
    Given I visit the mobile web site as a guest user
    When I search using mobile website for "jeans"
    When I select a random member product using mobile website
    Then I see write a review button on PDP page and click on it
    When I click on back button
    Then I see add to bag and write a review button
    And I click on back button

  @scenario4
  Scenario: MeW swipe and shop for women dresses
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop     |
      | Women    |
      | Clothing |
      | Dresses  |
    And I tab on swipe
    Then I see Swipe and Shop experience to select like and dislike products
    And I tab on back button to see Swipe
    When I tab on info icon and I see PDP
    Then I navigate to see swipe and I tab back button on swipe

  @scenario5
  Scenario: MeW swipe and shop by searching category as jeans
    Given I visit the mobile web site as a guest user
    When I search using mobile website for "jeans"
    And I tab on swipe
    Then I see Swipe and Shop experience to select like and dislike products
    And I tab on back button to see Swipe
    When I tab on info icon and I see PDP
    Then I navigate to see swipe and I tab back button on swipe


    @scenario6
    Scenario: As a guest user I want to see scarcity widget on PDP
      Given I visit the mobile web site as a guest user
      When I search using mobile website for "613985"
      Then I see scarcity widget on PDP

