# Author: Ovidiu Rucoi
# Story:
# Date Created: 05/11/2017
# Date Signed Off:


Feature: As a BLM user, I want to launch and login to the OmniClient application with various roles

  @pipeline @domain_stores @omniclient @story_login @bcom
  Scenario Outline: All BLM associate roles launch and logs into the OmniClient application and land on the HomePage.

    Given I launch the bloomingdales's omniclient page
    When I enter BLM "<username>" in username field of Omniclient login page
    Then I enter BLM "<password>" in password field of Omniclient login page
    And I click Sign In button of BLM Omniclient login page
    Then I should be logged in "<role>" and see the BLM omniclient landing page

    Examples:
      | username | password    | role      |
      | 10000083 | Temp$Pass83 | associate |
#      | 10000089 | Temp$Pass89  | sales manager    |
#      | 10000091 | Temp$Pass91  | general manager  |
#      | 10000093 | Temp$Pass93  | district manager |
#      | 10000102 | Temp$Pass102 | corp admin       |
#      | 10000103 | Temp$Pass103 | corp store exec  |
   
    
    
