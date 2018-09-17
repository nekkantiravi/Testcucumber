Feature: Verify the data layer attributes for Home page

  @Tealium @Datalayer
  Scenario: Verify the data layer attributes on Home page as guest user
    Given I visit the web site as a guest user
    Then I verify the data attributes for tealium tags on Home page
      | page_id               |
      | page_name             |

  @Tealium @Datalayer
  Scenario: Verify the data layer attributes on Home page as registered user
    Given I visit the web site as a registered user
    Then I verify the data attributes for tealium tags on Home page
      | page_id               |
      | page_name             |