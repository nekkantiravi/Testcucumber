#####################################################################################
# Story             : B-79883 : BUS:: UI:: BCOM:: Desktop::M/BCOM::SDT Migration::Profile
#
# Author            : Credit Systems Conversion Carryover :: QE Team
#####################################################################################

Feature: Create Profile removal and Edit Profile removal
  As a Bloomingdales or macys credit customer, I need to see only fields relevant to my profile,
  so when I create a profile, I do not enter credit card information.

  @use_regression @domain_customer @release_15K @use_browser
  Scenario Outline: Verify add card and apply now functionality
    Given I visit the web site as a guest user
    And I create a new profile without closing the add card overlay
    Then I should see one time add card overlay and its components
    When I select on "<field>"
    Then I should redirect to "<target>" page
    Examples:
      | field                             | target            |
      | add_card_overlay_add_card_button  | fusion_add_card   |
      | add_card_overlay_apply_today_link | fusion_apply_card |

  @use_regression @domain_customer @release_15K @use_browser
  Scenario Outline: Verify no thanks and close functionality on overlay
    Given I visit the web site as a guest user
    And I create a new profile without closing the add card overlay
    Then I should see one time add card overlay and its components
    When I select on "<field>"
    Then I should redirect to myAccount Page
    Examples:
      | field                              |
      | add_card_overlay_no_thanks_button  |
      | add_card_overlay_close_button      |

  @use_regression @domain_customer @release_15K @use_browser
  Scenario: Verify the display of update billing address message and button, when the customer has a already added card details to the existing user
    Given I sign in with user having "new_prop_user" added in profile
    And  I navigate to my account page
    And I navigate to my profile
    Then I should see below fields on my profile page
      | csr_update_billing_info_button  |
      | csr_update_billing_info_section |

  @use_regression @domain_customer @release_15K @use_browser
  Scenario: Verify the display of update billing address message and button, when the customer doesn't have card details to the existing user
    Given I visit the web site as a registered user
    When I navigate to my profile
    Then I should not see below fields on my profile page
      | csr_update_billing_info_button  |
      | csr_update_billing_info_section |