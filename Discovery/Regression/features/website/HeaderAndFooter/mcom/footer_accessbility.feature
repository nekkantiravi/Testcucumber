#######################################################################
# B-100723 :: MCOM :: Header Redesign :: ACCESSIBILITY :: Desktop Footer
# Author: Discovery QE
# Date Created: 12/27/2017
######################################################################

Feature: Verify Desktop footer to be WCAG 2.0 AA compliant and accessible

  Scenario Outline: Verify the contentinfo role is added to footer
    Given I visit the web site as a guest user in "<mode>" mode
    When I scroll the page down
    And I verify the footer contains role contentinfo
    Examples:
      | mode     |
      | domestic |
      | registry |
      | iship    |

  Scenario Outline: Verify decorative images are hidden to users
    Given I visit the web site as a guest user in "<mode>" mode
    When I scroll the page down
    And I verify the country flag is hidden for website
    Examples:
      | mode     |
      | domestic |
      | registry |
      | iship    |

  Scenario Outline: Verify mobile app image is hidden to users
    Given I visit the web site as a guest user in "<mode>" mode
    When I scroll the page down
    And I verify the mobile app image is hidden
    Examples:
      | mode     |
      | domestic |
      | registry |
      | iship    |

  Scenario Outline: Verify text font is as recommended
    Given I visit the web site as a guest user in "<mode>" mode
    When I scroll the page down
    Then I verify the font size is 11 px
    And I verify the type color is #333333
    And I verify the font is Helvetica, Arial,sans-serif
    Examples:
      | mode     |
      | domestic |
      | registry |
      | iship    |

  Scenario Outline: Verify the label is removed for what's happening at macys link
    Given I visit the web site as a guest user in "<mode>" mode
    When I scroll the page down
    And I verify the whats happening at macys link does not contain label tag
    Examples:
      | mode     |
      | domestic |
      | registry |
      | iship    |

  Scenario Outline: Verify the what's happening at macys link is working correctly after label removal
    Given I visit the web site as a guest user in "<mode>" mode
    When I scroll the page down
    Then I verify the WHAT'S HAPPENING AT MACY'S link in the Footer
    And I verify the  WHAT'S HAPPENING AT MACY'S page is rendered properly
    Examples:
      | mode     |
      | domestic |
      | registry |
      | iship    |