#Author: Ovidiu Rucoi
#Story: SDE-111 - MOBILE: List/Event Visibility
#Date Created: 06/12/2017
#Date Signed Off:

  # TODO: RESEARCH FOR A WAY TO RUN BOTH WEB AND MOBILE STEPS IN THE SAME SCENARIO AND AUTOMATE

Feature:As a associate using the mobile clientele application, I want to see all Lists and Events created for the
  Selling Associates who I oversee so that I can monitor status and avoid creating duplicate Lists.

  @manual @domain_stores @omniclient @story_SDE-111 @bcom @MEW
  Scenario: SAs and SMs should see the List created by a General Manager

    # The To DO first needs to be manually created wia the Web app,
    # since the Mobile app does not have support for creating To Dos

    Given I launch the bloomingdales's omniclient page on mobile
    When I sign into OmniClient BLM mobile application as Selling Manager
    When I navigate to My Lists mobile page
    Then I should see the list of TO DOS on the mobile BLM page
    And I should see the new TO DO created by the General Manager on BLM mobile
    And I should see the list information on the mobile page
      | Created by       |
      | List Title       |
      | Uncalled clients |
    When I select the switch user input box
    And I enter "EIGHTY T" credentials
    And I select the "EIGHTY THREE" credentials from the dropdown
    Then I should see switch user overlay
    When click the Switch button
    Then I should be switched into the selected "associate"
    When I navigate to My Lists mobile page
    Then I should see the list of TO DOS on the mobile BLM page
    And I should see the new TO DO created by the General Manager on BLM
    And I should see the list information on the mobile page
      | Created by       |
      | List Title       |
      | Uncalled clients |

  @manual @domain_stores @omniclient @story_SDE-111 @bcom @MEW
  Scenario: SAs and SMs should see the List created by a Corporate Store Executive

    # The To DO first needs to be manually created wia the Web app,
    # since the Mobile app does not have support for creating To Dos

    Given I launch the bloomingdales's omniclient page on mobile
    When I sign into Omniclient BLM application as Selling Manager
    When I navigate to My Lists mobile page
    Then I should see the list of TO DOS on the mobile BLM page
    And I should see the new TO DO created by the Corporate Store Executive on BLM mobile
    And I should see the list information on the mobile page
      | Created by       |
      | List Title       |
      | Uncalled clients |
    When I select the switch user input box
    And I enter "EIGHTY T" credentials
    And I select the "EIGHTY THREE" credentials from the dropdown
    Then I should see switch user overlay
    When click the Switch button
    Then I should be switched into the selected "associate"
    When I navigate to My Lists mobile page
    Then I should see the list of TO DOS on the mobile BLM page
    And I should see the new TO DO created by the Corporate Store Executive on BLM mobile
    And I should see the list information on the mobile page
      | Created by       |
      | List Title       |
      | Uncalled clients |

  @manual @domain_stores @omniclient @story_SDE-111 @bcom @MEW
  Scenario: SAs and SMs should see the Event created by a Corporate Admin

    # The To DO first needs to be manually created wia the Web app,
    # since the Mobile app does not have support for creating To Dos

    Given I launch the bloomingdales's omniclient page on mobile
    When I sign into Omniclient BLM application as Selling Manager
    When I navigate to My Lists mobile page
    Then I should see the new Event created by the Corporate Admin on BLM mobile
    And I should see the list information on the mobile page
      | Created by       |
      | List Title       |
      | Uncalled clients |
    When I select the switch user input box
    And I enter "EIGHTY T" credentials
    And I select the "EIGHTY THREE" credentials from the dropdown
    Then I should see switch user overlay
    When click the Switch button
    Then I should be switched into the selected "associate"
    When I navigate to My Lists mobile page
    Then I should see the new Event created by the Corporate Admin on BLM mobile
    And I should see the list information on the mobile page
      | Created by       |
      | List Title       |
      | Uncalled clients |