# Author: Ovidiu Rucoi
# Story: SDE-180 - Edit Profile: Email Validation Checks
# Date Created: 07/05/2017
# Date Signed Off:

Feature: As an associate, I want the email address I add/edit on a Client/Customer/Contact profile to be validated
  so that I can ensure I am adding real/accurate information and also not missing any required information.

  @domain_stores @omniclient @story_SDE-180 @website @bcom
  Scenario: Edit Profile Invalid Email Validation
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I click on My Book radio button
    And I type the name of a customer "KRAZY" in the search box
    And I click on the omniclient search button
    And I click on the searched client from the customers results list
    And I navigate to Manage Customer tab
    And I click on the Edit button from the CUSTOMER INFO section BLM
    And we click on ADD button from Emails section BLM
    And I verify that each incorrect email is rejected with an ERROR message BLM:
      | test@email@yahoo.com    | must contain exactly one '@' character                                |
      | @gmail.com              | must contain at least one character before the '@' character          |
      | dot.@yahoo.com          | cannot contain an '@' character directly following a '.'              |
      | test@                   | must contain at least one character directly after the '@' character  |
      | test@_yahoo.com         | cannot contain an '_' after the '@' character                         |
      | test@.yahoo.com         | cannot have a '.' directly following the '@' character                |
      | test@yahoocom           | must contain at least one '.' after the '@' character                 |
      | .test@gmail.com         | cannot start with a period (.)                                        |
      | test@yahoo.com-         | cannot end with a '-' or '\u2014' <dash or hyphen>                    |
      | test@yahoo.c            | must contain at least 2 characters after the last '.'                 |
      | t@o.c                   | must be at least 6 characters                                         |
      | ?~!#$%^&*()=+@yahoo.com | can only contain numbers, letters, @ , periods, dashes and underscore |

  @domain_stores @omniclient @story_SDE-180 @website @bcom
  Scenario: Edit Profile autocorrect Email
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I click on My Book radio button
    And I type the name of a customer "KRAZY" in the search box
    And I click on the omniclient search button
    And I click on the searched client from the customers results list
    And I navigate to Manage Customer tab
    And I verify that each incorrect email is autocorrected BLM:
      | test@yahoo.c0m   | test@yahoo.com   | email address ends with .c0m should auto-correct to be .com   |
      | test@yahoo.c0n   | test@yahoo.com   | email address ends with .c0n should auto-correct to be .com   |
      | test@yahoo.cpm   | test@yahoo.com   | email address ends with .cpm should auto-correct to be .com   |
      | test@yahoo.cpn   | test@yahoo.com   | email address ends with .cpn should auto-correct to be .com   |
      | test@yahoo.con   | test@yahoo.com   | email address ends with .con should auto-correct to be .com   |
      | test@macys.0rg   | test@macys.org   | email address ends with .0rg should auto-correct to be .org   |
      | test@macys.g0v   | test@macys.gov   | email address ends with .g0v should auto-correct to be .gov   |
      | test@macys.inf0  | test@macys.info  | email address ends with .inf0 should auto-correct to be .info |
      | test@yah0o.com   | test@yahoo.com   | email address contain Yah0o should auto-correct to 'o'        |
      | test@h0tmail.com | test@hotmail.com | email address contain H0tmail should auto-correct to 'o'      |
      | test@outlook.com | test@outlook.com | email address contain 0utlook should auto-correct to 'o'      |
      | test@a0l.com     | test@aol.com     | email address contain A0L should auto-correct to 'o'          |
      | test@inb0x.com   | test@inbox.com   | email address contain Inb0x should auto-correct to 'o'        |
      | test@iCl0ud.com  | test@iCloud.com  | email address contain icl0ud should auto-correct to 'o'       |