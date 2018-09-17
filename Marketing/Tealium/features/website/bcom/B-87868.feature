Feature: Environment Awareness for BCOM Fashion pages

  @Tealium_proxy
  Scenario Outline: Verify that tealium tags script is loaded in in network call on BCOM fashion pages
    When I visit the web site for following "<fashion>" pages
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    Examples:
      | fashion                                                                                                |
      | http://fashion-test.bloomingdales.com/b/about-us/directory/                                            |
      | http://fashion-test.bloomingdales.com/b/about-us/buy-online-pickup-in-store/?cm_sp=ways_to_shop-_-bops |
      | http://fashion-test.bloomingdales.com/b/about-us/amfar-b-cause-philanthropy/                           |
      | http://fashion-test.bloomingdales.com/b/international/#                                                |