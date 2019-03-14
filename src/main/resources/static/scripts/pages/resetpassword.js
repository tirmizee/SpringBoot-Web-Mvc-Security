var ResetPasswordModule = function(){
	
	var handleFormForgotPassword = function(){
		$('#formForgotPassword').bootstrapValidator({
	        fields: {
	            email: {
	                validators: {
	                    notEmpty: {
	                        message: 'The username is required'
	                    },
	                    emailAddress: {
	                    	 message: 'The input is not a valid email address'
	                    }
	                }
	            }
        	}
		}).on('success.form.bv', function(e) {
            e.preventDefault();
            
            var ReqForgotPassword = {
            	email : $('input[name="email"]').val()
            };
            
            AjaxManager.PostData(ReqForgotPassword, "api/user/forgotpassword",
				function(response){

            	},
				function(jqXHR, textStatus, errorThrown){

            	}
			);
            
		});
	}
	
	return {
		init : function(){
			handleFormResetPassword();
		}
	};
	
}();