Feature: As a product owner, business teams are requesting to enable users to write reviews on CHANEL PDP pages

  @Mew_UFT @release_17M @domain_selection @project_UFT
  Scenario: verify that the customer can write reviews for channel products on PDP
    Given I visit the mobile web site as a registered user
    When I type "74714" in mew search box
    And I click on search go button
    And I click on write review link
    And I verify that I am on my reviews page
    Then I enter the Review Headline Review Text & Review NickName
    | Write Review Details                                       |
    | Product fragrance extraordinary                            |
    | The products has an impeccable smell which makes it worthy |
    | rossy                                                      |
    Then I select the recommendation option
    And I select an average rating rating value
    And I click on the Review Submit button
    Then I should see the message contains "Thanks for your review" on my reviews page

  @Mew_UFT @release_17M @domain_selection @project_UFT
  Scenario: verify that the customer can write reviews for channel products through browse page on PDP
    Given I visit the mobile web site as a registered user
    When I type "Chanel" in mew search box
    And I click on search go button
    And I navigate PDP of the first product
    And I click on write review link
    And I verify that I am on my reviews page
    Then I enter the Review Headline Review Text & Review NickName
    | Write Review Details                                        |
    | Product fragrance extraordinary                             |
    | The products has an impeccable smell which makes it worthy  |
    | rossy                                                       |
    Then I select the recommendation option
    And I select an average rating rating value
    And I click on the Review Submit button
    Then I should see the message contains "Thanks for your review" on my reviews page