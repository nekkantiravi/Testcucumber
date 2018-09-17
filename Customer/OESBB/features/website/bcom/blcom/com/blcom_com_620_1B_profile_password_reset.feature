Feature: Verify BCOM 620 profile password reset email content

  @regression @blcom_com_620_1B_profile_password_reset @bat
  Scenario: Verify Email content for template blcom_com_620_1B_profile_password_template
    Given i trigger "blcom_com_620_1B_profile_password_reset" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    And i should see preheader:
      """
      Creating a new password is easy.
      """
    Then i should see static contents for com template:
      """
      We received a request to change the password for the bloomingdales.com profile associated with this email address. To reset your password, click this <link>link<link> (valid for 4 hours). If you didn't request a password reset, please let us know immediately by following the <link>link<link>. Link not working? Please contact us immediately at 1-800-777-0000. We're available 24 hours, 7 days a week or email at customerservice@bloomingdales.com. 
      """
    And i should see firstname
    And i should see bloomingdales logo
    And i should see freeshipping image

  @optional
  Scenario: Verify optional fields empty scenario for template blcom_com_620_1B_profile_password_template
    Given i trigger "blcom_com_620_1B_profile_password_reset_optional" input through csp testemail
    When i navigate to view the email page
    Then i should see bloomingdales logo