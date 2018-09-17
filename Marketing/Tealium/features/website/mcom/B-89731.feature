Feature:  MCOM Tag Migration Killswitch Macys-node Registry

  @Tealium_proxy
  Scenario Outline: Verify that tealium tags script is loaded on macys node page for MCOM
    Given I visit the web site as a registered user in "registry" mode
    And  I visit the "<macys-node>" pages
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js |
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js|
    Examples:
    | macys-node |
    | wgl/registry/registrant |



