########################################################################################################################
# Author: DISCOVERY REGRESSION QE
# Date Migrated: 10/04/2017
#B-49709 Autocomplete Product Recommendation
#B-63511: Autocomplete Product Recommendation - Two tier pricing changes Desktop 16O
#B-56822 && B-61463: Mcom :: Desktop :: Include promo badges in Autocomplete Product recommendation && Promotions display backend logic
#B-59618 : MCOM :: Search term doesn't persist after clicking on product
########################################################################################################################

Feature: Verify Auto complete Product Recommendation Panel Details

######################################## DOMESTIC MODE ########################################################
  @domain_discovery @feature_catsplash @use_regression @mode_domestic @migrated_to_sdt
  Scenario Outline: SubSplashPage - Verify search keyword persist on PDP page after selecting any APR product in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "MICHAEL Michael Kors" sub splash page under "HANDBAGS"
    When I enter "<keyword_to_search>" keyword in search field
    Then I should see autocomplete suggestions
    And I should see APR panel with "Best Sellers" header and "4" APR at max
    When I mouse over on the random auto suggestion text
    Then I should see APR panel updated for different suggestion text
    And I select random APR and navigate to PDP
    Examples:
      | keyword_to_search |
      | Lenox             |
      | Gold              |