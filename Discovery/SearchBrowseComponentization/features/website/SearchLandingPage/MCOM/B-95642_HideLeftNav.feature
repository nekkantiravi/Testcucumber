Feature: Verifying Left Hand Navigation functionality based on Stella attribute - "Left Hand Display"

  @feature_slp
  Scenario Outline: SLP - Domestic - Verify Left Hand Navingation is displayed with sub categories when Stella attribute - "Left Hand Display" is not sent
    Given I am on SLP for "<category_id>" category id in <shopping_mode> mode
    And   I verify the stella attribute "LEFT_NAV_DISPLAY" details
    When  I see Stella attribute is "not sent"
    Then  I should see LHN on SLP page with sub categories
    Examples:
      | shopping_mode | category_id |
      | Domestic      | 80361       |
#Note: Sub categories verifaction was pending as story #B-97423 is still in Dev

  @feature_slp
  Scenario Outline: SLP - Domestic - Verify Left Hand Navigation is not displayed when Stella attribute - "Left Hand Display" is sent as N
    Given I am on SLP for "<category_id>" category id in <shopping_mode> mode
    And   I verify the stella attribute "LEFT_NAV_DISPLAY" details
    When  I see Stella attribute is "N"
    Then  I should not see LHN on SLP page
    Examples:
      | shopping_mode | category_id |
      | Domestic      | 65545       |

  @feature_slp
  Scenario Outline: SLP - Domestic - Verify Left Hand Navingation is displayed with facets when Stella attribute - "Left Hand Display" is not sent
    Given I am on SLP for "<category_id>" category id in <shopping_mode> mode
    And   I verify the stella attribute "LEFT_NAV_DISPLAY" details
    When  I see Stella attribute is "not sent"
    Then  I should see LHN on SLP page with facets
    Examples:
      | shopping_mode | category_id |
      | Domestic      | 73253       |

  @feature_slp
  Scenario Outline: SLP - Domestic - Verify Left Hand Navigation facets is not displayed when Stella attribute - "Left Hand Display" is sent as N
    Given I am on SLP for "<category_id>" category id in <shopping_mode> mode
    And   I verify the stella attribute "LEFT_NAV_DISPLAY" details
    When  I see Stella attribute is "N"
    Then  I should not see LHN on SLP page
    Examples:
      | shopping_mode | category_id |
      | Domestic      | 65532       |

  @feature_slp
  Scenario Outline: SLP - Domestic - Verify Left Hand Navingation is displayed with sub categories and facets when Stella attribute - "Left Hand Display" is not sent
    Given I am on SLP for "<category_id>" category id in <shopping_mode> mode
    And   I verify the stella attribute "LEFT_NAV_DISPLAY" details
    When  I see Stella attribute is "not sent"
    Then  I should see LHN on SLP page with sub categories and facets
    Examples:
      | shopping_mode | category_id |
      | Domestic      | 75974       |
    #Note: Sub categories verifaction was pending as story #B-97423 is still in Dev


