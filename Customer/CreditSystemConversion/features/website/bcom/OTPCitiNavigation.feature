##########################################################################
# B-94647::OTSP3::BUS::BCOM::UI::Desktop/MEW:: CG:: Use cases where user clicks "Make a Payment" and goes to Fusion (1 card)
# B-91423:OTSP3:BUS:BCOM:UI:Desktop/MEW:CG:Credit card with no Fusion payment source, user clicks "Make a Payment" (1 card)
# Author: QE Team
############################################################################

Feature: As a user with a card that has no payment source set up in Fusion, when I click the "Make A Payment" button on Credit Gateway,
  I want to be taken to Fusion (where I can set up my payment source, and then make a payment).

# Pre-Requisite:1.Customer is signed in and has 1 credit card account in Fusion profile.
#       2.In Scheduled Payment API, payment_eligibility_flag = N
#       3. Functionality should be wrapped in creditOnetimePaymentsEnabled kill switch

  # eligibility_reason_code B
  @wip @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: User tries to make a payment with a card that has no payment source set up in Fusion
    Given I sign in with user having "card_no_source" added in profile
    When I navigate to the "credit services" page from footer
    When I tap on "make_payment_button"
    Then I should redirect to "citi_fusion" page
    And I should see "makepaymentoptions" process indicator

  # eligibility_reason_code E
  @wip @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: User tries to make a payment for restricted cards
    Given I sign in with user having "restricted_card_l" added in profile
    When I navigate to the "credit services" page from footer
    When I tap on "make_payment_button"
    Then I should redirect to "citi_fusion" page
    And I should see "dashboard" process indicator