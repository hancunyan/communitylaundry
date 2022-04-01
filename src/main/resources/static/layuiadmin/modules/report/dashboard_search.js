/**

 @Name： 
 @Author：liumei caolijun

 */
layui.define(['table', 'form', 'laydate'], function(exports) {
	var $ = layui.$,
		laydate = layui.laydate,
		form = layui.form,
		e1 = echarts.init(document.getElementById("e1")),
		e2 = echarts.init(document.getElementById("e2")),
		searchData='';
	form.on('submit(LAY-user-search)', function(data) {
		var field = data.field;
		searchData=field.date;
		reqAllData(0);
		getTheBar()
	});
	laydate.render({
		elem: '#_date',
		type: 'date'
	});
	var option1 = {
		legend: {
			bottom: '0'
		},
		tooltip: {},
		title: {
			text: '体温数据按设备统计',
			left: 'center',
			top: '6',
			textStyle: {
				fontSize: '16',
				color: '#4f5451',
				fontFamily: 'sans-serif'
			}
		},
		color: ['#67ce84', '#796fd6'],
		grid: {
			right: '0',
			left: '30'
		},
		dataset: {
			dimensions: ['product', '正常', '异常'],
			source: []
		},
		xAxis: {
			type: 'category'
		},
		yAxis: {},
		series: [{
				type: 'bar',
				barWidth: '20%',
			},
			{
				type: 'bar',
				barWidth: '20%',
			}
		]
	};
	var option2 = {
		title: {
			text: '体温状态统计',
			left: 'center',
			top: '6',
			textStyle: {
				color: '#4f5451',
				fontSize: '16'
			}
		},
		tooltip: {
			trigger: 'item',
			formatter: '{b} : {c}<br/>{d}%'
		},
		legend: {
			orient: 'vertical',
			left: 'left',
			top: '42',
			data: ['体温正常数', '体温异常数']
		},
		color: ['#67ce84', '#796fd6'],
		series: [{
			type: 'pie',
			radius: '55%',
			center: ['50%', '60%'],
			data: [{
					value: 0,
					name: '体温正常数'
				},
				{
					value: 0,
					name: '体温异常数'
				}
			],
			emphasis: {
				itemStyle: {
					shadowBlur: 10,
					shadowOffsetX: 0,
					shadowColor: 'rgba(0, 0, 0, 0.5)'
				}
			}
		}]
	};
	e1.setOption(option1);
	e2.setOption(option2);
	var dataOjb={};
	var reqArr=[{
		'path':'statis/queryDayNormalCount',
		'domName':'#normalNum',
		'dataObjKey':'normalNum'
	},{
		'path':'statis/queryDayAbnormalCount',
		'domName':'#notNormalNum',
		'dataObjKey':'notNormalNum'
	},{
		'path':'statis/queryDayPunchCount',
		'domName':'#currMarkNum',
		'dataObjKey':'currMarkNum'
	}];
	//面板数据
	function reqAllData(i){
		var pathObj=reqArr[i];
		$.ajax({
			url: pathObj.path,
			type: "get",
			data:{
				days:searchData
			},
			success: function(data) {
				var code=data.optStatusCode;
				if(code==10400){
					var domName=pathObj['domName'];
					var variable=pathObj['dataObjKey'];
					dataOjb[variable]=data.data.count;
					$(domName).text(data.data.count);
				}
			},
			error:function(errMess){
				layer.msg('数据请求失败，请联系管理员！',{icon:7});
			},
			complete:function(){
				if(i>=(reqArr.length-1)){
					return;
				}
				i++;
				if(i==(reqArr.length-1)){
					renderE1();
				}
				reqAllData(i);
			}
		});
	};
	function renderE1(){
		option2.series[0].data[0].value=dataOjb['normalNum'];
		option2.series[0].data[1].value=dataOjb['notNormalNum'];
		e2.setOption(option2);
	};
	reqAllData(0);
	getTheBar();
	function getTheBar(){
		$.ajax({
			url: 'statis/queryDayByDevice',
			type: "get",
			data:{
				days:searchData
			},
			success: function(data) {
				var code=data.optStatusCode;
				if(code==10400){
					var theData=data.data;
					renderTheBar(theData);
				}
			},
			error:function(errMess){
				layer.msg('数据请求失败，请联系管理员！',{icon:7});
			}
		});
	};
	function renderTheBar(theData){
		var sourceArr=[];
		for(var i=0;i<theData.length;i++){
			var dataIt=theData[i];
			var pushObj={};
			pushObj['product']=dataIt.common;
			pushObj['正常']=dataIt['normalCount'];
			pushObj['异常']=dataIt['abnormalCount'];
			sourceArr.push(pushObj);
		}
		option1.dataset.source=sourceArr;
		e1.setOption(option1);
	};
	window.onresize = function() {
		e1.resize();
		e2.resize();
	};
	exports('dashboard_search', {})
});
