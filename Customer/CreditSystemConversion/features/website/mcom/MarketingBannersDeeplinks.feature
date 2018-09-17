#########################################################
##Author:: QE Team
## Story :: B-77555 :: BUS::UI::M/BCOM::Enable Deep Links to Fusion from Marketing Banners - Responsive Gateway
#########################################################

Feature: As an online customer, I would like to not be interrupted by a speedbump when leaving Macy's to go to Citi for credit servicing to streamline my credit servicing experience.

  @domain_customer @project_credit_service @artifact_shopapp @release_17J
  Scenario Outline: Verification of marketing banner deep links as a signedin user has card in profile
    Given I sign in with user having "new_prop_user" added in profile
    When I open "<citi_email_deep_link>" through url
    Then I should redirect to "citi_fusion" page
    And I should see "<expected_process_indicator>" process indicator
    Examples:
      | citi_email_deep_link                                                                                                         | expected_process_indicator |
      | /creditservice/bmarknemail?linkProc=marketingbanner&processIndicator=paperless&cm_mmc=MCOM-_-citiOps-_-goPaperlessNow-_-n    | paperless                  |
      | /creditservice/bmarknemail?linkProc=marketingbanner&processIndicator=creditlineincrease&cm_mmc=MCOM-_-citiOps-_-CLI-_-n      | creditlineincrease         |
      | /creditservice/bmarknemail?linkProc=marketingbanner&processIndicator=addauthusers&cm_mmc=MCOM-_-citiOps-_-addauthuser-_-n    | addauthusers               |
      | /creditservice/bmarknemail?linkProc=marketingbanner&processIndicator=incomecapture&cm_mmc=MCOM-_-citiOps-_-incomecapture-_-n | incomecapture              |
      | /creditservice/bmarknemail?linkProc=marketingbanner&processIndicator=verify&cm_mmc=MCOM-_-citiOps-_-activatecard-_-n         | verify                     |

  @domain_customer @project_credit_service @artifact_shopapp @release_17J
  Scenario Outline: Verification of marketing banner deep links as a guest user
    Given I visit the web site as a guest user
    When I open "<citi_email_deep_link>" through url
    Then SignIn page should get loaded
    When I sign in with "new_prop_user" user
    Then I should redirect to "citi_fusion" page
    And I should see "<expected_process_indicator>" process indicator
    Examples:
      | citi_email_deep_link                                                                                                         | expected_process_indicator |
      | /creditservice/bmarknemail?linkProc=marketingbanner&processIndicator=paperless&cm_mmc=MCOM-_-citiOps-_-goPaperlessNow-_-n    | paperless                  |
      | /creditservice/bmarknemail?linkProc=marketingbanner&processIndicator=creditlineincrease&cm_mmc=MCOM-_-citiOps-_-CLI-_-n      | creditlineincrease         |
      | /creditservice/bmarknemail?linkProc=marketingbanner&processIndicator=addauthusers&cm_mmc=MCOM-_-citiOps-_-addauthuser-_-n    | addauthusers               |
      | /creditservice/bmarknemail?linkProc=marketingbanner&processIndicator=incomecapture&cm_mmc=MCOM-_-citiOps-_-incomecapture-_-n | incomecapture              |

  @domain_customer @project_credit_service @artifact_shopapp @release_17J
  Scenario Outline: Verification of marketing banner deep links as a signed in user with no card
    Given I visit the web site as a registered user
    When I open "<citi_email_deep_link>" through url
    Then I tap on "add_card_button"
    Then I should redirect to "citi_fusion" page
    And I should see "linkadditionalcards" process indicator
    Examples:
      | citi_email_deep_link                                                                                                         |
      | /creditservice/bmarknemail?linkProc=marketingbanner&processIndicator=paperless&cm_mmc=MCOM-_-citiOps-_-goPaperlessNow-_-n    |
      | /creditservice/bmarknemail?linkProc=marketingbanner&processIndicator=creditlineincrease&cm_mmc=MCOM-_-citiOps-_-CLI-_-n      |
      | /creditservice/bmarknemail?linkProc=marketingbanner&processIndicator=addauthusers&cm_mmc=MCOM-_-citiOps-_-addauthuser-_-n    |
      | /creditservice/bmarknemail?linkProc=marketingbanner&processIndicator=incomecapture&cm_mmc=MCOM-_-citiOps-_-incomecapture-_-n |

  @domain_customer @project_credit_service @artifact_shopapp @release_17J
  Scenario: Verification of activate carrd marketing banner deep links as a guest user
    Given I visit the web site as a guest user
    When I open "/creditservice/bmarknemail?linkProc=marketingbanner&processIndicator=verify&cm_mmc=MCOM-_-citiOps-_-activatecard-_-n" through url
    Then I should redirect to "citi_fusion" page
    And I should see "verify" process indicator

  @domain_customer @project_credit_service @artifact_shopapp @release_17J
  Scenario: Verification of activate carrd marketing banner deep links as a signed in user
    Given I visit the web site as a registered user
    When I open "/creditservice/bmarknemail?linkProc=marketingbanner&processIndicator=verify&cm_mmc=MCOM-_-citiOps-_-activatecard-_-n" through url
    Then I should redirect to "citi_fusion" page
    And I should see "verify" process indicator

  @domain_customer @project_credit_service @artifact_shopapp @release_17T
  Scenario Outline: Verification of marketing banner deep links as a signed in user with invalid/ missing process indicator
    Given I sign in with user having "new_prop_user" added in profile
    When I open "<citi_email_deep_link>" through url
    Then I should redirect to "citi_fusion" page
    And I should see "<expected_process_indicator>" process indicator
    Examples:
      | citi_email_deep_link                                                                                             | expected_process_indicator |
      | /creditservice/bmarknemail?linkProc=marketingbanner&processIndicator=&cm_mmc=MCOM-_-citiOps-_-goPaperlessNow-_-n | dashboard                  |
      | /creditservice/bmarknemail?linkProc=marketingbanner&processIndicator=ncrease&cm_mmc=MCOM-_-citiOps-_-CLI-_-n     | dashboard                  |

  @domain_customer @project_credit_service @artifact_shopapp @release_17J
  Scenario Outline: Verification of marketing banner deep links as a guest user with invalid/ missing process indicator
    Given I visit the web site as a guest user
    When I open "<citi_email_deep_link>" through url
    Then SignIn page should get loaded
    When I sign in with "new_prop_user" user
    Then I should redirect to "citi_fusion" page
    And I should see "<expected_process_indicator>" process indicator
    Examples:
      | citi_email_deep_link                                                                                             | expected_process_indicator |
      | /creditservice/bmarknemail?linkProc=marketingbanner&processIndicator=&cm_mmc=MCOM-_-citiOps-_-goPaperlessNow-_-n | dashboard                  |
      | /creditservice/bmarknemail?linkProc=marketingbanner&processIndicator=ncrease&cm_mmc=MCOM-_-citiOps-_-CLI-_-n     | dashboard                  |