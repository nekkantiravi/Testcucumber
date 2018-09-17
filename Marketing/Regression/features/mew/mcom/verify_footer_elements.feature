Feature: Footer Sign in

  @dsv_mew_sev2 @domain_marketing
  Scenario: Footer appears on Home Page
    Given I visit the mobile web site as a guest user
    Then I should see the footer elements displayed at the bottom of the page
      | text              | element                |
      | Footer            | footer                 |
      | Signup            | goto_email_text_signup |
      | Privacy Practices | goto_privacy           |
      | Customer Service  | goto_contact_us        |
      | Desktop Version   | goto_full_site         |
      | Find A Store      | find_a_store           |
      | Pinterest         | goto_pinterest         |
      | Facebook          | goto_facebook          |
      | Twitter           | goto_twitter           |
      | Instagram         | goto_instagram         |
      | Youtube           | goto_youtube           |
      | Macys Blog        | goto_macys_blog        |
      | Copyright         | trademark              |