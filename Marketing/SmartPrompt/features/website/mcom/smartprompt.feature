# Author: Smartprompt Project QE Team
#B-91338 QE only ::MCOM: Smart Prompt "Yes to Marketing" Pop Up
Feature: Epic Item E-10339 :: Smartprompt :: COM

#=============================================================================================
# Story Title: MCOM :: Smart Prompt "Yes to Marketing" Pop Up
# Version One: MCOM Story B-91338
# VersionOne Link: https://www14.v1host.com/Macyscom/story.mvc/Summary?oidToken=Story%3A3849486
#=============================================================================================

  @desktop @marketing_domain @wip
  Scenario: Verify the functionality of Smart Prompt when the user submits his email
    Given my online uid having Marketing Emails set to "Yes" is available in user_prompt table
    And I set the unifiedLoginEnabled header
    When I sign in with the same profile
    Then I should see Smart Prompt popup
    And I should see background screen "freeze"
    And I should see pre-populated with the current email address in email field on popup
    When I click on "submit" button
    Then I should see the pop up disappear

    @desktop @marketing_domain @wip
  Scenario: Verify the functionality of Smart Prompt when the user having Yes to Marketing Emails closes the prompt
  # Pre-requisite: User with the criteria: Yes to Marketing Emails but has a Deliverability Status: 3 (dead), 4 (invalid), 99 (implied unsub)
    Given my online uid having Marketing Emails set to "Yes" is available in user_prompt table
    And I set the unifiedLoginEnabled header
    When I sign in with the same profile
    Then I should see Smart Prompt popup
    When I click on X close option at the top of the popup
    Then I should see the pop up disappear


  @desktop @marketing_domain
  Scenario: Verify the functionality of Smart Prompt when the user having No to Marketing Emails submits his email
    # Pre-requisite: No to Marketing Emails but has a Deliverability Status: 1 (deliverable) But ONLY presenting to people who have said NO to marketing in the past 12 months since last timestamp, 3 (dead), 4 (invalid), 99 (implied unsub)
    Given my online uid having Marketing Emails set to "No" is available in user_prompt table
    And I set the unifiedLoginEnabled header
    When I sign in with the same profile
    Then I should see Smart Prompt popup
    And I should see background screen "freeze"
    And I should see pre-populated with the current email address in email field on popup
    When I click on "submit" button
    Then I should see the pop up disappear

  @desktop @marketing_domain @wip
  Scenario: Verify the functionality of Smart Prompt when the user having No to Marketing Emails closes the prompt
  # Pre-requisite: No to Marketing Emails but has a Deliverability Status: 1 (deliverable) But ONLY presenting to people who have said NO to marketing in the past 12 months since last timestamp, 3 (dead), 4 (invalid), 99 (implied unsub)
    Given my online uid having Marketing Emails set to "No" is available in user_prompt table
    And I set the unifiedLoginEnabled header
    When I sign in with the same profile
    Then I should see Smart Prompt popup
    When I click on X close option at the top of the popup
    Then I should see the pop up disappear