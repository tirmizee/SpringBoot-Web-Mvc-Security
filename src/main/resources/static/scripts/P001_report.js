var ReportModule = function(){
	
	var data = [];
	
	var activeMenu = function(){
		 $('ul.sidebar-menu > li.treeview-report').addClass('active');
	}
	
	var loadData = function(){
		AjaxManager.GetData(null, '../api/geography/countprovince/',
			function(response){
				data = $.map(response, function (item) { 
                    return {
                    	label  : item.geoNameTh,
                    	data   : item.countProvince,
                    	color  : '#' + Math.floor(Math.random() * 16777215).toString(16)
                    }
                });
			},
			function(jqXHR, textStatus, errorThrown){
				
			}
		);
	}
	
	var handleDonutCart =  function(){
		AjaxManager.GetData(null, '../api/geography/countprovince/',
			function(response){
				renderDonutCart(response);
			},
			function(jqXHR, textStatus, errorThrown){}
		);
	}
	
	var renderDonutCart =  function(response){
		
		data = $.map(response, function (item) { 
            return {
            	label  : item.geoNameTh,
            	data   : item.countProvince,
            	color  : '#'+ ('000000' + Math.floor(Math.random()*16777215).toString(16)).slice(-6)
            }
        });
	
		var option = {
			series: {
				pie: {
					show: true,
					radius: 1,
					innerRadius: 0.30,
					label: {
						show: true,
						radius: 2 / 3,
						formatter: function (label, series) {
							return '<div style="font-size:12px; text-align:center; padding:2px; color: #fff; font-weight: 600;">' +
								series.data[0][1] +
								'<br>' +
								Math.round(series.percent) + '%</div>'
						},
						threshold: 0.1
					}

				}
			},
			legend: {
				show: true
			},
		    grid: {
		        hoverable: true
		    }
		};
		
		$.plot("#donut-chart", data, option);
	}
	
	var _randomScalingFactor = function(length) {
		return Math.round(Math.random() * length);
	};
	
	var handleChart = function(){
		AjaxManager.GetData(null, '../api/address/district/9004/countvillage',
			function(response){
				renderBarchart(response);
			},
			function(jqXHR, textStatus, errorThrown){}
		);
	}
	
	var renderBarchart = function(response) {
		
		var label = null;
		var labels = []; 
		var datas = []; 
		var backgroundColor = [];
		
		$.each(response, function( index, value) {
			if (value.subdistrictCode == '900401') {
				label = value.subdistrictNameTh;
			}
			datas.push(value.countVillage);
			labels.push(value.subdistrictNameTh);
			backgroundColor.push('rgba(54, 162, 235, 0.9)');
		});
		
		var data = {
			labels: labels,
	        datasets: [
	        	{
		            label :label,
		            data : datas,
		            backgroundColor : backgroundColor
		        }
	        ]
		};
		
		var options = {
	        scales: {
	            yAxes: [{
	                ticks: {
	                    beginAtZero:true
	                }
	            }]
	        }
	    };
		
		var ctx  = $('#chart').get(0).getContext('2d');
		var myChart = new Chart(ctx, {
		    type : 'bar',
		    data : data,
		    options : options
		});
	}
	
	var handleLineChart = function(){
		
		var DATA_COUNT = 12;
		
		var data = {
			labels: Samples.utils.months({count: DATA_COUNT}),
			datasets: [{
				data: Samples.utils.numbers({
					count: DATA_COUNT,
					min: 0,
					max: 100
				}),
				backgroundColor: '#red',
				borderColor: '#4dc9f6',
			}]
		};

		var options = {
			legend: false,
			tooltips: true,
			elements: {
				line: {
					fill: false,
				}
			},
			scales: {
		      xAxes: [{
		         ticks: {
		            fontColor: "white",
		         }
		      }],
		      yAxes: [{
		    	  ticks: {
		            fontColor: "white",
		         }
		      }]
		   }
		};
		var ctx  = $('#chart-0').get(0).getContext('2d');
		var chart = new Chart(ctx, {
			type: 'line',
			data: data,
			options: options
		});
	}
	
	var handleBarChart = function(){
		 
		var barChartCanvas = $('#barChart').get(0).getContext('2d')
		var barChart       = new Chart(barChartCanvas);
		var barChartData = {
			labels  : ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
		    datasets: [
		        {
		          label               : 'Spring',
		          backgroundColor     : '#605ca8',
		          data                : [65, 59, 80, 81, 56, 55, 40]
		        },
		        {
		          label               : 'Strut',
		          backgroundColor     : '#ff9800',
		          data                : [28, 48, 40, 19, 86, 27, 90]
		        }
	        ]
		};
		 
		barChartData.datasets[1].fillColor   = '#00a65a';
		barChartData.datasets[1].strokeColor = '#00a65a';
		barChartData.datasets[1].pointColor  = '#00a65a';
	
		var barChartOptions = {
		  type: 'bar',
		  data: barChartData
		};
		
		barChartOptions.datasetFill = false
		new Chart(barChartCanvas, barChartOptions)
	}
	
	return {
		init : function(){
			activeMenu();
			handleDonutCart();
			handleLineChart();
			handleBarChart();
			handleChart();
		}
	};
	
}();