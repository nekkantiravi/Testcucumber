Feature: Marketing Regression Scenarios

    @bcom_international_home_page @Marketing_CBT
    Scenario: As a customer I want to verify whether the seasonal ad is present in iship mode and if it gets expanded when clicked
        When I visit the web site as a guest user in "iship" mode
        And I close the welcome mat if it's visible
        Then I should see the seasonal promo in the header
        When I click on the seasonal promo in the header
        Then the seasonal promo should be expanded

    @bcom_international_home_page @Marketing_CBT
    Scenario: As as customer I want to verify whether the country flag is present and whether the currency is correct in iship mode
        When I visit the web site as a guest user in "domestic" mode
        And I navigate to international context page
        And I change country to "Canada"
        And I close the welcome mat if it's visible
        Then I should see the correct iship currency for Canada in the header
        And I should see the correct country flag for Canada

    @scenario10 @domain_marketing @xbrowser @xbrowser_two @high @Marketing_CBT
    Scenario: Verify user should be able to enroll into loyalty program
        Given I visit the web site as a registered user
        When I navigate to the loyalty landing page as a "signed_in" user
        Then I should be able to enroll in to the loyalty program as a "signed_in" user

    @scenario11 @domain_marketing @xbrowser @xbrowser_two @data_dependency @Marketing_CBT
    Scenario: Verify user should be able to see wallet offers in shopping bag page
        Given I visit the web site as a registered user
        When I navigate to My Wallet page from My Account page
        Then I saved omnichannel offer having more than one promo code in wallet
        And I verify the promo code added to my wallet page

    @use_regression @use_bat @artifact_shopapp @priority_high @artifact_shopapp_2 @domain_marketing @loyalty_enroll @Marketing_CBT
    Scenario: Enroll in loyalty by creating a new profile
        Given I visit the web site as a guest user
        When I enroll into the Loyalty program by creating a new profile
        And I navigate to the loyalty account summary page
        Then I verify the account summary page for the loyallist

    @domain_Marketing @project_Preferences @bcom @use_regression @Marketing_CBT
    Scenario: Verify changing the email notification preference
        Given I visit the web site as a registered user
        When I navigate to Preferences page directly from My Account page
        And I select Enabled text for Email preference
        Then I should get navigated to a page having "Communication Preferences" page title
        When I click on 'Edit' option for Email
        Then I should see the Email section in edit state
        When I select 'Maybe later No thanks' radio option in edit state
        And I click on 'Save' button in edit state of Email Preference
        Then I should see "Disabled" status for the Email Preference

    @domain_Marketing @project_Preferences @bcom @use_regression @Marketing_CBT
    Scenario: Validate the display on preferences landing page
        Given I visit the web site as a registered user
        When I navigate to Preferences page directly from My Account page
        Then I should see "My Preferences" page title
        And I should see "Communication Preferences" card
        And I should see the "Preferred Store" card

    @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing @coremetrics @Marketing_CBT
    Scenario: Verify that the reward cards are being displayed in payment option section when there is reward cards in my bwallet
        Given I visit the web site as a registered user
        When I navigate to Loyallist Account Association page from My Account
        And I can associate my account by loyallist number using "rewards"
        When I navigate to My Wallet page from My Account page
        When I add an "available" product to my bag
        And I ensure to be on "shopping bag" page
# updated the step name to avoid duplicate step issue
        And I checkout to reach the "shipping & payment" page as a "signed in" user
        Then I should see reward cards in payment option section
    # Note: We will verify reward cards are being displayed in payment option section and they should be unchecked by default
    # Note: In the above step we will verify "Reward Card title" , "Expiration date", "EXPIRING SOON" text, Card balance

    @domain_marketing @use_bat @journey @mustpass @sv_mcom_refactored @sv_bcom_refactored @s4a_stable @ifs @Marketing_CBT
    Scenario: A registered user can add a store credit card to their wallet.
        Given I visit the web site as a guest user
        When I create a new profile
        And I add a store credit card to my wallet
        Then my credit card information should be saved successfully

          #Test Case ID: BLCOM-56492
  #Commenting the tags below because BCOM have a new Store Events interface so this scenario is not applicable anymore
    @use_regression @use_launch_call @domain_marketing @akamai_waf @Marketing_CBT
    Scenario: Verify Stores & Events locator
        Given I visit the web site as a guest user
        When I select "stores" link in home page
        Then I verify stores are listed in distance order of search location
        When I navigate to a Store Details page
        Then I verify the store details page
        When I navigate to Store Events page from Store Details
        Then I verify the store events page
    # Notes
    # Select a store from the drop down and click on go
    # Verify in store details page: Store hours, store details and store events are displayed for the selected store
    # Select a store event to navigate to store events page
    # Verify user can select event by type , by date on store events page

    @domain_Marketing @project_Preferences @bcom @Marketing_CBT
    Scenario: Validate the display on Preferred Store card
        Given I visit the web site as a registered user
        When I navigate to Preferences page directly from My Account page
        Then I should see "My Preferences" page title
        And I should see the "Preferred Store" card
        And I should see the 'Add a Store' option

    @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_shopapp @use_iteration @use_bat @Marketing_CBT
    Scenario: Dashboard My Account Links - My account, order status, Pay My Bill
        Given I visit the web site as a registered user
        Then I should see my first name on dashboard
        And I should see "Sign out" link on dashboard
        And I should see "My Account" link on dashboard
        And I should see "Order Status & History" link on dashboard
        And I should see "Pay My Bill" link on dashboard
        When I select "My Account" link from dashboard
        Then I verify that the "My Account - Bloomingdale's" page is rendered
        When I select "Order Status & History" link from dashboard
        Then I verify that the "Check My Order Status & Order History - Bloomingdale's" page is rendered
        When I select "Pay My Bill" link from dashboard
        Then I verify that the "Credit Gateway" page is rendered

    @use_regression @priority_high @artifact_navapp @domain_marketing @use_bat @iq_bat @saucelab @Marketing_CBT
    Scenario:The home page should be displayed without errors and with all relevant information present.
        Given I visit the web site as a guest user
        Then I verify basic attributes of the home page

    @dsv_desktop_sev2 @domain_marketing @use_regression @Marketing_CBT
    Scenario: As a guest user, I should see the Header banner section on registry home page
        Given I visit the web site as a guest user
        When I navigate to registry home page
        Then I should see header banner with all the elements on Home Page



