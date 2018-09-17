#Created by Gabriel Zanin on 05/25/17

Feature: Registry Bcom

  @dsv_desktop_sev2 @use_regression @domain_selection
  Scenario: As a customer, I want to verify cat splash pages appears without errors
    Given I visit the web site as a guest user
    When I navigate to registry home page
    Then I verify cat splash pages appears without errors

  @dsv_desktop_sev2 @use_regression @domain_selection
  Scenario: A guest user is navigated to the registry search results page when entering no names in the search boxes on Registry Home Page
    Given I visit the web site as a guest user
    And I navigate to registry home page
    When I submit "" and "" in Find form on registry home page
    Then I should be navigated to registry "Search Results Page" page
    And I should see the error message displayed:
    """
    We're sorry. The fields highlighted below must be completed before we can process your request.
    """

  @dsv_desktop_sev2 @use_regression @domain_selection
  Scenario: SearchResultsPage - Verify that All products that display in Registry search results are those that have the 'Add to Registry' button on the PDP in REGISTRY mode
    Given I visit the web site as a guest user in "registry" mode
    When I search for "plates"
    Then I should be in Search Landing page
    And I should be in Registry mode
    When I select a random product from search results page
    Then I should be redirected to registry PDP page
    And I should be in Registry mode
    And I should see Add to Registry button on PDP

  @dsv_desktop_sev2 @use_regression @domain_selection
  Scenario: As a user, I should navigate to Registry Create page by clicking Create button on the Registry Home Page
    Given I visit the web site as a guest user
    And I navigate to registry home page
    When I navigate to registry create page from registry homepage
    Then I should be navigated to registry "Capture Email Page" page

  @dsv_desktop_sev2 @use_regression @domain_selection
  Scenario: As a user, I should navigate to registry sign in page by without submitting data in Manage form in horizontal jsp on Registry Home Page
    Given I visit the web site as a guest user
    And I navigate to registry home page
    When I submit "" and "" in manage form on registry home page
    Then I should be navigated to registry "Sign In" page

  @dsv_desktop_sev2 @use_regression @domain_selection
  Scenario: As a user, I should navigate to registry sign in page by without submitting email and submitting password data in Manage form in horizontal jsp on Registry Home Page
    Given I visit the web site as a guest user
    And I navigate to registry home page
    When I submit "" and "password" in manage form on registry home page
    Then I should be navigated to registry "Sign In" page

  @dsv_desktop_sev2 @use_regression @domain_selection
  Scenario: As a user, I should navigate to registry sign in page by without submitting password and submitting email data in Manage form in horizontal jsp on Registry Home Page
    Given I visit the web site as a guest user
    And I navigate to registry home page
    When I submit "test01@gmail.com" and "" in manage form on registry home page
    Then I should be navigated to registry "Sign In" page

  @dsv_desktop_sev2 @use_regression @domain_selection
  Scenario: Splash Page, Sub Splash Page and Browse Page - Verify FOBs and two browse page in each FOB - Registry Mode
    Given I visit the web site as a guest user
    When I navigate to registry home page
    Then I verify the Registry FOBs in below table
      | DINING & ENTERTAINING | SERVEWARE |
      | KITCHEN               | ELECTRICS |
      | BED & BATH            | BATH      |
      | HOME DECOR            | LIGHTING  |
      | LUGGAGE               | LUGGAGE   |

  @dsv_desktop_sev2 @use_regression @domain_selection
  Scenario: SearchResultsPage - Verify junk word search in REGISTRY mode
    Given I visit the web site as a guest user
    When I navigate to registry home page
    And I navigate to random category splash page
    And I search for "sadafssdsddasdssSDSS"
    Then I should be redirected to the registry zeroResults page

  @dsv_desktop_sev2
  Scenario: Verify the basic attributes of the view registry page
    Given I visit the website as a registered user with registry
    When I navigate to bvr page
    Then I verify the basic attributes of the BVR page


  @dsv_desktop_sev2
   Scenario: Verify Currency Symbol - Registry
    Given I visit the web site as a guest user
    When I navigate to registry home page
    Then I search for the existing registry
    And I should see GVR items present in the page
    When I add item to bag from GVR page
    Then I verify all attributes of registry ATB overlay

  @dsv_desktop_sev2
  Scenario: As a user I should be able to search couples registry for dsv
    Given I visit the web site as a guest user
    When I navigate to registry home page
    Then I search for the existing registry
    And I verify the basic attributes of the GVR page
    And I should see GVR items present in the page

  @dsv_desktop_sev2
  Scenario: As a user I should be verify Checkout for registry products Without Sign in
    Given I visit the web site as a guest user
    When I navigate to registry home page
    Then I search for the existing registry
    When I add item to bag from GVR page
    And I verify all attributes of registry ATB overlay
    And I click on "Checkout" button on registry ATB overlay
    Then I should land on the bag page with the item present
    When I click on the continue shopping button from ATB page
    And I click checkout as a guest
    Then I should navigated to shipping & payment
    And I checkout until I reach the order review page as a "guest" user

  @dsv_desktop_sev2
  Scenario: As a user I should be able to verify Checkout for two separate registries & any random product Without Sign in
    Given I visit the web site as a guest user in "registry" mode
    And I search for the existing registry
    Then I add item to bag from GVR page
    And I search for the different existing registry
    And I add item to bag from GVR page
    When I add a "orderable and prod_available" product to my bag and checkout
    When I click on the continue shopping button from ATB page
    And I click checkout as a guest
    Then I should navigated to shipping & payment
    And I verify the shipping message for multiple registries:
    """
    Please note that your bag contains items from more than one registry. If you would like to ship to the registrants directly, please place a separate order specific to each registry.
    """
    And I checkout until I reach the order review page as a "guest" user


