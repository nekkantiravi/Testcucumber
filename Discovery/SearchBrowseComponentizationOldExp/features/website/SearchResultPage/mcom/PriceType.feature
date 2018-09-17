# Author: Discovery QE
# Date Created: 01/20/2017

Feature: Verifying Product Thumbnail details for each PriceType product in  Search Results Page

  # ******************************* PRICE TYPE ID = 0 PRICE TYPE = Ticket  **********************************************************
  # *******************************          WITH COLORWAY                 **********************************************************

  @scenario_PriceType0_MemberProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=0) in ALL modes for MEMBER product with Colorway Pricing
    Given I am on SearchResultsPage for "lichenberg curtain 60 x 63 panel" in <Site_Mode> mode
    When I select a "member" product "WithColorWay" pricing of PriceTypeID = 0 and PriceType = "Ticket"
    Then I verify that Product Thumbnail PriceTypeID = 0 and PriceType = "Ticket" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType0_MasterProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=0) in ALL modes for MASTER product with Colorway Pricing
    Given I am on SearchResultsPage for "Joy Lace Curtain Panel" in <Site_Mode> mode
    When I select a "master" product "WithColorWay" pricing of PriceTypeID = 0 and PriceType = "Ticket"
    Then I verify that Product Thumbnail PriceTypeID = 0 and PriceType = "Ticket" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType0_StandardProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=0) in ALL modes for STANDARD product with Colorway Pricing
    Given I am on SearchResultsPage for "men's faux suede slippers" in <Site_Mode> mode
    When I select a "standard" product "WithColorWay" pricing of PriceTypeID = 0 and PriceType = "Ticket"
    Then I verify that Product Thumbnail PriceTypeID = 0 and PriceType = "Ticket" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # *******************************          WITHOUT COLORWAY                 **********************************************************

  @scenario_PriceType0_MemberProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=0) in ALL modes for MEMBER product without Colorway Pricing
    Given I am on SearchResultsPage for "softline layne 55 x 84 panel" in <Site_Mode> mode
    When I select a "member" product "WithoutColorWay" pricing of PriceTypeID = 0 and PriceType = "Ticket"
    Then I verify that Product Thumbnail PriceTypeID = 0 and PriceType = "Ticket" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType0_MasterProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=0) in ALL modes for MASTER product without Colorway Pricing
    Given I am on SearchResultsPage for "softline layne grommet" in <Site_Mode> mode
    When I select a "master" product "WithoutColorWay" pricing of PriceTypeID = 0 and PriceType = "Ticket"
    Then I verify that Product Thumbnail PriceTypeID = 0 and PriceType = "Ticket" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |


  @scenario_PriceType0_StandardProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=0) in ALL modes for STANDARD product without Colorway Pricing
    Given I am on SearchResultsPage for "Men's Jersey Clog Slippers" in <Site_Mode> mode
    When I select a "standard" product "WithoutColorWay" pricing of PriceTypeID = 0 and PriceType = "Ticket"
    Then I verify that Product Thumbnail PriceTypeID = 0 and PriceType = "Ticket" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # ******************************* PRICE TYPE ID = 13 PRICE TYPE = EveryDay Value  **********************************************************
 # *******************************               WITH COLORWAY                     **********************************************************

  @scenario_PriceType13_MemberProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=13) in ALL modes for MEMBER product with Colorway Pricing
    Given I am on SearchResultsPage for "softline alwar 55 x 84 panel" in <Site_Mode> mode
    When I select a "member" product "WithColorWay" pricing of PriceTypeID = 13 and PriceType = "EveryDay Value"
    Then I verify that Product Thumbnail PriceTypeID = 13 and PriceType = "EveryDayValue" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType13_MasterProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=13) in ALL modes for MASTER product with Colorway Pricing
    Given I am on SearchResultsPage for "softline alwar embroidered " in <Site_Mode> mode
    When I select a "master" product "WithColorWay" pricing of PriceTypeID = 13 and PriceType = "EveryDay Value"
    Then I verify that Product Thumbnail PriceTypeID = 13 and PriceType = "EveryDayValue" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType13_StandardProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=13) in ALL modes for STANDARD product with Colorway Pricing
    Given I am on SearchResultsPage for "men's suede moccasin slippers" in <Site_Mode> mode
    When I select a "standard" product "WithColorWay" pricing of PriceTypeID = 13 and PriceType = "EveryDay Value"
    Then I verify that Product Thumbnail PriceTypeID = 13 and PriceType = "EveryDay Value" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

   # *******************************          WITHOUT COLORWAY                 **********************************************************

  @scenario_PriceType13_MemberProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=13) in ALL modes for MEMBER product without Colorway Pricing
    Given I am on SearchResultsPage for "softline hatters 55 x 84 panel" in <Site_Mode> mode
    When I select a "member" product "WithoutColorWay" pricing of PriceTypeID = 13 and PriceType = "EveryDay Value"
    Then I verify that Product Thumbnail PriceTypeID = 13 and PriceType = "EveryDayValue" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType13_MasterProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=13) in ALL modes for MASTER product without Colorway Pricing
    Given I am on SearchResultsPage for "softline hatters" in <Site_Mode> mode
    When I select a "master" product "WithoutColorWay" pricing of PriceTypeID = 13 and PriceType = "EveryDay Value"
    Then I verify that Product Thumbnail PriceTypeID = 13 and PriceType = "EveryDayValue" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType13_StandardProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=13) in ALL modes for STANDARD product without Colorway Pricing
    Given I am on SearchResultsPage for "suede moccasin slippers with faux-fur lining" in <Site_Mode> mode
    When I select a "standard" product "WithoutColorWay" pricing of PriceTypeID = 13 and PriceType = "EveryDay Value"
    Then I verify that Product Thumbnail PriceTypeID = 13 and PriceType = "EveryDay Value" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # ******************************* PRICE TYPE ID = 11    PRICE TYPE = Markup       **********************************************************
 # *******************************               WITH COLORWAY                     **********************************************************


  @scenario_PriceType11_MemberProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=11) in ALL modes for MEMBER product with Colorway Pricing
    Given I am on SearchResultsPage for "anaheim 50 x 84 grommet curtain panel" in <Site_Mode> mode
    When I select a "member" product "WithColorWay" pricing of PriceTypeID = 11 and PriceType = "Markup"
    Then I verify that Product Thumbnail PriceTypeID = 11 and PriceType = "Markup" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType11_MasterProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=11) in ALL modes for MASTER product with Colorway Pricing
    Given I am on SearchResultsPage for "miller curtains anaheim grommet curtain panel collection" in <Site_Mode> mode
    When I select a "master" product "WithColorWay" pricing of PriceTypeID = 11 and PriceType = "Markup"
    Then I verify that Product Thumbnail PriceTypeID = 11 and PriceType = "Markup" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType11_StandardProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=11) in ALL modes for STANDARD product with Colorway Pricing
    Given I am on SearchResultsPage for "men's faux suede bootie slippers" in <Site_Mode> mode
    When I select a "standard" product "WithColorWay" pricing of PriceTypeID = 11 and PriceType = "Markup"
    Then I verify that Product Thumbnail PriceTypeID = 11 and PriceType = "Markup" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # *******************************          WITHOUT COLORWAY                 **********************************************************

  @scenario_PriceType11_MemberProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=11) in ALL modes for MEMBER product without Colorway Pricing
    Given I am on SearchResultsPage for "Shiro Ikat Crushed Sheer 50 x 63" in <Site_Mode> mode
    When I select a "member" product "WithoutColorWay" pricing of PriceTypeID = 11 and PriceType = "Markup"
    Then I verify that Product Thumbnail PriceTypeID = 11 and PriceType = "Markup" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType11_MasterProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=11) in ALL modes for MASTER product without Colorway Pricing
    Given I am on SearchResultsPage for "lichtenberg shiro ikat crushed sheer" in <Site_Mode> mode
    When I select a "master" product "WithoutColorWay" pricing of PriceTypeID = 11 and PriceType = "Markup"
    Then I verify that Product Thumbnail PriceTypeID = 11 and PriceType = "Markup" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType11_StandardProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=11) in ALL modes for STANDARD product without Colorway Pricing
    Given I am on SearchResultsPage for "men's herringbone bomber moccasin slippers" in <Site_Mode> mode
    When I select a "standard" product "WithoutColorWay" pricing of PriceTypeID = 11 and PriceType = "Markup"
    Then I verify that Product Thumbnail PriceTypeID = 11 and PriceType = "Markup" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

# *******************************   PRICE TYPE ID = 9    PRICE TYPE = Revalue       **********************************************************
 # *******************************               WITH COLORWAY                      **********************************************************

  @scenario_PriceType9_MemberProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=9) in ALL modes for MEMBER product with Colorway Pricing
    Given I am on SearchResultsPage for "lichtenberg whitley grommet curtain 48 x 63 panel" in <Site_Mode> mode
    When I select a "member" product "WithColorWay" pricing of PriceTypeID = 9 and PriceType = "Revalue"
    Then I verify that Product Thumbnail PriceTypeID = 9 and PriceType = "Revalue" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType9_MasterProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=9) in ALL modes for MASTER product with Colorway Pricing
    Given I am on SearchResultsPage for "lichtenberg whitley grommet curtain panel collection" in <Site_Mode> mode
    When I select a "master" product "WithColorWay" pricing of PriceTypeID = 9 and PriceType = "Revalue"
    Then I verify that Product Thumbnail PriceTypeID = 9 and PriceType = "Revalue" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType9_StandardProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=9) in ALL modes for STANDARD product with Colorway Pricing
    Given I am on SearchResultsPage for "o neil'l men's surf turkey original slippers" in <Site_Mode> mode
    When I select a "standard" product "WithColorWay" pricing of PriceTypeID = 9 and PriceType = "Revalue"
    Then I verify that Product Thumbnail PriceTypeID = 9 and PriceType = "Revalue" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  # *******************************          WITHOUT COLORWAY                 **********************************************************

  @scenario_PriceType9_MemberProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=9) in ALL modes for MEMBER product without Colorway Pricing
    Given I am on SearchResultsPage for "teigen 50 x 63 curtain panel" in <Site_Mode> mode
    When I select a "member" product "WithoutColorWay" pricing of PriceTypeID = 9 and PriceType = "Revalue"
    Then I verify that Product Thumbnail PriceTypeID = 9 and PriceType = "Revalue" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType9_MasterProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=9) in ALL modes for MASTER product without Colorway Pricing
    Given I am on SearchResultsPage for "lichtenberg teigen curtain panel collection" in <Site_Mode> mode
    When I select a "master" product "WithoutColorWay" pricing of PriceTypeID = 9 and PriceType = "Revalue"
    Then I verify that Product Thumbnail PriceTypeID = 9 and PriceType = "Revalue" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType9_StandardProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=9) in ALL modes for STANDARD product without Colorway Pricing
    Given I am on SearchResultsPage for "men's buffalo plaid moccasin slippers" in <Site_Mode> mode
    When I select a "standard" product "WithoutColorWay" pricing of PriceTypeID = 9 and PriceType = "Revalue"
    Then I verify that Product Thumbnail PriceTypeID = 9 and PriceType = "Revalue" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

# ******************************* PRICE TYPE ID = 1 PRICE TYPE = Promo Percent Off  **********************************************************
# *******************************               WITH COLORWAY                       **********************************************************

  @scenario_PriceType1_MemberProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=1) in ALL modes for MEMBER product with Colorway Pricing
    Given I am on SearchResultsPage for "Anne 57 x 13 Valance" in <Site_Mode> mode
    When I select a "member" product "WithColorWay" pricing of PriceTypeID = 1 and PriceType = "Promo Percent Off"
    Then I verify that Product Thumbnail PriceTypeID = 1 and PriceType = "Promo Percent Off" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType1_MasterProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=1) in ALL modes for MASTER product with Colorway Pricing
    Given I am on SearchResultsPage for "saturday knight anne window treatment collection" in <Site_Mode> mode
    When I select a "master" product "WithColorWay" pricing of PriceTypeID = 1 and PriceType = "Promo Percent Off"
    Then I verify that Product Thumbnail PriceTypeID = 1 and PriceType = "Promo Percent Off" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType1_StandardProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=1) in ALL modes for STANDARD product with Colorway Pricing
    Given I am on SearchResultsPage for "mid casual sneakers from finish line" in <Site_Mode> mode
    When I select a "standard" product "WithColorWay" pricing of PriceTypeID = 1 and PriceType = "Promo Percent Off"
    Then I verify that Product Thumbnail PriceTypeID = 1 and PriceType = "Promo Percent Off" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # *******************************          WITHOUT COLORWAY                 **********************************************************

  @scenario_PriceType1_MemberProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=1) in ALL modes for MEMBER product without Colorway Pricing
    Given I am on SearchResultsPage for "silvia crushed sheer 50 x 63 panel" in <Site_Mode> mode
    When I select a "member" product "WithoutColorWay" pricing of PriceTypeID = 1 and PriceType = "Promo Percent Off"
    Then I verify that Product Thumbnail PriceTypeID = 1 and PriceType = "Promo Percent Off" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType1_MasterProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=1) in ALL modes for MASTER product without Colorway Pricing
    Given I am on SearchResultsPage for "silvia crushed sheer curtain collection" in <Site_Mode> mode
    When I select a "master" product "WithoutColorWay" pricing of PriceTypeID = 1 and PriceType = "Promo Percent Off"
    Then I verify that Product Thumbnail PriceTypeID = 1 and PriceType = "Promo Percent Off" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType1_StandardProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=1) in ALL modes for STANDARD product without Colorway Pricing
    Given I am on SearchResultsPage for "men's low running sneakers from finish line" in <Site_Mode> mode
    When I select a "standard" product "WithoutColorWay" pricing of PriceTypeID = 1 and PriceType = "Promo Percent Off"
    Then I verify that Product Thumbnail PriceTypeID = 1 and PriceType = "Promo Percent Off" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # ******************************* PRICE TYPE ID = 3 PRICE TYPE = Promo Sale Price  **********************************************************
 # *******************************               WITH COLORWAY                       **********************************************************

  @scenario_PriceType3_MemberProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=3) in ALL modes for MEMBER product with Colorway Pricing
    Given I am on SearchResultsPage for "peri faux silk stripe 42 x 84 panel" in <Site_Mode> mode
    When I select a "member" product "WithColorWay" pricing of PriceTypeID = 3 and PriceType = "Promo Sale Price"
    Then I verify that Product Thumbnail PriceTypeID = 3 and PriceType = "Promo Sale Price" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType3_MasterProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=3) in ALL modes for MASTER product with Colorway Pricing
    Given I am on SearchResultsPage for "chf peri faux silk stripe collection" in <Site_Mode> mode
    When I select a "master" product "WithColorWay" pricing of PriceTypeID = 3 and PriceType = "Promo Sale Price"
    Then I verify that Product Thumbnail PriceTypeID = 3 and PriceType = "Promo Sale Price" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType3_StandardProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=3) in ALL modes for STANDARD product with Colorway Pricing
    Given I am on SearchResultsPage for "men's low running sneakers from finish line" in <Site_Mode> mode
    When I select a "standard" product "WithColorWay" pricing of PriceTypeID = 3 and PriceType = "Promo Sale Price"
    Then I verify that Product Thumbnail PriceTypeID = 3 and PriceType = "Promo Sale Price" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # *******************************          WITHOUT COLORWAY                 **********************************************************

  @scenario_PriceType3_MemberProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=3) in ALL modes for MEMBER product without Colorway Pricing
    Given I am on SearchResultsPage for "rod desyne twist single 28-48 rod" in <Site_Mode> mode
    When I select a "member" product "WithoutColorWay" pricing of PriceTypeID = 3 and PriceType = "Promo Sale Price"
    Then I verify that Product Thumbnail PriceTypeID = 3 and PriceType = "Promo Sale Price" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType3_MasterProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=3) in ALL modes for MASTER product without Colorway Pricing
    Given I am on SearchResultsPage for "rod desyne rod window hardware collection" in <Site_Mode> mode
    When I select a "master" product "WithoutColorWay" pricing of PriceTypeID = 3 and PriceType = "Promo Sale Price"
    Then I verify that Product Thumbnail PriceTypeID = 3 and PriceType = "Promo Sale Price" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType3_StandardProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=3) in ALL modes for STANDARD product without Colorway Pricing
    Given I am on SearchResultsPage for "men's low running sneakers from finish line" in <Site_Mode> mode
    When I select a "standard" product "WithoutColorWay" pricing of PriceTypeID = 3 and PriceType = "Promo Sale Price"
    Then I verify that Product Thumbnail PriceTypeID = 3 and PriceType = "Promo Sale Price" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |


 # ******************************* PRICE TYPE ID = 4 PRICE TYPE = Promo Event Price  **********************************************************
 # *******************************               WITH COLORWAY                       **********************************************************

  @scenario_PriceType4A_MemberProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=4A) in ALL modes for MEMBER product with Colorway Pricing
    Given I am on SearchResultsPage for "Rod Desyne Ivy 120-170 Double Curtain Rod" in <Site_Mode> mode
    When I select a "member" product "WithColorWay" pricing of PriceTypeID = 4 and PriceType = "Promo Event Price"
    Then I verify that Product Thumbnail PriceTypeID = 4 and PriceType = "Promo Event Price" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType4A_MasterProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=4A) in ALL modes for MASTER product with Colorway Pricing
    Given I am on SearchResultsPage for "rod desyne ivy double curtain rod collection" in <Site_Mode> mode
    When I select a "master" product "WithColorWay" pricing of PriceTypeID = 4 and PriceType = "Promo Event Price"
    Then I verify that Product Thumbnail PriceTypeID = 4 and PriceType = "Promo Event Price" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType4A_StandardProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=4A) in ALL modes for STANDARD product with Colorway Pricing
    Given I am on SearchResultsPage for "gel-lyte v casual sneakers from finish line" in <Site_Mode> mode
    When I select a "standard" product "WithColorWay" pricing of PriceTypeID = 4 and PriceType = "Promo Event Price"
    Then I verify that Product Thumbnail PriceTypeID = 4 and PriceType = "Promo Event Price" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # *******************************          WITHOUT COLORWAY                 **********************************************************

  @scenario_PriceType4A_MemberProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=4A) in ALL modes for MEMBER product without Colorway Pricing
    Given I am on SearchResultsPage for "rod desyne curl single 28-48 rod" in <Site_Mode> mode
    When I select a "member" product "WithoutColorWay" pricing of PriceTypeID = 4 and PriceType = "Promo Event Price"
    Then I verify that Product Thumbnail PriceTypeID = 4 and PriceType = "Promo Event Price" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType4A_MasterProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=4A) in ALL modes for MASTER product without Colorway Pricing
    Given I am on SearchResultsPage for "rod desyne curl single rod window hardware collection" in <Site_Mode> mode
    When I select a "master" product "WithoutColorWay" pricing of PriceTypeID = 4 and PriceType = "Promo Event Price"
    Then I verify that Product Thumbnail PriceTypeID = 4 and PriceType = "Promo Event Price" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType4A_StandardProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=4A) in ALL modes for STANDARD product without Colorway Pricing
    Given I am on SearchResultsPage for "k-swiss casual sneakers from finish line" in <Site_Mode> mode
    When I select a "standard" product "WithoutColorWay" pricing of PriceTypeID = 4 and PriceType = "Promo Event Price"
    Then I verify that Product Thumbnail PriceTypeID = 4 and PriceType = "Promo Event Price" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

# ******************************* PRICE TYPE ID = 5 PRICE TYPE = Promo Event Tiered  **********************************************************
 # *******************************               WITH COLORWAY                       **********************************************************

  @scenario_PriceType5A_MemberProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=5A) in ALL modes for MEMBER product with Colorway Pricing
    Given I am on SearchResultsPage for "rod desyne acorn 28-48 double curtain rod" in <Site_Mode> mode
    When I select a "member" product "WithColorWay" pricing of PriceTypeID = 5 and PriceType = "Promo Event Tiered"
    Then I verify that Product Thumbnail PriceTypeID = 5 and PriceType = "Promo Event Tiered" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType5A_MasterProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=5A) in ALL modes for MASTER product with Colorway Pricing
    Given I am on SearchResultsPage for "rod desyne acorn double curtain rod collection" in <Site_Mode> mode
    When I select a "master" product "WithColorWay" pricing of PriceTypeID = 5 and PriceType = "Promo Event Tiered"
    Then I verify that Product Thumbnail PriceTypeID = 5 and PriceType = "Promo Event Tiered" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType5A_StandardProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=5A) in ALL modes for STANDARD product with Colorway Pricing
    Given I am on SearchResultsPage for "gel-lyte mt boots from finish line" in <Site_Mode> mode
    When I select a "standard" product "WithColorWay" pricing of PriceTypeID = 5 and PriceType = "Promo Event Tiered"
    Then I verify that Product Thumbnail PriceTypeID = 5 and PriceType = "Promo Event Tiered" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # *******************************          WITHOUT COLORWAY                 **********************************************************

  @scenario_PriceType5A_MemberProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=5A) in ALL modes for MEMBER product without Colorway Pricing
    Given I am on SearchResultsPage for "rod desyne twist 28-48 double curtain rod" in <Site_Mode> mode
    When I select a "member" product "WithoutColorWay" pricing of PriceTypeID = 5 and PriceType = "Promo Event Tiered"
    Then I verify that Product Thumbnail PriceTypeID = 5 and PriceType = "Promo Event Tiered" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType5A_MasterProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=5A) in ALL modes for MASTER product without Colorway Pricing
    Given I am on SearchResultsPage for "rod desyne twist double curtain rod collection" in <Site_Mode> mode
    When I select a "master" product "WithoutColorWay" pricing of PriceTypeID = 5 and PriceType = "Promo Event Tiered"
    Then I verify that Product Thumbnail PriceTypeID = 5 and PriceType = "Promo Event Tiered" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType5A_StandardProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=5A) in ALL modes for STANDARD product without Colorway Pricing
    Given I am on SearchResultsPage for "gel-lyte mt boots from finish line" in <Site_Mode> mode
    When I select a "standard" product "WithoutColorWay" pricing of PriceTypeID = 5 and PriceType = "Promo Event Tiered"
    Then I verify that Product Thumbnail PriceTypeID = 5 and PriceType = "Promo Event Tiered" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # ******************************* PRICE TYPE ID = 4 PRICE TYPE = Promo Event2 Price  **********************************************************
 # *******************************               WITH COLORWAY                        **********************************************************

  #@scenario_PriceType4B_MemberProduct_withcolorway
  @ignore
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=4B) in ALL modes for MEMBER product with Colorway Pricing
    Given I am on SearchResultsPage for "NEED INFO " in <Site_Mode> mode
    When I select a "member" product "WithColorWay" pricing of PriceTypeID = 4 and PriceType = "Promo Event2 Price"
    Then I verify that Product Thumbnail PriceTypeID = 4 and PriceType = "Promo Event2 PriceType4" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  #@scenario_PriceType4B_MasterProduct_withcolorway
  @ignore
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=4B) in ALL modes for MASTER product with Colorway Pricing
    Given I am on SearchResultsPage for "NEED INFO" in <Site_Mode> mode
    When I select a "master" product "WithColorWay" pricing of PriceTypeID = 4 and PriceType = "Promo Event2 Price"
    Then I verify that Product Thumbnail PriceTypeID = 4 and PriceType = "Promo Event2 PriceType4" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  #@scenario_PriceType4B_StandardProduct_withcolorway
  @ignore
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=4B) in ALL modes for STANDARD product with Colorway Pricing
    Given I am on SearchResultsPage for "NEED INFO" in <Site_Mode> mode
    When I select a "standard" product "WithColorWay" pricing of PriceTypeID = 4 and PriceType = "Promo Event2 Price"
    Then I verify that Product Thumbnail PriceTypeID = 4 and PriceType = "Promo Event2 PriceType4" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # *******************************          WITHOUT COLORWAY                 **********************************************************

  #@scenario_PriceType4B_MemberProduct_withoutcolorway
  @ignore
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=4B) in ALL modes for MEMBER product without Colorway Pricing
    Given I am on SearchResultsPage for "NEED INFO" in <Site_Mode> mode
    When I select a "member" product "WithoutColorWay" pricing of PriceTypeID = 4 and PriceType = "Promo Event2 Price"
    Then I verify that Product Thumbnail PriceTypeID = 4 and PriceType = "Promo Event2 PriceType4" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  #@scenario_PriceType4B_MasterProduct_withoutcolorway
  @ignore
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=4B) in ALL modes for MASTER product without Colorway Pricing
    Given I am on SearchResultsPage for "NEED INFO" in <Site_Mode> mode
    When I select a "master" product "WithoutColorWay" pricing of PriceTypeID = 4 and PriceType = "Promo Event2 Price"
    Then I verify that Product Thumbnail PriceTypeID = 4 and PriceType = "Promo Event2 Price" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  #@scenario_PriceType4B_StandardProduct_withoutcolorway
  @ignore
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=4B) in ALL modes for STANDARD product without Colorway Pricing
    Given I am on SearchResultsPage for "NEED INFO" in <Site_Mode> mode
    When I select a "standard" product "WithoutColorWay" pricing of PriceTypeID = 4 and PriceType = "Promo Event2 Price"
    Then I verify that Product Thumbnail PriceTypeID = 4 and PriceType = "Promo Event2 PriceType4" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

# ******************************* PRICE TYPE ID = 5 PRICE TYPE = Promo Event2 Price Tiered  **********************************************************
 # *******************************               WITH COLORWAY                       **********************************************************

  #@scenario_PriceType5B_MemberProduct_withcolorway
  @ignore
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=5B) in ALL modes for MEMBER product with Colorway Pricing
    Given I am on SearchResultsPage for "NEED INFO" in <Site_Mode> mode
    When I select a "member" product "WithColorWay" pricing of PriceTypeID = 5 and PriceType = "Promo Event2 Price Tiered"
    Then I verify that Product Thumbnail PriceTypeID = 5 and PriceType = "Promo Event2 Price Tiered" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  #@scenario_PriceType5B_MasterProduct_withcolorway
  @ignore
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=5B) in ALL modes for MASTER product with Colorway Pricing
    Given I am on SearchResultsPage for "NEED INFO" in <Site_Mode> mode
    When I select a "master" product "WithColorWay" pricing of PriceTypeID = 5 and PriceType = "Promo Event2 Price Tiered"
    Then I verify that Product Thumbnail PriceTypeID = 5 and PriceType = "Promo Event2 Price Tiered" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  #@scenario_PriceType5B_StandardProduct_withcolorway
  @ignore
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=5B) in ALL modes for STANDARD product with Colorway Pricing
    Given I am on SearchResultsPage for "NEED INFO" in <Site_Mode> mode
    When I select a "standard" product "WithColorWay" pricing of PriceTypeID = 5 and PriceType = "Promo Event2 Price Tiered"
    Then I verify that Product Thumbnail PriceTypeID = 5 and PriceType = "Promo Event2 Price Tiered" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # *******************************          WITHOUT COLORWAY                 **********************************************************

  #@scenario_PriceType5B_MemberProduct_withoutcolorway
  @ignore
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=5B) in ALL modes for MEMBER product without Colorway Pricing
    Given I am on SearchResultsPage for "NEED INFO" in <Site_Mode> mode
    When I select a "member" product "WithoutColorWay" pricing of PriceTypeID = 5 and PriceType = "Promo Event2 Price Tiered"
    Then I verify that Product Thumbnail PriceTypeID = 5 and PriceType = "Promo Event2 Price Tiered" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  #@scenario_PriceType5B_MasterProduct_withoutcolorway
  @ignore
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=5B) in ALL modes for MASTER product without Colorway Pricing
    Given I am on SearchResultsPage for "NEED INFO" in <Site_Mode> mode
    When I select a "master" product "WithoutColorWay" pricing of PriceTypeID = 5 and PriceType = "Promo Event2 Price Tiered"
    Then I verify that Product Thumbnail PriceTypeID = 5 and PriceType = "Promo Event2 Price Tiered" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  #@scenario_PriceType5B_StandardProduct_withoutcolorway
  @ignore
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=5B) in ALL modes for STANDARD product without Colorway Pricing
    Given I am on SearchResultsPage for "NEED INFO" in <Site_Mode> mode
    When I select a "standard" product "WithoutColorWay" pricing of PriceTypeID = 5 and PriceType = "Promo Event2 Price Tiered"
    Then I verify that Product Thumbnail PriceTypeID = 5 and PriceType = "Promo Event2 Price Tiered" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # ******************************* PRICE TYPE ID = 17 PRICE TYPE = Price Break Tiered **********************************************************
 # *******************************               WITH COLORWAY                        **********************************************************

  @scenario_PriceType17_MemberProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=17) in ALL modes for MEMBER product with Colorway Pricing
    Given I am on SearchResultsPage for "oscar thermal lined 40 x 63 panel" in <Site_Mode> mode
    When I select a "member" product "WithColorWay" pricing of PriceTypeID = 17 and PriceType = "Price Break Tiered"
    Then I verify that Product Thumbnail PriceTypeID = 17 and PriceType = "Price Break Tiered" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType17_MasterProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=17) in ALL modes for MASTER product with Colorway Pricing
    Given I am on SearchResultsPage for "sun zero oscar printed" in <Site_Mode> mode
    When I select a "master" product "WithColorWay" pricing of PriceTypeID = 17 and PriceType = "Price Break Tiered"
    Then I verify that Product Thumbnail PriceTypeID = 17 and PriceType = "Price Break Tiered" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType17_StandardProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=17) in ALL modes for STANDARD product with Colorway Pricing
    Given I am on SearchResultsPage for "tubular invader strap casual sneakers from finish line" in <Site_Mode> mode
    When I select a "standard" product "WithColorWay" pricing of PriceTypeID = 17 and PriceType = "Price Break Tiered"
    Then I verify that Product Thumbnail PriceTypeID = 17 and PriceType = "Price Break Tiered" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # *******************************          WITHOUT COLORWAY                 **********************************************************

  @scenario_PriceType17_MemberProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=17) in ALL modes for MEMBER product without Colorway Pricing
    Given I am on SearchResultsPage for "sun zero pattern room darkening 54 x 63 panel" in <Site_Mode> mode
    When I select a "member" product "WithoutColorWay" pricing of PriceTypeID = 17 and PriceType = "Price Break Tiered"
    Then I verify that Product Thumbnail PriceTypeID = 17 and PriceType = "Price Break Tiered" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType17_MasterProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=17) in ALL modes for MASTER product without Colorway Pricing
    Given I am on SearchResultsPage for "rowan all over circle pattern room darkening collection" in <Site_Mode> mode
    When I select a "master" product "WithoutColorWay" pricing of PriceTypeID = 17 and PriceType = "Price Break Tiered"
    Then I verify that Product Thumbnail PriceTypeID = 17 and PriceType = "Price Break Tiered" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType17_StandardProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=17) in ALL modes for STANDARD product without Colorway Pricing
    Given I am on SearchResultsPage for "tubular invader strap casual sneakers from finish line" in <Site_Mode> mode
    When I select a "standard" product "WithoutColorWay" pricing of PriceTypeID = 17 and PriceType = "Price Break Tiered"
    Then I verify that Product Thumbnail PriceTypeID = 17 and PriceType = "Price Break Tiered" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # ******************************* PRICE TYPE ID = 15 PRICE TYPE = LPOS **********************************************************
 # *******************************               WITH COLORWAY          **********************************************************

  @scenario_PriceType15_MemberProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=15) in ALL modes for MEMBER product with Colorway Pricing
    Given I am on SearchResultsPage for "lichtenberg cera open weave sheer 50 x 63 panel" in <Site_Mode> mode
    When I select a "member" product "WithColorWay" pricing of PriceTypeID = 15 and PriceType = "LPOS"
    Then I verify that Product Thumbnail PriceTypeID = 15 and PriceType = "LPOS" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType15_MasterProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=15) in ALL modes for MASTER product with Colorway Pricing
    Given I am on SearchResultsPage for "lichtenberg cera open weave sheer collection" in <Site_Mode> mode
    When I select a "master" product "WithColorWay" pricing of PriceTypeID = 15 and PriceType = "LPOS"
    Then I verify that Product Thumbnail PriceTypeID = 15 and PriceType = "LPOS" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType15_StandardProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=15) in ALL modes for STANDARD product with Colorway Pricing
    Given I am on SearchResultsPage for "adidas basketball sneakers from finish line" in <Site_Mode> mode
    When I select a "standard" product "WithColorWay" pricing of PriceTypeID = 15 and PriceType = "LPOS"
    Then I verify that Product Thumbnail PriceTypeID = 15 and PriceType = "LPOS" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # *******************************          WITHOUT COLORWAY                 **********************************************************

  @scenario_PriceType15_MemberProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=15) in ALL modes for MEMBER product without Colorway Pricing
    Given I am on SearchResultsPage for "emerson woven geometric blackout 52 x 63 panel" in <Site_Mode> mode
    When I select a "member" product "WithoutColorWay" pricing of PriceTypeID = 15 and PriceType = "LPOS"
    Then I verify that Product Thumbnail PriceTypeID = 15 and PriceType = "LPOS" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType15_MasterProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=15) in ALL modes for MASTER product without Colorway Pricing
    Given I am on SearchResultsPage for "sun zero emerson woven geometric blackout collection" in <Site_Mode> mode
    When I select a "master" product "WithoutColorWay" pricing of PriceTypeID = 15 and PriceType = "LPOS"
    Then I verify that Product Thumbnail PriceTypeID = 15 and PriceType = "LPOS" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  #Xl product not available in qa17 also
  #@scenario_PriceType15_StandardProduct_withoutcolorway
  @ignore
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=15) in ALL modes for STANDARD product without Colorway Pricing
    Given I am on SearchResultsPage for "NEED INFO" in <Site_Mode> mode
    When I select a "standard" product "WithoutColorWay" pricing of PriceTypeID = 15 and PriceType = "LPOS"
    Then I verify that Product Thumbnail PriceTypeID = 15 and PriceType = "LPOS" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |


# ******************************* PRICE TYPE ID = 18 PRICE TYPE = LPOS Tiered **********************************************************
 # *******************************               WITH COLORWAY                **********************************************************

  @scenario_PriceType18_MemberProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=18) in ALL modes for MEMBER product with Colorway Pricing
    Given I am on SearchResultsPage for "silk road faux-silk 54 x 84 panel" in <Site_Mode> mode
    When I select a "member" product "WithColorWay" pricing of PriceTypeID = 18 and PriceType = "LPOS Tiered"
    Then I verify that Product Thumbnail PriceTypeID = 18 and PriceType = "LPOS Tiered" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType18_MasterProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=18) in ALL modes for MASTER product with Colorway Pricing
    Given I am on SearchResultsPage for "softline silk road faux-silk window collection" in <Site_Mode> mode
    When I select a "master" product "WithColorWay" pricing of PriceTypeID = 18 and PriceType = "LPOS Tiered"
    Then I verify that Product Thumbnail PriceTypeID = 18 and PriceType = "LPOS Tiered" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType18_StandardProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=18) in ALL modes for STANDARD product with Colorway Pricing
    Given I am on SearchResultsPage for "reebok adapt casual sneakers from finish line" in <Site_Mode> mode
    When I select a "standard" product "WithColorWay" pricing of PriceTypeID = 18 and PriceType = "LPOS Tiered"
    Then I verify that Product Thumbnail PriceTypeID = 18 and PriceType = "LPOS Tiered" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # *******************************          WITHOUT COLORWAY                 **********************************************************

  @scenario_PriceType18_MemberProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=18) in ALL modes for MEMBER product without Colorway Pricing
    Given I am on SearchResultsPage for "softline kalika 54 x 84 panel" in <Site_Mode> mode
    When I select a "member" product "WithoutColorWay" pricing of PriceTypeID = 18 and PriceType = "LPOS Tiered"
    Then I verify that Product Thumbnail PriceTypeID = 18 and PriceType = "LPOS Tiered" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType18_MasterProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=18) in ALL modes for MASTER product without Colorway Pricing
    Given I am on SearchResultsPage for "softline kalika window panel collection" in <Site_Mode> mode
    When I select a "master" product "WithoutColorWay" pricing of PriceTypeID = 18 and PriceType = "LPOS Tiered"
    Then I verify that Product Thumbnail PriceTypeID = 18 and PriceType = "LPOS Tiered" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType18_StandardProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=18) in ALL modes for STANDARD product without Colorway Pricing
    Given I am on SearchResultsPage for "reebok casual sneakers from finish line" in <Site_Mode> mode
    When I select a "standard" product "WithoutColorWay" pricing of PriceTypeID = 18 and PriceType = "LPOS Tiered"
    Then I verify that Product Thumbnail PriceTypeID = 18 and PriceType = "LPOS Tiered" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # ******************************* PRICE TYPE ID = 20 PRICE TYPE = Special Savings **********************************************************
 # *******************************               WITH COLORWAY                 **********************************************************

  @scenario_PriceType20_MemberProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=20) in ALL modes for MEMBER product with Colorway Pricing
    Given I am on SearchResultsPage for "diverge 88-120 double rod" in <Site_Mode> mode
    When I select a "member" product "WithColorWay" pricing of PriceTypeID = 20 and PriceType = "Special Savings"
    Then I verify that Product Thumbnail PriceTypeID = 20 and PriceType = "Special Savings" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType20_MasterProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=20) in ALL modes for MASTER product with Colorway Pricing
    Given I am on SearchResultsPage for "double rod window hardware" in <Site_Mode> mode
    When I select a "master" product "WithColorWay" pricing of PriceTypeID = 20 and PriceType = "Special Savings"
    Then I verify that Product Thumbnail PriceTypeID = 20 and PriceType = "Special Savings" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType20_StandardProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=20) in ALL modes for STANDARD product with Colorway Pricing
    Given I am on SearchResultsPage for "nike ltd 3 running sneakers from finish line" in <Site_Mode> mode
    When I select a "standard" product "WithColorWay" pricing of PriceTypeID = 20 and PriceType = "Special Savings"
    Then I verify that Product Thumbnail PriceTypeID = 20 and PriceType = "Special Savings" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # *******************************          WITHOUT COLORWAY                 **********************************************************

  @scenario_PriceType20_MemberProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=20) in ALL modes for MEMBER product without Colorway Pricing
    Given I am on SearchResultsPage for "softline sentinel panel 54 x 84" in <Site_Mode> mode
    When I select a "member" product "WithoutColorWay" pricing of PriceTypeID = 20 and PriceType = "Special Savings"
    Then I verify that Product Thumbnail PriceTypeID = 20 and PriceType = "Special Savings" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType20_MasterProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=20) in ALL modes for MASTER product without Colorway Pricing
    Given I am on SearchResultsPage for "softline sentinel window collection" in <Site_Mode> mode
    When I select a "master" product "WithoutColorWay" pricing of PriceTypeID = 20 and PriceType = "Special Savings"
    Then I verify that Product Thumbnail PriceTypeID = 20 and PriceType = "Special Savings" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType20_StandardProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=20) in ALL modes for STANDARD product without Colorway Pricing
    Given I am on SearchResultsPage for "nike flyknit running sneakers from finish line" in <Site_Mode> mode
    When I select a "standard" product "WithoutColorWay" pricing of PriceTypeID = 20 and PriceType = "Special Savings"
    Then I verify that Product Thumbnail PriceTypeID = 20 and PriceType = "Special Savings" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # ******************************* PRICE TYPE ID = 21 PRICE TYPE = Special Savings Tiered **********************************************************
 # *******************************                     WITH COLORWAY                      **********************************************************

  @scenario_PriceType21_MemberProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=21) in ALL modes for MEMBER product with Colorway Pricing
    Given I am on SearchResultsPage for "elrene linen stripe 30 x 24 tier" in <Site_Mode> mode
    When I select a "member" product "WithColorWay" pricing of PriceTypeID = 21 and PriceType = "Special Savings Tiered"
    Then I verify that Product Thumbnail PriceTypeID = 21 and PriceType = "Special Savings Tiered" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType21_MasterProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=21) in ALL modes for MASTER product with Colorway Pricing
    Given I am on SearchResultsPage for "elrene linen stripe window treatment collection" in <Site_Mode> mode
    When I select a "master" product "WithColorWay" pricing of PriceTypeID = 21 and PriceType = "Special Savings Tiered"
    Then I verify that Product Thumbnail PriceTypeID = 21 and PriceType = "Special Savings Tiered" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType21_StandardProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=21) in ALL modes for STANDARD product with Colorway Pricing
    Given I am on SearchResultsPage for "adidas sport pack casual sneakers from finish line" in <Site_Mode> mode
    When I select a "standard" product "WithColorWay" pricing of PriceTypeID = 21 and PriceType = "Special Savings Tiered"
    Then I verify that Product Thumbnail PriceTypeID = 21 and PriceType = "Special Savings Tiered" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # *******************************          WITHOUT COLORWAY                 **********************************************************

  @scenario_PriceType21_MemberProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=21) in ALL modes for MEMBER product without Colorway Pricing
    Given I am on SearchResultsPage for "elrene taylor 30 x 24 tier" in <Site_Mode> mode
    When I select a "member" product "WithoutColorWay" pricing of PriceTypeID = 21 and PriceType = "Special Savings Tiered"
    Then I verify that Product Thumbnail PriceTypeID = 21 and PriceType = "Special Savings Tiered" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType21_MasterProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=21) in ALL modes for MASTER product without Colorway Pricing
    Given I am on SearchResultsPage for "elrene taylor window treatment collection" in <Site_Mode> mode
    When I select a "master" product "WithoutColorWay" pricing of PriceTypeID = 21 and PriceType = "Special Savings Tiered"
    Then I verify that Product Thumbnail PriceTypeID = 21 and PriceType = "Special Savings Tiered" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  #product not available
  #@scenario_PriceType21_StandardProduct_withoutcolorway
  @ignore
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=21) in ALL modes for STANDARD product without Colorway Pricing
    Given I am on SearchResultsPage for "NEED INFO" in <Site_Mode> mode
    When I select a "standard" product "WithoutColorWay" pricing of PriceTypeID = 21 and PriceType = "Special Savings Tiered"
    Then I verify that Product Thumbnail PriceTypeID = 21 and PriceType = "Special Savings Tiered" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # ******************************* PRICE TYPE ID = 22 PRICE TYPE = Web Buster **********************************************************
 # *******************************                     WITH COLORWAY         **********************************************************

  @scenario_PriceType22_MemberProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=22) in ALL modes for MEMBER product with Colorway Pricing
    Given I am on SearchResultsPage for "elrene ticking 30 x 24 tier" in <Site_Mode> mode
    When I select a "member" product "WithColorWay" pricing of PriceTypeID = 22 and PriceType = "Web Buster"
    Then I verify that Product Thumbnail PriceTypeID = 22 and PriceType = "Web Buster" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType22_MasterProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=22) in ALL modes for MASTER product with Colorway Pricing
    Given I am on SearchResultsPage for "elrene ticking window treatment collection" in <Site_Mode> mode
    When I select a "master" product "WithColorWay" pricing of PriceTypeID = 22 and PriceType = "Web Buster"
    Then I verify that Product Thumbnail PriceTypeID = 22 and PriceType = "Web Buster" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType22_StandardProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=22) in ALL modes for STANDARD product with Colorway Pricing
    Given I am on SearchResultsPage for "adidas em running sneakers from finish line" in <Site_Mode> mode
    When I select a "standard" product "WithColorWay" pricing of PriceTypeID = 22 and PriceType = "Web Buster"
    Then I verify that Product Thumbnail PriceTypeID = 22 and PriceType = "Web Buster" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # *******************************          WITHOUT COLORWAY                 **********************************************************

  @scenario_PriceType22_MemberProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=22) in ALL modes for MEMBER product without Colorway Pricing
    Given I am on SearchResultsPage for "elrene flora 30 x 24 tier" in <Site_Mode> mode
    When I select a "member" product "WithoutColorWay" pricing of PriceTypeID = 22 and PriceType = "Web Buster"
    Then I verify that Product Thumbnail PriceTypeID = 22 and PriceType = "Web Buster" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType22_MasterProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=22) in ALL modes for MASTER product without Colorway Pricing
    Given I am on SearchResultsPage for "elrene flora window treatment collection" in <Site_Mode> mode
    When I select a "master" product "WithoutColorWay" pricing of PriceTypeID = 22 and PriceType = "Web Buster"
    Then I verify that Product Thumbnail PriceTypeID = 22 and PriceType = "Web Buster" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType22_StandardProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=22) in ALL modes for STANDARD product without Colorway Pricing
    Given I am on SearchResultsPage for "adidas em running sneakers from finish line" in <Site_Mode> mode
    When I select a "standard" product "WithoutColorWay" pricing of PriceTypeID = 22 and PriceType = "Web Buster"
    Then I verify that Product Thumbnail PriceTypeID = 22 and PriceType = "Web Buster" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # ******************************* PRICE TYPE ID = 23 PRICE TYPE = Deal of the Day Type A **********************************************************
 # *******************************                     WITH COLORWAY                      **********************************************************

  @scenario_PriceType23A_MemberProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=23A) in ALL modes for MEMBER product with Colorway Pricing
    Given I am on SearchResultsPage for "miller curtains nero sheer burnout 50 x 63 panel" in <Site_Mode> mode
    When I select a "member" product "WithColorWay" pricing of PriceTypeID = 23 and PriceType = "Deal of the Day Type A"
    Then I verify that Product Thumbnail PriceTypeID = 23 and PriceType = "Deal of the Day Type A" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType23A_MasterProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=23A) in ALL modes for MASTER product with Colorway Pricing
    Given I am on SearchResultsPage for "elrene ticking window treatment collection" in <Site_Mode> mode
    When I select a "master" product "WithColorWay" pricing of PriceTypeID = 23 and PriceType = "Deal of the Day Type A"
    Then I verify that Product Thumbnail PriceTypeID = 23 and PriceType = "Deal of the Day Type A" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType23A_StandardProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=23A) in ALL modes for STANDARD product with Colorway Pricing
    Given I am on SearchResultsPage for "dockers men's norwalk sneakers" in <Site_Mode> mode
    When I select a "standard" product "WithColorWay" pricing of PriceTypeID = 23 and PriceType = "Deal of the Day Type A"
    Then I verify that Product Thumbnail PriceTypeID = 23 and PriceType = "Deal of the Day Type A" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # *******************************          WITHOUT COLORWAY                 **********************************************************

  @scenario_PriceType23A_MemberProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=23A) in ALL modes for MEMBER product without Colorway Pricing
    Given I am on SearchResultsPage for "elrene serene 30 x 24 tier" in <Site_Mode> mode
    When I select a "member" product "WithoutColorWay" pricing of PriceTypeID = 23 and PriceType = "Deal of the Day Type A"
    Then I verify that Product Thumbnail PriceTypeID = 23 and PriceType = "Deal of the Day Type A" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType23A_MasterProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=23A) in ALL modes for MASTER product without Colorway Pricing
    Given I am on SearchResultsPage for "elrene serene window treatment collection" in <Site_Mode> mode
    When I select a "master" product "WithoutColorWay" pricing of PriceTypeID = 23 and PriceType = "Deal of the Day Type A"
    Then I verify that Product Thumbnail PriceTypeID = 23 and PriceType = "Deal of the Day Type A" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType23A_StandardProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=23A) in ALL modes for STANDARD product without Colorway Pricing
    Given I am on SearchResultsPage for "adidas tubular shadow casual sneakers from finish line" in <Site_Mode> mode
    When I select a "standard" product "WithoutColorWay" pricing of PriceTypeID = 23 and PriceType = "Deal of the Day Type A"
    Then I verify that Product Thumbnail PriceTypeID = 23 and PriceType = "Deal of the Day Type A" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # ******************************* PRICE TYPE ID = 23 PRICE TYPE = Deal of the Day Type B    **********************************************************
 # *******************************                     WITH COLORWAY                         **********************************************************

  @scenario_PriceType23B_MemberProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=23B) in ALL modes for MEMBER product with Colorway Pricing
    Given I am on SearchResultsPage for "saturday knight tranquility 58 x 13 valance" in <Site_Mode> mode
    When I select a "member" product "WithColorWay" pricing of PriceTypeID = 23 and PriceType = "Deal of the Day Type B"
    Then I verify that Product Thumbnail PriceTypeID = 23 and PriceType = "Deal of the Day Type B" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType23B_MasterProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=23B) in ALL modes for MASTER product with Colorway Pricing
    Given I am on SearchResultsPage for "saturday knight tranquility tiers collection" in <Site_Mode> mode
    When I select a "master" product "WithColorWay" pricing of PriceTypeID = 23 and PriceType = "Deal of the Day Type B"
    Then I verify that Product Thumbnail PriceTypeID = 23 and PriceType = "Deal of the Day Type B" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType23B_StandardProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=23B) in ALL modes for STANDARD product with Colorway Pricing
    Given I am on SearchResultsPage for "levi's men's stan buck ii sneakers" in <Site_Mode> mode
    When I select a "standard" product "WithColorWay" pricing of PriceTypeID = 23 and PriceType = "Deal of the Day Type B"
    Then I verify that Product Thumbnail PriceTypeID = 23 and PriceType = "Deal of the Day Type B" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # *******************************          WITHOUT COLORWAY                 **********************************************************

  @scenario_PriceType23B_MemberProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=23B) in ALL modes for MEMBER product without Colorway Pricing
    Given I am on SearchResultsPage for "elrene wilton 30 x 24 tier" in <Site_Mode> mode
    When I select a "member" product "WithoutColorWay" pricing of PriceTypeID = 23 and PriceType = "Deal of the Day Type B"
    Then I verify that Product Thumbnail PriceTypeID = 23 and PriceType = "Deal of the Day Type B" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType23B_MasterProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=23B) in ALL modes for MASTER product without Colorway Pricing
    Given I am on SearchResultsPage for "elrene wilton window treatment collection" in <Site_Mode> mode
    When I select a "master" product "WithoutColorWay" pricing of PriceTypeID = 23 and PriceType = "Deal of the Day Type B"
    Then I verify that Product Thumbnail PriceTypeID = 23 and PriceType = "Deal of the Day Type B" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType23B_StandardProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=23B) in ALL modes for STANDARD product without Colorway Pricing
    Given I am on SearchResultsPage for "adidas zx 700 casual sneakers from finish line" in <Site_Mode> mode
    When I select a "standard" product "WithoutColorWay" pricing of PriceTypeID = 23 and PriceType = "Deal of the Day Type B"
    Then I verify that Product Thumbnail PriceTypeID = 23 and PriceType = "Deal of the Day Type B" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # ******************************* PRICE TYPE ID = 24 PRICE TYPE = Limited Time Special Type A    **********************************************************
 # *******************************                     WITH COLORWAY                              **********************************************************

  @scenario_PriceType24A_MemberProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=24A) in ALL modes for MEMBER product with Colorway Pricing
    Given I am on SearchResultsPage for "elrene embroidered 52 x 84 room darkening panel" in <Site_Mode> mode
    When I select a "member" product "WithColorWay" pricing of PriceTypeID = 24 and PriceType = "Limited-Time Special Type A"
    Then I verify that Product Thumbnail PriceTypeID = 24 and PriceType = "Limited-Time Special Type A" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType24A_MasterProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=24A) in ALL modes for MASTER product with Colorway Pricing
    Given I am on SearchResultsPage for "elrene ticking window treatment collection" in <Site_Mode> mode
    When I select a "master" product "WithColorWay" pricing of PriceTypeID = 24 and PriceType = "Limited-Time Special Type A"
    Then I verify that Product Thumbnail PriceTypeID = 24 and PriceType = "Limited-Time Special Type A" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType24A_StandardProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=24A) in ALL modes for STANDARD product with Colorway Pricing
    Given I am on SearchResultsPage for "calvin klein men's igor sneakers" in <Site_Mode> mode
    When I select a "standard" product "WithColorWay" pricing of PriceTypeID = 24 and PriceType = "Limited-Time Special Type A"
    Then I verify that Product Thumbnail PriceTypeID = 24 and PriceType = "Limited-Time Special Type A" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # *******************************          WITHOUT COLORWAY                 **********************************************************

  @scenario_PriceType24A_MemberProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=24A) in ALL modes for MEMBER product without Colorway Pricing
    Given I am on SearchResultsPage for "softline palisade tile 55 x 84 panel" in <Site_Mode> mode
    When I select a "member" product "WithoutColorWay" pricing of PriceTypeID = 24 and PriceType = "Limited-Time Special Type A"
    Then I verify that Product Thumbnail PriceTypeID = 24 and PriceType = "Limited-Time Special Type A" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType24A_MasterProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=24A) in ALL modes for MASTER product without Colorway Pricing
    Given I am on SearchResultsPage for "softline palisade tile window treatment collection" in <Site_Mode> mode
    When I select a "master" product "WithoutColorWay" pricing of PriceTypeID = 24 and PriceType = "Limited-Time Special Type A"
    Then I verify that Product Thumbnail PriceTypeID = 24 and PriceType = "Limited-Time Special Type A" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType24A_StandardProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=24A) in ALL modes for STANDARD product without Colorway Pricing
    Given I am on SearchResultsPage for "frye brett low top sneakers" in <Site_Mode> mode
    When I select a "standard" product "WithoutColorWay" pricing of PriceTypeID = 24 and PriceType = "Limited-Time Special Type A"
    Then I verify that Product Thumbnail PriceTypeID = 24 and PriceType = "Limited-Time Special Type A" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # ******************************* PRICE TYPE ID = 24 PRICE TYPE = Limited Time Special Type B    **********************************************************
 # *******************************                     WITH COLORWAY                              **********************************************************

  @scenario_PriceType24B_MemberProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=24B) in ALL modes for MEMBER product with Colorway Pricing
    Given I am on SearchResultsPage for "elrene linen medina 52 x 84 panel" in <Site_Mode> mode
    When I select a "member" product "WithColorWay" pricing of PriceTypeID = 24 and PriceType = "Limited-Time Special Type B"
    Then I verify that Product Thumbnail PriceTypeID = 24 and PriceType = "Limited-Time Special Type B" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType24B_MasterProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=24B) in ALL modes for MASTER product with Colorway Pricing
    Given I am on SearchResultsPage for "elrene medina window treatment collection" in <Site_Mode> mode
    When I select a "master" product "WithColorWay" pricing of PriceTypeID = 24 and PriceType = "Limited-Time Special Type B"
    Then I verify that Product Thumbnail PriceTypeID = 24 and PriceType = "Limited-Time Special Type B" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType24B_StandardProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=24B) in ALL modes for STANDARD product with Colorway Pricing
    Given I am on SearchResultsPage for "calvin klein navin high-top sneakers" in <Site_Mode> mode
    When I select a "standard" product "WithColorWay" pricing of PriceTypeID = 24 and PriceType = "Limited-Time Special Type B"
    Then I verify that Product Thumbnail PriceTypeID = 24 and PriceType = "Limited-Time Special Type B" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # *******************************          WITHOUT COLORWAY                 **********************************************************

  @scenario_PriceType24B_MemberProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=24B) in ALL modes for MEMBER product without Colorway Pricing
    Given I am on SearchResultsPage for "softline alondra 54 x 84 panel" in <Site_Mode> mode
    When I select a "member" product "WithoutColorWay" pricing of PriceTypeID = 24 and PriceType = "Limited-Time Special Type B"
    Then I verify that Product Thumbnail PriceTypeID = 24 and PriceType = "Limited-Time Special Type B" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType24B_MasterProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=24B) in ALL modes for MASTER product without Colorway Pricing
    Given I am on SearchResultsPage for "softline alondra window treatment collection" in <Site_Mode> mode
    When I select a "master" product "WithoutColorWay" pricing of PriceTypeID = 24 and PriceType = "Limited-Time Special Type B"
    Then I verify that Product Thumbnail PriceTypeID = 24 and PriceType = "Limited-Time Special Type B" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType24B_StandardProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=24B) in ALL modes for STANDARD product without Colorway Pricing
    Given I am on SearchResultsPage for "calvin klein ivo slip-on sneakers" in <Site_Mode> mode
    When I select a "standard" product "WithoutColorWay" pricing of PriceTypeID = 24 and PriceType = "Limited-Time Special Type B"
    Then I verify that Product Thumbnail PriceTypeID = 24 and PriceType = "Limited-Time Special Type B" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # ******************************* PRICE TYPE ID = 25 PRICE TYPE = Black Friday Special Type A    **********************************************************
 # *******************************                     WITH COLORWAY                              **********************************************************

  @scenario_PriceType25A_MemberProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=25A) in ALL modes for MEMBER product with Colorway Pricing
    Given I am on SearchResultsPage for "cameron faux-suede 50 x 95 panel" in <Site_Mode> mode
    When I select a "member" product "WithColorWay" pricing of PriceTypeID = 25 and PriceType = "Black Friday Special Type A"
    Then I verify that Product Thumbnail PriceTypeID = 25 and PriceType = "Black Friday Special Type A" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType25A_MasterProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=25A) in ALL modes for MASTER product with Colorway Pricing
    Given I am on SearchResultsPage for "cameron faux-suede window treatment collection" in <Site_Mode> mode
    When I select a "master" product "WithColorWay" pricing of PriceTypeID = 25 and PriceType = "Black Friday Special Type A"
    Then I verify that Product Thumbnail PriceTypeID = 25 and PriceType = "Black Friday Special Type A" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType25A_StandardProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=25A) in ALL modes for STANDARD product with Colorway Pricing
    Given I am on SearchResultsPage for "tommy hilfiger pawley low-top sneakers" in <Site_Mode> mode
    When I select a "standard" product "WithColorWay" pricing of PriceTypeID = 25 and PriceType = "Black Friday Special Type A"
    Then I verify that Product Thumbnail PriceTypeID = 25 and PriceType = "Black Friday Special Type A" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # *******************************          WITHOUT COLORWAY                 **********************************************************

  @scenario_PriceType25A_MemberProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=25A) in ALL modes for MEMBER product without Colorway Pricing
    Given I am on SearchResultsPage for "sheer butterflies lace 56 x 38 swag valance" in <Site_Mode> mode
    When I select a "member" product "WithoutColorWay" pricing of PriceTypeID = 25 and PriceType = "Black Friday Special Type A"
    Then I verify that Product Thumbnail PriceTypeID = 25 and PriceType = "Black Friday Special Type A" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType25A_MasterProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=25A) in ALL modes for MASTER product without Colorway Pricing
    Given I am on SearchResultsPage for "saturday knight sheer butterflies lace collection" in <Site_Mode> mode
    When I select a "master" product "WithoutColorWay" pricing of PriceTypeID = 25 and PriceType = "Black Friday Special Type A"
    Then I verify that Product Thumbnail PriceTypeID = 25 and PriceType = "Black Friday Special Type A" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType25A_StandardProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=25A) in ALL modes for STANDARD product without Colorway Pricing
    Given I am on SearchResultsPage for "kenneth cole new york down-n-up sneakers" in <Site_Mode> mode
    When I select a "standard" product "WithoutColorWay" pricing of PriceTypeID = 25 and PriceType = "Black Friday Special Type A"
    Then I verify that Product Thumbnail PriceTypeID = 25 and PriceType = "Black Friday Special Type A" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # ******************************* PRICE TYPE ID = 25 PRICE TYPE = Black Friday Special Type B    **********************************************************
 # *******************************                     WITH COLORWAY                              **********************************************************

  @scenario_PriceType25B_MemberProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=25B) in ALL modes for MEMBER product with Colorway Pricing
    Given I am on SearchResultsPage for "CHF Jayden 60 x 30 Swag Valance" in <Site_Mode> mode
    When I select a "member" product "WithColorWay" pricing of PriceTypeID = 25 and PriceType = "Black Friday Special Type B"
    Then I verify that Product Thumbnail PriceTypeID = 25 and PriceType = "Black Friday Special Type B" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
  #    |  iship    |
  #    | registry  |

  @scenario_PriceType25B_MasterProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=25B) in ALL modes for MASTER product with Colorway Pricing
    Given I am on SearchResultsPage for "chf jayden tiers window treatment collection" in <Site_Mode> mode
    When I select a "master" product "WithColorWay" pricing of PriceTypeID = 25 and PriceType = "Black Friday Special Type B"
    Then I verify that Product Thumbnail PriceTypeID = 25 and PriceType = "Black Friday Special Type B" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
   #   |  iship    |
   #   | registry  |

  @scenario_PriceType25B_StandardProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=25B) in ALL modes for STANDARD product with Colorway Pricing
    Given I am on SearchResultsPage for "levi's stan buck ii sneakers" in <Site_Mode> mode
    When I select a "standard" product "WithColorWay" pricing of PriceTypeID = 25 and PriceType = "Black Friday Special Type B"
    Then I verify that Product Thumbnail PriceTypeID = 25 and PriceType = "Black Friday Special Type B" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # *******************************          WITHOUT COLORWAY                 **********************************************************

  @scenario_PriceType25B_MemberProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=25B) in ALL modes for MEMBER product without Colorway Pricing
    Given I am on SearchResultsPage for "chf sheer peach skin 42 x 63 panel" in <Site_Mode> mode
    When I select a "member" product "WithoutColorWay" pricing of PriceTypeID = 25 and PriceType = "Black Friday Special Type B"
    Then I verify that Product Thumbnail PriceTypeID = 25 and PriceType = "Black Friday Special Type B" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType25B_MasterProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=25B) in ALL modes for MASTER product without Colorway Pricing
    Given I am on SearchResultsPage for "chf sheer peach skin collection" in <Site_Mode> mode
    When I select a "master" product "WithoutColorWay" pricing of PriceTypeID = 25 and PriceType = "Black Friday Special Type B"
    Then I verify that Product Thumbnail PriceTypeID = 25 and PriceType = "Black Friday Special Type B" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType25B_StandardProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=25B) in ALL modes for STANDARD product without Colorway Pricing
    Given I am on SearchResultsPage for "calvin klein berke patent high-top sneakers" in <Site_Mode> mode
    When I select a "standard" product "WithoutColorWay" pricing of PriceTypeID = 25 and PriceType = "Black Friday Special Type B"
    Then I verify that Product Thumbnail PriceTypeID = 25 and PriceType = "Black Friday Special Type B" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # ******************************* PRICE TYPE ID = 26 PRICE TYPE = Cyber Monday Special **********************************************************
 # *******************************               WITH COLORWAY                         **********************************************************

  #@scenario_PriceType26_MemberProduct_withcolorway
  @ignore
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=26) in ALL modes for MEMBER product with Colorway Pricing
    Given I am on SearchResultsPage for "NEED INFO" in <Site_Mode> mode
    When I select a "member" product "WithColorWay" pricing of PriceTypeID = 26 and PriceType = "Cyber Monday Special"
    Then I verify that Product Thumbnail PriceTypeID = 26 and PriceType = "Cyber Monday Special" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  #@scenario_PriceType26_MasterProduct_withcolorway
  @ignore
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=26) in ALL modes for MASTER product with Colorway Pricing
    Given I am on SearchResultsPage for "NEED INFO" in <Site_Mode> mode
    When I select a "master" product "WithColorWay" pricing of PriceTypeID = 26 and PriceType = "Cyber Monday Special"
    Then I verify that Product Thumbnail PriceTypeID = 26 and PriceType = "Cyber Monday Special" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  #@scenario_PriceType26_StandardProduct_withcolorway
  @ignore
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=26) in ALL modes for STANDARD product with Colorway Pricing
    Given I am on SearchResultsPage for "NEED INFO" in <Site_Mode> mode
    When I select a "standard" product "WithColorWay" pricing of PriceTypeID = 26 and PriceType = "Cyber Monday Special"
    Then I verify that Product Thumbnail PriceTypeID = 26 and PriceType = "Cyber Monday Special" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # *******************************          WITHOUT COLORWAY                 **********************************************************

  #@scenario_PriceType26_MemberProduct_withoutcolorway
  @ignore
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=26) in ALL modes for MEMBER product without Colorway Pricing
    Given I am on SearchResultsPage for "NEED INFO" in <Site_Mode> mode
    When I select a "member" product "WithoutColorWay" pricing of PriceTypeID = 26 and PriceType = "Cyber Monday Special"
    Then I verify that Product Thumbnail PriceTypeID = 26 and PriceType = "Cyber Monday Special" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  #@scenario_PriceType26_MasterProduct_withoutcolorway
  @ignore
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=26) in ALL modes for MASTER product without Colorway Pricing
    Given I am on SearchResultsPage for "NEED INFO" in <Site_Mode> mode
    When I select a "master" product "WithoutColorWay" pricing of PriceTypeID = 26 and PriceType = "Cyber Monday Special"
    Then I verify that Product Thumbnail PriceTypeID = 26 and PriceType = "Cyber Monday Special" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  #@scenario_PriceType26_StandardProduct_withoutcolorway
  @ignore
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=26) in ALL modes for STANDARD product without Colorway Pricing
    Given I am on SearchResultsPage for "NEED INFO" in <Site_Mode> mode
    When I select a "standard" product "WithoutColorWay" pricing of PriceTypeID = 26 and PriceType = "Cyber Monday Special"
    Then I verify that Product Thumbnail PriceTypeID = 26 and PriceType = "Cyber Monday Special" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # ******************************* PRICE TYPE ID = 5 PRICE TYPE = Additional Off promo    **********************************************************
 # *******************************                    WITH COLORWAY                       **********************************************************

  #@scenario_PriceType5C_MemberProduct_withcolorway
  @ignore
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=5C) in ALL modes for MEMBER product with Colorway Pricing
    Given I am on SearchResultsPage for "NEED INFO" in <Site_Mode> mode
    When I select a "member" product "WithColorWay" pricing of PriceTypeID = 5 and PriceType = "Additional Off promo"
    Then I verify that Product Thumbnail PriceTypeID = 5 and PriceType = "Additional Off promo" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  #@scenario_PriceType5C_MasterProduct_withcolorway
  @ignore
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=5C) in ALL modes for MASTER product with Colorway Pricing
    Given I am on SearchResultsPage for "NEED INFO" in <Site_Mode> mode
    When I select a "master" product "WithColorWay" pricing of PriceTypeID = 5 and PriceType = "Additional Off promo"
    Then I verify that Product Thumbnail PriceTypeID = 5 and PriceType = "Additional Off promo" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  #@scenario_PriceType5C_StandardProduct_withcolorway
  @ignore
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=5C) in ALL modes for STANDARD product with Colorway Pricing
    Given I am on SearchResultsPage for "NEED INFO" in <Site_Mode> mode
    When I select a "standard" product "WithColorWay" pricing of PriceTypeID = 5 and PriceType = "Additional Off promo"
    Then I verify that Product Thumbnail PriceTypeID = 5 and PriceType = "Additional Off promo" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # *******************************          WITHOUT COLORWAY                 **********************************************************

  #@scenario_PriceType5C_MemberProduct_withoutcolorway
  @ignore
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=5C) in ALL modes for MEMBER product without Colorway Pricing
    Given I am on SearchResultsPage for "NEED INFO" in <Site_Mode> mode
    When I select a "member" product "WithoutColorWay" pricing of PriceTypeID = 5 and PriceType = "Additional Off promo"
    Then I verify that Product Thumbnail PriceTypeID = 5 and PriceType = "Additional Off promo" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  #@scenario_PriceType5C_MasterProduct_withoutcolorway
  @ignore
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=5C) in ALL modes for MASTER product without Colorway Pricing
    Given I am on SearchResultsPage for "NEED INFO" in <Site_Mode> mode
    When I select a "master" product "WithoutColorWay" pricing of PriceTypeID = 5 and PriceType = "Additional Off promo"
    Then I verify that Product Thumbnail PriceTypeID = 5 and PriceType = "Additional Off promo" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  #@scenario_PriceType5C_StandardProduct_withoutcolorway
  @ignore
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=5C) in ALL modes for STANDARD product without Colorway Pricing
    Given I am on SearchResultsPage for "NEED INFO" in <Site_Mode> mode
    When I select a "standard" product "WithoutColorWay" pricing of PriceTypeID = 5 and PriceType = "Additional Off promo"
    Then I verify that Product Thumbnail PriceTypeID = 5 and PriceType = "Additional Off promo" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # ******************************* PRICE TYPE ID = 6 PRICE TYPE = Additional Off Perm    **********************************************************
 # *******************************                    WITH COLORWAY                      **********************************************************

  #@scenario_PriceType6_MemberProduct_withcolorway
  @ignore
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=6) in ALL modes for MEMBER product with Colorway Pricing
    Given I am on SearchResultsPage for "NEED INFO" in <Site_Mode> mode
    When I select a "member" product "WithColorWay" pricing of PriceTypeID = 6 and PriceType = "Additional Off Perm"
    Then I verify that Product Thumbnail PriceTypeID = 6 and PriceType = "Additional Off Perm" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  #@scenario_PriceType6_MasterProduct_withcolorway
  @ignore
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=6) in ALL modes for MASTER product with Colorway Pricing
    Given I am on SearchResultsPage for "NEED INFO" in <Site_Mode> mode
    When I select a "master" product "WithColorWay" pricing of PriceTypeID = 6 and PriceType = "Additional Off Perm"
    Then I verify that Product Thumbnail PriceTypeID = 6 and PriceType = "Additional Off Perm" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  #@scenario_PriceType6_StandardProduct_withcolorway
  @ignore
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=6) in ALL modes for STANDARD product with Colorway Pricing
    Given I am on SearchResultsPage for "NEED INFO" in <Site_Mode> mode
    When I select a "standard" product "WithColorWay" pricing of PriceTypeID = 6 and PriceType = "Additional Off Perm"
    Then I verify that Product Thumbnail PriceTypeID = 6 and PriceType = "Additional Off Perm" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # *******************************          WITHOUT COLORWAY                 **********************************************************

  #@scenario_PriceType6_MemberProduct_withoutcolorway
  @ignore
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=6) in ALL modes for MEMBER product without Colorway Pricing
    Given I am on SearchResultsPage for "NEED INFO" in <Site_Mode> mode
    When I select a "member" product "WithoutColorWay" pricing of PriceTypeID = 6 and PriceType = "Additional Off Perm"
    Then I verify that Product Thumbnail PriceTypeID = 6 and PriceType = "Additional Off Perm" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  #@scenario_PriceType6_MasterProduct_withoutcolorway
  @ignore
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=6) in ALL modes for MASTER product without Colorway Pricing
    Given I am on SearchResultsPage for "NEED INFO" in <Site_Mode> mode
    When I select a "master" product "WithoutColorWay" pricing of PriceTypeID = 6 and PriceType = "Additional Off Perm"
    Then I verify that Product Thumbnail PriceTypeID = 6 and PriceType = "Additional Off Perm" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  #@scenario_PriceType6_StandardProduct_withoutcolorway
  @ignore
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=6) in ALL modes for STANDARD product without Colorway Pricing
    Given I am on SearchResultsPage for "NEED INFO" in <Site_Mode> mode
    When I select a "standard" product "WithoutColorWay" pricing of PriceTypeID = 6 and PriceType = "Additional Off Perm"
    Then I verify that Product Thumbnail PriceTypeID = 6 and PriceType = "Additional Off Perm" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # ******************************* PRICE TYPE ID = 19 PRICE TYPE = Additional Off Closeout    **********************************************************
 # *******************************                    WITH COLORWAY                           **********************************************************

  #@scenario_PriceType19_MemberProduct_withcolorway
  @ignore
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=19) in ALL modes for MEMBER product with Colorway Pricing
    Given I am on SearchResultsPage for "NEED INFO" in <Site_Mode> mode
    When I select a "member" product "WithColorWay" pricing of PriceTypeID = 19 and PriceType = "Additional Off Closeout"
    Then I verify that Product Thumbnail PriceTypeID = 19 and PriceType = "Additional Off Closeout" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  #@scenario_PriceType19_MasterProduct_withcolorway
  @ignore
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=19) in ALL modes for MASTER product with Colorway Pricing
    Given I am on SearchResultsPage for "NEED INFO" in <Site_Mode> mode
    When I select a "master" product "WithColorWay" pricing of PriceTypeID = 19 and PriceType = "Additional Off Closeout"
    Then I verify that Product Thumbnail PriceTypeID = 19 and PriceType = "Additional Off Closeout" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  #@scenario_PriceType19_StandardProduct_withcolorway
  @ignore
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=19) in ALL modes for STANDARD product with Colorway Pricing
    Given I am on SearchResultsPage for "NEED INFO" in <Site_Mode> mode
    When I select a "standard" product "WithColorWay" pricing of PriceTypeID = 19 and PriceType = "Additional Off Closeout"
    Then I verify that Product Thumbnail PriceTypeID = 19 and PriceType = "Additional Off Closeout" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # *******************************          WITHOUT COLORWAY                 **********************************************************

  #@scenario_PriceType19_MemberProduct_withoutcolorway
  @ignore
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=19) in ALL modes for MEMBER product without Colorway Pricing
    Given I am on SearchResultsPage for "NEED INFO" in <Site_Mode> mode
    When I select a "member" product "WithoutColorWay" pricing of PriceTypeID = 19 and PriceType = "Additional Off Closeout"
    Then I verify that Product Thumbnail PriceTypeID = 19 and PriceType = "Additional Off Closeout" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  #@scenario_PriceType19_MasterProduct_withoutcolorway
  @ignore
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=19) in ALL modes for MASTER product without Colorway Pricing
    Given I am on SearchResultsPage for "NEED INFO" in <Site_Mode> mode
    When I select a "master" product "WithoutColorWay" pricing of PriceTypeID = 19 and PriceType = "Additional Off Closeout"
    Then I verify that Product Thumbnail PriceTypeID = 19 and PriceType = "Additional Off Closeout" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  #@scenario_PriceType19_StandardProduct_withoutcolorway
  @ignore
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=19) in ALL modes for STANDARD product without Colorway Pricing
    Given I am on SearchResultsPage for "NEED INFO" in <Site_Mode> mode
    When I select a "standard" product "WithoutColorWay" pricing of PriceTypeID = 19 and PriceType = "Additional Off Closeout"
    Then I verify that Product Thumbnail PriceTypeID = 19 and PriceType = "Additional Off Closeout" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |


 # ******************************* PRICE TYPE ID = 7 PRICE TYPE = Permanent Markdown     **********************************************************
 # *******************************                    WITH COLORWAY                      **********************************************************

  @scenario_PriceType7_MemberProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=7) in ALL modes for MEMBER product with Colorway Pricing
    Given I am on SearchResultsPage for "regis woven curtain 48 x 63 panel" in <Site_Mode> mode
    When I select a "member" product "WithColorWay" pricing of PriceTypeID = 7 and PriceType = "Permanent Markdown"
    Then I verify that Product Thumbnail PriceTypeID = 7 and PriceType = "Permanent Markdown" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType7_MasterProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=7) in ALL modes for MASTER product with Colorway Pricing
    Given I am on SearchResultsPage for "regis woven curtain panel collection" in <Site_Mode> mode
    When I select a "master" product "WithColorWay" pricing of PriceTypeID = 7 and PriceType = "Permanent Markdown"
    Then I verify that Product Thumbnail PriceTypeID = 7 and PriceType = "Permanent Markdown" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType7_StandardProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=7) in ALL modes for STANDARD product with Colorway Pricing
    Given I am on SearchResultsPage for "crocs bistro pro clogs" in <Site_Mode> mode
    When I select a "standard" product "WithColorWay" pricing of PriceTypeID = 7 and PriceType = "Permanent Markdown"
    Then I verify that Product Thumbnail PriceTypeID = 7 and PriceType = "Permanent Markdown" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # *******************************          WITHOUT COLORWAY                 **********************************************************

  @scenario_PriceType7_MemberProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=7) in ALL modes for MEMBER product without Colorway Pricing
    Given I am on SearchResultsPage for "lorraine pair of 82 x 84 pole top window panels" in <Site_Mode> mode
    When I select a "member" product "WithoutColorWay" pricing of PriceTypeID = 7 and PriceType = "Permanent Markdown"
    Then I verify that Product Thumbnail PriceTypeID = 7 and PriceType = "Permanent Markdown" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType7_MasterProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=7) in ALL modes for MASTER product without Colorway Pricing
    Given I am on SearchResultsPage for "croscill lorraine window collection" in <Site_Mode> mode
    When I select a "master" product "WithoutColorWay" pricing of PriceTypeID = 7 and PriceType = "Permanent Markdown"
    Then I verify that Product Thumbnail PriceTypeID = 7 and PriceType = "Permanent Markdown" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType7_StandardProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=7) in ALL modes for STANDARD product without Colorway Pricing
    Given I am on SearchResultsPage for "crocs classic lined clogs" in <Site_Mode> mode
    When I select a "standard" product "WithoutColorWay" pricing of PriceTypeID = 7 and PriceType = "Permanent Markdown"
    Then I verify that Product Thumbnail PriceTypeID = 7 and PriceType = "Permanent Markdown" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # ******************************* PRICE TYPE ID = 8 PRICE TYPE = Clearance Markdown    **********************************************************
 # *******************************                    WITH COLORWAY                      **********************************************************

  @scenario_PriceType8_MemberProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=8) in ALL modes for MEMBER product with Colorway Pricing
    Given I am on SearchResultsPage for "lichtenberg sarina curtain 48 x 84 panel" in <Site_Mode> mode
    When I select a "member" product "WithColorWay" pricing of PriceTypeID = 8 and PriceType = "Clearance Markdown"
    Then I verify that Product Thumbnail PriceTypeID = 8 and PriceType = "Clearance Markdown" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType8_MasterProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=8) in ALL modes for MASTER product with Colorway Pricing
    Given I am on SearchResultsPage for "lichtenberg sarina printed grommet curtain panel collection" in <Site_Mode> mode
    When I select a "master" product "WithColorWay" pricing of PriceTypeID = 8 and PriceType = "Clearance Markdown"
    Then I verify that Product Thumbnail PriceTypeID = 8 and PriceType = "Clearance Markdown" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType8_StandardProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=8) in ALL modes for STANDARD product with Colorway Pricing
    Given I am on SearchResultsPage for "crocs yukon mesa clogs" in <Site_Mode> mode
    When I select a "standard" product "WithColorWay" pricing of PriceTypeID = 8 and PriceType = "Clearance Markdown"
    Then I verify that Product Thumbnail PriceTypeID = 8 and PriceType = "Clearance Markdown" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # *******************************          WITHOUT COLORWAY                 **********************************************************

  @scenario_PriceType8_MemberProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=8) in ALL modes for MEMBER product without Colorway Pricing
    Given I am on SearchResultsPage for "room darkening woven curtain 50 x 63 panel" in <Site_Mode> mode
    When I select a "member" product "WithoutColorWay" pricing of PriceTypeID = 8 and PriceType = "Clearance Markdown"
    Then I verify that Product Thumbnail PriceTypeID = 8 and PriceType = "Clearance Markdown" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType8_MasterProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=8) in ALL modes for MASTER product without Colorway Pricing
    Given I am on SearchResultsPage for "room darkening woven curtain panel collection" in <Site_Mode> mode
    When I select a "master" product "WithoutColorWay" pricing of PriceTypeID = 8 and PriceType = "Clearance Markdown"
    Then I verify that Product Thumbnail PriceTypeID = 8 and PriceType = "Clearance Markdown" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType8_StandardProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=8) in ALL modes for STANDARD product without Colorway Pricing
    Given I am on SearchResultsPage for "crocs classic realtree xtra clog" in <Site_Mode> mode
    When I select a "standard" product "WithoutColorWay" pricing of PriceTypeID = 8 and PriceType = "Clearance Markdown"
    Then I verify that Product Thumbnail PriceTypeID = 8 and PriceType = "Clearance Markdown" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # *******************************   PRICE TYPE ID = 16 PRICE TYPE = Closeout             **********************************************************
 # *******************************                    WITH COLORWAY                       **********************************************************

  @scenario_PriceType16_MemberProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=16) in ALL modes for MEMBER product with Colorway Pricing
    Given I am on SearchResultsPage for "woodbury pair of 49 x 84 window panels" in <Site_Mode> mode
    When I select a "member" product "WithColorWay" pricing of PriceTypeID = 16 and PriceType = "Closeout"
    Then I verify that Product Thumbnail PriceTypeID = 16 and PriceType = "Closeout" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType16_MasterProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=16) in ALL modes for MASTER product with Colorway Pricing
    Given I am on SearchResultsPage for "woodbury window collection" in <Site_Mode> mode
    When I select a "master" product "WithColorWay" pricing of PriceTypeID = 16 and PriceType = "Closeout"
    Then I verify that Product Thumbnail PriceTypeID = 16 and PriceType = "Closeout" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType16_StandardProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=16) in ALL modes for STANDARD product with Colorway Pricing
    Given I am on SearchResultsPage for "rockport suede loafer style slippers" in <Site_Mode> mode
    When I select a "standard" product "WithColorWay" pricing of PriceTypeID = 16 and PriceType = "Closeout"
    Then I verify that Product Thumbnail PriceTypeID = 16 and PriceType = "Closeout" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # *******************************          WITHOUT COLORWAY                 **********************************************************

  @scenario_PriceType16_MemberProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=16) in ALL modes for MEMBER product without Colorway Pricing
    Given I am on SearchResultsPage for "room darkening woven curtain 54 x 63 panel" in <Site_Mode> mode
    When I select a "member" product "WithoutColorWay" pricing of PriceTypeID = 16 and PriceType = "Closeout"
    Then I verify that Product Thumbnail PriceTypeID = 16 and PriceType = "Closeout" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType16_MasterProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=19) in ALL modes for MASTER product without Colorway Pricing
    Given I am on SearchResultsPage for "room darkening woven curtain panel collection" in <Site_Mode> mode
    When I select a "master" product "WithoutColorWay" pricing of PriceTypeID = 16 and PriceType = "Closeout"
    Then I verify that Product Thumbnail PriceTypeID = 16 and PriceType = "Closeout" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType19_StandardProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=19) in ALL modes for STANDARD product without Colorway Pricing
    Given I am on SearchResultsPage for "crocs bistro clogs" in <Site_Mode> mode
    When I select a "standard" product "WithoutColorWay" pricing of PriceTypeID = 16 and PriceType = "Closeout"
    Then I verify that Product Thumbnail PriceTypeID = 16 and PriceType = "Closeout" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # ******************************* PRICE TYPE ID = 10 PRICE TYPE = Markdown Cancel    **********************************************************
 # *******************************                    WITH COLORWAY                   **********************************************************

  @scenario_PriceType10_MemberProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=10) in ALL modes for MEMBER product with Colorway Pricing
    Given I am on SearchResultsPage for "darien 56 x 84 panel" in <Site_Mode> mode
    When I select a "member" product "WithColorWay" pricing of PriceTypeID = 10 and PriceType = "Markdown Cancel"
    Then I verify that Product Thumbnail PriceTypeID = 10 and PriceType = "Markdown Cancel" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType10_MasterProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=10) in ALL modes for MASTER product with Colorway Pricing
    Given I am on SearchResultsPage for "darien panels collection" in <Site_Mode> mode
    When I select a "master" product "WithColorWay" pricing of PriceTypeID = 10 and PriceType = "Markdown Cancel"
    Then I verify that Product Thumbnail PriceTypeID = 10 and PriceType = "Markdown Cancel" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType10_StandardProduct_withcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=10) in ALL modes for STANDARD product with Colorway Pricing
    Given I am on SearchResultsPage for "faux suede clog slippers" in <Site_Mode> mode
    When I select a "standard" product "WithColorWay" pricing of PriceTypeID = 10 and PriceType = "Markdown Cancel"
    Then I verify that Product Thumbnail PriceTypeID = 10 and PriceType = "Markdown Cancel" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # *******************************          WITHOUT COLORWAY                 **********************************************************

  @scenario_PriceType10_MemberProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=10) in ALL modes for MEMBER product without Colorway Pricing
    Given I am on SearchResultsPage for "room darkening grommet 40 x 63 panel" in <Site_Mode> mode
    When I select a "member" product "WithoutColorWay" pricing of PriceTypeID = 10 and PriceType = "Markdown Cancel"
    Then I verify that Product Thumbnail PriceTypeID = 10 and PriceType = "Markdown Cancel" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType10_MasterProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=10) in ALL modes for MASTER product without Colorway Pricing
    Given I am on SearchResultsPage for "room darkening grommet" in <Site_Mode> mode
    When I select a "master" product "WithoutColorWay" pricing of PriceTypeID = 10 and PriceType = "Markdown Cancel"
    Then I verify that Product Thumbnail PriceTypeID = 10 and PriceType = "Markdown Cancel" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType10_StandardProduct_withoutcolorway
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=10) in ALL modes for STANDARD product without Colorway Pricing
    Given I am on SearchResultsPage for "club room terry slip-on slippers" in <Site_Mode> mode
    When I select a "standard" product "WithoutColorWay" pricing of PriceTypeID = 10 and PriceType = "Markdown Cancel"
    Then I verify that Product Thumbnail PriceTypeID = 10 and PriceType = "Markdown Cancel" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |
