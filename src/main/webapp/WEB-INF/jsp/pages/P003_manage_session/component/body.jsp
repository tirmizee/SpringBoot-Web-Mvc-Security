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
	        	<div id="BoxAllUsers" class="info-box bg-aqua">
				    <a class="refresh" href="#"  style="color: white;"><span class="info-box-icon"><i class="ion ion-ios-people-outline"></i></span></a>
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
	        		<div id="BoxUsersLogged" class="info-box bg-yellow">
				    <a class="refresh" href="#"  style="color: white;"><span class="info-box-icon"><i class="fa fa-user-secret"></i></span></a>
				    <div class="info-box-content">
				        <span class="info-box-text">All Users Logged in</span>
				        <span id="SPCountUserLogged" class="info-box-number">0</span>
				        <div class="progress">
				            <div class="progress-bar" style="width: 70%"></div>
				        </div>
				        <span class="progress-description"> 70% Increase in 30 Days  </span>
				    </div>
				</div>
        	</div>
        	<div class="col-md-3">
        		<div id="BoxUsersActive" class="info-box bg-green">
				    <a class="refresh" href="#"  style="color: white;"><span class="info-box-icon"><i class="fa fa-user-plus"></i></span></a>
				    <div class="info-box-content">
				        <span class="info-box-text">All Session Active</span>
				        <span id="SPCountUserActive" class="info-box-number">0</span>
				        <div class="progress">
				            <div class="progress-bar" style="width: 70%"></div>
				        </div>
				        <span class="progress-description">70% Increase in 30 Days</span>
				    </div>
				</div>
        	</div>
        	<div class="col-md-3">
        		<div id="BoxSessionExpried" class="info-box bg-red">
				    <a class="refresh" href="#" style="color: white;"><span class="info-box-icon"><i class="fa fa-user-times"></i></span></a>
				    <div class="info-box-content">
				        <span class="info-box-text">All Session Expired</span>
				        <span id="SPCountSessionExpired" class="info-box-number">0</span>
				        <div class="progress">
				            <div class="progress-bar" style="width: 70%"></div>
				        </div>
				        <span class="progress-description">70% Increase in 30 Days</span>
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