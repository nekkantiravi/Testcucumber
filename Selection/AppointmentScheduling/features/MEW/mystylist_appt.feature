Feature: Mystylist appointment

  @mew1
  Scenario: As an appointment scheduling user I should be able to see store locations
    Given I visit the mobile web site as a guest user
    When I set cookies for appointment scheduling
    When I navigate to stores screen
    Then  I should see make an appointment button and I click it
    And   I close location alert from timetrade
    And   I should enter location and select a store
    And   I select mystylist appointment
    And   I select mystylist appointment type
    And   I have selected a stylist date and time on mobile
    And   I enter my contact information on mobile
      |John|
      |Doe |
      |John.Doe@macys.com|
      |(650) 534-1111    |
    Then  I should see mystylist appointment confirmation on mobile
      |Location                       | Valley Fair\n2801 Stevens Creek Blvd\nSanta Clara, CA 95050     |
      |Program Type                   | Appointments                                                                                       |
      |Appointment Type               | Clinique                                                                                |
      |Contact Information            |John Doe                                                                                           |
      |                               |John.Doe@macys.com                                                                                       |
      |                               |(650) 534-1111                                                                                           |


  @mew2
  Scenario: verify appointment schedule link on wedding registry page registered user
    Given I visit the mobile web site as a registered user without add CC
    When I set cookies for appointment scheduling
    When I navigate to wedding registry page
    When I set cookies for appointment scheduling
    When I verify appointment link

  @mew3
  Scenario: verify appointment schedule link on wedding registry page unregistered user
    Given I visit the mobile web site as a guest user
    When I set cookies for appointment scheduling
    When I navigate to wedding registry page
    When I set cookies for appointment scheduling
    When I verify appointment link












    

  @confirmationPage @wip
    Scenario: Validate confirmation page
        Then  I should see mystylist appointment confirmation

  @tag1 @wip
    Scenario: As an appointment scheduling user above 50 mi radius I should not see timetrade lightbox
    Given I visit mystylist page above 50 mi radius
    When  I click Book Now on mystylist page
    Then  I should not see timetrade lightbox

  @tag2 @wip
  Scenario: As an appointment scheduling user within 50 mi radius I should see timetrade lightbox
    Given I visit mystylist page within 50 mi radius
    When  I click Book Now on mystylist page
    Then  I should see timetrade lightbox

  @wip
  Scenario: As a mystylist user I should be able to select a store
    Given I am on store locations offering all kinds of appointments
    When  I select a store offering mystylist appointment and click continue
    Then  I should see mystylist appointment

  @wip
  Scenario: As a mystylist user I should be able to select a stylist
    Given I have selected mystylist appointment
    When  I select a stylist
    Then  I should see available stylist appointments


  Scenario: As a mystylist user I should be able to schedule appointment
    Given I have selected a stylist date and time
    When  I enter my contact information
    Then  I should see mystylist appointment confirmation