

@objects
	first_name		id 	profile.profileAddress.firstName
	last_name		id	profile.profileAddress.lastName
	address_line_1		id	profile.profileAddress.address.addressLine1
	address_line_2		id	profile.profileAddress.address.addressLine2
	address_city		id	profile.profileAddress.address.city
	address_state		id	profile.profileAddress.address.state
	address_zip_code	id	profile.profileAddress.address.postalCode
	email			id	profile.email
	email_verify		id	profile.confirmEmail
	password		id	profile.password
	password_verify		id	profile.confirmPassword
	dob_month		id	profile.month
	dob_day			id	profile.date
	dob_year		id	profile.year
	security_question	id	profile.securityQuestion
	security_answer		id	profile.securityAnswer
	create_profile_button	id	createProfileBtn
	gender_female		id	female_cover
	gender_male		id	male_cover

= Create Profile Form =
	first_name:
        width  ~205 px
        height ~38 px

	last_name:
        width  ~205 px
        height ~38 px

	address_line_1:
        width  ~205 px
        height ~38 px

	address_line_2:
        width  ~205 px
        height ~38 px

	address_city:
        width  ~205 px
        height ~38 px

	address_state:
        width  ~205 px
        height ~38 px

	address_zip_code:
        width  ~205 px
        height ~38 px

	email:
        width  ~205 px
        height ~38 px

	email_verify:
        width  ~205 px
        height ~38 px

	password:
        width  ~205 px
        height ~38 px

	password_verify:
        width  ~205 px
        height ~38 px

	dob_month:
        width  ~60 px
        height ~38 px

	dob_day:
        width  ~60 px
        height ~38 px

	dob_year:
        width  ~60 px
        height ~38 px

	security_question:
        width  ~205 px
        height ~38 px

	security_answer:
        width  ~205 px
        height ~38 px

	gender_female:
        width  ~20 px
        height ~20 px

	gender_male:
        width  ~20 px
        height ~20 px

	create_profile_button:
        width  ~65 px
        height ~26 px
