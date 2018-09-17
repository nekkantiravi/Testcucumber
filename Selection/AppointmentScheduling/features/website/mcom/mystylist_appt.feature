Feature: Mystylist appointment

  @tag1
  Scenario Outline: As an appointment scheduling user within 50 mi radius I should see timetrade lightbox and book appointment
    Given I visit mystylist page
    When I click Book Now on mystylist page
    When  I accept location alert from timetrade on desktop
    When  I enter "<Show Locations Near>" to find a store for scheduling an appointment
    When  I select a "<Store Offering Appointment>" and click continue
    When  I Select a "<Program Type>"
    When  I Select an "<Appointment Type>"
    When I have selected a stylist date and time
    When I enter my contact information
    Then I verify confirmation page

    Examples:
      |Show Locations Near|Store Offering Appointment|Program Type |Appointment Type|
      | San Jose, CA  |A - Valley Fair\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Personal Stylist|Personal Styling|
      | San Jose, CA  |A - Valley Fair\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Personal Stylist|Wardrobe Upgrade |
      | San Jose, CA  |A - Valley Fair\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Wedding Registry|Complete Your Registry|
      | San Jose, CA   |A - Valley Fair\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Wedding Registry|Create Your Registry|
      | San Jose, CA   |A - Valley Fair\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Wedding Registry|Update Your Registry|
      | San Jose, CA   |A - Valley Fair\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Fine Jewelry|Fine Jewelry Consultation|
      | San Jose, CA   |A - Valley Fair\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Fine Jewelry|Wedding Ring Consultation|
      | San Jose, CA   |A - Valley Fair\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Beauty|Clinique|
      | San Jose, CA   |A - Valley Fair\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Beauty|Estee Lauder|
      | San Jose, CA   |A - Valley Fair\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Beauty|Lancome|
      | San Jose, CA   |A - Valley Fair\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Beauty|Shiseido|

    #  | San Francisco, CA  |A - Valley Fair2\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Personal Stylist|Personal Styling|
    #  | San Francisco, CA  |A - Valley Fair2\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Personal Stylist|Wardrobe Upgrade |
    #  | San Francisco, CA  |A - Valley Fair2\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Wedding Registry|Complete Your Registry|
    #  | San Francisco, CA   |A - Valley Fair2\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Wedding Registry|Create Your Registry|
    #  | San Francisco, CA   |A - Valley Fair2\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Wedding Registry|Update Your Registry|
    #  | San Francisco, CA   |A - Valley Fair2\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Fine Jewelry|Fine Jewelry Consultation|
    #  | San Francisco, CA   |A - Valley Fair2\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Fine Jewelry|Wedding Ring Consultation|
    #  | San Francisco, CA   |A - Valley Fair2\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Beauty|Clinique|
    #  | San Francisco, CA   |A - Valley Fair2\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Beauty|Estee Lauder|
    #  | San Francisco, CA   |A - Valley Fair2\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Beauty|Lancome|
    #  | San Francisco, CA   |A - Valley Fair2\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Beauty|Shiseido|

    #  | Walnut Creek, CA  |A - Valley Fair3\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Personal Stylist|Personal Styling|
    #  | Walnut Creek, CA  |A - Valley Fair3\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Personal Stylist|Wardrobe Upgrade |
    #  | Walnut Creek, CA  |A - Valley Fair3\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Wedding Registry|Complete Your Registry|
    #  | Walnut Creek, CA   |A - Valley Fair3\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Wedding Registry|Create Your Registry|
    #  | Walnut Creek, CA   |A - Valley Fair3\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Wedding Registry|Update Your Registry|
    #  | Walnut Creek, CA   |A - Valley Fair3\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Fine Jewelry|Fine Jewelry Consultation|
    #  | Walnut Creek, CA   |A - Valley Fair3\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Fine Jewelry|Wedding Ring Consultation|
    #  | Walnut Creek, CA   |A - Valley Fair3\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Beauty|Clinique|
    #  | Walnut Creek, CA   |A - Valley Fair3\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Beauty|Estee Lauder|
    #  | Walnut Creek, CA   |A - Valley Fair3\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Beauty|Lancome|
    #  | Walnut Creek, CA   |A - Valley Fair3\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Beauty|Shiseido|


  @tag1
  Scenario Outline: Verify header store drop down list An Appointment link
    Given I visit Macys home page within 50 mi radius from "<Store Offering Appointment>"
    When I open new browser tab
    Then I verify header dropdown Schedule An Appointment link
    Examples:
      |Show Locations Near|Store Offering Appointment|Program Type |Appointment Type|
      | San Jose, CA  |A - Valley Fair\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Personal Stylist|Personal Styling|
    #  | San Jose, CA  |A - Valley Fair2\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Personal Stylist|Personal Styling|
    #  | San Jose, CA  |A - Valley Fair3\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Personal Stylist|Personal Styling|

  @tag1
  Scenario Outline: Verify header store drop down list Find a store
    Given I visit Macys home page within 50 mi radius from "<Store Offering Appointment>"
    When I open new browser tab
    Then I verify header dropdown Find a store link
    Examples:
      |Show Locations Near|Store Offering Appointment|Program Type |Appointment Type|
      | San Francisco, CA  |A - Valley Fair\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Personal Stylist|Personal Styling|
    #  | San Francisco, CA  |A - Valley Fair2\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Personal Stylist|Personal Styling|
    #  | San Francisco, CA  |A - Valley Fair3\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Personal Stylist|Personal Styling|

  @tag1
  Scenario Outline: Verify header store drop down list Find An Event
    Given I visit Macys home page within 50 mi radius from "<Store Offering Appointment>"
    When I open new browser tab
    Then I verify header dropdown Find An Event link
    Examples:
      |Show Locations Near|Store Offering Appointment|Program Type |Appointment Type|
      | Walnut Creek, CA  |A - Valley Fair\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Personal Stylist|Personal Styling|
     # | Walnut Creek, CA  |A - Valley Fair2\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Personal Stylist|Personal Styling|
     # | Walnut Creek, CA  |A - Valley Fair3\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Personal Stylist|Personal Styling|

  @tag1
  Scenario Outline: Verify header store page banner
    Given I visit Macys home page within 50 mi radius from "<Store Offering Appointment>"
    When I open new browser tab
    Then I verify store page Schedule An Appointment link
    Examples:
      |Show Locations Near|Store Offering Appointment|Program Type |Appointment Type|
      | Walnut Creek, CA  |A - Valley Fair\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Personal Stylist|Personal Styling|
     # | Walnut Creek, CA  |A - Valley Fair2\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Personal Stylist|Personal Styling|
    #  | Walnut Creek, CA  |A - Valley Fair3\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Personal Stylist|Personal Styling|

  @tag1
  Scenario Outline: Verify header store page banner wedding registry
    Given I visit Macys home page within 50 mi radius from "<Store Offering Appointment>" and set cookie for wedding registry
    When I navigate to desktop wedding registry page
    When I verify the appointment banner on wedding registry page
    Examples:
      |Show Locations Near|Store Offering Appointment|Program Type |Appointment Type|
      | Walnut Creek, CA  |A - Valley Fair\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Personal Stylist|Personal Styling|
    # | Walnut Creek, CA  |A - Valley Fair2\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Personal Stylist|Personal Styling|
    #  | Walnut Creek, CA  |A - Valley Fair3\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050\n3.6 miles|Personal Stylist|Personal Styling|