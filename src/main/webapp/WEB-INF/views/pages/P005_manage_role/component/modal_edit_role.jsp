<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="ModalEditRole" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
	<div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content">
	        <div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	            <h4 class="modal-title" id="gridSystemModalLabel">Modal title</h4>
	        </div>
	        <div class="modal-body">
	        	<!-- Date -->
			    <div class="form-group">
			        <label>Date:</label>
			
			        <div class="input-group date">
			            <div class="input-group-addon">
			                <i class="fa fa-calendar"></i>
			            </div>
			            <input type="text" class="form-control pull-right" id="datepicker">
			        </div>
			    </div>
			
			    <div class="form-group">
			        <label>Date range:</label>
			
			        <div class="input-group">
			            <div class="input-group-addon">
			                <i class="fa fa-calendar"></i>
			            </div>
			            <input type="text" class="form-control pull-right" id="reservation">
			        </div>
			    </div>
			
			    <div class="form-group">
			        <label>Date and time range:</label>
			        <div class="input-group">
			            <div class="input-group-addon">
			                <i class="fa fa-clock-o"></i>
			            </div>
			            <input type="text" class="form-control pull-right" id="reservationtime">
			        </div>
			    </div>
	        </div>
	        <div class="modal-footer">
	            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	            <button type="button" class="btn btn-primary">Save changes</button>
	        </div>
	    </div>
	    <!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->