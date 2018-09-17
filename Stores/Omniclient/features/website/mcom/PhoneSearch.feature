#Author: Traci Morris
#Story:
#Date Created: 03/28/2017
#Date Signed Off:

Feature: As an associate, I want to launch, login and search for a client using phone number
	

@domain_stores @omniclient @story_phone_search
Scenario: Launch, login and search for a client using a phone number
Given I launch the macy's omniclient page
   When I sign into Omniclient application as associate
   And I enter "4402335478" in the "phone" textbox
   And I select the Search button in Omniclient home screen
   Then the No Search results screen is displayed in Omniclient search results page

