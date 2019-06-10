var ResetPasswordModule = function(){
	
	var handleFormResetPassword = function(){
		$('#formResetPassword').bootstrapValidator({
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
            
            var reqPasswordResetToken = {
            	uid             : parseInt($('input[name="uid"]').val()),
            	token           : $('input[name="token"]').val(),
            	password        : $('input[name="password"]').val(),
            	confirmPassword : $('input[name="passwordConfirm"]').val()
            };
           
            AjaxManager.PostData(reqPasswordResetToken, "../../api/user/password/reset",
				function(response){
	            	$.confirm({
					    title: 'Message Alert!',
					    content: response.messageName,
					    type: 'green',
					    typeAnimated: true,
					    buttons: {
					        ok : {
					            text: 'OK',
					            btnClass: 'btn-green',
					            closeIcon: true,
					            action: function(){
					            	window.location.href = '../../login';
					            }
					        }
					    }
					});
            	},
				function(jqXHR, textStatus, errorThrown){
            		var error = JSON.parse(jqXHR.responseText);
					$('#formResetPassword button[type="submit"]').prop("disabled",false);
					$.confirm({
					    title: 'Message Alert!' ,
					    content: error.message ,
					    type: 'red',
					    typeAnimated: true ,
					    closeIcon: true,
					    closeIconClass: 'fa fa-close',
					    buttons: {
					        close: function () {	
					        	
					        }
					    }
					});
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