############################################
# Author: Discovery Regression QE Team
# Date: 21st June 2017
# Date Modified: None
############################################

Feature: Verify NavApp specific responses in ALL server instances

  #
  @domain_discovery @priority_high @mode_domestic @wip
  Scenario: ValidateNavApp - Verify NavApp specific response in all server instance
    Given I get all server instance names from IFSCAP
    When I read the version from the page source
    Then I verify that the versions on all servers are identical
  # Notes:
  # Hit IFSCAP and get the instance name based on environment type [RTP CELLA /RTP CELL B/DAL]
  # Make an api call to one of the instances and keep it as benchmark
  # Validate the api responses from all other instances against the bench mark 



