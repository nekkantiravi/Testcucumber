   #Author: Stores Domain Checkout Team
   #Story: SDU-620 - Restricted Special Merch Table
   #Date Created: 06/13/2017
   #Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDU-620
Feature:  As an associate, I want to know when I cannot ring a UPC on uPOS for Take sales,
  so that I can redirect my transaction to the old system immediately,
  instead of figuring it out later into the transaction.

  @Macy's @Take
  Scenario Outline: Macy's - Try to add Special Merchandise Items in a Take Sale
    Given I am on "Macy's Sales Trans"
    When I select Take option
    And I add "<UPC>" item to the Checkout bag
    Then I can see the Restricted Item error
    And I close the Restricted Item error
    When I click on the bag icon
    Then I can see Checkout empty bag view

    Examples:
      |UPC                    |
#Apple Activation (Dept/Class 21/2) - Macy's Store 166
      | 492030560867            |
#Apple Care (Dept/Class 986/4) - Macy's Store 166
      | 885909474691           |
#Apple Product (Dept/Class 762/2) - Macy's Store 166
      | 888462322607           |
#Area Rugs (Dept/Class 911/1) - Macy's Store 166
      | 400084119146           |
#Backstage Final Sale (Dept/Class 89/26) - Macy's Store 166
      | 884884335904           |
#Bridge Jewelry (Dept/Class 232/10) - Macy's Store 166
      | 0716170049816          |
#Burberry (Dept/Class 924/9) - Macy's Store 166
      | 400822518873           |
#Carbonating Bottles (Dept/Class 186/36) - Macy's Store 166
      | 08949672623            |
#Condensed Fine Jewelry (Dept/Class 263/29) - Macy's Store 166
      | 34729014451            |
#Duplicate Receipt (Dept/Class 946/72) - Macy's Store 166
      | 400073756260            |
#Ear Piercing (Dept/Class 728/2) - Macy's Store 166
      | 20409000371           |
#Fashion Watches (Dept/Class 289/15) - Macy's Store 166
      | 86702355488           |
#Fine Jewelry (Dept/Class 262/72) - Macy's Store 166
      | 689439684434           |
#Finish Line (Dept/Class 930/1) - Macy's Store 166
      | 887047182735           |
#Jewelry Department (Dept/Class 759/10) - Macy's Store 166
      | 478000365908           |
#Leased Department (Dept/Class 985/5) - Macy's Store 166
      | 400818516104           |
#Leased Department - Duplicate Receipt (Dept/Class 912/11) - Macy's Store 166
      | 400084037228           |
#Leased Transaction (Dept/Class 942/2) - Macy's Store 166
      | 492018695956           |
#Leased Transaction - Duplicate Receipt (Dept/Class 920/61) - Macy's Store 166
      | 400122090529           |
#Luxury Watches (Dept/Class 277/10) - Macy's Store 166
      | 775924261362           |
#Optical Department (Dept/Class 240/9) - Macy's Store 166
      | 08672681428           |
#Refurbished Jewelry (Dept/Class 758/20) - Macy's Store 166
      | 400820813635           |
#Repair Department (Dept/Class 983/1) - Macy's Store 166
      | 400822287489           |
#Ruby (Dept/Class 264/1) - Macy's Store 166
      | 492000555817           |
#Silver Jewelry (Dept/Class 296/10) - Macy's Store 166
      | 606879000976           |
#Tech Watches (Dept/Class 281/4) - Macy's Store 166
      | 847634059246           |
#Trunk Show and Estate (Dept/Class 756/5) - Macy's Store 166
      | 400820782832           |
#WNM - Fine Jewelry (Dept/Class 985/2) - Macy's Store 166
      | 400818516074           |
#WNM - Fine Watches (Dept/Class 985/5) - Macy's Store 166
      | 400818516104           |
#WNM - Renewal/Pre-owned (Dept/Class 985/8) - Macy's Store 166
      | 400818909036           |
#worrynomore at POS (Dept/Class 974/2) - Macy's Store 166
      | 400084078184           |
#Flexible Merchandise A (Dept/Class 302/15) - Macy's Store 166
      | 85805049737            |
#Flexible Merchandise B (Dept/Class 357/11) - Macy's Store 166
      | 20714739362            |
#Flexible Merchandise C (Dept/Class 325/40) - Macy's Store 166
      | 400387018160            |
#Flexible Merchandise D (Dept/Class 904/90) - Macy's Store 166
      | 7009001110018            |
#Flexible Merchandise E (Dept/Class 906/2) - Macy's Store 166
      | 696458020013            |
#Flexible Merchandise F (Dept/Class 966/6) - Macy's Store 166
      | 70896405654            |
#Flexible Merchandise H (Dept/Class 958/15) - Macy's Store 166
      | 31290065653           |
#Flexible Merchandise I (Dept/Class 963/1) - Macy's Store 166
      | 400126248773           |
#Flexible Merchandise K (Dept/Class 964/1) - Macy's Store 166
      | 492000072017           |
#Flexible Merchandise M (Dept/Class 315/30) - Macy's Store 166
      | 826480650241           |
#Flexible Merchandise N (Dept/Class 316/18) - Macy's Store 166
      | 846524005219           |
#Flexible Merchandise O (Dept/Class 987/2) - Macy's Store 166
      | 100003302412           |
#Flexible Merchandise P (Dept/Class 991/20) - Macy's Store 166
      | 63913109130            |
#Flexible Merchandise Q (Dept/Class 501/63) - Macy's Store 166
      | 608381859066           |
#Flexible Merchandise R (Dept/class 317/61) - Macy's Store 166
      | 12036767000           |
#Flexible Merchandise S (Dept/Class 317/68) - Macy's Store 166
      | 12036096841           |
#Flexible Merchandise T (Dept/Class 411/79) - Macy's Store 166
      | 884733020203           |
#Flexible Merchandise V (Dept/Class 317/75) - Macy's Store 166
      | 12036491653            |


  @Macy's @Take
  Scenario Outline: Macy's - Try to add Special Merchandise Items directly in the bag
    Given I am on "Macy's Sales Trans"
    When I click on the bag icon
    Then I scan UPC "<UPC>" into the bag
    And I can see the Restricted Item error
    When I close the Restricted Item error
    Then I can see Checkout empty bag view

    Examples:
      |UPC                    |
#Apple Activation (Dept/Class 21/2) - Macy's Store 166
      | 492030560867            |
#Apple Care (Dept/Class 986/4) - Macy's Store 166
      | 885909474691           |
#Apple Product (Dept/Class 762/2) - Macy's Store 166
      | 888462322607           |
#Area Rugs (Dept/Class 911/1) - Macy's Store 166
      | 400084119146           |
#Backstage Final Sale (Dept/Class 89/26) - Macy's Store 166
      | 884884335904           |
#Bridge Jewelry (Dept/Class 232/10) - Macy's Store 166
      | 0716170049816          |
#Burberry (Dept/Class 924/9) - Macy's Store 166
      | 400822518873           |
#Carbonating Bottles (Dept/Class 186/36) - Macy's Store 166
      | 08949672623            |
#Condensed Fine Jewelry (Dept/Class 263/29) - Macy's Store 166
      | 34729014451            |
#Duplicate Receipt (Dept/Class 946/72) - Macy's Store 166
      | 400073756260            |
#Ear Piercing (Dept/Class 728/2) - Macy's Store 166
      | 20409000371           |
#Fashion Watches (Dept/Class 289/15) - Macy's Store 166
      | 86702355488           |
#Fine Jewelry (Dept/Class 262/72) - Macy's Store 166
      | 689439684434           |
#Finish Line (Dept/Class 930/1) - Macy's Store 166
      | 887047182735           |
#Jewelry Department (Dept/Class 759/10) - Macy's Store 166
      | 478000365908           |
#Leased Department (Dept/Class 985/5) - Macy's Store 166
      | 400818516104           |
#Leased Department - Duplicate Receipt (Dept/Class 912/11) - Macy's Store 166
      | 400084037228           |
#Leased Transaction (Dept/Class 942/2) - Macy's Store 166
      | 492018695956           |
#Leased Transaction - Duplicate Receipt (Dept/Class 920/61) - Macy's Store 166
      | 400122090529           |
#Luxury Watches (Dept/Class 277/10) - Macy's Store 166
      | 775924261362           |
#Optical Department (Dept/Class 240/9) - Macy's Store 166
      | 08672681428           |
#Refurbished Jewelry (Dept/Class 758/20) - Macy's Store 166
      | 400820813635           |
#Repair Department (Dept/Class 983/1) - Macy's Store 166
      | 400822287489           |
#Ruby (Dept/Class 264/1) - Macy's Store 166
      | 492000555817           |
#Silver Jewelry (Dept/Class 296/10) - Macy's Store 166
      | 606879000976           |
#Tech Watches (Dept/Class 281/4) - Macy's Store 166
      | 847634059246           |
#Trunk Show and Estate (Dept/Class 756/5) - Macy's Store 166
      | 400820782832           |
#WNM - Fine Jewelry (Dept/Class 985/2) - Macy's Store 166
      | 400818516074           |
#WNM - Fine Watches (Dept/Class 985/5) - Macy's Store 166
      | 400818516104           |
#WNM - Renewal/Pre-owned (Dept/Class 985/8) - Macy's Store 166
      | 400818909036           |
#worrynomore at POS (Dept/Class 974/2) - Macy's Store 166
      | 400084078184           |
#Flexible Merchandise A (Dept/Class 302/15) - Macy's Store 166
      | 85805049737            |
#Flexible Merchandise B (Dept/Class 357/11) - Macy's Store 166
      | 20714739362            |
#Flexible Merchandise C (Dept/Class 325/40) - Macy's Store 166
      | 400387018160            |
#Flexible Merchandise D (Dept/Class 904/90) - Macy's Store 166
      | 7009001110018            |
#Flexible Merchandise E (Dept/Class 906/2) - Macy's Store 166
      | 696458020013            |
#Flexible Merchandise F (Dept/Class 966/6) - Macy's Store 166
      | 70896405654            |
#Flexible Merchandise H (Dept/Class 958/15) - Macy's Store 166
      | 31290065653           |
#Flexible Merchandise I (Dept/Class 963/1) - Macy's Store 166
      | 400126248773           |
#Flexible Merchandise K (Dept/Class 964/1) - Macy's Store 166
      | 492000072017           |
#Flexible Merchandise M (Dept/Class 315/30) - Macy's Store 166
      | 826480650241           |
#Flexible Merchandise N (Dept/Class 316/18) - Macy's Store 166
      | 846524005219           |
#Flexible Merchandise O (Dept/Class 987/2) - Macy's Store 166
      | 100003302412           |
#Flexible Merchandise P (Dept/Class 991/20) - Macy's Store 166
      | 63913109130            |
#Flexible Merchandise Q (Dept/Class 501/63) - Macy's Store 166
      | 608381859066           |
#Flexible Merchandise R (Dept/class 317/61) - Macy's Store 166
      | 12036767000           |
#Flexible Merchandise S (Dept/Class 317/68) - Macy's Store 166
      | 12036096841           |
#Flexible Merchandise T (Dept/Class 411/79) - Macy's Store 166
      | 884733020203           |
#Flexible Merchandise V (Dept/Class 317/75) - Macy's Store 166
      | 12036491653            |

  @bloomingdale's @Take
  Scenario Outline: Bloomingdale's - Try to add Special Merchandise Items in a Take Sale
    Given I am on "Bloomingdale's Sales Trans"
    When I select Take option
    And I add "<UPC>" item to the Checkout bag
    Then I can see the Restricted Item error
    And I close the Restricted Item error
    When I click on the bag icon
    Then I can see Checkout empty bag view

    Examples:
      |UPC                    |
#Bloomingdale's Lamps (Dept/Class 664/20) - Bloomingdale's Store 22
      | 756808007672           |
#Dresses (Dept/Class 145/96) - Bloomingdale's Store 22
      | 884176579832           |
#Non-Refundable Deposit (Dept/Class 14/39) - Bloomingdale's Store 22
      | 498013110944           |
#Refundable Deposit (Dept/Class 14/38) - Bloomingdale's Store 22
      | 400013004390           |

  @Bloomingdale's @Take
  Scenario Outline: Bloomingdale's - Try to add Special Merchandise Items directly in the bag
    Given I am on "Bloomingdale's Sales Trans"
    When I click on the bag icon
    Then I scan UPC "<UPC>" into the bag
    And I can see the Restricted Item error
    When I close the Restricted Item error
    Then I can see Checkout empty bag view

    Examples:
      |UPC                    |
#Bloomingdale's Lamps (Dept/Class 664/20) - Bloomingdale's Store 22
      | 756808007672           |
#Dresses (Dept/Class 145/96) - Bloomingdale's Store 22
      | 884176579832           |
#Non-Refundable Deposit (Dept/Class 14/39) - Bloomingdale's Store 22
      | 498013110944           |
#Refundable Deposit (Dept/Class 14/38) - Bloomingdale's Store 22
      | 400013004390           |
