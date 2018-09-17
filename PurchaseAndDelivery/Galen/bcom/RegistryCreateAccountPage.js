load("CustomerData.js");
this.RegistryCaptureEmailPage = $page("Registry Capture Email Page", {
        existing_user_email:             "id: existingEmail",
        existing_user_password:          "id: existingPassword",
        new_user_email:                  "id: newUserEmail",
        new_user_password:               "id: newUserPassword",
        new_user_email_verify:           "id: verifyEmail",
        new_user_password_verify:        "id: verifyPassword",
        existing_user_continue_button:   "id: existingUserBtn",
        new_user_continue_button:        "id: newUserBtn"
});
var registryCaptureEmailPage = new RegistryCaptureEmailPage(driver);
registryCaptureEmailPage.waitForIt();
var email = getRandomEmail();
registryCaptureEmailPage.new_user_email.typeText(email);
registryCaptureEmailPage.new_user_email_verify.typeText(email);
registryCaptureEmailPage.new_user_password.typeText("pass123");
registryCaptureEmailPage.new_user_password_verify.typeText("pass123");
registryCaptureEmailPage.new_user_continue_button.click();

