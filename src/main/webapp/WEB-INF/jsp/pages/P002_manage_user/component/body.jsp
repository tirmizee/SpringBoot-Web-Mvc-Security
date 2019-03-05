<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="box box-default">
    <div class="box-header with-border">
        <h3 class="box-title">Expandable</h3>
        <div class="box-tools pull-right">
            <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
            </button>
        </div>
    </div>
    <div class="box-body" style="">
        <div class="row">
        	<div class="col-md-3">
	        	<div class="form-group">
				    <label>User Name</label>
				    <input name="username" type="text" class="form-control" placeholder="Username...">
				</div>
        	</div>
        	<div class="col-md-3">
        		<div class="form-group">
				    <label>First Name</label>
				    <input name="firstName" type="text" class="form-control" placeholder="Firstname...">
				</div>
        	</div>
        	<div class="col-md-3">
	        	<div class="form-group">
				    <label>Last Name</label>
				    <input name="lastName" type="text" class="form-control" placeholder="Lastname...">
				</div>
        	</div>
        	<div class="col-md-3">
        		<div class="form-group">
				    <label>Email</label>
				    <input name="email" type="text" class="form-control" placeholder="Email...">
				</div>
        	</div>
        </div>
        <div class="row">
        	<div class="col-md-3">
        		<div class="form-group">
				    <label>Disabled Result</label>
				    <select id="SLRole" class="form-control select2" style="width: 100%;">
				        <option selected="selected">Alabama</option>
				    </select>
				</div>
        	</div>
        </div>
        <div class="row text-center">
			<button id="BtnClear" type="button" class="btn btn-default btn-flat">Clear</button>
			<button id="BtnSearch" type="button" class="btn btn-primary btn-flat">Search</button>
		</div>
    </div>
</div>
<div class="box box-default">
    <div class="box-body" style="">
    	<table id="TBUser" class="display nowrap" cellspacing="0" width="100%"></table>
    </div>
</div>