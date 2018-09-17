#Author: Loyalty Bonus Offers Visibility Team
#Date Created: June 01, 2015
#Date Modified:

#VersionOne story link: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=+B-19965


Feature: MCOM :: Create loyaltyTargetedBonusOffersEnabled for Loyalty Targeted Offers

  @lbov @high @marketing @15H @manual
  Scenario: Verify Default value is set to OFF for BCOM kill switch property "loyaltyTargetedBonusOffers" to keep Loyalty Targeted Offers in dormant mode
    Given I am on mass home page
    When I navigates to kill switch configuration page
    Then I should see kill switch loyaltyTargetedBonusOffers is not checked by default
    And I should see the Loyalty Targeted Offers feature in dormant mode

  @lbov @high @marketing @15H @manual
  Scenario: Verify Default value is set to ON for BCOM kill switch property "loyaltyTargetedBonusOffers" to see Loyalty Targeted Offers on BCOM site
    Given I am on mass home page
    When I navigates to kill switch configuration page
    Then I should see kill switch loyaltyTargetedBonusOffers is checked by default
    And I should see the Loyalty Targeted Offers feature as designed on site