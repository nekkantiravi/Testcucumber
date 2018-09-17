Feature: Verifying Pixels

  @pixels_project
  Scenario: Verifying that pixels are fired on the HomePage in Domestic Mode
    Given I visit the web site as a guest user
    Then I verify that pixels "script" tag has fired containing with "src" attribute containing the string "?aid=1033&siclientid="