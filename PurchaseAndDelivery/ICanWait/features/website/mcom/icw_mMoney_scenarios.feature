# Author: I Can Wait Project QE Team
# Date Created: 10/21/2016
Feature: ICW mMoney scenarios

  ############################################### ICW Earn scenarios, when ICW earn is active ###########################################

  @project_icw @domain_purchase_and_delivery @scenario1 @icw_mMoney
  Scenario: As a signedIn user, verify noHurry suggestion message is displayed on shipping method section with mMoney details when ICW mMoney Earn is active
    Given I visit the web site as a registered user in ICWMoney earn period
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as a "signedIn" user
    Then I should see nohurry suggestion message displayed in the shipping method section:
      | You qualify for FREE No Hurry shipping + $5 in Macy's Money. Click "change" to select offer or learn more. |

  @project_icw @domain_purchase_and_delivery @scenario2 @icw_mMoney
  Scenario: As a guest user, verify learn more information having mMoney details of noHurry shipping method when ICW mMoney Earn is active
    Given I visit the web site as a guest user in ICWMoney earn period
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    And I enter shipping address on guest shipping page
    And I should see below shipping options:
      | standard_shipping | true |
      | premium_shipping  | true |
      | express_shipping  | true |
      | nohurry_shipping  | true |
    And I select nohurry in shipping methods
    And I select learn more link in nohurry shipping method
    Then I should see following delivery ship note for nohurry shipping method on responsive checkout page:
      | HOW IT WORKS: For any single qualifying order shipped using No Hurry Shipping, we'll send you $5 Macy's Money Reward Card to your provided email address. EXCLUDES THE FOLLOWING PURCHASES: gift cards, services & fees, sales tax, macybed, all phone & live chat orders, lease depts, Espot, restaurants, international orders, online backorders, e-gifting orders. USE YOUR MACY'S MONEY REWARD CARD from effectiveDate - expirationDate. May not be: redeemed for cash, used to purchase Macy's gift cards or applied as payment to credit your credit card account. If a purchase used to accumulate Macy's Money is returned, Macy's reserves the right to void the Macy's Money Reward Card or reduce the corresponding value. |

  @project_icw @domain_purchase_and_delivery @scenario3 @icw_mMoney
  Scenario: As a signedIn user, verify learn more information having mMoney details of noHurry shipping method when ICW mMoney Earn is active
    Given I visit the web site as a registered user in ICWMoney earn period
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as an "responsive signed in" user
    And I select learn more link in nohurry shipping method
    And I should see following delivery ship note for nohurry shipping method on responsive checkout page:
      | HOW IT WORKS: For any single qualifying order shipped using No Hurry Shipping, we'll send you $5 Macy's Money Reward Card to your provided email address. EXCLUDES THE FOLLOWING PURCHASES: gift cards, services & fees, sales tax, macybed, all phone & live chat orders, lease depts, Espot, restaurants, international orders, online backorders, e-gifting orders. USE YOUR MACY'S MONEY REWARD CARD from effectiveDate - expirationDate. May not be: redeemed for cash, used to purchase Macy's gift cards or applied as payment to credit your credit card account. If a purchase used to accumulate Macy's Money is returned, Macy's reserves the right to void the Macy's Money Reward Card or reduce the corresponding value. |

  @project_icw @domain_purchase_and_delivery @scenario4 @icw_mMoney
  Scenario: As a guest user, Verify the estimate ICW mMoney amount on RC checkout page after selecting nohurry option when ICW mMoney Earn is active
    Given I visit the web site as a guest user in ICWMoney earn period
    When I add a "mbmoney and orderable and mbmoney_eligible" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    When I select nohurry in shipping methods
    Then I should see icw mMoney message on shipping method section:
      | Plus get $5 Macy's Money! |
    When I checkout until I reach the payment page as a "guest" user
    Then I should see estimated mMoney amount is displayed on RC order summary section

  @project_icw @domain_purchase_and_delivery @scenario5 @icw_mMoney
  Scenario: As a signedIn user, Verify the estimate ICW mMoney amount on RC checkout page after selecting nohurry option ICW mMoney Earn is active
    Given I visit the web site as a registered user in ICWMoney earn period
    When I add a "mbmoney and orderable and mbmoney_eligible" product to my bag
    And I checkout until I reach the shipping & payment page as a "responsive signed in" user
    When I select nohurry in shipping methods
    Then I should see icw mMoney message on shipping method section:
      | Plus get $5 Macy's Money! |
    And I should see estimated mMoney amount is displayed on RC order summary section

  @project_icw @domain_purchase_and_delivery @scenario6 @icw_mMoney
  Scenario Outline: Verify user should not be able to see the ICW mMoney estimated amount, when bag has EGC item and shipping method as noHurry
    Given I visit the web site as a <user_type> in ICWMoney earn period
    When I add an "<EGC_item>" item to bag with "<amount>" using add to bag service
    And I checkout until I reach the <page> page as a "<user>" user
    Then I should see below shipping options:
      | standard_shipping | true |
      | premium_shipping  | true |
      | nohurry_shipping  | true |
    When I select nohurry in shipping methods
    Then I should not see icw mMoney message on shipping method section:
      | Plus get $5 Macy's Money! |
    When I checkout until I reach the order review page as a "<user>" user
    Then I should not see estimated mMoney amount is displayed on RC order summary section
    Examples:
      | user_type       | page               | user      | EGC_item | amount |
      | guest user      | shipping           | guest     | 35352360 | 20     |
      | registered user | shipping & payment | signed in | 35352360 | 20     |



  ############################################### ICW Earn negative scenarios, when ICW burn (OR) mMoney earn is active ###########################################


  @project_icw @domain_purchase_and_delivery @scenario7 @icw_mMoney
  Scenario Outline: As a signedIn user, Verify noHurry suggestion message should not have ICW mMoney details, when mMoney is in earn period
    Given I visit the web site as a registered user in <campaign_type> <period> period
    When I add a "mbmoney and orderable and mbmoney_eligible" product to my bag
    And I checkout until I reach the shipping & payment page as a "responsive signed in" user
    Then I should see nohurry suggestion message displayed in the shipping method section:
      | You qualify for FREE No Hurry shipping. Click "change" to select offer or learn more. |
    And I should not see nohurry suggestion message displayed in the shipping method section:
      | You qualify for FREE No Hurry shipping + $5 in Macy's Money. Click "change" to select offer or learn more. |
    When I select nohurry in shipping methods
    Then I should not see icw mMoney message on shipping method section:
      | Plus get $5 Macy's Money! |

    Examples:
      | campaign_type | period |
      | mMoney        | earn   |
      | ICWMoney      | brun   |

  @project_icw @domain_purchase_and_delivery @scenario8 @icw_mMoney
  Scenario Outline: As a guest user, Verify noHurry suggestion message should not have ICW mMoney details, when ICW is in redeem period
    Given I visit the web site as a registered user in <campaign_type> <period> period
    When I add a "mbmoney and orderable and mbmoney_eligible" product to my bag
    And I checkout until I reach the shipping & payment page as a "responsive signed in" user
    And I select nohurry in shipping methods
    Then I should not see icw mMoney message on shipping method section:
      | Plus get $5 Macy's Money! |

    Examples:
      | campaign_type | period |
      | mMoney        | earn   |
      | ICWMoney      | brun   |

  @project_icw @domain_purchase_and_delivery @scenario9 @icw_mMoney
  Scenario Outline: Verify user able to earn the mMoney reward card, when user place an order during mMoney earn period
    Given I visit the web site as a <user_type> in mMoney earn period
    When I add a "mbmoney and orderable and mbmoney_eligible" product to my bag
    And I navigate to shopping bag page from add to bag page
    And I update the bag to meet the mbMoney earn threshold if the bag not met threshold
    Then I should see estimated mMoney earned section in the shopping bag page
    When I checkout until I reach the <page> page as a "<user>" user
    Then I should see the same estimated mMoney amount is displayed on RC page till place order

    Examples:
      | user_type       | page               | user      |
      | guest user      | shipping           | guest     |
      | registered user | shipping & payment | signed in |



