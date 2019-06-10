<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="ModalEditRole" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
	<div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content">
	    	<form id="FormEditRole">
	        <div class="modal-header modal-header-color">
	            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	            <h4 class="modal-title"><label>Edit Role</label></h4>
	        </div>
	        <div class="modal-body"  style="margin: 20px;">
	        	<div class="row">
	        		<div class="col-md-6">
	        			<div class="row">
	        				<div class="col-md-6">
		        				<div class="form-group">
							        <label>Role Code</label>
							        <div class="input-group date">
							            <div class="input-group-addon">
							                <i class="fa fa-trademark"></i>
							            </div>
							            <input name="roleCode" type="text" class="form-control pull-right" >
							        </div>
							    </div>
	        				</div>
	        				<div class="col-md-6">
	        					<div class="form-group">
							        <label>Role Name</label>
							        <div class="input-group date">
							            <div class="input-group-addon">
							                <i class="fa fa-suitcase"></i>
							            </div>
							            <input name="roleName" type="text" class="form-control pull-right" >
							        </div>
							    </div>
	        				</div>
	        			</div>
	        		</div>
	        		<div class="col-md-6">
		        		<div class="form-group">
					        <label>Role Description</label>
					        <div class="input-group date">
					            <div class="input-group-addon">
					                <i class="fa fa-sticky-note-o"></i>
					            </div>
					            <input type="text" class="form-control pull-right" >
					        </div>
					    </div>
	        		</div>
	        	</div>
			
			    <div class="row">
					<div class="box-body">
				    	<table id="TBEditPermission" class="display nowrap" cellspacing="0" width="100%"></table>
				    </div>
			    </div>
	        </div>
	        <div class="modal-footer ">
	        	<div class="row text-center">
		            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		            <button type="submit" class="btn btn-success">Save</button>
	            </div>
	        </div>
	        </form>
	    </div>
	    <!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->