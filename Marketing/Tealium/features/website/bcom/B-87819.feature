Feature:  Implement Tealium script for BCOM Fashion pages

  @Tealium_proxy @WIP
  Scenario Outline: Verify that tealium tags script is loaded in BCOM fashion pages
    Given I visit the web site for following "<fashion>" pages
    And I verify tealium tags script is available in network call for fashion pages
      | http://tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | http://tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    Examples:
      | fashion                                                                                                |
      | http://fashion-test.bloomingdales.com/b/about-us/directory/                                            |
      | http://fashion-test.bloomingdales.com/b/about-us/buy-online-pickup-in-store/?cm_sp=ways_to_shop-_-bops |
      | http://fashion-test.bloomingdales.com/b/about-us/amfar-b-cause-philanthropy/                           |
      | http://fashion-test.bloomingdales.com/b/international/#                                                |