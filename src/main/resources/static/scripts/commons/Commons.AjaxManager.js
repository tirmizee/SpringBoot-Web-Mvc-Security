var AjaxManager = function(){
	
	var rootUrl = $("meta[name='rootUrl']").attr("content");
	
	var token = $("meta[name='_csrf']").attr("content");

	var header = $("meta[name='_csrf_header']").attr("content");
	
	var PostData = function (objToPost, postUri, onPostSuccess, onPostError) {
        $.ajax({
            type: 'POST',
            url: postUri,
            contentType: 'application/json',
            headers: {
                'Accept' : 'application/json',
                'X-CSRF-TOKEN' : token 
            },
            data: JSON.stringify(objToPost)
        }).done(function (objRet) {
            onPostSuccess(objRet);
        }).fail(function (jqXHR, textStatus, errorThrown) {
        	if (onPostError !== undefined) {
        		onPostError(jqXHR, textStatus, errorThrown);
	    	}
        });
    };
    
    var GetData = function (objToGet, getUri, onGetSuccess, onGetError) {
        $.ajax({
            type: 'GET',
            url: getUri,
            contentType: 'application/json;charset=utf-8',
            dataType: 'json',
            headers: {
            	'Accept' : 'application/json',
            	'X-CSRF-TOKEN' : token
            },
            data: objToGet,
        }).done(function (objRet) {
            onGetSuccess(objRet);
        }).fail(function (jqXHR, textStatus, errorThrown) {
	    	 if (onGetError !== undefined) {
	    		 onGetError(jqXHR, textStatus, errorThrown);
	    	 }
        });
    };
    
    var DeleteData = function (deleteUri, onDeleteSuccess, onDeleteError) {
        $.ajax({
            type: 'DELETE',
            url: deleteUri,
            contentType: 'application/json;charset=utf-8',
            headers: {
            	'Accept'        : 'application/json',
            	'X-CSRF-TOKEN'  : token
            },
        }).done(function (objRet) {
        	onDeleteSuccess(objRet);
        }).fail(function (jqXHR, textStatus, errorThrown) {
        	if (onDeleteError !== undefined) {
        		onDeleteError(jqXHR, textStatus, errorThrown);
	    	 }
        });
    };
    
    var UploadData = function (formData, postUri, onPostSuccess, onPostError) {
        $.ajax({
        	processData: false,
            contentType: false,
            headers: {
            	'X-CSRF-TOKEN' : token
            },
            async: false,
            cache: false,
        	type: 'POST',
            url: postUri,
            data: formData
        }).done(function (objRet) {
            onPostSuccess(objRet);
        }).fail(function (jqXHR, textStatus, errorThrown) {
            onPostError(jqXHR, textStatus, errorThrown);
        });
    }
    
    return {
    	GetData    : GetData,
    	PostData   : PostData,
    	DeleteData : DeleteData,
    	UploadData : UploadData,
    	CsrfHeader : header,
    	CsrfToken  : token,
    	RootUrl    : rootUrl
    };
    
}();