Feature: Identify and inject tealium script

  @Tealium_proxy
  Scenario: Verify that tealium tags script is loaded in network call on shop page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop           |
      | Men            |
      | Activewear     |
    Then I verify tealium tags script is available in network call for mew
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |

  @Tealium
  Scenario: To verify the kill switch on for tealium tags on shop page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop           |
      | Men            |
      | Activewear     |
    Then I verify the kill switch toggle
      | on |

  @Tealium_proxy
  Scenario: Verify that tealium tags script is loaded in network call on deals page
    Given I visit the mobile web site as a guest user
    And I navigate the global navigation menu as follows:
      | Deals |
    Then I verify tealium tags script is available in network call for mew
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |

  @Tealium
  Scenario: To verify the kill switch on for tealium tags on deals page
    Given I visit the mobile web site as a guest user
    And I navigate the global navigation menu as follows:
      | Deals |
    Then I verify the kill switch toggle
      | on |

  @Tealium_proxy
  Scenario: Verify that tealium tags script is loaded in network call on lists page
    Given I visit the mobile web site as a guest user
    And I navigate the global navigation menu as follows:
      | Lists |
    Then I verify tealium tags script is available in network call for mew
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |

  @Tealium
  Scenario: To verify the kill switch on for tealium tags on lists page
    Given I visit the mobile web site as a guest user
    And I navigate the global navigation menu as follows:
      | Lists |
    Then I verify the kill switch toggle
      | on |

  @Tealium_proxy
  Scenario: Verify that tealium tags script is loaded in network call on stores page
    Given I visit the mobile web site as a guest user
    And I navigate the global navigation menu as follows:
      | Stores |
    Then I verify tealium tags script is available in network call for mew
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |

  @Tealium
  Scenario: To verify the kill switch on for tealium tags on stores page
    Given I visit the mobile web site as a guest user
    And I navigate the global navigation menu as follows:
      | Stores |
    Then I verify the kill switch toggle
      | on |

  @Tealium_proxy
  Scenario: Verify that tealium tags script is loaded in network call on wallet page
    Given I visit the mobile web site as a guest user
    And I navigate the global navigation menu as follows:
      | Wallet |
    Then I verify tealium tags script is available in network call for mew
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |

  @Tealium
  Scenario: To verify the MEW kill switch on for tealium tags on wallet page
    Given I visit the mobile web site as a guest user
    And I navigate the global navigation menu as follows:
      | Wallet |
    Then I verify the kill switch toggle
      | on |

  @Tealium_proxy
  Scenario: Verify that tealium tags script is loaded in network call on My Account page
    Given I visit the mobile web site as a guest user
    And I navigate the global navigation menu as follows:
      | My Account |
    Then I verify tealium tags script is available in network call for mew
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |