/**
 * Created by mpamujula on 11-03-2016.
 */
load("CustomerData.js");
this.HeaderPanel = $page("Home Page", {
    goto_my_account:            "id: bl_nav_myAcct"
});
this.SignInPage = $page("Sign In Page", {
    create_profile:            "id: signUpBtn",
    email:                     "id: email",
    password:                  "id: password",
    sign_in_button:            "id: accountSignInBtn"
});
this.CreateProfilePage = $page("Create Profile Page", {
    first_name:            "id: profile.profileAddress.firstName",
    last_name:             "id: profile.profileAddress.lastName",
    address_line_1:        "id: profile.profileAddress.address.addressLine1",
    address_line_2:        "id: profile.profileAddress.address.addressLine2",
    address_city:          "id: profile.profileAddress.address.city",
    address_state:         "id: profile.profileAddress.address.state",
    address_zip_code:      "id: profile.profileAddress.address.postalCode",
    email:                 "id: profile.email",
    email_verify:          "id: profile.confirmEmail",
    password:              "id: profile.password",
    password_verify:       "id: profile.confirmPassword",
    dob_month:             "id: profile.month",
    dob_day:               "id: profile.date",
    dob_year:              "id: profile.year",
    security_question:     "id: profile.securityQuestion",
    security_answer:       "id: profile.securityAnswer",
    gender:                "id: gender",
    create_profile_button: "id: createProfileBtn"
});

this.LeftNavPanel = $page("Account Left Nav Panel", {
    my_profile:            "xpath: //ul[@class='side-nav']/li/a[contains(@href, 'my_profile')]",
    my_address:            "xpath: //ul[@class='side-nav']/li/a[contains(@href, 'my_address_book')]",
    my_wallet:             "xpath: //ul[@class='side-nav']/li/a[contains(@href, 'my_wallet')]"
});
this.LeftNavPanel = $page("Account Left Nav Panel", {
    my_profile:            "id: myAccount_myProfileLink",
    my_address:            "id: myAccount_myAddressBookLink",
    my_wallet:             "id: myAccount_myWalletLink"
});

this.UpdateProfilePage = $page("Update Profile Page", {
    first_name:            "id: profile.profileAddress.firstName"
});

this.AddAddressPage = $page("Add Address Page", {
    first_name:            "id: shippingContact.firstName"
});

this.MyWalletPage = $page("My Wallet Page", {
    add_card_button:            "id: wlt_addCardButton"
});

this.AddCardOverlay = $page("Add Card Overlay", {
    first_name:            "id: billingAddress.firstName"
});

//signInPage = new SignInPage(driver);
//signInPage.waitForIt();
//signInPage.email.typeText("nisumtech1@gmail.com");
//signInPage.password.typeText("nisum123");
//signInPage.sign_in_button.click();

var headerPanel = new HeaderPanel(driver);
headerPanel.waitForIt();
headerPanel.goto_my_account.click();
signInPage = new SignInPage(driver);
signInPage.waitForIt();
signInPage.create_profile.click();
var createProfile = new CreateProfilePage(driver);
createProfile.waitForIt();
 //create profile
profileData = generateProfileData();
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
leftNavPanel.my_wallet.click();
myWalletPage = new MyWalletPage(driver);
myWalletPage.waitForIt();
myWalletPage.add_card_button.click();
addCardOvelay = new AddCardOverlay(driver);
addCardOvelay.waitForIt();
