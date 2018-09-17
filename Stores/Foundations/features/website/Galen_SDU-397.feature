# Author: Stores Domain Checkout Team
# Story: SDU-397 - Checkout :: Update fonts in CSG
# Date Created: 5/4/2017
# Date Signed Off:

Feature: As an associate, I want to be able to use our application on multiple size devices/screens.
  I want the content of those screens to be responsive and scale appropriately for ease of use.

  Scenario: Verifying all of the below scenarios using gaven tool.
    Given I launch the macy's store domain CSG
    Then I should see the CSG UI page as expected


#  @domain_stores @project_checkout @release_1708 @story_SDU-397 @wip
#  Scenario: Associates should see appropriately sized type on mobile screens. If an associate moves from one sized
#  screen to another, the typeface should scale to that screen.
#
#    Given I launch the macy's store domain CSG
#    And I am using a mobile device screen 320 x 568 mobile
#    And I should see "H1" font-size set to "2.488" rem
#    And I should see "H2" font-size set to "2.074" rem
#    And I should see "H3" font-size set to "1.728" rem
#    And I should see "H4" font-size set to "1.44" rem
#    And I should see "H5" font-size set to "1.2" rem
#    And I should see "H6" font-size set to "1.0" rem
#    And I should see "x-small" font-size set to "0.833" rem
#    And I should see "xx-small" font-size set to "0.684" rem
#
#
#  Scenario: Associates should see appropriately sized type on legacy register screens. If an associate moves from one
#  sized screen to another, the typeface should scale to that screen.
#    Given I launch the macy's store domain CSG
#    And I am using a legacy register screen 1024px x 768 old monitors
#    And I should see "H1" font-size set to "2.488" rem
#    And I should see "H2" font-size set to "2.074" rem
#    And I should see "H3" font-size set to "1.728" rem
#    And I should see "H4" font-size set to "1.44" rem
#    And I should see "H5" font-size set to "1.2" rem
#    And I should see "H6" font-size set to "1.0" rem
#    And I should see "x-small" font-size set to "0.833" rem
#    And I should see "xx-small" font-size set to "0.684" rem
#
#  Scenario: Associates should see appropriately sized type on next generation screens. If an associate moves from one
#  sized screen to another, the typeface should scale to that screen.
#    Given I launch the macy's store domain CSG
#    And I am using a next generation register screen 1366px x 768  monitors
#    And I should see "H1" font-size set to "2.488" rem
#    And I should see "H2" font-size set to "2.074" rem
#    And I should see "H3" font-size set to "1.728" rem
#    And I should see "H4" font-size set to "1.44" rem
#    And I should see "H5" font-size set to "1.2" rem
#    And I should see "H6" font-size set to "1.0" rem
#    And I should see "x-small" font-size set to "0.833" rem
#    And I should see "xx-small" font-size set to "0.684" rem
#
#  Scenario: Associates should see appropriately sized type on next generation large screens. If an associate moves from
#  one sized screen to another, the typeface should scale to that screen.
#    Given I launch the macy's store domain CSG
#    And I am using a next generation large register screen  1920 x 1080  monitors
#    And I should see "H1" font-size set to "2.488" rem
#    And I should see "H2" font-size set to "2.074" rem
#    And I should see "H3" font-size set to "1.728" rem
#    And I should see "H4" font-size set to "1.44" rem
#    And I should see "H5" font-size set to "1.2" rem
#    And I should see "H6" font-size set to "1.0" rem
#    And I should see "x-small" font-size set to "0.833" rem
#    And I should see "xx-small" font-size set to "0.684" rem