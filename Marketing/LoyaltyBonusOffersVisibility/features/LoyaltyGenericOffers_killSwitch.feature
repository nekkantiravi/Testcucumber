#Author: Loyalty Bonus Offers Visibility Team
#Date Created: June 01, 2015
#Date Modified:

#VersionOne story link: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-19461


Feature: MCOM :: Create loyaltyGenericBonusOffersEnabled for Loyalty Generic Offers

  @lbov @high @marketing @15H @manual
  Scenario: Verify Default value is set to OFF for BCOM kill switch property "loyaltyGenericBonusOffersEnabled" to keep Loyalty Generic Offers in dormant mode
    Given I am on mass home page
    When I navigates to kill switch configuration page
    Then I should see kill switch loyaltyGenericBonusOffersEnabled is not checked by default
    And I should see the Loyalty Generic Offers feature in dormant mode

  @lbov @high @marketing @15H @manual
  Scenario: Verify Default value is set to ON for BCOM kill switch property "loyaltyGenericBonusOffersEnabled" to see Loyalty Generic Offers on BCOM site
    Given I am on mass home page
    When I navigates to kill switch configuration page
    Then I should see kill switch loyaltyGenericBonusOffersEnabled is checked by default
    And I should see the Loyalty Generic Offers feature as designed on site