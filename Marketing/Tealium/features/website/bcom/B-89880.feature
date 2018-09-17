Feature: BCOM implement Tealium script b pages fashion heroku app master

  @Tealium
  Scenario Outline: Verify that tealium tags script is loaded in b pages fashion heroku app master
    Given I visit the web site as a guest user
    And  I visit the "<fashion_heroku>" pages
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    Examples:
      | fashion_heroku |
      | b/registry/wedding/benefits-perks/  |
      | b/international/#/     |
      | b/about-us/amfar-b-cause-philanthropy/ |
      | b/about-us/buy-online-pickup-in-store/?cm_sp=ways_to_shop-_-bops |
      | b/about-us/directory/                                            |