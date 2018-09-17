#### Author: Eligible Offers Team
#### Date Created: 06/24/2017
#### Date Signed Off:
###
###
Feature: Verify the sign in eligible offers on desktop bag page as a sign in user

  @Desktop_1
  Scenario: Verify the eligible offers section on bag page as sign in user
    Given I visit the web site as a registered|signed in user
    When I search for "4560460"
    And I select "4" quantity
    And I add product to bag
    Then I should see wallet icon
    And I should see "Eligible Offers" title
    And I should see following text for "guest offers" section
      | There might be better offers in your wallet. Sign in to make sure you get the best one! Sign In |
    And I should see the enter promo code text
      | Enter promo code. Limit of 1 offer per order. |
    And I should see promo code field with apply button
    And I should see following best offer text in eligible offer section
      | Here's your best available offer . |
    And I verify eligible offer details
    And I verify see all offers link


  @Desktop_2
  Scenario: Verify no eligible offers section as a sign in user
    Given I visit the web site as a registered|signed in user
    When I search for "4560460"
    And I select "1" quantity
    And I add product to bag
    Then I should see wallet icon
    And I should see "Eligible Offers" title
    And I should see following text for "no offers" section
      | Sorry, there are no offers that apply to the items in your bag. To see if you have other offers available, sign in. |
    And I should see the enter promo code text
      | Enter Promo code.Limit of 1 offer per order. |
    And I should see promo code field with apply button
    And I should not see eligible offers


  @Desktop_3
  Scenario: Verify offer applied section as a sign in user
    Given I visit the web site as a registered|signed in user
    When I search for "4560460"
    And I select "4" quantity
    And I add product to bag
    And I save the bag subtotal
    When I apply promotion
    Then I verify offer applied successfully
    And I verify applied offer details
    When I remove applied promotion
    Then I verify offer removed successfully
    And I verify eligible offer details

  @Desktop_4
  Scenario: Verify sign in link in eligible offers section in bag page
    Given I visit the web site as a registered|signed in user
    And I create a new profile
    And I sign out
    When I search for "4560460"
    And I select "4" quantity
    And I add product to bag
    When I sign in from eligible offers section
    And I sign in with existing profile
    Then I should see Shopping Bag Page
    And I should not see sign in link eligible offers section

  @Desktop_5
  Scenario: Verify the offers in see all offers overlay in bag page
    Given I visit the web site as a registered|signed in user
    When I search for "4560460"
    And I select "4" quantity
    And I add product to bag
    And I save the bag subtotal
    When I select see all offers
    Then I should see apply an offer overlay
    And I verify the promotion offers in the overlay
    When I apply promotion randomly in the overlay
    Then I verify offer applied successfully

  @Desktop_6
  Scenario: Verify the offer applied section when promo code applied manually
     Given I visit the web site as a registered|signed in user
    When I search for "4560460"
    And I select "4" quantity
    And I add product to bag
    And I save the bag subtotal
    When I enter "BUY2" promocode manually
    Then I verify offer applied successfully
    And I verify applied offer details
    When I remove applied promotion
    Then I verify offer removed successfully
    And I verify eligible offer details

  @Desktop_7
  Scenario: Verify the validation message when invalid promo code is applied
    Given I visit the web site as a registered|signed in user
    When I search for "4560460"
    And I select "4" quantity
    And I add product to bag
    When I enter "XYZ" promocode manually
    Then I should see following validation message
      | Promo Code: bloom.  We're sorry. We do not recognize the Promo Code you entered. Please try again. |

  @Desktop_8
  Scenario: Verify the validation message when valid promo code is applied but bag do not have qualified items
    Given I visit the web site as a registered|signed in user
    When I search for "1310"
    And I select "4" quantity
    And I add product to bag
    When I enter "BUY2" promocode manually
    Then I should see following validation message
      | Sorry, but the items in your Shopping Bag do not qualify for this promotion. If you need assistance, please call Customer Service at 1-800-BUY-MACY (1-800-289-6229). |

