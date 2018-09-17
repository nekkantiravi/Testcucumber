 #Author: Stores Domain Checkout Team
     #Story: SDU-549 - Checkout :: TECH: Enhance CRL validation in Barcode Library
     #Date Created: 05/25/2017
     #Date Signed Off:

 @domain_stores @project_checkout @story_SDU-549
 Feature: We need to enhance the Barcode library to actually validate a CRL
   The validation rules for crl is any all numeric 8 or 9 digit string is considered a returnLabelBarcode.

   @Macy's
   Scenario Outline: Macy's - Scanning an Invalid CRL
     Given I am on "Macy's Sales Trans"
     When I select Take option
     And I add an item to the Checkout bag
     Then I see the CRL Overlay
     When I scan "<crl_value>" in to the CRL Overlay
     Then I can see the Invalid CRL error message
     When I close the CRL Overlay
     And I call Cancel
     Then I can see the sales trans landing page

   Examples:
   | crl_value      |
   | 1234567        |
   | 1234567890     |
   | alphacrl       |
   | alphacrls      |
   | alphano1       |
   | alphanum1      |
   | 1234567&       |
   | \1234567/      |
   | $ρε¢ï@Ł™       |
   | $ρε¢ï@Ł™♠     |
   | 11111111       |
   | 222222222      |



#   table contains CRLs with:
#     7 digits
#     10 digits
#     8 alphabetic chars
#     9 alphabetic chars
#     8 alphanumeric chars
#     9 alphanumeric chars
#     7 digits plus special chars
#     8 digits plus special char
#     8 special chars
#     9 special chars