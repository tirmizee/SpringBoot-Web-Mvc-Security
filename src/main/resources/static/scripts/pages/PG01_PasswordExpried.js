var PasswordExpriedModule = function(){
	
	var handleFormPasswordExpried = function(){
		$('#formPasswordExpried').bootstrapValidator({
			icon: {
	            valid: 'glyphicon glyphicon-ok', 
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {
	        	oldPassword: {
	                validators: {
	                    notEmpty: {
	                        message: 'The old password is required'
	                    }
	                }
	            },
	            newPassword: {
	                validators: {
	                    notEmpty: {
	                        message: 'The new password is required'
	                    }
	                },
                    stringLength: {
                        min: 6,
                        max: 30,
                        message: 'The password must be more than 6 and less than 30'
                    }
	            },
	            newPasswordConfirm : {
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
            	oldPassword : $('input[name="oldPassword"]').val(),
            	newPassword : $('input[name="newPassword"]').val(),
            	newPasswordConfirm : $('input[name="newPasswordConfirm"]').val()
            };
            
            AjaxManager.PostData(passwordReq ,"api/user/password/expried",
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
					var error = JSON.parse(jqXHR.responseText);
					swal({ type  : 'error', 
						   title : "Message Alert",  
						   text  : error.message, 
						   showConfirmButton : false, 
						   timer: 4000 });
					$('#formPasswordExpried button[type="submit"]').prop("disabled",false);
				});
		});
	}
	
	return {
		init : function(){
			handleFormPasswordExpried();
		}
	};
	
}();

