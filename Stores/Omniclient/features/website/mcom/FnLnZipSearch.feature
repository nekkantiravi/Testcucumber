#Author: your.email@your.domain.com
#Story:
#Date Created:
#Date Signed Off:

Feature: As an associate, I want to launch, login and search for a client using FN, LN and zip
	

@domain_stores @omniclient @story_fnlnzip_search
Scenario: Launch, login and search for a client using a FN,LN and zip
Given I launch the macy's omniclient page
   When I sign into Omniclient application as associate
   And I click on the Search Radio button
   And I enter "NORESULTS" in the "first name" textbox
   And I enter "TEST" in the "last name" textbox
   And I enter "44870" in the "zip code" textbox
   And I select the Search button in Omniclient home screen
   Then the No Search results screen is displayed in Omniclient search results page