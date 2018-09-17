Feature: Marketing Regression Scenarios


  @use_regression @Marketing_CBT
  Scenario:  Verify the Wallet section on My Account page
    Given I visit my account page as a signed user
    Then I Verify the Wallet icon and header
    And Offers: section with caption text and 'Deals & Promotions' link
    And Credit card section with plus icon and 'Add A Payment Method' link
    And Wallet section footer links 'Wallet' and 'Gift Cards'

  @use_regression @Marketing_CBT
  Scenario:  Verify the Plenti section on My Account page
    Given I visit my account page as a signed user
    Then I Verify the Plenti icon and header
    And I Verify Phone Number tab and section
    And I Verify Plenti Number tab and section
    And I Verify join our program text and Learn More link


    ########################## My Preferences Section ####################

  @use_regression @Marketing_CBT
  Scenario: My Account - Rendering my preferences section
    Given I visit my account page as a signed user
    Then I verify the basic attributes of the my preferences section on My Account Page
    Then I verify the My Account Page "View Preferences" link rendered properly
#      | my preferences |

  @use_regression @Marketing_CBT
  Scenario: Verify Find a Store
    Given I navigated to the Our Stores Page as a guest user
    When I navigate to the "Store Locations & Hours" in our stores page
    Then I verify that the "Store Locations & Hours" our stores page has rendered
    And I verify the functionality in "Store Locations & Hours" page


  @use_regression @Marketing_CBT
  Scenario: Verify that user is able to add a valid online only offer promo code to wallet manually
    Given I visit the web site as a new registered user
    When I navigate to My Wallet page from My Account page
    And I added wallet eligible offer manually on wallet page
    Then I should see offer is added to wallet
    And I should see "Changes saved to your wallet." on top of the wallet page
    # Note: As part of above step we will check the offer is added in my offers section or not


  @use_regression @Marketing_CBT
  Scenario:Verify that find an offer link is displayed in shopping bag page for registered user when user have no offers in wallet
    Given I visit the web site as a registered user with no stored offers
    When I add an "available" product to my bag
    When I navigate to shopping bag page from add to bag page
    Then I should see Shopping Bag Page
    And I should see "Apply an offer to see the savings!" text in offer widget section for signed in user

  @use_regression @Marketing_CBT
  Scenario: Verify applied promotion details in shopping bag and checkout page
    Given I visit the web site as a registered user with "percent_off" promotion in wallet
    And I add "percent_off" product to my bag
    And I select options to trigger the promotion and add to bag
    And I navigate to shopping bag page from add to bag overlay
    And I verify the APPLY promotion details in shopping bag page
    And I click on APPLY button to apply the promotion
    Then I should see promotion applied details in wallet section
    Then I should see promotion applied details in the item section
    And I checkout until I reach the shipping & payment page as a "responsive_signed in" user
    Then I should see the applied promotion in checkout page

  @use_regression @Marketing_CBT
  Scenario: Verify eligible promotion can be applied using apply promotion field
    Given I visit the web site as a registered user with "percent_off" promotion in wallet
    And I add "percent_off" product to my bag
    And I select options to trigger the promotion and add to bag
    And I navigate to shopping bag page from add to bag overlay
    Then I should see APPLY promotion option
    Then I enter the "percent_off" promotion in promotion field and click on apply
    Then I should see promotion applied details in wallet section
    Then I should see promotion applied details in the item section
    And I checkout until I reach the shipping & payment page as a "responsive_signed in" user
    Then I should see the applied promotion in checkout page


  @use_regression @Marketing_CBT
  Scenario: Validate the display on preferences landing page
    Given I visit the web site as a registered user
    When I navigate to Preferences page directly from My Account page
    Then I should see the below three preferences cards display on landing page
      | .goto_categories      |
      | .goto_notifications   |
      | .goto_preferred_store |


  @use_regression @Marketing_CBT
  Scenario: Verify user is able to save the brands in Add State
    Given I visit the web site as a guest user
    When I click on "my account" link in the header
    And I navigate to create profile page
    And I create a new profile
    Then I should see user logged in to account successfully
    When I navigate to Preferences page directly from My Account page
    And I navigate to Categories page from Preferences Landing page
    And I should see "Brands" tab heading in the page
    When I click on 'Brands' tab option
    Then I should see the "Add Brands" Brands sub heading
    And I should see the "Shopping Preferences" page title
    And I should see the 'Save' button below the brands in disabled state
    And I should see the "What do you usually shop for? Tell us your favorite categories, brands & sizes" text on brands page
    And I should see the "Choose the brands you usually shop for:" Brands caption text
    And I should see the Brands dropdown field
    When I select any Brands tile
    And I click on 'Save' button
    Then I should see the Summary state of Brands

  @use_regression @Marketing_CBT
  Scenario: Plenti - Successful lookups from shopping bag page as a guest user
    Given I visit the web site as a guest user
 # When I add an "available and orderable" radical product to my bag
    When I add an "available" product to my bag
 # And I should be redirected to shopping bag page
    When I navigate to shopping bag page from add to bag page
    Then I perform all valid Plenti lookups on the "shopping_bag" page

  @use_regression @Marketing_CBT
  Scenario: As a fully enrolled USL user, Verify USL basic attributes on USL account summary page
    Given I visit the web site as a registered user
    When I add fully_enrolled_usl id on my account page
    And I navigate to USL account summary page
    Then I should see USL basic attributes on USL account summary page

  @use_regression @Marketing_CBT
  Scenario: Verify that signed in user is able to add all wallet eligible offers to wallet from deals and promotions page
    Given I visit the web site as a registered user
    When I visit the deals and promotions page
    And I add all wallet eligible offers from deals and promotions page
    Then I should see the added offers in my wallet page

  @use_regression @Marketing_CBT
  Scenario: Verify in bag more than one code are displaying as comma separated in info/Exclusions overlay
    Given I visit the web site as a registered user
    When I navigate to My Wallet page from My Account page
    Then I saved omnichannel offer having more than one promo code in wallet
    And I verify the promo code added to my wallet page