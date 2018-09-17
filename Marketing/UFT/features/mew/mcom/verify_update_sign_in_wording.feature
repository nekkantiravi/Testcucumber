Feature: As a producer, I would like to see wording getting updated on sign-in page.

  @Mew_UFT @release_17H @domain_marketing @project_UFT @use_regression @mew_regression
  Scenario: verify that the wording is updated on m-secure Plenti sign-in page
    Given I visit the mobile web site as a guest user
    When I navigate to plenti page using mobile website
    Then I should see the expected word on m-secure plenti page
      | Expected Text |
      | account       |
      | Practices     |
      | your          |

  @Mew_UFT @release_17H @domain_marketing @project_UFT @use_regression @mew_regression
  Scenario: verify that the wording is updated on non secure-m home page footer link
    Given I visit the mobile web site as a guest user
    When I navigate to the sign-in page
    Then I should see the expected word on sign in page
      |Expected Text |
      | account      |
      | Practices    |

  @Mew_UFT @release_17H @domain_marketing @project_UFT @use_regression @mew_regression
  Scenario: verify that the wording is updated on m-secure Wallet sign-in page
    Given I visit the mobile web site as a guest user
    And I navigate the global navigation menu as follows:
      | My Account |
    Then I should see the expected word on sign in page
      | Expected Text |
      | account        |
      | Practices      |