Feature: Verifying contents of Brand Index Page

  Scenario Outline: Brand Index Page - Domestic|Iship|Registry - Verify content of Brand Index Page
    Given I am on brand index page in "<shopping_mode>" mode
    Then  I verify the content of Brand Index Page:
      | Featured Brands |
      | Brand Index     |
      | Brand List      |
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |
      | Registry      |

  Scenario: Brand Index Page - Domestic|Iship|Registry - Verify back to top link is displayed under brand box
    Given I am on brand index page in "<shopping_mode>" mode
    When  I select a random alphabet in brand index
    Then  I verify that navigated to appropriate brand list in the same page
    And   I verify back to top link is displayed below brand box
  Examples:
  | shopping_mode |
  | Domestic      |
  | Iship         |
  | Registry      |

  Scenario: Brand Index Page - Domestic|Iship|Registry - Verify back to top link is not displayed below Featured Brands section
    Given I am on brand index page in "<shopping_mode>" mode
    Then  I verify back to top link is not displayed below Featured Brands
  Examples:
  | shopping_mode |
  | Domestic      |
  | Iship         |
  | Registry      |

  Scenario: Brand Index Page - Domestic|Iship|Registry - Verify we have navigated to Featured Brands section after clicking on back to top link
    Given I am on brand index page in "<shopping_mode>" mode
    When  I select a random alphabet in brand index
    Then  I verify that navigated to appropriate brand list in the same page
    And   I verify back to top link is displayed below brand box
    When  I click on back to top link below brand box
    Then  I verify that navigated to Featured Brands section
  Examples:
  | shopping_mode |
  | Domestic      |
  | Iship         |
  | Registry      |

  Scenario: Brand Index Page - Domestic|Iship|Registry - Verify clicking on random brand index alphabet would take to the appropriate brand list in the same page
    Given I am on brand index page in "<shopping_mode>" mode
    When  I select a random alphabet in brand index
    Then  I verify that navigated to appropriate brand list in the same page
  Examples:
  | shopping_mode |
  | Domestic      |
  | Iship         |
  | Registry      |

  Scenario: Brand Index Page - Domestic|Iship|Registry - Verify clicking on pound(#) in brand index it would have taken to the appropriate brand list in the same page
    Given I am on brand index page in "<shopping_mode>" mode
    When  I select a pound symbol in brand index
    Then  I verify that navigated to appropriate brand list in the same page
  Examples:
  | shopping_mode |
  | Domestic      |
  | Iship         |
  | Registry      |