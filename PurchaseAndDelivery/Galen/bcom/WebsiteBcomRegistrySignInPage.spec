

@objects

	existing_user_email		xpath		//form[@id='registrySignInVB']//input[@id='email']
	existing_user_password	xpath		//form[@id='registrySignInVB']//input[@id='password']
	sign_in		xpath		//div[@id='r_signin_signinButtonContainer']/input
	goto_start_registry_for_new_user		xpath		//div[@id='r_signin_createRegistryButtonContainer']/input

= Registry Sign In Page  =
	existing_user_email:
        width  ~151 px
        height ~20 px

	existing_user_password:
        width  ~151 px
        height ~20 px

	sign_in:
        width ~72 px
        height ~26 px

	goto_start_registry_for_new_user:
        width  ~133 px
        height ~26 px
