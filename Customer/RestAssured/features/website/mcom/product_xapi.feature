Feature: PDPxAPI Rest API test Scenarios

  @sample1
  Scenario: Verify rest api service for demo
    Given I set rest api server
    When I call service xapi service "state/search/IND?text=pradesh"
    Then I verify rest api response has key: "RestResponse.result[0].largest_city" to be value: "Bhopal"

  @sample2
  Scenario: Verify rest api service for demo
    Given I set rest api server to "http://services.groupkt.com"
    When I call service xapi service "state/search/IND?text=pradesh"
    Then I verify rest api response has key: "RestResponse.result[0].largest_city" to be value: "Bhopal"

  @sample3
  Scenario: Verify pdp-xapi service for product name
    Given I set rest api server to "http://172.17.14.99" with port number "8080"
    When I call pdp-xapi service for "2351352" item
    Then I verify pdp-xapi service response has product name