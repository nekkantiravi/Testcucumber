/**
 * Created by mpamujula on 11-03-2016.
 */
load("CustomerData.js");
this.HeaderPanel = $page("Home Page", {
    goto_my_account:            "id: href_myAccountHeader"
});
this.SignInPage = $page("Sign In Page", {
    create_profile:            "xpath: //*[@id='createProfileContainer']",
    email:                     "id: email",
    password:                  "id: password",
    sign_in_button:            "id: signInBtn"
});

this.CreateProfilePage = $page("Create Profile Page", {
    first_name:            "id: firstName",
    last_name:             "id: lastName",
    address_line_1:        "id: addressLine1",
    address_line_2:        "id: addressLine2",
    address_city:          "id: city",
    address_state:         "id: state",
    address_zip_code:      "id: zipcode",
    email:                 "id: email",
    email_verify:          "id: createConfirmEmail",
    password:              "id: password",
    password_verify:       "id: confirmPassword",
    dob_month:             "id: month",
    dob_day:               "id: date",
    dob_year:              "id: year",
    security_question:     "id: SecurityQna",
    security_answer:       "id: securityAns",
    gender:                "id: gender",
    create_profile_button: "id: createProfileBtn"
});
this.LeftNavPanel = $page("Account Left Nav Panel", {
    my_profile:            "xpath: //ul[@class='side-nav']/li/a[contains(@href, 'my_profile')]",
    my_address:            "xpath: //ul[@class='side-nav']/li/a[contains(@href, 'my_address_book')]",
    my_wallet:             "xpath: //ul[@class='side-nav']/li/a[contains(@href, 'my_wallet')]"
});

this.UpdateProfilePage = $page("Update Profile Page", {
    first_name:            "id: firstName"
});

this.AddAddressPage = $page("Add Address Page", {
    first_name:            "id: firstName"
});

this.MyWalletPage = $page("My Wallet Page", {
    add_card_button:            "id: addCreditCard"
});

this.AddCardOverlay = $page("Add Card Overlay", {
    first_name:            "id: creditCardFirstName"
});

var headerPanel = new HeaderPanel(driver);
headerPanel.waitForIt();
headerPanel.goto_my_account.click();
signInPage = new SignInPage(driver);
signInPage.waitForIt();
//signInPage.email.typeText("uslmcom1@gmail.com");
//signInPage.password.typeText("pass123");
//signInPage.sign_in_button.click();
signInPage.create_profile.click();
var createProfile = new CreateProfilePage(driver);
createProfile.waitForIt();
// create profile
profileData = generateProfileData()
createProfile.first_name.typeText(profileData["first_name"]);
createProfile.last_name.typeText(profileData["last_name"]);
createProfile.address_line_1.typeText(profileData["address_line_1"]);
createProfile.address_line_2.typeText(profileData["address_line_2"]);
createProfile.address_city.typeText(profileData["address_city"]);
createProfile.address_state.typeText(profileData["address_state"]);
createProfile.address_zip_code.typeText(profileData["address_zip_code"]);
createProfile.dob_month.typeText(profileData["dob_month"]);
createProfile.dob_day.typeText(profileData["dob_date"]);
createProfile.dob_year.typeText(profileData["dob_year"]);
createProfile.gender.typeText(profileData["gender"]);
createProfile.email.typeText(profileData["email"]);
createProfile.email_verify.typeText(profileData["verify_email"]);
createProfile.password.typeText(profileData["password"]);
createProfile.password_verify.typeText(profileData["verify_password"]);
createProfile.security_question.typeText(profileData["security_question"]);
createProfile.security_answer.typeText(profileData["security_answer"]);
createProfile.create_profile_button.click();
leftNavPanel = new LeftNavPanel(driver);
leftNavPanel.waitForIt();
leftNavPanel.my_profile.click();
updateProfilePage = new UpdateProfilePage(driver);
updateProfilePage.waitForIt();
