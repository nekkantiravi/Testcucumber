 # Author: Claudiu Chirila
  # Story: SDE-14 - OmniClient :: Launch Application
  # Date Created:
  # Date Signed Off:

 @manual
 Feature: As a Selling Manager, I want to see all Lists and Events created for the Selling Associates who I oversee so that I can monitor status and avoid creating duplicate Lists1



@domain_stores @omniclient @story_SDE-14 @website @bcom
Scenario: Selling Manager is able to view Lists created by another Selling Manager1

  Given I launch the bloomingdales's omniclient page
  When I sign into Omniclient BLM application as Selling Manager
  And I log out from the application
  When I sign in as another selling manager in BLM
  When I navigate to MY TASKS page
  And I click on LISTS tab from BLM

#And I click on the Switch User button
#And I enter "EIGHTY NI" credentials
#And I select the "EIGHTY NINE" credentials from the dropdown
#And click the Switch button
#Then I should be switched into the selected "sales manager" from BLM
#When I navigate to MY TASKS page
#And I click on LISTS tab from BLM