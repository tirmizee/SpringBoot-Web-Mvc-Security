<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="row">
	<div class="col-md-12">
		<div class="nav-tabs-custom">
		    <ul class="nav nav-tabs">
		        <li class="active"><a href="#tab_role" data-toggle="tab" aria-expanded="true"><b>Roles</b></a></li>
		        <li class=""><a href="#tab_permission" data-toggle="tab" aria-expanded="false"><b>Permission</b></a></li>
		    </ul>
		    <div class="tab-content">
		        <div class="tab-pane active" id="tab_role">
		        	<br>
		        	<form id="FormSearchRole">
			            <div class="row">
			            	<div class="col-md-3 col-md-offset-3">
								<div class="form-group">
							        <label>Role Code</label>
							        <div class="input-group date">
							            <div class="input-group-addon">
							                <i class="fa fa-lock"></i>
							            </div>
							            <input name="roleCode" type="text" class="form-control pull-right" >
							        </div>
							    </div>
			            	</div>
			            	<div class="col-md-3">
			            		<div class="form-group">
								    <label>Role Name</label>
								    <input name="roleName" type="text" class="form-control" placeholder="">
								</div>
			            	</div>
			            </div>
			            <div class="row text-center">
			            	<div class="col-md-6 col-md-offset-3">
								<button id="BtnClear" type="button" class="btn btn-default btn-flat">Clear</button>
								<button id="BtnSearch" type="button" class="btn btn-primary btn-flat">Search</button>
							</div>
						</div>
					</form>
					<div class="row">
						<div class="box-body">
					    	<table id="TBRole" class="display nowrap" cellspacing="0" width="100%"></table>
					    </div>
				    </div>
		        </div>
		        <div class="tab-pane" id="tab_permission">
		            The European languages are members of the same family. Their separate existence is a myth. For science, music, sport, etc, Europe uses the same vocabulary. The languages only differ in their grammar, their pronunciation and their most common words. Everyone realizes why a new common language would be desirable: one could refuse to pay expensive translators. To achieve this, it would be necessary to have uniform grammar, pronunciation and more common words. If several languages coalesce, the grammar of the resulting language is more simple and regular than that of the individual languages.
		        </div>
		    </div>
		</div>
	</div>
</div>
