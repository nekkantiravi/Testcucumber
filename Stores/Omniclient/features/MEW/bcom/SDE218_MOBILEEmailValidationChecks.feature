# Author: Ovidiu Rucoi
# Story: SDE-218 - MOBILE: Email Validation Checks
# Date Created: 08/08/2017
# Date Signed Off:

Feature: As an associate using the mobile application, I want the email address I add/edit to be validated so that
  I can ensure I am adding real/accurate information and also not missing any required information.

  @domain_stores @omniclient @Story_SDE-218 @bcom @MEW
  Scenario Outline: Edit Profile Invalid Email Validation
    Given I launch the bloomingdales's omniclient page on mobile
    When I sign into OmniClient BLM mobile application as Associate
    And I click on the Search My Book button
    Then I should see the Search My Book page
    When I select By Name button from the Search My Book page
    Then I should see the By Name search page
    When I input the name "<name>" on the By Name search page
    And I click the Search button on the Search My Book page
    Then I should see the search results on the Search My Book
    When I click on the Client on the My Book Results page
    And I tap on Edit button from Contact Profile page BLM
    And I tap on Edit Email from Edit Contact page
    And I verify that each incorrect email is rejected with an ERROR message mobile BLM:
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

    Examples:
      | name       | type     |
      | KONSTANCE  | CUSTOMER |
      | ALECSANDRI | CONTACT  |

  @domain_stores @omniclient @Story_SDE-218 @bcom @MEW
  Scenario Outline: Edit Profile autocorrect Email
    Given I launch the bloomingdales's omniclient page on mobile
    When I sign into OmniClient BLM mobile application as Associate
    And I click on the Search My Book button
    Then I should see the Search My Book page
    When I select By Name button from the Search My Book page
    Then I should see the By Name search page
    When I input the name "<name>" on the By Name search page
    And I click the Search button on the Search My Book page
    Then I should see the search results on the Search My Book
    When I click on the Client on the My Book Results page
    And I verify that each incorrect email is autocorrected mobile BLM:
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
      | test@iCl0ud.com  | test@icloud.com  | email address contain icl0ud should auto-correct to 'o'       |

    Examples:
      | name       | type     |
      | KONSTANCE  | CUSTOMER |
      | ALECSANDRI | CONTACT  |

  @domain_stores @omniclient @Story_SDE-218 @bcom @MEW
  Scenario: I must have a valid email address on file in order to save it with a preferred contact method of Email
    Given I launch the bloomingdales's omniclient page on mobile
    When I sign into OmniClient BLM mobile application as Associate
    And I click on the Search All Clients button
    Then I should see the Search All Clients page
    When I select Telephone button from the Search All Clients page
    Then I should see the Telephone Number search page
    When I input telephone number "2223334445" on the Telephone Number search page
    And I click the Search button on the Search All Clients page
    And I tap on Create Client button
    And I write the first name "Luke" in the First Name field
    And I write the last name "Skywalker" in the Last Name Field
#    And I select the Phone Type from Phone Type dropdown
    And I write the required Address "Moisture Farm"
    And I write the city in the City field "Tatooine"
    And I write the zip code "12345" in the Zip code field
    And I select random state from State dropdown mobile
    And I tap on Save button from Add Client page
    And I write a hint in the Hint field "Jedi Mind Tricks"
    And I select Preferred Contact method as email from dropdown
    And I tap on Save button from Add Client page
    Then I should see the email address required attention popup
    When I click on the OK button on the error popup from CLIENT INFO section BLM mobile
    And I write the email in the Email field "luke@skywalker.com"
    And I tap on Save button from Add Client page
    And I select Preferred Contact method as email from dropdown
    And I write a hint in the Hint field "Jedi Mind Tricks"
    And I tap on Save button from Add Client page
    And I tap OK in the add confirmation popup
    Then existing email "luke@skywalker.com" information is displayed