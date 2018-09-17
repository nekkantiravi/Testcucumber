# Author: BGV QE Team
# Date Created :
# Date Modified :
# Date Signed Off: TBD

Feature:C2 :: P2 :: BCOM :: Browse Grid Variations automation scenarios for DLP Page

##############################################################################################################
# Story B-10372 : C2 P2 :: Browse :: BCOM :: CAP :: Default Grid :: Quick Peek Icon/Alt image
# VersionOne Link: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-10372
##############################################################################################################

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: Dynamic Landing page - Verify static quick peek icon (quick peek icon needs to display without hovering on product image) on product images in ISHIP & DOMESTIC & REGISTRY mode
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    And I verify that the quick peek icon on product image without hovering on product image
    Examples:
      | shopping_mode |
      | normal        |
      | registry      |
      | iship         |

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: Dynamic Landing page - Verify quick peek tool tip is displaying when hovered on quick peek icon in ISHIP & DOMESTIC & REGISTRY mode
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    And I verify that the quick peek icon on product image without hovering on product image
    Then I should see quick peek tool tip "Quick Peek" message on page
    Examples:
      | shopping_mode |
      | normal        |
      | registry      |
      | iship         |


  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: Dynamic Landing page - Verify video icon is suppressed below the product thumbnail in ISHIP & DOMESTIC & REGISTRY mode
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    And I verify that the quick peek icon on product image without hovering on product image
    Then I Verify video icon is not displayed below product thumbnail on page
    Examples:
      | shopping_mode |
      | normal        |
      | registry      |
      | iship         |

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic @wip
  Scenario Outline: Dynamic Landing page - Verify first sequenced alt image is displayed without fade when mouse hovered on product image in ISHIP & DOMESTIC & REGISTRY mode
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    When I hovered on a product thumbnail image which has alt image
    And I should see first sequenced alt image is hold without fade
    When I hovered off on same product image icon
    Then I should see default product thumbnail image for same product
    Examples:
      | shopping_mode |
      | normal        |
      | registry      |
      | iship         |

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP - Verify Quick Peek button selection and overlay close button selection on desktop in DOMESTIC & ISHIP mode
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    When I select the quick peek of random product
    Then I verify that quick peek is displayed
    And I verify that default attributes on new quick peek overlay
    When I select X button on new quick peek overlay
    Then I verify that quick peek is not displayed
    When I select 'multiple' facet value from 'any' facet sections
    And I select the quick peek of random product
    Then I verify that quick peek is displayed
    And I verify that default attributes on new quick peek overlay
    When I select anywhere outside of new quick peek overlay
    Then I verify that quick peek is not displayed
    Examples:
      | shopping_mode |
      | normal        |
      | registry      |
      | iship         |
