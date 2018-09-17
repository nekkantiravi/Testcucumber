Feature: Macy's Card Holder benefits page

  @use_regression @domain_customer @project_csr @release_15K @use_domain_qual
  Scenario Outline: Verify the functionality of Macy's credit card faq and bank agreement notices
    Given I visit the web site as a guest user
    When I navigate to the "cardholder benefits" page from footer
    Then I should see the card holder benefits page
    When I select "<privacy_agreement>" link in cardholder benefits page
    Then I should reach on the "<privacy_policy>" page
    Examples:
      | privacy_agreement                              | privacy_policy                                                      |
      | macys_credit_card_faqs                         | /app/answers/list/c/5                                               |
      | department_stores_national_bank_privacy_notice | /dyn_img/banners/Department_Stores_National_Bank_Privacy_Notice.pdf |
      | macys_credit_card_agreement                    | /dyn_img/banners/Macys_Credit_Card_Agreement.pdf                    |
      | macys_american_express_card_agreement          | /dyn_img/banners/Macys_American_Express_Card_Agreement.pdf          |

      ### B-49281 ##
    ### Pre-Condition:#creditSix2016Enabled = True ###

  @domain_customer @project_csr @artifact_shopapp @release_16G
  Scenario: Verify the Updated URL on fusion page for acquisition flow for signedin user
    Given I visit the web site as a registered user
    When I navigate to the "cardholder benefits" page from footer
    Then I should see the card holder benefits page
    When I click on Apply Now button on "cardholder_benefits" page
    Then I should reach on the "https://citiretailservices.citibankonline.com/CRS/acq/launch/index.action?app=UNSOL&siteId=PLCN_MACYS&langId=EN&sc=80002" page

  @domain_customer @project_csr @artifact_shopapp @release_16G @dsv_desktop_sev1
  Scenario: Verify the Updated URL on fusion page for acquisition flow for guest user
    Given I visit the web site as a guest user
    When I navigate to the "apply & learn more" page from footer
    Then I should see the learn more & apply page
    When I click on Apply Now button on "learn more & apply" page
    Then I should reach on the "CRS/acq/launch/index.action?app=UNSOL&siteId=PLCN_MACYS&langId=EN&sc=80002" page
    Then I should redirect to "apply now" page

#  @use_regression @domain_customer @project_csr @release_15K @use_browser @cm_dsv_high_10
#  Scenario: Verify acquisition flow from card holder benefits page
#    Given I visit the web site as a guest user
#    When I navigate to the "cardholder benefits" page from footer
#    Then I should see the card holder benefits page
#    When I click on Apply Now button on "cardholder_benefits" page
#   Then I should redirect to "citi_fusion" page

  ## NOte: As we got new story for Apply now navigation from benefits page, so removing the above scenario
