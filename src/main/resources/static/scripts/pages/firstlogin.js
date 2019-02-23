var FirstLoginModule = function(){
	
	var handleFormFirstLogin = function(){
		$('#formFirstLogin').bootstrapValidator({
			icon: {
	            valid: 'glyphicon glyphicon-ok', 
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {
	        	password: {
	                validators: {
	                    notEmpty: {
	                        message: 'The password is required'
	                    },
	                    stringLength: {
	                        min: 6,
	                        max: 30,
	                        message: 'The password must be more than 6 and less than 30'
	                    }
	                }
	            },
	            confirmPassword : {
	                validators: {
	                    identical: {
	                        field: 'password',
	                        message: 'The password and its confirm are not the same'
	                    }
	                }
	            }
        	}
		}).on('success.form.bv', function(e) {
            e.preventDefault();
            
            var passwordReq = {
            	password : $('input[name="password"]').val(),
            	confirmPassword : $('input[name="confirmPassword"]').val()
            };
            
            AjaxManager.PostData(passwordReq ,"api/user/password/firstlogin",
				function(response){
					if (response) {
						console.log(response);
						swal({ type  : 'success',  
							   title : 'Reset Your Password Complete', 
							   text  : 'Go To Login Page', 
							   showConfirmButton : false, 
							   timer: 2000  
						}).then(function() {
							window.location.href = 'login';
						});
					}
				},
				function(jqXHR, textStatus, errorThrown){

				});
            
		});
	}
	
	return {
		init : function(){
			handleFormFirstLogin();
		}
	};
	
}();

