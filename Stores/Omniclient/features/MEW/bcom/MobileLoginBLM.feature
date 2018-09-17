# Author: Traci Morris
# Story:
# Date Created: 05/22/2017
# Date Signed Off:


Feature: As a BLM user, I want to launch and login to the OmniClient application with various roles

  @pipeline @domain_stores @omniclient @MEW @story_login @bcom
  Scenario Outline: All BLM associate roles launch and logs into the OmniClient application and land on the HomePage.

    Given I launch the bloomingdales's omniclient page on mobile
    When I enter BLM "<username>" in username field on OmniClient mobile login page
    Then I enter BLM "<password>" in password field on OmniClient mobile login page
    And I click Log In button on OmniClient mobile login page
    Then I should be logged in as "<role>" and see the OmniClient mobile landing page

    Examples:
      | username | password    | role      |
      | 10000083 | Temp$Pass83 | associate |
#      | 10000089 | Temp$Pass89  | sales manager    |
#      | 10000091 | Temp$Pass91  | general manager  |
#      | 10000093 | Temp$Pass93  | district manager |
#      | 10000101 | Temp$Pass101 | corp admin       |
#      | 10000103 | Temp$Pass103 | corp store exec  |
