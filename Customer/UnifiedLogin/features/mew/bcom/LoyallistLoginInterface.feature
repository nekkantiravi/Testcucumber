#######################################
# MEW Loyallist Flows
# Author :: QE TEAM
#######################################

  Feature: Unified sign in feature through Loyallist flow.

    @domain_customer @project_unified_login @artifact_account_ui
    Scenario: Verify sign in feature through "become a loyallist" button on loyalty home page through homepage footer become a loyallist section as a loyalty user.
      Given I visit the mobile web home page
      When I click on "become_guest_loyallist" in "home" mobile page
      Then I should be redirected to "loyalty_home" page
      When I click on "become_a_loyallist_button" in "loyalty_home" mobile page
      Then I should be redirected to "loyalty_sign_in" page
      And I should verify "pre_signin" url for "loyalty_sign_in_mew" link
      When I signin as a loyalty user
      Then I should be redirected to "loyallist_account_summary" page
      And I should verify "post_signin" url for "loyallist_account_summary_mew" link

    @domain_customer @project_unified_login @artifact_account_ui
    Scenario: Verify sign in feature through "become a loyallist" button on loyalty home page through homepage footer become a loyallist section as a non-loyalty user.
      Given I visit the mobile web home page
      When I click on "become_guest_loyallist" in "home" mobile page
      Then I should be redirected to "loyalty_home" page
      When I click on "become_a_loyallist_button" in "loyalty_home" mobile page
      Then I should be redirected to "loyalty_sign_in" page
      And I should verify "pre_signin" url for "loyalty_sign_in_mew" link
      When I signin as a non-loyalty user
      Then I should be redirected to "loyalty_enrollment" page
      And I should verify "post_signin" url for "loyalty_enrollment_mew" link

    @domain_customer @project_unified_login @artifact_account_ui
    Scenario: Verify sign in feature through "sign in" button on loyalty home
    page through homepage footer become a loyallist section as loyalty user.
      Given I visit the mobile web home page
      When I click on "become_guest_loyallist" in "home" mobile page
      Then I should be redirected to "loyalty_home" page
      When I click on "already_a_loyallist_sign_in_button" in "loyalty_home" mobile page
      Then I should be redirected to "loyalty_sign_in" page
      And I should verify "pre_signin" url for "loyalty_sign_in_mew" link
      When I signin as a loyalty user
      Then I should be redirected to "loyallist_account_summary" page
      And I should verify "post_signin" url for "loyallist_account_summary_mew" link

    @domain_customer @project_unified_login @artifact_account_ui
    Scenario: Verify sign in feature through "sign in" button on loyalty home
    page through homepage footer become a loyallist section as non-loyalty user.
      Given I visit the mobile web home page
      When I click on "become_guest_loyallist" in "home" mobile page
      Then I should be redirected to "loyalty_home" page
      When I click on "already_a_loyallist_sign_in_button" in "loyalty_home" mobile page
      Then I should be redirected to "loyalty_sign_in" page
      And I should verify "pre_signin" url for "loyalty_sign_in_mew" link
      When I signin as a non-loyalty user
      Then I should be redirected to "loyalty_enrollment" page
      And I should verify "post_signin" url for "loyalty_enrollment_mew" link

    @domain_customer @project_unified_login @artifact_account_ui
    Scenario: Verify sign in feature through "Become a Loyallist" global nav on loyalty homepage as loyalty user.
      Given I visit the mobile web site as a guest user
      When I navigate the global navigation menu as follows:
        | My Loyallist       |
        | Become a Loyallist |
      Then I should be redirected to "loyalty_home" page
      When I click on "already_a_loyallist_sign_in_button" in "loyalty_home" mobile page
      Then I should be redirected to "loyalty_sign_in" page
      And I should verify "pre_signin" url for "loyalty_sign_in_mew" link
      When I signin as a loyalty user
      Then I should be redirected to "loyallist_account_summary" page
      And I should verify "post_signin" url for "loyallist_account_summary_mew" link

    @domain_customer @project_unified_login @artifact_account_ui
    Scenario: Verify sign in feature through "Become a Loyallist" global nav on loyalty homepage as non-loyalty user.
      Given I visit the mobile web site as a guest user
      When I navigate the global navigation menu as follows:
        | My Loyallist       |
        | Become a Loyallist |
      Then I should be redirected to "loyalty_home" page
      When I click on "already_a_loyallist_sign_in_button" in "loyalty_home" mobile page
      Then I should be redirected to "loyalty_sign_in" page
      And I should verify "pre_signin" url for "loyalty_sign_in_mew" link
      When I signin as a non-loyalty user
      Then I should be redirected to "loyalty_enrollment" page
      And I should verify "post_signin" url for "loyalty_enrollment_mew" link

    @domain_customer @project_unified_login @artifact_account_ui
    Scenario: Verify sign in feature through "Already a Loyallist" global nav on loyalty homepage as loyalty user.
      Given I visit the mobile web site as a guest user
      When I navigate the global navigation menu as follows:
        | My Loyallist       |
        | Already a Loyallist |
      Then I should be redirected to "loyalty_home" page
      When I click on "already_a_loyallist_sign_in_button" in "loyalty_home" mobile page
      Then I should be redirected to "loyalty_sign_in" page
      And I should verify "pre_signin" url for "loyalty_sign_in_mew" link
      When I signin as a loyalty user
      Then I should be redirected to "loyallist_account_summary" page
      And I should verify "post_signin" url for "loyallist_account_summary_mew" link

    @domain_customer @project_unified_login @artifact_account_ui
    Scenario: Verify sign in feature through "Already a Loyallist" global nav on loyalty homepage as non-loyalty user.
      Given I visit the mobile web site as a guest user
      When I navigate the global navigation menu as follows:
        | My Loyallist       |
        | Already a Loyallist |
      Then I should be redirected to "loyalty_home" page
      When I click on "already_a_loyallist_sign_in_button" in "loyalty_home" mobile page
      Then I should be redirected to "loyalty_sign_in" page
      And I should verify "pre_signin" url for "loyalty_sign_in_mew" link
      When I signin as a non-loyalty user
      Then I should be redirected to "loyalty_association" page
      And I should verify "post_signin" url for "loyalty_association_mew" link