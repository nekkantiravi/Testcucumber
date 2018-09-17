#Author: UFT team
#Date Created: 05/18/2016
#Date Signed Off:
#Version One Card: B-79037

Feature: As a production Support developer, I would like to disable the WishlistShareEmailEnabled feature in DKS.

  @sst
  Scenario: Verify WishlistShareEmailEnabled KillSwitch is off by default
    Given I login into mass portal as a valid user
    When I navigate to the "Zookeeper KS Configuration" page under cache lookup section
    Then I should see "wishlistShareEmailEnabled" KS is available and false