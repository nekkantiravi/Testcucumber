load("CustomerData.js");
this.RegistryCaptureEmailPage = $page("Registry Capture Email Page", {
        existing_user_email:             "xpath: //div[@class='innerLeft gainlayout']/fieldset/div[1]/input[@id='email']",
        existing_user_password:          "xpath: //div[@class='innerLeft gainlayout']/fieldset/div[2]/input[@id='password']",
        new_user_email:                  "xpath: //fieldset[@id='captureEmailFields']/div[1]/input[@id='email']",
        new_user_password:               "xpath: //fieldset[@id='captureEmailFields']/div[3]/div[1]/input[@id='password']",
        new_user_email_verify:           "id: verifyEmail",
        new_user_password_verify:        "id: verifyPassword",
        existing_user_continue_button:   "xpath: //div[@id='regLeft']/input[@id='noProfileSubmitImg']",
        new_user_continue_button:        "xpath: //div[@id='regRight']/input[@id='noProfileSubmitImg']"
});
var registryCaptureEmailPage = new RegistryCaptureEmailPage(driver);
registryCaptureEmailPage.waitForIt();
var email = getRandomEmail();
registryCaptureEmailPage.new_user_email.typeText(email);
registryCaptureEmailPage.new_user_email_verify.typeText(email);
registryCaptureEmailPage.new_user_password.typeText("pass123");
registryCaptureEmailPage.new_user_password_verify.typeText("pass123");
registryCaptureEmailPage.new_user_continue_button.click();

