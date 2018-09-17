

@objects

	existing_user_email		xpath		//form[@id='signInForm']//input[@id='email']
	existing_user_password	xpath		//form[@id='signInForm']//input[@id='password']
	existing_user_store_access_email	xpath		//form[@id='registrySignInVB']//input[@id='email']
	existing_user_store_access_registry_id	xpath		//form[@id='registrySignInVB']//input[@id='registryId']
	sign_in		id		signInButton
	continue		id		submitImg

= Registry Sign In Page  =
	existing_user_email:
        width  ~163 px
        height ~20 px

    existing_user_store_access_email:
        width  ~163 px
        height ~20 px

	existing_user_password:
        width  ~164 px
        height ~20 px

	sign_in:
        width ~64 px
        height ~29 px

	continue:
        width  ~84 px
        height ~29 px
