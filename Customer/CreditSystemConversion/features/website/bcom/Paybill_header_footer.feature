Feature: Polaris PayBill Header/Footer links

######### B-74305 ::: PayBill Header/Footer   #Support paybill link: " /creditservice/creditacct/paybill "############
  @domain_customer @project_paybil_header_footer @release_17G
  Scenario: Verify non-signed in user is redirected to citi fusion payment page when user signs in and has a card in profile
    Given I visit the web site as a guest user
    When I Navigate to "Pay Bill" footer links
    Then SignIn page should get loaded
    When I sign in with "new_prop_user" user
    Then I should redirect to "citi_fusion" page
    And I should see "makepaymentoptions" process indicator

  @domain_customer @project_paybil_header_footer @release_17G
  Scenario: Verify  non-signed in user gets a pop-up to add a card to profile when user signs in and has no card in profile
    Given I visit the web site as a registered user
    And I sign out from my current profile
    When I Navigate to "Pay Bill" footer links
    Then SignIn page should get loaded
    When I sign in with "profile_nocard" user
    Then I should get a pop-up to add a card to profile

  @domain_customer @project_paybil_header_footer @release_17G
  Scenario: Verify  signed in user is redirected to citi fusion payment page when user signs in and has a card in profile
    Given I sign in with user having "new_prop_user" added in profile
    When I Navigate to "Pay Bill" footer links
    Then I should redirect to "citi_fusion" page
    And I should see "makepaymentoptions" process indicator

  @domain_customer @project_paybil_header_footer @release_17G
  Scenario: Verify  signed in user gets a pop-up to add a card to profile when user signs in and has no card in profile
    Given I visit the web site as a registered user
    When I Navigate to "Pay Bill" footer links
    Then I should get a pop-up to add a card to profile

  ###Backwards compatibility:  /account/signin?linkProc=site&processIndicator=makepaymentoptions&cm_sp=FOOTER-_-BOTTOM_NAV-_-pay_bill

  @domain_customer @project_paybil_header_footer @release_17G
  Scenario: Verify  non-signed in user gets a pop-up to add a card to profile when user signs in and has no card in profile
    Given I visit the web site as a registered user
      And I sign out from my current profile
    When I open "/account/signin?linkProc=site&processIndicator=makepaymentoptions&cm_sp=FOOTER-_-BOTTOM_NAV-_-pay_bill" through url
    Then SignIn page should get loaded
    When I sign in with "profile_nocard" user
    Then I should get a pop-up to add a card to profile

  @domain_customer @project_paybil_header_footer @release_17G
  Scenario: Verify  signed in user is redirected to citi fusion payment page when user signs in and has a card in profile
    Given I sign in with user having "new_prop_user" added in profile
    When I open "/account/signin?linkProc=site&processIndicator=makepaymentoptions&cm_sp=FOOTER-_-BOTTOM_NAV-_-pay_bill" through url
    Then I should redirect to "citi_fusion" page
    And I should see "makepaymentoptions" process indicator

  @domain_customer @project_paybil_header_footer @release_17G
  Scenario: Verify  signed in user gets a pop-up to add a card to profile when user signs in and has no card in profile
    Given I visit the web site as a registered user
    When I open "/account/signin?linkProc=site&processIndicator=makepaymentoptions&cm_sp=FOOTER-_-BOTTOM_NAV-_-pay_bill" through url
    Then I should get a pop-up to add a card to profile

## Note: We'll get a new story for the backward compatibility URL for non-signed in user when user signs in and has a card in profile