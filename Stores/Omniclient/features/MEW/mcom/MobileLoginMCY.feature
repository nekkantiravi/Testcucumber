# Author: Traci Morris
# Story:
# Date Created: 05/04/2017
# Date Signed Off:


Feature: As a user, I want to launch and login to the OmniClient application with various roles

  @pipeline @domain_stores @omniclient @MEW @story_login
  Scenario Outline: All associate roles launch and logs into the OmniClient application and land on the HomePage.

    Given I launch the macy's omniclient page on mobile
    When I enter "<username>" in username field on OmniClient mobile login page
    Then I enter "<password>" in password field on OmniClient mobile login page
    And I click Log In button on OmniClient mobile login page
    Then I should be logged in as "<role>" and see the OmniClient mobile landing page

    Examples:
      | username | password   | role      |
      | 10000051 | Temp$Pass1 | associate |
#      | 10000057 | Temp$Pass7   | sales manager    |
#      | 10000059 | Temp$Pass9   | general manager  |
#      | 10000061 | Temp$Pass11  | district manager |
#      | 10000069 | Temp$Pass19  | corp admin       |
#      | 10000117 | Temp$Pass117 | corp store exec  |
   
    
    
