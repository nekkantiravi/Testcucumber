Feature: Open FB Messenger and initiate chat with Macy's bot
# Pre-requisites if any

  @ThirdParty @chat-bot @manual
  Scenario Outline: Verify that user is able to login to Messenger
    Given I visit the messenger web site
    When I sign-in using my credentials
    Then I see messenger signed-in page

    Examples:
      | segment_value | qty_1 |

