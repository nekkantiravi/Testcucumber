#Author: Discovery QE
#Date Created: 19/04/2017

Feature: Verify Media Rail functionality in DOMESTIC, ISHIP and REGISTRY modes
# Treatment A

# Notes:
# Below scenarios should be verified in all Home,Browse,Splash,Sub-Splash,Catsplash,PDP,Bag,DLP,Search Results,SLP,Brand pages
# All Shopapp pages Examples:My Preferences,My Address Book,Furniture & Mattress Status,My Macy's Credit Card,Cardholder Benifits,Apply & Learn,More           ,Gift Card Balance,Signout,Registry Home page,Create Registry,Manage Registry,Add to Registry,Registry Claim,Registry Signin,Thank You Card Manager            ,Enclosure Cards,Registry Star Rewards,Registry Edit Account,Wedding Website,Find Registry,Barcode Generation for Order Status,Returns confirmation         ,Shop Kick Email Verification
# All Navapp pages
# All Bagapp pages
# Should be verified on all site modes (domestic, registry, iship)

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario Outline: Header - Verify New Media rail should be displayed in DOMESTIC, ISHP and Registry modes
    Given I visit the web site as a guest user in "<mode_name>" mode
    Then I verify new media rail is displayed
    Examples:
      | mode_name |
      | domestic  |
      | registry  |
      | iship     |

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: Verify display of two advertisements in the Media Rail at the top of the global headerglobal in DOMESTIC, ISHIP and REGISTRY modes
    Given I visit the web site as a guest user
    And call content service to get global pool response for domestic mode
    |global_pool      |
    |SITE_LEFT_HEADER_MEDIA  |
    Then I verify Media rail appears on top of the global header

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign @wip
  Scenario: Verify Media rail advertisements appear same in UI and service in DOMESTIC mode
    Given I visit the web site as a guest user
    And Navigate to "HnF Clean" viewtype in new header footer experience
    Then I Verify Media rail advertisements appear same in UI and service

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign @wip
  Scenario: Verify Media rail advertisements appear same in UI and service in IShip mode
    Given I visit the web site as a guest user
    And Navigate to "HnF Clean" viewtype in new header footer experience
    When I navigate to international context page
    When I change country to "Australia"
    Then I Verify Media rail advertisements appear same in UI and service

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign @wip
  Scenario: Verify Media rail advertisements appear same in UI and service in Registry mode
    Given I visit the web site as a guest user
    And Navigate to "HnF Clean" viewtype in new header footer experience
    When I navigate to registry home page
    Then I Verify Media rail advertisements appear same in UI and service

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: Verify medial rail add pools in DOMESTIC
    Given I visit the web site as a guest user
    Then I verify advertisements appears on mediarail

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: Verify medial rail add pools in Registry
    Given I visit the web site as a guest user
    Then I verify advertisements appears on mediarail
    When I navigate to wedding registry by clicking registry link
    Then I verify advertisements appears on mediarail

# Treatment - B

#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario: Verify display of two advertisements in the Media Rail at the top of the global headerglobal in DOMESTIC, ISHIP and REGISTRY modes for Treatment B
#    Given I visit the web site as a guest user
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    Then I verify Media rail appears on top of the global header
#
#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario: Verify Media rail appears at the top of the global header in DOMESTIC for Treatment B
#    Given I visit the web site as a guest user
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    Then I verify advertisements appears on mediarail
#    And I verify Media rail appears on top of the global header
#
#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign @wip
#  Scenario: Verify Media rail advertisements appear same in UI and service in DOMESTIC mode for treatment B
#    Given I visit the web site as a guest user
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    Then I Verify Media rail advertisements appear same in UI and service
#
#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign @wip
#  Scenario: Verify Media rail advertisements appear same in UI and service in IShip mode for treatment B
#    Given I visit the web site as a guest user
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    When I navigate to international context page
#    When I change country to "Australia"
#    Then I Verify Media rail advertisements appear same in UI and service
#
#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign @wip
#  Scenario: Verify Media rail advertisements appear same in UI and service in Registry mode for treatment B
#    Given I visit the web site as a guest user
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    When I navigate to registry home page
#    Then I Verify Media rail advertisements appear same in UI and service
#
#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario: Verify Media rail appears at the top of the global header in Registry for Treatment B
#    Given I visit the web site as a guest user
#    When I navigate to registry home page
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    Then I verify Media rail appears on top of the global header
#    And I verify advertisements appears on mediarail
#
#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario: Verify medial rail add pools in DOMESTIC for Treatment B
#    Given I visit the web site as a guest user
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    And I verify advertisements appears on mediarail
#
#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario: Verify medial rail add pools in Registry for Treatment B
#    Given I visit the web site as a guest user
#    When I navigate to registry home page
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    Then I verify Media rail appears on top of the global header
#    And I verify advertisements appears on mediarail

  #As Clean will be scaled to 100% as of 08/23/2017, ignoring Control and Holdout scenarios

#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario: Header - Verify media rail is not displayed when user navigate to Hnf Holdout view type for Treatment B
#    Given I visit the web site as a guest user
#    And Navigate to "HnF Holdout" viewtype in new header footer experience
#    Then I verify media rail is not displayed

#  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario: Header - Verify media rail is not displayed when user navigate to Hnf Control view type for Treatment B
#    Given I visit the web site as a guest user
#    And Navigate to "HnF Control" viewtype in new header footer experience
#    Then I verify media rail is not displayed