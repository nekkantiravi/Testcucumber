Feature:  MCOM Tag Migration Polaris Killswitch customer-preferences

  @Tealium_proxy
  Scenario Outline: Verify that tealium tags script is loaded on customer preferences page for MCOM
    Given I visit the web site as a registered user
    And  I visit the "<account_preferences>" pages
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js |
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js|
    Examples:
    | account_preferences |
    | /account/preferences?cm_sp=macys_account-_-my_account-_-view_preferences |
    | /account/preferences?cm_sp=macys_account-_-my_account-_-view_preferences#styleNFit |
    | /account/preferences#notifications |
    | /account/preferences#preferredStore |

