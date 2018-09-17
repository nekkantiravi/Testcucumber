Feature: Verify Category Page Header and Footer

  Scenario: Category Page - Verify Footer - BEAUTY in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "BEAUTY" category page
    Then I should verify all footer links and images are functioning properly