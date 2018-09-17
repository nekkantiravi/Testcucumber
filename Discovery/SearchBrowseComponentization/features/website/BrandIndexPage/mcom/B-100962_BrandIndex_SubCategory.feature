# Author: QE Team
# Date Created: 01/22/2018
# Story B-100962

Feature: Verifying MCOM Brand Index Brand link Brands Sub-Category page

  @SNBC_Phase4
  Scenario Outline: Verify correct url is displayed If a sub-category exists for the selected brand on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to Brands index page
    And I select "<category>" category from left navigation
    Then I click on "<brand>" brand under brands section
    Then I verify navigated URL pattern "when sub-category exists"
    And I select browse 'back' button
    Then I verify previous location is persisted
    Examples:
      | shopping_mode | category | brand  |
      | domestic      | Kitchen  | Anolon |
      | iship         | Kitchen  | Anolon |
      | registry      | Kitchen  | Anolon |

  @SNBC_Phase4
  Scenario Outline: Verify correct url is displayed If a sub-category does not exists for the selected brand on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to Brands index page
    And I select "<category>" category from left navigation
    Then I click on "<brand>" brand under brands section
    Then I verify navigated URL pattern "/shop/featured/[BrandName]-[SubCatName]"
    And I select browse 'back' button
    Then I verify previous location is persisted
    Examples:
      | shopping_mode | category | brand   |
      | domestic      | Kitchen  | Bonjour |
      | iship         | Kitchen  | Bonjour |

  @SNBC_Phase4
  Scenario Outline: Verify correct url is displayed If a sub-category does not exists for the selected brand on registry mode
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to Brands index page
    And I select "<category>" category from left navigation
    Then I click on "<brand>" brand under brands section
    Then I verify navigated URL pattern "/shop/featured/[BrandName]-[SubCatName]?mode=wedding"
    And I select browse 'back' button
    Then I verify previous location is persisted
    Examples:
      | shopping_mode | category | brand   |
      | registry      | Kitchen  | Bonjour |

  @SNBC_Phase4
  Scenario Outline: Verify correct url is displayed If a brand is having more than one word on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to Brands index page
    And I select "<category>" category from left navigation
    Then I click on "<brand>" brand under brands section
    Then I verify navigated URL pattern "when brand name has more than one words"
    And I select browse 'back' button
    Then I verify previous location is persisted
    Examples:
      | shopping_mode | category | brand          |
      | domestic      | Dining   | Alex Woo       |
      | iship         | Dining   | Alex Woo       |
      | registry      | Dining   | Abbyson Living |

  @SNBC_Phase4
  Scenario Outline: Verify when clicking on a category redirect exists then it  doesn't land on DLP page
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to Brands index page
    And I select "<category>" category from left navigation
    Then I click on "<brand>" brand under brands section
    And I verify url changes and page doesn't land on DLP page
    And I select browse 'back' button
    Then I verify previous location is persisted
    Examples:
      | shopping_mode | category | brand  |
      | domestic      | Beauty   | CHANEL |
