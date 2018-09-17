Feature:Implement Environment awareness in Tealium script for MEW applications

  @Tealium_proxy
  Scenario: Verify tealium tags script url is loaded in network call for Category pages
    Given I visit the mobile web site as a guest user
    Then I verify the kill switch toggle
      | on |
    When I navigate the global navigation menu as follows:
      | WOMEN  |
      | Jeans  |
      | Skinny |
    Then I verify the kill switch toggle
      | on |
    And I verify tealium tags script is available in network call for mew
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js |

  @Tealium_proxy
  Scenario:Verify tealium tags script url is loaded in network call for Wish List page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Wish List |
    Then I verify the kill switch toggle
      | on |
    And I verify tealium tags script is available in network call for mew
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js |

  @Tealium_proxy
  Scenario: Verify tealium tags script url is loaded in network call for Stores page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Stores |
    Then I verify the kill switch toggle
      | on |
    And I verify tealium tags script is available in network call for mew
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js |

  @Tealium_proxy
  Scenario: Verify tealium tags script url is loaded in network call for MY bWALLET page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | MY bWALLET |
    Then I verify the kill switch toggle
      | on |
    And I verify tealium tags script is available in network call for mew
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js |

  @Tealium_proxy
  Scenario:Verify tealium tags script url is loaded in network call for My Account page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | My Account |
    Then I verify the kill switch toggle
      | on |
    And I verify tealium tags script is available in network call for mew
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js |

  @Tealium_proxy
  Scenario: Verify tealium tags script url is loaded in network call for PDP page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | WOMEN  |
      | Jeans  |
      | Skinny |
    And I select a random member product using mobile website
    Then I verify the kill switch toggle
      | on |
    And I verify tealium tags script is available in network call for mew
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js |

  @Tealium_proxy
  Scenario: Verify tealium tags script url is loaded in network call for promotions page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Promotions |
    Then I verify the kill switch toggle
      | on |
    And I verify tealium tags script is available in network call for mew
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js |

  @Tealium_proxy
  Scenario: Verify tealium tags script url is loaded in network call for Loyality page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | MY LOYALLIST       |
      | Become a Loyallist |
    Then I verify the kill switch toggle
      | on |
    And I verify tealium tags script is available in network call for mew
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js |

  @Tealium_proxy
  Scenario: Verify tealium tags script url is loaded in network call for BagApp page as MEW guest user
    Given I visit the mobile web site as a guest user
    When  I directly add an available and orderable product "3048" to my bag in mobile site
    Then I verify the kill switch toggle
      | on |
    And I verify tealium tags script is available in network call for mew
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js |

  @Tealium_proxy
  Scenario: Verify tealium tags script url is loaded in network call for search results page as a MEW guest user
    Given I visit the mobile web site as a guest user
    When I search using mobile website for "mens watches"
    Then I verify the kill switch toggle
      | on |
    And I verify tealium tags script is available in network call for mew
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js |

  @Tealium_proxy
  Scenario: Verify tealium tags script url is loaded in network call for Registry page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | The Registry |
    Then I verify the kill switch toggle
      | on |
    And I verify tealium tags script is available in network call for mew
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js|