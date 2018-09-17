Feature: Verify recommendations on PDP responsive page

  @wip @gene
  Scenario Outline: Verify vertical recommendations on PDP pages for member product
    #Given I visit the home page on <device> as a guest user
    #When I navigate directly to "member" PDP site mode on <device>
    Then I verify "vertical" recommendation panel on <device>
    And I should see recommendation products in "vertical" panel on <device>
    And I should see scrolling for "vertical" recommendation panel on <device>
    Then I verify navigation to the corresponding PDP from "vertical" panel on <device>
    Examples:
      |device  |
      |desktop |
    #  |mobile  |
    #  |tablet  |

  @wip
  Scenario Outline: Verify horizontal recommendations on PDP pages for member product
    Given I visit the home page on <device> as a guest user
    When I navigate directly to "member" PDP site mode on <device>
    Then I verify "horizontal" recommendation panel on <device>
    And I should see recommendation products in "horizontal" panel on <device>
    And I should see scrolling for "horizontal" recommendation panel on <device>
    Then I verify navigation to the corresponding PDP from "horizontal" panel on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      #|tablet  |

  @wip
  Scenario Outline: Verify vertical recommendations on PDP pages for master product
    Given I visit the home page on <device> as a guest user
    When I navigate directly to "master" PDP site mode on <device>
    Then I verify "vertical" recommendation panel on <device>
    And I should see recommendation products in "vertical" panel on <device>
    And I should see scrolling for "vertical" recommendation panel on <device>
    Then I verify navigation to the corresponding PDP from "vertical" panel on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
     # |tablet  |

  @wip
  Scenario Outline: Verify horizontal recommendations on PDP pages for master product
    Given I visit the home page on <device> as a guest user
    When I navigate directly to "master" PDP site mode on <device>
    Then I verify "horizontal" recommendation panel on <device>
    And I should see recommendation products in "horizontal" panel on <device>
    And I should see scrolling for "horizontal" recommendation panel on <device>
    Then I verify navigation to the corresponding PDP from "horizontal" panel on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
    #  |tablet  |

  @wip
  Scenario Outline: Verify grid recommendations on PDP pages (on for mobile;off desktop/tablet)
    Given I visit the home page on <device> as a guest user
    When I navigate directly to "member" PDP site mode on <device>

    #remove step below when no hardcoded env needed
    Then I navigate to product "2907350" on <device>
    Then I verify grid recommendation panel on <device>
    Then I verify navigation to the corresponding PDP from grid panel on <device>
    Examples:
      |device  |
    #  |desktop |
      |mobile  |
    #  |tablet  |

    @wip
  Scenario Outline: Verify recommendations on PDP ZSR pages
    Given I visit the home page on <device> as a guest user

      #remove step below when no hardcoded env needed
      Then I verify "horizontal" recommendation panel on <device>
    When I search for "zzz"
    Then I verify recommendation panel for zsr on <device>
    Examples:
      |device  |
      |desktop |
    #  |mobile  |
    #  |tablet  |

@manual
  Scenario Outline: Verify no recommendations returned
    Given I visit the home page on <device> as a guest user
    When I navigate directly to "member" PDP site mode on <device>
    Then I verify that carousel will collapse on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |

#  iShip phase 2
  @wip
  Scenario Outline: Verify PROS vertical recommendation panels on member PDP page in iShip mode
    Given I visit the web site as a guest user in "domestic" mode
    And I navigate to international context page
    When I change country to "a random country"
    And I close the welcome mat if it's visible
    When I navigate directly to "member" PDP site mode on <device>
    Then I verify "vertical" recommendation panel on <device>
    And I should see recommendation products in "vertical" panel on <device>
    And I should see scrolling for "vertical" recommendation panel on <device>
    Then I verify navigation to the corresponding PDP from "vertical" panel on <device>
    Examples:
      |device  |
      |desktop |
     # |mobile  |
     # |tablet  |

  @wip
  Scenario Outline: Verify PROS horizontal recommendation panels on member PDP page in iShip mode
    Given I visit the web site as a guest user in "domestic" mode
    And I navigate to international context page
    When I change country to "a random country"
    And I close the welcome mat if it's visible
    When I navigate directly to "member" PDP site mode on <device>
    Then I verify "horizontal" recommendation panel on <device>
    And I should see recommendation products in "horizontal" panel on <device>
    And I should see scrolling for "horizontal" recommendation panel on <device>
    Then I verify navigation to the corresponding PDP from "horizontal" panel on <device>
    Examples:
      |device  |
      |desktop |
    #  |mobile  |
    #  |tablet  |

  @wip
  Scenario Outline: Verify PROS vertical recommendation panels on master PDP page in iShip mode
    Given I visit the web site as a guest user in "domestic" mode
    And I navigate to international context page
    When I change country to "a random country"
    And I close the welcome mat if it's visible
    When I navigate directly to "master" PDP site mode on <device>
    Then I verify "vertical" recommendation panel on <device>
    And I should see recommendation products in "vertical" panel on <device>
    And I should see scrolling for "vertical" recommendation panel on <device>
    Then I verify navigation to the corresponding PDP from "vertical" panel on <device>
    Examples:
      |device  |
      |desktop |
    #  |mobile  |
    #  |tablet  |

  @wip
  Scenario Outline: Verify PROS horizontal recommendation panels on master PDP page in iShip mode
    Given I visit the web site as a guest user in "domestic" mode
    And I navigate to international context page
    When I change country to "a random country"
    And I close the welcome mat if it's visible
    When I navigate directly to "master" PDP site mode on <device>
    Then I verify "horizontal" recommendation panel on <device>
    And I should see recommendation products in "horizontal" panel on <device>
    And I should see scrolling for "horizontal" recommendation panel on <device>
    Then I verify navigation to the corresponding PDP from "horizontal" panel on <device>
    Examples:
      |device  |
      |desktop |
    #  |mobile  |
    #  |tablet  |



@wip
  Scenario Outline: Verify product description functionality in vertical recommendation panel on PDP page in Registry mode
    Given I visit the web site as a guest user in "registry" mode
    When I navigate directly to "master" PDP site mode on <device>
    Then I verify "vertical" recommendation panel on <device>
    And I should see recommendation products in "vertical" panel on <device>
    And I should see scrolling for "vertical" recommendation panel on <device>
    Then I verify navigation to the corresponding PDP from "vertical" panel on <device>
    Examples:
      |device  |
      |desktop |
    #  |mobile  |
    #  |tablet  |

  @wip
  Scenario Outline: Verify product description functionality in vertical recommendation panel on PDP page in Registry mode
    Given I visit the web site as a guest user in "registry" mode
    When I navigate directly to "master" PDP site mode on <device>
    Then I verify "horizontal" recommendation panel on <device>
    And I should see recommendation products in "horizontal" panel on <device>
    And I should see scrolling for "horizontal" recommendation panel on <device>
    Then I verify navigation to the corresponding PDP from "horizontal" panel on <device>
    Examples:
      |device  |
      |desktop |
    #  |mobile  |
    #  |tablet  |