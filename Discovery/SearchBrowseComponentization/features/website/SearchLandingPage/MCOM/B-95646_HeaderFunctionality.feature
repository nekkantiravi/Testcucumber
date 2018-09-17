# Author: QE Team
# Date Created: 12/18/2017

Feature: Verifying Page header functionality on the SLP page

  @SNBC_Phase4 @feature_slp
  Scenario Outline: Verify category name is displayed in header on the SLP page in domestic mode
    Given I am on SLP for "<category_id>" category id in Domestic mode
    Then   I verify category name in header should be "<headerName>"
    Examples:
       | category_id | headerName         |
       | 83480       | Little Black Dress |
       | 72436       | Activewear         |

  @SNBC_Phase4 @feature_slp
  Scenario Outline: Verify facet value is displayed in the header on applying the facet on SLP page in domestic mode
    Given I am on SLP for "<category_id>" category id in Domestic mode
    When I select the first color in the Color facet
    Then I verify facet value is appended with header
    Examples:
      | category_id |
      | 83480       |
      | 72436       |

  @SNBC_Phase4 @feature_slp
  Scenario Outline: Verify single facet value is displayed in the header on applying multiple facet values under single facet name
    Given I am on SLP for "<category_id>" category id in Domestic mode
    And  I select multiple facet value from Brand facet section
    Then  I verify single facet value is appended with header
    Examples:
      | category_id |
      | 83480       |
      | 72436       |

  @SNBC_Phase4 @feature_slp
  Scenario Outline:Verify two facet value is displayed in the header on applying two facet values from different facet names
    Given I am on SLP for "<category_id>" category id in Domestic mode
    When  I select 'single' facet value from 'each' facet section
    Then  I verify two facet values are appended with header
    Examples:
      | category_id |
      | 83480       |
      | 72436       |