Feature: Gamification - Olapic,Personalized Review Widget, stats tray and my reviews scenarios

  @scenario1_olapic_zerostate @use_regression @domain_selection
  Scenario: Verify the Zero State Olapic Widget on Radical PDP
    Given I visit the web site as a guest user
    When I search for "Michael Kors Handbags"
    #	Then I should be in Search Landing page
    And I select a random member product
    When I scroll down the PDP page to view the olapic widget
    And I click on shop and return
    Then I click on add photo button
    When I click on close button to close the photo uploader
    Then I click on view gallery to view the macyslove

  @scenario2_olapic_fullstate @use_regression @domain_selection
  Scenario: Verify the Full State Olapic Widget on Radical PDP
    Given I visit the web site as a guest user
    When I added a "available and olapic" product to my bag
    Then I scroll down the PDP page to view the olapic widget
    And I click on next and previou arrows on olapic to scroll the images
    When I click on one of the olapic image
    Then I click on product list to see the page redirection to radical PDP


#Sign out and sign back in is not working
  @scenario3_personalizedreviewwidget
  Scenario: Verify Personalized Review Widget on Search page
    Given I visit the web site as a registered user with checkout eligible address
    When I navigate to My Wallet page from My Account page
    And I click ADD a NEW CARD button
    And I add a credit card to My Wallet as default card on My Wallet page
    And I add a "available and orderable" product to my bag
    And I checkout until I reach the order confirmation page as a "signed in" user
    Then I navigate to home page
    When I sign out from my current profile
    And I sign in to my existing profile
    When I search for "michael kors coats"
    When I search for handbags
    Then I scroll down the Michael Kors Handbags search results
    When I see personalized review widget
    And I click on write a review link
    Then I see order history link on myaccount page

 

  @scenario4_statstray_withorderhistory
  Scenario: To verify the stats tray write a review as a registered user by placing an order
    Given I visit the web site as a registered user with checkout eligible address
    When I navigate to My Wallet page from My Account page
    And I click ADD a NEW CARD button
    And I add a credit card to My Wallet as default card on My Wallet page
    When I add a "available and orderable" product to my bag
    And I checkout until I reach the order confirmation page as an "signed in" user
    When I click on myaccount
    Then I see stats tray on myaccount page
    When I click on Write a review
    Then I see order history or Myreviews page

  @scenario5_pdp_review_yes @use_regression @domain_selection
  Scenario: To verify write a review from the PDP entry point as a recommended product
    Given I visit the web site as a registered user
    When I search for "jeans"
    #	  Then I should be in Search Landing page
    And I select a random member product
    When I click on write a review on PDP
    And I select star rating and filled review form
    And I select is recommended as yes
    And I click on review submit
    Then I see confirmation message for myreviews
    
   @scenario6_statstray_noorderhistory
  Scenario: To verify stats tray write a review on myaccount page without orderhistory
    Given I visit the web site as a registered user
    Then I see stats tray on myaccount page
    And I see statstray info about stars
    When I click on Write a review
    Then I see myreviews page

  @scenario7_pdp_review_no @use_regression @domain_selection
  Scenario: To verify write a review from the PDP entry point as not a recommended product
    Given I visit the web site as a registered user
    When I search for "jeans"
    #	  Then I should be in Search Landing page
    And I select a random member product
    When I click on write a review on PDP
    And I select star rating and filled review form
    And I select is recommended as no
    And I click on review submit
    Then I see confirmation message for myreviews
