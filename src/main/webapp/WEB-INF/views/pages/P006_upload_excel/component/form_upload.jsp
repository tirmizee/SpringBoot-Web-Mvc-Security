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
    	<form id="FormUploadExcel">
    		<div class="row">
    		
			    <div class="col-lg-3 col-xs-6">
			        <div class="small-box bg-aqua">
			            <div class="inner">
			                <h3 id="TotalRecord">0</h3>
			                <p>Record</p>
			            </div>
			            <div class="icon">
			                <i class="fa  fa-file-text"></i>
			            </div>
			            <a href="#" class="small-box-footer">
			              More info <i class="fa fa-arrow-circle-right"></i>
			            </a>
			        </div>
			    </div>
			   
			    <div class="col-lg-3 col-xs-6">
			        <div class="small-box bg-yellow">
			            <div class="inner">
			                <h3 id="TotalAmount">0</h3>
			                <p>Total Amount</p>
			            </div>
			            <a href="#" class="small-box-footer">
			              More info <i class="fa fa-arrow-circle-right"></i>
			            </a>
			        </div>
			    </div>
			     <div class="col-lg-3 col-xs-6">
			        <div class="small-box bg-green">
			            <div class="inner">
			                <h3><span id="PercenValid">0</span><sup style="font-size: 20px">%</sup></h3>
			                <p>Data Valid <span id="RecordValid">0</span></p>
			            </div>
			            <div class="icon">
			                <i class="fa fa-check"></i>
			            </div>
			            <a href="#" class="small-box-footer">
			              More info <i class="fa fa-arrow-circle-right"></i>
			            </a>
			        </div>
			    </div>
			    <div class="col-lg-3 col-xs-6">
			        <div class="small-box bg-red">
			            <div class="inner">
			                <h3><span id="PercenInvalid">0</span><sup style="font-size: 20px">%</sup></h3>
			
			                <p>Data invalid <span id="RecordInvalid">0</span></p>
			            </div>
			            <div class="icon">
			                <i class="fa fa-remove"></i>
			            </div>
			            <a href="#" class="small-box-footer">
			              More info <i class="fa fa-arrow-circle-right"></i>
			            </a>
			        </div>
			    </div>
			    <!-- ./col -->
			</div>
	        <div class="row">
	        	<div class="col-md-6">
					<div class="form-group">
				        <label>Description</label>
				        <div class="input-group date">
				            <div class="input-group-addon">
				                <i class="fa fa-file-excel-o"></i>
				            </div>
				            <input name="description" type="text" class="form-control" placeholder="Description...">
				        </div>
				    </div>
	        	</div>
	        	<div class="col-md-6">
	        		<div class="form-group">
					    <label>File</label>
					   	<input name="file" type="file" class="file">
					   	<p class="help-block">Excel file only</p>
					</div>
	        	</div>
	        </div>
	        <div class="row text-center">
				<button id="BtnClear" type="button" class="btn btn-default btn-flat">Clear</button>
				<button id="BtnSearch" type="submit" class="btn btn-success btn-flat">Validate</button>
			</div>
		</form>
    </div>
</div>