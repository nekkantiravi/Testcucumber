Feature: Verify the existing ATB functionality is not affected by Bag Componentization

#Below Test cases, validates below confluence document	
#http://confluence5/display/CAPSA/Add+Item+to+Bag (Verifications needed on ATB overlay)
#http://confluence5/display/CAPSA/View+Bag (Verifications needed on shopping bag page)

@use_regression @domain_architecture @artifact_navapp @project_site_foundation @release_16F @feature_bag_rest_migration @priority_high @mode_domestic
Scenario Outline: Verify the ability to add a "normal" product to shopping bag 

Given I visit the web site as a "<user_type>" user in "<mode>" mode
When I navigate to PDP page of an "normal" product
Then I verify the product details of an "normal" product in PDP page
Then I add the product to bag
  And I verify the basic attributes of add to bag page or overlay for "normal" product
When I continue checkout to shopping bag page
Then I should see Shopping Bag Page
  And I verify the basic attributes of shopping bag page for "normal" product

Examples:
	| user_type || mode   |
	| guest 	|| site   |
	| guest 	|| iship  |
	| registered|| site   |
	| guest	    ||registry|
	| registered||registry|

@use_regression @domain_architecture @artifact_navapp @project_site_foundation @release_16F @feature_bag_rest_migration @priority_high @mode_domestic
Scenario Outline: Verify the ability to add a "multiple" products to shopping bag

Given I visit the web site as a "<user_type>" user in "<mode>" mode
When I navigate to PDP page of an "normal" product
Then I verify the product details of an "normal" product in PDP page
When I add the product to bag
Then I verify the basic attributes of add to bag page or overlay for "normal" product
When I continue checkout to shopping bag page
Then I should see Shopping Bag Page
  And I verify the basic attributes of shopping bag page for "normal" product
When I navigate to PDP page of an "normal" product
Then I verify the product details of an "normal" product in PDP page
When I add the product to bag
Then I verify the basic attributes of add to bag page or overlay for "normal" product
When I continue checkout to shopping bag page
Then I should see Shopping Bag Page
  And I verify the basic attributes of shopping bag page for "normal" product

Examples:
	| user_type || mode   |
	| guest 	|| site   |
	| guest 	|| iship  |
	| registered|| site   |
	|	guest	||registry|
	| registered||registry|  
  
@use_regression @domain_architecture @artifact_navapp @project_site_foundation @release_16F @feature_bag_rest_migration @priority_high @mode_domestic
Scenario Outline: Verify the ability to add a "PWP" product to shopping bag

Given I visit the web site as a "<user_type>" user in "<mode>" mode
When I navigate to PDP page of an "PWP" product
Then I verify the product details of an "PWP" product in PDP page
When I add the product to bag
Then I verify the basic attributes of add to bag page or overlay for "PWP" product
When I continue checkout to shopping bag page
Then I should see Shopping Bag Page
  And I verify the basic attributes of shopping bag page for "PWP" product
  
Examples:
	| user_type || mode   |
	| guest 	|| site   |
	| guest 	|| iship  |
	| registered|| site   |
	
@use_regression @domain_architecture @artifact_navapp @project_site_foundation @release_16F @feature_bag_rest_migration @priority_high @mode_domestic
Scenario Outline: Verify the ability to add a "GWP" product to shopping bag

Given I visit the web site as a "<user_type>" user in "<mode>" mode
When I navigate to PDP page of an "GWP" product
Then I verify the product details of an "GWP" product in PDP page
When I add the product to bag
Then I verify the basic attributes of add to bag page or overlay for "GWP" product
When I continue checkout to shopping bag page
Then I should see Shopping Bag Page
  And I verify the basic attributes of shopping bag page for "GWP" product

Examples:
	| user_type || mode   |
	| guest 	|| site   |
	| guest 	|| iship  |
	| registered|| site   |

@use_regression @domain_architecture @artifact_navapp @project_site_foundation @release_16F @feature_bag_rest_migration @priority_high @mode_domestic
Scenario Outline: Verify the ability to add a "BOPS" product to shopping bag

Given I visit the web site as a "<user_type>" user in "<mode>" mode
When I navigate to PDP page of an "BOPS" product
Then I verify the product details of an "BOPS" product in PDP page
When I add the product to bag
Then I verify the basic attributes of add to bag page or overlay for "BOPS" product
When I continue checkout to shopping bag page
Then I should see Shopping Bag Page
  And I verify the basic attributes of shopping bag page for "BOPS" product

Examples:
	| user_type || mode   |
	| guest 	|| site   |
	| registered|| site   |

@use_regression @domain_architecture @artifact_navapp @project_site_foundation @release_16F @feature_bag_rest_migration @priority_high @mode_domestic
Scenario: Verify the ability to add a "REGISTRY" product to shopping bag

Given I visit the web site as a "registered" user in "registry" mode
When I navigate to PDP page of an "REGISTRY" product
Then I verify the product details of an "REGISTRY" product in PDP page
When I add the product to a registry and continue shopping
Then I sign out from my current profile
When I navigate to find registry page
When I search for the existing couple's registry
Then I click view registry in GVR page
When I add a registry product to the shopping bag
Then I should see Shopping Bag Page
  And I verify the basic attributes of shopping bag page for "REGISTRY" product

@use_regression @domain_architecture @artifact_navapp @project_site_foundation @release_16F @feature_bag_rest_migration @priority_high @mode_domestic
Scenario Outline: Verify the ability to add a "VGC" product to shopping bag

Given I visit the web site as a "<user_type>" user in "<mode>" mode
When I navigate to PDP page of an "VGC" product
Then I verify the product details of an "VGC" product in PDP page
When I add the product to bag
Then I verify the basic attributes of add to bag page or overlay for "VGC" product
When I continue checkout to shopping bag page
Then I should see Shopping Bag Page
  And I verify the basic attributes of shopping bag page for "VGC" product

Examples:
	| user_type || mode   |
	| guest 	|| site   |
	| registered|| site   |

@use_regression @domain_architecture @artifact_navapp @project_site_foundation @release_16F @feature_bag_rest_migration @priority_high @mode_domestic
Scenario Outline: Verify the ability to add a "EGC" product to shopping bag

Given I visit the web site as a "<user_type>" user in "<mode>" mode
When I navigate to PDP page of an "EGC" product
  And I verify the product details of an "EGC" product in PDP page
Then I should see Shopping Bag Page
  And I verify the basic attributes of shopping bag page for "EGC" product
 
Examples:
	| user_type || mode   |
	| guest 	|| site   |
	| registered|| site   |

@use_regression @domain_architecture @artifact_navapp @project_site_foundation @release_16F @feature_bag_rest_migration @priority_high @mode_domestic
Scenario Outline: Verify the ability to add a two BOPS item each of different stores to shopping bag

Given I visit the web site as a "<user_type>" user in "<mode>" mode
When I navigate to PDP page of an "BOPS" product
Then I verify the product details of an "BOPS" product in PDP page
When I add the product to bag
Then I verify the basic attributes of add to bag page or overlay for "BOPS" product
  And I select continue shopping in ATB page or overlay
When I navigate to PDP page of an "BOPS" product
Then I verify the product details of an "BOPS" product in PDP page
When I add the product to bag
Then I verify the basic attributes of add to bag page or overlay for "BOPS" product
When I continue checkout to shopping bag page
Then I should see Shopping Bag Page
  And I verify the basic attributes of shopping bag page for "BOPS" product
  
Examples:
	| user_type || mode   |
	| guest 	|| site   |
	| registered|| site   |

@use_regression @domain_architecture @artifact_navapp @project_site_foundation @release_16F @feature_bag_rest_migration @priority_high @mode_domestic
Scenario: Verify the ability to add a "REGISTRY" product to shopping bag as a signed-in user

Given I visit the web site as a "registered" user in "registry" mode
When I navigate to PDP page of an "REGISTRY" product
Then I verify the product details of an "REGISTRY" product in PDP page
When I add the product to bag
Then I continue checkout to shopping bag page
When I navigate to find registry page
When I search for the existing couple's registry
Then I click view registry in GVR page
When I add a registry product to the shopping bag
Then I should see Shopping Bag Page
  And I verify the basic attributes of shopping bag page for "REGISTRY" product
  

#Below Test cases, validates below confluence document
#http://confluence5/display/CAPSA/Update+Item

@use_regression @domain_architecture @artifact_navapp @project_site_foundation @release_16F @feature_bag_rest_migration @priority_high @mode_domestic
Scenario Outline: Verify the ability to update "normal" type of product related options in shopping bag page

Given I visit the web site as a "<user_type>" user in "<mode>" mode
When I navigate to PDP page of an "normal" product
Then I verify the product details of an "normal" product in PDP page
When I add the product to bag
Then I continue checkout to shopping bag page
  And I should see Shopping Bag Page
When I update the "normal" product related options in shopping bag page
Then I verify the basic attributes of shopping bag page for "normal" product

Examples:
	| user_type || mode   |
	| guest 	|| site   |
	| guest 	|| iship  |
	| registered|| site   |
	|	guest	||registry|
	| registered||registry|

@use_regression @domain_architecture @artifact_navapp @project_site_foundation @release_16F @feature_bag_rest_migration @priority_high @mode_domestic
Scenario Outline: Verify the ability to update "PWP" type of product related options in shopping bag page

Given I visit the web site as a "<user_type>" user in "<mode>" mode
When I navigate to PDP page of an "PWP" product
Then I verify the product details of an "PWP" product in PDP page
When I add the product to bag
  And I continue checkout to shopping bag page
Then I should see Shopping Bag Page
When I update the "PWP" product related options in shopping bag page
Then I verify the basic attributes of shopping bag page for "PWP" product
	
Examples:
	| user_type || mode   |
	| guest 	|| site   |
	| guest 	|| iship  |
	| registered|| site   |

@use_regression @domain_architecture @artifact_navapp @project_site_foundation @release_16F @feature_bag_rest_migration @priority_high @mode_domestic
Scenario Outline: Verify the ability to update "GWP" type of product related options in shopping bag page

Given I visit the web site as a "<user_type>" user in "<mode>" mode
When I navigate to PDP page of an "GWP" product
Then I verify the product details of an "GWP" product in PDP page
When I add the product to bag
  And I continue checkout to shopping bag page
Then I should see Shopping Bag Page
When I update the "GWP" product related options in shopping bag page
Then I verify the basic attributes of shopping bag page for "GWP" product

Examples:
	| user_type || mode   |
	| guest 	|| site   |
	| guest 	|| iship  |
	| registered|| site   |

@use_regression @domain_architecture @artifact_navapp @project_site_foundation @release_16F @feature_bag_rest_migration @priority_high @mode_domestic
Scenario Outline: Verify the ability to update "BOPS" type of product related options in shopping bag page

Given I visit the web site as a "<user_type>" user in "<mode>" mode
When I navigate to PDP page of an "BOPS" product
Then I verify the product details of an "BOPS" product in PDP page
When I add the product to bag
  And I continue checkout to shopping bag page
Then I should see Shopping Bag Page
When I update the "BOPS" product related options in shopping bag page
Then I verify the basic attributes of shopping bag page for "BOPS" product
  
Examples:
	| user_type || mode   |
	| guest 	|| site   |
	| registered|| site   |
	
@use_regression @domain_architecture @artifact_navapp @project_site_foundation @release_16F @feature_bag_rest_migration @priority_high @mode_domestic
Scenario: Verify the ability to update "REGISTRY" type of product related options in shopping bag page

Given I visit the web site as a "registered" user in "registry" mode
When I navigate to PDP page of an "REGISTRY" product
Then I verify the product details of an "REGISTRY" product in PDP page
When I add the product to a registry and continue shopping
Then I sign out from my current profile
When I navigate to find registry page
When I search for the existing couple's registry
Then I click view registry in GVR page
When I add a registry product to the shopping bag
Then I should see Shopping Bag Page
When I update the "REGISTRY" product related options in shopping bag page
Then I verify the basic attributes of shopping bag page for "REGISTRY" product

@use_regression @domain_architecture @artifact_navapp @project_site_foundation @release_16F @feature_bag_rest_migration @priority_high @mode_domestic
Scenario Outline: Verify the ability to update "EGC" type of product related options in shopping bag page

Given I visit the web site as a "<user_type>" user in "<mode>" mode
When I navigate to PDP page of an "EGC" product
Then I verify the product details of an "EGC" product in PDP page
When I add the product to bag
Then I should see Shopping Bag Page
When I update the "EGC" product related options in shopping bag page
Then I verify the basic attributes of shopping bag page for "EGC" product
	
Examples:
	| user_type || mode   |
	| guest 	|| site   |
	| registered|| site   |

@use_regression @domain_architecture @artifact_navapp @project_site_foundation @release_16F @feature_bag_rest_migration @priority_high @mode_domestic
Scenario Outline: Verify the ability to update a two BOPS item each of different stores to shopping bag

Given I visit the web site as a "<user_type>" user in "<mode>" mode
When I navigate to PDP page of an "BOPS" product
Then I verify the product details of an "BOPS" product in PDP page
When I add the product to bag
Then I verify the basic attributes of add to bag page or overlay for "BOPS" product
  And I select continue shopping in ATB page or overlay
When I navigate to PDP page of an "BOPS" product
Then I verify the product details of an "BOPS" product in PDP page
When I add the product to bag
Then I verify the basic attributes of add to bag page or overlay for "BOPS" product
When I continue checkout to shopping bag page
Then I should see Shopping Bag Page
When I update the "BOPS" product related options in shopping bag page
  And I verify the basic attributes of shopping bag page for "BOPS" product

Examples:
	| user_type || mode   |
	| guest 	|| site   |
	| registered|| site   |

#Below Test cases, validates below confluence document
#http://confluence5/display/CAPSA/Remove+Item+from+bag

@use_regression @domain_architecture @artifact_navapp @project_site_foundation @release_16F @feature_bag_rest_migration @priority_high @mode_domestic
Scenario Outline: Verify the ability to remove "normal" type of products in shopping bag page as a guest user

Given I visit the web site as a "<user_type>" user in "<mode>" mode
When I navigate to PDP page of an "normal" product
Then I verify the product details of an "normal" product in PDP page
When I add the product to bag
Then I verify the basic attributes of add to bag page or overlay for "normal" product
When I continue checkout to shopping bag page
Then I should see Shopping Bag Page
  And I verify the basic attributes of shopping bag page for "normal" product
  And I verify the calculation details in shopping bag page
When I remove the "normal" product in shopping bag page
  And I verify the basic attributes of shopping bag page for "normal" product

Examples:
	| user_type || mode   |
	| guest 	|| site   |
	| registered|| site   |
	| guest 	|| iship  |
	|	guest	||registry|
	| registered||registry|
	
@use_regression @domain_architecture @artifact_navapp @project_site_foundation @release_16F @feature_bag_rest_migration @priority_high @mode_domestic
Scenario Outline: Verify the ability to Remove a BOPS item in shopping bag page

Given I visit the web site as a "<user_type>" user in "<mode>" mode
When I navigate to PDP page of an "BOPS" product
Then I verify the product details of an "BOPS" product in PDP page
When I add the product to bag
Then I verify the basic attributes of add to bag page or overlay for "BOPS" product
When I continue checkout to shopping bag page
Then I should see Shopping Bag Page
  And I verify the basic attributes of shopping bag page for "BOPS" product
  And I verify the calculation details in shopping bag page
When I remove the "BOPS" product in shopping bag page
Then I verify the basic attributes of shopping bag page for "BOPS" product	

Examples:
	| user_type || mode   |
	| guest 	|| site   |
	| registered|| site   |

@use_regression @domain_architecture @artifact_navapp @project_site_foundation @release_16F @feature_bag_rest_migration @priority_high @mode_domestic
Scenario Outline: Verify the ability to Remove a VGC Item in shopping bag page

Given I visit the web site as a "<user_type>" user in "<mode>" mode
When I navigate to PDP page of an "VGC" product
Then I verify the product details of an "VGC" product in PDP page
  And I add the product to bag
When I continue checkout to shopping bag page
Then I should see Shopping Bag Page
  And I verify the basic attributes of shopping bag page for "VGC" product
  And I verify the calculation details in shopping bag page
When I remove the "VGC" product in shopping bag page
Then I verify the basic attributes of shopping bag page for "VGC" product

Examples:
	| user_type || mode   |
	| guest 	|| site   |
	| registered|| site   |
	
@use_regression @domain_architecture @artifact_navapp @project_site_foundation @release_16F @feature_bag_rest_migration @priority_high @mode_domestic
Scenario Outline: Verify the ability to Remove a EGC Item in shopping bag page

Given I visit the web site as a "<user_type>" user in "<mode>" mode
When I navigate to PDP page of an "EGC" product
Then I verify the product details of an "EGC" product in PDP page
When I add the product to bag
  And I should see Shopping Bag Page
  And I verify the basic attributes of shopping bag page for "EGC" product
  And I verify the calculation details in shopping bag page
Then I remove the "EGC" product in shopping bag page
Then I verify the basic attributes of shopping bag page for "EGC" product

Examples:
	| user_type || mode   |
	| guest 	|| site   |
	| registered|| site   |
	
@use_regression @domain_architecture @artifact_navapp @project_site_foundation @release_16F @feature_bag_rest_migration @priority_high @mode_domestic
Scenario Outline: Verify the ability to Remove a PWP Item in shopping bag page

Given I visit the web site as a "<user_type>" user in "<mode>" mode
When I navigate to PDP page of an "PWP" product
Then I verify the product details of an "PWP" product in PDP page
When I add the product to bag
Then I verify the basic attributes of add to bag page or overlay for "PWP" product
When I continue checkout to shopping bag page
Then I should see Shopping Bag Page
  And I verify the basic attributes of shopping bag page for "PWP" product
  And I verify the calculation details in shopping bag page
When I remove the "PWP" product in shopping bag page
Then I verify the basic attributes of shopping bag page for "PWP" product

Examples:
	| user_type || mode   |
	| guest 	|| site   |
	| registered|| site   |
	| guest 	|| iship  |
	
@use_regression @domain_architecture @artifact_navapp @project_site_foundation @release_16F @feature_bag_rest_migration @priority_high @mode_domestic
Scenario Outline: Verify the ability to Remove a Gift (GWP) Item in shopping bag page

Given I visit the web site as a "<user_type>" user in "<mode>" mode
When I navigate to PDP page of an "GWP" product
Then I verify the product details of an "GWP" product in PDP page
When I add the product to bag
Then I verify the basic attributes of add to bag page or overlay for "GWP" product
When I continue checkout to shopping bag page
Then I should see Shopping Bag Page
  And I verify the basic attributes of shopping bag page for "GWP" product
  And I verify the calculation details in shopping bag page
When I remove the "GWP" product in shopping bag page
Then I verify the basic attributes of shopping bag page for "GWP" product

Examples:
	| user_type || mode   |
	| guest 	|| site   |
	| registered|| site   |
	| guest 	|| iship  |

@use_regression @domain_architecture @artifact_navapp @project_site_foundation @release_16F @feature_bag_rest_migration @priority_high @mode_domestic
Scenario Outline: Verify the ability to Remove a REGISTRY Item in shopping bag page

Given I visit the web site as a "<user_type>" user in "<mode>" mode
When I navigate to PDP page of an "REGISTRY" product
Then I verify the product details of an "REGISTRY" product in PDP page
When I add the product to bag
Then I verify the basic attributes of add to bag page or overlay for "REGISTRY" product
When I continue checkout to shopping bag page
Then I should see Shopping Bag Page
  And I verify the basic attributes of shopping bag page for "REGISTRY" product
  And I verify the calculation details in shopping bag page
When I remove the "REGISTRY" product in shopping bag page
Then I verify the basic attributes of shopping bag page for "REGISTRY" product

Examples:
	| user_type || mode   |
	|   guest   ||registry|
	| registered||registry|

@use_regression @domain_architecture @artifact_navapp @project_site_foundation @release_16F @feature_bag_rest_migration @priority_high @mode_domestic
Scenario: Verify the ability to add/view, update and remove a "normal" product from Wishlist page to shopping bag as a guest user

Given I visit the web site as a guest user
When I navigate to PDP page of an "normal" product
Then I verify the product details of an "normal" product in PDP page
When I add the product to wishlist
Then I select wishlist link on the wishlist overlay in PDP page
When I add product to my bag from wishlist page and checkout shopping
Then I update the "normal" product related options in shopping bag page
When I remove the "normal" product in shopping bag page
Then I verify the basic attributes of shopping bag page for "normal" product

@use_regression @domain_architecture @artifact_navapp @project_site_foundation @release_16F @feature_bag_rest_migration @priority_high @mode_domestic
Scenario: Verify the ability to add/view, update and remove a "normal" product from Wishlist page to shopping bag as a registered user

Given I visit the web site as a registered user
When I navigate to PDP page of an "normal" product
Then I verify the product details of an "normal" product in PDP page
When I add the product to wishlist
Then I select wishlist link on the wishlist overlay in PDP page
When I add product to my bag from wishlist page and checkout shopping
Then I update the "normal" product related options in shopping bag page
When I remove the "normal" product in shopping bag page
Then I verify the basic attributes of shopping bag page for "normal" product
  
@use_regression @domain_architecture @artifact_navapp @project_site_foundation @release_16F @feature_bag_rest_migration @priority_high @mode_domestic
Scenario: Verify the ability to add/view, update and remove a "random" product from Wishlist page to shopping bag after creating a wishlist associated to a registered user

Given I visit the web site as a registered user
When I select wishlist link in header
Then I should see wishlist landing page as a registered user
  And I create a list "Wishlist1" from wishlist page
When I navigate to PDP page of an "normal" product
Then I verify the product details of an "normal" product in PDP page
  And I add the product to wishlist
  And I select wishlist link on the wishlist overlay in PDP page
  And I add product to my bag from wishlist page and checkout shopping
Then I update the "normal" product related options in shopping bag page
When I remove the "normal" product in shopping bag page
Then I verify the basic attributes of shopping bag page for "normal" product

@use_regression @domain_architecture @artifact_navapp @project_site_foundation @release_16F @feature_bag_rest_migration @priority_high @mode_domestic
Scenario: Verify the merge bag scenario for Checkout user sign in when "normal" product is in bag

Given I visit the web site as a "guest" user in "site" mode
When I create a new profile with given data
Then I navigate to PDP page of an "normal" product
When I add the product to bag
Then I close and reopen the browser
  And I visit the web site as a "guest" user in "site" mode
When I navigate to PDP page of an "normal" product
Then I add the product to bag
  And I continue checkout to shopping bag page
When I sign in during checkout for merge bag
Then I verify the functionality of merge bag

@use_regression @domain_architecture @artifact_navapp @project_site_foundation @release_16F @feature_bag_rest_migration @priority_high @mode_domestic
Scenario: Verify the merge bag scenario for Checkout user sign in when "PWP" product is in bag

Given I visit the web site as a "guest" user in "site" mode
When I create a new profile with given data
Then I navigate to PDP page of an "PWP" product
When I add the product to bag
Then I close and reopen the browser
  And I visit the web site as a "guest" user in "site" mode
When I navigate to PDP page of an "PWP" product
Then I add the product to bag
  And I continue checkout to shopping bag page
When I sign in during checkout for merge bag
Then I verify the functionality of merge bag

@use_regression @domain_architecture @artifact_navapp @project_site_foundation @release_16F @feature_bag_rest_migration @priority_high @mode_domestic
Scenario: Verify the merge bag scenario for Checkout user sign in when "GWP" product is in bag

Given I visit the web site as a "guest" user in "site" mode
When I create a new profile with given data
Then I navigate to PDP page of an "GWP" product
When I add the product to bag
Then I close and reopen the browser
  And I visit the web site as a "guest" user in "site" mode
When I navigate to PDP page of an "GWP" product
Then I add the product to bag
  And I continue checkout to shopping bag page
When I sign in during checkout for merge bag
Then I verify the functionality of merge bag

@use_regression @domain_architecture @artifact_navapp @project_site_foundation @release_16F @feature_bag_rest_migration @priority_high @mode_domestic
Scenario: Verify the merge bag scenario for Checkout user sign in when "BOPS" product is in bag

Given I visit the web site as a "guest" user in "site" mode
When I create a new profile with given data
Then I navigate to PDP page of an "BOPS" product
When I add the product to bag
Then I close and reopen the browser
  And I visit the web site as a "guest" user in "site" mode
When I navigate to PDP page of an "BOPS" product
Then I add the product to bag
  And I continue checkout to shopping bag page
When I sign in during checkout for merge bag
Then I verify the functionality of merge bag

@use_regression @domain_architecture @artifact_navapp @project_site_foundation @release_16F @feature_bag_rest_migration @priority_high @mode_domestic
Scenario: Verify the merge bag scenario for Checkout user sign in when "EGC" product is in bag

Given I visit the web site as a "guest" user in "site" mode
When I create a new profile with given data
Then I navigate to PDP page of an "EGC" product
  And I verify the product details of an "EGC" product in PDP page
When I add the product to bag
Then I close and reopen the browser
  And I visit the web site as a "guest" user in "site" mode
When I navigate to PDP page of an "EGC" product
  And I verify the product details of an "EGC" product in PDP page
Then I add the product to bag
When I sign in during checkout for merge bag
Then I verify the functionality of merge bag

@use_regression @domain_architecture @artifact_navapp @project_site_foundation @release_16F @feature_bag_rest_migration @priority_high @mode_domestic
Scenario: Verify the merge bag scenario for Checkout user sign in when "VGC" product is in bag

Given I visit the web site as a "guest" user in "site" mode
When I create a new profile with given data
Then I navigate to PDP page of an "VGC" product
  And I verify the product details of an "VGC" product in PDP page
When I add the product to bag
Then I close and reopen the browser
  And I visit the web site as a "guest" user in "site" mode
When I navigate to PDP page of an "VGC" product
  And I verify the product details of an "VGC" product in PDP page
Then I add the product to bag
  And I continue checkout to shopping bag page
When I sign in during checkout for merge bag
Then I verify the functionality of merge bag

@use_regression @domain_architecture @artifact_navapp @project_site_foundation @release_16F @feature_bag_rest_migration @priority_high @mode_domestic
Scenario: Verify the merge bag scenario for Checkout user sign in when "BOPS with different store" product is in bag

Given I visit the web site as a "guest" user in "site" mode
When I create a new profile with given data
Then I navigate to PDP page of an "BOPS" product
When I add the product to bag
Then I close and reopen the browser
  And I visit the web site as a "guest" user in "site" mode
When I navigate to PDP page of an "BOPS" product
Then I add the product to bag
  And I continue checkout to shopping bag page
When I sign in during checkout for merge bag
Then I verify the functionality of merge bag

@use_regression @domain_architecture @artifact_navapp @project_site_foundation @release_16F @feature_bag_rest_migration @priority_high @mode_domestic
Scenario: Verify the merge bag scenario for Checkout user sign in when "REGISTRY" product is in bag

Given I visit the web site as a "guest" user in "registry" mode
When I create a new profile with given data
Then I navigate to PDP page of an "REGSITRY" product
When I add the product to bag
Then I close and reopen the browser
  And I visit the web site as a "guest" user in "registry" mode
When I navigate to PDP page of an "REGISTRY" product
Then I add the product to bag
  And I continue checkout to shopping bag page
When I sign in during checkout for merge bag
Then I verify the functionality of merge bag

@use_regression @domain_architecture @artifact_navapp @project_site_foundation @release_16F @feature_bag_rest_migration @priority_high @mode_domestic
Scenario: verify the merge bag scenario for Direct Signed in user when "normal" product is in bag

Given I visit the web site as a "guest" user in "site" mode
When I create a new profile with given data
Then I navigate to PDP page of an "normal" product
When I add the product to bag
  And I close and reopen browser
  And I visit the web site as a "guest" user in "site" mode
Then I sign in to my existing profile
When I navigate to PDP page of an "normal" product
Then I add the product to bag
  And I continue checkout to shopping bag page
  And I verify the functionality of merge bag

@use_regression @domain_architecture @artifact_navapp @project_site_foundation @release_16F @feature_bag_rest_migration @priority_high @mode_domestic
Scenario: verify the merge bag scenario for Direct Signed in user when "PWP" product is in bag

Given I visit the web site as a "guest" user in "site" mode
When I create a new profile with given data
Then I navigate to PDP page of an "PWP" product
When I add the product to bag
  And I close and reopen the browser
  And I visit the web site as a "guest" user in "site" mode
Then I sign in to my existing profile
When I navigate to PDP page of an "PWP" product
Then I add the product to bag
  And I continue checkout to shopping bag page
  And I verify the functionality of merge bag

@use_regression @domain_architecture @artifact_navapp @project_site_foundation @release_16F @feature_bag_rest_migration @priority_high @mode_domestic
Scenario: verify the merge bag scenario for Direct Signed in user when "GWP" product is in bag

Given I visit the web site as a "guest" user in "site" mode
When I create a new profile with given data
Then I navigate to PDP page of an "GWP" product
When I add the product to bag
  And I close and reopen the browser
  And I visit the web site as a "guest" user in "site" mode
Then I sign in to my existing profile
When I navigate to PDP page of an "GWP" product
Then I add the product to bag
  And I continue checkout to shopping bag page
  And I verify the functionality of merge bag

@use_regression @domain_architecture @artifact_navapp @project_site_foundation @release_16F @feature_bag_rest_migration @priority_high @mode_domestic
Scenario: verify the merge bag scenario for Direct Signed in user when "BOPS" product is in bag

Given I visit the web site as a "guest" user in "site" mode
When I create a new profile with given data
Then I navigate to PDP page of an "BOPS" product
When I add the product to bag
  And I close and reopen the browser
  And I visit the web site as a "guest" user in "site" mode
Then I sign in to my existing profile
When I navigate to PDP page of an "BOPS" product
Then I add the product to bag
  And I continue checkout to shopping bag page
  And I verify the functionality of merge bag

@use_regression @domain_architecture @artifact_navapp @project_site_foundation @release_16F @feature_bag_rest_migration @priority_high @mode_domestic
Scenario: verify the merge bag scenario for Direct Signed in user when "EGC" product is in bag

Given I visit the web site as a "guest" user in "site" mode
When I create a new profile with given data
Then I navigate to PDP page of an "EGC" product
  And I verify the product details of an "EGC" product in PDP page
When I add the product to bag
  And I close and reopen the browser
  And I visit the web site as a "guest" user in "site" mode
Then I sign in to my existing profile
When I navigate to PDP page of an "EGC" product
  And I verify the product details of an "EGC" product in PDP page
Then I add the product to bag
  And I continue checkout to shopping bag page
  And I verify the functionality of merge bag

@use_regression @domain_architecture @artifact_navapp @project_site_foundation @release_16F @feature_bag_rest_migration @priority_high @mode_domestic
Scenario: verify the merge bag scenario for Direct Signed in user when "VGC" product is in bag

Given I visit the web site as a "guest" user in "site" mode
When I create a new profile with given data
Then I navigate to PDP page of an "VGC" product
  And I verify the product details of an "VGC" product in PDP page
When I add the product to bag
  And I close and reopen the browser
  And I visit the web site as a "guest" user in "site" mode
Then I sign in to my existing profile
When I navigate to PDP page of an "VGC" product
  And I verify the product details of an "VGC" product in PDP page
Then I add the product to bag
  And I continue checkout to shopping bag page
  And I verify the functionality of merge bag

@use_regression @domain_architecture @artifact_navapp @project_site_foundation @release_16F @feature_bag_rest_migration @priority_high @mode_domestic
Scenario: verify the merge bag scenario for Direct Signed in user when "BOPS with different store" product is in bag

Given I visit the web site as a "guest" user in "site" mode
When I create a new profile with given data
Then I navigate to PDP page of an "BOPS" product
When I add the product to bag
  And I close and reopen the browser
  And I visit the web site as a "guest" user in "site" mode
Then I sign in to my existing profile
When I navigate to PDP page of an "BOPS" product
Then I add the product to bag
  And I continue checkout to shopping bag page
  And I verify the functionality of merge bag

@use_regression @domain_architecture @artifact_navapp @project_site_foundation @release_16F @feature_bag_rest_migration @priority_high @mode_domestic
Scenario: verify the merge bag scenario for Direct Signed in user when "REGISTRY" product is in bag

Given I visit the web site as a "guest" user in "registry" mode
When I create a new profile with given data
Then I navigate to PDP page of an "REGISTRY" product
When I add the product to bag
  And I close and reopen the browser
  And I visit the web site as a "guest" user in "registry" mode
Then I sign in to my existing profile
When I navigate to PDP page of an "REGISTRY" product
Then I add the product to bag
  And I continue checkout to shopping bag page
  And I verify the functionality of merge bag