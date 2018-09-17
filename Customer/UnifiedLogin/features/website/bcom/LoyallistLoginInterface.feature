#######################################
# Desktop Loyallist Flows
# Author :: QE TEAM
#######################################

  Feature: Unified sign in feature through Loyallist flow.

    @domain_customer @project_unified_login @artifact_account_ui
    Scenario: Verify sign in feature through "become a loyallist" button on loyalty home page as a loyalty user.
      Given I visit the web site as a guest user
      When I click on "goto_loyallist" in "home" page
      Then I should be redirected to "loyalty_home" page
      When I click on "become_a_loyallist_button" in "loyalty_home" page
      Then I should be redirected to "loyalty_sign_in" page
      And I should verify "pre_signin" url for "loyalty_sign_in" link
      When I signin as a loyalty user
      Then I should be redirected to "loyallist_account_summary" page
      And I should verify "post_signin" url for "loyallist_account_summary" link

    @domain_customer @project_unified_login @artifact_account_ui
    Scenario: Verify sign in feature through "become a loyallist" button on loyalty home page as a non-loyalty user.
      Given I visit the web site as a guest user
      When I click on "goto_loyallist" in "home" page
      Then I should be redirected to "loyalty_home" page
      When I click on "become_a_loyallist" in "loyalty_home" page
      Then I should be redirected to "loyalty_sign_in" page
      And I should verify "pre_signin" url for "loyalty_sign_in" link
      When I signin as a non-loyalty user
      Then I should be redirected to "loyalty_enrollment" page
      And I should verify "post_signin" url for "loyalty_enrollment" link

    @domain_customer @project_unified_login @artifact_account_ui
    Scenario: Verify sign in feature through "sign in" button on loyalty homepage as loyalty user.
      Given I visit the web site as a guest user
      When I click on "goto_loyallist" in "home" page
      Then I should be redirected to "loyalty_home" page
      When I click on "sign_in" in "loyalty_home" mobile page
      Then I should be redirected to "loyalty_sign_in" page
      And I should verify "pre_signin" url for "loyalty_sign_in" link
      When I signin as a loyalty user
      Then I should be redirected to "loyallist_account_summary" page
      And I should verify "post_signin" url for "loyallist_account_summary" link

    @domain_customer @project_unified_login @artifact_account_ui
    Scenario: Verify sign in feature through "sign in" button on loyalty homepage as non-loyalty user.
      Given I visit the web site as a guest user
      When I click on "goto_loyallist" in "home" page
      Then I should be redirected to "loyalty_home" page
      When I click on "sign_in" in "loyalty_home" mobile page
      Then I should be redirected to "loyalty_sign_in" page
      And I should verify "pre_signin" url for "loyalty_sign_in" link
      When I signin as a non-loyalty user
      Then I should be redirected to "loyalty_enrollment" page
      And I should verify "post_signin" url for "loyalty_enrollment" link

    @domain_customer @project_unified_login @artifact_account_ui
    Scenario: Verify sign in feature through "become a loyallist" button on shopping bag page as a loyalty user.
      Given I visit the web site as a guest user
      And I add an "available and orderable" product to my bag and select checkout
      When I click on "become_a_loyallist" in "shopping_bag" page
      Then I should be redirected to "loyalty_home" page
      When I click on "sign_in" in "loyalty_home" page
      Then I should be redirected to "loyalty_sign_in" page
      And I should verify "pre_signin" url for "loyalty_sign_in" link
      When I signin as a loyalty user
      Then I should be redirected to "loyalty_enrollment" page
      And I should verify "post_signin" url for "loyalty_enrollment" link

    @domain_customer @project_unified_login @artifact_account_ui
    Scenario: Verify sign in feature through "become a loyallist" button on shopping bag page as non loyalty user.
      Given I visit the web site as a guest user
      And I add an "available and orderable" product to my bag and select checkout
      When I click on "become_a_loyallist" in "shopping_bag" page
      Then I should be redirected to "loyalty_home" page
      When I click on "sign_in" in "loyalty_home" page
      Then I should be redirected to "loyalty_sign_in" page
      And I should verify "pre_signin" url for "loyalty_sign_in" link
      When I signin as a non-loyalty user
      Then I should be redirected to "loyalty_enrollment" page
      And I should verify "post_signin" url for "loyalty_enrollment" link

    @domain_customer @project_unified_login @artifact_account_ui
    Scenario: Verify sign in feature through "become a loyallist" button on ATB overlay as a loyalty user.
      Given I visit the web site as a guest user
      And I add an "available and orderable" product to my bag
      When I click on "become_a_loyallist" in "add_to_bag_dialog" page
      Then I should be redirected to "loyalty_home" page
      When I click on "sign_in" in "loyalty_home" page
      Then I should be redirected to "loyalty_sign_in" page
      And I should verify "pre_signin" url for "loyalty_sign_in" link
      When I signin as a loyalty user
      Then I should be redirected to "loyalty_enrollment" page
      And I should verify "post_signin" url for "loyalty_enrollment" link

    @domain_customer @project_unified_login @artifact_account_ui
    Scenario: Verify sign in feature through "become a loyallist" button on ATB overlay as non loyalty user.
      Given I visit the web site as a guest user
      And I add an "available and orderable" product to my bag
      When I click on "become_a_loyallist" in "add_to_bag_dialog" page
      Then I should be redirected to "loyalty_home" page
      When I click on "sign_in" in "loyalty_home" page
      Then I should be redirected to "loyalty_sign_in" page
      And I should verify "pre_signin" url for "loyalty_sign_in" link
      When I signin as a non-loyalty user
      Then I should be redirected to "loyalty_enrollment" page
      And I should verify "post_signin" url for "loyalty_enrollment" link

    @domain_customer @project_unified_login @artifact_account_ui
    Scenario: Verify sign in feature through "become a loyallist" button on quick bag overlay as a loyalty user.
      Given I visit the web site as a guest user
      And I add an "available and orderable" product to my bag and select checkout
      And I click on "quick_bag" in "shopping_bag" page
      When I click on "become_a_loyallist" in "quick_bag" page
      Then I should be redirected to "loyalty_home" page
      When I click on "sign_in" in "loyalty_home" page
      Then I should be redirected to "loyalty_sign_in" page
      And I should verify "pre_signin" url for "loyalty_sign_in" link
      When I signin as a loyalty user
      Then I should be redirected to "loyalty_enrollment" page
      And I should verify "post_signin" url for "loyalty_enrollment" link

    @domain_customer @project_unified_login @artifact_account_ui
    Scenario: Verify sign in feature through "become a loyallist" button on quick bag overlay as non loyalty user.
      Given I visit the web site as a guest user
      And I add an "available and orderable" product to my bag and select checkout
      When I click on "become_a_loyallist" in "shopping_bag" page
      Then I should be redirected to "loyalty_home" page
      When I click on "sign_in" in "loyalty_home" page
      Then I should be redirected to "loyalty_sign_in" page
      And I should verify "pre_signin" url for "loyalty_sign_in" link
      When I signin as a non-loyalty user
      Then I should be redirected to "loyalty_enrollment" page
      And I should verify "post_signin" url for "loyalty_enrollment" link




