# Author: SNBC QE Team
# Date Created: 08/09/2017

Feature: To verify the horizontal recommendation panel on the Zero Search Results page for Domestic and Iship modes

   @in_transition @14G @priority_high @artifact_navapp  @domain_selection @use_regression @pros @bcom @snbc_comp
  Scenario: Verify product description functionality in horizontal recommendation panel on ZSR page in domestic mode
    Given I am on ZeroSearchResultPage for "jnjnsecjnc" in Domestic mode
    Then I verify that recommendation panel is displayed on ZSR page
    Then I verify recommendation product's default attributes displayed on the panel
    When I select any random recommendation product "description" on the panel
    Then I verify that landed on respective product display page
  # Notes:
  # Recommended product description and thumbnail should be available
  # Recommended product thumbnail badge text should be displayed, when available Eg: MORE COLORS
  # Ratings should display for the products based on availability
  # Current price of the product should be available
  # 1)SALE price should display before the price and both in Red color
  # 2)NOW price should display before the price and both in Red color
  # Promo text should be displayed if available

  #Display PROS panels in ISHIP mode
  @in_transition @14H @priority_high @artifact_navapp @mode_iship @domain_selection @use_regression @pros @bcom @snbc_comp
  Scenario: Verify horizontal recommendations panel on ZSR page in iShip mode
    Given I am on ZeroSearchResultPage for "jnjnsecjnc" in Iship mode
    Then I verify that recommendation panel is displayed on ZSR page


