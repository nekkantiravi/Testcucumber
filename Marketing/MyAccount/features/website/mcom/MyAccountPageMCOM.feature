#Author: Shatabdi Sheet
#Date Created:  4th April 2014
#Date Signed Off:

Feature:  As a customer, I verify my account page

  #Mingle Story: http://mingle/projects/market/cards/631
  #MCOM-91990
  @upi_631_mcom @artifact_shopapp @priority_high @health_check @myaccount_4 @use_launch_call @s4a_stable @domain_marketing @use_bat
  Scenario: Verify MyAccount Pages and links
    Given I visit the production web site as an existing user
    Then I verify the basic attributes of the My Account Page
    And I verify the My Account Pages are rendered properly
      | My Profile                  |
      | my preferences              |
      | My Address Book             |
      | My Wallet                   |
      | My Wish List                |
      | My Order Status & History   |
      | Furniture & Mattress Status |
#      | Macy's Credit Card          |
      | Gift Card Balance           |

  @use_bat_next @upi_631_mcom @artifact_shopapp @priority_medium @myaccount_4  @s4a_stable @domain_marketing
  Scenario: Verify My Account Page is rendered for a newly registered user
    Given I visit my account page as a signed user
    Then I verify the My Account Pages are rendered properly
      | my account                  |
      | my profile                  |
      | my preferences              |
      | My Address Book             |
      | My Wallet                   |
      | My Wish List                |
      | Order Status                |
      | Furniture & Mattress Status |
#      | Macy's Credit Card          |
      | Gift Card Balance           |


  ########################## My Wishlist Section ####################
   # Please refer to Selection -> Wishlist Folder for these scenarios

  ########################## My Profile Section ####################

  @prod_myaccount @upi_631_mcom @artifact_shopapp_2 @priority_medium @myaccount_7 @artifact_shopapp @s4a_stable @domain_marketing
  Scenario: My Account - Rendering my profile section
    Given I visit my account page as a signed user
    Then I verify the basic attributes of the my profile section on My Account Page
    Then I verify the My Account Pages are rendered properly
      | my profile |

  @upi_631_mcom @artifact_shopapp @priority_medium @myaccount_4 @use_launch_call @s4a_stable @domain_marketing @use_domain_qual
  Scenario: My Account - Rendering my profile section
    Given I visit my account page as a signed user
    Then I verify the basic attributes of the my profile section on My Account Page

  ########################## My Preferences Section ####################

  @use_regression @prod_myaccount @upi_631_mcom @artifact_shopapp_2 @priority_medium @myaccount_7 @artifact_shopapp  @s4a_stable @domain_marketing
  Scenario: My Account - Rendering my preferences section
    Given I visit my account page as a signed user
    Then I verify the basic attributes of the my preferences section on My Account Page
    Then I verify the My Account Page "View Preferences" link rendered properly
#      | my preferences |

########################## My Credit card Section ####################

  @prod_myaccount @upi_631_mcom @artifact_shopapp_2 @priority_medium @myaccount_7 @artifact_shopapp  @s4a_stable @domain_marketing
  Scenario: My Account - Rendering my credit card section - user does not have a CC
    Given I visit the web site as a guest user
    When I create a new profile
    And I navigate to the My Account Page
    Then I should verify the basic attributes of the my credit card section
    Then I verify the My Account Pages are rendered properly
      | Apply Now and Save |
      | Learn More         |

  ########################## My Stores Section ####################

  @use_regression @use_bat_next @prod_myaccount @use_dsv @upi_631_mcom @artifact_shopapp_2 @priority_medium @myaccount_7 @artifact_shopapp @mustpass @domain_marketing
  Scenario: My Account - Rendering my store Section with make this my store
    Given I visit the web site as a guest user
    When I create a new profile
#    And I select a random store with zip code in Choose a Preferred Store Overlay
#    Then I should verify that the selected store details are displayed in the my preferred store Section on My Account Page
    Then I verify the My Account Pages for below links are rendered properly
       | locations & hours |
       | store events      |
       | catalogs          |
       | tell us what you think|
       | macy's backstage  |
       | my stylist personal shopping|

  @prod_myaccount @upi_631_mcom @artifact_shopapp_2 @priority_medium @myaccount_7 @artifact_shopapp @mustpass @domain_marketing
  Scenario: My Account - Rendering my store Section with make this my store (for DSV)
    Given I visit the web site as a guest user
    When I create a new profile
    And I select a random store with zip code in Choose a Preferred Store Overlay
    Then I should verify that the selected store details are displayed in the my preferred store Section on My Account Page
    And I verify the My Account Pages are rendered properly
      | store locator          |
      | Store Events           |


  Scenario: My Account - Rendering my store Section with make this my store (for DSV) - store locator
    Given I visit the web site as a guest user
    When I create a new profile
    And I select a random store with zip code in Choose a Preferred Store Overlay
    Then I should verify that the selected store details are displayed in the my preferred store Section on My Account Page
    When I navigate to the "store locator" link in My Account page
    Then I verify that the "store locator" page is rendered


  Scenario: My Account - Rendering my store Section with make this my store (for DSV) - Store Events
    Given I visit the web site as a guest user
    When I create a new profile
    And I select a random store with zip code in Choose a Preferred Store Overlay
    Then I should verify that the selected store details are displayed in the my preferred store Section on My Account Page
    When I navigate to the "Store Events" link in My Account page
    Then I verify that the "Store Events" page is rendered

    # This test case is obsolete because, Choose a Preferred Store OVerlay is not present on Create Profile page as 17P release
  #@use_regression @prod_myaccount @upi_631_mcom @artifact_shopapp_2 @priority_medium @myaccount_7 @artifact_shopapp @domain_marketing @priority_high
#  Scenario: My Account - my store Section - find by zipcode
#    Given I visit the web site as a guest user
#    When I create a new profile
#    When I select a random store with zip code in Choose a Preferred Store Overlay
#    Then I should verify that the selected store details are displayed in the my preferred store Section on My Account Page
#    When I select a random store with zip code in Choose a Preferred Store Overlay
#    Then I should verify that the selected store details are displayed in the my preferred store Section on My Account Page

  ########################## My Registry Section ####################

  @prod_myaccount @upi_631_mcom @artifact_legacy @priority_medium @myaccount_7 @s4a_stable @domain_marketing
  Scenario: My Account - Rendering my Registry section - user does not have a Registry
    Given I visit my account page as a signed user
    When I navigate to My Account page
    And I do not have a registry attached to my profile
    Then I verify the basic attributes of the my Registry section on the My Account Page without a registry
    And I verify the My Account Pages are rendered properly
      | create a registry                                              |
      | first time accessing your in-store registry online? start here |
      | locate a registry                                              |
      | registry star rewards program                                  |
      | Macy's bridal salons                                           |

  @prod_myaccount @upi_631_mcom @artifact_legacy @priority_medium @myaccount_7 @domain_marketing
  Scenario: My Account - Rendering my Registry section - user has Registry
    Given I visit the web site as a guest user
    When I create a new wedding registry
    And I return to home page
    And I navigate to the My Account Page
    And I should see one time add card overlay and its components
    And I select on "add_card_overlay_no_thanks_button"
    Then I verify the basic attributes of the my Registry section on the My Account Page
    And I verify the My Account Pages are rendered properly
      | registered user locate a registry            |
      | registry star rewards program                |
      | Macy's bridal salons                         |

  ########################## My Order History Section ####################

  @prod_myaccount @upi_631_mcom @artifact_shopapp_2 @priority_medium @myaccount_7 @artifact_shopapp @s4a_stable @domain_marketing
  Scenario: My Account - Rendering My Order History section - user with no Orders
    Given I visit my account page as a signed user
    When I navigate to My Account page
    And I have no orders attached to my profile
    Then I verify the basic attributes of the My Order History section on the My Account Page
    And I verify that the Recently Purchased Items section should not be displayed
    And I verify the My Account Pages are rendered properly
      | view order history          |
      | Furniture & Mattress Status |
      | featured help topics        |

  @upi_631_bcom @upi_595_mcom @artifact_shopapp @priority_medium @myaccount_4 @use_launch_call @domain_marketing
  Scenario: Verify Order Number link redirects to Order Details Page
    Given I visit the web site as a registered user
    And I add a "available and orderable" radical product to my bag
    And I checkout until I reach the "order confirmation" page as a "signed in" user
    And I navigate to the My Account Page
    And I Click any order number display under my orders section
    Then I should navigate to order details page
    And I should see the user is redirected to valid shopapp url


    #The below test case is obsolete becuase My Account page is changed to response and no left nav bar on My Account page from 17I
#  @use_regression @upi_631_mcom @artifact_shopapp @priority_medium @myaccount_4 @s4a_stable @domain_marketing
#  Scenario:Verify My rewards section should not display on my account page
#    Given I visit my account page as a signed user
#    Then I should see reward section on my account page
  # Notes:
  # My rewards link should not be there on Left nav
  # Test case description:
  # Verify My rewards should not display on my account page
  # Test case steps:
  # 1. Navigate to macys.com
  # 2.Sign in with valid credentials and click on my account
  # Test case expected result:
  # 1. Macys.com home page should display
  # 2. In my account page, My rewards link should not be there on LNA

  #this is not a valid TC as per D-32835
  @upi_631_mcom @artifact_shopapp @priority_medium @s4a_stable @domain_marketing
  Scenario:Verify dyces product under My order
    Given I visit the web site as a registered user
    When I add an "available and orderable" radical product to my bag
      And I checkout until I reach the "order confirmation" page as a "signed in" user
      And I return to home page
      And I navigate to the My Account Page
    Then I verify dyces product under My order section

    #This is obsolete as of 17P since the new Responsive My Account page does not have left nav bar
#  @use_regression @mustpass @myaccount_4 @s4a_stable @domain_marketing
#  Scenario:Verify My rewards should not display on my account page
#    Given I visit the web site as a registered user
#    When I navigate to the My Account Page
#    Then I verify the rewards link is not displayed in left nav
    # Notes:
    # My rewards link should not be there on Left nav
    # Test case description:
    # Verify My rewards should not display on my account page
    # Test case steps:
    # 1. Navigate to macys.com
    # 2.Sign in with valid credentials and click on my account
    # Test case expected result:
    # 1. Macys.com home page should display
    # 2. In my account page, My rewards link should not be there on LNA

  @artifact_shopapp @release_16C @priority_medium @domain_marketing @project_UFT @myaccount_5
  Scenario: Verify email address is encrypted in bright tag when user sign in after selecting pay bill link from footer
    Given I visit the web site as a guest user
    When I create a new profile
    And I sign out from profile
    And I navigate through "pay bill" from footer
    And I sign in using already created profile
    Then I should see email address is encrypted in bright tag data dictionary object
    And I should see hashed email address hE2 tag in bright tag

  @artifact_navapp @release_16J @priority_medium @domain_selection @project_UFT @myaccount_5
  Scenario: Verify email address is encrypted in bright tag data dictionary object when signed in from home page
    Given I visit the web site as a guest user
    When I navigate to sign in page
    And I enter valid credentials and click Sign In button
    Then I should see hashed email address hE tag in bright tag
    And I should see hashed email address hE2 tag in bright tag

    # Notes:
    # hE: email address is hashed via SHA256
    # hE2: email address is double hashed via MD5 first and then via SHA256

  @artifact_navapp @release_16J @priority_medium @domain_selection @project_UFT @myaccount_5
  Scenario: Verify email address is encrypted in bright tag data dictionary object when signed in from DP Page
    Given I visit the web site as a guest user
    When I navigate to deals and promotions page
    And I navigate to sign in page
    And I enter valid credentials and click Sign In button
    Then I should see hashed email address hE tag in bright tag
    And I should see hashed email address hE2 tag in bright tag

      # Notes:
      # hE: email address is hashed via SHA256
      # hE2: email address is double hashed via MD5 first and then via SHA256

###########################Backlog scenarios for MyAccount MCOM##########################################

  #MCOM-69981
  @wip @please_automate_backlog @domain_marketing
  Scenario: Verify the cookies for an idle application

	Given I visit the web site as a guest user
	When I navigate a to sign in page using goto_sign_in link
	 And I sign in using existing username and password
	Then I should verify sign-in related cookies with expected values
	When I navigate to My Address book page
	And I leave application idle for 30 min
    And I navigate to the My Wallet page
	Then I should be navigated to SignIn page
	And I verify cookies displayed with expected values
	# Notes
	# Input
	# 1. Enter valid user credentials and login into the application.
	# 2. verify cookie.
	# 3. navigate to address book page and leave the application idle for 30 min and then after 30 min click on the wallet page	
	# Expected Results
	# 1. Verify that user should be able to login into the account.
	# 2.secure_token, last_access_token should be generated and the signed value should change to one
	# 3.a) user should be navigated to the signin page
	# 3.b) secure_token,last_access_token should be deleted from the browser cookies
	# 3.c) signed in value is changed to zero.

 ####################################################Manual Scenarios ###################################################

    @manual @upi_631_mcom @domain_marketing
  Scenario: Verify the behaviour when the product in the recently purchased list - not available product
    Given I visit the web site as a registered user
    And I place an order in Real time
    When I navigate to the My Account Page
    And I should see recently purchased Items with recommendations that are not available
    Then I should not be able to Click product thumbnail and description of the recently purchased items

  @manual @upi_631_mcom @domain_marketing
  Scenario: Verify the recently purchased list - available product
    Given I visit the web site as a registered user
    When I place 4 different orders
    And I navigate to the My Account Page
    Then I should verify only two recent purchased orders should display under order history section
    And I verify the My Account Pages are rendered properly
      | Order_number_link |
      | View_All          |


# MCOM-62885
  @manual @domain_marketing
  Scenario: Verify customer service EGC balance - Batch mode
    Given I visit the web site as a registered user
    When I navigate to the My Account Page
    Then I verify Gift Card Balance page for batch mode
    # Notes:
    # User can navigate to gift card balance page by clicking Gift Card Balance under Gift Cards in the left Navigation bar.
    # Breadcrumb should be My Account > Gifts & Gift Cards
    # The message "We're sorry. Our Electronic Gift Card inquiry services are currently unavailable. To obtain information on your Gift Card, please call 1-800-511-2752. " is displayed to the user.


  # MCOM-83823
  @use_regression @manual @domain_marketing
  Scenario:  Verify the gift card link on Wallet section
    Given I visit my account page as a signed user
    When I navigate to gift card balance page
    Then I verify the message displayed on gift card balance page
    # Notes:
    # Enter all required details in Gift Card Balance inquiry page and click view balance button
    # Then "We're sorry. Our Electronic Gift Card inquiry services are currently unavailable. To obtain information on your Gift Card, please call 1-800-511-2752." message should display

  @use_regression
  Scenario:  Verify the Wallet section on My Account page
    Given I visit my account page as a signed user
    Then I Verify the Wallet icon and header
    And Offers: section with caption text and 'Deals & Promotions' link
    And Credit card section with plus icon and 'Add A Payment Method' link
    And Wallet section footer links 'Wallet' and 'Gift Cards'

  @use_regression
  Scenario:  Verify the Plenti section on My Account page
    Given I visit my account page as a signed user
    Then I Verify the Plenti icon and header
    And I Verify Phone Number tab and section
    And I Verify Plenti Number tab and section
    And I Verify join our program text and Learn More link
