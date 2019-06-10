<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<br>
<form id="FormSearchPermission">
    <div class="row">
        <div class="col-md-3 col-md-offset-3">
            <div class="form-group">
                <label>Permission Code</label>
                <div class="input-group date">
                    <div class="input-group-addon">
                        <i class="fa fa-lock"></i>
                    </div>
                    <input name="perCode" type="text" class="form-control pull-right">
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="form-group">
                <label>Permission Name</label>
                <input name="perName" type="text" class="form-control" placeholder="">
            </div>
        </div>
    </div>
    <div class="row text-center">
        <div class="col-md-6 col-md-offset-3">
            <button id="BtnClearPermission" type="button" class="btn btn-default btn-flat">Clear</button>
            <button id="BtnSearchPermission" type="button" class="btn btn-primary btn-flat">Search</button>
        </div>
    </div>
</form>
<div class="row">
    <div class="box-body">
        <table id="TBSearchPermission" class="display nowrap" cellspacing="0" width="100%"></table>
    </div>
</div>