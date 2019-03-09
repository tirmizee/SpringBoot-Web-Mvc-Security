<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="box box-default">
    <div class="box-header with-border">
        <h3 class="box-title">Manage Session</h3>
        <div class="box-tools pull-right">
            <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
            </button>
        </div>
    </div>
    <div class="box-body" style="">
        <div class="row">
        	<div class="col-md-3">
	        	<div class="info-box bg-red">
				    <span class="info-box-icon"><i class="fa fa-comments-o"></i></span>
				    <div class="info-box-content">
				        <span class="info-box-text">All Users</span>
				        <span id="SPCountUsers" class="info-box-number">0</span>
				        <div class="progress">
				            <div class="progress-bar" style="width: 70%"></div>
				        </div>
				        <span class="progress-description"> 70% Increase in 30 Days  </span>
				    </div>
				</div>
        	</div>
        	<div class="col-md-3">
        		<div class="info-box bg-green">
				    <span class="info-box-icon"><i class="fa fa-calendar"></i></span>
				    <div class="info-box-content">
				        <span class="info-box-text">All Users Active</span>
				        <span id="SPCountUserActive" class="info-box-number">0</span>
				        <div class="progress">
				            <div class="progress-bar" style="width: 70%"></div>
				        </div>
				        <span class="progress-description">70% Increase in 30 Days</span>
				    </div>
				</div>
        	</div>
        	 <div class="col-md-3">
        		<div class="info-box">
				   <span class="info-box-icon bg-aqua"><i class="ion ion-ios-people-outline"></i></span>
				   <div class="info-box-content">
				      <span class="info-box-text">All Users</span>
				      <span  class="info-box-number">0</span>
				   </div>
				</div>
        	</div>
        	<div class="col-md-3">
	        	<div class="info-box">
				   <span class="info-box-icon bg-green"><i class="ion ion-ios-people-outline"></i></span>
				   <div class="info-box-content">
				      <label class="info-box-text">All Users Active</label>
				      <span  class="info-box-number">0</span>
				   </div>
				</div>
        	</div>
        </div>
    </div>
</div>
<div class="box box-default">
    <div class="box-body" style="">
    	<table id="TBSession" class="display nowrap" cellspacing="0" width="100%"></table>
    </div>
</div>