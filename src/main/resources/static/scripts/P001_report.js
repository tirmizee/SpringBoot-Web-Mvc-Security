var ReportModule = function(){
	
	var data = [];
	
	var activeMenu = function(){
		 $('ul.sidebar-menu > li.treeview-report').addClass('active');
	}
	
	var loadData = function(){
		AjaxManager.GetData(null, 'api/geography/countprovince/',
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
		AjaxManager.GetData(null, 'api/geography/countprovince/',
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
	  	
		var ctx  = $('#chart').get(0).getContext('2d');
		var myChart = new Chart(ctx, {
		    type: 'bar',
		    data: {
		        labels: ["Red", "Blue", "Yellow", "Green", "Purple", "Orange"],
		        datasets: [
		        	{
			            label: 'Male',
			            data: [
			            	_randomScalingFactor(20),
			            	_randomScalingFactor(20),
			            	_randomScalingFactor(20),
			            	_randomScalingFactor(20),
			            	_randomScalingFactor(20),
			            	_randomScalingFactor(20)
			            ],
			            backgroundColor: [
			            	'rgba(54, 162, 235, 0.9)',
			                'rgba(54, 162, 235, 0.9)',
			                'rgba(54, 162, 235, 0.9)',
			                'rgba(54, 162, 235, 0.9)',
			                'rgba(54, 162, 235, 0.9)',
			                'rgba(54, 162, 235, 0.9)'
			            ]
			        },
			        {
			            label: 'Famale',
			            data: [
			            	_randomScalingFactor(20),
			            	_randomScalingFactor(20),
			            	_randomScalingFactor(20),
			            	_randomScalingFactor(20),
			            	_randomScalingFactor(20),
			            	_randomScalingFactor(20)
			            ],
			            backgroundColor: [
			            	'rgba(255, 159, 64, 0.9)',
			            	'rgba(255, 159, 64, 0.9)',
			            	'rgba(255, 159, 64, 0.9)',
			            	'rgba(255, 159, 64, 0.9)',
			            	'rgba(255, 159, 64, 0.9)',
			                'rgba(255, 159, 64, 0.9)'
			            ]
			        }
		        ]
		    },
		    options: {
		        scales: {
		            yAxes: [{
		                ticks: {
		                    beginAtZero:true
		                }
		            }]
		        }
		    }
		});
	    
	}
	
	return {
		init : function(){
			activeMenu();
			handleDonutCart();
			handleChart();
		}
	};
	
}();