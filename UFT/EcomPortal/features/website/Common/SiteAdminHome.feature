# Author: Casey Grimm
# Date Created: 06/21/2016
# Date Modified: 06/21/2016

Feature: Verify the structure of the Site Admin home page

  @use_bat_next @sst
  Scenario: Verify Site Admin home page
    Given I login into site admin portal as a valid user
    Then I verify menu items panel on site admin page
