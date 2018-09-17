#########################################################
##Author:: QE Team
## Feature file includes B-43052, B-79887 stories
#########################################################

Feature: As an online customer, I would like to not be interrupted by a speedbump when leaving Macy's to go to Citi for credit servicing to streamline my credit servicing experience.

  @domain_customer @project_credit_service @artifact_shopapp @release_17J
  Scenario Outline: Verification of email deep links as a signedin user has card in profile
    Given I sign in with user having "new_prop_user" added in profile
    When I open "<citi_email_deep_link>" through url
    Then I should redirect to "citi_fusion" page
    And I should see "<expected_process_indicator>" process indicator
    Examples:
      | citi_email_deep_link                                                                                                                   | expected_process_indicator |
      | /creditservice/bmarknemail?linkProc=email&processIndicator=dashboard&cm_mmc=MCOM-_-citiOps-_-viewYourAccount-_-n&uID=1234567890123456  | dashboard                  |
      | /creditservice/bmarknemail?linkProc=email&processIndicator=makepaymentoptions&cm_mmc=MCOM-_-citiOps-_-payBill-_-n&uID=1234567890123456 | makepaymentoptions         |
      | /creditservice/bmarknemail?linkProc=email&processIndicator=helpcontact&cm_mmc=MCOM-_-citiOps-_-contactUs-_-n&uID=1234567890123456      | helpcontact                |
      | /creditservice/bmarknemail?linkProc=email&processIndicator=securemessage&cm_mmc=MCOM-_-citiOps-_-contactUs-_-n&uID=1234567890123456    | securemessage              |
      | /creditservice/bmarknemail?linkProc=email&processIndicator=dashboard&cm_mmc=MCOM-_-citiOps-_-viewYourAccount-_-n&uID=1234567890123456  | dashboard                  |

  @domain_customer @project_credit_service @artifact_shopapp @release_17J
  Scenario Outline: Verification of email deep links as a guest user
    Given I visit the web site as a guest user
    When I open "<citi_email_deep_link>" through url
    Then SignIn page should get loaded
    When I sign in with "new_prop_user" user
    Then I should redirect to "citi_fusion" page
    And I should see "<expected_process_indicator>" process indicator
    Examples:
      | citi_email_deep_link                                                                                                                   | expected_process_indicator |
      | /creditservice/bmarknemail?linkProc=email&processIndicator=dashboard&cm_mmc=MCOM-_-citiOps-_-viewYourAccount-_-n&uID=1234567890123456  | dashboard                  |
      | /creditservice/bmarknemail?linkProc=email&processIndicator=makepaymentoptions&cm_mmc=MCOM-_-citiOps-_-payBill-_-n&uID=1234567890123456 | makepaymentoptions         |
      | /creditservice/bmarknemail?linkProc=email&processIndicator=helpcontact&cm_mmc=MCOM-_-citiOps-_-contactUs-_-n&uID=1234567890123456      | helpcontact                |
      | /creditservice/bmarknemail?linkProc=email&processIndicator=securemessage&cm_mmc=MCOM-_-citiOps-_-contactUs-_-n&uID=1234567890123456    | securemessage              |
      | /creditservice/bmarknemail?linkProc=email&processIndicator=dashboard&cm_mmc=MCOM-_-citiOps-_-viewYourAccount-_-n&uID=1234567890123456  | dashboard                  |

  @domain_customer @project_credit_service @artifact_shopapp @release_17J
  Scenario Outline: Verification of email deep links as a signed in user with no card
    Given I visit the web site as a registered user
    When I open "<citi_email_deep_link>" through url
    Then I should get a pop-up to add a card to profile
    And I clicked on Add Card button on credit gate way page
    Then I should redirect to "citi_fusion" page
    And I should see "linkadditionalcards" process indicator
    Examples:
      | citi_email_deep_link                                                                                                                   |
      | /creditservice/bmarknemail?linkProc=email&processIndicator=dashboard&cm_mmc=MCOM-_-citiOps-_-viewYourAccount-_-n&uID=1234567890123456  |
      | /creditservice/bmarknemail?linkProc=email&processIndicator=makepaymentoptions&cm_mmc=MCOM-_-citiOps-_-payBill-_-n&uID=1234567890123456 |
      | /creditservice/bmarknemail?linkProc=email&processIndicator=helpcontact&cm_mmc=MCOM-_-citiOps-_-contactUs-_-n&uID=1234567890123456      |
      | /creditservice/bmarknemail?linkProc=email&processIndicator=securemessage&cm_mmc=MCOM-_-citiOps-_-contactUs-_-n&uID=1234567890123456    |
      | /creditservice/bmarknemail?linkProc=email&processIndicator=dashboard&cm_mmc=MCOM-_-citiOps-_-viewYourAccount-_-n&uID=1234567890123456  |

  @domain_customer @project_credit_service @artifact_shopapp @release_17J
  Scenario Outline: Verification of email deep links navigation when user signin with profile havingno card
    Given I visit the web site as a registered user
    And I sign out from my current profile
    When I open "<citi_email_deep_link>" through url
    Then SignIn page should get loaded
    When I sign in with "profile_no_card" user
    Then I should get a pop-up to add a card to profile
    And I clicked on Add Card button on credit gate way page
    Then I should redirect to "citi_fusion" page
    And I should see "linkadditionalcards" process indicator
    Examples:
      | citi_email_deep_link                                                                                                                   |
      | /creditservice/bmarknemail?linkProc=email&processIndicator=dashboard&cm_mmc=MCOM-_-citiOps-_-viewYourAccount-_-n&uID=1234567890123456  |
      | /creditservice/bmarknemail?linkProc=email&processIndicator=makepaymentoptions&cm_mmc=MCOM-_-citiOps-_-payBill-_-n&uID=1234567890123456 |
      | /creditservice/bmarknemail?linkProc=email&processIndicator=helpcontact&cm_mmc=MCOM-_-citiOps-_-contactUs-_-n&uID=1234567890123456      |
      | /creditservice/bmarknemail?linkProc=email&processIndicator=securemessage&cm_mmc=MCOM-_-citiOps-_-contactUs-_-n&uID=1234567890123456    |
      | /creditservice/bmarknemail?linkProc=email&processIndicator=dashboard&cm_mmc=MCOM-_-citiOps-_-viewYourAccount-_-n&uID=1234567890123456  |