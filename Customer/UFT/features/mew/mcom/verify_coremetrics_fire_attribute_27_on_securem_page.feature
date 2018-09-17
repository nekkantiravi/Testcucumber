Feature: As a product owner, I would like to ensure that  "Attribute 27 (Explore) (pv_a27)" tag fires under page view tags for secure-m.

  @coremetrics @Mew_UFT @release_17K @domain_customer @project_UFT
  Scenario: Verify explore attribute 27 fired when user navigate to secure-m wallet page
    Given I visit the mobile web site as a registered user without add CC
    And I navigate back to "my account" page using mobile website
    And I navigate to "oc my wallet" page from my account page using mobile website

  @coremetrics @Mew_UFT @release_17k @domain_customer @project_UFT
  Scenario: Verify explore attribute 27 fired when user navigate to secure-m profile page
    Given I visit the mobile web site as a registered user without add CC
    And I navigate back to "my account" page using mobile website
    And I navigate to "my profile" page from my account page using mobile website

  @coremetrics @Mew_UFT @release_17K @domain_customer @project_UFT
  Scenario: Verify explore attribute 27 fired when user navigate to secure-m my address book page
    Given I visit the mobile web site as a registered user without add CC
    And I navigate back to "my account" page using mobile website
    And I navigate to "my address book" page from my account page using mobile website

  @coremetrics @Mew_UFT @release_17K @domain_customer @project_UFT
  Scenario: Verify explore attribute 27 fired when user navigate to secure-m order history page
    Given I visit the mobile web site as a registered user without add CC
    And I navigate back to "my account" page using mobile website
    And I navigate to "order status" page from my account page using mobile website