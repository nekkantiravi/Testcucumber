#####################################################################################
# Author            : Credit Systems Conversion Carryover :: QE Team
# Date              : Aug 31, 2015
#####################################################################################

Feature: Bloomingdale's Card Holder Benefits page

  @domain_customer @project_csr @release_15K @use_domain_qual
  Scenario: Verify loyallist sign up from card holder benefits page
    Given I visit the web site as a guest user
    When I navigate to the "cardholder benefits" page from footer
    Then I should see the card holder benefits page
    When I select "sign_up" link in cardholder benefits page
    Then I should reach "loyalty_home" page

  @domain_customer @project_csr @release_15K @use_domain_qual @cm_dsv_high_10
  Scenario: Verify acquisition flow from card holder benefits page
    Given I visit the web site as a guest user
    When I navigate to the "cardholder benefits" page from footer
    Then I should see the card holder benefits page
    When I select "apply_now" link in cardholder benefits page
    Then I should reach "citi_fusion" page

  @wip @domain_customer @project_csr @release_15K @use_domain_qual
  Scenario: Verify acquisition flow from card holder benefits page using learn_and_apply link
    Given I visit the web site as a guest user
    When I navigate to the "cardholder benefits" page from footer
    Then I should see the card holder benefits page
    When I select "learn_and_apply" link in cardholder benefits page
    Then I should reach "credit_service_marketing" page

  @manual @domain_customer @project_csr @release_15K
  Scenario Outline: Verify the functionality of Bloomingdales Loyallist benefits
    Given I visit the web site as a guest user
    When I navigate to the "cardholder benefits" page from footer
    Then I should see the card holder benefits page
    Then I should be able to navigate to "<section>" using "<benefits_page_link>"

    Examples:
      | section                                     | benefits_page_link                |
      | APPLY FOR A BLOOMINGDALE'S CARD             | bloomies_inclusion                |
      | APPLY FOR A BLOOMINGDALE'S CARD             | bloomies_ae_inclusion             |
      | TOP OF THE LIST STATUS                      | bloomies_tol                      |
      | TOP OF THE LIST STATUS                      | bloomies_ae_tol                   |
      | EARN POINTS FOR EVERY DOLLAR YOU SPEND      | bloomies_dollar_spent             |
      | POINTS ON RESTAURANT PURCHASES OUT OF STORE | bloomies_purchase_out_of_store    |
      | FREE SHIPPING EVERY DAY ONLINE AND IN-STORE | free_shipping_online_instore      |
      | EARN POWER POINTS                           | eligible_power_points             |
      | DINING CLUB                                 | dining_club                       |
      | AMERICAN EXPRESS PREMIUM TRAVEL SERVICES    | ae_travel_services                |
      | FREE LOCAL DELIVERY                         | free_local_delivery               |
      | UNLIMITED COMPLIMENTARY GIFT WRAP           | unlimited_complimentary_gift_wrap |
      | CHOOSE YOUR OWN TRIPLE POINTS DAY           | triple_points_day                 |

  @domain_customer @project_csr @release_15K @use_browser
  Scenario: Verify the functionality of Bloomingdales premium travel services
    Given I visit the web site as a guest user
    When I navigate to the "cardholder benefits" page from footer
    Then I should see the card holder benefits page
    When I select "view_premium_travel_services" link in cardholder benefits page
    Then I should reach on the "http://www.bloomingdalespremiumtravelservices.com/?Obj=landing" page


  @domain_customer @project_csr @release_15K @use_browser
  Scenario: Verify the functionality of amex offers learn more
    Given I visit the web site as a guest user
    When I navigate to the "cardholder benefits" page from footer
    Then I should see the card holder benefits page
    When I select "amex_learn_more" link in cardholder benefits page
    Then I should reach on the "http://www.amexoffers.com/bloomingdales" page

  @wip @domain_customer @project_csr @release_15K
  Scenario Outline: Verify the functionality of Bloomingdales card bank agreement notices
    Given I visit the web site as a guest user
    When I navigate to the "cardholder benefits" page from footer
    Then I should see the card holder benefits page
    When I select "<privacy_agreement>" link in cardholder benefits page
    Then I should reach on the "<privacy_policy>" page
    Examples:
      | privacy_agreement     | privacy_policy                                                       |
      | bank_privacy_notice   | /dyn_img/site_ads/Department_Stores_National_Bank_Privacy_Notice.pdf |
      | credit_card_agreement | /dyn_img/site_ads/Bloomingdales_Credit_Card_Agreement.pdf            |
      | amex_card_agreement   | /dyn_img/site_ads/Bloomingdales_American_Express_Card_Agreement.pdf  |

  @domain_customer @project_csr @release_15K @use_domain_qual
  Scenario: Verify the functionality of Bloomingdale's credit card faqs
    Given I visit the web site as a guest user
    When I navigate to the "cardholder benefits" page from footer
    Then I should see the card holder benefits page
    When I select "cc_faqs" link in cardholder benefits page
    Then I should reach on the "/app/answers/list/session/" page

  @domain_customer @project_csr_post_launch @release_15K @da
  Scenario: Verify the updated text of Earn Power Points on Bloomingdale's benefits page
    Given I visit the web site as a guest user
    When I navigate to the "cardholder benefits" page from footer
    Then I should see the card holder benefits page
    Then I should see updated text in card holder benefits page

  @domain_customer @project_csr_post_launch @release_15K @da
  Scenario: Verify the updated Updated Learn and Apply Link URL on Bloomingdale's benefits page
    Given I visit the web site as a guest user
    When I navigate to the "cardholder benefits" page from footer
    Then I should see the card holder benefits page
    When I select "learn_and_apply" link in cardholder benefits page
    Then I should reach on the "creditservice/marketing/main" page

    ### B-49287 ##
### Pre-Condition:#creditSix2016Enabled = True ###

  @domain_customer @project_csr @artifact_shopapp @release_16G
  Scenario: Verify the Updated URL on fusion page for acquisition flow for signedin user
    Given I visit the web site as a registered user
    When I navigate to the "cardholder benefits" page from footer
    Then I should see the card holder benefits page
    When I select "apply_now" link in cardholder benefits page
    Then I should reach on the "/CRS/acq/launch/index.action?" page

  @domain_customer @project_csr @artifact_shopapp @release_16G @dsv_desktop_sev1
  Scenario: Verify the Updated URL on fusion page for acquisition flow for guest user
    Given I visit the web site as a guest user
    When I navigate to the "cardholder benefits" page from footer
    Then I should see the card holder benefits page
    When I select "apply_now" link in cardholder benefits page
    Then I should reach on the "/CRS/acq/launch/index.action?" page
    Then I should redirect to "apply now" page