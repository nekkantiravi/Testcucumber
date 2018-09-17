#Author: UFT team
#Date Created: 09/14/2016
#Date Signed Off:
#Version One Card: B-52326

Feature: As a product owner, I would like to ensure kill switches have the correct default values.

  @sst
  Scenario: Verify ZTailor KillSwitch is off by default
    Given I login into mass portal as a valid user
    When I navigate to the "Zookeeper KS Configuration" page under cache lookup section
    Then I should see "zTailorFeatureEnabled" KS is available and false

  @sst
  Scenario: Verify whiteHeaderNavigationEnabled KillSwitch availability for the White Header Navigation
    Given I login into mass portal as a valid user
    When I navigate to the "Zookeeper KS Configuration" page under cache lookup section
    Then I should see "whiteHeaderNavigationEnabled" KS is available and false

