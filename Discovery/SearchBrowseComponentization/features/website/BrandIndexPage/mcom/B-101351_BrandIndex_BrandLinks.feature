# Author: QE Team
# Date Created: 01/11/2018
# Story B-101351

Feature: Verifying all the brand links on BrandIndex page

  @SNBC_Phase4
  Scenario Outline: Verify clicking on a brand name lands on DLP page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to Brands index page
    And I click on brand link under brands section
    Then I verify page lands on DLP page for "alfani" category
    And I select browse 'back' button
    Then I verify user is redirected back to previous page
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |

  @SNBC_Phase4
  Scenario Outline: Verify clicking on a brand name lands on DLP page on registry mode
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to Brands index page
    And I click on brand link under brands section
    Then I verify page lands on DLP page for "alfani" category on registry mode
    And I select browse 'back' button
    Then I verify user is redirected back to previous page
    Examples:
      | shopping_mode |
      | registry      |

  @SNBC_Phase4
  Scenario Outline: Verify clicking on a brand name that has more than one word contains hyphen and lands on DLP page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to Brands index page
    And I click on brand link that has more than one word under brands section
    Then I verify page lands on DLP page for "american-tourister" category
    And I select browse 'back' button
    And I verify user is redirected back to previous page
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |

  @SNBC_Phase4
  Scenario Outline: Verify clicking on a brand name that has more than one word contains hyphen and lands on DLP page on registry mode
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to Brands index page
    And I click on brand link that has more than one word under brands section
    Then I verify page lands on DLP page for "american-tourister" category on registry mode
    And I select browse 'back' button
    And I verify user is redirected back to previous page
    Examples:
      | shopping_mode |
      | registry      |

  @SNBC_Phase4
  Scenario Outline: Verify clicking on a other category redirect exists and doesn't land on DLP page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to Brands index page
    And I click on brand link that has more than one word under brands section
    Then I verify page lands on DLP page for "american-tourister" category
    And I navigate to the "CHANEL" sub splash page under "BEAUTY"
    And I verify url changes and page doesn't land on DLP page
    Examples:
      | shopping_mode |
      | domestic      |
