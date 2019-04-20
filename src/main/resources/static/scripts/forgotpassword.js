var ForgotPasswordModule = function(){
	
	var handleFormForgotPassword = function(){
		$('#FormForgotPassword').bootstrapValidator({
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
            
            AjaxManager.PostData(ReqForgotPassword, "api/user/password/forgot",
				function(response){
	            	$.confirm({
					    title: 'Message Alert!',
					    content: 'The system has received the request. Please check the email.',
					    type: 'green',
					    typeAnimated: true,
					    buttons: {
					        ok : {
					            text: 'OK',
					            btnClass: 'btn-green',
					            closeIcon: true,
					            action: function(){
					            	window.location.href = 'login';
					            }
					        }
					    }
					});
            	},
            	function(jqXHR, textStatus, errorThrown){
            		$('#FormForgotPassword button[type="submit"]').prop("disabled",false);
            	}
			);
		});
	}
	
	return {
		init : function(){
			handleFormForgotPassword();
		}
	};
	
}();