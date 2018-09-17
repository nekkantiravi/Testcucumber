#Author: Shatabdi Sheet
#Date Created:  4th April 2014
#Date Signed Off:

Feature:  To verify My Account page functionality

  #BLCOM-80180
  @use_launch_call @s4a_stable @domain_marketing
  Scenario:Verify My Account link on Home Page navigates to My Account Page after user SignsIn
    Given I visit the web site as a guest user
    When I create a new profile
    Then I sign out from profile
    And I visit the web site as a guest user
    When I navigate to sign in page
    And I sign in using existing username and password
    Then I should navigate to my account page

   #Mingle Story: http://mingle/projects/market/cards/595
  @upi_595_bcom @artifact_shopapp @priority_high @myaccount_4 @domain_marketing @use_domain_qual
  Scenario: Verify all My Account Pages are rendered for known user
    Given I visit the web site as a guest user
    And I sign-in to my existing profile if in prod else I create a new profile
    When I navigate to the My Account Page
    Then I verify the basic attributes of the My Account Page
    And I verify the My Account Pages are rendered properly
      | My Profile                   |
      | My AddressBook               |
      | My Wallet                    |
      | My Wishlist                  |
      | FAQ                          |
      | Order Status and History     |
      | My Bloomingdales Credit card |

  @upi_595_bcom @artifact_shopapp @priority_medium @myaccount_4 @domain_marketing
  Scenario: Verify My Account Page is rendered for a newly registered loyallist user
    Given I visit the web site as a loyallist
    When I navigate to the My Account Page
    Then I verify the breadcrumb of My Account page
    Then I verify the My Account Pages are rendered properly
      | my profile                    |
      | My Address Book               |
      | My Wallet                     |
      | My Wish List                  |
      | My Points                     |
      | Bonus Offers                  |
      | Member Benefits               |
      | FAQs                          |
      | My Order Status & History     |
      | My Bloomingdale's Credit Card |

#TEST-LINK-MCOM-83332
  @upi_595_bcom @artifact_shopapp @priority_low @sstbacklog @myaccount_1 @domain_marketing
  Scenario: Verify My Account Page is rendered properly for registered user
    Given I visit the web site as a registry user
    When I sign out from profile
    And I sign in with credentials generated previously
    Then I verify the basic attributes of My Account page

  #BLCOM-80181
  @use_bat_next @use_dsv_3 @mustpass @s4a_stable @domain_marketing
  Scenario: My Account - Rendering My Account Page as a newly registered user
    Given I visit the web site as a guest user
    And I sign-in to my existing profile if in prod else I create a new profile
    When I navigate to the My Account Page
    Then I verify the breadcrumb of My Account page
    Then I verify the My Account Pages are rendered properly
      | my profile                    |
      | My Address Book               |
      | My Wallet                     |
      | My Wish List                  |
      | My Points                     |
      | Member Benefits               |
      | FAQs                          |
      | My Order Status & History     |
      | My Bloomingdale's Credit Card |
  # Notes:
  # as a newly registered user, user does not have CC, no Orders, no Loyalty
  # verify all the sections are displayed in left nav

 ############################################## My Wishlist Section #####################################################

  @upi_595_bcom @artifact_legacy @priority_medium @myaccount_4 @s4a_stable @domain_marketing
  Scenario:My Account - Rendering my WishList section - user with no WishList
    Given I visit my account page as a signed user
    When I navigate to My Account page
    Then I verify the basic attributes of the my WishList section not having any wishlist

  #BLCOM-80183
  @use_dsv @mustpass @use_launch_call @domain_marketing

  Scenario: Verify the Added to WishList overlay
    Given I visit the web site as a registered user
    When I select wishlist link in header
    And I delete all available wishlists
    And I create a list "dsv_test" from wishlist page
    When I navigate to a product having "orderable and available" attributes
    And I add the product to wishlist from PDP
    And I click the WishList link on the overlay
    Then I should see My WishList landing page
    And I select a list "dsv_test" in wishlist page
    And I delete the selected list in wishlist page
  # Notes:
  # "This item has been added to your wishlist" message overlay
  # with the wishlist link should be displayed

###################################################### My Profile Section ############################################

  @upi_595_bcom @artifact_shopapp @priority_medium @myaccount_4 @s4a_stable @domain_marketing
  Scenario: My Account - Rendering my profile section
    Given I visit my account page as a signed user
    When I navigate to My Account page
    Then I verify the basic attributes of the my profile section on My Account Page
    When I navigate to the "View/Edit My Profile" link in My Account page
    Then I verify that the "View/Edit My Profile" page is rendered

  ###################################################### My Credit Card Section ########################################

  @upi_595_bcom @artifact_shopapp @priority_medium @myaccount_4 @s4a_stable @domain_marketing
  Scenario: My Account - Rendering my credit card section - user does not have a CC
    Given I visit the web site as a registered user
    When I navigate to the My Account Page
    Then I verify the basic attributes of the my bloomingdales card section
    Then I verify the My Account Pages are rendered properly
      | Learn about the benefits |
      | Go to My Profile         |

  ############################################## My Loyallist Section ###################################################
# Below test scenario is covered as part of WalletUIOnMyAccountPage.feature file last 2 scenarios
#  @use_regression @prod_myaccount @upi_595_bcom @artifact_legacy @priority_medium @myaccount_4 @s4a_stable @domain_marketing
#  Scenario: My Account - Rendering My Loyallist Account Section - user is not enrolled in loyalty
#    Given I visit my account page as a signed user
#    Then I should verify the basic attributes of the My Loyallist Account section
#    And I verify the bWallet section for loyalty links "Become a Loyallist" and "Add my Loyallist Account"
##      | Add your Loyallist number |
##      | enroll now                |

  @upi_bcom_14F @upi_595_bcom @artifact_legacy @priority_medium @domain_marketing
  Scenario: My Account - Rendering My Loyallist Account Section - user is a 3rd-party customer enrolled in LoyalList with a LoyalList card
    Given I visit the web site as a registered user
    When I navigate to the loyalty account summary page
    Then I can associate my account by loyallist number using "thirdparty_loyallist_details"
    Then I verify the account summary page for the loyallist

  @upi_bcom_14F @upi_595_bcom @artifact_legacy @priority_medium @s4a_stable @domain_marketing
  Scenario:My Account - Rendering My Loyallist Account Section - User has a BCC (they are auto-enrolled in LoyalList), but does not have their card linked to their BCOM profile
    Given I visit my account page as a signed user
    When I navigate to My Account page
    Then I should verify the basic attributes of the My Loyallist Account section
    When I navigate to the "Add your Loyallist number" link in My Account page
    Then I verify that the "Add Loyallist Page" page is rendered

  @use_bat_next  @upi_631_bcom @upi_595_mcom  @artifact_shopapp @priority_high @myaccount_4 @domain_marketing
  Scenario: Verify all valid sections are displayed under my account section
    Given I visit the web site as a loyallist
    When I navigate to the My Account Page
    Then I should be able to visit all of the sections under My Account

  ################################################ My Preferred Store Section ##########################################

  # The below test cases are obsolete from 17Q on wards as Preferred Store has moved to My Preferences page.
#  @use_regression @use_bat_next @prod_myaccount @upi_595_bcom @artifact_shopapp_2 @priority_medium @myaccount_4 @artifact_shopapp @domain_marketing
#  Scenario: My Account - Rendering my preferred store Section with select this store
#    Given I visit the web site as a guest user
#    When I create a new profile
#    And I select a random store with store name in Choose a Preferred Store Overlay
#    Then I should verify that the selected store details are displayed in the my preferred store Section on My Account Page
#    Then I verify the My Account Pages are rendered properly
#      | Map/Directions       |
#      | Store Events         |
#      | Today's Hours - More |
#      | Services - More      |
#      | Restaurants - More   |

##BLCOM-80181
#  @use_regression @artifact_shopapp_2 @myaccount_1 @artifact_shopapp @prod_myaccount @bat_refactored @use_dsv @mustpass  @s4a_stable @domain_marketing
#  Scenario: My Account - my preferred store Section - select a store
#    Given I visit the web site as a guest user
#    And I sign-in to my existing profile if in prod else I create a new profile
#    And I navigate to the My Account Page
#    And I change location on my preferred store Section on the My Account Page
#    And I select a store from the drop down on the Choose a Preferred Store Overlay
#    Then I verify that at-least one store is displayed in the results
#
#  @use_regression @14B @googlemapsmigration_1686 @priority_medium @artifact_shopapp_2 @bat_refactored @myaccount_1 @artifact_shopapp @s4a_stable @domain_marketing
#  Scenario: My Account - my preferred store Section - Select this store
#    Given I visit the web site as a guest user
#    When I create a new profile
#    And I change location on my preferred store Section on the My Account Page
#    Then I verify the basic attributes of the Choose a Preferred Store Overlay
#    And I select a store from the drop down on the Choose a Preferred Store Overlay
#    When I select the first store
#    Then I "should not" see Choose a Preferred Store overlay
#    And I should see "selected" store set as preferred store
#
#
#  @use_regression @14B @googlemapsmigration_1686 @priority_medium @artifact_shopapp_2 @myaccount_1 @artifact_shopapp  @s4a_stable @domain_marketing
#  Scenario: My Account - my preferred store Section - Closing overlay
#    Given I visit the web site as a guest user
#    When I create a new profile
#    And I change location on my preferred store Section on the My Account Page
#    Then I verify the basic attributes of the Choose a Preferred Store Overlay
#    When I select close button in Choose a Preferred Store overlay
#    Then I "should not" see Choose a Preferred Store overlay
#    And I should see "same" store set as preferred store
#
#  @use_regression @prod_myaccount @upi_595_bcom @artifact_shopapp_2 @priority_medium @myaccount_4 @artifact_shopapp @domain_marketing
#  Scenario: My Account - my preferred store Section - find by zipcode
#    Given I visit the web site as a guest user
#    When I create a new profile
#    And I select a random store with zip code in Choose a Preferred Store Overlay
#    Then I should verify that the selected store details are displayed in the my preferred store Section on My Account Page
#
#  @use_regression  @upi_BCOM @upi_595_bcom @artifact_shopapp @priority_medium @myaccount_4 @domain_marketing
#  Scenario: MY Account - my preferred store section - no store selected
#    Given I visit the web site as a guest user
#    When I create a new profile
#    When I select a random store with store name in Choose a Preferred Store Overlay
#    Then I should verify that the selected store details are displayed in the my preferred store Section on My Account Page
#    When I select a random store with zip code in Choose a Preferred Store Overlay
#    Then I should verify that the selected store details are displayed in the my preferred store Section on My Account Page

  #  @use_regression @use_dsv @upi_595_bcom @artifact_shopapp @priority_low @domain_marketing @myaccount_5 @use_e2e
#  Scenario: Verify Change store on my account
#    Given I visit my account page as a signed user
#    When I navigate to My Account page
#    And I change location on my preferred store Section on the My Account Page
#    And I find by zipcode on the Choose a Preferred Store Overlay
#    And I select the first store
#    Then I "should not" see Choose a Preferred Store overlay
#    And I should verify that the selected store details are displayed in the my preferred store Section on My Account Page
  #Notes:
  #click on change location
  #verify change your preferred store popup is displaying and select the store from the drop down list.
  #click on select this store.
  #verify changed store on my account page
  #  Description: verify Change store on my account
  #  Steps
  #  1.Navigate to Bloomingdales.com
  #  2.Sign in with valid username and password.
  #  3.Click on my account link.
  #  4.Verify my preferred store is displaying.
  #  5.click on change location
  #  6.Verify change your preferred store popup is displaying and select the store from the drop down list.
  #  7.Click on select this store.
  #  8.Verify changed store on my account page.
  #
  #  Expected Results
  #  1.Bloomingdales homepage should display.
  #  2.User should signed in.
  #  3.My preferred store should display.
  #  4.Change location link should display.
  #  5.Change you preferred store popup should display.
  #  6.store should be selected from the drop down list.
  #  7.Select this store should clickable and store should be selected.
  #  8.Selected store should display under my preferred store in my account page.

  ######################################################## My Registry Section #########################################

  @prod_myaccount @upi_595_bcom @artifact_shopapp_2 @priority_medium @myaccount_4 @artifact_shopapp  @s4a_stable @domain_marketing
  Scenario: My Account - Rendering my Registry section - user does not have a Registry
    Given I visit my account page as a signed user
    When I navigate to My Account page
    And I do not have a registry attached to my profile
    Then I verify the basic attributes of the my Registry section on the My Account Page without a registry
  # Notes:
  # verify that my Registry section is not be displayed when the user does not have any Registry

  @prod_myaccount @upi_595_bcom @artifact_legacy @priority_medium @myaccount_4 @s4a_stable @domain_marketing
  Scenario: My Account - Rendering my Registry section - user has Registry
    Given I visit the web site as a guest user
    When I create a new wedding registry
    And I return to home page from registry page
    And I navigate to the My Account Page
    Then I verify the basic attributes of the my Registry section on the My Account Page
    And I verify the My Account Pages are rendered properly
      | registrants first name and last name as link |
      | Log in to your Registry                      |

  ########################################### My Order History Section #################################################

  #BLCOM-80206
  @upi_631_bcom @upi_595_mcom @artifact_shopapp @priority_medium @health_check  @s4a_stable @domain_marketing @myaccount_7
  Scenario: Verify when user clicks on Order number link then it should redirect to Order Details page
    Given I visit my account page as a signed user
    When I add a "available and orderable" radical product to my bag
    And I checkout until I reach the "order confirmation" page as a "signed in" user
    And I navigate to the My Account Page
    And I Click any order number display under my orders section
    Then I should navigate to shopapp "OD" page

  @prod_myaccount @upi_595_bcom @artifact_shopapp_2 @priority_medium @artifact_shopapp @s4a_stable @domain_marketing @myaccount_7
  Scenario: My Account - My Order History section - user with no Orders
    Given I visit my account page as a signed user
    When I navigate to My Account page
    And I have no orders attached to my profile
    Then I verify the basic attributes of the My Order History section on the My Account Page
    And I verify the My Account Pages are rendered properly
      | View All |

################################################## Manual Scenarios  #################################################


  #TEST-LINK-BLCOM-80253
  @upi_631_bcom @upi_595_mcom @artifact_shopapp @priority_high @domain_marketing @myaccount_3 @use_bat
  Scenario: Verifying recently purchased items with recommendations in My Account page
    Given I visit the web site as a registered user
    When I add 2 random products to my bag and checkout
    And I checkout until I reach the "responsive order confirmation" page as a "signed in" user
    And I select continue shopping button
    And I should be on home page
    When I navigate to the My Account Page
    Then I should see recently purchased Items with recommendations under my order history section
    When I click on "right scroll" button display under recently purchased items section
    Then I should see next recently purchased Item with recommendations
  # Notes:
  # Recently Purchased Items panel should include below elements
  # For MCOM -
  # Title - you recently purchased > you might also like
  # For BCOM -
  # Title - Recently Purchased Items > you might also like
  # Pagination with right and left navigation arrow keys

  # MCOM-83333
  @manual @wip @domain_marketing
  Scenario: Verify the behaviour when the product in the recently purchased list is not available on My Account Page
    Given I visit the web site as a registered user
    And I place an order in Real time
    When I navigate to the My Account Page
    And I should see recently purchased Items with recommendations that are not available
    Then I should not be able to Click product thumbnail and description of the recently purchased items
  # pre-condition - product in the recently purchased list should not be available


  @manual @wip @domain_marketing
  Scenario: Verify the redirection when user click on the available product in the recently purchased list on My Account Page
    Given I visit the web site as a registered user
    And I place an order in Real time
    When I navigate to the My Account Page
    When I click any product thumbnail display under the recently purchased list from My Account page
    Then I should navigate to the appropriate pdp page

  @14B @googlemapsmigration_1686 @manual @wip @domain_marketing
  Scenario: My Account - Rendering my preferred store Section Map/Directions link
    Given I visit the web site as a guest user
    When I create a new profile
    And I change location on my preferred store Section on the My Account Page
    Then I verify the basic attributes of the Choose a Preferred Store Overlay
    When I select the first store
    Then I should verify that the selected store details are displayed in the my preferred store Section on My Account Page
    When I click Map/Directions link in My Account page
    Then I should see Google Map pop up window in direction mode with store location filled in destination field


 #######################################################################################################################
