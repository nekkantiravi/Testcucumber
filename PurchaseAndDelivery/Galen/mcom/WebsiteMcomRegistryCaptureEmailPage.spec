

@objects

    existing_user_email                 xpath       //div[@class='innerLeft gainlayout']/fieldset/div[1]/input[@id='email']
    existing_user_password              xpath       //div[@class='innerLeft gainlayout']/fieldset/div[2]/input[@id='password']
    new_user_email                      xpath       //fieldset[@id='captureEmailFields']/div[1]/input[@id='email']
    new_user_password                   xpath       //fieldset[@id='captureEmailFields']/div[3]/div[1]/input[@id='password']
    new_user_email_verify               id          verifyEmail
    new_user_password_verify            id          verifyPassword
    existing_user_continue_button       xpath       //div[@id='regLeft']/input[@id='noProfileSubmitImg']
    new_user_continue_button            xpath       //div[@id='regRight']/input[@id='noProfileSubmitImg']

= Registry Capture Email Page  =
	existing_user_email:
        width  ~182 px
        height ~20 px

    existing_user_password:
        width  ~182 px
        height ~20 px

	new_user_email:
        width  ~182 px
        height ~20 px

	new_user_password:
        width ~182 px
        height ~20 px

	new_user_email_verify:
        width  ~182 px
        height ~20 px

    new_user_password_verify:
        width  ~182 px
        height ~20 px

    existing_user_continue_button:
        width  ~74 px
        height ~29 px

    new_user_continue_button:
        width  ~74 px
        height ~29 px
