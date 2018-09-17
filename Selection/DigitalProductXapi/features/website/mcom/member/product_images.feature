Feature: Product Images scenarios

  @service_only
  Scenario: Verify pdp-xapi service against fcc service for product images and video Product has primary and alternate images and video id
    Given I call pdp-xapi service for "802011" product
    Then I verify pdp-xapi service of "802011" against fcc service for product images and video

  @service_only
  Scenario: Verify pdp-xapi service against fcc service for product images and video Product has primary and alternate images and video id is not
  completely digits
    Given I call pdp-xapi service for "728155" product
    Then I verify pdp-xapi service of "728155" against fcc service for product images and video

  @service_only
  Scenario: Verify pdp-xapi service against fcc service for product images and video Product has primary, no alternate image and video id
    Given I call pdp-xapi service for "2113567" product
    Then I verify pdp-xapi service of "2113567" against fcc service for product images and video

  @service_only
  Scenario: Verify pdp-xapi service for product images and selected color images
    Given I call pdp-xapi service for "2426172" product
    Then I verify pdp-xapi service of "2426172" for product images and and selected color images

  @service_only
  Scenario: Verify pdp-xapi service for video id in selected color images
    Given I call pdp-xapi service for "2426172" product
    Then I verify pdp-xapi service for video id in selected color images
