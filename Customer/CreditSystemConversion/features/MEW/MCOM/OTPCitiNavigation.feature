##########################################################################
# B-90869::OTSP3::BUS::MCOM::UI::Desktop/MEW:CG::Credit card with no Fusion payment source, user clicks "Make a Payment"(Single card)
# Author: QE Team
############################################################################

Feature: As a user with a card that has no payment source set up in Fusion, when I click the "Make A Payment" button on Credit Gateway,
  I want to be taken to Fusion (where I can set up my payment source, and then make a payment).

# Pre-Requisite:1.Customer is signed in and has 1 credit card account in Fusion profile.
#       2.In Scheduled Payment API, payment_eligibility_flag = N
#       3.In Scheduled payment API, eligibility_reason_code = "B" or "E".

  # eligibility_reason_code B
  @wip @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: User tries to make a payment with a card that has no payment source set up in Fusion
    Given I sign to mobile website with user having "card_no_source" added in profile
    When I navigate to credit service gateway page on MEW
    When I tap on "make_payment_button"
    Then I should redirect to "citi_fusion" page
    And I should see "makepaymentoptions" process indicator

  # eligibility_reason_code E
  @wip @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: User tries to make a payment for restricted cards
    Given I sign to mobile website with user having "restricted_card_l" added in profile
    When I navigate to credit service gateway page on MEW
    When I tap on "make_payment_button"
    Then I should redirect to "citi_fusion" page
    And I should see "dashboard" process indicator