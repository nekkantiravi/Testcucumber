# Author: Claudiu Chirila
# Story:
# Date Created: 05/19/2017
# Date Signed Off:

Feature: As a BLM user, I want to launch and login to the OmniClient application with various user types and then logout and login with another type of user

  @domain_stores @omniclient @story_login @bcom
  Scenario: All BLM user types launch and logs into the OmniClient application and land on the HomePage and then they logout

Given I launch the bloomingdales's omniclient page
And I sign into Omniclient BLM application as associate
And I log out from the application
And I sign into Omniclient BLM application as corporate admin
And I log out from the application
And I sign into Omniclient BLM application as General Manager
And I log out from the application
And I sign into Omniclient BLM application as Corporate Store Executive
And I log out from the application
And I sign into Omniclient BLM application as Selling Manager

