Feature: Registry Search on Registry Related Pages

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: For Signed in user, validate the registry search on Registry home page
    Given I visit the mobile web site as a guest user in registry mode
    When I type "<criteria>" in mew search and click enter
    Then I should go to respective "<url>"
    Examples:
      | criteria | url                           |
      | plates   | shop/registry/wedding/search? |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: For Signed in user, validate the registry search on Registry manager page
    Given I visit the mobile web site as a registered user without add CC
    When I navigate the global navigation menu as follows:
      | Wedding Registry |
      | Registry Tools   |
      | Manage Registry  |
    When I type "<criteria>" in mew search and click enter
    Then I should go to respective "<url>"
    Examples:
      | criteria | url                           |
      | plates   | shop/registry/wedding/search? |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: For Signed in user, validate the registry search on thankyou manager page
    Given I visit the mobile web site as a registered user without add CC
    When I navigate the global navigation menu as follows:
      | Wedding Registry  |
      | Registry Tools    |
      | Thank You Manager |
    When I type "<criteria>" in mew search and click enter
    Then I should go to respective "<url>"
    Examples:
      | criteria  | url                           |
      | cook ware | shop/registry/wedding/search? |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: For Signed in user, validate the registry search on star rewards page
    Given I visit the mobile web site as a registered user without add CC
    When I navigate the global navigation menu as follows:
      | Wedding Registry |
      | Registry Tools   |
      | Rewards          |
    When I type "<criteria>" in mew search and click enter
    Then I should go to respective "<url>"
    Examples:
      | criteria     | url                           |
      | Blenders 123 | shop/registry/wedding/search? |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: For Signed in user, validate the registry search on find registry page
    Given I visit the mobile web site as a registered user without add CC
    When I navigate the global navigation menu as follows:
      | Wedding Registry |
      | Find A Registry  |
    When I type "<criteria>" in mew search and click enter
    Then I should go to respective "<url>"
    Examples:
      | criteria | url                           |
      | pillows | shop/registry/wedding/search? |


  @domain_mew_discovery @use_mew_regression
  Scenario Outline: For Signed in user, validate the registry search on enroll page
    Given I visit the mobile web site as a registered user without add CC
    When I directly navigate to registry rewards enroll page
    When I type "<criteria>" in mew search and click enter
    Then I should go to respective "<url>"
    Examples:
      | criteria | url                           |
      | Dinner plates    | shop/registry/wedding/search? |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: For Signed in user, validate the registry search on registry sign in page
    Given I visit the mobile web site as a registered user without add CC
    When I directly navigate to registry sign in page
    When I type "<criteria>" in mew search and click enter
    Then I should go to respective "<url>"
    Examples:
      | criteria   | url                           |
      | electrics? | shop/registry/wedding/search? |