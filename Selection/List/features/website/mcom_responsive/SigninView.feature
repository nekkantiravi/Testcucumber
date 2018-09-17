#---------------------------------------------------
# Brand         : MCOM_responsive
# Author        : vijaya bharathi
# Date Created	: Aug.11,2017
#---------------------------------------------------

Feature: Sigin view of List use cases and verification

  @responsive_list @domain_selection @project_MCOM
  Scenario: As a  signed in user who has no list land on list page
    Given I visit the web site as a registered user
    And I set cookie for SSC to see responsive experience
    And I land on List landing page
    #And I should land of list of list page
    #And when I click on Default list
    And I verify the basic components on the page for "SignedIn" user
    #All social Icon
    #Send me AlertButton
    #print
    #Email
    # see all lists
    #Keep Shopping Button
    #settings




#  @responsive_list @domain_selection @project_MCOM @wip
  Scenario: As a  signed in user who has default list lands on list page and clicks
    Given I visit the web site as a registered user
    And I click on List link in the header
    #And I should land of list of list page
    #And when I click on Default list
    And I verify the basic components on the page for "Signed In" user
    And I click on the "Keep brwosing" button
    Then I should land on Home page

#
#
#  @responsive_list @domain_selection @project_MCOM @wip
#  Scenario: As a signed in user who has default list with one available upc level item lands on list page
#    Given I visit the web site as a registered user
#    #And I navigate directly to member PDP and add a product to my list
#    When I select a "member" product and navigate to PDP in "site" mode
#    And I navigate directly to list by url
#    And I verify the basic componenets on the page
#    #Send me AlertButton
#    #All social Icon
#    #print
#    #Email
#    #see all lists
#    #settings
#    And I verify "Defaultlist" with username showing up on List Page
#    And I should verify the no of items as "1 items"
#    And I should verify basic component for the Items on the page
#    #Price
#    #Add to Bag Button
#    #Move
#    #Delete
#
#
#  @responsive_list @domain_selection @project_MCOM @wip
#  Scenario: As a signed in user who has default list with one available upc level from master product item lands on list page
#    Given I visit the web site as a registered user
#    And I add one available product thru service
#    And I navigate directly to list by url
#    And I verify the basic componenets on the page
#    #Send me AlertButton
#    #All social Icon
#    # print
#    #Email
#    #see all lists
#    # settings
#    And I verify "Defaultlist" with username showing up on List Page
#    And I should verify the no of items as "1 items"
#    And I should verify basic component for the Items on the page
#    #Price
#    #Select And Add button
#    #Move
#    #Delete
#
#
#  @responsive_list @domain_selection @project_MCOM @wip
#  Scenario: As a signed in user who has one available upc level item  added from Sitemode -QV lands on list page
#    Given I visit the web site as a registered user
#    When I select a "master" product and navigate to PDP in "site" mode
#    And I scroll upDown for lazyLoad elements to load on PDP
#    And I hover over product tumbnails and click QV button
#    And I click on Add to List button on QV
#    And I navigate directly to list by url
#    And I verify the basic componenets on the page
#    #Send me AlertButton
#    #All social Icon
#    #print
#    #Email
#    #see all lists
#    #setings
#    And I verify "Defaultlist" with username showing up on List Page
#    And I should verify the no of items as "1 items"
#    And I should verify basic component for the Items on the page
#    #Price
#    #Add to Bag Button
#    #Move
#    #Delete
#
#  @responsive_list @domain_selection @project_MCOM @wip
#  Scenario: As a signed in user who has default list with one available product level item lands on list page
#    Given I visit the web site as a registered user
#    When I select a "master" product and navigate to PDP in "site" mode
#    And I add Item to List
#    And I navigate directly to list by url
#    And I verify the basic componenets on the page
#  #Send me AlertButton
#  #All social Icon
#  # print
#  #Email
#  #see all lists
#  # settings
#    And I verify "Defaultlist" with username showing up on List Page
#    And I should verify the no of items as "1 items"
#    And I should verify basic component for the Items on the page
#    #Price
#    #Add to Bag Button
#    #Move
#    #Delete
#
#
#
#  @responsive_list @domain_selection @project_MCOM @wip
#  Scenario: As a signed in user who has default list with one available upc level chanel product item lands on list page
#    Given I visit the web site as a registered user
#    When I select a "chanel" product and navigate to PDP in "site" mode
#    And I add Item to List
#    And I navigate directly to list by url
#    And I verify the basic componenets on the page
#      #Send me AlertButton
#      #All social Icon
#      # print
#      #Email
#      #see all lists
#      # settings
#    And I verify "Defaultlist" with username showing up on List Page
#    And I should verify the no of items as "1 items"
#    #Price
#    #Add to Bag Button
#    #Move
#    #Delete
#
#
#
#  @responsive_list @domain_selection @project_MCOM @wip
#  Scenario: As a signed in user who has default list with one BigTicket Item upc level item lands on list page
#    Given I visit the web site as a registered user
#    When I select a "BigTicket" product and navigate to PDP in "site" mode
#    And I add Item to List
#    And I navigate directly to list by url
#    And I verify the basic componenets on the page
#      #Send me AlertButton
#      #All social Icon
#      # print
#      #Email
#      #see all lists
#      # settings
#    And I verify "Defaultlist" with username showing up on List Page
#    And I should verify the no of items as "1 items"
#    #Price
#    #Order By Phone
#    #Move
#    #Delete
#
#  @responsive_list @domain_selection @project_MCOM @wip
#  Scenario: As a signed in user who has default list with one unavailable upc level item lands on list page
#    Given I visit the web site as a registered user
#    And I add one unavailable upc thru service
#    And I navigate directly to list by url
#    And I verify the basic componenets on the page
#      #Send me AlertButton
#      #All social Icon
#      # print
#      #Email
#      #see all lists
#      # settings
#    And I verify "Defaultlist" with username showing up on List Page
#    And I should verify the no of items as "1 items"
#    #Unavailable
#    #See More Like it
#    #Move
#    #Delete
#
#  @responsive_list @domain_selection @project_MCOM @wip
#  Scenario: As a signed in user who has default list with one unavailable product level item lands on list page
#    Given I visit the web site as a registered user
#    And I add one unavailable product thru service
#    And I navigate directly to list by url
#    And I verify the basic componenets on the page
#      #Send me AlertButton
#      #All social Icon
#      # print
#      #Email
#      #see all lists
#      # settings
#    And I verify "Defaultlist" with username showing up on List Page
#    And I should verify the no of items as "1 items"
#    #Unavailable
#    #See More Like it
#    #Move
#    #Delete
#
#
#
#  @responsive_list @domain_selection @project_MCOM @wip
#  Scenario: As a signed in user who has default list with one product multiple upc- level item lands on list page
#    Given I visit the web site as a registered user
#    And I add one unavailable product to database
#    And I navigate directly to list by url
#    And I verify the basic componenets on the page
#      #Send me AlertButton
#      #All social Icon
#      # print
#      #Email
#      #see all lists
#      # settings
#    And I verify "Defaultlist" with username showing up on List Page
#    And I should verify the no of items as "1 items"
#    #Unavailable
#    #See More Like it
#    #Move
#    #Delete
#
#  @responsive_list @domain_selection @project_MCOM @wip
#  Scenario: As a signed in user who has default list with one discontinued upc/product item lands on list page
#    Given I visit the web site as a registered user
#    And I add one discontinued upc to database
#    And I navigate directly to list by url
#    And I verify the basic componenets on the page
#      #Send me AlertButton
#      #All social Icon
#      # print
#      #Email
#      #see all lists
#      # settings
#    And I verify "Defaultlist" with username showing up on List Page
#    And I should verify the no of items as "1 items"
#    #Unavailable
#    #See More Like it
#    #Move
#    #Delete
#    #Sorry Image unavailable