#Author: UFT team
#Date Created: 2017/01/10
#Date Signed Off:
#Feature story: B-61891
#Automation story: B-65324

Feature: Refactor to group and organize the BOPS kill switches

  @sst
  Scenario: Verify refactored BOPS Kill Switch availability and default values
    Given I login into mass portal as a valid user
    When I navigate to the "Zookeeper KS Configuration" page under cache lookup section
    Then I should find kill switches
      | ks_id                                           |  shopapp | bagapp | navapp |
      | bopsSlaEnabled                                  |  true    |        |        |
      | bopsPickupAlternateEnabled                      |  true    |        |        |
      | bopsCXFeaturesEnabled                           |  true    | true   | true   |
      | responsiveCheckoutSignedInBopsEnabled           |  true    |        |        |
      | bopsCXImprovementsPreferredStoreEndpointEnabled |          | true   | true   |
      | bops_facet_enabled                              |          |        | true   |
      | bopsCXStoreFinderComponentEnabled               |          | true   | true   |
      | bopsEnabled                                     |          | true   | true   |
      | bopsPickupSMSEnabled                            |  true    |        |        |
      | bopsRegistryEnabled                             |  true    | true   | true   |
      | bopsMobileWalkEnabled                           |  true    |        |        |
      | bopsCXImprovedStoreOverlayEnabled               |          | true   | true   |
      | bopsWalkEnabled                                 |  true    | true   | true   |
      | responsiveCheckoutBopsEnabled                   |  true    |        |        |
      | wishlistBopsEnabled                             |  false   |        |        |
      | bopsCXBagImprovedCoremetricsEnabled             |          | true   |        |
