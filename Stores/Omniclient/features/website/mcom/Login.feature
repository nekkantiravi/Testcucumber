# Author: Traci Morris
# Story:
# Date Created: 03/22/2017
# Date Signed Off:


Feature: As a user, I want to launch and login to the OmniClient application with various roles

  @pipeline @domain_stores @omniclient @story_login
  Scenario Outline: All associate roles launch and logs into the OmniClient application and land on the HomePage.

    Given I launch the macy's omniclient page
    When I enter "<username>" in username field of Omniclient login page
    Then I enter "<password>" in password field of Omniclient login page
    And I click Sign In button of Omniclient login page
    Then I should be logged in "<role>" and see the omniclient landing page

    Examples:
      | username | password   | role      |
      | 10000051 | Temp$Pass1 | associate |
#    | 10000057 | Temp$Pass7   | sales manager    |
#    | 10000059 | Temp$Pass9   | general manager  |
#    | 10000061 | Temp$Pass11  | district manager |
#    | 10000069 | Temp$Pass19  | corp admin       |
#    | 10000117 | Temp$Pass117 | corp store exec  |
   
    
    
