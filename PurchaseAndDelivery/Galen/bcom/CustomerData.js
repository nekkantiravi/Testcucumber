var randomStringSource = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
var randomNumberSource = "0123456789";
function generateRandomString(number) {
    var randomString = "";
    for (var i = 0; i < number; i++) {
        randomString += randomStringSource.charAt(Math.floor(Math.random() * randomStringSource.length));
    }
    return randomString;
}
function generateRandomNumber(number) {
    var randomNumber = "";
    for (var i = 0; i < number; i++) {
        randomNumber += randomNumberSource.charAt(Math.floor(Math.random() * randomNumberSource.length));
        if (i == 0 && randomNumber == "0") {
            i -= 1;
            randomNumber = "";
        }
    }
    return randomNumber;
}

function getRandomFirstName() {
    var firstNames = ["JAMES", "JOHN", "ROBERT", "MICHAEL", "WILLIAM", "DAVID", "RICHARD", "CHARLES", "JOSEPH", "THOMAS", "CHRISTOPHER"];
    return firstNames[Math.floor(Math.random() * firstNames.length)];
}
function getRandomLastName() {
    var lastNames = ["SMITH", "JOHNSON", "BROWN", "JONES", "MILLER", "GARCIA", "RODRIGUEZ", "ANDERSON", "TAYLOR", "THOMAS", "MOORE"];
    return lastNames[Math.floor(Math.random() * lastNames.length)];
}
function getRandomGender() {
    var genders = ["Male", "Female"];
    return genders[Math.floor(Math.random() * genders.length)];
}
function getRandomMonthName() {
    var monthNames = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
    return monthNames[Math.floor(Math.random() * monthNames.length)];
}
function generateRandomSecurityAnswer() {
    var securityAnswers = ["Metallica", "Billy Joel", "The Beatles", "Michael Jackson", "Justin Bieber"];
    return securityAnswers[Math.floor(Math.random() * securityAnswers.length)];
}
function getRandomEmail() {
    return (generateRandomString(8) + generateRandomNumber(5) + "@macys.com");
}
function getRandomDate() {
    return (1 + Math.floor(Math.random() * 28));
}
function getRandomYear() {
    var yearList = [];
    var lowEnd = 1950;
    var highEnd = 1990;
    for (var i = lowEnd; i <= highEnd; i++) {
        yearList.push(i);
    }
    return yearList[Math.floor(Math.random() * yearList.length)];
}

function getRandomAddress() {

    var profileAddress = [
        {
            "address_line_1": "1855 E Peckham Ln",
            "address_line_2": "Ste D",
            "address_city": "Reno",
            "address_state": "Nevada",
            "address_zip_code": "89502",
            "phone_area_code": "775",
            "country": "United States",
            "country_code": "US"
        },
        {
            "address_line_1": "4416 Walnut St",
            "address_line_2": "",
            "address_city": "Philadelphia",
            "address_state": "Pennsylvania",
            "address_zip_code": "19104",
            "phone_area_code": "484",
            "country": "United States",
            "country_code": "US"
        },
        {
            "address_line_1": "4209 San Pedro Dr NE",
            "address_line_2": "Apt 239",
            "address_city": "Albuquerque",
            "address_state": "New Mexico",
            "address_zip_code": "87109",
            "phone_area_code": "505",
            "country": "United States",
            "country_code": "US"
        },
        {
            "address_line_1": "1810 18th Ave NE",
            "address_line_2": "",
            "address_city": "Hickory",
            "address_state": "North Carolina",
            "address_zip_code": "28601",
            "phone_area_code": "828",
            "country": "United States",
            "country_code": "US"
        },
        {
            "address_line_1": "6130 Evergreen Blvd",
            "address_line_2": "",
            "address_city": "Saint Louis",
            "address_state": "Missouri",
            "address_zip_code": "63134",
            "phone_area_code": "314",
            "country": "United States",
            "country_code": "US"
        },
        {
            "address_line_1": "858 Ninth Ave",
            "address_line_2": "",
            "address_city": "Longview",
            "address_state": "Washington",
            "address_zip_code": "98632",
            "phone_area_code": "360",
            "country": "United States",
            "country_code": "US"
        },
        {
            "address_line_1": "6130 Evergreen Blvd",
            "address_line_2": "",
            "address_city": "Saint Louis",
            "address_state": "Missouri",
            "address_zip_code": "63134",
            "phone_area_code": "314",
            "country": "Australia",
            "country_code": "AU",
        },
        {
            "address_line_1": "1855 E Peckham Ln",
            "address_line_2": "",
            "address_line_3": "",
            "address_city": "Reno",
            "address_state": "Nevada",
            "address_zip_code": "89502",
            "phone_area_code": "775",
            "country": "Canada",
            "country_code": "CD",
        },
        {
            "address_line_1": "Natasha Apartments",
            "address_line_2": "Inner Ring Road",
            "address_line_3": "Domlur",
            "address_city": "Bangalore",
            "address_state": "Karnataka",
            "address_zip_code": "560071",
            "phone_area_code": "80",
            "country": "India",
            "country_code": "IN",
        },
        {
            "address_line_1": "1197 Galleria Blvd",
            "address_line_2": "",
            "address_line_3": "",
            "address_city": "Roseville",
            "address_state": "California",
            "address_zip_code": "10022",
            "phone_area_code": "998",
            "country": "United States",
            "country_code": "US",
        },
        {
            "address_line_1": "1199 Galleria Blvd",
            "address_line_2": "",
            "address_line_3": "",
            "address_city": "Roseville",
            "address_state": "California",
            "address_zip_code": "22102",
            "phone_area_code": "415",
            "country": "United States",
            "country_code": "US",
        },
        {
            "address_line_1": "680 Folsom St",
            "address_line_2": "Apt 123",
            "address_city": "San Francisco",
            "address_state": "California",
            "address_zip_code": "94107",
            "phone_area_code": "415",
            "country": "United States",
            "country_code": "US",
        }
    ];
    return profileAddress[Math.floor(Math.random() * profileAddress.length)];
}

function generateProfileData() {
    var profileData = new Object();
    var address = getRandomAddress();
    profileData["first_name"] = getRandomFirstName();
    profileData["last_name"] = getRandomLastName();
    profileData["address_line_1"] = address["address_line_1"];
    profileData["address_line_2"] = address["address_line_2"];
    profileData["address_city"] = address["address_city"];
    profileData["address_state"] = address["address_state"];
    profileData["address_zip_code"] = address["address_zip_code"];
    profileData["phone_area_code"] = address["phone_area_code"];
    profileData["country"] = address["country"];
    profileData["country_code"] = address["country_code"];
    profileData["address_line_2"] = getRandomFirstName();
    profileData["dob_month"] = getRandomMonthName();
    profileData["dob_date"] = getRandomDate();
    profileData["dob_year"] = getRandomYear();
    profileData["email"] = getRandomEmail();
    profileData["verify_email"] = profileData["email"];
    profileData["password"] = "Macys1234";
    profileData["verify_password"] = profileData["password"];
    profileData["security_question"] = "What was the first concert you attended?";
    profileData["security_answer"] = generateRandomSecurityAnswer();
    profileData["gender"] = getRandomGender();
    return profileData;

}
//console.log(generateProfileData()["first_a"]);