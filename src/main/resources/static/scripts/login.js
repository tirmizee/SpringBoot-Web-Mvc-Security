var LoginModule = function(){
	
	var handleFormLogin = function(){
		$('#formLogin').formValidation({
	        fields: {
	            username: {
	                validators: {
	                    notEmpty: {
	                        message: 'The username is required'
	                    },
	                    stringLength: {
	                        min: 6,
	                        max: 30,
	                        message: 'The username must be more than 6 and less than 30 '
	                    }
	                }
	            },
	            password: {
	                validators: {
	                    notEmpty: {
	                        message: 'The password is required'
	                    },
	                    stringLength: {
	                        min: 6,
	                        max: 100,
	                        message: 'The password must be more than 6 and less than 100 '
	                    }
	                }
	            }
        	}
		});
	}
	
	var handleInputUsername = function(){
		$('#formLogin input[name="username"]').inputmask('Regex', { 
		    regex: "^[a-zA-Z]+$"
		});
	}
	
	return {
		init : function(){
			handleFormLogin();
			handleInputUsername();
		}
	};
	
}();