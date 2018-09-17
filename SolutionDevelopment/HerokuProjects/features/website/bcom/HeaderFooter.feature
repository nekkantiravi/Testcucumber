#Author: Richard Mcleggon
#Date Created: 3/1/17
#Version 1: B-67594 (https://www14.v1host.com/Macyscom/story.mvc/Summary?oidToken=Story:2879466)

Feature: Heroku Header & Footer

  @bcom_heroku
  Scenario: As a customer I want verify search functionality in Heroku page
    Given I visit the heroku homepage
    When I navigate to search results page in "domestic" mode
    Then I verify that I am on browse page

  @bcom_heroku
  Scenario: As a customer I want to verify Bloomindale's logo navigates to Bloomingdale's Homepage
    Given I visit the heroku homepage
    Then I verify the bloomingdales logo available in "domestic" homepage
    When I click on the Bloomingdale's logo
    Then I verify that I am on the home page

  @bcom_heroku
  Scenario: Verify the FOB's displaying in Heroku page
    Given I visit the heroku homepage
    Then I verify the Fob's displayed
      |Designers            |
      |100%                 |
      |What's new           |
      |Women                |
      |Shoes                |
      |Handbags             |
      |Jewelry & Accessories|
      |Beauty               |
      |Men                  |
      |Kids                 |
      |Home                 |
      |Gifts                |
      |The Registry         |
      |Sale                 |

  @bcom_heroku
  Scenario Outline: Verify the Flyouts displaying in Heroku page
    Given I visit the heroku homepage
    When I hover over "<categories>"
    Then I should see "<categories>" flyout
    Examples:
      |categories           |
      |Designers            |
      |100%                 |
      |What's new           |
      |Women                |
      |Shoes                |
      |Handbags             |
      |Jewelry & Accessories|
      |Beauty               |
      |Men                  |
      |Kids                 |
      |Home                 |
      |Gifts                |
      |The Registry         |
      |Sale                 |

  @bcom_heroku
  Scenario: Verify the topnav section in Heroku page
    Given I visit the heroku homepage
    Then I should see topnav section
      |Stores & Events|
      |Country change |
      |My account     |
      |Wishlist       |
      |Brown bag      |

  @bcom_heroku
  Scenario Outline: Verify the topnav link navigation in Heroku page
    Given I visit the heroku homepage
    When I navigate to "<topnav's>" page from topnav section
    Then I should see the page navigates to "<topnav's>" page
    Examples:
      |topnav's       |
      |Stores & Events|
      |Country change |
      |My account     |
      |Wishlist       |
      |Brown bag      |

  @bcom_heroku
  Scenario: Verify the Brown Bag flyout displaying in Heroku page
    Then I visit the heroku homepage
    When I hover over "Brown Bag"
    Then I should see "Brown Bag" flyout


  @MANUAL @WIP
  Scenario: Verify the My Account flyout displaying in Heroku page
    Given I visit the heroku homepage as a registered user
    When I hover over "My Account"
    Then I should see "My Account" flyout

  @bcom_heroku
  Scenario Outline: Verify if a random subcategory link of a random FOB flyout goes to the correct page when clicked
    Given I visit the heroku homepage
    When I hover over a random FOB flyout of the "<section>" section
    Then I verify the flyout has more than one subcategory
    And I verify that all of the subcategories in the flyout have a correspondent url
    When I click on a random sucabtegory link
    Then I verify it navigated to the correct page
    Examples:
      |section|
      |regular|
      |custom |


    #By custom, we want to say that its contained in ("The Registry", "100%", "Designers", "Sale",   "Whats New")

    #----------------------------------Footer Section----------------------------------

  @bcom_heroku
  Scenario Outline: Verify the Customer Service footer links navigation in Heroku page
    Given I visit the heroku homepage
    When I Navigate to "<links>" footer links
    Then I verify navigated to "<links>" page
    Examples:
      |links              |
      |CUSTOMER SERVICE   |
      |Shipping Policy    |
      |Returns & Exchanges|
      |International      |

  @bcom_heroku
  Scenario Outline: Verify the My Account footer links navigation in Heroku page
    Given I visit the heroku homepage
    When I Navigate to "<links>" footer links
    Then I verify navigated to "<links>" page
    Examples:
      |links         |
      |MY ACCOUNT    |
      |Order Status  |
      |My Loyallist  |
      |My Profile    |

  @bcom_heroku
  Scenario Outline: Verify the My credit card footer links navigation in Heroku page
    Given I visit the heroku homepage
    When I Navigate to "<links>" footer links
    Then I verify navigated to "<links>" page
    Examples:
      |links               |
      |MY CREDIT CARD      |
      |Pay Bill            |
      |Cardholder Benefits |
      |Apply & Learn More  |

  @bcom_heroku
  Scenario Outline: Verify the Company footer links navigation in Heroku page
    Given I visit the heroku homepage
    When I Navigate to "<links>" footer links
    Then I verify navigated to "<links>" page
    Examples:
      |links    |
      |COMPANY  |
      |About Us |
      |b.cause  |
      |Careers  |

  @bcom_heroku
  Scenario Outline: Verify the Ways to Shop footer links navigation in Heroku page
    Given I visit the heroku homepage
    When I Navigate to "<links>" footer links
    Then I verify navigated to "<links>" page
    Examples:
      |links           |
      |WAYS TO SHOP    |
      |Online & Mobile |
      |Stores          |
      |Outlets         |

  @bcom_heroku
  Scenario: Verify that social icons click navigate to the correct page
    Given I visit the heroku homepage
    Then I verify the footer icon is present and has the correct url
      |Facebook   |
      |Twitter    |
      |Pinterest  |
      |Instagram  |
      |Mobile     |


  @bcom_heroku
  Scenario: Verify if the copyright is present and has the correct year
    Given I visit the heroku homepage
    Then The copyright looks like expected

