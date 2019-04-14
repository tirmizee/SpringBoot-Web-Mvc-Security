<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="ModalEditUser" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
	<div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content">
	    	<form id="FormEditUser">
	        <div class="modal-header modal-header-color">
	            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	            <h4 class="modal-title"><label>Edit User</label></h4>
	        </div>
	        <div class="modal-body"  style="margin:0 20px 20px 20px;">
	        	<h3>User Account</h3>
	        	<div class="row">
	        		<div class="col-md-6">
	        			<div class="row">
	        				<div class="col-md-6">
		        				<div class="form-group">
							        <label>User ID</label>
							        <div class="input-group date">
							            <div class="input-group-addon readonly">
							                <i class="fa fa-trademark"></i>
							            </div>
							            <input name="userId" type="text" class="form-control pull-right" readonly>
							        </div>
							    </div>
	        				</div>
	        				<div class="col-md-6">
	        					<div class="form-group">
							        <label>User Name</label>
							        <div class="input-group date">
							            <div class="input-group-addon">
							                <i class="fa fa-user"></i>
							            </div>
							            <input name="username" type="text" class="form-control pull-right" >
							        </div>
							    </div>
	        				</div>
	        			</div>
	        		</div>
	        		<div class="col-md-6">
	       				
	        		</div>
	        	</div>
	        	
	        	<div class="row">
	        		<div class="col-md-6">
	        			<div class="row">
	        				<div class="col-md-6">
	        					<div class="form-group">
								    <label>Max Session</label>
								    <div class="input-group">
	                                    <span class="input-group-btn">
	                                        <button type="button" class="number-minus btn btn-defualt btn-number"  data-type="minus" data-field="">
	                                          <span class="glyphicon glyphicon-minus"></span>
	                                        </button>
	                                    </span>
	                                    <input type="text" name="maxSession" class="form-control input-number" value="1" style=" text-align: center; ">
	                                    <span class="input-group-btn">
	                                        <button type="button" class="number-plus btn btn-defualt btn-number" data-type="plus" data-field="">
	                                            <span class="glyphicon glyphicon-plus"></span>
	                                        </button>
	                                    </span>
	                                </div>
								</div>
	        				</div>
	        				<div class="col-md-6">
	        					<div class="form-group">
								    <label>Role</label>
								    <select name="roleId" class="form-control select2" style="width: 100%;">
								    </select>
								</div>
	        				</div>
	        				
	        			</div>
	        		</div>
	        		<div class="col-md-6">
	        			<div class="row">
	        				<div class="col-md-6">
	        					<div class="form-group">
								    <label>Account Expired Date</label>
								    <div class="input-group date">
								        <div class="input-group-addon">
								            <i class="fa fa-calendar"></i>
								        </div>
								        <input name="accountExpiredDate" type="text" class="form-control pull-right">
								    </div>
								</div>
	        				</div>
	        				<div class="col-md-6">
	        					<div class="form-group">
								    <label>Password Expired Date</label>
								    <div class="input-group date">
								        <div class="input-group-addon">
								            <i class="fa fa-calendar"></i>
								        </div>
								        <input name="passwordExpiredDate" type="text" class="form-control pull-right">
								    </div>
								</div>
	        				</div>
	        			</div>
	        		</div>
	        	</div>
	        	
	        	<div class="row">
	        		<div class="col-md-6">
	        			<div class="row">
	        				<div class="col-md-6">
						    	<label>Account Enabled</label>
						    	<div class="btn-group toggle-account-enabled"> 
						    		<a class="btn btn-sm btn-success active" data-name="Y">Enable</a>
						    		<a class="btn btn-sm btn-default" data-name="N">Disable</a>
						  		</div>
			        		</div>
			        		<div class="col-md-6">
						    	<label>Account Locked</label>
						    	<div class="btn-group toggle-account-locked"> 
						    		<a class="btn btn-sm btn-success active" data-name="Y">Non Locked</a>
						    		<a class="btn btn-sm btn-default" data-name="N">Locked</a>
						  		</div>
			        		</div>
						</div>
	        		</div>
	        		<div class="col-md-6">
	        			<div class="row">
	        				<div class="col-md-6">
						    	<label>Account Expired</label>
						    	<div class="btn-group toggle-account-expired"> 
						    		<a class="btn btn-sm btn-success active" data-name="Y">Non Expired</a>
						    		<a class="btn btn-sm btn-default" data-name="N">Expired</a>
						  		</div>
			        		</div>
			        		<div class="col-md-6">
						    	<label>Password Expired</label>
						    	<div class="btn-group toggle-password-expired"> 
						    		<a class="btn btn-sm btn-success active" data-name="Y">Non Expired</a>
						    		<a class="btn btn-sm btn-default" data-name="N">Expired</a>
						  		</div>
			        		</div>
						</div>
	        		</div>
	        	</div>
	        	
	        	
			
				<hr>
				<h3>Personal Info</h3>
				<div class="row">
					<div class="col-md-6">
		        		<div class="row">
	        				<div class="col-md-6">
		        				<div class="form-group">
							        <label>First Name</label>
							        <div class="input-group date">
							            <div class="input-group-addon">
							                <i class="fa  fa-user"></i>
							            </div>
							            <input name="firstName" type="text" class="form-control pull-right" >
							        </div>
							    </div>
	        				</div>
	        				<div class="col-md-6">
	        					<div class="form-group">
							        <label>Last Name</label>
							        <div class="input-group date">
							            <div class="input-group-addon">
							                <i class="fa  fa-user"></i>
							            </div>
							            <input name="lastName" type="text" class="form-control pull-right" >
							        </div>
							    </div>
	        				</div>
	        			</div>
	        		</div>
	        		<div class="col-md-6">
	        		</div>
	        	</div>
				<div class="row">
					<div class="col-md-6">
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
							        <label>Citizen ID</label>
							        <div class="input-group date">
							            <div class="input-group-addon">
							                <i class="fa fa-male"></i>
							            </div>
							            <input name="citizenId" type="text" class="form-control pull-right" >
							        </div>
							    </div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
							        <label>Tel</label>
							        <div class="input-group date">
							            <div class="input-group-addon">
							                <i class="fa fa-phone"></i>
							            </div>
							            <input name="tel" type="text" class="form-control pull-right" >
							        </div>
							    </div>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
					        <label>Email</label>
					        <div class="input-group date">
					            <div class="input-group-addon">
					                <i class="glyphicon glyphicon-envelope"></i>
					            </div>
					            <input name="email" type="text" class="form-control pull-right" >
					        </div>
					    </div>
					</div>
				</div>
				
				<div class="row">
	        		<div class="col-md-6">
	        			<div class="row">
	        				<div class="col-md-6">
		        				<div class="form-group">
							        <label>Province</label>
							        <select id="SLProvince" class="form-control select2" style="width: 100%;">
							        	
								    </select>
							    </div>
	        				</div>
	        				<div class="col-md-6">
	        					<div class="form-group">
							        <label>District</label>
							        <select id="SLDistrict" class="form-control select2" style="width: 100%;">
								    </select>
							    </div>
	        				</div>
	        			</div>
	        		</div>
	        		<div class="col-md-6">
		        		<div class="row">
	        				<div class="col-md-6">
		        				<div class="form-group">
							        <label>Sub District</label>
							        <select id="SLSubDistrict" class="form-control select2" style="width: 100%;">
								    </select>
							    </div>
	        				</div>
	        				<div class="col-md-6">
	        					<div class="form-group">
							        <label>Post Code</label>
							        <div class="input-group date">
							            <div class="input-group-addon readonly">
							                <i class="fa fa-suitcase"></i>
							            </div>
							            <input name="postCode" type="text" class="form-control pull-right" readonly>
							        </div>
							    </div>
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
		            <button type="button" class="btn btn-default btn-flat" data-dismiss="modal">Close</button>
		            <button type="submit" class="btn btn-success btn-flat">Save</button>
	            </div>
	        </div>
	        </form>
	    </div>
	    <!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->