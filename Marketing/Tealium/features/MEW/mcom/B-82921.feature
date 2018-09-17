Feature: Identify and inject tealium script

  @Tealium_proxy
  Scenario: Verify that tealium tags script is loaded in network call on shopapp page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop           |
      | Men            |
      | Activewear     |
    And I select a random member product using mobile website
    Then I should be redirected to PDP page using mobile website
    Then I verify tealium tags script is available in network call for mew
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |

  @Tealium
  Scenario: To verify the kill switch on for tealium tags on mobile home page mew
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop           |
      | Men            |
      | Activewear     |
    And I select a random member product using mobile website
    Then I should be redirected to PDP page using mobile website
    Then I verify the kill switch toggle
      | on |