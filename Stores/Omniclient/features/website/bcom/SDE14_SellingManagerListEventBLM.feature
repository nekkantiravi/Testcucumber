 # Author: Claudiu Chirila
   # Story: SDE-14 - OmniClient :: Launch Application
   # Date Created:
   # Date Signed Off:

 Feature: As a Selling Manager, I want to see all Lists and Events created for the Selling Associates who I oversee so that I can monitor status and avoid creating duplicate Lists.


   @domain_stores @omniclient @story_SDE-14 @website @bcom
   Scenario: Selling Manager is able to view Lists created by a General Manager
     Given I launch the bloomingdales's omniclient page
     When I sign into Omniclient BLM application as General Manager
     And I create a TO DO from CREATE LIST page
       | TARGET GM |
     And I click on the Switch User button
     Then I should see the Switch User search popup
     When I enter "EIGHTY NIN" credentials
     And I select the "EIGHTY NINE" credentials from the dropdown
     And click the Switch button
     Then I should be switched into the selected "sales manager" from BLM
     When I navigate to MY TASKS page
     And I click on LISTS tab from BLM
     Then I should see the list of TO DOS on BLM
     And I should see the list information
       | Created by       |
       | List Title       |
       | Uncalled clients |
       | Due by           |
     And I should see the new TO DO "TITLE-BLM2" created by the General Manager on BLM
     When I click to expand the chevron
     Then List description will be displayed

   @domain_stores @omniclient @story_SDE-14 @website @bcom
   Scenario: Selling Manager is able to view Lists created by a Corporate Store Executive
     Given I launch the bloomingdales's omniclient page
     When I sign into Omniclient BLM application as Corporate Store Executive
     And I create a TO DO from CREATE LIST page
       | TARGET CORP STORE EXEC |
     And I click on the Switch User button
     Then I should see the Switch User search popup
     When I enter "EIGHTY NIN" credentials
     And I select the "EIGHTY NINE" credentials from the dropdown
     And click the Switch button
     Then I should be switched into the selected "sales manager" from BLM
     When I navigate to MY TASKS page
     And I click on LISTS tab from BLM
     Then I should see the list of TO DOS on BLM
     And I should see the list information
       | Created by       |
       | List Title       |
       | Uncalled clients |
       | Due by           |
     And I should see the new TO DO "TITLE-BLM1" created by the Corporate Store Executive on BLM
     When I click to expand the chevron
     Then List description will be displayed

        #   @manual @domain_stores @omniclient @story_SDE-14 @website @bcom
   #   Scenario: Selling Manager is able to view Events created by a Corporate Admin
   #     Given I launch the bloomingdales's omniclient page
   #     When I sign into Omniclient BLM application as corporate admin
   #     And I create an event from CREATE EVENT page from BLM
   #     And I click on the Switch User button
   #     And I enter "EIGHTY NI" credentials
   #     And I select the "EIGHTY NINE" credentials from the dropdown
   #     And click the Switch button
   #     Then I should be switched into the selected "sales manager" from BLM
   #     When I navigate to MY TASKS page
   #     And I click on LISTS tab from BLM
   #     Then I should see the list of TO DOS on BLM
   #     And I should see the list information
   #     And I should see the new EVENT created by the Corporate Admin on BLM
   #     When I click to expand the chevron
   #     Then List description will be displayed

    #   @domain_stores @omniclient @story_SDE-14 @website @bcom
 #   NOT APPLICABLE ON BLM -> My Shop is off
 #   Scenario: Selling Manager is able to view Lists created by another Selling Manager
 #     Given I launch the bloomingdales's omniclient page
 #     When I sign into Omniclient BLM application as Selling Manager
 #    And I create a TO DO from CREATE LIST page
 #     And I log out from the application
 #     When I sign in as another selling manager in BLM
 #     When I navigate to MY TASKS page
 #     And I click on LISTS tab from BLM
 #     Then I should see the list of TO DOS
 #     And I should see the list information
 #       | Created by       |
 #       | List Title       |
 #       | Uncalled clients |
 #       | Due by           |
 #     And I should see the new TO DO created by another selling manager
 #     When I click to expand the chevron
 #     Then List description will be displayed

